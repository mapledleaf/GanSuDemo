package com.powersi.biz.medicare.inhospital.service.date;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 刘勇
 *
 */
public interface DateService extends Serializable {

	/**
	 * "yyyy-MM-dd"
	 */
	public String _yyyyMMdd = "yyyy-MM-dd";

	/**
	 * "yyyy-MM-dd HH:mm:ss"
	 */
	public String _yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

	/**
	 * microsecond 微秒
	 */
	public String _yyyyMMddHHmmssSSSSSS = "yyyy-MM-dd HH:mm:ss.SSSSSS";

	/**
	 * microsecond 微秒
	 */
	public String yyyyMMddHHmmssSSSSSS = "yyyyMMddHHmmssSSSSSS";

	/**
	 * millisecond 毫秒
	 */
	public String _yyyyMMddHHmmssSSS = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * millisecond 毫秒
	 */
	public String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

	/**
	 * 核三日期格式"yyyyMMdd"
	 */
	public String yyyyMMdd = "yyyyMMdd";

	/**
	 * yyyyMMddHHmmss
	 */
	public String yyyyMMddHHmmss = "yyyyMMddHHmmss";

	/**
	 * "yyyyMMdd HH:mm:ss"
	 */
	public String DATETIME_FORMAT = "yyyyMMdd HH:mm:ss";

	/**
	 * "yyyyMMdd"
	 */
	public String DATE_FORMAT = "yyyyMMdd";

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public Date toDate(String date, String format);

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public String dateToString(Date date, String format);

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public boolean isValidDate(String date, String format);

	/**
	 * 
	 * @return yyyyMMdd
	 */
	public String formatStringDate(String date, String division);

	/**
	 * 
	 * @return yyyyMMdd
	 */
	public String formatStringDate(String date);

	/**
	 * 
	 * @return yyyyMMddHHmmss
	 */
	public String formatStringTime(String dateTime, String division);

	/**
	 * 
	 * @return yyyyMMddHHmmss
	 */
	public String formatStringTime(String dateTime);

	/**
	 * 
	 * @return yyyy-MM-dd
	 */
	public String formatDate(String date);

	/**
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public String formatDateTime(String dateTime);

}
