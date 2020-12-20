/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * The Class ManagerUtil.
 */
public abstract class ManagerUtil {
	
	/**
	 * Gets the log path.
	 *
	 * @return the log path
	 */
	public static String getLogPath() {
		String logPath = (String) GlobalContext.getContext("log_path", null);
		if (!UtilFunc.hasText(logPath)) {
			logPath = GlobalContext.getPhysicalPath("WEB-INF/logs");
		} else {
			logPath = logPath + "logs" + UtilFunc.FILE_SEPARATOR;
		}

		return logPath;
	}

	/**
	 * Clear server log.
	 * 
	 * @param fileName
	 *            the file name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void clearServerLog(String fileName) throws IOException {
		if (!UtilFunc.fileExists(fileName)) {
			throw new HygeiaException("日志文件(" + fileName + ")不存在");
		}

		File f = new File(fileName);
		FileOutputStream out = new FileOutputStream(f);
		out.close();
	}

	/**
	 * Gets the log level.
	 * 
	 * @param str
	 *            the str
	 * @return the log level
	 */
	public static int getLogLevel(String level) {
		if(level != null){
			String str = level.trim();
			if ("TRACE".equals(level)) {
				return 1;
			} else if ("DEBUG".equals(str)) {
				return 2;
			} else if ("INFO".equals(str)) {
				return 3;
			} else if ("WARN".equals(str)) {
				return 4;
			} else if ("ERROR".equals(str)) {
				return 5;
			} else if ("FATAL".equals(str)) {
				return 6;
			}
		}
		
		return 0;
	}

	/**
	 * 创建表达式条件.
	 * 
	 * @param cndExp
	 *            表达式
	 * @param colName
	 *            列名
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            SQL参数
	 */
	public static void buildExpCondition(String cndExp, String colName,
			StringBuilder sql, List params) {
		if (!UtilFunc.hasText(cndExp) || !UtilFunc.hasText(colName)) {
			return;
		}

		// 双引号开头结尾=
		// 空格表示AND
		// 竖线表示OR
		// 减号表示NOT
		String[] keys = UtilFunc.splitSqlParam(cndExp);
		String value = null;
		if (keys != null && keys.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < keys.length; i++) {
				if (i > 0) {
					sql.append(" and ");
				}

				value = keys[i];
				if(value.indexOf("|") > 0){
					String[] childs = UtilFunc.split(value, "|");
					sql.append("(");
					for(int j = 0; j < childs.length; j ++){
						if(j > 0){
							sql.append(" or ");
						}
						buildChildCondition(childs[j], colName, sql, params);
					}
					sql.append(")");
				} else {
					buildChildCondition(value, colName, sql, params);
				}
			}
			sql.append(") ");
		}
	}
	
	
	/**
	 * 创建子条件.
	 * 
	 * @param cndExp
	 *            表达式
	 * @param colName
	 *            列名
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            SQL参数
	 */
	private static void buildChildCondition(String cndExp, String colName, StringBuilder sql, List params){
		boolean isNot = false;
		String value = null;
		if(cndExp.startsWith("-")){
			isNot = true;
			value = cndExp.substring(1);
		} else{
			value = cndExp;
		}
		
		if (value.length() >= 2 && value.startsWith("\"")
				&& value.endsWith("\"")) {
			sql.append(colName);
			if(value.equals("\"\"")){
				if(isNot){
					sql.append(" is not null ");
				} else {
					sql.append(" is null ");
				}
			} else {
				if(isNot){
					sql.append(" <> ? ");
				} else {
					sql.append(" = ? ");
				}
				params.add(value.substring(1, value.length() - 1));
			}
		} else {
			sql.append(" instr(").append(colName).append(", ?)");
			if(isNot){
				sql.append(" = 0 ");
			} else {
				sql.append(" > 0 ");
			}
			params.add(value);
		}
	}
}
