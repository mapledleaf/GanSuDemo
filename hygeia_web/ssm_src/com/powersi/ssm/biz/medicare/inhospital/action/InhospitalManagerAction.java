package com.powersi.ssm.biz.medicare.inhospital.action;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.powersi.ssm.biz.medicare.common.util.GanSuDemoUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.powersi.biz.medicare.comm.pojo.KA06;
import com.powersi.biz.medicare.comm.pojo.KA06DTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.service.DiseaseQueryService;
import com.powersi.biz.medicare.comminter.pojo.KAB3DTO;
import com.powersi.biz.medicare.comminter.service.InvoiceManagerService;
import com.powersi.biz.medicare.hosp.pojo.Bkn2Dto;
import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;
import com.powersi.biz.medicare.hosp.service.api.HospApiService;
import com.powersi.biz.medicare.inhospital.pojo.FundPayInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.pojo.Kcg4DTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120001Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120002Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120003Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120004Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120102Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120103Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120104Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120105Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120106Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120107Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120108Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120109Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120133Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120203Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120302Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120312Service;
import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.biz.medicare.query.dto.KCD1Log;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.util.DataGridHelper;
import com.powersi.hygeia.web.util.PagerHelper;
import com.powersi.pcloud.dict.service.DictService;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEHospApiServiceImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120001ServiceImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120002ServiceImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120003ServiceImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120102ServiceImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120103ServiceImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120104ServiceImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120106ServiceImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120107ServiceImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120109ServiceImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120203ServiceImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120302ServiceImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120312ServiceImpl;
import com.powersi.ssm.biz.medicare.common.service.QueryStringService;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.inhospital.service.InhospitalManagerService;
import com.powersi.ssm.biz.medicare.jdreport.util.MoneyUtil;

/**
 * 
 * 住院管理
 * 
 * @author 刘勇
 *
 */
@Action(value = "InhospitalManagerAction", results = {
		@Result(name = "applyBack", location = "/pages/biz/medicare/inhospital/applyBack.jsp"),
		@Result(name = "registerKsyd", location = "/pages/biz/medicare/inhospital/RegisterKsyd.jsp"),
		@Result(name = "registerSnyd", location = "/pages/biz/medicare/inhospital/RegisterSnyd.jsp"),
		@Result(name = "registerYdjy", location = "/pages/biz/medicare/inhospital/RegisterYdjy.jsp"),
		@Result(name = "registerMaternity", location = "/pages/biz/medicare/inhospital/RegisterMaternity.jsp"),
		@Result(name = "registerInjury", location = "/pages/biz/medicare/inhospital/RegisterInjury.jsp"),
		@Result(name = "registerSpecial", location = "/pages/biz/medicare/inhospital/RegisterSpecial.jsp"),
		@Result(name = "register", location = "/pages/biz/medicare/inhospital/Register.jsp"),
		@Result(name = "registerHomeSickbed", location = "/pages/biz/medicare/inhospital/RegisterHomeSickbed.jsp"),
		@Result(name = "modifyRegisterKsyd", location = "/pages/biz/medicare/inhospital/ModifyRegisterKsyd.jsp"),
		@Result(name = "modifyRegisterSnyd", location = "/pages/biz/medicare/inhospital/ModifyRegisterSnyd.jsp"),
		@Result(name = "modifyRegisterYdjy", location = "/pages/biz/medicare/inhospital/ModifyRegisterYdjy.jsp"),
		@Result(name = "modifyRegisterMaternity", location = "/pages/biz/medicare/inhospital/ModifyRegisterMaternity.jsp"),
		@Result(name = "modifyRegisterInjury", location = "/pages/biz/medicare/inhospital/ModifyRegisterInjury.jsp"),
		@Result(name = "modifyRegisterSpecial", location = "/pages/biz/medicare/inhospital/ModifyRegisterSpecial.jsp"),
		@Result(name = "modifyRegister", location = "/pages/biz/medicare/inhospital/ModifyRegister.jsp"),
		@Result(name = "modifyRegisterHomeSickbed", location = "/pages/biz/medicare/inhospital/ModifyRegisterHomeSickbed.jsp"),
		@Result(name = "inputFeeSnyd", location = "/pages/biz/medicare/inhospital/InputFeeSnyd.jsp"),
		@Result(name = "inputFeeKsyd", location = "/pages/biz/medicare/inhospital/InputFeeKsyd.jsp"),
		@Result(name = "inputFeeYdjy", location = "/pages/biz/medicare/inhospital/InputFeeYdjy.jsp"),
		@Result(name = "inputFeeMaternity", location = "/pages/biz/medicare/inhospital/InputFeeMaternity.jsp"),
		@Result(name = "inputFeeInjury", location = "/pages/biz/medicare/inhospital/InputFeeInjury.jsp"),
		@Result(name = "inputFeeSpecial", location = "/pages/biz/medicare/inhospital/InputFeeSpecial.jsp"),
		@Result(name = "inputFee", location = "/pages/biz/medicare/inhospital/InputFee.jsp"),
		@Result(name = "inputFeeHomeSickbed", location = "/pages/biz/medicare/inhospital/InputFeeHomeSickbed.jsp"),
		@Result(name = "inputFeeComminter", location = "/pages/biz/medicare/comminter/InputFeeComminter.jsp"),
		@Result(name = "outRegisterKsyd", location = "/pages/biz/medicare/inhospital/OutRegisterKsyd.jsp"),
		@Result(name = "outRegisterSnyd", location = "/pages/biz/medicare/inhospital/OutRegisterSnyd.jsp"),
		@Result(name = "outRegisterMaternity", location = "/pages/biz/medicare/inhospital/OutRegisterMaternity.jsp"),
		@Result(name = "outRegisterInjury", location = "/pages/biz/medicare/inhospital/OutRegisterInjury.jsp"),
		@Result(name = "outRegisterSpecial", location = "/pages/biz/medicare/inhospital/OutRegisterSpecial.jsp"),
		@Result(name = "outRegister", location = "/pages/biz/medicare/inhospital/OutRegister.jsp"),
		@Result(name = "outRegisterHomeSickbed", location = "/pages/biz/medicare/inhospital/OutRegisterHomeSickbed.jsp"),
		@Result(name = "outChargeKsydCorrect", location = "/pages/biz/medicare/inhospital/OutChargeKsydCorrect.jsp"),
		@Result(name = "outChargeKsyd", location = "/pages/biz/medicare/inhospital/OutChargeKsyd.jsp"),
		@Result(name = "outChargeSnyd", location = "/pages/biz/medicare/inhospital/OutChargeSnyd.jsp"),
		@Result(name = "outChargeYdjy", location = "/pages/biz/medicare/inhospital/OutChargeYdjy.jsp"),
		@Result(name = "outChargeMaternity", location = "/pages/biz/medicare/inhospital/OutChargeMaternity.jsp"),
		@Result(name = "outChargeInjury", location = "/pages/biz/medicare/inhospital/OutChargeInjury.jsp"),
		@Result(name = "outChargeSpecial", location = "/pages/biz/medicare/inhospital/OutChargeSpecial.jsp"),
		@Result(name = "outCharge", location = "/pages/biz/medicare/inhospital/OutCharge.jsp"),
		@Result(name = "outChargeHomeSickbed", location = "/pages/biz/medicare/inhospital/OutChargeHomeSickbed.jsp"),
		@Result(name = "outChargeComminter", location = "/pages/biz/medicare/comminter/OutChargeComminter.jsp"),
		@Result(name = "queryInHospitalKsyd", location = "/pages/biz/medicare/inhospital/QueryInHospitalKsyd.jsp"),
		@Result(name = "queryInHospitalSnyd", location = "/pages/biz/medicare/inhospital/QueryInHospitalSnyd.jsp"),
		@Result(name = "queryInHospitalYdjy", location = "/pages/biz/medicare/inhospital/QueryInHospitalYdjy.jsp"),
		@Result(name = "queryInHospitalMaternity", location = "/pages/biz/medicare/inhospital/QueryInHospitalMaternity.jsp"),
		@Result(name = "queryInHospitalInjury", location = "/pages/biz/medicare/inhospital/QueryInHospitalInjury.jsp"),
		@Result(name = "queryInHospitalSpecial", location = "/pages/biz/medicare/inhospital/QueryInHospitalSpecial.jsp"),
		@Result(name = "queryInHospComminter", location = "/pages/biz/medicare/comminter/QueryInHospitalComminter.jsp"),
		@Result(name = "queryInHospitalHomeSickbed", location = "/pages/biz/medicare/inhospital/QueryInHospitalHomeSickbed.jsp"),
		@Result(name = "queryInHospital", location = "/pages/biz/medicare/inhospital/QueryInHospital.jsp"),
		@Result(name = "queryForegiftDetail", location = "/pages/biz/medicare/inhospital/QueryForegiftDetail.jsp"),
		@Result(name = "printForegift", location = "/pages/biz/medicare/inhospital/QueryForegiftPrint.jsp"),
		@Result(name = "printForegiftDetail", location = "/pages/biz/medicare/inhospital/QueryForegiftPrintDetail.jsp"),
		@Result(name = "queryForegift", location = "/pages/biz/medicare/inhospital/QueryForegift.jsp"),
		@Result(name = "queryDiagnose", location = "/pages/biz/medicare/comm/QueryMoreDiagnoseInfo.jsp"),
		@Result(name = "choose106SubDisease", location = "/pages/biz/medicare/inhospital/choose106SubDisease.jsp") })
public class InhospitalManagerAction extends BaseInhospitalManagerAction {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 费用日期
	 */
	private String ake007;
	/**
	 * 当前日期
	 */
	private String bka890;
	/**
	 * 生育必填
	 */
	private boolean maternityRequired = false;

	/**
	 * 106单病种疾病编码
	 */
	private String aka120106;
	@Autowired
	private DictService dictService;

	@Autowired
	private InhospitalManagerService inhospitalManagerService;

	/**
	 * 回退申请
	 * 
	 * @return
	 */
	public String applyBack() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_12);
					this.getInHospitalDTO().setAae011(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka015(BizHelper.getUserName());
					this.getInHospitalDTO().setBka013(DateFunc.dateToString(new Date(), CommunalService.yyyyMMdd));
					MCCEbizh120102Service mCCEbizh120102Service = getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					mCCEbizh120102Service.checkBiz(this.getInHospitalDTO());
					MCCEbizh120133Service mCCEbizh120133Service = getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120133Service.class, this.getInHospitalDTO().getAkb020());
					mCCEbizh120133Service.fallbackApplySave(this.getInHospitalDTO());
					this.setJSONReturn("申请回退成功！", this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				return "applyBack";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 待遇类型判断
	 * 
	 * @return
	 */
	public String selectbka006() {
		try {
			this.initCtrlInHospitalDTO();
			if ("123".equals(this.getInHospitalDTO().getBka006())
					&& "310".equals(this.getInHospitalDTO().getAae140())) {
				throw new HygeiaException("城镇职工参保人不允许办理城乡居民生育住院业务!");
			}
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 通用接口费用记账
	 */
	public String inputFeeComminter() {
		this.initCtrlInHospitalDTO(aka130_12);
		this.setAke007(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
		return "inputFeeComminter";
	}

	/**
	 * 通用接口出院结算
	 */
	public String outChargeComminter() {
		this.initCtrlInHospitalDTO(aka130_12);
		return "outChargeComminter";
	}

	/**
	 * 通用接口住院回退初始化界面
	 * 
	 * @return
	 */
	public String queryInHospComminter() {
		this.initCtrlInHospitalDTO(aka130_12);
		this.getInHospitalDTO().setAae030(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
		this.getInHospitalDTO().setAae031(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
		this.getInHospitalDTO().setBka891("0");
		return "queryInHospital";
	}

	/**
	 * 
	 * 读卡解析返回卡号
	 * 
	 * @return
	 */
	public String readic() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO();
					this.setJSONReturn(this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO();
				return "readic";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 跨省异地就医入院登记
	 * 
	 * @return
	 */
	public String registerKsyd() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_82);
					this.getInHospitalDTO().setAae011(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka015(BizHelper.getUserName());
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka020())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
						this.getInHospitalDTO().setBka020(this.getInHospitalDTO().getAkf001());
					}
					if (StringUtils.isBlank(getInHospitalDTO().getBka022())
							&& StringUtils.isNotBlank(getInHospitalDTO().getBka021())) {
						this.getInHospitalDTO().setBka022(this.getInHospitalDTO().getBka021());
					}
					MCCEbizh120103Service mCCEbizh120103Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120103Service.class, getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120103Service.saveRegisterInfo(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的跨省异地就医入院登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_82);
				this.loadCodeSelectData();
				this.getInHospitalDTO().setBka017(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "registerKsyd";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 省内异地就医登记
	 * 
	 * @return
	 */
	public String registerSnyd() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_72);
					this.getInHospitalDTO().setAae011(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka015(BizHelper.getUserName());
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka020())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
						this.getInHospitalDTO().setBka020(this.getInHospitalDTO().getAkf001());
					}
					if (StringUtils.isBlank(getInHospitalDTO().getBka022())
							&& StringUtils.isNotBlank(getInHospitalDTO().getBka021())) {
						this.getInHospitalDTO().setBka022(this.getInHospitalDTO().getBka021());
					}
					MCCEbizh120103Service mCCEbizh120103Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120103Service.class, getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120103Service.saveRegisterInfo(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的省内异地就医登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_72);
				this.loadCodeSelectData();
				this.getInHospitalDTO().setAae030(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "registerSnyd";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 异地就医登记（包括省内、省外）
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String registerYdjy() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_12);
					this.getInHospitalDTO().setAae011(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka015(BizHelper.getUserName());

					String akf001 = this.getInHospitalDTO().getAkf001();
					String bka021 = this.getInHospitalDTO().getBka021();
					String ake022 = this.getInHospitalDTO().getAke022();
					Map akf001List = new LinkedHashMap();
					this.loadAkf001List(akf001List);
					if (akf001List.containsKey(akf001)) {
						this.getInHospitalDTO().setBka020(akf001List.get(akf001).toString());
					}
					Map bka021List = this.loadBka021Map();
					if (bka021List.containsKey(bka021)) {
						this.getInHospitalDTO().setBka022(bka021List.get(bka021).toString());
					}

					Map ake022List = this.loadAke022Map();
					if (ake022List.containsKey(ake022)) {
						this.getInHospitalDTO().setAke022_name(ake022List.get(ake022).toString());
					}
					MCCEbizh120103Service mCCEbizh120103Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120103ServiceImpl.class, getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120103Service.saveRegisterInfo(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的异地就医登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_12);
				this.loadCodeSelectData();
				this.getInHospitalDTO().setAae030(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "registerYdjy";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 生育登记
	 * 
	 * @return
	 */
	public String registerMaternity() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_52);
					this.getInHospitalDTO().setAae011(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka015(BizHelper.getUserName());

					String jsonDiagnoseinfo = URLDecoder.decode(getParameter("diagnoseinfo"), "UTF-8");
					this.getInHospitalDTO().setDiagnoseinfos(jsonDiagnoseinfo);

					if (StringUtils.isBlank(this.getInHospitalDTO().getBka020())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
						this.getInHospitalDTO().setBka020(this.getInHospitalDTO().getAkf001());
					}
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka022())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getBka021())) {
						this.getInHospitalDTO().setBka022(this.getInHospitalDTO().getBka021());
					}
					this.getInHospitalDTO()
							.setBkz101(CodetableMapping.getDisplayValue("bke414", this.getInHospitalDTO().getAkc193()));
					MCCEbizh120103Service mCCEbizh120103Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120103ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120103Service.saveRegisterInfo(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的生育住院登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_52);
				this.loadCodeSelectData();
				this.getInHospitalDTO().setAae030(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				if (aaa027_440400.equals(BizHelper.getAaa027())) {
					this.setMaternityRequired(true);
				}
				return "registerMaternity";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 工伤登记
	 * 
	 * @return
	 */
	public String registerInjury() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_42);
					this.getInHospitalDTO().setAae011(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka015(BizHelper.getUserName());
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka020())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
						this.getInHospitalDTO().setBka020(this.getInHospitalDTO().getAkf001());
					}
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka022())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getBka021())) {
						this.getInHospitalDTO().setBka022(this.getInHospitalDTO().getBka021());
					}
					MCCEbizh120103Service mCCEbizh120103Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120103Service.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120103Service.saveRegisterInfo(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的工伤住院登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_42);
				this.loadCodeSelectData();
				this.getInHospitalDTO().setBka017(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "registerInjury";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 门特登记
	 * 
	 * @return
	 */
	public String registerSpecial() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_16);
					this.getInHospitalDTO().setAae011(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka015(BizHelper.getUserName());
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka020())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
						this.getInHospitalDTO().setBka020(this.getInHospitalDTO().getAkf001());
					}
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka022())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getBka021())) {
						this.getInHospitalDTO().setBka022(this.getInHospitalDTO().getBka021());
					}
					MCCEbizh120103Service mCCEbizh120103Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120103Service.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120103Service.saveRegisterInfo(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的门特登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_16);
				this.loadCodeSelectData();
				this.getInHospitalDTO().setBka017(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "registerSpecial";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 住院登记
	 * 
	 * @return
	 */
	public String register() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_12);
					this.getInHospitalDTO().setAae011(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka015(BizHelper.getUserName());
					String jsonDiagnoseinfo = URLDecoder.decode(getParameter("diagnoseinfo"), "UTF-8");
					this.getInHospitalDTO().setDiagnoseinfos(jsonDiagnoseinfo);

					if (StringUtils.isBlank(this.getInHospitalDTO().getBka020())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
						this.getInHospitalDTO().setBka020(this.getInHospitalDTO().getAkf001());
					}
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka022())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getBka021())) {
						this.getInHospitalDTO().setBka022(this.getInHospitalDTO().getBka021());
					}
					
					//TS19102100146 - 湘潭县分级诊疗相关政策调整【业务实现】 add by 李嘉伦 20191101
					//前台没传值时是没勾选，默认给0
					if (StringUtils.isBlank(this.getInHospitalDTO().getAka241())){
						this.getInHospitalDTO().setAka241("0");
					}
					
