package com.powersi.biz.medicare.inhospital.service.xml;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * 
 * @author 刘勇
 *
 */
public interface XMLService extends Serializable {

	@SuppressWarnings("rawtypes")
	public List splitXMLString(String xml, String tag);

	/**
	 * 
	 * @param strValue
	 * @param iFlag
	 * @return
	 */
	public String transMarkupChar(String strValue, int iFlag);

	/**
	 * 
	 * @param xml
	 * @param tag
	 * @return
	 */
	public String outer(String xml, String tag);

	/**
	 * 
	 * @param xml
	 * @param tag
	 * @return
	 */
	public String inner(String xml, String tag);

	/**
	 * 
	 * @param document
	 * @param encoding
	 * @return
	 */
	public String document2string(Document document, String encoding);

	/**
	 * 
	 * @param map
	 * @param node
	 * @param childNodeName
	 */
	@SuppressWarnings("rawtypes")
	public void map2xml(Map map, Element node, String childNodeName);

	/**
	 * 
	 * @param map
	 * @param rootNodeName
	 * @param childNodeName
	 * @param encoding
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String map2xml(Map map, String rootNodeName, String childNodeName, String encoding);

	/**
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String map2xml(Map map);

	/**
	 * 
	 * @param list
	 * @param node
	 * @param childNodeName
	 * @param unstandard
	 */
	@SuppressWarnings("rawtypes")
	public void list2xml(List list, Element node, String childNodeName, String unstandard);

	/**
	 * 
	 * @param list
	 * @param name
	 * @param unstandard
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String list2xml(List list, String name, String unstandard);

	/**
	 * 
	 * @param list
	 * @param name
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String list2xml(List list, String name);

	/**
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String list2xml(List list);

	/**
	 * 
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map xml2map(String xml);

	/**
	 * 
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List xml2list(String xml);

}
