package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.Map;

public interface MCCEbizxjg000018Service extends Serializable {

	/**
	 * 查询病理报告信息
	 * @param mParas
	 * @return
	 */
	public Map<String, Object> queryMasPathologyReportInfo(Map<String, Object> mParas);
}
