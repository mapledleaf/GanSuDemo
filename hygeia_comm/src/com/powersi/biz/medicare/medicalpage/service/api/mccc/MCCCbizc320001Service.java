package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.io.Serializable;
import java.util.Map;

public interface MCCCbizc320001Service extends Serializable{

	public String serviceid = "BIZC320001";
	
	/**
	 * 保存医嘱信息
	 * 包括 doctorrecipe（处方）、doctoradvice（医嘱）、disease（疾病）
	 * @param mParas
	 * @return
	 */
	public int saveDoctorRAD(Map<String, Object> mParas);
	
}
