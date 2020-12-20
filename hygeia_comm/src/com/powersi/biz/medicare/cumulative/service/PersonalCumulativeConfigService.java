package com.powersi.biz.medicare.cumulative.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.cumulative.pojo.PersonalCumulativeConfigDTO;

/**
 * 个人医疗累计要素配置服务接口
 * 
 * @author zhos
 *
 */
public interface PersonalCumulativeConfigService extends Serializable {

	public String CUMULATIVE_AAE001_CODE = "CUMULATIVE_AAE001_CODE";
	
	/**
	 * 获取个人医疗累计要素配置
	 * @param personalCumulativeResourcesDTO
	 * @return
	 */
	public List<PersonalCumulativeConfigDTO> loadPersonCumulativeConfigRows(
			PersonalCumulativeConfigDTO personalCumulativeConfigDTO);
	
	/**
	 * 通过配置获取累计年度
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Object getAae001ByConfig(Map param);
}
