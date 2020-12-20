/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.sys.util.StringUtil;

/**
 * 菜单DAO接口实现.
 */
public class MenuDAOImpl extends BaseDAOImpl implements MenuDAO {
	
	//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	  boolean checkRight = !ParameterMapping.getStringByCode("check_conn_mysql", "0").equals("0");
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.manager.dao.MenuDAO#getMenuTree(java.lang.String)
	 */
	public List getMenuTree(String rootMenuId) {
		return getMenuTree(rootMenuId, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.manager.dao.MenuDAO#getMenuTree(java.lang.String,
	 * java.util.Map)
	 */
	public List getMenuTree(String rootMenuId, Map filterMap) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select menu_id, ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
			sql.append("        IF(IFNULL(win_name, 'null') = NULL,0,1) AS menu_flag, ");
	    }else {
	    	sql.append("        level as menu_level, ");
			sql.append("        decode(nvl(win_name, 'null'), 'null', 0, 1) as menu_flag, ");
	    }
		sql.append("        menu_name, ");
		sql.append("        menu_up_id, ");
		sql.append("        menu_type, ");
		sql.append("        menu_order, ");
		sql.append("        valid_flag ");
		sql.append("   from sys_menu menu");
		sql.append("  where win_type = '2'");

		List param = new ArrayList();
		if (!UtilFunc.isEmpty(filterMap)) {
			for (Object key : filterMap.keySet()) {
				sql.append(String.format(" and %1$s = ? ", key));
				param.add(filterMap.get(key));
			}
		}

