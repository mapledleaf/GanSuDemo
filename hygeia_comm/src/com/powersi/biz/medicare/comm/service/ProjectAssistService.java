package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;

/**
 * 
 * @author 刘勇
 *
 */
public interface ProjectAssistService extends Serializable {

	/**
	 * 打印工程信息
	 */
	public void printString();

	/**
	 * 是否本机调试模式_API
	 * 
	 * @return
	 */
	public boolean isDebugApiMode();

	/**
	 * 是否本机调试模式_WEB
	 * 
	 * @return
	 */
	public boolean isDebugWebMode();

	/**
	 * 结算云核心缓存组件redis非常脆弱需要加强保护
	 * 
	 * <pre>
	 * 1、如果是开发人员的本机调试环境则不自动加载缓存，防止开发启应用的时候把错误的数据加载到服务器的缓存；
	 * </pre>
	 * 
	 * @return 是否开发人员本机调试环境
	 */
	public boolean isWhetherDeveloperEnvironment();

}
