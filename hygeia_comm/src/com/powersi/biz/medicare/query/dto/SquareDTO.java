package com.powersi.biz.medicare.query.dto;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 个人医疗静态要素集合
 * 
 * @author 刘勇
 * 
 */
@SuppressWarnings("rawtypes")
public class SquareDTO extends HashMap implements IPOJO {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param squareCode
	 * @param squareValue
	 */
	@SuppressWarnings("unchecked")
	public void setSquare(String squareCode, String squareValue) {

		this.put(squareCode, squareValue);
	}

	/**
	 * 
	 * @param squareCode
	 * @return
	 */
	public String getSquare(String squareCode) {

		return (String) this.get(squareCode);
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
