package com.powersi.biz.medicare.query.service.api;

import java.io.Serializable;

import com.powersi.webservice.WebServiceProcesser;

/**
 * 提供给云药店的查门诊类结算单数据的接口
 * @author ChenXing
 *
 */
public interface MCCHbizc200306Service extends WebServiceProcesser,Serializable {

	public String function_id = "bizc200306";
	
}