package com.powersi.biz.medicare.yygh.pojo;

import java.util.Date;

import com.powersi.biz.medicare.comm.pojo.RemotingDTO;


public class KE01 extends RemotingDTO {

	private static final long serialVersionUID = 1L;

	/** `KE01ID` varchar(50) NOT NULL COMMENT '主键uuid', **/
	private String ke01id;

	/** `AKB020` varchar(20) NOT NULL COMMENT '医院编号', **/
	private String akb020;

	/** `AAZ307` varchar(20) NOT NULL COMMENT '医疗机构科室编号', **/
	private String aaz307;

	/** `AAZ386` varchar(100) NOT NULL COMMENT '科室名称', **/
	private String aaz386;

	/** `BKA503` varchar(20) NOT NULL COMMENT '医生编号', **/
	private String bka503;

	/** `AAC003` varchar(20) NOT NULL COMMENT '医生名称', **/
	private String aac003;

	/** `ACD231` varchar(30) NOT NULL COMMENT '职称',**/
	private String acd231;
	
	/**  `BAC045` varchar(50) NOT NULL COMMENT '专业', **/
	private String bac045;
	
	/** `ABK027` varchar(500) DEFAULT NULL COMMENT '个人简介',**/
	private String abk027;

	/** `AAE030` date NOT NULL COMMENT '就诊日期', **/
	private Date aae030;

	/** `BAE031` varchar(10) NOT NULL COMMENT '接诊班次，例如：上午、下午或晚上', **/
	private String bae031;

	/** `BAE032` varchar(30) NOT NULL COMMENT '接诊时间段，例如：8:30-9:00, 9:00-9:30', **/
	private String bae032;

	/** `BAE587` varchar(3) NOT NULL COMMENT '总号源数',**/
	private String bae587;
	
	/** `BAE588` varchar(3) NOT NULL COMMENT '剩余号源数',**/
	private String bae588;
	
	/** `BAE589` varchar(100) NOT NULL COMMENT '出诊地点',**/
	private String bae589;
	
	/** `AKC225` varchar(7) DEFAULT NULL COMMENT '挂号费标准',**/
	private String akc225;

	/** `AAE013` varchar(150) DEFAULT NULL COMMENT '说明', **/
	private String aae013;

	/** 传输标志 0待传输 1传输完毕**/
	private String bka044;
	
	/** 修改计数器,默认插入时为1，执行一次update需要加一**/
	private String bka971;
	
	/** 传输打包标志 0未打包 1已打包**/
	private String bka972;
	
	/** 数据最后修改时间 **/
	private Date bka973;
	
	/** 最后传输批次编号 **/
	private String bka974;
	
	private String yyyymm;
	
	private String aaz217;
	
	public String getKe01id() {
		return ke01id;
	}

	public void setKe01id(String ke01id) {
		this.ke01id = ke01id;
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

	public String getBka503() {
		return bka503;
	}

	public void setBka503(String bka503) {
		this.bka503 = bka503;
	}

	public String getAac003() {
		return aac003;
	}

	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}

	public String getAcd231() {
		return acd231;
	}

	public void setAcd231(String acd231) {
		this.acd231 = acd231;
	}

	public String getBac045() {
		return bac045;
	}

	public void setBac045(String bac045) {
		this.bac045 = bac045;
	}

	public String getAbk027() {
		return abk027;
	}

	public void setAbk027(String abk027) {
		this.abk027 = abk027;
	}

	public Date getAae030() {
		return aae030;
	}

	public void setAae030(Date aae030) {
		this.aae030 = aae030;
	}

	public String getBae031() {
		return bae031;
	}

	public void setBae031(String bae031) {
		this.bae031 = bae031;
	}

	public String getBae032() {
		return bae032;
	}

	public void setBae032(String bae032) {
		this.bae032 = bae032;
	}

	public String getBae587() {
		return bae587;
	}

	public void setBae587(String bae587) {
		this.bae587 = bae587;
	}

	public String getBae588() {
		return bae588;
	}

	public void setBae588(String bae588) {
		this.bae588 = bae588;
	}

	public String getBae589() {
		return bae589;
	}

	public void setBae589(String bae589) {
		this.bae589 = bae589;
	}

	public String getAkc225() {
		return akc225;
	}

	public void setAkc225(String akc225) {
		this.akc225 = akc225;
	}

	public String getAae013() {
		return aae013;
	}

	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	public String getBka044() {
		return bka044;
	}

	public void setBka044(String bka044) {
		this.bka044 = bka044;
	}

	public String getBka971() {
		return bka971;
	}

	public void setBka971(String bka971) {
		this.bka971 = bka971;
	}

	public String getBka972() {
		return bka972;
	}

	public void setBka972(String bka972) {
		this.bka972 = bka972;
	}

	public Date getBka973() {
		return bka973;
	}

	public void setBka973(Date bka973) {
		this.bka973 = bka973;
	}

	public String getBka974() {
		return bka974;
	}

	public void setBka974(String bka974) {
		this.bka974 = bka974;
	}

	public String getYyyymm() {
		return yyyymm;
	}

	public void setYyyymm(String yyyymm) {
		this.yyyymm = yyyymm;
	}

	public String getAaz217() {
		return aaz217;
	}

	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217;
	}
	
	
}
