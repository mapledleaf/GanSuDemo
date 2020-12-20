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
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.sys.entity.SysMonitorAction;

/**
 * The Class ActionMonitorUtil.
 */
public class ActionMonitorUtil2 {

	/** The batch size. */
	private static int BATCH_SIZE = 1000;

	/** The buffer size. */
	private static int BUFFER_SIZE = 5 * 1024 * 1024;

	/** The table name. */
	private static String TABLE_NAME = "operates";

	/**
	 * Import log file.
	 * 
	 * @param file
	 *            the file
	 * @return the int
	 */
	public static int importLogFile(File file) {
		return importLogFile(file, null);
	}

	/**
	 * 将log日志文件，转成信息列表.
	 * 
	 * @param file
	 *            the file
	 * @param skipDate
	 *            the skip date
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

			Long skip = null;
			if (UtilFunc.hasText(skipDate)) {
				skip = Long.valueOf(DateFunc.parseDatetime(
						skipDate.trim().substring(0, 19)).getTime());
				if (skipDate.trim().length() == 23) {
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
						if (skip == null
								|| skip.compareTo(sma.getActionDate().getTime()
										+ sma.getActionTotaltime().longValue()) < 0) {
							lst.add(toDocument(sma));
							n++;

							// 每隔1000行提交一次数据库
							if (lst.size() % BATCH_SIZE == 0) {
								collection.insertMany(lst);
								lst.clear();
							}
						}

						sma = null;
					}

					action = reader.readLine();
					if (action != null && action.startsWith("action: ")
							&& line.length() >= 30 && action.length() >= 8) {
						sma = new SysMonitorAction();
						// sma.setActionNo(UtilFunc.getUUID());
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
				if (skip == null
						|| skip.compareTo(sma.getActionDate().getTime()
								+ sma.getActionTotaltime().longValue()) < 0) {
					lst.add(toDocument(sma));
					n++;
				}

				sma = null;
			}

			reader.close();
			fis.close();

			if (lst.size() > 0) {
				collection.insertMany(lst);
				lst.clear();
			}

			MongoHelper
					.createIndexes(
							collection,
							"action_date, log_level, action_totaltime, action_url, action_result, action_view, action_caller, time_point");

			return n;
		} catch (Exception ex) {
			throw new HygeiaException(ex);
		}
	}

	/**
	 * To document.
	 * 
	 * @param sma
	 *            the sma
	 * @return the document
	 */
	private static Document toDocument(SysMonitorAction sma) {
		Document doc = MongoHelper.toDocument(sma);
		doc.put("time_point",
				DateFunc.dateToString(sma.getActionDate(), "yyyy-MM-dd HH:mm"));
		doc.remove("action_no");
		return doc;
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
		query.setSort(MongoHelper.getSort("action_date"));
		return MongoHelper.replaceId(
				MongoHelper.query(TABLE_NAME, query, HashMap.class),
				"action_no");
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
		ops.add(Aggregation.project("action_caller", "action_date",
				"action_totaltime"));
		// group
		ops.add(Aggregation.group("action_caller").count().as("action_num")
				.min("action_date").as("action_begin_date").max("action_date")
				.as("action_end_date")
				.sum("action_totaltime").as("action_totaltime_total")
				.avg("action_totaltime").as("action_totaltime_avg")
				.max("action_totaltime").as("action_totaltime_max")
				.min("action_totaltime").as("action_totaltime_min"));
		// sort
		ops.add(MongoHelper.getSortOperation("action_num desc"));

		// aggregate获取记录数性能很差，使用distinct提升性能（只支持单key）
		return MongoHelper.replaceId(
				MongoHelper.aggregateWithDistinct(TABLE_NAME, ops,
						HashMap.class, "action_caller", filter),
				"action_caller");
	}

	/**
	 * Gets the date list.
	 * 
	 * @param map
	 *            the map
	 * @return the date list
	 */
	public static List getDateList(Map map) {
		MongoFilter filter = getQueryFilter(map);
		List<AggregationOperation> ops = new ArrayList<AggregationOperation>();
		// match
		ops.add(MongoHelper.getMatchOperation(filter));
		// project
		ops.add(Aggregation.project("time_point", "action_totaltime"));
		// group
		ops.add(Aggregation.group("time_point").count().as("action_num")
				.sum("action_totaltime").as("action_totaltime_total")
				.avg("action_totaltime").as("action_totaltime_avg")
				.max("action_totaltime").as("action_totaltime_max")
				.min("action_totaltime").as("action_totaltime_min"));
		// sort
		ops.add(MongoHelper.getSortOperation("_id"));

		// aggregate获取记录数性能很差，使用distinct提升性能（只支持单key）
		return MongoHelper.replaceId(
				MongoHelper.aggregateWithDistinct(TABLE_NAME, ops, HashMap.class, "time_point", filter),
				"action_date");
	}

	/**
	 * Gets the query filter.
	 * 
	 * @param map
	 *            the map
	 * @return the query filter
	 */
	private static MongoFilter getQueryFilter(Map map) {
		MongoFilter filter = new MongoFilter();

		filter.betweenDatetime("action_date",
				UtilFunc.getString(map, "begin_date"),
				UtilFunc.getString(map, "end_date"));

		filter.addIntValues("log_level", map.get("log_level"));

		String[] ranges = new String[] { "totaltime" };
		for (String range : ranges) {
			filter.betweenInt("action_" + range,
					UtilFunc.getString(map, range + "_min"),
					UtilFunc.getString(map, range + "_max"));
		}

		String[] cnds = new String[] { "action_url", "action_param",
				"action_result", "action_view", "action_caller" };
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

			String filepath = "F:\\mydownload\\baseaction.log";
			File file = new File(filepath);

			ActionMonitorUtil2.importLogFile(file);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			GlobalContext.shutdown();
		}
	}
}
