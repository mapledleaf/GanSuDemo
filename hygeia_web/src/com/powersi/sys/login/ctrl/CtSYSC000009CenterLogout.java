/**
 * Copyright 2009 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.login.ctrl;

import javax.servlet.http.HttpServletRequest;

import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.BusiService;
import com.powersi.hygeia.framework.IParameterObj;
import com.powersi.hygeia.framework.IResultObj;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.annotation.Method;
import com.powersi.hygeia.framework.util.LoginHelper;
import com.powersi.hygeia.web.util.SessionHelper;

/**
 * 中心注销服务.
 */
@Method(value="SysLogout", desc="中心用户注销")
public class CtSYSC000009CenterLogout extends BusiService {

	/**
	 * 公共入口方法[0].
	 * 
	 * @param result
	 *            结果集
	 * @param param
	 *            the param
	 * 
	 * @return 大于0表示成功，小于0表示出错
	 */
	public int execute(final IParameterObj param, final IResultObj result) throws Exception {
		final HttpServletRequest request = (HttpServletRequest) BusiContext
				.getContext("user_request");

		// 注销必须从session获取用户信息
		UserInfo userInfo = SessionHelper.getUserInfo(request);
		LoginHelper
				.logout(userInfo, LoginHelper.LOGOUT_CLIENTCLOSE);

		result.setRetMsg("中心用户注销成功");
		return SUCCESS;
	}
}
