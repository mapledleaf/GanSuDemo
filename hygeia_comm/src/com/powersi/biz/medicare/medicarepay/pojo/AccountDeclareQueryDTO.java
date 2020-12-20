package com.powersi.biz.medicare.medicarepay.pojo;

import com.powersi.biz.medicare.comm.pojo.ParamDTO;

/**
 * 申报查询DTO
 * 
 * 2018年7月12日下午2:10:43
 *
 * @author lwyao
 *
 */
@SuppressWarnings("serial")
public class AccountDeclareQueryDTO extends ParamDTO {

	String akb020; // 医疗机构编号

	String aae041; // 申报开始年月

	String aae042; // 申报结束年月

	String bkp002; // 申报类型

	String daa027; // 拨付中心

	String aae013; // 申报说明

	String detail_orderby; // 明细排序

	String get_detail; // 是否获取明细，值为1的时候获取

	String reset_sum; // 是否重新统计，值为1的时候重新统计

	String isAuto; // 是否自动申报，值为1的时候是自动申报

	String bke100; // 登账号，多个用,分隔

	String bke402; // 申报号

	String bke403; // 申报批次

	String getBranch; // 获取分店(院)，1为获取

	public AccountDeclareQueryDTO() {

	}

	public AccountDeclareQueryDTO(String bke100) {
		this.bke100 = bke100;
	}

	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * 医疗机构编号
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getAkb020() {
		return akb020;
	}

	/**
	 * 医疗机构编号
	 * 
	 * @author lwyao
	 * @param akb020
	 */
	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}

	/**
	 * 申报类型
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getBkp002() {
		return bkp002;
	}

	/**
	 * 申报类型
	 * 
	 * @author lwyao
	 * @param bkp002
	 */
	public void setBkp002(String bkp002) {
		this.bkp002 = bkp002;
	}

	/**
	 * 申报开始年月
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getAae041() {
		return aae041;
	}

	/**
	 * 申报开始年月
	 * 
	 * @author lwyao
	 * @param aae041
	 */
	public void setAae041(String aae041) {
		this.aae041 = aae041;
	}

	/**
	 * 申报结束年月
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getAae042() {
		return aae042;
	}

	/**
	 * 申报结束年月
	 * 
	 * @author lwyao
	 * @param aae042
	 */
	public void setAae042(String aae042) {
		this.aae042 = aae042;
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
	 * 申报说明
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getAae013() {
		return aae013;
	}

	/**
	 * 申报说明
	 * 
	 * @author lwyao
	 * @param aae013
	 */
	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	/**
	 * 明细排序
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getDetail_orderby() {
		return detail_orderby;
	}

	/**
	 * 明细排序
	 * 
	 * @author lwyao
	 * @param detail_orderby
	 */
	public void setDetail_orderby(String detail_orderby) {
		this.detail_orderby = detail_orderby;
	}

	/**
	 * 是否获取明细，值为1的时候获取
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getGet_detail() {
		return get_detail;
	}

	/**
	 * 是否获取明细，值为1的时候获取
	 * 
	 * @author lwyao
	 * @param get_detail
	 */
	public void setGet_detail(String get_detail) {
		this.get_detail = get_detail;
	}

	/**
	 * 是否重新统计，值为1的时候重新统计
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getReset_sum() {
		return reset_sum;
	}

	/**
	 * 是否重新统计，值为1的时候重新统计
	 * 
	 * @author lwyao
	 * @param reset_sum
	 */
	public void setReset_sum(String reset_sum) {
		this.reset_sum = reset_sum;
	}

	/**
	 * 是否自动申报，值为1的时候是自动申报
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getIsAuto() {
		return isAuto;
	}

	/**
	 * 是否自动申报，值为1的时候是自动申报
	 * 
	 * @author lwyao
	 * @param isAuto
	 */
	public void setIsAuto(String isAuto) {
		this.isAuto = isAuto;
	}

	/**
	 * 登账号，多个用,分隔
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getBke100() {
		return bke100;
	}

	/**
	 * 登账号，多个用,分隔
	 * 
	 * @author lwyao
	 * @param bke100
	 */
	public void setBke100(String bke100) {
		this.bke100 = bke100;
	}

	/**
	 * 申报号
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getBke402() {
		return bke402;
	}

	/**
	 * 申报号
	 * 
	 * @author lwyao
	 * @param bke402
	 */
	public void setBke402(String bke402) {
		this.bke402 = bke402;
	}

	/**
	 * 申报批次
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getBke403() {
		return bke403;
	}

	/**
	 * 申报批次
	 * 
	 * @author lwyao
	 * @param bke403
	 */
	public void setBke403(String bke403) {
		this.bke403 = bke403;
	}

	/** 获取分店(院)，1为获取 */
	public String getGetBranch() {
		return getBranch;
	}

	/** 获取分店(院)，1为获取 */
	public void setGetBranch(String getBranch) {
		this.getBranch = getBranch;
	}

}
