package com.powersi.biz.medicare.hosp.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
  * @Description: 医院接入进度
  * @author zhos
  * @date 2016年12月14日 下午1:53:05
  *
 */
public class HospitalAccessScheduleDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String bae320;//医院接入进度流水号
	private String bae310;//医院接入计划流水号
	private String bae309;//进度情况（0未开始 1正在执行 2已完成）
	private String bae302;//填报人
	private Date bae303;//填报时间
	private String bae313;//备注
	
	
	private String deltag;//可删除数据标记： 1（存在关联数据）不可删除  0（不存在关联数据）可删除
	
	public String getBae320() {
		return bae320;
	}
	public void setBae320(String bae320) {
		this.bae320 = bae320;
	}
	public String getBae310() {
		return bae310;
	}
	public void setBae310(String bae310) {
		this.bae310 = bae310;
	}
	public String getBae309() {
		return bae309;
	}
	public void setBae309(String bae309) {
		this.bae309 = bae309;
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
