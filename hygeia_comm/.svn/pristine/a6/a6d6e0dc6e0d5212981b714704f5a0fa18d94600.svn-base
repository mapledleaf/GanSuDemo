package com.powersi.biz.medicare.inhospital.service.util;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;

/**
 * 公共工具类
 * 
 * @author 刘勇
 *
 */
public interface CommunalService extends Serializable {

	/**
	 * 是否包含字母数字汉字
	 * 
	 * @param key
	 * @return
	 */
	public boolean isAlphanumericChinesecharacters(String key);

	/**
	 * 获取批量插入sql的value后面的sql语句，因在mybatis里面采用foreach循环性能比较低，为了规避这个问题，采用在外面用StringBuilder拼接sql传入，减少性能的消耗。
	 * 
	 * @param rowValueList
	 * @param rowNameList
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getSqlValues(List rowValueList, List<String> rowNameList);

	/**
	 * 正负号函数
	 * 
	 * @param a
	 * @return
	 */
	public int signum(int a);

	/**
	 * 正负号函数
	 * 
	 * @param a
	 * @return
	 */
	public int signum(double a);

	/**
	 * 
	 * @param dNumber
	 * @param iPRECISION
	 * @return
	 */
	public double getRound(double dNumber, int iPRECISION);

	/**
	 * 小数点后保留两位
	 * 
	 * @param money
	 * @return
	 */
	public String retainTwoDecimalPlaces(String money);

	/**
	 * 云平台日志采集
	 * 
	 * @param logger
	 * @param exception
	 * @param message
	 */
	public void debug(Logger logger, Exception exception, String message);

	/**
	 * 云平台日志采集
	 * 
	 * @param logger
	 * @param exception
	 * @param message
	 */
	public void debug(Logger logger, Throwable exception, String message);

	/**
	 * 云平台日志采集
	 * 
	 * @param logger
	 * @param exception
	 * @param message
	 */
	public void error(Logger logger, Exception exception, String message);

	/**
	 * 云平台日志采集
	 * 
	 * @param logger
	 * @param exception
	 * @param message
	 */
	public void error(Logger logger, Throwable exception, String message);

	/**
	 * 分表台账
	 * 
	 * @param begin_date
	 * @param end_date
	 * @return yyyyMM
	 */
	public List<String> tableLedger(Date begin_date, Date end_date);

	/**
	 * 日期格式(年月)
	 */
	public String yyyyMM = "yyyyMM";

	/**
	 * 日期格式(年月日)
	 */
	public String yyyyMMdd = "yyyyMMdd";

	/**
	 * 
	 * @return UUID(小写)
	 */
	public String uuid();

	/**
	 * 
	 * @param prefix
	 * @return
	 */
	public String uuid(String prefix);

	/**
	 * 
	 * @param cur
	 * @param def
	 * @return
	 */
	public String nvl(String cur, String def);

	/**
	 * 
	 * @param e
	 * @return
	 */
	public String exceptionString(Throwable e);

	/**
	 * 
	 * @param asc
	 * @return
	 */
	public char ascii2char(int asc);

	/**
	 * 
	 * @param c
	 * @return
	 */
	public int char2ascii(char c);

	/**
	 * 
	 * @param ec2
	 * @return
	 */
	public String crypt(String ec2);

	/**
	 * 
	 * @param map
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String str(Map<?, ?> map, Object key, String defaultValue);

	/**
	 * 
	 * @param map
	 * @param value
	 * @param defaultKey
	 * @return
	 */
	public String strKey(Map<?, ?> map, Object value, String defaultKey);

	/**
	 * 根据出生日期计算年龄
	 * 
	 * @param birthday
	 * @return
	 */
	public int calcAgeByBirth(Date birthday);

	/**
	 * 解决grid数据多的时候excel显示空白问题
	 * 
	 * @author yangmj 2018年1月16日 下午5:19:11
	 * @param list
	 * @param gridexportMap
	 * @param filename
	 *            void
	 */
	@SuppressWarnings("rawtypes")
	public HSSFWorkbook exportToExcel(List<Map> list, Map<String, Object> gridexportMap);

}
