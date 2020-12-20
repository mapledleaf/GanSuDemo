package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.query.dto.KC27;
import com.powersi.biz.medicare.query.dto.KCD1;

public interface SettlementInfo extends Serializable {

	public List<KCD1> getKcd1(InHospitalDTO inHospitalDTO);

	public List<Map<String, Object>> queryJsdData1Info(InHospitalDTO inHospitalDTO);

	public List<Map<String, Object>> queryData2Info(InHospitalDTO inHospitalDTO);

	public List<Map<String, Object>> queryJsdData3Info(InHospitalDTO inHospitalDTO);

	public List<Map<String, Object>> queryJsdData5Info(InHospitalDTO inHospitalDTO);

	public List<Map<String, Object>> queryData10Info(InHospitalDTO inHospitalDTO);

	public List<Map<String, Object>> queryData11Info(InHospitalDTO inHospitalDTO);

	public List<KC27> getKcd7Info(InHospitalDTO inHospitalDTO);

	public List<KC27> getKc27Info(InHospitalDTO inHospitalDTO);

	/**
	 * TS19041100099 - 【需求开发】单病种出院结算单需要调整为中心端结算单
	 * modified 675  增加单病种限制用药列表
	 * @param inHospitalDTO
	 * @return
	 */
	public List<Map<String, Object>> queryData13Info(InHospitalDTO inHospitalDTO) ;
	
	//TS19123100155 - 【需求开发】城乡居民门诊两病全省需求-湘潭结算云结算单改造 add by ljl 20191231
	public List<Map<String, Object>> queryData14Info(InHospitalDTO inHospitalDTO);
}
