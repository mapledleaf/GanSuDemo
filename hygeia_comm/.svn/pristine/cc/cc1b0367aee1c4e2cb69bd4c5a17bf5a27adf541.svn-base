package com.powersi.biz.medicare.inhospital.pojo;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.powersi.biz.medicare.comm.pojo.IPOJO;
import com.powersi.comm.mybatis.Page;

/**
 * 
  * @Description: 医院预约挂号停诊异动信息
  * @author zhos
  * @date 2016年12月2日 下午4:46:50
  *
 */
public class AppointmentExceptionInfoDTO extends Page implements IPOJO{

	private static final long serialVersionUID = 1L;
	private String ke13id;
	private String ke02id;
	private String akb020;//医院编号
	private String bac003;//预约病人姓名
	private String bac002;//预约病人身份证号码
	private String bac004;//性别 1男 2女
	private String aae005;//联系电话
	private String aae006;//联系地址
	private String bka503;//医生编号
	private String aac003;//医生姓名
	private String bae030;//预约就诊日期
	private String bka030;//预约就诊接诊时间
	private String bka508;//预约号
	private String aae013;//说明
	private String bka044;//传输标志 0待传输 1传输完毕
	private String bka971;//修改计数器,默认插入时为1，执行一次update需要加一
	private String bka972;//传输打包标志 0未打包 1已打包
	private Date bka973;//数据最后修改时间
	private String bka974;//最后传输批次编号
	
	
	public String getKe13id() {
		return ke13id;
	}
	public void setKe13id(String ke13id) {
		this.ke13id = ke13id;
	}
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
	public String getBae030() {
		return bae030;
	}
	public void setBae030(String bae030) {
		this.bae030 = bae030;
	}
	public String getBka030() {
		return bka030;
	}
	public void setBka030(String bka030) {
		this.bka030 = bka030;
	}
	public String getBka508() {
		return bka508;
	}
	public void setBka508(String bka508) {
		this.bka508 = bka508;
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
		
		
	@Override
	public String toJson() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}

	@Override
	public boolean compareTo(Object obj) {
		return this.toString().equals(obj.toString());
	}	
}
