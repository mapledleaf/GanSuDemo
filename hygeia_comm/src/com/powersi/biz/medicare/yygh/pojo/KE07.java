package com.powersi.biz.medicare.yygh.pojo;

import com.powersi.biz.medicare.comm.pojo.RemotingDTO;

public class KE07 extends RemotingDTO {

	private static final long serialVersionUID = 1L;

	/** `KE07ID` varchar(50) NOT NULL COMMENT '主键uuid', **/
	private String ke07id;

	/** `AKB020` varchar(20) NOT NULL COMMENT '医院编号', **/
	private String akb020;

	/** `AAZ307` varchar(20) NOT NULL COMMENT '医疗机构科室编号', **/
	private String aaz307;

	/** `AAZ386` varchar(100) NOT NULL COMMENT '科室名称', **/
	private String aaz386;

	/**  `AAE100` char(1) NOT NULL COMMENT '有效标志', **/
	private String aae100;

	/** `AAE013` varchar(150) DEFAULT NULL COMMENT '说明', **/
	private String aae013;

	public String getKe07id() {
		return ke07id;
	}

	public void setKe07id(String ke07id) {
		this.ke07id = ke07id;
	}

	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}

	public String getAaz307() {
		return aaz307;
	}

	public void setAaz307(String aaz307) {
		this.aaz307 = aaz307;
	}

	public String getAaz386() {
		return aaz386;
	}

	public void setAaz386(String aaz386) {
		this.aaz386 = aaz386;
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	public String getAae013() {
		return aae013;
	}

	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	
	
}
