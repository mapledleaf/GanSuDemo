/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.message.service;

import java.util.List;

import com.powersi.hygeia.framework.BaseService;
import com.powersi.sys.entity.SysMessage;

/**
 * The Interface MessageService.
 */
public interface MessageService extends BaseService {

	/**
	 * 发送消息.
	 * 
	 * @param userList
	 *            the user list
	 * @param sysMessage
	 *            the sys message
	 * @return the int
	 */
	public int sendMessage(List userList, SysMessage sysMessage);

	/**
	 * 是否存在未读消息.
	 * 
	 * @param user
	 *            the user
	 * @return the long
	 */
	public long existUnreadMessage();
	
	public List getBulletinsInfo(String akb020);
}
