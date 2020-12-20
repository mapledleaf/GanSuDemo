package com.powersi.biz.medicare.inhospital.service.api.mcch;

import java.io.Serializable;

import com.powersi.webservice.WebServiceProcesser;

/**
 * 保存普通住院申请信息
 * @author ChenXing
 */
public interface MCCHbizh120113Service extends WebServiceProcesser,Serializable {

	public String function_id = "bizh120113";
	
	public String serviceid   = "MEDICARE.ZNYS.0002";
	
}
