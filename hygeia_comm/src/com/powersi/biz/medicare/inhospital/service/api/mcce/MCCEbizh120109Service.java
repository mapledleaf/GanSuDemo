package com.powersi.biz.medicare.inhospital.service.api.mcce;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120109Service;

/**
 * 
 * bizh120109 取消入院登记
 * 
 * @author 刘勇
 *
 */
public interface MCCEbizh120109Service extends MCCEsbService {

	public String serviceid = mcce_ + MCCHbizh120109Service.function_id;

	/**
	 * 取消入院登记
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO cancelRegister(InHospitalDTO inHospitalDTO);

}
