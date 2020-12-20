/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.util;

import com.powersi.hygeia.framework.ParameterMapping;

/**
 * 统一平台辅助类.
 */
public abstract class UnifiedPlatformHelper {
	/** The up server. */
	private static boolean serverFlag = false;

	static{
		setServerFlag("1".equals(ParameterMapping.getStringByCode("up_server_flag")));
	}
	/**
	 * 是否统一平台服务器.
	 * 
	 * @return true, if is server flag
	 */
	public static boolean isServerFlag() {
		return serverFlag;
	}

	/**
	 * 返回统一平台服务器.
	 * 
	 * @param serverFlag
	 *            the new server flag
	 */
	public static void setServerFlag(boolean serverFlag) {
		UnifiedPlatformHelper.serverFlag = serverFlag;
	}
}
