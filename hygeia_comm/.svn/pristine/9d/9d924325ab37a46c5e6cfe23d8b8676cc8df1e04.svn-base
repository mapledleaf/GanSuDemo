package com.powersi.hygeia.comm.dto;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powersi.biz.utils.ConstantUtils;
import com.powersi.comm.utils.XMLUtils;

/**
 * 服务于公司模式的xml服务请求参数封装类
 * 
 * @author 李志钢
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RemoteCallParam extends LinkedHashMap {

	private static final long serialVersionUID = 1L;
	/**
	 * 医保格式，list的key带行号<row1><row2>...
	 */
	private String formate = "yb";
	private String rootName = null;
	private Map parameters = new LinkedHashMap();
	private List<LinkedHashMap> userinfos = Collections.singletonList(new LinkedHashMap());
	private String paramXML = null;

	public RemoteCallParam() {
		this("");
	}

	/**
	 * @param funcitionID (功能号)
	 */
	public RemoteCallParam(String funcitionID) {
		this(funcitionID, null);
	}

	/**
	 * @param funcitionID (功能号)
	 * @param param       (参数)
	 */
	public RemoteCallParam(String funcitionID, Map<?, ?> param) {
		this.setFuncitionID(funcitionID);
		this.put("parameters", this.parameters);
		if (!"BIZC201501".equals(funcitionID) && !"BIZC200902".equals(funcitionID)) {
			this.put("user_info", this.userinfos);
		}
		if (param != null) {
			parameters.putAll(param);
		}
		if (StringUtils.isBlank(getPrameter("caa027"))) {// 默认一个caa027
			setParameter("caa027", ConstantUtils.CAA027);
		}
	}

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
		this.parameters.put(key, value);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getPrameter(String key) {
		Object lsValueObj = this.parameters.get(key);
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
		return this.parameters;
	}

	/**
	 * 设置xml的key / value参数
	 * 
	 * @param key
	 * @param value
	 */
	public void setUserinfo(String key, String value) {
		Map userinfo = this.userinfos.get(0);
		userinfo.put(key, value);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getUserinfo(String key) {
		Map userinfo = this.userinfos.get(0);
		Object lsValueObj = userinfo.get(key);
		if (lsValueObj == null) {
			return "";
		}
		return lsValueObj.toString();
	}

	/**
	 * 
	 * @return
	 */
	public Map getUserinfo() {
		Map userinfo = this.userinfos.get(0);
		return userinfo;
	}

	/**
	 * listkey=inputs
	 * 
	 * @author lwyao
	 * @date 2018年11月8日
	 * @param listValue
	 */
	public void setList(List<Map> listValue) {
		setList("inputs", listValue);
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

}
