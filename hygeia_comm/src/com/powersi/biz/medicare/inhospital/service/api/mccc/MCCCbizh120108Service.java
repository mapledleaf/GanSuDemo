package com.powersi.biz.medicare.inhospital.service.api.mccc;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120108Service;

/**
 * 
 * bizh120108 中心取消出院登记
 * 
 * @author 刘勇
 *
 */
public interface MCCCbizh120108Service extends MCCCenterService {

	String serviceid = mccc_ + MCCHbizh120108Service.function_id;

	/**
	 * 中心取消出院登记
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public void cancelOutRegister(InHospitalDTO inHospitalDTO);

}
