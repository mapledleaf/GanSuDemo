package com.powersi.ssm.biz.medicare.common.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 报表数据对象
 * 
 * 2017年5月17日下午3:05:42
 *
 * @author lwyao
 *
 */
@SuppressWarnings("serial")
public class ReportData extends LinkedHashMap<String, Object> {
	Map<String, Object> head = new LinkedHashMap<String, Object>();
	Map<String, Object> foot = new LinkedHashMap<String, Object>();

	public ReportData() {
		this.put("head", Collections.singletonList(this.head));
		this.put("foot", Collections.singletonList(this.foot));
	}

	/**
	 * 获取报表数据集
	 * 
	 * @author lwyao
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getList(String key) {
		return (List<Map<String, Object>>) this.get(key);
	}

	/**
	 * 设置报表数据集
	 * 
	 * @author lwyao
	 * @param key
	 * @param datas
	 */
	public void setList(String key, List<Map<String, Object>> datas) {
		this.put(key, datas);
	}

	public Map<String, Object> getHead() {
		return head;
	}

	public void setHead(Map<String, Object> head) {
		this.head = head;
	}

	/**
	 * 设置报表表头字段
	 * 
	 * @author lwyao
	 * @param key
	 * @param value
	 */
	public void setHeadValue(String key, Object value) {
		this.head.put(key, value);
	}

	/**
	 * 获取报表表头字段
	 * 
	 * @author lwyao
	 * @param key
	 * @return
	 */
	public Object getHeadValue(String key) {
		return this.head.get(key);
	}

	public Map<String, Object> getFoot() {
		return foot;
	}

	public void setFoot(Map<String, Object> foot) {
		this.foot = foot;
	}

	/**
	 * 设置报表页脚字段
	 * 
	 * @author lwyao
	 * @param key
	 * @param value
	 */
	public void setFootValue(String key, Object value) {
		this.foot.put(key, value);
	}

	/**
	 * 获取报表页脚字段
	 * 
	 * @author lwyao
	 * @param key
	 * @return
	 */
	public Object getFootValue(String key) {
		return this.foot.get(key);
	}

}