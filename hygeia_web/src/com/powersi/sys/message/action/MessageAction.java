/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.message.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.sys.entity.SysMessage;
import com.powersi.sys.message.dao.MessageDAO;
import com.powersi.sys.message.dto.SearchMessageDTO;
import com.powersi.sys.user.dao.StaffDAO;
import com.powersi.sys.util.MessageHelper;
import com.powersi.sys.util.UserTaskHelper;

/**
 * The Class MessageAction.
 */
@Action(value = "MessageAction", results = {
		@Result(name = "sendMessage", location = "/pages/sys/message/MessageSend.jsp"),
		@Result(name = "getPersons", location = "/pages/sys/message/MessageSelPerson.jsp"),
		@Result(name = "managerMessage", location = "/pages/sys/message/MessageManager.jsp"),
		@Result(name = "viewMessage", location = "/pages/sys/message/MessageView.jsp"),
		@Result(name = "viewMessageDialog", location = "/pages/sys/message/MessageViewDialog.jsp") })
public class MessageAction extends BaseAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The message dao. */
	public static MessageDAO messageDAO = getBean(MessageDAO.class);

	/** The staff dao. */
	public static StaffDAO staffDAO = getBean(StaffDAO.class);

	/** The sys message. */
	private SysMessage sysMessage;

	/** The search message vo. */
	private SearchMessageDTO searchMessageVo;

	/**
	 * Gets the sys message.
	 * 
	 * @return the sys message
	 */
	public SysMessage getSysMessage() {
		return sysMessage;
	}

	/**
	 * Sets the sys message.
	 * 
	 * @param sysMessage
	 *            the new sys message
	 */
	public void setSysMessage(SysMessage sysMessage) {
		this.sysMessage = sysMessage;
	}

	/**
	 * Gets the search message vo.
	 * 
	 * @return the search message vo
	 */
	public SearchMessageDTO getSearchMessageVo() {
		return searchMessageVo;
	}

	/**
	 * Sets the search message vo.
	 * 
	 * @param searchMessageVo
	 *            the new search message vo
	 */
	public void setSearchMessageVo(SearchMessageDTO searchMessageVo) {
		this.searchMessageVo = searchMessageVo;
	}

	/**
	 * Send message.
	 * 
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String sendMessage() throws Exception {
		if (!isEmpty(this.getParameter("init"))) {
			return initSendMessage();
		} else {
			return saveSendMessage();
		}
	}

	/**
	 * Inits the send message.
	 * 
	 * @return the string
	 */
	private String initSendMessage() {
		try {
			List staffList = messageDAO.getPersonList();
			this.setAttribute("staffList", staffList);

			if ("true".equals(this.getParameter("close"))) {
				this.setAttribute("closeFlag", "true");
			}

			int sendType = UtilFunc.stringToInt(this.getParameter("type", "0"),
					0);
			if (sendType > 0) {
				String msId = this.getParameter("msid");
				if (isNotEmpty(msId)) {
					Map msMap = messageDAO.getMessage(msId);
					if (!isEmpty(msMap)) {
						sysMessage = new SysMessage();
						sysMessage.setMsId("");
						sysMessage.setMsType("1");
						if (sendType == 3) {
							sysMessage.setMsTitle("转发："
									+ getString(msMap, "ms_title", ""));
						} else {
							sysMessage.setMsTitle("答复："
									+ getString(msMap, "ms_title", ""));

							if (sendType == 1) {
								this.setAttribute("receiveMan", UtilFunc
										.getString(msMap, "sender_staff"));
							} else if (sendType == 2) {
								List lst = messageDAO.getMessageReceive(msId);
								Set set = new LinkedHashSet<String>();
								set.add(getString(msMap, "sender_staff"));
								for (int i = 0; i < lst.size(); i++) {
									String receiveStaff = getString(
											(Map) lst.get(i), "receive_staff");
									if (isNotEmpty(receiveStaff)) {
										set.add(receiveStaff);
									}
								}
								// 答复人不包括本人
								set.remove(getUserInfo().getUserId());

								this.setAttribute("receiveMan",
										UtilFunc.join(set, ","));
							}
						}

						sysMessage.setMsContent("\r\n\r\n"
								+ getString(msMap, "ms_content", ""));
					}
				}
			}

			return "sendMessage";
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Save send message.
	 * 
	 * @return the string
	 */
	private String saveSendMessage() {
		try {
			if (isEmpty(sysMessage.getMsTitle())) {
				throw new HygeiaException("消息标题不能为空");
			}

			if (isEmpty(sysMessage.getMsContent())) {
				// throw new HygeiaException("消息内容不能为空");
			}

			// 添加消息记录
			sysMessage.setMsId(String.valueOf(SysFunc.getMaxNo("ms_id")));
			UserInfo userInfo = this.getUserInfo();
			sysMessage.setSendStaff(userInfo.getUserId());
			sysMessage.setSendMan(userInfo.getUserName());
			sysMessage.setValidFlag("1");

			messageDAO.addMessage(sysMessage);

			// 是否保存到发件人发件箱
			Map sendMap = null;
			if ("true".equals(sysMessage.getSaveFlag())) {
				sendMap = new HashMap();
				sendMap.put("ms_id", sysMessage.getMsId());
				sendMap.put("ms_folder", "2");
				sendMap.put("receive_org",
						getString(userInfo, "dept_id", "999999"));
				sendMap.put("receive_staff", sysMessage.getSendStaff());
				sendMap.put("receive_man", sysMessage.getSendMan());
				sendMap.put("valid_flag", "1");
				sendMap.put("receive_date", new java.util.Date());
				sendMap.put("receive_statue", "0");

				messageDAO.addMessageStaff(sendMap);
			}

			// 添加到收件人收件箱
			if (!isEmpty(sysMessage.getReveiveMan())) {
				List lst = parseList(sysMessage.getReveiveMan());
				for (int i = 0; i < lst.size(); i++) {
					Map map = (Map) lst.get(i);

					map.put("ms_id", sysMessage.getMsId());
					map.put("ms_folder", "1");
					map.put("receive_org", map.get("pid"));
					map.put("receive_staff", map.get("id"));
					map.put("receive_man", map.get("name"));
					map.put("valid_flag", "1");
					map.put("receive_date", null);
					map.put("receive_statue", "0");
				}

				messageDAO.addMessageStaff(lst);
				messageDAO.updateMessageLastTime(lst);
			}

			Map map = new HashMap();
			map.put("msid", sysMessage.getMsId());
			return renderJson("发送成功", map);
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Gets the persons.
	 * 
	 * @return the persons
	 * @throws Exception
	 *             the exception
	 */
	public String getPersons() throws Exception {
		try {
			List staffList = messageDAO.getPersonList();
			Map map = new HashMap();
			map.put("staffList", staffList);
			return renderJson(map);
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Manager message.
	 * 
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String managerMessage() throws Exception {
		try {
			if (searchMessageVo == null) {
				searchMessageVo = new SearchMessageDTO();
				java.util.Date now = new java.util.Date();
				searchMessageVo.setBeginDate(DateFunc.dateToString(DateFunc
						.addDays(now, -7)) + " 00:00:00");
				searchMessageVo.setEndDate(DateFunc.dateToString(now)
						+ " 23:59:59");
				searchMessageVo.setStatus("all");

				searchMessageVo.setMsFloder("1");
			}

			if (isPostRequest()) {
				List messageList = null;
				initPager();

				searchMessageVo.setLoginStaff(getUserInfo().getUserId());
				messageList = messageDAO.getMessageList(searchMessageVo);
				return renderGrid(messageList);
			} else {
				return "managerMessage";
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * View message.
	 * 
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String viewMessage() throws Exception {
		try {
			String msid = this.getParameter("msid");
			// 查询消息详情
			// String folder = this.getParameter("msfolder");
			Map msMap = messageDAO.getMessage(msid);
			this.setAttribute("msMap", msMap);

			if (!isEmpty(this.getParameter("viewflag"))) {
				return "viewMessage";
			}

			// 设置该消息的阅读状态
			List list = new ArrayList();
			list.add(Integer.parseInt(msid));
			messageDAO.changeReadStat(list);

			return "viewMessageDialog";
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Del message.
	 * 
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String delMessage() throws Exception {
		try {
			String msstr = this.getParameter("msid");
			List list = (List) parseList(msstr);
			String msfolder = this.getParameter("msfolder");
			messageDAO.delMessage(list, msfolder);
			Map map = new HashMap();
			map.put("msids", msstr);
			return renderJson("删除消息成功", map);
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Change read stat.
	 * 
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String changeReadStat() throws Exception {
		try {
			String msstr = this.getParameter("msid");
			if (isNotEmpty(msstr)) {
				messageDAO.changeReadStat(parseList(msstr));
			}
			Map map = new HashMap();
			map.put("msids", msstr);
			return renderJson("标记消息成功", map);
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Polling message.
	 * 
	 * @return the string
	 */
	public String pollingMessage() {
		try {
			// 关闭日志输出
			setAttribute("debugEnabeld", "false");

			return renderJson(MessageHelper.queryMessageList(false));
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Polling task.
	 * 
	 * @return the string
	 */
	public String pollingTask() {
		try {
			// 关闭日志输出
			setAttribute("debugEnabeld", "false");

			String num = getParameter("size");
			if (isEmpty(num)) {
				num = "10";
			}
			return renderJson(UserTaskHelper.queryTaskList(Integer
					.parseInt(num)));
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Query task list.
	 * 
	 * @return the string
	 */
	public String queryTaskList() {
		try {
			String num = getParameter("size");
			if (isEmpty(num)) {
				num = "20";
			}
			return renderGrid(UserTaskHelper.queryTaskList(Integer
					.parseInt(num)));
		} catch (Exception ex) {
			return renderError(ex);
		}
	}
}
