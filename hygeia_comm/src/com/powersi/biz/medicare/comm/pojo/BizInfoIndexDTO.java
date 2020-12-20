package com.powersi.biz.medicare.comm.pojo;

import java.util.Date;

public class BizInfoIndexDTO implements java.io.Serializable{
private static final long serialVersionUID = 1L;
	
	private String kc21id;
	private String akb020;
	private String aaz217;
	private String aae140; //险种编码
	private String aka130; //业务类别编码
	private Date aae030; //业务开始时间  入院日期
	private Date aae031; //业务结束时间  出院日期
	private Date akc194;  // 完成时间  结算时间
	private String aac002; //社会保障号
	private String aac003; //姓名
	private String akc190;  // 医院业务号（住院号）
	private String bka006;  // 待遇类型
	private String aae100;  // 有效标识
	private String aaa027;  
	private String baa027;  
	
	public String getAaa027() {
		return aaa027;
	}
	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}
	public String getBaa027() {
		return baa027;
	}
	public void setBaa027(String baa027) {
		this.baa027 = baa027;
	}
	public String getAae100() {
		return aae100;
	}
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}
	public String getBka006() {
		return bka006;
	}
	public void setBka006(String bka006) {
		this.bka006 = bka006;
	}
	public String getKc21id() {
		return kc21id;
	}
	public void setKc21id(String kc21id) {
		this.kc21id = kc21id;
	}
	public String getAkb020() {
		return akb020;
	}
	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}
	public String getAaz217() {
		return aaz217;
	}
	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217;
	}
	public String getAae140() {
		return aae140;
	}
	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}
	public String getAka130() {
		return aka130;
	}
	public void setAka130(String aka130) {
		this.aka130 = aka130;
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
	public Date getAkc194() {
		return akc194;
	}
	public void setAkc194(Date akc194) {
		this.akc194 = akc194;
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
}
