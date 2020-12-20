package com.powersi.ssm.biz.medicare.common.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powersi.comm.utils.XMLUtils;

/**
 * 服务于公司模式的xml服务请求参数封装类
 * 
 * @author tangmin
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RemoteCallAPIParam extends LinkedHashMap {

	private static final long serialVersionUID = 1L;

	/**
	 * 医保格式，list的key带行号<row1><row2>...
	 */
	private String formate = "";

	private String rootName = null;

	private String paramXML = null;
	
	private static final String akb020_key = "akb020";
	
	private static final String aaa027_key = "aaa027";
	
	private static final String aab301_key = "aab301";

	/**
	 * 设置功能号
	 * 
	 * @param functionID
	 */
	public void setFuncitionID(String functionID) {
		this.put("FunctionID", functionID);
	}

	/**
	 * 
	 * @return
	 */
	public String getFunctionID() {
		Object lsFunctionIDObj = this.get("FunctionID");
		if (lsFunctionIDObj == null) {
			return "";
		}
		return lsFunctionIDObj.toString();
	}

	/**
	 * 设置xml的key / value参数
	 * 
	 * @param key
	 * @param value
	 */
	public void setParameter(String key, String value) {
		this.put(key, value);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getPrameter(String key) {
		Object lsValueObj = this.get(key);
		if (lsValueObj == null) {
			return "";
		}
		return lsValueObj.toString();
	}

	/**
	 * 
	 * @return
	 */
	public Map getParameters() {
		return this;
	}

	/**
	 * 
	 * @param listName
	 * @param listValue
	 */
	public void setList(String listName, List<Map> listValue) {
		this.put(listName, listValue);
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
	 * @param formate
	 */
	public void setFormate(String formate) {
		this.formate = formate;
	}

	/**
	 * 设置xml的root标识，可以不调用，默认为：Program
	 * 
	 * @param rootName
	 */
	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	/**
	 * 将map转成xml
	 * 
	 * @return
	 */
	public String toXml() {
		String xml = XMLUtils.map2xml(this, this.rootName, this.formate);
		this.paramXML = xml;
		return xml;
	}

	/**
	 * 
	 * @return
	 */
	public String getParamXML() {
		if (StringUtils.isBlank(this.paramXML)) {
			this.toXml();
		}
		return this.paramXML;
	}
	
	/**
	 * 医疗机构编码
	 * 
	 * @return
	 */
	public String getAkb020() {
		return (String) this.get(akb020_key);
	}

	/**
	 * 医疗机构编码
	 * 
	 * @param akb020
	 */
	public void setAkb020(String akb020) {
		this.put(akb020_key, akb020);
	}

	/**
	 * 统筹区编码
	 * 
	 * @return
	 */
	public String getAaa027() {
		return (String) this.get(aaa027_key);
	}

	/**
	 * 统筹区编码
	 * 
	 * @param aaa027
	 */
	public void setAaa027(String aaa027) {
		this.put(aaa027_key, aaa027);
	}

	/**
	 * 参保人所属行政区划代码
	 * 
	 * @return
	 */
	public String getAab301() {
		return (String) this.get(aab301_key);
	}

	/**
	 * 参保人所属行政区划代码
	 * 
	 * @param aab301
	 */
	public void setAab301(String aab301) {
		this.put(aab301_key, aab301);
	}
}
