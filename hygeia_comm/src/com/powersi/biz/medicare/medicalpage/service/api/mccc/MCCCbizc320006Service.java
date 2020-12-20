package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.io.Serializable;
import java.util.Map;

public interface MCCCbizc320006Service extends Serializable{
	
	public String serviceid = "BIZC320006";
	
	public int deleteMedicalInfo(Map<String, Object> mParas);
}
