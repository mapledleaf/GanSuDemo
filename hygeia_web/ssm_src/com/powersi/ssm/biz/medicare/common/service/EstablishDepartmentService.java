package com.powersi.ssm.biz.medicare.common.service;

import com.powersi.config.pojo.BizYyInfo;
import com.powersi.mq.MQCallback;

/**
 * 创建医院默认部门、管理员用户
 * 
 * @author 刘勇
 *
 */
public interface EstablishDepartmentService extends MQCallback, java.io.Serializable {

	/**
	 * 
	 * @param yyInfo
	 */
	public void establishDepartment(BizYyInfo yyInfo);

}
