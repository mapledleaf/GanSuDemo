package com.powersi.biz.medicare.cumulative.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.cumulative.pojo.PersonalCumulativeResourcesDTO;
import com.powersi.biz.medicare.query.dto.KC21;
import com.powersi.biz.medicare.query.dto.KC27;

/**
 * 个人医疗累计服务接口（入口）
 * 
 * @author zhos
 *
 */
public interface PersonalCumulativeService extends Serializable {

	public String aaa027_430300 = "430300";
	public String serviceid = "mccc_bizh000000";

	/**
	 * 处理个人医疗累计
	 * @param personalCumulativeResourcesDTO
	 */
	public void loadPersonalCumulative(PersonalCumulativeResourcesDTO personalCumulativeResourcesDTO);

	/**
	 * 提供调用累计的参数装载方法
	 * @param refundFlag
	 * @param kc21
	 * @param kc27Rows
	 * @param cumulateData
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public PersonalCumulativeResourcesDTO prepareParamsLoadToLoadCumulative(String refundFlag, KC21 kc21,
			List<KC27> kc27Rows, Map cumulateData);
}
