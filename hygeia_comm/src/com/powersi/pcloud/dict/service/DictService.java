package com.powersi.pcloud.dict.service;

import java.util.List;

import com.powersi.pcloud.dict.pojo.BasDictValues;

public interface DictService {
	/**
	 * 根据字典key查询字典值
	 * @param dict_key 字典的key
	 * @return 所有对应的字典值
	 */
	
	List<BasDictValues> getByKey(String dict_key);
	/**
	 * 根据字典key和字典值代码查询字典值value
	 * @param dict_key 字典key
	 * @param dict_value_code 字典值代码
	 * @return 字典值value
	 */ 
	String getValue(String dict_key,String dict_value_code);
	String getValue(String dict_key,String dict_value_code, String defaultValue);
	
	/**
	 * 根据字典key和字典值代码查询字典值value_float
	 * @param dict_key 字典key
	 * @param dict_value_code 字典值代码
	 * @return value_float 字典值value的float类型
	 */
	float getValue_float(String dict_key,String dict_value_code);
	float getValue_float(String dict_key,String dict_value_code, float defaultValue);
	
	/**
	 * 根据字典key和字典值代码查询字典值value_int
	 * @param dict_key 字典key
	 * @param dict_value_code 字典值代码
	 * @return value_int 字典值value的int类型
	 */
	int getValue_int(String dict_key,String dict_value_code);
	int getValue_int(String dict_key,String dict_value_code, int defaultValue);
}
