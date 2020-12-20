package com.powersi.biz.medicare.medicarepay.pojo;

import com.powersi.biz.medicare.comm.pojo.ParamDTO;

/**
 * 拨付单查询DTO
 * 
 * 2018年8月3日上午11:46:21
 *
 * @author lwyao
 *
 */
@SuppressWarnings("serial")
public class BillQueryDTO extends ParamDTO {

	String field; // 日期查询字段
	String aae030; // 开始日期(yyyyMMdd)
	String aae031; // 结束日期(yyyyMMdd)
	String daa027; // 拨付中心
	String aae140; // 险种类型
	String aae016; // 审核状态
	String bke142; // 拨付状态
	String bke127; // 拨付类型
	String bke126; // 拨付单号

	String getBranch = "0"; // 获取分店(院)，1为获取

	/**
	 * 日期查询字段
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getField() {
		return field;
	}

	/**
	 * 日期查询字段
	 * 
	 * @author lwyao
	 * @param field
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * 开始日期(yyyyMMdd)
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getAae030() {
		return aae030;
	}

	/**
	 * 开始日期(yyyyMMdd)
	 * 
	 * @author lwyao
	 * @param aae030
	 */
	public void setAae030(String aae030) {
		this.aae030 = aae030;
	}

	/**
	 * 结束日期(yyyyMMdd)
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getAae031() {
		return aae031;
	}

	/**
	 * 结束日期(yyyyMMdd)
	 * 
	 * @author lwyao
	 * @param aae031
	 */
	public void setAae031(String aae031) {
		this.aae031 = aae031;
	}

	/**
	 * 拨付中心
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getDaa027() {
		return daa027;
	}

	/**
	 * 拨付中心
	 * 
	 * @author lwyao
	 * @param daa027
	 */
	public void setDaa027(String daa027) {
		this.daa027 = daa027;
	}

	/**
	 * 险种类型
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getAae140() {
		return aae140;
	}

	/**
	 * 险种类型
	 * 
	 * @author lwyao
	 * @param aae140
	 */
	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}

	/**
	 * 审核状态
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getAae016() {
		return aae016;
	}

	/**
	 * 审核状态
	 * 
	 * @author lwyao
	 * @param aae016
	 */
	public void setAae016(String aae016) {
		this.aae016 = aae016;
	}

	/**
	 * 拨付状态
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getBke142() {
		return bke142;
	}

	/**
	 * 拨付状态
	 * 
	 * @author lwyao
	 * @param bke142
	 */
	public void setBke142(String bke142) {
		this.bke142 = bke142;
	}

	/**
	 * 拨付类型
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getBke127() {
		return bke127;
	}

	/**
	 * 拨付类型
	 * 
	 * @author lwyao
	 * @param bke127
	 */
	public void setBke127(String bke127) {
		this.bke127 = bke127;
	}

	/**
	 * 拨付单号
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getBke126() {
		return bke126;
	}

	/**
	 * 拨付单号
	 * 
	 * @author lwyao
	 * @param bke126
	 */
	public void setBke126(String bke126) {
		this.bke126 = bke126;
	}

	/** 获取分店(院)，1为获取 */
	public String getGetBranch() {
		return getBranch;
	}

	/** 获取分店(院)，1为获取 */
	public void setGetBranch(String getBranch) {
		this.getBranch = "on".equals(getBranch) ? "1" : getBranch;
	}

}