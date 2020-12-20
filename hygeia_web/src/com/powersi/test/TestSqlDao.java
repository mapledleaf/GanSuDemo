/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.PaginatedListHolder;
import com.powersi.hygeia.framework.util.DBFunc;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.StringConvert;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.hygeia.web.util.PagerHelper;

/**
 * The Class TestSqlDao.
 */
public class TestSqlDao {
	// 获取类的sql命名空间（可以使用@SqlNamespace注解，一般情况不需要配置，框架根据类名自动生成）
	// 类名生成sql命名空间规则：全部小写 去除com.powersi 去除dao 去除daompl
	// //////////////////////////////////////////////////////////////////////////////////////
	//
	// 继承BaseDAOImpl类可以直接使用dbTemplate方法，无需自己拼sql的namespace
	//
	// 参数值使用自动数组Object... values
	// 当使用索引?绑定的时候使用array语法：value1,value2,value3...
	// 当使用参数名或者模板绑定的时候使用map语法：key1,value1,key2,value2,key3,value3...
	// 当使用批量操作的时候使用list语法：list<Object[]> list<Map> list<Entity>
	// //////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		try {
			// 初始化框架
			GlobalContext.init();

			// 获取实例名
			String serverName = GlobalContext.getInstanceName();

			// 获取日志对象
			Log logger = LogHelper.getLogger();

			{// 创建表
				dbTemplate("createTable");
			}

			{// 创建存储过程包
				dbTemplate("creatPackageDefine");
				dbTemplate("creatPackageBody");
			}

			{// 测试存储过程调用

				// 定义入参
				Map inparamMap = new HashMap();
				inparamMap.put(Integer.valueOf(1), "1288");
				inparamMap.put(Integer.valueOf(6), "测试输入输出");

				// 定义出参
				Map outparamMap = new HashMap();

				// 设置结果集类型(OracleTypes.CURSOR)等同于一个list
				outparamMap.put(Integer.valueOf(2),
						Integer.valueOf(DBFunc.SP_RESULTSET_TYPE));

				outparamMap.put(Integer.valueOf(3),
						Integer.valueOf(java.sql.Types.NUMERIC));
				outparamMap.put(Integer.valueOf(4),
						Integer.valueOf(java.sql.Types.VARCHAR));
				outparamMap.put(Integer.valueOf(5),
						Integer.valueOf(java.sql.Types.TIMESTAMP));
				outparamMap.put(Integer.valueOf(6),
						Integer.valueOf(java.sql.Types.VARCHAR));

				// 执行模板
				Map resultMap = (Map) dbTemplate("callProc", inparamMap, outparamMap);

				// 显示结果
				for (Iterator it = resultMap.keySet().iterator(); it.hasNext();) {
					Object key = it.next();
					logger.debug(key + ":" + resultMap.get(key));
				}
			}

			{// 测试关闭调试功能
				logger.debug("调试功能已经关闭，sql语句不会输出控制台");
				int ret = (Integer) dbTemplate("closeDebug");
				logger.debug("test disabled debug:" + ret);
			}

			{// 测试索引参数(参数使用数组)
				TestDaoEntity ssl = new TestDaoEntity();
				ssl.setLogSn(SysFunc.getID());
				ssl.setServerName(serverName);
				ssl.setLogDate(new java.util.Date());
				ssl.setLogLevel("DEBUG");
				ssl.setLogMessage("测试sql索引插入");

				// insert
				int ret = (Integer) dbTemplate("inserIndex", ssl.getLogSn(), ssl.getServerName(),
						ssl.getLogDate(), ssl.getLogLevel(), ssl.getLogName(),
						UtilFunc.trimWhitespace(ssl.getLogMessage()),
						UtilFunc.trimWhitespace(ssl.getLogException()),
						ssl.getLoginUser(), ssl.getUserAddress(),
						UtilFunc.left(ssl.getOperateAction(), 500),
						ssl.getOperateName(), ssl.getBizId());

				logger.debug("insert index result:" + ret);

				// blob(第一个参数必须byte[]或者InputStream)
				byte[] blob = StringConvert.getBytes("测试blob索引数据");
				ret = (Integer) dbTemplate("blobIndex", blob, ssl.getLogSn());

				logger.debug("blob index result:" + ret);

				// find
				Object obj = dbTemplate("findIndex", ssl.getLogSn());
				if (obj != null) {
					logger.debug("find index result:"
							+ StringConvert.newString(DBHelper.blobToBytes(obj)));
				} else {
					logger.error("find index result:null");
				}
			}

