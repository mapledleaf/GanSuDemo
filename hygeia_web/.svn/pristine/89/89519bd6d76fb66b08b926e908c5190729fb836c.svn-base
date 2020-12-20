/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.web.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.constructs.blocking.LockTimeoutException;
import net.sf.ehcache.constructs.web.AlreadyCommittedException;
import net.sf.ehcache.constructs.web.AlreadyGzippedException;
import net.sf.ehcache.constructs.web.filter.FilterNonReentrantException;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.IMapping;
import com.powersi.hygeia.framework.ParameterMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.RefreshMappingUtil;

/**
 * 静态资源缓存过滤器.
 */
public class StaticResourceCacheFilter extends SimplePageCachingFilter
		implements IMapping {
	/** 刷新次数. */
	private int times = 0;

	/** 启用标志. */
	protected boolean enabled = true;

	/**
	 * 初始化.
	 */
	public void init() {

	}

	/**
	 * 刷新.
	 * 
	 */
	public void refresh() {
		try {
			synchronized (this.getClass()) {
				times++;

				String cacheName = getCacheName();
				Ehcache cache = getCacheManager().getEhcache(cacheName);

				int size = 0;
				LogHelper.getLogger().debug(
						(String.format("开始刷新静态资源缓存%1$s...", cacheName)));
				if (cache != null) {
					size = cache.getSize();
					cache.removeAll();
				}
				LogHelper.getLogger().debug(
						String.format("完成静态资源缓存%s刷新，共清除%d条缓存.", cacheName,
								size));
			}
		} catch (HygeiaException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new HygeiaException("刷新静态资源缓存出错", ex);
		}
	}

	/**
	 * 获取次数.
	 * 
	 * @return the times
	 */
	public int getTimes() {
		return times;
	}

	/**
	 * 获取大小.
	 * 
	 * @return the size
	 */
	public int getSize() {
		Ehcache cache = getCacheManager().getEhcache(getCacheName());
		if (cache != null) {
			return cache.getSize();
		} else {
			return 0;
		}
	}

	/**
	 * 获取启用.
	 * 
	 * @return true, if is enabled
	 */
	public final boolean isEnabled() {
		return enabled;
	}

	/**
	 * 设置启用.
	 * 
	 * @param enabled
	 *            the new enabled
	 */
	public final void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.ehcache.constructs.web.filter.CachingFilter#doInit(javax.servlet
	 * .FilterConfig)
	 */
	public void doInit(FilterConfig filterConfig) throws CacheException {
		try {
			super.doInit(filterConfig);

			String str = ParameterMapping.getStringByCode(
					"use_staticresource_cache", "1");
			setEnabled(!"0".equals(str));

			RefreshMappingUtil.add("staticresource", this);

			StringBuilder sb = new StringBuilder(4096);
			sb.append("StaticResourceCacheFilter has initialized [")
					.append(GlobalContext.getAppName()).append("]");
			sb.append(": ").append(isEnabled() ? "enabled" : "disabeld");
			sb.append("\n");
			Ehcache cache = getCacheManager().getEhcache(getCacheName());
			if (cache != null) {
				sb.append(cache.toString());
				sb.append("\n");
			}

			System.out.println(sb.toString());
		} catch (Exception ex) {
			LogHelper.getLogger().warn("初始化StaticResourceCacheFilter失败", ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.ehcache.constructs.web.filter.CachingFilter#doFilter(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * javax.servlet.FilterChain)
	 */
	protected void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws AlreadyGzippedException, AlreadyCommittedException,
			FilterNonReentrantException, LockTimeoutException, Exception {

		if (isEnabled() && !isDevMode(request)) {
			super.doFilter(request, response, chain);
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * 检查是否开发模式.
	 * 
	 * @param request
	 *            the request
	 * @return true, if is dev mode
	 */
	private boolean isDevMode(HttpServletRequest request) {
		if (GlobalContext.getWeblogicFlag()) {
			return false;
		}

		String serverName = request.getServerName();
		if ("localhost".equals(serverName) || "127.0.0.1".equals(serverName)) {
			return true;
		}

		return false;
	}
}
