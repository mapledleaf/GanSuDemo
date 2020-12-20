package com.powersi.biz.medicare.statics;

/**
 * 申报状态
 * 
 * 2018年7月11日上午9:33:08
 *
 * @author lwyao
 *
 */
public abstract class BAZ016 {
	/**
	 * 0:未对账(已生成台账)
	 */
	public static final String UNCHECKED = "0";
	/**
	 * 1:已对账(已申报)
	 */
	public static final String CHECKED = "1";
	/**
	 * 2:已受理(中心已受理)
	 */
	public static final String ACCEPTED = "2";
	/**
	 * 3:已审核(已开始审核)
	 */
	public static final String AUDITED = "3";
	/**
	 * 4:取消申报
	 */
	public static final String INVALID = "4";
	/**
	 * 5:正在生成月台账
	 */
	public static final String WAIT = "5";
}
