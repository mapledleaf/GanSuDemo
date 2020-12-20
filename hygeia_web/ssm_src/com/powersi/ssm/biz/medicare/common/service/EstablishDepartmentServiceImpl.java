package com.powersi.ssm.biz.medicare.common.service;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.powersi.asyn.service.AsynService;
import com.powersi.biz.medicare.comm.pojo.KB01;
import com.powersi.biz.medicare.comm.service.ProjectAssistService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.comm.service.memory.MemoryDBWrapper;
import com.powersi.config.pojo.BizYyInfo;
import com.powersi.hygeia.comm.service.ConfigService;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.mq.Consumer;
import com.powersi.pcloud.dict.service.DictService;
import com.powersi.ssm.biz.medicare.common.dao.EstablishDepartmentDAO;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

/**
 * 
 * @author 刘勇
 * 
 *         <pre>
 * 李志钢补充说明： 本类负责监听医院创建的消息，一旦新医院创建，本类负责建立默认管理员等操作
 *         </pre>
 */
@Service
public class EstablishDepartmentServiceImpl implements EstablishDepartmentService {

	private static final long serialVersionUID = 1L;
	private final String queue = "MESSAGE_CREATE_YYINFO";
	private final Logger logger = LoggerFactory.getLogger(EstablishDepartmentServiceImpl.class);
	@Autowired
	@Qualifier("consumer_core")
	private Consumer consumer;
	@Autowired
	@Qualifier("memoryDBWrapper_core")
	private MemoryDBWrapper memoryDBWrapper;
	@Autowired
	private CommunalService communalService;
	@Autowired
	private EstablishDepartmentDAO establishDepartmentDAO;
	@Autowired
	private ConfigService configService;
	@Autowired
	@Qualifier("asynService")
	private AsynService asynService;
	@Autowired
	private DictService dictService;
	@Autowired
	private ProjectAssistService projectAssistService;

	@PostConstruct
	public void init() {
		this.asynService.addWork(this, "postInit");
	}

	/**
	 * 
	 */
	public void postInit() {
		if (projectAssistService.isWhetherDeveloperEnvironment()) {
			return;
		}
		this.consumer.createConsumer(queue + this.configService.getTcjgbm(), this);
	}

	@Override
	public void onMessageArrived(String msgName, Object msgParam) {
		try {
			String yybm = (String) msgParam;
			BizYyInfo yyInfo = (BizYyInfo) this.memoryDBWrapper.getMemoryDB().getMapValue("MAP_BIZ_YY_INFO", yybm);

			if (yyInfo == null) {
				throw new HygeiaException("memoryDB找不到医院信息，编码：" + yybm);
			}
			this.establishDepartment(yyInfo);
		} catch (Exception e) {
			this.communalService.error(this.logger, e, "响应医院创建处理失败");
		}
	}

	/**
	 * <pre>
	 * 1、创建医院部门到默认部门之下;
	 * 2、创建医院默认管理员用户关联到默认角色;
	 * </pre>
	 * 
	 * @param yyInfo
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void establishDepartment(BizYyInfo yyInfo) {
		if (yyInfo == null) {
			return;
		}
		if (StringUtils.isBlank(yyInfo.getYybm())) {
			return;
		}
		if (StringUtils.isBlank(yyInfo.getYymc())) {
			throw new HygeiaException("医院缓存医院名称为空，医院编码：" + yyInfo.getYybm());
		}
		if (StringUtils.isBlank(yyInfo.getTcqbm())) {
			throw new HygeiaException("医院缓存医院所在统筹区编码为空，医院编码：" + yyInfo.getYybm());
		}
		try {
			Map map = DBHelper.executeMap("select * from kb01 where akb020 = ? and aae100 = '1'",
					new Object[] { yyInfo.getYybm() });
			if (map != null && map.size() > 0) {
				return;
			}
			String dept_id = this.getMaxNo("dept_id");
			String staff_id = this.getMaxNo("staff_id");
			String staff_name = "定点医院系统管理员";
			this.establishDepartmentDAO.createDept(dept_id, yyInfo.getYymc());
			this.establishDepartmentDAO.createStaff(staff_id, yyInfo.getYybm(), staff_name, yyInfo.getTcqbm(), dept_id);

			this.establishDepartmentDAO.createSysUser(staff_id, staff_name, yyInfo.getYybm());
			// 根据不同的业务经办类型绑定不同的角色
			this.establishDepartmentDAO.urbanRole(staff_id, yyInfo.getYybm(), staff_name,
					dictService.getValue("HGYEIA_WEB_ROLE_YWJB_TYPE", yyInfo.getYwjb_type().toString().trim()));
			String bka501 = yyInfo.getTcqbm();
			String aaa027 = BizHelper.getAaa027ByBka501(bka501);
			KB01 kb01 = new KB01();
			kb01.setKb01id(this.communalService.uuid());
			kb01.setDept_id(dept_id);
			kb01.setAkb020(yyInfo.getYybm());
			kb01.setAkb021(yyInfo.getYymc());
			kb01.setAaa027(StringUtils.isBlank(aaa027) ? bka501 : aaa027);
			kb01.setAae100("1");
			DAOHelper.insert("kb01", kb01);
			DBHelper.commit();
		} catch (Exception e) {
			DBHelper.rollback();
			throw new HygeiaException(e);
		} finally {
			DBHelper.close();
		}
	}

	/**
	 * 
	 * @param MaxNo_Name
	 * @return
	 */
	private String getMaxNo(String MaxNo_Name) {
		return String.valueOf(SysFunc.getMaxNo(MaxNo_Name));
	}

}
