/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.comm.service.memory.MemoryDB;
import com.powersi.hygeia.comm.service.HygeiaBeanService;
import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.cache.DBCache;
import com.powersi.hygeia.framework.cache.DBCacheManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.sqltemplate.function.GetUserInfoFunction;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.sys.entity.SysMessage;
import com.powersi.sys.message.service.BulletinService;
import com.powersi.sys.message.service.MessageService;

/**
 * The Class MessageHelper.
 */
public class MessageHelper {
	private static final MessageService messageService = BeanHelper
			.getBean(MessageService.class);

	/** The Constant bulletinService. */
	private static final BulletinService bulletinService = BeanHelper
			.getBean(BulletinService.class);

	/**
	 * 发送消息.
	 * 
	 * @param receiveUserList
	 *            用户staffid列表
	 * @param sysMessage
	 *            the sys message
	 * @return the int
	 * @throws Exception
	 *             the exception
	 */
	public static int sendMessage(List receiveUserList, SysMessage sysMessage)
			throws Exception {
		if (sysMessage == null) {
			throw new HygeiaException("消息实体不能为空");
		}
		if (receiveUserList == null || receiveUserList.size() <= 0) {
			throw new HygeiaException("用户列表为空");
		}
		if (UtilFunc.isEmpty(sysMessage.getMsTitle())) {
			throw new HygeiaException("消息标题不能为空");
		}
		return messageService.sendMessage(receiveUserList, sysMessage);
	}

	/**
	 * 发送消息.
	 * 
	 * @param userList
	 *            the user list
	 * @param messageTitle
	 *            the message title
	 * @param messageUrl
	 *            the message url
	 * @return the int
	 * @throws Exception
	 *             the exception
	 */
	public static int sendMessage(List userList, String messageTitle,
			String messageUrl) throws Exception {
		SysMessage sysMessage = new SysMessage();
		sysMessage.setMsTitle(messageTitle);
		sysMessage.setMsUrl(messageUrl);
		return sendMessage(userList, sysMessage);
	}

	/**
	 * 发送消息.
	 * 
	 * @param userList
	 *            the user list
	 * @param messageTitle
	 *            the message title
	 * @param messageContent
	 *            the message content
	 * @param messageUrl
	 *            the message url
	 * @return the int
	 * @throws Exception
	 *             the exception
	 */
	public static int sendMessage(List userList, String messageTitle,
			String messageContent, String messageUrl) throws Exception {
		SysMessage sysMessage = new SysMessage();
		sysMessage.setMsTitle(messageTitle);
		sysMessage.setMsUrl(messageUrl);
		sysMessage.setMsContent(messageContent);
		return sendMessage(userList, sysMessage);
	}

	/**
	 * 供外部调用接口发送消息.
	 * 
	 * @param userList
	 * @param messageTitle
	 * @param messageContent
	 * @param send_id
	 * @param send_name
	 * @return
	 * @throws Exception
	 */
	public static int sendMessage(List userList, String messageTitle,
			String messageContent, String send_id,
			String send_name)
			throws Exception
	{
		SysMessage sysMessage = new SysMessage();
		sysMessage.setMsTitle(messageTitle);
		sysMessage.setMsContent(messageContent);
		sysMessage.setSendStaff(send_id);
		sysMessage.setSendMan(send_name);
		return sendOuterMessage(userList, sysMessage);
	}

	/**
	 * 外部发送消息.
	 * 
	 * @param receiveUserList
	 *            用户staffid列表
	 * @param sysMessage
	 *            the sys message
	 * @return the int
	 * @throws Exception
	 *             the exception
	 */
	public static int sendOuterMessage(List receiveUserList,
			SysMessage sysMessage)
			throws Exception
	{
		if (sysMessage == null)
		{
			throw new HygeiaException("消息实体不能为空");
		}
		if (receiveUserList == null || receiveUserList.size() <= 0)
		{
			throw new HygeiaException("用户列表为空");
		}
		if (UtilFunc.isEmpty(sysMessage.getMsTitle()))
		{
			throw new HygeiaException("消息标题不能为空");
		}
		return messageService.sendMessage(receiveUserList, sysMessage);
	}

	/**
	 * 检查是否未读消息.
	 * 
	 * @param userinfo
	 *            the userinfo
	 * @return the long
	 */
	public static long existUnReadMessage() {
		return messageService.existUnreadMessage();
	}

	/**
	 * 获取公告列表.
	 * 
	 * @param maxSize
	 *            the max size
	 * @return the bulletinl list
	 */
	public static List getBulletinlList(int maxSize) {
		UserInfo user = BusiContext.getUserInfo();

		// 获取机构列表
		String orgList = null;
		if ("2".equals(user.getUserKind())) {
			orgList = UtilFunc.getString(user, "corp_list", null);
		} else if ("3".equals(user.getUserKind())) {
			orgList = UtilFunc.getString(user, "hospital_list", null);
		} else {
			orgList = UtilFunc.getString(user, "dept_up_list", null);
		}
		if (UtilFunc.isEmpty(orgList)) {
			orgList = "0";
		} else {
			orgList = "0," + orgList;
		}

		String[] orgs = UtilFunc.split(orgList, ",");
		//由于中心下发下来的医疗机构，并没有转码，所以需在这里进行转码
		MemoryDB memoryDB = HygeiaBeanService.getInstance().getBeanByClass(MemoryDB.class);
		String operHospitalId =  (String)memoryDB.getMapValue("MAP_BIZ_YY_MATCH_PCLOUD_CENTER", "4303-zg_"+ user.get("akb020"));
		
		List lst=messageService.getBulletinsInfo(operHospitalId);		
		return lst;
	}

	/**
	 * 查询消息列表（包含公告).
	 * 
	 * @param loginFlag
	 *            登录标志
	 * @return the list
	 */
	public static List queryMessageList(boolean loginFlag) {
		return bulletinService.queryMessageList(loginFlag);
	}
}
