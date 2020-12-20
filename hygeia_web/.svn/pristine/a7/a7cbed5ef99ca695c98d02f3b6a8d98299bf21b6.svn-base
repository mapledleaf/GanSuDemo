/**
 * Copyright 2011 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.util;

import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.SysMenu;
import com.powersi.hygeia.framework.cache.DBCacheManager;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.RefreshMappingUtil;

/**
 * 菜单辅助类.
 */
public abstract class MenuHelper {

	/** The Constant cacheName. */
	private static final String cacheName = "menurightlist";

	/** The cache flag. */
	private static boolean cacheFlag;

	static {
		cacheFlag = DBCacheManager.getInstance().existsCache(cacheName);

	}

	/**
	 * 获取当前调用菜单.
	 * 
	 * @return the menu info
	 */
	public static SysMenu getMenuInfo() {
		return BusiContext.getMenuInfo();
	}

	/**
	 * 更新缓存.
	 */
	public static void updateCache() {
		if (!cacheFlag) {
			return;
		}

		try {
			DBCacheManager.getInstance().updateCache(cacheName);
		} catch (Exception ex) {
			LogHelper.getLogger().error("更新缓存" + cacheName + "出错", ex);
		}
	}

	/**
	 * 刷新缓存.
	 */
	public static void refreshCache() {
		if (cacheFlag && !DBCacheManager.getInstance().checkRefresh(cacheName)) {
			return;
		}
		try {

			RefreshMappingUtil.refresh("menuright");
		} catch (Exception ex) {
			LogHelper.getLogger().error("刷新缓存menuright出错", ex);
		}
	}
}
