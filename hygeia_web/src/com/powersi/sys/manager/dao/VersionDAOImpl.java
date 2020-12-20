/*
 * Copyright 2012 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAOImpl;
import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * 版本DAO接口实现.
 */
public class VersionDAOImpl extends BaseDAOImpl implements VersionDAO {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.VersionDAO#getFile(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public List getFile(String sn, String fileName, String fileType) throws UnsupportedEncodingException {

		String sql = "select application_version_sn as sn,\n"
				+
				"       file_name,\n"
				+
				"       file_content,\n"
				+
				"       to_char(last_modify, 'yyyy-mm-dd HH24:mi:ss') as last_modify,\n"
				+
				"       file_size,\n" +
				"       op_staff as op,\n" +
				"       op_flag as flag,\n" +
				"       op_terminal as terminal,\n" +
				"       file_type,\n" +
				"       encode_flag\n" +
				"  from sys_application_version\n" +
				" where application_version_sn = ?\n" +
				"   and file_name = ?\n" +
				"   and file_type = ?";

		List lst = DBHelper.executeList(sql, new Object[] { sn, fileName,
				fileType.toUpperCase() });
		if (CollectionHelper.isNotEmpty(lst)) {
			for (int i = 0; i < lst.size(); i++) {
				Map map = (Map) lst.get(i);
				byte[] fileContent = DBHelper.blobToBytes(map.get("file_content"));
				if (!"1".equals(UtilFunc.getString(map, "encode_flag"))) {
					map.put("file_content", UtilFunc
							.toBase64String(fileContent));
				} else {
					map.put("file_content", new String(fileContent, GlobalContext.getCharset()));
				}
			}
		}

		return lst;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.powersi.sys.manager.dao.VersionDAO#getVersionInfo(java.lang.String)
	 */
	public List getVersionInfo(String fileType) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select application_version_sn as sn, ");
		sql.append("        file_name, ");
		sql.append("        to_char(last_modify, 'yyyy-mm-dd hh24:mi:ss') as last_modify, ");
		sql.append("        file_size, ");
		sql.append("        op_staff as op, ");
		sql.append("        op_flag as flag, ");
		sql.append("        op_terminal as terminal, ");
		sql.append("        file_type ");
		sql.append("   from sys_application_version ");

		if (!fileType.toUpperCase().equals("ALL")) {
			sql.append("  where file_type = ? ");
			sql.append("  order by application_version_sn ");
			return DBHelper.executeList(sql.toString(),
					new Object[] { fileType.toUpperCase() });
		} else {
			sql.append("  order by application_version_sn ");

			return DBHelper.executeList(sql.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.VersionDAO#insert(java.util.List)
	 */
	public int insert(List lstData) {
		if (UtilFunc.isEmpty(lstData)) {
			return 0;
		}

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into sys_application_version ");
		sql.append("   (application_version_sn, ");
		sql.append("    file_name, ");
		sql.append("    file_content, ");
		sql.append("    last_modify, ");
		sql.append("    file_size, ");
		sql.append("    op_staff, ");
		sql.append("    op_flag, ");
		sql.append("    op_terminal, ");
		sql.append("    file_type, ");
		sql.append("    last_date) ");
		sql.append(" values ");
		sql.append("   (:sn, ");
		sql.append("    :file_name, ");
		sql.append("    empty_blob(), ");
		sql.append("    :last_modify, ");
		sql.append("    :file_size, ");
		sql.append("    :op, ");
		sql.append("    :flag, ");
		sql.append("    :terminal, ");
		sql.append("    :file_type, ");
		sql.append("    sysdate) ");

		for (int i = 0; i < lstData.size(); i++) {
			Map map = (Map) lstData.get(i);
			if (!UtilFunc.isEmptyString(map.get("last_modify"))) {
				map.put("last_modify",
						DateFunc.toTimestamp(map.get("last_modify").toString()));
			}
		}

		return DBHelper.executeBatch(sql.toString(), lstData).length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.VersionDAO#update(java.util.List)
	 */
	public int update(List lstData) {
		if (UtilFunc.isEmpty(lstData)) {
			return 0;
		}

		String sql = "update sys_application_version\n" +
				"   set application_version_sn = :sn,\n" +
				"       file_name              = :file_name,\n" +
				"       last_modify            = :last_modify,\n" +
				"       file_size              = :file_size,\n" +
				"       op_staff               = :op,\n" +
				"       op_flag                = :flag,\n" +
				"       op_terminal            = :terminal,\n" +
				"       file_type              = :file_type,\n" +
				"       last_date              = sysdate\n" +
				" where application_version_sn = :sn_original";
		for (int i = 0; i < lstData.size(); i++) {
			Map map = (Map) lstData.get(i);
			if (!UtilFunc.isEmptyString(map.get("last_modify"))) {
				map.put("last_modify",
						DateFunc.toTimestamp(map.get("last_modify").toString()));
			}
		}

		return DBHelper.executeBatch(sql, lstData).length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.VersionDAO#updateFile(java.util.List)
	 */
	public int updateFile(List lstData) throws UnsupportedEncodingException {
		if (UtilFunc.isEmpty(lstData)) {
			return 0;
		}

		Map mapData = (Map) lstData.get(0);
		if (UtilFunc.isEmpty(mapData)) {
			return 0;
		}

		if (UtilFunc.isEmptyString(mapData.get("sn"))) {
			throw new HygeiaException("文件序号不能为空");
		}

		String fileContent = UtilFunc.getString(mapData, "file_content");
		if (UtilFunc.isEmpty(fileContent)) {
			throw new HygeiaException("文件内容不能为空");
		}

		DBHelper.update(
				"update sys_application_version set encode_flag = 1, last_date = sysdate where application_version_sn = ?",
				new Object[] { mapData.get("sn") });

		String sql = "select file_content from sys_application_version where application_version_sn = ? ";
		return DBHelper.blobUpdate(sql, new Object[] { mapData.get("sn") },
				fileContent.getBytes(GlobalContext.getCharset()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.powersi.sys.manager.dao.VersionDAO#delete(java.util.List)
	 */
	public int delete(List lstData) {
		if (UtilFunc.isEmpty(lstData)) {
			return 0;
		}

		StringBuilder sql = new StringBuilder();

		sql.append(" delete from sys_application_version ");
		sql.append("  where application_version_sn = :sn_original ");

		return DBHelper.executeBatch(sql.toString(), lstData).length;
	}
}
