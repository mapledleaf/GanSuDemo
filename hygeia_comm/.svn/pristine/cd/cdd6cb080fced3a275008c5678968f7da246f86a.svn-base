package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface MCCCbizc320004Service extends Serializable{

	public String serviceid = "BIZC320004";
	
	/**
	  *  字段转换
	 * @param aaz217
	 * @param abk020
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
