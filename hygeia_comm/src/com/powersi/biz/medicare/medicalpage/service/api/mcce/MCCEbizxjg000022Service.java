package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.Map;

public interface MCCEbizxjg000022Service extends Serializable {

	/**
	 * 删除病程记录相关信息
	 * @param mParas
	 * @return
	 */
	public int deleteMasMrRecordInfo(Map<String, Object> mParas);
}
