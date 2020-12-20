package com.powersi.biz.medicare.inhospital.service.api.mcce;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120003Service;

/**
 * 
 * bizh120003 校验并计算费用信息
 * 
 * @author 刘勇
 *
 */
public interface MCCEbizh120003Service extends MCCEsbService {

	public String actionName = "计算费用明细-";

	public String serviceid = mcce_ + MCCHbizh120003Service.function_id;

	/**
	 * 校验并计算费用信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO checkAndCalcFeeInfo(InHospitalDTO inHospitalDTO);

}
