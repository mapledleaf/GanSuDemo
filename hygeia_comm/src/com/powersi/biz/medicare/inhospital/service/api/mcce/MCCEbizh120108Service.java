package com.powersi.biz.medicare.inhospital.service.api.mcce;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120108Service;

/**
 * 
 * bizh120108 取消出院登记
 * 
 * @author 刘勇
 *
 */
public interface MCCEbizh120108Service extends MCCEsbService {

	public String actionName = "取消出院登记-";

	public String serviceid = mcce_ + MCCHbizh120108Service.function_id;

	/**
	 * 取消出院登记
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO cancelOutRegister(InHospitalDTO inHospitalDTO);

}
