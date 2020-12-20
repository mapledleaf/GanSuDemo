/**
 * Copyright 2014 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.manager.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;

import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.RefreshMappingUtil;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.action.UploadAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.sys.entity.DownloadFile;
import com.powersi.sys.manager.dao.SqlDAO;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.DownloadHelper;
import com.powersi.sys.util.PagerHelper;

/**
 * The Class SqlManagerAction.
 */
@Action("SqlManager")
public class SqlManagerAction extends UploadAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant CACHE_NAME. */
	private static final String CACHE_NAME = "sql";
	
	/** The dao. */
	private static SqlDAO dao = BeanHelper.getBean(SqlDAO.class);

	/**
	 * Query.
	 * 
	 * @return the string
	 */
	public String query() {
		try {
			PagerHelper.initPagination(getRequest());
			List lst = dao.query(getAllParameters());
			DataGridHelper.render(getRequest(), getResponse(),
					PagerHelper.getPaginatedList(lst));
		} catch (Exception ex) {
			saveJSONError(ex);
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

			{
				String delete = getParameter("delete");
				if (UtilFunc.hasText(delete)) {
					dao.delete(JsonHelper.toList(delete));
				}
			}

			{
				String update = getParameter("update");
				if (UtilFunc.hasText(update)) {
					dao.update(JsonHelper.toList(update));
				}
			}

			{
				String insert = getParameter("insert");
				if (UtilFunc.hasText(insert)) {
					dao.insert(JsonHelper.toList(insert));
				}
			}

			RefreshMappingUtil.refresh(CACHE_NAME);

			saveJSONMessage("保存成功");
		} catch (Exception ex) {
			saveJSONError("保存系统sql出错", ex);
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
			List lst = dao.query(getAllParameters());
			String json = JsonHelper.toJson(lst);
			
			String fileName = "sys_sqls_"
					+ DateFunc.dateToString(new java.util.Date(),
							"yyyyMMddHHmmss") + ".json";

			DownloadFile df = new DownloadFile(fileName, json);
			DownloadHelper.download(getResponse(), df);
		} catch (Exception ex) {
			saveJSONError("导出系统sql出错", ex);
		}
		
		return NONE;
	}

	/**
	 * Upload.
	 * 
	 * @return the string
	 */
	public String upload() {
		try {
			if (CollectionHelper.isEmpty(uploads)) {
				saveJSONError("导入文件不能为空");
				return NONE;
			}

			if (!UtilFunc.startsWithIgnoreCase(uploadsFileName.get(0),
					"sys_sqls")) {
				saveJSONError("导入文件名必须使用sys_sqls开始");
				return NONE;
			}
			
			if (!UtilFunc.endsWithIgnoreCase(uploadsFileName.get(0),
					".json")) {
				saveJSONError("导入文件只支持json格式的文件");
				return NONE;
			}

			String json = new String(UtilFunc.readFile(uploads.get(0)), GlobalContext.getCharset());
			List<Map<String, Object>> lst = JsonHelper.toList(json);
			
			Set<String> keys = dao.keys();
			List<Map<String, Object>> inserts = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> updates = new ArrayList<Map<String, Object>>();
			
			java.util.Date now = new java.util.Date();
			for (Map<String, Object> map : lst) {
				String key = map.get("id").toString();
				if (!keys.contains(key)) {
					map.put("last_date", now);
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
			
			if("true".equals(getParameter("forceUpdate")) && updates.size() > 0){
				updatecnt = updates.size();
				
				dao.update(updates);
			}
			
			if(insertcnt > 0 || updatecnt > 0){
				RefreshMappingUtil.refresh(CACHE_NAME);
			}

			saveJSONMessage(String.format(
					"导入系统sql成功\n(共%1$d项 其中新增%2$d项 更新%3$d项)",
					lst.size(), insertcnt, updatecnt));
		} catch (Exception ex) {
			saveJSONError("导入系统sql出错", ex);
		}
		
		return NONE;
	}
}