/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.sys.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.sys.user.dto.SearchUserDTO;

/**
 * The Class StaffDAOImpl.
 */
public class StaffDAOImpl extends BaseDAOImpl implements StaffDAO {
	
	//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	boolean checkRight = !ParameterMapping.getStringByCode("check_conn_mysql", "0").equals("0");
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.user.dao.StaffDAO#checkLoginUserRepeate(java.lang.String,
	 * java.lang.String)
	 */
	public boolean checkLoginUserRepeate(String loginUser, String userId) {
		return dbTemplate(Integer.class, "checkLoginUserRepeate", loginUser, new Long(userId)) > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.StaffDAO#insertUser(java.util.Map)
	 */
	public int insertUser(Map userInfo) {
		return dbTemplate(Integer.class, "insertUser", userInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.StaffDAO#updateUser(java.util.Map)
	 */
	public int updateUser(Map userInfo) {
		return dbTemplate(Integer.class, "updateUser", userInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.StaffDAO#deleteUser(java.lang.String)
	 */
	public int deleteUser(String userId) {
		dbTemplate("insertStaffHis", userId);

		return dbTemplate(Integer.class, "deleteStaff", userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.StaffDAO#queryUserList()
	 */
	public List<Map<String, Object>> queryUserList() {
		UserInfo user = getUserInfo();
		if(user == null || !user.isValidUser()){
			return null;
		}

		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
	    	return dbTemplate(List.class, "mysql_queryUserList", user);
	    }else {
	    	return dbTemplate(List.class, "queryUserList", user);
	    }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.StaffDAO#findUser(java.lang.String)
	 */
	public Map<String, Object> findUser(String staffId) {
		return dbTemplate(Map.class, "findUser", staffId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.StaffDAO#getUserKind()
	 */
	public String getUserKind() {
		return "9";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.StaffDAO#getOrgTree()
	 */
	public List getOrgTree() {
		UserInfo user = getUserInfo();
		if(user == null || !user.isValidUser()){
			return null;
		}
		
		return dbTemplate(List.class, "getOrgTree", user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.StaffDAO#getUserOrgList(java.lang.String)
	 */
	public List getUserOrgList(String staffId) {
		return dbTemplate(List.class, "getUserOrgList", staffId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.StaffDAO#deleteUserOrg(java.lang.String)
	 */
	public int deleteUserOrg(String staffId, String orgList) {
		if (isEmpty(orgList)) {
			return 0;
		}
		
		return dbTemplate(Integer.class, "deleteUserOrg", "staff_id", staffId, "org_list", orgList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.StaffDAO#insertUserOrg(java.util.List)
	 */
	public int insertUserOrg(List data) {
		return dbTemplate(Integer.class, "insertUserOrg", data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.user.dao.StaffDAO#getUserListByDeptId(java.lang.String)
	 */
	public List<Map<String, Object>> getUserListByDeptId(String deptId) {
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
	    	return dbTemplate(List.class, "mysql_getUserListByDeptId", "dept_id", deptId);
	    }else {
	    	return dbTemplate(List.class, "getUserListByDeptId", "dept_id", deptId);
	    }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.user.dao.StaffDAO#getUserList(com.powersi.sys.user.entity
	 * .SearchUserDto)
	 */
	public List<Map<String, Object>> getUserList(SearchUserDTO searchUserDto) {
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
	    	return dbTemplate(List.class, "mysql_getUserList", searchUserDto);
	    }else {
	    	return dbTemplate(List.class, "getUserList", searchUserDto);
	    }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.StaffDAO#getUserTreeByDept()
	 */
	public List<Map<String, Object>> getUserTreeByDept() {
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
	    	return dbTemplate(List.class, "mysql_getUserTreeByDept");
	    }else {
	    	return dbTemplate(List.class, "getUserTreeByDept");
	    }
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.StaffDAO#getUserGridByDept()
	 */
	public List<Map<String, Object>> getUserGridByDept(String sval,String valid_flag) {
		/*Map map = new HashMap();
		map.put("sval", sval);
		map.put("valid_flag", valid_flag);*/
		return dbTemplate(List.class, "mysql_getUserGridByDept","sval", sval,
				"valid_flag", valid_flag);
	}

}