/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.message.dao;

import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseDAO;
import com.powersi.sys.entity.SysMessage;
import com.powersi.sys.message.dto.SearchMessageDTO;

/**
 * The Interface MessageDAO.
 */
public interface MessageDAO extends BaseDAO {

	/** The table sys message. */
	public static String TABLE_SYS_MESSAGE = "sys_message";

	/** The table sys message staff. */
	public static String TABLE_SYS_MESSAGE_STAFF = "sys_message_staff";

	/**
	 * 增加消息.
	 * 
	 * @param sysMessage
	 *            the sys message
	 * @return the int
	 */
	public int addMessage(SysMessage sysMessage);

	/**
	 * 更新消息内容.
	 * 
	 * @param sysMessage
	 *            the sys message
	 * @return the int
	 */
	public int updateMessage(SysMessage sysMessage);

	/**
	 * 删除消息.
	 *
	 * @param msidList the msid list
	 * @param msFloder the ms floder
	 * @return the int
	 */
	public int delMessage(List msidList, String msFloder);

	/**
	 * 查询消息列表.
	 * 
	 * @param searchMessageVo
	 *            the search message vo
	 * @return the message list
	 */
	public List getMessageList(SearchMessageDTO searchMessageVo);

	/**
	 * 获取消息详情.
	 * 
	 * @param msId
	 *            the ms id
	 * @return the message
	 */
	public Map getMessage(String msId);

	/**
	 * 获取接收人列表.
	 * 
	 * @return the person list
	 */
	public List getPersonList();

	/**
	 * 添加消息关系.
	 * 
	 * @param mesStaMap
	 *            the mes sta map
	 * @return the int
	 */
	public int addMessageStaff(Map mesStaMap);

	/**
	 * 添加消息关系.
	 *
	 * @param mesStaList the mes sta list
	 * @return the int
	 */
	public int addMessageStaff(List mesStaList);
	
	
	/**
	 * 获取消息接收列表.
	 *
	 * @param msId the ms id
	 * @return the message staff
	 */
	public List getMessageReceive(String msId);

	/**
	 * 更新消息最后时间.
	 *
	 * @param mesStaList the mes sta list
	 * @return the int
	 */
	public int updateMessageLastTime(List mesStaList);
	
	/**
	 * 改变阅读状态.
	 *
	 * @param msidList the msid list
	 * @return the int
	 */
	public int changeReadStat(List msidList);

	/**
	 * 判断用户是否有未读消息.
	 * 
	 * @return 消息条数
	 */
	public long existUnreadMessage();

	public List getBulletinsInfo(String akb020);
}
