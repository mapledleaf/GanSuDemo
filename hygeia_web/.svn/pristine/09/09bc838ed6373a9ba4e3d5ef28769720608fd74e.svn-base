/**
 * Copyright 2010 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.manager.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.sys.manager.dao.DeptDAO;
import com.powersi.sys.manager.dao.MenuDAO;
import com.powersi.sys.manager.dao.RoleDAO;
import com.powersi.sys.manager.service.MenuService;
import com.powersi.sys.util.MenuHelper;

/**
 * The Class RoleManagerAction.
 */
@Action(value = "RoleManager", results = {
		@Result(name = "success", location = "/pages/sys/manager/RoleManager.jsp")
})
public class RoleManagerAction extends BaseAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The role dao. */
	public static RoleDAO roleDAO = BeanHelper.getBean(RoleDAO.class);

	/** The menu dao. */
	public static MenuDAO menuDAO = BeanHelper.getBean(MenuDAO.class);

	/** The dept dao. */
	public static DeptDAO deptDAO = BeanHelper.getBean(DeptDAO.class);

	/** The menu service. */
	public static MenuService menuService = BeanHelper
			.getBean(MenuService.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		try {
			Map filterMap = new HashMap();
			filterMap.put("valid_flag", "1");

			List lst = menuDAO.getMenuTree("0", filterMap);
			setJSONAttribute("menutree", lst);

			List rolelist = roleDAO.getRoleTreeByDeptId();
			setJSONAttribute("roleTree", rolelist);

			// 可以被授权的角色列表
			setJSONAttribute("allGrantRoles", roleDAO.getAllRoleList());

			setAttribute("deptList", getDeptTree());
			return SUCCESS;
		} catch (Exception ex) {
			saveError(ex);
			return ERROR;
		}
	}

	/**
	 * Gets the dept tree.
	 * 
	 * @return the dept tree
	 */
	public String getAllRoleList() {
		try {
			List lst = roleDAO.getAllRoleList();
			setJSONReturn(lst);
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Gets the dept tree.
	 * 
	 * @return the dept tree
	 */
	private Map getDeptTree() {
		Map trees = new LinkedHashMap();
		List<Map> lst = deptDAO.getDeptTree();
		for (Map map : lst) {
			int level = Integer.parseInt(UtilFunc.getString(map, "dept_level",
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
	 * Gets the menu.
	 * 
	 * @return the menu
	 */
	@Action("GetRoleMenu")
	public String getRoleMenu() {
		try {
			String roleId = getParameter("role_id");
			if (!UtilFunc.hasText(roleId)) {
				throw new HygeiaException("角色编号不能为空");
			}

			Map map = new HashMap();
			map.put("role_menu", roleDAO.getRoleMenu(roleId));
			map.put("role_info", roleDAO.getRoleInfo(roleId));

			// 已经被授权的角色列表
			map.put("role_grant", roleDAO.getRoleGrant(roleId));
			map.put("role_user", roleDAO.getRoleUsers(roleId));

			// 可被授权的列表
			map.put("all_grant_role", roleDAO.getAllRoleList());

			setJSONReturn(map);
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Save menu.
	 * 
	 * @return the string
	 */
	@Action("SaveRoleMenu")
	public String saveRoleMenu() {
		try {
			String roleId = getParameter("role_id");
			if (!UtilFunc.hasText(roleId)) {
				throw new HygeiaException("角色编号不能为空");
			}
			String menuList = getParameter("menu_list");

			if (roleDAO.getRoleInfo(roleId) == null) {
				throw new HygeiaException("角色不存在");
			}

			// 插入用户权限变更信息
			List<String> newMenuIdList = UtilFunc.tokenizeToList(menuList, ",");
			menuService.saveChangeInfo(roleId, newMenuIdList,
					this.getUserInfo());

			roleDAO.deleteRoleMenu(roleId);
			roleDAO.insertRoleMenu(roleId,
					UtilFunc.tokenizeToList(menuList, ","));
			saveJSONMessage("保存角色菜单成功");

			// RefreshMappingUtil.refresh("menuright");
			MenuHelper.updateCache();
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Save role info.
	 * 
	 * @return the string
	 */
	@Action("SaveRoleInfo")
	public String saveRoleInfo() {
		try {
			String roleId = getParameter("role_id");
			validateSaveRole();

			Map map = new HashMap();
			map.putAll(getAllParameters());
			boolean creatFlag = false;

			if (roleId.equals("-1")) {
				creatFlag = true;
				long id = SysFunc.getMaxNo("ROLE_ID");
				if (id < 100) {// 预留100个角色
					SysFunc.setMaxNo("ROLE_ID", 100);
					id = 100;
				}

				roleId = String.valueOf(id);
				map.put("role_id", roleId);

				map.put("role_creator", getUserInfo().getUserId());
				if (UtilFunc.isEmpty((String) map.get("role_range"))) {
					map.put("role_range", "1");
				}
				roleDAO.insertRole(map);
			} else {
				roleDAO.updateRole(map);
			}

			String menuList = getParameter("menu_list");
			if (UtilFunc.hasText(menuList)) {
				roleDAO.deleteRoleMenu(roleId);
				roleDAO.insertRoleMenu(roleId,
						UtilFunc.tokenizeToList(menuList, ","));
			}
			roleDAO.deleteRoleGrant(roleId);
			String grantList = getParameter("role_grant");
			if (UtilFunc.hasText(grantList)) {
				List lst = new ArrayList();
				List grants = UtilFunc.tokenizeToList(grantList, ",");
				for (int i = 0; i < grants.size(); i++) {
					Map m = new HashMap();
					m.put("role_id", roleId);
					m.put("grant_role_id", grants.get(i));
					m.put("grant_option", "1");
					lst.add(m);
				}
				roleDAO.insertRoleGrant(lst);
			}

			// insert的时候，授权权限应该默认赋予创建的权限
			if (creatFlag) {
				List lst = new ArrayList();
				String loginRoleId = BusiContext.getUserInfo().getRoleId();
				;// 登录用户的roleid
				List loginRoleList = UtilFunc.tokenizeToList(loginRoleId, ",");
				Map m = null;
				for (int i = 0; i < loginRoleList.size(); i++) {
					m = new HashMap();
					m.put("role_id", loginRoleList.get(i));
					m.put("grant_role_id", roleId);
					m.put("grant_option", "1");
					lst.add(m);
				}
				roleDAO.insertRoleGrant(lst);
			}
			// 部门不为空时，带出部门名称
			if (!UtilFunc.isEmpty((String) map.get("dept_id"))) {
				String deptId = (String) map.get("dept_id");
				map.put("dept_name",
						deptDAO.getDeptInfo(deptId).get("dept_name"));
			}

			setJSONReturn("保存角色信息成功", map);

			// RefreshMappingUtil.refresh("menuright");
			MenuHelper.updateCache();
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Del role info.
	 * 
	 * @return the string
	 */
	@Action("DelRoleInfo")
	public String delRoleInfo() {
		try {
			String roleId = getParameter("role_id");
			if (!UtilFunc.hasText(roleId)) {
				throw new HygeiaException("角色编号不能为空");
			}
			long id = Long.parseLong(roleId);
			if (id == -1) {
				throw new HygeiaException("不能删除没有保存的角色信息");
			}
			if (id >= 0 && id < 100) {
				throw new HygeiaException("系统缺省角色不能删除");
			}

			roleDAO.deleteRole(roleId);

			Map ret = new HashMap();
			ret.put("role_id", roleId);
			ret.put("all_grant_role", roleDAO.getAllRoleList());

			setJSONReturn("删除角色成功", ret);

			// RefreshMappingUtil.refresh("menuright");
			MenuHelper.updateCache();
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Gets the dept tree.
	 * 
	 * @return the dept tree
	 */
	public String getRoleTree() {
		try {
			// 用户基本信息树
			setJSONReturn(roleDAO.getRoleTreeByDeptId());
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * 删除用户角色.
	 * 
	 * @return the string
	 */
	public String delRoleUser() {
		try {
			Map map = new HashMap();
			map.putAll(getAllParameters());

			String roleId = UtilFunc.getString(map, "role_id");
			if (!UtilFunc.hasText(roleId)) {
				throw new HygeiaException("角色编号不能为空");
			}

			if (!UtilFunc.isEmptyString(map.get("users"))) {
				roleDAO.deleteRoleUser(roleId, "9", map.get("users").toString());
			}

			saveJSONMessage("取消用户角色授权成功");
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Validate save role.
	 */
	private void validateSaveRole() {
		String roleId = getParameter("role_id");
		if (!UtilFunc.hasText(roleId)) {
			throw new HygeiaException("角色编号不能为空");
		}
//		long id = Long.valueOf(roleId);
//		if (id >= 0 && id < 100) {
//			throw new HygeiaException("系统缺省角色不能修改");
//		}

		if (!UtilFunc.hasText(getParameter("role_name"))) {
			throw new HygeiaException("角色名不能为空");
		}

		if (!UtilFunc.hasText(getParameter("dis_order"))) {
			throw new HygeiaException("显示序号不能为空");
		}
	}
}