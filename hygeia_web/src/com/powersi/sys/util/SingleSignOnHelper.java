/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.util;

import java.util.HashMap;
import java.util.Map;

import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * 单点登录辅助类.
 */
public abstract class SingleSignOnHelper {

	/** The server url. */
	private static String serverUrl;

	/** The login url. */
	private static String loginUrl;

	/** The logout url. */
	private static String logoutUrl;

	/** The exit url. */
	private static String exitUrl;

	/** The noauth url. */
	private static String noauthUrl;

	/** The server name. */
	private static String serverName;

	/** The used. */
	private static boolean used = false;

	/** The enabled. */
	private static boolean enabled = false;

	/** The sync flag. */
	private static boolean syncFlag = true;

	/**
	 * Gets the server url.
	 * 
	 * @return the server url
	 */
	public static final String getServerUrl() {
		return serverUrl;
	}

	/**
	 * Sets the server url.
	 * 
	 * @param serverUrl
	 *            the new server url
	 */
	public static final void setServerUrl(String serverUrl) {
		SingleSignOnHelper.serverUrl = serverUrl;
	}

	/**
	 * Gets the login url.
	 * 
	 * @return the login url
	 */
	public static final String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * Sets the login url.
	 * 
	 * @param loginUrl
	 *            the new login url
	 */
	public static final void setLoginUrl(String loginUrl) {
		SingleSignOnHelper.loginUrl = loginUrl;
	}

	/**
	 * Gets the logout url.
	 * 
	 * @return the logout url
	 */
	public static final String getLogoutUrl() {
		return logoutUrl;
	}

	/**
	 * Sets the logout url.
	 * 
	 * @param logoutUrl
	 *            the new logout url
	 */
	public static final void setLogoutUrl(String logoutUrl) {
		SingleSignOnHelper.logoutUrl = logoutUrl;
	}

	/**
	 * Gets the exit url.
	 * 
	 * @return the exit url
	 */
	public static final String getExitUrl() {
		return exitUrl;
	}

	/**
	 * Sets the exit url.
	 * 
	 * @param exitUrl
	 *            the new exit url
	 */
	public static final void setExitUrl(String exitUrl) {
		SingleSignOnHelper.exitUrl = exitUrl;
	}

	/**
	 * Gets the server name.
	 * 
	 * @return the server name
	 */
	public static final String getServerName() {
		return serverName;
	}

	/**
	 * Sets the server name.
	 * 
	 * @param serverName
	 *            the new server name
	 */
	public static final void setServerName(String serverName) {
		SingleSignOnHelper.serverName = serverName;
	}

	/**
	 * Checks if is used.
	 * 
	 * @return true, if is used
	 */
	public static final boolean isUsed() {
		return used;
	}

	/**
	 * Sets the used.
	 * 
	 * @param used
	 *            the new used
	 */
	public static final void setUsed(boolean used) {
		SingleSignOnHelper.used = used;
	}

	/**
	 * Checks if is enabled.
	 * 
	 * @return true, if is enabled
	 */
	public static final boolean isEnabled() {
		return enabled && ParameterMapping.isSingleSignOnEnabled();
	}

	/**
	 * Sets the enabled.
	 * 
	 * @param enabled
	 *            the new enabled
	 */
	public static final void setEnabled(boolean enabled) {
		SingleSignOnHelper.enabled = enabled;
	}

	/**
	 * Gets the noauth url.
	 * 
	 * @return the noauth url
	 */
	public static String getNoauthUrl() {
		return noauthUrl;
	}

	/**
	 * Sets the noauth url.
	 * 
	 * @param noauthUrl
	 *            the new noauth url
	 */
	public static void setNoauthUrl(String noauthUrl) {
		SingleSignOnHelper.noauthUrl = noauthUrl;
	}

	/**
	 * Inits the user.
	 * 
	 * @param userInfo
	 *            the user info
	 * @param userKind
	 *            the user kind
	 * @param loginUser
	 *            the login user
	 */
	public static void initUser(UserInfo userInfo, String userKind,
			String loginUser) {
		if (userInfo == null) {
			return;
		}

		if (!userInfo.isValidUser()) {
			userInfo.put("login_user", loginUser);
			userInfo.put("user_id", Long.valueOf(-1));
			userInfo.put("user_kind", Integer.valueOf(userKind));
			userInfo.put("user_name", "未注册用户");
			userInfo.put("role_id", "0");
		}

		userInfo.put("sso_user", "true");
	}

	/**
	 * Checks if is user.
	 * 
	 * @param userInfo
	 *            the user info
	 * @return true, if is user
	 */
	public static boolean isUser(UserInfo userInfo) {
		return userInfo == null ? false : "true".equals(UtilFunc.getString(
				userInfo, "sso_user"));
	}