//					MCCEbizh120103Service mCCEbizh120103Service = this.getHygeiaServiceManager()
//							.getBeanByClass(MCCEbizh120103ServiceImpl.class, this.getInHospitalDTO().getAkb020());
//					InHospitalDTO inHospitalDTOTemp = mCCEbizh120103Service.saveRegisterInfo(this.getInHospitalDTO());
					this.getInHospitalDTO().setAaz217("SN20201221");
					InHospitalDTO inHospitalDTOTemp = GanSuDemoUtils.saveRegisterInfo(this.getInHospitalDTO());

					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的住院登记保存成功！!",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_12);
				this.loadCodeSelectData();
				this.getInHospitalDTO().setAae030(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "register";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 家庭病床登记
	 * 
	 * @return
	 */
	public String registerHomeSickbed() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_14);
					this.getInHospitalDTO().setAae011(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka015(BizHelper.getUserName());

					String jsonDiagnoseinfo = URLDecoder.decode(getParameter("diagnoseinfo"), "UTF-8");
					this.getInHospitalDTO().setDiagnoseinfos(jsonDiagnoseinfo);

					if (StringUtils.isBlank(this.getInHospitalDTO().getBka020())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
						this.getInHospitalDTO().setBka020(this.getInHospitalDTO().getAkf001());
					}
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka022())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getBka021())) {
						this.getInHospitalDTO().setBka022(this.getInHospitalDTO().getBka021());
					}
					MCCEbizh120103Service mCCEbizh120103Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120103ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120103Service.saveRegisterInfo(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的家庭病床登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_14);
				this.loadCodeSelectData();
				this.getInHospitalDTO().setAae030(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "registerHomeSickbed";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 加载控件值
	 */
	@SuppressWarnings("rawtypes")
	private void loadCodeSelectData() {
		Map akf001List = new LinkedHashMap();
		Map bka021List = new LinkedHashMap();
		Map ake020List = new LinkedHashMap();
		Map ake022List = new LinkedHashMap();
		this.loadAkf001List(akf001List);
		this.setAttribute("akf001List", akf001List); // 科室列表
		this.setAttribute("bka021List", bka021List);
		this.setAttribute("ake020List", ake020List); // 床位号
		this.setAttribute("ake022List", ake022List);
	}

	/**
	 * 加载科室
	 * 
	 * @param akf001List
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadAkf001List(Map akf001List) {
//		HospInfoDTO hospInfoDto = new HospInfoDTO();
//		hospInfoDto.setAkb020(this.getInHospitalDTO().getAkb020());
//		hospInfoDto.setAae016("1");
//		hospInfoDto.setAae100("1");
//
//		HospApiService hospApiService = this.getHygeiaServiceManager().getBeanByClass(MCCEHospApiServiceImpl.class,
//				this.getInHospitalDTO().getAkb020());
//		List<HospInfoDTO> deptRows = hospApiService.queryHospDept(this.getInHospitalDTO().getAkb020(), hospInfoDto);
//
//		if (deptRows != null) {
//			for (HospInfoDTO deptRow : deptRows) {
//				akf001List.put(deptRow.getBkc156(), deptRow.getBkc157());
//			}
//		}

		akf001List.put("001", "眼科");
	}

	/**
	 * 加载医保医师
	 * 
	 * @return
	 */
	public String loadAke022List() {
		try {
			this.initCtrlInHospitalDTO();
			this.setJSONReturn(this.loadAke022Map());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 加载医保医师
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map loadAke022Map() {
		Map ake022Map = new HashMap();
//		if (StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
//			HospInfoDTO hospInfoDto = new HospInfoDTO();
//			hospInfoDto.setAkb020(this.getInHospitalDTO().getAkb020());
//			hospInfoDto.setAae016("1");
//			hospInfoDto.setAae100("1");
//			hospInfoDto.setBkc156(this.getInHospitalDTO().getAkf001());
//
//			HospApiService hospApiService = this.getHygeiaServiceManager().getBeanByClass(MCCEHospApiServiceImpl.class,
//					this.getInHospitalDTO().getAkb020());
//			List<HospInfoDTO> doctorRows = hospApiService.queryHospDoctor(this.getInHospitalDTO().getAkb020(),
//					hospInfoDto);
//
//			if (doctorRows != null) {
//				for (HospInfoDTO doctorRow : doctorRows) {
//					ake022Map.put(doctorRow.getBkc274(), doctorRow.getBkc275());
//				}
//			}
//		}
		ake022Map.put("001", "李医生");
		return ake022Map;
	}

	/**
	 * 加载病区
	 * 
	 * @return
	 */
	public String loadBka021List() {
		try {
			this.initCtrlInHospitalDTO();
			this.setJSONReturn(this.loadBka021Map());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 加载病区
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map loadBka021Map() {
		Map bka021Map = new HashMap();
//		if (StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
//			HospInfoDTO hospInfoDto = new HospInfoDTO();
//			hospInfoDto.setAkb020(this.getInHospitalDTO().getAkb020());
//			hospInfoDto.setAae016("1");
//			hospInfoDto.setAae100("1");
//			hospInfoDto.setBkc156(this.getInHospitalDTO().getAkf001());
//			HospApiService hospApiService = this.getHygeiaServiceManager().getBeanByClass(MCCEHospApiServiceImpl.class,
//					this.getInHospitalDTO().getAkb020());
//			List<HospInfoDTO> areaRows = hospApiService.queryHospArea(this.getInHospitalDTO().getAkb020(), hospInfoDto);
//
//			if (areaRows != null) {
//				for (HospInfoDTO areaRow : areaRows) {
//					bka021Map.put(StringUtils.trim(areaRow.getBkc153()), areaRow.getBkc154());
//				}
//			}
//		}
		bka021Map.put("001","一病区");
		return bka021Map;
	}

	/**
	 * 加载床位号
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String loadAke020List() {
		try {
			this.initCtrlInHospitalDTO();
			Map ake020List = new HashMap();
//			if (StringUtils.isNotBlank(this.getInHospitalDTO().getBka021())) {
//				HospInfoDTO hospInfoDto = new HospInfoDTO();
//				hospInfoDto.setAkb020(this.getInHospitalDTO().getAkb020());
//				hospInfoDto.setAae016("1");
//				hospInfoDto.setAae100("1");
//				hospInfoDto.setBkc171("0");
//				hospInfoDto.setBkc153(this.getInHospitalDTO().getBka021());
//				HospApiService hospApiService = this.getHygeiaServiceManager()
//						.getBeanByClass(MCCEHospApiServiceImpl.class, this.getInHospitalDTO().getAkb020());
//				List<HospInfoDTO> bedRows = hospApiService.queryHospBed(this.getInHospitalDTO().getAkb020(),
//						hospInfoDto);
//				if (bedRows != null) {
//					for (HospInfoDTO bedRow : bedRows) {
//						ake020List.put(String.valueOf(bedRow.getBkc161()), bedRow.getBkc161());
//					}
//				}
//			}
			ake020List.put("001", "01");
			this.setJSONReturn(ake020List);
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 跨省异地就医入院登记检索人员信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String findAac001Ksyd() {
		try {
			this.initCtrlInHospitalDTO(aka130_82);
			if (!"1".equals(this.getInHospitalDTO().getBka977_name())) {
				this.checkReadCard();
			}
			this.getInHospitalDTO().setBka977_name("");
			MCCEbizh120001Service mCCEbizh120001Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120001ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			List<InHospitalDTO> inHospitalDTORows = mCCEbizh120001Service.searchPersonInfo(this.getInHospitalDTO());
			if (UtilFunc.isEmpty(inHospitalDTORows)) {
				return NONE;
			}
			if (inHospitalDTORows.size() == 1) {
				this.getBeanService().copyProperties(inHospitalDTORows.get(0), this.getInHospitalDTO(), true);
				this.loadHidDataAndItemName();
				Map mapRow = new HashMap();
				this.getBeanService().copyProperties(this.getInHospitalDTO(), mapRow, true);
				this.setJSONReturn(mapRow);
			} else if (inHospitalDTORows.size() > 1) {
				List<Map> mapRows = new ArrayList<Map>();
				Map mapRow = null;
				for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
					mapRow = new HashMap();
					this.getBeanService().copyProperties(inHospitalDTORow, mapRow, true);
					mapRows.add(mapRow);
				}
				this.setJSONReturn(this.getInHospitalDTO().getAac002(), mapRows);
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 省内异地检索人员信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String findAac001Snyd() {
		try {
			this.initCtrlInHospitalDTO(aka130_72);
			if (!"1".equals(this.getInHospitalDTO().getBka977_name())) {
				this.checkReadCard();
			}
			this.getInHospitalDTO().setBka977_name("");
			MCCEbizh120001Service mCCEbizh120001Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120001ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			List<InHospitalDTO> inHospitalDTORows = mCCEbizh120001Service.searchPersonInfo(this.getInHospitalDTO());
			if (UtilFunc.isEmpty(inHospitalDTORows)) {
				return NONE;
			}
			if (inHospitalDTORows.size() == 1) {
				this.getBeanService().copyProperties(inHospitalDTORows.get(0), this.getInHospitalDTO(), true);
				this.loadHidDataAndItemName();
				Map mapRow = new HashMap();
				this.getBeanService().copyProperties(this.getInHospitalDTO(), mapRow, true);
				this.setJSONReturn(mapRow);
			} else if (inHospitalDTORows.size() > 1) {
				List<Map> mapRows = new ArrayList<Map>();
				Map mapRow = null;
				for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
					mapRow = new HashMap();
					this.getBeanService().copyProperties(inHospitalDTORow, mapRow, true);
					mapRows.add(mapRow);
				}
				this.setJSONReturn(this.getInHospitalDTO().getAac002(), mapRows);
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 异地检索人员信息（包括省内、省外）
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String findAac001Ydjy() {
		try {
			this.initCtrlInHospitalDTO(aka130_12);
			if (!"1".equals(this.getInHospitalDTO().getBka977_name())) {
				this.checkReadCard();
			}
			this.getInHospitalDTO().setBka977_name("");
			this.getInHospitalDTO().setAaa027(BizHelper.getAaa027());
			// 设置异地就医默认标识，此处只做后台取异地人员信息判断使用。
			this.getInHospitalDTO().setAae139("1");
			if (UtilFunc.isNotBlank(this.getInHospitalDTO().getBke548())) {
				String[] str = this.getInHospitalDTO().getBke548().split("\\|");
				this.getInHospitalDTO().setBke550(str[3]);
				this.getInHospitalDTO().setAac0021(str[1]);
				this.getInHospitalDTO().setAac003(str[4]);
				this.getInHospitalDTO().setBka100(str[2]);
			}
			MCCEbizh120001Service mCCEbizh120001Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120001ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			List<InHospitalDTO> inHospitalDTORows = mCCEbizh120001Service.searchPersonInfo(this.getInHospitalDTO());
			if (UtilFunc.isEmpty(inHospitalDTORows)) {
				throw new HygeiaException("没有获取到省平台返回的人员信息！");
			}
			if (inHospitalDTORows.size() == 1) {
				this.getBeanService().copyProperties(inHospitalDTORows.get(0), this.getInHospitalDTO(), true);
				this.loadHidDataAndItemName();
				Map mapRow = new HashMap();
				this.getBeanService().copyProperties(this.getInHospitalDTO(), mapRow, true);
				this.setJSONReturn(mapRow);
			} else if (inHospitalDTORows.size() > 1) {
				List<Map> mapRows = new ArrayList<>();
				Map mapRow = null;
				for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
					mapRow = new HashMap();
					this.getBeanService().copyProperties(inHospitalDTORow, mapRow, true);
					mapRows.add(mapRow);
				}
				this.setJSONReturn(this.getInHospitalDTO().getAac002(), mapRows);
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 生育检索人员信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String findAac001Maternity() {
		try {
			this.initCtrlInHospitalDTO(aka130_52);
			this.getInHospitalDTO().setBka977_name("");
			if ("bka100".equals(this.getInHospitalDTO().getArgName())) {
				this.getInHospitalDTO().setBka100(this.getInHospitalDTO().getQuerystring());
			}
			MCCEbizh120001Service mCCEbizh120001Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120001ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			List<InHospitalDTO> inHospitalDTORows = mCCEbizh120001Service.searchPersonInfo(this.getInHospitalDTO());
			if (UtilFunc.isEmpty(inHospitalDTORows)) {
				return NONE;
			}
			if (inHospitalDTORows.size() == 1) {
				this.getBeanService().copyProperties(inHospitalDTORows.get(0), this.getInHospitalDTO(), true);
				this.loadHidDataAndItemName();
				Map mapRow = new HashMap();
				this.getBeanService().copyProperties(this.getInHospitalDTO(), mapRow, true);
				this.setJSONReturn(mapRow);
			} else if (inHospitalDTORows.size() > 1) {
				List<Map> mapRows = new ArrayList<>();
				Map mapRow = null;
				for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
					mapRow = new HashMap();
					this.getBeanService().copyProperties(inHospitalDTORow, mapRow, true);
					mapRows.add(mapRow);
				}
				this.setJSONReturn(this.getInHospitalDTO().getAac002(), mapRows);
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 工伤检索人员信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String findAac001Injury() {
		try {
			this.initCtrlInHospitalDTO(aka130_42);
			if (!"1".equals(this.getInHospitalDTO().getBka977_name())) {
				this.checkReadCard();
			}
			this.getInHospitalDTO().setBka977_name("");
			MCCEbizh120001Service mCCEbizh120001Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120001ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			List<InHospitalDTO> inHospitalDTORows = mCCEbizh120001Service.searchPersonInfo(this.getInHospitalDTO());
			if (UtilFunc.isEmpty(inHospitalDTORows)) {
				return NONE;
			}
			if (inHospitalDTORows.size() == 1) {
				this.getBeanService().copyProperties(inHospitalDTORows.get(0), this.getInHospitalDTO(), true);
				this.loadHidDataAndItemName();
				Map mapRow = new HashMap();
				this.getBeanService().copyProperties(this.getInHospitalDTO(), mapRow, true);
				this.setJSONReturn(mapRow);
			} else if (inHospitalDTORows.size() > 1) {
				List<Map> mapRows = new ArrayList<Map>();
				Map mapRow = null;
				for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
					mapRow = new HashMap();
					this.getBeanService().copyProperties(inHospitalDTORow, mapRow, true);
					mapRows.add(mapRow);
				}
				this.setJSONReturn(this.getInHospitalDTO().getAac002(), mapRows);
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 门特检索人员信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String findAac001Special() {
		try {
			this.initCtrlInHospitalDTO(aka130_16);
			if (!"1".equals(this.getInHospitalDTO().getBka977_name())) {
				this.checkReadCard();
			}
			this.getInHospitalDTO().setBka977_name("");
			MCCEbizh120001Service mCCEbizh120001Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120001ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			List<InHospitalDTO> inHospitalDTORows = mCCEbizh120001Service.searchPersonInfo(this.getInHospitalDTO());
			if (UtilFunc.isEmpty(inHospitalDTORows)) {
				return NONE;
			}
			if (inHospitalDTORows.size() == 1) {
				this.getBeanService().copyProperties(inHospitalDTORows.get(0), this.getInHospitalDTO(), true);
				// RQ18052306477 血友病门特专项如果就诊的医院不是二级、二级以上的医院，则在门特就医登记时即进行校验限制。20180528
				String bka006 = this.getInHospitalDTO().getBka006();
				String bkc110 = this.getInHospitalDTO().getBkc110();
				if (StringUtils.isNotBlank(bka006) && "16C06".equals(bka006)) {
					if (StringUtils.isNotBlank(bkc110)) {
						if (!bkc110.matches("1|2|3|4")) {
							throw new HygeiaException("血友病门特专项，就诊的医院必须是二级、二级以上才能结算！【" + bkc110 + "】");
						}
					} else {
						throw new HygeiaException("血友病门特专项，就诊的医院必须是二级、二级以上才能结算！【" + bkc110 + "】");
					}
				}
				this.loadHidDataAndItemName();
				Map mapRow = new HashMap();
				this.getBeanService().copyProperties(this.getInHospitalDTO(), mapRow, true);
				this.setJSONReturn(mapRow);
			} else if (inHospitalDTORows.size() > 1) {
				List<Map> mapRows = new ArrayList<Map>();
				Map mapRow = null;
				for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
					mapRow = new HashMap();
					this.getBeanService().copyProperties(inHospitalDTORow, mapRow, true);
					mapRows.add(mapRow);
				}
				this.setJSONReturn(this.getInHospitalDTO().getAac002(), mapRows);
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询个人参保信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String findAac001() {
		try {
			this.initCtrlInHospitalDTO(aka130_12);
			this.getInHospitalDTO().setBka977_name("");
			if ("bka100".equals(this.getInHospitalDTO().getArgName())) {
				this.getInHospitalDTO().setBka100(this.getInHospitalDTO().getQuerystring());
			}
//			MCCEbizh120001Service mCCEbizh120001Service = this.getHygeiaServiceManager()
//					.getBeanByClass(MCCEbizh120001ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			//List<InHospitalDTO> inHospitalDTORows = mCCEbizh120001Service.searchPersonInfo(this.getInHospitalDTO());
			String aac002 = this.getInHospitalDTO().getAac002();
			List<InHospitalDTO> inHospitalDTORows = GanSuDemoUtils.PERSON_INFO.stream()
					.filter(inHospitalDTO -> aac002.equals(inHospitalDTO.getAac002())).collect(Collectors.toList());
			if (UtilFunc.isEmpty(inHospitalDTORows)) {
				throw new HygeiaException("人员信息不存在！");
			}
			if (inHospitalDTORows.size() == 1) {
				this.getBeanService().copyProperties(inHospitalDTORows.get(0), this.getInHospitalDTO(), true);
				this.loadHidDataAndItemName();
				Map mapRow = new HashMap();
				this.getBeanService().copyProperties(this.getInHospitalDTO(), mapRow, true);
				this.setJSONReturn(mapRow);
			} else if (inHospitalDTORows.size() > 1) {
				List<Map> mapRows = new ArrayList<>();
				Map mapRow = null;
				for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
					mapRow = new HashMap();
					this.getBeanService().copyProperties(inHospitalDTORow, mapRow, true);
					mapRows.add(mapRow);
				}
				this.setJSONReturn(this.getInHospitalDTO().getAac002(), mapRows);
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 家庭病床检索人员信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String findAac001HomeSickbed() {
		try {
			this.initCtrlInHospitalDTO(aka130_14);
			this.getInHospitalDTO().setBka977_name("");
			if ("bka100".equals(this.getInHospitalDTO().getArgName())) {
				this.getInHospitalDTO().setBka100(this.getInHospitalDTO().getQuerystring());
			}
			MCCEbizh120001Service mCCEbizh120001Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120001ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			List<InHospitalDTO> inHospitalDTORows = mCCEbizh120001Service.searchPersonInfo(this.getInHospitalDTO());
			if (UtilFunc.isEmpty(inHospitalDTORows)) {
				return NONE;
			}
			if (inHospitalDTORows.size() == 1) {
				this.getBeanService().copyProperties(inHospitalDTORows.get(0), this.getInHospitalDTO(), true);
				this.loadHidDataAndItemName();
				Map mapRow = new HashMap();
				this.getBeanService().copyProperties(this.getInHospitalDTO(), mapRow, true);
				this.setJSONReturn(mapRow);
			} else if (inHospitalDTORows.size() > 1) {
				List<Map> mapRows = new ArrayList<>();
				Map mapRow = null;
				for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
					mapRow = new HashMap();
					this.getBeanService().copyProperties(inHospitalDTORow, mapRow, true);
					mapRows.add(mapRow);
				}
				this.setJSONReturn(this.getInHospitalDTO().getAac002(), mapRows);
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 跨省异地就医住院信息修改
	 * 
	 * @return
	 */
	public String modifyRegisterKsyd() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_82);
					this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka047(BizHelper.getUserName());
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka020())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
						this.getInHospitalDTO().setBka020(this.getInHospitalDTO().getAkf001());
					}
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka022())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getBka021())) {
						this.getInHospitalDTO().setBka022(this.getInHospitalDTO().getBka021());
					}
					MCCEbizh120104Service mCCEbizh120104Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120104ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120104Service.modifyRegisterInfo(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的跨省异地就医住院信息修改保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_82);
				this.loadCodeSelectData();
				return "modifyRegisterKsyd";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 省内异地就医住院信息修改
	 * 
	 * @return
	 */
	public String modifyRegisterSnyd() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_72);
					this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka047(BizHelper.getUserName());
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka020())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
						this.getInHospitalDTO().setBka020(this.getInHospitalDTO().getAkf001());
					}
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka022())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getBka021())) {
						this.getInHospitalDTO().setBka022(this.getInHospitalDTO().getBka021());
					}
					MCCEbizh120104Service mCCEbizh120104Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120104ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120104Service.modifyRegisterInfo(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的省内异地就医住院信息修改保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_72);
				this.loadCodeSelectData();
				return "modifyRegisterSnyd";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 异地就医住院信息修改（包括省内、省外）
	 * 
	 * @return
	 */
	public String modifyRegisterYdjy() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_12);
					this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka047(BizHelper.getUserName());
					this.getInHospitalDTO().setAae139("1");
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka020())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
						this.getInHospitalDTO().setBka020(this.getInHospitalDTO().getAkf001());
					}
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka022())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getBka021())) {
						this.getInHospitalDTO().setBka022(this.getInHospitalDTO().getBka021());
					}
					MCCEbizh120104Service mCCEbizh120104Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120104ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120104Service.modifyRegisterInfo(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的异地就医住院信息修改保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_12);
				this.loadCodeSelectData();
				return "modifyRegisterYdjy";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 工伤住院信息修改
	 * 
	 * @return
	 */
	public String modifyRegisterInjury() {
		try {
			if (isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_42);
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka020())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
						this.getInHospitalDTO().setBka020(this.getInHospitalDTO().getAkf001());
					}
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka022())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getBka021())) {
						this.getInHospitalDTO().setBka022(this.getInHospitalDTO().getBka021());
					}
					MCCEbizh120104Service mCCEbizh120104Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120104ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120104Service.modifyRegisterInfo(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的工伤住院信息修改保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_42);
				this.loadCodeSelectData();
				return "modifyRegisterInjury";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 生育住院信息修改
	 * 
	 * @return
	 */
	public String modifyRegisterMaternity() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_52);

					String jsonDiagnoseinfo = URLDecoder.decode(getParameter("diagnoseinfo"), "UTF-8");
					this.getInHospitalDTO().setDiagnoseinfos(jsonDiagnoseinfo);

					if (StringUtils.isBlank(getInHospitalDTO().getBka020())
							&& StringUtils.isNotBlank(getInHospitalDTO().getAkf001())) {
						this.getInHospitalDTO().setBka020(getInHospitalDTO().getAkf001());
					}
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka022())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getBka021())) {
						this.getInHospitalDTO().setBka022(this.getInHospitalDTO().getBka021());
					}
					MCCEbizh120104Service mCCEbizh120104Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120104ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120104Service.modifyRegisterInfo(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的生育住院信息修改保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_52);
				this.loadCodeSelectData();
				return "modifyRegisterMaternity";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 门特住院信息修改
	 * 
	 * @return
	 */
	public String modifyRegisterSpecial() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_16);
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka020())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
						this.getInHospitalDTO().setBka020(this.getInHospitalDTO().getAkf001());
					}
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka022())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getBka021())) {
						this.getInHospitalDTO().setBka022(this.getInHospitalDTO().getBka021());
					}
					MCCEbizh120104Service mCCEbizh120104Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120104ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120104Service.modifyRegisterInfo(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的门特住院信息修改保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_16);
				this.loadCodeSelectData();
				return "modifyRegisterSpecial";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 住院信息修改
	 * 
	 * @return
	 */
	public String modifyRegister() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_12);
					this.getInHospitalDTO().setAae011(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka015(BizHelper.getUserName());

					String jsonDiagnoseinfo = URLDecoder.decode(getParameter("diagnoseinfo"), "UTF-8");
					this.getInHospitalDTO().setDiagnoseinfos(jsonDiagnoseinfo);

					if (StringUtils.isBlank(this.getInHospitalDTO().getBka020())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
						this.getInHospitalDTO().setBka020(this.getInHospitalDTO().getAkf001());
					}
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka022())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getBka021())) {
						this.getInHospitalDTO().setBka022(this.getInHospitalDTO().getBka021());
					}
					if (StringUtils.isNotBlank(this.getInHospitalDTO().getAkc190Update())) {
						this.getInHospitalDTO().setAkc190(this.getInHospitalDTO().getAkc190Update());
					}
					
					//TS19102100146 - 湘潭县分级诊疗相关政策调整【业务实现】
					//保存前处理aka241  add by 李嘉伦 20191024
