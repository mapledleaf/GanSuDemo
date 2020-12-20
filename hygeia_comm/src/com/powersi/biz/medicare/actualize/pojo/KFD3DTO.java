package com.powersi.biz.medicare.actualize.pojo;

import java.util.Date;

import com.powersi.comm.mybatis.Page;

public class KFD3DTO extends Page{

	private static final long serialVersionUID = 1L;

	private String bkf307;//实施计划医院表id
	private String bkf306;//实施计划id
	private String aaa027;//统筹区
	private String akb020;//医院编号
	private Date bka045;//计划完成时间
	private String aae100;//实施计划状态  1、激活 0、关闭
	private Date bkf308;//申请验收时间
	private Date aae015;//验收审核时间
	private String aae014;//验收人工号
	private String bae101;//验收人姓名
	private String aae013;//验收意见
	private String bkf318;//计划名称
	private String bkf325;//申请人工号
	private String bkf326;//申请人姓名
	
	
	public String getBkf325() {
		return bkf325;
	}
	public void setBkf325(String bkf325) {
		this.bkf325 = bkf325;
	}
	public String getBkf326() {
		return bkf326;
	}
	public void setBkf326(String bkf326) {
		this.bkf326 = bkf326;
	}
	public String getBkf318() {
		return bkf318;
	}
	public void setBkf318(String bkf318) {
		this.bkf318 = bkf318;
	}
	public String getBkf307() {
		return bkf307;
	}
	public void setBkf307(String bkf307) {
		this.bkf307 = bkf307;
	}
	public String getBkf306() {
		return bkf306;
	}
	public void setBkf306(String bkf306) {
		this.bkf306 = bkf306;
	}
	public String getAaa027() {
		return aaa027;
	}
	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}
	public String getAkb020() {
		return akb020;
	}
	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}
	public Date getBka045() {
		return bka045;
	}
	public void setBka045(Date bka045) {
		this.bka045 = bka045;
	}
	public String getAae100() {
		return aae100;
	}
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}
	public Date getBkf308() {
		return bkf308;
	}
	public void setBkf308(Date bkf308) {
		this.bkf308 = bkf308;
	}
	public Date getAae015() {
		return aae015;
	}
	public void setAae015(Date aae015) {
		this.aae015 = aae015;
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
	public String getAae013() {
		return aae013;
	}
	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}
	
	
}
