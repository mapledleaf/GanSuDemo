package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.util.Map;

public interface MCCCbizxjg000101Service {
public String serviceid = "BIZXJG000101";
	
	/**
	 * 保存检验报告信息
	 * @param mParas
	 * @return
	 */
	public int saveMasLisReportInfo(Map<String, Object> mParas);
}
