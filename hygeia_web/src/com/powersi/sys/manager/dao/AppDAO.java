/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAO;

/**
 * The Interface AppDAO.
 */
public interface AppDAO extends BaseDAO {

	/**
	 * 查询.
	 *
	 * @param param the param
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
	int update(Map param);

	/**
	 * 新增.
	 * 
	 * @param param
	 *            the param
	 * @return the int
	 */
	int insert(Map param);

	/**
	 * 删除.
	 * 
	 * @param param
	 *            the param
	 * @return the int
	 */
	int delete(Map param);
	
	/**
	 * Gets the role list.
	 *
	 * @param appId the app id
	 * @return the role list
	 */
	List getRoleList(String appId);
	
	/**
	 * add role list.
	 *
	 * @param appId the app id
	 * @return the list
	 */
	int addRoleList(String appId, String data);
	
	/**
	 * del role list.
	 *
	 * @param appId the app id
	 * @return the list
	 */
	int delRoleList(String appId, String data);
	
	/**
	 * Gets the app list.
	 *
	 * @return the app list
	 */
	List getAppList();
}
