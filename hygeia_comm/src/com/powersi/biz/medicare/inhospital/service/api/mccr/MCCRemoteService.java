package com.powersi.biz.medicare.inhospital.service.api.mccr;

import com.powersi.webservice.WebServiceProcesser;

/**
 * 
 * 结算云面向中心端提供服务接口_remote
 * 
 * @author 刘勇
 *
 */
public interface MCCRemoteService extends WebServiceProcesser {

	/**
	 * 电脑号
	 */
	public String aac001 = "aac001";

	/**
	 * 证件号码(社会保障号)
	 */
	public String aac002 = "aac002";

	/**
	 * 社保卡卡号
	 */
	public String bka100 = "bka100";

	/**
	 * 就医登记号
	 */
	public String aaz217 = "aaz217";

	/**
	 * 住院号
	 */
	public String bka025 = "bka025";

	/**
	 * 医院编号
	 */
	public String akb020 = "akb020";

}
