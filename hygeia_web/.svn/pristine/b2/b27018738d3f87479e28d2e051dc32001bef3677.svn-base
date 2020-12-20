/*
 * Copyright 2013 Powersi. All rights reserved.
 */
package com.powersi.sys.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.powersi.hygeia.framework.CodetableTransform;
import com.powersi.hygeia.framework.util.LogHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.web.export.ExportHelper;

/**
 * The Class ExportGrid.
 */
public class ExportGrid extends HttpServlet {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException{
		super.init();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	public void destroy() {
		super.destroy();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Processrequest.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		try {
			List data = null;
			// 处理codetable
			if (request.getParameter("codetable") != null) {
				List<Map<String, Object>> codes = JsonHelper.toList(request
						.getParameter("codetable"));
				CodetableTransform transform = new CodetableTransform();
				for (Map<String, Object> code : codes) {
					transform.add(UtilFunc.getString(code, "type", ""),
							UtilFunc.getString(code, "data", ""),
							UtilFunc.getString(code, "display", ""));
				}
				data = transform.execute(JsonHelper.toList(request
						.getParameter("griddata")));
			} else {
				data = JsonHelper.toList(request.getParameter("griddata"));
			}

			ExportHelper.exportDataGrid(request, response, data);
		} catch (Exception ex) {
			LogHelper.getLogger().error("导出DataGrid文件出错", ex);
			throw new ServletException(ex);
		}
	}
}