package com.powersi.ssm.biz.medicare.settlement.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.settlement.pojo.ClinicSettlementBaseDTO;
import com.powersi.biz.medicare.settlement.service.SettlementClinic;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.powerreport.service.PowerReportDao;
import com.powersi.powerreport.service.PowerReportImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

@Action("settlement")
public class SettlementAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	HygeiaServiceManager hygeiaServiceManager;
	
	private ClinicSettlementBaseDTO clinicSettlementBaseDTO;
	
	public ClinicSettlementBaseDTO getClinicSettlementBaseDTO() {
		return clinicSettlementBaseDTO;
	}

	public void setClinicSettlementBaseDTO(ClinicSettlementBaseDTO clinicSettlementBaseDTO) {
		this.clinicSettlementBaseDTO = clinicSettlementBaseDTO;
	}

	public String createSettlementReport(){
		try {
			PowerReportImpl pri = new PowerReportImpl();

			String strBizId = "MZJSD"+clinicSettlementBaseDTO.getAaz217();
			
			//判断bizID是否已经存在Map,存在先删除老数据，在新增
			PowerReportDao dao = new PowerReportDao();
			Map<?, ?> checkMap = dao.getReportBaseInfoBizID(strBizId);
			if(checkMap != null) {
				pri.delReport(null, "1");
				DBHelper.commit();
			}
			
			Map<?, ?> mapData = new HashMap<Object, Object>();
			SettlementClinic setlementClinic = (SettlementClinic)hygeiaServiceManager.getBean("settlementclinic", BizHelper.getAkb020());
			mapData = setlementClinic.getClinicReport(clinicSettlementBaseDTO);
			
			//测试产生带数据的报表
			String reportID = pri.createReport("settlement/clinicwork.xls", strBizId, 1, mapData, "门诊结算单","sys");
			setJSONReturn("生成的报表ID为"+reportID,reportID);
		} catch (IOException e) {
			saveJSONError("生成菜单报表出错:" + e.getMessage(), e);
		}
		
		return NONE;
	}
}
