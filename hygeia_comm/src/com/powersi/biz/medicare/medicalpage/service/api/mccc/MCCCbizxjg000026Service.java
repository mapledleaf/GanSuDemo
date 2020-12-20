package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.io.Serializable;
import java.util.Map;

public interface MCCCbizxjg000026Service extends Serializable {

	public String serviceid = "BIZXJG000026";
	
	/**
	 * 删除药敏结果信息
	 * @param mParas
	 * @return
	 */
	public int deleteMasLisAllergyResultInfo(Map<String, Object> mParas);
}
