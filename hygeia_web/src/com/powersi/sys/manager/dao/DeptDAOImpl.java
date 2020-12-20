/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * 部门DAO接口实现.
 */
public class DeptDAOImpl extends BaseDAOImpl implements DeptDAO {
	//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	boolean checkRight = !ParameterMapping.getStringByCode("check_conn_mysql", "0").equals("0");
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.manager.dao.DeptDAO#getDeptTree(java.lang.String,
	 * java.util.Map)
	 */
	public List getDeptTree() {
		StringBuilder sql = new StringBuilder();

		sql.append(" select dept_id, ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(!checkRight){
	    	sql.append("        level as dept_level, ");
	    }
		sql.append("        dept_name, ");
		sql.append("        short_name, ");
		sql.append("        dept_up_id, ");
		sql.append("        dept_type, ");
		sql.append("        dis_order, ");
		sql.append("        center_id ");
		sql.append("   from sys_dept where valid_flag = '1'");
		
		Long rootDeptId = null;
		UserInfo user = getUserInfo();
		int gradeId = Integer.valueOf(UtilFunc.getString(user, "grade_id", "0"));
		
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight) {
			if(user.isSuperUser() || gradeId >= 4){
				rootDeptId = Long.valueOf(0);
		    	sql.append(" and dept_id != '"+rootDeptId+"' and dept_fid like '%-" + rootDeptId
						+ "-%'");
			} else {
				rootDeptId = Long.valueOf(UtilFunc.getString(user, "dept_id", "-1"));
		    	sql.append(" and dept_fid like '%-" + rootDeptId
						+ "-%'");
				
			}
			sql.append("  order by dis_order, dept_id ");
		}else{
			if(user.isSuperUser() || gradeId >= 4){
				rootDeptId = Long.valueOf(0);
		    	sql.append("  start with dept_up_id = "+ rootDeptId
						+ " ");
			} else {
				rootDeptId = Long.valueOf(UtilFunc.getString(user, "dept_id", "-1"));
			    sql.append("  start with dept_id = "+ rootDeptId
						+ " ");
			}
			sql.append(" connect by prior dept_id = dept_up_id ");
			sql.append("  order siblings by dis_order, dept_id ");
		}
		
		return DBHelper.executeList(sql.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.manager.dao.DeptDAO#getDeptInfo(java.lang.String)
	 */
	public Map getDeptInfo(String deptId) {
		Map param = new HashMap();
		param.put("dept_id", Long.valueOf(deptId));
		return DAOHelper.queryForMap(TABLE_NAME, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.manager.dao.DeptDAO#hasChildDept(java.lang.String)
	 */
	public boolean hasChildDept(String deptId) {
		Map param = new HashMap();
		param.put("dept_up_id", Long.valueOf(deptId));
		return DAOHelper
				.count(TABLE_NAME, param, new String[] { "dept_up_id" }) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sips.sys.manager.dao.DeptDAO#insertDept(java.util.Map)
	 */
	public int insertDept(Map deptMap) {
		return DAOHelper.insert(TABLE_NAME, deptMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sips.sys.manager.dao.DeptDAO#updateDept(java.util.Map)
	 */
	public int updateDept(Map deptMap) {
		return DAOHelper.update(TABLE_NAME, deptMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sips.sys.manager.dao.DeptDAO#delDept(java.lang.String)
	 */
	public int deleteDept(String deptId) {
		int ret = 0;
		Long id = Long.valueOf(deptId);
		if(id.longValue() == 0){
			return 0;
		}
		
		Map param = new HashMap();
		param.put("dept_id", id);
		
		{
			//更新岗位部门
			StringBuilder sql = new StringBuilder();

			sql.append(" update sys_roles set dept_id = null");
			sql.append("  where dept_id in ");
			
			//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		    if(checkRight){
		    	sql.append("       (select dept_id  from sys_dept  ");
		    	sql.append("        where dept_id != :dept_id and dept_fid like concat ('%-',:dept_id,'-%')  ");
		    }else {
		    	sql.append("        (select dept_id ");
				sql.append("           from sys_dept ");
				sql.append("          start with dept_up_id = :dept_id ");
				sql.append("         connect by prior dept_id = dept_up_id ");
		    }
			
			sql.append("         union all ");
			sql.append("         select dept_id from sys_dept where dept_id = :dept_id ) ");

			DBHelper.executeUpdate(sql.toString(), param);
		}
		
		{
			//更新操作员部门
			StringBuilder sql = new StringBuilder();

			sql.append(" update sys_staff set dept_id = null");
			sql.append("  where dept_id in ");
			//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		    if(checkRight){
		    	sql.append("       (select dept_id  from sys_dept  ");
		    	sql.append("        where dept_id != :dept_id and dept_fid like concat ('%-',:dept_id,'-%')");
		    }else {
		    	sql.append("        (select dept_id ");
				sql.append("           from sys_dept ");
				sql.append("          start with dept_up_id = :dept_id ");
				sql.append("         connect by prior dept_id = dept_up_id ");
		    }
		
			sql.append("         union all ");
			sql.append("         select dept_id from sys_dept where dept_id = :dept_id ) ");

			DBHelper.executeUpdate(sql.toString(), param);
		}
		
		{
			//删除部门
			StringBuilder sql = new StringBuilder();

			sql.append(" delete from sys_dept ");
			//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		    if(checkRight){
		    	sql.append("  where dept_fid like concat ('%-',:dept_id,'-%') ");
		    }else {
		    	sql.append("  where dept_id in ");
		    	sql.append("        (select dept_id ");
				sql.append("           from sys_dept ");
				sql.append("          start with dept_up_id = :dept_id ");
				sql.append("         connect by prior dept_id = dept_up_id ");
				sql.append("         union all ");
				sql.append("         select dept_id from sys_dept where dept_id = :dept_id ) ");
		    }

			DBHelper.executeUpdate(sql.toString(), param);
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.manager.dao.DeptDAO#checkDeptNesting(java.lang.String
	 * , java.lang.String)
	 */
	public boolean checkDeptNesting(String deptId, String deptUpId) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select count(1) ");
		
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	    if(checkRight){
	    	sql.append("      from (select dept_id  from sys_dept  ");
	    	sql.append("        where valid_flag = '1' and dept_id != "+deptId+" and dept_fid like concat ('%-',"+deptId+",'-%') ) tab1");
	    }else {
			sql.append("   from (select dept_id ");
			sql.append("           from sys_dept where valid_flag = '1'");
			sql.append("          start with dept_up_id = "+deptId+" ");
			sql.append("         connect by prior dept_id = dept_up_id) ");
	    }
	
		sql.append("  where dept_id = ? ");

		int count = DBHelper.executeInt(sql.toString(), new Object[] {
				new Long(deptUpId) });
		return (count == 0 ? false : true);
	}

	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.DeptDAO#updateRolesByDeptId(java.lang.String, java.lang.String)
	 */
	public int updateRolesByDeptId(String deptId, String roles) {
		if(!UtilFunc.hasText(roles)){
			return 0;
		}
		
		StringBuilder sql = new StringBuilder();

		sql.append(" update sys_roles set dept_id = ? where role_id in (");
		sql.append(roles);
		sql.append(")");
		
		return DBHelper.executeUpdate(sql.toString(), new Object[]{deptId});
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.DeptDAO#updateUsersByDeptId(java.lang.String, java.lang.String)
	 */
	public int updateUsersByDeptId(String deptId, String users) {
		if(!UtilFunc.hasText(users)){
			return 0;
		}
		
		StringBuilder sql = new StringBuilder();

		sql.append(" update sys_staff set dept_id = ? where staff_id in (");
		sql.append(users);
		sql.append(")");
		
		return DBHelper.executeUpdate(sql.toString(), new Object[]{deptId});
	}

	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.DeptDAO#hasUser(java.lang.String)
	 */
	public boolean hasUser(String deptId) {
		int count = DBHelper.executeInt(" select count(1) from sys_staff where dept_id = ?", new Object[] {
				new Long(deptId)});
		return (count == 0 ? false : true);
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.DeptDAO#hasRole(java.lang.String)
	 */
	public boolean hasRole(String deptId) {
		int count = DBHelper.executeInt(" select count(1) from sys_roles where dept_id = ?", new Object[] {
				new Long(deptId)});
		return (count == 0 ? false : true);
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.DeptDAO#getDeptUUID(java.lang.String)
	 */
	public String getDeptUUID(String deptId) {
		return (String)DBHelper.executeScale("select dept_uuid from sys_dept where dept_id = ?", new Object[]{deptId});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getDeptGrid(String sval) {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT  dept_id, dept_name,last_date ,dept_fid,dept_up_id FROM sys_dept WHERE valid_flag = '1'");
		sql.append("  AND dept_id != '0' ");
		sql.append(" AND dept_fid LIKE '%-0-%' ");
		if( StringUtils.isNotBlank(sval) ) {
			sql.append(" AND (dept_name LIKE '%"+ sval +"%' OR dept_fid Like '-%"+ sval +"-%')  ");
		}
		
		Long rootDeptId = null;
		UserInfo user = getUserInfo();
		int gradeId = Integer.valueOf(UtilFunc.getString(user, "grade_id", "0"));
		
		if(user.isSuperUser() || gradeId >= 4){
			rootDeptId = Long.valueOf(0);
	    	sql.append(" and dept_id != '"+rootDeptId+"' and dept_fid like '%-" + rootDeptId
					+ "-%'");
		} else {
			rootDeptId = Long.valueOf(UtilFunc.getString(user, "dept_id", "-1"));
	    	sql.append(" and dept_fid like '%-" + rootDeptId
					+ "-%'");
		}
		sql.append("  order by dept_fid,dis_order ");
		
		List<Map<String,Object>> list = DBHelper.executeList(sql.toString());
		
		getDeptUpInfo(list);
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.DeptDAO#getDeptUpInfo()
	 */
	public void getDeptUpInfo(List<Map<String,Object>> list) {
		
		if(list == null || list.isEmpty()) {
			return;
		}
		
		for (Map tmp_map : list) {
			//存在map数据，该key一定存在
			String dept_fid = (String) tmp_map.get("dept_fid");
			String dept_fid_str = StringUtils.strip(dept_fid, "-").replace("-", ",");
			
			StringBuilder sql_dept_name = new StringBuilder();

			sql_dept_name.append(" SELECT GROUP_CONCAT(dept_name SEPARATOR '-') as dept_name ") ;
			sql_dept_name.append(" FROM sys_dept WHERE dept_id IN (" + dept_fid_str + ") ORDER BY dept_id; ");
			Map executeMap = DBHelper.executeMap(sql_dept_name.toString());
			
			if(executeMap == null || executeMap.isEmpty()) {
				continue;
			}
			tmp_map.put("dept_up_name", executeMap.get("dept_name"));
		}
	}
	
}