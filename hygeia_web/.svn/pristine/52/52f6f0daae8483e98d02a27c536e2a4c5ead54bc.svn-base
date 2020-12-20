/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.web.filter;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.validation.Assertion;

//import com.neusoft.ec.service.sso.client.filter.JasigSignInFilter;
import com.powersi.hygeia.framework.GlobeContext;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.util.DBFunc;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.LoginHelper;
import com.powersi.hygeia.framework.util.NetworkHelper;
import com.powersi.hygeia.framework.util.UserHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.util.SessionHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.sys.util.SingleSignOnHelper;
import com.powersi.sys.util.UnifiedPlatformHelper;

/**
 * cas验证过滤器.
 */
public class CasAuthenticationFilter// extends JasigSignInFilter 
{
//	/** The title html. */
//	private static String titleHtml = null;
//
//	/** The noauth html. */
//	private static String noauthHtml = null;
//
//	/** The _server name. */
//	private String _serverName = null;
//
//	/** The cas server url prefix. */
//	private String casServerUrlPrefix = null;
//
//	/** The unauthorized url. */
//	private String noauthUrl = null;
//
//	/** The excluded patterns. */
//	protected List<Pattern> excludedPatterns = null;
//
//	/** The excluded url. */
//	private String excludedUrl = null;
//
//	/** The main page. */
//	private String mainPage = "/main.action";
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.neusoft.ec.service.sso.client.filter.JasigSignInFilter#init(javax
//	 * .servlet.FilterConfig)
//	 */
//	@Override
//	public void init(final FilterConfig filterConfig) throws ServletException {
//		super.init(filterConfig);
//
//		initCustom(filterConfig);
//	}
//
//	/**
//	 * Internal init.
//	 * 
//	 * @param filterConfig
//	 *            the filter config
//	 */
//	protected void initCustom(final FilterConfig filterConfig) {
//		try {
//			if (titleHtml != null) {
//				return;
//			}
//
//			if ("desktop".equals(ParameterMapping.getStringByCode("main_page"))) {
//				mainPage = "/pages/desktop.jsp";
//			}
//
//			if ("0".equals(ParameterMapping.getStringByCode("up_sync_flag"))) {
//				SingleSignOnHelper.setSyncFlag(false);
//			} else {
//				SingleSignOnHelper.setSyncFlag(true);
//			}
//
//			// 统一平台标志
//			if (UnifiedPlatformHelper.isServerFlag()) {
//				// 禁用同步
//				SingleSignOnHelper.setSyncFlag(false);
//			}
//
//			// 没有判断此值是否为空
//			if (ALLOW_URL == null) {
//				ALLOW_URL = "";
//			}
//
//			// 初始化sso配置
//			if (filterConfig == null) {
//				SingleSignOnHelper.setServerName(_serverName);
//				SingleSignOnHelper.setServerUrl(casServerUrlPrefix);
//				SingleSignOnHelper.setNoauthUrl(noauthUrl);
//			} else {
//				SingleSignOnHelper.setServerName(getPropertyFromInitParams(
//						filterConfig, "serverName", ""));
//				SingleSignOnHelper.setServerUrl(getPropertyFromInitParams(
//						filterConfig, "casServerUrlPrefix", ""));
//				SingleSignOnHelper.setNoauthUrl(getPropertyFromInitParams(
//						filterConfig, "unauthorizedUrl", ""));
//
//				excludedUrl = getPropertyFromInitParams(
//						filterConfig, "excludedUrl", null);
//
//				SingleSignOnHelper.setEnabled(true);
//			}
//
//			this.excludedPatterns = UtilFunc.buildPatternsList(excludedUrl);
//
//			String serviceUrl = String.format("%1$s%2$s",
//					SingleSignOnHelper.getServerName(),
//					GlobeContext.getContextPath());
//			String loginUrl = String.format("%1$s/login",
//					SingleSignOnHelper.getServerUrl());
//			String logoutUrl = String.format("%1$s/logout?service=%2$s",
//					SingleSignOnHelper.getServerUrl(), serviceUrl);
//			String exitUrl = String.format("%1$s/logout",
//					SingleSignOnHelper.getServerUrl());
//
//			SingleSignOnHelper.setLoginUrl(loginUrl);
//			SingleSignOnHelper.setLogoutUrl(logoutUrl);
//			SingleSignOnHelper.setExitUrl(exitUrl);
//
//			SingleSignOnHelper.setUsed(true);
//
//			StringBuilder sb = new StringBuilder(2048);
//			sb.append("CasAuthenticationFilter has initialized [")
//					.append(GlobeContext.getAppName()).append("]: ");
//			if (SingleSignOnHelper.isEnabled()) {
//				// 检查sso服务器状态（3秒超时)
//				boolean serverStatus = NetworkHelper.isServerReachable(
//						loginUrl, 3 * 1000);
//				sb.append("enabled\n");
//
//				sb.append("login_url = ").append(loginUrl).append("\n");
//				sb.append("logout_url = ").append(logoutUrl).append("\n");
//				sb.append("exit_url = ").append(exitUrl).append("\n");
//				sb.append("noauth_url = ")
//						.append(noauthUrl == null ? "" : noauthUrl)
//						.append("\n");
//				sb.append("excluded_url = ")
//						.append(excludedUrl == null ? "" : excludedUrl)
//						.append("\n");
//				sb.append("check_status = ")
//						.append(serverStatus ? "success" : "failed")
//						.append("\n");
//			} else {
//				sb.append("disabled\n");
//			}
//
//			System.out.println(sb.toString());
//
//			{
//				// 构造一个以系统名称为标题的页面
//				StringBuilder sbHtml = new StringBuilder(1024);
//				sbHtml.append("<html><head><title>");
//				sbHtml.append(ParameterMapping.getSystemName());
//				sbHtml.append("</title></head><body></body></html>");
//				titleHtml = sbHtml.toString();
//			}
//
//			{
//				if (UtilFunc.hasText(noauthUrl)) {
//					// 构造一个跳转无权限的页面
//					StringBuffer sbHtml = new StringBuffer();
//
//					sbHtml.append("<html> ");
//					sbHtml.append("<head> ");
//					sbHtml.append("<title>")
//							.append(ParameterMapping.getSystemName())
//							.append("</title> ");
//					sbHtml.append("<script type=\"text/javascript\"> ");
//					sbHtml.append("function gotoUrl(){ ");
//					/*
//					 * sbHtml.append("	window.location.href='"); if
//					 * (noauthUrl.startsWith("/")) {
//					 * sbHtml.append(GlobeContext.getContextPath()); }
//					 * sbHtml.append(noauthUrl.trim()).append("'; ");
//					 */
//					sbHtml.append("} ");
//					sbHtml.append("function init(){ if(window == top){");// 非内嵌页面执行注销
//					sbHtml.append("	var iframe = document.getElementById('logout'); ");
//					sbHtml.append("	iframe.src = '")
//							.append(SingleSignOnHelper.getExitUrl())
//							.append("'; ");
//					sbHtml.append("	if (iframe.attachEvent) {  ");
//					sbHtml.append("		iframe.attachEvent(\"onload\", gotoUrl); ");
//					sbHtml.append("	} else { ");
//					sbHtml.append("		iframe.onload = gotoUrl; ");
//					sbHtml.append("	} ");
//					sbHtml.append("} else{gotoUrl();}}");
//					sbHtml.append("</script> ");
//					sbHtml.append("</head> ");
//					sbHtml.append("<body onload=\"init()\" style=\"overflow:hidden\"><div><h1 style=\"color: #2778ec;margin-top:120px;text-align:center;\">您没有权限访问");
//					sbHtml.append(ParameterMapping.getSystemName());
//					sbHtml.append("。</h1></div>");
//					sbHtml.append("	<div style='display: none'> ");
//					sbHtml.append("		<iframe id='logout' name='logout'></iframe> ");
//					sbHtml.append("	</div> ");
//					sbHtml.append("</body> ");
//					sbHtml.append("</html> ");
//
//					noauthHtml = sbHtml.toString();
//				}
//
//			}
//		} catch (Exception ex) {
//			LogHelper.getLogger().warn("初始化CasAuthenticationFilter失败", ex);
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.jasig.cas.client.util.AbstractCasFilter#destroy()
//	 */
//	@Override
//	public void destroy() {
//		if (this.excludedPatterns != null) {
//			this.excludedPatterns.clear();
//			this.excludedPatterns = null;
//		}
//
//		System.out.println("CasAuthenticationFilter has destroy");
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.neusoft.ec.service.sso.client.filter.JasigSignInFilter#doFilter(javax
//	 * .servlet.ServletRequest, javax.servlet.ServletResponse,
//	 * javax.servlet.FilterChain)
//	 */
//	@Override
//	public void doFilter(ServletRequest servletRequest,
//			ServletResponse servletResponse,
//			FilterChain filterChain) throws IOException, ServletException {
//		final HttpServletRequest request = (HttpServletRequest) servletRequest;
//		final HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//		// 字符集处理(覆盖其它filter设置的字符集）
//		WebHelper.setRequestEncoding(request);
//
//		String path = WebHelper.getRequestPath(request);
//		if (path == null) {
//			path = "";
//		}
//
//		// 处理管理地址(重定向到根目录)
//		if (path.startsWith("/admin/")) {
//			String forwardPath = path.substring(6);
//			if (forwardPath.length() == 0 || forwardPath.equals("/")
//					|| forwardPath.equals(WebHelper.getLoginPage())) {
//				WebHelper.writeText(response, titleHtml);
//			} else if (forwardPath.startsWith("/login/logout.action")) {
//				response.sendRedirect(request.getContextPath()
//						+ "/admin/login/login.htm?action=relogin&id="
//						+ UtilFunc.getUUID());
//			} else {
//				request.getRequestDispatcher(forwardPath).forward(request,
//						response);
//			}
//
//			return;
//		}
//
//		// sso没有启用或者请求需要排除
//		if (!SingleSignOnHelper.isEnabled() || isRequestExcluded(path)) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//
//		// 自定义浏览器处理
//		boolean isUserBrowser = checkUserBrowser(request);
//		if (isUserBrowser) {
//			if ("SysTitle".equals(request.getHeader("UserIndentity"))) {
//				WebHelper.writeText(response, titleHtml);
//				return;
//			}
//		}
//
//		// 1 登录 2 自动登录 9重登录
//		int loginPageType = 0;
//		if (path.equals(WebHelper.getLoginPage())) {
//			loginPageType = 1;
//		} else if (path.startsWith("/login/")
//				&& !path.startsWith("/login/settings")) {
//			if (path.equals("/login/logout.action")) {
//				loginPageType = 0;
//			} else {
//				String action = request.getParameter("action");
//				if ("autologin".equals(action)) {
//					loginPageType = 2;
//				} else if ("relogin".equals(action)) {
//					loginPageType = 9;
//				} else {
//					loginPageType = 1;
//				}
//
//				// 为了兼容以前旧版用户浏览器不能识别的问题
//				if (!isUserBrowser && path.equals("/login/login.htm")) {
//					isUserBrowser = true;
//				}
//			}
//		} else {
//			loginPageType = 0;
//		}
//
//		// sso用户处理
//		UserInfo userInfo = null;
//		final HttpSession session = request.getSession(false);
//		if (session != null) {
//			userInfo = SessionHelper.getUserInfo(session);
//
//			// 已经登录的用户处理
//			if (userInfo != null && userInfo.isValidUser()) {
//				boolean validUser = true;
//				if (loginPageType > 0) {
//					// 重登录
//					validUser = false;
//				} else {
//					Assertion assertion = (Assertion) session
//							.getAttribute(CONST_CAS_ASSERTION);
//
//					if (assertion != null
//							&& !userInfo.getLoginUser().equals(
//									assertion.getPrincipal().getName())) {
//						// 本地用户是否和sso用户一致
//						validUser = false;
//					}
//				}
//
//				if (validUser) {
//					filterChain.doFilter(request, response);
//					return;
//				} else {
//					// 需要重置本地用户，避免本地用户和sso用户不一致的问题出现
//					SessionHelper.removeUserInfo(request);
//					userInfo = null;
//				}
//			}
//
//			if (loginPageType != 9) {
//				if (userInfo == null || !userInfo.isValidUser()) {
//					Assertion assertion = (Assertion) session
//							.getAttribute(CONST_CAS_ASSERTION);
//
//					if (assertion != null) {
//						// 获取cas的登录名
//						String loginUser = assertion.getPrincipal().getName();
//
//						// 同步用户信息
//						SingleSignOnHelper.syncUser(loginUser);
//
//						//获取用户类型
//						String userKind = getUserKind(loginUser);
//						if(userKind != null){
//							// 获取用户信息
//							userInfo = UserHelper.getUserByLogin(userKind, loginUser);
//						}
//						
//						// 检查用户权限
//						if (userInfo == null
//								|| UtilFunc.isEmpty(userInfo.getRoleId())) {
//							// 无权限跳转到指定页面
//							if (noauthHtml != null) {
//								// 避免死循环跳转
//								if (SingleSignOnHelper.getNoauthUrl().equals(
//										path)) {
//									filterChain.doFilter(request, response);
//									return;
//								}
//
//								try {
//									if (userInfo != null
//											&& userInfo.isValidUser()) {
//										LoginHelper.logout(userInfo,
//												LoginHelper.LOGOUT_CLIENTCLOSE);
//									}
//								} catch (Exception ex) {
//
//								}
//
//								response.setContentType("text/html; charset="
//										+ GlobeContext.getCharset());
//								response.getWriter().println(noauthHtml);
//								return;
//							}
//						}
//
//						// 初始化单点登录用户(不存在则初始化)
//						if (userInfo == null) {
//							userInfo = new UserInfo();
//						}
//						SingleSignOnHelper.initUser(userInfo, userKind, loginUser);
//
//						userInfo.setLoginAddress(SessionHelper
//								.getRemoteAddr(request));
//						SessionHelper.setUserInfo(request, userInfo);
//					}
//				}
//			}
//		}
//
//		// 处理浏览器的登录
//		if (loginPageType > 0) {
//			boolean isValidUser = (userInfo != null && userInfo.isValidUser());
//			if (loginPageType == 1
//					&& "true".equals(request.getParameter("useBrowser"))
//					&& isValidUser) {
//				// 自定义浏览器的自动跳转
//				request.setAttribute("autoLogin", "true");
//				request.setAttribute("loginUser", userInfo.getLoginUser());
//
//				filterChain.doFilter(request, response);
//				return;
//			}
//
//			if (isUserBrowser) {
//				if (loginPageType == 1) {// login
//					if (isValidUser) {
//						response.sendRedirect(getAutoLoginUrl(userInfo
//								.getLoginUser()));
//						return;
//					}
//				} else if (loginPageType == 2) {// autologin
//					filterChain.doFilter(request, response);
//					return;
//				} else if (loginPageType == 9) {// relogin
//					response.sendRedirect(SingleSignOnHelper.getLoginUrl()
//							+ "?service=" + SingleSignOnHelper.getServerName()
//							+ GlobeContext.getContextPath()
//							+ "/login/login.htm?action=autologin&id="
//							+ UtilFunc.getUUID());
//					return;
//				}
//			} else {
//				// 处理浏览器登录
//				if (loginPageType != 9) {
//					if (isValidUser) {// login or autologin
//						try {
//							// 记录登录日志
//							LoginHelper.login(userInfo,
//									LoginHelper.LOGIN_SUCCESS, "单点登录");
//						} catch (Exception ex) {
//							LogHelper.getLogger().warn("记录单点登录日志出错", ex);
//						}
//
//						// 跳转主页面
//						response.sendRedirect(request.getContextPath()
//								+ mainPage);
//						return;
//					}
//				} else {// relogin
//					response.sendRedirect(SingleSignOnHelper.getLoginUrl()
//							+ "?service="
//							+ SingleSignOnHelper.getServerName()
//							+ GlobeContext.getContextPath()
//							+ mainPage);
//					return;
//				}
//			}
//		}
//
//		super.doFilter(request, response, filterChain);
//	}
//
//	/**
//	 * Gets the auto login url.
//	 * 
//	 * @param loginUser
//	 *            the login user
//	 * @return the auto login url
//	 */
//	private String getAutoLoginUrl(String loginUser) {
//		StringBuilder sb = new StringBuilder(256);
//		sb.append(GlobeContext.getContextPath());
//		sb.append("/login/login.htm?action=autologin");
//		sb.append("&loginuser=").append(loginUser);
//		sb.append("&id=").append(UtilFunc.getUUID());
//
//		return sb.toString();
//	}
//
//	/**
//	 * Checks if is request excluded.
//	 *
//	 * @param uri the uri
//	 * @return true, if is request excluded
//	 */
//	protected boolean isRequestExcluded(String uri) {
//		if (excludedPatterns != null) {
//			for (Pattern pattern : excludedPatterns) {
//				if (pattern.matcher(uri).matches()) {
//					return true;
//				}
//			}
//		}
//
//		return false;
//	}
//
//	/**
//	 * Checks if is user browser.
//	 * 
//	 * @param request
//	 *            the request
//	 * @return true, if is user browser
//	 */
//	protected boolean checkUserBrowser(HttpServletRequest request) {
//		if (request.getHeader("UserIndentity") != null) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/**
//	 * Gets the user kind.
//	 *
//	 * @param loginUser the login user
//	 * @return the user kind
//	 */
//	protected String getUserKind(String loginUser) {
//		Connection conn = null;
//		try {
//			conn = DBFunc.getSimpleConnection();
//			/*
//			String sql = "select 9 as user_kind\n" +
//					"  from sys_staff\n" +
//					" where login_user = ?\n" +
//					"union all\n" +
//					"select user_kind\n" +
//					"  from sys_user_info\n" +
//					" where login_user = ?";
//			*/String sql = "select user_kind\n" + 
//					"  from sys_user_info\n" + 
//					" where login_user = ?\n" + 
//					"union all\n" + 
//					"select 9 as user_kind\n" + 
//					"  from sys_staff\n" + 
//					" where login_user = ?";
//
//			Object obj = DBFunc.executeScale(conn, sql, new Object[] {
//					loginUser, loginUser });
//			if (obj != null) {
//				return obj.toString();
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			DBFunc.closeConnection(conn);
//		}
//		return null;
//	}
//
//	/**
//	 * Sets the enabled.
//	 * 
//	 * @param enabled
//	 *            the new enabled
//	 */
//	public final void setEnabled(boolean enabled) {
//		SingleSignOnHelper.setEnabled(enabled);
//
//		initCustom(null);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * org.jasig.cas.client.util.AbstractCasFilter#setServerName(java.lang.String
//	 * )
//	 */
//	@Override
//	public final void setServerName(final String serverName) {
//		_serverName = serverName;
//
//		super.setServerName(serverName);
//	}
//
//	/**
//	 * Gets the cas server url prefix.
//	 * 
//	 * @return the cas server url prefix
//	 */
//	public String getCasServerUrlPrefix() {
//		return casServerUrlPrefix;
//	}
//
//	/**
//	 * Sets the cas server url prefix.
//	 * 
//	 * @param casServerUrlPrefix
//	 *            the new cas server url prefix
//	 */
//	public void setCasServerUrlPrefix(String casServerUrlPrefix) {
//		this.casServerUrlPrefix = casServerUrlPrefix;
//	}
//
//	/**
//	 * Gets the noauth url.
//	 * 
//	 * @return the noauth url
//	 */
//	public String getNoauthUrl() {
//		return noauthUrl;
//	}
//
//	/**
//	 * Sets the noauth url.
//	 * 
//	 * @param noauthUrl
//	 *            the new noauth url
//	 */
//	public void setNoauthUrl(String noauthUrl) {
//		this.noauthUrl = noauthUrl;
//	}
//
//	/**
//	 * Gets the excluded url.
//	 * 
//	 * @return the excluded url
//	 */
//	public String getExcludedUrl() {
//		return excludedUrl;
//	}
//
//	/**
//	 * Sets the excluded url.
//	 * 
//	 * @param excludedUrl
//	 *            the new excluded url
//	 */
//	public void setExcludedUrl(String excludedUrl) {
//		this.excludedUrl = excludedUrl;
//	}
}
