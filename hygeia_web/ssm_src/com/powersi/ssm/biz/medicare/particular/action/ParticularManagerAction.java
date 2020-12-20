package com.powersi.ssm.biz.medicare.particular.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.powersi.biz.medicare.catalog.service.api.HospCataApiService;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120302Service;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.PaginationHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.util.DataGridHelper;
import com.powersi.hygeia.web.util.PagerHelper;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

/**
 * 
 * @author 宋程杰、刘勇
 *
 */
@Action(value = "ParticularManagerAction", results = {
		@Result(name = "referralTransferApply", location = "/pages/biz/medicare/particular/referralTransferApply.jsp"),
		@Result(name = "referralTransferAudit", location = "/pages/biz/medicare/particular/referralTransferAudit.jsp"),
		@Result(name = "referralTransferModify", location = "/pages/biz/medicare/particular/referralTransferModify.jsp"),
		@Result(name = "referralTransferInfoView", location = "/pages/biz/medicare/particular/referralTransferInfoView.jsp"),
		@Result(name = "referralTransferInfoCheckPass", location = "/pages/biz/medicare/particular/referralTransferInfoCheckPass.jsp"),
		@Result(name = "referralTransferInfoCheckNotPass", location = "/pages/biz/medicare/particular/referralTransferInfoCheckNotPass.jsp"),
		@Result(name = "referralTransferInfoModify", location = "/pages/biz/medicare/particular/referralTransferInfoModify.jsp"),
		@Result(name = "queryHospInfo", location = "/pages/biz/medicare/particular/chooseHospInfo.jsp") })
public class ParticularManagerAction extends BaseParticularManagerAction {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 转诊转院个人信息查询以及上次住院/门诊信息查询
	 * 
	 * @return
	 */
	public String findPersonLastInhospInfo() {
		try {
			this.initCtrlInHospitalDTO();
			MCCEbizh120302Service mCCEbizh120302Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120302Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTO = mCCEbizh120302Service.getPersonLastInhospInfo(this.getInHospitalDTO());
			if (inHospitalDTO == null) {
				return NONE;
			}
			this.getBeanService().copyProperties(inHospitalDTO, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn+e.getMessage());
		}
		return NONE;
	}

