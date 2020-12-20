package com.powersi.biz.medicare.inhospital.service.api.mccc;

import com.powersi.biz.medicare.comm.pojo.IMediCalc;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120003Service;

/**
 *
 * bizh120003 中心校验并计算费用信息
 * 
 * @author 刘勇
 *
 */
public interface MCCCbizh120003Service extends MCCCenterService {
	String serviceid = mccc_ + MCCHbizh120003Service.function_id;

	/**
	 * 中心校验并计算费用信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public IMediCalc checkAndCalcFeeInfo(IMediCalc imediCalc, InHospitalDTO inHospitalDTO);

}
