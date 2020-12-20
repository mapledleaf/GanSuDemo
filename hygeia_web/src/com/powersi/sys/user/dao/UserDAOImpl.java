/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.sys.user.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.entity.SysUserKind;
import com.powersi.hygeia.framework.sql.PreparedSql;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.util.TreeSerializer;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.user.dto.PwdRetrieveDTO;

/**
 * 用户DAO接口实现.
 */
public class UserDAOImpl extends BaseDAOImpl implements UserDAO {
	
	//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	  boolean checkRight = !ParameterMapping.getStringByCode("check_conn_mysql", "0").equals("0");
	  
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.login.dao.LoginDAO#getRoleMenus(java.lang.String)
	 */
	public List getRoleMenus(String roleId) {
		if (isEmpty(roleId)) {
			return null;
		}

		List lst = UtilFunc.tokenizeToList(roleId, ",");
		if (isEmpty(lst)) {
			return null;
		}

		Set roleSet = new HashSet();
		for (int i = 0; i < lst.size(); i++) {
			roleSet.add(Integer.parseInt(lst.get(i).toString()));
		}

		return getRoleMenus(roleSet);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.login.dao.LoginDAO#getRoleMenus(java.lang.String)
	 */
	public List getRoleMenus(Set roleSet) {
		if (isEmpty(roleSet)) {
			return null;
		}

		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		List menus = new ArrayList();
	    if(checkRight){
	    	menus = dbTemplate(List.class, "mysql_getRoleMenus", "role_id", roleSet);
	    }else {
	    	menus = dbTemplate(List.class, "getRoleMenus", "role_id", roleSet);
	    }
		TreeSerializer ts = new TreeSerializer(menus);
		ts.setRootValue("0");
		ts.setNameOfId("menu_id");
		ts.setNameOfParentId("menu_up_id");
		return ts.toList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.user.dao.UserDAO#updatePasswordErrorCount(java.util
	 * .Map, boolean)
	 */
	public void updatePasswordErrorCount(Map user, boolean pwdFlag) {
		confirmUserExist(user);

		dbTemplate("updatePasswordErrorCount", "user_id", user.get("user_id"),
				"user_kind", user.get("user_kind"), "pwd_flag", pwdFlag);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sips.sys.user.dao.UserDAO#changePassword(java.util.Map,
	 * java.lang.String)
	 */
	public void changePassword(Map user, String pwd) {
		confirmUserExist(user);

		dbTemplate("changePassword", pwd, user.get("user_id"),
				user.get("user_kind"));
	}

	/**
	 * Confirm user exist.
	 * 
	 * @param user
	 *            the user
	 */
	public void confirmUserExist(Map user) {
		if (dbTemplate(Integer.class, "confirmUserExist", user.get("user_id"),
				user.get("user_kind")) == 0) {
			dbTemplate("insertUser", user);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sips.sys.user.dao.UserDAO#lockUser(java.util.Map,
	 * java.lang.String)
	 */
	public void lockUser(Map user, String reason) {
		confirmUserExist(user);

		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
	    	dbTemplate("mysql_lockUser", reason, user.get("user_id"),
					user.get("user_kind"));
	    }else {
	    	dbTemplate("lockUser", reason, user.get("user_id"),
					user.get("user_kind"));
	    }
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sips.sys.user.dao.UserDAO#unlockUser(java.util.Map)
	 */
	public void unlockUser(Map user) {
		confirmUserExist(user);

		dbTemplate("unlockUser", user.get("user_id"), user.get("user_kind"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sips.sys.user.dao.UserDAO#resetPassword(java.util.Map)
	 */
	public void resetPassword(Map user) {
		confirmUserExist(user);

		dbTemplate("resetPassword", user.get("user_id"), user.get("user_kind"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#clearPasswordError(java.util.Map)
	 */
	public void clearPasswordError(Map user) {
		confirmUserExist(user);

		dbTemplate("clearPasswordError", user.get("user_id"),
				user.get("user_kind"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.user.dao.UserDAO#getUserByLogin(com.powersi.sys.user.
	 * entity.SysUserKind, java.lang.String)
	 */
	public Map getUserByLogin(SysUserKind userKind, String loginUser) {
		PreparedSql ps = sqlPrepared("getUserByLoginUser");
		ps.setSql(userKind.getLoginSql());
		return dbFind(SqlHelper.parseSql(ps, "login_user", loginUser),
				Map.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.user.dao.UserDAO#findUser(com.powersi.sys.user.entity
	 * .SysUserKind, java.lang.String)
	 */
	public Map findUser(SysUserKind userKind, String userId) {
		PreparedSql ps = sqlPrepared("getUserByUserId");
		ps.setSql(userKind.getFindSql());
		return dbFind(SqlHelper.parseSql(ps, "user_id", Long.valueOf(userId)),
				Map.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.user.dao.UserDAO#queryUserList(com.powersi.sys.user.entity
	 * .SysUserKind, java.lang.String, java.lang.String)
	 */
	public List queryUserList(SysUserKind userKind, String loginUser,
			String userName) {
		Map paramMap = new HashMap();
		paramMap.putAll(getUserInfo());
		
		String param = UtilFunc.trimSqlParam(loginUser);
		if (UtilFunc.hasLength(param)) {
			paramMap.put("login_user", "%" + param + "%");
		} else {
			paramMap.put("login_user", "");
		}

		param = UtilFunc.trimSqlParam(userName);
		if (UtilFunc.hasLength(param)) {
			paramMap.put("user_name", "%" + param + "%");
		} else {
			paramMap.put("user_name", "");
		}

		if("100".equals(paramMap.get("user_id").toString())) {
			paramMap.put("akb020", "");
		}
		PreparedSql ps = sqlPrepared("queryUserList");
		if (userKind.getUserKind().intValue() == 9) {
			StringBuilder sql = new StringBuilder();
			sql.append(userKind.getQuerySql());
			//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
			if(checkRight){
				sql.append(sqlTemplate("mysql_filterQueryUser", paramMap));
			}else {
				sql.append(sqlTemplate("filterQueryUser", paramMap));
			}
			ps.setSql(sql.toString());
		} else {
			ps.setSql(userKind.getQuerySql());
		}
		return dbQuery(SqlHelper.parseSql(ps, paramMap));
	}

	/**
	 * 通过身份证检查个人用户.
	 * 
	 * @param idcard
	 *            the idcard
	 * @return the map
	 */
	public Map queryPersonUser(String idcard) {
		return dbTemplate(Map.class, "queryPersonUser", idcard);
	}

	/**
	 * 获取人员密保问题.
	 * 
	 * @param personId
	 *            人员id
	 * @return the retrieve question
	 */
	public PwdRetrieveDTO getRetrieveQuestion(Long personId) {
		return dbTemplate(PwdRetrieveDTO.class, "getRetrieveQuestion", personId);
	}

	/**
	 * 保存个人密保.
	 * 
	 * @param dto
	 *            the dto
	 */
	public void saveRetrieveQuestion(PwdRetrieveDTO dto) {
		Map paramMap = new HashMap();

		paramMap.put("person_id", dto.getPerson_id());
		paramMap.put("question_1", dto.getQuestion_1());
		paramMap.put("question_2", dto.getQuestion_2());
		paramMap.put("question_3", dto.getQuestion_3());
		paramMap.put("answer_1", dto.getAnswer_1());
		paramMap.put("answer_2", dto.getAnswer_2());
		paramMap.put("answer_3", dto.getAnswer_3());
		paramMap.put("idcard", dto.getIdcard());

		dbTemplate("saveRetrieveQuestion", paramMap);
	}

	/**
	 * 更新个人密保.
	 * 
	 * @param dto
	 *            the dto
	 */
	public void updateRetrieveQuestion(PwdRetrieveDTO dto) {
		Map paramMap = new HashMap();

		paramMap.put("person_id", dto.getPerson_id());
		paramMap.put("question_1", dto.getQuestion_1());
		paramMap.put("question_2", dto.getQuestion_2());
		paramMap.put("question_3", dto.getQuestion_3());
		paramMap.put("answer_1", dto.getAnswer_1());
		paramMap.put("answer_2", dto.getAnswer_2());
		paramMap.put("answer_3", dto.getAnswer_3());
		paramMap.put("idcard", dto.getIdcard());

		dbTemplate("updateRetrieveQuestion", paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#queryCorpUserPay(java.lang.String)
	 */
	public Map queryCorpUserPay(String corpId) {
		return dbTemplate(Map.class, "queryCorpUserPay", corpId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#getConfig()
	 */
	public Map getConfig() {
		List<Object[]> lst = dbTemplate(List.class, "getConfig", getUserInfo()
				.getUserId(), getUserInfo().getUserKind());
		Map map = new HashMap();
		for (Object[] obj : lst) {
			map.put(obj[0].toString(), obj[1] == null ? "" : obj[1].toString());
		}
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#getConfig(java.lang.String)
	 */
	public String getConfig(String code) {
		return getConfig(code, "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#getConfig(java.lang.String,
	 * java.lang.String)
	 */
	public String getConfig(String code, String defaultValue) {
		String obj = dbTemplate(String.class, "findConfig", getUserInfo()
				.getUserId(), getUserInfo().getUserKind(), code);
		return obj == null ? defaultValue : obj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#saveConfig(java.lang.String,
	 * java.lang.String)
	 */
	public int saveConfig(String code, String value) {
		Map param = new HashMap();
		param.put("user_id", getUserInfo().getUserId());
		param.put("user_kind", getUserInfo().getUserKind());
		param.put("config_code", code);
		param.put("config_value", value);

		return dbTemplate(Integer.class, "saveConfig", param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#queryUserList(java.lang.String,
	 * java.util.Map)
	 */
	public List queryUserList(String userKind, Map map) {
		Map params = new HashMap(map);
		map.put("user_kind", userKind);

		return dbTemplate(List.class, "queryUserInfoList", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#queryOrgList(java.lang.String,
	 * java.util.Map)
	 */
	public List queryOrgList(String userKind, Map map) {
		map.put("user_kind", userKind);
		return dbTemplate(List.class, "queryOrgList", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#getUserRoleIdList(java.lang.String,
	 * java.lang.String)
	 */
	public List getUserRoleIdList(String userKind, String userId) {
		return dbTemplate(List.class, "getUserRoleIdList", userKind, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#getUserOrgList(java.lang.String,
	 * java.lang.String)
	 */
	public List getUserOrgList(String userKind, String userId) {
		return dbTemplate(List.class, "getUserOrgList", userKind, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#getUserInfo(java.lang.String,
	 * java.lang.String)
	 */
	public Map getUserInfo(String userKind, String userId) {
		return dbTemplate(Map.class, "getUserInfo", userKind, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.user.dao.UserDAO#checkLoginUserRepeate(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public boolean checkLoginUserRepeate(String userKind, String loginUser,
			String userId) {
		return dbTemplate(Integer.class, "getUserInfo", userKind, loginUser,
				userId) > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#insertUser(java.util.Map)
	 */
	public int insertUser(Map userInfo) {
		return dbTemplate(Integer.class, "insertUserInfo", userInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#updateUser(java.util.Map)
	 */
	public int updateUser(Map userInfo) {
		return dbTemplate(Integer.class, "updateUserInfo", userInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#deleteUser(java.lang.String,
	 * java.lang.String)
	 */
	public int deleteUser(String userKind, String userId) {
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			return dbTemplate(Integer.class, "mysql_deleteUserInfo", userKind, userId);
		}else {
			return dbTemplate(Integer.class, "deleteUserInfo", userKind, userId);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#deleteUserOrg(java.lang.String,
	 * java.lang.String)
	 */
	public int deleteUserOrg(String userKind, String userId) {
		return dbTemplate(Integer.class, "deleteUserOrg", userKind, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#insertUserOrg(java.util.List)
	 */
	public int insertUserOrg(List data) {
		return dbTemplate(Integer.class, "insertUserOrg", data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#queryUserKind(java.util.Map)
	 */
	public List queryUserKind(Map param) {
		
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
	    	return dbTemplate(List.class, "mysql_queryUserKind", param);
	    }else {
	    	return dbTemplate(List.class, "queryUserKind", param);
	    }
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#saveUserKind(java.util.List)
	 */
	public int saveUserKind(Map param) {
		return dbTemplate(Integer.class, "saveUserKind", param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#keysUserKind()
	 */
	public Set<String> keysUserKind() {
		Set<String> set = new HashSet<String>();
		List<Object> lst = (List<Object>) SqlHelper.executeTemplate("keysUserKind");
		for (Object kind : lst) {
			set.add(kind.toString());
		}
		
		return set;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#insertUserKind(java.util.List)
	 */
	public int insertUserKind(List data) {
		if (CollectionHelper.isEmpty(data)) {
			return 0;
		}

		return dbTemplate(Integer.class, "insertUserKind", data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#updateUserKind(java.util.List)
	 */
	public int updateUserKind(List data) {
		if (CollectionHelper.isEmpty(data)) {
			return 0;
		}

		return dbTemplate(Integer.class, "saveUserKind", data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.user.dao.UserDAO#queryAddressList(java.util.Map)
	 */
	public List queryAddressList(Map param) {
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
	    	return dbTemplate(List.class, "mysql_queryAddressList", param);
	    }else{
	    	return dbTemplate(List.class, "queryAddressList", param);
	    }
	}
}