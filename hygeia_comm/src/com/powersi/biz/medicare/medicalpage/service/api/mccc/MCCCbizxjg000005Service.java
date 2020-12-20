package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.io.Serializable;
import java.util.Map;

public interface MCCCbizxjg000005Service extends Serializable {

	public String serviceid = "BIZXJG000005";
	
	/**
	 * 保存细菌结果信息
	 * @param mParas
	 * @return
	 */
	public int saveMasLisBacteriaResultInfo(Map<String, Object> mParas);
}