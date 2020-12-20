/*
 * Copyright 2013 Powersi. All rights reserved.
 */
package com.powersi.sys.message.dto;


/**
 * The Class SearchMessageDTO.
 */
public class SearchMessageDTO {
	
	/** The begin date. */
	private String beginDate;
	
	/** The end date. */
	private String endDate;
	
	/** The ms floder. */
	private String msFloder;
	
	/** The man. */
	private String man;//如果为发件箱，则为消息接收人，如果为收件箱，则为消息发送人
	
	/** The ms title. */
	private String msTitle;
	
	/** The status. */
	private String status;//如果为发件箱，则为接受状态，如果为收件箱，则为阅读状态
	
	/** The login staff. */
	private String loginStaff;
	
	/**
	 * Gets the login staff.
	 *
	 * @return the login staff
	 */
	public String getLoginStaff() {
		return loginStaff;
	}
	
	/**
	 * Sets the login staff.
	 *
	 * @param loginStaff the new login staff
	 */
	public void setLoginStaff(String loginStaff) {
		this.loginStaff = loginStaff;
	}
	
	/**
	 * Gets the begin date.
	 *
	 * @return the begin date
	 */
	public String getBeginDate() {
		return beginDate;
	}
	
	/**
	 * Sets the begin date.
	 *
	 * @param beginDate the new begin date
	 */
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	
	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Gets the ms floder.
	 *
	 * @return the ms floder
	 */
	public String getMsFloder() {
		return msFloder;
	}
	
	/**
	 * Sets the ms floder.
	 *
	 * @param msFloder the new ms floder
	 */
	public void setMsFloder(String msFloder) {
		this.msFloder = msFloder;
	}
	
	/**
	 * Gets the ms title.
	 *
	 * @return the ms title
	 */
	public String getMsTitle() {
		return msTitle;
	}
	
	/**
	 * Sets the ms title.
	 *
	 * @param msTitle the new ms title
	 */
	public void setMsTitle(String msTitle) {
		this.msTitle = msTitle;
	}
	
	/**
	 * Gets the man.
	 *
	 * @return the man
	 */
	public String getMan() {
		return man;
	}
	
	/**
	 * Sets the man.
	 *
	 * @param man the new man
	 */
	public void setMan(String man) {
		this.man = man;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
