/*
 * Copyright 2013 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.sys.entity.SysMonitorAccess;

/**
 * The Class AccessMonitorUtil.
 */
public class AccessMonitorUtil {
	/** The batch size. */
	private static int BATCH_SIZE = 1000;

	/** The table name. */
	private static String TABLE_NAME = "SYS_MONITOR_ACCESS";

	/**
	 * Parses the monitor file.
	 * 
	 * @param batchNo
	 *            the batch no
	 * @param filePath
	 *            the file path
	 * @return the list
	 */
	private static long parseMonitorFile(String filePath) {
		try {
			String str = null;
			List<SysMonitorAccess> lst = new ArrayList();

			long ret = 0;
			Reader reader = new FileReader(filePath);
			BufferedReader br = new BufferedReader(reader);

			while ((str = br.readLine()) != null) {
				if (str == null || str.length() == 0)
					continue;

				SysMonitorAccess access = parseMonitorString(str);
				if (access != null) {
					lst.add(access);
					ret++;

					if (lst.size() % BATCH_SIZE == 0) {
						DAOHelper.insert(TABLE_NAME, lst);

						DBHelper.commit();
						lst.clear();
					}
				}
			}

			if (lst.size() > 0) {
				DAOHelper.insert(TABLE_NAME, lst);

				DBHelper.commit();
				lst.clear();
			}

			reader.close();
			br.close();

			return ret;
		} catch (IOException ex) {
			throw new HygeiaException(ex);
		}
	}

	/**
	 * Parses the monitor string.
	 * 
	 * @param batchNo
	 *            the batch no
	 * @param log
	 *            the log
	 * @return the sys monitor access
	 */
	private static SysMonitorAccess parseMonitorString(String log)
	{
		if (UtilFunc.isEmpty(log)) {
			return null;
		}

		int endPos = log.indexOf("$$");
		if (endPos < 1) {
			return null;
		}

		String str = log.substring(0, endPos).trim();
		if (!str.startsWith("[")) {
			return null;
		}

		SysMonitorAccess access = new SysMonitorAccess();

		int r_index = str.indexOf("]");
		String req_time = str.substring(1, r_index);
		access.setReqTimeDetail(req_time);
		access.setReqTime(DateFunc.toDatetime(req_time,
				"yyyy-MM-dd HH:mm:ss,SSS").getTime());
		str = str.substring(r_index + 6);
		String[] arr = str.split(" ");
		int len = arr.length;
		if (len > 0) {
			access.setReqFlag(arr[0]);
		}
		if (len > 1) {
			access.setReqNo(arr[1]);
		}
		if (len > 2) {
			access.setReqUser(arr[2]);
		}
		if (len > 3) {
			access.setReqType(arr[3]);
		}
		if (len > 4) {
			access.setReqUrl(arr[4]);
		}
		if (len > 5) {
			access.setReqJvmTotal(Long.parseLong(arr[5]));
		}
		if (len > 6) {
			access.setReqJvmCurrent(Long.parseLong(arr[6]));
		}
		if (len > 7) {
			access.setReqSucFlag("success".equals(arr[7]) ? "S" : "F");
		}
		if (len > 8) {
			access.setrReqStatus(arr[8]);
		}
		if (len > 9) {
			access.setReqSize(Long.parseLong(arr[9]));
		}

		return access;
	}

	/**
	 * 将log日志文件，转成信息列表.
	 * 
	 * @param attachs
	 *            the attachs
	 * @return the string
	 */
	public static long makeLogList4File(List<String> files) {
		long ret = 0;
		if (files != null) {
			// 暂时只保留一个文件的日志，因为暂时没有处理重复请求
			clearMonitorLog();

			for (int i = 0; i < files.size(); i++) {
				ret += parseMonitorFile(files.get(i));
			}
		}

		return ret;
	}

