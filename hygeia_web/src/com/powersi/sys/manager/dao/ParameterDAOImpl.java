/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * The Class ParameterDAOImpl.
 */
public class ParameterDAOImpl extends BaseDAOImpl implements ParameterDAO {

	/** The Constant TABLE_NAME. */
	private final static String TABLE_NAME = "SYS_PARAMETER";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.ParameterDAO#query(java.util.Map)
	 */
	public List query(Map param) {
		ArrayList params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select t.*, t.code as id ");// 复制code作为id主键，以实现code可修改
		sql.append("   from sys_parameter t");
		sql.append("   where 1 = 1 ");

		for (String col : DAOHelper.getTableColumns(TABLE_NAME)) {
			SqlHelper.addStringExp(UtilFunc.getString(param, col), col, sql, params);
		}

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.ParameterDAO#update(java.util.Map)
	 */
	public int update(List param) {
		if (CollectionHelper.isEmpty(param)) {
			return 0;
		}
		return DAOHelper.update(TABLE_NAME, param, new String[] { "$code|id" });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.ParameterDAO#insert(java.util.Map)
	 */
	public int insert(List param) {
		if (CollectionHelper.isEmpty(param)) {
			return 0;
		}
		return DAOHelper.insert(TABLE_NAME, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.ParameterDAO#delete(java.lang.String)
	 */
	public int delete(List param) {
		if (CollectionHelper.isEmpty(param)) {
			return 0;
		}

		return DAOHelper.delete(TABLE_NAME, param, new String[] { "$code|id" });
	}
	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.ParameterDAO#keys()
	 */
	public Set<String> keys() {
		StringBuilder sql = new StringBuilder();

		sql.append(" select code");
		sql.append("   from sys_parameter");

		Set<String> set = new HashSet<String>();
		List<Object[]> lst = DBHelper.executeArrayList(sql.toString());
		for (Object[] obj : lst) {
			set.add(obj[0].toString());
		}

		return set;
	}
}
