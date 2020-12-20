package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.Map;

public interface MCCEbizxjg000003Service extends Serializable {

	/**
	 * 保存检验报告信息
	 * @param mParas
	 * @return
	 */
	public int saveMasLisReportInfo(Map<String, Object> mParas);
}
