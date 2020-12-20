/**
 * Copyright 2011 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * 级别辅助类.
 */
public abstract class GradeHelper {
	
	/** The Constant codeGradeId. */
	private static final String codeGradeId[] = new String[]{"无", "低", "中", "高", "系统", "开发"};
	
	/**
	 * 获取用户级别.
	 *
	 * @param user the user
	 * @return the grade id
	 */
	public static Integer getUserGradeId(UserInfo user){
		return Integer.valueOf(UtilFunc.getString(user, "grade_id", "0"));
	}
	
	/**
	 * 级别码表.
	 *
	 * @param user the user
	 * @return the map
	 */
	public static Map<String, String> valueMap(UserInfo user){
		Map map = new LinkedHashMap();
		int level = getUserGradeId(user).intValue();
        for(int i = 0; i < codeGradeId.length; i ++){
        	if(level >= i){
        		map.put(String.valueOf(i), codeGradeId[i]);
        	} else {
        		break;
        	}
        }
        
        return map;
	}
	
	/**
	 * 级别码表.
	 *
	 * @param user the user
	 * @return the map
	 */
	public static Map<String, String> valueMap(){
		Map map = new LinkedHashMap();
        for(int i = 0; i < codeGradeId.length; i ++){
        	map.put(String.valueOf(i), codeGradeId[i]);
        }
        
        return map;
	}
}
