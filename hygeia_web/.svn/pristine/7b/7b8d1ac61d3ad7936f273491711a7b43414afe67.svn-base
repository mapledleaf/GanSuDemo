/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.message.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.powersi.hygeia.comm.entity.AZE1;
import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.SqlHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.powerreport.service.store.PowerReportStore;
import com.powersi.sys.entity.FileBulletins;
import com.powersi.sys.entity.SysBulletins;
import com.powersi.sys.message.dto.SearchBulletinDTO;
import com.powersi.sys.util.GradeHelper;

/**
 * The Class BulletinDAOImpl.
 */
public class BulletinDAOImpl extends BaseDAOImpl implements BulletinDAO {
	
	/** 用户级别. */
	private static int MIN_GRADE_ID = 3;
	
	//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
	boolean checkRight = !ParameterMapping.getStringByCode("check_conn_mysql", "0").equals("0");
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.BulletinDAO#queryList(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	public List queryList(SearchBulletinDTO searchBulletinDto) {
		UserInfo user = getUserInfo();
		int gradeId = GradeHelper.getUserGradeId(user);

		ArrayList params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append(" select bu.bulletin_id, ");
		sql.append("        bu.dis_order, ");
		sql.append("        bu.bulletin_type, ");
		sql.append("        bu.bulletin_title, ");
/*		sql.append("        decode(bu.bulletin_type, ");
		sql.append("               '1', ");
		sql.append("               bu.bulletin_content, ");
		sql.append("               to_char(bu.bulletin_id)) as bulletin_url, ");*/
		sql.append("        bu.file_batch, ");
		sql.append("        bu.sender_man, ");
		sql.append("        bu.send_date, ");
		sql.append("        bu.effect_date, ");
		sql.append("        bu.expire_date, ");
		sql.append("        bu.audit_flag, ");
		sql.append("        bu.audit_man, ");
		sql.append("        bu.second_audit_flag, ");
		sql.append("        bu.second_audit_man, ");
		sql.append("        bu.reply_flag, ");
		sql.append("        bu.audit_date, ");
		sql.append("        bu.audit_opinion, ");
		sql.append("        bu.second_audit_date, ");
		sql.append("        bu.second_audit_opinion, ");
		sql.append("   user_kind ");
		sql.append("   from sys_bulletins bu ");

