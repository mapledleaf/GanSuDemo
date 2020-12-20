/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.TaskMapping;
import com.powersi.hygeia.framework.TaskScheduler;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.scheduling.CronExpression;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.ExceptionHelper;
import com.powersi.hygeia.framework.util.RefreshMappingUtil;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.action.UploadAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.sys.manager.dao.TaskDAO;

/**
 * The Class TaskManagerAction.
 */
@Action(value = "TaskManager", results = {
		@Result(name = "success", location = "/pages/sys/manager/TaskManager.jsp"),
		@Result(name = "log", location = "/pages/sys/manager/TaskLog.jsp")
})
public class TaskManagerAction extends UploadAction {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant CACHE_NAME. */
	private static final String CACHE_NAME = "task";

	/** The begin date. */
	private String beginDate = null;

	/** The end date. */
	private String endDate = null;

	/** The dao. */
	private static TaskDAO dao = getBean(TaskDAO.class);

	/**
	 * Query.
	 * 
	 * @return the string
	 */
	public String queryCronExp() {
		try {
			String exp = getParameter("cron");

			int count = 100;
			if (isNotEmpty(getParameter("count"))) {
				count = UtilFunc.stringToInt(getParameter("count"));
			}
			if (count < 1) {
				count = 1;
			}
			if (count > 10000) {
				count = 10000;
			}

			java.util.Date date = null;
			if (isNotEmpty(getParameter("date"))) {
				date = DateFunc.toDatetime(getParameter("date"));
			} else {
				date = new java.util.Date();
			}

			Calendar cal = new GregorianCalendar();

			StringBuilder sb = new StringBuilder(count * 30);
			CronExpression cron = new CronExpression(exp);
			int size = String.valueOf(count).length();
			String space = UtilFunc.rightPad("", 10, ' ');
			for (int i = 0; i < count; i++) {
				date = cron.getNextValidTimeAfter(date);
				sb.append(UtilFunc.leftPad(String.valueOf(i), size, '0'));
				sb.append(space);
				sb.append(DateFunc.datetimeToString(date));

				cal.setTime(date);

				sb.append(space);
				sb.append(UtilFunc.leftPad(
						String.valueOf(cal.get(Calendar.WEEK_OF_YEAR)), 2, '0'));

				sb.append(space);
				sb.append(cal.get(Calendar.WEEK_OF_MONTH));

				sb.append(space);
				sb.append(cal.get(Calendar.DAY_OF_WEEK));

				sb.append('\n');

			}
			return renderJson(sb.toString());
		} catch (Exception ex) {
			try {
				return renderJson(ExceptionHelper.getThrowableStr(ex));
			} catch (IOException ioex) {
				return NONE;
			}
		}
	}

	/**
	 * Query log.
	 * 
	 * @return the string
	 */
	public String query() {
		try {
			if (isPostRequest()) {
				initPager();
				return renderGrid(dao.query(getAllParameters()));
			} else {
				return SUCCESS;
			}
		} catch (Exception ex) {
			return renderError("查询任务日志出错", ex);
		}
	}

	/**
	 * Query status.
	 * 
	 * @return the string
	 */
	public String queryStatus() {
		try {
			return renderJson(TaskScheduler.getStatus());
		} catch (Exception ex) {
			return renderError("查询任务状态出错", ex);
		}
	}

	/**
	 * Query log.
	 * 
	 * @return the string
	 */
	public String queryLog() {
		try {
			if (isPostRequest()) {
				initPager();
				return renderGrid(dao.queryLog(beginDate, endDate, getAllParameters()));
			} else {
				java.util.Date now = new java.util.Date();
				beginDate = DateFunc.dateToString(DateFunc.addMonths(now, -1))
						+ " 00:00:00";
				endDate = DateFunc.dateToString(now)
						+ " 23:59:59";
				return "log";
			}
		} catch (Exception ex) {
			return renderError("查询任务日志出错", ex);
		}
	}

	/**
	 * Update.
	 * 
	 * @return the string
	 */
	public String save() {
		try {
			Map<String, Object> map = getAllParameters();
			List lst = checkParam(map);
			if (lst.size() > 0) {
				return renderError(UtilFunc.join(lst, "\n"));
			} else {
				if (UtilFunc.isEmpty(getParameter("id"))) {
					dao.insert(map);
				} else {
					dao.update(map);
				}
				RefreshMappingUtil.refresh(CACHE_NAME);
				return renderJson("保存成功", map);
			}
		} catch (Exception ex) {
			return renderError("保存系统任务出错", ex);
		}
	}

