package com.powersi.biz.medicare.comm.pojo;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 码表
 * 
 * @author 刘勇
 *
 */
public class AA10DTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String aaa100;// 代码类别
	private String aaa102;// 代码码值
	private String aaa103;// 代码码值名称
	private Date aae030;// 生效日期
	private Date aae031;// 失效日期
	private String aae100;// 数据有效标识 1 有效 0无效
	private String aaz093;//
	private String aaz094;//
	private String aaa104;//
	private String aaa101;// 代码类别名称
	private String aaa027;// 所属统筹中心编码

	/**
	 * 
	 * @return 代码类别
	 */
	public String getAaa100() {
		return aaa100;
	}

	/**
	 * 
	 * @param aaa100
	 *            代码类别
	 */
	public void setAaa100(String aaa100) {
		this.aaa100 = aaa100;
	}

	/**
	 * 
	 * @return 代码码值
	 */
	public String getAaa102() {
		return aaa102;
	}

	/**
	 * 
	 * @param aaa102
	 *            代码码值
	 */
	public void setAaa102(String aaa102) {
		this.aaa102 = aaa102;
	}

	/**
	 * 
	 * @return 代码码值名称
	 */
	public String getAaa103() {
		return aaa103;
	}

	/**
	 * 
	 * @param aaa103
	 *            代码码值名称
	 */
	public void setAaa103(String aaa103) {
		this.aaa103 = aaa103;
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

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	public String getAaz093() {
		return aaz093;
	}

	public void setAaz093(String aaz093) {
		this.aaz093 = aaz093;
	}

	public String getAaz094() {
		return aaz094;
	}

	public void setAaz094(String aaz094) {
		this.aaz094 = aaz094;
	}

	public String getAaa104() {
		return aaa104;
	}

	public void setAaa104(String aaa104) {
		this.aaa104 = aaa104;
	}

	/**
	 * 
	 * @return 代码类别名称
	 */
	public String getAaa101() {
		return aaa101;
	}

	/**
	 * 
	 * @param aaa101
	 *            代码类别名称
	 */
	public void setAaa101(String aaa101) {
		this.aaa101 = aaa101;
	}

	public String getAaa027() {
		return aaa027;
	}

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
