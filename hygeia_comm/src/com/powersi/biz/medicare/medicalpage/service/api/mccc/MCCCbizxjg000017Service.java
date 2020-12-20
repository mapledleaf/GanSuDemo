package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.io.Serializable;
import java.util.Map;

public interface MCCCbizxjg000017Service extends Serializable {

	public String serviceid = "BIZXJG000017";
	
	/**
	 * 查询生命体征信息
	 * @param mParas
	 * @return
	 */
	public Map<String, Object> queryMasRisReportInfo(Map<String, Object> mParas);
}

