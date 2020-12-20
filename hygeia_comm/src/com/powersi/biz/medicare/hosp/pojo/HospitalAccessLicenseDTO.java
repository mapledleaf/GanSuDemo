package com.powersi.biz.medicare.hosp.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
  * @Description: 医院接入许可发放情况
  * @author zhos
  * @date 2016年12月14日 下午1:53:05
  *
 */
public class HospitalAccessLicenseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String bae330;//医院接入许可流水号
	private String akb020;//医院编号
	private String akb021;//医院名称
	private String bae314;//年度
	private Date bae315;//许可发放日期
	private String bae302;//录入人
	private Date bae303;//录入时间
	private String bae313;//备注
	
	private String deltag;//可删除数据标记： 1（存在关联数据）不可删除  0（不存在关联数据）可删除

	
	public String getBae330() {
		return bae330;
	}
	public void setBae330(String bae330) {
		this.bae330 = bae330;
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
	public String getBae314() {
		return bae314;
	}
	public void setBae314(String bae314) {
		this.bae314 = bae314;
	}
	public Date getBae315() {
		return bae315;
	}
	public void setBae315(Date bae315) {
		this.bae315 = bae315;
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
