/*
 * Copyright 2015 Powersi. All rights reserved.
 */
package com.powersi.sys.util;

import com.powersi.hygeia.framework.util.CryptoHelper;

/**
 * 密码辅助类.
 */
public abstract class PasswordHelper {

    /**
     * 比较密码.
     *
     * @param inputPwd 输入密码
     * @param dbPwd 数据库密码
     * @return true, if successful
     */
    public static boolean equalsPassword(String inputPwd, String dbPwd) {
        String in = (inputPwd == null ? "" : inputPwd);
        String db = (dbPwd == null ? "" : dbPwd);
        if (!db.startsWith("{md5}")) {
            db = "{md5}" + createPassword(dbPwd);
        }
        if (!in.startsWith("{md5}")) {
            in = "{md5}" + createPassword(inputPwd);
        }

        return in.equals(db);
    }

    /**
     * 创建密码.
     *
     * @param pwd 明文
     * @return 密文
     */
    public static String createPassword(String pwd) {
        //return CryptoHelper.md5(pwd);
    	if(pwd == null){
    		return "";
    	}
    	
    	return CryptoHelper.md5(pwd);
    }
}
