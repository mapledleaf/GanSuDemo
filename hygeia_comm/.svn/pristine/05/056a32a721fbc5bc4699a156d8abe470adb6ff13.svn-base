package com.powersi.biz.utils;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * 医保
 * 
 * @author 刘勇
 *
 */
public final class ToolsM {

	public static final int BUFFER = 2 * 1024;

	public static final String EXT = ".gz";

	public static final String _yyyyMMdd = "yyyy-MM-dd";

	public static final String yyyyMMdd = "yyyyMMdd";

	public static final String _yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

	public static final String _yyyyMMddHHmmssSSSSSS = "yyyy-MM-dd HH:mm:ss.SSSSSS";

	public static final String yyyyMMddHHmmssSSSSSS = "yyyyMMddHHmmssSSSSSS";

	public static final String _yyyyMMddHHmmssSSS = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

	/**
	 * 
	 * @return
	 */
	public static final String uuid() {
		return uuid("");
	}

	/**
	 * 
	 * @param prefix
	 * @return
	 */
	public static final String uuid(String prefix) {
		StringBuilder sb = new StringBuilder();
		sb.append(prefix);
		sb.append(UUID.randomUUID().toString().toLowerCase());
		return sb.toString();
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	public static final String excstr(Throwable e) {
		if (e == null) {
			return "";
		}
		String excstr = "";
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		try {
			e.printStackTrace(printWriter);
			printWriter.flush();
			stringWriter.flush();
			printWriter.close();
			stringWriter.close();
			excstr = stringWriter.toString();
			printWriter = null;
			stringWriter = null;
		} catch (Throwable e1) {
			throw new RuntimeException(e1);
		} finally {
			if (printWriter != null) {
				printWriter.close();
				printWriter = null;
			}
			if (stringWriter != null) {
				try {
					stringWriter.close();
					stringWriter = null;
				} catch (Throwable e2) {
					throw new RuntimeException(e2);
				}
			}
		}
		return excstr;
	}

	/**
	 * 
	 * @param map
	 * @param key
	 * @param defaultvalue
	 * @return
	 */
	public static final String gtvalue(Map<?, ?> map, Object key, String defaultvalue) {
		if (map == null) {
			return defaultvalue;
		}
		if (key == null) {
			return defaultvalue;
		}
		Object value = map.get(key);
		if (value == null) {
			return defaultvalue;
		}
		if (StringUtils.isBlank(value.toString())) {
			return defaultvalue;
		}
		return value.toString();
	}

	/**
	 * 
	 * @param map
	 * @param key
	 * @param defaultvalue
	 * @return
	 */
	public static final String gtvalue(Map<?, ?> map, String key, String defaultvalue) {
		return gtvalue(map, (Object) key, defaultvalue);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static final boolean isutf8(String key) {
		if (key == null || "".equals(key.trim())) {
			return false;
		}
		Pattern pattern = Pattern.compile(
				"^[a-z0-9A-Z\u4e00-\u9fa5\\w\\-\\——\\*\\$\\%\\#\\@\\!\\&\\^\\/\\\\\\(\\)\\（\\）\\、\\。\\.\\，\\,\\;\\；\\:\\：\\'\\|]+$");
		return pattern.matcher(key).find();
	}

	/**
	 * 
	 * @param birthday
	 * @return
	 */
	public static final int age(Date birthday) {
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		Calendar birth = Calendar.getInstance();
		birth.setTime(birthday);
		if (birth.after(now)) {
			return 0;
		}
		if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
			return now.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + 1;
		}
		return now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
	}

	/**
	 * 
	 * @param dnumber
	 * @param iprecision
	 * @return
	 */
	public static final double round(double dnumber, int iprecision) {
		double nTemp1 = 0.0D;
		double nTemp2 = 0.0D;
		nTemp1 = Math.abs(dnumber);
		nTemp2 = (new BigDecimal(nTemp1)).setScale(iprecision, 4).doubleValue();
		if (nTemp1 - nTemp2 - 0.5D * Math.pow(10D, -1 * iprecision) < 9.9999999999999995E-08D
				* Math.pow(10D, -1 * (iprecision - 2))
				&& nTemp1 - nTemp2 - 0.5D * Math.pow(10D, -1 * iprecision) > -9.9999999999999995E-08D
						* Math.pow(10D, -1 * (iprecision - 2))) {
			nTemp1 = (new BigDecimal(nTemp2 + Math.pow(10D, -1 * iprecision))).setScale(iprecision, 4).doubleValue();
		} else {
			nTemp1 = (new BigDecimal(nTemp2)).setScale(iprecision, 4).doubleValue();
		}
		return nTemp1 * (double) signum(dnumber);
	}

	/**
	 * 
	 * @param a
	 * @return
	 */
	public static final int signum(double a) {
		return a != 0.0D ? a <= 0.0D ? -1 : 1 : 0;
	}

	/**
	 * 
	 * @param a
	 * @return
	 */
	public static final int signum(int a) {
		return a != 0 ? a <= 0 ? -1 : 1 : 0;
	}

	/**
	 * 
	 * @param money
	 * @return
	 */
	public static final String decimalplaces2(String money) {
		String strmoney = String.valueOf(round(Double.parseDouble(money), 2));
		String str = ".";
		int index = strmoney.indexOf(str);
		if (index == -1) {
			return strmoney + ".00";
		}
		if (strmoney.length() == index + 1) {
			return strmoney + "00";
		}
		if (strmoney.length() == index + 2) {
			return strmoney + "0";
		}
		if (strmoney.length() == index + 3) {
			return strmoney;
		}
		return money;
	}

	/**
	 * 
	 * @param asc
	 * @return
	 */
	public static final char ascii2char(int asc) {
		return (char) asc;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static final int char2ascii(char c) {
		return (int) c;
	}

	/**
	 * 
	 * @param ec2
	 * @return
	 */
	public static final String crypt(String ec2) {
		if (StringUtils.isBlank(ec2)) {
			return "";
		}
		int liRet = 0;
		int liLen = 0;
		int liAsc = 0;
		liLen = ec2.length();
		liRet = char2ascii(ec2.substring(0, 1).toCharArray()[0]) % 10;
		StringBuilder sb = new StringBuilder();
		for (int i = 3; i <= liLen;) {
			liAsc = char2ascii(ec2.substring(i - 1, i).toCharArray()[0]);
			if ((char2ascii(ec2.substring(i - 2, i - 1).toCharArray()[0]) % 2) == 0) {
				sb.append(ascii2char(liAsc + (i - 1) / 2 + liRet));
			} else {
				sb.append(ascii2char(liAsc - (i - 1) / 2 - liRet));
			}
			i = i + 2;
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static final Date todate(String date, String format) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return simpleDateFormat.parse(date);
		} catch (Throwable e) {
			return null;
		}
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static final String date2string(Date date, String format) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return simpleDateFormat.format(date);
		} catch (Throwable e) {
			return "";
		}
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static final boolean isvalidate(String date, String format) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return date.equals(simpleDateFormat.format(simpleDateFormat.parse(date)));
		} catch (Throwable e) {
			return false;
		}
	}

