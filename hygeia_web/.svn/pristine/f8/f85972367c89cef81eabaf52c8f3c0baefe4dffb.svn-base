package com.powersi.test;

import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.UserHelper;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

/**
 * 模拟登陆
 * 
 * @author 刘勇
 *
 */
public class TestHygeiaWeb {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BusiContext.setUserInfo(UserHelper.getUser("9", "100"));
			System.out.println(BizHelper.getAkb020());
			DBHelper.commit();
		} catch (Exception ex) {
			DBHelper.rollback();
			ex.printStackTrace();
		} catch (Throwable ex) {
			DBHelper.rollback();
			ex.printStackTrace();
		} finally {
			DBHelper.close();
		}
	}

}
