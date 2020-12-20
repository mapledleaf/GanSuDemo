/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.action;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;

import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.NetworkHelper;
import com.powersi.hygeia.framework.util.RefreshMappingUtil;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.sys.manager.dao.ClusterDAO;

/**
 * The Class ClusterManagerAction.
 */
@Action(value = "ClusterManager")
public class ClusterManagerAction extends BaseAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dao. */
	private static ClusterDAO dao = BeanHelper.getBean(ClusterDAO.class);

	/**
	 * Query.
	 * 
	 * @return the string
	 */
	public String query() {
		try {
			boolean isDev = getUserInfo().isSuperUser()
					|| "1".equals(getUserInfo().get("is_developer"));
			if (isDev) {
				List lst = dao.query(getAllParameters());
				if ("true".equals(getParameter("ping"))) {
					for (int i = 0; i < lst.size(); i++) {
						Map map = (Map) lst.get(i);
						long start = System.currentTimeMillis();
						String url = UtilFunc.getString(map, "cluster_url") + "/" + GlobalContext.getContextPath();
						if (NetworkHelper.isUrlReachable(
								url, 3000)) {
							map.put("response_time", System.currentTimeMillis()
									- start);
						} else {
							map.put("response_time", -1);
						}
					}
				}
				setJSONReturn(lst);
			} else {
				setJSONReturn(Collections.EMPTY_LIST);
			}
		} catch (Exception ex) {
			saveJSONError("查询集群配置信息出错", ex);
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
			boolean isDev = getUserInfo().isSuperUser()
					|| "1".equals(getUserInfo().get("is_developer"));
			List lst = JsonHelper.toList(getParameter("data"));
			if (isDev) {
				dao.save(lst);
				saveJSONMessage("保存成功");

				RefreshMappingUtil.refresh("cluster");
			} else {
				saveJSONError("保存出错");
			}
		} catch (Exception ex) {
			saveJSONError("保存集群配置信息出错", ex);
		}

		return NONE;
	}
}