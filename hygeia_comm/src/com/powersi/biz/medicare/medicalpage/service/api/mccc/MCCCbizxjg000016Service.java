package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.io.Serializable;
import java.util.Map;

public interface MCCCbizxjg000016Service extends Serializable {

	public String serviceid = "BIZXJG000016";
	
	/**
	 * 查询药敏结果信息
	 * @param mParas
	 * @return
	 */
	public Map<String, Object> queryMasLisAllergyResultInfo(Map<String, Object> mParas);
}
