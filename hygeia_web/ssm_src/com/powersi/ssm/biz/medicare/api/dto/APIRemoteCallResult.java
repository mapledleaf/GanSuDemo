package com.powersi.ssm.biz.medicare.api.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;

import com.powersi.comm.utils.XMLUtils;

/**
 * 
 * @author 刘勇
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class APIRemoteCallResult extends HashMap {

	private static final long serialVersionUID = 1L;
	private final String return_code_key = "return_code";
	private final String return_code_message_key = "return_code_message";
	private String resultXML = "";

	/**
	 * 
	 * @param xml
	 */
	public APIRemoteCallResult(String xml) {
		try {
			this.put(return_code_key, "");
			this.put(return_code_message_key, "");
			if (StringUtils.isNotBlank(xml)) {
				this.resultXML = xml;
				this.putAll(XMLUtils.xml2map(xml));
			}
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Return XML
	 * 
	 * @return
	 */
	public String getResultXML() {
		return this.resultXML;
	}

	/**
	 * 
	 * @return
	 */
	public String getReturn_code() {
		return (String) this.get(return_code_key);
	}

	/**
	 * 
	 * @return
	 */
	public String getReturn_code_message() {
		return (String) this.get(return_code_message_key);
	}

	/**
	 * 
	 * @param listName
	 * @return
	 */
	public List<Map> getList(String listName) {
		if (this.get(listName) instanceof List) {
			return (List<Map>) this.get(listName);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		Object valueobj = this.get(key);
		if (valueobj == null) {
			return "";
		}
		return (String) valueobj;
	}

}
