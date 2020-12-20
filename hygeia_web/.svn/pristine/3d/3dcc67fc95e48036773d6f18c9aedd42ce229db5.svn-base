/*
 * Copyright 2016 Powersi. All rights reserved.
 */

package com.powersi.sys.web.filter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.ErrorObj;
import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.servlet.ProcessAll;
import com.powersi.hygeia.framework.util.DBFunc;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.ExceptionHelper;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.PaginationHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.filter.ActionResponseWrapper;
import com.powersi.hygeia.web.util.SessionHelper;
import com.powersi.hygeia.web.util.WebHelper;

/**
 * 预处理和执行过滤器.
 */
public class PrepareAndExecuteFilter_pcloud implements Filter {

	/** The excluded patterns. */
	protected List<Pattern> excludedPatterns = null;

	/** The included patterns. */
	protected List<Pattern> includedPatterns = null;

	/** The login page. */
	protected String loginPage = null;

	/** The error page. */
	protected String errorPage = null;

	/** The encoding. */
	protected String encoding = null;

	/** The check login. */
	protected boolean checkLogin = true;

	/** The client cache enabled. */
	protected boolean clientCacheEnabled = true;

	/** The static cache timeout. */
	protected int clientCacheTimeout = -1;

	/** The static patterns. */
	protected List<Pattern> clientCachePatterns = null;

	/** The _logger. */
	private static Log logger = LogFactory.getLog(LogHelper
			.getLoggerName(ProcessAll.class));

	/** The check jsp. */
	protected boolean checkJsp = true;

	/** The jsps. */
	private ConcurrentHashMap<String, Boolean> jsps = new ConcurrentHashMap<String, Boolean>();

	/** The words. */
	protected String[] words = new String[] { "Runtime.getRuntime",
			"HttpURLConnection", "ResultSetMetaData", "new File(",
			"isDirectory", "FileInputStream", "FileOutputStream" };

