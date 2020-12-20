/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.entity;

import java.io.Serializable;

/**
 * 监控action表.
 */
public class SysMonitorAction implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** ACTION号. */
	private java.lang.String actionNo;

	/** ACTION发生时间. */
	private java.util.Date actionDate;

	/** 日志级别(1:TRACE 2:DEBUG 3:INFO 4:WARN 5:ERROR 6:FATAL). */
	private java.lang.Integer logLevel;

	/** ACTION总耗时. */
	private java.lang.Integer actionTotaltime;

	/** ACTION访问地址. */
	private java.lang.String actionUrl;

	/** ACTION参数. */
	private java.lang.String actionParam;

	/** ACTION结果. */
	private java.lang.String actionResult;

	/** ACTION视图. */
	private java.lang.String actionView;

	/** ACTION调用者. */
	private java.lang.String actionCaller;

	/**
	 * 获取 ACTION号 的属性值.
	 * 
	 * @return actionNo : ACTION号
	 */
	public java.lang.String getActionNo() {
		return this.actionNo;
	}

	/**
	 * 设置 ACTION号 的属性值.
	 * 
	 * @param actionNo
	 *            : ACTION号
	 */
	public void setActionNo(java.lang.String actionNo) {
		this.actionNo = actionNo;
	}

	/**
	 * 获取 ACTION发生时间 的属性值.
	 * 
	 * @return actionDate : ACTION发生时间
	 */
	public java.util.Date getActionDate() {
		return this.actionDate;
	}

	/**
	 * 设置 ACTION发生时间 的属性值.
	 * 
	 * @param actionDate
	 *            : ACTION发生时间
	 */
	public void setActionDate(java.util.Date actionDate) {
		this.actionDate = actionDate;
	}

	/**
	 * 获取 日志级别(1:TRACE 2:DEBUG 3:INFO 4:WARN 5:ERROR 6:FATAL) 的属性值.
	 * 
	 * @return logLevel : 日志级别(1:TRACE 2:DEBUG 3:INFO 4:WARN 5:ERROR 6:FATAL)
	 */
	public java.lang.Integer getLogLevel() {
		return this.logLevel;
	}

	/**
	 * 设置 日志级别(1:TRACE 2:DEBUG 3:INFO 4:WARN 5:ERROR 6:FATAL) 的属性值.
	 * 
	 * @param logLevel
	 *            : 日志级别(1:TRACE 2:DEBUG 3:INFO 4:WARN 5:ERROR 6:FATAL)
	 */
	public void setLogLevel(java.lang.Integer logLevel) {
		this.logLevel = logLevel;
	}

	/**
	 * 获取 ACTION总耗时 的属性值.
	 * 
	 * @return actionTotaltime : ACTION总耗时
	 */
	public java.lang.Integer getActionTotaltime() {
		return this.actionTotaltime;
	}

	/**
	 * 设置 ACTION总耗时 的属性值.
	 * 
	 * @param actionTotaltime
	 *            : ACTION总耗时
	 */
	public void setActionTotaltime(java.lang.Integer actionTotaltime) {
		this.actionTotaltime = actionTotaltime;
	}

	/**
	 * 获取 ACTION访问地址 的属性值.
	 * 
	 * @return actionUrl : ACTION访问地址
	 */
	public java.lang.String getActionUrl() {
		return this.actionUrl;
	}

	/**
	 * 设置 ACTION访问地址 的属性值.
	 * 
	 * @param actionUrl
	 *            : ACTION访问地址
	 */
	public void setActionUrl(java.lang.String actionUrl) {
		this.actionUrl = actionUrl;
	}

	/**
	 * 获取 ACTION参数 的属性值.
	 * 
	 * @return actionParam : ACTION参数
	 */
	public java.lang.String getActionParam() {
		return this.actionParam;
	}

	/**
	 * 设置 ACTION参数 的属性值.
	 * 
	 * @param actionParam
	 *            : ACTION参数
	 */
	public void setActionParam(java.lang.String actionParam) {
		this.actionParam = actionParam;
	}

	/**
	 * 获取 ACTION结果 的属性值.
	 * 
	 * @return actionResult : ACTION结果
	 */
	public java.lang.String getActionResult() {
		return this.actionResult;
	}

	/**
	 * 设置 ACTION结果 的属性值.
	 * 
	 * @param actionResult
	 *            : ACTION结果
	 */
	public void setActionResult(java.lang.String actionResult) {
		this.actionResult = actionResult;
	}

	/**
	 * 获取 ACTION视图 的属性值.
	 * 
	 * @return actionView : ACTION视图
	 */
	public java.lang.String getActionView() {
		return this.actionView;
	}

	/**
	 * 设置 ACTION视图 的属性值.
	 * 
	 * @param actionView
	 *            : ACTION视图
	 */
	public void setActionView(java.lang.String actionView) {
		this.actionView = actionView;
	}

	/**
	 * 获取 ACTION调用者 的属性值.
	 * 
	 * @return actionCaller : ACTION调用者
	 */
	public java.lang.String getActionCaller() {
		return this.actionCaller;
	}

	/**
	 * 设置 ACTION调用者 的属性值.
	 * 
	 * @param actionCaller
	 *            : ACTION调用者
	 */
	public void setActionCaller(java.lang.String actionCaller) {
		this.actionCaller = actionCaller;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "{" + "actionNo(ACTION号)="
				+ actionNo
				+ ","
				+ "actionDate(ACTION发生时间)="
				+ actionDate
				+ ","
				+ "logLevel(日志级别)="
				+ logLevel + "," + "actionTotaltime(ACTION总耗时)="
				+ actionTotaltime + "," + "actionUrl(ACTION访问地址)=" + actionUrl
				+ "," + "actionParam(ACTION参数)=" + actionParam + ","
				+ "actionResult(ACTION结果)=" + actionResult + ","
				+ "actionView(ACTION视图)=" + actionView + ","
				+ "actionCaller(ACTION调用者)=" + actionCaller + "}";
	}
}