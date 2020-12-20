package com.powersi.biz.medicare.query.dto;

import java.util.Date;


public class KC73 extends POJO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String kc73id;// varchar2(50) not null,【ID】
	private String bka588;// varchar2(50) not null,【静态要素代码】
	private String bka587;// varchar2(500) not null,【静态要素名称】
	private String bka595;// varchar2(50), 【静态要素加载条件ID(为空默认自动加载)】
	private String bka590;// varchar2(500), 【静态要素值加载实现类】
	private Date aae030;// date not null,【生效日期】
	private Date aae031;// date not null,【失效日期】
	private Date aae037;// date not null,【维护日期】
	private Integer bka594;// number(12) not null, 【静态要素加载顺序】
	private String bka589;// varchar2(6) not null,【缓存标识1是0否(默认0(不使用))】
	private String aae013;// varchar2(500) 【备注】
	private String aae100;// varchar2(1) default '1' not null,【数据有效标识】
	
	
	public String getKc73id() {
		return kc73id;
	}
	public void setKc73id(String kc73id) {
		this.kc73id = kc73id;
	}
	public String getBka588() {
		return bka588;
	}
	public void setBka588(String bka588) {
		this.bka588 = bka588;
	}
	public String getBka587() {
		return bka587;
	}
	public void setBka587(String bka587) {
		this.bka587 = bka587;
	}
	public String getBka595() {
		return bka595;
	}
	public void setBka595(String bka595) {
		this.bka595 = bka595;
	}
	public String getBka590() {
		return bka590;
	}
	public void setBka590(String bka590) {
		this.bka590 = bka590;
	}
	public Date getAae030() {
		return aae030;
	}
	public void setAae030(Date aae030) {
		this.aae030 = aae030;
	}
	public Date getAae031() {
		return aae031;
	}
	public void setAae031(Date aae031) {
		this.aae031 = aae031;
	}
	public Date getAae037() {
		return aae037;
	}
	public void setAae037(Date aae037) {
		this.aae037 = aae037;
	}
	public Integer getBka594() {
		return bka594;
	}
	public void setBka594(Integer bka594) {
		this.bka594 = bka594;
	}
	public String getBka589() {
		return bka589;
	}
	public void setBka589(String bka589) {
		this.bka589 = bka589;
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
