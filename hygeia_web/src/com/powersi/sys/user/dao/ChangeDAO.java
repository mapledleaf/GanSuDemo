/*
 * Copyright 2013 Powersi. All rights reserved.
 */
package com.powersi.sys.user.dao;

import java.util.List;
import java.util.Map;

import com.powersi.sys.user.dto.SearchChangeDTO;
import com.powersi.sys.user.dto.SysChangeDTO;

/**
 * The Interface ChangeDAO.
 */
public interface ChangeDAO {
	/** The Constant ITEM_TYPE_USER_INFO. */
	public static final String ITEM_TYPE_USER_INFO ="1";
	
	/** The Constant ITEM_TYPE_RIGHT_INFO. */
	public static final String ITEM_TYPE_RIGHT_INFO="2";
	
	/** The Constant ITEM_EVENT_TYPE_ADD. */
	public static final String ITEM_EVENT_TYPE_ADD ="1";
	
	/** The Constant ITEM_EVENT_TYPE_DEL. */
	public static final String ITEM_EVENT_TYPE_DEL="2";
	
	/** The Constant ITEM_EVENT_TYPE_UPT. */
	public static final String ITEM_EVENT_TYPE_UPT ="3";
	
	
	/**
	 * 插入改变事件.
	 *
	 * @param changeDto the change dto
	 * @return the int
	 */
	public int insertChangeEvent(SysChangeDTO changeDto);
	
	/**
	 * 插入改变内容.
	 *
	 * @param changeDetailList the change detail list
	 * @return the int
	 */
	public int insertChangeDetail(List<Map<String,Object>> changeDetailList);
	
	/**
	 * 查询变更列表.
	 *
	 * @param searchChangeDto the search change dto
	 * @return the list
	 */
	public List<Map<String, Object>> queryChangeDetail(SearchChangeDTO searchChangeDto);
}
