/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.sys.manager.dao.LogDAO;
import com.powersi.sys.util.UserKindHelper;

/**
 * The Class QueryLoginLogAction.
 */
@Action(value = "QueryOperateLog", results = { @Result(name = "success", location = "/pages/sys/manager/OperateLog.jsp") })
public class QueryOperateLogAction extends BaseAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The begin date. */
	String beginDate = null;

	/** The end date. */
	String endDate = null;

	/** The login user. */
	String loginUser = null;

	/** The user name. */
	String userName = null;

	/** The user kind. */
	List userKind = null;

	/** The operate flag. */
	String operateFlag = "all";

	/** The operate name. */
	String operateName = "";

	/** The dao. */
	private static LogDAO dao = getBean(LogDAO.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		if ("true".equals(getParameter("detail_flag"))) {
			return detail();
		}

		try {

			if (isEmpty(beginDate)) {
				String now = DateFunc.dateToString(new java.util.Date());
				beginDate = now + " 00:00:00";
				endDate = now + " 23:59:59";
			}

			Map<String, String> code = UserKindHelper.valueMap(getUserInfo()
					.getUserKind());
			setAttribute("codeUserKind", code);

			Map paramMap = new HashMap();
			paramMap.putAll(getAllParameters());
			if (userKind == null) {
				userKind = new ArrayList<String>();
				userKind.addAll(code.keySet());
				paramMap.put("userKind", UtilFunc.toStringArray(userKind));
			}

			if (isPostRequest()) {
				initPager();
				renderGrid(dao.queryOperateLog(beginDate, endDate, paramMap));
				return NONE;
			} else {
				return SUCCESS;
			}
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Detail.
	 * 
	 * @return the string
	 */
	public String detail() {
		try {
			String operateId = getParameter("operate_id");
			if (isEmpty(operateId)) {
				throw new HygeiaException("操作编号不能为空");
			}

			return renderJson(dao.getOperateLog(operateId));
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Gets the begin date.
	 * 
	 * @return the begin date
	 */
	public String getBeginDate() {
		return beginDate;
	}

	/**
	 * Sets the begin date.
	 * 
	 * @param beginDate
	 *            the new begin date
	 */
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate
	 *            the new end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the login user.
	 * 
	 * @return the login user
	 */
	public String getLoginUser() {
		return loginUser;
	}

	/**
	 * Sets the login user.
	 * 
	 * @param loginUser
	 *            the new login user
	 */
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 * 
	 * @param userName
	 *            the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the user kind.
	 * 
	 * @return the user kind
	 */
	public List getUserKind() {
		return userKind;
	}

	/**
	 * Sets the user kind.
	 * 
	 * @param userKind
	 *            the new user kind
	 */
	public void setUserKind(List userKind) {
		this.userKind = userKind;
	}

	/**
	 * Gets the operate flag.
	 * 
	 * @return the operate flag
	 */
	public String getOperateFlag() {
		return operateFlag;
	}

	/**
	 * Sets the operate flag.
	 * 
	 * @param operateFlag
	 *            the new operate flag
	 */
	public void setOperateFlag(String operateFlag) {
		this.operateFlag = operateFlag;
	}

	/**
	 * Gets the operate name.
	 * 
	 * @return the operate name
	 */
	public String getOperateName() {
		return operateName;
	}

	/**
	 * Sets the operate name.
	 * 
	 * @param operateName
	 *            the new operate name
	 */
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
}