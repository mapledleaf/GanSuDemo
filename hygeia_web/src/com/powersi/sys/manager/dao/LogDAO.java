/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAO;

/**
 * 日志DAO接口.
 */
public interface LogDAO extends BaseDAO {

	/**
	 * 查询登录日志.
	 * 
	 * @param beginDate
	 *            the begin date
	 * @param endDate
	 *            the end date
	 * @param map
	 *            the map
	 * @return the list
	 */
	public List queryLoginLog(String beginDate, String endDate, Map map);

	/**
	 * Gets the login log.
	 *
	 * @param loginId the login id
	 * @return the login log
	 */
	public Map getLoginLog(String loginId);
	
	/**
	 * 查询操作日志.
	 * 
	 * @param beginDate
	 *            the begin date
	 * @param endDate
	 *            the end date
	 * @param map
	 *            the map
	 * @return the list
	 */
	public List queryOperateLog(String beginDate, String endDate, Map map);

	/**
	 * Gets the operate log.
	 *
	 * @param operateId the operate id
	 * @return the operate log
	 */
	public Map getOperateLog(String operateId);
	
	/**
	 * 查询系统日志.
	 * 
	 * @param beginDate
	 *            the begin date
	 * @param endDate
	 *            the end date
	 * @param map
	 *            the map
	 * @return the list
	 */
	public List querySystemLog(String beginDate, String endDate, Map map);
	
	/**
	 * 获取系统日志.
	 * 
	 * @param logSn
	 *            the log sn
	 * @return the system log
	 */
	public Map getSystemLog(String logSn);

	/**
	 * 统计登录日志.
	 * 
	 * @param beginDate
	 *            the begin date
	 * @param endDate
	 *            the end date
	 * @param map
	 *            the map
	 * @return the list
	 */
	public List statLoginLog(String beginDate, String endDate, Map map);
	
	/**
	 * 统计注销日志.
	 * 
	 * @param beginDate
	 *            the begin date
	 * @param endDate
	 *            the end date
	 * @param map
	 *            the map
	 * @return the list
	 */
	public List statLogoutLog(String beginDate, String endDate, Map map);
	
	/**
	 * 清理系统日志.
	 *
	 * @param date the date
	 */
	public void clearSystemLog(java.util.Date date);
	
	/**
	 * Analysis login log by time.
	 *
	 * @param map the map
	 * @return the list
	 */
	public List analysisLoginLogByTime(Map map);
	
	/**
	 * Analysis login log by user.
	 *
	 * @param map the map
	 * @return the list
	 */
	public List analysisLoginLogByUser(Map map);
	
	/**
	 * Analysis login log by date.
	 *
	 * @param map the map
	 * @return the list
	 */
	public List analysisLoginLogByDate(Map map);
}
