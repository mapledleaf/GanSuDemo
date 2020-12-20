package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.Map;

public interface MCCEbizxjg000001Service extends Serializable {

	/**
	 * 保存生命体征信息
	 * @param mParas
	 * @return
	 */
	public int saveVitalSignInfo(Map<String, Object> mParas);
}
