package com.powersi.biz.medicare.inhospital.service.api.mccc;

import java.util.List;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120001Service;
import com.powersi.hygeia.comm.dto.RemoteCallResult;

/**
 *
 * bizh120001 入院登记时取人员信息接口
 * 
 * @author 刘勇
 *
 */
public interface MCCCbizh120001Service extends MCCCenterService {

	String serviceid = mccc_ + MCCHbizh120001Service.function_id;

	/**
	 * 住院去中心取人员信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public List<InHospitalDTO> searchPersonInfo(InHospitalDTO inHospitalDTO);

	/**
	 * 住院去中心取静态要素
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public RemoteCallResult loadStaticFactor(InHospitalDTO inHospitalDTO);

}
