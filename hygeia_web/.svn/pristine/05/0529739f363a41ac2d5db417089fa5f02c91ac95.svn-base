/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.test;

import static com.powersi.hygeia.framework.util.SqlHelper.debugSql;
import static com.powersi.hygeia.framework.util.SqlHelper.templateSql;

import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.jdbc.util.JdbcUtils;
import com.powersi.hygeia.framework.sql.PreparedSql;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.UserHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * 调试sql模板.
 */
public class DebugSqlTemplate {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		try {
			String method = "com.powersi.sys.user.dao.UserDAOImpl.queryOrgList(String, Map)";
			Object[] params = new Object[] { "user_kind", "3", "org_code", "222", "login_user", "admin" };

			// 设置操作用户
			 BusiContext.setUserInfo(UserHelper.getUser("9", "100")); //超级用户
			// BusiContext.setUserInfo(UserHelper.getUser("9", "101")); //省级用户
			// BusiContext.setUserInfo(UserHelper.getUser("9", "5342")); //市级用户
			// BusiContext.setUserInfo(UserHelper.getUser("9", "1395")); //区县用户

			// BusiContext.setUserInfo(UserHelper.getUser("1", "1")); //个人用户
			// BusiContext.setUserInfo(UserHelper.getUser("2", "1")); //单位用户

			PreparedSql ps = templateSql(getSqlId(method), params); 
			//输出绑定sql和参数数组(真实sql)
			StringBuilder sb = new StringBuilder();
			sb.append(ps.getId()).append("\n");
			sb.append(ps.getSql()).append("\n\nparams:[");
			sb.append(UtilFunc.join(ps.getParams(), ",")).append("]");
			LogHelper.getLogger().debug(sb.toString());
			
			//输出绑定参数后的sql(调试sql)
			debugSql(ps);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Gets the sql id.
	 * 
	 * @param methodName
	 *            the method name
	 * @return the sql id
	 */
	public static String getSqlId(String methodName) {
		String sqlId = methodName;
		int idx = sqlId.indexOf("(");
		if (idx > 0) {
			sqlId = sqlId.substring(0, idx);
		}

		idx = sqlId.lastIndexOf(".");
		if (idx >= 0) {
			return JdbcUtils.getNamespace(sqlId.substring(0, idx)) + "."
					+ sqlId.substring(idx + 1);
		} else {
			return JdbcUtils.getNamespace(sqlId);
		}
	}
}
