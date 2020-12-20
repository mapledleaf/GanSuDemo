package com.powersi.biz.medicare.comm.pojo;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.powersi.comm.mybatis.Page;

/**
 * 
 * @author 刘勇
 *
 */
public class RemotingDTO extends Page implements IPOJO {

	private static final long serialVersionUID = 1L;

	private String begin_date;
	private String end_date;
	private Date begindate;
	private Date enddate;

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

	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public Date getBegindate() {
		return begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
