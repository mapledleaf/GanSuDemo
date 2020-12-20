/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBFunc;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * The Class CodetableDetailDAOImpl.
 */
public class CodetableDetailDAOImpl extends BaseDAOImpl implements
		CodetableDetailDAO {

	/** The Constant TABLE_NAME. */
	private final static String TABLE_NAME = "SYS_CODE_TABLE_DETAIL";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.CodetableDetailDAO#query(java.util.Map)
	 */
	public List query(Map param) {
		ArrayList params = new ArrayList();
		String type = UtilFunc.getString(param, "type", "");
		StringBuilder sql = new StringBuilder();
		Object codeSql = DBHelper
				.executeScale(
						"select code_sql from sys_code_table where code_type = ?",
						new Object[] { type });

		String id = null;
		if (DBHelper.existColumn(TABLE_NAME, "LOCAL_ID")) {
			id = "(t.local_id || '|' || t.data_value)";
		} else {
			id = "t.data_value";
		}

		if (UtilFunc.isEmptyString(codeSql)) {
			sql.append("select t.*, ")
					.append(id)
					.append(" as id from sys_code_table_detail t where code_type = ?");
			params.add(type);
		} else {
			List<String> cols = getColumns(type,
					codeSql.toString());
			sql.append("select * from ( select ")
					.append(cols.get(0))
					.append(" as data_value, ");
			sql.append(cols.get(1)).append(
					" as display_value, rownum as dis_order from(");
			sql.append(codeSql);
			sql.append(")) where 1 = 1");
		}

		for (String col : DAOHelper.getTableColumns(TABLE_NAME)) {
			SqlHelper.addStringExp(UtilFunc.getString(param, col), col, sql,
					params);
		}

		sql.append(" order by dis_order");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.manager.dao.CodetableDetailDAO#queryList(java.util.Map)
	 */
	public List queryList(Map param) {
		ArrayList params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		boolean hasLocal = DBHelper.existColumn(TABLE_NAME, "LOCAL_ID");

		sql.append(" select a.*, ");
		if (hasLocal) {
			sql.append("(a.local_id || '|' || a.data_value) as id");
		} else {
			sql.append("(a.data_value) as id");
		}
		sql.append("   from sys_code_table_detail a, sys_code_table b ");
		sql.append("  where a.code_type = b.code_type ");
		
		/*
		if (hasLocal) {
			sql.append("    and (a.local_id = '0' or a.local_id = '")
					.append(ParameterMapping.getLocalId()).append("') ");
		}
		*/
		
		for (String col : DAOHelper.getTableColumns("SYS_CODE_TABLE")) {
			SqlHelper.addStringExp(UtilFunc.getString(param, col), "b." + col,
					sql, params);
		}

		if (hasLocal) {
			sql.append(" order by a.local_id, a.code_type, a.dis_order ");
		} else {
			sql.append(" order by a.code_type, a.dis_order ");
		}

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/**
	 * Gets the codetable detail columns.
	 * 
	 * @param type
	 *            the type
	 * @param codeSql
	 *            the code sql
	 * @return the codetable detail columns
	 */
	private List<String> getColumns(String type, String codeSql) {
		String sql = "select * from (" + codeSql + ") where  1 = 0";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> cols = new ArrayList<String>();
		try {
			ps = DBFunc.prepareStatement(DBHelper.getConnection(), sql);
			rs = DBFunc.executeQuery(DBHelper.getConnection(), ps, sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				cols.add(rsmd.getColumnName(i).toLowerCase());
			}

			if (cols.size() < 2) {
				throw new HygeiaException(type + "列数不够(需要数据列和显示列)");
			}
			return cols;
		} catch (Exception ex) {
			throw new HygeiaException("获取列名集合出错");
		} finally {
			DBFunc.closeResultSet(rs);
			DBFunc.closeStatement(ps);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.manager.dao.CodetableDetailDAO#update(java.util.List)
	 */
	public int update(List param) {
		if (CollectionHelper.isEmpty(param)) {
			return 0;
		}

		String[] keys = null;
		if (DBHelper.existColumn(TABLE_NAME, "LOCAL_ID")) {
			keys = new String[] { "code_type", "$local_id|old_local_id",
					"$data_value|old_data_value" };
			for (int i = 0; i < param.size(); i++) {
				Map map = (Map) param.get(i);
				String[] str = UtilFunc.split(UtilFunc.getString(map, "id"),
						"|");
				if (str.length >= 2) {
					map.put("old_local_id", str[0]);
					map.put("old_data_value", str[1]);
				}
			}
		} else {
			keys = new String[] { "code_type", "$data_value|id" };
		}

		return DAOHelper.update(TABLE_NAME, param, keys);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.manager.dao.CodetableDetailDAO#insert(java.util.List)
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
	 * @see
	 * com.powersi.sys.manager.dao.CodetableDetailDAO#delete(java.util.List)
	 */
	public int delete(List param) {
		if (CollectionHelper.isEmpty(param)) {
			return 0;
		}

		String[] keys = null;
		if (DBHelper.existColumn(TABLE_NAME, "LOCAL_ID")) {
			keys = new String[] { "code_type", "$local_id|old_local_id",
					"$data_value|old_data_value" };
			for (int i = 0; i < param.size(); i++) {
				Map map = (Map) param.get(i);
				String[] str = UtilFunc.split(UtilFunc.getString(map, "id"),
						"|");
				if (str.length >= 2) {
					map.put("old_local_id", str[0]);
					map.put("old_data_value", str[1]);
				}
			}
		} else {
			keys = new String[] { "code_type", "$data_value|id" };
		}

		return DAOHelper.delete(TABLE_NAME, param, keys);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.manager.dao.CodetableDetailDAO#update(java.lang.String,
	 * java.lang.String)
	 */
	public int update(String newCodeType, String oldCodeType) {
		return DBHelper
				.update("UPDATE SYS_CODE_TABLE_DETAIL SET CODE_TYPE = ? WHERE CODE_TYPE = ?",
						new Object[] { newCodeType, oldCodeType });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.manager.dao.CodetableDetailDAO#delete(java.lang.String)
	 */
	public int delete(String codeType) {
		return DBHelper
				.update("DELETE FROM SYS_CODE_TABLE_DETAIL WHERE CODE_TYPE = ?",
						new Object[] { codeType });
	}
	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.CodetableDetailDAO#delete(java.util.List)
	 */
	public int deleteList(List param) {
		return DBHelper
				.batchUpdate("DELETE FROM SYS_CODE_TABLE_DETAIL WHERE CODE_TYPE = :code_type", param).length;
	}
}