	/**
	 * Check param.
	 * 
	 * @param params
	 *            the params
	 * @return the list
	 */
	private List checkParam(Map params) {
		List lst = new ArrayList();
		if (isEmpty(params.get("task_name"))) {
			lst.add("任务名称不能为空");
		}

		if (isEmpty(params.get("task_desc"))) {
			lst.add("任务描述不能为空");
		}

		if (isEmpty(params.get("task_class"))) {
			lst.add("任务类名不能为空");
		}

		String taskClass = UtilFunc.getString(params, "task_class");
		if (isEmpty(taskClass)) {
			lst.add("任务类名不能为空");
		} else {
			if ("com.powersi.hygeia.framework.scheduling.BusiTask"
					.equals(taskClass)) {
				String taskParam = UtilFunc.getString(params, "task_param");
				if (isEmpty(taskParam)) {
					lst.add("任务参数不能为空");
				} else {
					if (!taskParam.startsWith("<Program>")
							|| !taskParam.endsWith("</Program>")) {
						lst.add("任务参数格式错误(必须使用<Program>开头，</Program>结尾的xml串");
					}
				}
			}
		}

		String triggerType = UtilFunc.getString(params, "trigger_type");
		if (isEmpty(taskClass)) {
			lst.add("触发器类型不能为空");
		} else {
			String triggerValue = UtilFunc.getString(params, "trigger_value");
			if ("0".equals(triggerType)) {
				if (!CronExpression.isValidExpression(triggerValue)) {
					lst.add("无效的cron表达式");
				}
			} else if ("1".equals(triggerType) || "2".equals(triggerType)) {
				if (UtilFunc.stringToInt(triggerValue, 0) < 1) {
					lst.add("无效的时间间隔");
				}
			} else if ("3".equals(triggerType)) {

			} else {
				lst.add("无效的触发器类型");
			}
		}

		return lst;
	}

	/**
	 * Delete.
	 * 
	 * @return the string
	 */
	public String delete() {
		try {
			dao.delete(getAllParameters());
			RefreshMappingUtil.refresh(CACHE_NAME);
			saveJSONMessage("删除成功");
		} catch (Exception ex) {
			saveJSONError("删除系统任务出错", ex);
		}

		return NONE;
	}

	/**
	 * Run.
	 * 
	 * @return the string
	 */
	public String run() {
		try {
			String taskName = getParameter("task_name");
			if (isEmpty(taskName)) {
				throw new HygeiaException("任务名称不能为空");
			}

			TaskMapping.runTask(taskName);

			saveJSONMessage(taskName + "任务开始执行");
		} catch (Exception ex) {
			saveJSONError("执行系统任务出错", ex);
		}

		return NONE;
	}

	/**
	 * Status.
	 * 
	 * @return the string
	 */
	public String queryTask() {
		try {
			String taskName = getParameter("task_name");
			if (isEmpty(taskName)) {
				throw new HygeiaException("任务名称不能为空");
			}

			List lst = dao.query(getAllParameters());
			if (CollectionHelper.isNotEmpty(lst)) {
				return renderJson(lst.get(0));
			} else {
				return renderMessage("没有符合条件的任务");
			}
		} catch (Exception ex) {
			return renderError("获取系统任务出错", ex);
		}
	}

	/**
	 * Download.
	 * 
	 * @return the string
	 */
	public String download() {
		try {
			List<Map> lst = dao.query(getAllParameters());

			String[] cols = new String[] { "id", "task_name", "task_desc", "task_class", "task_param",
					"trigger_type", "trigger_value", "trigger_instance", 
					"lock_flag", "log_flag", "valid_flag", 
					"effect_date", "expire_date" };
			List rows = new ArrayList(lst.size());
			for (Map data : lst) {
				Map row = new HashMap();
				for (String col : cols) {
					row.put(col, data.get(col));
				}
				rows.add(row);
			}

			String fileName = "sys_tasks_"
					+ DateFunc.dateToString(new java.util.Date(),
							"yyyyMMddHHmmss") + ".json";

			return renderFile(createDownloadFile(fileName, toJson(rows)));
		} catch (Exception ex) {
			return renderError("导出系统任务出错", ex);
		}
	}

	/**
	 * Upload.
	 * 
	 * @return the string
	 */
	public String upload() {
		try {
			if (CollectionHelper.isEmpty(uploads)) {
				return renderError("导入文件不能为空");
			}

			if (!UtilFunc.startsWithIgnoreCase(uploadsFileName.get(0),
					"sys_tasks")) {
				return renderError("导入文件名必须使用sys_tasks开始");
			}

			if (!UtilFunc.endsWithIgnoreCase(uploadsFileName.get(0),
					".json")) {
				return renderError("导入文件只支持json格式的文件");
			}

			String json = new String(UtilFunc.readFile(uploads.get(0)),
					GlobalContext.getCharset());
			List<Map<String, Object>> lst = JsonHelper.toList(json);

			Set<String> keys = dao.keys();
			List<Map<String, Object>> inserts = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> updates = new ArrayList<Map<String, Object>>();

			for (Map<String, Object> map : lst) {
				String key = map.get("id").toString();
				if (!keys.contains(key)) {
					inserts.add(map);
				} else {
					updates.add(map);
				}
			}

			int insertcnt = 0, updatecnt = 0;
			if (inserts.size() > 0) {
				insertcnt = inserts.size();

				dao.insert(inserts);
			}

			if ("true".equals(getParameter("forceUpdate"))
					&& updates.size() > 0) {
				updatecnt = updates.size();

				dao.update(updates);
			}

			if (insertcnt > 0 || updatecnt > 0) {
				RefreshMappingUtil.refresh(CACHE_NAME);
			}

			return renderMessage(String.format(
					"导入系统任务成功\n(共%1$d项 其中新增%2$d项 更新%3$d项)",
					lst.size(), insertcnt, updatecnt));
		} catch (Exception ex) {
			return renderError("导入系统任务出错", ex);
		}
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
}