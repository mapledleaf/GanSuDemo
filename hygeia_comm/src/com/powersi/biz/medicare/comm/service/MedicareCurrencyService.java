package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;
import java.util.Date;

/**
 * 医疗通用服务
 * 
 * @author 刘勇
 *
 */
public interface MedicareCurrencyService extends Serializable {

	/**
	 * 
	 * @param aae030
	 * @param aae031
	 * @param permit
	 */
	public void checkDateSpan_Day(String aae030, String aae031, int permit);

	/**
	 * 
	 * @param aae030
	 * @param aae031
	 * @param permit
	 */
	public void checkDateSpan_Day(Date aae030, Date aae031, int permit);

	/**
	 * 
	 * @param aae030
	 * @param aae031
	 * @param permit
	 */
	public void checkDateSpan_Month(String aae030, String aae031, int permit);

	/**
	 * 
	 * @param aae030
	 * @param aae031
	 * @param permit
	 */
	public void checkDateSpan_Month(Date aae030, Date aae031, int permit);

}
