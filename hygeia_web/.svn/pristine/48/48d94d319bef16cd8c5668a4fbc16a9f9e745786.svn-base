/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.action;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.IParameterObj;
import com.powersi.hygeia.framework.IResultObj;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.ParameterObj;
import com.powersi.hygeia.framework.ResultObj;
import com.powersi.hygeia.framework.util.DBFunc;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.RefreshMappingUtil;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;

/**
 * The Class MappingManagerAction.
 */
@Action(value = "MappingManager", results = {
		@Result(name = "success", location = "/pages/sys/manager/MappingManager.jsp")
})
public class MappingManagerAction extends BaseAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		try {
			boolean isDev = getUserInfo().isSuperUser() || "1".equals(getUserInfo().get("is_developer"));
			//缓存信息
			RefreshMappingUtil refreshUtil = new RefreshMappingUtil();
			IParameterObj parameterObj = new ParameterObj();
			IResultObj resultObj = new ResultObj();
			try{
				String cacheList = ParameterMapping.getStringByCode(
						"refresh_cache_list", "action");
				
				if ("true".equals(getParameter("refresh"))) {
					GlobalContext.init();
					parameterObj.setParameter("refresh", cacheList);
				} else{
					parameterObj.setParameter("view", cacheList);
					parameterObj.setParameter("refresh", "");
				}
				
				refreshUtil.execute(parameterObj, resultObj);

				setAttribute("info", resultObj.getResultSet("info"));
				
				if (resultObj.getErrType() == 0) {
					List<Map> lst = (List<Map>) resultObj.getResultSet("info");
					for(Map row : lst){
						String msg = UtilFunc.getString(row, "message");
						if(msg == null || msg.length() == 0){
							row.put("remark", "");
						} else{
							int pos = msg.indexOf("：");
							if(pos > 0){
								row.put("remark", msg.substring(0, pos));
							} else{
								row.put("remark", msg);
							}
						}
					}
					setAttribute("info", resultObj.getResultSet("info"));
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("刷新缓存出错");
					sb.append("<BR>");
					sb.append("错误信息:");
					sb.append("<BR>");
					sb.append(resultObj.getRetMsg());
					sb.append("<BR>");
					sb.append("异常信息:");
					sb.append("<BR>");
					sb.append(resultObj.getException());

					setAttribute("mappingInfo", sb.toString());
				}
			} finally{
				parameterObj = null;
				resultObj = null;
				refreshUtil = null;
			}

			//全局信息
			{
				StringBuilder sb = new StringBuilder();
				sb.append(GlobalContext.printString());
				
				setAttribute("contextInfo",
						UtilFunc.replace(sb.toString(),
								UtilFunc.NEW_LINE, "<BR>"));
			}

			//数据库信息
			if (isDev) {
				StringBuilder sb = new StringBuilder();
				sb.append(getDatabaseInfo());
				
				if(GlobalContext.isTraceBusiConn()){
					java.util.Set<String> set = DBFunc.getActiveConnections();
					sb.append(UtilFunc.NEW_LINE);
					sb.append(UtilFunc.NEW_LINE);
					sb.append("<b>Active Connections(");
					sb.append(set.size());
					sb.append(")</b>");
					
					sb.append("<ol>");
					int i = 0;
					for(String name : set){
						sb.append("<li>");
						int idx = name.indexOf("):");
						if(idx >= 0){
							sb.append("<a href=\"javascript:toggleDetail('d" + i + "')\">");
							sb.append(name.substring(0, idx + 1));
							sb.append("</a>");
							
							sb.append("<div id=\"d" + i + "\" style=\"display:none\">");
							sb.append(UtilFunc.replace(name.substring(idx + 2).trim(), "\n", "<br/>"));
							sb.append("</div>");
						} else{
							sb.append("<div>");
							sb.append(UtilFunc.replace(name, "\n", "<br/>"));
							sb.append("</div>");
						}
						
						sb.append("</li>");
						
						i ++;
					}
					sb.append("</ol>");
				}
				
				setAttribute("databaseInfo", UtilFunc.replace(sb.toString(), UtilFunc.NEW_LINE, "<BR>"));
			} else{
				setAttribute("databaseInfo", "");
			}
			
			//服务器信息
			if(isDev) {
				StringBuilder sb = new StringBuilder();
				sb.append(getSystemProperties());
				
			    setAttribute("serverInfo",
						UtilFunc.replace(sb.toString(),
								UtilFunc.NEW_LINE, "<BR>"));
			} else{
				setAttribute("serverInfo", "");
			}
			
