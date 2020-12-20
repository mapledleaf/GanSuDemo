package com.powersi.biz.medicare.hosp.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
  * @Description: 医院接入计划
  * @author zhos
  * @date 2016年12月14日 下午1:53:05
  *
 */
public class HospitalAccessPlanDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String bae310;//医院接入计划流水号
	private String bae300;//医院接入方式流水号
	private String bae304;//实施内容编号
	private String bae305;//实施内容名称
	private String bae306;//阶段 1开发阶段 2联调阶段 3验收阶段 0无
	private Date bae307;//计划开始时间
	private String bae308;//计划时间
	private String bae302;//录入人
	private Date bae303;//录入时间
	private String bae313;//备注
	
	private String deltag;//可删除数据标记： 1（存在关联数据）不可删除  0（不存在关联数据）可删除
	
	public String getBae310() {
		return bae310;
	}
	public void setBae310(String bae310) {
		this.bae310 = bae310;
	}
	public String getBae300() {
		return bae300;
	}
	public void setBae300(String bae300) {
		this.bae300 = bae300;
	}
	public String getBae304() {
		return bae304;
	}
	public void setBae304(String bae304) {
		this.bae304 = bae304;
	}
	public String getBae305() {
		return bae305;
	}
	public void setBae305(String bae305) {
		this.bae305 = bae305;
	}
	public String getBae306() {
		return bae306;
	}
	public void setBae306(String bae306) {
		this.bae306 = bae306;
	}
	public Date getBae307() {
		return bae307;
	}
	public void setBae307(Date bae307) {
		this.bae307 = bae307;
	}
	public String getBae308() {
		return bae308;
	}
	public void setBae308(String bae308) {
		this.bae308 = bae308;
	}
	public String getBae302() {
		return bae302;
	}
	public void setBae302(String bae302) {
		this.bae302 = bae302;
	}
	public Date getBae303() {
		return bae303;
	}
	public void setBae303(Date bae303) {
		this.bae303 = bae303;
	}
	public String getBae313() {
		return bae313;
	}
	public void setBae313(String bae313) {
		this.bae313 = bae313;
	}
	public String getDeltag() {
		return deltag;
	}
	public void setDeltag(String deltag) {
		this.deltag = deltag;
	}
	
	
}