		if (UtilFunc.hasLength(searchBulletinDto.getBulletinId())) {
			sql.append("  where bu.bulletin_id = ?");
			sql.append("    and bu.valid_flag = '1' ");

			params.add(searchBulletinDto.getBulletinId());
		} else {
			sql.append("  where bu.send_date BETWEEN (?) ");
			sql.append("    and (?) ");
			sql.append("    and bu.valid_flag = '1' ");

			params.add(searchBulletinDto.getBeginDate());
			params.add(searchBulletinDto.getEndDate());

			SqlHelper.addStringExp(searchBulletinDto.getSenderMan(),
					"bu.sender_man", sql, params);
			SqlHelper.addStringExp(searchBulletinDto.getBulletinTitle(),
					"bu.bulletin_title", sql, params);
			SqlHelper.addStringExp(searchBulletinDto.getBulletinContent(),
					"bu.bulletin_content", sql, params);
			
			SqlHelper.addStringValues(searchBulletinDto.getAuditFlag(),
					"bu.audit_flag", sql, params);
			//增加一个接受者查询
		/*	SqlHelper.addStringValues(searchBulletinDto.getReceive_users(),
					"bu.receive_users", sql, params);	*/
			sql.append("  order by bu.send_date desc ");
		}

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#getBulletin(java.lang.String)
	 */
	public SysBulletins getBulletin(Long bulletinId) {
		Map paramMap = new HashMap();

		paramMap.put("bulletin_id", bulletinId);
		paramMap.put("valid_flag", "1");

		return DAOHelper.queryForObject(TABLE_NAME, paramMap, new String[] {
				"bulletin_id", "valid_flag" }, SysBulletins.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#insertBulletin(com.powersi.sys
	 * .message.entity.SysBulletins)
	 */
	public int insertBulletin(SysBulletins sysBulletin) {
		return DAOHelper.insert(TABLE_NAME, sysBulletin);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#updateBulletin(com.powersi.sys
	 * .message.entity.SysBulletins)
	 */
	public int updateBulletin(SysBulletins sysBulletin) {
		return DAOHelper.updateWithNotNull(TABLE_NAME, sysBulletin, null, new String[] {
				"bulletin_id"});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.BulletinDAO#getUserList(java.lang.Long)
	 */
	public List<String> getUserList(Long bulletinId) {
		List<Object[]> lst = DBHelper
				.executeArrayList(
						"select user_kind from sys_bulletin_user where bulletin_id = ?",
						new Object[] { bulletinId });

		List userKind = new ArrayList<String>();
		for (Object[] obj : lst) {
			userKind.add(obj[0].toString());
		}

		return userKind;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#insertUserList(java.lang.Long,
	 * java.util.List)
	 */
	public int insertUserList(Long bulletinId, List<String> userKind) {
		DBHelper.update("delete from sys_bulletin_user where bulletin_id = ?",
				new Object[] { bulletinId });

		for (String str : userKind) {
			DBHelper.update(
					"insert into sys_bulletin_user(bulletin_id, user_kind) values(?, ?)",
					new Object[] { bulletinId, str });
		}

		return CollectionHelper.size(userKind);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#deleteBulletin(java.lang.Long)
	 */
	public int deleteBulletin(Long bulletinId) {
		int ret = DBHelper
				.update("update sys_bulletins set valid_flag = '0' where bulletin_id = ? and valid_flag = '1' and audit_flag = '0'",
						new Object[] { bulletinId });
		if (ret > 0) {
			DBHelper.update(
					"update sys_bulletin_receive set valid_flag = '0' where bulletin_id = ? and valid_flag = '1'",
					new Object[] { bulletinId });
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#auditBulletin(java.lang.Long)
	 */
	public int auditBulletin(Long bulletinId, String auditType,
			String auditFlag, String auditOpinion) {
		// 审核自动推迟1分钟，保证查询
		String sql = null;
		int ret = 0;
		if ("1".equals(auditType)) {
			sql = "update sys_bulletins set audit_flag = ?, second_audit_flag = '1', audit_staff = ?, audit_man = ?, audit_opinion = ?, audit_date = "+ (checkRight == true ?"now()":"sysdate") +" where bulletin_id = ? and valid_flag = '1' and audit_flag = '0'";
			ret = DBHelper.update(sql, new Object[] {
					auditFlag, getUserInfo().getAdminId(), getUserInfo().getUserName(), auditOpinion,
					bulletinId });
		} else if ("2".equals(auditType)) {
			sql = "update sys_bulletins set audit_flag = ?, second_audit_flag = ?, second_audit_staff = ?, second_audit_man = ?, second_audit_opinion = ?, second_audit_date = ("+ (checkRight == true ?"now()":"sysdate") +" + 1 / (24*60)) where bulletin_id = ? and valid_flag = '1' and second_audit_flag = '0'";
			ret = DBHelper.update(sql, new Object[] {
					auditFlag, auditFlag, getUserInfo().getAdminId(), getUserInfo().getUserName(), auditOpinion,
					bulletinId });
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#cancelBulletin(java.lang.Long)
	 */
	public int cancelBulletin(Long bulletinId, String auditType) {
		String sql = null;
		int ret = 0;
		if ("1".equals(auditType)) {
			sql = "update sys_bulletins set audit_flag = '0', audit_staff = null, audit_man = null, audit_date = null where bulletin_id = ? and valid_flag = '1' and audit_flag = '1'";
			ret = DBHelper.update(sql, new Object[] { bulletinId });
		} else if ("2".equals(auditType)) {
			sql = "update sys_bulletins set second_audit_flag = '0', second_audit_staff = null, second_audit_man = null, second_audit_date = null where bulletin_id = ? and valid_flag = '1' and second_audit_flag = '1'";
			ret = DBHelper.update(sql, new Object[] { bulletinId });
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.BulletinDAO#viewBulletin(java.lang.Long)
	 */
	public Map<String, Object> viewBulletin(Long bulletinId) {
		Map paramMap = new HashMap();
		paramMap.put("bulletin_id", bulletinId);
		paramMap.put("valid_flag", "1");

		return DAOHelper.queryForMap(TABLE_NAME, paramMap, new String[] {
				"bulletin_id", "valid_flag" });
	}

	/**
	 * 查询操作员消息.
	 * 
	 * @param limit
	 *            the limit
	 * @return the list
	 */
	private List queryStaffMessage(int limit) {
		String sql = "";
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql = "select *\n"
					+
					"  from (select *\n"
					+
					"          from (select 21 as ms_type,\n"
					+
					"                       '' as ms_title,\n"
					+
					"                       t.bulletin_title as ms_content,\n"
					+
					"                       ('message/BulletinManagerAction!bulletinInfo.action' ||\n"
					+
					"                       char(63) || 'bulletinId=' || t.bulletin_id) as diag_url,\n"
					+
					"                       600 as diag_height,\n"
					+
					"                       600 as diag_width,\n"
					+
					"                       t.sender_man,\n"
					+
					"                       to_char(t.second_audit_date, 'yyyy-mm-dd hh24:mi:ss') as send_date\n"
					+
					"                  from sys_bulletins t\n"
					+
					"                 where t.effect_date < now()\n"
					+
					"                   and (t.expire_date is null or t.expire_date >= now() )\n"
					+
					"                   and t.second_audit_flag = '1'\n"
					+
					"                   and t.valid_flag = '1'\n"
					+
					"                   and t.second_audit_date >=\n"
					+
					"                       (select ifnull(message_querytime, now() - 1)\n"
					+
					"                          from sys_user\n"
					+
					"                         where user_id = :user_id\n"
					+
					"                           and user_kind = :user_kind)\n"
					+
					"                   and (t.user_kind = '0' or t.user_kind = :user_kind)\n"
					+
					"                   and exists (select 'X'\n"
					+
					"                          from sys_bulletin_receive br\n"
					+
					"                         where br.bulletin_id = t.bulletin_id\n"
					+
					"                           and br.receive_org in (:receive_org)\n"
					+
					"                           and br.valid_flag = '1')\n" +
					"                 order by t.second_audit_date desc limit " + String.valueOf(limit)
					+ "        ) table1  " 
					+ "\n"
					+
					"        union all\n"
					+
					"        select 11 as ms_type,\n"
					+
					"               '' as ms_title,\n"
					+
					"               '您有 ' || count(1) || ' 条新消息' as ms_content,\n"
					+
					"               '/message/MessageAction!managerMessage.action' as diag_url,\n"
					+
					"               600 as diag_height,\n"
					+
					"               800 as diag_width,\n"
					+
					"               '' as sender_man,\n"
					+
					"               to_char(now(), 'yyyy-mm-dd hh24:mi:ss') as send_date\n"
					+
					"          from sys_message_staff ms\n"
					+
					"         where ms.send_date >= (select ifnull(message_querytime, now() - 1)\n"
					+
					"                                  from sys_user\n"
					+
					"                                 where user_id = :user_id\n"
					+
					"                                   and user_kind = :user_kind)\n"
					+
					"           and ms.ms_folder = '1'\n" +
					"           and ms.valid_flag = '1'\n" +
					"           and ms.receive_statue = '0'\n" +
					"           and ms.receive_staff = to_char(:user_id)\n" +
					"         group by ms.receive_staff\n" +
					"        having count(1) > 0) tablesum \n" +
					" order by ms_type desc, send_date asc";
		}else {
			sql = "select *\n"
				+
				"  from (select *\n"
				+
				"          from (select 21 as ms_type,\n"
				+
				"                       '' as ms_title,\n"
				+
				"                       t.bulletin_title as ms_content,\n"
				+
				"                       ('message/BulletinManagerAction!bulletinInfo.action' ||\n"
				+
				"                       chr(63) || 'bulletinId=' || t.bulletin_id) as diag_url,\n"
				+
				"                       600 as diag_height,\n"
				+
				"                       600 as diag_width,\n"
				+
				"                       t.sender_man,\n"
				+
				"                       to_char(t.second_audit_date, 'yyyy-mm-dd hh24:mi:ss') as send_date\n"
				+
				"                  from sys_bulletins t\n"
				+
				"                 where t.effect_date < sysdate\n"
				+
				"                   and (t.expire_date is null or t.expire_date >= sysdate)\n"
				+
				"                   and t.second_audit_flag = '1'\n"
				+
				"                   and t.valid_flag = '1'\n"
				+
				"                   and t.second_audit_date >=\n"
				+
				"                       (select nvl(message_querytime, sysdate - 1)\n"
				+
				"                          from sys_user\n"
				+
				"                         where user_id = :user_id\n"
				+
				"                           and user_kind = :user_kind)\n"
				+
				"                   and (t.user_kind = '0' or t.user_kind = :user_kind)\n"
				+
				"                   and exists (select 'X'\n"
				+
				"                          from sys_bulletin_receive br\n"
				+
				"                         where br.bulletin_id = t.bulletin_id\n"
				+
				"                           and br.receive_org in (:receive_org)\n"
				+
				"                           and br.valid_flag = '1')\n" +
				"                 order by t.second_audit_date desc)\n" +
				"         where rownum <= "
				+ String.valueOf(limit)
				+ "\n"
				+
				"        union all\n"
				+
				"        select 11 as ms_type,\n"
				+
				"               '' as ms_title,\n"
				+
				"               '您有 ' || count(1) || ' 条新消息' as ms_content,\n"
				+
				"               '/message/MessageAction!managerMessage.action' as diag_url,\n"
				+
				"               600 as diag_height,\n"
				+
				"               800 as diag_width,\n"
				+
				"               '' as sender_man,\n"
				+
				"               to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') as send_date\n"
				+
				"          from sys_message_staff ms\n"
				+
				"         where ms.send_date >= (select nvl(message_querytime, sysdate - 1)\n"
				+
				"                                  from sys_user\n"
				+
				"                                 where user_id = :user_id\n"
				+
				"                                   and user_kind = :user_kind)\n"
				+
				"           and ms.ms_folder = '1'\n" +
				"           and ms.valid_flag = '1'\n" +
				"           and ms.receive_statue = '0'\n" +
				"           and ms.receive_staff = to_char(:user_id)\n" +
				"         group by ms.receive_staff\n" +
				"        having count(1) > 0)\n" +
				" order by ms_type desc, send_date asc";
		}
		
		Map params = new HashMap(getUserInfo());
		String deptUpList = UtilFunc.getString(params, "dept_up_list", "0");
		params.put("receive_org",
				UtilFunc.arrayToList(UtilFunc.split(deptUpList, ",")));

		return DBHelper.executeList(sql, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.BulletinDAO#getQueryMessage()
	 */
	public List getQueryMessage() {
		return queryStaffMessage(1000);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.BulletinDAO#queryListByLogin()
	 */
	public List getLoginMessage() {
		return queryStaffMessage(10);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.BulletinDAO#hasNewMessage()
	 */
	public boolean hasNewMessage() {
		UserInfo userInfo = getUserInfo();

		String sql = "select 1 as cnt\n" +
				"  from (select message_lasttime, message_querytime\n" +
				"          from sys_user\n" +
				"         where user_id = ?\n" +
				"           and user_kind = ?) u\n" +
				" where u.message_lasttime > u.message_querytime\n" +
				"    or exists (select 'X'\n" +
				"          from sys_cache_config\n" +
				"         where cache_name = 'bulletinlist'\n" +
				"           and last_date > u.message_querytime)";

		return DBHelper.executeInt(
				sql,
				new Object[] { userInfo.getUserId(),
						userInfo.getUserKind() }) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.BulletinDAO#updateQueryMessage()
	 */
	public int updateQueryMessage() {
		String sql = "";
		//是否使用的mysql数据库   2016-09-09 lingang hygeia_web修改支持mysql数据库
		if(checkRight){
			sql = "update sys_user\n" +
					"   set message_querytime = now() \n" +
					" where user_id = :user_id\n" +
					"   and user_kind = :user_kind";
		}else {
			sql = "update sys_user\n" +
					"   set message_querytime = sysdate\n" +
					" where user_id = :user_id\n" +
					"   and user_kind = :user_kind";
		}
		return DBHelper.update(sql, getUserInfo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.BulletinDAO#getFileList(java.lang.Long)
	 */
	public List getFileList(Long bulletinId) {
		String sql = "select bulletin_id,\n" +
				"       file_id,\n" +
				"       file_name,\n" +
				"       file_type,\n" +
				"       file_desc,\n" +
				"       file_length,\n" +
				"       edit_date,\n" +
				"       edit_staff,\n" +
				"       edit_man,\n" +
				"       last_date\n" +
				"  from sys_bulletin_file\n" +
				" where bulletin_id = ?\n" +
				"   and valid_flag = '1'";

		return DBHelper.executeList(sql, new Object[] { bulletinId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.BulletinDAO#deleteFile(java.lang.Long,
	 * java.lang.String[])
	 */
	public int deleteFile(Long bulletinId, String[] files) {
		if (files == null || files.length == 0) {
			return 0;
		}

		StringBuilder sql = new StringBuilder();

		sql.append(" update sys_bulletin_file ");
		sql.append("   set valid_flag = '0', last_date = "+ (checkRight == true ?"now()":"sysdate") +" ");
		sql.append(" where bulletin_id = ? ");
		sql.append("   and file_id in (")
				.append(UtilFunc.toDelimitedString(files, ",", "'", "'"))
				.append(")");

		return DBHelper.update(sql.toString(), new Object[] { bulletinId });
	}
/**
 * 插入附件上传ftp所需
 */
	private String tempPath;
	public String getTempPath() {
		if (StringUtils.isBlank(this.tempPath)) {
			this.tempPath = System.getProperty("java.io.tmpdir");
		}
		// 判断是否是“/“结尾,On Linux: java.io.tmpdir: [/tmp] but On Windows:
		// java.io.tmpdir:[C:\DOCUME~1\joshua\LOCALS~1\Temp\]
		if (!this.tempPath.endsWith(System.getProperty("file.separator"))) {
			this.tempPath = this.tempPath + System.getProperty("file.separator");
		}
		return this.tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.BulletinDAO#insertFile(java.lang.Long,
	 * java.util.List, java.util.List, java.util.List)
	 */
	public int insertFile(Long bulletinId, List<File> files,
			List<String> names, List<String> types) {
		if (CollectionHelper.isEmpty(files)) {
			return 0;
		}
		
		String staffId = getUserInfo().getUserId();
		String staffName = getUserInfo().getUserName();

		/*String sql = "insert into sys_bulletin_file\n" +
				"  (bulletin_id,\n" +
				"   file_id,\n" +
				"   file_name,\n" +
				"   file_type,\n" +
				"   file_desc,\n" +
				"   file_content,\n" +
				"   file_length,\n" +
				"   edit_date,\n" +
				"   edit_staff,\n" +
				"   edit_man,\n" +
				"   last_date,\n" +
				"   valid_flag)\n" +
				"values\n" +
				"  (:bulletin_id,\n" +
				"   :file_id,\n" +
				"   :file_name,\n" +
				"   :file_type,\n" +
				"   :file_desc,\n" +
				"   empty_blob(),\n" +
				"   :file_length,\n" +
				"   "+ (checkRight == true ?"now()":"sysdate") +",\n" +
				"   :edit_staff,\n" +
				"   :edit_man,\n" +
				"   "+ (checkRight == true ?"now()":"sysdate") +",\n" +
				"   '1')";*/

		
		PowerReportStore powerReportStore = (PowerReportStore) BeanHelper.getBean("PowerReportStore");
		// 如果是sftp或者ftp返回的是上传的文件路径
		FileBulletins f=new FileBulletins();
		f.setBulletin_id(bulletinId);
		f.setValid_flag("1");
				for (int i = 0; i < files.size(); i++) {
					InputStream in=null;
					try {
						in = new FileInputStream(files.get(i));
					} catch (Exception e) {
						throw new HygeiaException("保存公告附件出错", e);
					}
					String store_id = powerReportStore.store(in, "file/"+names.get(i));
					f.setFile_name(names.get(i));
					String fileid = UtilFunc.getUUID();
					f.setFile_id(fileid);
					f.setFile_desc(store_id);
					return DAOHelper.insert("sys_bulletin_file",f);
				}
		
		
	//	String updateSql = "select file_content from sys_bulletin_file where bulletin_id = ? and file_id = ?";
			
		/*for (int i = 0; i < files.size(); i++) {
			String fileId = UtilFunc.getUUID();

			Map map = new HashMap();

			map.put("bulletin_id", bulletinId);
			map.put("file_id", fileId);
			map.put("file_name", names.get(i));
			map.put("file_type", types.get(i));
			map.put("file_desc", "");
			map.put("file_length", Long.valueOf(files.get(i).length()));
			map.put("edit_staff", staffId);
			map.put("edit_man", staffName);

			DBHelper.update(sql, map);

			InputStream in = null;
			try {
				in = new FileInputStream(files.get(i));
				DBHelper.blobUpdate(updateSql, new Object[] { bulletinId, fileId }, in);
			} catch (Exception ex) {
				throw new HygeiaException("保存公告附件出错", ex);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}*/

		return files.size();
	}
	/**
	 * 通过对象来获取附件（其中有blob）
	 */
	public FileBulletins getFileAttch(Long bulletinId,String fileId,boolean fileContent){
		/*StringBuilder sql = new StringBuilder();

		sql.append(" select bulletin_id, ");
		sql.append("        file_id, ");
		sql.append("        file_name, ");
		sql.append("        file_type, ");
		if (fileContent) {
			sql.append("        file_content, ");
		}
		sql.append("        file_desc, ");
		sql.append("        file_length, ");
		sql.append("        edit_date, ");
		sql.append("        edit_staff, ");
		sql.append("        edit_man, ");
		sql.append("        last_date ");
		sql.append("   from sys_bulletin_file ");
		sql.append("  where bulletin_id = ? ");
		sql.append("    and file_id = ? ");
		sql.append("    and valid_flag = '1' ");*/
		String[] wheres = { "bulletin_id","file_id","valid_flag" };
		Map map = new HashMap();
		map.put("bulletin_id", bulletinId);
		map.put("file_id", fileId);
		map.put("valid_flag", "1");
		FileBulletins fileBulletins = DAOHelper.queryForObject("sys_bulletin_file", map, wheres, FileBulletins.class);
		return fileBulletins;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.BulletinDAO#getFile(java.lang.Long,
	 * java.lang.String, boolean)
	 */
	public Map getFile(Long bulletinId, String fileId, boolean fileContent) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select bulletin_id, ");
		sql.append("        file_id, ");
		sql.append("        file_name, ");
		sql.append("        file_type, ");
		if (fileContent) {
			sql.append("        file_content, ");
		}
		sql.append("        file_desc, ");
		sql.append("        file_length, ");
		sql.append("        edit_date, ");
		sql.append("        edit_staff, ");
		sql.append("        edit_man, ");
		sql.append("        last_date ");
		sql.append("   from sys_bulletin_file ");
		sql.append("  where bulletin_id = ? ");
		sql.append("    and file_id = ? ");
		sql.append("    and valid_flag = '1' ");

		return DBHelper.executeMap(sql.toString(), new Object[] { bulletinId,
				fileId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#getQueryMessage(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public List getQueryMessage(String centerId, String receiveOrg,
			String receiveStaff) {
		String sql = "select t.ms_id,\n"
				+
				"       t.ms_type,\n"
				+
				"       t.ms_title,\n"
				+
				"       t.ms_content,\n"
				+
				"       t.center_id,\n"
				+
				"       t.sender_org,\n"
				+
				"       t.sender_staff,\n"
				+
				"       t.sender_man,\n"
				+
				"       t.sender_ip,\n"
				+
				"       t.send_date,\n"
				+
				"       t.receive_org,\n"
				+
				"       t.receive_staff,\n"
				+
				"       t.receive_man,\n"
				+
				"       t.receive_date,\n"
				+
				"       t.receive_statue,\n"
				+
				"       t.remark,\n"
				+
				"       t.valid_flag\n"
				+
				"  from (select b.bulletin_id as ms_id,\n"
				+
				"               '10' as ms_type,\n"
				+
				"               b.bulletin_title as ms_title,\n"
				+
				"               b.bulletin_content as ms_content,\n"
				+
				"               c.center_id,\n"
				+
				"               c.center_id as sender_org,\n"
				+
				"               b.sender_staff as sender_staff,\n"
				+
				"               b.sender_man,\n"
				+
				"               '' as sender_ip,\n"
				+
				"               to_char(b.audit_date, 'yyyy-mm-dd hh24:mi:ss') as send_date,\n"
				+
				"               c.receive_org,\n"
				+
				"               c.receive_staff,\n"
				+
				"               c.receive_man,\n"
				+
				"               to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') as receive_date,\n"
				+
				"               c.receive_statue,\n"
				+
				"               c.receive_man as remark,\n"
				+
				"               b.valid_flag\n"
				+
				"          from sys_bulletins b, sys_bulletin_hospital c\n"
				+
				"         where (c.receive_org = :center_id or c.receive_org = 'all')\n"
				+
				"           and (c.receive_staff = :receive_org or c.receive_staff = 'all')\n"
				+
				"           and c.valid_flag = '1'\n"
				+
				"           and c.bulletin_id = b.bulletin_id\n"
				+
				"           and b.audit_flag = '1'\n"
				+
				"           and b.valid_flag = '1'\n"
				+
				"           and b.audit_date > (select "+ (checkRight == true ?"nvl":"ifnull") +"(max(message_lastdate),\n"
				+
				"                                          to_date('2012-01-01 00:00:00',\n"
				+
				"                                                  'yyyy-mm-dd hh24:mi:ss')) as max_date\n"
				+
				"                                 from sys_hospital_staff s\n"
				+
				"                                where s.center_id = :center_id\n"
				+
				"                                  and s.staff_org = :receive_org\n"
				+
				"                                  and s.staff_id = :receive_staff)) t";

		Map map = new HashMap();

		map.put("center_id", centerId);
		map.put("receive_org", receiveOrg);
		map.put("receive_staff", receiveStaff);

		return DBHelper.executeList(sql, map);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#updateQueryMessage(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public int updateQueryMessage(String centerId, String receiveOrg,
			String receiveStaff, String receiveMan, String msId) {
		if (UtilFunc.isEmpty(msId)) {
			return 0;
		}

		Map map = new HashMap();

		map.put("center_id", centerId);
		map.put("receive_org", receiveOrg);
		map.put("receive_staff", receiveStaff);
		map.put("receive_man", receiveMan);

		{
			StringBuilder sql = new StringBuilder();

			sql.append(" select "+ (checkRight == true ?"nvl":"null") +"(max(audit_date), "+ (checkRight == true ?"now()":"sysdate") +") as last_date ");
			sql.append("   from sys_bulletins t ");
			sql.append("  where bulletin_id in (").append(msId).append(") ");

			map.put("last_date", DBHelper.executeScale(sql.toString()));
		}

		{
			String sql = "merge into sys_hospital_staff\n"
					+
					"using (select 'X' from dual) d\n"
					+
					"on (center_id = :center_id and staff_org = :receive_org and staff_id = :receive_staff)\n"
					+
					"when matched then\n"
					+
					"  update set message_lastdate = :last_date, staff_man = :receive_man\n"
					+
					"when not matched then\n"
					+
					"  insert\n"
					+
					"    (center_id, staff_org, staff_id, staff_man, message_lastdate)\n"
					+
					"  values\n"
					+
					"    (:center_id, :receive_org, :receive_staff, :receive_man, :last_date)";

			DBHelper.update(sql, map);
		}

		{
			StringBuilder sql = new StringBuilder();

			sql.append(" update sys_bulletin_hospital ");
			sql.append("    set receive_count = receive_count + 1 ");
			sql.append("    where bulletin_id in (" + msId + ") ");
			// sql.append("    and center_id = :center_id ");
			if (centerId.equals(receiveOrg)) {
				// sql.append("    and receive_org = :receive_org ");
			} else {
				sql.append("    and (receive_org = 'all' or  receive_org = :receive_org ) ");
			}
			sql.append("    and (receive_staff = 'all' or receive_staff = :receive_staff) ");

			DBHelper.update(sql.toString(), map);
		}

		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#hasNewMessage(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public List hasNewMessage(String centerId, String receiveOrg,
			String receiveStaff) {
		String sql = "select sum(t.nums) as counts\n"
				+
				"  from (select count(1) as nums\n"
				+
				"          from sys_bulletins b, sys_bulletin_hospital c\n"
				+
				"         where (c.receive_org = :center_id or c.receive_org = 'all')\n"
				+
				"           and (c.receive_staff = :receive_org or c.receive_staff = 'all')\n"
				+
				"           and c.valid_flag = '1'\n"
				+
				"           and c.bulletin_id = b.bulletin_id\n"
				+
				"           and b.audit_flag = '1'\n"
				+
				"           and b.valid_flag = '1'\n"
				+
				"           and b.audit_date >\n"
				+
				"               (select "+ (checkRight == true ?"nvl":"ifnull") +"(max(message_lastdate),\n"
				+
				"                           to_date('2012-01-01 00:00:00',\n"
				+
				"                                   'yyyy-mm-dd hh24:mi:ss')) as max_date\n"
				+
				"                  from sys_hospital_staff s\n" +
				"                 where s.center_id = :center_id\n" +
				"                   and s.staff_org = :receive_org\n" +
				"                   and s.staff_id = :receive_staff)) t";

		Map map = new HashMap();

		map.put("center_id", centerId);
		map.put("receive_org", receiveOrg);
		map.put("receive_staff", receiveStaff);

		return DBHelper.executeList(sql, map);
	}

	/**
	 * 查找同一统筹区内的部门树.
	 * 
	 * @param searchBulletinDto
	 *            the search bulletin dto
	 * @return the list
	 */
	@Override
	public List findCenterList(SearchBulletinDTO searchBulletinDto) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select dept_id, ");
		sql.append("        level as dept_level, ");
		sql.append("        dept_name, ");
		sql.append("        short_name, ");
		sql.append("        dept_up_id, ");
		sql.append("        dept_type, ");
		sql.append("        dis_order, ");
		sql.append("        center_id ");
		sql.append("   from sys_dept ");

		Long rootDeptId = null;
		UserInfo user = getUserInfo();

		int gradeId = Integer
				.valueOf(UtilFunc.getString(user, "grade_id", "0"));
		if (user.isSuperUser() || gradeId >= MIN_GRADE_ID) {
			rootDeptId = Long.valueOf(0);
			sql.append("  start with dept_up_id = ? ");
		} else {
			rootDeptId = Long
					.valueOf(UtilFunc.getString(user, "dept_id", "-1"));

			sql.append("  start with dept_id = ? ");
		}

		sql.append(" connect by prior dept_id = dept_up_id ");
		sql.append("  order siblings by dis_order, dept_id ");

		return DBHelper.executeList(sql.toString(),
				new Object[] { rootDeptId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#findHospList(com.powersi.sys.
	 * message.dto.SearchBulletinDTO)
	 */
	@Override
	public List findHospList(SearchBulletinDTO searchBulletinDto) {
		List params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append(" select * ");
		sql.append("   from sys_hospital");
		sql.append("  where out_flag = '0' ");// 只返回本地医院

		int staffLevel = Integer.parseInt(UtilFunc.getString(getUserInfo(),
				"staff_level", "9"));
		if (!getUserInfo().isSuperUser() && staffLevel > 1) {
			String centerList = UtilFunc.getString(getUserInfo(),
					"center_list", "0");
			SqlHelper.addStringValues(centerList, "center_id", sql, params);
		}

		SqlHelper.addStringValues(searchBulletinDto.getCenter(), "center_id",
				sql, params);

		SqlHelper.addStringExp(searchBulletinDto.getHospName(),
				"hospital_name", sql, params);

		SqlHelper.addString(searchBulletinDto.getLevel(), "hosp_level", "=",
				sql, params);
		SqlHelper.addString(searchBulletinDto.getHosptype(), "hosp_kind", "=",
				sql, params);

		sql.append(" order by hospital_id asc");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#findCorpList(com.powersi.sys.
	 * message.dto.SearchBulletinDTO)
	 */
	@Override
	public List findCorpList(SearchBulletinDTO searchBulletinDto) {
		List params = new ArrayList();
		StringBuilder sql = new StringBuilder();

		sql.append(" select * ");
		sql.append("   from sys_corps ");
		sql.append("  where 1 = 1 ");
		sql.append(" order by corp_code asc");

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#insertBulletinReceive(java.util
	 * .List)
	 */
	@Override
	public void insertBulletinReceive(List recList) {
		String sql = "insert into sys_bulletin_receive\n" +
				"  (bulletin_id,\n" +
				"   bulletin_type,\n" +
				"   center_id,\n" +
				"   receive_org,\n" +
				"   receive_staff,\n" +
				"   receive_man,\n" +
				"   receive_statue,\n" +
				"   receive_count,\n" +
				"   receive_date,\n" +
				"	valid_flag)\n" +
				"values\n" +
				"  (:bulletin_id,\n" +
				"   :bulletin_type,\n" +
				"   :center_id,\n" +
				"   :receive_org,\n" +
				"   1,\n" +
				"   :receive_man,\n" +
				"   0,\n" +
				"   0,\n" + 
				"   NOW(),\n"+
				"   1)";

		DBHelper.batchUpdate(sql, recList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#delBulletinReceive(java.lang.
	 * Long)
	 */
	@Override
	public int delBulletinReceive(Long bulId) {
		String desql = "delete from sys_bulletin_receive where bulletin_id = ?";
		int ret = DBHelper.executeUpdate(desql, new Object[] { bulId });
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#findReplyList(com.powersi.sys
	 * .message.dto.SearchBulletinDTO)
	 */
	@Override
	public List findReplyList(SearchBulletinDTO searchBulletinDto) {
		String replyStatus = searchBulletinDto.getReplyStatus();
		String bulletin_id = searchBulletinDto.getBulletinId();

		SysBulletins bul = getBulletin(Long.parseLong(bulletin_id));
		StringBuilder sesql = new StringBuilder();

		List params = new ArrayList();
		if ("1".equals(replyStatus)) {
			if ("2".equals(bul.getUserKind())) {
				sesql.append("select t.*, (select corp.org_name from sys_user_info corp where corp.user_id = t.reply_staff and corp.user_kind = 2) org_name,");
			} else if ("3".equals(bul.getUserKind())) {
				sesql.append("select t.*, (select hosp.hospital_name from sys_hospital_login hosp where hosp.hospital_id = t.reply_org) org_name,");
			} else {
				sesql.append("select t.*, (select dept.dept_name from sys_dept dept where dept.dept_id = t.reply_org) org_name,");
			}
			sesql.append("decode (t.reply_role,'1','医保管理岗','2','医保财务岗','3','医院端系统管理员','') as rolename from sys_bulletin_reply t  where 1=1");
			if (!UtilFunc.isEmpty(bulletin_id)) {
				params.add(bulletin_id);
				sesql.append(" and t.bulletin_id = ?");
			}
			SqlHelper.addStringExp(searchBulletinDto.getReplyMan(),
					"t.reply_man", sesql, params);
			sesql.append("order by t.reply_date desc");
		} else {
			params.add(bulletin_id);
			// 获取未回执单位
			if ("2".equals(bul.getUserKind())) {
				sesql.append(" select staff.org_name   as org_name, ");
				sesql.append("        staff.user_name  as reply_man, ");
				sesql.append("        staff.login_user as rolename, ");
				sesql.append("        null             as reply_date, ");
				sesql.append("        null             as reply_info ");
				sesql.append("   from sys_user_info staff ");
				sesql.append("  where staff.user_sta = '1' ");
				sesql.append("    and staff.user_kind = '2' ");
				sesql.append("    and (exists (select 'X' ");
				sesql.append("                   from sys_bulletin_receive ");
				sesql.append("                  where bulletin_id = ? ");
				sesql.append("                    and valid_flag = '1' ");
				sesql.append("                    and receive_org = '0') or ");
				sesql.append("         org_id in (select receive_org ");
				sesql.append("                             from sys_bulletin_receive ");
				sesql.append("                            where bulletin_id = ? ");
				sesql.append("                              and valid_flag = '1')) ");
				sesql.append("    and not exists (select 'X' ");
				sesql.append("           from sys_bulletin_reply sbr ");
				sesql.append("          where sbr.reply_staff = to_char(staff.user_id) ");
				sesql.append("            and bulletin_id = ?) ");

				params.add(bulletin_id);
				params.add(bulletin_id);

				SqlHelper.addStringExp(searchBulletinDto.getReplyMan(),
						"staff.user_name", sesql, params);
			} else if ("3".equals(bul.getUserKind())) {
				sesql.append(" select hosp.hospital_name as org_name, ");
				sesql.append("        '' as reply_man, ");
				sesql.append("        '' as rolename, ");
				sesql.append("        null as reply_date, ");
				sesql.append("        null as reply_info ");
				sesql.append("   from sys_hospital hosp ");
				sesql.append("  where hosp.out_flag = '0' ");
				sesql.append("    and hosp.valid_flag = '1' ");
				sesql.append("    and (exists ");
				sesql.append("         (select 'X' ");
				sesql.append("            from sys_bulletin_receive ");
				sesql.append("           where bulletin_id = ? ");
				sesql.append("             and valid_flag = '1' ");
				sesql.append("             and receive_org = '0') or ");
				sesql.append("         hospital_id in (select receive_org ");
				sesql.append("                           from sys_bulletin_receive ");
				sesql.append("                          where bulletin_id = ? ");
				sesql.append("                            and valid_flag = '1')) ");
				sesql.append("    and not exists (select 'X' ");
				sesql.append("           from sys_bulletin_reply sbr ");
				sesql.append("          where sbr.reply_staff = to_char(hosp.hospital_id) ");
				sesql.append("            and bulletin_id = ?) ");
				
				params.add(bulletin_id);
				params.add(bulletin_id);

				SqlHelper.addStringExp(searchBulletinDto.getReplyMan(),
						"hosp.hospital_name", sesql, params);

			} else {
				sesql.append(" select distinct sd.dept_name  as org_name, ");
				sesql.append("                 ss.staff_name as reply_man, ");
				sesql.append("                 ss.login_user as rolename, ");
				sesql.append("                 null          as reply_date, ");
				sesql.append("                 null          as reply_info ");
				sesql.append("   from (select staff_name, login_user, dept_id ");
				sesql.append("           from sys_staff staff ");
				sesql.append("          where staff.staff_sta = '1' ");
				sesql.append("            and (exists ");
				sesql.append("                 (select 'X' ");
				sesql.append("                    from sys_bulletin_receive ");
				sesql.append("                   where bulletin_id = ? ");
				sesql.append("                     and valid_flag = '1' ");
				sesql.append("                     and receive_org = '0') or ");
				sesql.append("                 dept_id in (select dept_id ");
				sesql.append("                               from sys_dept ");
				sesql.append("                              start with dept_id in ");
				sesql.append("                                         (select receive_org ");
				sesql.append("                                            from sys_bulletin_receive ");
				sesql.append("                                           where bulletin_id = ? ");
				sesql.append("                                             and valid_flag = '1') ");
				sesql.append("                             connect by prior dept_id = dept_up_id)) ");
				sesql.append("            and not exists ");
				sesql.append("          (select 'X' ");
				sesql.append("                   from sys_bulletin_reply sbr ");
				sesql.append("                  where sbr.reply_staff = to_char(staff.staff_id) ");
				sesql.append("                    and bulletin_id = ?)) ss, ");
				sesql.append("        sys_dept sd ");
				sesql.append("  where ss.dept_id = sd.dept_id(+) ");

				params.add(bulletin_id);
				params.add(bulletin_id);

				SqlHelper.addStringExp(searchBulletinDto.getReplyMan(),
						"ss.staff_name", sesql, params);
			}
		}

		return DBHelper.executeList(sesql.toString(), UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.BulletinDAO#addReplyInfo(java.util.Map)
	 */
	public int addReplyInfo(Map map) {
		int ret = DAOHelper.insert("sys_bulletin_reply", map);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.message.dao.BulletinDAO#getReplyInfo(java.lang.Long,
	 * java.util.Map)
	 */
	public List getReplyInfo(Long bulletinId, Map filter) {
		List params = new ArrayList();

		StringBuilder sql = new StringBuilder();

		sql.append(" select *");
		sql.append("   from sys_bulletin_reply");
		sql.append("  where bulletin_id = ? ");

		params.add(bulletinId);

		if (!UtilFunc.isEmpty(params)) {
			Set<Entry> set = filter.entrySet();
			for (Entry entry : set) {
				sql.append(" and ").append(entry.getKey()).append(" = ? ");
				params.add(entry.getValue());
			}
		}

		return DBHelper.executeList(sql.toString(), UtilFunc.toArray(params));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#hasDuplulitReplyInfo(java.lang
	 * .Long)
	 */
	public boolean hasDuplulitReplyInfo(Long bulletinId) {
		UserInfo userinfo = this.getUserInfo();
		String sql = "select 1 from sys_bulletin_reply t where t.bulletin_id = ? and t.reply_org = ?";
		long count = DBHelper.executeCount(sql, new Object[] { bulletinId,
				UtilFunc.getString(userinfo, "dept_id") });
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#isAbleToReply(java.lang.Long)
	 */
	public boolean isAbleToReply(Long bulletinId) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select 1 ");
		sql.append("   from sys_bulletin_receive ");
		sql.append("  where bulletin_id = ? ");
		sql.append("    and receive_org in (")
				.append(UtilFunc.getString(getUserInfo(), "dept_up_list", "0"))
				.append(") ");
		sql.append("    and valid_flag = '1' ");

		long count = DBHelper.executeCount(sql.toString(),
				new Object[] { bulletinId });
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#getReceiveList(java.lang.Long)
	 */
	public List getReceiveList(Long bulletinId) {
		String sql = "select center_id, receive_org, receive_staff, receive_man\n"
				+
				"  from sys_bulletin_receive\n" +
				" where bulletin_id = ?\n" +
				"   and valid_flag = '1'";

		return DBHelper.executeList(sql, new Object[] { bulletinId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#getReceiveCount(java.lang.Long)
	 */
	public long getReceiveCount(Long bulletinId) {
		String sql = "select 1 from sys_bulletin_receive where bulletin_id = ?";
		return DBHelper.executeCount(sql, new Object[] { bulletinId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.message.dao.BulletinDAO#updateDisOrder(java.lang.Long,
	 * int)
	 */
	public int updateDisOrder(Long bulletinId, int disOrder) {
		return DBHelper.update(
				"update sys_bulletins set dis_order = ? where bulletin_id = ?",
				new Object[] { disOrder, bulletinId });
	}
}
