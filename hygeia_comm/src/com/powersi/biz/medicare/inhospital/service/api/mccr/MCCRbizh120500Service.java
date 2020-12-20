package com.powersi.biz.medicare.inhospital.service.api.mccr;

/**
 * 住院审核写入
 * 
 * <pre>
 * 1、结算云提供一个接口给中心端住院审核回调用，推送审核标识、审核说明、审核人；
 * 2、结算云把这几个数据项保存到kcd1表，kcd1有备用字段可用，住院审核目前只有清远有这个业务要求；
 * 3、在住院回退界面查未结算状态业务可查看住院审核情况；
 * 4、结算云调中心住院审核那块逻辑要对照修改，如果kcd1已经审核了，就不要调了； 
 * `BKA507` bigint(20) DEFAULT NULL,审核标识
 * `BKA510` varchar(50) DEFAULT NULL,审核说明
 * 审核人就暂时不固化了
 * </pre>
 * 
 * @author 刘飞扬、刘勇
 *
 */
public interface MCCRbizh120500Service extends MCCRemoteService {

	/**
	 * 
	 */
	public String function_id = "mccr_bizh120500";

}