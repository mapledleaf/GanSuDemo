package com.powersi.biz.medicare.inhospital.service.api.mccc.asyn;

import java.io.Serializable;

import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEsbService;
import com.powersi.mq.MQCallback;

/**
 * 
 * 调中心异步处理接口
 * 
 * @author 刘勇
 *
 */
public interface AsynMCCCenterService extends MQCallback, Serializable {

	String queue_mcce_ = MCCEsbService.queue_mcce_;

}
