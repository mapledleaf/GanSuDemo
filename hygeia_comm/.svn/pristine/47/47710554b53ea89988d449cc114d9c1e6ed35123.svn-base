package com.powersi.switches.pojo;

import java.util.Date;
import java.util.HashMap;

import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.biz.medicare.inhospital.service.date.DateServiceImpl;

/**
 * 取值
 * 
 * @author 刘勇
 *
 */
@SuppressWarnings("rawtypes")
public class SwitchesMap extends HashMap {

	private static final long serialVersionUID = 1L;

	private static final DateService dateService = new DateServiceImpl();

	/**
	 * 
	 * @return 当前日期(核三日期格式"yyyyMMdd")
	 */
	public String getBka890() {
		return dateService.dateToString(new Date(), DateService.yyyyMMdd);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		if (this.containsKey(key)) {
			return (String) this.get(key);
		}
		return null;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public void setValue(String key, String value) {
		this.put(key, value);
	}

}