			{// 测试名称参数(参数一个使用map或者entity，超过1个参数使用key1,value1,key2,value2，框架自动构建map)
				TestDaoEntity ssl = new TestDaoEntity();
				ssl.setLogSn(SysFunc.getID());
				ssl.setServerName(serverName);
				ssl.setLogDate(new java.util.Date());
				ssl.setLogLevel("DEBUG");
				ssl.setLogMessage("测试sql名称插入");

				// insert
				int ret = (Integer) dbTemplate("insertName", ssl);

				logger.debug("insert name result:" + ret);

				// blob(第一个参数必须byte[]或者InputStream)
				// 支持多个参数自动生成map
				byte[] blob = StringConvert.getBytes("测试blob名称数据");
				ret = (Integer) dbTemplate("blobName", blob, "log_sn", ssl.getLogSn(),
						"server_name", ssl.getServerName());

				logger.debug("blob name result:" + ret);

				// find
				// 支持使用entity
				Object obj = dbTemplate("findName", ssl);
				if (obj != null) {
					logger.debug("find name result:"
							+ StringConvert.newString(DBHelper.blobToBytes(obj)));
				} else {
					logger.error("find name result:null");
				}
			}

			{// 测试模板参数(参数一个使用map或者entity，超过1个参数使用key1,value1,key2,value2，框架自动构建map)
				TestDaoEntity ssl = new TestDaoEntity();
				ssl.setLogSn(SysFunc.getID());
				ssl.setServerName(serverName);
				ssl.setLogDate(new java.util.Date());
				ssl.setLogLevel("DEBUG");
				ssl.setLogMessage("测试sql模板插入");

				// insert
				int ret = (Integer) dbTemplate("insertTemplate", ssl);

				logger.debug("insert template result:" + ret);

				// blob(第一个参数必须byte[]或者InputStream)
				// 支持多个参数自动生成map
				byte[] blob = StringConvert.getBytes("测试blob模板数据");
				ret = (Integer) dbTemplate("blobTemplate", blob, "log_sn", ssl.getLogSn(),
						"server_name", ssl.getServerName());

				logger.debug("blob template result:" + ret);

				// find
				// 支持使用entity
				Object obj = dbTemplate("findTemplate", ssl);
				if (obj != null) {
					logger.debug("find template result:"
							+ StringConvert.newString(DBHelper.blobToBytes(obj)));
				} else {
					logger.error("find template result:null");
				}
			}

			{// 测试索引批量参数(参数使用List<数组>)
				List lst = new ArrayList();
				for (int i = 0; i < 5; i++) {
					TestDaoEntity ssl = new TestDaoEntity();
					ssl.setLogSn(SysFunc.getID());
					ssl.setServerName(serverName);
					ssl.setLogDate(new java.util.Date());
					ssl.setLogLevel("DEBUG");
					ssl.setLogMessage("测试sql索引批量插入" + i);

					lst.add(new Object[] { ssl.getLogSn(), ssl.getServerName(),
							ssl.getLogDate(), ssl.getLogLevel(),
							ssl.getLogName(),
							UtilFunc.trimWhitespace(ssl.getLogMessage()),
							UtilFunc.trimWhitespace(ssl.getLogException()),
							ssl.getLoginUser(), ssl.getUserAddress(),
							UtilFunc.left(ssl.getOperateAction(), 500),
							ssl.getOperateName(), ssl.getBizId() });
				}

				// insert
				int[] ret = (int[]) dbTemplate("inserIndex", lst);

				logger.debug("insert batch index result:" + ret.length);
			}

