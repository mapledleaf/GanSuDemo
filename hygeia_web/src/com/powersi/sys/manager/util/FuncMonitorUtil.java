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
import com.powersi.hygeia.framework.util.XMLFunc;
import com.powersi.sys.entity.SysMonitorFunc;

/**
 * The Class ActionMonitorUtil.
 */
public class FuncMonitorUtil {

	/** The batch size. */
	private static int BATCH_SIZE = 1000;

	/** The buffer size. */
	private static int BUFFER_SIZE = 5 * 1024 * 1024;

	/** The table name. */
	private static String TABLE_NAME = "SYS_MONITOR_FUNC";

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
			List<SysMonitorFunc> lst = new ArrayList<SysMonitorFunc>();

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
			String userinfo = "";
			SysMonitorFunc smf = null;
			while ((line = reader.readLine()) != null) {
				String prefix = UtilFunc.left(line, 5);
				int level = LogHelper.parseLogLevel(prefix);
				if (level > 0) {
					if (smf != null) {
						if(skip == null || skip.compareTo(smf.getFuncDate().getTime()) < 0){
							lst.add(smf);
							n++;
							
							// 每隔10000行提交一次数据库
							if (lst.size() % BATCH_SIZE == 0) {
								DAOHelper.insert(TABLE_NAME, lst);
								DBHelper.commit();

								lst.clear();
							}
						}
						
						smf = null;
					}
					
					userinfo = reader.readLine();
					if (userinfo != null && userinfo.startsWith("userinfo:")
							&& line.length() >= 30) {
						smf = new SysMonitorFunc();
						smf.setFuncNo(UtilFunc.getUUID());
						smf.setLogLevel(level);

						long logTime = DateFunc.toDatetime(
								line.substring(7, 30),
								"yyyy-MM-dd HH:mm:ss,SSS").getTime();
						smf.setFuncDate(new java.util.Date(logTime));
						int pos = line.indexOf(":", 31);
						if (pos > 0) {
							smf.setFuncId(line.substring(pos + 1).trim());
						} else if (line.length() > 32) {
							smf.setFuncId(line.substring(32));
						}

						smf.setFuncTotaltime(Integer.valueOf(0));

						// 处理参数
						StringBuilder param = new StringBuilder();
						if ((line = reader.readLine()) != null
								&& line.startsWith("request  : ")) {
							param.append(line.substring(11));
							while ((line = reader.readLine()) != null
									&& !line.startsWith("transform:")
									&& !line.startsWith("response :")) {
								param.append(line);
							}
						}
						smf.setFuncParam(param.toString());
						smf.setParamLength(smf.getFuncParam().length());

						if ("0".equals(smf.getFuncId()) || "SysLogin".equals(smf.getFuncId())) {
							String s = smf.getFuncParam();
							pos = s.indexOf("<login_password>");
							if (pos > 0) {
								StringBuilder sb = new StringBuilder();
								sb.append(s.substring(0, pos));
								pos = s.indexOf("</login_password>", pos + 1);
								if (pos > 0) {
									sb.append(s.substring(pos
											+ "</login_password>".length() + 1));
								}
								smf.setFuncParam(sb.toString());
							}
						}

						// 处理结果
						StringBuilder result = new StringBuilder();
						if (line != null) {
							if (!line.startsWith("response :")) {
								while ((line = reader.readLine()) != null
										&& !line.startsWith("response :")) {
								}
							}
							
							if (line != null) {
								result.append(line.substring(11));
								if (line.lastIndexOf("</Program>") < 0) {
									while ((line = reader.readLine()) != null
											&& line.lastIndexOf("</Program>") < 0) {
										result.append(line);
									}
									if (line != null) {
										result.append(line);
									}
								}
							}
						}
						smf.setFuncResult(result.toString());
						smf.setResultLength(smf.getFuncResult().length());

						// 处理功能号
						if (!UtilFunc.hasLength(smf.getFuncId())) {
							smf.setFuncId(XMLFunc.getXMLElementValue(
									smf.getFuncParam(), "FunctionID"));
						}
					}
				}
			}

