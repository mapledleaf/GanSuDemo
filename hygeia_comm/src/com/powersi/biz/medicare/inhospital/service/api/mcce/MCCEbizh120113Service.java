package com.powersi.biz.medicare.inhospital.service.api.mcce;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120113Service;
import com.powersi.biz.medicare.query.pojo.BizQueryDTO;

/**
 * 费用清单明细
 * 
 * @author yangmj
 *
 */
public interface MCCEbizh120113Service extends MCCEsbService {

	public String serviceid = mcce_ + MCCHbizh120113Service.function_id;

	/**
	 * 费用清单明细
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryFeeDetailTable(BizQueryDTO bizQueryDto);

}
