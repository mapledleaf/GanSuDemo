package com.powersi.hygeia.comm.service;

/**
 * 用于在当前线程存放正在处理的统筹机构编码 例如hygeia_calc，在计算api入口通过本方法放置当前请求的统筹编码，后续计算环节的服务都可以通过
 * 本服务得到当前正在处理的统筹区编码
 * 
 * @author 李志钢
 *
 */
public class ThreadLocal_tcjgbm {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	/**
	 * 
	 * @param tcjgbm
	 */
	public static void setThreadTcjgbm(String tcjgbm) {
		contextHolder.set(tcjgbm);
	}

	/**
	 * 
	 * @return
	 */
	public static String getThreadTcjgbm() {
		return contextHolder.get();
	}

}
