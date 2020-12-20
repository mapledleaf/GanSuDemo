package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.Map;

public interface MCCEbizxjg000011Service extends Serializable {

	/**
	 * 查询生命体征信息
	 * @param mParas
	 * @return
	 */
	public Map<String, Object> queryVitalSignInfo(Map<String, Object> mParas);
}
