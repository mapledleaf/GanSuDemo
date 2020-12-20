/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.entity;

import java.io.Serializable;

/**
 * 监控功能表.
 */
public class SysMonitorFunc implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 日志号. */
	private java.lang.String funcNo;

	/** 发生时间. */
	private java.util.Date funcDate;

	/** 日志级别(1:TRACE 2:DEBUG 3:INFO 4:WARN 5:ERROR 6:FATAL). */
	private java.lang.Integer logLevel;

	/** 总耗时. */
	private java.lang.Integer funcTotaltime;

	/** 功能号. */
	private java.lang.String funcId;

	/** 参数. */
	private java.lang.String funcParam;

	/** 结果. */
	private java.lang.String funcResult;

	/** 参数长度. */
	private java.lang.Integer paramLength;

	/** 结果长度. */
	private java.lang.Integer resultLength;

	/**
	 * 获取 日志号 的属性值.
	 * 
	 * @return funcNo : 日志号
	 */
	public java.lang.String getFuncNo() {
		return this.funcNo;
	}

	/**
	 * 设置 日志号 的属性值.
	 * 
	 * @param funcNo
	 *            : 日志号
	 */
	public void setFuncNo(java.lang.String funcNo) {
		this.funcNo = funcNo;
	}

	/**
	 * 获取 发生时间 的属性值.
	 * 
	 * @return funcDate : 发生时间
	 */
	public java.util.Date getFuncDate() {
		return this.funcDate;
	}

	/**
	 * 设置 发生时间 的属性值.
	 * 
	 * @param funcDate
	 *            : 发生时间
	 */
	public void setFuncDate(java.util.Date funcDate) {
		this.funcDate = funcDate;
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
	 * 获取 总耗时 的属性值.
	 * 
	 * @return funcTotaltime : 总耗时
	 */
	public java.lang.Integer getFuncTotaltime() {
		return this.funcTotaltime;
	}

	/**
	 * 设置 总耗时 的属性值.
	 * 
	 * @param funcTotaltime
	 *            : 总耗时
	 */
	public void setFuncTotaltime(java.lang.Integer funcTotaltime) {
		this.funcTotaltime = funcTotaltime;
	}

	/**
	 * 获取 功能号 的属性值.
	 * 
	 * @return funcId : 功能号
	 */
	public java.lang.String getFuncId() {
		return this.funcId;
	}

	/**
	 * 设置 功能号 的属性值.
	 * 
	 * @param funcId
	 *            : 功能号
	 */
	public void setFuncId(java.lang.String funcId) {
		this.funcId = funcId;
	}

	/**
	 * 获取 参数 的属性值.
	 * 
	 * @return funcParam : 参数
	 */
	public java.lang.String getFuncParam() {
		return this.funcParam;
	}

	/**
	 * 设置 参数 的属性值.
	 * 
	 * @param funcParam
	 *            : 参数
	 */
	public void setFuncParam(java.lang.String funcParam) {
		this.funcParam = funcParam;
	}

	/**
	 * 获取 结果 的属性值.
	 * 
	 * @return funcResult : 结果
	 */
	public java.lang.String getFuncResult() {
		return this.funcResult;
	}

	/**
	 * 设置 结果 的属性值.
	 * 
	 * @param funcResult
	 *            : 结果
	 */
	public void setFuncResult(java.lang.String funcResult) {
		this.funcResult = funcResult;
	}

	/**
	 * 获取 参数长度 的属性值.
	 * 
	 * @return paramLength : 参数长度
	 */
	public java.lang.Integer getParamLength() {
		return this.paramLength;
	}

	/**
	 * 设置 参数长度 的属性值.
	 * 
	 * @param paramLength
	 *            : 参数长度
	 */
	public void setParamLength(java.lang.Integer paramLength) {
		this.paramLength = paramLength;
	}

	/**
	 * 获取 结果长度 的属性值.
	 * 
	 * @return resultLength : 结果长度
	 */
	public java.lang.Integer getResultLength() {
		return this.resultLength;
	}

	/**
	 * 设置 结果长度 的属性值.
	 * 
	 * @param resultLength
	 *            : 结果长度
	 */
	public void setResultLength(java.lang.Integer resultLength) {
		this.resultLength = resultLength;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "{" + "funcNo(日志号)=" + funcNo + "," + "funcDate(发生时间)="
				+ funcDate + "," + "logLevel(日志级别)=" + logLevel + ","
				+ "funcTotaltime(总耗时)=" + funcTotaltime + "," + "funcId(功能号)="
				+ funcId + "," + "funcParam(参数)=" + funcParam + ","
				+ "funcResult(结果)=" + funcResult + "," + "paramLength(参数长度)="
				+ paramLength + "," + "resultLength(结果长度)=" + resultLength
				+ "}";
	}
}