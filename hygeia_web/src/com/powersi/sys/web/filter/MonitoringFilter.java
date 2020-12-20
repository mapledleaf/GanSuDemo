/*
 * Copyright 2013 Powersi. All rights reserved.
 */

package com.powersi.sys.web.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.util.SessionHelper;
import com.powersi.hygeia.web.util.WebHelper;

/**
 * The Class MonitoringFilter.
 */
public class MonitoringFilter implements Filter {
	/** The _logger. */
	private static Log logger = LogFactory.getLog(LogHelper
			.getLoggerName(MonitoringFilter.class));

	/** The monitoring disabled. */
	private boolean monitoringDisabled;

	/** The log enabled. */
	// private boolean logEnabled;

	/** The url exclude pattern. */
	private Pattern urlExcludePattern;

	/** The allowed addr pattern. */
	private Pattern allowedAddrPattern;

	/** The filter config. */
	private FilterConfig filterConfig;

	/** The monitoring url. */
	// private String monitoringUrl;

	private final long initMem;

	/**
	 * Constructeur.
	 */
	public MonitoringFilter() {
		super();
		initMem = Runtime.getRuntime().totalMemory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;

		monitoringDisabled = "true".equals(this.filterConfig
				.getInitParameter("disabled"));
		if (monitoringDisabled) {
			return;
		}

		{
			String str = this.filterConfig
					.getInitParameter("url-exclude-pattern");
			if (str != null && str.length() > 0) {
				urlExcludePattern = Pattern.compile(str);
			}
		}

		{
			String str = this.filterConfig
					.getInitParameter("allowed-addr-pattern");
			if (str != null && str.length() > 0) {
				allowedAddrPattern = Pattern.compile(str);
			}
		}

		{
			try{
				System.gc();
			} catch(Exception ex){
				ex.printStackTrace();
			}
			
			StringBuilder sb = new StringBuilder(1024);
			sb.append("MonitoringFilter has initialized [")
					.append(GlobalContext.getAppName()).append("]");
			sb.append(": ").append(monitoringDisabled ? "disabled" : "enabled");
			sb.append("\ninit memory: ").append(
					FileUtils.byteCountToDisplaySize(initMem));
			sb.append(", max memory: ").append(
					FileUtils.byteCountToDisplaySize(Runtime.getRuntime()
							.maxMemory()));
			sb.append(", total memory: ").append(
					FileUtils.byteCountToDisplaySize(Runtime.getRuntime()
							.totalMemory()));
			sb.append(", free memory: ").append(
					FileUtils.byteCountToDisplaySize(Runtime.getRuntime()
							.freeMemory()));
			sb.append(", used memory: ").append(
					FileUtils.byteCountToDisplaySize(Runtime.getRuntime()
							.totalMemory() - Runtime.getRuntime()
							.freeMemory()));
			sb.append("\n");
			
			System.out.println(sb.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		urlExcludePattern = null;
		allowedAddrPattern = null;
		filterConfig = null;

		System.out.println("MonitoringFilter has destroy");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		if (isDisabled() || !logger.isTraceEnabled()) {
			chain.doFilter(request, response);
			return;
		}
		
		// if
		// (httpRequest.getRequestURI().equals(getMonitoringUrl(httpRequest))) {
		// doMonitoring(httpRequest, httpResponse);
		// return;
		// }

		if (isRequestExcluded((HttpServletRequest) request)) {
			// si cette url est exclue ou si le counter http est d茅sactiv茅, on
			// ne monitore pas cette requ锚te http
			chain.doFilter(request, response);
			return;
		}

		doFilter(chain, httpRequest, httpResponse);
		
		//System.out.println(WebHelper.printRequestHeader(httpRequest));
		//System.out.println(httpResponse.getCharacterEncoding());
	}

	/**
	 * Do filter.
	 * 
	 * @param chain
	 *            the chain
	 * @param httpRequest
	 *            the http request
	 * @param httpResponse
	 *            the http response
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ServletException
	 *             the servlet exception
	 */
	private void doFilter(FilterChain chain, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws IOException,
			ServletException {
		String loginUser = null;
		String requestId = null;
		String requestUri = null;
		String requestType = null;
		
		try {
			loginUser = getLoginUser(httpRequest);
			if (!UtilFunc.hasText(loginUser)) {
				loginUser = "UNKNOWN";
			}

			requestId = UtilFunc.getUUID();
			requestUri = WebHelper.getRequestPath(httpRequest);
			
			if (requestUri.endsWith(".action")) {
				requestType = "A";
			} else if (requestUri.endsWith(".jsp")) {
				requestType = "J";
			} else if (requestUri.equals("/ProcessAll")) {
				requestType = "S";
			} else {
				requestType = "R";
			}

			{
				StringBuilder sb = new StringBuilder(1024);
				sb.append("@@B ");
				sb.append(requestId).append(" ");
				sb.append(loginUser).append(" ");
				sb.append(requestType).append(" ");
				sb.append(requestUri).append(" ");
				sb.append(Runtime.getRuntime().totalMemory()).append(" ");
				sb.append(Runtime.getRuntime().freeMemory()).append(" ");
				sb.append("success").append("$$");

				logger.trace(sb.toString());
			}

			chain.doFilter(httpRequest, httpResponse);
		} finally {
			if ("UNKNOWN".equals(loginUser)) {
				loginUser = getLoginUser(httpRequest);
				if (!UtilFunc.hasText(loginUser)) {
					loginUser = "UNKNOWN";
				}
			}
			{
				StringBuilder sb = new StringBuilder(1024);
				sb.append("@@E ");
				sb.append(requestId).append(" ");
				sb.append(loginUser).append(" ");
				sb.append(requestType).append(" ");
				sb.append(requestUri).append(" ");
				sb.append(Runtime.getRuntime().totalMemory()).append(" ");
				sb.append(Runtime.getRuntime().freeMemory()).append(" ");
				sb.append("success").append("$$");

				logger.trace(sb.toString());
			}
		}
	}

	/**
	 * Gets the login user.
	 * 
	 * @param request
	 *            the request
	 * @return the login user
	 */
	private String getLoginUser(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				UserInfo userInfo = SessionHelper.getUserInfo(session);
				if (userInfo != null) {
					return userInfo.getLoginUser();
				}
			}

			return null;
		} catch (Exception ex) {
			LogHelper.getLogger().warn("性能监控获取用户登录名出错", ex);
			return null;
		}
	}

	/**
	 * Gets the request name.
	 * 
	 * @param request
	 *            the request
	 * @return the request name
	 */
	protected String getRequestName(HttpServletRequest request) {
		return getCompleteRequestName(request, false);
	}

	/**
	 * Checks if is disabled.
	 * 
	 * @return true, if is disabled
	 */
	protected boolean isDisabled() {
		return monitoringDisabled || false/*
										 * !ParameterMapping.isMonitoringEnabled(
										 * )
										 */;
	}

	/**
	 * Do monitoring.
	 * 
	 * @param httpRequest
	 *            the http request
	 * @param includeQueryString
	 *            the include query string
	 * @return the complete request name
	 */
	/*
	 * private void doMonitoring(HttpServletRequest httpRequest,
	 * HttpServletResponse httpResponse) throws IOException { if
	 * (!isRequestAllowed(httpRequest)) {
	 * LOG.debug("Forbidden access to monitoring from " +
	 * httpRequest.getRemoteAddr());
	 * httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN,
	 * "Forbidden access"); return; } final Collector collector =
	 * filterContext.getCollector(); final MonitoringController
	 * monitoringController = new MonitoringController(collector, null);
	 * monitoringController.doActionIfNeededAndReport(httpRequest, httpResponse,
	 * filterConfig.getServletContext());
	 * 
	 * if
	 * ("stop".equalsIgnoreCase(httpRequest.getParameter(COLLECTOR_PARAMETER)))
	 * { // on a 茅t茅 appel茅 par un serveur de collecte qui fera l'aggr茅gation
	 * dans le temps, // le stockage et les courbes, donc on arr锚te le timer
	 * s'il est d茅marr茅 // et on vide les stats pour que le serveur de collecte
	 * ne r茅cup猫re que les deltas for (final Counter counter :
	 * collector.getCounters()) { counter.clear(); }
	 * 
	 * if (!collector.isStopped()) { LOG.debug(
	 * "Stopping the javamelody thread in this webapp, because a collector server from "
	 * + httpRequest.getRemoteAddr() + " wants to collect the data itself"); if
	 * (filterContext.getTimer() != null) { filterContext.getTimer().cancel(); }
	 * collector.stop(); } } }
	 */

	/**
	 * Gets the complete request name.
	 * 
	 * @param httpRequest
	 *            the http request
	 * @param includeQueryString
	 *            the include query string
	 * @return the complete request name
	 */
	private static String getCompleteRequestName(
			HttpServletRequest httpRequest,
			boolean includeQueryString) {

		String tmp = httpRequest.getRequestURI().substring(
				httpRequest.getContextPath().length());

		final int lastIndexOfSemiColon = tmp.lastIndexOf(';');
		if (lastIndexOfSemiColon != -1) {
			tmp = tmp.substring(0, lastIndexOfSemiColon);
		}
		final String method;
		if ("XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With"))) {
			method = "ajax " + httpRequest.getMethod();
		} else {
			method = httpRequest.getMethod();
		}
		if (!includeQueryString) {
			return tmp + ' ' + method;
		}
		final String queryString = httpRequest.getQueryString();
		if (queryString == null) {
			return tmp + ' ' + method;
		}
		return tmp + '?' + queryString + ' ' + method;
	}

	/**
	 * Checks if is request excluded.
	 * 
	 * @param httpRequest
	 *            the http request
	 * @return true, if is request excluded
	 */
	private boolean isRequestExcluded(HttpServletRequest httpRequest) {
		return urlExcludePattern != null
				&& urlExcludePattern.matcher(
						httpRequest.getRequestURI()
								.substring(
										httpRequest.getContextPath().length()))
						.matches();
	}

	/**
	 * Checks if is request allowed.
	 * 
	 * @param httpRequest
	 *            the http request
	 * @return true, if is request allowed
	 */
	protected boolean isRequestAllowed(HttpServletRequest httpRequest) {
		return allowedAddrPattern == null
				|| allowedAddrPattern.matcher(httpRequest.getRemoteAddr())
						.matches();
	}

	/**
	 * Throw exception.
	 * 
	 * @param t
	 *            the t
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ServletException
	 *             the servlet exception
	 */
	/*
	 * private static void throwException(Throwable t) throws IOException,
	 * ServletException { if (t instanceof Error) { throw (Error) t; } else if
	 * (t instanceof RuntimeException) { throw (RuntimeException) t; } else if
	 * (t instanceof IOException) { throw (IOException) t; } else if (t
	 * instanceof ServletException) { throw (ServletException) t; } else {
	 * 
	 * throw new ServletException(t.getMessage(), t); } }
	 */
}
