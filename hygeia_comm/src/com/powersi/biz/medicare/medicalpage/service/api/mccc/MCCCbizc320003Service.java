package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.io.Serializable;
import java.util.Map;

public interface MCCCbizc320003Service extends Serializable{

	public String serviceid = "BIZC320003";
	
	/**
	 * 删除医嘱相关信息
	 * @param mParas
	 * @return
	 */
	public int deleteDoctorRAD(Map<String, Object> mParas);
}
