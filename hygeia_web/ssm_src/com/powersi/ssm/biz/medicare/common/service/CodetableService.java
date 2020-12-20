/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.ssm.biz.medicare.common.service;

import java.util.Map;

/**
 * 代码表服务.
 */
public interface CodetableService {

	/**
	 * 是否包含codetable.
	 * 
	 * @param codeType
	 *            码表类型
	 * @return true, if successful
	 */
	boolean contains(final String codeType);

	/**
	 * 根据代码类型获取代码表.
	 * 
	 * @param codeType
	 *            代码类型
	 * @return 代码表
	 */
	@SuppressWarnings("rawtypes")
	Map get(final String codeType);

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
	Map get(final String codeType, final String filter);

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
	Map get(final String codeType, final String filter, final String sort);

	/**
	 * 根据代码类型和值获取显示值.
	 * 
	 * @param codeType
	 *            代码类型
	 * @param value
	 *            码表值
	 * @return the display value
	 */
	String getDisplayValue(final String codeType, final Object value);

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
	String getDisplayValue(final String codeType, final Object value, final String defaultValue);

	/**
	 * 刷新映射码表.
	 * 
	 */
	void refresh();

	/**
	 * 刷新码表.
	 * 
	 * @param codeType
	 *            码表类型
	 */
	void refresh(final String codeType);

	/**
	 * 获取映射的大小.
	 * 
	 * @return the size
	 */
	int getSize();

	/**
	 * 获取刷新次数.
	 * 
	 * @return 刷新次数
	 */
	int getTimes();

	/**
	 * 初始化.
	 */
	void init();

}
