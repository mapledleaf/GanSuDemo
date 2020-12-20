package com.powersi.hygeia.comm.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;

import com.alibaba.fastjson.JSON;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.pojo.ListResultDTO;
import com.powersi.comm.utils.XMLUtils;

/**
 * 根据公司xml服务返回值，构建访问对象
 * 
 * @author 李志钢
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RemoteCallResult extends HashMap {

	private static final long serialVersionUID = 1L;
	private String resultXML = null;

	/**
	 * 将xml转成map
	 * 
	 * @param xml
	 */
	public RemoteCallResult(String xml) {
		try {
			if (StringUtils.isNotBlank(xml)) {
				this.resultXML = xml;
				this.putAll(XMLUtils.xml2map(xml));
			}
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}

	/**
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
	public String getErrorType() {
		Object lsErrorTypeObj = this.get("ErrorType");
		if (lsErrorTypeObj == null) {
			return "";
		}
		return lsErrorTypeObj.toString();
	}

	/**
	 * 
	 * @return
	 */
	public String getErrorNo() {
		Object lsErrorNoObj = this.get("ErrorNo");
		if (lsErrorNoObj == null) {
			return "";
		}
		return lsErrorNoObj.toString();
	}

	/**
	 * 
	 * @return
	 */
	public String getErrorMessage() {
		Object lsErrorMessageObj = this.get("ErrorMessage");
		if (lsErrorMessageObj == null) {
			return "";
		}
		return this.thinErrorMessage(lsErrorMessageObj.toString());
	}

	/**
	 * 
	 * @return
	 */
	public String getException() {
		Object lsExceptionObj = this.get("Exception");
		if (lsExceptionObj == null) {
			return "";
		}
		return this.thinErrorMessage(lsExceptionObj.toString());
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
	 * 获取分页数据，配合结算云中心端Pagination对象使用
	 * 
	 * @author lwyao
	 * @return
	 */
	public ListResult getPageList() {
		return ListResultDTO.newInstance().setList((List<Map>) JSON.parse(getParameter("_list")))
				.setCount(this.getParameters().containsKey("_count") ? Integer.parseInt(this.getValue("_count")) : 0);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		Object lsValueObj = this.getParameters().get(key);
		if (lsValueObj == null) {
			return "";
		}
		return lsValueObj.toString();

	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getParameter(String key) {
		return this.getValue(key);
	}

	/**
	 * 
	 * @return
	 */
	public Map getParameters() {
		if (this.get("parameters") instanceof Map) {
			return (Map) this.get("parameters");
		}
		return Collections.EMPTY_MAP;
	}

	/**
	 * 
	 * @param message
	 * @return
	 */
	private String thinErrorMessage(String message) {
		String lsmessage = message;
		if (StringUtils.isNotBlank(lsmessage)) {
			lsmessage = lsmessage.replaceAll("java.lang.RuntimeException", "");
		}
		if (StringUtils.isNotBlank(lsmessage)) {
			lsmessage = lsmessage.replaceAll("java.lang.NullPointerException", "");
		}
		if (StringUtils.isNotBlank(lsmessage)) {
			lsmessage = lsmessage.replaceAll("com.powersi.biz.core.ws.BizException", "");
		}
		if (StringUtils.isNotBlank(lsmessage)) {
			lsmessage = lsmessage.replaceAll("com.powersi.hygeia.framework.exception.HygeiaException", "");
		}
		if (StringUtils.isNotBlank(lsmessage)) {
			lsmessage = lsmessage.replaceAll("com.powersi.comm.exception.ApusException", "");
		}
		int index = -1;
		if (StringUtils.isNotBlank(lsmessage)) {
			index = -1;
			index = lsmessage.indexOf("at ");
			if (index != -1) {
				lsmessage = lsmessage.substring(0, index);
			}
		}
		if (StringUtils.isNotBlank(lsmessage)) {
			index = -1;
			index = lsmessage.indexOf("<");
			if (index != -1) {
				lsmessage = lsmessage.substring(0, index);
			}
		}
		if (StringUtils.isNotBlank(lsmessage)) {
			return lsmessage;
		}
		return message;
	}

}
