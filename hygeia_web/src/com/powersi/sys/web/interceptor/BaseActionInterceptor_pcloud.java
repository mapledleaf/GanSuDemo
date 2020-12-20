/**
 * Copyright 2014 Powersi. All rights reserved.
 * 
 */
package com.powersi.sys.web.interceptor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.MenuRightMapping;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.SysMenu;
import com.powersi.hygeia.framework.TaskController;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.entity.SysOperateLog;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.stat.profile.Profiler;
import com.powersi.hygeia.framework.task.SaveOperateLogTask;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.ExceptionHelper;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.WebHelper;

/**
 * BaseAction拦截器.
 */
public final class BaseActionInterceptor_pcloud extends AbstractInterceptor {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The _logger. */
	private static Log logger = LogFactory.getLog(LogHelper
			.getLoggerName(BaseAction.class));

	/** The check right. */
	private boolean checkRight = true;

	/** The ignore url pattens. */
	private List<Pattern> ignoreUrlPattens = null;

	/** The debug enabled. */
	private boolean debugEnabeld = true;

	/** The max param size. */
	private static int MAX_PARAM_SIZE = 1024 * 100;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#init()
	 */
	@Override
	public void init() {
		super.init();

		checkRight = !ParameterMapping.getStringByCode("check_action_right",
				"1").equals("0");

		debugEnabeld = logger.isDebugEnabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#destroy()
	 */
	@Override
	public void destroy() {
		if (this.ignoreUrlPattens != null) {
			this.ignoreUrlPattens.clear();
			this.ignoreUrlPattens = null;
		}

		super.destroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com
	 * .opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(final ActionInvocation invocation) throws Exception {
		String retVal = "";
		String actionName = "";
		String actionUrl = "";

		String viewUri = null;
		String className = null;
		String methodName = null;
		String classShortName = null;
		long beginTimeMillis = 0;

		int logFlag = 0;
		java.util.Date beginDate = null;
		boolean operateFlag = false;
		boolean commitFlag = false;
		ActionSupport actionSupport = null;

		UserInfo user = null;
		SysMenu sysMenu = null;

		long startNano = 0;

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			// 用户信息
			user = BusiContext.getUserInfo();

			// 字符集处理
			WebHelper.setRequestEncoding(request);

			// action解析
			Object action = invocation.getAction();
			if (action != null && action instanceof ActionSupport) {
				actionSupport = (ActionSupport) action;
			}

			// url处理
			ActionProxy proxy = invocation.getProxy();
			if (proxy != null) {
				// 统计处理
				if (Profiler.isEnable()) {
					startNano = System.nanoTime();

					Profiler.enter(Profiler.PROFILE_TYPE_ACTION, action
							.getClass().getCanonicalName(), proxy.getMethod());
				}

				StringBuilder sb = new StringBuilder();
				String namespace = proxy.getNamespace();
				if (!namespace.equals("/")) {
					sb.append(proxy.getNamespace());
				}
				sb.append("/");
				sb.append(proxy.getActionName());

				if (request.getServletPath().indexOf("!") > 0) {
					sb.append("!");
					sb.append(proxy.getMethod());
				}

				actionName = sb.toString();

				String queryString = request.getQueryString();
				if (!UtilFunc.isEmpty(queryString)) {
					if (!GlobalContext.isWeblogic()) {
						try {
							if (java.nio.charset.Charset.forName("ISO-8859-1")
									.newEncoder().canEncode(queryString)) {
								queryString = new String(
										queryString.getBytes("ISO-8859-1"),
										GlobalContext.getCharset());
							}
						} catch (final Exception ex) {
							// ex.printStackTrace();
						}
					}

					sb.append("?").append(queryString);
				}

				actionUrl = sb.toString();

				if (debugEnabeld) {
					className = action.getClass().getName();
					methodName = proxy.getMethod();
					if (!UtilFunc.hasLength(methodName)) {
						methodName = "execute";
					}
					classShortName = action.getClass().getSimpleName();

					beginTimeMillis = System.currentTimeMillis();
				}
			}

			// 权限处理
			int rightFlag = 1;
			boolean loginFlag = true;
			if (checkRight) {
				List<String> menuList = findMenu(actionUrl, actionName);
				Set roleSet = user.getRoleSet();

				rightFlag = 0;
				loginFlag = user.isValidUser();

				int gradeId = Integer.parseInt(UtilFunc.getString(user,
						"grade_id", "0"));
				// 遍历菜单
				if (!UtilFunc.isEmpty(menuList)) {
					for (String menuId : menuList) {
						sysMenu = getMenu(menuId);
						if (sysMenu == null) {
							continue;
						}

						String rightMenuId = sysMenu.getRightMenuId();

						// 无效的菜单
						if ("-1".equals(rightMenuId)) {
							continue;
						}

						// 权限检查
						if ("0".equals(rightMenuId)
								|| checkRight(rightMenuId, roleSet)) {

							// 菜单等级
							if (gradeId >= sysMenu.getGradeId().intValue()) {
								rightFlag = 1;
								loginFlag = true;
								break;
							} else {
								rightFlag = -1;
							}
						}
					}
				}

				// 检查url是否需要忽略
				if (!loginFlag || rightFlag != 1) {
					// jetty缺省页的actionName为/*/
					if (isIgnoreUrl(actionName)
							|| (actionName.startsWith("/") && actionName
									.endsWith("/"))) {
						rightFlag = 1;
						loginFlag = true;
					}
				}
			} else {
				List<String> menuList = findMenu(actionUrl, actionName);
				if (!UtilFunc.isEmpty(menuList)) {
					for (String menuId : menuList) {
						sysMenu = getMenu(menuId);
						if (sysMenu != null) {
							break;
						}
					}
				}
			}

			{
				// 处理action的url(某些url带的参数太大，导致内存溢出)
				String opAction = null;
				if (sysMenu != null) {
					opAction = sysMenu.getWinName();
				} else {
					// 超过1000字符的自带截断参数
					if (actionUrl.length() > 1000) {
						int idx = actionUrl.indexOf("?");
						if (idx > 0) {
							opAction = actionUrl.substring(0, idx);
						}
					}
					if (opAction == null) {
						opAction = actionUrl;
					}
				}

				if (opAction != null && opAction.length() > 1000) {
					opAction = UtilFunc.truncate(opAction, 990) + "...";
				}

				BusiContext.setContext("action", opAction);
			}

			// 登录处理
			if (!loginFlag) {
				String errMsg = "未登录或者登录已经超时，请重新登录";
				viewUri = "未登录";

				// 记录出错的url和cookie，方便诊断错误
				StringBuilder sbEx = new StringBuilder(errMsg);
				sbEx.append("\nurl:").append(
						WebHelper.getRequestResource(request));
				sbEx.append("\ncookie:").append(
						WebHelper.getHeader(request, "cookie"));
				sbEx.append("\naddress:").append(
						WebHelper.getRemoteAddr(request));
				logger.warn(errMsg, new HygeiaException(sbEx.toString()));

				if (WebHelper.isAJAXRequest(request)) {
					WebHelper.writeJSONError(response, errMsg);
					retVal = Action.NONE;
				} else {
					WebHelper.addError(request, errMsg);
					if (actionSupport != null) {
						actionSupport.addActionError(errMsg);
					}

					retVal = Action.LOGIN;
				}

				return retVal;
			}

			// 权限处理
			if (rightFlag != 1) {
				String errMsg = null;
				if (rightFlag != -1) {
					errMsg = "您没有权限操作["
							+ (sysMenu == null ? actionName : sysMenu
									.getMenuName()) + "]。";
					viewUri = "没有权限("
							+ UtilFunc.getString(user, "login_user", "") + ")";
				} else {
					errMsg = "您的权限等级不足，不能操作["
							+ (sysMenu == null ? actionName : sysMenu
									.getMenuName()) + "]。";
					viewUri = "权限等级不足("
							+ UtilFunc.getString(user, "login_user", "") + ")";
				}

				if (WebHelper.isAJAXRequest(request)) {
					WebHelper.writeJSONError(response, errMsg);
					retVal = Action.NONE;
				} else {
					WebHelper.addError(request, errMsg);
					if (actionSupport != null) {
						actionSupport.addActionError(errMsg);
					}

					retVal = Action.ERROR;
				}

				// 记录没有权限的记录，方便维护
				logFlag = 1;

				return retVal;
			}

			BusiContext.setContext("sys_menu", sysMenu);

			// 日志处理
			if (sysMenu != null) {
				logFlag = sysMenu.getLogFlag().intValue();
			}

			if (logFlag > 0) {
				beginDate = new java.util.Date();
			}

			// 业务处理
			retVal = invocation.invoke();

			// 结果处理
			if (Action.ERROR.equals(retVal)) {
				operateFlag = false;// 返回错误
			} else {
				if (actionSupport != null && actionSupport.hasActionErrors()) {
					operateFlag = false;
				} else {
					operateFlag = true;
				}
			}

			// 数据库事务处理
			if (operateFlag) {
				DBHelper.commitAndClose();
				commitFlag = true;

				try {
					TaskController.runCommitAfterTask();
				} catch (Exception ex) {
					logger.warn("运行数据库事务提交任务异常", ex);
				}
			}
		} catch (Throwable ex) {
			if (logger.isWarnEnabled()) {
				logger.error("Action异常", ex);
			}

			// if (response.isCommitted()) {
			// return Action.NONE;
			// }

			try {
				String errMsg = ExceptionHelper.getErrorStr(ex);
				if (WebHelper.isAJAXRequest(request)) {
					WebHelper.writeJSONError(response, errMsg);
					retVal = Action.NONE;
				} else {
					WebHelper.addError(request, errMsg);
					if (actionSupport != null) {
						actionSupport.addActionError(errMsg);
					}

					retVal = Action.ERROR;
				}
			} catch (Exception err) {
				logger.warn("处理Action异常信息出错", err);
			}
		} finally {
			// 数据库事务处理
			if (!commitFlag) {
				DBHelper.rollbackAndClose();

				try {
					TaskController.clearCommitAfterTask();
				} catch (Exception ex) {
					LogHelper.getLogger().warn("清理事务提交任务异常", ex);
				}
			}

			// 系统日志输出
			if (debugEnabeld
					&& !"false".equals(request.getAttribute("debugEnabeld"))) {
				try {
					if (viewUri == null) {
						viewUri = UtilFunc
								.getString((Map) ServletActionContext
										.getContext().get("request"),
										"struts.view_uri");
					}

					String actionClass = String.format(
							"%5$dms at %1$s.%3$s(%2$s.java:%4$d)", className,
							classShortName, methodName, 1,
							System.currentTimeMillis() - beginTimeMillis);

					writeLog(actionUrl, getParametersString(request), retVal,
							actionClass, viewUri);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			// 保存业务日志
			if (logFlag > 0) {
				saveLog(request, user, logFlag, actionUrl, sysMenu, beginDate,
						operateFlag, actionSupport);
			}

			// 统计处理
			if (startNano > 0) {
				Profiler.release(System.nanoTime() - startNano);
			}
		}

		return retVal;
	}

	/**
	 * Find menu.
	 * 
	 * @param actionUrl
	 *            the action url
	 * @param actionName
	 *            the action name
	 * @return the list
	 */
	private List<String> findMenu(final String actionUrl,
			final String actionName) {
		List<String> menuList = MenuRightMapping.findMenu(actionUrl);
		if (UtilFunc.isEmpty(menuList)) {
			menuList = MenuRightMapping.findMenu(actionName);
		}

		return menuList;
	}

	/**
	 * Gets the menu.
	 * 
	 * @param menuId
	 *            the menu id
	 * @return the menu
	 */
	private SysMenu getMenu(final String menuId) {
		return MenuRightMapping.getMenu(menuId);
	}

	/**
	 * Check right.
	 * 
	 * @param rightMenuId
	 *            the right menu id
	 * @param roleSet
	 *            the role set
	 * @return true, if successful
	 * @throws HygeiaException
	 *             the hygeia exception
	 */
	private boolean checkRight(final String rightMenuId, final Set roleSet)
			throws HygeiaException {
		return MenuRightMapping.hasMenuRole(rightMenuId, roleSet);
	}

	/**
	 * Write log.
	 * 
	 * @param actionUrl
	 *            the action url
	 * @param paramVal
	 *            the param val
	 * @param retVal
	 *            the ret val
	 * @param actionClass
	 *            the action class
	 * @param viewUri
	 *            the view uri
	 */
	private void writeLog(final String actionUrl, final String paramVal,
			final String retVal, final String actionClass, final String viewUri) {
		try {
			StringBuilder sb = new StringBuilder();

			sb.append("\naction: ");
			sb.append(actionUrl);

			sb.append("\nparam : ");
			int paramLen = (paramVal == null) ? 0 : paramVal.length();
			if (paramLen > MAX_PARAM_SIZE) {
				sb.append(paramVal.substring(0, MAX_PARAM_SIZE));

				sb.append("... ");
				sb.append(paramLen - MAX_PARAM_SIZE);
				sb.append(" more (size ");
				sb.append(paramLen);
				sb.append(")");
			} else {
				sb.append(paramVal);
			}

			sb.append("\nresult: ");
			sb.append(retVal);

			sb.append("\nexec  : ");
			sb.append(actionClass);

			sb.append("\nview  : ");
			sb.append(viewUri);

			logger.debug(sb);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Save log.
	 * 
	 * @param request
	 *            the request
	 * @param userInfo
	 *            the user info
	 * @param logFlag
	 *            the log flag
	 * @param actionUrl
	 *            the action url
	 * @param sysMenu
	 *            the sys menu
	 * @param beginDate
	 *            the begin date
	 * @param operateFlag
	 *            the operate flag
	 * @param actionSupport
	 *            the action support
	 */
	private void saveLog(final HttpServletRequest request,
			final UserInfo userInfo, final int logFlag, final String actionUrl,
			final SysMenu sysMenu, final java.util.Date beginDate,
			final boolean operateFlag, final ActionSupport actionSupport) {
		try {
			if (logFlag < 1) {
				return;
			}

			SysOperateLog sysOperateLog = new SysOperateLog();

			// 异步生成主键
			// sysOperateLog.setOperateId();

			// 没有登录
			if (userInfo == null || StringUtils.isBlank(userInfo.getLoginId())) {
				sysOperateLog.setLoginId(Integer.valueOf(0).toString());
				sysOperateLog.setLoginUser(null);
				sysOperateLog.setUserId(null);
				sysOperateLog.setUserName(null);
				sysOperateLog.setUserKind(Integer.valueOf(0));
				sysOperateLog.setLoginAddress(WebHelper.getRemoteAddr(request));
			} else {
				sysOperateLog.setLoginId(UtilFunc
						.getString(userInfo, "login_id", "0"));
				sysOperateLog.setLoginUser(UtilFunc.getString(userInfo,
						"login_user"));
				sysOperateLog.setUserId(UtilFunc.stringToInteger(UtilFunc
						.getString(userInfo, "user_id", "0")));
				sysOperateLog.setUserName(UtilFunc.getString(userInfo,
						"user_name"));
				sysOperateLog.setUserKind(UtilFunc.stringToInteger(UtilFunc
						.getString(userInfo, "user_kind", "0")));
				sysOperateLog.setLoginAddress(UtilFunc.getString(userInfo,
						"login_address"));
			}

			sysOperateLog.setOperateAction(actionUrl);
			if (sysMenu != null) {
				sysOperateLog.setOperateName(sysMenu.getMenuName());
				sysOperateLog.setBizId("menuid" + sysMenu.getMenuId());
			}

			sysOperateLog.setOperateFlag(Integer.valueOf(operateFlag ? 1 : 2));

			String operateParam = logFlag > 1 ? getParametersString(request)
					: "";
			if (operateParam != null && operateParam.length() > 1000) {
				sysOperateLog.setOperateParam(operateParam.substring(0, 1000));
			} else {
				sysOperateLog.setOperateParam(operateParam);
			}

			String remark = null;
			if (!operateFlag && actionSupport != null) {
				if (actionSupport.hasActionErrors()) {
					remark = UtilFunc
							.join(actionSupport.getActionErrors(), ",");
				}
			}

			if (remark != null && remark.length() > 500) {
				sysOperateLog.setRemark(remark.substring(0, 500));
			} else {
				sysOperateLog.setRemark(remark);
			}

			java.util.Date now = new java.util.Date();
			sysOperateLog.setBeginDate(beginDate == null ? now : beginDate);
			sysOperateLog.setEndDate(now);

			SaveOperateLogTask task = new SaveOperateLogTask();
			task.setLog(sysOperateLog);
			TaskController.execute(task);
		} catch (Exception ex) {
			logger.warn("保存业务日志出错", ex);
		}
	}

	/**
	 * Gets the all parameters.
	 * 
	 * @param request
	 *            the request
	 * @return the all parameters
	 */
	private String getParametersString(final HttpServletRequest request) {
		try {
			Map map = WebHelper.getAllParameters(request);

			JSONObject jsonObjct = JSONObject.fromObject(map);
			return jsonObjct.toString();
			/*
			 * StringBuilder sb = new StringBuilder(); sb.append("{"); if
			 * (!UtilFunc.isEmpty(map)) { String split = null; Object value =
			 * null; for (Iterator it = map.entrySet().iterator();
			 * it.hasNext();) { Entry entry = (Entry) it.next(); if (split !=
			 * null) { sb.append(split); } else { split = ", "; }
			 * sb.append(entry.getKey()); sb.append("="); value =
			 * entry.getValue();
			 * 
			 * if (value instanceof Object[]) { sb.append("[");
			 * sb.append(UtilFunc.arrayToString((Object[]) value, ", "));
			 * sb.append("]"); } else { sb.append(value == null ? "" : value); }
			 * } } sb.append("}");
			 * 
			 * return sb.toString();
			 */
		} catch (Exception ex) {
			return ExceptionHelper.getThrowableStr(ex);
		}

	}

	/**
	 * Sets the ignore url pattens.
	 * 
	 * @param patterns
	 *            the new ignore url pattens
	 */
	public void setIgnoreUrlPattens(final String patterns) {
		this.ignoreUrlPattens = UtilFunc.buildPatternsList(patterns);
	}

	/**
	 * Checks if is login excluded.
	 * 
	 * @param uri
	 *            the uri
	 * @return true, if is login excluded
	 */
	private boolean isIgnoreUrl(final String uri) {
		if (this.ignoreUrlPattens != null) {
			for (Pattern pattern : ignoreUrlPattens) {
				if (pattern.matcher(uri).matches()) {
					return true;
				}
			}
		}

		return false;
	}
}
