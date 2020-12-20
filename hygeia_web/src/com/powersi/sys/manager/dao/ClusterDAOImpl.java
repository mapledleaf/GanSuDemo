/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UtilFunc;


/**
 * The Class ClusterDAOImpl.
 */
public class ClusterDAOImpl extends BaseDAOImpl implements ClusterDAO {
	/** The Constant TABLE_NAME. */
	private final static String TABLE_NAME = "SYS_CLUSTERS";

	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.ClusterDAO#query(java.util.Map)
	 */
	public List query(Map param) {
		ArrayList params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select t.* ");
		sql.append("   from ").append(TABLE_NAME).append(" t");
		sql.append("   where 1 = 1 ");

		for (String col : DAOHelper.getTableColumns(TABLE_NAME)) {
			SqlHelper.addStringExp(UtilFunc.getString(param, col), col, sql,
					params);
		}

		sql.append(" order by cluster_name");
		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.ClusterDAO#save(java.util.List)
	 */
	public int save(List param) {
		DBHelper.executeUpdate("DELETE FROM SYS_CLUSTERS");
		
		return DAOHelper.insert(TABLE_NAME, param);
	}
}