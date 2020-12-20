package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface MCCEbizc131271Service extends Serializable{

	/**
	 * 去中心获取业务信息
	 * @param mParas
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryBizInfo(Map<String, Object> mParas);
	
	/**
	 * 加载本地业务信息
	 * @param mParas
	 * @return
	 */
	public Map<String, Object> queryLocalBizInfo(Map<String, Object> mParas);
}
