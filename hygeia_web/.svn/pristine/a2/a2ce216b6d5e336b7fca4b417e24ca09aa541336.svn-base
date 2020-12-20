package com.powersi.powerreport.servlet;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.powerreport.service.PowerReport;

/**
 * Servlet implementation class reportfileservlet
 */
public class DownloadReportHtmlServlet extends HttpServlet {

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// 获取reportID bizID 参数
		String reportID = request.getParameter("reportID");
		String bizID = request.getParameter("bizID");
		// 设置临时路径
		String filePath = "";
		// 设置存放变量
		String str = "";
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try {
			if (StringUtils.isNotEmpty(reportID)) {
				filePath = this.powerReport.getTempPath() + reportID + ".html";
			} else {
				filePath = this.powerReport.getTempPath() + bizID + ".html";
			}
			filePath = new String(filePath.getBytes("GBK"), "ISO8859-1");
			// 根据参数获取的输出流
			OutputStream myout = new FileOutputStream(filePath);
			this.powerReport.getReportHtml(reportID, bizID, myout);
			// 从文件读取，写入String
			File xlsFile = new File(filePath);
			fis = new FileInputStream(xlsFile);
			bos = new ByteArrayOutputStream();
			// 读取缓存
			byte[] buffer = new byte[2048];
			int length = 0;
			while ((length = fis.read(buffer)) != -1) {
				bos.write(buffer, 0, length);// 写入输出流
			}
			// 根据输出流创建字符串对象
			str = new String(bos.toByteArray(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			throw new HygeiaException("设置文件编码出错，文件为" + filePath, e1);
		} catch (FileNotFoundException e1) {
			throw new HygeiaException("系统找不到指定的文件,文件为" + filePath, e1);
		} catch (IOException e1) {
			throw new HygeiaException("读取文件流出错", e1);
		} finally {
			try {
				if (fis != null) {
					fis.close();// 读取完毕，关闭输入流
					fis = null;
				}
				if (bos != null) {
					bos.flush();
					bos.close();
					bos = null;
				}
			} catch (Exception e) {
				throw new HygeiaException("关闭文件流出错!", e);
			} finally {
				// 删除文件
				File file = new File(filePath);
				if (file.exists()) {
					file.delete();
				}
			}
		}
		// 通过response传参,填充response
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(str);
		} catch (IOException e) {
			throw new HygeiaException("downloadReportHtmlServlet类填充response出错", e);
		} finally {
			if (out != null) {
				out.close();
				out = null;
			}
		}
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
