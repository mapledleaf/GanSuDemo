package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.io.Serializable;
import java.util.Map;

public interface MCCCbizxjg000004Service extends Serializable {

	public String serviceid = "BIZXJG000004";
	
	/**
	 * 保存检验结果信息
	 * @param mParas
	 * @return
	 */
	public int saveMasLisIndicatorsInfo(Map<String, Object> mParas);
}