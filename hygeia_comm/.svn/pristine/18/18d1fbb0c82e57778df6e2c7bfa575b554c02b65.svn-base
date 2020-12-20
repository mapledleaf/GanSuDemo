package com.powersi.biz.medicare.comm.pojo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 统筹区
 * 
 * @author 刘勇
 *
 */
public class AA13DTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String bka501;// 统筹区编码

	private String aaa129;// 统筹区名称

	private String aab301;// 所属行政区代码

	private String aaa013;// 备注

	private String baa151;// 经办权限统筹区

	private String aaa096;// 统筹层次 1 省级 2市级 3区县级

	private String aae100;// 1 有效 0无效

	private String aaa027;// 所属统筹中心编码

	/**
	 * 
	 * @return 统筹区编码
	 */
	public String getBka501() {
		return bka501;
	}

	/**
	 * 
	 * @param bka501
	 *            统筹区编码
	 */
	public void setBka501(String bka501) {
		this.bka501 = bka501;
	}

	/**
	 * 
	 * @return 统筹区名称
	 */
	public String getAaa129() {
		return aaa129;
	}

	/**
	 * 
	 * @param aaa129
	 *            统筹区名称
	 */
	public void setAaa129(String aaa129) {
		this.aaa129 = aaa129;
	}

	public String getAab301() {
		return aab301;
	}

	public void setAab301(String aab301) {
		this.aab301 = aab301;
	}

	public String getAaa013() {
		return aaa013;
	}

	public void setAaa013(String aaa013) {
		this.aaa013 = aaa013;
	}

	public String getBaa151() {
		return baa151;
	}

	public void setBaa151(String baa151) {
		this.baa151 = baa151;
	}

	public String getAaa096() {
		return aaa096;
	}

	public void setAaa096(String aaa096) {
		this.aaa096 = aaa096;
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	/**
	 * 
	 * @return 所属统筹中心编码
	 */
	public String getAaa027() {
		return aaa027;
	}

	/**
	 * 
	 * @param aaa027
	 *            所属统筹中心编码
	 */
	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	@Override
	public String toString() {
		return toJson();
	}

	public String toJson() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}

	public boolean compareTo(Object obj) {
		return toString().equals(obj.toString());
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}