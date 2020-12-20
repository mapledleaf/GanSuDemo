/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.message.dto;

import java.util.List;

/**
 * The Class SearchBulletinDTO.
 */
public class SearchBulletinDTO {
	
	/** 查询类型(0列表 1编辑 2审核 3审签 4回执). */
	private String queryType;
	
	/** The begin date. */
	private String beginDate = null;
	
	/** The end date. */
	private String endDate = null;
	
	/** The audit flag. */
	private List auditFlag = null;
	
	/** The second audit flag. */
	private List secondAuditFlag = null;
	
	/** The bulletin title. */
	private String bulletinTitle = null;
	
	/** The sender man. */
	private String senderMan;
	
	/** The effect date. */
	private String effectDate;
	
	/** The center. */
	private String center;
	
	/** The level. */
	private String level;
	
	/** The hosptype. */
	private String hosptype;
	
	/** The hosp name. */
	private String hospName;
	
	/** The reply status. */
	private String replyStatus;
	
	/** The bulletin id. */
	private String bulletinId;
	
	/** The user kind. */
	private List userKind;
	
	/** The reply flag. */
	private List replyFlag;
	
	/** The bulletin content. */
	private String bulletinContent;
	
	/** The reply man. */
	private String replyMan;
	
	/**the receive_users**/
	private String receive_users;
	
	public String getReceive_users() {
		return receive_users;
	}

	public void setReceive_users(String receive_users) {
		this.receive_users = receive_users;
	}

	/**
	 * Gets the query type.
	 *
	 * @return the query type
	 */
	public String getQueryType() {
		return queryType;
	}
	
	/**
	 * Sets the query type.
	 *
	 * @param queryType the new query type
	 */
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	
	/**
	 * Gets the sender man.
	 *
	 * @return the sender man
	 */
	public String getSenderMan() {
		return senderMan;
	}
	
	/**
	 * Sets the sender man.
	 *
	 * @param senderMan the new sender man
	 */
	public void setSenderMan(String senderMan) {
		this.senderMan = senderMan;
	}
	
	/**
	 * Gets the bulletin title.
	 *
	 * @return the bulletin title
	 */
	public String getBulletinTitle() {
		return bulletinTitle;
	}
	
	/**
	 * Sets the bulletin title.
	 *
	 * @param bulletinTitle the new bulletin title
	 */
	public void setBulletinTitle(String bulletinTitle) {
		this.bulletinTitle = bulletinTitle;
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
	 * Gets the audit flag.
	 *
	 * @return the audit flag
	 */
	public List getAuditFlag() {
		return auditFlag;
	}
	
	/**
	 * Sets the audit flag.
	 *
	 * @param auditFlag the new audit flag
	 */
	public void setAuditFlag(List auditFlag) {
		this.auditFlag = auditFlag;
	}
	
	/**
	 * Gets the second audit flag.
	 *
	 * @return the second audit flag
	 */
	public List getSecondAuditFlag() {
		return secondAuditFlag;
	}
	
	/**
	 * Sets the second audit flag.
	 *
	 * @param secondAuditFlag the new second audit flag
	 */
	public void setSecondAuditFlag(List secondAuditFlag) {
		this.secondAuditFlag = secondAuditFlag;
	}
	
	/**
	 * Gets the bulletin id.
	 *
	 * @return the bulletin id
	 */
	public String getBulletinId() {
		return bulletinId;
	}
	
	/**
	 * Sets the bulletin id.
	 *
	 * @param bulletinId the new bulletin id
	 */
	public void setBulletinId(String bulletinId) {
		this.bulletinId = bulletinId;
	}
	
	/**
	 * Gets the reply status.
	 *
	 * @return the reply status
	 */
	public String getReplyStatus() {
		return replyStatus;
	}
	
	/**
	 * Sets the reply status.
	 *
	 * @param replyStatus the new reply status
	 */
	public void setReplyStatus(String replyStatus) {
		this.replyStatus = replyStatus;
	}
	
	/**
	 * Gets the center.
	 *
	 * @return the center
	 */
	public String getCenter() {
		return center;
	}
	
	/**
	 * Sets the center.
	 *
	 * @param center the new center
	 */
	public void setCenter(String center) {
		this.center = center;
	}
	
	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	
	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	
	/**
	 * Gets the hosptype.
	 *
	 * @return the hosptype
	 */
	public String getHosptype() {
		return hosptype;
	}
	
	/**
	 * Sets the hosptype.
	 *
	 * @param hosptype the new hosptype
	 */
	public void setHosptype(String hosptype) {
		this.hosptype = hosptype;
	}
	
	/**
	 * Gets the hosp name.
	 *
	 * @return the hosp name
	 */
	public String getHospName() {
		return hospName;
	}
	
	/**
	 * Sets the hosp name.
	 *
	 * @param hospName the new hosp name
	 */
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	
	/**
	 * Gets the effect date.
	 *
	 * @return the effect date
	 */
	public String getEffectDate() {
		return effectDate;
	}
	
	/**
	 * Sets the effect date.
	 *
	 * @param effectDate the new effect date
	 */
	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	/**
	 * Gets the user kind.
	 *
	 * @return the user kind
	 */
	public List getUserKind() {
		return userKind;
	}

	/**
	 * Sets the user kind.
	 *
	 * @param userKind the new user kind
	 */
	public void setUserKind(List userKind) {
		this.userKind = userKind;
	}

	/**
	 * Gets the reply flag.
	 *
	 * @return the reply flag
	 */
	public List getReplyFlag() {
		return replyFlag;
	}

	/**
	 * Sets the reply flag.
	 *
	 * @param replyFlag the new reply flag
	 */
	public void setReplyFlag(List replyFlag) {
		this.replyFlag = replyFlag;
	}

	/**
	 * Gets the bulletin content.
	 *
	 * @return the bulletin content
	 */
	public String getBulletinContent() {
		return bulletinContent;
	}

	/**
	 * Sets the bulletin content.
	 *
	 * @param bulletinContent the new bulletin content
	 */
	public void setBulletinContent(String bulletinContent) {
		this.bulletinContent = bulletinContent;
	}

	/**
	 * Gets the reply man.
	 *
	 * @return the reply man
	 */
	public String getReplyMan() {
		return replyMan;
	}

	/**
	 * Sets the reply man.
	 *
	 * @param replyMan the new reply man
	 */
	public void setReplyMan(String replyMan) {
		this.replyMan = replyMan;
	}
}
