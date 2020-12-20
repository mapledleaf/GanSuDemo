package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface MCCEbizc320004Service extends Serializable{
	
	
	/**
	  *  字段转换（aaz217）
	 * @param mParas
	 * @return
	 */
	public String fieldTrans(Map<String, Object> mParas);
	
	/**
	 * 保存病案首页信息
	 * @param mParas
	 * @return
	 */
	public int saveMedicalBasis(Map<String, Object> mParas);
	
	/**
	 * 获取疾病/手术编码信息
	 * @param mParas
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getDiseaseOrOpsCodeInfo(Map<String, Object> mParas);
}
