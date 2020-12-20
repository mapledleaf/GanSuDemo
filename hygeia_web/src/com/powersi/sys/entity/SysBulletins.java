/*
 * Copyright 2016 Powersi. All rights reserved.
 */

package com.powersi.sys.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 系统公告表.
 */
public class SysBulletins implements Serializable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 公告ID. */
	private java.lang.Long bulletinId;

	/** 公告类型(0:内部公告 1:外部公告). */
	private java.lang.String bulletinType;

	/** 显示序号. */
	private java.lang.Integer disOrder;

	/** 标题. */
	private java.lang.String bulletinTitle;

	/** 内容. */
	private java.lang.String bulletinContent;

	/** 附件批次. */
	private java.lang.String fileBatch;

	/** 发送员工号. */
	private java.lang.Integer senderStaff;

	/** 发送者名称. */
	private java.lang.String senderMan;

	/** 发送时间. */
	private java.util.Date sendDate;

	/** 生效日期. */
	private java.util.Date effectDate;

	/** 失效日期. */
	private java.util.Date expireDate;

	/** 审核标志(0:未审核 1:审核通过 2:审核不通过). */
	private java.lang.String auditFlag;

	/** 审核人工号. */
	private java.lang.Integer auditStaff;

	/** 审核人. */
	private java.lang.String auditMan;

	/** 审签标志(0:未审签 1:审签通过 2:审签不通过). */
	private String secondAuditFlag;

	/** 审签人工号. */
	private java.lang.Integer secondAuditStaff;

	/** 审签人. */
	private java.lang.String secondAuditMan;

	/** 接收人类型. */
	private String userKind;

	/** 接受单位 *. */
	private String receiveString;

	/** 接收用户. */
	private String receiveUsers;// 用 医院名称[收件人名称], 医院名称[收件人名称]...表示

	/** 回执标志 0：不需要回执 1：需要回执 2：需要回执意见. */
	private String replyFlag;

	/** 公告日期. */
	private String bulletinDate;

	/** 文件列表. */
	private List fileList;

	/** 审核类型（0：无 1：审核 2：审签). */
	private String auditType;

	/** 审核意见. */
	private String auditOpinion;

	/** 审签意见. */
	private String secondAuditOpinion;

	/**有效标志**/
	private String validFlag;
	
	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	/**
	 * Gets the file list.
	 * 
	 * @return the file list
	 */
	public List getFileList() {
		return fileList;
	}

	/**
	 * Sets the file list.
	 * 
	 * @param fileList
	 *            the new file list
	 */
	public void setFileList(List fileList) {
		this.fileList = fileList;
	}

	/**
	 * Gets the bulletin date.
	 * 
	 * @return the bulletin date
	 */
	public String getBulletinDate() {
		return bulletinDate;
	}

	/**
	 * Sets the bulletin date.
	 * 
	 * @param bulletinDate
	 *            the new bulletin date
	 */
	public void setBulletinDate(String bulletinDate) {
		this.bulletinDate = bulletinDate;
	}

	/**
	 * Gets the user kind.
	 * 
	 * @return the user kind
	 */
	public String getUserKind() {
		return userKind;
	}

	/**
	 * Sets the user kind.
	 * 
	 * @param userKind
	 *            the new user kind
	 */
	public void setUserKind(String userKind) {
		this.userKind = userKind;
	}

	/**
	 * Gets the receive string.
	 * 
	 * @return the receive string
	 */
	public String getReceiveString() {
		return receiveString;
	}

	/**
	 * Sets the receive string.
	 * 
	 * @param receiveString
	 *            the new receive string
	 */
	public void setReceiveString(String receiveString) {
		this.receiveString = receiveString;
	}

	/**
	 * Gets the second audit flag.
	 * 
	 * @return the second audit flag
	 */
	public String getSecondAuditFlag() {
		return secondAuditFlag;
	}

	/**
	 * Sets the second audit flag.
	 * 
	 * @param secondAuditFlag
	 *            the new second audit flag
	 */
	public void setSecondAuditFlag(String secondAuditFlag) {
		this.secondAuditFlag = secondAuditFlag;
	}

	/**
	 * Gets the reply flag.
	 * 
	 * @return the reply flag
	 */
	public String getReplyFlag() {
		return replyFlag;
	}

	/**
	 * Sets the reply flag.
	 * 
	 * @param replyFlag
	 *            the new reply flag
	 */
	public void setReplyFlag(String replyFlag) {
		this.replyFlag = replyFlag;
	}

	/**
	 * 获取 公告ID 的属性值.
	 * 
	 * @return bulletinId : 公告ID
	 */
	public java.lang.Long getBulletinId() {
		return this.bulletinId;
	}

	/**
	 * 设置 公告ID 的属性值.
	 * 
	 * @param bulletinId
	 *            : 公告ID
	 */
	public void setBulletinId(java.lang.Long bulletinId) {
		this.bulletinId = bulletinId;
	}

	/**
	 * 获取 公告类型(0:内部公告 1:外部公告) 的属性值.
	 * 
	 * @return bulletinType : 公告类型(0:内部公告 1:外部公告)
	 */
	public java.lang.String getBulletinType() {
		return this.bulletinType;
	}

	/**
	 * 设置 公告类型(0:内部公告 1:外部公告) 的属性值.
	 * 
	 * @param bulletinType
	 *            : 公告类型(0:内部公告 1:外部公告)
	 */
	public void setBulletinType(java.lang.String bulletinType) {
		this.bulletinType = bulletinType;
	}

	/**
	 * 获取 显示序号 的属性值.
	 * 
	 * @return disOrder : 显示序号
	 */
	public java.lang.Integer getDisOrder() {
		return this.disOrder;
	}

	/**
	 * 设置 显示序号 的属性值.
	 * 
	 * @param disOrder
	 *            : 显示序号
	 */
	public void setDisOrder(java.lang.Integer disOrder) {
		this.disOrder = disOrder;
	}

	/**
	 * 获取 标题 的属性值.
	 * 
	 * @return bulletinTitle : 标题
	 */
	public java.lang.String getBulletinTitle() {
		return this.bulletinTitle;
	}

	/**
	 * 设置 标题 的属性值.
	 * 
	 * @param bulletinTitle
	 *            : 标题
	 */
	public void setBulletinTitle(java.lang.String bulletinTitle) {
		this.bulletinTitle = bulletinTitle;
		if (this.bulletinTitle != null) {
			this.bulletinTitle = this.bulletinTitle.trim().replaceAll("'", "")
					.replaceAll("\"", "");
		}
	}

	/**
	 * 获取 内容 的属性值.
	 * 
	 * @return bulletinContent : 内容
	 */
	public java.lang.String getBulletinContent() {
		return this.bulletinContent;
	}

	/**
	 * 设置 内容 的属性值.
	 * 
	 * @param bulletinContent
	 *            : 内容
	 */
	public void setBulletinContent(java.lang.String bulletinContent) {
		this.bulletinContent = bulletinContent;
		if (this.bulletinContent != null) {
			this.bulletinContent = this.bulletinContent.trim();
		}
	}

	/**
	 * 获取 附件批次 的属性值.
	 * 
	 * @return fileBatch : 附件批次
	 */
	public java.lang.String getFileBatch() {
		return this.fileBatch;
	}

	/**
	 * 设置 附件批次 的属性值.
	 * 
	 * @param fileBatch
	 *            : 附件批次
	 */
	public void setFileBatch(java.lang.String fileBatch) {
		this.fileBatch = fileBatch;
	}

	/**
	 * 获取 发送员工号 的属性值.
	 * 
	 * @return senderStaff : 发送员工号
	 */
	public java.lang.Integer getSenderStaff() {
		return this.senderStaff;
	}

	/**
	 * 设置 发送员工号 的属性值.
	 * 
	 * @param senderStaff
	 *            : 发送员工号
	 */
	public void setSenderStaff(java.lang.Integer senderStaff) {
		this.senderStaff = senderStaff;
	}

	/**
	 * 获取 发送者名称 的属性值.
	 * 
	 * @return senderMan : 发送者名称
	 */
	public java.lang.String getSenderMan() {
		return this.senderMan;
	}

	/**
	 * 设置 发送者名称 的属性值.
	 * 
	 * @param senderMan
	 *            : 发送者名称
	 */
	public void setSenderMan(java.lang.String senderMan) {
		this.senderMan = senderMan;
	}

	/**
	 * 获取 发送时间 的属性值.
	 * 
	 * @return sendDate : 发送时间
	 */
	public java.util.Date getSendDate() {
		return this.sendDate;
	}

	/**
	 * 设置 发送时间 的属性值.
	 * 
	 * @param sendDate
	 *            : 发送时间
	 */
	public void setSendDate(java.util.Date sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * 获取 生效日期 的属性值.
	 * 
	 * @return effectDate : 生效日期
	 */
	public java.util.Date getEffectDate() {
		return this.effectDate;
	}

	/**
	 * 设置 生效日期 的属性值.
	 * 
	 * @param effectDate
	 *            : 生效日期
	 */
	public void setEffectDate(java.util.Date effectDate) {
		this.effectDate = effectDate;
	}

	/**
	 * 获取 失效日期 的属性值.
	 * 
	 * @return expireDate : 失效日期
	 */
	public java.util.Date getExpireDate() {
		return this.expireDate;
	}

	/**
	 * 设置 失效日期 的属性值.
	 * 
	 * @param expireDate
	 *            : 失效日期
	 */
	public void setExpireDate(java.util.Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * 获取 审核标志(0:未审核 1:审核通过 2:审核不通过) 的属性值.
	 * 
	 * @return auditFlag : 审核标志(0:未审核 1:审核通过 2:审核不通过)
	 */
	public java.lang.String getAuditFlag() {
		return this.auditFlag;
	}

	/**
	 * 设置 审核标志(0:未审核 1:审核通过 2:审核不通过) 的属性值.
	 * 
	 * @param auditFlag
	 *            : 审核标志(0:未审核 1:审核通过 2:审核不通过
	 */
	public void setAuditFlag(java.lang.String auditFlag) {
		this.auditFlag = auditFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SysBulletins [bulletinId=" + bulletinId + ", bulletinType="
				+ bulletinType + ", disOrder=" + disOrder + ", bulletinTitle="
				+ bulletinTitle + ", bulletinContent=" + bulletinContent
				+ ", fileBatch=" + fileBatch + ", senderStaff=" + senderStaff
				+ ", senderMan=" + senderMan + ", sendDate=" + sendDate
				+ ", effectDate=" + effectDate + ", expireDate=" + expireDate
				+ ", auditFlag=" + auditFlag + "]";
	}

	/**
	 * Gets the receive users.
	 * 
	 * @return the receive users
	 */
	public String getReceiveUsers() {
		return receiveUsers;
	}

	/**
	 * Sets the receive users.
	 * 
	 * @param receiveUsers
	 *            the new receive users
	 */
	public void setReceiveUsers(String receiveUsers) {
		this.receiveUsers = receiveUsers;
	}

	/**
	 * Gets the audit staff.
	 * 
	 * @return the audit staff
	 */
	public java.lang.Integer getAuditStaff() {
		return auditStaff;
	}

	/**
	 * Sets the audit staff.
	 * 
	 * @param auditStaff
	 *            the new audit staff
	 */
	public void setAuditStaff(java.lang.Integer auditStaff) {
		this.auditStaff = auditStaff;
	}

	/**
	 * Gets the audit man.
	 * 
	 * @return the audit man
	 */
	public java.lang.String getAuditMan() {
		return auditMan;
	}

	/**
	 * Sets the audit man.
	 * 
	 * @param auditMan
	 *            the new audit man
	 */
	public void setAuditMan(java.lang.String auditMan) {
		this.auditMan = auditMan;
	}

	/**
	 * Gets the second audit staff.
	 * 
	 * @return the second audit staff
	 */
	public java.lang.Integer getSecondAuditStaff() {
		return secondAuditStaff;
	}

	/**
	 * Sets the second audit staff.
	 * 
	 * @param secondAuditStaff
	 *            the new second audit staff
	 */
	public void setSecondAuditStaff(java.lang.Integer secondAuditStaff) {
		this.secondAuditStaff = secondAuditStaff;
	}

	/**
	 * Gets the second audit man.
	 * 
	 * @return the second audit man
	 */
	public java.lang.String getSecondAuditMan() {
		return secondAuditMan;
	}

	/**
	 * Sets the second audit man.
	 * 
	 * @param secondAuditMan
	 *            the new second audit man
	 */
	public void setSecondAuditMan(java.lang.String secondAuditMan) {
		this.secondAuditMan = secondAuditMan;
	}

	/**
	 * Gets the audit type.
	 * 
	 * @return the audit type
	 */
	public final String getAuditType() {
		return auditType;
	}

	/**
	 * Sets the audit type.
	 * 
	 * @param auditType
	 *            the new audit type
	 */
	public final void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	/**
	 * Gets the audit opinion.
	 * 
	 * @return the audit opinion
	 */
	public final String getAuditOpinion() {
		return auditOpinion;
	}

	/**
	 * Sets the audit opinion.
	 * 
	 * @param auditOpinion
	 *            the new audit opinion
	 */
	public final void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	/**
	 * Gets the second audit opinion.
	 * 
	 * @return the second audit opinion
	 */
	public final String getSecondAuditOpinion() {
		return secondAuditOpinion;
	}

	/**
	 * Sets the second audit opinion.
	 * 
	 * @param secondAuditOpinion
	 *            the new second audit opinion
	 */
	public final void setSecondAuditOpinion(String secondAuditOpinion) {
		this.secondAuditOpinion = secondAuditOpinion;
	}
}