/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAO;
import com.powersi.sys.user.dto.SearchUserDTO;

/**
 * 角色DAO接口.
 */
public interface RoleDAO extends BaseDAO {

	/**
	 * 获取角色信息.
	 * 
	 * @param roleId
	 *            the role id
	 * @return the menu
	 */
	public Map getRoleInfo(String roleId);

	/**
	 * 获取所有角色列表.
	 * 
	 * @return the menu
	 */
	public List<Map<String, Object>> getAllRoleList();

	/**
	 * 获取角色菜单.
	 * 
	 * @param roleId
	 *            the role id
	 * @return the menu
	 */
	public List getRoleMenu(String roleId);
	
	/**
	 * 获取角色用户.
	 * 
	 * @param roleId
	 *            the role id
	 * @return the menu
	 */
	public List getRoleUsers(String roleId);

	/**
	 * 获取角色授权.
	 * 
	 * @param roleId
	 *            the role id
	 * @return the role grant
	 */
	public List getRoleGrant(String roleId);

	/**
	 * 获取菜单角色.
	 * 
	 * @param menuId
	 *            the menu id
	 * @return the role
	 */
	public List getMenuRole(String menuId);

	/**
	 * 插入角色菜单.
	 * 
	 * @param roleId
	 *            the role id
	 * @param menuList
	 *            the menu list
	 * @return the int
	 */
	public int insertRoleMenu(String roleId, List menuList);

	/**
	 * 删除角色菜单.
	 * 
	 * @param roleId
	 *            the role id
	 * @return the int
	 */
	public int deleteRoleMenu(String roleId);

	/**
	 * 插入角色信息.
	 * 
	 * @param roleInfo
	 *            the role info
	 * @return the int
	 */
	public int insertRole(Map roleInfo);

	/**
	 * 更新角色信息.
	 * 
	 * @param roleInfo
	 *            the role info
	 * @return the int
	 */
	public int updateRole(Map roleInfo);

	/**
	 * 删除角色信息.
	 * 
	 * @param roleId
	 *            the role id
	 * @return the int
	 */
	public int deleteRole(String roleId);
	
	/**
	 * 删除角色授权.
	 * 
	 * @param roleId
	 *            the role id
	 * @return the int
	 */
	public int deleteRoleGrant(String roleId);
	
	/**
	 * 保存角色授权.
	 *
	 * @param roles the roles
	 * @return the int
	 */
	public int insertRoleGrant(List roles);

	/**
	 * 获取用户角色列表.
	 * 
	 * @param userId
	 *            the user id
	 * @param userKind
	 *            the user kind
	 * @return the role list
	 */
	public List<Map<String, Object>> getUserRoleList(String userId, String userKind);

	/**
	 * 删除用户角色.
	 * 
	 * @param userId
	 *            the user id
	 * @param userKind
	 *            the user kind
	 * @param roleList
	 *            the role list
	 * @return the role list
	 */
	int deleteUserRole(String userId, String userKind, String roleList);
	
	/**
	 * 删除用户角色.
	 *
	 * @param userId the user id
	 * @param userKind the user kind
	 * @return the role list
	 */
	int deleteUserRole(String userId, String userKind);

	/**
	 * 插入用户角色.
	 * 
	 * @param data
	 *            the data
	 * @return the role list
	 */
	public int insertUserRole(List data);

	/**
	 * 获取角色列表.
	 * 
	 * @return the role list
	 */
	public List<Map<String, Object>> getRoleList();

	/**
	 * 删除角色菜单.
	 * 
	 * @param menuId
	 *            the menu id
	 * @return the int
	 */
	public int deleteRoleMenuByMenu(String menuId);

	/**
	 * 插入角色菜单.
	 *
	 * @param menuId the menu id
	 * @param roleList the role list
	 * @return the int
	 */
	public int insertRoleMenuByMenu(String menuId, List roleList);
	
	/**
	 * 获取角色列表根据部门编号.
	 *
	 * @param deptId the dept id
	 * @return the role by dept id
	 */
	public List<Map<String, Object>> getRoleListByDeptId(String deptId);
	
	/**
	 * 根据部门获取角色树.
	 *
	 * @return the role tree by dept id
	 */
	public List<Map<String, Object>> getRoleTreeByDeptId();
	
	/**
	 * 根据部门获取角色grid数据.
	 *
	 * @return the role grid by dept id
	 */
	public List<Map<String, Object>> getRoleGridByDeptId(String sval);
	
	/**
	 * 搜索角色.
	 *
	 * @param searchUserDto the search user dto
	 * @return the list
	 */
	public List searchRoleListBySearchDto(SearchUserDTO searchUserDto);
	
	/**
	 * 删除用户角色.
	 *
	 * @param roleId the role id
	 * @param userKind the user kind
	 * @param userList the users
	 * @return the int
	 */
	public int deleteRoleUser(String roleId, String userKind, String userList);
	
	
	/**
	 * Gets the role list by user kind.
	 *
	 * @param userKind the user kind
	 * @return the role list by user kind
	 */
	public List getRoleListByUserKind(String userKind);
}
