/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.sys.login.ctrl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.BusiService;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.IParameterObj;
import com.powersi.hygeia.framework.IResultObj;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.annotation.Method;
import com.powersi.hygeia.framework.entity.SysUserKind;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.LoginHelper;
import com.powersi.hygeia.web.util.SessionHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.sys.user.service.UserService;
import com.powersi.sys.util.MenuHelper;
import com.powersi.sys.util.SingleSignOnHelper;
import com.powersi.sys.util.UserKindHelper;

/**
 * 中心登录服务.
 */
@Method(value = "SysLogin", desc = "中心用户登录")
public class CtSYSC000008CenterLogin extends BusiService {

	private static final UserService service = getBean(UserService.class);

	/**
	 * 公共入口方法.
	 *
	 * @param param
	 *            the param
	 * @param result
	 *            结果集
	 * @return 大于0表示成功，小于0表示出错
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int execute(final IParameterObj param, final IResultObj result) throws Exception {
		final HttpServletRequest request = (HttpServletRequest) BusiContext.getContext("user_request");
		String serverName = request.getServerName();
		if (serverName == null) {
			serverName = "unknown";
		}
		final String loginUser = getParameter("login_user");
		if (loginUser == null) {
			errorHandle(2, "请输入登录用户名(login_user)");
			return FAILURE;
		}
		String password = getParameter("login_password");
		if (password == null) {
			errorHandle(2, "请输入登录密码(login_password)");
			return FAILURE;
		}
		UserInfo userInfo = BusiContext.getUserInfo();
		if (userInfo != null && userInfo.isValidUser() && "true".equals(getParameter("auto_login"))) {
			userInfo = BusiContext.getUserInfo();
			if (!loginUser.equals(userInfo.getLoginUser())) {
				errorHandle(2, "自动登录用户名错误，请重登录");
				return FAILURE;
			}
			final String remoteIP = WebHelper.getRemoteAddr(request);
			userInfo.setLoginAddress(remoteIP);
			LoginHelper.login(userInfo, LoginHelper.LOGIN_SUCCESS, "单点登录");
		} else {
			if (userInfo != null) {
				userInfo.clear();
				userInfo = null;
			}
			BusiContext.setUserInfo(null);
			userInfo = new UserInfo();
			userInfo.put("user_id", "0");
			userInfo.put("login_user", loginUser);
			userInfo.put("password", password);
			String userKind = getParameter("user_kind");
			if (isEmpty(userKind)) {
				userKind = "9";
			}
			SysUserKind userKindDTO = UserKindHelper.get(userKind);
			if (userKindDTO != null) {
				userInfo.put("user_kind", userKind);
				userInfo.put("login_type", userKindDTO.getLoginType());
			} else {
				userInfo.put("user_kind", "9");
				userInfo.put("login_type", "admin");
			}
			final String remoteIP = WebHelper.getRemoteAddr(request);
			userInfo.setLoginAddress(remoteIP);
			// 验证码检查
			if (ParameterMapping.getUseCheckCode() && !checkVerifyCode(getParameter("verifycode"))) {
				errorHandle(1, "输入验证码错误");
				return FAILURE;
			}
			// 登录检查
			try {
				service.verifyLogin(userInfo);
			} catch (Exception ex) {
				try {
					LoginHelper.login(userInfo, LoginHelper.LOGIN_FAILURE, ex.getMessage());
				} catch (Exception logex) {
					logex.printStackTrace();
				}
				errorHandle(1, ex.getMessage());
				return FAILURE;
			}
			LoginHelper.login(userInfo, LoginHelper.LOGIN_SUCCESS, "");
			BusiContext.setUserInfo(userInfo);
			SessionHelper.setUserInfo(request, userInfo);
		}
		if (!"1".equals(getParameter("web_flag"))) {
			final StringBuilder sbUserInfo = new StringBuilder();
			String centerName = CodetableMapping.getDisplayValue("aaa027", userInfo.get("center_id"));
			if (isNotEmpty(centerName)) {
				sbUserInfo.append(" 【所属统筹区：");
				sbUserInfo.append(centerName);
				sbUserInfo.append("】");
			}
			if (userInfo.isCorpUser()) {
				sbUserInfo.append(" 【操作级别：");
				sbUserInfo.append("单位专管员");
				sbUserInfo.append("】");
			} else if (UserInfo.HOSP_KIND.equals(userInfo.getUserKind())) {
				String hospName = CodetableMapping.getDisplayValue("sys_hosp", userInfo.get("hospital_id"));
				if (isNotEmpty(hospName)) {
					sbUserInfo.append(" 【所属医院：");
					sbUserInfo.append(hospName);
					sbUserInfo.append("】");
				}
			} else {
				String deptName = CodetableMapping.getDisplayValue("sys_dept", userInfo.get("dept_id"));
				if (isNotEmpty(deptName)) {
					sbUserInfo.append(" 【所属部门：");
					sbUserInfo.append(deptName);
					sbUserInfo.append("】");
				}
				String staffLevel = CodetableMapping.getDisplayValue("staff_level", userInfo.get("staff_level"));
				if (isNotEmpty(staffLevel)) {
					sbUserInfo.append(" 【操作级别：");
					sbUserInfo.append(staffLevel);
					sbUserInfo.append("】");
				}
			}
			String strUserIndentity = getString(userInfo, "user_indentity", userInfo.getUserId());
			result.setResult("staff_name", userInfo.getUserName());
			result.setResult("user_info", sbUserInfo.toString());
			// 本地用户默认使用开发者模式
			if ("localhost".equals(serverName) || "127.0.0.1".equals(serverName)) {
				result.setResult("is_developer", "1");
			} else {
				result.setResult("is_developer", getString(userInfo, "is_developer", "0"));
			}
			result.setResult("user_indentity", strUserIndentity);
			result.setResult("login_time", DateFunc.datetimeToString(new java.util.Date()));
			result.setResult("welcome_url", ParameterMapping.getStringByCode("welcome_url"));
			result.setResult("header_url", ParameterMapping.getStringByCode("header_url"));
			result.setResult("about_url", ParameterMapping.getStringByCode("about_url"));
			result.setResult("time_error", ParameterMapping.getStringByCode("time_error", "120"));
			result.setResult("header_height", ParameterMapping.getStringByCode("header_height", "90"));
			// 单点登录需要页面跳转实现注销
			if (SingleSignOnHelper.isUsed() && SingleSignOnHelper.isEnabled()) {
				result.setResult("relogin_url",
						String.format("/login/logout.action?action=relogin&loginid=%1$s&userbrowser=true",
								getString(userInfo, "login_id", "0")));
			}
			result.setResult("diag_realsize", "1");
			// 检查程序是否需要升级
			String strClientVer = "";
			int verType = 0;
			if (getParameter("explorer_version") != null) {
				strClientVer = getParameter("explorer_version").toString().trim();
				verType = 2;// explorer
			} else if (getParameter("login_version") != null) {
				strClientVer = getParameter("login_version").toString().trim();
				verType = 1;// activex
				if (strClientVer.startsWith("B")) {
					strClientVer = strClientVer.substring(1);
					verType = 2;
				}
			}
			String strServerVer = "";
			switch (verType) {
			case 1:
				strServerVer = ParameterMapping.getStringByCode("web_client_ver").trim();
				break;
			case 2:
				strServerVer = ParameterMapping.getStringByCode("explorer_client_ver").trim();
				break;
			}
			if (verType > 0 && isNotEmpty(strClientVer) && isNotEmpty(strServerVer)
					&& strClientVer.compareTo(strServerVer) < 0) {
				String strUpdateUrl = null;
				switch (verType) {
				case 1:
					strUpdateUrl = ParameterMapping.getStringByCode("web_setup_url").trim();
					break;
				case 2:
					strUpdateUrl = ParameterMapping.getStringByCode("explorer_setup_url").trim();
					break;
				}
				if (isNotEmpty(strUpdateUrl)) {
					result.setResult("update_ver", strServerVer);
					result.setResult("update_url", strUpdateUrl);
				}
			}
			// 检查程序是否启用消息系统
			final String strMessageFlag = ParameterMapping.getStringByCode("message_flag", "0");
			if ("1".equals(strMessageFlag)) {
				result.setResult("message_flag", strMessageFlag);

				final String strInterval = ParameterMapping.getStringByCode("message_query_interval", "-1");
				if (Integer.parseInt(strInterval) >= 10) {
					result.setResult("message_interval", strInterval);
				}
			}
			// 文本大小
			String textSize = getString(BusiContext.getUserInfo(), "web_text_size",
					ParameterMapping.getStringByCode("web_text_size"));
			if ("large".equals(textSize)) {
				result.setResult("large_text", "1");
			}
			// 单位用户缴费检查
			if (userInfo.isCorpUser() && "1".equals(ParameterMapping.getByCode("check_corppay", "0"))) {
				boolean isOut = true;// 是否外网用户(127.0.0.1为了测试，认为是外网登录）
				if (serverName != null) {
					if (serverName.equals("localhost") || serverName.startsWith("192.")
							|| serverName.startsWith("172.")) {
						isOut = false;
					}
				}
				// 外网访问
				if (isOut) {
					boolean isPay = false;
					long nums = 0;
					Map pay = service.queryCorpUserPay(getString(userInfo, "user_id", "0"));
					if (isNotEmpty(pay)) {
						isPay = "1".equals(getString(pay, "payed"));
						nums = Long.valueOf(getString(pay, "per_number", "0"));
					}
					// 未缴费用户
					if (!isPay) {
						Set set = new HashSet();
						if (nums >= 1000) {
							set.add(Integer.valueOf(3));
							userInfo.put("role_id", "3");
						} else {
							set.add(Integer.valueOf(4));
							userInfo.put("role_id", "4");
						}
						userInfo.put("role_set", set);
					}
				}
			}
			// 刷新菜单缓存
			MenuHelper.refreshCache();
			// 设置返回菜单
			List menuList = service.getRoleMenus(userInfo.getRoleSet());
			result.setResultSet("_default_resultset", menuList);
		}
		result.setRetMsg("中心用户登录成功");
		return SUCCESS;
	}

	/**
	 * 校验验证码
	 * 
	 * @param inputValue
	 * @return
	 */
	private static final boolean checkVerifyCode(String inputValue) {
		HttpSession session = null;
		String saveValue = null;
		HttpServletRequest request = BusiContext.getRequest();
		if (request != null) {
			session = request.getSession(false);
			if (session != null) {
				saveValue = (String) session.getAttribute(SessionHelper.SESSION_VERIFYCODE);
			}
		}
		if (isEmpty(inputValue) || isEmpty(saveValue) || !inputValue.equalsIgnoreCase(saveValue)) {
			return false;
		} else {
			if (session != null) {
				session.removeAttribute(SessionHelper.SESSION_VERIFYCODE);
			}
			return true;
		}
	}

}
