package com.powersi.biz.medicare.actualize.pojo;

import java.util.Date;

import com.powersi.comm.mybatis.Page;
/**
 * 实施计划日志表 2017.9.20
 */
public class KFD5DTO extends Page{
	private static final long serialVersionUID = 1L;
	
	private String bkf315;    //实施计划日志id
	private String bkf306;    //实施计划id
	private String bkf307;    //实施计划医院表id
	private String bkf309;    //实施计划明细id
	private String bkf311;    //his完成情况
	private String bkf312;    //联调情况
	private String bkf316;    //实施信息记录
	private String aae014;    //操作人工号
	private String bae101;   //操作人姓名
	private Date bkf317;    //记录时间
	
	public String getBkf315() {
		return bkf315;
	}
	public void setBkf315(String bkf315) {
		this.bkf315 = bkf315;
	}
	public String getBkf306() {
		return bkf306;
	}
	public void setBkf306(String bkf306) {
		this.bkf306 = bkf306;
	}
	public String getBkf307() {
		return bkf307;
	}
	public void setBkf307(String bkf307) {
		this.bkf307 = bkf307;
	}
	public String getBkf309() {
		return bkf309;
	}
	public void setBkf309(String bkf309) {
		this.bkf309 = bkf309;
	}
	public String getBkf311() {
		return bkf311;
	}
	public void setBkf311(String bkf311) {
		this.bkf311 = bkf311;
	}
	public String getBkf312() {
		return bkf312;
	}
	public void setBkf312(String bkf312) {
		this.bkf312 = bkf312;
	}
	public String getBkf316() {
		return bkf316;
	}
	public void setBkf316(String bkf316) {
		this.bkf316 = bkf316;
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
	public Date getBkf317() {
		return bkf317;
	}
	public void setBkf317(Date bkf317) {
		this.bkf317 = bkf317;
	}
	
}
