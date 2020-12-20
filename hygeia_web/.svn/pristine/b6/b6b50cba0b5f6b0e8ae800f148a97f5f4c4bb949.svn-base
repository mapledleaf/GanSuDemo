package com.powersi.ssm.biz.medicare.medicarepay.service;

import java.util.Map;

import com.powersi.hygeia.comm.service.HygeiaServiceManager;

/**
 * 
 * 医疗拨付Service
 * 
 * 2018年9月14日下午3:32:03
 *
 * @author lwyao
 *
 */
public interface MedicarepayService {

	/**
	 * HygeiaService RPC
	 * 
	 * @author lwyao
	 * @date 2018年11月15日
	 */
	public void setHygeiaServiceManager(HygeiaServiceManager hygeiaServiceManager);

	/**
	 * HygeiaService RPC
	 * 
	 * @author lwyao
	 * @date 2018年11月15日
	 */
	public HygeiaServiceManager getHygeiaServiceManager();

	/**
	 * 刷新中心端码表
	 * 
	 * @author lwyao
	 * @date 2018年11月15日
	 * @param codetype 码表类型，为空时则获取医保中心配置的可同步全部码表
	 * @param daa027   拨付中心
	 * @return
	 */
	public Map<String, Map<String, String>> refreshCenterCodetype(String codetype, String daa027);

}
