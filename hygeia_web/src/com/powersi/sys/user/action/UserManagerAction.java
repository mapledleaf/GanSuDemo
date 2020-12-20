/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.user.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.sys.manager.dao.RoleDAO;
import com.powersi.sys.user.dao.StaffDAO;
import com.powersi.sys.user.dao.UserDAO;
import com.powersi.sys.user.dto.UserKindDTO;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;
import com.powersi.sys.util.PasswordHelper;
import com.powersi.sys.util.UserKindHelper;

/**
 * 用户管理类.
 */
@Action(value = "UserManager", results = {
		@Result(name = "success", location = "/pages/sys/user/UserManager.jsp")
})
public class UserManagerAction extends BaseAction {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 用户类型. */
	private String userKind;
	/** 用户名. */
	private String userName;
	/** 登录名. */
	private String loginUser;
	/** 用户ID. */
	private String userId;

	/** 角色列表. */
	private List roleList;

	/** The user dao. */
	private static UserDAO userDAO = BeanHelper.getBean(UserDAO.class);

	/** The role dao. */
	private static RoleDAO roleDAO = BeanHelper.getBean(RoleDAO.class);

	/** The staff dao. */
	private static StaffDAO staffDAO = BeanHelper.getBean(StaffDAO.class);

	/**
	 * 根据条件查询用户.
	 * 
	 * @return the string
	 */
	@Actions({
			@Action(value = "queryUserList", results = {
					@Result(name = "success", location = "/pages/sys/user/ResetPassword.jsp")
			}),
			@Action(value = "queryUserListForLock", results = {
					@Result(name = "success", location = "/pages/sys/user/LockUser.jsp")
			})
	})
	public String queryList() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				UserKindDTO dto = UserKindHelper.get(userKind);
				if (dto == null) {
					throw new HygeiaException("无效的用户类型");
				}

