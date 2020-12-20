package com.powersi.biz.medicare.outland.pojo;

import com.powersi.comm.bean.BaseBean;

public class OutAccountBizDTO extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String ykc705;//对账类型
	private String ykc706;//对账日期
	private String aab299;//行政区划代码（就医地）
	private String aab301;//行政区划代码（参保地）
	private String akc264;//医疗费总额
	private String ake149;//医保基金支付总额
	private String akc194;//就诊结算时间
	private String aaa113;//交易类型
	private String ykc700;//就诊登记号
	private String aaz216;//结算流水号
	private String otransid;//原交易流水号
	private String totalrow;
	public String getYkc705() {
		return ykc705;
	}
	public void setYkc705(String ykc705) {
		this.ykc705 = ykc705;
	}
	public String getYkc706() {
		return ykc706;
	}
	public void setYkc706(String ykc706) {
		this.ykc706 = ykc706;
	}
	public String getAab299() {
		return aab299;
	}
	public void setAab299(String aab299) {
		this.aab299 = aab299;
	}
	public String getAab301() {
		return aab301;
	}
	public void setAab301(String aab301) {
		this.aab301 = aab301;
	}
	public String getAkc264() {
		return akc264;
	}
	public void setAkc264(String akc264) {
		this.akc264 = akc264;
	}
	public String getAke149() {
		return ake149;
	}
	public void setAke149(String ake149) {
		this.ake149 = ake149;
	}
	public String getAkc194() {
		return akc194;
	}
	public void setAkc194(String akc194) {
		this.akc194 = akc194;
	}
	public String getAaa113() {
		return aaa113;
	}
	public void setAaa113(String aaa113) {
		this.aaa113 = aaa113;
	}
	public String getYkc700() {
		return ykc700;
	}
	public void setYkc700(String ykc700) {
		this.ykc700 = ykc700;
	}
	public String getAaz216() {
		return aaz216;
	}
	public void setAaz216(String aaz216) {
		this.aaz216 = aaz216;
	}
	public String getOtransid() {
		return otransid;
	}
	public void setOtransid(String otransid) {
		this.otransid = otransid;
	}
	public String getTotalrow() {
		return totalrow;
	}
	public void setTotalrow(String totalrow) {
		this.totalrow = totalrow;
	}
	

}
