package com.powersi.pcloud.medicare.comm.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author 刘勇
 *
 */
public class ThreadLocalCommUtils {

	private static final ThreadLocal<Map<String, Object>> contextHolder = new ThreadLocal<Map<String, Object>>();

	/**
	 * 
	 * @param akb020
	 */
	public static void setThreadAkb020(String akb020) {
		init();
		contextHolder.get().put("akb020", akb020);
	}

	/**
	 * 
	 * @return
	 */
	public static String getThreadAkb020() {
		init();
		return (String) contextHolder.get().get("akb020");
	}

	/**
	 * 
	 * @param tcjgbm
	 */
	public static void setThreadTcjgbm(String tcjgbm) {
		init();
		contextHolder.get().put("tcjgbm", tcjgbm);
	}

	/**
	 * 
	 * @return
	 */
	public static String getThreadTcjgbm() {
		init();
		return (String) contextHolder.get().get("tcjgbm");
	}

	/**
	 * 
	 */
	public static void init() {
		synchronized (contextHolder) {
			if (contextHolder.get() == null) {
				contextHolder.set(new HashMap<String, Object>());
			}
		}
	}

}