			//日志信息
			if(isDev) {
				StringBuilder sb = new StringBuilder();
				
				 // 获取所有的Appender配置
			    Logger rootLogger = Logger.getRootLogger();
			    Enumeration allAppenders = rootLogger.getAllAppenders();
			    while (allAppenders.hasMoreElements()) {
			    	sb.append(UtilFunc.NEW_LINE);
			        Appender appender = (Appender) allAppenders.nextElement();
			        
			        sb.append(appender.getClass().getCanonicalName() + " = "
			            + rootLogger.getLevel().toString() + ", " + appender.getName());
			    }
			    sb.append(UtilFunc.NEW_LINE);
			  	
			    // 获取所有的package log configuration
			    Enumeration currentLoggers = LogManager.getCurrentLoggers();
			    while (currentLoggers.hasMoreElements()) {
			        Logger logger = (Logger) currentLoggers.nextElement();
			        Level level = logger.getLevel();
			        if (level != null) {
			        	sb.append(UtilFunc.NEW_LINE);
			            sb.append(logger.getName() + " = " + level.toString());
			            for (Enumeration<Appender> a = logger.getAllAppenders(); a.hasMoreElements();) {
							Appender w = a.nextElement();
							sb.append(", ");
							sb.append(w.getName());
						}
			        }
			    }
			    
			    setAttribute("logInfo",
						UtilFunc.replace(sb.toString(),
								UtilFunc.NEW_LINE, "<BR>"));
			} else{
				setAttribute("logInfo", "");
			}

			if(isDev) {
				List lst = new ArrayList();
				File root = new File(GlobalContext.getPhysicalPath("/WEB-INF/lib/"));
				String[] vers = new String[] {"Implementation-Version", "Bundle-Version", "Specification-Version"};
				if (root.exists() && root.isDirectory()) {
					File[] files = root.listFiles();
					for (File file : files) {
						if (file.isFile()
								&& file.getName().toLowerCase().endsWith(".jar")) {
							String fileName = file.getName();
							JarFile jarFile = new JarFile(file);
							String version = null;
							Manifest manifest = jarFile.getManifest();
							if (manifest != null) {
								Attributes attr = manifest.getMainAttributes();
								for(String ver : vers){
									version = attr.getValue(ver);
									
									if(version != null)
										break;
								}
							}
							/*
							if(!UtilFunc.hasText(version)){
								int pos = fileName.lastIndexOf("-");
								if(pos >= 0){
									version = fileName.substring(pos + 1, fileName.length() - 4);
								}
							}*/

							Map map = new HashMap();
							map.put("file_name", fileName);
							map.put("file_length", file.length());
							map.put("last_modified", new
									java.util.Date(file.lastModified()));
							map.put("file_version", version);
							lst.add(map);
						}
					}
				}
				
				 setAttribute("jarInfo", lst);
			} else{
				setAttribute("jarInfo", Collections.EMPTY_LIST);
			}
			
			return SUCCESS;
		} catch (Exception ex) {
			saveError(ex);
			return ERROR;
		}
	}

	/**
	 * Gets the database info.
	 *
	 * @return the database info
	 */
	public String getDatabaseInfo() {
		return DBFunc.getMetaInfo();
	}
	
	/**
	 * Gets the system properties.
	 * 
	 * @return the system properties
	 */
	public String getSystemProperties() {
		StringBuilder sb = new StringBuilder();
		Properties prop = System.getProperties();
		for (Enumeration e = prop.keys(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			String val = (String) prop.get(key);

			sb.append(key);
			sb.append(" = ");
			sb.append(val);
			if (!"line.separator".equals(key)) {
				sb.append(UtilFunc.NEW_LINE);
			}
		}

		try {
			System.gc();

			sb.append("mem.max = ")
					.append(FileUtils.byteCountToDisplaySize(Runtime
							.getRuntime().maxMemory()))
					.append(UtilFunc.NEW_LINE);
			sb.append("mem.total = ")
					.append(FileUtils.byteCountToDisplaySize(Runtime
							.getRuntime().totalMemory()))
					.append(UtilFunc.NEW_LINE);
			sb.append("mem.free = ")
					.append(FileUtils.byteCountToDisplaySize(Runtime
							.getRuntime().freeMemory()))
					.append(UtilFunc.NEW_LINE);

			sb.append("mem.used = ")
					.append(FileUtils.byteCountToDisplaySize(Runtime
							.getRuntime().totalMemory() - Runtime
							.getRuntime().freeMemory()))
					.append(UtilFunc.NEW_LINE);

			RuntimeMXBean mx = ManagementFactory.getRuntimeMXBean();

			sb.append("rmx.starttime = ")
					.append(DateFunc.datetimeToString(new Date(mx.getStartTime())))
					.append(UtilFunc.NEW_LINE);
			sb.append("rmx.uptime = ")
			.append(mx.getUptime()).append(UtilFunc.NEW_LINE);
			sb.append("rmx.name = ")
					.append(mx.getName())
					.append(UtilFunc.NEW_LINE);
		} catch (Throwable t) {
			LogHelper.getLogger().warn("获取服务器信息出错", t);
		}
		return sb.toString();
	}
}