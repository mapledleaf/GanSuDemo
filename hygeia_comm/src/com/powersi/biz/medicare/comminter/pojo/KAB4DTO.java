package com.powersi.biz.medicare.comminter.pojo;

import java.util.Date;

import com.powersi.comm.mybatis.Page;

public class KAB4DTO extends Page{

	private static final long serialVersionUID = 1L;
	private String kab4id;//主键UUID
	private String kab1id;//票据id
	private String akb020;//医院编号
	private String bka014;//领用员工ID
	private String bka015;//领用员工姓名
	private Date bka013;//操作时间
	private String bka033;//发票员工ID
	private String bka034;//发票员工姓名
	private String bae410;//票据类型，00:门诊住院通用 01：门诊发票 02：住院发票  03：门诊挂号 04：门诊充值 11：住院预交
	private String bae411;//起始号码
	private String bae412;//终止号码
	private String bae415;//票据号前缀
	private String aae013;//备注
	private String aae011;//操作人ID
	private String aae012;//操作人姓名
	private String bae420;//操作类型 01 发票领用  02 发票退领
	public String getKab4id() {
		return kab4id;
	}
	public void setKab4id(String kab4id) {
		this.kab4id = kab4id;
	}
	public String getKab1id() {
		return kab1id;
	}
	public void setKab1id(String kab1id) {
		this.kab1id = kab1id;
	}
	public String getAkb020() {
		return akb020;
	}
	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}
	public String getBka014() {
		return bka014;
	}
	public void setBka014(String bka014) {
		this.bka014 = bka014;
	}
	public String getBka015() {
		return bka015;
	}
	public void setBka015(String bka015) {
		this.bka015 = bka015;
	}
	public Date getBka013() {
		return bka013;
	}
	public void setBka013(Date bka013) {
		this.bka013 = bka013;
	}
	public String getBka033() {
		return bka033;
	}
	public void setBka033(String bka033) {
		this.bka033 = bka033;
	}
	public String getBka034() {
		return bka034;
	}
	public void setBka034(String bka034) {
		this.bka034 = bka034;
	}
	public String getBae410() {
		return bae410;
	}
	public void setBae410(String bae410) {
		this.bae410 = bae410;
	}
	public String getBae411() {
		return bae411;
	}
	public void setBae411(String bae411) {
		this.bae411 = bae411;
	}
	public String getBae412() {
		return bae412;
	}
	public void setBae412(String bae412) {
		this.bae412 = bae412;
	}
	public String getBae415() {
		return bae415;
	}
	public void setBae415(String bae415) {
		this.bae415 = bae415;
	}
	public String getAae013() {
		return aae013;
	}
	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}
	public String getAae011() {
		return aae011;
	}
	public void setAae011(String aae011) {
		this.aae011 = aae011;
	}
	public String getAae012() {
		return aae012;
	}
	public void setAae012(String aae012) {
		this.aae012 = aae012;
	}
	public String getBae420() {
		return bae420;
	}
	public void setBae420(String bae420) {
		this.bae420 = bae420;
	}
	
}
