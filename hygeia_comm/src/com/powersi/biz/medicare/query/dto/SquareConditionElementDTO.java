package com.powersi.biz.medicare.query.dto;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * 结算单要素判断条件要素集合
 * 
 * @author 刘勇
 *
 */
@SuppressWarnings("rawtypes")
public class SquareConditionElementDTO extends HashMap implements IPOJO {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param squareConditionElementCode
	 *          结算单要素判断条件要素
	 * @return 结算单要素判断条件要素值
	 */
	public String getSquareConditionElement(String squareConditionElementCode) {

		return (String) this.get(squareConditionElementCode);
	}

	/**
	 * 
	 * @param squareConditionElementCode
	 *            静态要素判断条件要素
	 * @param squareConditionElementValue
	 *            静态要素判断条件要素值
	 */
	@SuppressWarnings("unchecked")
	public void setSquareConditionElement(String squareConditionElementCode,
			String squareConditionElementValue) {

		this.put(squareConditionElementCode, squareConditionElementValue);
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
