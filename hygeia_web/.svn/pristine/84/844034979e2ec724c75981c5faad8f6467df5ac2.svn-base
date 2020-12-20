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
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * The Class CodetableDAOImpl.
 */
public class CodetableDAOImpl extends BaseDAOImpl implements CodetableDAO {

	/** The Constant TABLE_NAME. */
	private final static String TABLE_NAME = "SYS_CODE_TABLE";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.CodetableDAO#query(java.util.Map)
	 */
	public List query(Map param) {
		ArrayList params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append(" select t.*, t.code_type as id ");// 复制code_type作为id主键，实现主键可修改
		sql.append("   from sys_code_table t ");
		sql.append("   where 1 = 1 ");

		for (String col : DAOHelper.getTableColumns(TABLE_NAME)) {
			SqlHelper.addStringExp(UtilFunc.getString(param, col), col, sql, params);
		}

		sql.append(" order by code_type");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.CodetableDAO#update(java.util.Map)
	 */
	public int update(Map param) {
		return DAOHelper.update(TABLE_NAME, param,
				new String[] { "$code_type|id" });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.CodetableDAO#insert(java.util.Map)
	 */
	public int insert(Map param) {
		return DAOHelper.insert(TABLE_NAME, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.CodetableDAO#delete(java.util.List)
	 */
	public int delete(Map param) {
		return DAOHelper.delete(TABLE_NAME, param,
				new String[] { "$code_type|id" });
	}
	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.CodetableDAO#keys()
	 */
	public Set<String> keys() {
		StringBuilder sql = new StringBuilder();

		sql.append(" select code_type");
		sql.append("   from sys_code_table");

		Set<String> set = new HashSet<String>();
		List<Object[]> lst = DBHelper.executeArrayList(sql.toString());
		for (Object[] obj : lst) {
			set.add(obj[0].toString());
		}

		return set;
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.CodetableDAO#update(java.util.List)
	 */
	public int update(List param) {
		return DAOHelper.update(TABLE_NAME, param,
					new String[] { "$code_type|id" });
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.CodetableDAO#insert(java.util.List)
	 */
	public int insert(List param) {
		return DAOHelper.insert(TABLE_NAME, param);
	}
}
