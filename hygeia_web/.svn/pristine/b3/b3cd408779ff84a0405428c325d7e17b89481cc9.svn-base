/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.sys.entity;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;


/**
 * 文件下载实体类(请使用框架同名类).
 */
public class DownloadFile extends com.powersi.hygeia.framework.entity.DownloadFile {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 构造文件下载类.
	 */
	public DownloadFile() {

	}

	/**
	 * 构造文件下载类.
	 * 
	 * @param fileName
	 *            the file name
	 * @param is
	 *            the is
	 * @param fileCharset
	 *            the file charset
	 */
	public DownloadFile(String fileName, InputStream is, String fileCharset) {
		super(fileName, is, fileCharset);
	}

	/**
	 * 构造文件下载类.
	 * 
	 * @param fileName
	 *            the file name
	 * @param fileContent
	 *            the file content
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	public DownloadFile(String fileName, String fileContent) {
		super(fileName, fileContent);
	}
	
	/**
	 * Instantiates a new download file.
	 * 
	 * @param fileName
	 *            the file name
	 * @param fileContent
	 *            the file content
	 * @param fileCharset
	 *            the file charset
	 * @throws UnsupportedEncodingException 
	 */
	public DownloadFile(String fileName, String fileContent, String fileCharset){
		super(fileName, fileContent, fileCharset);
	}

	/**
	 * 构造文件下载类.
	 * 
	 * @param filePath
	 *            the file path
	 */
	public DownloadFile(String filePath) {
		super(filePath);
	}
	
	/**
	 * 构造文件下载类.
	 * 
	 * @param file
	 *            the file
	 */
	public DownloadFile(File file) {
		super(file);
	}
}
