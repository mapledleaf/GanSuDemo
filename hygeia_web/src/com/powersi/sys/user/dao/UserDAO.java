/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.user.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.powersi.hygeia.framework.BaseDAO;
import com.powersi.hygeia.framework.entity.SysUserKind;
import com.powersi.sys.user.dto.PwdRetrieveDTO;

/**
 * 用户DAO接口.
 */
public interface UserDAO extends BaseDAO {

	/**
	 * 通过用户类型和登录名获取用户.
	 * 
	 * @param userKind
	 *            用户类型对象
	 * @param loginUser
	 *            登录名
	 * @return 用户对象
	 */
	public Map getUserByLogin(SysUserKind userKind, String loginUser);

	/**
	 * 通过用户类型和登录名查找用户.
	 * 
	 * @param userKind
	 *            用户类型对象
	 * @param userId
	 *            用户编号
	 * @return 用户对象
	 */
	public Map findUser(SysUserKind userKind, String userId);

	/**
	 * 确认用户是否存在.
	 *
	 * @param user the user
	 */
	public void confirmUserExist(Map user);
	
	/**
	 * 获取角色菜单.
	 * 
	 * @param roleSet
	 *            the role set
	 * @return the role menus
	 */
	public List getRoleMenus(Set roleSet);

	/**
	 * 获取角色菜单.
	 * 
	 * @param roleId
	 *            the role id
	 * @return the role menus
	 */
	public List getRoleMenus(String roleId);

	/**
	 * 更新密码次数.
	 * 
	 * @param user
	 *            用户
	 * @param pwdFlag
	 *            密码成功标志
	 */
	public void updatePasswordErrorCount(Map user, boolean pwdFlag);

	/**
	 * 修改用户密码.
	 * 
	 * @param user
	 *            用户
	 * @param pwd
	 *            密码
	 */
	public void changePassword(Map user, String pwd);

	/**
	 * 锁定用户.
	 * 
	 * @param user
	 *            用户
	 * @param reason
	 *            锁定原因
	 */
	public void lockUser(Map user, String reason);

	/**
	 * 解锁用户.
	 * 
	 * @param user
	 *            用户
	 */
	public void unlockUser(Map user);

	/**
	 * 重置密码.
	 * 
	 * @param user
	 *            用户
	 */
	public void resetPassword(Map user);

	/**
	 * 清除密码错误次数.
	 * 
	 * @param user
	 *            the user
	 */
	public void clearPasswordError(Map user);

	/**
	 * 通过条件查询用户列表.
	 * 
	 * @param userKind
	 *            the user kind
	 * @param loginUser
	 *            用户类型
	 * @param userName
	 *            用户名称
	 * @return 用户列表
	 */
	public List<Map> queryUserList(SysUserKind userKind, String loginUser,
			String userName);

	/**
	 * 通过身份证检查个人用户.
	 * 
	 * @param idcard
	 *            the idcard
	 * @return the map
	 */
	public Map queryPersonUser(String idcard);

	/**
	 * 获取人员密保问题.
	 * 
	 * @param personId
	 *            the person id
	 * @return the retrieve question
	 */
	public PwdRetrieveDTO getRetrieveQuestion(Long personId);

	/**
	 * 保存个人密保.
	 * 
	 * @param dto
	 *            the dto
	 */
	public void saveRetrieveQuestion(PwdRetrieveDTO dto);

	/**
	 * 更新个人密保.
	 * 
	 * @param dto
	 *            the dto
	 */
	public void updateRetrieveQuestion(PwdRetrieveDTO dto);

	/**
	 * 查询单位用户缴费.
	 * 
	 * @param corpId
	 *            the corp id
	 * @return the map
	 */
	public Map queryCorpUserPay(String corpId);
	
	/**
	 * 获取用户配置信息.
	 *
	 * @return the config
	 */
	public Map getConfig();


	/**
	 * 获取用户配置信息.
	 * 
	 * @param code
	 *            the code
	 * @return the config
	 */
	public String getConfig(String code);

	/**
	 * 获取用户配置信息.
	 * 
	 * @param code
	 *            the code
	 * @param defaultValue
	 *            the default value
	 * @return the config
	 */
	public String getConfig(String code, String defaultValue);

	/**
	 * 保存用户配置信息.
	 * 
	 * @param code
	 *            the code
	 * @param value
	 *            the value
	 * @return the int
	 */
	public int saveConfig(String code, String value);

	/**
	 * 查询用户列表.
	 * 
	 * @param userKind
	 *            the user kind
	 * @param params
	 *            the params
	 * @return the list
	 */
	public List queryUserList(String userKind, Map params);

	/**
	 * 查询机构列表.
	 * 
	 * @param userKind
	 *            the user kind
	 * @param params
	 *            the params
	 * @return the list
	 */
	public List queryOrgList(String userKind, Map params);

	/**
	 * 获取用户角色id列表.
	 * 
	 * @param userKind
	 *            the user kind
	 * @param userId
	 *            the user id
	 * @return the user role list
	 */
	public List getUserRoleIdList(String userKind, String userId);

	/**
	 * 获取用户机构列表.
	 * 
	 * @param userKind
	 *            the user kind
	 * @param userId
	 *            the user id
	 * @return the user org list
	 */
	public List getUserOrgList(String userKind, String userId);
	
	/**
	 * 获取用户信息.
	 *
	 * @param userKind the user kind
	 * @param userId the user id
	 * @return the user info
	 */
	public Map getUserInfo(String userKind, String userId);
	
	 /**
 	 * 检查登录名重复.
 	 *
 	 * @param userKind the user kind
 	 * @param loginUser the login user
 	 * @param userId the user id
 	 * @return true, if successful
 	 */
	public boolean checkLoginUserRepeate(String userKind, String loginUser, String userId);
	
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
     * @param userKind the user kind
     * @param userId the user id
     * @return true, if successful
     */
    public int deleteUser(String userKind, String userId);
    
    /**
     * 删除用户组织.
     *
     * @param userKind the user kind
     * @param userId the user id
     * @return the int
     */
    public int deleteUserOrg(String userKind, String userId);
       
    /**
     * 插入用户组织.
     *
     * @param data the data
     * @return the int
     */
    public int insertUserOrg(List data);
    
    
    /**
     * 查询用户类别.
     *
     * @param param the param
     * @return the list
     */
    public List queryUserKind(Map param);
    
    
    /**
     * 保存用户类别.
     *
     * @param param the param
     * @return the int
     */
    public int saveUserKind(Map param);
    
    /**
     * 保存用户类别.
     *
     * @param data the data
     * @return the int
     */
    public int insertUserKind(List data);
    
    /**
     * 更新用户类别.
     *
     * @param data the data
     * @return the int
     */
    public int updateUserKind(List data);
  
    /**
     * 用户类别主键.
     *
     * @return the sets the
     */
    public Set<String> keysUserKind();
    
    
    /**
     * 查询通讯录.
     *
     * @param param the param
     * @return the list
     */
    public List queryAddressList(Map param);
}
