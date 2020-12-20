package com.powersi.configvalue.service;

import java.io.Serializable;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.MediDTO;

/**
 * 配置取值（业务组件）
 * 
 * @author 刘勇
 *
 */
public interface ConfigValueService extends Serializable {

	/**
	 * 需要配置的对象定义
	 */
	public String MAP_HYGEIA_BASE_KE65 = "MAP_HYGEIA_BASE_KE65";

	/**
	 * 条件
	 */
	public String MAP_HYGEIA_BASE_KE63 = "MAP_HYGEIA_BASE_KE63";

	/**
	 * 要素
	 */
	public String MAP_HYGEIA_BASE_KE61 = "MAP_HYGEIA_BASE_KE61";

	/**
	 * 配置取值
	 * 
	 * @param mediDTO
	 *            业务参数集
	 * @param bke553
	 *            需要配置的对象化名(VARCHAR2(100) not null)__比如：aae001(医保年度)
	 * @return
	 */
	public String configValue(MediDTO mediDTO, String bke553);

	/**
	 * 
	 * @param map
	 *            业务参数集
	 * @param bke553
	 *            需要配置的对象化名(VARCHAR2(100) not null)__比如：aae001(医保年度)
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String configValue(Map map, String bke553);

}
