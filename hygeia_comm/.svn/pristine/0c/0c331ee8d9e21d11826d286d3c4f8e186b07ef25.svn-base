package com.powersi.pcloud.medicare.comm.pojo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 刘勇
 *
 */
@SuppressWarnings("rawtypes")
public class MedicareCommParam extends HashMap {

	private static final long serialVersionUID = 1L;
	private final Map parameters_medicare = new HashMap();
	private final Map parametersets_medicare = new HashMap();

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public MedicareCommParam() {
		this.put("parameters_medicare", this.parameters_medicare);
		this.put("parametersets_medicare", this.parametersets_medicare);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public void setParameter(String key, String value) {
		this.parameters_medicare.put(key, value);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getPrameter(String key) {
		Object obj = this.parameters_medicare.get(key);
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	/**
	 * 
	 * @return
	 */
	public Map getParametersAll() {
		return this.parameters_medicare;
	}

	/**
	 * 
	 * @return
	 */
	public Map getParameterSetsAll() {
		return this.parametersets_medicare;
	}

	/**
	 * 
	 * @param listName
	 * @param listValue
	 */
	@SuppressWarnings("unchecked")
	public void setList(String listName, List<Map> listValue) {
		this.parametersets_medicare.put(listName, listValue);
	}

	/**
	 * 
	 * @param listName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getList(String listName) {
		if (this.parametersets_medicare.get(listName) instanceof List) {
			return (List<Map>) this.get(listName);
		}
		return Collections.EMPTY_LIST;
	}

}
