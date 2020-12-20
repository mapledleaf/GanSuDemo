package com.powersi.biz.medicare.inhospital.service.api.mcce;

import java.util.List;

import com.powersi.biz.medicare.inhospital.pojo.FundPayInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 
 * bizh120312 出院结算后取基金支付信息
 * 
 * @author 刘勇
 *
 */
public interface MCCEbizh120312Service extends MCCEsbService {

	public String serviceid = mcce_ + "bizh120312";

	/**
	 * 出院结算后取基金支付信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public List<FundPayInfoDTO> queryFundPay(InHospitalDTO inHospitalDTO);

}
