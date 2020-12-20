package com.powersi.biz.medicare.inhospital.pojo;

import com.powersi.biz.medicare.comm.pojo.POJO;

/**
 * 
 * @author 刘勇
 *
 */
public class FundPayInfoDTO extends POJO {

	private static final long serialVersionUID = 1L;

	private String aaa157;// 基金编号VARCHAR2(6)

	private String aad006;// 基金名称VARCHAR2(50)

	private String aae019;// 金额

	private String aae019_lower;// 金额小写

	private String aae019_upper;// 金额大写

	/**
	 * 
	 * @return 基金编号
	 */
	public String getAaa157() {
		return aaa157;
	}

	/**
	 * 
	 * @param aaa157
	 *            基金编号
	 */
	public void setAaa157(String aaa157) {
		this.aaa157 = aaa157;
	}

	/**
	 * 
	 * @return 基金名称
	 */
	public String getAad006() {
		return aad006;
	}

	/**
	 * 
	 * @param aad006
	 *            基金名称
	 */
	public void setAad006(String aad006) {
		this.aad006 = aad006;
	}

	/**
	 * 
	 * @return 金额
	 */
	public String getAae019() {
		return aae019;
	}

	/**
	 * 
	 * @param aae019
	 *            金额
	 */
	public void setAae019(String aae019) {
		this.aae019 = aae019;
	}

	/**
	 * 
	 * @return 金额小写
	 */
	public String getAae019_lower() {
		return aae019_lower;
	}

	/**
	 * 
	 * @param aae019_lower
	 *            金额小写
	 */
	public void setAae019_lower(String aae019_lower) {
		this.aae019_lower = aae019_lower;
	}

	/**
	 * 
	 * @return 金额大写
	 */
	public String getAae019_upper() {
		return aae019_upper;
	}

	/**
	 * 
	 * @param aae019_upper
	 *            金额大写
	 */
	public void setAae019_upper(String aae019_upper) {
		this.aae019_upper = aae019_upper;
	}

}
