/**
 * Copyright 2013 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.user.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.sys.manager.dao.DeptDAO;
import com.powersi.sys.manager.dao.RoleDAO;
import com.powersi.sys.user.service.StaffService;
import com.powersi.sys.user.service.UserService;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.GradeHelper;
import com.powersi.sys.util.PagerHelper;
import com.powersi.sys.util.PasswordHelper;
import com.powersi.sys.util.SingleSignOnHelper;
import com.powersi.sys.util.UnifiedPlatformHelper;

/**
 * The Class StaffManagerAction.
 */
@Action(value = "StaffManager", results = {
		@Result(name = "success", location = "/pages/sys/user/StaffManager.jsp"),
		@Result(name = "staff_grid", location = "/pages/sys/user/StaffManager_grid.jsp")
})
public class StaffManagerAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	public static RoleDAO roleDAO = getBean(RoleDAO.class);
	public static DeptDAO deptDAO = getBean(DeptDAO.class);

	public static UserService userService = BeanHelper
			.getBean(UserService.class);
	
	public static StaffService staffService = getBean(StaffService.class);

	@Override
	public String execute() throws Exception {
		try {
			// 同步统一门户的部门和用户
			if (SingleSignOnHelper.isUsed() && SingleSignOnHelper.isEnabled()) {
				SingleSignOnHelper.syncAllDept();
				SingleSignOnHelper.syncAllUser();
			}

			// 用户基本信息树
			setAttribute("staffTree", toJson(staffService.getUserTreeByDept()));
			// 操作级别
			setAttribute("gradeList", GradeHelper.valueMap(getUserInfo()));
			// 统筹区树
			// setAttribute("orgTree", toJson(staffDAO.getOrgTree()));
			// 角色列表
			setAttribute("roleList", roleDAO.getRoleList());
			// 部门列表
			setAttribute("deptList", getDeptTree());
			return SUCCESS;
		} catch (Exception ex) {
			return renderError(ex);
		}
	}
	
	/**
	 * 进入grid显示用户信息 界面
	 * @return
	 * @throws Exception
	 * String
	 */
	public String showStaffGrid() throws Exception {
		try {
			// 同步统一门户的部门和用户
			if (SingleSignOnHelper.isUsed() && SingleSignOnHelper.isEnabled()) {
				SingleSignOnHelper.syncAllDept();
				SingleSignOnHelper.syncAllUser();
			}
			
			// 用户基本信息树
			//setAttribute("staffTree", toJson(staffService.getUserTreeByDept()));
			// 操作级别
			setAttribute("gradeList", GradeHelper.valueMap(getUserInfo()));
			// 统筹区树
			// setAttribute("orgTree", toJson(staffDAO.getOrgTree()));
			// 角色列表
			setAttribute("roleList", roleDAO.getRoleList());
			// 部门列表
			setAttribute("deptList", getDeptTree());
			
			return "staff_grid";
		} catch (Exception ex) {
			saveError(ex);
			return ERROR;
		}
	}
	
	/**
	 * Gets the Staff .
	 * 
	 * @return the Staff 
	 */
	public String getStaffGrid() {
		try {
			
			PagerHelper.initPagination(getRequest());
			
			String sval = getParameter("sval");
			String valid_flag = getParameter("valid_flag");
			List staffList = staffService.getUserGridByDept(sval, valid_flag);
			
			DataGridHelper.render(this.getRequest(), this.getResponse(),
					PagerHelper.getPaginatedList(staffList));
		} catch (Exception ex) {
			saveJSONError(ex);
		}
		
		return NONE;
	}
	
	/**
	 * 获取操作员详细信息
	 * 
	 * @return
	 */
	@Action("GetStaffInfo")
	public String getInfo() {
		try {
			String userId = getParameter("staff_id");
			if (isEmpty(userId)) {
				throw new HygeiaException("用户编号不能为空");
			}

			// 获取用户信息
			Map map = staffService.findUser(userId);

			// 获取角色信息
			String roles = UtilFunc.joinList(
					roleDAO.getUserRoleList(userId, staffService.getUserKind()),
					"role_id", ",");
			if (isNotEmpty(roles)) {
				map.put("roles", roles);
			}

			// 获取组织信息
			String orgs = UtilFunc.joinList(staffService.getUserOrgList(userId),
					"org_id", ",");
			if (isNotEmpty(orgs)) {
				map.put("orgs", orgs);
			}

			return renderJson(map);
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * 保存用户信息
	 * 
	 * @return
	 */
	@Actions({
		@Action("AddStaff"),
		@Action("ModifyStaff")
	})
	public String saveUser() {
		try {
			String userId = getParameter("staff_id");
			String loginUser = getParameter("login_user").toLowerCase();
			// 校验
			validateSaveUser();

			Map map = new HashMap();
			map.putAll(getAllParameters());
			map.put("login_user", loginUser);
			map.remove("roles");
			map.remove("password");

			// 2016-09-09 lingang hygeia_web修改支持mysql数据库
			if (isEmpty(getParameter("staff_level"))) {
				map.put("staff_level", 0);
			}
			if (isEmpty(getParameter("dept_id")) || "null".equals(getParameter("dept_id")) ) {
				map.put("dept_id", 0);
			}
			if (isEmpty(getParameter("staff_type"))) {
				map.put("staff_type", 0);
			}
			
			// 角色参数
			String[] roles = getParameterValues("roles");
			String hisroles = getParameter("his_roles");
			// 组织参数
			String strHisOrgs = getParameter("his_orgs");
			String strOrgs = getParameter("orgs");
			List strOrgsList = handleOrgList(strOrgs, userId);

			// 处理用户信息
			String password = getParameter("password");
			if (userId.equals("-1")) {
				if (isEmpty(getParameter("password"))) {
					password = PasswordHelper.createPassword("888888");
				}

				long id = SysFunc.getMaxNo("STAFF_ID");
				if (id < 1000) {// 预留1000个用户
					SysFunc.setMaxNo("STAFF_ID", 1000);
					id = 1000;
				}

				userId = String.valueOf(id);
				map.put("staff_id", userId);

				//统一门户处理
				if(UnifiedPlatformHelper.isServerFlag()){
					map.put("staff_uuid", UtilFunc.getUUID());
					map.put("unified_flag", "1");
					
					map.put("last_date", DBHelper.getDBTimestamp());
					map.put("dept_list", map.get("dept_id"));
				}
				staffService.insertUser(map);
				userService
						.saveAddUserChangeInfo(map,
								UtilFunc.arrayToList(roles), strOrgsList,
								getUserInfo());
			} else {
				//统一门户处理
				if(UnifiedPlatformHelper.isServerFlag()){
					map.put("last_date", DBHelper.getDBTimestamp());
					map.put("dept_list", map.get("dept_id"));
				}
				
				userService
						.saveUpdateUserChangeInfo(map,
								UtilFunc.arrayToList(roles), strOrgsList,
								getUserInfo());
				staffService.updateUser(map);
			}

			Map user = new HashMap();
			user.put("user_id", map.get("staff_id"));
			user.put("user_kind", staffService.getUserKind());
			user.put("user_name", map.get("staff_name"));
			user.put("login_user", loginUser);

			// 处理密码
			if (UtilFunc.hasText(password)) {
				userService.changePassword(user, password);
			}

			// 处理角色
			roleDAO.deleteUserRole(userId, staffService.getUserKind(), hisroles);

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
			String delOrgs = handleHisOrgList(strHisOrgs);
			staffService.deleteUserOrg(userId, delOrgs);
			staffService.insertUserOrg(strOrgsList);

			return renderJson("保存用户信息成功", map);
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * 删除用户信息
	 * 
	 * @return the string
	 */
	@Action("DelStaff")
	public String deleteUser() {
		try {
			String userId = getParameter("staff_id");
			if (isEmpty(userId)) {
				throw new HygeiaException("用户编号不能为空");
			}

			if (userId.equals(getUserInfo().getUserId())) {
				throw new HygeiaException("不能删除当前用户");
			}

			Map delUserMap = staffService.findUser(userId);
			// 保存变更信息
			userService.saveDelUserChangeInfo(delUserMap, this.getUserInfo());
			staffService.deleteUser(userId);

			return renderMessage("删除用户成功");
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Gets the dept tree.
	 * 
	 * @return the dept tree
	 */
	public String getStaffTree() {
		try {
			// 用户基本信息树
			return renderJson(staffService.getUserTreeByDept());
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * 获取部门树
	 * 
	 * @return the dept tree
	 */
	private Map getDeptTree() {
		Map trees = new LinkedHashMap();
		List<Map> lst = deptDAO.getDeptTree();
		for (Map map : lst) {
			int level = Integer.parseInt(getString(map, "dept_level",
					"0"));
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < level; i++) {
				sb.append("　");// 使用全角空格
			}
			sb.append(map.get("dept_name"));
			trees.put(map.get("dept_id"), sb.toString());
		}
		return trees;
	}

	/**
	 * 校验保存的用户信息
	 */
	private void validateSaveUser() {
		String userId = getParameter("staff_id");
		if (isEmpty(userId)) {
			throw new HygeiaException("用户编号不能为空");
		}
		String loginUser = getParameter("login_user");
		if (isEmpty(getParameter("login_user"))) {
			throw new HygeiaException("登录名不能为空");
		}
		loginUser = loginUser.toLowerCase();
		if (isEmpty(getParameter("staff_name"))) {
			throw new HygeiaException("用户名不能为空");
		}
		if (staffService.checkLoginUserRepeate(loginUser, userId)) {
			throw new HygeiaException("登录名已存在");
		}
		UserInfo userInfo = getUserInfo();
		if (!userInfo.isSuperUser()) {
			if (getString(userInfo, "grade_id", "0").compareTo(
					getString(getAllParameters(), "grade_id", "0")) < 0) {
				throw new HygeiaException("用户等级过高");
			}
			String staffLevel = getString(getAllParameters(), "staff_level", "9");
			if(staffLevel.length() == 0){
				staffLevel = "9";
			}
			if (getString(userInfo, "staff_level", "9").compareTo(staffLevel) > 0) {
				throw new HygeiaException("操作级别过高");
			}
		}
	}

	/**
	 * 处理原有组织列表
	 * 
	 * @param strHisOrgs
	 * @return
	 */
	private String handleHisOrgList(String strHisOrgs) {
		String delOrgs = null;
		if (!UtilFunc.isEmpty(strHisOrgs)) {
			String[] hisOrgs = strHisOrgs.split(",");
			StringBuilder sb = new StringBuilder();
			for (String hisOrg : hisOrgs) {
				String[] s = hisOrg.split("_");
				if (s.length != 2) {
					throw new HygeiaException("组织代码必须是类型_编号");
				}
				sb.append(",");
				sb.append(s[1]);
			}
			delOrgs = sb.toString().substring(1);
		}
		return delOrgs;
	}

	/**
	 * 处理新增组织列表
	 * 
	 * @param strOrgs
	 * @param userId
	 * @return
	 */
	private List handleOrgList(String strOrgs, String userId) {
		List data = new ArrayList();
		if (isNotEmpty(strOrgs)) {
			String[] orgs = strOrgs.split(",");
			for (String org : orgs) {
				String[] s = org.split("_");
				if (s.length != 2) {
					throw new HygeiaException("组织代码必须是类型_编号");
				}

				Map row = new HashMap();
				row.put("staff_id", userId);
				row.put("org_type", s[0]);
				row.put("org_id", s[1]);

				data.add(row);
			}
		}
		return data;
	}
}