			{// 测试名称批量参数(参数使用List<map或者entity>)
				List lst = new ArrayList();
				for (int i = 0; i < 5; i++) {
					TestDaoEntity ssl = new TestDaoEntity();
					ssl.setLogSn(SysFunc.getID());
					ssl.setServerName(serverName);
					ssl.setLogDate(new java.util.Date());
					ssl.setLogLevel("DEBUG");
					ssl.setLogMessage("测试sql名称批量插入" + i);

					lst.add(ssl);
				}

				// insert
				int[] ret = (int[]) dbTemplate("insertName", lst);

				logger.debug("insert batch name result:" + ret.length);
			}

			{ // 测试模板批量参数(参数使用List<map或者entity>)
				// 每条sql都需要解析，不推荐使用模板提交批量数据，性能差
				List lst = new ArrayList();
				for (int i = 0; i < 5; i++) {
					TestDaoEntity ssl = new TestDaoEntity();
					ssl.setLogSn(SysFunc.getID());
					ssl.setServerName(serverName);
					ssl.setLogDate(new java.util.Date());
					ssl.setLogLevel("DEBUG");
					ssl.setLogMessage("测试sql模板批量插入" + i);

					lst.add(ssl);
				}

				// insert
				int[] ret = (int[]) dbTemplate("insertTemplate", lst);

				logger.debug("insert batch template result:" + ret.length);
			}

			{ // 测试查询索引(参数使用数组)
				List lst = (List) dbTemplate("queryIndex", 5);

				logger.debug("query index result:\n" + JsonHelper.toJson(lst));
			}

			{ // 测试查询名称(参数一个使用map或者entity，超过1个参数使用key1,value1,key2,value2，框架自动构建map)
				List lst = (List) dbTemplate("queryName", "rownum", 5);

				logger.debug("query name result:\n" + JsonHelper.toJson(lst));
			}

			{ // 测试查询模板(参数一个使用map或者entity，超过1个参数使用key1,value1,key2,value2，框架自动构建map)
				List lst = (List) dbTemplate("queryTemplate", "rownum", 5);

				logger.debug("query template result:\n"
						+ JsonHelper.toJson(lst));
			}

			{ // 测试计数索引(参数使用数组)
				long cnt = (Long) dbTemplate("countIndex", serverName);

				logger.debug("count index result:" + cnt);
			}

			{ // 测试计数名称(参数一个使用map或者entity，超过1个参数使用key1,value1,key2,value2，框架自动构建map)
				// 使用框架内置map
				long cnt = (Long) dbTemplate("countName", "server_name", serverName);

				logger.debug("count name result:" + cnt);
			}

			{ // 测试计数模板(参数一个使用map或者entity，超过1个参数使用key1,value1,key2,value2，框架自动构建map)
				TestDaoEntity ssl = new TestDaoEntity();
				ssl.setServerName(serverName);
				// 使用entity
				long cnt = (Long) dbTemplate("countTemplate", ssl);

				logger.debug("count template result:" + cnt);
			}

			{// 测试单条dao插入更新删除
				TestDaoEntity ssl = new TestDaoEntity();
				ssl.setLogSn(SysFunc.getID());
				ssl.setServerName(serverName);
				ssl.setLogDate(new java.util.Date());
				ssl.setLogLevel("DEBUG");
				ssl.setLogMessage("测试dao插入");

				// insert
				int ret = (Integer) dbTemplate("insertDao", ssl);
				logger.debug("insert dao result:" + ret);

				// update
				ret = (Integer) dbTemplate("updateDao", "log_sn", ssl.getLogSn(), "log_message",
						"更新dao消息使用主键");
				logger.debug("update dao result:" + ret);

				// delete
				ret = (Integer) dbTemplate("deleteDao", "log_sn", ssl.getLogSn());
				logger.debug("delete dao result:" + ret);
			}

