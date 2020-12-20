/*
 * Copyright 2011 Powersi. All rights reserved.
 */
package com.powersi.sys.user.ctrl;

import com.powersi.hygeia.framework.BusiService;
import com.powersi.hygeia.framework.IParameterObj;
import com.powersi.hygeia.framework.IResultObj;
import com.powersi.sys.user.service.UserService;

/**
 * 中心用户密码修改.
 */
public class CtSYS000010ChangePassword extends BusiService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.hygeia.framework.IBusiService#execute(com.powersi.hygeia.
	 * framework.IParameterObj, com.powersi.hygeia.framework.IResultObj)
	 */
	public int execute(IParameterObj param, IResultObj result) throws Exception {

		String oldPassword = getParameter("old_password");
		String newPassword = getParameter("new_password");

		if (oldPassword == null) {
			errorHandle(1, "请输入原密码");
			return FAILURE;
		}

		if (newPassword == null) {
			errorHandle(2, "请输入新密码");
			return FAILURE;
		}

		if (oldPassword.equals(newPassword)) {
			errorHandle(3, "新密码与密码相同，无需修改");
			return FAILURE;
		}
		
		UserService service = getBean(UserService.class);
		service.changePassword(oldPassword, newPassword);

		result.setRetMsg("密码修改成功");
		return SUCCESS;
	}
}
