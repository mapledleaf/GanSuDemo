package com.powersi.ssm.biz.inhospital;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.UserHelper;
import com.powersi.ssm.biz.medicare.api.dto.APIRemoteCallParam;
import com.powersi.ssm.biz.medicare.api.dto.APIRemoteCallResult;
import com.powersi.ssm.biz.medicare.api.service.APIRemoteCallService;
import com.powersi.test.base.TestBase;

/**
 * 
 * @author 刘勇
 *
 */
public class TestWebApi extends TestBase {

	@Autowired
	private APIRemoteCallService aPIRemoteCallService;

	@Test
	public void testAPIRemoteCallService() {
		try {
			BusiContext.setUserInfo(UserHelper.getUser("9", "33443"));// 33443 100
			APIRemoteCallParam remoteCallParam = new APIRemoteCallParam();
			remoteCallParam.setFunction_id("bizh120913");
			remoteCallParam.setValue("bka977", "500");
			APIRemoteCallResult aPIRemoteCallResult = aPIRemoteCallService.execute(remoteCallParam);
			System.out.println(aPIRemoteCallResult.getResultXML());
			DBHelper.commit();
		} catch (Throwable ex) {
			DBHelper.rollback();
			ex.printStackTrace();
		} finally {
			DBHelper.close();
		}
	}

}
