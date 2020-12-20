package com.powersi.biz.medicare.diagnose.service;

import java.util.List;
import java.util.Map;

public interface ChooseDiagnoseBizVS {

	@SuppressWarnings("rawtypes")
	public List selectBiz(String arg_name, String arg_value, String hospid, String biztype,String bka006);

	@SuppressWarnings("rawtypes")
	public List getDiagnoseDetail(String akb020, String aaz217);

	@SuppressWarnings("rawtypes")
	public List selectBizFee(String aaz217, String akb020, String kcd1id, String bka039);

	@SuppressWarnings("rawtypes")
	public List selectBizFeeInfo(String aaz217, String akb020, String kcd1id, String pka001, String bka039);

	@SuppressWarnings("rawtypes")
	public List selectBatchFee(String aaz217, String akb020, String kcd1id, String bka039, String bka001);
	
	@SuppressWarnings("rawtypes")
	public List getDiagnoseScene(String akb020, String aaz217, String bka001, String bka039);
	
	@SuppressWarnings("rawtypes")
	public List getPayInfoByAaz217(String akb020, String aaz217, String bka001);
	
	@SuppressWarnings("rawtypes")
	public List getSceneByBka435(String akb020, String aaz217, String bka001, String bka435);
	
	@SuppressWarnings("rawtypes")
	public List getDoctor(Map whereMap);
}
