package com.powersi.biz.medicare.actualize.pojo;

import java.util.Date;

import com.powersi.comm.mybatis.Page;

public class KFD4DTO extends Page{

	private static final long serialVersionUID = 1L;
	
	private String bkf309;//实施计划明细id
	private String bkf307;//实施计划医院表id
	private String bkf302;//业务子类id
	private String bkf310;//是否需要测试(0:是 1：否)
	private String bkf311;//his完成情况
	private String bkf312;//联调情况
	private int bkf313;//联调次数
	private Date bka017;//联调开始时间
	private Date bka032;//联调结束时间
	private String bkf314;//负责人
	private String akb020;
	private String aaa027;
	
	public String getAkb020() {
		return akb020;
	}
	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}
	public String getAaa027() {
		return aaa027;
	}
	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}
	public String getBkf309() {
		return bkf309;
	}
	public void setBkf309(String bkf309) {
		this.bkf309 = bkf309;
	}
	public String getBkf307() {
		return bkf307;
	}
	public void setBkf307(String bkf307) {
		this.bkf307 = bkf307;
	}
	public String getBkf302() {
		return bkf302;
	}
	public void setBkf302(String bkf302) {
		this.bkf302 = bkf302;
	}
	public String getBkf310() {
		return bkf310;
	}
	public void setBkf310(String bkf310) {
		this.bkf310 = bkf310;
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
	public int getBkf313() {
		return bkf313;
	}
	public void setBkf313(int bkf313) {
		this.bkf313 = bkf313;
	}
	public Date getBka017() {
		return bka017;
	}
	public void setBka017(Date bka017) {
		this.bka017 = bka017;
	}
	public Date getBka032() {
		return bka032;
	}
	public void setBka032(Date bka032) {
		this.bka032 = bka032;
	}
	public String getBkf314() {
		return bkf314;
	}
	public void setBkf314(String bkf314) {
		this.bkf314 = bkf314;
	}
	
	
}