	/**
	 * 
	 * @param xml
	 * @param tag
	 * @return
	 */
	public static final String outer(String xml, String tag) {
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

	/**
	 * 
	 * @param document
	 * @param encoding
	 * @return
	 */
	private static final String document2string(Document document, String encoding) {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			OutputFormat outputFormat = new OutputFormat("", false, encoding);
			XMLWriter xmlWriter = new XMLWriter(byteArrayOutputStream, outputFormat);
			xmlWriter.write(document);
			return byteArrayOutputStream.toString(encoding);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param list
	 * @param node
	 * @param childnodename
	 * @param unstandard
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static final void list2xml(List list, Element node, String childnodename, String unstandard) {
		String tempName = "";
		if (StringUtils.isBlank(childnodename)) {
			tempName = "row";
		} else {
			tempName = childnodename;
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

	/**
	 * 
	 * @param map
	 * @param node
	 * @param childNodeName
	 */
	@SuppressWarnings("rawtypes")
	private static final void map2xml(Map map, Element node, String childNodeName) {
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

	/**
	 * 
	 * @param xml
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Map xml2map(String xml) {
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
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param xml
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final List xml2list(String xml) {
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
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param list
	 * @param name
	 * @param unstandard
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static final String list2xml(List list, String name, String unstandard) {
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

	/**
	 * 
	 * @param list
	 * @param name
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static final String list2xml(List list, String name) {
		return list2xml(list, name, null);
	}

	/**
	 * 
	 * @param map
	 * @param rootNodeName
	 * @param childNodeName
	 * @param encoding
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static final String map2xml(Map map, String rootNodeName, String childNodeName, String encoding) {
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
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static final String map2xml(Map map) {
		return map2xml(map, null, null, null);
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static final String list2xml(List list) {
		return list2xml(list, null, null);
	}

	/**
	 * 
	 * @param strvalue
	 * @param flag
	 * @return
	 */
	public static final String markup(String strvalue, int flag) {
		String[] strMarkupCharArray = { "&", ">", "<", "\"", "'" };
		String[] strEntityCharArray = { "&amp;", "&gt;", "&lt;", "&quot;", "&apos;" };
		if (flag == 1) {
			for (int i = 0; i < strMarkupCharArray.length; i++) {
				strvalue = strvalue.replaceAll(strMarkupCharArray[i], strEntityCharArray[i]);
			}
		} else {
			for (int i = strMarkupCharArray.length - 1; i >= 0; i--) {
				strvalue = strvalue.replaceAll(strEntityCharArray[i], strMarkupCharArray[i]);
			}
		}
		return strvalue;
	}

	/**
	 * 
	 * @param xml
	 * @param tag
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final List splitxmlstr(String xml, String tag) {
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

	/**
	 * 
	 * @param xml
	 * @param tag
	 * @return
	 */
	public static final String inner(String xml, String tag) {
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

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static final byte[] compress(byte[] data) {
		ByteArrayInputStream byteArrayInputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(data);
			byteArrayOutputStream = new ByteArrayOutputStream();
			compress(byteArrayInputStream, byteArrayOutputStream);
			byteArrayOutputStream.flush();
			return byteArrayOutputStream.toByteArray();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.close();
					byteArrayOutputStream = null;
				}
				if (byteArrayInputStream != null) {
					byteArrayInputStream.close();
					byteArrayInputStream = null;
				}
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static final byte[] decompress(byte[] data) {
		ByteArrayInputStream byteArrayInputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(data);
			byteArrayOutputStream = new ByteArrayOutputStream();
			decompress(byteArrayInputStream, byteArrayOutputStream);
			byteArrayOutputStream.flush();
			return byteArrayOutputStream.toByteArray();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.close();
					byteArrayOutputStream = null;
				}
				if (byteArrayInputStream != null) {
					byteArrayInputStream.close();
					byteArrayInputStream = null;
				}
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 
	 * @param inputStream
	 * @param outputStream
	 */
	private static final void compress(InputStream inputStream, OutputStream outputStream) {
		GZIPOutputStream gZIPOutputStream = null;
		try {
			gZIPOutputStream = new GZIPOutputStream(outputStream);
			int count = 0;
			byte[] data = new byte[BUFFER];
			while ((count = inputStream.read(data, 0, BUFFER)) != -1) {
				gZIPOutputStream.write(data, 0, count);
			}
			gZIPOutputStream.finish();
			gZIPOutputStream.flush();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (gZIPOutputStream != null) {
					gZIPOutputStream.close();
					gZIPOutputStream = null;
				}
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 
	 * @param inputStream
	 * @param outputStream
	 */
	private static final void decompress(InputStream inputStream, OutputStream outputStream) {
		GZIPInputStream gZIPInputStream = null;
		try {
			gZIPInputStream = new GZIPInputStream(inputStream);
			int count = 0;
			byte[] data = new byte[BUFFER];
			while ((count = gZIPInputStream.read(data, 0, BUFFER)) != -1) {
				outputStream.write(data, 0, count);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (gZIPInputStream != null) {
					gZIPInputStream.close();
					gZIPInputStream = null;
				}
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 
	 * @param file
	 * @param isdelete
	 */
	public static final void compress(File file, boolean isdelete) {
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			fileOutputStream = new FileOutputStream(file.getPath() + EXT);
			compress(fileInputStream, fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
			fileOutputStream = null;
			fileInputStream.close();
			fileInputStream = null;
			if (isdelete) {
				file.delete();
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
					fileOutputStream = null;
				}
				if (fileInputStream != null) {
					fileInputStream.close();
					fileInputStream = null;
				}
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 
	 * @param file
	 * @param isdelete
	 */
	public static final void decompress(File file, boolean isdelete) {
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			fileOutputStream = new FileOutputStream(file.getPath().replace(EXT, ""));
			decompress(fileInputStream, fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
			fileOutputStream = null;
			fileInputStream.close();
			fileInputStream = null;
			if (isdelete) {
				file.delete();
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
					fileOutputStream = null;
				}
				if (fileInputStream != null) {
					fileInputStream.close();
					fileInputStream = null;
				}
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 
	 * @param srcfiles
	 * @param outputstream
	 */
	public static final void tozip(List<File> srcfiles, OutputStream outputstream) {
		ZipOutputStream zipOutputStream = null;
		try {
			zipOutputStream = new ZipOutputStream(outputstream);
			for (File srcfile : srcfiles) {
				zipOutputStream.putNextEntry(new ZipEntry(srcfile.getName()));
				int len = -1;
				byte[] buf = new byte[BUFFER];
				FileInputStream fileInputStream = new FileInputStream(srcfile);
				while ((len = fileInputStream.read(buf)) != -1) {
					zipOutputStream.write(buf, 0, len);
				}
				zipOutputStream.closeEntry();
				fileInputStream.close();
				fileInputStream = null;
			}
			zipOutputStream.finish();
			zipOutputStream.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (zipOutputStream != null) {
				try {
					zipOutputStream.close();
					zipOutputStream = null;
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * 
	 * @param bean
	 * @param field
	 * @param value
	 * @param ignorenull
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	private static final void setProperty(Object bean, String field, Object value, boolean ignorenull)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (StringUtils.isBlank(field)) {
			return;
		}
		if ("class".equalsIgnoreCase(field)) {
			return;
		}
		if (bean instanceof Map) {
			if (StringUtils.isBlank(ObjectUtils.toString(value)) && ignorenull) {

			} else {
				if (value instanceof Date) {
					value = date2string((Date) value, yyyyMMdd);
				}
				((Map) bean).put(field, value);
			}
		} else {
			PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(bean, field);
			if (propertyDescriptor == null) {
				return;
			}
			String simpleName = propertyDescriptor.getPropertyType().getSimpleName();
			if (StringUtils.isBlank(simpleName)) {
				return;
			}
			if (StringUtils.isBlank(ObjectUtils.toString(value))) {
				if (ignorenull) {

				} else {
					PropertyUtils.setProperty(bean, field, value);
				}
			} else {
				if (simpleName.equalsIgnoreCase("Date") || simpleName.equalsIgnoreCase("Timestamp")) {
					if (value instanceof Date) {
						PropertyUtils.setProperty(bean, field, value);
					} else if (isvalidate(ObjectUtils.toString(value), yyyyMMdd)) {
						PropertyUtils.setProperty(bean, field, todate(ObjectUtils.toString(value), yyyyMMdd));
					} else {
						PropertyUtils.setProperty(bean, field, value);
					}
				} else {
					if (value instanceof Date) {
						value = date2string((Date) value, yyyyMMdd);
					}
					if (simpleName.equalsIgnoreCase("BigDecimal")) {
						if (NumberUtils.isNumber(ObjectUtils.toString(value))) {
							PropertyUtils.setProperty(bean, field, new BigDecimal(ObjectUtils.toString(value)));
						}
					} else if (simpleName.equalsIgnoreCase("String")) {
						PropertyUtils.setProperty(bean, field, ObjectUtils.toString(value));
					} else if (simpleName.equalsIgnoreCase("Integer") || simpleName.equalsIgnoreCase("int")) {
						if (NumberUtils.isNumber(ObjectUtils.toString(value))) {
							PropertyUtils.setProperty(bean, field, Integer.valueOf(ObjectUtils.toString(value)));
						}
					} else if (simpleName.equalsIgnoreCase("Boolean")) {
						PropertyUtils.setProperty(bean, field, Boolean.valueOf(ObjectUtils.toString(value)));
					} else if (simpleName.equalsIgnoreCase("Long")) {
						if (NumberUtils.isNumber(ObjectUtils.toString(value))) {
							PropertyUtils.setProperty(bean, field, Long.valueOf(ObjectUtils.toString(value)));
						}
					} else if (simpleName.equalsIgnoreCase("Float")) {
						if (NumberUtils.isNumber(ObjectUtils.toString(value))) {
							PropertyUtils.setProperty(bean, field, Float.valueOf(ObjectUtils.toString(value)));
						}
					} else if (simpleName.equalsIgnoreCase("Double")) {
						if (NumberUtils.isNumber(ObjectUtils.toString(value))) {
							PropertyUtils.setProperty(bean, field, Double.valueOf(ObjectUtils.toString(value)));
						}
					} else if (simpleName.equalsIgnoreCase("Short")) {
						if (NumberUtils.isNumber(ObjectUtils.toString(value))) {
							PropertyUtils.setProperty(bean, field, Short.valueOf(ObjectUtils.toString(value)));
						}
					} else {
						if (!simpleName.equalsIgnoreCase("Class")) {
							throw new RuntimeException("UnSupport Type " + simpleName);
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param source
	 * @param target
	 */
	@SuppressWarnings("rawtypes")
	public static final void copyProperties(Object source, Object target) {
		if (source == null || target == null) {
			return;
		}
		if (target instanceof Map) {
			copyPropertiesall(source, target, true);
		} else {
			Map temp = new HashMap();
			copyPropertiesall(source, temp, true);
			copyPropertiesall(temp, target, true);
		}
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @param ignorenull
	 */
	@SuppressWarnings("rawtypes")
	public static final void copyProperties(Object source, Object target, boolean ignorenull) {
		if (source == null || target == null) {
			return;
		}
		if (target instanceof Map) {
			copyPropertiesall(source, target, ignorenull);
		} else {
			Map temp = new HashMap();
			copyPropertiesall(source, temp, ignorenull);
			copyPropertiesall(temp, target, ignorenull);
		}
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @param ignorenull
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static final void copyPropertiesall(Object source, Object target, boolean ignorenull) {
		try {
			if (source instanceof Map) {
				Set<Entry<String, Object>> entrySet = ((Map) source).entrySet();
				Entry<String, Object> entry = null;
				for (Iterator<Entry<String, Object>> iterator = entrySet.iterator(); iterator.hasNext();) {
					entry = iterator.next();
					setProperty(target, entry.getKey(), entry.getValue(), ignorenull);
				}
			} else {
				PropertyDescriptor[] sourceProperties = PropertyUtils.getPropertyDescriptors(source);
				String sourcefieldName = null;
				Object sourcefieldValue = null;
				for (int i = 0; i < sourceProperties.length; i++) {
					sourcefieldName = sourceProperties[i].getName();
					sourcefieldValue = PropertyUtils.getProperty(source, sourcefieldName);
					setProperty(target, sourcefieldName, sourcefieldValue, ignorenull);
				}
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @param propertyname
	 */
	@SuppressWarnings({ "rawtypes" })
	public static final void copyProperties(Object source, Object target, String[] propertyname) {
		if (source == null || target == null) {
			return;
		}
		if (target instanceof Map) {
			copyPropertiesall(source, target, propertyname);
		} else {
			Map temp = new HashMap();
			copyPropertiesall(source, temp, propertyname);
			copyPropertiesall(temp, target, propertyname);
		}
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @param propertyname
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static final void copyPropertiesall(Object source, Object target, String[] propertyname) {
		try {
			if (source instanceof Map) {
				Set<Entry<String, Object>> entrySet = ((Map) source).entrySet();
				Entry<String, Object> entry = null;
				for (Iterator<Entry<String, Object>> iterator = entrySet.iterator(); iterator.hasNext();) {
					entry = iterator.next();
					for (String colName : propertyname) {
						if (entry.getKey().equals(colName)) {
							setProperty(target, entry.getKey(), entry.getValue(), false);
						}
					}
				}
			} else {
				PropertyDescriptor[] sourceProperties = PropertyUtils.getPropertyDescriptors(source);
				String sourcefieldName = null;
				Object sourcefieldValue = null;
				for (int i = 0; i < sourceProperties.length; i++) {
					sourcefieldName = sourceProperties[i].getName();
					for (String colName : propertyname) {
						if (sourcefieldName.equals(colName)) {
							sourcefieldValue = PropertyUtils.getProperty(source, sourcefieldName);
							setProperty(target, sourcefieldName, sourcefieldValue, false);
						}
					}
				}
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static final void main(String[] args) {
		try {
			System.out.println(uuid());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
