/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;

import com.mongodb.client.MongoCollection;
import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.mongodb.MongoFilter;
import com.powersi.hygeia.framework.mongodb.MongoHelper;
import com.powersi.hygeia.framework.mongodb.MongoQuery;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.sys.entity.LogRecord;

/**
 * The Class SqlMonitorUtil.
 */
public class SqlMonitorUtil2 {

	/** The batch size. */
	private static int BATCH_SIZE = 1000;

	/** The buffer size. */
	private static int BUFFER_SIZE = 5 * 1024 * 1024;

	/** The table name. */
	private static String TABLE_NAME = "sqls";

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
			MongoCollection collection = MongoHelper.getCollection(TABLE_NAME);

			List<Document> lst = new ArrayList<Document>(BATCH_SIZE);

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
							
							// 每隔1000行提交一次数据库
							if (lst.size() % BATCH_SIZE == 0) {
								collection.insertMany(lst);
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
				collection.insertMany(lst);
				lst.clear();
			}

			MongoHelper.createIndexes(
							collection,
							"sql_date, log_level, sql_totaltime, sql_runtime, sql_fetchtime, sql_rowscount, sql_caller, time_point");
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
	private static Document parseLogRecord(LogRecord lr) {
		Document sms = new Document();
		
		//sms.put("sql_no", UtilFunc.getUUID());
		sms.put("log_level", lr.getLogLevel());

		sms.put("sql_text", lr.getLogMessage().toString());
		sms.put("sql_error", lr.getLogException().toString());

		sms.put("sql_runtime", Integer.valueOf(0));
		sms.put("sql_fetchtime", Integer.valueOf(0));
		sms.put("sql_rowsoucnt", Integer.valueOf(0));

		int pos = lr.getLogSummary().indexOf(" at ");
		if (pos > 0) {
			sms.put("sql_caller", lr.getLogSummary().substring(pos + 4).trim());

			String exec = lr.getLogSummary().substring(0, pos).trim();

			if (exec.indexOf(",") > 0) {
				String[] s = UtilFunc.trimArrayElements(UtilFunc.split(exec,
						","));

				if (s.length >= 3) {
					sms.put("sql_runtime",
							UtilFunc.stringToInteger(s[0].substring(
									0, s[0].indexOf("ms"))));
					sms.put("sql_fetchtime", UtilFunc.stringToInteger(s[1]
							.substring(6, s[1].indexOf("ms"))));
					sms.put("sql_rowscount", UtilFunc.stringToInteger(s[2]
							.substring(0, s[2].indexOf(" rows"))));
				}
			} else {
				sms.put("sql_runtime",
						UtilFunc.stringToInteger(exec.substring(0,
								exec.indexOf("ms"))));
			}
		} else {
			sms.put("sql_caller", "unknow");
		}

		Integer total = (Integer) sms.get("sql_fetchtime")
				+ (Integer) sms.get("sql_runtime");
		sms.put("sql_totaltime", Integer.valueOf(total));

		long logTime = DateFunc.toDatetime(lr.getLogDate(),
				"yyyy-MM-dd HH:mm:ss,SSS").getTime();

		// sql时间等于日志时间减去运行时间
		java.util.Date sqlDate = new java.util.Date(logTime - total.longValue());
		sms.put("sql_date", sqlDate);
		
		//计算具体发生时间点
		sms.put("time_point",
				DateFunc.dateToString(sqlDate, "yyyy-MM-dd HH:mm"));

		return sms;
	}

	/**
	 * Clear monitor log.
	 */
	public static void clearMonitorLog() {
		MongoHelper.getTemplate().dropCollection(TABLE_NAME);
		MongoHelper.getTemplate().createCollection(TABLE_NAME);
	}

	/**
	 * Gets the time list.
	 * 
	 * @param map
	 *            the map
	 * @return the time list
	 */
	public static List getTimeList(Map map) {
		MongoQuery query = new MongoQuery(getQueryFilter(map));
		query.setSort(MongoHelper.getSort("sql_date"));
		return MongoHelper.replaceId(
				MongoHelper.query(TABLE_NAME, query, HashMap.class),
				"sql_no");
	}

