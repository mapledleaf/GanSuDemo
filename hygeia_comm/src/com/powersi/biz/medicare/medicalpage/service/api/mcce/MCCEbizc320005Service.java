package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.Map;

public interface MCCEbizc320005Service extends Serializable{

	public Map<String, Object> queryMedicalInfo(Map<String, Object> mParas);
}
