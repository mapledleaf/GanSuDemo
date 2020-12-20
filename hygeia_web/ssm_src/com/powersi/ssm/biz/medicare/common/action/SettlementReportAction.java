package com.powersi.ssm.biz.medicare.common.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.comm.pojo.MediDTO;
import com.powersi.biz.medicare.comm.service.SettlementReportDataService;
import com.powersi.biz.medicare.comm.service.SettlementReportService;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.query.pojo.BizQueryDTO;
import com.powersi.biz.medicare.query.service.api.BizQueryApiService;
import com.powersi.biz.medicare.settlement.service.SettlementClinic;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.powerreport.service.PowerReportDao;
import com.powersi.powerreport.service.PowerReportImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120102ServiceImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.common.util.ReportData;
import com.powersi.ssm.biz.medicare.inhospital.action.BaseInhospitalManagerAction;

/**
 * 
 * @author 刘勇
 *
 */
@Action(value = "SettlementReportAction", results = {
		@Result(name = "settlementReportInHosp", location = "/pages/biz/medicare/comm/SettlementReportInHosp.jsp"),
		@Result(name = "settlementReportClinic", location = "/pages/biz/medicare/comm/SettlementReportClinic.jsp"),
		@Result(name = "inProvincialMedical", location = "/pages/biz/medicare/comm/inProvincialMedical.jsp"),
		@Result(name = "outProvincialMedical", location = "/pages/biz/medicare/comm/outProvincialMedical.jsp") })
public class SettlementReportAction extends BaseInhospitalManagerAction {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String reportIDfirst;
	private String reportIDsecond;
	private BizQueryDTO bizQueryDTO;
	@Autowired
	private SettlementReportService settlementReportService;
	@Autowired
	private SettlementReportDataService settlementReportDataService;

	public BizQueryDTO getBizQueryDTO() {
		return bizQueryDTO;
	}

	public void setBizQueryDTO(BizQueryDTO bizQueryDTO) {
		this.bizQueryDTO = bizQueryDTO;
	}

	public String getReportIDfirst() {
		return reportIDfirst;
	}

	public void setReportIDfirst(String reportIDfirst) {
		this.reportIDfirst = reportIDfirst;
	}

	public String getReportIDsecond() {
		return reportIDsecond;
	}

	public void setReportIDsecond(String reportIDsecond) {
		this.reportIDsecond = reportIDsecond;
	}

