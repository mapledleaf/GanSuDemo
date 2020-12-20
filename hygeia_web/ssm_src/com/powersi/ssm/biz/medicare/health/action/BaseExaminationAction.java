package com.powersi.ssm.biz.medicare.health.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.powersi.biz.medicare.health.pojo.SetMealDTO;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.log.service.ErrLogSnService;
import com.powersi.log.service.ErrLogSnService.ProjectType;
import com.powersi.pcloud.medicare.comm.utils.MedicareCommUtils;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

public class BaseExaminationAction  extends BaseAction{
	private static final long serialVersionUID = 1L;
	private SetMealDTO setMealDTO = null;
	@Autowired
	private DateService dateService;

	@Autowired
	@Qualifier("errLogSnService")
	private ErrLogSnService errLogSnService;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private BeanService beanService;
	@Autowired 
	private CommunalService communalService;
	
	
	
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

	public DateService getDateService() {
		return dateService;
	}

	public void setDateService(DateService dateService) {
		this.dateService = dateService;
	}

	public SetMealDTO getSetMealDTO() {
		if (setMealDTO == null) {
			setMealDTO = new SetMealDTO();
		}
		return setMealDTO;
	}

	public void setSetMealDTO(SetMealDTO setMealDTO) {
		this.setMealDTO = setMealDTO;
	}
	
	/**
	 * 
	 * @return
	 */
	public String addErrSNInfo() {
		String errLogSn = this.getErrLogSnService().getErrSN(ProjectType.WEB);
		errLogSn = "错误号 " + errLogSn + " 信息： ";
		return errLogSn;
	}

	public ErrLogSnService getErrLogSnService() {
		return errLogSnService;
	}

	public void setErrLogSnService(ErrLogSnService errLogSnService) {
		this.errLogSnService = errLogSnService;
	}

	@Override
	public void saveJSONError(String message) {
		if (StringUtils.isNotBlank(message)) {
			message = MedicareCommUtils.thinErrorMessage(message);
		}
		this.saveJSONError(message, null);
	}


	/**
	 * 
	 * @param aka130Code
	 *            业务类别编码
	 */
	public void initCtrlInHospitalDTO() {
		this.getSetMealDTO().setAaa027(BizHelper.getAaa027());
		this.getSetMealDTO().setAkb020(BizHelper.getAkb020());
		this.getSetMealDTO().setBkh016(this.getDateService().dateToString(new Date(), DateService.yyyyMMdd));
		if(StringUtils.isNotBlank(this.getSetMealDTO().getIndi_id())) {
			this.getSetMealDTO().setAac001(this.getSetMealDTO().getIndi_id());
		}
		if(StringUtils.isNotBlank(this.getSetMealDTO().getIdcard())) {
			this.getSetMealDTO().setAac002(this.getSetMealDTO().getIdcard());
		}
		if(StringUtils.isNotBlank(this.getSetMealDTO().getInsr_code())) {
			this.getSetMealDTO().setAaz500(this.getSetMealDTO().getInsr_code());
		}
		
		//TS19080100103 - 【问题修复】【体检信息召回】-无法根据人员信息控件对数据进行过滤
		//修改人：daliang.long
		//修改时间：20190803
		if("idcard".equals(this.getSetMealDTO().getArg_name())) {
			this.getSetMealDTO().setAac002(this.getSetMealDTO().getQuerystring());
		}
		if("indi_id".equals(this.getSetMealDTO().getArg_name())) {
			this.getSetMealDTO().setAac001(this.getSetMealDTO().getQuerystring());
		}
		if("patient_id".equals(this.getSetMealDTO().getArg_name())) {
			this.getSetMealDTO().setAaz217(this.getSetMealDTO().getQuerystring());
		}
		
		
		
	}
	
	public void loadHidDataAndItemName() {
		if(StringUtils.isNotBlank(this.getSetMealDTO().getAac004())) {
			this.getSetMealDTO().setAac004_name(CodetableMapping.getDisplayValue("aac004",
					this.getSetMealDTO().getAac004(), this.getSetMealDTO().getAac004()));
		}
		if(StringUtils.isNotBlank(this.getSetMealDTO().getBka035())) {
			this.getSetMealDTO().setBka035_name(CodetableMapping.getDisplayValue("bka035", this.getSetMealDTO().getBka035(),this.getSetMealDTO().getBka035()));
		}
		if(StringUtils.isNotBlank(this.getSetMealDTO().getBac001())) {
			this.getSetMealDTO().setBac001_name(CodetableMapping.getDisplayValue("bac001", this.getSetMealDTO().getBac001(),this.getSetMealDTO().getBac001()));
		}
		if(StringUtils.isNotBlank(this.getSetMealDTO().getBkh015())) {
			this.getSetMealDTO().setBkh015_name(CodetableMapping.getDisplayValue("bkh015", this.getSetMealDTO().getBkh015()));
		}
		if("1".equals(this.getSetMealDTO().getBkh002())) {
			this.getSetMealDTO().setBkh002_name("已审核");
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
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
}
