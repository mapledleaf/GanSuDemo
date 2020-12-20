/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * The Class BeanDAOImpl.
 */
public class BeanDAOImpl extends BaseDAOImpl implements BeanDAO {

	/** The Constant TABLE_NAME. */
	private final static String TABLE_NAME = "SYS_BEANS";

	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.BeanDAO#query(java.util.Map)
	 */
	public List query(Map param) {
		List params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select t.*, (t.local_id || '|' || t.bean_id) as id ");// 复制bean_id作为id主键，以实现bean_id可修改
		sql.append("   from ").append(TABLE_NAME).append(" t ");
		sql.append("   where (local_id = '0' or local_id = '")
				.append(ParameterMapping.getLocalId()).append("')");

		SqlHelper.addDatetime(UtilFunc.getString(param, "begin_date"), "last_date", ">=", sql, params);
		SqlHelper.addDatetime(UtilFunc.getString(param, "end_date"), "last_date", "<=", sql, params);
		
		for (String col : DAOHelper.getTableColumns(TABLE_NAME)) {
			if ("local_id".equals(col) || "valid_flag".equals(col)) {
				SqlHelper.addStringValues(param.get(col), col, sql, params);
			} else {
				SqlHelper.addStringExp(UtilFunc.getString(param, col), col,
						sql, params);
			}
		}
		
		sql.append(" order by bean_id");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.BeanDAO#update(java.util.List)
	 */
	public int update(List param) {
		if (CollectionHelper.isEmpty(param)) {
			return 0;
		}

		Timestamp lastDate = DBHelper.getDBTimestamp();
		for (int i = 0; i < param.size(); i++) {
			Map map = (Map) param.get(i);
			String[] str = UtilFunc.split(UtilFunc.getString(map, "id"), "|");
			if (str.length >= 2) {
				map.put("old_local_id", str[0]);
				map.put("old_bean_id", str[1]);
			}
			map.put("last_date", lastDate);
		}

		return DAOHelper.update(TABLE_NAME, param, new String[] {
				"$local_id|old_local_id", "$bean_id|old_bean_id" });
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.BeanDAO#insert(java.util.List)
	 */
	public int insert(List param) {
		if (CollectionHelper.isEmpty(param)) {
			return 0;
		}
		return DAOHelper.insert(TABLE_NAME, param);
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.BeanDAO#delete(java.util.List)
	 */
	public int delete(List param) {
		if (CollectionHelper.isEmpty(param)) {
			return 0;
		}

		for (int i = 0; i < param.size(); i++) {
			Map map = (Map) param.get(i);
			String[] str = UtilFunc.split(UtilFunc.getString(map, "id"), "|");
			if (str.length >= 2) {
				map.put("old_local_id", str[0]);
				map.put("old_bean_id", str[1]);
			}
		}

		return DAOHelper.delete(TABLE_NAME, param, new String[] {
				"$local_id|old_local_id", "$bean_id|old_bean_id" });
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.BeanDAO#keys()
	 */
	public Set<String> keys() {
		StringBuilder sql = new StringBuilder();

		sql.append(" select local_id, bean_id");
		sql.append("   from ").append(TABLE_NAME);

		Set<String> set = new HashSet<String>();
		List<Object[]> lst = DBHelper.executeArrayList(sql.toString());
		for (Object[] obj : lst) {
			set.add(obj[0].toString() + "|" + obj[1].toString());
		}

		return set;
	}
}
