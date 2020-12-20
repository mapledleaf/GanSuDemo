package com.powersi.biz.medicare.universal.pojo;

import java.util.Date;

import com.powersi.comm.mybatis.Page;

public class KCD1_Hosp_HisDTO  extends Page implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String kcd1id;//
	private String aac001; //个人编号
	private String aac002; //身份证号码
	private String aac003; //姓名
	private String akc190; //住院号
	private String aae030; //入院日期
	private String akf001; //入院科室编码
	private String bka020; //入院科室名称
	private String bka021; //病区编码
	private String bka022; //病区名称
	private String bka023; //床位号
	private String aaz217; //就医登记号
	private String bka035; //人员类别
	private   Date aae036;//登记时间
	private String bka044; //上传标志 0 未上传 1 未结算 2 已结算 3 重传
	private String caa027; //中心编码
	private String akb020; //医院编码
	public String getKcd1id() {
		return kcd1id;
	}
	public void setKcd1id(String kcd1id) {
		this.kcd1id = kcd1id;
	}
	public String getAac001() {
		return aac001;
	}
	public void setAac001(String aac001) {
		this.aac001 = aac001;
	}
	public String getAac002() {
		return aac002;
	}
	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}
	public String getAac003() {
		return aac003;
	}
	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}
	public String getAkc190() {
		return akc190;
	}
	public void setAkc190(String akc190) {
		this.akc190 = akc190;
	}
	public String getAae030() {
		return aae030;
	}
	public void setAae030(String aae030) {
		this.aae030 = aae030;
	}
	public String getAkf001() {
		return akf001;
	}
	public void setAkf001(String akf001) {
		this.akf001 = akf001;
	}
	public String getBka020() {
		return bka020;
	}
	public void setBka020(String bka020) {
		this.bka020 = bka020;
	}
	public String getBka021() {
		return bka021;
	}
	public void setBka021(String bka021) {
		this.bka021 = bka021;
	}
	public String getBka022() {
		return bka022;
	}
	public void setBka022(String bka022) {
		this.bka022 = bka022;
	}
	public String getAaz217() {
		return aaz217;
	}
	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217;
	}
	public String getBka035() {
		return bka035;
	}
	public void setBka035(String bka035) {
		this.bka035 = bka035;
	}
	public Date getAae036() {
		return aae036;
	}
	public void setAae036(Date aae036) {
		this.aae036 = aae036;
	}
	public String getBka044() {
		return bka044;
	}
	public void setBka044(String bka044) {
		this.bka044 = bka044;
	}
	public String getCaa027() {
		return caa027;
	}
	public void setCaa027(String caa027) {
		this.caa027 = caa027;
	}
	public String getAkb020() {
		return akb020;
	}
	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}
	public String getBka023() {
		return bka023;
	}
	public void setBka023(String bka023) {
		this.bka023 = bka023;
	}
	
	
}
