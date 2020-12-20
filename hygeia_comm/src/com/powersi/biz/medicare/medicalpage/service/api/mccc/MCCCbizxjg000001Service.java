package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.io.Serializable;
import java.util.Map;

public interface MCCCbizxjg000001Service extends Serializable {

	public String serviceid = "BIZXJG000001";
	
	/**
	 * 保存生命体征信息
	 * @param mParas
	 * @return
	 */
	public int saveVitalSignInfo(Map<String, Object> mParas);
}
