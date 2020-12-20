/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.sys.user.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powersi.hygeia.framework.BaseServiceImpl;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.sys.manager.dao.DeptDAO;
import com.powersi.sys.user.dao.StaffDAO;
import com.powersi.sys.user.dto.SearchUserDTO;

/**
 * The Class StaffServiceImpl.
 */
public class StaffServiceImpl extends BaseServiceImpl implements StaffService {
	/** The staff dao. */
	public static StaffDAO dao = getBean(StaffDAO.class);
	
	public static DeptDAO deptDAO = getBean(DeptDAO.class);

	/* (non-Javadoc)
	 * @see com.powersi.sys.user.service.StaffService#checkLoginUserRepeate(java.lang.String, java.lang.String)
	 */
	public boolean checkLoginUserRepeate(String loginUser, String staffId) {
		return dao.checkLoginUserRepeate(loginUser, staffId);
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.user.service.StaffService#insertUser(java.util.Map)
	 */
	public int insertUser(Map userInfo) {
		return dao.insertUser(userInfo);
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.user.service.StaffService#updateUser(java.util.Map)
	 */
	public int updateUser(Map userInfo) {
		return dao.updateUser(userInfo);
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.user.service.StaffService#deleteUser(java.lang.String)
	 */
	public int deleteUser(String staffId) {
		return dao.deleteUser(staffId);
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.user.service.StaffService#findUser(java.lang.String)
	 */
	public Map<String, Object> findUser(String staffId) {
		return dao.findUser(staffId);
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.user.service.StaffService#queryUserList()
	 */
	public List<Map<String, Object>> queryUserList() {
		return dao.queryUserList();
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.user.service.StaffService#getUserKind()
	 */
	public String getUserKind() {
		return dao.getUserKind();
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.user.service.StaffService#getOrgTree()
	 */
	public List getOrgTree() {
		return dao.getOrgTree();
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.user.service.StaffService#getUserOrgList(java.lang.String)
	 */
	public List getUserOrgList(String staffId) {
		return dao.getUserOrgList(staffId);
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.user.service.StaffService#deleteUserOrg(java.lang.String, java.lang.String)
	 */
	public int deleteUserOrg(String staffId, String orgList) {
		return dao.deleteUserOrg(staffId, orgList);
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.user.service.StaffService#insertUserOrg(java.util.List)
	 */
	public int insertUserOrg(List data) {
		return dao.insertUserOrg(data);
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.user.service.StaffService#getUserListByDeptId(java.lang.String)
	 */
	public List<Map<String, Object>> getUserListByDeptId(String deptId) {
		return dao.getUserListByDeptId(deptId);
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.user.service.StaffService#getUserList(com.powersi.sys.user.dto.SearchUserDTO)
	 */
	public List<Map<String, Object>> getUserList(SearchUserDTO searchUserDto) {
		return dao.getUserList(searchUserDto);
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.user.service.StaffService#getUserTreeByDept()
	 */
	public List getUserTreeByDept() {
		return dao.getUserTreeByDept();
	}
	
	/* (non-Javadoc)
	 * @see com.powersi.sys.user.service.StaffService#getUserTreeByDept()
	 */
	public List getUserGridByDept(String sval,String valid_flag) {
		
		List<Map<String,Object>> list = dao.getUserGridByDept(sval,valid_flag);
		deptDAO.getDeptUpInfo(list);
		
		return list;
	}
}
