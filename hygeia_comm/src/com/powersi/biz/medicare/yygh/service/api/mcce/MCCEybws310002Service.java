package com.powersi.biz.medicare.yygh.service.api.mcce;

import com.powersi.biz.medicare.yygh.pojo.KE02;

/**
 * 
 * @Description: 预约挂号（或取消预约挂号）
 * @author zhos
 * @date 2017年3月29日 下午3:03:25
 *
 */
public interface MCCEybws310002Service {

	/**
	 * 提交预约挂号（或取消预约挂号）
	 */
	public KE02 saveOrCancelYygh(KE02 dto);
}
