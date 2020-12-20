/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.MapBuilder;
import com.powersi.hygeia.framework.SysMenu;
import com.powersi.hygeia.framework.annotation.SqlNamespace;
import com.powersi.hygeia.framework.sql.PreparedSql;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UserHelper;


/**
 * The Class TestSqlTemplate.
 */
@SqlNamespace("sample.demo")
public class TestSqlTemplate {
	// 获取类的sql命名空间（可以使用@SqlNamespace注解，一般情况不需要配置，框架根据类名自动生成）
	// 类名生成sql命名空间规则：全部小写 去除com.powersi 去除dao 去除daompl
	// 继承BaseDAOImpl类可以直接使用templateSql方法，无需自己拼sql的namespace
	/** The sql namespace. */
	private static String SQL_NAMESPACE = SqlHelper
			.getNamespace(TestSqlTemplate.class) + ".";

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		try {
			// 打印模板帮助
			SqlHelper.debugSql(SqlHelper.preparedSql("help.templateHelp"));
			
			{
				Map<String, Object> param = CollectionHelper.newHashMap(
						"center_id", "440100", "query_date",
						new java.util.Date(), "query_type", "name",
						"query_value", "金骨");

				PreparedSql ps = SqlHelper.templateSql(SQL_NAMESPACE
						+ "paramMap", param);
				SqlHelper.debugSql(ps);
			}

			{
				SysMenu sysMenu = DBHelper.executeBean(SysMenu.class,
						"select * from sys_menu");
				SqlHelper.debugSql(SqlHelper.templateSql(SQL_NAMESPACE
						+ "paramBean", sysMenu));
			}

			{
				Map<String, Object> param = new HashMap();
				param.put("code_name", "sys_dept");
				SqlHelper.debugSql(SqlHelper.templateSql(SQL_NAMESPACE
						+ "dbUse", param));
			}

			{
				Map<String, Object> param = new MapBuilder()
						.append("login_user", "admin min")
						.append("user_name", "管理员").append("user_id", 100)
						.append("last_date", "2000-01-01")
						.append("birth_date", new java.util.Date())
						.append("qq", "10000").append("sex", "1, 2, 9")
						.append("grade_list", "1, 2, 3").toMap();

				SqlHelper.debugSql(SqlHelper.templateSql(SQL_NAMESPACE
						+ "dbAdds", param));
			}

			{
				Map<String, Object> param = CollectionHelper.newHashMap(
						"user_name", "广州");

				PreparedSql result = SqlHelper.templateSql(SQL_NAMESPACE
						+ "dbLike", param);
				SqlHelper.debugSql(result);
			}

			{
				Map<String, Object> param = CollectionHelper.newHashMap(
						"user_name", new Object[] { "用户名1", "用户名2", "用户名3" },
						"valid_flag", "0, 1");
				List lst = new ArrayList();
				lst.add(DateFunc.datetimeToString(new java.util.Date()));
				lst.add("2016-08-08");
				param.put("login_date", lst);
				SqlHelper.debugSql(SqlHelper.templateSql(SQL_NAMESPACE
						+ "dbJoin", param));
			}

			{
				Map<String, Object> param = CollectionHelper.newHashMap("time",
						"123456789012345678", "money", "18.12345678");
				PreparedSql result = SqlHelper.templateSql(SQL_NAMESPACE
						+ "dbNumber", param);
				SqlHelper.debugSql(result);
			}

			{
				Map<String, Object> param = CollectionHelper.newHashMap(
						"begin_date", "2016-08-08 12:00:00", "end_date",
						"2016/12/08");
				PreparedSql result = SqlHelper.templateSql(SQL_NAMESPACE
						+ "dbDate", param);
				SqlHelper.debugSql(result);
			}

			{
				Map<String, Object> param = CollectionHelper.newHashMap(
						"begin_date", "2016-08-08 12:00:00", "end_date",
						"2016/12/08 14:00:00");
				PreparedSql result = SqlHelper.templateSql(SQL_NAMESPACE
						+ "dbTimestamp", param);
				SqlHelper.debugSql(result);
			}

			{
				BusiContext.setUserInfo(UserHelper.getUser("9", "100"));
				PreparedSql result = SqlHelper.templateSql(SQL_NAMESPACE
						+ "getUserInfo");
				SqlHelper.debugSql(result);
			}
			
			{
				BusiContext.setUserInfo(UserHelper.getUser("9", "5342"));
				PreparedSql result = SqlHelper.templateSql("sys.user.staff.getUserTreeByDept");
				SqlHelper.debugSql(result);
			}
			
			{
				PreparedSql result = SqlHelper.templateSql(SQL_NAMESPACE
						+ "showError");
				SqlHelper.debugSql(result);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBHelper.close();
		}
	}
}
