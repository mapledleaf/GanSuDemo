package com.powersi.biz.medicare.comm.service;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.comm.utils.SysUtils;

/**
 * 
 * @author 刘勇
 *
 */
@Service
public class ProjectAssistServiceImpl implements ProjectAssistService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Properties configs;

	@PostConstruct
	public void init() {
		this.printString();
	}

	@Override
	public void printString() {
		System.out.println(
				"【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】");
		System.out.println(new StringBuilder().append("project.name：")
				.append(this.configs.getProperty("project.name", "xxx-xxx")));
		System.out.println(new StringBuilder().append("project.version：")
				.append(this.configs.getProperty("project.version", "xxx-xxx")));
		System.out.println(
				"【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】【】");
	}

	@Override
	public boolean isDebugApiMode() {
		if ("1".equals(this.configs.getProperty("pcloud_rpc.debug_api", "0"))) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isDebugWebMode() {
		if ("1".equals(this.configs.getProperty("pcloud_rpc.debug_web", "0"))) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isWhetherDeveloperEnvironment() {
		String systemType = SysUtils.getPlatform();
		if (StringUtils.isBlank(systemType)) {
			return false;
		}
		if ("WINDOWS".equals(systemType) || "MAC".equals(systemType)) {
			return true;
		}
		return false;
	}

}
