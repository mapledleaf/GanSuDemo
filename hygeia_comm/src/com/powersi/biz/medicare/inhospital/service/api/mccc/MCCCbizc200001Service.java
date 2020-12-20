package com.powersi.biz.medicare.inhospital.service.api.mccc;

import com.powersi.biz.medicare.comm.pojo.MediDTO;
import com.powersi.biz.medicare.comm.pojo.SettlementRemoteDTO;

/**
 *
 * BIZC200001 获取异地结算单信息
 * 
 * @author zhos
 *
 */
public interface MCCCbizc200001Service extends MCCCenterService {
	String serviceid = "BIZC200001";

	/**
	 * 获取异地结算单信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public SettlementRemoteDTO getSettlementInfo(MediDTO mediDTO);

}
