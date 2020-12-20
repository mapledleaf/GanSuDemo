package com.powersi.biz.medicare.inhospital.service.api.mcce;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120005Service;

public interface MCCEbizh120005Service extends MCCEsbService {
	
	public String serviceid = mcce_ + MCCHbizh120005Service.function_id;

	public String actionName = "校验并保存费用-";

	/**
	 * 校验并保存费用信息_通用接口
	 * 
	 * @param inHospitalDTO
	 * @param inHospitalDTORows
	 * @return
	 */
	public InHospitalDTO checkAndSaveFeeInfoCommnInterface(InHospitalDTO inHospitalDTO,
			List<InHospitalDTO> inHospitalDTORows);

	/**
	 * 校验并保存费用信息
	 * 
	 * @param inHospitalDTO
	 * @param inHospitalDTORows
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> checkAndSaveFeeInfo(InHospitalDTO inHospitalDTO, List<InHospitalDTO> inHospitalDTORows);

	/**
	 * 校验并保存费用信息（返回自付比例）
	 * 
	 * @param inHospitalDTO
	 * @param inHospitalDTORows
	 * @param feeinfoRowsScale
	 * @return
	 */
	/*@SuppressWarnings("rawtypes")
	public InHospitalDTO checkAndSaveFeeInfo(InHospitalDTO inHospitalDTO, List<InHospitalDTO> inHospitalDTORows,
			List<Map> feeinfoRowsScale);*/

	/**
	 * 省内异地上传费用信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO proccessFeeInfoSnyd(InHospitalDTO inHospitalDTO);

}
