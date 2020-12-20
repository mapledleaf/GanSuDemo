/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.user.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.entity.SysUserKind;
import com.powersi.sys.user.dto.PwdRetrieveDTO;

/**
 * 用户服务接口.
 */
public interface UserService {

	/**
	 * 校验用户登录.
	 * 
	 * @param userInfo
	 *            the user info
	 */
	public void verifyLogin(Map userInfo);

	/**
	 * 修改用户密码.
	 * 
	 * @param oldPassword
	 *            原密码
	 * @param newPassword
	 *            新密码
	 */
	public void changePassword(String oldPassword, String newPassword);

	/**
	 * 处理人员信息增加.
	 * 
	 * @param newUserMap
	 *            the new user map
	 * @param newRoleList
	 *            the new role list
	 * @param newOrgs
	 *            the new orgs
	 * @param userInfo
	 *            the user info
	 */
	public void saveAddUserChangeInfo(Map<String, Object> newUserMap,
			List<String> newRoleList, List newOrgs, UserInfo userInfo);

	/**
	 * 处理人员信息的删除.
	 * 
	 * @param delUserMap
	 *            the del user map
	 * @param userInfo
	 *            the user info
	 */
	public void saveDelUserChangeInfo(Map<String, Object> delUserMap,
			UserInfo userInfo);

	/**
	 * 处理人 员信息变更.
	 * 
	 * @param newUserMap
	 *            the new user map
	 * @param newRoleList
	 *            the new role list
	 * @param newOrgs
	 *            the new orgs
	 * @param userInfo
	 *            the user info
	 */
	public void saveUpdateUserChangeInfo(Map<String, Object> newUserMap,
			List<String> newRoleList, List newOrgs, UserInfo userInfo);

	/**
	 * 初始化用户信息导入xls格式.
	 * 
	 * @param wb
	 *            the wb
	 * @param sheet
	 *            the sheet
	 * @param userinfo
	 *            the userinfo
	 */
	public void initExportUserInfoXls(HSSFWorkbook wb, HSSFSheet sheet,
			Map userinfo);

	/**
	 * 初始化用户信息list导出xls格式.
	 * 
	 * @param wb
	 *            the wb
	 * @param sheet
	 *            the sheet
	 * @param map
	 *            the map
	 * @param initFlag
	 *            初始化头标志
	 * @param userSeq
	 *            第几条用户信息
	 */
	public void initExportUserListXls(HSSFWorkbook wb, HSSFSheet sheet,
			Map map, boolean initFlag, int userSeq);

	/**
	 * 初始化用户权限信息导出xls格式.
	 * 
	 * @param wb
	 *            the wb
	 * @param sheet
	 *            the sheet
	 * @param map
	 *            the map
	 */
	public void initExportUserRightXls(HSSFWorkbook wb, HSSFSheet sheet, Map map);

	/**
	 * 初始化角色信息list导出xls格式.
	 * 
	 * @param wb
	 *            the wb
	 * @param sheet
	 *            the sheet
	 * @param map
	 *            the map
	 * @param initFlag
	 *            初始化头标志
	 * @param roleSeq
	 *            the role seq
	 */
	public void initExportRoleListXls(HSSFWorkbook wb, HSSFSheet sheet,
			Map map, boolean initFlag, int roleSeq);

	/**
	 * 初始化角色权限信息导出xls格式.
	 * 
	 * @param wb
	 *            the wb
	 * @param sheet
	 *            the sheet
	 * @param map
	 *            the map
	 */
	public void initExportRoleRightXls(HSSFWorkbook wb, HSSFSheet sheet, Map map);

	/**
	 * 获取部门树.
	 * 
	 * @return the dept tree
	 */
	public Map<String, String> getDeptTree();

	/**
	 * 是否支持书签.
	 * 
	 * @return true, if successful
	 */
	public boolean supportBookmark();

	/**
	 * 获取书签.
	 * 
	 * @return the int
	 */
	public String getBookmark();

	/**
	 * 保存书签.
	 * 
	 * @param bookmark
	 *            the bookmark
	 * @return the int
	 */
	public int saveBookmark(String bookmark);

	/**
	 * 查询用户信息.
	 * 
	 * @return the map
	 */
	public Map queryUserInfo();

	/**
	 * 更新用户信息.
	 * 
	 * @param params
	 *            用户信息
	 * @return the int
	 */
	public int updateUserInfo(Map params);
	
	/**
	 * 获取配置信息.
	 *
	 * @return the config
	 */
	public Map getConfig();
	
	/**
	 * 获取配置项.
	 *
	 * @param code the code
	 * @param defaultValue the default value
	 * @return the config
	 */
	public String getConfig(String code, String defaultValue);
	
	/**
	 * 保存配置信息.
	 *
	 * @param code the code
	 * @param value the value
	 * @return the string
	 */
	public int saveConfig(String code, String value);
	
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
	 * 查询单位用户缴费.
	 * 
	 * @param corpId
	 *            the corp id
	 * @return the map
	 */
	public Map queryCorpUserPay(String corpId);
	
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
	 * 通过身份证检查个人用户.
	 * 
	 * @param idcard
	 *            the idcard
	 * @return the map
	 */
	public Map queryPersonUser(String idcard);
	
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
	 * 根据当前用户所属部门id获取用户列表
	 * @return
	 */
	public List<Map<String,Object>> queryUserInfosByCurrentDeptId();
}