	/**
	 * Gets the caller list.
	 * 
	 * @param map
	 *            the map
	 * @return the caller list
	 */
	public static List getCallerList(Map map) {
		MongoFilter filter = getQueryFilter(map);
		List<AggregationOperation> ops = new ArrayList<AggregationOperation>();
		// match
		ops.add(MongoHelper.getMatchOperation(filter));
		// project
		ops.add(Aggregation.project("sql_caller", "sql_date", "sql_totaltime",
				"sql_runtime", "sql_fetchtime", "sql_rowscount"));
		// group
		ops.add(Aggregation.group("sql_caller").count().as("sql_num")
				.min("sql_date").as("sql_begin_date").max("sql_date")
				.as("sql_end_date")
				.sum("sql_totaltime").as("sql_runtime_total")
				.avg("sql_totaltime").as("sql_runtime_avg")
				.max("sql_totaltime").as("sql_runtime_max")
				.min("sql_totaltime").as("sql_totaltime_min")
				.sum("sql_runtime").as("sql_runtime_total").avg("sql_runtime")
				.as("sql_runtime_avg").max("sql_runtime").as("sql_runtime_max")
				.min("sql_runtime").as("sql_runtime_min")
				.sum("sql_fetchtime").as("sql_fetchtime_total")
				.avg("sql_fetchtime").as("sql_fetchtime_avg")
				.max("sql_fetchtime").as("sql_fetchtime_max")
				.min("sql_fetchtime").as("sql_fetchtime_min")
				.sum("sql_rowscount").as("sql_rowscount_total")
				.avg("sql_rowscount").as("sql_rowscount_avg")
				.max("sql_rowscount").as("sql_rowscount_max")
				.min("sql_rowscount").as("sql_rowscount_min"));
		// sort
		ops.add(MongoHelper.getSortOperation("sql_num desc"));

		// aggregate获取记录数性能很差，使用distinct提升性能（只支持单key）
		return MongoHelper.replaceId(
					MongoHelper.aggregateWithDistinct(TABLE_NAME, ops, HashMap.class, "sql_caller", filter),
					"sql_caller");
	}

	/**
	 * Gets the date list.
	 *
	 * @param map the map
	 * @return the date list
	 */
	public static List getDateList(Map map) {
		MongoFilter filter = getQueryFilter(map);
		List<AggregationOperation> ops = new ArrayList<AggregationOperation>();
		// match
		ops.add(MongoHelper.getMatchOperation(filter));
		// project
		ops.add(Aggregation
				.project("time_point", "sql_totaltime", "sql_runtime",
						"sql_fetchtime", "sql_rowscount")/*
														 * .and("sql_date").
														 * extractYear
														 * ().as("year")
														 * .and("sql_date"
														 * ).extractMonth
														 * ().as("month")
														 * .and("sql_date"
														 * ).extractDayOfMonth
														 * ().as("dayOfMonth")
														 * .and
														 * ("sql_date").extractHour
														 * ().as("hour")
														 * .and("sql_date"
														 * ).extractMinute
														 * ().as("minute")
														 */);
		// group
		ops.add(Aggregation
				// .group("year", "month", "dayOfMonth", "hour",
				// "minute").count()
				.group("time_point").count()
				.as("sql_num")
				.sum("sql_totaltime").as("sql_runtime_total")
				.avg("sql_totaltime").as("sql_runtime_avg")
				.max("sql_totaltime").as("sql_runtime_max")
				.min("sql_totaltime").as("sql_totaltime_min")
				.sum("sql_runtime").as("sql_runtime_total").avg("sql_runtime")
				.as("sql_runtime_avg").max("sql_runtime").as("sql_runtime_max")
				.min("sql_runtime").as("sql_runtime_min")
				.sum("sql_fetchtime").as("sql_fetchtime_total")
				.avg("sql_fetchtime").as("sql_fetchtime_avg")
				.max("sql_fetchtime").as("sql_fetchtime_max")
				.min("sql_fetchtime").as("sql_fetchtime_min")
				.sum("sql_rowscount").as("sql_rowscount_total")
				.avg("sql_rowscount").as("sql_rowscount_avg")
				.max("sql_rowscount").as("sql_rowscount_max")
				.min("sql_rowscount").as("sql_rowscount_min"));
		// sort
		/*
		 * ops.add(MongoHelper
		 * .getSortOperation("year,month,dayOfMonth,hour,minute"));
		 */
		ops.add(MongoHelper.getSortOperation("_id"));

		// aggregate获取记录数性能很差，使用distinct提升性能（只支持单key）
		/*
		 * return MongoHelper.replaceDate( MongoHelper.aggregate(TABLE_NAME,
		 * ops, HashMap.class), "sql_date", "yyyy-MM-dd HH:mm");
		 */
		return MongoHelper.replaceId(
				MongoHelper.aggregateWithDistinct(TABLE_NAME, ops, HashMap.class, "time_point", filter),
				"sql_date");
	}

	/**
	 * Gets the query filter.
	 *
	 * @param map the map
	 * @return the query filter
	 */
	private static MongoFilter getQueryFilter(Map map) {
		MongoFilter filter = new MongoFilter();

		filter.betweenDatetime("sql_date",
				UtilFunc.getString(map, "begin_date"),
				UtilFunc.getString(map, "end_date"));

		filter.addIntValues("log_level", map.get("log_level"));

		String[] ranges = new String[] { "totaltime", "runtime", "fetchtime",
				"rowscount" };
		for (String range : ranges) {
			filter.betweenInt("sql_" + range,
					UtilFunc.getString(map, range + "_min"),
					UtilFunc.getString(map, range + "_max"));
		}

		String[] cnds = new String[] { "sql_text", "sql_error", "sql_caller" };
		for (String cnd : cnds) {
			filter.addStringExp(cnd, UtilFunc.getString(map, cnd));
		}

		return filter;
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
			for (int i = 0; i < 1; i++)
				SqlMonitorUtil2.importLogFile(file);
			System.out
					.println(new java.util.Date().getTime() - start.getTime());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			GlobalContext.shutdown();
		}
	}
}
