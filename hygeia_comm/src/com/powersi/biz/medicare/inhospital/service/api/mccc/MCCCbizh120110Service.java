package com.powersi.biz.medicare.inhospital.service.api.mccc;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120110Service;

/**
 * 
 * bizh120110 中心保存预付款
 * 
 * @author 陈cl
 *
 */
public interface MCCCbizh120110Service extends MCCCenterService {

	String serviceid = mccc_ + MCCHbizh120110Service.function_id;
 
	/**
	 * 中心保存 预付款
	 * 
	 * @param inHospitalDTO
	 */
	public void saveForegift(InHospitalDTO inHospitalDTO);

}
