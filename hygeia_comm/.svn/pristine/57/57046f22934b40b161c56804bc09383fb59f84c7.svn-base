package com.powersi.biz.medicare.inhospital.service.xml;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;

/**
 * 
 * @author 刘勇
 *
 */
@Service
public class XMLServiceImpl implements XMLService {
	private static final long serialVersionUID = 1L;

	@Override
	public String outer(String xml, String tag) {
		if (StringUtils.isBlank(xml)) {
			return "";
		}
		if (StringUtils.isBlank(tag)) {
			return "";
		}
		int left = xml.indexOf("<" + tag + ">");
		if (left < 0) {
			return "";
		}
		int right = xml.indexOf("</" + tag + ">", left);
		if (right < 0) {
			return "";
		}
		return xml.substring(left, right + tag.length() + 3);
	}

	@Override
	public String document2string(Document document, String encoding) {
		try {
			String curencoding = "";
			if (StringUtils.isBlank(encoding)) {
				curencoding = "UTF-8";
			} else {
				curencoding = encoding;
			}
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			OutputFormat outputFormat = new OutputFormat("", false, curencoding);
			XMLWriter xmlWriter = new XMLWriter(byteArrayOutputStream, outputFormat);
			xmlWriter.write(document);
			return byteArrayOutputStream.toString(curencoding);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void list2xml(List list, Element node, String childNodeName, String unstandard) {
		String tempName = "";
		if (StringUtils.isBlank(childNodeName)) {
			tempName = "row";
		} else {
			tempName = childNodeName;
		}
		Element child = null, grandson = null;
		Object value = null;
		int i = 1;
		for (Map map : (List<Map>) list) {
			child = node.addElement(tempName + ("1".equals(unstandard) ? String.valueOf(i++) : ""));
			for (Object key : map.keySet()) {
				grandson = child.addElement((String) key);
				value = map.get(key);
				if (value == null) {
					grandson.setText("");
				} else if (value instanceof Map) {
					map2xml((Map) value, grandson, null);
				} else if (value instanceof List) {
					list2xml((List) value, grandson, tempName, unstandard);
				} else {
					grandson.setText(String.valueOf(value));
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void map2xml(Map map, Element node, String childNodeName) {
		Element child = null;
		Object value = null;
		for (Object key : map.keySet()) {
			child = node.addElement((String) key);
			value = map.get(key);
			if (value == null) {
				child.setText("");
			} else if (value instanceof Map) {
				map2xml((Map) value, child, childNodeName);
			} else if (value instanceof List) {
				list2xml((List) value, child, childNodeName, null);
			} else {
				child.setText(String.valueOf(value));
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map xml2map(String xml) {
		try {
			Document document = DocumentHelper.parseText(xml);
			Element rootNode = document.getRootElement();
			List elements = rootNode.elements();
			Element node = null;
			Map tempMap = null;
			Map returnMap = new HashMap();
			for (Iterator it = elements.iterator(); it.hasNext();) {
				node = (Element) it.next();
				tempMap = xml2map(node.asXML());
				if (tempMap != null && tempMap.size() > 0) {
					returnMap.put(node.getName(), tempMap);
				} else {
					returnMap.put(node.getName(), node.getText());
				}
			}
			return returnMap;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List xml2list(String xml) {
		try {
			Document document = DocumentHelper.parseText(xml);
			Element rootNode = document.getRootElement();
			List elements = rootNode.elements();
			Element element = null;
			Map tempMap = null;
			List returnList = new ArrayList();
			for (Iterator it = elements.iterator(); it.hasNext();) {
				element = (Element) it.next();
				tempMap = xml2map(element.asXML());
				if (tempMap != null && tempMap.size() > 0) {
					returnList.add(tempMap);
				}
			}
			return returnList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String list2xml(List list, String name, String unstandard) {
		String rootNodeName = "";
		if (StringUtils.isBlank(name)) {
			rootNodeName = "input";
		} else {
			rootNodeName = name;
		}
		Document document = DocumentHelper.createDocument();
		Element rootNode = document.addElement(rootNodeName);
		list2xml(list, rootNode, null, unstandard);
		return document2string(document, null);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String list2xml(List list, String name) {
		return list2xml(list, name, null);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String map2xml(Map map, String rootNodeName, String childNodeName, String encoding) {
		try {
			String tempName = null;
			if (StringUtils.isBlank(rootNodeName)) {
				tempName = "input";
			} else {
				tempName = rootNodeName;
			}
			Document document = DocumentHelper.createDocument();
			Element rootNode = document.addElement(tempName);
			Element node = null;
			Object vlaue = null;
			for (Object key : map.keySet()) {
				node = rootNode.addElement((String) key);
				vlaue = map.get(key);
				if (vlaue == null) {
					node.setText("");
				} else if (vlaue instanceof Map) {
					map2xml((Map) vlaue, node, childNodeName);
				} else if (vlaue instanceof List) {
					list2xml((List) vlaue, node, childNodeName, null);
				} else {
					node.setText(String.valueOf(vlaue));
				}
			}
			return document2string(document, encoding);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String map2xml(Map map) {
		return map2xml(map, null, null, null);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String list2xml(List list) {
		return list2xml(list, null, null);
	}

	@Override
	public String transMarkupChar(String strValue, int flag) {
		if (StringUtils.isBlank(strValue)) {
			return "";
		}
		String[] strMarkupCharArray = { "&", ">", "<", "\"", "'" };
		String[] strEntityCharArray = { "&amp;", "&gt;", "&lt;", "&quot;", "&apos;" };
		if (flag == 1) {
			for (int i = 0; i < strMarkupCharArray.length; i++) {
				strValue = strValue.replaceAll(strMarkupCharArray[i], strEntityCharArray[i]);
			}
		} else {
			for (int i = strMarkupCharArray.length - 1; i >= 0; i--) {
				strValue = strValue.replaceAll(strEntityCharArray[i], strMarkupCharArray[i]);
			}
		}
		return strValue;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List splitXMLString(String xml, String tag) {
		if (StringUtils.isBlank(xml) || StringUtils.isBlank(tag)) {
			throw new RuntimeException("分隔XML串或者分隔符不能为空");
		}
		List returnList = new ArrayList();
		int length = xml.length();
		int beginPos = 0;
		int endPos = 0;
		do {
			beginPos = xml.indexOf("<" + tag + ">", endPos);
			if (beginPos < 0) {
				break;
			}
			endPos = xml.indexOf("</" + tag + ">", beginPos);
			if (endPos < 0) {
				throw new RuntimeException("XML串没有" + tag + "结束标记");
			}
			endPos += tag.length() + 3;
			returnList.add(xml.substring(beginPos, endPos));
		} while (endPos < length);
		return returnList;
	}

	@Override
	public String inner(String xml, String tag) {
		if (StringUtils.isBlank(xml)) {
			return "";
		}
		if (StringUtils.isBlank(tag)) {
			return "";
		}
		int beginPos = 0;
		int endPos = 0;
		beginPos = xml.indexOf("<" + tag + ">", endPos);
		if (beginPos >= 0) {
			endPos = xml.indexOf("</" + tag + ">", beginPos);
			if (endPos >= 0) {
				return xml.substring(beginPos + tag.length() + 2, endPos);
			}
		}
		return "";
	}
}
