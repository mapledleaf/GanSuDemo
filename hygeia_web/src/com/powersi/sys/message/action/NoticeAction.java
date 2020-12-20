/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.message.action;

import org.apache.struts2.convention.annotation.Action;

import com.powersi.hygeia.web.BaseAction;
import com.powersi.sys.util.MessageHelper;

/**
 * The Class NoticeAction.
 */
@Action(value = "NoticeAction")
public class NoticeAction extends BaseAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Polling message.
	 * 
	 * @return the string
	 */
	public String pollingMessage() {
		try {
			// 关闭日志输出
			setAttribute("debugEnabeld", "false");

			return renderJson(MessageHelper.queryMessageList(false));
		} catch (Exception ex) {
			return renderError(ex);
		}
	}
}
