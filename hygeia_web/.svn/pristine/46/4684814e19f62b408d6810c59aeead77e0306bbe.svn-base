/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sample.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.struts2.convention.annotation.Action;

import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.UserMapping;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.sample.dao.SampleDAO;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

/**
 * The Class SampleAction.
 */
@Action(value = "Sample")
public class SampleAction extends BaseAction {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dao. */
	private static SampleDAO dao = BeanHelper.getBean(SampleDAO.class);

	/** 上传文件列表. */
	private List<File> uploads;

	/** 上传文件名. */
	private List<String> uploadsFileName;

	/** 上传文件类型. */
	private List<String> uploadsContentType;

	/**
	 * Query catalog.
	 * 
	 * @return the string
	 */
	public String queryCatalog() {
		try {
			String centerId = getParameter("centerid");
			String queryType = getParameter("searchType");
			String queryValue = getParameter("searchTerm");

			PagerHelper.initPagination(getRequest());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper
					.getPaginatedList(dao.queryCatalog(centerId, queryType,
							queryValue)));
		} catch (Exception ex) {
			if (WebHelper.isAJAXRequest(getRequest())) {
				saveJSONError(ex);
			} else {
				saveError(ex);
			}
		}

		return NONE;
	}

	/**
	 * Query codetable.
	 * 
	 * @return the string
	 */
	public String queryCodetable() {
		try {
			long t1 = System.currentTimeMillis();
			// 数据集
			PagerHelper.initPagination(getRequest());
			List lst = dao.queryCodetable(getAllParameters());

			long t2 = System.currentTimeMillis();
			// 结果集
			Map result = new HashMap();
			result.put("server_time",
					DateFunc.datetimeToString(new java.util.Date()));
			result.put("elapsed_time", t2 - t1);

			DataGridHelper.render(getRequest(), getResponse(), PagerHelper
					.getPaginatedList(lst), result);
		} catch (Exception ex) {
			saveError(ex);
		}
		return NONE;
	}

	/**
	 * Query codetable.
	 * 
	 * @return the string
	 */
	public String queryCodetableDetail() {
		try {
			PagerHelper.initPagination(getRequest());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper
					.getPaginatedList(dao
							.queryCodetableDetail(getAllParameters())));
		} catch (Exception ex) {
			saveError(ex);
		}
		return NONE;
	}

	/**
	 * Check login user exists.
	 * 
	 * @return the string
	 */
	public String checkLoginUserExists() {
		try {
			String loginUser = getParameter("login_user");
			if (!UtilFunc.hasLength(loginUser)) {
				saveJSONError("请输入登录名");
			} else {
				UserInfo user = UserMapping.findUser("9", loginUser);
				if (user != null && user.isValidUser()) {
					saveJSONError("登录名[" + loginUser + "]已经存在");
				} else {
					saveJSONMessage("登录名[" + loginUser + "]不存在");
				}
			}
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Query codetable.
	 * 
	 * @return the string
	 */
	public String queryAa10() {
		try {
			PagerHelper.initPagination(getRequest());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper
					.getPaginatedList(dao.queryAa10(getAllParameters())));
		} catch (Exception ex) {
			saveError(ex);
		}
		return NONE;
	}

	/**
	 * Download.
	 * 
	 * @return the string
	 */
	public String download() {
		try {
			List lst = dao.queryTexts(getAllParameters());
			String filename = "导出测试.xml";

			Properties prop = new Properties();
			for (int i = 0; i < lst.size(); i++) {
				Map map = (Map) lst.get(i);
				prop.put(map.get("text_code"),
						UtilFunc.getString(map, "text_value", ""));
			}
			getResponse().setContentType("application/xml");
			getResponse().setHeader("Content-disposition", String.format(
					"attachment; filename=\"%1$s\"",
					new String(filename.getBytes(), "ISO-8859-1")));
			prop.storeToXML(getResponse().getOutputStream(), "导出测试", "utf-8");
		} catch (Exception ex) {
			saveJSONError("导出系统文件出错", ex);
		}

		return NONE;
	}

	/**
	 * Show param.
	 * 
	 * @return the string
	 */
	public String showParam() {
		try {
			Map map = getAllParameters();
			System.err.println(map);
			setJSONReturn(map);
		} catch (Exception ex) {
			saveJSONError("显示参数出错", ex);
		}

		return NONE;
	}

	/**
	 * Big data.
	 * 
	 * @return the string
	 */
	public String bigData() {
		try {
			List lst = new ArrayList();

			// StringBuilder sb = new StringBuilder();
			// sb.append("[");
			Iterator it = getAllParameters().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String key = entry.getKey().toString();
				if (key.length() > 4 && key.startsWith("data")) {
					// 直接解析
					lst.addAll(JsonHelper.toList(entry.getValue().toString()));

					// 拼字符串
					/*
					 * String val = entry.getValue().toString(); if(sb.length()
					 * > 1){ sb.append(","); } sb.append(val.substring(1,
					 * val.length() - 1));
					 */
				} else {
					// 显示其他参数
					System.out.println(entry.getKey() + ":" + entry.getValue());
				}
			}

			// sb.append("]");
			// lst = JsonHelper.toList(sb.toString());

			// LogHelper.getLogger().debug(lst);
			saveJSONMessage("共接收 " + String.valueOf(lst.size()) + " 条数据");
		} catch (Exception ex) {
			saveJSONError("测试大数据出错:" + ex.getMessage(), ex);
		}

		return NONE;
	}

	public String bigSubmit() {
		try {
			Map map = new HashMap();
			Iterator it = getAllParameters().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String key = entry.getKey().toString();
				map.put(key, entry.getValue().toString().length());
			}

			setJSONReturn("大数据提交", map);
		} catch (Exception ex) {
			saveJSONError("测试大数据提交出错:" + ex.getMessage(), ex);
		}

		return NONE;
	}

	/**
	 * Save.
	 * 
	 * @return the string
	 */
	public String save() {
		try {
			int deletecnt = 0, updatecnt = 0, insertcnt = 0;
			{
				String delete = getParameter("delete");
				if (UtilFunc.hasText(delete)) {
					// dao.delete(JsonHelper.toList(delete));
					deletecnt = JsonHelper.toList(delete).size();
				}
			}

			{
				String update = getParameter("update");
				if (UtilFunc.hasText(update)) {
					// dao.update(JsonHelper.toList(update));
					updatecnt = JsonHelper.toList(update).size();
				}
			}

			{
				String insert = getParameter("insert");
				if (UtilFunc.hasText(insert)) {
					// dao.insert(JsonHelper.toList(insert));
					insertcnt = JsonHelper.toList(insert).size();
				}
			}

			saveJSONMessage(String.format(
					"保存成功。\r\n其中：删除 %1$d 条，更新 %2$d 条，新增 %3$d 条", deletecnt,
					updatecnt, insertcnt));
		} catch (Exception ex) {
			saveJSONError("保存业务数据出错", ex);
		}

		return NONE;
	}

	/**
	 * Sleep.
	 * 
	 * @return the string
	 */
	public String sleep() {
		int sec = 0;
		LogHelper.getLogger().debug("休眠开始");
		try {
			if (getUserInfo().isSuperUser()) {
				if (UtilFunc.hasText(getParameter("time"))) {
					sec = Integer.parseInt(getParameter("time"));
				} else {
					sec = 10;
				}

				if ("true".equals(getParameter("db"))) {
					Map param = new HashMap();
					param.put("text_code", "data_value");
					dao.queryTexts(param);
				}

				Thread.sleep(sec * 1000);
			}

			Map map = new HashMap();
			map.put("date", DateFunc.datetimeToString(new java.util.Date()));
			map.put("time", sec);

			setJSONReturn("success", map);
		} catch (Exception ex) {
			saveJSONError("休眠出错", ex);
		}

		LogHelper.getLogger().debug("休眠结束");

		return NONE;
	}

	/**
	 * Upload.
	 * 
	 * @return the string
	 */
	public String upload() {
		try {
			System.out.println(WebHelper.printRequestHeader(getRequest()));

			if (CollectionHelper.isEmpty(uploads)) {
				saveJSONError("上传文件不能为空");
				return NONE;
			}

			saveJSONMessage(UtilFunc.join(uploadsFileName, "\n"));
		} catch (Exception ex) {
			saveJSONError("上传文件出错", ex);
		}

		return NONE;
	}

	/**
	 * Xss filter.
	 * 
	 * @return the string
	 */
	public String xssFilter() {
		try {
			System.out.println(WebHelper.printRequestHeader(getRequest()));
			System.out.println(WebHelper.printRequestCookie(getRequest()));
			System.out.println(getAllParameters());
			String input = getRequest().getParameter("input");
			setJSONReturn("", input);
		} catch (Exception ex) {
			saveJSONError("xss过滤出错", ex);
		}

		return NONE;
	}

	/**
	 * Gets the uploads.
	 * 
	 * @return the uploads
	 */
	public final List<File> getUploads() {
		return uploads;
	}

	/**
	 * Sets the uploads.
	 * 
	 * @param uploads
	 *            the new uploads
	 */
	public final void setUploads(List<File> uploads) {
		this.uploads = uploads;
	}

	/**
	 * Gets the uploads file name.
	 * 
	 * @return the uploads file name
	 */
	public final List<String> getUploadsFileName() {
		return uploadsFileName;
	}

	/**
	 * Sets the uploads file name.
	 * 
	 * @param uploadsFileName
	 *            the new uploads file name
	 */
	public final void setUploadsFileName(List<String> uploadsFileName) {
		this.uploadsFileName = uploadsFileName;
	}

	/**
	 * Gets the uploads content type.
	 * 
	 * @return the uploads content type
	 */
	public final List<String> getUploadsContentType() {
		return uploadsContentType;
	}

	/**
	 * Sets the uploads content type.
	 * 
	 * @param uploadsContentType
	 *            the new uploads content type
	 */
	public final void setUploadsContentType(List<String> uploadsContentType) {
		this.uploadsContentType = uploadsContentType;
	}
}
