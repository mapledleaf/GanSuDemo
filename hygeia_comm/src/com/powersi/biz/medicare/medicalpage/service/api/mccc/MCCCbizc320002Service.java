package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.io.Serializable;
import java.util.Map;

public interface MCCCbizc320002Service extends Serializable{

	public String serviceid = "BIZC320002";
	
	/**
	 * 查询医嘱相关信息
	 * @param mParas
	 * @return
	 */
	public Map<String, Object> queryDoctorRAD(Map<String, Object> mParas);
}
