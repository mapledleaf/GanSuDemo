package com.powersi.biz.medicare.inhospital.service.api.mccc;

import com.powersi.biz.medicare.comm.pojo.IMediCalc;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120106Service;

/**
 * 
 * bizh120106 中心出院结算
 * 
 * @author 刘勇
 *
 */
public interface MCCCbizh120106Service extends MCCCenterService {

	String serviceid = mccc_ + MCCHbizh120106Service.function_id;

	/**
	 * 中心出院结算
	 * 
	 * @param imediCalc
	 * @param inHospitalDTO
	 * @return
	 */
	public void outCharge(IMediCalc imediCalc, InHospitalDTO inHospitalDTO);

}
