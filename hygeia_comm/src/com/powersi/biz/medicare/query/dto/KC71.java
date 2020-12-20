package com.powersi.biz.medicare.query.dto;

import java.util.Date;

public class KC71 extends POJO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String kc71id;// varchar2(50) not null,【ID】
	private String bka593;// varchar2(500) not null,【静态要素加载条件描述】
	private Date aae037;// date not null,【维护日期】
	private String aae013;// varchar2(500) 【备注】
	private String aae100;// varchar2(1) default '1' not null,【数据有效标识】
	
	
	public String getKc71id() {
		return kc71id;
	}
	public void setKc71id(String kc71id) {
		this.kc71id = kc71id;
	}
	public String getBka593() {
		return bka593;
	}
	public void setBka593(String bka593) {
		this.bka593 = bka593;
	}
	public Date getAae037() {
		return aae037;
	}
	public void setAae037(Date aae037) {
		this.aae037 = aae037;
	}
	public String getAae013() {
		return aae013;
	}
	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}
	public String getAae100() {
		return aae100;
	}
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	

}
