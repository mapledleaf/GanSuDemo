package com.powersi.powerreport.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.powerreport.service.PowerReport;

/**
 * Servlet implementation class reportfileservlet
 */
public class DownloadReportFileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final PowerReport powerReport = BeanHelper.getBean(PowerReport.class);

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
		// 获取reportID bizID 差数
		String reportID = request.getParameter("reportID");
		String bizID = request.getParameter("bizID");
		// 设置response的编码方式
		// 获取报表的名称
		String reportName = this.powerReport.getReportName(reportID, bizID) + ".xls";
		request.setCharacterEncoding("gbk");
		reportName = new String(reportName.getBytes("GBK"), "ISO8859-1");
		response.setContentType("application/octet-stream");
		// 发送到客户端的文件名称
		response.setHeader("Content-Disposition", "attachment;filename=" + reportName);
		// 根据参数获取的输出流
		OutputStream myout = response.getOutputStream();
		this.powerReport.getReport(reportID, bizID, myout);
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
