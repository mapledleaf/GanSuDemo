/*
 * Copyright 2013 Powersi. All rights reserved.
 */
package com.powersi.sys.user.dao;

import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.sys.user.dto.SearchChangeDTO;
import com.powersi.sys.user.dto.SysChangeDTO;

/**
 * The Class ChangeDAOImpl.
 */
public class ChangeDAOImpl extends BaseDAOImpl implements ChangeDAO {
	
	//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	boolean checkRight = !ParameterMapping.getStringByCode("check_conn_mysql", "0").equals("0");

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.user.dao.ChangeDAO#insertChangeDetail(java.util.List)
	 */
	public int insertChangeDetail(List<Map<String, Object>> changeDetailList) {
		return dbTemplate(Integer.class, "insertChangeDetail", changeDetailList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.user.dao.ChangeDAO#insertChangeEvent(com.powersi.sys.
	 * user.dto.SysChangeDTO)
	 */
	public int insertChangeEvent(SysChangeDTO changeDto) {
		return dbTemplate(Integer.class, "insertChangeEvent", changeDto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.user.dao.ChangeDAO#queryChangeDetail(com.powersi.sys.
	 * user.dto.SearchChangeDTO)
	 */
	public List<Map<String, Object>> queryChangeDetail(
			SearchChangeDTO searchChangeDto) {
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
	    	return dbTemplate(List.class, "mysql_queryChangeDetail", searchChangeDto);
	    }else {
	    	return dbTemplate(List.class, "queryChangeDetail", searchChangeDto);
	    }
	}
}
