/*
 * Copyright 2014 Powersi. All rights reserved.
 */
package com.powersi.sys.message.dao;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAO;
import com.powersi.sys.entity.FileBulletins;
import com.powersi.sys.entity.SysBulletins;
import com.powersi.sys.message.dto.SearchBulletinDTO;

/**
 * 公告DAO接口.
 */
public interface BulletinDAO extends BaseDAO {

	/** The Constant TABLE_NAME. */
	public static final String TABLE_NAME = "sys_bulletins";

	/**
	 * 通过对象来获取附件（其中有blob）
	 */
	public FileBulletins getFileAttch(Long bulletinId,String fileId,boolean fileContent);
	/**
	 * 查询列表.
	 *
	 * @param searchBulletinDto the search bulletin dto
	 * @return the list
	 */
	List queryList(SearchBulletinDTO searchBulletinDto);

	/**
	 * 查询公告信息.
	 * 
	 * @param bulletinId
	 *            the bulletin id
	 * @return the bulletin
	 */
	Map<String, Object> viewBulletin(Long bulletinId);

	/**
	 * 获取公告信息.
	 * 
	 * @param bulletinId
	 *            the bulletin id
	 * @return the bulletin
	 */
	SysBulletins getBulletin(Long bulletinId);

	/**
	 * 获取用户列表.
	 * 
	 * @param bulletinId
	 *            the bulletin id
	 * @return the bulletin
	 */
	List<String> getUserList(Long bulletinId);

	/**
	 * 插入公告信息.
	 * 
	 * @param sysBulletin
	 *            the sys bulletin
	 * @return the int
	 */
	int insertBulletin(SysBulletins sysBulletin);

	/**
	 * 更新公告信息.
	 * 
	 * @param sysBulletin
	 *            the sys bulletin
	 * @return the int
	 */
	int updateBulletin(SysBulletins sysBulletin);

	/**
	 * 插入用户列表.
	 * 
	 * @param bulletinId
	 *            the bulletin id
	 * @param userKind
	 *            the user kind
	 * @return the int
	 */
	int insertUserList(Long bulletinId, List<String> userKind);

	/**
	 * 删除公告.
	 * 
	 * @param bulletinId
	 *            the bulletin id
	 * @return the bulletin
	 */
	int deleteBulletin(Long bulletinId);

	/**
	 * 审核公告.审签公告
	 *
	 * @param bulletinId the bulletin id
	 * @param auditType the audit type
	 * @param auditFlag the audit flag
	 * @param auditOpinion the audit opinion
	 * @return the bulletin
	 */
	int auditBulletin(Long bulletinId, String auditType, String auditFlag, String auditOpinion);

	/**
	 * 取消审核公告.
	 *
	 * @param bulletinId the bulletin id
	 * @param auditType the audit type
	 * @return the bulletin
	 */
	int cancelBulletin(Long bulletinId, String auditType);

	/**
	 * 查询附件.
	 * 
	 * @param bulletinId
	 *            the bulletin id
	 * @return the list
	 */
	List getFileList(Long bulletinId);

	/**
	 * 删除附件.
	 * 
	 * @param bulletinId
	 *            the bulletin id
	 * @param files
	 *            the files
	 * @return the int
	 */
	int deleteFile(Long bulletinId, String[] files);

	/**
	 * 插入附件.
	 * 
	 * @param bulletinId
	 *            the bulletin id
	 * @param files
	 *            the files
	 * @param names
	 *            the names
	 * @param types
	 *            the types
	 * @return the int
	 */
	int insertFile(Long bulletinId, List<File> files, List<String> names,
			List<String> types);

	/**
	 * 获取附件.
	 * 
	 * @param bulletinId
	 *            the bulletin id
	 * @param fileId
	 *            the file id
	 * @param fileContent
	 *            the file content
	 * @return the file
	 */
	Map getFile(Long bulletinId, String fileId, boolean fileContent);

	/**
	 * 检查是否有新消息.
	 * 
	 * @return true, if successful
	 */
	boolean hasNewMessage();

