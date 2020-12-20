/**
 * Copyright 2011 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.manager.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.powersi.hygeia.framework.BaseDAO;

/**
 * 版本DAO接口.
 */
public interface VersionDAO extends BaseDAO {
	/** DAO对应表名. */
	public static final String TABLE_NAME = "sys_application_version";
	
	/**
	 * 根据文件类型，取版本基本信息.
	 *
	 * @param sn 序号
	 * @param fileName 文件名称
	 * @param fileType 文件类型
	 * @return 文件列表
	 * @throws UnsupportedEncodingException 
	 */
	public List getFile(String sn, String fileName, String fileType) throws UnsupportedEncodingException;

	/**
	 * 根据文件类型获取版本信息.
	 * 
	 * @param fileType
	 *            文件类型
	 * 
	 * @return 文件列表
	 *         
	 */
	public List getVersionInfo(String fileType);

	/**
	 * 插入版本信息.
	 * 
	 * @param lstData
	 *            数据
	 * 
	 * @return 插入记录数
	 */
	public int insert(List lstData);
	
	/**
	 * 更新版本信息.
	 * 
	 * @param lstData
	 *            数据
	 * 
	 * @return 更新记录数
	 */
	public int update(List lstData);

	/**
	 * 更新版本文件.
	 * 
	 * @param lstData
	 *            数据
	 * 
	 * @return 更新记录数
	 * @throws UnsupportedEncodingException 
	 */
	public int updateFile(List lstData) throws UnsupportedEncodingException;

	/**
	 * 删除版本信息.
	 * 
	 * @param lstData
	 *            数据
	 * 
	 * @return 删除记录数
	 */
	public int delete(List lstData);
}
