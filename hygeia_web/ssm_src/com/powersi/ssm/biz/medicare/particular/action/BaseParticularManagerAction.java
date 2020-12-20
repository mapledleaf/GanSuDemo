package com.powersi.ssm.biz.medicare.particular.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.powersi.biz.medicare.comm.pojo.HospDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.log.service.ErrLogSnService;
import com.powersi.log.service.ErrLogSnService.ProjectType;
import com.powersi.ssm.biz.medicare.common.service.QueryStringService;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

/**
 * 
 * @author 宋程杰、刘勇
 *
 */
public class BaseParticularManagerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private InHospitalDTO inHospitalDTO = null;
	private HospDTO hospDTO = null;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private BeanService beanService;
	@Autowired
	private CommunalService communalService;
	@Autowired
	private QueryStringService queryStringService;
	@Autowired
	private DateService dateService;
	@Autowired
	@Qualifier("errLogSnService")
	private ErrLogSnService errLogSnService;
	
	public String addErrSNInfo() {
		String errLogSn = this.getErrLogSnService().getErrSN(ProjectType.WEB);
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
	@Override
	public void saveJSONError(String message) {
		if (StringUtils.isNotBlank(message)) {
			message = message.replaceAll("java.lang.RuntimeException:", "");
		}
		this.saveJSONError(message, null);
	}

	@Override
	public void saveError(String message) {
		if (StringUtils.isNotBlank(message)) {
			message = message.replaceAll("java.lang.RuntimeException:", "");
		}
		this.saveError(message, null);
	}

	public ErrLogSnService getErrLogSnService() {
		return errLogSnService;
	}

	public void setErrLogSnService(ErrLogSnService errLogSnService) {
		this.errLogSnService = errLogSnService;
	}

	public void loadHidDataAndItemName() {
		// 转诊转院标识 1--转诊 2--转院
		if (StringUtils.isNotBlank(this.getInHospitalDTO().getBka600())) {
			this.getInHospitalDTO()
					.setBka600_name(CodetableMapping.getDisplayValue("bka600$" + this.getInHospitalDTO().getAaa027(),
							this.getInHospitalDTO().getBka600(), this.getInHospitalDTO().getBka600()));
		}
	}

	/**
	 * 对单条记录的InHospitalDTO进行转义 主要给获取结果为LIST<InHospitalDTO>的方法调用
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public void loadHidDataAndItemName(InHospitalDTO inHospitalDTO) {
		// 转诊转院标识 1--转诊 2--转院
		if (inHospitalDTO != null && StringUtils.isNotBlank(inHospitalDTO.getBka600())) {
			inHospitalDTO.setBka600_name(CodetableMapping.getDisplayValue("bka600$" + inHospitalDTO.getAaa027(),
					inHospitalDTO.getBka600(), inHospitalDTO.getBka600()));
		}
		// 审核状态 0--未审批 1--审批通过 2--审批不通过
		if (inHospitalDTO != null && StringUtils.isNotBlank(inHospitalDTO.getBke058())) {
			inHospitalDTO.setBke058_name(
					CodetableMapping.getDisplayValue("bke058", inHospitalDTO.getBke058(), inHospitalDTO.getBke058()));
		}
	}

	/**
	 * 
	 */
	public void initCtrlInHospitalDTO() {
		this.initCtrlInHospitalDTO(null);
	}

	/**
	 * 
	 * @param aka130Code
	 */
	public void initCtrlInHospitalDTO(String aka130Code) {
		this.getInHospitalDTO().setAaa027(BizHelper.getAaa027());
		this.getInHospitalDTO().setAaa027_name(BizHelper.getAaa027Name());
		this.getInHospitalDTO().setAkb020(BizHelper.getAkb020());
		this.getInHospitalDTO().setAkb021(BizHelper.getAkb021());
		this.getInHospitalDTO().setAae011(BizHelper.getUserName());
		this.getInHospitalDTO().setAae036(new Date());
		this.getQueryStringService().convertQueryString(this.getInHospitalDTO());
		if (StringUtils.isBlank(aka130Code)) {
			return;
		}
		this.getInHospitalDTO().setAka130(aka130Code);
		this.getInHospitalDTO().setAka130_name(CodetableMapping.getDisplayValue("aka130", aka130Code, aka130Code));
	}

	public InHospitalDTO getInHospitalDTO() {
		if (inHospitalDTO == null) {
			inHospitalDTO = new InHospitalDTO();
		}
		return inHospitalDTO;
	}

	public void setInHospitalDTO(InHospitalDTO inHospitalDTO) {
		this.inHospitalDTO = inHospitalDTO;
	}

	public HospDTO getHospDTO() {
		if (hospDTO == null) {
			hospDTO = new HospDTO();
		}
		return hospDTO;
	}

	public void setHospDTO(HospDTO hospDTO) {
		this.hospDTO = hospDTO;
	}

	public HygeiaServiceManager getHygeiaServiceManager() {
		return hygeiaServiceManager;
	}

	public void setHygeiaServiceManager(HygeiaServiceManager hygeiaServiceManager) {
		this.hygeiaServiceManager = hygeiaServiceManager;
	}

	public BeanService getBeanService() {
		return beanService;
	}

	public void setBeanService(BeanService beanService) {
		this.beanService = beanService;
	}

	public CommunalService getCommunalService() {
		return communalService;
	}

	public void setCommunalService(CommunalService communalService) {
		this.communalService = communalService;
	}

	public QueryStringService getQueryStringService() {
		return queryStringService;
	}

	public void setQueryStringService(QueryStringService queryStringService) {
		this.queryStringService = queryStringService;
	}

	public DateService getDateService() {
		return dateService;
	}

	public void setDateService(DateService dateService) {
		this.dateService = dateService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
