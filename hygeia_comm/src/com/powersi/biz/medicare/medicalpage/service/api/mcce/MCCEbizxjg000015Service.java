package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.Map;

public interface MCCEbizxjg000015Service extends Serializable {

	/**
	 * 查询细菌结果信息
	 * @param mParas
	 * @return
	 */
	public Map<String, Object> queryMasLisBacteriaResultInfo(Map<String, Object> mParas);
}
