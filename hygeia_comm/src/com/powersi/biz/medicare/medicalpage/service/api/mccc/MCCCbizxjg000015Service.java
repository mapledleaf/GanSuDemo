package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.io.Serializable;
import java.util.Map;

public interface MCCCbizxjg000015Service extends Serializable {

	public String serviceid = "BIZXJG000015";
	
	/**
	 * 查询细菌结果信息
	 * @param mParas
	 * @return
	 */
	public Map<String, Object> queryMasLisBacteriaResultInfo(Map<String, Object> mParas);
}
