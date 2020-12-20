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
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.sys.entity.SysMonitorAction;

/**
 * The Class ActionMonitorUtil.
 */
public class ActionMonitorUtil {

	/** The batch size. */
	private static int BATCH_SIZE = 1000;

	/** The buffer size. */
	private static int BUFFER_SIZE = 5 * 1024 * 1024;

	/** The table name. */
	private static String TABLE_NAME = "SYS_MONITOR_ACTION";

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
			List<SysMonitorAction> lst = new ArrayList<SysMonitorAction>();

			BufferedInputStream fis = new BufferedInputStream(
					new FileInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					fis, "GBK"), BUFFER_SIZE);

			Long skip = null;
			if(UtilFunc.hasText(skipDate)){
				skip = Long.valueOf(DateFunc.parseDatetime(skipDate.trim().substring(0, 19)).getTime());
				if(skipDate.trim().length() == 23){
					skip += Long.parseLong(skipDate.trim().substring(20));
				}
			}
			
			String line = "";
			String exec = "";
			String action = "";
			SysMonitorAction sma = null;
			while ((line = reader.readLine()) != null) {
				String prefix = UtilFunc.left(line, 5);
				int level = LogHelper.parseLogLevel(prefix);
				if (level > 0) {
					if (sma != null) {
						if(skip == null || skip.compareTo(sma.getActionDate().getTime() + sma.getActionTotaltime().longValue()) < 0){
							lst.add(sma);
							n++;
							
							// 每隔10000行提交一次数据库
							if (lst.size() % BATCH_SIZE == 0) {
								DAOHelper.insert(TABLE_NAME, lst);
								DBHelper.commit();

								lst.clear();
							}
						}
						
						sma = null;
					}

					action = reader.readLine();
					if (action != null && action.startsWith("action: ")
							&& line.length() >= 30 && action.length() >= 8) {
						sma = new SysMonitorAction();
						sma.setActionNo(UtilFunc.getUUID());
						sma.setLogLevel(level);
						long logTime = DateFunc.toDatetime(
								line.substring(7, 30),
								"yyyy-MM-dd HH:mm:ss,SSS").getTime();
						sma.setActionUrl(UtilFunc.truncate(action.substring(8),
								4000));
						sma.setActionTotaltime(0);
						StringBuilder param = new StringBuilder();
						if ((line = reader.readLine()) != null) {
							param.append(line.substring(8));
							while ((line = reader.readLine()) != null
									&& !line.startsWith("result: ")) {
								param.append(line);
							}
						}
						sma.setActionParam(param.toString());

						if (sma.getActionUrl().startsWith("/login/")) {
							String s = sma.getActionParam();
							if (s.indexOf("password") >= 0) {
								Map m = JsonHelper.toMap(s);
								m.remove("pwd");
								m.remove("password");
								sma.setActionParam(JsonHelper.toJson(m));
							}
						} else if (sma.getActionUrl().equals(
								"/user/ChangePassword")) {
							sma.setActionParam("{}");
						}

						if (line != null) {
							sma.setActionResult(line.substring(8));
						}
						if ((line = reader.readLine()) != null
								&& line.startsWith("exec  : ")) {
							exec = line.substring(8);
							int pos = exec.indexOf(" at ");
							if (pos > 0) {
								sma.setActionCaller(exec.substring(pos + 4)
										.trim());

								sma.setActionTotaltime(UtilFunc
										.stringToInteger(exec.substring(0,
												exec.indexOf("ms"))));
							} else {
								sma.setActionCaller("unknow");
							}
						}
						if ((line = reader.readLine()) != null
								&& line.startsWith("view  : ")) {
							sma.setActionView(line.substring(8));
						}

						// action时间等于日志时间减去运行时间
						sma.setActionDate(new java.util.Date(logTime
								- sma.getActionTotaltime().longValue()));
					}
				}
			}

			if (sma != null) {
				if(skip == null || skip.compareTo(sma.getActionDate().getTime() + sma.getActionTotaltime().longValue()) < 0){
					lst.add(sma);
					n ++;
				}
				
				sma = null;
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

		sql.append(" select /*+parallel(t 2)*/action_no, ");
		sql.append("        action_date, ");
		sql.append("        log_level, ");
		sql.append("        action_totaltime, ");
		sql.append("        action_url, ");
		sql.append("        action_param, ");
		sql.append("        action_result, ");
		sql.append("        action_view, ");
		sql.append("        action_caller ");
		sql.append("   from sys_monitor_action t ");
		sql.append("  where 1 = 1 ");

		addSqlParams(map, sql, params);

		sql.append(" order by action_date");

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
		sql.append("        action_caller, ");
		sql.append("        count(distinct action_no) as action_num, ");
		sql.append("        sum(action_totaltime) as action_totaltime_total, ");
		sql.append("        max(action_totaltime) as action_totaltime_max, ");
		sql.append("        min(action_totaltime) as action_totaltime_min, ");
		sql.append("        round(avg(action_totaltime)) as action_totaltime_avg, ");
		sql.append("        min(action_date) as action_begin_date, ");
		sql.append("        max(action_date) as action_end_date ");
		sql.append("   from (select action_no, action_date, action_caller, action_totaltime ");
		sql.append("           from sys_monitor_action ");
		sql.append("          where 1 = 1 ");
		addSqlParams(map, sql, params);
		sql.append("          ) t ");
		sql.append("  group by action_caller ");
		sql.append("  order by action_num desc ");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	public static List getDateList(Map map) {
		List params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select /*+parallel(t 2)*/");
		sql.append("        action_date, ");
		sql.append("        count(distinct action_no) as action_num, ");
		sql.append("        sum(action_totaltime) as action_totaltime_total, ");
		sql.append("        max(action_totaltime) as action_totaltime_max, ");
		sql.append("        min(action_totaltime) as action_totaltime_min, ");
		sql.append("        round(avg(action_totaltime)) as action_totaltime_avg ");
		sql.append("   from (select action_no, ");
		sql.append("                to_char(action_date, 'yyyy-mm-dd hh24:mi') as action_date, ");
		sql.append("                action_totaltime ");
		sql.append("           from sys_monitor_action ");
		sql.append("          where 1 = 1 ");
		addSqlParams(map, sql, params);
		sql.append("          ) t ");
		sql.append("  group by action_date ");
		sql.append("  order by action_date ");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/**
	 * Adds the sql params.
	 *
	 * @param map the map
	 * @param sql the sql
	 * @param params the params
	 */
	private static void addSqlParams(Map map, StringBuilder sql, List params) {
		SqlHelper.addDatetime(UtilFunc.getString(map, "begin_date"), "action_date", ">=", sql, params);
		SqlHelper.addDatetime(UtilFunc.getString(map, "end_date"), "action_date", "<=", sql, params);
		
		SqlHelper.addIntValues(map.get("log_level"), "log_level", sql, params);
	
		SqlHelper.addInt(UtilFunc.getString(map, "totaltime_min"), "action_totaltime", ">=", sql, params);
		SqlHelper.addInt(UtilFunc.getString(map, "totaltime_max"), "action_totaltime", "<=", sql, params);
		
		String[] cnds = new String[] { "action_url", "action_param",
				"action_result", "action_view", "action_caller" };
		for (String cnd : cnds) {
			SqlHelper.addStringExp(UtilFunc.getString(map, cnd), cnd,
					sql, params);
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

			String filepath = "C:\\Users\\lei\\Desktop\\baseaction.log";
			File file = new File(filepath);

			ActionMonitorUtil.importLogFile(file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