	/**
	 * 医保结算单
	 * 
	 * @return
	 */
	public String settlementReport() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO();
				} catch (Exception e) {
					this.getCommunalService().error(this.logger, e, null);
					this.saveJSONError(e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO();
				return this.createSettlementReport();
			}
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 
	 * @return
	 */
	private String createSettlementReport() {
		if (StringUtils.isBlank(this.getInHospitalDTO().getAaz217())) {
			throw new HygeiaException("就医登记号不能为空!");
		}
		this.getInHospitalDTO().setAae100("1");
		this.getInHospitalDTO().setBka977("1");
		this.getInHospitalDTO().setBka891("1");
		List<InHospitalDTO> inHospitalDTORows = new ArrayList<>();
		if ("2".equals(this.getInHospitalDTO().getBke035())) {
			SettlementClinic settlementClinic = this.getHygeiaServiceManager().getBeanByClass(SettlementClinic.class,
					this.getInHospitalDTO().getAkb020());
			inHospitalDTORows = settlementClinic.queryInHospitalaaz217s(this.getInHospitalDTO());
		} else {
			MCCEbizh120102ServiceImpl mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			inHospitalDTORows = mCCEbizh120102Service.queryInHospitalaaz217s(this.getInHospitalDTO());
			if (inHospitalDTORows == null || inHospitalDTORows.size() == 0) {
				this.getInHospitalDTO().setBka891("0");
				inHospitalDTORows = mCCEbizh120102Service.queryInHospitalaaz217s(this.getInHospitalDTO());
			}
		}
		if (inHospitalDTORows == null || inHospitalDTORows.size() == 0) {
			throw new HygeiaException("本医院不存在此就医登记号的业务信息(" + this.getInHospitalDTO().getAaz217() + ")!");
		}
		InHospitalDTO inHospitalDTORow = inHospitalDTORows.get(0);
		MediDTO mediDTO = new MediDTO();
		this.getBeanService().copyProperties(inHospitalDTORow, mediDTO, true);
		mediDTO.setBka984(BizHelper.getLoginUser());
		mediDTO.setAae011(BizHelper.getUserName());
		mediDTO.setBka891(this.getInHospitalDTO().getBka891());
		mediDTO.setBke035(this.getInHospitalDTO().getBke035());
		if (aka130_12.equals(mediDTO.getAka130()) || aka130_14.equals(mediDTO.getAka130())
				|| aka130_16.equals(mediDTO.getAka130()) || aka130_17.equals(mediDTO.getAka130())
				|| aka130_42.equals(mediDTO.getAka130()) || aka130_52.equals(mediDTO.getAka130())
				|| aka130_72.equals(mediDTO.getAka130()) || aka130_82.equals(mediDTO.getAka130())) {
			return this.createSettlementReportInHosp(mediDTO);
		} else {
			return this.createSettlementReportClinic(mediDTO);
		}
	}

	/**
	 * 住院结算单
	 * 
	 * @param mediDTO
	 */
	@SuppressWarnings("rawtypes")
	private String createSettlementReportInHosp(MediDTO mediDTO) {
		// 跨省异地就医住院结算单格式区别 add by zhos
		if (aka130_82.equals(mediDTO.getAka130())) {
			mediDTO.setBka983("跨省异地就医住院结算单");
			Map settlementReportData = null;
			mediDTO.setBka981(mediDTO.getAaz217() + "_1");
			mediDTO.setBka980("settlement/inhosp_ksyd.xls");
			settlementReportData = this.settlementReportDataService.loadSettlementReportData(mediDTO);
			this.reportIDfirst = this.settlementReportService.createSettlementReport(mediDTO, settlementReportData);
		} else if (aka130_72.equals(mediDTO.getAka130())) {
			mediDTO.setBka983("省内异地就医住院结算单");
			Map settlementReportData = null;
			mediDTO.setBka981(mediDTO.getAaz217() + "_1");
			mediDTO.setBka980("settlement/inhosp_snyd.xls");
			settlementReportData = this.settlementReportDataService.loadSettlementReportData(mediDTO);
			this.reportIDfirst = this.settlementReportService.createSettlementReport(mediDTO, settlementReportData);
		} else {
			mediDTO.setBka983("住院结算单");
			Map settlementReportData = null;
			// 一级结算单
			mediDTO.setBka981(mediDTO.getAaz217() + "_1");
			mediDTO.setBka980("settlement/" + mediDTO.getAaa027().substring(0, 4) + "00" + "/inhosp_"
					+ mediDTO.getAae140() + "_" + mediDTO.getBka006() + ".xls");
			mediDTO.setBka981(mediDTO.getAaz217() + "_3");

			mediDTO.setBka980("settlement/" + mediDTO.getAaa027() + "/inhosp_" + mediDTO.getAae140() + "_3" + ".xls");

			if ("121".equals(mediDTO.getBka006())) {
				mediDTO.setBka980("settlement/" + mediDTO.getAaa027() + "/inhosp_" + mediDTO.getAae140() + "_3_"
						+ mediDTO.getBka006() + ".xls");
			}

			if ("122".equals(mediDTO.getBka006())) {
				mediDTO.setBka980("settlement/" + mediDTO.getAaa027() + "/inhosp_" + mediDTO.getAae140() + "_3_"
						+ mediDTO.getBka006() + ".xls");
			}

			if ("521".equals(mediDTO.getBka006())) {
				mediDTO.setBka980("settlement/" + mediDTO.getAaa027() + "/inhosp_" + mediDTO.getAae140() + "_3_"
						+ mediDTO.getBka006() + ".xls");
			}

			if ("140".equals(mediDTO.getBka006())) {
				mediDTO.setBka980("settlement/" + mediDTO.getAaa027() + "/inhosp_" + mediDTO.getAae140() + "_3_"
						+ mediDTO.getBka006() + ".xls");
			}
			// 一站式结算单
			if ("1".equals(mediDTO.getBka040()) || "1".equals(mediDTO.getBke054())) {
				mediDTO.setBka980("settlement/430300/One_stop_settlement_sheet.xls");
			}

			if (isReportFileExists(mediDTO.getBka980()) == 0) {
				mediDTO.setBka980(mediDTO.getBka980().replaceAll(mediDTO.getBka980().split("/")[1],
						mediDTO.getAaa027().substring(0, 2) + "0300"));
			}

			settlementReportData = this.settlementReportDataService.loadSettlementReportData(mediDTO);
			this.reportIDfirst = this.settlementReportService.createSettlementReport(mediDTO, settlementReportData);
		}
		return "settlementReportInHosp";
	}

	private int isReportFileExists(String Bka980) {
		try {
			String wordtempPath = Thread.currentThread().getContextClassLoader().getResource("/").getPath() + "/"
					+ Bka980;
			File file = new File(wordtempPath);
			if (file.exists()) {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return 0;
	}

	/**
	 * 门诊结算单
	 * 
	 * @param mediDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String createSettlementReportClinic(MediDTO mediDTO) {
		Map settlementReportData = this.settlementReportDataService.loadSettlementReportData(mediDTO);
		mediDTO.setBka983("门诊结算单");
		if ("114".equals(mediDTO.getBka006())) {// 打印实质性结算单，由于aka130和aae140都是和普通门诊一样
			mediDTO.setAka130("14");
		}
		mediDTO.setBka980("settlement/" + mediDTO.getAaa027().substring(0, 4) + "00" + "/clinic_" + mediDTO.getAae140()
				+ "_" + mediDTO.getAka130() + ".xls");
		this.reportIDfirst = this.settlementReportService.createSettlementReport(mediDTO, settlementReportData);
		return "settlementReportClinic";
	}

	/**
	 * 异地就医结算单打印
	 * 
	 * @return
	 */
	public String settlementReportYdjy() {
		bizQueryDTO.setAaa027(BizHelper.getAaa027());
		bizQueryDTO.setAkb020(BizHelper.getAkb020());
		BizQueryApiService bizQueryApiService = (BizQueryApiService) this.getHygeiaServiceManager()
				.getBean("bizQueryApiServiceImpl", BizHelper.getAkb020());
		String page = "";
		// 1、从后台获取结果信息
		Map<String, Object> mapData = bizQueryApiService.settlementReport(bizQueryDTO);

		if (mapData != null && !mapData.isEmpty()) {
			// 2、获取结算单的模板位置以及返回的jsp界面
			String[] arr = getReturnUrlNew2Yd();
			// 3、加载模板数据
			String reportID = loadJsdModule2Yd(mapData, arr[0]);

			// 返回报表ID到jsp前台界面
			setAttribute("reportID", reportID);
			page = arr[1];
			return page;
		} else {
			throw new HygeiaException("无法获取结算信息");
		}

	}

	/**
	 * 获取结算单跳转的URL(省内和跨省)
	 * 
	 * @return
	 */
	private String[] getReturnUrlNew2Yd() {
		String[] arr = new String[] { "", "" };
		String aaz217 = bizQueryDTO.getAaz217();
		String aaa027 = BizHelper.getAaa027();
		String bka006 = bizQueryDTO.getBka006();
		// 取得报表模版目录路径
		arr[0] = "settlement/" + aaa027.substring(0, 4) + "00/";
		if (aaz217.startsWith("po")) { // 跨省
			arr[0] = arr[0] + "outProvincialMedical.xls";
			arr[1] = "outProvincialMedical";
		} else { //
			if (StringUtils.isNotBlank(bka006) && "521".equals(bka006)) {
				arr[0] = arr[0] + "inProvincialMedical_sy.xls";
				arr[1] = "inProvincialMedical_sy";
			} else {
				arr[0] = arr[0] + "inProvincialMedical.xls";
				arr[1] = "inProvincialMedical";
			}
		}

		return arr;
	}

	/**
	 * 根据结算单的excel模板和获取的结果集数据加载数据，返回模板ID
	 * 
	 * @param mData1
	 * @param reportXLS
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String loadJsdModule2Yd(Map mData1, String reportXLS) {
		ReportData reportData = new ReportData();
		Map<String, Object> head = reportData.getHead(); // 头部数据列
		head.put("name", BizHelper.getAaa027Name());

		reportData.put("data1", mData1.get("data1"));
		reportData.put("data2", mData1.get("data2"));
		reportData.put("data3", mData1.get("data3"));
		reportData.put("data4", mData1.get("data4"));
		reportData.put("data5", mData1.get("data5"));
		reportData.put("data6", mData1.get("data6"));
		reportData.put("data7", mData1.get("data7"));
		reportData.put("data8", mData1.get("data8"));
		reportData.put("data9", mData1.get("data9"));

		String bizID = "bizId_99999"; // 业务ID自由定义
		PowerReportDao prd = new PowerReportDao();
		PowerReportImpl pri = new PowerReportImpl();
		Map<String, Object> checkMap = prd.getReportBaseInfoBizID(bizID);
		if (checkMap != null) {
			pri.delReport(null, bizID);
			DBHelper.commit();
		}

		return pri.createReport(reportXLS, bizID, 1, reportData, "[" + bizID + "]",
				BizHelper.getLoginUser()); // 返回报表ID
	}
}
