package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.Map;

public interface MCCEbizxjg000101Service  extends Serializable {

	/**
	 * 出院小结
	 * @param mParas
	 * @return
	 */
	public int saveMasLisReportInfo(Map<String, Object> mParas);
	
}

