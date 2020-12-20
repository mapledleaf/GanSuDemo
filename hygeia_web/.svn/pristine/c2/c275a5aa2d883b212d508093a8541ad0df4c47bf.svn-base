/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.manager.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.sys.manager.dao.LogDAO;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;
import com.powersi.sys.util.UserKindHelper;

/**
 * The Class QueryLoginLogAction.
 */
@Action(value = "QueryLoginLog", results = { @Result(name = "success", location = "/pages/sys/manager/LoginLog.jsp") })
public class QueryLoginLogAction extends BaseAction {

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

	/** The login flag. */
	String loginFlag = "all";

	/** The stat type. */
	List statType = null;

	/** The dao. */
	private static LogDAO dao = getBean(LogDAO.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		if ("true".equals(getParameter("detail_flag"))) {
			return detail();
		}

		try {
			if (UtilFunc.isEmpty(beginDate)) {
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
				renderGrid(dao.queryLoginLog(beginDate, endDate, paramMap));
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
			String loginId = getParameter("login_id");
			if (UtilFunc.isEmpty(loginId)) {
				throw new HygeiaException("登录编号不能为空");
			}

			return renderJson(dao.getLoginLog(loginId));
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
	 * Gets the login flag.
	 * 
	 * @return the login flag
	 */
	public String getLoginFlag() {
		return loginFlag;
	}

	/**
	 * Sets the login flag.
	 * 
	 * @param loginFlag
	 *            the new login flag
	 */
	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

	/**
	 * Gets the stat type.
	 * 
	 * @return the stat type
	 */
	public final List getStatType() {
		return statType;
	}

	/**
	 * Sets the stat type.
	 * 
	 * @param statType
	 *            the new stat type
	 */
	public final void setStatType(List statType) {
		this.statType = statType;
	}

	/**
	 * stat.
	 * 
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	@Action(value = "LoginLogStatis", results = { @Result(name = "success", location = "/pages/sys/manager/LoginLogStatis.jsp") })
	public String stat() throws Exception {
		try {
			if (UtilFunc.isEmpty(beginDate)) {
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

			if (statType == null) {
				String[] s = new String[] { "1", "2" };
				statType = Arrays.asList(s);
				paramMap.put("statType", s);
			}

			{
				if (statType == null || statType.contains("1")) {
					Map<Integer, Integer> loginStat = new TreeMap<Integer, Integer>();
					for (int i = 0; i < 24; i++) {
						loginStat.put(Integer.valueOf(i), Integer.valueOf(0));
					}
					List<Map> lst = dao.statLoginLog(beginDate, endDate,
							paramMap);
					for (Map m : lst) {
						loginStat.put(Integer.parseInt(m.get("x").toString()),
								Integer.parseInt(m.get("y").toString()));
					}
					setAttribute("loginStat", toJson(loginStat.values()));
				}

			}

			{
				if (statType == null || statType.contains("2")) {
					Map<Integer, Integer> logoutStat = new TreeMap<Integer, Integer>();
					for (int i = 0; i < 24; i++) {
						logoutStat.put(Integer.valueOf(i), Integer.valueOf(0));
					}

					List<Map> lst = dao.statLogoutLog(beginDate, endDate,
							paramMap);
					for (Map m : lst) {
						logoutStat.put(Integer.parseInt(m.get("x").toString()),
								Integer.parseInt(m.get("y").toString()));
					}

					setAttribute("logoutStat", toJson(logoutStat.values()));
				}
			}

			return SUCCESS;
		} catch (Exception ex) {
			return renderError(ex);
		}
	}

	/**
	 * Analysis.
	 * 
	 * @return the string
	 */
	@Action(value = "LoginLogAnalysis", results = { @Result(name = "success", location = "/pages/sys/manager/LoginLogAnalysis.jsp") })
	public String analysis() {
		if (isGetRequest()) {
			return SUCCESS;
		}

		try {
			String type = getParameter("type");

			PagerHelper.initPagination(getRequest());
			List lst = null;
			Map params = getAllParameters();
			if ("time".equals(type)) {
				lst = dao.analysisLoginLogByTime(params);
			} else if ("caller".equals(type)) {
				lst = dao.analysisLoginLogByUser(params);
			} else if ("date".equals(type)) {
				lst = dao.analysisLoginLogByDate(params);
			} else {
				lst = Collections.EMPTY_LIST;
			}

			DataGridHelper.render(getRequest(), getResponse(),
					PagerHelper.getPaginatedList(lst));
		} catch (Exception ex) {
			saveJSONError(ex);
		}

		return NONE;
	}
}