		UserInfo user = getUserInfo();
		int gradeId = Integer
				.valueOf(UtilFunc.getString(user, "grade_id", "0"));
		if (gradeId < 4 && !user.isSuperUser()) { //lwyao 2018.01.30菜单为无需授权时，角色管理中的菜单权限也要显示出来
			sql.append("    and (right_flag = '0' or exists (select 'X' ");
			sql.append("           from sys_role_menus role ");
			sql.append("          where role.menu_id = menu.menu_id ");
			sql.append("            and role.role_id in (")
					.append(UtilFunc.getString(user, "role_id", "-100"))
					.append("))) ");
		}

		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
			sql.append(" AND menu_fid LIKE \'%-"+rootMenuId+"-%\' ");
			sql.append("  order by menu_order, menu_id ");
	    }else {
	    	param.add(new Long(rootMenuId));

	    	sql.append("  start with menu_up_id = ? ");
	    	sql.append(" connect by prior menu_id = menu_up_id ");
	    	sql.append("  order siblings by menu_order, menu_id ");
	    }
	
		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(param));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.manager.dao.MenuDAO#getMenuInfo(java.lang.String)
	 */
	public Map getMenuInfo(String menuId) {
		Map param = new HashMap();
		param.put("menu_id", Long.valueOf(menuId));
		return DAOHelper.queryForMap(TABLE_NAME, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.manager.dao.MenuDAO#hasChildMenu(java.lang.String)
	 */
	public boolean hasChildMenu(String menuId) {
		Map param = new HashMap();
		param.put("menu_up_id", Long.valueOf(menuId));
		return DAOHelper
				.count(TABLE_NAME, param, new String[] { "menu_up_id" }) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sips.sys.manager.dao.MenuDAO#insertMenu(java.util.Map)
	 */
	public int insertMenu(Map menuMap) {
		return DAOHelper.insert(TABLE_NAME, menuMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sips.sys.manager.dao.MenuDAO#updateMenu(java.util.Map)
	 */
	public int updateMenu(Map menuMap) {
		return DAOHelper.update(TABLE_NAME, menuMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sips.sys.manager.dao.MenuDAO#updateMenuValidFlag(java.util.Map)
	 *///  2016-09-09 lingang hygeia_web修改支持mysql数据库
	public int updateMenuValidFlag(Map menuMap){
		return DBHelper.executeUpdate(
					"UPDATE sys_menu SET valid_flag = ? WHERE menu_fid LIKE CONCAT(?,'%')",
					new Object[] { menuMap.get("valid_flag").toString()
							,menuMap.get("menu_fid").toString() });
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sips.sys.manager.dao.MenuDAO#delMenu(java.lang.String)
	 */
	public int deleteMenu(String menuId) {
		int ret = 0;
		Long id = Long.valueOf(menuId);
		if (id.longValue() == 0) {
			return 0;
		}

		Map param = new HashMap();
		param.put("menu_id", id);

		{
			// 删除权限菜单
			StringBuilder sql = new StringBuilder();

			sql.append(" delete from sys_role_menus ");
			sql.append("  where menu_id in ");
			//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		    if(checkRight){
		    	sql.append("        (SELECT * FROM (select menu_id ");
				sql.append("           from sys_menu ");
				sql.append("          WHERE menu_fid LIKE ");
				sql.append("         CONCAT('%-',(SELECT menu_fid FROM sys_menu WHERE menu_id = :menu_id ),'-%') )a ) ");

		    }else {
				sql.append("        (select menu_id ");
				sql.append("           from sys_menu ");
				sql.append("          start with menu_up_id = :menu_id ");
				sql.append("         connect by prior menu_id = menu_up_id ");
				sql.append("         union all ");
				sql.append("         select menu_id from sys_menu where menu_id = :menu_id ) ");	
		    }
			DBHelper.executeUpdate(sql.toString(), param);
		}

		{
			// 删除菜单
			StringBuilder sql = new StringBuilder();

			sql.append(" delete from sys_menu ");
			sql.append("  where menu_id in ");
			//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		    if(checkRight){
		    	sql.append("        (SELECT * FROM (select menu_id ");
				sql.append("           from sys_menu ");
				sql.append("          WHERE menu_fid LIKE ");
				sql.append("         CONCAT('%-',(SELECT menu_fid FROM sys_menu WHERE menu_id = :menu_id ),'-%') ) a) ");

		    }else {
				sql.append("        (select menu_id ");
				sql.append("           from sys_menu ");
				sql.append("          start with menu_up_id = :menu_id ");
				sql.append("         connect by prior menu_id = menu_up_id ");
				sql.append("         union all ");
				sql.append("         select menu_id from sys_menu where menu_id = :menu_id ) ");
		    }

			DBHelper.executeUpdate(sql.toString(), param);
		}

		{
			// 删除任务菜单
			if (DBHelper.existTable("SYS_MENU_TASK")) {
				DBHelper.executeUpdate(
						"delete from sys_menu_task where menu_id = ?",
						new Object[] { id });
			}
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.manager.dao.MenuDAO#checkMenuNesting(java.lang.String
	 * , java.lang.String)
	 */
	public boolean checkMenuNesting(String menuId, String menuUpId) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select count(1) ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
	    	sql.append("   from (SELECT menu_id FROM sys_menu ");
			sql.append("    WHERE menu_fid LIKE CONCAT('%-',(SELECT menu_fid FROM sys_menu WHERE menu_id = ? ),'-%') ");
			sql.append("   AND menu_fid != (SELECT menu_fid FROM sys_menu WHERE menu_id = ? ) ) tab1");

	    }else {
			sql.append("   from (select menu_id ");
			sql.append("           from sys_menu ");
			sql.append("          start with menu_up_id = ? ");
			sql.append("         connect by prior menu_id = menu_up_id) ");
	    }
		sql.append("  where menu_id = ? ");

		int count = DBHelper.executeInt(sql.toString(), new Object[] {
				new Long(menuId), new Long(menuId), new Long(menuId)  });///修改
		return (count == 0 ? false : true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.manager.dao.MenuDAO#getMenuTreeByRoles(java.lang.String)
	 */
	public List getMenuTreeByRoles(String roles) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select menu_id, ");
		sql.append("        menu_name, ");
		sql.append("        menu_up_id, ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
	    	sql.append("        menu_fid,IF(IFNULL(win_name, 'null') = NULL,0,1) AS menu_flag, ");
	    }else {
	    	sql.append("        level as menu_level, ");
			sql.append("        decode(nvl(win_name, 'null'), 'null', 0, 1) as menu_flag, ");
	    }
		sql.append("        menu_type, ");
		sql.append("        menu_order, ");
		sql.append("        valid_flag ");
		sql.append("   from (select menu.* ");
		sql.append("           from sys_menu menu ");
		sql.append("          where menu.valid_flag = '1' ");
		if ("allmenu".equals(roles)) {
			sql.append("            and menu.menu_type = '1' ");
		} else if (!"all".equals(roles)) {
			sql.append("            and (menu.right_flag = '0' or ");
			sql.append("                (menu.right_flag = '1' and exists ");
			sql.append("                 (select 'X' ");
			sql.append("                     from sys_role_menus ");
			sql.append("                    where sys_role_menus.role_id in (")
					.append(roles).append(")");
			sql.append("                      and sys_role_menus.menu_id = menu.menu_id))) ");
			sql.append("             or (menu.right_flag = '2' and exists ");
			sql.append("                 (select 'X' ");
			sql.append("                    from sys_role_menus ");
			sql.append("                   where sys_role_menus.role_id in (")
					.append(roles).append(")");
			//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		    if(checkRight){
		    	sql.append(" and sys_role_menus.menu_id = ( select menu_id  from sys_menu upmenu ");
		    	sql.append(" where    upmenu.right_flag = '1' and ");
		    	sql.append(" (select menu_fid from sys_menu where menu_id= menu.menu_id ) like concat('%-',upmenu.menu_id,'-%') ");
		    	sql.append(" order by length(upmenu.menu_fid) desc limit 1 )  )  ) ");
		    }else {
		    	sql.append("   and sys_role_menus.menu_id = uf_menu_right(menu.menu_id)))");
		    }
		}
		sql.append("          ) t ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
	    	sql.append("  where  menu_fid like '%-0-%' ");
	    	sql.append("  order  by menu_fid,menu_order, menu_id ");
	    	
	    	List tempList = DBHelper.executeList(sql.toString()); 
	 	    
	 	    //返回的数据列表
	 	    List menuByRoleList = new ArrayList();
	 	    /*
	 	     * 循环列表，判断父级菜单是否存在列表数据中,只有存在才添加到返回数据列表中。
	 	     * 父级为 -0-  不进行判断，默认添加
	 	     * 比如 -0-100-122- 只有 -0-100-存在里列表中，-0-100-122-才添加返回
	 	     */
	 	    for(int i = 0 ;i < tempList.size(); i++) {
	 	    	HashMap tempMap = (HashMap) tempList.get(i);
	 	    	String menuId = tempMap.get("menu_id").toString().trim();
	 	    	//获取父级的fid,
	 	    	String menuUpFid = tempMap.get("menu_fid").toString().replace(menuId+"-", "").trim();
	 	    	//判断父级是否存在或者等于-0-
	 	    	if(("-0-").equals(menuUpFid)) {
	 	    		menuByRoleList.add(tempMap);
	 	    		continue;
	 	    	}
	 	    	for(int j = 0 ;j < menuByRoleList.size(); j++) {
	 	    		HashMap menuRoleMap = (HashMap) menuByRoleList.get(j);
	 	    		if(menuUpFid.equals(menuRoleMap.get("menu_fid").toString().trim()) ) {
	 	    			menuByRoleList.add(tempMap);
	 	    			break;
	 	    		}
	 	    	}
	 	    }	    
	 		return menuByRoleList;
	    }else {
	    	sql.append("  start with menu_up_id = 0 ");
	    	sql.append(" connect by prior menu_id = menu_up_id ");
	    	sql.append("  order siblings by menu_order, menu_id ");
	    	
	    	return DBHelper.executeList(sql.toString()); 
	    }
	   
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.MenuDAO#getMenuTask(java.lang.String)
	 */
	public Map getMenuTask(String menuId) {
		return DBHelper.executeMap(
				"select * from sys_menu_task where menu_id = ?",
				new Object[] { menuId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.MenuDAO#saveMenuTask(java.util.Map)
	 */
	public int saveMenuTask(Map taskMap) {
		DAOHelper.delete("SYS_MENU_TASK", taskMap);
		//2016-09-09 lingang hygeia数据库修改为支持mysql
		if(StringUtil.isBlank(taskMap.get("task_right_type").toString()) ) {
			taskMap.put("task_right_type", null);
		}
		return DAOHelper.insert("SYS_MENU_TASK", taskMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.MenuDAO#deleteMenuTask(java.lang.String)
	 */
	public int deleteMenuTask(String menuId) {
		return DBHelper.executeUpdate(
				"delete from sys_menu_task where menu_id = ?",
				new Object[] { menuId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.MenuDAO#query(java.util.Map)
	 */
	public List query(Map param) {
		ArrayList params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select menu_id, ");
		sql.append("        menu_name, ");
		sql.append("        menu_desc, ");
		sql.append("        menu_up_id, ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
	    	sql.append("        if(menu_up_id = '0', '系统', (select menu_name from sys_menu where t.menu_up_id = menu_id)) as menu_up_name, ");
	    }else {
	    	sql.append("        decode(menu_up_id, '0', '系统', (select menu_name from sys_menu where t.menu_up_id = menu_id)) as menu_up_name, ");
	    }
		sql.append("        menu_order, ");
		sql.append("        win_name, ");
		sql.append("        menu_parm, ");
		sql.append("        menu_option, ");
		sql.append("        pic_name, ");
		sql.append("        win_type, ");
		sql.append("        menu_type, ");
		sql.append("        grade_id, ");
		sql.append("        log_flag, ");
		sql.append("        right_flag, ");
		sql.append("        out_app, ");
		sql.append("        valid_flag ");
		sql.append("   from sys_menu t");
		sql.append("  where win_type = '2'");

		boolean includeChild = "true".equals(UtilFunc.getString(param,
				"include_child"));
		if (includeChild) {
			sql.append("    and menu_id in ");
			sql.append("        (select menu_id ");
			sql.append("           from sys_menu ");
			sql.append("          start with 1 = 1 ");
		}

		String[] ranges = new String[] { "menu_id" };
		for (String range : ranges) {
			SqlHelper.addInt(UtilFunc.getString(param, range + "_min"), range,
					">=", sql, params);
			SqlHelper.addInt(UtilFunc.getString(param, range + "_max"), range,
					"<=", sql, params);
		}

		String[] cols = new String[] { "menu_id", "menu_name", "menu_desc",
				"menu_up_id", "menu_order", "win_name",
				"menu_parm", "menu_option", "pic_name" };
		for (String col : cols) {
			SqlHelper.addStringExp(UtilFunc.getString(param, col), col, sql,
					params);
		}

		if (includeChild) {
			sql.append("         connect by prior menu_id = menu_up_id) ");
		}

		String[] vals = new String[] { /* "win_type", */"menu_type",
				"grade_id", "log_flag", "right_flag", "out_app", "valid_flag" };
		for (String col : vals) {
			SqlHelper.addStringValues(param.get(col), col, sql,
					params);
		}

		sql.append(" order by menu_id");
		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.MenuDAO#query(java.lang.String)
	 */
	public List query(String menus) {
		if (UtilFunc.isBlank(menus)) {
			return Collections.EMPTY_LIST;
		}

		StringBuilder sql = new StringBuilder();

		sql.append(" select menu_id, ");
		sql.append("        menu_name, ");
		sql.append("        menu_desc, ");
		sql.append("        menu_up_id, ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
	    	sql.append("        IFNULL(menu_up_id = '0', '系统', (select menu_name from sys_menu where t.menu_up_id = menu_id)) as menu_up_name, ");
	    }else {
	    	sql.append("        decode(menu_up_id, '0', '系统', (select menu_name from sys_menu where t.menu_up_id = menu_id)) as menu_up_name, ");
	    }
		sql.append("        menu_order, ");
		sql.append("        win_name, ");
		sql.append("        menu_parm, ");
		sql.append("        menu_option, ");
		sql.append("        pic_name, ");
		sql.append("        win_type, ");
		sql.append("        menu_type, ");
		sql.append("        grade_id, ");
		sql.append("        log_flag, ");
		sql.append("        right_flag, ");
		sql.append("        out_app, ");
		sql.append("        valid_flag ");
		sql.append("   from sys_menu t");
		sql.append("  where win_type = '2'");
		sql.append(" and menu_id in (").append(UtilFunc.trimSqlParam(menus))
				.append(")");

		return DBHelper.executeList(sql.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.MenuDAO#changeMenuId(java.lang.String,
	 * java.lang.String)
	 */
	public void changeMenuId(String oldMenuId, String newMenuId) {
		if (UtilFunc.isEmpty(oldMenuId) || UtilFunc.isEmpty(newMenuId)
				|| oldMenuId.equals(newMenuId)) {
			return;
		}

		if (!UtilFunc.isEmpty(getMenuInfo(newMenuId))) {
			throw new HygeiaException("菜单编号 " + newMenuId + " 已经存在");
		}

		String[] tabs = new String[] { "sys_menu", "sys_menu_task",
				"sys_role_menus" };
		Object[] param = new Object[] { newMenuId, oldMenuId };

		for (String tab : tabs) {
			DBHelper.update(String.format(
					"update %1$s set menu_id = ? where menu_id = ?", tab),
					param);
		}

		DBHelper.update(
				"update sys_menu set menu_up_id = ? where menu_up_id = ?",
				param);
	}

	
	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.MenuDAO#keys()
	 */
	public Set<String> keys() {
		StringBuilder sql = new StringBuilder();

		sql.append(" select menu_id");
		sql.append("   from sys_menu");

		Set<String> set = new HashSet<String>();
		List<Object[]> lst = DBHelper.executeArrayList(sql.toString());
		for (Object[] obj : lst) {
			set.add(obj[0].toString());
		}

		return set;
	}
	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.MenuDAO#insert(java.util.List)
	 */
	public int insert(List data) {
		return DAOHelper.insert(TABLE_NAME, data);
	}

	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.MenuDAO#update(java.util.List)
	 */
	public int update(List data) {
		return DAOHelper.update(TABLE_NAME, data);
	}

	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.MenuDAO#queryMenuRole(java.util.Map)
	 */
	public List queryMenuRole(Map param) {
		ArrayList params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append("select * from sys_role_menus where menu_id in (");
		
		sql.append(" select menu_id ");
		sql.append("   from sys_menu t");
		sql.append("  where win_type = '2'");

		boolean includeChild = "true".equals(UtilFunc.getString(param,
				"include_child"));
		if (includeChild) {
			sql.append("    and menu_id in ");
			sql.append("        (select menu_id ");
			sql.append("           from sys_menu ");
			sql.append("          start with 1 = 1 ");
		}

		String[] ranges = new String[] { "menu_id" };
		for (String range : ranges) {
			SqlHelper.addInt(UtilFunc.getString(param, range + "_min"), range,
					">=", sql, params);
			SqlHelper.addInt(UtilFunc.getString(param, range + "_max"), range,
					"<=", sql, params);
		}

		String[] cols = new String[] { "menu_id", "menu_name", "menu_desc",
				"menu_up_id", "menu_order", "win_name",
				"menu_parm", "menu_option", "pic_name" };
		for (String col : cols) {
			SqlHelper.addStringExp(UtilFunc.getString(param, col), col, sql,
					params);
		}

		if (includeChild) {
			sql.append("         connect by prior menu_id = menu_up_id) ");
		}

		String[] vals = new String[] { /* "win_type", */"menu_type",
				"grade_id", "log_flag", "right_flag", "out_app", "valid_flag" };
		for (String col : vals) {
			SqlHelper.addStringValues(param.get(col), col, sql,
					params);
		}

		sql.append(")");
		
		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}


	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.MenuDAO#queryMenuRole(java.lang.String)
	 */
	public List queryMenuRole(String menus) {
		if (UtilFunc.isBlank(menus)) {
			return Collections.EMPTY_LIST;
		}

		StringBuilder sql = new StringBuilder();

		sql.append("select * from sys_role_menus where ");
		sql.append(" menu_id in (").append(UtilFunc.trimSqlParam(menus))
				.append(")");

		return DBHelper.executeList(sql.toString());
	}
	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.MenuDAO#deleteMenuRole(java.util.List)
	 */
	public int deleteMenuRole(List data) {
		return DAOHelper.delete("SYS_ROLE_MENUS", data);
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.MenuDAO#insertMenuRole(java.util.List)
	 */
	public int insertMenuRole(List data) {
		return DAOHelper.insert("SYS_ROLE_MENUS", data);
	}
}