package com.powersi.biz.medicare.alipay.service;

import java.util.List;
import java.util.Map;

/**
 * .同步支付宝订单信息管理
 * TS19092700016 - 【需求开发】电子社保卡医保结算业务相关优化改造
 * @author wtf
 * @DateTime 2019年10月12日上午10:30:57
 */
public interface OrderInfoService {
	
	/**
	 * .存储需同步的订单信息
	 * @param aliPayParam
	 * @param subMsg
	 * @param status
	 * @return
	 */
	int saveOrderInfos(Map<String, Object> aliPayParam, String subMsg, String reduceflag);
	
	/**
	 * .查询三天内同步失败的订单信息
	 * @return
	 */
	List<Map<String, Object>> queryOrderInfos();

	/**
	 * .更新订单同步信息
	 * @param aliPayParams
	 * @param string 
	 * @param string2
	 * @return
	 */
	int updateOrderInfo(Map<String, Object> aliPayParams, String isSuccess, String subMsg);
}