	/**
	 * 同步用户.
	 * 
	 * @param loginUser
	 *            the login user
	 */
	public static void syncUser(String loginUser) {
		try {
			if (UtilFunc.isEmpty(loginUser)) {
				return;
			}

			if (!syncFlag) {
				return;
			}
			
			String sql = "{ call pkg_unified_portal.prc_sync_staff(?) }";
			Map inparamMap = new HashMap();
			inparamMap.put(Integer.valueOf(1), loginUser);

			Map outparamMap = new HashMap();
			DBHelper.executeCall(sql, inparamMap, outparamMap);
		} catch (Exception ex) {
			LogHelper.getLogger().warn("同步用户" + loginUser + "出错", ex);
		}
	}

	/**
	 * 同步部门.
	 * 
	 * @param deptUuid
	 *            the dept uuid
	 */
	public static void syncDept(String deptUuid) {
		try {
			if (UtilFunc.isEmpty(deptUuid)) {
				return;
			}
			
			if (!syncFlag) {
				return;
			}

			String sql = "{ call pkg_unified_portal.prc_sync_dept(?) }";
			Map inparamMap = new HashMap();
			inparamMap.put(Integer.valueOf(1), deptUuid);

			Map outparamMap = new HashMap();
			DBHelper.executeCall(sql, inparamMap, outparamMap);
		} catch (Exception ex) {
			LogHelper.getLogger().warn("同步部门" + deptUuid + "出错", ex);
		}
	}

	/** 同步锁. */
	private static Object syncLock = new Object();

	/**
	 * 同步所有用户.
	 */
	public static void syncAllUser() {
		try {
			if (!syncFlag) {
				return;
			}

			synchronized (syncLock) {
				String sql = "{ call pkg_unified_portal.prc_sync_all_staff() }";
				Map inparamMap = new HashMap();
				Map outparamMap = new HashMap();
				DBHelper.executeCall(sql, inparamMap, outparamMap);
			}

		} catch (Exception ex) {
			LogHelper.getLogger().warn("同步全部用户出错", ex);
		}
	}

	/**
	 * 同步所有部门.
	 */
	public static void syncAllDept() {
		try {
			if (!syncFlag) {
				return;
			}

			synchronized (syncLock) {
				String sql = "{ call pkg_unified_portal.prc_sync_all_dept() }";
				Map inparamMap = new HashMap();
				Map outparamMap = new HashMap();
				DBHelper.executeCall(sql, inparamMap, outparamMap);
			}
		} catch (Exception ex) {
			LogHelper.getLogger().warn("同步全部部门出错", ex);
		}
	}

	/**
	 * 完整同步用户.
	 */
	public static void syncFullUser() {
		try {
			if (!syncFlag) {
				return;
			}

			synchronized (syncLock) {
				String sql = "{ call pkg_unified_portal.prc_sync_full_staff() }";
				Map inparamMap = new HashMap();
				Map outparamMap = new HashMap();
				DBHelper.executeCall(sql, inparamMap, outparamMap);
			}

		} catch (Exception ex) {
			throw new HygeiaException("完整同步用户出错", ex);
		}
	}

	/**
	 * 完整同步部门.
	 */
	public static void syncFullDept() {
		try {
			if (!syncFlag) {
				return;
			}

			synchronized (syncLock) {
				String sql = "{ call pkg_unified_portal.prc_sync_full_dept() }";
				Map inparamMap = new HashMap();
				Map outparamMap = new HashMap();
				DBHelper.executeCall(sql, inparamMap, outparamMap);
			}
		} catch (Exception ex) {
			throw new HygeiaException("完整同步部门出错", ex);
		}
	}

	/**
	 * 完整同步全部.
	 */
	public static void syncFullAll() {
		try {
			if (!syncFlag) {
				return;
			}

			synchronized (syncLock) {
				String sql = "{ call pkg_unified_portal.prc_sync_job() }";
				Map inparamMap = new HashMap();
				Map outparamMap = new HashMap();
				DBHelper.executeCall(sql, inparamMap, outparamMap);
			}
		} catch (Exception ex) {
			throw new HygeiaException("完整同步全部出错", ex);
		}
	}

	/**
	 * 是否同步标志.
	 * 
	 * @return true, if is sync flag
	 */
	public static boolean isSyncFlag() {
		return syncFlag;
	}

	/**
	 * 设置同步标志.
	 * 
	 * @param syncFlag
	 *            the new sync flag
	 */
	public static void setSyncFlag(boolean syncFlag) {
		SingleSignOnHelper.syncFlag = syncFlag;
	}
}