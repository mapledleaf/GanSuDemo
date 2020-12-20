/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.sys.user.dto.SearchUserDTO;

/**
 * 角色DAO接口实现.
 */
public class RoleDAOImpl extends BaseDAOImpl implements RoleDAO {
	/** The table name. */
	private static String TABLE_NAME = "SYS_ROLES";

	/** The table kind. */
	private static String TABLE_USERKIND = "SYS_ROLE_USERKIND";
	
	//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	boolean checkRight = !ParameterMapping.getStringByCode("check_conn_mysql", "0").equals("0");
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.manager.dao.RoleDAO#deleteRoleMenu(java.lang.String)
	 */
	public int deleteRoleMenu(String roleId) {
		return DBHelper.executeUpdate(
				"delete from sys_role_menus where role_id = ?",
				new Object[] { new Long(roleId) });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sips.sys.manager.dao.RoleDAO#getRoleTree()
	 */
	public List<Map<String, Object>> getAllRoleList() {
		UserInfo user = getUserInfo();
		int gradeId = Integer
				.valueOf(UtilFunc.getString(user, "grade_id", "0"));
		
		StringBuilder sql = new StringBuilder();
		
		if (gradeId < 4 && !user.isSuperUser()) {
			 //是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
			if(checkRight) {
				sql.append(" select a.*, b.dept_name as dept_name,  CONCAT('（',IFNULL(b.dept_name, '无'), '）',a.role_name) AS role_text ");
				sql.append("   from  ");
				sql.append("        (SELECT dept_id  FROM sys_dept WHERE dept_fid LIKE ");
				sql.append("          CONCAT('%',"+UtilFunc.getString(user, "dept_id", "-1")+",'%') and valid_flag = '1' ) dept ,");
				sql.append("         sys_roles a left join sys_dept b on a.dept_id = b.dept_id ");
				sql.append("  where a.dept_id = dept.dept_id ");
				sql.append("  order by IFNULL(b.dis_order, -1000), b.dept_name, a.dis_order, a.role_name ");
			}else {
				sql.append(" select a.*, b.dept_name,  ('（' || IFNULL(b.dept_name, '无') || '）' || a.role_name) as role_text ");
				sql.append("   from sys_roles a, ");
				sql.append("        (select dept_id ");
				sql.append("           from sys_dept where valid_flag = '1'");
				sql.append("          start with dept_id = ").append(
						UtilFunc.getString(user, "dept_id", "-1"));
				sql.append("         connect by prior dept_id = dept_up_id) dept, sys_dept b ");
				sql.append("  where a.dept_id = dept.dept_id ");
				sql.append("  order by nvl(b.dis_order, -1000), b.dept_name, a.dis_order, a.role_name ");
			}
		} else {
			 //是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
			if(checkRight) {
				sql.append(" select a.*, b.dept_name as dept_name,  CONCAT('（',IFNULL(b.dept_name, '无'), '）',a.role_name) AS role_text ");
				sql.append("   from sys_roles a left join sys_dept b on a.dept_id = b.dept_id ");
				sql.append("  where '1' = b.valid_flag");
				sql.append("  order by IFNULL(b.dis_order, -1000), b.dept_name, a.dis_order, a.role_id ");
			}else {
				sql.append(" select a.*, b.dept_name as dept_name, ('（' || nvl(b.dept_name, '无') || '）' || a.role_name) as role_text ");
				sql.append("   from sys_roles a, sys_dept b");
				sql.append("  where a.dept_id = b.dept_id(+) and '1' = b.valid_flag(+)");
				sql.append("  order by nvl(b.dis_order, -1000), b.dept_name, a.dis_order, a.role_id ");
			}
		}

		return DBHelper.executeList(sql.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.manager.dao.RoleDAO#getRoleMenu(java.lang.String)
	 */
	public List getRoleMenu(String roleId) {
		return DBHelper.executeList(
				"select menu_id from sys_role_menus where role_id = ?",
				new Object[] { new Long(roleId) });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.RoleDAO#getMenuRole(java.lang.String)
	 */
	public List getMenuRole(String menuId) {
		return DBHelper.executeList(
				"select role_id from sys_role_menus where menu_id = ?",
				new Object[] { new Long(menuId) });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.manager.dao.RoleDAO#insertRoleMenu(java.lang.String,
	 * java.util.List)
	 */
	public int insertRoleMenu(String roleId, List menuList) {
		if (UtilFunc.isEmpty(menuList)) {
			return 0;
		}

		Long id = new Long(roleId);
		List paramList = new ArrayList();
		for (int i = 0; i < menuList.size(); i++) {
			List param = new ArrayList();
			param.add(id);
			param.add(menuList.get(i));

			paramList.add(param);
		}

		String sql = "insert into sys_role_menus(role_id, menu_id) values(?, ?)";
		DBHelper.executeBatch(sql, UtilFunc.toArrayOfArray(paramList));
		return menuList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.RoleDAO#getRoleInfo(java.lang.String)
	 */
	public Map getRoleInfo(String roleId) {
		Long id = Long.valueOf(roleId);
		Map roleInfo = DBHelper.executeMap(
				"select * from sys_roles where role_id = ? ",
				new Object[] { id });

		if (DBHelper.existTable(TABLE_USERKIND)) {
			List lst = DBHelper.executeScaleList(
					"select user_kind from sys_role_userkind where role_id = ?",
					new Object[] { id });
			roleInfo.put("role_userkind", lst);
		}

		return roleInfo;
	}

	/**
	 * Save role kind.
	 * 
	 * @param roleInfo
	 *            the role info
	 */
	private void saveRoleUserKind(Map roleInfo) {
		if (DBHelper.existTable(TABLE_USERKIND)) {
			String roleId = (String) roleInfo.get("role_id");
			String[] roleKind = (String[]) roleInfo.get("role_userkind");
			DAOHelper.delete(TABLE_USERKIND, roleInfo, new String[] { "role_id" });
			if (roleKind != null && roleKind.length > 0) {
				List lst = new ArrayList();
				for (String kind : roleKind) {
					Map map = new HashMap();
					map.put("role_id", roleId);
					map.put("user_kind", kind);
					lst.add(map);
				}
				DAOHelper.insert(TABLE_USERKIND, lst);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.RoleDAO#insertRole(java.util.Map)
	 */
	public int insertRole(Map roleInfo) {
		saveRoleUserKind(roleInfo);

		return DAOHelper.insert(TABLE_NAME, roleInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.RoleDAO#updateRole(java.util.Map)
	 */
	public int updateRole(Map roleInfo) {
		saveRoleUserKind(roleInfo);

		return DAOHelper.updateWithNotNull(TABLE_NAME, roleInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.RoleDAO#deleteRole(java.lang.String)
	 */
	public int deleteRole(String roleId) {
		int ret = 0;
		String tabs[] = new String[] { "sys_role_menus", "sys_user_role",
				"sys_roles", "sys_role_roles", "sys_role_userkind" };
		Object arg[] = new Object[] { new Long(roleId) };
		for (String tab : tabs) {
			if (DBHelper.existTable(tab)) {
				String sql = String.format(
						"DELETE FROM %1$S WHERE ROLE_ID = ?", tab);
				ret += DBHelper.executeUpdate(sql, arg);
			}
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.manager.dao.RoleDAO#deleteRoleMenuByMenu(java.lang.String
	 * )
	 */
	public int deleteRoleMenuByMenu(String menuId) {
		return DBHelper.update("delete from sys_role_menus where menu_id = ?",
				new Object[] { Long.valueOf(menuId) });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.manager.dao.RoleDAO#insertRoleMenuByMenu(java.lang.String
	 * , java.util.List)
	 */
	public int insertRoleMenuByMenu(String menuId, List roleList) {
		if (UtilFunc.isEmpty(roleList)) {
			return 0;
		}

		/*
		Long id = new Long(menuId);
		List paramList = new ArrayList();
		for (int i = 0; i < roleList.size(); i++) {
			List param = new ArrayList();
			param.add(id);
			param.add(roleList.get(i));

			paramList.add(param);
		}

		String sql = "insert into sys_role_menus(menu_id, role_id) values(?, ?)";
		DBHelper.executeBatch(sql, UtilFunc.toArrayOfArray(paramList));
		*/
		Long id = new Long(menuId);
		List paramList = new ArrayList();
		for (int i = 0; i < roleList.size(); i++) {
			Map param = new HashMap();
			param.put("menu_id", id);
			param.put("role_id", roleList.get(i));

			paramList.add(param);
		}
		
		StringBuilder sql = new StringBuilder();

		sql.append(" insert into sys_role_menus(role_id, menu_id) ");
		sql.append("   select role_id, menu_id ");
		sql.append("     from (select :role_id as role_id, menu_id as menu_id ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight) {
			sql.append("             from (select menu_id, menu_type ");
			sql.append("                     from sys_menu upmenu ");
			sql.append("                    where upmenu.menu_type = 1 ");
			sql.append("                    and upmenu.menu_fid like concat('%',:menu_id,'-') )tab1 ");
		}else {
			sql.append("             from (select menu_id, menu_type ");
			sql.append("                     from sys_menu upmenu ");
			sql.append("                    where upmenu.menu_type = 1 ");
			sql.append("                    start with upmenu.menu_id = :menu_id ");
			sql.append("                           and upmenu.menu_type = 1 ");
			sql.append("                   connect by prior upmenu.menu_up_id = upmenu.menu_id) ");
		}

		sql.append("            where menu_type = 1 ");
		sql.append("           union all ");
		sql.append("           select :role_id as role_id, m.menu_id as menu_id ");
		sql.append("             from sys_menu m ");
		sql.append("            where menu_id = :menu_id ");
		sql.append("              and menu_type <> 1) m ");
		sql.append("    where not exists (select 'x' ");
		sql.append("             from sys_role_menus r ");
		sql.append("            where m.menu_id = r.menu_id ");
		sql.append("              and r.role_id = :role_id) ");

		DBHelper.executeBatch(sql.toString(), paramList);
		return roleList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.manager.dao.RoleDAO#getUserRoleList(java.lang.String,
	 * java.lang.String)
	 */
	public List<Map<String, Object>> getUserRoleList(String userId,
			String userKind) {
		String sql = "select ur.*,role.role_name  from sys_user_role ur,sys_roles role where ur.role_id = role.role_id \n"
				+
				" and ur.user_id = ?\n" +
				"   and ur.user_kind = ?" +
				//2017-08-09 lingang 系统角色不能操作 
				 "  and role.role_creator != 'sys'";

		return DBHelper.executeList(sql, new Object[] { Long.valueOf(userId),
				Integer.valueOf(userKind) });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.RoleDAO#deleteUserRole(java.lang.String,
	 * java.lang.String)
	 */
	public int deleteUserRole(String userId, String userKind, String roleList) {
		if (!UtilFunc.hasText(roleList)) {
			return 0;
		}

		StringBuilder sql = new StringBuilder();

		sql.append(" delete from sys_user_role ");
		sql.append("  where user_id = ? ");
		sql.append("    and user_kind = ? ");
		sql.append("    and role_id in (").append(roleList).append(")");

		return DBHelper.executeUpdate(sql.toString(),
				new Object[] { Long.valueOf(userId),
						Integer.valueOf(userKind) });
	}
	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.RoleDAO#deleteUserRole(java.lang.String, java.lang.String)
	 */
	public int deleteUserRole(String userId, String userKind) {
		StringBuilder sql = new StringBuilder();

		sql.append(" delete from sys_user_role ");
		sql.append("  where user_id = ? ");
		sql.append("    and user_kind = ? ");

		return DBHelper.executeUpdate(sql.toString(),
				new Object[] { Long.valueOf(userId),
						Integer.valueOf(userKind) });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.RoleDAO#insertUserRole(java.util.List)
	 */
	public int insertUserRole(List data) {
		String sql = "insert into sys_user_role\n" +
				"  (user_id, user_kind, role_id, login_user, user_name)\n" +
				"values\n" +
				"  (:user_id, :user_kind, :role_id, :login_user, :user_name)";

		return DBHelper.executeBatch(sql, data).length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.RoleDAO#getRoleList()
	 */
	public List<Map<String, Object>> getRoleList() {
		UserInfo user = getUserInfo();
		String staffLevel = UtilFunc.getString(user, "staff_level", "9");
		String userId = user.getUserId();
		String userKind = user.getUserKind();
		String userDept = UtilFunc.getString(user, "dept_id", "-1");
		StringBuilder sql = new StringBuilder();

		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight) {
			sql.append(" select a.*, b.dept_name as dept_name, CONCAT('（',IFNULL(b.dept_name, '无'),'）', a.role_name) AS role_text ");
			sql.append("   from sys_roles a left join sys_dept b on a.dept_id = b.dept_id");
			sql.append("  where a.role_range = '1' ");
			sql.append("   and '1' = b.valid_flag ");
			//2017-08-09 lingang 关联用户移除不属于登陆用户部门下的角色
			if( "100".equals(userId) ) {
				//如果是系统管理员登陆 也不能操作系统管理员角色
				sql.append("    and a.role_creator != 'sys'  ");
			}else {
				sql.append("    and  b.dept_fid like concat('%',(select dept_id from sys_staff f where staff_id = "+userId+"),'%')  ");
			}
		}else {
			sql.append(" select a.*, b.dept_name as dept_name, ('（' || nvl(b.dept_name, '无') || '）' || a.role_name) as role_text ");
			sql.append("   from sys_roles a, sys_dept b");
			sql.append("  where a.role_range = '1' ");
			sql.append("   and a.dept_id = b.dept_id(+) and '1' = b.valid_flag(+) ");
		}

		//2017-08-11 lingang 用户管理显示可以操作的角色只需要关联部门就可以
		/*if (!"1".equals(staffLevel) && !user.isSuperUser()) {
			sql.append("    and a.role_id in (select role_id ");
			sql.append("                        from sys_user_role ");
			sql.append("                       where user_id = ").append(
					userId);
			sql.append("                         and user_kind = ").append(
					userKind);
			sql.append("                      union all ");
			sql.append("                      select grant_role_id as role_id ");
			sql.append("                        from sys_role_roles ");
			sql.append("                       where role_id in (select role_id ");
			sql.append("                                           from sys_user_role ");
			sql.append(
					"                                          where user_id = ")
					.append(userId);
			sql.append(
					"                                            and user_kind = ")
					.append(userKind).append(")) ");
		}*/

		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight) {
			sql.append("  order by IFNULL(b.dis_order, -1000), b.dept_name, a.dis_order, a.role_id ");
		}else {
			sql.append("  order by nvl(b.dis_order, -1000), b.dept_name, a.dis_order, a.role_id ");
		}

		return DBHelper.executeList(sql.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.RoleDAO#getRoleGrant(java.lang.String)
	 */
	public List getRoleGrant(String roleId) {
		return DBHelper.executeList(
				"select * from sys_role_roles where role_id = ? ",
				new Object[] { Long.valueOf(roleId) });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.manager.dao.RoleDAO#deleteRoleGrant(java.lang.String)
	 */
	public int deleteRoleGrant(String roleId) {
		return DBHelper.update("delete from sys_role_roles where role_id = ?",
				new Object[] { Long.valueOf(roleId) });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.RoleDAO#saveRoleGrant(java.util.List)
	 */
	public int insertRoleGrant(List roles) {
		return DAOHelper.insert("sys_role_roles", roles);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.manager.dao.RoleDAO#getRoleByDeptId(java.lang.String)
	 */
	public List<Map<String, Object>> getRoleListByDeptId(String deptId) {
		String sql = null;
		if ("0".equals(deptId)) {
			sql = "select a.*,(select dept_name from sys_dept where dept_id = a.dept_id) as dept_name  from sys_roles a where a.dept_id is null order by a.dis_order";
			return DBHelper.executeList(sql);
		} else {
			sql = "select a.*,(select dept_name from sys_dept where dept_id = a.dept_id) as dept_name  from sys_roles a where a.dept_id = ? order by a.dis_order";
			return DBHelper.executeList(sql, new Object[] { deptId });
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.RoleDAO#getRoleTreeByDeptId()
	 */
	public List<Map<String, Object>> getRoleTreeByDeptId() {
		StringBuilder querySQL = new StringBuilder();

		querySQL.append(" select * ");
		querySQL.append("   from (select dept.dept_id as dept_id, ");
		querySQL.append("                0 as role_id, ");
		querySQL.append("                dept.dept_name as dept_name, ");
		querySQL.append("                dept.short_name, ");
		querySQL.append("                dept.dept_up_id, ");
		querySQL.append("                dept.dis_order, ");
		querySQL.append("                dept.dept_fid, ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight) {
			querySQL.append("                cast(IFNULL(dept.dept_type, 999999)  as unsigned int) as dept_type, ");
		}else {
			querySQL.append("                to_number(dept.dept_type) as dept_type, ");
		}
		
		querySQL.append("                '0' as isrole ");
		querySQL.append("           from sys_dept dept where dept.valid_flag = '1'");
		querySQL.append("         union all ");
		querySQL.append("         select 999999 as dept_id, ");
		querySQL.append("                0 as role_id, ");
		querySQL.append("                '其他' as dept_name, ");
		querySQL.append("                '其他' as short_name, ");
		querySQL.append("                0 as dept_up_id, ");
		querySQL.append("                999999 as dis_order, ");
		querySQL.append("                '0-' as dept_fid, ");
		querySQL.append("                2 as dept_type, ");
		querySQL.append("                '0' as isrole ");
		querySQL.append("           from dual ");
		querySQL.append("         union all ");
		querySQL.append("         select (-1 - rol.role_id) as dept_id, ");
		querySQL.append("                rol.role_id, ");
		querySQL.append("                rol.role_name as dept_name, ");
		querySQL.append("                rol.role_name as short_name, ");
		
		UserInfo user = getUserInfo();
		int gradeId = Integer
				.valueOf(UtilFunc.getString(user, "grade_id", "0"));
		
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight) {
			querySQL.append("                cast(IFNULL(rol.dept_id, 999999)  as unsigned int) as dept_up_id, ");
			querySQL.append("                (999999 + rol.dis_order) as dis_order, ");
			querySQL.append(gradeId >= 4 || user.isSuperUser()?"'0-' as dept_fid, ":"dept.DEPT_FID AS dept_fid,");
			querySQL.append("                0 as dept_type, ");
			querySQL.append("                '1' as isrole ");
			querySQL.append("          FROM sys_roles rol,sys_dept dept WHERE rol.DEPT_ID = dept.DEPT_ID) dep_rol ");
		}else {
			querySQL.append("                to_number(IFNULL(rol.dept_id, 999999)) as dept_up_id, ");
			querySQL.append("                (999999 + rol.dis_order) as dis_order, ");
			querySQL.append(gradeId >= 4 || user.isSuperUser()?"'0-' as dept_fid, ":"concat('-',dept_id,'-') as dept_fid,");
			querySQL.append("                0 as dept_type, ");
			querySQL.append("                '1' as isrole ");
			querySQL.append("           from sys_roles rol) dep_rol ");
		}

		Long rootDeptId = null;
		
		
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight) {
			if (gradeId >= 4 || user.isSuperUser()) {
				rootDeptId = Long.valueOf(0);
				querySQL.append(" where dept_id != '"+rootDeptId+"' and  dep_rol.dept_fid like '%" + rootDeptId
						+ "%'");
			} else {
				rootDeptId = Long
						.valueOf(UtilFunc.getString(user, "dept_id", "-1"));
				querySQL.append(" where  dep_rol.dept_fid like '%" + rootDeptId
						+ "%'");
			}
			querySQL.append("        order by dep_rol.dept_up_id,dep_rol.dis_order\n")
			.toString();
		}else {
			if (gradeId >= 4 || user.isSuperUser()) {
				rootDeptId = Long.valueOf(0);
				querySQL.append("  start with dep_rol.dept_up_id = ' " + rootDeptId
						+ "'");
			} else {
				rootDeptId = Long
						.valueOf(UtilFunc.getString(user, "dept_id", "-1"));
				querySQL.append("  start with dep_rol.dept_id = ' " + rootDeptId
						+ "'");
			}
				querySQL.append(
			"          connect by prior dep_rol.dept_id = dep_rol.dept_up_id\n")
			.append("        order siblings by dep_rol.dept_up_id,dep_rol.dis_order\n")
			.toString();
		}
		
		return DBHelper.executeList(querySQL.toString());
		/*StringBuilder querySQL = new StringBuilder();

		querySQL.append(" select * ");
		querySQL.append("   from (select dept.dept_id as dept_id, ");
		querySQL.append("                0 as role_id, ");
		querySQL.append("                dept.dept_name as dept_name, ");
		querySQL.append("                dept.short_name, ");
		querySQL.append("                dept.dept_up_id, ");
		querySQL.append("                dept.dis_order, ");
		querySQL.append("                dept.dept_fid, ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight) {
			querySQL.append("                cast(IFNULL(dept.dept_type, 999999)  as unsigned int) as dept_type, ");
		}else {
			querySQL.append("                to_number(dept.dept_type) as dept_type, ");
		}
		
		querySQL.append("                '0' as isrole ");
		querySQL.append("           from sys_dept dept where dept.valid_flag = '1'");
		querySQL.append("         union all ");
		querySQL.append("         select 999999 as dept_id, ");
		querySQL.append("                0 as role_id, ");
		querySQL.append("                '其他' as dept_name, ");
		querySQL.append("                '其他' as short_name, ");
		querySQL.append("                0 as dept_up_id, ");
		querySQL.append("                999999 as dis_order, ");
		querySQL.append("                '0-' as dept_fid, ");
		querySQL.append("                2 as dept_type, ");
		querySQL.append("                '0' as isrole ");
		querySQL.append("           from dual ");
		querySQL.append("         union all ");
		querySQL.append("         select (-1 - rol.role_id) as dept_id, ");
		querySQL.append("                rol.role_id, ");
		querySQL.append("                rol.role_name as dept_name, ");
		querySQL.append("                rol.role_name as short_name, ");
		
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight) {
			querySQL.append("                cast(IFNULL(rol.dept_id, 999999)  as unsigned int) as dept_up_id, ");
		}else {
			querySQL.append("                to_number(IFNULL(rol.dept_id, 999999)) as dept_up_id, ");
		}
		
		querySQL.append("                (999999 + rol.dis_order) as dis_order, ");
		querySQL.append("                '0-' as dept_fid, ");
		querySQL.append("                0 as dept_type, ");
		querySQL.append("                '1' as isrole ");
		querySQL.append("           from sys_roles rol) dep_rol ");

		Long rootDeptId = null;
		UserInfo user = getUserInfo();
		int gradeId = Integer
				.valueOf(UtilFunc.getString(user, "grade_id", "0"));
		
		
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight) {
			if (gradeId >= 4 || user.isSuperUser()) {
				rootDeptId = Long.valueOf(0);
				querySQL.append(" where dept_id != '"+rootDeptId+"' and  dep_rol.dept_fid like '%" + rootDeptId
						+ "%'");
			} else {
				rootDeptId = Long
						.valueOf(UtilFunc.getString(user, "dept_id", "-1"));
				querySQL.append(" where  dep_rol.dept_fid like '%" + rootDeptId
						+ "%'");
			}
			querySQL.append("        order by dep_rol.dept_up_id,dep_rol.dis_order\n")
			.toString();
		}else {
			if (gradeId >= 4 || user.isSuperUser()) {
				rootDeptId = Long.valueOf(0);
				querySQL.append("  start with dep_rol.dept_up_id = ' " + rootDeptId
						+ "'");
			} else {
				rootDeptId = Long
						.valueOf(UtilFunc.getString(user, "dept_id", "-1"));
				querySQL.append("  start with dep_rol.dept_id = ' " + rootDeptId
						+ "'");
			}
				querySQL.append(
			"          connect by prior dep_rol.dept_id = dep_rol.dept_up_id\n")
			.append("        order siblings by dep_rol.dept_up_id,dep_rol.dis_order\n")
			.toString();
		}
		
		return DBHelper.executeList(querySQL.toString());*/
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.RoleDAO#getRoleGridByDeptId()
	 */
	public List<Map<String, Object>> getRoleGridByDeptId(String sval) {
		StringBuilder querySQL = new StringBuilder();

		querySQL.append(" select * ");
		querySQL.append("   from (");
		querySQL.append("         select (-1 - rol.role_id) as dept_id, ");
		querySQL.append("                rol.role_id, ");
		querySQL.append("                rol.role_name as role_name, ");
		querySQL.append("                rol.role_desc as role_desc, ");
		querySQL.append("                rol.role_name as short_name, ");
		
		querySQL.append("                dept.dept_name AS dept_name, ");
		querySQL.append("                CONCAT(dept.dept_name,'(',dept.dept_id, ')') AS dept_info, ");
		querySQL.append("                CONCAT(rol.role_name,'(',rol.role_id, ')') AS role_info, ");
		
		UserInfo user = getUserInfo();
		int gradeId = Integer.valueOf(UtilFunc.getString(user, "grade_id", "0"));
		
		querySQL.append("                cast(IFNULL(rol.dept_id, 999999)  as unsigned int) as dept_up_id, ");
		querySQL.append("                (999999 + rol.dis_order) as dis_order, ");
		//querySQL.append(gradeId >= 4 || user.isSuperUser()?"'0-' as dept_fid, ":"dept.DEPT_FID AS dept_fid,");
		querySQL.append( 				"dept.dept_fid AS dept_fid,");
		querySQL.append("                0 as dept_type, ");
		querySQL.append("                '1' as isrole ");
		querySQL.append("          FROM sys_roles rol,sys_dept dept WHERE rol.DEPT_ID = dept.DEPT_ID) dep_rol ");

		Long rootDeptId = null;
		
		if (gradeId >= 4 || user.isSuperUser()) {
			rootDeptId = Long.valueOf(0);
			querySQL.append(" where dept_id != '"+rootDeptId+"' and  dep_rol.dept_fid like '%-" + rootDeptId + "-%'");
		} else {
			rootDeptId = Long.valueOf(UtilFunc.getString(user, "dept_id", "-1"));
			querySQL.append(" where  dep_rol.dept_fid like '%-" + rootDeptId + "-%'");
		}
		
		if(UtilFunc.isNotBlank(sval)) {
			querySQL.append(" AND (role_info LIKE concat ('%','"+sval+"','%') or dept_info LIKE concat ('%','"+sval+"','%') ) "
					+ " or role_desc LIKE concat ('%','"+sval+"','%')");
		}
		
		querySQL.append(" order by dep_rol.dept_up_id,dep_rol.dis_order\n");
		
		return DBHelper.executeList(querySQL.toString());
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.RoleDAO#getRoleUsers(java.lang.String)
	 */
	public List getRoleUsers(String roleId) {
		String sql = "select ur.user_id,\n"
				+
				"       ur.user_kind,\n"
				+
				"       ur.role_id,\n"
				+
				"       staff.login_user,\n"
				+
				"       staff.staff_name as user_name,\n"
				+
				"       staff.staff_sta,\n"
				+
				"       staff.dept_id,\n"
				+
				"       staff.center_id,\n"
				+
				"       dept.dept_name\n";
				//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
				if(checkRight) {
					sql += "  from sys_user_role ur,  sys_staff staff LEFT JOIN sys_dept dept ON staff.dept_id = dept.dept_id\n"
							+
							" where ur.user_id = staff.staff_id\n"
							+
							"   and '1' = dept.valid_flag\n" ;
				}else {
					sql +=  "  from sys_user_role ur, sys_staff staff, sys_dept dept\n"
							+
							" where ur.user_id = staff.staff_id\n"
							+
							"   and staff.dept_id = dept.dept_id(+) and '1' = dept.valid_flag(+)\n";
				}
				
				sql +=	"   and ur.user_kind = '9'\n"
				+
				"   and ur.role_id = ?\n"
				+
				"   and staff.grade_id <= ?\n"
				+
				" order by dept.dis_order, dept.dept_id, staff.dis_order, staff.login_user";

		return DBHelper.executeList(
				sql,
				new String[] { roleId,
						UtilFunc.getString(getUserInfo(), "grade_id", "0") });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.manager.dao.RoleDAO#searchRoleListBySearchDto(com.powersi
	 * .sys.user.entity.SearchUserDTO)
	 */
	public List searchRoleListBySearchDto(SearchUserDTO searchUserDto) {
		UserInfo user = getUserInfo();
		int gradeId = Integer
				.valueOf(UtilFunc.getString(user, "grade_id", "0"));
		
		StringBuilder sql = new StringBuilder();
		if (gradeId < 4 && !user.isSuperUser()) {
			sql.append(" select a.*, b.dept_name,  ('（' || IFNULL(b.dept_name, '无') || '）' || a.role_name) as role_text ");
			sql.append("   from sys_roles a, ");
			sql.append("        (select dept_id ");
			sql.append("           from sys_dept where valid_flag = '1'");
			sql.append("          start with dept_id = ").append(
					UtilFunc.getString(user, "dept_id", "-1"));
			sql.append("         connect by prior dept_id = dept_up_id) dept, sys_dept b ");
			sql.append("  where a.dept_id = dept.dept_id ");
			sql.append("    and a.dept_id = b.dept_id(+) ");
		} else {
			sql.append(" select a.*, b.dept_name as dept_name, ('（' || IFNULL(b.dept_name, '无') || '）' || a.role_name) as role_text ");
			sql.append("   from sys_roles a, sys_dept b");
			sql.append("  where a.dept_id = b.dept_id(+) and '1' = b.valid_flag(+)");
		}

		if (searchUserDto != null) {
			if (!UtilFunc.isEmpty(searchUserDto.getDep())) {
				sql.append(" and a.dept_id='" + searchUserDto.getDep() + "'");
			}
			if (!UtilFunc.isEmpty(searchUserDto.getRoleName())) {
				sql.append(" and a.role_name like '%"
						+ searchUserDto.getRoleName() + "%'");
			}
		}

		 //是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight) {
			sql.append("  order by IFNULL(b.dis_order, -1000), b.dept_name, a.dis_order, a.role_id ");
		}else {
			sql.append("  order by nvl(b.dis_order, -1000), b.dept_name, a.dis_order, a.role_id ");
		}
		return DBHelper.executeList(sql.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.RoleDAO#deleteRoleUser(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public int deleteRoleUser(String roleId, String userKind, String userList) {
		if (!UtilFunc.hasText(userList)) {
			return 0;
		}

		StringBuilder sql = new StringBuilder();

		sql.append(" delete from sys_user_role ");
		sql.append("  where role_id = ? ");
		sql.append("    and user_kind = ? ");
		sql.append("    and user_id in (").append(userList).append(")");

		return DBHelper.executeUpdate(sql.toString(),
				new Object[] { Long.valueOf(roleId),
						Integer.valueOf(userKind) });
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.RoleDAO#getRoleListByUserKind(java.lang.String)
	 */
	public List getRoleListByUserKind(String userKind) {
		String sql = "select a.role_id, a.role_name\n" + 
				"  from sys_roles a, sys_role_userkind b\n" + 
				" where a.role_id = b.role_id\n" + 
				"   and b.user_kind = ?\n" + 
				" order by a.dis_order";
		return DBHelper.executeList(sql, new Object[]{userKind});
	}
}