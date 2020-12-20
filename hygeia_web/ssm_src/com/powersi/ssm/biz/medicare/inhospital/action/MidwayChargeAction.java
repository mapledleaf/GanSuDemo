package com.powersi.ssm.biz.medicare.inhospital.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120110Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120111Service;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

@Action(value = "MidwayChargeAction", results = {
		@Result(name = "midwayCharge", location = "/pages/biz/medicare/inhospital/MidwayCharge.jsp"),
		@Result(name = "midwayChargeSnyd", location = "/pages/biz/medicare/inhospital/MidwayChargeSnyd.jsp") })
public class MidwayChargeAction extends BaseInhospitalManagerAction {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 中途结算 : 依次做出院登记，出院结算，再重新住院登记
	 * 
	 * 
	 */
	public String midwayCharge() {
		try {
			if (this.isPostRequest()) {
				this.initCtrlInHospitalDTO(aka130_12);
				this.getInHospitalDTO().setBka033(BizHelper.getLoginUser());
				this.getInHospitalDTO().setBka034(BizHelper.getUserName());
				this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
				this.getInHospitalDTO().setBka047(BizHelper.getUserName());
				MCCEbizh120110Service mCCEbizh120110Service = this.getHygeiaServiceManager()
						.getBeanByClass(MCCEbizh120110Service.class, this.getInHospitalDTO().getAkb020());
				InHospitalDTO inHospitalDTO = mCCEbizh120110Service.midwayCharge(this.getInHospitalDTO());
				this.getBeanService().copyProperties(inHospitalDTO, getInHospitalDTO(), true);
				this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的中途结算保存成功！",
						this.getInHospitalDTO().getAaz217());
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_12);
				this.getInHospitalDTO().setBka032(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "midwayCharge";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("中途结算出错："+errLogSn+e.getMessage());
			return ERROR;
		}
	}


	/**
	 * 取消中途结算
	 */
	public String cancelMidwayCharge() {
		try {
			this.getInHospitalDTO().setAaa027(BizHelper.getAaa027());
			this.getInHospitalDTO().setAkb020(BizHelper.getAkb020());
			MCCEbizh120111Service mCCEbizh120111Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120111Service.class, this.getInHospitalDTO().getAkb020());
			mCCEbizh120111Service.cancelMidwayCharge(this.getInHospitalDTO());
			this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的取消中途结算保存成功！",
					this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn+e.getMessage());
		}
		return NONE;
	}
	
	
	/**
	 * 中途结算 : 依次做出院登记，出院结算，再重新住院登记(省内异地)
	 * 
	 * 
	 */
	public String midwayChargeSnyd() {
		try {
			if (this.isPostRequest()) {
				this.initCtrlInHospitalDTO(aka130_72);
				this.getInHospitalDTO().setBka033(BizHelper.getLoginUser());
				this.getInHospitalDTO().setBka034(BizHelper.getUserName());
				this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
				this.getInHospitalDTO().setBka047(BizHelper.getUserName());
				MCCEbizh120110Service mCCEbizh120110Service = this.getHygeiaServiceManager()
						.getBeanByClass(MCCEbizh120110Service.class, this.getInHospitalDTO().getAkb020());
				InHospitalDTO InHospitalDTOZtjs = mCCEbizh120110Service.midwayCharge(this.getInHospitalDTO());
				this.getBeanService().copyProperties(InHospitalDTOZtjs, getInHospitalDTO(), true);
				this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的省内异地中途结算保存成功！",
						this.getInHospitalDTO().getAaz217());
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_72);
				this.getInHospitalDTO().setBka032(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "midwayChargeSnyd";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("省内异地中途结算出错："+errLogSn+e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 取消省内异地中途结算
	 */
	public String doMidwayChargeSnyd() {
		try {
			this.getInHospitalDTO().setAaa027(BizHelper.getAaa027());
			this.getInHospitalDTO().setAkb020(BizHelper.getAkb020());
			MCCEbizh120111Service mCCEbizh120111Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120111Service.class, this.getInHospitalDTO().getAkb020());
			mCCEbizh120111Service.cancelMidwayCharge(this.getInHospitalDTO());
			this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的取消异地就医中途结算保存成功！",
					this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn+e.getMessage());
		}
		return NONE;
	}

}