	/**
	 * 转诊转院申请
	 * 
	 * @return
	 */
	public String referralTransferApply() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO();
					MCCEbizh120302Service mCCEbizh120302Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120302Service.class, this.getInHospitalDTO().getAkb020());
					mCCEbizh120302Service.saveReferralApplyInfo(this.getInHospitalDTO());
					this.loadHidDataAndItemName();
					this.setJSONReturn("[" + this.getInHospitalDTO().getAac003() + "]的"
							+ this.getInHospitalDTO().getBka600_name() + "登记保存成功!", this.getInHospitalDTO());
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn+e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO();
				this.getInHospitalDTO().setBka600("2"); // 默认选中转院
				this.getInHospitalDTO().setBke129(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "referralTransferApply";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn+e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 转入医院 转诊转院查询
	 * 
	 * @return
	 */
	public String referralTransferAudit() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO();
					// 将当前操作员所在医院编码赋值到转入医院编码上，便于后续查询
					this.getInHospitalDTO().setAke017(this.getInHospitalDTO().getAkb020());
					MCCEbizh120302Service mCCEbizh120302Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120302Service.class, this.getInHospitalDTO().getAkb020());
					List<InHospitalDTO> inHospitalDTORows = mCCEbizh120302Service
							.getChangeHospApplyList(this.getInHospitalDTO());
					if (UtilFunc.isEmpty(inHospitalDTORows)) {
						return NONE;
					}
					// 对结果集中部分字段进行转义
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						this.loadHidDataAndItemName(inHospitalDTORow);
					}
					PagerHelper.initPagination(this.getRequest());
					PagerHelper.getPaginationObj().setCount(inHospitalDTORows.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(inHospitalDTORows));
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO();
				this.getInHospitalDTO().setBka600("2"); // 默认选中住院
				this.getInHospitalDTO()
						.setAae030(DateFunc.dateToString(DateFunc.addDays(DateFunc.getDate(), -7), "yyyyMMdd"));
				this.getInHospitalDTO().setAae031(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "referralTransferAudit";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn+e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 转出医院 转诊转院申请查询
	 * 
	 * @return
	 */
	public String referralTransferModify() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO();
					MCCEbizh120302Service mCCEbizh120302Service = this.getHygeiaServiceManager()
							.getBeanByClass(MCCEbizh120302Service.class, this.getInHospitalDTO().getAkb020());
					List<InHospitalDTO> inHospitalDTORows = mCCEbizh120302Service
							.getChangeHospApplyList(this.getInHospitalDTO());
					if (UtilFunc.isEmpty(inHospitalDTORows)) {
						return NONE;
					}
					// 对结果集中部分字段进行转义
					for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
						this.loadHidDataAndItemName(inHospitalDTORow);
					}
					PagerHelper.initPagination(this.getRequest());
					PagerHelper.getPaginationObj().setCount(inHospitalDTORows.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(inHospitalDTORows));
				} catch (Throwable e) {
					String errLogSn = this.addErrSNInfo();
					this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
							.append(this.addNotBlankParameters()).append(":处理结果:").toString());
					this.saveJSONError(errLogSn+e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO();
				this.getInHospitalDTO().setBka600("2"); // 默认选中住院
				this.getInHospitalDTO()
						.setAae030(DateFunc.dateToString(DateFunc.addDays(DateFunc.getDate(), -7), "yyyyMMdd"));
				this.getInHospitalDTO().setAae031(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				return "referralTransferModify";
			}
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn+e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 转诊转院审批 由参数决定更新内容 可能为更新、审批通过或审批不通过
	 * 
	 * @return
	 */
	public String updateReferralTransferInfo() {
		try {
			this.initCtrlInHospitalDTO();
			MCCEbizh120302Service mCCEbizh120302Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120302Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTO = mCCEbizh120302Service.updateReferralApplyInfo(this.getInHospitalDTO());
			this.getBeanService().copyProperties(inHospitalDTO, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn+e.getMessage());
		}
		return NONE;
	}

	/**
	 * 转诊转院申请详情页面信息获取 只查看
	 * 
	 * @return
	 */
	public String referralTransferInfoView() {
		try {
			InHospitalDTO inHospitalDTO = this.getSelectReferalTransferInfo();
			if (inHospitalDTO == null) {
				return NONE;
			}
			this.getBeanService().copyProperties(inHospitalDTO, this.getInHospitalDTO(), true);
			return "referralTransferInfoView";
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn+e.getMessage());
		}
		return NONE;
	}

	/**
	 * 转诊转院申请审批通过详情页面信息获取
	 * 
	 * @return
	 */
	public String referralTransferInfoCheckPass() {
		try {
			InHospitalDTO inHospitalDTO = this.getSelectReferalTransferInfo();
			if (inHospitalDTO == null) {
				return NONE;
			}
			this.getBeanService().copyProperties(inHospitalDTO, this.getInHospitalDTO(), true);
			return "referralTransferInfoCheckPass";
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn+e.getMessage());
		}
		return NONE;
	}

	/**
	 * 转诊转院申请审批不通过详情页面信息获取
	 * 
	 * @return
	 */
	public String referralTransferInfoCheckNotPass() {
		try {
			InHospitalDTO inHospitalDTO = this.getSelectReferalTransferInfo();
			if (inHospitalDTO == null) {
				return NONE;
			}
			this.getBeanService().copyProperties(inHospitalDTO, this.getInHospitalDTO(), true);
			return "referralTransferInfoCheckNotPass";
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn+e.getMessage());
		}
		return NONE;
	}

	/**
	 * 转诊转院申请审批修改详情页面信息获取
	 * 
	 * @return
	 */
	public String referralTransferInfoModify() {
		try {
			InHospitalDTO inHospitalDTO = this.getSelectReferalTransferInfo();
			if (inHospitalDTO == null) {
				return NONE;
			}
			this.getBeanService().copyProperties(inHospitalDTO, this.getInHospitalDTO(), true);
			this.loadHidDataAndItemName();
			return "referralTransferInfoModify";
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn+e.getMessage());
		}
		return NONE;
	}

	/**
	 * 获取指定转诊转院信息
	 * 
	 * @return
	 */
	private InHospitalDTO getSelectReferalTransferInfo() {
		this.initCtrlInHospitalDTO();
		MCCEbizh120302Service mCCEbizh120302Service = this.getHygeiaServiceManager()
				.getBeanByClass(MCCEbizh120302Service.class, this.getInHospitalDTO().getAkb020());
		List<InHospitalDTO> inHospitalDTORows = mCCEbizh120302Service.getChangeHospApplyList(this.getInHospitalDTO());
		if (UtilFunc.isEmpty(inHospitalDTORows)) {
			return null;
		}
		InHospitalDTO inHospitalDTO = inHospitalDTORows.get(0);
		if (StringUtils.isBlank(inHospitalDTO.getBka600()) && StringUtils.isNotBlank(inHospitalDTO.getBke052())) {
			inHospitalDTO.setBka600(inHospitalDTO.getBke052());
		}
		this.loadHidDataAndItemName(inHospitalDTO);// 部分字段进行转义 转诊转院 、审批标识等
		return inHospitalDTO;
	}

	/**
	 * 删除转诊转院申请信息
	 * 
	 * @return
	 */
	public String deleteReferralTransferInfo() {
		try {
			this.initCtrlInHospitalDTO();
			MCCEbizh120302Service mCCEbizh120302Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120302Service.class, this.getInHospitalDTO().getAkb020());
			InHospitalDTO inHospitalDTO = mCCEbizh120302Service.deleteReferralApplyInfo(this.getInHospitalDTO());
			if (inHospitalDTO == null) {
				return NONE;
			}
			this.getBeanService().copyProperties(inHospitalDTO, this.getInHospitalDTO(), true);
			this.setJSONReturn(this.getInHospitalDTO());
		} catch (Throwable e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn+e.getMessage());
		}
		return NONE;
	}

	/*
	 * 根据地区统筹码等信息查询医院信息
	 */
	public String queryHospInfo() {
		try {
			if (this.isPostRequest()) {
				PagerHelper.initPagination(getRequest());
				String akb020 = BizHelper.getAkb020();
				HospCataApiService hospCataService = this.getHygeiaServiceManager()
						.getBeanByClass(HospCataApiService.class, akb020);
				this.getHospDTO().setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				this.getHospDTO().setPageSize(PagerHelper.getPaginationObj().getPageSize());
				ListResult listResult = hospCataService.queryHospInfoByAAA027(akb020, this.getHospDTO());
				PaginationHelper.getPaginationObj().setCount(listResult.getCount());
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
			} else {
				return "queryHospInfo";
			}
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			saveJSONError("查询医疗机构信息出错！" + errLogSn + e.getMessage());
		}
		return NONE;
	}

	//2017-12-06 LHY  珠海转诊转院需求 判断该医院是否为"可转出"医院
	public String TransOutInhospital(){
		try{
			String akb020 = BizHelper.getAkb020();
			this.getInHospitalDTO().setAkb020(akb020);
			MCCEbizh120302Service mCCEbizh120302Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120302Service.class, this.getInHospitalDTO().getAkb020());
			List<InHospitalDTO> inHospitalDTORows = mCCEbizh120302Service.queryInhospitalTransfer(this.getInHospitalDTO());
			this.setJSONReturn(inHospitalDTORows);
		}catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			saveJSONError("查询医疗机构是否为”可转出“出错！" + errLogSn + e.getMessage());
		}
		return NONE;
	}
	
	//2017-12-06 LHY  珠海转诊转院需求 判断该医院是否为"可转入"医院
	public String TransInInhospital(){
		try{
			String akb020 = BizHelper.getAkb020();
			this.getInHospitalDTO().setAkb020(akb020);
			String ake017 = getParameter("ake017");
			if(StringUtils.isNotBlank(ake017)){
				this.getInHospitalDTO().setAkc172(ake017);
			}
			MCCEbizh120302Service mCCEbizh120302Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120302Service.class, this.getInHospitalDTO().getAkb020());
			List<InHospitalDTO> inHospitalDTORows = mCCEbizh120302Service.queryInhospitalTransferIn(this.getInHospitalDTO());
			this.setJSONReturn(inHospitalDTORows);
		}catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			saveJSONError("查询医疗机构是否为”可转出“出错！" + errLogSn + e.getMessage());
		}
		return NONE;
	}

}
