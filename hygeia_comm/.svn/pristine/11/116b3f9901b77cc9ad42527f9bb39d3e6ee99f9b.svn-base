package com.powersi.events;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 事件明细
 * 
 * @author 刘勇
 *
 */
public class KE68 implements Serializable {

	private static final long serialVersionUID = 1L;

	private String bke586;// 事件明细ID UUID(VARCHAR2(50) not null)
	private String bke581;// 事件ID UUID(VARCHAR2(50) not null)
	private String bke587;// 事件明细项目 (VARCHAR2(100) not null)
	private String bke588;// 事件明细项目事前值 (VARCHAR2(200) not null)
	private String bke589;// 事件明细项目事后值 (VARCHAR2(200) not null)
	private String aae013;// 事件明细备注 VARCHAR2(100) null
	private String aae100;// 有效标志(VARCHAR2(1) default '1' not null)

	@Override
	public String toString() {
		return this.toJson();
	}

	/**
	 * 
	 * @return
	 */
	public String toJson() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public boolean compareTo(Object obj) {
		return this.toString().equals(obj.toString());
	}

	/**
	 * 事件明细ID
	 * 
	 * @return
	 */
	public String getBke586() {
		return bke586;
	}

	/**
	 * 事件明细ID
	 * 
	 * @param bke586
	 */
	public void setBke586(String bke586) {
		this.bke586 = bke586;
	}

	/**
	 * 事件ID
	 * 
	 * @return
	 */
	public String getBke581() {
		return bke581;
	}

	/**
	 * 事件ID
	 * 
	 * @param bke581
	 */
	public void setBke581(String bke581) {
		this.bke581 = bke581;
	}

	/**
	 * 事件明细项目
	 * 
	 * @return
	 */
	public String getBke587() {
		return bke587;
	}

	/**
	 * 事件明细项目
	 * 
	 * @param bke587
	 */
	public void setBke587(String bke587) {
		this.bke587 = bke587;
	}

	/**
	 * 事件明细项目事前值
	 * 
	 * @return
	 */
	public String getBke588() {
		return bke588;
	}

	/**
	 * 事件明细项目事前值
	 * 
	 * @param bke588
	 */
	public void setBke588(String bke588) {
		this.bke588 = bke588;
	}

	/**
	 * 事件明细项目事后值
	 * 
	 * @return
	 */
	public String getBke589() {
		return bke589;
	}

	/**
	 * 事件明细项目事后值
	 * 
	 * @param bke589
	 */
	public void setBke589(String bke589) {
		this.bke589 = bke589;
	}

	/**
	 * 事件明细备注
	 * 
	 * @return
	 */
	public String getAae013() {
		return aae013;
	}

	/**
	 * 事件明细备注
	 * 
	 * @param aae013
	 */
	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	/**
	 * 有效标志
	 * 
	 * @return
	 */
	public String getAae100() {
		return aae100;
	}

	/**
	 * 有效标志
	 * 
	 * @param aae100
	 */
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
