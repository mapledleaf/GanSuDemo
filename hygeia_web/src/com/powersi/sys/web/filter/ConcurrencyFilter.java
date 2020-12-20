package com.powersi.sys.web.filter;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.powersi.comm.service.memory.MemoryDBWrapper;
import com.powersi.hygeia.comm.service.HygeiaBeanService;
import com.powersi.pcloud.dict.service.DictService;

/**
 * 
 * @author 林刚
 *
 */
public class ConcurrencyFilter implements Filter {

	@Override
	public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse,
			FilterChain paramFilterChain) throws IOException, ServletException {
		String switch_flag = "1";
		String prompt_info = "医保结算云暂停服务,请稍后重试!!!";
		DictService dictService = null;
		try {
			dictService = (DictService) HygeiaBeanService.getInstance().getBean("dictService");
			switch_flag = dictService.getValue("LIMITER_API_PARAM", "WEB_EXTERNAL_SERVICE_SWITCH", "1");
			prompt_info = dictService.getValue("LIMITER_API_PARAM", "WEB_EXTERNAL_SERVICE_PROMPT",
					"医保结算云暂停服务,请稍后重试!!!");
		} catch (Throwable e) {

		}
		if ("0".equals(switch_flag) && StringUtils.isNotBlank(prompt_info)) {
			paramServletRequest.setAttribute("prompt_info", prompt_info);
			paramServletRequest.getRequestDispatcher("/pages/util/prompt.jsp").forward(paramServletRequest,
					paramServletResponse);
			return;
		}
		MemoryDBWrapper memoryDBWrapper_moniter = null;
		try {
			memoryDBWrapper_moniter = (MemoryDBWrapper) HygeiaBeanService.getInstance()
					.getBean("memoryDBWrapper_moniter");
		} catch (Throwable e) {

		}
		Calendar calendar = Calendar.getInstance();
		String key = "MONITER_CONCURRENT_HYGEIA_WEB_" + calendar.get(Calendar.DAY_OF_MONTH);
		boolean isStatistics = false;
		try {
			try {
				if (memoryDBWrapper_moniter != null) {
					memoryDBWrapper_moniter.getMemoryDB().plusLong(key, 2 * 24 * 60 * 60, 1L);
					isStatistics = true;
				}
			} catch (Throwable e) {

			}
			paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
		} finally {
			try {
				if (memoryDBWrapper_moniter != null) {
					if (isStatistics) {
						memoryDBWrapper_moniter.getMemoryDB().plusLong(key, 2 * 24 * 60 * 60, -1L);
						isStatistics = false;
					}
				}
			} catch (Throwable e) {

			}
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {

	}

}
