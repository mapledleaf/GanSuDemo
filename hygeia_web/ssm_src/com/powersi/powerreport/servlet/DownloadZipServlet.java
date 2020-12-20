package com.powersi.powerreport.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.powerreport.service.store.PowerReportStore;

/**
 * Servlet implementation class reportfileservlet
 */
public class DownloadZipServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	public void destroy() {
		super.destroy();
	}

	/*
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String store_id = request.getParameter("store_id");
		String file_name = request.getParameter("file_name");
		request.setCharacterEncoding("gbk");
		String reportName = new String(file_name.getBytes("GBK"), "ISO8859-1");
		response.setContentType("application/octet-stream");
		// 发送到客户端的文件名称
		response.setHeader("Content-Disposition", "attachment;filename=" + reportName);
		// 根据参数获取的输出流
		OutputStream myout = response.getOutputStream();
		PowerReportStore powerReportStore = (PowerReportStore) BeanHelper.getBean("PowerReportStore");
		powerReportStore.get(store_id, myout);
	}

	/*
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
