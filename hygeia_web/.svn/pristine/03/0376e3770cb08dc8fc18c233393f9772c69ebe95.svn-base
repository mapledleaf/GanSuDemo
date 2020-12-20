/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.tool.encoding;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.util.ConsoleHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.EncodingHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * 文件编码转换工具.
 */
public class FileEncodingConvert {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		try {
			GlobalContext.init();

			String fileEncoding = "UTF-8";

			String srcPath = new File(GlobalContext.getPhysicalPath(""))
					.getParent();
			String destPath = null;
			String acceptExt = "java,jsp,htm,html,css,js,txt,log";
			List<String> acceptType = UtilFunc.tokenizeToList(acceptExt, ",");
			String fileType = "java,jsp";
			List<String> filterType = null;

			StringBuilder sb = new StringBuilder();
			String line = null;

			ConsoleHelper
					.println("--------------------------------------------------------------------------------------------");
			ConsoleHelper.println("\t文件字符集转换(", acceptExt, ")");
			ConsoleHelper
					.println("--------------------------------------------------------------------------------------------");

			line = ConsoleHelper.readLine("请输入源代码路径(" + srcPath + ")：");
			if (UtilFunc.hasText(line)) {
				srcPath = line.trim();
			}
			sb.append("源代码路径：").append(srcPath).append(UtilFunc.NEW_LINE);

			boolean validFlag = false;
			while (!validFlag) {
				line = ConsoleHelper.readLine("请输入文件类型(" + fileType + ")，多文件类型逗号分隔：");
				if (UtilFunc.hasText(line)) {
					fileType = line.trim();
				}

				validFlag = true;
				filterType = UtilFunc.tokenizeToList(fileType, ",");
				for (String type : filterType) {
					if (!acceptType.contains(type)) {
						System.err.println("不支持的文件类型：" + type);
						validFlag = false;
						break;
					}
				}
				if (filterType.size() == 0) {
					System.err.println("必须输入文件类型");
					validFlag = false;
				}
			}

			sb.append("文件类型：").append(fileType).append(UtilFunc.NEW_LINE);

			line = ConsoleHelper.readLine("请输入输出文件编码(" + fileEncoding + ")：");
			if (UtilFunc.hasText(line)) {
				fileEncoding = line.trim().toUpperCase();
			}
			sb.append("输出文件编码：").append(fileEncoding).append(UtilFunc.NEW_LINE);

			destPath = srcPath + "-"
					+ fileEncoding.toLowerCase().replace("-", "");
			while (true) {
				line = ConsoleHelper.readLine("请输入输出路径(" + destPath + ")，不能与源路径相同：");

				if (UtilFunc.hasText(line)) {
					destPath = line.trim();
				}
				
				if(!destPath.isEmpty() && !srcPath.equals(destPath)){
					break;
				}
			}
			
			sb.append("输出路径：").append(destPath).append(UtilFunc.NEW_LINE);

			line = ConsoleHelper.readLine("\n" + UtilFunc.leftPad("", 50, "-") + "\n"
					+ sb.toString() + UtilFunc.leftPad("", 50, "-")
					+ "\n\n请确认转换参数，现在是否开始执行转换(yes | no)：");
			if (UtilFunc.hasText(line) && line.toLowerCase().startsWith("n")) {
				System.out.println("操作已经取消");
				return;
			}

			ConsoleHelper.println("\n\n");

			long startMillis = System.currentTimeMillis();
			Collection<File> files = FileUtils.listFiles(new File(srcPath),
					UtilFunc.toStringArray(filterType), true);

			File destFile = null;
			String data = null;
			String encoding = null;
			String extName = null;
			StringBuilder result = new StringBuilder();
			for (File file : files) {
				encoding = EncodingHelper.getFileEncode(file);
				if (encoding == null || encoding.isEmpty()) {
					encoding = "";
					data = FileUtils.readFileToString(file);
				} else {
					data = FileUtils.readFileToString(file, encoding);
				}
				extName = file.getName().toLowerCase();
				if (extName.endsWith(".jsp")) {
					data = converJsp(data, fileEncoding);
				} else if (extName.endsWith(".html")
						|| extName.endsWith(".htm")) {
					data = convertHtml(data, fileEncoding);
				}
				destFile = new File(file.getAbsolutePath().replace(srcPath,
						destPath));
				if (!destFile.getParentFile().exists()) {
					destFile.getParentFile().mkdirs();
				}
				FileUtils.writeStringToFile(destFile, data, fileEncoding);
				result.append(
						destFile.getAbsolutePath() + ":" + encoding + "->"
								+ fileEncoding).append("\n");
			}
			ConsoleHelper.println(result.toString());

			ConsoleHelper
					.println("--------------------------------------------------------------------------------------------");
			ConsoleHelper.println(destPath, " : size = ", files.size(),
					", time = ", (System.currentTimeMillis() - startMillis),
					" ms.");
			ConsoleHelper
					.println("--------------------------------------------------------------------------------------------");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBHelper.close();
		}
	}

	/**
	 * Conver jsp.
	 * 
	 * @param data
	 *            the data
	 * @param charset
	 *            the charset
	 * @return the string
	 */
	private static String converJsp(String data, String charset) {
		String s = data;
		{
			Pattern p = Pattern
					.compile(".*page.*contentType=\"([^<>\"]*)\"[^<>]*");
			Matcher m = p.matcher(s);
			if (m.find()) {
				StringBuilder sb = new StringBuilder(s.length() + 10);
				sb.append(s.substring(0, m.start(1)));
				sb.append("text/html; charset=").append(charset);
				sb.append(s.substring(m.end(1)));
				s = sb.toString();
			}
		}

		{
			Pattern p = Pattern.compile(".*pageEncoding=\"([^<>\"]*)\"[^<>]*");
			Matcher m = p.matcher(s);
			if (m.find()) {
				StringBuilder sb = new StringBuilder(s.length() + 10);
				sb.append(s.substring(0, m.start(1)));
				sb.append(charset);
				sb.append(s.substring(m.end(1)));
				s = sb.toString();
			}
		}

		return convertHtml(s, charset);
	}

	/**
	 * Convert html.
	 * 
	 * @param data
	 *            the data
	 * @param charset
	 *            the charset
	 * @return the string
	 */
	private static String convertHtml(String data, String charset) {
		String s = data;
		{
			Pattern p = Pattern
					.compile(".*<meta.*Content-Type.*content=\"([^<>\"]*)\"[^<>]*");
			Matcher m = p.matcher(s);
			if (m.find()) {
				StringBuilder sb = new StringBuilder(s.length() + 10);
				sb.append(s.substring(0, m.start(1)));
				sb.append("text/html; charset=").append(charset);
				sb.append(s.substring(m.end(1)));
				s = sb.toString();
			}
		}

		return s;
	}
}
