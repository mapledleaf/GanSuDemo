package com.powersi.biz.medicare.cumulative.pojo;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.powersi.biz.medicare.comm.pojo.IPOJO;

/**
 * 个人医疗累计要素集合
 * 
 * @author zhos
 *
 */
@SuppressWarnings("rawtypes")
public class PersonalCumulativeElementDTO extends HashMap implements IPOJO {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param cumulativeElementCode
	 *            个人医疗累计要素编码
	 * @return 个人医疗累计要素值
	 */
	public String getCumulativeElement(String cumulativeElementCode) {
		return (String) this.get(cumulativeElementCode);
	}

	/**
	 * 
	 * @param cumulativeElementCode
	 *            个人医疗累计要素编码
	 * @param cumulativeElementValue
	 *            个人医疗累计要素值
	 */
	@SuppressWarnings("unchecked")
	public void setCumulativeElement(String cumulativeElementCode, String cumulativeElementValue) {
		this.put(cumulativeElementCode, cumulativeElementValue);
	}

	@Override
	public String toString() {
		return this.toJson();
	}

	@Override
	public String toJson() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}

	@Override
	public boolean compareTo(Object obj) {
		return this.toString().equals(obj.toString());
	}

}
