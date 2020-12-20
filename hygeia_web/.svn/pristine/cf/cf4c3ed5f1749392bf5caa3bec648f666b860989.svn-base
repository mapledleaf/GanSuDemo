/*
 * Copyright 2012 Powersi. All rights reserved.
 */

package com.powersi.sys.manager.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.biz.medicare.hosp.pojo.ExpertInfoDTO;
import com.powersi.biz.medicare.hosp.service.api.ExpertManagerService;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.sys.manager.dao.DeptDAO;
import com.powersi.sys.manager.dao.RoleDAO;
import com.powersi.sys.user.dao.StaffDAO;
import com.powersi.sys.util.DBPaginatedList;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;
import com.powersi.sys.util.SingleSignOnHelper;
import com.powersi.sys.util.UnifiedPlatformHelper;

/**
 * 部门管理Action.
 */
@Action(value = "DeptManager", results = {
		@Result(name = "success", location = "/pages/sys/manager/DeptManager.jsp"),
		@Result(name = "dept_grid", location = "/pages/sys/manager/DeptManager_grid.jsp")
})
public class DeptManagerAction extends BaseAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dept dao. */
	public static DeptDAO deptDAO = BeanHelper.getBean(DeptDAO.class);

	/** The role dao. */
	public static RoleDAO roleDAO = BeanHelper.getBean(RoleDAO.class);

	/** The staff dao. */
	public static StaffDAO staffDAO = BeanHelper.getBean(StaffDAO.class);

	//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	boolean checkRight = !ParameterMapping.getStringByCode("check_conn_mysql", "0").equals("0");
		
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		try {
			// 同步统一门户的部门和用户
			if (SingleSignOnHelper.isUsed() && SingleSignOnHelper.isEnabled()) {
				SingleSignOnHelper.syncAllDept();
				SingleSignOnHelper.syncAllUser();
			}
			return SUCCESS;
		} catch (Exception ex) {
			saveError(ex);
			return ERROR;
		}
	}
	
	/**
	 * 进入grid显示部门信息 界面
	 * @return
	 * @throws Exception
	 * String
	 */
	public String showDeptGrid() throws Exception {
		try {
			// 同步统一门户的部门和用户
			if (SingleSignOnHelper.isUsed() && SingleSignOnHelper.isEnabled()) {
				SingleSignOnHelper.syncAllDept();
				SingleSignOnHelper.syncAllUser();
			}
			return "dept_grid";
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
	public String getDeptTree() {
		try {
			List lst = deptDAO.getDeptTree();
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
	public String getDeptGrid() {
		try {
			
			PagerHelper.initPagination(getRequest());
			
			String sval = getParameter("sval");
			List deptList = deptDAO.getDeptGrid(sval);
			
			DataGridHelper.render(getRequest(), getResponse(),
					PagerHelper.getPaginatedList(deptList));
		} catch (Exception ex) {
			saveJSONError(ex);
		}
		
		return NONE;
	}
	
	@SuppressWarnings("unchecked")
	public String loadDeptInfo() {
		try {
			PagerHelper.initPagination(getRequest());
			
			String sval = getParameter("sval");
			List deptList = deptDAO.getDeptGrid(sval);
			
			DBPaginatedList paginatedList = PagerHelper.getPaginatedList(deptList);
			Map map = new HashMap();
			map.put("paginatedList", paginatedList);
			map.put("deptList", deptList);
			setJSONReturn(map);
			
		} catch (Exception ex) {
			saveJSONError(ex);
		}
		return NONE;
	}

	/**
	 * Gets the dept.
	 * 
	 * @return the dept
	 */
	public String getDeptInfo() {
		try {
			String deptId = getParameter("dept_id");
			if (!UtilFunc.hasText(deptId)) {
				throw new HygeiaException("部门编号不能为空");
			}

			Map map = deptDAO.getDeptInfo(deptId);
			if (map == null) {
				map = new HashMap();
				map.put("dept_id", deptId);
			}

			// 获取角色信息
			map.put("roles", roleDAO.getRoleListByDeptId(deptId));
			// 获取用户信息
			map.put("users", staffDAO.getUserListByDeptId(deptId));

			setJSONReturn(map);
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Save dept.
	 * 
	 * @return the string
	 */
	public String saveDept() {
		try {
			Map map = new HashMap();
			map.putAll(getAllParameters());

			String deptId = UtilFunc.getString(map, "dept_id");
			String deptUpId = UtilFunc.getString(map, "dept_up_id");

			// 校验
			validateSaveDept(map);

			if (deptId.equals("-1")) {
				long maxId = SysFunc.getMaxNo("dept_id");
				if (maxId < 100) {// 预留100个部门
					SysFunc.setMaxNo("DEPT_ID", 100);
					maxId = 100;
				}
				deptId = String.valueOf(maxId);
				map.put("dept_id", deptId);

				// 统一门户处理
				if (UnifiedPlatformHelper.isServerFlag()) {
					map.put("dept_uuid", UtilFunc.getUUID());
					map.put("unified_flag", "1");

					map.put("last_date", DBHelper.getDBTimestamp());
					map.put("dept_up_uuid", deptDAO.getDeptUUID(deptUpId));
				}

				//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
			    if(checkRight){
			    	//lingang 增加menu_fid字段
			    	String dept_fid = "-0-";
			    	if(!"0".equals(deptUpId)) {
			    		dept_fid = deptDAO.getDeptInfo(deptUpId).get("dept_fid").toString();
			    	}
			    	map.put("dept_fid", dept_fid+deptId+"-");
			    }
				
				deptDAO.insertDept(map);
			} else {
				if (deptDAO.checkDeptNesting(deptId, deptUpId)) {
					throw new HygeiaException("部门不能嵌套，请修改上级部门");
				}

				// 统一门户处理
				if (UnifiedPlatformHelper.isServerFlag()) {
					map.put("last_date", DBHelper.getDBTimestamp());
					map.put("dept_up_uuid", deptDAO.getDeptUUID(deptUpId));
				}
				//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
			    if(checkRight){
			    	//lingang 增加menu_fid字段
			    	String dept_fid = "-0-";
			    	if(!"0".equals(deptUpId)) {
			    		dept_fid = deptDAO.getDeptInfo(deptUpId).get("dept_fid").toString();
			    	}
			    	map.put("dept_fid", dept_fid+deptId+"-");
			    }
				
				deptDAO.updateDept(map);
			}

			Map retMap = new HashMap();
			retMap.put("dept_id", deptId);
			setJSONReturn(retMap);
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Del dept.
	 * 
	 * @return the string
	 */
	public String delDept() {
		try {
			Map map = new HashMap();
			map.putAll(getAllParameters());

			String deptId = UtilFunc.getString(map, "dept_id");
			if (!UtilFunc.hasText(deptId) || deptId.equals("0")) {
				throw new HygeiaException("部门编号不能为空");
			}

			// 非超级用户不能随意删除部门
			if (!getUserInfo().isSuperUser()) {
				if (deptDAO.hasChildDept(deptId)) {
					throw new HygeiaException("存在子部门，不能删除");
				}

				if (deptDAO.hasRole(deptId)) {
					throw new HygeiaException("该部门存在角色，不能删除");
				}

				if (deptDAO.hasUser(deptId)) {
					throw new HygeiaException("该部门存在用户，不能删除");
				}
			}

			deptDAO.deleteDept(deptId);

			Map retMap = new HashMap();
			retMap.put("dept_id", deptId);
			setJSONReturn(retMap);
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Batch modify dept.
	 * 
	 * @return the string
	 */
	public String batchModifyDept() {
		try {
			Map map = new HashMap();
			map.putAll(getAllParameters());

			String deptId = UtilFunc.getString(map, "dept_id");
			if (!UtilFunc.hasText(deptId)) {
				throw new HygeiaException("部门编号不能为空");
			}

			if ("0".equals(deptId)) {
				deptId = null;
			}

			if (!UtilFunc.isEmptyString(map.get("roles"))) {
				deptDAO.updateRolesByDeptId(deptId, map.get("roles").toString());
			}

			if (!UtilFunc.isEmptyString(map.get("users"))) {
				deptDAO.updateUsersByDeptId(deptId, map.get("users").toString());
			}

			saveJSONMessage("批量修改成功");
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}

	/**
	 * Validate save dept.
	 * 
	 * @param map
	 *            the map
	 */
	private void validateSaveDept(Map map) {
		String deptId = UtilFunc.getString(map, "dept_id");
		if (!UtilFunc.hasText(deptId) || deptId.equals("0")) {
			throw new HygeiaException("部门编号不能为空");
		}
		String deptUpId = UtilFunc.getString(map, "dept_up_id");
		if (!UtilFunc.hasText(deptUpId)) {
			throw new HygeiaException("上级部门编号不能为空");
		}
		if (deptId.equals(deptUpId)) {
			throw new HygeiaException("上级部门不能与部门编号相同");
		}
		if (!UtilFunc.hasText(UtilFunc.getString(map, "dept_name"))) {
			throw new HygeiaException("部门名称不能为空");
		}
		if (!UtilFunc.hasText(UtilFunc.getString(map, "dis_order"))) {
			throw new HygeiaException("显示序号不能为空");
		}

	}

	/**
	 * 统一门户同步.
	 * 
	 * @return the string
	 */
	public String syncUifiedPortal() {
		try {
			String typeName = "";
			String type = getParameter("type", "all");
			if ("dept".equals(type)) {
				typeName = "部门";
				SingleSignOnHelper.syncFullDept();
			} else if ("user".equals(type)) {
				typeName = "用户";
				SingleSignOnHelper.syncFullUser();
			} else {
				typeName = "全部";
				SingleSignOnHelper.syncFullAll();
			}

			saveJSONMessage("统一门户" + typeName + "同步成功");
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}
}