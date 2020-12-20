package com.powersi.biz.medicare.comm.pojo;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.powersi.comm.mybatis.Page;

/**
 * 继承分页的基础bean
 * 
 * @author lwyao
 * 
 */
@SuppressWarnings("serial")
public class PageBaseBean extends Page implements Serializable {

	public String toString() {
		return toJson();
	}

	public String toJson() {
		Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}

	public boolean compareTo(Object o) {
		return toString().equals(o.toString());
	}

}
