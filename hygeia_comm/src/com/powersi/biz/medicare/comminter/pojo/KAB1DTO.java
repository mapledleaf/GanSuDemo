package com.powersi.biz.medicare.comminter.pojo;

import java.util.Date;
import java.util.List;

import com.powersi.comm.mybatis.Page;

public class KAB1DTO extends Page{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String kab1id;//'票据ID'
	private String akb020;//'医院编号'
	private String bka014;//'领用员工ID'
	private String bka015;//'领用员工姓名'
	private Date bka013;//领用日期
	private String bka033;//发票员工ID
	private String bka034;//发票员工姓名
	private String bae410;//票据类型，00:门诊住院通用 01：门诊发票 02：住院发票  03：门诊挂号 04：门诊充值 11：住院预交
	private String bae411;//起始号码
	private String bae412;//终止号码
	private String bae413;//当前号码
	private String bae414;//使用标识，01：在用 02：待用 03：用完   04退领
	
	private Date aae030;//开始使用时间
	private Date aae031;//结束时间
	private String akc226;//剩余数量
	private String bae415;//票据号前缀
	private String aae013;//备注
	private String aaz217;//就医登记号
	private Date start_time;
	private Date end_time;
	
	private String bae420;
	private int start_number;
	private int end_number;
	private String loginuser;
	private String username;
	private List<String> bae414list;
	
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
	public String getBae413() {
		return bae413;
	}
	public void setBae413(String bae413) {
		this.bae413 = bae413;
	}
	public String getBae414() {
		return bae414;
	}
	public void setBae414(String bae414) {
		this.bae414 = bae414;
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
	public String getAkc226() {
		return akc226;
	}
	public void setAkc226(String akc226) {
		this.akc226 = akc226;
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
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getAaz217() {
		return aaz217;
	}
	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217;
	}
	public String getBae420() {
		return bae420;
	}
	public void setBae420(String bae420) {
		this.bae420 = bae420;
	}
	public int getStart_number() {
		return start_number;
	}
	public void setStart_number(int start_number) {
		this.start_number = start_number;
	}
	public int getEnd_number() {
		return end_number;
	}
	public void setEnd_number(int end_number) {
		this.end_number = end_number;
	}
	public String getLoginuser() {
		return loginuser;
	}
	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getBae414list() {
		return bae414list;
	}
	public void setBae414list(List<String> bae414list) {
		this.bae414list = bae414list;
	}
	
	
}
