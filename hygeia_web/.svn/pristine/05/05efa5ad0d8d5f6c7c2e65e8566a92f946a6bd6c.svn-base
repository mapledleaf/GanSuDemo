/*
 * Copyright 2014 Powersi. All rights reserved.
 */

package com.powersi.sys.manager.action;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.action.UploadAction;
import com.powersi.sys.manager.util.ActionMonitorUtil;
import com.powersi.sys.manager.util.ActionMonitorUtil2;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

/**
 * The Class ActionMonitorAction.
 */
@Action(value = "ActionMonitor", results = {
		@Result(name = "success", location = "/pages/sys/manager/ActionMonitor.jsp")
})
public class ActionMonitorAction extends UploadAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Make log list4 file.
	 * 
	 * @return the string
	 */
	public String importLogFile() {
		try {
			String type = getParameter("type");
			File file = null;
			if ("server".equals(type)) {
				file = new File(LogHelper.getLogPath()
						+ getParameter("filename"));
			} else {
				if (uploads.size() > 0) {
					file = uploads.get(0);
				}
			}

			if (file != null) {
				long startTime = new java.util.Date().getTime();
				if ("true".equals(getParameter("clearflag"))) {
					if (isMongo()) {
						ActionMonitorUtil2.clearMonitorLog();
					} else {
						ActionMonitorUtil.clearMonitorLog();
					}
				}

				String skipdate = getParameter("skipdate");
				int ret = isMongo() ? ActionMonitorUtil2.importLogFile(file,
						skipdate) : ActionMonitorUtil
						.importLogFile(file, skipdate);

				saveJSONMessage("共导入 "
						+ MessageFormat.format("{0,number,###,###,###}",
								ret)
						+ " 条日志，\n共耗时 "
						+ MessageFormat.format("{0,number,###,###,###}",
								new java.util.Date().getTime() - startTime)
						+ " 毫秒。");
			} else {
				saveJSONError("请输入日志文件");
			}
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Gets the run perform.
	 * 
	 * @return the run perform
	 */
	public String getRunPerform() {
		try {
			String type = getParameter("type");

			PagerHelper.initPagination(getRequest());
			List lst = null;
			Map params = getAllParameters();
			if ("time".equals(type)) {
				if (isMongo()) {
					lst = ActionMonitorUtil2.getTimeList(params);
				} else {
					lst = ActionMonitorUtil.getTimeList(params);
				}
			} else if ("caller".equals(type)) {
				if (isMongo()) {
					lst = ActionMonitorUtil2.getCallerList(params);
				} else {
					lst = ActionMonitorUtil.getCallerList(params);
				}
			} else if ("date".equals(type)) {
				if (isMongo()) {
					lst = ActionMonitorUtil2.getDateList(params);
				} else {
					lst = ActionMonitorUtil.getDateList(params);
				}
			} else {
				lst = new ArrayList();
			}

			DataGridHelper.render(getRequest(), getResponse(),
					PagerHelper.getPaginatedList(lst));
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Clear result.
	 * 
	 * @return the string
	 */
	public String clearPerform() {
		try {
			String fileName = getParameter("filename");
			if (UtilFunc.hasLength(fileName)) {
				LogHelper.clearLogFile(LogHelper.getLogPath() + fileName);
			} else {
				if (isMongo()) {
					ActionMonitorUtil2.clearMonitorLog();
				} else {
					ActionMonitorUtil.clearMonitorLog();
				}
			}
			saveJSONMessage("清理日志成功");
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}
	
	private boolean isMongo() {
		return "mongodb".equals(getParameter("db"));
	}
}
