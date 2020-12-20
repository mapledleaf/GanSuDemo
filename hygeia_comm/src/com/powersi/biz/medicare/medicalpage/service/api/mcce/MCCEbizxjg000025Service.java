package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.Map;

public interface MCCEbizxjg000025Service extends Serializable {

	/**
	 * 删除细菌结果信息
	 * @param mParas
	 * @return
	 */
	public int deleteMasLisBacteriaResultInfo(Map<String, Object> mParas);
}
