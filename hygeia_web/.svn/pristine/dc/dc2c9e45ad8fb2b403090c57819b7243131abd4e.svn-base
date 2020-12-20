/**
 * Copyright 2011 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.user.dao;

import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAO;
import com.powersi.sys.user.dto.SearchUserDTO;

/**
 * The Interface StaffDAO.
 */
public interface StaffDAO extends BaseDAO {
	 /**
 	 * 检查登录名重复.
 	 *
 	 * @param loginUser the login user
 	 * @param staffId the user id
 	 * @return true, if successful
 	 */
	public boolean checkLoginUserRepeate(String loginUser, String staffId);

    /**
     * 插入用户信息.
     *
     * @param userInfo the user info
     * @return true, if successful
     */
    public int insertUser(Map userInfo);

    /**
     * 更新用户信息.
     *
     * @param userInfo the user info
     * @return true, if successful
     */
    public int updateUser(Map userInfo);

    /**
     * 删除用户信息.
     *
     * @param staffId the user id
     * @return true, if successful
     */
    public int deleteUser(String staffId);
    
    /**
     * 查找用户.
     *
     * @param staffId the staff id
     * @return 用户列表
     */
    public Map<String, Object> findUser(String staffId);
    
    /**
     * 查询用户用户列表.
     *
     * @return 用户列表
     */
    public List<Map<String, Object>> queryUserList();
      
    /**
     * 获取用户类型.
     *
     * @return the user kind
     */
    public String getUserKind();
    
    /**
     * 获取机构树.
     *
     * @return 机构树
     */
    public List getOrgTree();
    
    
    /**
     * 获取用户组织列表.
     *
     * @param staffId the staff id
     * @return 组织列表
     */
    public List getUserOrgList(String staffId);
    
    /**
     * 删除用户组织.
     *
     * @param staffId the staff id
     * @param orgList the org list
     * @return the int
     */
    public int deleteUserOrg(String staffId, String orgList);
       
    /**
     * 插入用户组织.
     *
     * @param data the data
     * @return the int
     */
    public int insertUserOrg(List data);
    
    /**
     * 获取用户列表根据部门编号.
     *
     * @param deptId the dept id
     * @return the user list by dept id
     */
	public List<Map<String, Object>> getUserListByDeptId(String deptId);
	
	/**
	 * 搜索用户列表
	 * @param searchUserDto
	 * @return
	 */
	public List<Map<String, Object>> getUserList(SearchUserDTO searchUserDto);
	
	/**
	 * 根据部门获取用户树
	 * @param deptId
	 * @return
	 */
	public List<Map<String, Object>> getUserTreeByDept();
	
	/**
	 * 根据部门获取用户 grid 数据
	 * @param deptId
	 * @return
	 */
	public List<Map<String, Object>> getUserGridByDept(String sval,String valid_flag);
}
