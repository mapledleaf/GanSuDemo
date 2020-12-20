package com.powersi.ssm.biz.medicare.common.dao;

import java.io.Serializable;

/**
 * 
 * @author 刘勇
 *
 */
public interface EstablishDepartmentDAO extends Serializable {

	/**
	 * 
	 * @param dept_id
	 * @param dept_name
	 */
	public void createDept(String dept_id, String dept_name);

	/**
	 * 
	 * @param staff_id
	 * @param login_user
	 * @param staff_name
	 * @param center_id
	 * @param dept_id
	 */
	public void createStaff(String staff_id, String login_user, String staff_name, String center_id, String dept_id);

	/**
	 * 
	 * @param user_id
	 * @param login_user
	 * @param user_name
	 */
	public void urbanRole(String user_id, String login_user, String user_name,String role_id);

	/**
	 * 
	 * @param user_id
	 * @param user_name
	 * @param login_user
	 */
	public void createSysUser(String user_id, String user_name, String login_user);

}
