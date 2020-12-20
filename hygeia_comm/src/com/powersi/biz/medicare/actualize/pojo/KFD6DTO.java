package com.powersi.biz.medicare.actualize.pojo;

import java.util.Date;

import com.powersi.comm.mybatis.Page;
/**
 * 实施计划总表 2017.9.20
 */
public class KFD6DTO extends Page{
	private static final long serialVersionUID = 1L;
	
	private String bkf306;    //实施计划id
	private String bkf318;    //实施计划名称
	private Date bkf319;    //建立计划时间
	private String aae014;    //建立人工号
	private String bae101;   //建立人姓名
	private Date bkf320;     //计划总体开始时间
	private Date bkf321;    //计划预计结束时间
	private String aae100;   //实施计划总体状态 1、激活 0、关闭
	private String aaa027;
	
	
	public String getAaa027() {
		return aaa027;
	}
	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}
	public String getBkf306() {
		return bkf306;
	}
	public void setBkf306(String bkf306) {
		this.bkf306 = bkf306;
	}
	public String getBkf318() {
		return bkf318;
	}
	public void setBkf318(String bkf318) {
		this.bkf318 = bkf318;
	}
	public Date getBkf319() {
		return bkf319;
	}
	public void setBkf319(Date bkf319) {
		this.bkf319 = bkf319;
	}
	public String getAae014() {
		return aae014;
	}
	public void setAae014(String aae014) {
		this.aae014 = aae014;
	}
	public String getBae101() {
		return bae101;
	}
	public void setBae101(String bae101) {
		this.bae101 = bae101;
	}
	public Date getBkf320() {
		return bkf320;
	}
	public void setBkf320(Date bkf320) {
		this.bkf320 = bkf320;
	}
	public Date getBkf321() {
		return bkf321;
	}
	public void setBkf321(Date bkf321) {
		this.bkf321 = bkf321;
	}
	public String getAae100() {
		return aae100;
	}
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}
	
}
