package com.powersi.hygeia.comm.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件下载
 *
 */
public class FileDownLoad {

	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	public void downLoad(HttpServletRequest request, HttpServletResponse response, String filename) {
		try {
			request.setCharacterEncoding("gbk");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType(CONTENT_TYPE);
		// 得到下载文件的名字
		System.out.println("^^^^^^^^^^^^^^" + filename);
		int i = filename.lastIndexOf(File.separator);
		// 创建file对象
		File file = new File(filename);
		// 设置response的编码方式
		// response.setContentType("application/x-msdownload");
		// 写明要下载的文件的大小
		response.setContentLength((int) file.length());
		// 发送到客户端的文章
		response.setHeader("Content-Disposition",
				"attachment;filename=" + filename.substring(i + 1, filename.length()));
		OutputStream myout = null;
		try {
			// 读出文件到i/o流
			FileInputStream fis = new FileInputStream(file);
			@SuppressWarnings("resource")
			BufferedInputStream buff = new BufferedInputStream(fis);
			byte[] b = new byte[1024];// 相当于我们的缓存
			long k = 0;// 该值用于计算当前实际下载了多少字节
			// 从response对象中得到输出流,准备下载
			myout = response.getOutputStream();
			// 开始循环下载
			while (k < file.length()) {
				int j = buff.read(b, 0, 1024);
				k += j;
				// 将b中的数据写到客户端的内存
				myout.write(b, 0, j);
			}
			// 将写入到客户端的内存的数据,刷新到磁盘
			myout.flush();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				myout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void downLoad(HttpServletRequest request, HttpServletResponse response, String filename, Blob blob) {
		if (blob == null) {
			return;
		}
		try {
			// 设置response的编码方式
			request.setCharacterEncoding("gbk");
			filename = new String(filename.getBytes("GBK"), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/octet-stream");
		// 发送到客户端的文章
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		OutputStream myout = null;
		try {
			// 读出文件到i/o流
			InputStream fis = blob.getBinaryStream();
			BufferedInputStream buff = new BufferedInputStream(fis);
			byte[] b = new byte[1024];// 相当于我们的缓存
			int k = 0;// 该值用于计算当前实际下载了多少字节
			// 从response对象中得到输出流,准备下载
			myout = response.getOutputStream();
			// 开始循环下载
			while ((k = buff.read(b, 0, 1024)) != -1) {
				// 将b中的数据写到客户端的内存
				myout.write(b, 0, k);
			}
			// 将写入到客户端的内存的数据,刷新到磁盘
			myout.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				myout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
