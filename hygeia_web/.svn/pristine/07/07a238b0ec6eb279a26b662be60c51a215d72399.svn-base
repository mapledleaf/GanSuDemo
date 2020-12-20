/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.login.ctrl;

import com.powersi.hygeia.framework.BusiService;
import com.powersi.hygeia.framework.IParameterObj;
import com.powersi.hygeia.framework.IResultObj;
import com.powersi.hygeia.framework.Role;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.annotation.Method;
import com.powersi.sys.user.service.UserService;
import com.powersi.sys.util.MenuHelper;

/**
 * 获取菜单信息
 */
@Method(value="SysQueryMenu", desc="查询菜单信息", role={Role.STAFF})
public class CtSYSC000010QueryMenuInfo extends BusiService {
	private static UserService service = getBean(UserService.class);

	/**
	 * 公共入口方法.
	 * 
	 * @param param
	 *            入参对象
	 * @param result
	 *            结果对象
	 * 
	 * @return 大于0表示成功，小于0表示出错
	 */
	public int execute(IParameterObj param, IResultObj result) {
		try {
			UserInfo userInfo = (UserInfo) getUserInfo();
			if (isNotEmpty(userInfo) && userInfo.isValidUser()) {
				// 刷新菜单缓存
				MenuHelper.refreshCache();

				result.setResultSet("_default_resultset", service.getRoleMenus(userInfo.getRoleSet()));
			}

			setRetMsg("获取菜单信息成功");
			return SUCCESS;
		} catch (Exception e) {
			errorHandle(9, getActionName() + "出现异常", e);
			return FAILURE;
		}
	}
}
