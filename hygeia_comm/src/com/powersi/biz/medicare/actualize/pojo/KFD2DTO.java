package com.powersi.biz.medicare.actualize.pojo;

import com.powersi.comm.mybatis.Page;

public class KFD2DTO extends Page{

	private static final long serialVersionUID = 1L;

	private String akb020;//医院编号
	private String akb021;//医院名称
	private String bkf305;//his开发商id
	public String getAkb020() {
		return akb020;
	}
	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}
	public String getAkb021() {
		return akb021;
	}
	public void setAkb021(String akb021) {
		this.akb021 = akb021;
	}
	public String getBkf305() {
		return bkf305;
	}
	public void setBkf305(String bkf305) {
		this.bkf305 = bkf305;
	}
	
	
}
