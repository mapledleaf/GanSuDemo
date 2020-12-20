/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.MenuRightMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.util.JsonHelper;

/**
 * 꽉데직넋묏야잚.
 */
public class MenuFlowUtil {
	/** The table name. */
	private static String TABLE_NAME = "SYS_FLOWS";

	/**
	 * Query.
	 * 
	 * @return the list
	 */
	public static List query() {
		return DBHelper
				.executeList("select flow_id, flow_key, flow_type, flow_name, flow_desc, last_date, flow_group, dis_order from sys_flows where valid_flag = '1' order by dis_order, flow_key");
	}

	/**
	 * Gets the sub system.
	 *
	 * @return the sub system
	 */
	public static List getSubSystem(){
		String sql = "select menu_id as id, menu_name as text\n" + 
				"  from sys_menu sm\n" + 
				" where sm.menu_up_id = 0\n" + 
				"   and sm.menu_type = 1\n" + 
				"   and sm.valid_flag = '1'\n" + 
				" order by sm.menu_order";

		return DBHelper
				.executeList(sql);
	}
	/**
	 * Data.
	 * 
	 * @param key
	 *            the key
	 * @return the object
	 */
	public static String getDataByKey(String key) {
		return (String)DBHelper
				.executeScale(
						"select flow_data from sys_flows where flow_key = ? and valid_flag = '1'",
						new String[] { key });
	}

	/**
	 * Data.
	 * 
	 * @param id
	 *            the id
	 * @return the object
	 */
	public static String getDataById(String id) {
		return (String)DBHelper.executeScale(
				"select flow_data from sys_flows where flow_id = ?",
				new String[] { id });
	}

	/**
	 * Gets the data by cache.
	 * 
	 * @param key
	 *            the key
	 * @return the data by cache
	 */
	public static String getDataByCache(String key) {
		return null;
	}

	/**
	 * Gets the history rects.
	 * 
	 * @param data
	 *            the data
	 * @return the history rects
	 */
	public static String getHistoryRectsByUrl(String data) {
		List<String> rects = new ArrayList();
		try {
			Set roles = BusiContext.getUserInfo().getRoleSet();
			JSONObject flow = JSONObject.fromObject(data);
			if (flow != null) {
				JSONObject states = flow.getJSONObject("states");
				if (states != null) {
					Iterator it = states.keys();
					while (it.hasNext()) {
						String key = (String) it.next();
						if (!key.startsWith("rect")) {
							continue;
						}

						JSONObject props = states.getJSONObject(key).getJSONObject("props");
						if (props != null) {
							if (props.getJSONObject("url") != null && 
								props.getJSONObject("url").containsKey("value") && 
								props.getJSONObject("url").getString("value") != null) {
								String url = props.getJSONObject("url")
										.getString("value").trim();
								if (!UtilFunc.isEmpty(url)) {
									List<String> menus = MenuRightMapping
											.findMenu(url.replace(".action", ""));
									boolean hasRight = false;
									if(menus != null && menus.size() > 0){
										for (String menuId : menus) {
											if (MenuRightMapping.hasMenuRole(
													menuId, roles)) {
												hasRight = true;
												break;
											}
										}
									}
									if (!hasRight) {
										rects.add(key);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			LogHelper.getLogger().debug("썩驕직넋鑒앴놔댄", ex);
		}

		return JsonHelper.toJson(rects);
	}

	/**
	 * Check repeat.
	 * 
	 * @param key
	 *            the key
	 * @return true, if successful
	 */
	public static boolean checkRepeat(String key) {
		return DBHelper
				.executeInt(
						"select count(1) from sys_flows where flow_key = ? and valid_flag = '1'",
						new String[] { key }) > 0;
	}

	/**
	 * Check repeat.
	 * 
	 * @param key
	 *            the key
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	public static boolean checkRepeat(String key, String id) {
		return DBHelper
				.executeInt(
						"select count(1) from sys_flows where flow_key = ? and flow_id <> ? and valid_flag = '1'",
						new String[] { key, id }) > 0;
	}

	/**
	 * Insert.
	 * 
	 * @param map
	 *            the map
	 * @return the int
	 */
	public static int insert(Map map) {
		return DAOHelper.insert(TABLE_NAME, map);
	}

	/**
	 * Delete.
	 * 
	 * @param id
	 *            the id
	 * @return the int
	 */
	public static int delete(String id) {
		return DBHelper
				.update("update sys_flows set valid_flag = '0', last_date = sysdate, flow_key = (flow_key || '_' || flow_id) where flow_id = ? and valid_flag = '1'",
						new String[] { id });
	}

	/**
	 * Update.
	 * 
	 * @param map
	 *            the map
	 * @return the int
	 */
	public static int update(Map map) {
		Map defaults = new HashMap();
		defaults.put("last_date", "sysdate");
		return DAOHelper.update(TABLE_NAME, map, defaults, null, null);
	}

	/**
	 * Read file.
	 * 
	 * @param file
	 *            the file
	 * @return the byte[]
	 */
	public static byte[] readFile(File file) {
		BufferedInputStream bis = null;
		try {
			long len = file.length();
			byte[] bytes = new byte[(int) len];

			bis = new BufferedInputStream(new FileInputStream(file));
			bis.read(bytes);

			return bytes;

		} catch (Exception ex) {
			throw new HygeiaException("读取文件" + file.getName() + "出错", ex);
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
