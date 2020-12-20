package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.MediDTO;

/**
 * 
 * @author 刘勇
 *
 */
public interface SettlementReportDataService extends Serializable {

	/**
	 * 急诊留观
	 */
	public static final String aka130_17 = "17";

	/**
	 * 门特
	 */
	public static final String aka130_16 = "16";

	/**
	 * 家庭病床
	 */
	public static final String aka130_14 = "14";

	/**
	 * 门慢
	 */
	public static final String aka130_13 = "13";
	
	/**
	 * 购药
	 */
	public static final String aka130_10 = "10";
	
	/**
	 * 住院
	 */
	public static final String aka130_12 = "12";

	/**
	 * 工伤住院
	 */
	public static final String aka130_42 = "42";

	/**
	 * 生育住院
	 */
	public static final String aka130_52 = "52";

	/**
	 * 省内异地住院
	 */
	public static final String aka130_72 = "72";
	
	/**
	 * 跨省异地就医住院
	 */
	public static final String aka130_82 = "82";

	/**
	 * 
	 * @param mediDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map loadSettlementReportData(MediDTO mediDTO);

}
