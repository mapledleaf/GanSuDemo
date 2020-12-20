package com.powersi.biz.medicare.yygh.pojo;

import java.util.Date;

import com.powersi.biz.medicare.comm.pojo.RemotingDTO;

public class KE02 extends RemotingDTO {

	private static final long serialVersionUID = 1L;

	/** `KE02ID` varchar(50) NOT NULL COMMENT '主键uuid', **/
	private String ke02id;

	/** `AKB020` varchar(20) NOT NULL COMMENT '医院编号', **/
	private String akb020;

	/** `BAC003` varchar(20) NOT NULL COMMENT '预约病人姓名', **/
	private String bac003;

	/** `BAC002` varchar(25) NOT NULL COMMENT '预约病人身份证号码', **/
	private String bac002;

	/** `BAC004` varchar(1) DEFAULT NULL COMMENT '性别 1男 2女', **/
	private String bac004;

	/** `AAE005` varchar(20) DEFAULT NULL COMMENT '联系电话', **/
	private String aae005;

	/** `AAE006` varchar(40) DEFAULT NULL COMMENT '联系地址', **/
	private String aae006;

	/** `AAZ307` varchar(20) NOT NULL COMMENT '医疗机构科室编号', **/
	private String aaz307;

	/** `AAZ386` varchar(100) NOT NULL COMMENT '科室名称', **/
	private String aaz386;

	/** `BKA503` varchar(20) NOT NULL COMMENT '医生编号', **/
	private String bka503;

	/** `AAC003` varchar(20) NOT NULL COMMENT '医生名称', **/
	private String aac003;

	/** `AAE030` date NOT NULL COMMENT '就诊日期', **/
	private Date aae030;

	/** `BAE031` varchar(10) NOT NULL COMMENT '接诊班次，例如：上午、下午或晚上', **/
	private String bae031;

	/** `BAE032` varchar(30) NOT NULL COMMENT '接诊时间段，例如：8:30-9:00, 9:00-9:30', **/
	private String bae032;

	/** `BKA508` varchar(30) DEFAULT NULL COMMENT '预约号', **/
	private String bka508;

	/** `BKA504` varchar(1) NOT NULL COMMENT '1：预约挂号 2、取消预约挂号', **/
	private String bka504;

	/** `AAE013` varchar(150) DEFAULT NULL COMMENT '说明', **/
	private String aae013;

	/** `BAE029` varchar(1) DEFAULT NULL COMMENT '1停诊 2改诊', **/
	private String bae029;

	/** `BAE013` varchar(150) DEFAULT NULL COMMENT '停诊异动说明', **/
	private String bae013;

	/** `AAE034` DATE DEFAULT NULL COMMENT '改诊后的就诊日期，只有改诊时才有值', **/
	private Date aae034;

	/** `BAE035` VARCHAR(10) DEFAULT NULL COMMENT '改诊后的预约班次，只有改诊时才有值', **/
	private String bae035;

	/** `BAE036` VARCHAR(30) DEFAULT NULL COMMENT '改诊后的就诊时间，只有改诊时才有值', **/
	private String bae036;

	/** `BKA509` VARCHAR(30) DEFAULT NULL COMMENT '改诊后的预约号源编码，只有改诊时才有值', **/
	private String bka509;
	
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
	
	/** 异动类型 **/
	private String bae029_name;
	
	/** 分表后缀标识 **/
	private String yyyymm;
	
	/** 办理渠道: 医院简单系统标识：sys  API接口标识：api**/
	private String channel_tag;
	
	public String getKe02id() {
		return ke02id;
	}

	public void setKe02id(String ke02id) {
		this.ke02id = ke02id;
	}

	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}

	public String getBac003() {
		return bac003;
	}

	public void setBac003(String bac003) {
		this.bac003 = bac003;
	}

	public String getBac002() {
		return bac002;
	}

	public void setBac002(String bac002) {
		this.bac002 = bac002;
	}

	public String getBac004() {
		return bac004;
	}

	public void setBac004(String bac004) {
		this.bac004 = bac004;
	}

	public String getAae005() {
		return aae005;
	}

	public void setAae005(String aae005) {
		this.aae005 = aae005;
	}

	public String getAae006() {
		return aae006;
	}

	public void setAae006(String aae006) {
		this.aae006 = aae006;
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

	public String getBka508() {
		return bka508;
	}

	public void setBka508(String bka508) {
		this.bka508 = bka508;
	}

	public String getBka504() {
		return bka504;
	}

	public void setBka504(String bka504) {
		this.bka504 = bka504;
	}

	public String getAae013() {
		return aae013;
	}

	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	public String getBae029() {
		return bae029;
	}

	public void setBae029(String bae029) {
		this.bae029 = bae029;
	}

	public String getBae013() {
		return bae013;
	}

	public void setBae013(String bae013) {
		this.bae013 = bae013;
	}

	public Date getAae034() {
		return aae034;
	}

	public void setAae034(Date aae034) {
		this.aae034 = aae034;
	}

	public String getBae035() {
		return bae035;
	}

	public void setBae035(String bae035) {
		this.bae035 = bae035;
	}

	public String getBae036() {
		return bae036;
	}

	public void setBae036(String bae036) {
		this.bae036 = bae036;
	}

	public String getBka509() {
		return bka509;
	}

	public void setBka509(String bka509) {
		this.bka509 = bka509;
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

	public String getBae029_name() {
		return bae029_name;
	}

	public void setBae029_name(String bae029_name) {
		this.bae029_name = bae029_name;
	}

	public String getYyyymm() {
		return yyyymm;
	}

	public void setYyyymm(String yyyymm) {
		this.yyyymm = yyyymm;
	}

	public String getChannel_tag() {
		return channel_tag;
	}

	public void setChannel_tag(String channel_tag) {
		this.channel_tag = channel_tag;
	}
	
	
}
