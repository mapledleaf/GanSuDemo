package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.Map;

public interface MCCEbizxjg000027Service extends Serializable {

	/**
	 * 删除生命体征信息
	 * @param mParas
	 * @return
	 */
	public int deleteMasRisReportInfo(Map<String, Object> mParas);
}
