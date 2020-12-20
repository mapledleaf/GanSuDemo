package com.powersi.biz.medicare.settlement.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.MediDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.settlement.pojo.ClinicSettlementBaseDTO;

public interface SettlementClinic {

	@SuppressWarnings("rawtypes")
	public Map getClinicReport(ClinicSettlementBaseDTO clinicSettlementBaseDTO);

	@SuppressWarnings("rawtypes")
	public Map loadCenterSettlementReportData(MediDTO mediDTO);

	public List<InHospitalDTO> queryInHospitalaaz217s(InHospitalDTO inHospitalDTO);

}
