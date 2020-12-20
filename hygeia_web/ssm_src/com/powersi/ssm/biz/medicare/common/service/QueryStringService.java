package com.powersi.ssm.biz.medicare.common.service;

import java.io.Serializable;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

public interface QueryStringService extends Serializable {

	/**
	 * 
	 * @param inHospitalDTO
	 */
	public void convertQueryString(InHospitalDTO inHospitalDTO);

	/**
	 * 
	 * @param idcard
	 *            身份证编号
	 * @return
	 */
	public boolean isValidatedAllIdcard(String idcard);

	/**
	 * 
	 * @param aac001
	 *            电脑号
	 * @return
	 */
	public boolean isValidatedAac001(String aac001);

	/**
	 * 
	 * @param bka100
	 *            社保卡号
	 * @return
	 */
	public boolean isValidatedBka100(String bka100);

	/**
	 * 
	 * @param aaz217
	 *            就医登记号
	 * @return
	 */
	public boolean isValidatedaaz217(String aaz217);

}
