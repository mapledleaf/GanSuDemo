package com.powersi.pcloud.medicare.comm.service;

/**
 * 社保卡许可证服务
 * 
 * @author 刘勇
 *
 */
public interface ICPermitService {

	/**
	 * 面向移动支付_获取持卡就诊许可号_ic_reg_permit
	 * 
	 * @param akb020
	 *            医院编号
	 * @param aac002
	 *            社会保障号
	 * @param bka006
	 *            待遇类型
	 * @return
	 */
	public String getICRegPermit(String akb020, String aac002, String bka006);

}
