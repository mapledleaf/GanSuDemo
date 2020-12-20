package com.powersi.biz.medicare.inhospital.service.api.mcce;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120004Service;

/**
 * 
 * bizh120004 删除住院业务费用明细
 * 
 * @author 刘勇
 *
 */
public interface MCCEbizh120004Service extends MCCEsbService {

	public String serviceid = mcce_ + MCCHbizh120004Service.function_id;

	/**
	 * 删除住院业务费用明细
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO deleteInHospBizFees(InHospitalDTO inHospitalDTO);

	/**
	 * 
	 * <pre>
	 *  aac002 String 25 是 社会保障号码 
	 *  bka446 String 20 是 住院号或门诊号
	 * </pre>
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO deleteInHospBizFeesCommnInterface(InHospitalDTO inHospitalDTO);

}
