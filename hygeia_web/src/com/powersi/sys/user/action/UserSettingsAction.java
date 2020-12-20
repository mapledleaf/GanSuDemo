/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.user.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.web.BaseAction;
import com.powersi.sys.user.service.UserService;

/**
 * The Class UserSettingsAction.
 */
@Namespace("/login")
@Action(value = "settings", results = {
		@Result(name = "user", location = "/pages/sys/user/ViewUserInfo.jsp")
})
public class UserSettingsAction extends BaseAction {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The user service. */
	public static final UserService userService = getBean(UserService.class);

	/**
	 * Verify pwd.
	 * 
	 * @return the string
	 */
	public String verifyPwd() {
		try {
			// 校验用户密码
			Map userMap = new HashMap();

			userMap.putAll(getUserInfo());
			userMap.put("password", getParameter("pwd"));

			userService.verifyLogin(userMap);

			saveJSONMessage("密码校验成功");
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Save bookmark.
	 * 
	 * @return the string
	 */
	public String saveBookmark() {
		try {
			if (getUserInfo().isValidUser()) {
				userService.saveBookmark(getParameter("bookmark"));
				saveJSONMessage("保存书签成功");
			}
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Save config.
	 *
	 * @return the string
	 */
	public String saveConfig() {
		try {
			if (getUserInfo().isValidUser()) {
				String code = getParameter("code");
				String value = getParameter("value");
				
				userService.saveConfig(code, value);
				
				getUserInfo().put(code, value);
				saveJSONMessage("保存用户成功");
			} else {
				saveJSONError("请登录");
			}
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}
	
	/**
	 * find config.
	 *
	 * @return the string
	 */
	public String findConfig() {
		try {
			if (getUserInfo().isValidUser()) {
				String code = getParameter("code");
				String value = getParameter("value");
				
				return renderJson(userService.getConfig(code, value));
			} else {
				return renderError("请登录");
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Query user info.
	 * 
	 * @return the string
	 */
	public String queryUserInfo() {
		try {
			String userKind = getUserInfo().getUserKind();

			String orgLabel = "";
			if ("9".equals(userKind)) {
				orgLabel = "所属部门";
			} else if ("2".equals(userKind)) {
				orgLabel = "所属单位";
			} else if ("3".equals(userKind)) {
				orgLabel = "所属医院";
			} else if ("4".equals(userKind)) {
				orgLabel = "所属银行";
			} else if ("1".equals(userKind)) {
				orgLabel = "";
			}

			getRequest().setAttribute("labelOrg", orgLabel);

			if (orgLabel.length() > 0) {
				getRequest().setAttribute("showOrg", true);
			} else {
				getRequest().setAttribute("showOrg", false);
			}

			getRequest().setAttribute("user", userService.queryUserInfo());
			return "user";
		} catch (Exception ex) {
			saveError(ex);
			return ERROR;
		}
	}

	/**
	 * Update user info.
	 * 
	 * @return the string
	 */
	public String updateUserInfo() {
		try {
			if (getUserInfo().isValidUser()) {
				userService.updateUserInfo(getAllParameters());
				saveJSONMessage("保存用户信息成功");
			}
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Gets the config.
	 * 
	 * @return the config
	 */
	public static Map getConfig() {
		return userService.getConfig();
	}
}