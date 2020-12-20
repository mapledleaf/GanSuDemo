package com.powersi.biz.medicare.inhospital.service.api.mcce;

import java.util.List;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120001Service;

/**
 * 
 * bizh120001 入院登记时取人员信息
 * 
 * @author 刘勇
 *
 */
public interface MCCEbizh120001Service extends MCCEsbService {

	public String serviceid = mcce_ + MCCHbizh120001Service.function_id;

	public String NOPERSONINFOMESSAGE = "无法获取人员基本信息";

	/**
	 * 入院登记时取人员信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public List<InHospitalDTO> searchPersonInfo(InHospitalDTO inHospitalDTO);

}