	// 获取运行时间一览表
	/**
	 * Gets the run perform.
	 * 
	 * @param batchNo
	 *            the batch no
	 * @return the run perform
	 */
	public static List getRunPerform(Map map) {
		List params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append(" select t.*, ");
		sql.append("        (t.end_date - t.begin_date) as run_time, ");
		sql.append("        (t.end_jvm - t.begin_jvm) as run_jvm ");
		sql.append("   from (select req_no, ");
		sql.append("                req_type, ");
		sql.append("                req_url, ");
		sql.append("                max(begin_time) as begin_time, ");
		sql.append("                max(end_time) as end_time, ");
		sql.append("                sum(begin_date) as begin_date, ");
		sql.append("                sum(end_date) as end_date, ");
		sql.append("                sum(begin_jvm) as begin_jvm, ");
		sql.append("                sum(end_jvm) as end_jvm, ");
		sql.append("                sum(begin_used) as begin_used, ");
		sql.append("                sum(end_used) as end_used ");
		sql.append("           from (select req_no, ");
		sql.append("                        req_type, ");
		sql.append("                        req_url, ");
		sql.append("                        decode(REQ_FLAG, 'B', req_time_detail, null) as begin_time, ");
		sql.append("                        decode(REQ_FLAG, 'E', req_time_detail, null) as end_time, ");
		sql.append("                        decode(REQ_FLAG, 'B', req_time, 0) as begin_date, ");
		sql.append("                        decode(REQ_FLAG, 'E', req_time, 0) as end_date, ");
		sql.append("                        decode(REQ_FLAG, 'B', req_jvm_current, 0) as begin_jvm, ");
		sql.append("                        decode(REQ_FLAG, 'E', req_jvm_current, 0) as end_jvm, ");
		sql.append("                        decode(REQ_FLAG, ");
		sql.append("                               'B', ");
		sql.append("                               (1 - (req_jvm_current / req_jvm_total)), ");
		sql.append("                               0) as begin_used, ");
		sql.append("                        decode(REQ_FLAG, ");
		sql.append("                               'E', ");
		sql.append("                               1 - (req_jvm_current / req_jvm_total), ");
		sql.append("                               0) as end_used ");
		sql.append("                   from sys_monitor_access where 1 = 1");
		addSqlParams(map, sql, params);
		sql.append(")");

		sql.append("          group by req_no, req_type, req_url) t ");
		sql.append("  where t.begin_date > 0 ");
		sql.append("    and t.end_date > 0 ");

		addSqlDate(map, sql, params);

		sql.append("    order by run_time desc");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/**
	 * Gets the jvm perform.
	 * 
	 * @param batchNo
	 *            the batch no
	 * @return the jvm perform
	 */
	public static List getJvmPerform(Map map) {
		List params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append(" select t.*, ");
		sql.append("        (t.end_date - t.begin_date) as run_time, ");
		sql.append("        (t.begin_jvm - t.end_jvm) as run_jvm ");
		sql.append("   from (select req_no, ");
		sql.append("                req_type, ");
		sql.append("                req_url, ");
		sql.append("                max(begin_time) as begin_time, ");
		sql.append("                max(end_time) as end_time, ");
		sql.append("                sum(begin_date) as begin_date, ");
		sql.append("                sum(end_date) as end_date, ");
		sql.append("                sum(begin_jvm) as begin_jvm, ");
		sql.append("                sum(end_jvm) as end_jvm, ");
		sql.append("                sum(begin_used) as begin_used, ");
		sql.append("                sum(end_used) as end_used ");
		sql.append("           from (select req_no, ");
		sql.append("                        req_type, ");
		sql.append("                        req_url, ");
		sql.append("                        decode(REQ_FLAG, 'B', req_time_detail, null) as begin_time, ");
		sql.append("                        decode(REQ_FLAG, 'E', req_time_detail, null) as end_time, ");
		sql.append("                        decode(REQ_FLAG, 'B', req_time, 0) as begin_date, ");
		sql.append("                        decode(REQ_FLAG, 'E', req_time, 0) as end_date, ");
		sql.append("                        decode(REQ_FLAG, 'B', req_jvm_current, 0) as begin_jvm, ");
		sql.append("                        decode(REQ_FLAG, 'E', req_jvm_current, 0) as end_jvm, ");
		sql.append("                        decode(REQ_FLAG, ");
		sql.append("                               'B', ");
		sql.append("                               (1 - (req_jvm_current / req_jvm_total)), ");
		sql.append("                               0) as begin_used, ");
		sql.append("                        decode(REQ_FLAG, ");
		sql.append("                               'E', ");
		sql.append("                               (1 - (req_jvm_current / req_jvm_total)), ");
		sql.append("                               0) as end_used ");
		sql.append("                   from sys_monitor_access where 1 = 1");

		addSqlParams(map, sql, params);

		sql.append(")");
		sql.append("          group by req_no, req_type, req_url) t ");
		sql.append("  where t.begin_date > 0 ");
		sql.append("    and t.end_date > 0 ");

		addSqlDate(map, sql, params);

		sql.append("    order by begin_date desc");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/**
	 * Gets the run access.
	 * 
	 * @param batchNo
	 *            the batch no
	 * @return the run access
	 */
	public static List getRunAccess(Map map) {
		List params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append(" select substr(t.req_time_detail, 0, 16) req_time_mi, ");
		sql.append("        count(distinct t.req_no) as req_count, ");
		sql.append("        max(t.req_jvm_total) as jvm_total, ");
		sql.append("        max(t.req_jvm_current) as max_free, ");
		sql.append("        min(t.req_jvm_current) as min_free, ");
		sql.append("        max((1 - (req_jvm_current / req_jvm_total))) as max_used, ");
		sql.append("        min((1 - (req_jvm_current / req_jvm_total))) as min_used ");
		sql.append("   from (select * ");
		sql.append("           from (select req_no, ");
		sql.append("                        req_type, ");
		sql.append("                        req_url, ");
		sql.append("                        req_time_detail, ");
		sql.append("                        req_jvm_total, ");
		sql.append("                        req_jvm_current, ");
		sql.append("                        req_time as begin_date ");
		sql.append("                   from sys_monitor_access) ");
		sql.append("          where 1 = 1 ");
		addSqlDate(map, sql, params);
		addSqlParams(map, sql, params);
		sql.append("          ) t ");
		sql.append("  group by substr(t.req_time_detail, 0, 16) ");
		sql.append("  order by substr(t.req_time_detail, 0, 16) desc ");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/**
	 * Gets the func access.
	 * 
	 * @param batchNo
	 *            the batch no
	 * @return the func access
	 */
	public static List getFuncAccess(Map map) {
		List params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append(" select req_type, ");
		sql.append("        req_url, ");
		sql.append("        count(distinct req_no) as req_num, ");
		sql.append("        max(end_date - begin_date) as req_time_max, ");
		sql.append("        min(end_date - begin_date) as req_time_min, ");
		sql.append("        round(avg(end_date - begin_date)) as req_time_avg ");
		sql.append("   from (select req_no, ");
		sql.append("                req_type, ");
		sql.append("                req_url, ");
		sql.append("                max(begin_date) as begin_date, ");
		sql.append("                max(end_date) as end_date ");
		sql.append("           from (select req_no, ");
		sql.append("                        req_type, ");
		sql.append("                        req_url, ");
		sql.append("                        decode(REQ_FLAG, 'B', req_time, 0) as begin_date, ");
		sql.append("                        decode(REQ_FLAG, 'E', req_time, 0) as end_date ");
		sql.append("                   from sys_monitor_access where 1 = 1");

		addSqlParams(map, sql, params);

		sql.append(")");
		sql.append("          group by req_no, req_type, req_url) t");
		sql.append("  where t.begin_date > 0 ");
		sql.append("    and t.end_date > 0 ");

		addSqlDate(map, sql, params);

		sql.append("  group by req_url, req_type ");
		sql.append("  order by req_num desc ");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	private static void addSqlDate(Map map, StringBuilder sql,
			List params) {
		String beginDate = UtilFunc.getString(map, "begin_date");
		if (UtilFunc.hasLength(beginDate)) {
			sql.append(" and begin_date >= ?");
			params.add(DateFunc.toDate(beginDate, DateFunc.DATETIME_FORMAT)
					.getTime());
		}

		String endDate = UtilFunc.getString(map, "end_date");
		if (UtilFunc.hasLength(endDate)) {
			sql.append(" and begin_date <= ?");
			params.add(DateFunc.toDate(endDate, DateFunc.DATETIME_FORMAT)
					.getTime());
		}
	}

	private static void addSqlParams(Map map, StringBuilder sql, List params) {
		SqlHelper.addStringValues(map.get("req_type"), "req_type", sql, params);
		
		String[] cnds = new String[] { "req_url" };
		for (String cnd : cnds) {
			SqlHelper.addStringExp(UtilFunc.getString(map, cnd), cnd,
					sql, params);
		}
	}

	/**
	 * Clear monitor log.
	 */
	public static void clearMonitorLog() {
		DBHelper.update("truncate table sys_monitor_access");
	}
}
