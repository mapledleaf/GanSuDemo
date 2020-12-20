package com.powersi.ssm.biz.medicare.common.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.comminter.pojo.KAB1DTO;
import com.powersi.biz.medicare.comminter.pojo.KAB3DTO;
import com.powersi.biz.medicare.comminter.service.BillReportDataService;
import com.powersi.biz.medicare.comminter.service.BillReportService;
import com.powersi.biz.medicare.comminter.service.InvoiceManagerService;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.inhospital.action.BaseInhospitalManagerAction;

/**
 * 发票打印
 * @author 刘飞扬
 *
 */
@Action(value = "BillReportAction", results = {
		@Result(name = "printReport", location = "/pages/biz/medicare/comm/PrintingReport.jsp") })
public class BillReportAction extends BaseInhospitalManagerAction {

	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	
	@Autowired
	private BillReportService billReportService;
	@Autowired
	private BillReportDataService billReportDataService;
	private InHospitalDTO inHospitalDTO;
	
	public InHospitalDTO getInHospitalDTO() {
		return inHospitalDTO;
	}

	public void setInHospitalDTO(InHospitalDTO inHospitalDTO) {
		this.inHospitalDTO = inHospitalDTO;
	}

	private KAB1DTO kab1DTO;
	private KAB3DTO kab3DTO;
	
	private String reportIDfirst;

	public KAB1DTO getKab1DTO() {
		return kab1DTO;
	}

	public void setKab1DTO(KAB1DTO kab1dto) {
		kab1DTO = kab1dto;
	}

	
	public KAB3DTO getKab3DTO() {
		return kab3DTO;
	}

	public void setKab3DTO(KAB3DTO kab3dto) {
		kab3DTO = kab3dto;
	}

	/**
	 * 发票套打
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String printingReport() {
		try {
			kab3DTO.setAkb020(BizHelper.getAkb020());
			kab3DTO.setAaa027(BizHelper.getAaa027());
			if (StringUtils.isBlank(kab3DTO.getAaz217())) {
				throw new HygeiaException("就医登记号不能为空");
			}
			Map billReportData = null;
			if("1".equals(kab3DTO.getBka445())){//门诊套打
				kab3DTO.setBillReport("/settlement/bill/clincxt.xls");								
			}else{//住院套打			
				kab3DTO.setBillReport("/settlement/bill/hospitalxt.xls");			
			}
			billReportData = this.billReportDataService.loadPrintingReportData(kab3DTO);
			this.reportIDfirst = this.billReportService.createBillReport(kab3DTO, billReportData);			
			return "printReport";
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn+e.getMessage());
			return ERROR;
		}
	}
	
	
	/**
	 * 住院门诊费用单
	 * 
	 * @return
	 */
	public String billReport() {
		try {
			kab3DTO.setAkb020(BizHelper.getAkb020());
			
			this.setJSONReturn(this.createBillReport());
			//return this.createBillReport();
			return NONE;
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn+e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 
	 * @return
	 */
	private String createBillReport() {
		if (StringUtils.isBlank(kab3DTO.getAaz217())) {
			throw new HygeiaException("就医登记号不能为空");
		}
		//住院
		if("02".equals(kab3DTO.getBae410())){
			return this.createBillReportInHosp(kab3DTO);
		}else{
		//门诊
			return this.createBillReportClinic(kab3DTO);
		}
	}

	/**
	 * 住院发票
	 * 
	 * @param mediDTO
	 */
	@SuppressWarnings("rawtypes")
	private String createBillReportInHosp(KAB3DTO kab3DTO) {
		Map billReportData = null;
		kab3DTO.setBillReport("settlement/bill/bill.xls");
		billReportData = this.billReportDataService.loadBillReportData(kab3DTO);
		this.reportIDfirst = this.billReportService.createBillReport(kab3DTO, billReportData);
//		return "billReportInHosp";
		return this.reportIDfirst;
	}

	/**
	 * 门诊发票
	 * 
	 * @param 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String createBillReportClinic(KAB3DTO kab3DTO) {
		Map billReportData = null;
		kab3DTO.setBillReport("settlement/bill/bill.xls");
		billReportData = this.billReportDataService.loadBillReportData(kab3DTO);
		this.reportIDfirst = this.billReportService.createBillReport(kab3DTO, billReportData);
		//return "billReportClinic";
		return this.reportIDfirst;
	}
	
	public String getReportIDfirst() {
		return reportIDfirst;
	}

	public void setReportIDfirst(String reportIDfirst) {
		this.reportIDfirst = reportIDfirst;
	}
	
	/**
	 * 打印后业务处理（由打印控件回调处理）
	 * @return
	 */
	public String processBiz(){
		try {
			kab3DTO.setAkb020(BizHelper.getAkb020());
			InvoiceManagerService invoiceManagerService = this.hygeiaServiceManager.getBeanByClass(InvoiceManagerService.class, BizHelper.getAkb020());
			invoiceManagerService.processBizAfterPrint(kab3DTO);
//			this.setJSONReturn("发票打印成功");
			this.saveJSONMessage("发票打印业务更新成功");
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			saveJSONError(errLogSn+e.getMessage() );
		}
		return NONE;
	}
}
