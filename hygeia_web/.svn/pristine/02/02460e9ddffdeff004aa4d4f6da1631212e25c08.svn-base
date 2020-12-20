/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.sys.entity.DownloadFile;
import com.powersi.sys.manager.dao.LogDAO;

/**
 * The Class QuerySystemLogAction.
 */

/**
 * The Class QuerySystemLogAction.
 */
@Action(value = "QuerySystemLog", results = {
		@Result(name = "success", location = "/pages/sys/manager/ServerLog.jsp"),
		@Result(name = "list", location = "/pages/sys/manager/SystemLog.jsp")
})
public class QuerySystemLogAction extends BaseAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The download file. */
	private DownloadFile downloadFile;

	/** The begin date. */
	private String beginDate = null;

	/** The end date. */
	private String endDate = null;

	/** The log level. */
	private List logLevel = null;

	/** The all server. */
	private String serverName = null;

	/** The log name. */
	private String logName = null;

	/** The log message. */
	private String logMessage = null;

	/** The log exception. */
	private String logException = null;

	/** The operate name. */
	private String operateName = null;

	/** The login user. */
	private String loginUser = null;

	/** The user address. */
	private String userAddress = null;

	/** The log dao. */
	private static LogDAO dao = getBean(LogDAO.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		try {
			if (isPostRequest()) {
				if ("true".equals(getParameter("server"))) {
					Map info = new HashMap();
					info.put("host_name", getHostName());
					info.put("log_path", getLogPath());
					return renderJson(info);
				} else {
					renderGrid(getLogList());
				}
				return NONE;
			} else {
				return SUCCESS;
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Gets the log list.
	 * 
	 * @return the log list
	 */
	private List getLogList() {
		List lst = new ArrayList();
		String logPath = getLogPath();

		MessageFormat mf = new MessageFormat("{0, number, ###,###,###,###}");
		File root = new File(logPath);
		if (root.exists() && root.isDirectory()) {
			File[] files = root.listFiles();
			for (File file : files) {
				if (file.isFile()) {
					Map map = new HashMap();
					map.put("file_name", file.getName());
					map.put("file_length", mf.format(new Object[] { Long
							.valueOf(file.length()) }));
					map.put("last_modified",
							new java.util.Date(file.lastModified()));
					lst.add(map);
				}
			}
		}

		return lst;
	}

	/**
	 * View.
	 * 
	 * @return the string
	 */

	/**
	 * View.
	 * 
	 * @return the string
	 */
	@Action(value = "ViewSystemLog", results = {
			@Result(name = "success", type = "stream", params = {
					"contentType", "${downloadFile.contentType}",
					"contentDisposition", "${downloadFile.contentDisposition}",
					"inputName", "downloadFile.fileStream"
			})
	})
	public String view() {
		try {
			String fileName = getParameter("fileName");
			if (isEmpty(fileName)) {
				throw new HygeiaException("文件名不能为空");
			}

			if ("true".equals(getParameter("zip"))) {
				return downloadLogWithZip();
			} else {
				String logFile = getLogPath() + fileName;
				downloadFile = new DownloadFile(logFile);

				return SUCCESS;
			}
		} catch (Exception ex) {
			return renderError("查看服务器日志出错", ex);
		}
	}

	/**
	 * Download zip.
	 * 
	 * @return the string
	 */
	public String downloadLogWithZip() {
		try {
			String path = getLogPath();
			String fileName = getParameter("fileName");

			List<String> lst = null;
			if ("all".equals(fileName)) {
				lst = new ArrayList<String>();
				File root = new File(path);
				if (root.exists() && root.isDirectory()) {
					File[] files = root.listFiles();
					for (File file : files) {
						if (file.isFile()) {
							lst.add(file.getName());
						}
					}
				}
			} else {
				lst = UtilFunc.tokenizeToList(getParameter("fileName"), ",");
			}

			if (lst == null || lst.size() == 0) {
				return renderError("请输入文件名称");
			}

			getResponse().setContentType("application/octet-stream");
			getResponse().setHeader(
					"Content-disposition",
					String.format(
							"attachment; filename=\"log%1$s.zip\"", DateFunc
									.dateToString(new java.util.Date(),
											"yyyyMMddHHmmss")));

			ZipOutputStream zos = new ZipOutputStream(getResponse()
					.getOutputStream());
			zos.setComment(new String(String.format("%1$s logs",
					GlobalContext.getInstanceName()).getBytes(), "UTF-8"));

			for (String str : lst) {
				File file = new File(path + str);
				if (file.exists() && file.isFile()) {
					BufferedInputStream bis = new BufferedInputStream(
							new FileInputStream(file));
					zos.putNextEntry(new ZipEntry(file.getName()));

					byte[] buffer = new byte[1024];
					int c = 0;
					while ((c = bis.read(buffer)) != -1) {
						zos.write(buffer, 0, c);
					}
					bis.close();

					zos.flush();
					zos.closeEntry();
				}
			}
			zos.close();
		} catch (Exception ex) {
			LogHelper.getLogger().error("批量下载服务器日志出错", ex);
		}

		return NONE;
	}

	/**
	 * View.
	 * 
	 * @return the string
	 */
	@Action("ClearSystemLog")
	public String clear() {
		try {
			String fileName = getParameter("fileName");
			if (isEmpty(fileName)) {
				throw new HygeiaException("文件名不能为空");
			}

			String path = getLogPath();
			if ("all".equals(fileName)) {
				List<Map> lst = getLogList();
				for (Map m : lst) {
					LogHelper.clearLogFile(path + (String) m.get("file_name"));
				}

				return renderMessage("共清理 " + String.valueOf(lst.size()) + " 个日志文件");
			} else {
				LogHelper.clearLogFile(path + fileName);
				return renderMessage("清理 " + fileName + " 成功");
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Gets the host name.
	 * 
	 * @return the host name
	 */
	public String getHostName() {
		return GlobalContext.getHostName();
	}

	/**
	 * Gets the log path.
	 * 
	 * @return the log path
	 */
	public String getLogPath() {
		return LogHelper.getLogPath();
	}

	/**
	 * Query.
	 * 
	 * @return the string
	 */
	public String query() {
		try {
			if (isEmpty(beginDate)) {
				java.util.Date now = new java.util.Date();
				beginDate = DateFunc.dateToString(now) + " 00:00:00";
				endDate = DateFunc.dateToString(now) + " 23:59:59";

				// 集群环境下默认显示全部
				if (!GlobalContext.isClusters()) {
					serverName = GlobalContext.getHostName();
				}

				// 清理n天以前的系统日志
				int days = Integer.parseInt(ParameterMapping.getStringByCode(
						"system_log_days", "30"));
				if (days < 1) {
					days = 1;
				}
				dao.clearSystemLog(DateFunc.addDays(new java.util.Date(),
						0 - days));
			}

			Map<String, String> code = new LinkedHashMap<String, String>();
			code.put("TRACE", "跟踪");
			code.put("DEBUG", "调试");
			code.put("INFO", "提示");
			code.put("WARN", "警告");
			code.put("ERROR", "错误");
			code.put("FATAL", "严重错误");

			setAttribute("codeLogLevel", code);

			Map paramMap = new HashMap();
			paramMap.putAll(getAllParameters());

			paramMap.put("serverName", serverName);

			if (logLevel == null) {
				logLevel = new ArrayList<String>();
				logLevel.addAll(code.keySet());
				paramMap.put("logLevel", UtilFunc.toStringArray(logLevel));
			}

			if (isPostRequest()) {
				initPager();
				List lst = dao.querySystemLog(beginDate, endDate, paramMap);
				for (int i = 0; i < lst.size(); i++) {
					Map map = (Map) lst.get(i);
					String logName = getString(map, "log_name", "");
					if (logName != null && logName.length() > 0) {
						int pos = logName.lastIndexOf(".");
						if (pos >= 0) {
							map.put("log_name", logName.substring(pos + 1));
						}
					}
				}

				return renderGrid(lst);
			} else {
				return "list";
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Detail.
	 * 
	 * @return the string
	 */
	public String detail() {
		try {
			String logSn = getParameter("log_sn");
			if (isEmpty(logSn)) {
				throw new HygeiaException("日志流水号不能为空");
			}

			return renderJson(dao.getSystemLog(logSn));
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Gets the download file.
	 * 
	 * @return the download file
	 */
	public DownloadFile getDownloadFile() {
		return downloadFile;
	}

	/**
	 * Sets the download file.
	 * 
	 * @param downloadFile
	 *            the new download file
	 */
	public void setDownloadFile(DownloadFile downloadFile) {
		this.downloadFile = downloadFile;
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
	 * @param beginDate
	 *            the new begin date
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
	 * @param endDate
	 *            the new end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the log level.
	 * 
	 * @return the log level
	 */
	public List getLogLevel() {
		return logLevel;
	}

	/**
	 * Sets the log level.
	 * 
	 * @param logLevel
	 *            the new log level
	 */
	public void setLogLevel(List logLevel) {
		this.logLevel = logLevel;
	}

	/**
	 * Gets the server name.
	 * 
	 * @return the server name
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * Sets the server name.
	 * 
	 * @param serverName
	 *            the new server name
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * Gets the log name.
	 * 
	 * @return the log name
	 */
	public String getLogName() {
		return logName;
	}

	/**
	 * Sets the log name.
	 * 
	 * @param logName
	 *            the new log name
	 */
	public void setLogName(String logName) {
		this.logName = logName;
	}

	/**
	 * Gets the log message.
	 * 
	 * @return the log message
	 */
	public String getLogMessage() {
		return logMessage;
	}

	/**
	 * Sets the log message.
	 * 
	 * @param logMessage
	 *            the new log message
	 */
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	/**
	 * Gets the log exception.
	 * 
	 * @return the log exception
	 */
	public String getLogException() {
		return logException;
	}

	/**
	 * Sets the log exception.
	 * 
	 * @param logException
	 *            the new log exception
	 */
	public void setLogException(String logException) {
		this.logException = logException;
	}

	/**
	 * Gets the operate name.
	 * 
	 * @return the operate name
	 */
	public String getOperateName() {
		return operateName;
	}

	/**
	 * Sets the operate name.
	 * 
	 * @param operateName
	 *            the new operate name
	 */
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	/**
	 * Gets the login user.
	 * 
	 * @return the login user
	 */
	public String getLoginUser() {
		return loginUser;
	}

	/**
	 * Sets the login user.
	 * 
	 * @param loginUser
	 *            the new login user
	 */
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	/**
	 * Gets the user address.
	 * 
	 * @return the user address
	 */
	public String getUserAddress() {
		return userAddress;
	}

	/**
	 * Sets the user address.
	 * 
	 * @param userAddress
	 *            the new user address
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
}