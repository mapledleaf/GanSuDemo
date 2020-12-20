/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sample.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBFunc;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * The Class SampleDAOImpl.
 */
public class SampleDAOImpl extends BaseDAOImpl implements SampleDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sample.dao.SampleDAO#queryCatalog(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public List queryCatalog(String centerId, String queryType,
			String queryValue) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select * ");
		sql.append("   from ka20 ");
		sql.append("  where aaa027 = ? ");
		sql.append("    and aae030 <= trunc(sysdate) ");
		sql.append("    and aae031 >= trunc(sysdate) ");
		sql.append("    and aae016 = '1' ");

		String search = UtilFunc.trimSqlParam(queryValue);
		sql.append(" and ");
		if ("code".equals(queryType)) {
			sql.append("ake001");
		} else if ("name".equals(queryType)) {
			sql.append("ake002");
		} else if ("py".equals(queryType)) {
			sql.append("aka020");
		} else if ("wb".equals(queryType)) {
			sql.append("aka021");
		} else {
			sql.append("aka020");
		}

		sql.append(" like ?");

		sql.append(" order by ake002, ake001");
		return DBHelper.executeList(sql.toString(),
				new Object[] { centerId, "%" + search.toUpperCase() + "%" });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sample.dao.SampleDAO#queryCodetable(java.util.Map)
	 */
	public List queryCodetable(Map param) {
		ArrayList params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select * ");
		sql.append("   from sys_code_table ");
		sql.append("   where 1 = 1 ");

		for (String col : DAOHelper.getTableColumns("SYS_CODE_TABLE")) {
			SqlHelper.addStringExp(UtilFunc.getString(param, col), col, sql, params);
		}

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/**
	 * Query codetable detail.
	 * 
	 * @param param
	 *            the param
	 * @return the list
	 */
	public List queryCodetableDetail(Map param) {
		ArrayList params = new ArrayList();
		String type = UtilFunc.getString(param, "type", "");
		StringBuilder sql = new StringBuilder();
		Object codeSql = DBHelper
				.executeScale(
						"select code_sql from sys_code_table where code_type = ?",
						new Object[] { type });

		if (UtilFunc.isEmptyString(codeSql)) {
			sql.append("select * from sys_code_table_detail where code_type = ?");
			params.add(type);
		} else {
			List<String> cols = getCodetableDetailColumns(type,
					codeSql.toString());
			sql.append("select * from ( select ").append(cols.get(0))
					.append(" as data_value, ");
			sql.append(cols.get(1)).append(
					" as display_value, rownum as dis_order from(");
			sql.append(codeSql);
			sql.append(")) where 1 = 1");
		}

		for (String col : DAOHelper.getTableColumns("SYS_CODE_TABLE_DETAIL")) {
			SqlHelper.addStringExp(UtilFunc.getString(param, col), col, sql, params);
		}
		
		sql.append(" order by dis_order");

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
	private List<String> getCodetableDetailColumns(String type, String codeSql) {
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
	 * @see com.powersi.sample.dao.SampleDAO#queryAa10(java.util.Map)
	 */
	public List queryAa10(Map param) {
		ArrayList params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select * ");
		sql.append("   from aa10 ");
		sql.append("   where 1 = 1 ");

		for (String col : DAOHelper.getTableColumns("AA10")) {
			SqlHelper.addStringExp(UtilFunc.getString(param, col), col, sql, params);
		}

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}
	
	/* (non-Javadoc)
	 * @see com.powersi.sample.dao.SampleDAO#queryTexts(java.util.Map)
	 */
	public List queryTexts(Map param) {
		List params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append(" select t.*, (t.local_id || '|' || t.text_code) as id ");// 复制text_code作为id主键，以实现text_code可修改
		sql.append("   from sys_texts t");
		sql.append("   where valid_flag = '1'");
		sql.append("   and (local_id = '0' or local_id = '")
				.append(ParameterMapping.getLocalId()).append("')");

		for (String col : DAOHelper.getTableColumns("SYS_TEXTS")) {
			if ("local_id".equals(col)) {
				SqlHelper.addStringValues(param.get(col), col, sql, params);
			} else {
				SqlHelper.addStringExp(UtilFunc.getString(param, col), col,
						sql, params);
			}
		}

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}
}