	/**
	 * The info enabled.
	 * 
	 * @param filterConfig
	 *            the filter config
	 * @throws ServletException
	 *             the servlet exception
	 */
	// private boolean infoEnabled = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(final FilterConfig filterConfig) throws ServletException {
		try {
			this.excludedPatterns = UtilFunc.buildPatternsList(filterConfig
					.getInitParameter("checklogin_excluded"));
			this.includedPatterns = UtilFunc.buildPatternsList(filterConfig
					.getInitParameter("checklogin_included"));

			this.encoding = filterConfig.getInitParameter("encoding");
			if (!UtilFunc.hasText(this.encoding)) {
				this.encoding = null;
			}

			String str = filterConfig.getInitParameter("checklogin_flag");
			if (str != null
					&& (str.equals("0") || str.equalsIgnoreCase("false"))) {
				this.checkLogin = false;
			} else {
				this.checkLogin = true;
			}

			str = filterConfig.getInitParameter("checkjsp_flag");
			if (str != null
					&& (str.equals("0") || str.equalsIgnoreCase("false"))) {
				this.checkJsp = false;
			} else {
				this.checkJsp = true;
			}

			final String controllerClass = filterConfig
					.getInitParameter("connection_controller");
			DBHelper.setController(controllerClass);

			str = filterConfig.getInitParameter("client_cache_enabled");
			if (str != null && str.equalsIgnoreCase("flase")) {
				this.clientCacheEnabled = false;
			} else {
				this.clientCacheEnabled = true;
			}

			str = filterConfig.getInitParameter("client_cache_timeout");
			if (str != null && str.trim().length() > 0) {
				this.clientCacheTimeout = Integer.parseInt(str);
				if (this.clientCacheTimeout > 0) {
					this.clientCachePatterns = UtilFunc
							.buildPatternsList(filterConfig
									.getInitParameter("client_cache_url"));
					if (UtilFunc.isEmpty(this.clientCachePatterns)) {
						this.clientCacheTimeout = -1;
					}
				}
			}

			// infoEnabled = logger.isInfoEnabled();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServletException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(final ServletRequest req, final ServletResponse res,
			final FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// 设置字符集
		WebHelper.setRequestEncoding(request, this.encoding);

		// 获取uri
		String uri = getPath(request);
		UserInfo user = null;
		boolean hasUserInfo = BusiContext.hasUserInfo();
		// 检查登录
		if (this.checkLogin) {
			if (uri.endsWith(WebHelper.getLoginPage())) {
				// 登录页面处理
			} else {
				if (hasUserInfo) {
					user = BusiContext.getUserInfo();
				} else {
					user = getUserInfo(request);
				}

				if (user == null || !user.isValidUser()) {
					if (isCheckLoginUri(uri)) {
						String errMsg = "未登录或者登录已经超时，请重新登录";
						// 记录出错的url和cookie，方便诊断错误
						StringBuilder sbEx = new StringBuilder(errMsg);
						sbEx.append("\nurl:").append(
								WebHelper.getRequestResource(request));
						sbEx.append("\ncookie:").append(
								WebHelper.getHeader(request, "cookie"));
						sbEx.append("\naddress:").append(
								WebHelper.getRemoteAddr(request));
						logger.warn(errMsg,
								new HygeiaException(sbEx.toString()));

						if (WebHelper.isAJAXRequest(request)) {
							ErrorObj err = new ErrorObj();
							err.setErrorType(8);
							err.setErrMsg(errMsg);
							WebHelper.writeJSONError(response, err);
						} else {
							StringBuilder sb = new StringBuilder();
							String urlLogin = combinPath(request,
									WebHelper.getLoginPage());
							sb.append(urlLogin);
							if (urlLogin.indexOf("?") < 0) {
								sb.append("?");
							} else {
								sb.append("&");
							}
							sb.append("url=");
							sb.append(UtilFunc.encodeBase64URL(WebHelper
									.getRequestResource(request)));

							response.sendRedirect(sb.toString());
						}

						return;
					}
				}
			}
		}

		// 检查资源类型
		int resourceType = getResourceType(uri);

		// 处理action响应
		if (resourceType == 0 && uri.endsWith(".action")) {
			response = new ActionResponseWrapper(response);
		}

		// 检查jsp
		if (checkJsp && resourceType == 0 && uri.endsWith(".jsp")) {
			if (!isSafeJsp(uri)) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
		}

		// 处理缓存
		handleClientCache(uri, resourceType, request, response);

		// 执行业务
		try {
			if (resourceType == 0) {
				// 动态资源处理请求响应信息
				BusiContext.setRequest(request);
				BusiContext.setResponse(response);

				// 动态资源处理用户信息
				if (!hasUserInfo) {
					if (!this.checkLogin) {
						user = getUserInfo(request);
					}

					if (user != null) {
						BusiContext.setUserInfo(user);
					}
				}
			}

			chain.doFilter(request, response);
		} catch (Throwable ex) {
			try {
				if (logger.isErrorEnabled()) {
					if (resourceType != 1 && !isClientAbortException(ex)) {// 排除静态资源和客户端中断异常
						logger.error(uri, ex);
					}
				}

				if (WebHelper.isAJAXRequest(request)) {
					WebHelper.writeJSONError(response,
							ExceptionHelper.getErrorStr(ex));
				} else {
					response.sendError(
							HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							ExceptionHelper.getErrorStr(ex));
				}

			} catch (Throwable pe) {
				// 忽略客户端中断异常
			}
		} finally {
			if (resourceType == 0) {
				// 必须按顺序调用
				DBHelper.close();

				DBFunc.shutdownBusi("");

				PaginationHelper.cleanup();
				BusiContext.cleanup();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		if (this.excludedPatterns != null) {
			this.excludedPatterns.clear();
			this.excludedPatterns = null;
		}
		if (this.clientCachePatterns != null) {
			this.clientCachePatterns.clear();
			this.clientCachePatterns = null;
		}
	}

	/**
	 * 检查是否客户端中断异常.
	 * 
	 * @param ex
	 *            the ex
	 * @return true, if is client abort exception
	 */
	private boolean isClientAbortException(final Throwable ex) {
		return WebHelper.isClientAbortException(ex);
	}

	/**
	 * Combin path.
	 * 
	 * @param request
	 *            the request
	 * @param page
	 *            the page
	 * @return the string
	 */
	private String combinPath(final HttpServletRequest request,
			final String page) {
		return request.getContextPath() + page;
	}

	/**
	 * Gets the uri from the request.
	 * 
	 * @param request
	 *            The request
	 * @return The uri
	 */
	private String getPath(final HttpServletRequest request) {
		return WebHelper.getRequestPath(request);
	}

	/**
	 * Checks if is check login uri.
	 * 
	 * @param uri
	 *            the uri
	 * @return true, if is check login uri
	 */
	private boolean isCheckLoginUri(final String uri) {
		if (includedPatterns != null) {
			return isCheckLoginIncluded(uri) && !isCheckLoginExcluded(uri);
		} else if (excludedPatterns != null) {
			return !isCheckLoginExcluded(uri);
		} else {
			return false;
		}
	}

	/**
	 * Checks if is login excluded.
	 * 
	 * @param uri
	 *            the uri
	 * @return true, if is login excluded
	 */
	private boolean isCheckLoginExcluded(final String uri) {
		if (excludedPatterns != null) {
			for (Pattern pattern : excludedPatterns) {
				if (pattern.matcher(uri).matches()) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks if is check login included.
	 * 
	 * @param uri
	 *            the uri
	 * @return true, if is check login included
	 */
	private boolean isCheckLoginIncluded(final String uri) {
		if (includedPatterns != null) {
			for (Pattern pattern : includedPatterns) {
				if (pattern.matcher(uri).matches()) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks if is client cache.
	 * 
	 * @param uri
	 *            the uri
	 * @return true, if is client cache
	 */
	private boolean isClientCacheUrl(final String uri) {
		if (this.clientCachePatterns != null) {
			for (Pattern pattern : clientCachePatterns) {
				if (pattern.matcher(uri).matches()) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Gets the resource type.
	 * 
	 * @param uri
	 *            the uri
	 * @return the resource type
	 */
	private int getResourceType(final String uri) {
		String str = UtilFunc.right(uri, 4).toLowerCase();

		// 静态资源
		if ((str.equals(".gif")) || (str.equals(".jpg"))
				|| (uri.endsWith(".js")) || (str.equals(".css"))
				|| (str.equals(".htm")) || (str.equals(".png"))
				|| (str.equals(".swf")) || (str.equals(".exe"))
				|| (str.equals(".cab")) || (str.equals(".rar"))
				|| (str.equals(".zip")) || (str.equals(".apk"))
				|| uri.endsWith(".html") || str.equals(".ttf")
				|| uri.endsWith(".woff") || uri.endsWith(".woff2")
				|| str.equals(".svg"))
			return 1;

		// 自动缓存
		if (str.equals(".pbd") || str.equals(".pbl")) {
			return 3;
		}

		// 需要缓存的动态资源
		if (this.clientCacheTimeout > 0) {
			if (isClientCacheUrl(uri)) {
				return 2;
			}
		}

		// 动态资源
		return 0;
	}

	/**
	 * Handle client cache.
	 * 
	 * @param uri
	 *            the uri
	 * @param resourceType
	 *            the resource type
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return true, if successful
	 */
	private boolean handleClientCache(final String uri, final int resourceType,
			final HttpServletRequest request, final HttpServletResponse response) {
		try {
			if (this.clientCacheEnabled && !WebHelper.isLocalRequest(request)) {
				// 静态资源
				if (resourceType == 1) {
					java.util.Date now = new java.util.Date();
					response.setHeader("Last-Modified",
							DateFunc.toGMTString(now));
					response.setHeader("Expires",
							DateFunc.toGMTString(DateFunc.addMonths(now, 12)));
					response.setHeader("Cache-Control", "public"); // HTTP/1.1
					response.setHeader("Pragma", "Pragma"); // HTTP/1.0

					return false;
				}

				// 数据窗口
				if (resourceType == 3) {
					// response.setHeader("Expires", "-1");

					// response.setHeader("Pragma", "Pragma");
					// response.setHeader("Cache-Control", "must-revalidate");

					// ie9以下不支持
					// response.setHeader("Cache-Control", "max-age=60");
					return false;
				}

				// 客户端缓存
				if (this.clientCacheTimeout > 0) {
					if (resourceType == 2) {
						java.util.Date now = new java.util.Date();
						response.setHeader("Last-Modified",
								DateFunc.toGMTString(now));
						response.setHeader("Expires", DateFunc
								.toGMTString(DateFunc.addMinutes(now,
										this.clientCacheTimeout)));
						response.setHeader("Cache-Control", "public"); // HTTP/1.1
						response.setHeader("Pragma", "Pragma"); // HTTP/1.0

						return false;
					}
				}
			}

			// 不使用缓存
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expires", "0");
			response.setHeader("Pragma", "No-cache");
		} catch (Exception ex) {
			logger.error("Error disable cache to '" + uri, ex);
		}

		return true;
	}

	/**
	 * Gets the user info.
	 * 
	 * @param request
	 *            the request
	 * @return the user info
	 */
	private UserInfo getUserInfo(final HttpServletRequest request) {
		return SessionHelper.getUserInfo(request);
	}

	/**
	 * Checks if is safe jsp.
	 * 
	 * @param uri
	 *            the uri
	 */
	private boolean isSafeJsp(String uri) {
		Boolean safeFlag = jsps.get(uri);
		if (safeFlag == null) {
			try {
				safeFlag = Boolean.TRUE;
				String path = GlobalContext.getPhysicalPath(uri);
				if (UtilFunc.fileExists(path)) {
					String jsp = UtilFunc.readFile(new File(path),
							GlobalContext.getCharset());
					if (UtilFunc.hasText(jsp)) {
						for (String word : words) {
							if (jsp.contains(word)) {
								LogHelper.getLogger().error(
										uri + "包含非法函数:" + word);
								safeFlag = Boolean.valueOf(false);
								break;
							}
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			jsps.putIfAbsent(uri, safeFlag);
			safeFlag = jsps.get(uri);
		}

		return safeFlag;
	}
}