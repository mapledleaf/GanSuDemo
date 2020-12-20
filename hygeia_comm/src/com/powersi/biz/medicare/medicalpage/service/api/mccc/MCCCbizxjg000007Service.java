package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.io.Serializable;
import java.util.Map;

public interface MCCCbizxjg000007Service extends Serializable {

	public String serviceid = "BIZXJG000007";
	
	/**
	 * 保存检查报告信息
	 * @param mParas
	 * @return
	 */
	public int saveMasRisReportInfo(Map<String, Object> mParas);
}