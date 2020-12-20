/*
 * Copyright 2014 Powersi. All rights reserved.
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
 * The Class AppDAOImpl.
 */
public class AppDAOImpl extends BaseDAOImpl implements AppDAO {
	/** The Constant TABLE_NAME. */
	private final static String TABLE_NAME = "SYS_APP";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.AppDAO#query(java.util.Map)
	 */
	public List query(Map param) {
		ArrayList params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select t.* ");
		sql.append("   from sys_app t");
		sql.append("   where 1 = 1 ");

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
	 * @see com.powersi.sys.manager.dao.AppDAO#update(java.util.Map)
	 */
	public int update(Map param) {
		return DAOHelper.update(TABLE_NAME, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.AppDAO#insert(java.util.Map)
	 */
	public int insert(Map param) {
		return DAOHelper.insert(TABLE_NAME, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.AppDAO#delete(java.util.Map)
	 */
	public int delete(Map param) {
		DBHelper.update("delete from sys_role_apps where app_id = :app_id",
				param);
		return DAOHelper.delete(TABLE_NAME, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.AppDAO#getRoleList(java.lang.String)
	 */
	public List getRoleList(String appId) {
		return DBHelper.executeList(
				"select * from sys_role_apps where app_id = ?",
				new Object[] { appId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.AppDAO#delRoleList(java.lang.String,
	 * java.lang.String)
	 */
	public int delRoleList(String appId, String data) {
		if (UtilFunc.isEmpty(data)) {
			return 0;
		}

		String[] roles = UtilFunc.split(data, ",");
		List paramList = new ArrayList();
		for (String role : roles) {
			List param = new ArrayList();
			param.add(appId);
			param.add(role);

			paramList.add(param);
		}

		String sql = "delete from sys_role_apps where app_id = ? and role_id = ?";
		return DBHelper.executeBatch(sql, UtilFunc.toArrayOfArray(paramList)).length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.AppDAO#addRoleList(java.lang.String,
	 * java.lang.String)
	 */
	public int addRoleList(String appId, String data) {
		if (UtilFunc.isEmpty(data)) {
			return 0;
		}

		String[] roles = UtilFunc.split(data, ",");
		List paramList = new ArrayList();
		for (String role : roles) {
			List param = new ArrayList();
			param.add(appId);
			param.add(role);

			paramList.add(param);
		}

		String sql = "insert into sys_role_apps(app_id, role_id) values(?, ?)";
		return DBHelper.executeBatch(sql, UtilFunc.toArrayOfArray(paramList)).length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.AppDAO#getAppList()
	 */
	public List getAppList() {
		StringBuilder sql = new StringBuilder();

		sql.append(" select * ");
		sql.append("   from sys_app ");
		sql.append("  where valid_flag = '1' ");
		if (!getUserInfo().isSuperUser()) {
			String roleId = getUserInfo().getRoleId();
			if (!UtilFunc.hasLength(roleId)) {
				roleId = "-1";
			}
			sql.append("    and (app_grade = 0 or ");
			sql.append("        app_id in (select app_id from sys_role_apps b where role_id in ("
					+ roleId + "))) ");
		}
		sql.append("  order by dis_order ");

		return DBHelper.executeList(sql.toString());
	}
}