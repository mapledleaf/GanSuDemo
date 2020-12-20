package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.Map;

public interface MCCEbizxjg000028Service extends Serializable {

	/**
	 * 删除病理报告信息
	 * @param mParas
	 * @return
	 */
	public int deleteMasPathologyReportInfo(Map<String, Object> mParas);
}
