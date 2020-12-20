/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.action;

import org.apache.struts2.convention.annotation.Action;

import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.NetworkHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;


/**
 * The Class DiagnoseManagerAction.
 */
@Action("DiagnoseManager")
public class DiagnoseManagerAction extends BaseAction {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Check url.
	 *
	 * @return the string
	 */
	public String checkUrl() {
		try {
			boolean ok = true;
			String url = getParameter("url");
			String timeout = getParameter("timeout");
			if(UtilFunc.isEmpty(timeout)){
				timeout = "5000";
			}
			
			if(UtilFunc.hasLength(url)){
				ok = NetworkHelper.isUrlReachable(url, Integer.parseInt(timeout));
			}
			
			if(ok){
				saveJSONMessage(url + "连接成功");
			} else{
				saveJSONError(url + "连接失败");
			}
		} catch (Exception ex) {
			saveJSONError("检查网络连接出错", ex);
		}

		return NONE;
	}
	
	
	/**
	 * Check server.
	 *
	 * @return the string
	 */
	public String checkServer() {
		try {
			boolean ok = true;
			String url = getParameter("url");
			String timeout = getParameter("timeout");
			if(UtilFunc.isEmpty(timeout)){
				timeout = "5000";
			}
			
			if(UtilFunc.hasLength(url)){
				ok = NetworkHelper.isServerReachable(url, Integer.parseInt(timeout));
			}
			
			if(ok){
				saveJSONMessage(url + "连接成功");
			} else{
				saveJSONError(url + "连接失败");
			}
		} catch (Exception ex) {
			saveJSONError("检查服务器连接出错", ex);
		}

		return NONE;
	}
	/**
	 * Check db.
	 *
	 * @return the string
	 */
	public String checkDb() {
		try{
			String dataSource = getParameter("db");
			if(UtilFunc.hasLength(dataSource)){
				DBHelper.setConnection(dataSource);
			}
			
			DBHelper.getConnection();
			
			saveJSONMessage(dataSource == null ? "" : dataSource + "连接成功");
		} catch(Exception ex){
			saveJSONError("检查数据库连接出错", ex);
		}
		
		return NONE;
	}
}
