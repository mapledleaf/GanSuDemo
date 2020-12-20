package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;

/**
 * 摘自雷总的金额大写转换
 * 
 * @author 刘勇
 *
 */
public interface ConvertNumberService extends Serializable {

	/**
	 * 
	 * @param tempNumber
	 * @return
	 */
	public String numberToChinese(final double tempNumber);

}
