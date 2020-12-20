/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAO;


/**
 * The Interface CodetableDetailDAO.
 */
public interface CodetableDetailDAO extends BaseDAO {

	/**
	 * 查询.
	 * 
	 * @param param
	 *            the param
	 * @return the list
	 */
	List query(Map param);

	/**
	 * 更新.
	 * 
	 * @param param
	 *            the param
	 * @return the int
	 */
	int update(List param);

	/**
	 * 新增.
	 * 
	 * @param param
	 *            the param
	 * @return the int
	 */
	int insert(List param);

	/**
	 * 删除.
	 * 
	 * @param param
	 *            the param
	 * @return the int
	 */
	int delete(List param);
	
	/**
	 * Update.
	 *
	 * @param newCodeType the new code type
	 * @param oldCodeType the old code type
	 * @return the int
	 */
	int update(String newCodeType, String oldCodeType);
	
	/**
	 * Delete.
	 *
	 * @param codeType the code type
	 * @return the int
	 */
	int delete(String codeType);
	
	/**
	 * 查询.
	 * 
	 * @param param
	 *            the param
	 * @return the list
	 */
	List queryList(Map param);
	
	/**
	 * 删除.
	 * 
	 * @param param
	 *            the param
	 * @return the int
	 */
	int deleteList(List param);
}
