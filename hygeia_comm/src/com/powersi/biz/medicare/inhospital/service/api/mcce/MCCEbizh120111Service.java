package com.powersi.biz.medicare.inhospital.service.api.mcce;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120111Service;

/**
 * 
 * bizh120111 取消中途结算
 * 
 * @author 陈钘
 *
 */
public interface MCCEbizh120111Service extends MCCEsbService{

	public String actionName = "取消中途结算-";

	public String serviceid = mcce_ + MCCHbizh120111Service.function_id;

	/**
	 * 取消中途结算
	 * 
	 * @param inHospitalDTO
	 * @return
	 */	
	public InHospitalDTO cancelMidwayCharge(InHospitalDTO inHospitalDTO);
}
