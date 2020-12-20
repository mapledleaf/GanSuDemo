/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.message.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.BaseServiceImpl;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.sys.entity.SysMessage;
import com.powersi.sys.message.dao.MessageDAO;

/**
 * The Class MessageServiceImpl.
 */
public class MessageServiceImpl extends BaseServiceImpl implements MessageService{
	
	/** The message dao. */
	private static MessageDAO messageDAO = BeanHelper.getBean(MessageDAO.class);
	/* (non-Javadoc)
	 * @see com.powersi.sys.message.service.MessageService#existUnreadMessage(com.powersi.hygeia.framework.UserInfo)
	 */
	public long existUnreadMessage() {
		return messageDAO.existUnreadMessage();
	}

	/* (non-Javadoc)
	 * @see com.powersi.sys.message.service.MessageService#sendMessage(java.util.List, com.powersi.sys.entity.SysMessage)
	 */
	public int sendMessage(List userList, SysMessage sysMessage) {
		//添加消息记录
		sysMessage.setMsId(String.valueOf(SysFunc.getMaxNo("ms_id")));
		if(!UtilFunc.hasText(sysMessage.getSendStaff())){
			sysMessage.setSendStaff("0");
		}
		if(!UtilFunc.hasText(sysMessage.getSendMan())){
			sysMessage.setSendMan("系统");
		}
		
		sysMessage.setValidFlag("1");
		sysMessage.setMsType("0");//设置为系统消息
		messageDAO.addMessage(sysMessage);
		
		
		//添加到收件人收件箱
		List sendList = new ArrayList();
		for(int i=0;i<userList.size();i++){
			String staff  = (String) userList.get(i);
			Map sendMap = new HashMap();
			sendMap.put("ms_id", sysMessage.getMsId());
			sendMap.put("ms_folder", "1");//设置为收件箱
			sendMap.put("receive_staff", staff);
			sendMap.put("ms_type", "0");//设置为系统消息
			sendMap.put("valid_flag", "1");
			
			sendList.add(sendMap);
		}
		if(sendList.size() > 0){
			messageDAO.addMessageStaff(sendList);
			messageDAO.updateMessageLastTime(sendList);
		}
		
		
		return 0;
	}
	
	public List getBulletinsInfo(String akb020) {
		return messageDAO.getBulletinsInfo(akb020);
	}
}
