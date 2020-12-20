package com.powersi.biz.medicare.inhospital.service.api.mccc;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 
 * bizh120301 去中心判断住院审核
 * 
 * @author 刘勇
 *
 */
public interface MCCCbizh120301Service extends MCCCenterService {

	String serviceid = mccc_ + "bizh120301";

	/**
	 * 去中心判断住院审核
	 * 
	 * @param inHospitalDTO
	 */
	public void checkInHospitalRegisterAudit(InHospitalDTO inHospitalDTO);

}
