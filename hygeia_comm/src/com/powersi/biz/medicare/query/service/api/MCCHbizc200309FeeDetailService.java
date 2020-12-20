package com.powersi.biz.medicare.query.service.api;

import java.io.Serializable;

import com.powersi.webservice.WebServiceProcesser;

/**
* 提供给云药店的查费用清单的接口
* @author ChenXing
*/
public interface MCCHbizc200309FeeDetailService extends WebServiceProcesser,Serializable {

	public String function_id = "bizc200309";
	
}