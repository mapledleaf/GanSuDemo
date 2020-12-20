/*
 * Copyright 2016 Powersi. All rights reserved.
 */
package com.powersi.tool.security;

import com.powersi.hygeia.framework.util.ConsoleHelper;
import com.powersi.hygeia.framework.util.CryptoHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

/**
 * 属性加密器.
 */
public abstract class PropEncryption {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		try {
			String propValue = "";
			System.out
					.println("----------------------------------------------");
			System.out.println("\t属性加密");
			System.out
					.println("----------------------------------------------");

			String line = null;
			while (UtilFunc.isEmpty(propValue)) {
				line = ConsoleHelper.readLine("请输入需要加密的属性值：");
				if (UtilFunc.hasText(line)) {
					propValue = line.trim();
				}
			}
			
			String encrytValue = CryptoHelper.commonEncrypt(propValue);
			System.err.println(propValue);
			System.err.println("{DES}" + encrytValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
