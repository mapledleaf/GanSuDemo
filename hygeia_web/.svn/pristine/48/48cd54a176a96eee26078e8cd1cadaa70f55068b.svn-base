/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.message.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.sys.entity.SysMessage;
import com.powersi.sys.message.dto.SearchMessageDTO;

/**
 * The Class MessageDAOImpl.
 */
public class MessageDAOImpl extends BaseDAOImpl implements MessageDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.MessageDAO#addMessage(com.powersi.sys.entity
	 * .SysMessage)
	 */
	public int addMessage(SysMessage sysMessage) {
		Map map = new HashMap();
		if (!UtilFunc.isEmpty(sysMessage.getMsId())) {
			map.put("ms_id", sysMessage.getMsId());
		}
		if (!UtilFunc.isEmpty(sysMessage.getMsTitle())) {
			map.put("ms_title", sysMessage.getMsTitle());
		}
		if (!UtilFunc.isEmpty(sysMessage.getMsContent())) {
			map.put("ms_content", sysMessage.getMsContent());
		}
		if (!UtilFunc.isEmpty(sysMessage.getCenterId())) {
			map.put("center_id", sysMessage.getCenterId());
		}
		if (!UtilFunc.isEmpty(sysMessage.getSendOrg())) {
			map.put("sender_org", sysMessage.getSendOrg());
		}
		if (!UtilFunc.isEmpty(sysMessage.getSendStaff())) {
			map.put("sender_staff", sysMessage.getSendStaff());
		}
		if (!UtilFunc.isEmpty(sysMessage.getSendMan())) {
			map.put("sender_man", sysMessage.getSendMan());
		}
		if (!UtilFunc.isEmpty(sysMessage.getSendDate())) {
			map.put("send_date", sysMessage.getSendDate());
		}
		if (!UtilFunc.isEmpty(sysMessage.getReveiveManNames())) {
			map.put("receive_mans", sysMessage.getReveiveManNames());
		}
		if (!UtilFunc.isEmpty(sysMessage.getMsType())) {
			map.put("ms_type", sysMessage.getMsType());
		}
		if (!UtilFunc.isEmpty(sysMessage.getMsUrl())) {
			map.put("ms_url", sysMessage.getMsUrl());
		}
		map.put("valid_flag", "1");

		int ret = DAOHelper.insert(TABLE_SYS_MESSAGE, map);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.MessageDAO#delMessage(java.util.List,
	 * java.lang.String, java.lang.String)
	 */
	public int delMessage(List msidList, String msFloder) {
		if (CollectionHelper.isEmpty(msidList)) {
			return 0;
		}

		StringBuilder sqlBuffer = new StringBuilder();

		sqlBuffer.append(" update sys_message_staff t ");
		sqlBuffer.append("    set t.valid_flag = '0' ");
		sqlBuffer.append("  where t.receive_staff = ? ");
		sqlBuffer.append("    and t.ms_folder = ? ");
		sqlBuffer.append("    and t.valid_flag = '1' ");
		sqlBuffer.append("    and t.ms_id in (");

		for (int i = 0; i < msidList.size(); i++) {
			Integer msid = (Integer) msidList.get(i);
			if (i == (msidList.size() - 1)) {
				sqlBuffer.append(msid);
			} else {
				sqlBuffer.append(msid + ",");
			}
		}
		sqlBuffer.append(")");

		return DBHelper.executeUpdate(sqlBuffer.toString(), new Object[] {
				getUserInfo().getUserId(), msFloder });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.MessageDAO#updateMessage(com.powersi.sys.
	 * entity.SysMessage)
	 */
	public int updateMessage(SysMessage sysMessage) {
		return DAOHelper.update(TABLE_SYS_MESSAGE, sysMessage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.MessageDAO#getMessage(java.lang.String)
	 */
	public Map getMessage(String msId) {
		String sql = "select * from sys_message where ms_id = ?";
		return DBHelper.executeMap(sql, new Object[] { msId });
	}

	/**
	 * Gets the message list.
	 * 
	 * @param searchMessageVo
	 *            the search message vo
	 * @return the message list
	 */
	public List getMessageList(SearchMessageDTO searchMessageVo) {
		List params = new ArrayList();
		// 如果为收件箱,则收信人为登录用户
		StringBuilder sql = null;
		if ("1".equals(searchMessageVo.getMsFloder())) {
			sql = new StringBuilder(
					" select mes.* ,mest.ms_folder,mest.receive_org,mest.receive_staff,mest.receive_man as receiveman,mest.receive_date,mest.receive_statue ");
			sql.append(
					"  from sys_message mes,sys_message_staff mest where mes.ms_id = mest.ms_id and mest.valid_flag = '1' and mest.ms_folder =  '1' and mest.receive_staff = '")
					.append(searchMessageVo.getLoginStaff()).append("'");

			SqlHelper.addDatetime(searchMessageVo.getBeginDate(),
					"mes.send_date", ">=", sql, params);
			SqlHelper.addDatetime(searchMessageVo.getEndDate(),
					"mes.send_date", "<=", sql, params);

			SqlHelper.addStringExp(searchMessageVo.getMsTitle(),
					"mes.ms_title", sql, params);
			SqlHelper.addStringExp(searchMessageVo.getMan(), "mes.sender_man",
					sql, params);

			if (!"all".equals(searchMessageVo.getStatus())) {
				SqlHelper.addString(searchMessageVo.getStatus(),
						"mest.receive_statue", "=",
						sql, params);
			}

			sql.append(" order by mest.receive_statue,mes.send_date desc");
		} else {
			// 如果为发件箱,则发信人为登录用户
			sql = new StringBuilder(
					" select mes.*, '2' as ms_folder from sys_message mes where mes.valid_flag = '1' and mes.sender_staff ='")
					.append(searchMessageVo.getLoginStaff()).append("'");
			sql.append("    and  exists (select 1 from sys_message_staff staff where staff.ms_id = mes.ms_id and staff.ms_folder = '2' and staff.ms_type = '1' and staff.valid_flag = '1') ");

			SqlHelper.addDatetime(searchMessageVo.getBeginDate(),
					"mes.send_date", ">=", sql, params);
			SqlHelper.addDatetime(searchMessageVo.getEndDate(),
					"mes.send_date", "<=", sql, params);

			SqlHelper.addStringExp(searchMessageVo.getMsTitle(),
					"mes.ms_title", sql, params);
			SqlHelper.addStringExp(searchMessageVo.getMan(),
					"mes.receive_mans", sql, params);

			sql.append("order by mes.send_date desc");
		}

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.MessageDAO#getPersonList()
	 */
	public List getPersonList() {
		String sql = "select dep_staff.*\n" +
				"  from (select dept.dept_id as dept_id,\n" +
				"               0 as staff_id,\n" +
				"               dept.dept_name,\n" +
				"               dept.short_name,\n" +
				"               dept.dept_up_id,\n" +
				"               dept.dis_order,\n" +
				"               dept.center_id,\n" +
				"               to_number(dept.dept_type) as dept_type,\n" +
				"               '0' as isstaff\n" +
				"          from sys_dept dept\n" +
				"        union all\n" +
				"        select 999999 as dept_id,\n" +
				"               0 as staff_id,\n" +
				"               '其他' as dept_name,\n" +
				"               '其他' as short_name,\n" +
				"               0 as dept_up_id,\n" +
				"               999999 as dis_order,\n" +
				"               null as center_id,\n" +
				"               2 as dept_type,\n" +
				"               '0' as isstaff\n" +
				"          from dual\n" +
				"        union all\n" +
				"        select (0 - staff.staff_id) as dept_id,\n" +
				"               staff.staff_id,\n" +
				"               staff.staff_name as dept_name,\n" +
				"               staff.login_user as short_name,\n" +
				"               nvl(staff.dept_id, 999999) as dept_up_id,\n" +
				"               (999999 + staff.dis_order) as dis_order,\n" +
				"               staff.center_id,\n" +
				"               0 as dept_type,\n" +
				"               '1' as isstaff\n" +
				"          from sys_staff staff) dep_staff\n" +
				" start with dep_staff.dept_up_id = 0\n" +
				"connect by prior dep_staff.dept_id = dep_staff.dept_up_id\n" +
				" order siblings by dep_staff.dis_order, dep_staff.dept_up_id";

		return DBHelper.executeList(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.MessageDAO#addMessageStaff(java.util.Map)
	 */
	public int addMessageStaff(Map mesStaMap) {
		return DAOHelper.insert(TABLE_SYS_MESSAGE_STAFF, mesStaMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.MessageDAO#addMessageStaff(java.util.List)
	 */
	public int addMessageStaff(List mesStaList) {
		return DAOHelper.insert(TABLE_SYS_MESSAGE_STAFF, mesStaList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.MessageDAO#updateMessageLastTime(java.util
	 * .List)
	 */
	public int updateMessageLastTime(List mesStaList) {
		StringBuilder sql = new StringBuilder();

		sql.append(" update sys_user ");
		sql.append("    set message_lasttime = sysdate ");
		sql.append("  where user_id = :receive_staff ");
		sql.append("    and user_kind = 9 ");

		return DBHelper.batchUpdate(sql.toString(), mesStaList).length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.MessageDAO#changeReadStat(java.util.List,
	 * java.lang.String)
	 */
	public int changeReadStat(List msidList) {
		if (CollectionHelper.isEmpty(msidList)) {
			return 0;
		}

		StringBuilder sqlBuffer = new StringBuilder();

		sqlBuffer.append(" update sys_message_staff t ");
		sqlBuffer
				.append("    set t.receive_statue = '1', t.receive_date = sysdate ");
		sqlBuffer.append("  where t.receive_staff = ? ");
		sqlBuffer.append("    and t.ms_folder = '1' ");
		sqlBuffer.append("    and t.valid_flag = '1' ");
		sqlBuffer.append("    and t.receive_statue = '0' ");
		sqlBuffer.append("    and t.ms_id in (");

		for (int i = 0; i < msidList.size(); i++) {
			if (i > 0) {
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(msidList.get(i));
		}
		sqlBuffer.append(")");

		return DBHelper.executeUpdate(sqlBuffer.toString(),
				new Object[] { getUserInfo().getUserId() });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.MessageDAO#existUnreadMessage()
	 */
	public long existUnreadMessage() {
		String sql = "select count(1) from sys_message_staff t\n" +
				"where t.receive_statue = '0'\n" +
				"and t.ms_folder = '1'\n" +
				"and t.valid_flag = '1'\n" +
				"and t.receive_staff = ?";

		return DBHelper.executeLong(sql, new Object[] { getUserInfo()
				.getUserId() });
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.MessageDAO#getMessageReceive(java.lang.String
	 * )
	 */
	public List getMessageReceive(String msId) {
		return DBHelper
				.executeList(
						"select * from sys_message_staff where ms_id = ? and ms_folder = '1'",
						new Object[] { msId });
	}
	
	public List getBulletinsInfo(String akb020) {
		String sql = "SELECT * FROM (SELECT a.bulletin_id,\n" +
				" 					 a.bulletin_type,\n" +
				"                    a.bulletin_title,\n" +
				"                IF(bulletin_type = 1 ,bulletin_content,bulletin_id) AS bulletin_url,\n" +
				"               DATE_FORMAT(IFNULL(a.second_audit_date, a.send_date),'%Y-%m-%d') AS bulletin_date,\n" +
				"               a.effect_date,\n" +
				"               a.expire_date,\n" +
				"               IF(a.dis_order = 0 , 999999, a.dis_order) AS dis_order,\n" +
				"               IFNULL(a.user_kind, 0) AS user_kind,\n" +
				"               IFNULL(a.second_audit_date, a.send_date) AS audit_date,\n" +
				"          		a.sender_man\n" +
				"              FROM sys_bulletins a\n" +
				"         WHERE a.expire_date >= SYSDATE()\n" +
				"               AND a.audit_flag = '1'\n" +
				" 				AND a.second_audit_flag = '1'\n"+
				"				AND a.valid_flag = '1' \n" +
			    "               AND a.bulletin_id in (select BULLETIN_ID from sys_bulletin_receive where  receive_org in (? ,'0'))) t \n"+
				" ORDER BY t.dis_order ASC, t.audit_date DESC, t.bulletin_id";
		return DBHelper.executeList(sql,new Object[] { akb020 });
	}
}
