package com.powersi.sys.entity;

import java.io.Serializable;

/**
 * 监控访问表
 */
public class SysMonitorAccess implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * jvm总内存
	 */
	private java.lang.Long reqJvmTotal;

	/**
	 * 当前jvm内存
	 */
	private java.lang.Long reqJvmCurrent;

	/**
	 * 成功标志。成功为S，失败为F
	 */
	private java.lang.String reqSucFlag;

	/**
	 * reqSize
	 */
	private java.lang.Long reqSize;

	/**
	 * reqStatus
	 */
	private java.lang.String reqStatus;

	/**
	 * 请求号
	 */
	private java.lang.String reqNo;

	/**
	 * 时间点。转换成long以后的datetime
	 */
	private java.lang.Long reqTime;

	/**
	 * 时间点详情。如2013-06-09 10:25:05,572
	 */
	private java.lang.String reqTimeDetail;

	/**
	 * 开始结束标志。B为开始，E为结束
	 */
	private java.lang.String reqFlag;

	/**
	 * 请求人
	 */
	private java.lang.String reqUser;

	/**
	 * 请求类型。如A为Action，J为Jsp，R为resource
	 */
	private java.lang.String reqType;

	/**
	 * 请求url
	 */
	private java.lang.String reqUrl;

	/**
	 * 获取 jvm总内存 的属性值
	 * 
	 * @return reqJvmTotal : jvm总内存
	 * @author tangkf
	 */
	public java.lang.Long getReqJvmTotal() {
		return this.reqJvmTotal;
	}

	/**
	 * 设置 jvm总内存 的属性值
	 * 
	 * @param reqJvmTotal
	 *            : jvm总内存
	 * @author tangkf
	 */
	public void setReqJvmTotal(java.lang.Long reqJvmTotal) {
		this.reqJvmTotal = reqJvmTotal;
	}

	/**
	 * 获取 当前jvm内存 的属性值
	 * 
	 * @return reqJvmCurrent : 当前jvm内存
	 * @author tangkf
	 */
	public java.lang.Long getReqJvmCurrent() {
		return this.reqJvmCurrent;
	}

	/**
	 * 设置 当前jvm内存 的属性值
	 * 
	 * @param reqJvmCurrent
	 *            : 当前jvm内存
	 * @author tangkf
	 */
	public void setReqJvmCurrent(java.lang.Long reqJvmCurrent) {
		this.reqJvmCurrent = reqJvmCurrent;
	}

	/**
	 * 获取 成功标志。成功为S，失败为F 的属性值
	 * 
	 * @return reqSucFlag : 成功标志。成功为S，失败为F
	 * @author tangkf
	 */
	public java.lang.String getReqSucFlag() {
		return this.reqSucFlag;
	}

	/**
	 * 设置 成功标志。成功为S，失败为F 的属性值
	 * 
	 * @param reqSucFlag
	 *            : 成功标志。成功为S，失败为F
	 * @author tangkf
	 */
	public void setReqSucFlag(java.lang.String reqSucFlag) {
		this.reqSucFlag = reqSucFlag;
	}

	/**
	 * 获取 reqSize 的属性值
	 * 
	 * @return reqSize : reqSize
	 * @author tangkf
	 */
	public java.lang.Long getReqSize() {
		return this.reqSize;
	}

	/**
	 * 设置 reqSize 的属性值
	 * 
	 * @param reqSize
	 *            : reqSize
	 * @author tangkf
	 */
	public void setReqSize(java.lang.Long reqSize) {
		this.reqSize = reqSize;
	}

	/**
	 * 获取 reqStatus 的属性值
	 * 
	 * @return reqStatus : reqStatus
	 * @author tangkf
	 */
	public java.lang.String getReqStatus() {
		return this.reqStatus;
	}

	/**
	 * 设置 reqStatus 的属性值
	 * 
	 * @param reqStatus
	 *            : reqStatus
	 * @author tangkf
	 */
	public void setrReqStatus(java.lang.String reqStatus) {
		this.reqStatus = reqStatus;
	}

	/**
	 * 获取 请求号 的属性值
	 * 
	 * @return reqNo : 请求号
	 * @author tangkf
	 */
	public java.lang.String getReqNo() {
		return this.reqNo;
	}

	/**
	 * 设置 请求号 的属性值
	 * 
	 * @param reqNo
	 *            : 请求号
	 * @author tangkf
	 */
	public void setReqNo(java.lang.String reqNo) {
		this.reqNo = reqNo;
	}

	/**
	 * 获取 时间点。转换成long以后的datetime 的属性值
	 * 
	 * @return reqTime : 时间点。转换成long以后的datetime
	 * @author tangkf
	 */
	public java.lang.Long getReqTime() {
		return this.reqTime;
	}

	/**
	 * 设置 时间点。转换成long以后的datetime 的属性值
	 * 
	 * @param reqTime
	 *            : 时间点。转换成long以后的datetime
	 * @author tangkf
	 */
	public void setReqTime(java.lang.Long reqTime) {
		this.reqTime = reqTime;
	}

	/**
	 * 获取 时间点详情。如2013-06-09 10:25:05,572 的属性值
	 * 
	 * @return reqTimeDetail : 时间点详情。如2013-06-09 10:25:05,572
	 * @author tangkf
	 */
	public java.lang.String getReqTimeDetail() {
		return this.reqTimeDetail;
	}

	/**
	 * 设置 时间点详情。如2013-06-09 10:25:05,572 的属性值
	 * 
	 * @param reqTimeDetail
	 *            : 时间点详情。如2013-06-09 10:25:05,572
	 * @author tangkf
	 */
	public void setReqTimeDetail(java.lang.String reqTimeDetail) {
		this.reqTimeDetail = reqTimeDetail;
	}

	/**
	 * 获取 开始结束标志。B为开始，E为结束 的属性值
	 * 
	 * @return reqFlag : 开始结束标志。B为开始，E为结束
	 * @author tangkf
	 */
	public java.lang.String getReqFlag() {
		return this.reqFlag;
	}

	/**
	 * 设置 开始结束标志。B为开始，E为结束 的属性值
	 * 
	 * @param reqFlag
	 *            : 开始结束标志。B为开始，E为结束
	 * @author tangkf
	 */
	public void setReqFlag(java.lang.String reqFlag) {
		this.reqFlag = reqFlag;
	}

	/**
	 * 获取 请求人 的属性值
	 * 
	 * @return reqUser : 请求人
	 * @author tangkf
	 */
	public java.lang.String getReqUser() {
		return this.reqUser;
	}

	/**
	 * 设置 请求人 的属性值
	 * 
	 * @param reqUser
	 *            : 请求人
	 * @author tangkf
	 */
	public void setReqUser(java.lang.String reqUser) {
		this.reqUser = reqUser;
	}

	/**
	 * 获取 请求类型。如A为Action，J为Jsp，R为resource 的属性值
	 * 
	 * @return reqType : 请求类型。如A为Action，J为Jsp，R为resource
	 * @author tangkf
	 */
	public java.lang.String getReqType() {
		return this.reqType;
	}

	/**
	 * 设置 请求类型。如A为Action，J为Jsp，R为resource 的属性值
	 * 
	 * @param reqType
	 *            : 请求类型。如A为Action，J为Jsp，R为resource
	 * @author tangkf
	 */
	public void setReqType(java.lang.String reqType) {
		this.reqType = reqType;
	}

	/**
	 * 获取 请求url 的属性值
	 * 
	 * @return reqUrl : 请求url
	 * @author tangkf
	 */
	public java.lang.String getReqUrl() {
		return this.reqUrl;
	}

	/**
	 * 设置 请求url 的属性值
	 * 
	 * @param reqUrl
	 *            : 请求url
	 * @author tangkf
	 */
	public void setReqUrl(java.lang.String reqUrl) {
		this.reqUrl = reqUrl;
	}

	@Override
	public String toString() {
		return "{" + "reqJvmTotal(jvm总内存)=" + reqJvmTotal + ","
				+ "reqJvmCurrent(当前jvm内存)=" + reqJvmCurrent + ","
				+ "reqSucFlag(成功标志。成功为S，失败为F)=" + reqSucFlag + ","
				+ "reqSize()=" + reqSize + "," + "reqStatus()=" + reqStatus
				+ "," + "reqNo(请求号)=" + reqNo + ","
				+ "reqTime(时间点。转换成long以后的datetime)=" + reqTime + ","
				+ "reqTimeDetail(时间点详情。如2013-06-09 10:25:05,572)="
				+ reqTimeDetail + "," + "reqFlag(开始结束标志。B为开始，E为结束)=" + reqFlag
				+ "," + "reqUser(请求人)=" + reqUser + ","
				+ "reqType(请求类型。如A为Action，J为Jsp，R为resource)=" + reqType + ","
				+ "reqUrl(请求url)=" + reqUrl + "}";
	}
}