//					if (UtilFunc.isBlank(this.getInHospitalDTO().getAka241())) {
//						//界面传进来的地方为空默认正常0
//						this.getInHospitalDTO().setAka241("0");
//					}
					//TS19121200042  由于前端checkbox标签属性，value的值不再是正常的1和o，此处传入正确的值到数据库
					//任务人，时间  医保二部--赵银溪  2019/12/12
					if (this.getInHospitalDTO().getAka241().toString().equals("false")){
						this.getInHospitalDTO().setAka241("0");
					}else if(this.getInHospitalDTO().getAka241().toString().equals("0")|| this.getInHospitalDTO().getAka241().toString().equals("1")){
						this.getInHospitalDTO().setAka241("1");
					}
					
					
					MCCEbizh120104Service mCCEbizh120104Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120104ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120104Service.modifyRegisterInfo(this.getInHospitalDTO());

					this.getBeanService().copyProperties(inHospitalDTOTemp, getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的住院信息修改保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_12);
				this.loadCodeSelectData();
				return "modifyRegister";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 家庭病床住院信息修改
	 * 
	 * @return
	 */
	public String modifyRegisterHomeSickbed() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_14);

					String jsonDiagnoseinfo = URLDecoder.decode(getParameter("diagnoseinfo"), "UTF-8");
					this.getInHospitalDTO().setDiagnoseinfos(jsonDiagnoseinfo);

					if (StringUtils.isBlank(this.getInHospitalDTO().getBka020())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getAkf001())) {
						this.getInHospitalDTO().setBka020(this.getInHospitalDTO().getAkf001());
					}
					if (StringUtils.isBlank(this.getInHospitalDTO().getBka022())
							&& StringUtils.isNotBlank(this.getInHospitalDTO().getBka021())) {
						this.getInHospitalDTO().setBka022(this.getInHospitalDTO().getBka021());
					}
					if (StringUtils.isNotBlank(this.getInHospitalDTO().getAkc190Update())) {
						this.getInHospitalDTO().setAkc190(this.getInHospitalDTO().getAkc190Update());
					}

					MCCEbizh120104Service mCCEbizh120104Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120104ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120104Service.modifyRegisterInfo(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的家庭病床住院信息修改保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_14);
				this.loadCodeSelectData();
				return "modifyRegisterHomeSickbed";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 跨省异地就医出院登记
	 * 
	 * @return
	 */
	public String outRegisterKsyd() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_82);
					this.getInHospitalDTO().setBka033(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka034(BizHelper.getUserName());
					this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka047(BizHelper.getUserName());
					MCCEbizh120105Service mCCEbizh120105Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120105Service.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120105Service.outRegister(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的跨省异地就医出院登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_82);
				this.getInHospitalDTO().setBka032(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.setBka890(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "outRegisterKsyd";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 省内异地出院登记
	 * 
	 * @return
	 */
	public String outRegisterSnyd() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_72);
					this.getInHospitalDTO().setBka033(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka034(BizHelper.getUserName());
					this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka047(BizHelper.getUserName());
					MCCEbizh120105Service mCCEbizh120105Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120105Service.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120105Service.outRegister(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的省内异地出院登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_72);
				this.getInHospitalDTO().setBka032(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.setBka890(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "outRegisterSnyd";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 生育出院登记
	 * 
	 * @return
	 */
	public String outRegisterMaternity() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_52);
					this.getInHospitalDTO().setBka033(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka034(BizHelper.getUserName());
					MCCEbizh120105Service mCCEbizh120105Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120105Service.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120105Service.outRegister(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的生育出院登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_52);
				this.getInHospitalDTO().setBka032(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setBka911(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.setBka890(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				if (aaa027_440400.equals(BizHelper.getAaa027())) {
					this.setMaternityRequired(true);
				}
				return "outRegisterMaternity";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 工伤出院登记
	 * 
	 * @return
	 */
	public String outRegisterInjury() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_42);
					this.getInHospitalDTO().setBka033(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka034(BizHelper.getUserName());
					MCCEbizh120105Service mCCEbizh120105Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120105Service.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120105Service.outRegister(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的工伤出院登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_42);
				this.getInHospitalDTO().setBka032(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.setBka890(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "outRegisterInjury";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 门特出院登记
	 * 
	 * @return
	 */
	public String outRegisterSpecial() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_16);
					this.getInHospitalDTO().setBka033(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka034(BizHelper.getUserName());
					MCCEbizh120105Service mCCEbizh120105Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120105Service.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120105Service.outRegister(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的门特出院登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_16);
				this.getInHospitalDTO().setBka032(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.setBka890(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "outRegisterSpecial";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 出院登记
	 * 
	 * @return
	 */
	public String outRegister() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_12);
					this.getInHospitalDTO().setBka033(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka034(BizHelper.getUserName());
					MCCEbizh120105Service mCCEbizh120105Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120105Service.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = this.getInHospitalDTO();//mCCEbizh120105Service.outRegister(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的出院登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_12);
				this.getInHospitalDTO().setBka032(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.setBka890(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "outRegister";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 家庭病床出院登记
	 * 
	 * @return
	 */
	public String outRegisterHomeSickbed() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_14);
					this.getInHospitalDTO().setBka033(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka034(BizHelper.getUserName());
					MCCEbizh120105Service mCCEbizh120105Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120105Service.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120105Service.outRegister(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的家庭病床出院登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_14);
				this.getInHospitalDTO().setBka032(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.setBka890(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "outRegisterHomeSickbed";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 跨省异地住院就医回退
	 * 
	 * @return
	 */
	public String queryInHospitalKsyd() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_82);
					this.validateaaz217();
					MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					List<InHospitalDTO> inHospitalDTORows = mCCEbizh120102Service
							.queryInHospitalaaz217s(this.getInHospitalDTO());
					if (UtilFunc.isEmpty(inHospitalDTORows)) {
						return NONE;
					}
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						if (StringUtils.isNotBlank(inHospitalDTORow.getBka006())) {
							inHospitalDTORow.setBka006_name(CodetableMapping.getDisplayValue("bka006",
									inHospitalDTORow.getBka006(), inHospitalDTORow.getBka006()));
						}
					}
					PagerHelper.initPagination(this.getRequest());
					PagerHelper.getPaginationObj().setCount(inHospitalDTORows.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(inHospitalDTORows));
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_82);
				this.getInHospitalDTO().setAae030(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setAae031(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setBka891("0");
				return "queryInHospitalKsyd";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 省内异地就医住院回退
	 * 
	 * @return
	 */
	public String queryInHospitalSnyd() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_72);
					this.validateaaz217();
					MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					List<InHospitalDTO> inHospitalDTORows = mCCEbizh120102Service
							.queryInHospitalaaz217s(this.getInHospitalDTO());
					if (UtilFunc.isEmpty(inHospitalDTORows)) {
						return NONE;
					}
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						if (StringUtils.isNotBlank(inHospitalDTORow.getBka006())) {
							inHospitalDTORow.setBka006_name(CodetableMapping.getDisplayValue("bka006",
									inHospitalDTORow.getBka006(), inHospitalDTORow.getBka006()));
						}
					}
					PagerHelper.initPagination(this.getRequest());
					PagerHelper.getPaginationObj().setCount(inHospitalDTORows.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(inHospitalDTORows));
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_72);
				this.getInHospitalDTO().setAae030(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setAae031(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setBka891("0");
				return "queryInHospitalSnyd";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 异地就医住院回退（包括省内、省外）
	 * 
	 * @return
	 */
	public String queryInHospitalYdjy() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_12);
					this.getInHospitalDTO().setAae139("1");
					MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					List<InHospitalDTO> inHospitalDTORows = mCCEbizh120102Service
							.queryInHospitalaaz217s(this.getInHospitalDTO());
					if (UtilFunc.isEmpty(inHospitalDTORows)) {
						return NONE;
					}
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						if (StringUtils.isNotBlank(inHospitalDTORow.getBka006())) {
							inHospitalDTORow.setBka006_name(CodetableMapping.getDisplayValue("bka006",
									inHospitalDTORow.getBka006(), inHospitalDTORow.getBka006()));
						}
					}
					PagerHelper.initPagination(this.getRequest());
					PagerHelper.getPaginationObj().setCount(inHospitalDTORows.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(inHospitalDTORows));
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_12);
				this.getInHospitalDTO().setAae030(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setAae031(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setBka891("0");
				return "queryInHospitalYdjy";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 生育回退
	 * 
	 * @return
	 */
	public String queryInHospitalMaternity() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_52);
					this.validateaaz217();
					MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					List<InHospitalDTO> inHospitalDTORows = mCCEbizh120102Service
							.queryInHospitalaaz217s(this.getInHospitalDTO());
					if (UtilFunc.isEmpty(inHospitalDTORows)) {
						return NONE;
					}
//					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
//						if (StringUtils.isNotBlank(inHospitalDTORow.getBka006())) {
//							inHospitalDTORow.setBka006_name(
//									CodetableMapping.getDisplayValue("bka006$" + this.getInHospitalDTO().getAaa027(),
//											inHospitalDTORow.getBka006(), inHospitalDTORow.getBka006()));
//						}
//					}
					PagerHelper.initPagination(this.getRequest());
					PagerHelper.getPaginationObj().setCount(inHospitalDTORows.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(inHospitalDTORows));
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_52);
				this.getInHospitalDTO().setAae030(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setAae031(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setBka891("0");
				return "queryInHospitalMaternity";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 工伤回退
	 * 
	 * @return
	 */
	public String queryInHospitalInjury() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_42);
					this.validateaaz217();
					MCCEbizh120102Service mCCEbizh120102Service = getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					List<InHospitalDTO> inHospitalDTORows = mCCEbizh120102Service
							.queryInHospitalaaz217s(this.getInHospitalDTO());
					if (UtilFunc.isEmpty(inHospitalDTORows)) {
						return NONE;
					}
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						if (StringUtils.isNotBlank(inHospitalDTORow.getBka006())) {
							inHospitalDTORow.setBka006_name(
									CodetableMapping.getDisplayValue("bka006$" + this.getInHospitalDTO().getAaa027(),
											inHospitalDTORow.getBka006(), inHospitalDTORow.getBka006()));
						}
					}
					PagerHelper.initPagination(this.getRequest());
					PagerHelper.getPaginationObj().setCount(inHospitalDTORows.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(inHospitalDTORows));
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_42);
				this.getInHospitalDTO().setAae030(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setAae031(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setBka891("0");
				return "queryInHospitalInjury";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 门特回退
	 * 
	 * @return
	 */
	public String queryInHospitalSpecial() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_16);
					this.validateaaz217();
					MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					List<InHospitalDTO> inHospitalDTORows = mCCEbizh120102Service
							.queryInHospitalaaz217s(this.getInHospitalDTO());
					if (UtilFunc.isEmpty(inHospitalDTORows)) {
						return NONE;
					}
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						if (StringUtils.isNotBlank(inHospitalDTORow.getBka006())) {
							inHospitalDTORow.setBka006_name(
									CodetableMapping.getDisplayValue("bka006$" + this.getInHospitalDTO().getAaa027(),
											inHospitalDTORow.getBka006(), inHospitalDTORow.getBka006()));
						}
					}
					PagerHelper.initPagination(this.getRequest());
					PagerHelper.getPaginationObj().setCount(inHospitalDTORows.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(inHospitalDTORows));
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_16);
				this.getInHospitalDTO().setAae030(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setAae031(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setBka891("0");
				return "queryInHospitalSpecial";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 住院回退
	 * 
	 * @return
	 */
	public String queryInHospital() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_12);
					this.validateaaz217();
					MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					List<InHospitalDTO> inHospitalDTORows = mCCEbizh120102Service
							.queryInHospitalaaz217s(this.getInHospitalDTO());
					if (UtilFunc.isEmpty(inHospitalDTORows)) {
						return NONE;
					}
					PagerHelper.initPagination(this.getRequest());
					PagerHelper.getPaginationObj().setCount(inHospitalDTORows.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(inHospitalDTORows));
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_12);
				this.getInHospitalDTO().setAae030(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setAae031(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setBka891("0");
				return "queryInHospital";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 家庭病床回退
	 * 
	 * @return
	 */
	public String queryInHospitalHomeSickbed() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_14);
					this.validateaaz217();
					MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					List<InHospitalDTO> inHospitalDTORows = mCCEbizh120102Service
							.queryInHospitalaaz217s(this.getInHospitalDTO());
					if (UtilFunc.isEmpty(inHospitalDTORows)) {
						return NONE;
					}
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						if (StringUtils.isNotBlank(inHospitalDTORow.getBka006())) {
							inHospitalDTORow.setBka006_name(
									CodetableMapping.getDisplayValue("bka006$" + this.getInHospitalDTO().getAaa027(),
											inHospitalDTORow.getBka006(), inHospitalDTORow.getBka006()));
						}
					}
					PagerHelper.initPagination(this.getRequest());
					PagerHelper.getPaginationObj().setCount(inHospitalDTORows.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(inHospitalDTORows));
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_14);
				this.getInHospitalDTO().setAae030(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setAae031(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setBka891("0");
				return "queryInHospitalHomeSickbed";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 取消住院登记
	 * 
	 * @return
	 */
	public String cancelRegister() {
		try {
			if (this.isPostRequest()) {
				try {
					this.getInHospitalDTO().setAaa027(BizHelper.getAaa027());
					this.getInHospitalDTO().setAkb020(BizHelper.getAkb020());
					String aac002Biz = this.getInHospitalDTO().getAac002();
					if ("1".equals(
							this.dictService.getValue("HISAPI_BIZ_PARAM", "CHECK_READCARD_CANCEL_REGISTER", "0"))) {
						try {
							this.initCtrlInHospitalDTO();
						} catch (Exception e) {
							this.getInHospitalDTO().setBke548(null);
						}
						this.checkReadCard();
					}
					String aac002Ic = this.getInHospitalDTO().getAac002();
					if (StringUtils.isNotBlank(aac002Biz) && StringUtils.isNotBlank(aac002Ic)
							&& !aac002Biz.equals(aac002Ic)) {
						throw new HygeiaException("您不能操作非持卡人本人的业务(" + aac002Biz + "/" + aac002Ic + ")!");
					}
					MCCEbizh120109Service mCCEbizh120109Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120109ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120109Service.cancelRegister(this.getInHospitalDTO());

					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的取消住院登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				return "cancelRegister";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 取消出院
	 * 
	 * @return
	 */
	public String cancelOutRegister() {
		try {
			if (this.isPostRequest()) {
				try {
					this.getInHospitalDTO().setAaa027(BizHelper.getAaa027());
					this.getInHospitalDTO().setAkb020(BizHelper.getAkb020());
					MCCEbizh120108Service mCCEbizh120108Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120108Service.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120108Service.cancelOutRegister(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的取消出院登记保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				return "cancelOutRegister";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 取消出院登记和出院结算
	 * 
	 * @return
	 */
	public String cancelOutCharge() {
		try {
			if (this.isPostRequest()) {
				try {
					this.getInHospitalDTO().setAaa027(BizHelper.getAaa027());
					this.getInHospitalDTO().setAkb020(BizHelper.getAkb020());
					this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
					this.getInHospitalDTO().setAae014(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka047(BizHelper.getUserName());
					String aac002_biz = this.getInHospitalDTO().getAac002();
					if ("1".equals(
							this.dictService.getValue("HISAPI_BIZ_PARAM", "CHECK_READCARD_CANCEL_CHARGE", "0"))) {
						try {
							this.initCtrlInHospitalDTO();
						} catch (Exception e) {
							this.getInHospitalDTO().setBke548(null);
							this.getCommunalService().error(this.logger, e, "");
						}
						this.checkReadCard();
					}
					String aac002_ic = this.getInHospitalDTO().getAac002();
					if (StringUtils.isNotBlank(aac002_biz) && StringUtils.isNotBlank(aac002_ic)
							&& !aac002_biz.equals(aac002_ic)) {
						throw new HygeiaException("您不能操作非持卡人本人的业务(" + aac002_biz + "/" + aac002_ic + ")!");
					}
					/**
					 * 通用接口台账费用处理 在办理门诊退费或取消出院结算时，根据akb020和aaz217查询台账信息，获取是否存在发票信息；
					 */
					if ("1".equals(this.getInHospitalDTO().getComm_subsys_flag())) {
						InvoiceManagerService invoiceManagerService = this.getHygeiaServiceManager()
								.getBeanByClass(InvoiceManagerService.class, this.getInHospitalDTO().getAkb020());
						KAB3DTO kab3 = new KAB3DTO();
						kab3.setAaz217(this.getInHospitalDTO().getAaz217());
						kab3.setAkb020(this.getInHospitalDTO().getAkb020());
						kab3 = invoiceManagerService.selectKab3(kab3);
						if (kab3 != null && UtilFunc.isNotBlank(kab3.getKab1id())) {
							throw new HygeiaException("该笔业务已打印了发票,请先去【发票重打/补打界面】办理发票作废,发票号：[" + kab3.getBae413() + "],"
									+ "就诊登记号：[" + this.getInHospitalDTO().getAaz217() + "]!");
						}
					}
					MCCEbizh120107Service mCCEbizh120107Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120107ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120107Service.cancelOutCharge(this.getInHospitalDTO());

					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的取消出院结算保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				return "cancelOutCharge";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 查询在院登记业务信息_跨省异地就医出院登记
	 * 
	 * @return
	 */
	public String findOutRegisteraaz217Ksyd() {
		try {
			this.initCtrlInHospitalDTO(aka130_82);
			this.getInHospitalDTO().setBka438(bka438_3);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询在院登记业务信息_省内异地出院登记
	 * 
	 * @return
	 */
	public String findOutRegisteraaz217Snyd() {
		try {
			this.initCtrlInHospitalDTO(aka130_72);
			this.getInHospitalDTO().setBka438(bka438_3);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询在院登记业务信息_生育出院登记
	 * 
	 * @return
	 */
	public String findOutRegisteraaz217Maternity() {
		try {
			this.initCtrlInHospitalDTO(aka130_52);
			this.getInHospitalDTO().setBka438(bka438_3);
			MCCEbizh120102Service mCCEbizh120102Service = getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询在院登记业务信息_工伤出院登记
	 * 
	 * @return
	 */
	public String findOutRegisteraaz217Injury() {
		try {
			this.initCtrlInHospitalDTO(aka130_42);
			this.getInHospitalDTO().setBka438(bka438_3);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询在院登记业务信息_门特出院登记
	 * 
	 * @return
	 */
	public String findOutRegisteraaz217Special() {
		try {
			this.initCtrlInHospitalDTO(aka130_16);
			this.getInHospitalDTO().setBka438(bka438_3);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询在院登记业务信息_出院登记
	 * 
	 * @return
	 */
	public String findOutRegisterAaz217() {
		try {
			this.initCtrlInHospitalDTO(aka130_12);
			this.getInHospitalDTO().setBka438(bka438_3);
//			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
//					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
//			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());

			InHospitalDTO inHospitalDTOTemp = GanSuDemoUtils.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询在院登记业务信息_家庭病床出院登记
	 * 
	 * @return
	 */
	public String findOutRegisteraaz217HomeSickbed() {
		try {
			this.initCtrlInHospitalDTO(aka130_14);
			this.getInHospitalDTO().setBka438(bka438_3);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 *
	 * 查询就医登记信息_跨省异地就医出院结算
	 * 
	 * @return
	 */
	public String findOutChargeaaz217Ksyd() {
		try {
			this.initCtrlInHospitalDTO(aka130_82);
			this.getInHospitalDTO().setBka438(bka438_2);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 *
	 * 查询就医登记信息_省内异地出院结算
	 * 
	 * @return
	 */
	public String findOutChargeaaz217Snyd() {
		try {
			this.initCtrlInHospitalDTO(aka130_72);
			this.getInHospitalDTO().setBka438(bka438_2);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 *
	 * 查询就医登记信息_异地出院结算（包括省内、省外）
	 * 
	 * @return
	 */
	public String findOutChargeAaz217Ydjy() {
		try {
			this.initCtrlInHospitalDTO(aka130_12);
			this.getInHospitalDTO().setBka438(bka438_2);
			this.getInHospitalDTO().setAae139("1");
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.getInHospitalDTO().setAae031(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 *
	 * 查询就医登记信息_生育出院结算
	 * 
	 * @return
	 */
	public String findOutChargeAaz217Maternity() {
		try {
			this.initCtrlInHospitalDTO(aka130_52);
			this.getInHospitalDTO().setBka438(bka438_2);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 *
	 * 查询就医登记信息_工伤出院结算
	 * 
	 * @return
	 */
	public String findOutChargeaaz217Injury() {
		try {
			this.initCtrlInHospitalDTO(aka130_42);
			this.getInHospitalDTO().setBka438(bka438_2);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 *
	 * 查询就医登记信息_门特出院结算
	 * 
	 * @return
	 */
	public String findOutChargeaaz217Special() {
		try {
			this.initCtrlInHospitalDTO(aka130_16);
			this.getInHospitalDTO().setBka438(bka438_2);
			MCCEbizh120102Service mCCEbizh120102Service = getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 *
	 * 查询就医登记信息_出院结算
	 * 
	 * @return
	 */
	public String findOutChargeAaz217() {
		try {
			this.initCtrlInHospitalDTO(aka130_12);
			this.getInHospitalDTO().setBka438(bka438_2);
//			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
//					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
//			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			InHospitalDTO inHospitalDTOTemp = GanSuDemoUtils.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 *
	 * 查询就医登记信息_家庭病床出院结算
	 * 
	 * @return
	 */
	public String findOutChargeAaz217HomeSickbed() {
		try {
			this.initCtrlInHospitalDTO(aka130_14);
			this.getInHospitalDTO().setBka438(bka438_2);
			MCCEbizh120102Service mCCEbizh120102Service = getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询在院登记_跨省异地就医住院信息修改
	 * 
	 * @return
	 */
	public String findModifyRegisteraaz217Ksyd() {
		try {
			this.initCtrlInHospitalDTO(aka130_82);
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询在院登记_省内异地就医住院修改
	 * 
	 * @return
	 */
	public String findModifyRegisteraaz217Snyd() {
		try {
			this.initCtrlInHospitalDTO(aka130_72);
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询在院登记_工伤住院修改
	 * 
	 * @return
	 */
	public String findModifyRegisteraaz217Injury() {
		try {
			this.initCtrlInHospitalDTO(aka130_42);
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询在院登记_生育住院修改
	 * 
	 * @return
	 */
	public String findModifyRegisterAaz217Maternity() {
		try {
			this.initCtrlInHospitalDTO(aka130_52);
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询在院登记_门特住院修改
	 * 
	 * @return
	 */
	public String findModifyRegisteraaz217Special() {
		try {
			this.initCtrlInHospitalDTO(aka130_16);
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询在院登记_住院修改
	 * 
	 * @return
	 */
	public String findModifyRegisterAaz217() {
		try {
			this.initCtrlInHospitalDTO(aka130_12);
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			//TS19112700299   差别化待遇支付标志与住院登记kcd1表里的标志对应
			//修改描述：调中心端查kcd1表里的aka241标志
			//任务人，时间  医保二部--赵银溪  2019/12/02
			String aka241 = mCCEbizh120102Service.selectAka241(inHospitalDTOTemp);
			this.getInHospitalDTO().setAka241(aka241);

		
			
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询在院登记_家庭病床住院修改
	 * 
	 * @return
	 */
	public String findModifyRegisterAaz217HomeSickbed() {
		try {
			this.initCtrlInHospitalDTO(aka130_14);
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 跨省异地就医费用记账
	 * 
	 * @return
	 */
	public String inputFeeKsyd() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_82);
					String jsonFee = URLDecoder.decode(getParameter("feeinfo"), "UTF-8");
					List<InHospitalDTO> inHospitalDTORows = JSONArray.parseArray(jsonFee, InHospitalDTO.class);
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						inHospitalDTORow.setBka063(BizHelper.getLoginUser());
						inHospitalDTORow.setBka064(BizHelper.getUserName());
						inHospitalDTORow.setBka065(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
					}
					MCCEbizh120002Service mCCEbizh120002Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120002Service.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120002Service.checkAndSaveFeeInfo(this.getInHospitalDTO(),
							inHospitalDTORows);
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("跨省异地就医费用明细保存成功！", this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_82);
				this.getInHospitalDTO().setSearchType("py");
				this.setAke007(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "inputFeeKsyd";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 省内异地记账
	 * 
	 * @return
	 */
	public String inputFeeSnyd() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_72);
					String jsonFee = URLDecoder.decode(getParameter("feeinfo"), "UTF-8");
					List<InHospitalDTO> inHospitalDTORows = JSONArray.parseArray(jsonFee, InHospitalDTO.class);
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						inHospitalDTORow.setBka063(BizHelper.getLoginUser());
						inHospitalDTORow.setBka064(BizHelper.getUserName());
						inHospitalDTORow.setBka065(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
					}
					MCCEbizh120002Service mCCEbizh120002Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120002Service.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120002Service.checkAndSaveFeeInfo(this.getInHospitalDTO(),
							inHospitalDTORows);
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("省内异地费用明细保存成功！", this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_72);
				this.getInHospitalDTO().setSearchType("py");
				this.loadBka075List();
				this.setAke007(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "inputFeeSnyd";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 异地就医费用记账（包括省内、省外）
	 * 
	 * @return
	 */
	public String inputFeeYdjy() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_12);
					this.getInHospitalDTO().setAae139("1");
					this.getInHospitalDTO().setBka063(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka064(BizHelper.getUserName());
					String jsonFee = URLDecoder.decode(getParameter("feeinfo"), "UTF-8");
					List<InHospitalDTO> inHospitalDTORows = JSONArray.parseArray(jsonFee, InHospitalDTO.class);
					long aaz213 = 1l;
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						inHospitalDTORow.setAaz213(String.valueOf(aaz213++));
						inHospitalDTORow.setBka065(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
					}
					MCCEbizh120002Service mCCEbizh120002Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120002ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120002Service.checkAndSaveFeeInfo(this.getInHospitalDTO(),
							inHospitalDTORows);
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("异地费用明细保存成功！", this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_72);
				this.getInHospitalDTO().setSearchType("py");
				this.loadBka075List();
				this.setAke007(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "inputFeeYdjy";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 生育记账
	 * 
	 * @return
	 */
	public String inputFeeMaternity() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_52);
					String jsonFee = URLDecoder.decode(getParameter("feeinfo"), "UTF-8");
					List<InHospitalDTO> inHospitalDTORows = JSONArray.parseArray(jsonFee, InHospitalDTO.class);
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						inHospitalDTORow.setBka063(BizHelper.getLoginUser());
						inHospitalDTORow.setBka064(BizHelper.getUserName());
						inHospitalDTORow.setBka065(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
					}
					MCCEbizh120002Service mCCEbizh120002Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120002ServiceImpl.class, getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120002Service.checkAndSaveFeeInfo(this.getInHospitalDTO(),
							inHospitalDTORows);
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("生育费用明细保存成功！", this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_52);
				this.getInHospitalDTO().setSearchType("py");
				this.setAke007(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.loadBka075List();
				return "inputFeeMaternity";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 工伤记账
	 * 
	 * @return
	 */
	public String inputFeeInjury() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_42);
					String jsonFee = URLDecoder.decode(getParameter("feeinfo"), "UTF-8");
					List<InHospitalDTO> inHospitalDTORows = JSONArray.parseArray(jsonFee, InHospitalDTO.class);
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						inHospitalDTORow.setBka063(BizHelper.getLoginUser());
						inHospitalDTORow.setBka064(BizHelper.getUserName());
						inHospitalDTORow.setBka065(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
					}
					MCCEbizh120002Service mCCEbizh120002Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120002Service.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120002Service.checkAndSaveFeeInfo(this.getInHospitalDTO(),
							inHospitalDTORows);
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("工伤费用明细保存成功！", this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_42);
				this.getInHospitalDTO().setSearchType("py");
				this.setAke007(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "inputFeeInjury";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 门特记账
	 * 
	 * @return
	 */
	public String inputFeeSpecial() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_16);
					String jsonFee = URLDecoder.decode(getParameter("feeinfo"), "UTF-8");
					List<InHospitalDTO> inHospitalDTORows = JSONArray.parseArray(jsonFee, InHospitalDTO.class);
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						inHospitalDTORow.setBka063(BizHelper.getLoginUser());
						inHospitalDTORow.setBka064(BizHelper.getUserName());
						inHospitalDTORow.setBka065(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
					}
					MCCEbizh120002Service mCCEbizh120002Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120002Service.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120002Service.checkAndSaveFeeInfo(this.getInHospitalDTO(),
							inHospitalDTORows);
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("门特费用明细保存成功！", this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_16);
				this.getInHospitalDTO().setSearchType("py");
				this.setAke007(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "inputFeeSpecial";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 费用记帐
	 * 
	 * @return
	 */
	public String inputFee() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_12);
					String jsonFee = URLDecoder.decode(getParameter("feeinfo"), "UTF-8");
					List<InHospitalDTO> inHospitalDTORows = JSONArray.parseArray(jsonFee, InHospitalDTO.class);
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						inHospitalDTORow.setBka063(BizHelper.getLoginUser());
						inHospitalDTORow.setBka064(BizHelper.getUserName());
						inHospitalDTORow.setBka065(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
					}
//					MCCEbizh120002Service mCCEbizh120002Service = this.getHygeiaServiceManager()
//							.getBeanByClass(MCCEbizh120002ServiceImpl.class, getInHospitalDTO().getAkb020());
//					InHospitalDTO inHospitalDTOTemp = mCCEbizh120002Service.checkAndSaveFeeInfo(this.getInHospitalDTO(),
//							inHospitalDTORows);
					InHospitalDTO inHospitalDTOTemp = GanSuDemoUtils.saveFeeInfo(inHospitalDTORows);
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("费用明细保存成功！", this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_12);
				this.getInHospitalDTO().setSearchType("py");
				this.setAke007(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				//this.loadBka075List();
				return "inputFee";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadBka075List() {
		try {
			Map bka075List = new HashMap();
			HospApiService hospApiService = this.getHygeiaServiceManager().getBeanByClass(MCCEHospApiServiceImpl.class,
					this.getInHospitalDTO().getAkb020());
			HospInfoDTO hospInfoDto = new HospInfoDTO();
			hospInfoDto.setAkb020(this.getInHospitalDTO().getAkb020());
			hospInfoDto.setAae016("1");
			hospInfoDto.setAae100("1");
			hospInfoDto.setQuery_flag("1");
			List<HospInfoDTO> doctorRows = hospApiService.queryHospDoctor(this.getInHospitalDTO().getAkb020(),
					hospInfoDto);
			if (doctorRows != null) {
				for (HospInfoDTO doctorRow : doctorRows) {
					bka075List.put(doctorRow.getBkc274(), doctorRow.getBkc275());
				}
			}
			this.getRequest().setAttribute("bka075List", bka075List);
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
	}

	/**
	 * 家庭病床记账
	 * 
	 * @return
	 */
	public String inputFeeHomeSickbed() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_14);
					String jsonFee = URLDecoder.decode(getParameter("feeinfo"), "UTF-8");
					List<InHospitalDTO> inHospitalDTORows = JSONArray.parseArray(jsonFee, InHospitalDTO.class);
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						inHospitalDTORow.setBka063(BizHelper.getLoginUser());
						inHospitalDTORow.setBka064(BizHelper.getUserName());
						inHospitalDTORow.setBka065(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
					}
					MCCEbizh120002Service mCCEbizh120002Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120002ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120002Service.checkAndSaveFeeInfo(this.getInHospitalDTO(),
							inHospitalDTORows);
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("家庭病床费用明细保存成功！", this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_14);
				this.getInHospitalDTO().setSearchType("py");
				this.setAke007(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.loadBka075List();
				return "inputFeeHomeSickbed";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 删除费用
	 * 
	 * @return
	 */
	public String deleteFee() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO();
					MCCEbizh120004Service mCCEbizh120004Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120004Service.class, this.getInHospitalDTO().getAkb020());
					mCCEbizh120004Service.deleteInHospBizFees(this.getInHospitalDTO());
					this.setJSONReturn("费用明细删除成功", this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO();
				return "deleteFee";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 删除费用
	 * 
	 * @return
	 */
	public String deleteFeeSnyd() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO(aka130_72);
					MCCEbizh120004Service mCCEbizh120004Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120004Service.class, this.getInHospitalDTO().getAkb020());
					mCCEbizh120004Service.deleteInHospBizFees(this.getInHospitalDTO());
					this.setJSONReturn("费用明细删除成功", this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO();
				return "deleteFee";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 查询就医登记信息_跨省异地就医费用记账
	 * 
	 * @return
	 */
	public String findaaz217Ksyd() {
		try {
			this.initCtrlInHospitalDTO(aka130_82);
			this.validateaaz217();
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询就医登记信息_省内异地就医记账
	 * 
	 * @return
	 */
	public String findaaz217Snyd() {
		try {
			this.initCtrlInHospitalDTO(aka130_72);
			this.validateaaz217();
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询就医登记信息_异地就医记账（包括省内、省外）
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String findAaz217Ydjy() {
		try {
			this.initCtrlInHospitalDTO(aka130_12);
			this.getInHospitalDTO().setBka438(bka438_1);
			this.getInHospitalDTO().setAae139("1");
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.getInHospitalDTO().setBka021_hid(this.getInHospitalDTO().getBka021());
			this.getInHospitalDTO().setAke020_hid(this.getInHospitalDTO().getAke020());
			this.getInHospitalDTO().setAke022_hid(this.getInHospitalDTO().getAke022());
			String akf001 = this.getInHospitalDTO().getAkf001();
			String bka021 = this.getInHospitalDTO().getBka021();
			String ake022 = this.getInHospitalDTO().getAke022();

			Map akf001List = new LinkedHashMap();
			this.loadAkf001List(akf001List);
			if (akf001List.containsKey(akf001)) {
				this.getInHospitalDTO().setBka020(akf001List.get(akf001).toString());
			}
			Map bka021List = this.loadBka021Map();
			if (bka021List.containsKey(bka021)) {
				this.getInHospitalDTO().setBka022(bka021List.get(bka021).toString());
			}

			Map ake022List = this.loadAke022Map();
			if (ake022List.containsKey(ake022)) {
				this.getInHospitalDTO().setAke022_name(ake022List.get(ake022).toString());
			}

			DiseaseQueryService diseaseQueryService = (DiseaseQueryService) this.getHygeiaServiceManager()
					.getBean("diseaseQueryServiceesb", this.getInHospitalDTO().getAkb020());
			KA06 ka061 = diseaseQueryService.queryDisease(this.getInHospitalDTO().getAaa027(),
					this.getInHospitalDTO().getFin_disease1());
			if (ka061 != null) {
				this.getInHospitalDTO().setFin_disease1_name(ka061.getAka121());
			}
			KA06 ka062 = diseaseQueryService.queryDisease(this.getInHospitalDTO().getAaa027(),
					this.getInHospitalDTO().getFin_disease2());
			if (ka062 != null) {
				this.getInHospitalDTO().setFin_disease2_name(ka062.getAka121());
			}

			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询就医登记信息_生育记账
	 * 
	 * @return
	 */
	public String findAaz217Maternity() {
		try {
			initCtrlInHospitalDTO(aka130_52);
			this.validateaaz217();
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询就医登记信息_工伤记账
	 * 
	 * @return
	 */
	public String findaaz217Injury() {
		try {
			this.initCtrlInHospitalDTO(aka130_42);
			this.validateaaz217();
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询就医登记信息_门特记账
	 * 
	 * @return
	 */
	public String findaaz217Special() {
		try {
			this.initCtrlInHospitalDTO(aka130_16);
			this.validateaaz217();
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 *
	 * 查询就医登记信息_费用记账
	 * 
	 * @return
	 */
	public String findAaz217() {
		try {
			this.initCtrlInHospitalDTO(aka130_12);
			this.validateaaz217();
			this.getInHospitalDTO().setBka438(bka438_1);
//			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
//					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
//			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			InHospitalDTO inHospitalDTOTemp = GanSuDemoUtils.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询就医登记信息_家庭病床记账
	 * 
	 * @return
	 */
	public String findAaz217HomeSickbed() {
		try {
			this.initCtrlInHospitalDTO(aka130_14);
			this.validateaaz217();
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询已保存的费用_省内异地
	 * 
	 * @return
	 */
	public String feeSelectKsyd() {
		try {
			this.initCtrlInHospitalDTO(aka130_82);
			PagerHelper.initPagination(this.getRequest());
			this.getInHospitalDTO().setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.getInHospitalDTO().setPageSize(PagerHelper.getPaginationObj().getPageSize());
			this.loadHidDataAndItemName();
			MCCEbizh120203Service mCCEbizh120203Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120203ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			ListResult listResult = mCCEbizh120203Service.querySavedFee(this.getInHospitalDTO());
			if (listResult == null) {
				return NONE;
			}
			PagerHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(this.getRequest(), this.getResponse(),
					PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询已保存的费用_省内异地
	 * 
	 * @return
	 */
	public String feeSelectSnyd() {
		try {
			this.initCtrlInHospitalDTO(aka130_72);
			PagerHelper.initPagination(this.getRequest());
			this.getInHospitalDTO().setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.getInHospitalDTO().setPageSize(PagerHelper.getPaginationObj().getPageSize());
			this.loadHidDataAndItemName();
			MCCEbizh120203Service mCCEbizh120203Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120203ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			ListResult listResult = mCCEbizh120203Service.querySavedFee(this.getInHospitalDTO());
			if (listResult == null) {
				return NONE;
			}
			PagerHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(this.getRequest(), this.getResponse(),
					PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 异地就医查询已保存的费用（包括省内、省外）
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String feeSelectYdjy() {
		try {
			this.initCtrlInHospitalDTO(aka130_12);
			PagerHelper.initPagination(this.getRequest());
			this.getInHospitalDTO().setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.getInHospitalDTO().setPageSize(PagerHelper.getPaginationObj().getPageSize());
			this.getInHospitalDTO().setAae139("1");
			this.loadHidDataAndItemName();
			MCCEbizh120203Service mCCEbizh120203Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120203ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			ListResult listResult = mCCEbizh120203Service.querySavedFee(this.getInHospitalDTO());
			if (listResult == null) {
				return NONE;
			}
			List<Map> list = (List<Map>) listResult.getList();
			for (Map m : list) {
				String ake007 = (String) m.get("ake007");
				if (ake007 != null && ake007.length() >= 10) {
					m.put("ake007", ake007.substring(0, 10));
				}
			}
			PagerHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(this.getRequest(), this.getResponse(),
					PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询已保存的费用_生育
	 * 
	 * @return
	 */
	public String feeSelectMaternity() {
		try {
			this.initCtrlInHospitalDTO(aka130_52);
			PagerHelper.initPagination(this.getRequest());
			this.getInHospitalDTO().setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.getInHospitalDTO().setPageSize(PagerHelper.getPaginationObj().getPageSize());
			this.loadHidDataAndItemName();
			MCCEbizh120203Service mCCEbizh120203Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120203ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			ListResult listResult = mCCEbizh120203Service.querySavedFee(this.getInHospitalDTO());
			if (listResult == null) {
				return NONE;
			}
			PagerHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(this.getRequest(), this.getResponse(),
					PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询已保存的费用_工伤
	 * 
	 * @return
	 */
	public String feeSelectInjury() {
		try {
			this.initCtrlInHospitalDTO(aka130_42);
			PagerHelper.initPagination(this.getRequest());
			this.getInHospitalDTO().setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.getInHospitalDTO().setPageSize(PagerHelper.getPaginationObj().getPageSize());
			loadHidDataAndItemName();
			MCCEbizh120203Service mCCEbizh120203Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120203ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			ListResult listResult = mCCEbizh120203Service.querySavedFee(this.getInHospitalDTO());
			if (listResult == null) {
				return NONE;
			}
			PagerHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(this.getRequest(), this.getResponse(),
					PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询已保存的费用_门特
	 * 
	 * @return
	 */
	public String feeSelectSpecial() {
		try {
			this.initCtrlInHospitalDTO(aka130_16);
			PagerHelper.initPagination(this.getRequest());
			this.getInHospitalDTO().setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.getInHospitalDTO().setPageSize(PagerHelper.getPaginationObj().getPageSize());
			this.loadHidDataAndItemName();
			MCCEbizh120203Service mCCEbizh120203Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120203ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			ListResult listResult = mCCEbizh120203Service.querySavedFee(this.getInHospitalDTO());
			if (listResult == null) {
				return NONE;
			}
			PagerHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(this.getRequest(), this.getResponse(),
					PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询已保存的费用
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String feeSelect() {
		try {
			this.initCtrlInHospitalDTO(aka130_12);
			PagerHelper.initPagination(this.getRequest());
			this.getInHospitalDTO().setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.getInHospitalDTO().setPageSize(PagerHelper.getPaginationObj().getPageSize());
			this.loadHidDataAndItemName();
//			MCCEbizh120203Service mCCEbizh120203Service = this.getHygeiaServiceManager()
//					.getBeanByClass(MCCEbizh120203ServiceImpl.class, this.getInHospitalDTO().getAkb020());
//			ListResult listResult = mCCEbizh120203Service.querySavedFee(this.getInHospitalDTO());
//			if (listResult == null) {
//				return NONE;
//			}
//			List<Map> inHospitalDTORows = (List<Map>) listResult.getList();
			List<InHospitalDTO> tempRows = GanSuDemoUtils.getFeeInfo();
			List<Map> inHospitalDTORows = new ArrayList<>();
			if(CollectionUtils.isNotEmpty(tempRows)) {
				for (InHospitalDTO tempRow : tempRows) {
					Map m = new HashMap();
					this.getBeanService().copyProperties(tempRow, m, true);
					inHospitalDTORows.add(m);
				}
			}

			//修改概要：TS19083000214 - 【需求开发】结算云住院费用录入时，点击保存费用后药品信息（等级和规格消失了）
			//修改描述：这里实际已经处理了aka065的展示数据，但是没有正确返回，调整一下
			//修改人及修改时间：李嘉伦 20190903
			/*for (int i = 0; inHospitalDTORows != null && i < inHospitalDTORows.size(); i++) {
				InHospitalDTO inHospitalDTOTemp = new InHospitalDTO();
				this.getBeanService().copyProperties(inHospitalDTORows.get(i), inHospitalDTOTemp, true);
				if (StringUtils.isNotBlank(inHospitalDTOTemp.getAka065())) {
					inHospitalDTOTemp.setAka065_name(CodetableMapping.getDisplayValue("aka065",
							inHospitalDTOTemp.getAka065(), inHospitalDTOTemp.getAka065()));
				}
				if (StringUtils.isNotBlank(inHospitalDTOTemp.getAka070())) {
					inHospitalDTOTemp.setAka070_name(CodetableMapping.getDisplayValue("aka070",
							inHospitalDTOTemp.getAka070(), inHospitalDTOTemp.getAka070()));
				}
				if (StringUtils.isNotBlank(inHospitalDTOTemp.getAke003())) {
					inHospitalDTOTemp.setAke003_name(CodetableMapping.getDisplayValue("ake003",
							inHospitalDTOTemp.getAke003(), inHospitalDTOTemp.getAke003()));
				}
			}
			PagerHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(this.getRequest(), this.getResponse(),
					PagerHelper.getPaginatedList(listResult.getList()));*/
			for (int i = 0; inHospitalDTORows != null && i < inHospitalDTORows.size(); i++) {
				InHospitalDTO inHospitalDTOTemp = new InHospitalDTO();
				this.getBeanService().copyProperties(inHospitalDTORows.get(i), inHospitalDTOTemp, true);
				if (StringUtils.isNotBlank(inHospitalDTOTemp.getAka065())) {
					inHospitalDTOTemp.setAka065_name(CodetableMapping.getDisplayValue("aka065",
							inHospitalDTOTemp.getAka065(), inHospitalDTOTemp.getAka065()));
				}
				if (StringUtils.isNotBlank(inHospitalDTOTemp.getAka070())) {
					inHospitalDTOTemp.setAka070_name(CodetableMapping.getDisplayValue("aka070",
							inHospitalDTOTemp.getAka070(), inHospitalDTOTemp.getAka070()));
				}
				if (StringUtils.isNotBlank(inHospitalDTOTemp.getAke003())) {
					inHospitalDTOTemp.setAke003_name(CodetableMapping.getDisplayValue("ake003",
							inHospitalDTOTemp.getAke003(), inHospitalDTOTemp.getAke003()));
				}
				this.getBeanService().copyProperties(inHospitalDTOTemp, inHospitalDTORows.get(i), true);
			}
			PagerHelper.getPaginationObj().setCount(1);
			DataGridHelper.render(this.getRequest(), this.getResponse(),
					PagerHelper.getPaginatedList(inHospitalDTORows));
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询已保存的费用_家庭病床
	 * 
	 * @return
	 */
	public String feeSelectHomeSickbed() {
		try {
			this.initCtrlInHospitalDTO(aka130_16);
			PagerHelper.initPagination(this.getRequest());
			this.getInHospitalDTO().setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.getInHospitalDTO().setPageSize(PagerHelper.getPaginationObj().getPageSize());
			this.loadHidDataAndItemName();
			MCCEbizh120203Service mCCEbizh120203Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120203ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			ListResult listResult = mCCEbizh120203Service.querySavedFee(this.getInHospitalDTO());
			if (listResult == null) {
				return NONE;
			}
			PagerHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(this.getRequest(), this.getResponse(),
					PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 试算_跨省异地就医费用记账
	 * 
	 * @return
	 */
	public String tryCalcTreatKsyd() {
		try {
			this.initCtrlInHospitalDTO(aka130_82);
			this.getInHospitalDTO().setBka438(bka438_1);
			this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
			this.getInHospitalDTO().setBka047(BizHelper.getUserName());
			MCCEbizh120003Service mCCEbizh120003Service = getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 试算_异地就医记账（包括省内/省外） 【TS19030500222】【需求开发】异地就医费用记账增加试算功能
	 * 
	 * @return
	 */
	public String tryCalcTreatYdjy() {
		try {
			this.initCtrlInHospitalDTO(aka130_12);
			this.getInHospitalDTO().setBka438(bka438_1);
			this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
			this.getInHospitalDTO().setBka047(BizHelper.getUserName());
			this.getInHospitalDTO().setAae139("1");
			this.getInHospitalDTO().setBkm100("0"); // 是否保存计算结果（0：否 1：是）
			MCCEbizh120003Service mCCEbizh120003Service = getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 试算_生育记账
	 * 
	 * @return
	 */
	public String tryCalcTreatMaternity() {
		try {
			this.initCtrlInHospitalDTO(aka130_52);
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 试算_工伤记账
	 * 
	 * @return
	 */
	public String tryCalcTreatInjury() {
		try {
			this.initCtrlInHospitalDTO(aka130_42);
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 门特试算_门特记账
	 * 
	 * @return
	 */
	public String tryCalcTreatSpecial() {
		try {
			this.initCtrlInHospitalDTO(aka130_16);
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 
	 * 费用试算_费用记账
	 * 
	 * @return
	 */
	public String tryCalcTreat() {
		try {
			this.initCtrlInHospitalDTO(aka130_12);
			this.getInHospitalDTO().setBka438(bka438_1);
//			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
//					.getBeanByClass(MCCEbizh120003ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			//InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			InHospitalDTO inHospitalDTOTemp = GanSuDemoUtils.tryCheckout(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 门特试算_家庭病床记账
	 * 
	 * @return
	 */
	public String tryCalcTreatHomeSickbed() {
		try {
			this.initCtrlInHospitalDTO(aka130_14);
			this.getInHospitalDTO().setBka438(bka438_1);
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 费用计算(试算)_跨省异地就医出院登记
	 * 
	 * @return
	 */
	public String outRegisterKsydTryCalcTreat() {
		try {
			this.initCtrlInHospitalDTO(aka130_82);
			this.getInHospitalDTO().setBka438(bka438_3);
			this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
			this.getInHospitalDTO().setBka047(BizHelper.getUserName());
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 费用计算(试算)_省内异地就医出院登记
	 * 
	 * @return
	 */
	public String outRegisterSnydTryCalcTreat() {
		try {
			this.initCtrlInHospitalDTO(aka130_72);
			this.getInHospitalDTO().setBka438(bka438_3);
			this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
			this.getInHospitalDTO().setBka047(BizHelper.getUserName());
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 费用计算(试算)_生育出院登记
	 * 
	 * @return
	 */
	public String outRegisterMaternityTryCalcTreat() {
		try {
			this.initCtrlInHospitalDTO(aka130_52);
			this.getInHospitalDTO().setBka438(bka438_3);
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 费用计算(试算)_工伤出院登记
	 * 
	 * @return
	 */
	public String outRegisterInjuryTryCalcTreat() {
		try {
			this.initCtrlInHospitalDTO(aka130_42);
			this.getInHospitalDTO().setBka438(bka438_3);
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 费用计算(试算)_门特出院登记
	 * 
	 * @return
	 */
	public String outRegisterTryCalcTreatSpecial() {
		try {
			this.initCtrlInHospitalDTO(aka130_16);
			this.getInHospitalDTO().setBka438(bka438_3);
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 
	 * 费用计算(试算)_出院登记
	 * 
	 * @return
	 */
	public String outRegisterTryCalcTreat() {
		try {
			this.initCtrlInHospitalDTO(aka130_12);
			this.getInHospitalDTO().setBka438(bka438_3);
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 费用计算(试算)_家庭病床出院登记
	 * 
	 * @return
	 */
	public String outRegisterTryCalcTreatHomeSickbed() {
		try {
			this.initCtrlInHospitalDTO(aka130_14);
			this.getInHospitalDTO().setBka438(bka438_3);
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 
	 * 费用计算(试算)_跨省异地就医出院结算
	 * 
	 * @return
	 */
	public String outChargeKsydTryCalcTreat() {
		try {
			initCtrlInHospitalDTO(aka130_82);
			this.getInHospitalDTO().setBka438(bka438_2);
			this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
			this.getInHospitalDTO().setBka047(BizHelper.getUserName());
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 
	 * 费用计算(试算)_省内异地就医出院结算
	 * 
	 * @return
	 */
	public String outChargeYdjyTryCalcTreat() {
		try {
			initCtrlInHospitalDTO(aka130_12);
			this.getInHospitalDTO().setBka438(bka438_2);
			this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
			this.getInHospitalDTO().setBka047(BizHelper.getUserName());
			this.getInHospitalDTO().setAae139("1");
			this.getInHospitalDTO().setBkm100("0"); // 是否保存计算结果（0：否 1：是）
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 
	 * 费用计算(试算)_生育出院结算
	 * 
	 * @return
	 */
	public String outChargeMaternityTryCalcTreat() {
		try {
			this.initCtrlInHospitalDTO(aka130_52);
			this.getInHospitalDTO().setBka438(bka438_2);
			MCCEbizh120003Service mCCEbizh120003Service = getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 
	 * 费用计算(试算)_工伤出院结算
	 * 
	 * @return
	 */
	public String outChargeInjuryTryCalcTreat() {
		try {
			this.initCtrlInHospitalDTO(aka130_42);
			this.getInHospitalDTO().setBka438(bka438_2);
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 
	 * 费用计算(试算)_门特出院结算
	 * 
	 * @return
	 */
	public String outChargeSpecialTryCalcTreat() {
		try {
			this.initCtrlInHospitalDTO(aka130_16);
			this.getInHospitalDTO().setBka438(bka438_2);
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 
	 * 费用计算(试算)_出院结算
	 * 
	 * @return
	 */
	public String outChargeTryCalcTreat() {
		try {
			try {
				this.initCtrlInHospitalDTO(aka130_12);
			} catch (Exception e) {
				this.getInHospitalDTO().setBke548(null);
			}
			this.getInHospitalDTO().setBka438(bka438_2);
			this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
			this.getInHospitalDTO().setBka047(BizHelper.getUserName());

//			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
//					.getBeanByClass(MCCEbizh120003ServiceImpl.class, this.getInHospitalDTO().getAkb020());
//			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			InHospitalDTO inHospitalDTOTemp = GanSuDemoUtils.tryCheckout(this.getInHospitalDTO());

			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 
	 * 费用计算(试算)_家庭病床出院结算
	 * 
	 * @return
	 */
	public String outChargeHomeSickbedTryCalcTreat() {
		try {
			this.initCtrlInHospitalDTO(aka130_14);
			this.getInHospitalDTO().setBka438(bka438_2);
			MCCEbizh120003Service mCCEbizh120003Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120003ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120003Service.checkAndCalcFeeInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 跨省异地就医取消结算回退冲正_将已取消结算的业务重置回已出院结算
	 * 
	 * @return
	 */
	public String outChargeKsydCorrect() {
		try {
			if (this.isPostRequest()) {
				try {
					this.getInHospitalDTO().setBka977_name("1");
					this.initCtrlInHospitalDTO(aka130_82);
					this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka047(BizHelper.getUserName());
					this.getInHospitalDTO().setAaa113("-1");
					MCCEbizh120106Service mCCEbizh120106Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120106ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120106Service.outCharge(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的跨省异地就医回退冲正成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_82);
				return "outChargeKsydCorrect";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 跨省异地就医出院结算
	 * 
	 * @return
	 */
	public String outChargeKsyd() {
		try {
			if (this.isPostRequest()) {
				try {
					this.getInHospitalDTO().setBka977_name("1");
					this.initCtrlInHospitalDTO(aka130_82);
					this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka047(BizHelper.getUserName());
					MCCEbizh120106Service mCCEbizh120106Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120106ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120106Service.outCharge(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的跨省异地就医出院结算保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_82);
				return "outChargeKsyd";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 省内异地出院结算
	 * 
	 * @return
	 */
	public String outChargeSnyd() {
		try {
			if (this.isPostRequest()) {
				try {
					this.getInHospitalDTO().setBka977_name("1");
					this.initCtrlInHospitalDTO(aka130_72);
					this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka047(BizHelper.getUserName());
					MCCEbizh120106Service mCCEbizh120106Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120106ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120106Service.outCharge(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的省内异地出院结算保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_72);
				return "outChargeSnyd";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 异地出院结算（包括省内、省外）
	 * 
	 * @return
	 */
	public String outChargeYdjy() {
		try {
			if (this.isPostRequest()) {
				try {
					this.getInHospitalDTO().setBka977_name("1");
					this.getInHospitalDTO().setAae139("1");
					this.getInHospitalDTO().setBkm100("3");
					this.initCtrlInHospitalDTO(aka130_12);
					this.getInHospitalDTO().setAae014(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka034(BizHelper.getUserName());
					String aae031 = this.getInHospitalDTO().getAae031();
					this.getInHospitalDTO()
							.setAae031(DateFunc.dateToString(DateFunc.parseDate(aae031), DateService._yyyyMMddHHmmss));
					if (UtilFunc.isNotBlank(this.getInHospitalDTO().getBke548())) {
						String[] str = this.getInHospitalDTO().getBke548().split("\\|");
						this.getInHospitalDTO().setBke550(str[3]);
						this.getInHospitalDTO().setAac0021(str[1]);
						this.getInHospitalDTO().setAac003(str[4]);
						this.getInHospitalDTO().setBka100(str[2]);
					}
					MCCEbizh120106Service mCCEbizh120106Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120106ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120106Service.outCharge(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的异地出院结算保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_12);
				this.loadCodeSelectData();
				this.getInHospitalDTO().setAae031(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
				return "outChargeYdjy";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 生育出院结算
	 * 
	 * @return
	 */
	public String outChargeMaternity() {
		try {
			if (this.isPostRequest()) {
				try {
					this.getInHospitalDTO().setBka977_name("1");
					this.initCtrlInHospitalDTO(aka130_52);
					this.checkDoDebit();
					this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka047(BizHelper.getUserName());
					this.getInHospitalDTO().setAae014(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka034(BizHelper.getUserName());
					MCCEbizh120106Service mCCEbizh120106Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120106ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120106Service.outCharge(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的生育出院结算保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_52);
				this.loadCodeSelectData();
				return "outChargeMaternity";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 工伤出院结算
	 * 
	 * @return
	 */
	public String outChargeInjury() {
		try {
			if (this.isPostRequest()) {
				try {
					this.getInHospitalDTO().setBka977_name("1");
					this.initCtrlInHospitalDTO(aka130_42);
					this.checkDoDebit();
					this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka047(BizHelper.getUserName());
					MCCEbizh120106Service mCCEbizh120106Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120106ServiceImpl.class, getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120106Service.outCharge(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的工伤出院结算保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_42);
				return "outChargeInjury";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 门特出院结算
	 * 
	 * @return
	 */
	public String outChargeSpecial() {
		try {
			if (this.isPostRequest()) {
				try {
					this.getInHospitalDTO().setBka977_name("1");
					this.initCtrlInHospitalDTO(aka130_16);
					this.checkDoDebit();
					this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka047(BizHelper.getUserName());
					MCCEbizh120106Service mCCEbizh120106Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120106ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120106Service.outCharge(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的门特出院结算保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_16);
				return "outChargeSpecial";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 出院结算
	 * 
	 * @return
	 */
	public String outCharge() {
		try {
			if (this.isPostRequest()) {
				try {
					this.getInHospitalDTO().setBka977_name("1");
					this.initCtrlInHospitalDTO(aka130_12);
					this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka047(BizHelper.getUserName());
					this.getInHospitalDTO().setAae014(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka034(BizHelper.getUserName());
//					MCCEbizh120106Service mCCEbizh120106Service = this.getHygeiaServiceManager()
//							.getBeanByClass(MCCEbizh120106ServiceImpl.class, this.getInHospitalDTO().getAkb020());
//					InHospitalDTO inHospitalDTOTemp = mCCEbizh120106Service.outCharge(this.getInHospitalDTO());
					InHospitalDTO inHospitalDTOTemp = GanSuDemoUtils.checkout(this.getInHospitalDTO());

					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的出院结算保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_12);
				this.loadCodeSelectData();
				return "outCharge";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 家庭病床出院结算
	 * 
	 * @return
	 */
	public String outChargeHomeSickbed() {
		try {
			if (this.isPostRequest()) {
				try {
					this.getInHospitalDTO().setBka977_name("1");
					this.initCtrlInHospitalDTO(aka130_14);
					this.checkDoDebit();
					this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
					this.getInHospitalDTO().setBka047(BizHelper.getUserName());
					MCCEbizh120106Service mCCEbizh120106Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120106ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					InHospitalDTO inHospitalDTOTemp = mCCEbizh120106Service.outCharge(this.getInHospitalDTO());
					this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的家庭病床出院结算保存成功！",
							this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO(aka130_14);
				this.loadCodeSelectData();
				return "outChargeHomeSickbed";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 查询基金支付
	 * 
	 * @return
	 */
	public String queryFundPay() {
		try {
			this.initCtrlInHospitalDTO();
			this.loadHidDataAndItemName();
//			MCCEbizh120312Service mCCEbizh120312Service = this.getHygeiaServiceManager()
//					.getBeanByClass(MCCEbizh120312ServiceImpl.class, this.getInHospitalDTO().getAkb020());
//			List<FundPayInfoDTO> fundPayInfoDTORows = mCCEbizh120312Service.queryFundPay(this.getInHospitalDTO());
			List<FundPayInfoDTO> fundPayInfoDTORows = GanSuDemoUtils.queryFundPay(this.getInHospitalDTO());
			if (UtilFunc.isEmpty(fundPayInfoDTORows)) {
				return NONE;
			}
			this.setJSONReturn(fundPayInfoDTORows);
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 上传费用_省内异地就医出院登记
	 * 
	 * @return
	 */
	public String outRegisterSnydInputFee() {
		try {
			this.initCtrlInHospitalDTO(aka130_72);
			this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
			this.getInHospitalDTO().setBka047(BizHelper.getUserName());
			MCCEbizh120002Service mCCEbizh120002Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120002Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120002Service.proccessFeeInfoSnyd(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的费用上传成功！", this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 上传费用_跨省异地就医出院登记
	 * 
	 * @return
	 */
	public String outRegisterKsydInputFee() {
		try {
			this.initCtrlInHospitalDTO(aka130_82);
			this.getInHospitalDTO().setBka046(BizHelper.getLoginUser());
			this.getInHospitalDTO().setBka047(BizHelper.getUserName());
			MCCEbizh120002Service mCCEbizh120002Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120002Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTOTemp = mCCEbizh120002Service.proccessFeeInfoSnyd(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);
			this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的费用上传成功！", this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询业务信息
	 * 
	 * @return
	 */
	public String findBizinfo() {
		try {
			this.getInHospitalDTO().setAkb020(BizHelper.getAkb020());
			this.getInHospitalDTO().setAkb021(BizHelper.getAkb021());
			this.getInHospitalDTO().setAaa027(BizHelper.getAaa027());
			String arg_value = this.getInHospitalDTO().getQuerystring();
			String arg_name = this.getInHospitalDTO().getBka895();
			if (!"bka100".equals(arg_name)) {
				arg_name = this.getQueryName(arg_value);
			}
			this.getInHospitalDTO().setBka895(arg_name);
			MCCEbizh120102Service mCCEbizh120102Service = getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inhospital = mCCEbizh120102Service.queryBizInfo(this.getInHospitalDTO());
			if (inhospital != null) {
				this.getBeanService().copyProperties(inhospital, this.getInHospitalDTO(), true);
				this.loadHidDataAndItemName();
				this.setJSONReturn(this.getInHospitalDTO());
			} else {
				this.saveJSONError("没有符合条件的业务信息！");
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 
	 * @param arg_value
	 * @return
	 */
	private String getQueryName(String arg_value) {
		QueryStringService queryStringService = this.getHygeiaServiceManager().getBeanByClass(QueryStringService.class,
				BizHelper.getAkb020());
		if (StringUtils.isBlank(arg_value)) {
			throw new HygeiaException("您输入的查询条件为空，请确定！");
		}
		if (arg_value.length() > 20) {
			throw new HygeiaException("您输入的查询条件位数过多");
		}
		if (!(queryStringService.isValidatedaaz217(arg_value))) {
			throw new HygeiaException("请输入就医登记号查询！");
		}
		return "aaz217";
	}

	/**
	 * 预付款管理
	 * 
	 * @return
	 */
	public String queryForegift() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO();
					PagerHelper.initPagination(this.getRequest());
					getInHospitalDTO().setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
					getInHospitalDTO().setPageSize(PagerHelper.getPaginationObj().getPageSize());
					MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
					List<InHospitalDTO> inHospitalDTORows = mCCEbizh120102Service
							.queryInHospitalaaz217s(this.getInHospitalDTO());
					if (UtilFunc.isEmpty(inHospitalDTORows)) {
						return NONE;
					}
					PagerHelper.getPaginationObj().setCount(inHospitalDTORows.size() + 1);
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(inHospitalDTORows));
				} catch (Exception e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				this.getInHospitalDTO().setAae030(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				this.getInHospitalDTO().setAae031(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				String bka891 = getParameter("bka891");// 0未
				this.getInHospitalDTO().setAka130(getParameter("aka130"));
				this.getInHospitalDTO().setBka891(bka891);
				return "queryForegift";
			}
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 预付款
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String foregiftEdit() {
		try {
			if (this.isPostRequest()) {
				if ("getBkn2DtoList".equals(getParameter("type", ""))) {// 预付款列表
					HospApiService hospService = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEHospApiServiceImpl.class, this.getInHospitalDTO().getAkb020());
					List<Bkn2Dto> bkn2DtoList = hospService.selectByBkn2Aaz217(this.getInHospitalDTO());
					setJSONReturn(bkn2DtoList);
					return NONE;
				}

				Map retMsg = new HashMap();
				retMsg.put("suss", "0");
				retMsg.put("message", "保存失败");

				String bkn568 = getParameter("bkn568", "0");
				if (Double.parseDouble(bkn568) <= 0) {
					// throw new HygeiaException("金额必须大于0["+bkn568+"]!");
				}
				InHospitalDTO inHospitalDTOParam = new InHospitalDTO();
				inHospitalDTOParam.setAaz217(this.getInHospitalDTO().getAaz217());
				inHospitalDTOParam.setAkb020(this.getInHospitalDTO().getAkb020());
				MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
						.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
				InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(inHospitalDTOParam);
				if (inHospitalDTOTemp == null) {
					throw new HygeiaException("未找到对应数据[" + this.getInHospitalDTO().getAaz217() + "]!");
				}
				inHospitalDTOParam = null;

				if (inHospitalDTOTemp.getBka245() == null) {
					inHospitalDTOTemp.setBka245("0");
				}
				Bkn2Dto bkn2Dto = new Bkn2Dto();
				this.getBeanService().copyProperties(inHospitalDTOTemp, bkn2Dto, true);
				bkn2Dto.setId(null);
				bkn2Dto.setBka006(inHospitalDTOTemp.getBka006());
				bkn2Dto.setBkn149(new Date());
				bkn2Dto.setBkn565(BizHelper.getLoginUser());
				bkn2Dto.setBkn568(new BigDecimal(bkn568));
				bkn2Dto.setBkn574("2");
				HospApiService hospService = this.getHygeiaServiceManager().getBeanByClass(MCCEHospApiServiceImpl.class,
						this.getInHospitalDTO().getAkb020());
				inHospitalDTOTemp
						.setBka245(bkn2Dto.getBkn568().add(new BigDecimal(inHospitalDTOTemp.getBka245())).toString());
				bkn2Dto.setCurforegift(new BigDecimal(inHospitalDTOTemp.getBka245()));
				hospService.saveBkn2(bkn2Dto, inHospitalDTOTemp);

				retMsg.put("suss", "1");
				retMsg.put("message", "保存成功");

				setJSONReturn(retMsg);

				return NONE;
			} else if ("printForegift".equals(getParameter("type", ""))) {

				if (this.getInHospitalDTO().getAaz217() == null
						|| StringUtils.isBlank(this.getInHospitalDTO().getAaz217())) {
					throw new HygeiaException("aaz217为空");
				}
				this.initCtrlInHospitalDTO();
				MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
						.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
				InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
				if (inHospitalDTOTemp == null) {
					throw new HygeiaException("未找到对应数据[" + this.getInHospitalDTO().getAaz217() + "]!");
				}

				this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);

				if (this.getInHospitalDTO().getBka245() == null) {
					this.getInHospitalDTO().setBka245("0");
				}
				this.getInHospitalDTO().setAac004_name(CodetableMapping.getDisplayValue("aac004",
						this.getInHospitalDTO().getAac004(), this.getInHospitalDTO().getAac004()));
				Map mapData = new HashMap();
				List<Map> reportForegiftMapList = new ArrayList<Map>();
				Map reportForegiftMap = new HashMap();
				this.getBeanService().copyProperties(this.getInHospitalDTO(), reportForegiftMap, true);
				reportForegiftMap.put("jfrq", this.getDateService().dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
				reportForegiftMap.put("bka245dx", MoneyUtil.toChinese(this.getInHospitalDTO().getBka245()));
				reportForegiftMapList.add(reportForegiftMap);
				mapData.put("data1", reportForegiftMapList);

				String reportIDfirst = inhospitalManagerService.createForegiftReport("inhosp/foregift.xls",
						getInHospitalDTO().getAaz217() + "_foregift", 1, mapData, "foregift",
						this.getInHospitalDTO().getAae011());
				mapData = null;
				setAttribute("reportIDfirst", reportIDfirst);
				return "printForegift";
			} else if ("printForegiftDetail".equals(getParameter("type", ""))) {
				String bkn2IdString = getParameter("bkn2Id", "");
				if ("".equals(bkn2IdString)) {
					throw new HygeiaException("id为空");
				}
				this.initCtrlInHospitalDTO();

				HospApiService hospService = this.getHygeiaServiceManager().getBeanByClass(MCCEHospApiServiceImpl.class,
						this.getInHospitalDTO().getAkb020());
				Bkn2Dto bkn2Dto = hospService.selectByPrimaryKey(this.getInHospitalDTO().getAkb020(),
						Integer.parseInt(bkn2IdString));
				if (bkn2Dto == null) {
					throw new HygeiaException("未查到数据！");
				}
				this.getInHospitalDTO().setAaz217(bkn2Dto.getAaz217());
				if (this.getInHospitalDTO().getAaz217() == null
						|| StringUtils.isBlank(this.getInHospitalDTO().getAaz217())) {
					throw new HygeiaException("aaz217为空");
				}
				MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
						.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
				InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
				if (inHospitalDTOTemp == null) {
					throw new HygeiaException("未找到对应数据[" + this.getInHospitalDTO().getAaz217() + "]!");
				}

				this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);

				if (this.getInHospitalDTO().getBka245() == null) {
					this.getInHospitalDTO().setBka245("0");
				}
				this.getInHospitalDTO().setAac004_name(CodetableMapping.getDisplayValue("aac004",
						this.getInHospitalDTO().getAac004(), this.getInHospitalDTO().getAac004()));
				Map mapData = new HashMap();
				List<Map> reportForegiftMapList = new ArrayList<Map>();
				Map reportForegiftMap = new HashMap();
				this.getBeanService().copyProperties(this.getInHospitalDTO(), reportForegiftMap, true);
				reportForegiftMap.put("jfrq",
						this.getDateService().dateToString(bkn2Dto.getBkn149(), "yyyy-MM-dd HH:mm:ss"));
				reportForegiftMap.put("bkn568", bkn2Dto.getBkn568());
				reportForegiftMap.put("bkn568dx", MoneyUtil.toChinese(bkn2Dto.getBkn568().toString()));
				reportForegiftMapList.add(reportForegiftMap);
				mapData.put("data1", reportForegiftMapList);

				String reportIDfirst = inhospitalManagerService.createForegiftReport("inhosp/foregiftDetail.xls",
						getInHospitalDTO().getAaz217() + "_foregiftDetail", 1, mapData, "foregiftDetail",
						this.getInHospitalDTO().getAae011());
				mapData = null;
				setAttribute("reportIDfirst", reportIDfirst);
				return "printForegiftDetail";
			} else {
				if (this.getInHospitalDTO().getAaz217() == null
						|| StringUtils.isBlank(this.getInHospitalDTO().getAaz217())) {
					throw new HygeiaException("aaz217为空");
				}
				this.initCtrlInHospitalDTO();
				MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
						.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
				InHospitalDTO inHospitalDTOTemp = mCCEbizh120102Service.queryAaz217(this.getInHospitalDTO());
				if (inHospitalDTOTemp == null) {
					throw new HygeiaException("未找到对应数据[" + this.getInHospitalDTO().getAaz217() + "]!");
				}

				this.getBeanService().copyProperties(inHospitalDTOTemp, this.getInHospitalDTO(), true);

				if (this.getInHospitalDTO().getBka245() == null) {
					this.getInHospitalDTO().setBka245("0");
				}
				this.getInHospitalDTO().setAac004_name(CodetableMapping.getDisplayValue("aac004",
						this.getInHospitalDTO().getAac004(), this.getInHospitalDTO().getAac004()));
				this.getInHospitalDTO().setBka035_name(CodetableMapping.getDisplayValue("bka035",
						this.getInHospitalDTO().getBka035(), this.getInHospitalDTO().getBka035()));

				return "queryForegiftDetail";
			}

		} catch (Exception e) {
			this.getCommunalService().error(this.logger, e, null);
			this.saveError(e.getMessage());
			throw new HygeiaException(e.getMessage());
		}
	}

	/**
	 * 加载多诊断信息
	 * 
	 * @return
	 */
	public String loadDiagnoseInfos() {
		try {
			String aaz217 = getParameter("aaz217");
//			MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
//					.getBeanByClass(MCCEbizh120102ServiceImpl.class, BizHelper.getAkb020());
//			List<Kcg4DTO> kcg4DTO = mCCEbizh120102Service.loadDiagnoseInfosByAaz217(aaz217, BizHelper.getAkb020());
			List<Kcg4DTO> kcg4DTO = GanSuDemoUtils.loadDiagnoseInfos();
			setJSONReturn(kcg4DTO);
			return NONE;
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	public String selectKcd1Log() {
		try {
			this.initCtrlInHospitalDTO(aka130_12);
			MCCEbizh120104Service mCCEbizh120104Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120104ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			List<KCD1Log> kcd1Log = mCCEbizh120104Service.selectKcd1Log(this.getInHospitalDTO());
			setJSONReturn(kcd1Log);
			return NONE;
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 判断疾病是不是属于106个单病种
	 * 
	 * @return
	 */
	public String is106Disease() {
		try {
			this.initCtrlInHospitalDTO(aka130_12);

			DiseaseQueryService diseaseQueryService = (DiseaseQueryService) this.getHygeiaServiceManager()
					.getBean("diseaseQueryServiceesbImpl", this.getInHospitalDTO().getAkb020());
			this.getKa06dto().setAaa027(BizHelper.getAaa027());
			String aaz266 = diseaseQueryService.is106Disease(this.getKa06dto());
			setJSONReturn(aaz266);
			return NONE;
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询106个单病种的副诊断
	 * 
	 * @return
	 */
	public String choose106SubDisease() {
		try {
			if (this.isPostRequest()) {
				try {
					PagerHelper.initPagination(this.getRequest());
					this.getKa06dto().setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
					this.getKa06dto().setPageSize(PagerHelper.getPaginationObj().getPageSize());
					this.getKa06dto().setAkb020(BizHelper.getAkb020());
					this.getKa06dto().setAaa027(BizHelper.getAaa027());
					this.getKa06dto().setBke217("2");

					DiseaseQueryService diseaseQueryService = (DiseaseQueryService) this.getHygeiaServiceManager()
							.getBean("diseaseQueryServiceesbImpl", this.getKa06dto().getAkb020());
					List<KA06DTO> cumulativeList = diseaseQueryService.querybke216(this.getKa06dto());
					String bke216 = "";
					for (int i = 0; i < cumulativeList.size(); i++) {
						KA06DTO bke216temp = cumulativeList.get(i);
						if (bke216temp.getBke216() != null && !"".equals(bke216temp.getBke216())) {
							bke216 = bke216temp.getBke216();
						}
					}
					if (StringUtils.isBlank(bke216)) {
					} else {
						this.getKa06dto().setAaa027(bke216);
					}
					this.getKa06dto().setAka120(getParameter("aka120106"));
					ListResult listResult = diseaseQueryService.choose106SubDisease(this.getKa06dto());

					PagerHelper.getPaginationObj().setCount(listResult.getCount());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(listResult.getList()));
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				return "choose106SubDisease";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}
    
	/**
	 * TS19052700141   【需求开发】结算云（医院端）出院结算界面和住院信息查询界面需要显示多诊断信息  
	 * 新增方法获取住院多诊断信息
	 * 陈洁 20190529
	 * 查询多诊断信息
	 * 
	 * @return
	 */
	public String queryDiagnoseInfos() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO();
					PagerHelper.initPagination(this.getRequest());
					MCCEbizh120102Service mCCEbizh120102Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120102ServiceImpl.class, BizHelper.getAkb020());
					List<Kcg4DTO> kcg4DTO = mCCEbizh120102Service.loadDiagnoseInfosByAaz217(this.getInHospitalDTO().getAaz217(), BizHelper.getAkb020());
					if (kcg4DTO != null) {
						PagerHelper.getPaginationObj().setCount(kcg4DTO.size());
						DataGridHelper.render(this.getRequest(), this.getResponse(),
								PagerHelper.getPaginatedList(kcg4DTO));
					}
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn + e.getMessage());
				}
				return NONE;
			} else {
				return "queryDiagnose";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}
	/**
	 * 
	 * @return 生育必填
	 */
	public boolean isMaternityRequired() {
		return maternityRequired;
	}

	/**
	 * 
	 * @param maternityRequired 生育必填
	 */
	public void setMaternityRequired(boolean maternityRequired) {
		this.maternityRequired = maternityRequired;
	}

	/**
	 * 
	 * @return 费用日期
	 */
	public String getAke007() {
		return ake007;
	}

	/**
	 * 
	 * @param ake007 费用日期
	 */
	public void setAke007(String ake007) {
		this.ake007 = ake007;
	}

	/**
	 * 
	 * @return 当前日期
	 */
	public String getBka890() {
		return bka890;
	}

	/**
	 * 
	 * @param bka890 当前日期
	 */
	public void setBka890(String bka890) {
		this.bka890 = bka890;
	}

	public String getAka120106() {
		return aka120106;
	}

	public void setAka120106(String aka120_106) {
		this.aka120106 = aka120_106;
	}
}
