package com.powersi.ssm.biz.medicare.common.dao;

import org.springframework.stereotype.Service;

import com.powersi.hygeia.framework.util.DBHelper;

/**
 * 
 * @author 刘勇
 *
 */
@Service
public class EstablishDepartmentDAOImpl implements EstablishDepartmentDAO {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param dept_id
	 * @param dept_name
	 */
	@Override
	public void createDept(String dept_id, String dept_name) {
		StringBuilder sql = new StringBuilder();
		sql.append("    insert into sys_dept    \n");
		sql.append("      (dept_id, \n");
		sql.append("       dept_name,   \n");
		sql.append("       dept_up_id,  \n");
		sql.append("       dept_type,   \n");
		sql.append("       short_name,  \n");
		sql.append("       dis_order,   \n");
		sql.append("       center_id,   \n");
		sql.append("       address, \n");
		sql.append("       principal,   \n");
		sql.append("       linkman, \n");
		sql.append("       tel, \n");
		sql.append("       dept_uuid,   \n");
		sql.append("       dept_up_uuid,    \n");
		sql.append("       last_date,   \n");
		sql.append("       valid_flag,  \n");
		sql.append("       sync_date,   \n");
		sql.append("       unified_flag,\n");
		sql.append("       dept_fid)    \n");
		sql.append("    values  \n");
		sql.append("      (?,    \n");
		sql.append("       ?, \n");
		sql.append("       1030,    \n");
		sql.append("       '2', \n");
		sql.append("       null,    \n");
		sql.append("       ?,   \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       '1', \n");
		sql.append("       null,    \n");
		sql.append("       '0', \n");
		sql.append("       ?) \n");
		DBHelper.update(sql.toString(),
				new Object[] { new Integer(dept_id), dept_name, new Integer(dept_id), "-0-1030-" + dept_id + "-" });
	}

	/**
	 * 
	 * @param staff_id
	 * @param login_user
	 * @param staff_name
	 * @param center_id
	 * @param dept_id
	 */
	@Override
	public void createStaff(String staff_id, String login_user, String staff_name, String center_id, String dept_id) {
		StringBuilder sql = new StringBuilder();
		sql.append("    insert into sys_staff   \n");
		sql.append("      (staff_id,    \n");
		sql.append("       login_user,  \n");
		sql.append("       staff_name,  \n");
		sql.append("       staff_sta,   \n");
		sql.append("       grade_id,    \n");
		sql.append("       staff_type,  \n");
		sql.append("       staff_level, \n");
		sql.append("       center_id,   \n");
		sql.append("       dept_id, \n");
		sql.append("       dis_order,   \n");
		sql.append("       sex, \n");
		sql.append("       idcardno,    \n");
		sql.append("       birthdate,   \n");
		sql.append("       nationality, \n");
		sql.append("       position,    \n");
		sql.append("       position_desc,   \n");
		sql.append("       address, \n");
		sql.append("       postal_code, \n");
		sql.append("       mobile_phone,    \n");
		sql.append("       home_phone,  \n");
		sql.append("       office_phone,    \n");
		sql.append("       fax, \n");
		sql.append("       email,   \n");
		sql.append("       qq,  \n");
		sql.append("       msn, \n");
		sql.append("       remark,  \n");
		sql.append("       is_developer,    \n");
		sql.append("       staff_uuid,  \n");
		sql.append("       last_date,   \n");
		sql.append("       sync_date,   \n");
		sql.append("       dept_list,   \n");
		sql.append("       unified_flag,    \n");
		sql.append("       staff_role)  \n");
		sql.append("    values  \n");
		sql.append("      (?,    \n");
		sql.append("       ?,  \n");
		sql.append("       ?,  \n");
		sql.append("       '1', \n");
		sql.append("       1,   \n");
		sql.append("       2,   \n");
		sql.append("       3,   \n");
		sql.append("       ?,    \n");
		sql.append("       ?,    \n");
		sql.append("       ?,   \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       '0', \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       '0', \n");
		sql.append("       '4')    \n");
		DBHelper.update(sql.toString(), new Object[] { new Integer(staff_id), login_user, staff_name, center_id,
				new Integer(dept_id), new Integer(staff_id) });
	}

	/**
	 * 
	 * @param user_id
	 * @param login_user
	 * @param user_name
	 */
	@Override
	public void urbanRole(String user_id, String login_user, String user_name,String role_id) {
		StringBuilder sql = new StringBuilder();
		sql.append("    insert into sys_user_role   \n");
		sql.append("      (user_id, user_kind, role_id, login_user, user_name)  \n");
		sql.append("    values  \n");
		sql.append("      (?, 9, ?, ?, ?)   \n");
		DBHelper.update(sql.toString(), new Object[] { new Integer(user_id),new Integer(role_id), login_user, user_name });
	}

	/**
	 * 
	 * @param user_id
	 * @param user_name
	 * @param login_user
	 */
	@Override
	public void createSysUser(String user_id, String user_name, String login_user) {
		StringBuilder sql = new StringBuilder();
		sql.append("    insert into sys_user    \n");
		sql.append("      (user_id, \n");
		sql.append("       user_kind,   \n");
		sql.append("       user_name,   \n");
		sql.append("       password,    \n");
		sql.append("       login_user,  \n");
		sql.append("       create_time, \n");
		sql.append("       last_logintime,  \n");
		sql.append("       last_logouttime, \n");
		sql.append("       lock_state,  \n");
		sql.append("       lock_time,   \n");
		sql.append("       lock_reason, \n");
		sql.append("       password_errorcount, \n");
		sql.append("       message_querytime,   \n");
		sql.append("       message_lasttime)    \n");
		sql.append("    values  \n");
		sql.append("      (?,    \n");
		sql.append("       9,   \n");
		sql.append("       ?,  \n");
		sql.append("       '21218CCA77804D2BA1922C33E0151105',  \n");
		sql.append("       ?,  \n");
		sql.append("       str_to_date('20161109 08:09:30', '%Y%m%d %h:%i:%s'), \n");
		sql.append("       str_to_date('20161109 08:09:30', '%Y%m%d %h:%i:%s'), \n");
		sql.append("       str_to_date('20161109 08:09:30', '%Y%m%d %h:%i:%s'), \n");
		sql.append("       '0', \n");
		sql.append("       null,    \n");
		sql.append("       null,    \n");
		sql.append("       0,   \n");
		sql.append("       str_to_date('20161109 08:09:30', '%Y%m%d %h:%i:%s'), \n");
		sql.append("       null)   \n");
		DBHelper.update(sql.toString(), new Object[] { new Integer(user_id), user_name, login_user });
	}

}
