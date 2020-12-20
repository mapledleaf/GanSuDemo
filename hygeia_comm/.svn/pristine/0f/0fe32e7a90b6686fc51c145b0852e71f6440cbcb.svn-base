package com.powersi.biz.medicare.comm.pojo;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 索引
 * 
 * @author 刘勇
 *
 */
public class IndexesObj implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * 最后修改日期
	 */
	private Date aae036;
	/**
	 * 系统时间
	 */
	private Date aae037;
	/**
	 * 备注
	 */
	private String aae013;
	/**
	 * 数据有效标识
	 */
	private String aae100;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return 最后修改日期
	 */
	public Date getAae036() {
		return aae036;
	}

	/**
	 * 
	 * @param aae036
	 *            最后修改日期
	 */
	public void setAae036(Date aae036) {
		this.aae036 = aae036;
	}

	/**
	 * 
	 * @return 系统时间
	 */
	public Date getAae037() {
		return aae037;
	}

	/**
	 * 
	 * @param aae037
	 *            系统时间
	 */
	public void setAae037(Date aae037) {
		this.aae037 = aae037;
	}

	/**
	 * 
	 * @return 备注
	 */
	public String getAae013() {
		return aae013;
	}

	/**
	 * 
	 * @param aae013
	 *            备注
	 */
	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	/**
	 * 
	 * @return 数据有效标识
	 */
	public String getAae100() {
		return aae100;
	}

	/**
	 * 
	 * @param aae100
	 *            数据有效标识
	 */
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toString() {
		return this.toJson();
	}

	public String toJson() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}

	public boolean compareTo(Object obj) {
		if (obj != null) {
			return this.toString().equals(obj.toString());
		}
		return false;
	}

}