				PagerHelper.initPagination(getRequest());
				List lst = userDAO.queryUserList(dto, loginUser, userName);
				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(lst));
				return NONE;
			} else {
				/*
				 * Map<String, String> code = UserKindHelper
				 * .valueMap(getUserInfo() .getUserKind());
				 * 
				 * setAttribute("codeUserKind", code); if (userKind == null &&
				 * !UtilFunc.isEmpty(code)) { userKind =
				 * code.keySet().iterator().next().toString(); }
				 */

				return SUCCESS;
			}
		} catch (Exception ex) {
			saveError(ex);
			return ERROR;
		}
	}

	/**
	 * 查询通讯录.
	 * 
	 * @return the string
	 */
	@Action(value = "queryAddressList", results = {
			@Result(name = "success", location = "/pages/sys/user/AddressList.jsp")
	})
	public String queryAddressList() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				PagerHelper.initPagination(getRequest());
				List lst = userDAO.queryAddressList(getAllParameters());
				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(lst));
				return NONE;
			} else {
				return SUCCESS;
			}
		} catch (Exception ex) {
			saveError(ex);
			return ERROR;
		}
	}

	/**
	 * 锁定用户.
	 * 
	 * @return the string
	 * @the hygeia exception
	 */
	@Action("LockUser")
	public String lockUser() {
		try {
			UserKindDTO dto = UserKindHelper.get(userKind);
			if (dto == null) {
				throw new HygeiaException("无效的用户类型");
			}

			Map user = userDAO.findUser(dto, userId);
			if (UtilFunc.isEmpty(user)) {
				throw new HygeiaException("用户不存在");
			}

			if (userId.equals(getUserInfo().getUserId())) {
				throw new HygeiaException("不能锁定自己");
			}

			if (UtilFunc.getString(user, "lock_state", "0").equals("1")) {
				throw new HygeiaException("用户已锁定");
			}

			String reason = getParameter("reason");
			userDAO.lockUser(user, reason);

			Map map = new HashMap();
			map.put("user_id", userId);
			map.put("lock_state", "1");
			map.put("lock_time",
					DateFunc.datetimeToString(new java.util.Date()));
			map.put("lock_reason", reason);

			this.setJSONReturn("用户锁定成功", map);
		} catch (Exception e) {
			this.saveJSONError(e);
		}

		return NONE;
	}

	/**
	 * Unlock user.
	 * 
	 * @return the string
	 */
	@Action("UnlockUser")
	public String unlockUser() {
		try {
			UserKindDTO dto = UserKindHelper.get(userKind);
			if (dto == null) {
				throw new HygeiaException("无效的用户类型");
			}

			Map user = userDAO.findUser(dto, userId);
			if (UtilFunc.isEmpty(user)) {
				throw new HygeiaException("用户不存在");
			}

			if (UtilFunc.getString(user, "lock_state", "0").equals("0")) {
				throw new HygeiaException("用户已解锁");
			}

			userDAO.unlockUser(user);

			Map map = new HashMap();
			map.put("user_id", userId);
			map.put("lock_state", "0");
			map.put("lock_time", "");
			map.put("lock_reason", "");

			this.setJSONReturn("用户解锁成功", map);
		} catch (Exception e) {
			this.saveJSONError(e);
		}

		return NONE;
	}

	/**
	 * Reset password.
	 * 
	 * @return the string
	 */
	@Action("ResetPassword")
	public String resetPassword() {
		try {
			UserKindDTO dto = UserKindHelper.get(userKind);
			if (dto == null) {
				throw new HygeiaException("无效的用户类型");
			}

			Map user = userDAO.findUser(dto, userId);
			if (UtilFunc.isEmpty(user)) {
				throw new HygeiaException("用户不存在");
			}

			userDAO.resetPassword(user);

			StringBuilder sb = new StringBuilder();
			sb.append("用户[").append(user.get("login_user"));
			sb.append(" ").append(user.get("user_name")).append("]");
			sb.append("的密码已经重置");

			Map map = new HashMap();
			map.put("user_id", userId);

			this.setJSONReturn(sb.toString(), map);
		} catch (Exception e) {
			this.saveJSONError(e);
		}

		return NONE;
	}

	/**
	 * 清除密码错误次数.
	 * 
	 * @return the string
	 */
	@Action("ClearPasswordError")
	public String clearPasswordError() {
		try {
			UserKindDTO dto = UserKindHelper.get(userKind);
			if (dto == null) {
				throw new HygeiaException("无效的用户类型");
			}

			Map user = userDAO.findUser(dto, userId);
			if (UtilFunc.isEmpty(user)) {
				throw new HygeiaException("用户不存在");
			}

			userDAO.clearPasswordError(user);

			StringBuilder sb = new StringBuilder();
			sb.append("用户[").append(user.get("login_user"));
			sb.append(" ").append(user.get("user_name")).append("]");
			sb.append("的密码错误次数已经清除");

			Map map = new HashMap();
			map.put("user_id", userId);

			this.setJSONReturn(sb.toString(), map);
		} catch (Exception e) {
			this.saveJSONError(e);
		}

		return NONE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		try {
			String userKind = getParameter("user_kind");
			if (UtilFunc.isEmpty(userKind)) {
				saveJSONError("用户类别(user_kind)不能为空");
				return ERROR;
			}

			setAttribute("roleList", roleDAO.getRoleListByUserKind(userKind));
			return SUCCESS;
		} catch (Exception ex) {
			saveJSONError(ex);
			return ERROR;
		}

	}

	/**
	 * Query user info.
	 * 
	 * @return the string
	 */
	public String getUser() {
		try {
			String userKind = getParameter("user_kind");
			String userId = getParameter("user_id");
			Map userInfo = userDAO.getUserInfo(userKind, userId);
			if (UtilFunc.isEmpty(userInfo)) {
				saveJSONError("用户不存在");
				return NONE;
			}

			userInfo.put("role_list",
					userDAO.getUserRoleIdList(userKind, userId));
			userInfo.put("org_list", userDAO.getUserOrgList(userKind, userId));

			setJSONReturn(userInfo);
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * 保存用户信息.
	 * 
	 * @return the string
	 */
	public String saveUser() {
		try {
			String userKind = getParameter("user_kind");
			if (!UtilFunc.hasText(userKind)) {
				throw new HygeiaException("用户类别不能为空");
			}

			String userId = getParameter("user_id");
			if (!UtilFunc.hasText(userId)) {
				throw new HygeiaException("用户编号不能为空");
			}

			String loginUser = getParameter("login_user");
			if (!UtilFunc.hasText(loginUser)) {
				throw new HygeiaException("登录名不能为空");
			}
			loginUser = loginUser.toLowerCase();

			String userName = getParameter("user_name");
			if (!UtilFunc.hasText(userName)) {
				throw new HygeiaException("用户名不能为空");
			}

			if (userDAO.checkLoginUserRepeate(userKind, loginUser, userId)) {
				throw new HygeiaException("登录名已存在");
			}

			// 机构用户需要判断中心用户是否存在，避免重复
			if ("8".equals(userKind)) {
				if (staffDAO.checkLoginUserRepeate(loginUser, userId)) {
					throw new HygeiaException("登录名已存在");
				}
			}

			Map map = new HashMap();
			map.putAll(getAllParameters());
			map.put("login_user", loginUser);

			map.remove("orgs");
			map.remove("password");

			// 处理用户信息
			String password = getParameter("password");
			if (userId.equals("0")) {
				if (!UtilFunc.hasText(password)) {
					password = PasswordHelper.createPassword("000000");
				}

				long id = SysFunc.getMaxNo("USER_ID");
				if (id < 1000) {// 预留1000个用户
					SysFunc.setMaxNo("USER_ID", 1000);
					id = 1000;
				}

				// 用户编号使用负数
				userId = String.valueOf(0 - id);
				map.put("user_id", userId);

				userDAO.insertUser(map);
			} else {
				userDAO.updateUser(map);
			}

			Map user = new HashMap();

			user.put("user_id", userId);
			user.put("user_kind", userKind);
			user.put("user_name", userName);
			user.put("login_user", loginUser);

			// 处理密码
			if (UtilFunc.hasText(password)) {
				userDAO.changePassword(user, password);
			}

			// 处理角色
			roleDAO.deleteUserRole(userId, userKind);
			String[] roles = getParameterValues("role_list");
			if (!UtilFunc.isEmpty(roles) && roles.length > 0) {
				List data = new ArrayList();
				for (String role : roles) {
					Map row = new HashMap();
					row.putAll(user);
					row.put("role_id", role);
					data.add(row);
				}

				roleDAO.insertUserRole(data);
			}

			// 处理组织
			userDAO.deleteUserOrg(userKind, userId);
			String orgList = getParameter("org_list");
			if (UtilFunc.hasText(orgList)) {
				List<Map<String, Object>> data = JsonHelper.toList(orgList);
				String orgType = "0";
				if ("8".equals(userKind) || "9".equals(userKind)) {
					if ("4".equals(getParameter("user_level"))) {
						orgType = "2";
					} else if ("5".equals(getParameter("user_level"))) {
						orgType = "3";
					} else {
						orgType = "1";
					}
				} else {
					orgType = "1" + userKind;
				}

				for (Map<String, Object> org : data) {
					org.putAll(user);
					org.put("org_type", orgType);
				}
				userDAO.insertUserOrg(data);
			}

			setJSONReturn("保存用户信息成功", user);
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * 删除用户信息.
	 * 
	 * @return the string
	 */
	public String deleteUser() {
		try {
			String userKind = getParameter("user_kind");
			if (!UtilFunc.hasText(userKind)) {
				throw new HygeiaException("用户类别不能为空");
			}

			String userId = getParameter("user_id");
			if (!UtilFunc.hasText(userId)) {
				throw new HygeiaException("用户编号不能为空");
			}

			if (!getUserInfo().isSuperUser()) {
				throw new HygeiaException("只有超级用户，才能操作删除用户");
			}

			userDAO.deleteUser(userKind, userId);

			saveJSONMessage("删除用户成功");
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Query user list.
	 * 
	 * @return the string
	 */
	public String queryUserList() {
		try {
			String userKind = getParameter("user_kind");
			if (UtilFunc.isEmpty(userKind)) {
				saveJSONError("用户类别(user_kind)不能为空");
				return NONE;
			}

			PagerHelper.initPagination(getRequest());
			DataGridHelper.render(getRequest(), getResponse(),
					PagerHelper.getPaginatedList(userDAO.queryUserList(
							userKind, getAllParameters())));
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Query org list.
	 * 
	 * @return the string
	 */
	public String queryOrgList() {
		try {
			String userKind = getParameter("user_kind");
			if (UtilFunc.isEmpty(userKind)) {
				saveJSONError("用户类别(user_kind)不能为空");
				return NONE;
			}

			PagerHelper.initPagination(getRequest());
			DataGridHelper.render(getRequest(), getResponse(),
					PagerHelper.getPaginatedList(userDAO.queryOrgList(userKind,
							getAllParameters())));
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Gets the user kind.
	 * 
	 * @return the user kind
	 */
	public String getUserKind() {
		return userKind;
	}

	/**
	 * Sets the user kind.
	 * 
	 * @param userKind
	 *            the new user kind
	 */
	public void setUserKind(String userKind) {
		this.userKind = userKind;
	}

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 * 
	 * @param userName
	 *            the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the login user.
	 * 
	 * @return the login user
	 */
	public String getLoginUser() {
		return loginUser;
	}

	/**
	 * Sets the login user.
	 * 
	 * @param loginUser
	 *            the new login user
	 */
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 * 
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the role list.
	 * 
	 * @return the role list
	 */
	public List getRoleList() {
		return roleList;
	}

	/**
	 * Sets the role list.
	 * 
	 * @param roleList the new role list
	 */
	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}
}