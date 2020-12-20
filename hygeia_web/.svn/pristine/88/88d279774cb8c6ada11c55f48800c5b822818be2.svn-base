/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.sys.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.validation.Assertion;

import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.util.LoginHelper;
import com.powersi.hygeia.web.util.SessionHelper;
import com.yinhai.sso.DefaultTicketValidateFilter;

/**
 * The Class LocalCas20TicketValidationFilter.
 */
public class LocalCas20TicketValidationFilter extends
		DefaultTicketValidateFilter {
	/* (non-Javadoc)
	 * @see com.yinhai.sso.DefaultTicketValidateFilter#registLoalUserSession(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.jasig.cas.client.validation.Assertion)
	 */
	protected boolean registLoalUserSession(HttpServletRequest request,
			HttpServletResponse response, Assertion assertion) {
		try {
			String loginUser = transSSOLoginIdToLocalLoginId(assertion
					.getPrincipal());

			if (null == loginUser || "".equals(loginUser)) {
				return false;
			}

			// 实现本地系统注册用户Session，注册成功返回true，失败返回false
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.yinhai.sso.DefaultTicketValidateFilter#isNeedAuthUrl(javax.servlet.http.HttpServletRequest, java.lang.String)
	 */
	@Override
	protected boolean isNeedAuthUrl(HttpServletRequest request, String url) {
		// 这里返回 true表示当前url 需要经过单点登录认证；返回false不需要经过单点登录。
		// 若有不需要经过登录认证就可以访问的资源，可以在这里过滤。
		return true;
	}

	/* (non-Javadoc)
	 * @see com.yinhai.sso.DefaultTicketValidateFilter#doLogoutCurrentSystem(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doLogoutCurrentSystem(HttpServletRequest request,
			HttpServletResponse response) {
		// 本地系统注销用户Session
		try {
			UserInfo userInfo = SessionHelper.getUserInfo(request);
			if (userInfo != null && userInfo.isValidUser()) {
				LoginHelper.logout(userInfo, LoginHelper.LOGOUT_CLIENTCLOSE);
				SessionHelper.removeUserInfo(request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
