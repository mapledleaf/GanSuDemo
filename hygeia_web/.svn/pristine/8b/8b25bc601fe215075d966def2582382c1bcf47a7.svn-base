package com.powersi.ssm.biz.medicare.inhospital.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.comm.service.FcBizPolicyService;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.powerreport.service.PowerReport;
import com.powersi.powerreport.service.PowerReportDao;
import com.powersi.powerreport.service.PowerReportImpl;

/**
 * 
 * 住院管理本地服务实现类
 * 
 * @author 刘勇
 *
 */
@Service
public class InhospitalManagerServiceImpl implements InhospitalManagerService {

	private static final long serialVersionUID = 1L;

	private static final PowerReportDao powerReportDao = new PowerReportDao();
	@Autowired
	private FcBizPolicyService fcBizPolicyService;

	public String createForegiftReport(String reportTemplateID, String bizID, int keepDays, Map mapData,
			String report_comm, String user_name) {

		PowerReport powerReport = new PowerReportImpl();// getInHospitalDTO().getAae140()
		Map powerReportMap = null;
		String bizIdString = bizID;
		if (StringUtils.isNotBlank(bizIdString)) {
			powerReportMap = powerReportDao.getReportBaseInfoBizID(bizIdString);
		}
		if (powerReportMap != null) {
			powerReport.delReport(null, bizIdString);
		}
		powerReportMap = null;
		String reportIDfirst = powerReport.createReport(reportTemplateID, bizID, 1, mapData, report_comm, user_name);
		return reportIDfirst;
	}

	/***
	 * 用于登记后，通过个人电脑号等查信息,将其它几个设置为空，不查询另外几个字段
	 * 
	 * @param argName
	 * @param argValue
	 */
	public int resetArgNameInHospitalDTO(String argName, String argValue, InHospitalDTO inHospitalDTO) {
		if (inHospitalDTO == null) {
			return 0;
		}

		if (StringUtils.isNotBlank(inHospitalDTO.getAac001())) {
			inHospitalDTO.setAac002(null);
			inHospitalDTO.setBka100(null);
			inHospitalDTO.setBka025(null);
		} else if (StringUtils.isNotBlank(inHospitalDTO.getAac002())) {
			// this.getInHospitalDTO().setAac002(argValue);
			inHospitalDTO.setAac001(null);
			inHospitalDTO.setBka100(null);
			inHospitalDTO.setBka025(null);
		} else if (StringUtils.isNotBlank(inHospitalDTO.getBka025())) {
			// this.getInHospitalDTO().setBka025(argValue);
			inHospitalDTO.setAac002(null);
			inHospitalDTO.setAac001(null);
			inHospitalDTO.setBka100(null);
		} else {
			// this.getInHospitalDTO().setBka100(argValue);

			inHospitalDTO.setBka025(null);
			inHospitalDTO.setAac002(null);
			inHospitalDTO.setAac001(null);
		}
		return 1;
	}

	public int loadSysParamTips(InHospitalDTO inHospitalDTO, String name, Map mapParam) {
		if ("findOutRegisterAaz217".equals(name) || "findOutChargeAaz217".equals(name)
				|| "findModifyRegisterAaz217".equals(name)) {

			if (StringUtils.isNotBlank(inHospitalDTO.getBaa027())) {
				// inHospitalDTO.setSysparamTips(",qh_inhosp_bka006_icddisable_"+(fcBizPolicyService.getBizPolicyValue("6301","qh_inhosp_bka006_icddisable",'2',
				// "0").indexOf(inHospitalDTO.getBka006()+",")>=0?"1":"0"));//inHospitalDTO.getBaa027().substring(0,
				// 4)
				inHospitalDTO.setSysparamTips(",qh_inhosp_bka006_icddisable_"
						+ (getSysParamAdministrativeDivision("4301", "qh_inhosp_bka006_icddisable", '2', "0")
								.indexOf(inHospitalDTO.getBka035() + ",") >= 0 ? "1" : "0"));
			} else {
				inHospitalDTO.setSysparamTips(",qh_inhosp_bka006_icddisable_"
						+ (fcBizPolicyService.getBizPolicyValue("qh_inhosp_bka006_icddisable", '2', "0")
								.indexOf(inHospitalDTO.getBka035() + ",") >= 0 ? "1" : "0"));
			}
		}
		return 1;
	}

	public String getSysParamAdministrativeDivision(String CenterId, String policyCode, char CenterOrHosp,
			String defaultValue) {
		return !"0".equals(fcBizPolicyService.getBizPolicyValue(CenterId, policyCode, CenterOrHosp, defaultValue))
				? fcBizPolicyService.getBizPolicyValue(CenterId, policyCode, CenterOrHosp, defaultValue)
				: !"0".equals(fcBizPolicyService.getBizPolicyValue(
						CenterId.length() > 4 ? CenterId.substring(0, 4) : CenterId, policyCode, CenterOrHosp,
						defaultValue))
								? fcBizPolicyService.getBizPolicyValue(
										CenterId.length() > 4 ? CenterId.substring(0, 4) : CenterId, policyCode,
										CenterOrHosp, defaultValue)
								: fcBizPolicyService.getBizPolicyValue(
										CenterId.length() > 2 ? CenterId.substring(0, 2) : CenterId, policyCode,
										CenterOrHosp, defaultValue);
	}
}
