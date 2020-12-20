package com.powersi.biz.medicare.inhospital.pojo;

import java.util.List;

/**
 * 住院管理_只负责界面展示
 * 
 * @author 刘勇
 *
 */
public class InHospitalListDTO extends InHospitalDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 基金支付信息
	 */
	private List<FundPayInfoDTO> fundpayinfo;

	/**
	 * 
	 * @return 基金支付信息
	 */
	public List<FundPayInfoDTO> getFundpayinfo() {
		return fundpayinfo;
	}

	/**
	 * 
	 * @param fundpayinfo
	 *            基金支付信息
	 */
	public void setFundpayinfo(List<FundPayInfoDTO> fundpayinfo) {
		this.fundpayinfo = fundpayinfo;
	}

}
