package com.powersi.biz.medicare.inhospital.service.api.mccc;

import com.powersi.biz.medicare.comm.pojo.IMediCalc;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120105Service;

/**
 * 
 * bizh120105 中心保存出院登记
 * 
 * @author 刘勇
 *
 */
public interface MCCCbizh120105Service extends MCCCenterService {

	String serviceid = mccc_ + MCCHbizh120105Service.function_id;

	/**
	 * 中心保存出院登记
	 * 
	 * @param inHospitalDTO
	 * @param imediCalc
	 * @return
	 */
	public void outRegister(InHospitalDTO inHospitalDTO, IMediCalc imediCalc);

}
