/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAO;

/**
 * 部门DAO接口.
 */
public interface DeptDAO extends BaseDAO {
	/** DAO对应表名. */
	public static final String TABLE_NAME = "SYS_DEPT";

	/**
	 * 获取部门树.
	 *
	 * @return the Dept tree
	 */
	public List getDeptTree();
	
	/**
	 * 获取部门信息 grid
	 *
	 * @return the Dept 
	 */
	public List getDeptGrid(String sval);

	/**
	 * 获取部门.
	 * 
	 * @param DeptId
	 *            the Dept id
	 * @return the Dept
	 */
	public Map getDeptInfo(String DeptId);

	/**
	 * 是否存在子部门.
	 * 
	 * @param DeptId
	 *            the Dept id
	 * @return true, if successful
	 */
	public boolean hasChildDept(String DeptId);

	/**
	 * 检查部门是否嵌套.
	 * 
	 * @param deptId
	 *            the dept id
	 * @param deptUpId
	 *            the dept up id
	 * @return true, if successful
	 */
	public boolean checkDeptNesting(String deptId, String deptUpId);

	/**
	 * 插入部门.
	 * 
	 * @param deptMap
	 *            the dept map
	 * @return the int
	 */
	public int insertDept(Map deptMap);

	/**
	 * 更新部门.
	 * 
	 * @param deptMap
	 *            the dept map
	 * @return the int
	 */
	public int updateDept(Map deptMap);

	/**
	 * 删除部门.
	 * 
	 * @param deptId
	 *            the dept id
	 * @return the int
	 */
	public int deleteDept(String deptId);

	/**
	 * 更新角色通过部门编号.
	 * 
	 * @param deptId
	 *            the dept id
	 * @param roles
	 *            the roles
	 * @return the int
	 */
	public int updateRolesByDeptId(String deptId, String roles);

	/**
	 * 更新用户通过部门编号.
	 * 
	 * @param deptId
	 *            the dept id
	 * @param users
	 *            the users
	 * @return the int
	 */
	public int updateUsersByDeptId(String deptId, String users);
	
	/**
	 * 是否存在用户.
	 * 
	 * @param deptId
	 *            the Dept id
	 * @return true, if successful
	 */
	public boolean hasUser(String deptId);
	
	/**
	 * 是否存在角色.
	 *
	 * @param deptId the dept id
	 * @return true, if successful
	 */
	public boolean hasRole(String deptId);
	
	
	/**
	 * 获取部门uuid.
	 *
	 * @param deptId the dept id
	 * @return the dept uuid
	 */
	public String getDeptUUID(String deptId);
	
	/**
	 * 获取上级部门信息
	 *
	 * @param list
	 * @return 回写 list map key-dept_up_name
	 */
	public void getDeptUpInfo(List<Map<String,Object>> list);
}
