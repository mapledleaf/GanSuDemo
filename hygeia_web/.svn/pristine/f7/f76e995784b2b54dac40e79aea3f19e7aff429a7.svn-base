/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * The Class TaskDAOImpl.
 */
public class TaskDAOImpl extends BaseDAOImpl implements TaskDAO {
	/** The Constant TABLE_NAME. */
	private final static String TABLE_NAME = "SYS_TASKS";

	//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	boolean checkRight = !ParameterMapping.getStringByCode("check_conn_mysql", "0").equals("0");
		
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.TaskDAO#query(java.util.Map)
	 */
	public List query(Map param) {
		ArrayList params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select t.*, t.task_name as id ");
		sql.append("   from sys_tasks t");
		sql.append("   where 1 = 1 ");

		if (UtilFunc.isNotBlank(param.get("task_nameordesc"))) {
			SqlHelper.addStringExp(
					UtilFunc.getString(param, "task_nameordesc"),
					"(task_name || task_desc)", sql, params);
		}

		String[] vals = new String[] { "trigger_value", "log_flag",
				"lock_flag", "valid_flag" };
		for (String col : vals) {
			SqlHelper.addStringValues(param.get(col), col, sql,
					params);
		}

		String[] cols = new String[] { "task_name", "task_desc", "task_class",
				"task_param", "trigger_value", "trigger_instance",
				"this_instance", "last_instance" };
		for (String col : cols) {
			SqlHelper.addStringExp(UtilFunc.getString(param, col), col, sql,
					params);
		}

		sql.append(" order by task_name");
		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.TaskDAO#update(java.util.Map)
	 */
	public int update(Map param) {
		if (UtilFunc.isEmpty(param)) {
			return 0;
		}
		return DAOHelper.update(TABLE_NAME, param,
				new String[] { "$task_name|id" });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.TaskDAO#insert(java.util.Map)
	 */
	public int insert(Map param) {
		if (UtilFunc.isEmpty(param)) {
			return 0;
		}
		return DAOHelper.insert(TABLE_NAME, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.TaskDAO#delete(java.util.Map)
	 */
	public int delete(Map param) {
		if (UtilFunc.isEmpty(param)) {
			return 0;
		}
		return DAOHelper.delete(TABLE_NAME, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.TaskDAO#update(java.util.List)
	 */
	public int update(List param) {
		if (UtilFunc.isEmpty(param)) {
			return 0;
		}
		return DAOHelper.update(TABLE_NAME, param,
				new String[] { "$task_name|id" });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.TaskDAO#insert(java.util.List)
	 */
	public int insert(List param) {
		if (UtilFunc.isEmpty(param)) {
			return 0;
		}
		return DAOHelper.insert(TABLE_NAME, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.TaskDAO#queryLog(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	public List queryLog(String beginDate, String endDate, Map param) {
		ArrayList params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select t.*");
		sql.append("   from sys_task_log t");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql.append("  where task_date between date_format(?, '%Y-%m-%d %H:%i:%s') and date_format(?, '%Y-%m-%d %H:%i:%s') ");
		}else {
			sql.append("  where task_date between to_date(?, 'YYYY-MM-DD HH24:MI:SS') and to_date(?, 'YYYY-MM-DD HH24:MI:SS') ");
		}

		params.add(beginDate);
		params.add(endDate);

		SqlHelper.addStringValues(param.get("task_state"), "task_state", sql,
				params);

		if (UtilFunc.isNotBlank(param.get("task_nameordesc"))) {
			SqlHelper.addStringExp(
					UtilFunc.getString(param, "task_nameordesc"),
					"(task_name || task_desc)", sql, params);
		}

		String[] cols = new String[] { "task_id", "task_name", "task_desc",
				"instance_name",
				"task_param", "task_result", "task_remark" };
		for (String col : cols) {
			SqlHelper.addStringExp(UtilFunc.getString(param, col),
					col, sql, params);
		}

		sql.append("  order by task_date desc ");

		return DBHelper.executeList(sql.toString(),
				UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.TaskDAO#keys()
	 */
	public Set<String> keys() {
		StringBuilder sql = new StringBuilder();

		sql.append(" select task_name");
		sql.append("   from sys_tasks");

		Set<String> set = new HashSet<String>();
		List<Object[]> lst = DBHelper.executeArrayList(sql.toString());
		for (Object[] obj : lst) {
			set.add(obj[0].toString());
		}

		return set;
	}
}