package com.powersi.biz.medicare.inhospital.service.api.mccc;

import java.util.List;

import com.powersi.biz.medicare.comm.pojo.IMediCalc;
import com.powersi.biz.medicare.inhospital.pojo.FeeDTO;
import com.powersi.biz.medicare.inhospital.pojo.FeeStatDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.pojo.PayDTO;
import com.powersi.biz.medicare.inhospital.pojo.SceneDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120107Service;

/**
 * 
 * bizh120107 中心取消出院结算
 * 
 * @author 刘勇
 *
 */
public interface MCCCbizh120107Service extends MCCCenterService {

	String serviceid = mccc_ + MCCHbizh120107Service.function_id;

	/**
	 * 中心取消出院结算
	 * 
	 * @param imediCalc
	 * @param inHospitalDTO
	 * @param feeDTORows
	 * @param sceneDTORows
	 * @param payDTORows
	 * @param feeStatDTORows
	 */
	public void cancelOutCharge(IMediCalc imediCalc, InHospitalDTO inHospitalDTO, List<FeeDTO> feeDTORows,
			List<SceneDTO> sceneDTORows, List<PayDTO> payDTORows, List<FeeStatDTO> feeStatDTORows);

}
