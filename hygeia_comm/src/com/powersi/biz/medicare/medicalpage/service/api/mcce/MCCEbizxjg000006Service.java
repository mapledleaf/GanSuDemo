package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.Map;

public interface MCCEbizxjg000006Service extends Serializable {

	/**
	 * 保存药敏结果信息
	 * @param mParas
	 * @return
	 */
	public int saveMasLisAllergyResultInfo(Map<String, Object> mParas);
}