			if (smf != null) {
				if(skip == null || skip.compareTo(smf.getFuncDate().getTime()) < 0){
					lst.add(smf);
					n++;
				}
				
				smf = null;
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

		sql.append(" select /*+parallel(t 2)*/");
		sql.append("        func_no, ");
		sql.append("        func_date, ");
		sql.append("        log_level, ");
		sql.append("        func_totaltime, ");
		sql.append("        func_id, ");
		sql.append("        func_param, ");
		sql.append("        func_result, ");
		sql.append("        param_length, ");
		sql.append("        result_length ");
		sql.append("   from sys_monitor_func t ");
		sql.append("  where 1 = 1 ");
		addSqlParams(map, sql, params);
		sql.append(" order by func_date");

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
		sql.append("         func_id, ");
		sql.append("        count(distinct func_no) as func_num, ");
		sql.append("        sum(param_length) as param_length_total, ");
		sql.append("        max(param_length) as param_length_max, ");
		sql.append("        min(param_length) as param_length_min, ");
		sql.append("        round(avg(param_length)) as param_length_avg, ");
		sql.append("        sum(result_length) as result_length_total, ");
		sql.append("        max(result_length) as result_length_max, ");
		sql.append("        min(result_length) as result_length_min, ");
		sql.append("        round(avg(result_length)) as result_length_avg, ");
		sql.append("        min(func_date) as func_begin_date, ");
		sql.append("        max(func_date) as func_end_date ");
		sql.append("   from (select func_no, func_date, func_id, param_length, result_length ");
		sql.append("           from sys_monitor_func ");
		sql.append("          where 1 = 1 ");
		addSqlParams(map, sql, params);
		sql.append("          ) t ");
		sql.append("  group by func_id ");
		sql.append("  order by func_num desc ");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/**
	 * Gets the date list.
	 * 
	 * @param map
	 *            the map
	 * @return the date list
	 */
	public static List getDateList(Map map) {
		List params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select /*+parallel(t 2)*/");
		sql.append("        func_date, ");
		sql.append("        count(distinct func_no) as func_num, ");
		sql.append("        sum(param_length) as param_length_total, ");
		sql.append("        max(param_length) as param_length_max, ");
		sql.append("        min(param_length) as param_length_min, ");
		sql.append("        round(avg(param_length)) as param_length_avg, ");
		sql.append("        sum(result_length) as result_length_total, ");
		sql.append("        max(result_length) as result_length_max, ");
		sql.append("        min(result_length) as result_length_min, ");
		sql.append("        round(avg(result_length)) as result_length_avg, ");
		sql.append("        min(func_date) as func_begin_date, ");
		sql.append("        max(func_date) as func_end_date ");
		sql.append("   from (select func_no, ");
		sql.append("                to_char(func_date, 'yyyy-mm-dd hh24:mi') as func_date, ");
		sql.append("                param_length, ");
		sql.append("                result_length ");
		sql.append("           from sys_monitor_func ");
		sql.append("          where 1 = 1 ");
		addSqlParams(map, sql, params);
		sql.append("          ) t ");
		sql.append("  group by func_date ");
		sql.append("  order by func_date ");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/**
	 * Builds the sql param.
	 * 
	 * @param map
	 *            the map
	 * @param sql
	 *            the sql
	 * @param params
	 *            the params
	 */
	private static void addSqlParams(Map map, StringBuilder sql, List params) {
		SqlHelper.addDatetime(UtilFunc.getString(map, "begin_date"), "func_date", ">=", sql, params);
		SqlHelper.addDatetime(UtilFunc.getString(map, "end_date"), "func_date", "<=", sql, params);

		SqlHelper.addIntValues(map.get("log_level"), "log_level", sql, params);
		
		SqlHelper.addInt(UtilFunc.getString(map, "totaltime_min"), "func_totaltime", ">=", sql, params);
		SqlHelper.addInt(UtilFunc.getString(map, "totaltime_max"), "func_totaltime", "<=", sql, params);
		
		SqlHelper.addInt(UtilFunc.getString(map, "param_length_min"), "param_length", ">=", sql, params);
		SqlHelper.addInt(UtilFunc.getString(map, "param_length_max"), "param_length", "<=", sql, params);
		
		
		SqlHelper.addInt(UtilFunc.getString(map, "result_length_min"), "result_length", ">=", sql, params);
		SqlHelper.addInt(UtilFunc.getString(map, "result_length_max"), "result_length", "<=", sql, params);
		
		String[] cnds = new String[] { "func_id", "func_param",
				"func_result" };
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

			String filepath = "C:\\Users\\lei\\Desktop\\processall.log";
			File file = new File(filepath);

			FuncMonitorUtil.importLogFile(file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
