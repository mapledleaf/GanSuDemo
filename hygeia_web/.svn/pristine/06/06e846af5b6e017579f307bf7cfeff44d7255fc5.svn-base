/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.tool.codegen;

import java.io.File;

import com.powersi.hygeia.framework.GlobalContext;
import com.powersi.hygeia.framework.codegen.ActionCodeGenerator;
import com.powersi.hygeia.framework.util.ConsoleHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * 控制类生成器.
 */
public abstract class ActionCodeGen {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		try {
			GlobalContext.init();

			String name = "";
			String comment = "";
			String packageName = "com.powersi.temp.action";
			String srcPath = new File(GlobalContext.getPhysicalPath(""))
					.getParent() + UtilFunc.FILE_SEPARATOR + "src";
			boolean overWrite = true;
			String fileEncoding = GlobalContext.getCharset();

			System.out.println("----------------------------------------------");
			System.out.println("\t生成action");
			System.out.println("----------------------------------------------");
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			while (UtilFunc.isEmpty(name)) {
				line = ConsoleHelper.readLine("请输入控制名(不需要添加 Action，注意大小写)：");
				if (UtilFunc.hasText(line)) {
					name = line.trim();
				}
			}
			sb.append("控制名：").append(name).append(UtilFunc.NEW_LINE);

			while (UtilFunc.isEmpty(comment)) {
				line = ConsoleHelper.readLine("请输入控制类注释：");
				if (UtilFunc.hasText(line)) {
					comment = line.trim();
				}
			}
			sb.append("控制注释：").append(comment).append(UtilFunc.NEW_LINE);

			line = ConsoleHelper.readLine("请输入包名(" + packageName + ")：");
			if (UtilFunc.hasText(line)) {
				packageName = line.trim();
			}
			sb.append("包名：").append(packageName).append(UtilFunc.NEW_LINE);

			line = ConsoleHelper.readLine("请输入源代码路径(" + srcPath + ")：");
			if (UtilFunc.hasText(line)) {
				srcPath = line.trim();
			}
			sb.append("源代码路径：").append(srcPath).append(UtilFunc.NEW_LINE);

			line = ConsoleHelper.readLine("请输入是否覆盖文件(" + overWrite + ")(true | false)：");
			if (UtilFunc.hasText(line)) {
				overWrite = Boolean.valueOf(line.trim());
			}
			sb.append("覆盖文件：").append(overWrite).append(UtilFunc.NEW_LINE);

			line = ConsoleHelper.readLine("请输入文件编码(" + fileEncoding + ")：");
			if (UtilFunc.hasText(line)) {
				fileEncoding = line.trim().toUpperCase();
			}
			sb.append("文件编码：").append(fileEncoding).append(UtilFunc.NEW_LINE);

			line = ConsoleHelper.readLine("\n" + UtilFunc.leftPad("", 50, "-") + "\n"
					+ sb.toString() + UtilFunc.leftPad("", 50, "-")
					+ "\n\n请确认生成参数，现在是否开始执行代码生成(yes | no)：");
			if (UtilFunc.hasText(line) && line.toLowerCase().startsWith("n")) {
				System.out.println("操作已经取消");
				return;
			}

			System.out.println("\n\n");

			ActionCodeGenerator.generateFile(name, comment,
					packageName, srcPath, overWrite, fileEncoding);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}