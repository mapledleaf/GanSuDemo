/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;

import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.action.UploadAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.sys.manager.dao.AppDAO;
import com.powersi.sys.manager.dao.RoleDAO;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

/**
 * The Class AppManagerAction.
 */
@Action("AppManager")
public class AppManagerAction extends UploadAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant CACHE_NAME. */
	// private static final String CACHE_NAME = "app";

	private static AppDAO appDao = BeanHelper.getBean(AppDAO.class);

	/** The role dao. */
	public static RoleDAO roleDao = BeanHelper.getBean(RoleDAO.class);

	/**
	 * Query.
	 * 
	 * @return the string
	 */
	public String query() {
		try {
			PagerHelper.initPagination(getRequest());
			List lst = appDao.query(getAllParameters());
			DataGridHelper.render(getRequest(), getResponse(),
					PagerHelper.getPaginatedList(lst));
		} catch (Exception ex) {
			saveJSONError(ex);
		}
		return NONE;
	}

	/**
	 * Update.
	 * 
	 * @return the string
	 */
	public String save() {
		try {
			Map<String, Object> map = getAllParameters();
			map.put("last_date", new java.util.Date());
			String appId = UtilFunc.getString(map, "app_id");
			if (UtilFunc.isEmpty(appId)) {
				map.put("app_id", String.valueOf(SysFunc.getMaxNo("app_id")));
				appDao.insert(map);
			} else {
				appDao.update(map);
			}
			// RefreshMappingUtil.refresh(CACHE_NAME);
			setJSONReturn("保存成功", map);
		} catch (Exception ex) {
			saveJSONError("保存系统应用出错", ex);
		}

		return NONE;
	}

	/**
	 * Delete.
	 * 
	 * @return the string
	 */
	public String delete() {
		try {
			appDao.delete(getAllParameters());
			// RefreshMappingUtil.refresh(CACHE_NAME);
			saveJSONMessage("删除成功");
		} catch (Exception ex) {
			saveJSONError("删除系统应用出错", ex);
		}

		return NONE;
	}

	/**
	 * Gets the all role.
	 * 
	 * @return the all role
	 */
	public String getAllRole() {
		try {
			setJSONReturn(roleDao.getAllRoleList());
		} catch (Exception ex) {
			saveJSONError("获取全部角色出错", ex);
		}

		return NONE;
	}

	/**
	 * Gets the role list.
	 * 
	 * @return the role list
	 */
	public String getRoleList() {
		try {
			String appId = getParameter("app_id");
			setJSONReturn(appDao.getRoleList(appId));
		} catch (Exception ex) {
			saveJSONError("获取角色列表出错", ex);
		}

		return NONE;
	}

	/**
	 * Save role list.
	 * 
	 * @return the string
	 */
	public String saveRoleList() {
		try {
			String appId = getParameter("app_id");
			appDao.delRoleList(appId, getParameter("delete"));

			appDao.delRoleList(appId, getParameter("insert"));
			appDao.addRoleList(appId, getParameter("insert"));
			saveJSONMessage("保存应用角色成功");
		} catch (Exception ex) {
			saveJSONError("保存角色列表出错", ex);
		}

		return NONE;
	}

	/**
	 * Gets the app list json.
	 * 
	 * @return the app list json
	 */
	public static String getApps() {
		try {
			return JsonHelper.toJson(appDao.getAppList());
		} catch (Exception ex) {
			LogHelper.getLogger().error("获取应用列表出错", ex);
			return null;
		}
	}

	/** The bgs. */
	private static String bgs = null;
	
	/**
	 * Gets the wallpapers.
	 *
	 * @return the wallpapers
	 */
	public static String getWallpapers() {
		if (bgs == null) {
			List<String> lst = new ArrayList<String>();
			File root = new File(
					GlobalContext
							.getPhysicalPath("/resource/desktop/images/wallpaper"));
			if (root.exists() && root.isDirectory()) {
				File[] files = root.listFiles();
				for (File file : files) {
					if (file.isFile()) {
						String fileName = file.getName();
						String prefix = "";
						int idx = fileName.lastIndexOf(".");
						if(idx >= 0){
							prefix = fileName.substring(idx + 1).toLowerCase();
						}
						
						if ("jpg".equals(prefix) || "png".equals(prefix)
								|| "gif".equals(prefix) || "bmp".equals(prefix)) {
							lst.add(fileName);
						}
					}
				}
			}

			bgs = JsonHelper.toJson(lst);
		}

		return bgs;
	}
}