			{// 测试dao批量插入更新删除
				List<TestDaoEntity> lst = new ArrayList();
				for (int i = 0; i < 5; i++) {
					TestDaoEntity ssl = new TestDaoEntity();
					ssl.setLogSn(SysFunc.getID());
					ssl.setServerName(serverName);
					ssl.setLogDate(new java.util.Date());
					ssl.setLogLevel("DEBUG");
					ssl.setLogMessage("测试dao批量插入" + i);

					lst.add(ssl);
				}

				// insert batch
				int ret = (Integer) dbTemplate("insertBatchDao", lst);
				logger.debug("insert batch dao result:" + ret);

				// update batch
				for (TestDaoEntity ssl : lst) {
					ssl.setLogLevel("ERROR");
					ssl.setLogMessage(ssl.getLogMessage() + ":批量更新");
				}
				ret = (Integer) dbTemplate("updateBatchDao", lst);
				logger.debug("update batch dao result:" + ret);

				// delete batch
				ret = (Integer) dbTemplate("deleteBatchDao", lst);
				logger.debug("delete batch dao result:" + ret);
			}

			{// 准备码表数据
				int ret = 0;
				if ((Long) dbTemplate("countCodetable", "test_codetable") == 0) {
					dbTemplate("insertCodetable", "code_type",
							"test_codetable", "code_name", "测试码表");
					ret++;
				}
				if ((Long) dbTemplate("countCodetableDetail", "test_codetable", "DEBUG") == 0) {
					dbTemplate("insertCodetableDetail", "code_type",
							"test_codetable", "data_value", "DEBUG",
							"display_value", "调试", "dis_order", 1);
					ret++;
				}
				if ((Long) dbTemplate("countCodetableDetail", "test_codetable", "INFO") == 0) {
					dbTemplate("insertCodetableDetail", "code_type",
							"test_codetable", "data_value", "INFO",
							"display_value", "信息", "dis_order", 2);
					ret++;
				}
				if ((Long) dbTemplate("countCodetableDetail", "test_codetable", "ERROR") == 0) {
					dbTemplate("insertCodetableDetail", "code_type",
							"test_codetable", "data_value", "ERROR",
							"display_value", "错误", "dis_order", 3);
					ret++;
				}

				if (ret > 0) {
					CodetableMapping.refresh("test_codetable");
				}
			}

			{// 码表转换测试
				//map
				logger.debug(dbTemplate("codetableMap", serverName));

				//bean
				logger.debug(dbTemplate("codetableBean", serverName));
				
				//dao
				logger.debug(dbTemplate("codetableDao", "server_name", serverName));
			}

			{//分页测试
				
				//初始化分页(先用5行试下)
				PagerHelper.initPagination(5, 1);
				
				//disabled
				List lst = (List) dbTemplate("pageDisabled", serverName);
				logger.debug("disabled page result:" + lst.size());
				
				//summary
				PaginatedListHolder paginatedList = new PaginatedListHolder((List)dbTemplate("pageSummary", serverName));
				logger.debug(PagerHelper.paginatedListToMap(paginatedList));
				
				//初始化分页(缺省20行一页)
				PagerHelper.initPagination(2);
				paginatedList = new PaginatedListHolder((List)dbTemplate("pageCount", "server_name", serverName));
				logger.debug(PagerHelper.paginatedListToMap(paginatedList));
			}
			DBHelper.commit();
		} catch (Exception ex) {
			DBHelper.rollback();
			ex.printStackTrace();
		} finally {
			DBHelper.close();
		}
	}
	
	/**
	 * Db template.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param resultClass
	 *            the result class
	 * @param sqlId
	 *            the sql id
	 * @param params
	 *            the params
	 * @return the t
	 */
	public static <T> T dbTemplate(Class<T> resultClass, String sqlId, Object... params){
		return (T) dbTemplate(sqlId, params);
	}
	/**
	 * Db template.
	 * 
	 * @param sqlId
	 *            the sql id
	 * @param params
	 *            the params
	 * @return the object
	 */
	public static Object dbTemplate(String sqlId, Object... params){
		return SqlHelper.executeTemplate("sample.dao." + sqlId, params);
	}
}
