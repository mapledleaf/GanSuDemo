package com.powersi.biz.medicare.comm.pojo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author 刘勇
 *
 */
public class POJO implements IPOJO {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return toJson();
	}

	@Override
	public String toJson() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}

	@Override
	public boolean compareTo(Object obj) {
		return toString().equals(obj.toString());
	}

}
