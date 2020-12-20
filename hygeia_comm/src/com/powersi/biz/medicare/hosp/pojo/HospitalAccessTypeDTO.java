package com.powersi.biz.medicare.hosp.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
  * @Description: 医院接入方式
  * @author zhos
  * @date 2016年12月14日 下午1:53:05
  *
 */
public class HospitalAccessTypeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String bae300;//医院接入方式流水号
	private String akb020;//医院编码
	private String akb021;//医院名称
	private String bae301;//接入方式：1API接口 2个性化接口（通用模式） 3个性化接口（HIS、医保一体化模式）4医院前台简单模式
	private String bae302;//录入人
	private Date bae303;//录入时间
	private String bae313;//备注
	
	private String deltag;//可删除数据标记： 1（存在关联数据）不可删除  0（不存在关联数据）可删除
	
	public String getBae300() {
		return bae300;
	}
	public void setBae300(String bae300) {
		this.bae300 = bae300;
	}
	public String getAkb020() {
		return akb020;
	}
	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}
	public String getAkb021() {
		return akb021;
	}
	public void setAkb021(String akb021) {
		this.akb021 = akb021;
	}
	public String getBae301() {
		return bae301;
	}
	public void setBae301(String bae301) {
		this.bae301 = bae301;
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
