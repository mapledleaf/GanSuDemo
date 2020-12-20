package com.powersi.powerreport.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.powermarker.printer.PowerPrinterConfigs;

/**
 * 负责操作报表report_base表
 * 
 * @author 李志钢
 *
 */
public class PowerReportDao {

	/** DAO对应表名. */
	public static final String TABLE_NAME = "report_base";

	/**
	 * 新增报表基础信息主表信息
	 * 
	 * @param param
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	public int insertReportBase(Map param) {
		return DAOHelper.insert(TABLE_NAME, param);
	}

	/**
	 * 根据biz_id查询报表基础信息主表信息
	 * 
	 * @param bizID
	 * @return Map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getReportBaseInfoBizID(String bizID) {
		Map param = new HashMap();
		param.put("bizID", bizID);
		Map reportBaseInfo = DAOHelper.queryForMap(TABLE_NAME, param, new String[] { "$biz_id|bizID" });
		return reportBaseInfo;
	}

	/**
	 * 根据report_id查询报表基础信息主表信息
	 * 
	 * @param reportID
	 * @return Map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getReportBaseInfoReportID(String reportID) {
		Map param = new HashMap();
		param.put("reportID", reportID);
		Map reportBaseInfo = DAOHelper.queryForMap(TABLE_NAME, param, new String[] { "$report_id|reportID" });
		return reportBaseInfo;
	}

	/**
	 * 根据report_id删除报表基础信息主表信息
	 * 
	 * @param reportID
	 *            void
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteReportBaseInfo(String reportID) {
		Map param = new HashMap();
		param.put("reportID", reportID);
		DAOHelper.delete(TABLE_NAME, param, new String[] { "$report_id|reportID" });
	}

	/**
	 * 查询过期的报表基础信息主表信息,每次查询一百条（rownum <= 100）
	 * 
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List queryExpriedReportInfo() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * ");
		sql.append(" from " + TABLE_NAME + " ");
		sql.append(" where	keep_days != 0 ");
		if (DBHelper.getDatabaseName().equalsIgnoreCase("mysql")) {
			sql.append(" and CAST((DATEDIFF(NOW(),create_time)) AS SIGNED INTEGER ) > keep_days ORDER BY CREATE_TIME");
		} else if (DBHelper.getDatabaseName().equalsIgnoreCase("oracle")) {
			sql.append(" and ROUND(TO_NUMBER(sysdate - create_time)) > keep_days");
		}
		return DBHelper.executePage(sql.toString(), 100, 0);
	}

	/**
	 * 获取打印配置信息，报表存储到sftp或者sftp的时候才使用
	 * 
	 * @param store_id
	 * @return PowerPrinterConfigs
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PowerPrinterConfigs getPrintConfig(String store_id) {
		// 根据store_id查询字表信息
		Map param = new HashMap();
		param.put("storeId", store_id);
		Map queryForMap = DAOHelper.queryForMap(TABLE_NAME, param, new String[] { "$store_id|storeId" });
		// 返回打印配置信息
		PowerPrinterConfigs printerConfigs = null;
		if (queryForMap.get("print_config") != null) {
			Gson gson = new Gson();
			printerConfigs = gson.fromJson(queryForMap.get("print_config").toString(), PowerPrinterConfigs.class);
		}
		return printerConfigs;
	}

}
