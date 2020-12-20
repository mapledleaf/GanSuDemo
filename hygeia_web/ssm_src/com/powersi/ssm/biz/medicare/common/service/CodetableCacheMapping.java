/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.ssm.biz.medicare.common.service;

import java.util.Map;
import java.util.Set;

import com.powersi.hygeia.framework.service.ServiceSupport;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * 代码缓存.
 * 
 */
public abstract class CodetableCacheMapping {

	/** 服务编号. */
	public static final String SERVICE_ID = "CodetableCacheService";

	/** The service. */
	private static CodetableCacheService service;

	/**
	 * 是否包含codetable.
	 * 
	 * @param codeType
	 *            码表类型
	 * @return true, if successful
	 */
	public static boolean contains(final String codeType) {
		return getService().contains(codeType);
	}

	/**
	 * 根据代码类型获取代码表.
	 * 
	 * @param codeType
	 *            代码类型
	 * @return 代码表
	 */
	@SuppressWarnings("rawtypes")
	public static Map get(final String codeType) {
		return getService().get(codeType);
	}

	/**
	 * 根据代码类型获取代码表.
	 * 
	 * @param codeType
	 *            代码类型
	 * @param filter
	 *            过滤条件
	 * @return 代码表
	 */
	@SuppressWarnings("rawtypes")
	public static Map get(final String codeType, final String filter) {
		return getService().get(codeType, filter);
	}

	/**
	 * 根据代码类型获取代码表.
	 * 
	 * @param codeType
	 *            代码类型
	 * @param filter
	 *            过滤条件
	 * @param sort
	 *            排序条件
	 * @return 代码表
	 */
	@SuppressWarnings("rawtypes")
	public static Map get(final String codeType, final String filter, final String sort) {
		return getService().get(codeType, filter, sort);
	}

	/**
	 * 根据代码类型和值获取显示值.
	 * 
	 * @param codeType
	 *            代码类型
	 * @param value
	 *            码表值
	 * @return the display value
	 */
	public static String getDisplayValue(final String codeType, final Object value) {
		return getService().getDisplayValue(codeType, value);
	}

	/**
	 * 根据代码类型和值获取显示值.
	 * 
	 * @param codeType
	 *            代码类型
	 * @param value
	 *            码表值
	 * @param defaultValue
	 *            缺省值
	 * @return 代码表
	 */
	public static String getDisplayValue(final String codeType, final Object value, final String defaultValue) {
		return getService().getDisplayValue(codeType, value, defaultValue);
	}

	/**
	 * 获取代码类型集合.
	 * 
	 * @return the keys
	 */
	public static Set<String> getKeys() {
		return getService().getKeys();
	}

	/**
	 * 获取代码SQL.
	 * 
	 * @param codeType
	 *            代码类型
	 * @return the sql
	 */
	public static String getSql(String codeType) {
		return getService().getSql(codeType);
	}

	/**
	 * 刷新映射缓存.
	 */
	public static void refresh() {
		getService().refresh();
	}

	/**
	 * 刷新缓存.
	 * 
	 * @param codeType
	 *            码表类型
	 */
	public static void refresh(final String codeType) {
		getService().refresh(codeType);
	}

	/**
	 * 初始化.
	 */
	public static void init() {
		getService().init();
	}

	/**
	 * 获取映射的大小.
	 * 
	 * @return the size
	 */
	public static int getSize() {
		return getService().getSize();
	}

	/**
	 * 获取刷新次数.
	 * 
	 * @return 刷新次数
	 */
	public static int getTimes() {
		return getService().getTimes();
	}

	/**
	 * 获取codetable服务.
	 * 
	 * @return codetable服务
	 */
	public static CodetableCacheService getService() {
		if (service == null) {
			service = ServiceSupport.createService(SERVICE_ID, CodetableCacheService.class);
		}
		return service;
	}

	/**
	 * 设置codetable服务.
	 * 
	 * @param service
	 *            codetable服务
	 */
	public static void setService(CodetableCacheService service) {
		CodetableCacheMapping.service = service;
	}

	/**
	 * Find codetable.
	 * 
	 * @param type
	 *            the type
	 * @param local
	 *            the local
	 * @return the string
	 */
	public static String findCodetable(String type, String local) {
		if (local == null || local.isEmpty()) {
			return type;
		}

		if (local.length() != 6) {
			return type + "$" + local;
		}

		String code = UtilFunc.strcat(type, "$", local);
		if (getService().contains(code)) {
			return code;
		}

		code = UtilFunc.strcat(type, "$", local.substring(0, 4), "00");
		if (getService().contains(code)) {
			return code;
		}

		code = UtilFunc.strcat(type, "$", local.substring(0, 2), "0000");
		if (getService().contains(code)) {
			return code;
		}

		return type;
	}

}