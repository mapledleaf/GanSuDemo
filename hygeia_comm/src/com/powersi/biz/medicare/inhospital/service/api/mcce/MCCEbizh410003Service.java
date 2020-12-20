package com.powersi.biz.medicare.inhospital.service.api.mcce;

import com.powersi.biz.medicare.yygh.pojo.MobilePayDTO;

/**
 * 
 * bizh410003
 * 调用医院指令通知接口
 */
public interface MCCEbizh410003Service {

	/**
	 * 调用医院指令通知接口
	 * @param mobilePayDTO
	 * @return
	 */
	public MobilePayDTO sendInstruct(MobilePayDTO mobilePayDTO);

}
