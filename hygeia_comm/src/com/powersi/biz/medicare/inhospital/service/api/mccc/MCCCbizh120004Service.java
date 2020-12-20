package com.powersi.biz.medicare.inhospital.service.api.mccc;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120004Service;

/**
 *
 * bizh120004 删除住院业务费用明细
 * 
 * @author 刘勇
 *
 */
public interface MCCCbizh120004Service extends MCCCenterService {

	public String serviceid = mccc_ + MCCHbizh120004Service.function_id;

	/**
	 * 删除住院业务费用明细
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO deleteInHospBizFees(InHospitalDTO inHospitalDTO);

}
