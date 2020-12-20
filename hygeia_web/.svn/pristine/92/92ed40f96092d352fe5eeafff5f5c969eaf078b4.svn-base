/**
 * Copyright 2014 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.manager.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import com.powersi.sys.manager.dao.CodetableDAO;
import com.powersi.sys.manager.dao.CodetableDetailDAO;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.DownloadHelper;
import com.powersi.sys.util.PagerHelper;

/**
 * The Class CodetableManagerAction.
 */
@Action("CodetableManager")
public class CodetableManagerAction extends UploadAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dao. */
	private static CodetableDAO dao = BeanHelper.getBean(CodetableDAO.class);

	/** The detail dao. */
	private static CodetableDetailDAO daoDetail = BeanHelper
			.getBean(CodetableDetailDAO.class);

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
	 * Query detail.
	 *
	 * @return the string
	 */
	public String queryDetail() {
		try {
			PagerHelper.initPagination(getRequest());
			List lst = daoDetail.query(getAllParameters());
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
			String oper = getParameter("oper");
			if("add".equals(oper)){
				dao.insert(getAllParameters());
			} else if("update".equals(oper)){
				dao.update(getAllParameters());
				String oldId = getParameter("id");
				String newId = null;
				newId = getParameter("code_type");
				if(oldId != null && !oldId.equals(newId)){
					daoDetail.update(newId, oldId);
				}
			} else if("del".equals(oper)){
				dao.delete(getAllParameters());
				daoDetail.delete(getParameter("id"));
			}
			saveJSONMessage("保存成功");
		} catch (Exception ex) {
			saveJSONError("保存系统码表出错", ex);
		}

		return NONE;
	}
	
	/**
	 * Save detail.
	 *
	 * @return the string
	 */
	public String saveDetail() {
		try {

			{
				String delete = getParameter("delete");
				if (UtilFunc.hasText(delete)) {
					daoDetail.delete(JsonHelper.toList(delete));
				}
			}

			{
				String update = getParameter("update");
				if (UtilFunc.hasText(update)) {
					daoDetail.update(JsonHelper.toList(update));
				}
			}

			{
				String insert = getParameter("insert");
				if (UtilFunc.hasText(insert)) {
					daoDetail.insert(JsonHelper.toList(insert));
				}
			}

			saveJSONMessage("保存成功");
		} catch (Exception ex) {
			saveJSONError("保存码表明细出错", ex);
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
			Map<String, List> map = new LinkedHashMap<String, List>();
			
			map.put("list", dao.query(getAllParameters()));
			map.put("detail", daoDetail.queryList(getAllParameters()));
			
			String json = JsonHelper.toJson(map);
			
			String fileName = "sys_code_table_"
					+ DateFunc.dateToString(new java.util.Date(),
							"yyyyMMddHHmmss") + ".json";

			DownloadFile df = new DownloadFile(fileName, json);
			DownloadHelper.download(getResponse(), df);
		} catch (Exception ex) {
			saveJSONError("导出系统文本出错", ex);
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
					"sys_code_table")) {
				saveJSONError("导入文件名必须使用sys_code_table开始");
				return NONE;
			}
			
			if (!UtilFunc.endsWithIgnoreCase(uploadsFileName.get(0),
					".json")) {
				saveJSONError("导入文件只支持json格式的文件");
				return NONE;
			}

			String json = new String(UtilFunc.readFile(uploads.get(0)), GlobalContext.getCharset());
			
			Map data = JsonHelper.toMap(json);
			List<Map<String, Object>> lst = (List) data.get("list");
			List<Map<String, Object>> details = (List) data.get("detail");
			
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
			
			int insertcnt = 0, updatecnt = 0, detailcnt = 0;
			if (inserts.size() > 0) {
				insertcnt = inserts.size();
				
				dao.insert(inserts);
			}
			
			if("true".equals(getParameter("forceUpdate")) && updates.size() > 0){
				updatecnt = updates.size();
				
				dao.update(updates);
			}
			
			if(lst.size() > 0){
				detailcnt = details.size();
				
				daoDetail.deleteList(lst);
				daoDetail.insert(details);
			}
			
			if(insertcnt > 0 || updatecnt > 0 || detailcnt > 0){
				RefreshMappingUtil.refresh("codetable");
			}

			saveJSONMessage(String.format(
					"导入系统码表成功\n(共%1$d项 其中新增%2$d项 更新%3$d项)\n共导入%4$d项码表明细",
					lst.size(), insertcnt, updatecnt, detailcnt));
		} catch (Exception ex) {
			saveJSONError("导入系统码表出错", ex);
		}
		
		return NONE;
	}
}