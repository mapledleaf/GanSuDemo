package com.powersi.pcloud.cache;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.comm.exception.ApusException;
import com.powersi.comm.utils.SpringUtil;

/**
 * 工程名称的帮助类 1.从配置文件pcloud_cacherefresh.properties中获取项目名称（全大写）
 * 2.通过获取bean的形式进行缓存刷新 使用步骤：
 * 1.在需要进行缓存刷新的工程添加pcloud_cacherefresh.properties(存在project.properties的可以不添加)
 * 2.在需要进行缓存刷新的工程的web.xml中添加classpath*:spring/pcloud_cacherefresh.xml
 * 
 * @author 黄尧
 *
 */
public class ProjectNameHandler {

	@Autowired
	private Properties configs;
	@Autowired
	private SpringUtil springUtil;

	/**
	 * 从配置文件project.properties中获取项目名称（全大写）的操作方法
	 * 
	 * @return 项目名称
	 */
	public String getProjectNameHadle() {
		String projectName = configs.getProperty("project.name");
		if (StringUtils.isBlank(projectName)) {
			throw new ApusException("当前项目没有配置project.name参数，请检查配置文件project.properties");
		}
		return projectName.toUpperCase();

	}

	/**
	 * 通过获取bean的形式进行缓存刷新
	 * 
	 * @param serviceID
	 *            服务id
	 */
	public void refreshCache(String serviceID) {
		CacheRefresh bean = (CacheRefresh) springUtil.getBean(serviceID);
		bean.refreshCache();
	}

}
