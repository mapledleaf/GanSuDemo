/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.entity;

import java.io.Serializable;

import com.powersi.hygeia.framework.util.UtilFunc;

public class LogRecord implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log level. */
	private int logLevel;

	/** The log date. */
	private String logDate;

	/** The log summary. */
	private String logSummary;

	/** The log text. */
	private StringBuilder logMessage;

	/** The log exception. */
	private StringBuilder logException;

	/** The is exception. */
	private boolean appendException;

	/**
	 * Instantiates a new jdbc log record.
	 */
	public LogRecord() {
		logMessage = new StringBuilder();
		logException = new StringBuilder();
	}

	public void appendLine(String str) {
		if (!UtilFunc.hasText(str)) {
			return;
		}

		// ²ð·ÖmessageºÍexception
		if (logLevel >= 4) {
			if (!appendException) {
				if (str.indexOf("Exception") >= 0) {
					appendException = true;
				}
			}
		}

		if (appendException) {
			if (logException.length() > 0) {
				logException.append("\r\n");
			}
			logException.append(str);
		} else {
			if (logMessage.length() > 0) {
				logMessage.append("\r\n");
			}
			logMessage.append(str);
		}
	}

	/**
	 * Gets the log level.
	 * 
	 * @return the log level
	 */
	public final int getLogLevel() {
		return logLevel;
	}

	/**
	 * Sets the log level.
	 * 
	 * @param logLevel
	 *            the new log level
	 */
	public final void setLogLevel(int logLevel) {
		this.logLevel = logLevel;
	}

	/**
	 * Gets the log date.
	 * 
	 * @return the log date
	 */
	public final String getLogDate() {
		return logDate;
	}

	/**
	 * Sets the log date.
	 * 
	 * @param logDate
	 *            the new log date
	 */
	public final void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	/**
	 * Gets the log summary.
	 * 
	 * @return the log summary
	 */
	public final String getLogSummary() {
		return logSummary;
	}

	/**
	 * Sets the log summary.
	 * 
	 * @param logSummary
	 *            the new log summary
	 */
	public final void setLogSummary(String logSummary) {
		this.logSummary = logSummary;
	}

	/**
	 * Gets the log message.
	 * 
	 * @return the log message
	 */
	public final StringBuilder getLogMessage() {
		return logMessage;
	}

	/**
	 * Sets the log message.
	 * 
	 * @param logMessage
	 *            the new log message
	 */
	public final void setLogMessage(StringBuilder logMessage) {
		this.logMessage = logMessage;
	}

	/**
	 * Gets the log exception.
	 * 
	 * @return the log exception
	 */
	public final StringBuilder getLogException() {
		return logException;
	}

	/**
	 * Sets the log exception.
	 * 
	 * @param logException
	 *            the new log exception
	 */
	public final void setLogException(StringBuilder logException) {
		this.logException = logException;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "LogRecord [logLevel=" + logLevel + ", logDate="
				+ logDate + ", logSummary=" + logSummary + ", logMessage="
				+ logMessage + ", logException=" + logException + "]";
	}

}