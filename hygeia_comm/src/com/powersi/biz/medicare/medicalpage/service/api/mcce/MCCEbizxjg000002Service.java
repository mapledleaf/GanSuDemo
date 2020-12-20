package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.Map;

public interface MCCEbizxjg000002Service extends Serializable {

	/**
	 * 保存病程记录相关信息
	 * @param mParas
	 * @return
	 */
	public int saveMasMrRecordInfo(Map<String, Object> mParas);
}