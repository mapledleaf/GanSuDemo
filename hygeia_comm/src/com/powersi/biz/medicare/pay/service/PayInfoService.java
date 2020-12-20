package com.powersi.biz.medicare.pay.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.pay.pojo.PayInfo;
import com.powersi.biz.medicare.yygh.pojo.MobilePayDTO;

/**
 * @author penggang 移动支付服务接口
 */
public interface PayInfoService {

	/**
	 * 通过医院编号，就医登记号查询支付信息日志
	 * 
	 * @param akb020
	 *            医院编号
	 * @param aaz217
	 *            就医登记号
	 * @return
	 */
	public List<Map<String, String>> queryPayInfo(String akb020, String aaz217);
	
	/**
	 * 查询移动支付明细情况
	 * @param mobilePayDTO
	 * @return
	 */
	public List<PayInfo> queryMobilePaymentDetail(MobilePayDTO mobilePayDTO);

}
