/*
 * Copyright 2013 Powersi. All rights reserved.
 */

package com.powersi.sys.manager.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.action.UploadAction;
import com.powersi.sys.manager.util.AccessMonitorUtil;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

/**
 * The Class AccessMonitorAction.
 */
@Action(value = "AccessMonitor", results = {
		@Result(name = "success", location = "/pages/sys/manager/AccessMonitor.jsp")
})
public class AccessMonitorAction extends UploadAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Make log list4 file.
	 * 
	 * @return the string
	 */
	public String makeLogList4File() {
		try {
			String type = getParameter("type");
			List<String> files = new ArrayList<String>();
			if ("server".equals(type)) {
				files.add(LogHelper.getLogPath() + getParameter("filename"));
			} else {
				for (int i = 0; i < uploads.size(); i++) {
					files.add(uploads.get(i).getAbsolutePath());
				}
			}
			if (files.size() > 0) {
				AccessMonitorUtil.makeLogList4File(files);
				saveJSONMessage("日志导入成功");
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
				lst = AccessMonitorUtil.getRunPerform(params);
			} else if ("jvm".equals(type)) {
				lst = AccessMonitorUtil.getJvmPerform(params);
			} else if ("acc".equals(type)) {
				lst = AccessMonitorUtil.getRunAccess(params);
			} else if ("func".equals(type)) {
				lst = AccessMonitorUtil.getFuncAccess(params);
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
	 * Clear perform log.
	 * 
	 * @return the string
	 */
	public String clearPerformRs() {
		try {
			String fileName = getParameter("filename");
			if (UtilFunc.hasLength(fileName)) {
				LogHelper.clearLogFile(LogHelper.getLogPath() + fileName);
			} else {
				AccessMonitorUtil.clearMonitorLog();
			}
			saveJSONMessage("清理日志成功");
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}
}
