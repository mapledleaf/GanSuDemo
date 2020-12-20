/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.sys.entity.LogRecord;
import com.powersi.sys.entity.SysMonitorSql;

/**
 * The Class SqlMonitorUtil.
 */
public class SqlMonitorUtil {

	/** The batch size. */
	private static int BATCH_SIZE = 1000;

	/** The buffer size. */
	private static int BUFFER_SIZE = 5 * 1024 * 1024;

	/** The table name. */
	private static String TABLE_NAME = "SYS_MONITOR_SQL";

	/**
	 * Import log file.
	 *
	 * @param file the file
	 * @return the int
	 */
	public static int importLogFile(File file){
		return importLogFile(file, null);
	}
	
	/**
	 * 将log日志文件，转成信息列表.
	 *
	 * @param file the file
	 * @param skipDate the skip date
	 * @return the string
	 */
	public static int importLogFile(File file, String skipDate) {
		int n = 0;
		try {
			List<SysMonitorSql> lst = new ArrayList<SysMonitorSql>();

			BufferedInputStream fis = new BufferedInputStream(
					new FileInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					fis, "GBK"), BUFFER_SIZE);

			String skip = null;
			if(UtilFunc.hasText(skipDate)){
				skip = skipDate.trim();
			}
			
			String line = "";
			String exec = "";
			LogRecord jlr = null;
			while ((line = reader.readLine()) != null) {
				String prefix = UtilFunc.left(line, 5);
				int level = LogHelper.parseLogLevel(prefix);
				if (level > 0) {
					if (jlr != null) {
						if(skip == null || skip.compareTo(jlr.getLogDate()) < 0){
							lst.add(parseLogRecord(jlr));
							n++;
							
							// 每隔10000行提交一次数据库
							if (lst.size() % BATCH_SIZE == 0) {
								DAOHelper.insert(TABLE_NAME, lst);
								DBHelper.commit();

								lst.clear();
							}
						}
						
						jlr = null;
					}

					exec = reader.readLine();
					if (exec != null && exec.startsWith("exec :")
							&& line.length() >= 30 && exec.length() >= 7) {
						jlr = new LogRecord();
						jlr.setLogLevel(level);
						jlr.setLogDate(line.substring(7, 30));
						jlr.setLogSummary(exec.substring(7));
					}
				} else {
					if (jlr != null) {
						jlr.appendLine(line);
					}
				}
			}

			if (jlr != null) {
				if(skip == null || skip.compareTo(jlr.getLogDate()) < 0){
					lst.add(parseLogRecord(jlr));
					n ++;
				}
				jlr = null;
			}

			reader.close();
			fis.close();

			if (lst.size() > 0) {
				DAOHelper.insert(TABLE_NAME, lst);
				DBHelper.commit();

				lst.clear();
			}

			return n;
		} catch (Exception ex) {
			throw new HygeiaException(ex);
		}
	}

	/**
	 * Creates the sql bean.
	 * 
	 * @param lr
	 *            the lr
	 * @return the sys monitor sql
	 */
	private static SysMonitorSql parseLogRecord(LogRecord lr) {
		SysMonitorSql sms = new SysMonitorSql();
		sms.setSqlNo(UtilFunc.getUUID());
		sms.setLogLevel(lr.getLogLevel());

		sms.setSqlText(lr.getLogMessage().toString());
		sms.setSqlError(lr.getLogException().toString());

		sms.setSqlRuntime(0);
		sms.setSqlFetchtime(0);
		sms.setSqlRowscount(0);

		int pos = lr.getLogSummary().indexOf(" at ");
		if (pos > 0) {
			sms.setSqlCaller(lr.getLogSummary().substring(pos + 4).trim());

			String exec = lr.getLogSummary().substring(0, pos).trim();

			if (exec.indexOf(",") > 0) {
				String[] s = UtilFunc.trimArrayElements(UtilFunc.split(exec,
						","));

				if (s.length >= 3) {
					sms.setSqlRuntime(UtilFunc.stringToInteger(s[0].substring(
							0, s[0].indexOf("ms"))));
					sms.setSqlFetchtime(UtilFunc.stringToInteger(s[1]
							.substring(6, s[1].indexOf("ms"))));
					sms.setSqlRowscount(UtilFunc.stringToInteger(s[2]
							.substring(0, s[2].indexOf(" rows"))));
				}
			} else {
				sms.setSqlRuntime(UtilFunc.stringToInteger(exec.substring(0,
						exec.indexOf("ms"))));
			}
		} else {
			sms.setSqlCaller("unknow");
		}

		sms.setSqlTotaltime(sms.getSqlFetchtime() + sms.getSqlRuntime());

		
		long logTime = DateFunc.toDatetime(lr.getLogDate(),
				"yyyy-MM-dd HH:mm:ss,SSS").getTime();
		
		// sql时间等于日志时间减去运行时间
		sms.setSqlDate(new java.sql.Date(logTime - sms.getSqlTotaltime().longValue()));

		return sms;
	}

	/**
	 * Clear monitor log.
	 */
	public static void clearMonitorLog() {
		DBHelper.update("TRUNCATE TABLE " + TABLE_NAME);
	}

	/**
	 * Gets the time list.
	 * 
	 * @param map
	 *            the map
	 * @return the time list
	 */
	public static List getTimeList(Map map) {
		List params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select /*+parallel(t 2)*/sql_no, ");
		sql.append("        sql_date, ");
		sql.append("        log_level, ");
		sql.append("        sql_totaltime, ");
		sql.append("        sql_runtime, ");
		sql.append("        sql_fetchtime, ");
		sql.append("        sql_rowscount, ");
		sql.append("        sql_text, ");
		sql.append("        sql_error, ");
		sql.append("        sql_caller ");
		sql.append("   from sys_monitor_sql t");
		sql.append("  where 1 = 1 ");

		addSqlParams(map, sql, params);

		sql.append(" order by sql_date");

		return DBHelper.executeList(sql.toString(),
				UtilFunc.toArray(params));
	}

	/**
	 * Gets the caller list.
	 * 
	 * @param map
	 *            the map
	 * @return the caller list
	 */
	public static List getCallerList(Map map) {
		List params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select /*+parallel(t 2)*/");
		sql.append("        sql_caller, ");
		sql.append("        count(distinct sql_no) as sql_num, ");
		sql.append("        sum(sql_runtime) as sql_runtime_total, ");
		sql.append("        max(sql_runtime) as sql_runtime_max, ");
		sql.append("        min(sql_runtime) as sql_runtime_min, ");
		sql.append("        round(avg(sql_runtime)) as sql_runtime_avg, ");
		sql.append("        sum(sql_fetchtime) as sql_fetchtime_total, ");
		sql.append("        max(sql_fetchtime) as sql_fetchtime_max, ");
		sql.append("        min(sql_fetchtime) as sql_fetchtime_min, ");
		sql.append("        round(avg(sql_fetchtime)) as sql_fetchtime_avg, ");
		sql.append("        sum(sql_rowscount) as sql_rowscount_total, ");
		sql.append("        max(sql_rowscount) as sql_rowscount_max, ");
		sql.append("        min(sql_rowscount) as sql_rowscount_min, ");
		sql.append("        round(avg(sql_rowscount)) as sql_rowscount_avg, ");
		sql.append("        min(sql_date) as sql_begin_date, ");
		sql.append("        max(sql_date) as sql_end_date ");
		sql.append("   from ( ");
		sql.append("        select sql_no, ");
		sql.append("                sql_date, ");
		sql.append("                sql_caller, ");
		sql.append("                sql_runtime, ");
		sql.append("                sql_fetchtime, ");
		sql.append("                sql_rowscount ");
		sql.append("           from sys_monitor_sql ");
		sql.append("          where 1 = 1 ");
		addSqlParams(map, sql, params);
		sql.append("          ) t ");
		sql.append("  group by sql_caller ");
		sql.append("  order by sql_num desc ");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}
	
	public static List getDateList(Map map) {
		List params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select /*+parallel(t 2)*/");
		sql.append("        sql_date, ");
		sql.append("        count(distinct sql_no) as sql_num, ");
		sql.append("        sum(sql_runtime) as sql_runtime_total, ");
		sql.append("        max(sql_runtime) as sql_runtime_max, ");
		sql.append("        min(sql_runtime) as sql_runtime_min, ");
		sql.append("        round(avg(sql_runtime)) as sql_runtime_avg, ");
		sql.append("        sum(sql_fetchtime) as sql_fetchtime_total, ");
		sql.append("        max(sql_fetchtime) as sql_fetchtime_max, ");
		sql.append("        min(sql_fetchtime) as sql_fetchtime_min, ");
		sql.append("        round(avg(sql_fetchtime)) as sql_fetchtime_avg, ");
		sql.append("        sum(sql_rowscount) as sql_rowscount_total, ");
		sql.append("        max(sql_rowscount) as sql_rowscount_max, ");
		sql.append("        min(sql_rowscount) as sql_rowscount_min, ");
		sql.append("        round(avg(sql_rowscount)) as sql_rowscount_avg ");
		sql.append("   from (select sql_no, ");
		sql.append("                to_char(sql_date, 'yyyy-mm-dd hh24:mi') as sql_date, ");
		sql.append("                sql_runtime, ");
		sql.append("                sql_fetchtime, ");
		sql.append("                sql_rowscount ");
		sql.append("           from sys_monitor_sql ");
		sql.append("          where 1 = 1 ");
		addSqlParams(map, sql, params);
		sql.append("          ) t ");
		sql.append("  group by sql_date ");
		sql.append("  order by sql_date ");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	private static void addSqlParams(Map map, StringBuilder sql, List params) {
		SqlHelper.addDatetime(UtilFunc.getString(map, "begin_date"), "sql_date", ">=", sql, params);
		SqlHelper.addDatetime(UtilFunc.getString(map, "end_date"), "sql_date", "<=", sql, params);

		SqlHelper.addIntValues(map.get("log_level"), "log_level", sql, params);

		String[] ranges = new String[]{"totaltime", "runtime", "fetchtime", "rowscount"};
		for(String range : ranges){
			SqlHelper.addInt(UtilFunc.getString(map, range + "_min"), "sql_" + range, ">=", sql, params);
			SqlHelper.addInt(UtilFunc.getString(map, range + "_max"), "sql_" + range, "<=", sql, params);
		}
	
		String[] cnds = new String[]{"sql_text", "sql_error", "sql_caller"};
		for(String cnd : cnds){
			SqlHelper.addStringExp(UtilFunc.getString(map, cnd), cnd, sql, params);
		}
	}
	
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		try {
			GlobalContext.init();

			File file = new File("F:\\mydownload\\jdbclogger.log.1");

			java.util.Date start = new java.util.Date();
			for(int i = 0; i < 1; i ++)
				SqlMonitorUtil.importLogFile(file);
			System.out.println(new java.util.Date().getTime() - start.getTime());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
