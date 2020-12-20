package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.Map;

public interface MCCEbizc320002Service extends Serializable{
	
	/**
	 * 查询医嘱相关信息
	 * @param mParas
	 * @return
	 */
	public Map<String, Object> queryDoctorRAD(Map<String, Object> mParas);

}
