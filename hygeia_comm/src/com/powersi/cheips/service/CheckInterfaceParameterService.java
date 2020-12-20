package com.powersi.cheips.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 接口参数校验服务组件
 * 
 * @author 刘勇
 *
 */
public interface CheckInterfaceParameterService extends Serializable {

	/**
	 * 接口参数校验服务组件_接口参数定义
	 */
	public String MAP_HYGEIA_BASE_KE66 = "MAP_HYGEIA_BASE_KE66";

	/**
	 * 接口参数校验
	 * 
	 * @param param
	 *            接口参数集合
	 * @param bke563
	 *            接口化名(VARCHAR2(100) not null)
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String checkInterfaceParameter(Map param, String bke563);

	/**
	 * 接口参数校验_detail
	 * 
	 * @param param_list
	 *            接口参数集合_list
	 * @param bke563_list
	 *            接口化名(VARCHAR2(100) not null)加后缀_list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String checkInterfaceParameter(List<Map> param_list, String bke563_list);

}
