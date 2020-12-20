package com.powersi.ssm.biz.medicare.query.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.powersi.biz.medicare.comm.pojo.ParamDTO;
import com.powersi.biz.medicare.iccard.pojo.ICInfoDTO;
import com.powersi.biz.medicare.iccard.service.SSCardService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.log.service.ErrLogSnService;
import com.powersi.log.service.ErrLogSnService.ProjectType;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.common.util.Utils;

/**
 * ic卡管理
 * @author ChenXing
 *
 */
public class IcCardAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private CommunalService communalService;
	@Autowired
	@Qualifier("errLogSnService")
	private ErrLogSnService errLogSnService;
	/**
	 * 医保卡密码修改
	 */
	public void ylcardPswModify() {
		try {
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			String icinfoStr = getParameter("icinfo");
			String caa027 = getParameter("caa027");
			String newpwd = getParameter("newpwd");
			ICInfoDTO icInfo = Utils.mapToBean(JSON.parseObject(icinfoStr), ICInfoDTO.class, true);
			icInfo.setAkb020(akb020);
			icInfo.setAaa027(aaa027);
			icInfo.setCaa027(caa027);
			icInfo.put("newpwd", newpwd);
			icInfo.put("staff_id", BizHelper.getLoginUser());
			icInfo.put("staff_name", BizHelper.getUserName());
			SSCardService ssCardService = hygeiaServiceManager.getBeanByClass(SSCardService.class, akb020); // 卡服务类
			setJSONReturn(ssCardService.ylcardPswModify(icInfo));
		} catch (IOException e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
	}
	
	/**
	 * 卡挂失、启用
	 */
	public void cardReportLoss() {
		try {
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			Map<String, Object> userinfoMap = JSON.parseObject(getParameter("userinfo")); // 个人信息
			String opt_flag = getParameter("opt_flag"); // 操作类型，参考中心BIZC200904接口opt_flag字段取值
			String remark = getParameter("remark"); // 备注说明
			if (UtilFunc.isEmpty(userinfoMap))
				throw new HygeiaException("传入的个人信息错误");
			if (opt_flag.equals(userinfoMap.get("sta_code")))
				throw new HygeiaException("卡当前已是[" + userinfoMap.get("sta_code_name") + "]状态");
			ParamDTO params = new ParamDTO();
			params.putAll(userinfoMap);
			params.setAaa027(aaa027);
			params.setCaa027("4306_zg");
			params.setAkb020(akb020);
			params.put("opt_flag", opt_flag);
			params.put("remark", remark);
			params.put("staff_id", BizHelper.getLoginUser());
			SSCardService ssCardService = hygeiaServiceManager.getBeanByClass(SSCardService.class, akb020); // 卡服务类
			setJSONReturn(ssCardService.cardReportLoss(params.toMap()));
		} catch (IOException e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String findActiveCardData() {
		try {
			Map param = new HashMap<>();
			param.put("aac002", getParameter("aac002"));
			param.put("aaz500", getParameter("aaz500"));
			param.put("caa027", getParameter("caa027"));
			param.put("aaa027", BizHelper.getAaa027());
			SSCardService sSCardService = hygeiaServiceManager.getBeanByClass(SSCardService.class, 
					BizHelper.getAkb020());
			Map resultInfo = sSCardService.findActiveCardData(param);
			setJSONReturn(resultInfo);
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
			return ERROR;
		}
		return NONE;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String saveSicCardInfo() {
		try {
			SSCardService sSCardService = hygeiaServiceManager.getBeanByClass(SSCardService.class, 
					BizHelper.getAkb020());
			Map cardInfo = JsonHelper.toMap(getParameter("cardInfo"));
			cardInfo.put("aac001", getParameter("aac001"));
			cardInfo.put("caa027", getParameter("caa027"));
			cardInfo.put("org_type", getParameter("org_type"));
			cardInfo.put("sExecFlag", "activeIC");
			cardInfo.put("aaa027", BizHelper.getAaa027());
			sSCardService.SaveSicCardInfo(cardInfo);
			setJSONReturn("社保卡激活成功！");
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
			return ERROR;
		}
		return NONE;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String saveBackCardInfo() {
		try {
			SSCardService sSCardService = hygeiaServiceManager.getBeanByClass(SSCardService.class, 
					BizHelper.getAkb020());
			Map cardInfo = JsonHelper.toMap(getParameter("cardInfo"));
			cardInfo.put("aac001", getParameter("aac001"));
			cardInfo.put("caa027", getParameter("caa027"));
			cardInfo.put("org_type", getParameter("org_type"));
			cardInfo.put("aaa027", BizHelper.getAaa027());
			cardInfo.put("sExecFlag", "backActiveIC");
			sSCardService.SaveSicCardInfo(cardInfo);
			setJSONReturn("社保卡激活回退成功！");
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
			return ERROR;
		}
		return NONE;
	}
	
	public String addErrSNInfo() {
		String errLogSn = this.errLogSnService.getErrSN(ProjectType.WEB);
		errLogSn = "错误号 " + errLogSn + " 信息： ";
		return errLogSn;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String addNotBlankParameters() {
		Map allParameters = this.getAllParameters();
		if (allParameters == null || allParameters.size() == 0) {
			return "{}";
		}
		Map parameters = new HashMap();
		String key = "", value = "";
		Object objValue = null;
		Iterator it = allParameters.keySet().iterator();
		while (it.hasNext()) {
			key = it.next().toString();
			objValue = allParameters.get(key);
			if (objValue != null) {
				value = objValue.toString();
				if (StringUtils.isNotBlank(value)) {
					parameters.put(key, value);
				}
			}
		}
		return parameters.toString();
	}

	public CommunalService getCommunalService() {
		return communalService;
	}

	public void setCommunalService(CommunalService communalService) {
		this.communalService = communalService;
	}
}
