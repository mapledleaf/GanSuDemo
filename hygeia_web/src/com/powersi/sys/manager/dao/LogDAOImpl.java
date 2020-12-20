/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * 日志DAO实现.
 */
public class LogDAOImpl extends BaseDAOImpl implements LogDAO {

	//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	boolean checkRight = !ParameterMapping.getStringByCode("check_conn_mysql", "0").equals("0");
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.manager.dao.LogDAO#queryLoginLog(java.util.Date,
	 * java.util.Date, java.util.Map)
	 */
	public List queryLoginLog(String beginDate, String endDate, Map map) {
		ArrayList params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.login_id, ");
		sql.append("        a.login_user, ");
		sql.append("        a.user_id, ");
		sql.append("        a.user_kind, ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
		    sql.append("        ifnull(b.kind_name, a.user_kind) as kind_name, ");
		    sql.append("        if((instr(a.remark, 'xception') - 1) = -1, a.remark, '') as remark, ");
		    sql.append("        a.user_name, ");
			sql.append("        a.login_address, ");
			sql.append("        a.login_date, ");
			sql.append("        a.logout_date, ");
			sql.append("        a.login_flag, ");
			sql.append("        a.logout_flag");
			sql.append("   from sys_login_log a left join sys_user_kind b on a.user_kind = b.user_kind");
			sql.append("  where a.login_date between date_format(?, '%Y-%m-%d %H:%i:%s') and ");
			sql.append("        date_format(?, '%Y-%m-%d %H:%i:%s') ");
		}else {
			sql.append("        nvl(b.kind_name, a.user_kind) as kind_name, ");
			sql.append("        decode(instr(a.remark, 'xception') - 1, -1, a.remark, '') as remark, ");
			sql.append("        a.user_name, ");
			sql.append("        a.login_address, ");
			sql.append("        a.login_date, ");
			sql.append("        a.logout_date, ");
			sql.append("        a.login_flag, ");
			sql.append("        a.logout_flag");
			sql.append("   from sys_login_log a, sys_user_kind b");
			sql.append("  where a.login_date between to_date(?, 'YYYY-MM-DD HH24:MI:SS') and ");
			sql.append("        to_date(?, 'YYYY-MM-DD HH24:MI:SS') ");
			sql.append("    and a.user_kind = b.user_kind(+) ");
		}
		
		params.add(beginDate);
		params.add(endDate);

		if(!"all".equals((String) map.get("loginFlag"))){
			SqlHelper.addInt((String) map.get("loginFlag"), "a.login_flag", "=",
					sql, params);
		}
		
		SqlHelper.addIntValues(map.get("userKind"), "a.user_kind", sql, params);

		String[] cnds = new String[] { "loginUser", "userName", "loginAddress",
				"remark" };
		String[] cols = new String[] { "a.login_user", "a.user_name",
				"a.login_address", "a.remark" };
		for (int i = 0; i < cnds.length; i++) {
			SqlHelper.addStringExp(UtilFunc.getString(map, cnds[i]),
					cols[i], sql, params);
		}
		
		sql.append("    order by a.login_date desc");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.LogDAO#getLoginLog(java.lang.String)
	 */
	public Map getLoginLog(String loginId) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.login_id, ");
		sql.append("        a.login_user, ");
		sql.append("        a.user_id, ");
		sql.append("        a.user_kind, ");
		sql.append("        a.user_name, ");
		sql.append("        a.login_address, ");
		sql.append("        a.login_flag, ");
		sql.append("        a.logout_flag, ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql.append("        ifnull(b.kind_name, a.user_kind) as kind_name, ");
			sql.append("        date_format(a.login_date, '%Y-%m-%d %H:%i:%s') as login_date, ");
			sql.append("        date_format(a.logout_date, '%Y-%m-%d %H:%i:%s') as logout_date, ");
			sql.append("        if(a.login_flag = 1, '成功', '失败') as login_flag_name, ");
			sql.append("        if((instr(a.remark, 'xception') - 1) = -1, a.remark, '') as remark ");
			sql.append("   from sys_login_log a left join sys_user_kind b on a.user_kind = b.user_kind");
			sql.append("  where a.login_id = ? ");
		}else {
			sql.append("        nvl(b.kind_name, a.user_kind) as kind_name, ");
			sql.append("        to_char(a.login_date, 'yyyy-mm-dd hh24:mi:ss') as login_date, ");
			sql.append("        to_char(a.logout_date, 'yyyy-mm-dd hh24:mi:ss') as logout_date, ");
			sql.append("        decode(a.login_flag, 1, '成功', '失败') as login_flag_name, ");
			sql.append("        decode(instr(a.remark, 'xception') - 1, -1, a.remark, '') as remark ");
			sql.append("   from sys_login_log a, sys_user_kind b ");
			sql.append("  where a.login_id = ? ");
			sql.append("    and a.user_kind = b.user_kind(+) ");
		}

		return DBHelper.executeMap(sql.toString(), new Object[] { loginId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sips.sys.manager.dao.LogDAO#queryOperateLog(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	public List queryOperateLog(String beginDate, String endDate, Map map) {
		ArrayList params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.operate_id, ");
		sql.append("        a.login_id, ");
		sql.append("        a.login_user, ");
		sql.append("        a.user_id, ");
		sql.append("        a.user_kind, ");
		sql.append("        a.user_name, ");
		sql.append("        a.login_address, ");
		sql.append("        a.operate_name, ");
		sql.append("        a.operate_param, ");
		sql.append("        a.operate_flag, ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql.append("        ifnull(b.kind_name, a.user_kind) as kind_name, ");
			sql.append("        date_format(a.begin_date, '%Y-%m-%d %H:%i:%s') as begin_date, ");
			sql.append("        date_format(a.end_date, '%Y-%m-%d %H:%i:%s') as end_date, ");
			sql.append("        if((instr(a.remark, 'xception') - 1) = -1, a.remark, '') as remark ");
			sql.append("   from sys_operate_log a left join sys_user_kind b on a.user_kind = b.user_kind");
			sql.append("  where a.begin_date between date_format(?, '%Y-%m-%d %H:%i:%s') and ");
			sql.append("        date_format(?, '%Y-%m-%d %H:%i:%s') ");
		}else{
			sql.append("        nvl(b.kind_name, a.user_kind) as kind_name, ");
			sql.append("        to_char(a.begin_date, 'yyyy-mm-dd hh24:mi:ss') as begin_date, ");
			sql.append("        to_char(a.end_date, 'yyyy-mm-dd hh24:mi:ss') as end_date, ");
			sql.append("        decode(instr(a.remark, 'xception') - 1, -1, a.remark, '') as remark ");
			sql.append("   from sys_operate_log a, sys_user_kind b ");
			sql.append("  where a.begin_date between to_date(?, 'YYYY-MM-DD HH24:MI:SS') and ");
			sql.append("        to_date(?, 'YYYY-MM-DD HH24:MI:SS') ");
			sql.append("    and a.user_kind = b.user_kind(+) ");
		}

		params.add(beginDate);
		params.add(endDate);

		if(!"all".equals((String) map.get("operateFlag"))){
			SqlHelper.addInt((String) map.get("operateFlag"), "a.operate_flag", "=",
					sql, params);
		}
	
		SqlHelper.addIntValues(map.get("userKind"), "a.user_kind", sql, params);

		String[] cnds = new String[] { "loginUser", "userName", "operateName" };
		String[] cols = new String[] { "a.login_user", "a.user_name",
				"a.operate_name" };
		for (int i = 0; i < cnds.length; i++) {
			SqlHelper.addStringExp(UtilFunc.getString(map, cnds[i]),
					cols[i], sql, params);
		}

		sql.append("  order by a.begin_date desc ");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.LogDAO#getOperateLog(java.lang.String)
	 */
	public Map getOperateLog(String operateId) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.operate_id, ");
		sql.append("        a.login_id, ");
		sql.append("        a.login_user, ");
		sql.append("        a.user_id, ");
		sql.append("        a.user_kind, ");
		sql.append("        a.user_name, ");
		sql.append("        a.login_address, ");
		sql.append("        a.operate_name, ");
		sql.append("        a.operate_action, ");
		sql.append("        a.operate_param, ");
		sql.append("        a.operate_flag, ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql.append("        date_format(a.begin_date, '%Y-%m-%d %H:%i:%s') as begin_date, ");
			sql.append("        date_format(a.end_date, '%Y-%m-%d %H:%i:%s') as end_date, ");
			sql.append("        ifnull(b.kind_name, a.user_kind) as kind_name, ");
			sql.append("        (case a.operate_flag when 1 then '成功' when 2 then '失败' else '' end ) as operate_flag_name, ");
			sql.append("        if((instr(a.remark, 'xception') - 1) = -1, a.remark, '') as remark ");
			sql.append("   from sys_operate_log a left join sys_user_kind b on a.user_kind = b.user_kind");
			sql.append("  where a.operate_id = ? ");
		}else {
			sql.append("        to_char(a.begin_date, 'yyyy-mm-dd hh24:mi:ss') as begin_date, ");
			sql.append("        to_char(a.end_date, 'yyyy-mm-dd hh24:mi:ss') as end_date, ");
			sql.append("        nvl(b.kind_name, a.user_kind) as kind_name, ");
			sql.append("        decode(a.operate_flag, 1, '成功', 2, '失败', '') as operate_flag_name, ");
			sql.append("        decode(instr(a.remark, 'xception') - 1, -1, a.remark, '') as remark ");
			sql.append("   from sys_operate_log a, sys_user_kind b ");
			sql.append("  where a.operate_id = ? ");
			sql.append("    and a.user_kind = b.user_kind(+) ");
		}

		return DBHelper.executeMap(sql.toString(), new Object[] { operateId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.LogDAO#querySystemLog(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	public List querySystemLog(String beginDate, String endDate, Map map) {
		ArrayList params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select /*+parallel(t 4)*/log_sn, ");
		sql.append("        server_name, ");
		sql.append("        log_level, ");
		sql.append("        log_name, ");
		sql.append("        log_message, ");
		sql.append("        log_exception, ");
		sql.append("        login_user, ");
		sql.append("        user_address, ");
		sql.append("        operate_action, ");
		sql.append("        operate_name, ");
		sql.append("        biz_id ,");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql.append("        date_format(log_date, '%Y-%m-%d %H:%i:%s') as log_date");
			sql.append("   from sys_system_log t");
			sql.append("  where log_date between date_format(?, '%Y-%m-%d %H:%i:%s') and date_format(?, '%Y-%m-%d %H:%i:%s') ");
			
		}else {
			sql.append("        to_char(log_date, 'yyyy-mm-dd hh24:mi:ss') as log_date");
			sql.append("   from sys_system_log t");
			sql.append("  where log_date between to_date(?, 'YYYY-MM-DD HH24:MI:SS') and to_date(?, 'YYYY-MM-DD HH24:MI:SS') ");
			
		}

		params.add(beginDate);
		params.add(endDate);

		SqlHelper.addStringValues(map.get("logLevel"), "log_level", sql, params);
	
		String[] cnds = new String[] { "serverName", "logName", "logMessage",
				"logException", "loginUser", "userAddress",
				"operateAction", "operateName" };
		String[] cols = new String[] { "server_name", "log_name",
				"log_message",
				"log_exception", "login_user", "user_address",
				"operate_action", "operate_name" };
		for (int i = 0; i < cnds.length; i++) {
			SqlHelper.addStringExp(UtilFunc.getString(map, cnds[i]),
					cols[i], sql, params);
		}

		sql.append("  order by log_date desc ");

		List lst = DBHelper.executeList(sql.toString(),
				UtilFunc.toArray(params));
		for (int i = 0; i < lst.size(); i++) {
			Map m = (Map) lst.get(i);
			
			m.put("log_level",
					LogHelper.parseLogLevel(UtilFunc.getString(m, "log_level")));
		}

		return lst;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.LogDAO#getSystemLog(java.lang.String)
	 */
	public Map getSystemLog(String logSn) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select log_sn, ");
		sql.append("        server_name, ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql.append("        date_format(log_date, '%Y-%m-%d %H:%i:%s') as log_date, ");
		}else {
			sql.append("        to_char(log_date, 'yyyy-mm-dd hh24:mi:ss') as log_date, ");
		}
		sql.append("        log_level, ");
		sql.append("        log_name, ");
		sql.append("        log_message, ");
		sql.append("        log_exception, ");
		sql.append("        login_user, ");
		sql.append("        user_address, ");
		sql.append("        operate_action, ");
		sql.append("        operate_name, ");
		sql.append("        biz_id ");
		sql.append("   from sys_system_log ");
		sql.append("  where log_sn = ?");

		return DBHelper.executeMap(sql.toString(), new Object[] { logSn });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.LogDAO#statLoginLog(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	public List statLoginLog(String beginDate, String endDate, Map map) {
		ArrayList params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql.append(" select date_format(a.login_date, '%H') as x, ");
			sql.append("        count(1) as y ");
			sql.append("   from sys_login_log a left join sys_user_kind b on a.user_kind = b.user_kind");
			sql.append("  where a.login_date between date_format(?, '%Y-%m-%d %H:%i:%s') and ");
			sql.append("        date_format(?, '%Y-%m-%d %H:%i:%s') ");
		}else {
			sql.append(" select to_char(a.login_date, 'hh24') as x, ");
			sql.append("        count(1) as y ");
			sql.append("   from sys_login_log a, sys_user_kind b ");
			sql.append("  where a.login_date between to_date(?, 'YYYY-MM-DD HH24:MI:SS') and ");
			sql.append("        to_date(?, 'YYYY-MM-DD HH24:MI:SS') ");
			sql.append("    and a.user_kind = b.user_kind(+) ");
		}

		params.add(beginDate);
		params.add(endDate);

		if(!"all".equals((String) map.get("loginFlag"))){
			SqlHelper.addInt((String) map.get("loginFlag"), "a.login_flag", "=",
					sql, params);
		}

		SqlHelper.addIntValues(map.get("userKind"), "a.user_kind", sql, params);


		String[] cnds = new String[] { "loginUser", "userName" };
		String[] cols = new String[] { "a.login_user", "a.user_name" };
		for (int i = 0; i < cnds.length; i++) {
			SqlHelper.addStringExp(UtilFunc.getString(map, cnds[i]),
					cols[i], sql, params);
		}

		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql.append("  group by date_format(login_date, '%H') ");
		}else {
			sql.append("  group by to_char(login_date, 'hh24') ");
		}
		sql.append("  order by x");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.LogDAO#statLoginLog(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	public List statLogoutLog(String beginDate, String endDate, Map map) {
		ArrayList params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql.append(" select date_format(a.logout_date, '%H') as x, ");
			sql.append("        count(1) as y ");
			sql.append("   from sys_login_log a left join sys_user_kind b on a.user_kind = b.user_kind");
			sql.append("  where a.login_date between date_format(?, '%Y-%m-%d %H:%i:%s') and ");
			sql.append("        date_format(?, '%Y-%m-%d %H:%i:%s') ");
		}else {
			sql.append(" select to_char(a.logout_date, 'hh24') as x, ");
			sql.append("        count(1) as y ");
			sql.append("   from sys_login_log a, sys_user_kind b ");
			sql.append("  where a.login_date between to_date(?, 'YYYY-MM-DD HH24:MI:SS') and ");
			sql.append("        to_date(?, 'YYYY-MM-DD HH24:MI:SS') ");
			sql.append("    and a.user_kind = b.user_kind(+) ");
		}

		sql.append("    and a.logout_date is not null ");

		params.add(beginDate);
		params.add(endDate);

		if(!"all".equals((String) map.get("loginFlag"))){
			SqlHelper.addInt((String) map.get("loginFlag"), "a.login_flag", "=",
					sql, params);
		}

		SqlHelper.addIntValues(map.get("userKind"), "a.user_kind", sql, params);


		String[] cnds = new String[] { "loginUser", "userName" };
		String[] cols = new String[] { "a.login_user", "a.user_name" };
		for (int i = 0; i < cnds.length; i++) {
			SqlHelper.addStringExp(UtilFunc.getString(map, cnds[i]),
					cols[i], sql, params);
		}

		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql.append("  group by date_format(logout_date, '%H') ");
		}else {
			sql.append("  group by to_char(logout_date, 'hh24') ");
		}
		sql.append("  order by x");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.LogDAO#clearSystemLog(java.util.Date)
	 */
	public void clearSystemLog(Date date) {
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
				if(checkRight){
					DBHelper.update(
							"/*+parallel(t 4)*/delete from sys_system_log  where log_date <= date_format(?,'%Y-%m-%d %H:%i:%s')",
							new Object[] { date });
				}else {
					DBHelper.update(
							"/*+parallel(t 4)*/delete from sys_system_log t where log_date <= ?",
							new Object[] { date });
				}
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.LogDAO#analysisLoginLogByTime(java.util.Map)
	 */
	public List analysisLoginLogByTime(Map map) {
		ArrayList params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append(" select login_id, ");
		sql.append("        login_user, ");
		sql.append("        user_id, ");
		sql.append("        user_kind, ");
		sql.append("        user_name, ");
		sql.append("        login_address, ");
		sql.append("        login_date, ");
		sql.append("        logout_date, ");
		sql.append("        login_flag, ");
		sql.append("        logout_flag, ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql.append("        if((instr(remark, 'xception') - 1) = -1, remark, '') as remark ");
			sql.append("   from sys_login_log");
			sql.append("  where login_date between date_format(?, '%Y-%m-%d %H:%i:%s') and ");
			sql.append("        date_format(?, '%Y-%m-%d %H:%i:%s') ");
		}else {
			sql.append("        decode(instr(remark, 'xception') - 1, -1, remark, '') as remark ");
			sql.append("   from sys_login_log");
			sql.append("  where login_date between to_date(?, 'YYYY-MM-DD HH24:MI:SS') and ");
			sql.append("        to_date(?, 'YYYY-MM-DD HH24:MI:SS') ");
		}
		sql.append("    and user_id <> 0 ");
		
		params.add(map.get("begin_date"));
		params.add(map.get("end_date"));

		SqlHelper.addIntValues(map.get("user_kind"), "user_kind", sql, params);
		
		SqlHelper.addInt(UtilFunc.getString(map, "user_id"), "user_id", "=", sql, params);

		String[] cols = new String[] { "login_user", "user_name" };
		for (int i = 0; i < cols.length; i++) {
			SqlHelper.addStringExp(UtilFunc.getString(map, cols[i]),
					cols[i], sql, params);
		}

		sql.append("  order by login_date");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}
	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.LogDAO#analysisLoginLogByUser(java.util.Map)
	 */
	public List analysisLoginLogByUser(Map map) {
		ArrayList params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append(" select user_kind, ");
		sql.append("        user_id, ");
		sql.append("        max(login_user) as login_user, ");
		sql.append("        max(user_name) as user_name, ");
		sql.append("        count(distinct(login_id)) as login_num, ");
		sql.append("        sum(decode(login_flag, 1, 1, 0)) as success_num, ");
		sql.append("        sum(decode(login_flag, 0, 1, 0)) as error_num, ");
		sql.append("        min(login_date) as min_login_date, ");
		sql.append("        max(login_date) as max_login_date, ");
		sql.append("        min(logout_date) as min_logout_date, ");
		sql.append("        max(logout_date) as max_logout_date ");
		sql.append("   from sys_login_log ");
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql.append("  where login_date between date_format(?, '%Y-%m-%d %H:%i:%s') and ");
			sql.append("        date_format(?, '%Y-%m-%d %H:%i:%s') ");
		}else {
			sql.append("  where login_date between to_date(?, 'YYYY-MM-DD HH24:MI:SS') and ");
			sql.append("        to_date(?, 'YYYY-MM-DD HH24:MI:SS') ");
		}
		sql.append("    and user_id <> 0 ");
		
		params.add(map.get("begin_date"));
		params.add(map.get("end_date"));

		SqlHelper.addIntValues(map.get("user_kind"), "user_kind", sql, params);
		
		SqlHelper.addInt(UtilFunc.getString(map, "user_id"), "user_id", "=", sql, params);
		
		String[] cols = new String[] { "login_user", "user_name" };
		for (int i = 0; i < cols.length; i++) {
			SqlHelper.addStringExp(UtilFunc.getString(map, cols[i]),
					cols[i], sql, params);
		}

		sql.append("  group by user_kind, user_id ");
		sql.append("  order by user_kind desc, login_user");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}
	
	/* (non-Javadoc)
	 * @see com.powersi.sys.manager.dao.LogDAO#analysisLoginLogByDate(java.util.Map)
	 */
	public List analysisLoginLogByDate(Map map) {
		ArrayList params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql.append(" select date_format(login_date, '%Y-%m-%d') as login_date, ");
			sql.append("        count(distinct(login_id)) as login_num, ");
			sql.append("        sum(if(login_flag = 1, 1, 0)) as success_num, ");
			sql.append("        sum(if(login_flag = 0, 1, 0)) as error_num, ");
			sql.append("        count(distinct(concat(user_kind,'-',user_id)) as login_sum ");
			sql.append("   from sys_login_log ");
			sql.append("  where login_date between date_format(?, '%Y-%m-%d %H:%i:%s') and ");
			sql.append("        date_format(?, '%Y-%m-%d %H:%i:%s') ");
		}else {
			sql.append(" select to_char(login_date, 'YYYY-MM-DD') as login_date, ");
			sql.append("        count(distinct(login_id)) as login_num, ");
			sql.append("        sum(decode(login_flag, 1, 1, 0)) as success_num, ");
			sql.append("        sum(decode(login_flag, 0, 1, 0)) as error_num, ");
			sql.append("        count(distinct(user_kind || '-' || user_id)) as login_sum ");
			sql.append("   from sys_login_log ");
			sql.append("  where login_date between to_date(?, 'YYYY-MM-DD HH24:MI:SS') and ");
			sql.append("        to_date(?, 'YYYY-MM-DD HH24:MI:SS') ");
		}
		
		sql.append("    and user_id <> 0 ");
		
		params.add(map.get("begin_date"));
		params.add(map.get("end_date"));

		SqlHelper.addIntValues(map.get("user_kind"), "user_kind", sql, params);
		
		SqlHelper.addInt(UtilFunc.getString(map, "user_id"), "user_id", "=", sql, params);

		String[] cols = new String[] { "login_user", "user_name" };
		for (int i = 0; i < cols.length; i++) {
			SqlHelper.addStringExp(UtilFunc.getString(map, cols[i]),
					cols[i], sql, params);
		}

		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql.append("  group by date_format(login_date, '%Y-%m-%d') ");
		}else {
			sql.append("  group by to_char(login_date, 'YYYY-MM-DD') ");
		}

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}
}