	/**
	 * 查询公告列表根据定时器.
	 * 
	 * @return the list
	 */
	List getQueryMessage();

	/**
	 * 查询公告列表根据登录.
	 * 
	 * @return the list
	 */
	List getLoginMessage();

	/**
	 * Update query message.
	 * 
	 * @return the int
	 */
	int updateQueryMessage();

	/**
	 * 检查是否有新消息.
	 * 
	 * @param centerId
	 *            the center id
	 * @param receiveOrg
	 *            the receive org
	 * @param receiveStaff
	 *            the receive staff
	 * @return true, if successful
	 */
	List hasNewMessage(String centerId, String receiveOrg, String receiveStaff);

	/**
	 * Gets the query message.
	 * 
	 * @param centerId
	 *            the center id
	 * @param receiveOrg
	 *            the receive org
	 * @param receiveStaff
	 *            the receive staff
	 * @return the query message
	 */
	List getQueryMessage(String centerId, String receiveOrg, String receiveStaff);

	/**
	 * Update query message.
	 * 
	 * @param centerId
	 *            the center id
	 * @param receiveOrg
	 *            the receive org
	 * @param receiveStaff
	 *            the receive staff
	 * @param receiveMan
	 *            the receive man
	 * @param msId
	 *            the ms id
	 * @return the int
	 */
	int updateQueryMessage(String centerId, String receiveOrg,
			String receiveStaff, String receiveMan, String msId);

	/**
	 * 获取医院接受人列表.
	 *
	 * @param searchBulletinDto the search bulletin dto
	 * @return the list
	 */
	List findHospList(SearchBulletinDTO searchBulletinDto);

	/**
	 * 获取中心接受人列表.
	 *
	 * @param searchBulletinDto the search bulletin dto
	 * @return the list
	 */
	List findCenterList(SearchBulletinDTO searchBulletinDto);

	/**
	 * 获取单位接受人列表.
	 *
	 * @param searchBulletinDto the search bulletin dto
	 * @return the list
	 */
	List findCorpList(SearchBulletinDTO searchBulletinDto);

	/**
	 * 插入接收人列表.
	 *
	 * @param recList the rec list
	 */
	void insertBulletinReceive(List recList);

	/**
	 * 插入接收人列表.
	 *
	 * @param bulId the bul id
	 * @return the int
	 */
	int delBulletinReceive(Long bulId);

	/**
	 * 查找回执列表.
	 *
	 * @param searchBulletinDto the search bulletin dto
	 * @return the list
	 */
	List findReplyList(SearchBulletinDTO searchBulletinDto);

	/**
	 * 添加回执记录.
	 *
	 * @param map the map
	 * @return the int
	 */
	int addReplyInfo(Map map);

	/**
	 * 获取回执信息.
	 *
	 * @param bulletinId the bulletin id
	 * @param filter the filter
	 * @return the reply info
	 */
	List getReplyInfo(Long bulletinId, Map filter);
	
	/**
	 * 公告是否能被回执.
	 *
	 * @param bulletinId the bulletin id
	 * @return true, if is able to reply
	 */
	boolean isAbleToReply(Long bulletinId);

	/**
	 * 公告是否已被添加回执.
	 *
	 * @param bulletinId the bulletin id
	 * @return true, if successful
	 */
	boolean hasDuplulitReplyInfo(Long bulletinId);
	
	
	/**
	 * 获取接收人列表.
	 *
	 * @param bulletinId the bulletin id
	 * @return the receive list
	 */
	List getReceiveList(Long bulletinId);
	
	
	/**
	 * 获取接收人个数.
	 *
	 * @param bulletinId the bulletin id
	 * @return the recive count
	 */
	long getReceiveCount(Long bulletinId);
	
	/**
	 * 更新显示序号.
	 *
	 * @param bulletinId the bulletin id
	 * @param disOrder the dis order
	 * @return the int
	 */
	int updateDisOrder(Long bulletinId, int disOrder);
}
