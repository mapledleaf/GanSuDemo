/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.sys.util;

import org.apache.commons.lang.StringUtils;


/**
 * The Class StringUtil.
 */
public class StringUtil {
	
	/**
	 * 判断字符串是否为空 如果有单引号，则返回true,避免被注入.
	 * 
	 * @param str
	 *            字符串
	 * @return true or false
	 */
	public static boolean isBlank(String str){
		if(!StringUtils.isBlank(str)){
			if (str.indexOf("'")!=-1) { //屏蔽单引号
				return true;
			}
			return false;
		}
		return true;
	}
}


