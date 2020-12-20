package com.powersi.biz.medicare.inhospital.service.api.mcce;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120203Service;

/**
 * 
 * bizh120203 查询已保存的费用信息
 * 
 * @author 刘勇
 *
 */
public interface MCCEbizh120203Service extends MCCEsbService {

	public String serviceid = mcce_ + MCCHbizh120203Service.function_id;

	/**
	 * 查询已保存的费用信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public ListResult querySavedFee(InHospitalDTO inHospitalDTO);

}
