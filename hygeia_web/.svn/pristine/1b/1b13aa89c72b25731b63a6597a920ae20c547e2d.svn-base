/*
 * Copyright 2011 Powersi. All rights reserved.
 */
package com.powersi.sys.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 提取身份证相关信息.
 */
public class IdcardInfoExtractor {
	// 省份
	/** The province. */
	private String province;
	// 城市
	/** The city. */
	private String city;
	// 区县
	/** The region. */
	private String region;
	// 统筹区
	/** The aaa027. */
	private String aaa027;
	// 年份
	/** The year. */
	private int year;
	// 月份
	/** The month. */
	private int month;
	// 日期
	/** The day. */
	private int day;
	// 性别
	/** The gender. */
	private String gender;
	// 出生日期
	/** The birthday. */
	private Date birthday;

	/** The city code map. */
	private static Map<String, String> cityCodeMap = new HashMap<String, String>() {
		{
			this.put("11", "北京");
			this.put("12", "天津");
			this.put("13", "河北");
			this.put("14", "山西");
			this.put("15", "内蒙古");
			this.put("21", "辽宁");
			this.put("22", "吉林");
			this.put("23", "黑龙江");
			this.put("31", "上海");
			this.put("32", "江苏");
			this.put("33", "浙江");
			this.put("34", "安徽");
			this.put("35", "福建");
			this.put("36", "江西");
			this.put("37", "山东");
			this.put("41", "河南");
			this.put("42", "湖北");
			this.put("43", "湖南");
			this.put("44", "广东");
			this.put("45", "广西");
			this.put("46", "海南");
			this.put("50", "重庆");
			this.put("51", "四川");
			this.put("52", "贵州");
			this.put("53", "云南");
			this.put("54", "西藏");
			this.put("61", "陕西");
			this.put("62", "甘肃");
			this.put("63", "青海");
			this.put("64", "宁夏");
			this.put("65", "新疆");
			this.put("71", "台湾");
			this.put("81", "香港");
			this.put("82", "澳门");
			this.put("91", "国外");
		}

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
	};

	/** The validator. */
	private IdcardValidator validator = null;

	/**
	 * 通过构造方法初始化各个成员属性.
	 * 
	 * @param idcard
	 *            the idcard
	 */
	public IdcardInfoExtractor(String idcard) {
		try {
			validator = new IdcardValidator();
			// if (validator.isValidatedAllIdcard(idcard)) {
			if (idcard.length() == 15) {
				idcard = validator.convertIdcarBy15bit(idcard);
			}
			// 获取省份
			String provinceId = idcard.substring(0, 2);
			// 统筹区
			this.aaa027 = idcard.substring(0, 6);
			Set<String> key = cityCodeMap.keySet();
			for (String id : key) {
				if (id.equals(provinceId)) {
					this.province = cityCodeMap.get(id);
					break;
				}
			}

			// 获取性别
			String id17 = idcard.substring(16, 17);
			if (Integer.parseInt(id17) % 2 != 0) {
				this.gender = "男";
			} else {
				this.gender = "女";
			}

			// 获取出生日期
			String birthday = idcard.substring(6, 14);
			Date birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);
			this.birthday = birthdate;
			GregorianCalendar currentDay = new GregorianCalendar();
			currentDay.setTime(birthdate);
			this.year = currentDay.get(Calendar.YEAR);
			this.month = currentDay.get(Calendar.MONTH) + 1;
			this.day = currentDay.get(Calendar.DAY_OF_MONTH);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the province.
	 * 
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Gets the city.
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Gets the region.
	 * 
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Gets the year.
	 * 
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Gets the month.
	 * 
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Gets the day.
	 * 
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Gets the gender.
	 * 
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Gets the birthday.
	 * 
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * Gets the aaa027.
	 * 
	 * @return the aaa027
	 */
	public String getAaa027() {
		return aaa027;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "省份：" + this.province + ",性别：" + this.gender + ",出生日期："
				+ this.birthday;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		String idcard = "";
		IdcardInfoExtractor ie = new IdcardInfoExtractor(idcard);
		System.out.println(ie.toString());
	}
}
