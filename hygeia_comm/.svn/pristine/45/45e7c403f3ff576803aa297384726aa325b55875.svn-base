package com.powersi.biz.medicare.inhospital.service.api.mccc;

import java.util.List;

import com.powersi.biz.medicare.inhospital.pojo.FeeDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120002Service;

/**
 *
 * bizh120002 中心保存费用信息
 * 
 * @author 刘勇
 *
 */
public interface MCCCbizh120002Service extends MCCCenterService {

	String serviceid = mccc_ + MCCHbizh120002Service.function_id;

	/**
	 * 中心保存费用信息
	 * 
	 * @param inHospitalDTO
	 * @param feeDTORows
	 */
	public void checkAndSaveFeeInfo(InHospitalDTO inHospitalDTO, List<FeeDTO> feeDTORows);

}
