package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface MCCCbizc131271Service  extends Serializable{

	public String serviceid = "BIZC131271";
	
	@SuppressWarnings("rawtypes")
	public List<Map> queryBizInfo(Map<String, Object> mParas);
	
}
