package com.powersi.biz.medicare.inhospital.service.api.mcce;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120103Service;

/**
 * 
 * 
 * bizh120103 入院登记保存
 * 
 * 
 * @author 刘勇
 *
 */
public interface MCCEbizh120103Service extends MCCEsbService {

	public String serviceid = mcce_ + MCCHbizh120103Service.function_id;

	/**
	 * 入院登记信息保存
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO saveRegisterInfo(InHospitalDTO inHospitalDTO);

}
