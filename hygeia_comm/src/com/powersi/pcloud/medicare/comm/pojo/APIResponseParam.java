package com.powersi.pcloud.medicare.comm.pojo;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powersi.webservice.WebServiceResponseParam;

/**
 * 
 * @author 刘勇
 *
 */
public class APIResponseParam extends WebServiceResponseParam {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void setStrings(String key, String value) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		this.setValue(key, value);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getStrings(String key) {
		if (StringUtils.isBlank(key)) {
			return "";
		}
		Object objValue = this.get(key);
		if (objValue == null) {
			return "";
		}
		if (objValue instanceof String) {
			return (String) objValue;
		}
		return objValue.toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setList(String listName, List<Map> listValue) {
		if (StringUtils.isBlank(listName)) {
			return;
		}
		this.put(listName, listValue);
	}

	/**
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map> getList(String listName) {
		if (StringUtils.isBlank(listName)) {
			return Collections.EMPTY_LIST;
		}
		if (this.get(listName) == null) {
			return Collections.EMPTY_LIST;
		}
		if (this.get(listName) instanceof List) {
			return (List<Map>) this.get(listName);
		}
		return Collections.EMPTY_LIST;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(String key, Object value) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		this.put(key, value);
	}

	/**
	 * 
	 */
	@Override
	public String getValue(String key) {
		return this.getStrings(key);
	}

	/**
	 * 
	 * @param map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setMap(Map map) {
		if (map == null || map.size() == 0) {
			return;
		}
		this.putAll(map);
	}

}
