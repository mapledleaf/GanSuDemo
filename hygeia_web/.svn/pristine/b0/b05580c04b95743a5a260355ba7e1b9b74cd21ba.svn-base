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
import com.powersi.hygeia.framework.util.XMLFunc;
import com.powersi.sys.entity.SysMonitorFunc;

/**
 * The Class ActionMonitorUtil.
 */
public class FuncMonitorUtil2 {

	/** The batch size. */
	private static int BATCH_SIZE = 1000;

	/** The buffer size. */
	private static int BUFFER_SIZE = 5 * 1024 * 1024;

	/** The table name. */
	private static String TABLE_NAME = "services";

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
							lst.add(toDocument(smf));
							n++;
							
							// 每隔1000行提交一次数据库
							if (lst.size() % BATCH_SIZE == 0) {
								collection.insertMany(lst);
								lst.clear();
							}
						}
						
						smf = null;
					}
					
					userinfo = reader.readLine();
					if (userinfo != null && userinfo.startsWith("userinfo:")
							&& line.length() >= 30) {
						smf = new SysMonitorFunc();
						//smf.setFuncNo(UtilFunc.getUUID());
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
					lst.add(toDocument(smf));
					n++;
				}
				
				smf = null;
			}

			reader.close();
			fis.close();

			if (lst.size() > 0) {
				collection.insertMany(lst);
				lst.clear();
			}

			MongoHelper.createIndexes(
					collection,
					"func_date, log_level, func_totaltime, func_id, param_length, result_length, time_point");
			
			return n;
		} catch (Exception ex) {
			throw new HygeiaException(ex);
		}
	}

	private static Document toDocument(SysMonitorFunc smf){
		Document doc = MongoHelper.toDocument(smf);
		doc.put("time_point", DateFunc.dateToString(smf.getFuncDate(), "yyyy-MM-dd HH:mm"));
		doc.remove("func_no");
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
		query.setSort(MongoHelper.getSort("func_date"));
		return MongoHelper.replaceId(
				MongoHelper.query(TABLE_NAME, query, HashMap.class),
				"func_no");
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
		ops.add(Aggregation.project("func_id", "func_date", "param_length", "result_length"));
		// group
		ops.add(Aggregation.group("func_id").count().as("func_num")
				.min("func_date").as("func_begin_date").max("func_date").as("func_end_date")
				.sum("param_length").as("param_length_total")
				.avg("param_length").as("param_length_avg")
				.max("param_length").as("param_length_max")
				.min("param_length").as("param_length_min")
				.sum("result_length").as("result_length_total")
				.avg("result_length").as("result_length_avg")
				.max("result_length").as("result_length_max")
				.min("result_length").as("result_length_min"));
		// sort
		ops.add(MongoHelper.getSortOperation("func_num desc"));

		// aggregate获取记录数性能很差，使用distinct提升性能（只支持单key）
		return MongoHelper.replaceId(
				MongoHelper.aggregateWithDistinct(TABLE_NAME, ops, HashMap.class, "func_id", filter),
				"func_id");
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
		ops.add(Aggregation.project("time_point", "param_length", "result_length"));
		// group
		ops.add(Aggregation.group("time_point").count().as("func_num")
				.sum("param_length").as("param_length_total")
				.avg("param_length").as("param_length_avg")
				.max("param_length").as("param_length_max")
				.min("param_length").as("param_length_min")
				.sum("result_length").as("result_length_total")
				.avg("result_length").as("result_length_avg")
				.max("result_length").as("result_length_max")
				.min("result_length").as("result_length_min"));
		// sort
		ops.add(MongoHelper.getSortOperation("_id"));

		// aggregate获取记录数性能很差，使用distinct提升性能（只支持单key）
		return MongoHelper.replaceId(
				MongoHelper.aggregateWithDistinct(TABLE_NAME, ops, HashMap.class, "time_point", filter),
				"func_date");
	}
	
	private static MongoFilter getQueryFilter(Map map) {
		MongoFilter filter = new MongoFilter();

		filter.betweenDatetime("func_date",
				UtilFunc.getString(map, "begin_date"),
				UtilFunc.getString(map, "end_date"));

		filter.addIntValues("log_level", map.get("log_level"));

		filter.betweenInt("func_totaltime", UtilFunc.getString(map, "totaltime_min"), UtilFunc.getString(map, "totaltime_max"));
		filter.betweenInt("param_length", UtilFunc.getString(map, "param_length_min"), UtilFunc.getString(map, "param_length_max"));
		filter.betweenInt("result_length", UtilFunc.getString(map, "result_length_min"), UtilFunc.getString(map, "result_length_max"));
		
		String[] cnds = new String[] { "func_id", "func_param", "func_result" };
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

			String filepath = "C:\\Users\\lei\\Desktop\\processall.log";
			File file = new File(filepath);

			FuncMonitorUtil2.importLogFile(file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
