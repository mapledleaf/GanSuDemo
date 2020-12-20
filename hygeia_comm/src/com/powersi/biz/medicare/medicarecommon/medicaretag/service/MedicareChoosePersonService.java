/**  
 * Copyright © 2018 Powersi. All rights reserved.
 */
package com.powersi.biz.medicare.medicarecommon.medicaretag.service;

import java.util.List;
import java.util.Map;

/**
 * 

 * <p>Title: MedicareChoosePersonService</p>

 * <p>Description:选择人员控件 </p>

 * <p>Company: POWERSI</p> 

 * @author xiexiao

 * @date 2018年11月22日
 */

@SuppressWarnings("all")
public interface MedicareChoosePersonService {
	/**
	 * TS18112300024 - 【需求开发】结算云人员选择控件
	 * @Description: 查询人员信息（通过远程调用）
	 * @author: xiexiao
	 * @date: 2018年11月22日
	 * @param param
	 * @return
	 */
	List getPersonInfo(Map param);
	
	/**
	 * TS18112300024 - 【需求开发】结算云人员选择控件
	 * @Description: 根据社保卡号获取人员信息（包含卡鉴权）
	 * @author: xiexiao
	 * @date: 2018年11月22日
	 * @param param
	 * @return
	 */
	List getPersInfoByICCard(Map param);
}
