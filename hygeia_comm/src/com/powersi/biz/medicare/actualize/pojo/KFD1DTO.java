package com.powersi.biz.medicare.actualize.pojo;

import com.powersi.comm.mybatis.Page;

public class KFD1DTO extends Page{

	private static final long serialVersionUID = 1L;
	
	private String bkf300;//业务大类id
	private String bkf301;//业务大类描述
	private String bkf302;//业务子类id
	private String bkf303;//业务子类
	private String bkf304;//子类描述
	/**
	 * 统筹区编码
	 */
	private String aaa027;
	/**
	 * 医院编码
	 */
	private String akb020;
	
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
	public String getBkf300() {
		return bkf300;
	}
	public void setBkf300(String bkf300) {
		this.bkf300 = bkf300;
	}
	public String getBkf301() {
		return bkf301;
	}
	public void setBkf301(String bkf301) {
		this.bkf301 = bkf301;
	}
	public String getBkf302() {
		return bkf302;
	}
	public void setBkf302(String bkf302) {
		this.bkf302 = bkf302;
	}
	public String getBkf303() {
		return bkf303;
	}
	public void setBkf303(String bkf303) {
		this.bkf303 = bkf303;
	}
	public String getBkf304() {
		return bkf304;
	}
	public void setBkf304(String bkf304) {
		this.bkf304 = bkf304;
	}
	
	
}
