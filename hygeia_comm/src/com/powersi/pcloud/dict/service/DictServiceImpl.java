package com.powersi.pcloud.dict.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.powersi.comm.service.memory.MemoryDB;
import com.powersi.comm.service.memory.MemoryDBWrapper;
import com.powersi.pcloud.dict.pojo.BasDictValues;

public class DictServiceImpl implements DictService {

	private final String MAP_DICT_VALUELIST = "MAP_DICT_VALUELIST";
	private MemoryDBWrapper memoryDBWrapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<BasDictValues> getByKey(String dict_key) {
		List<BasDictValues> list = (List<BasDictValues>) this.getMemoryDB().getMapValue(MAP_DICT_VALUELIST, dict_key);
		return list;
	}

	@Override
	public String getValue(String dict_key, String dict_value_code) {
		return this.getValue(dict_key, dict_value_code, "");
	}

	@Override
	public String getValue(String dict_key, String dict_value_code, String defaultValue) {
		String dictValueMapName = "MAP_DICT_VALUE_" + dict_key.toUpperCase();
		if (!this.getMemoryDB().existMapKey(dictValueMapName, dict_value_code)) {
			return defaultValue;
		}
		String dictValue = (String) this.getMemoryDB().getMapValue(dictValueMapName, dict_value_code);
		return dictValue;
	}

	@Override
	public float getValue_float(String dict_key, String dict_value_code, float defaultValue) {
		String dictValueStr = this.getValue(dict_key, dict_value_code);
		if (StringUtils.isEmpty(dictValueStr)) {
			return defaultValue;
		}
		float value_float = Float.parseFloat(dictValueStr);
		return value_float;
	}

	@Override
	public float getValue_float(String dict_key, String dict_value_code) {
		return this.getValue_float(dict_key, dict_value_code, (float) 0.00);
	}

	@Override
	public int getValue_int(String dict_key, String dict_value_code, int defaultValue) {
		String dictValueStr = this.getValue(dict_key, dict_value_code);
		if (StringUtils.isEmpty(dictValueStr)) {
			return defaultValue;
		}
		int value_int = Integer.parseInt(dictValueStr);
		return value_int;
	}

	@Override
	public int getValue_int(String dict_key, String dict_value_code) {
		return this.getValue_int(dict_key, dict_value_code, 0);
	}

	public MemoryDB getMemoryDB() {
		return this.memoryDBWrapper.getMemoryDB();
	}

	public void setMemoryDBWrapper(MemoryDBWrapper memoryDBWrapper) {
		this.memoryDBWrapper = memoryDBWrapper;
	}

}
