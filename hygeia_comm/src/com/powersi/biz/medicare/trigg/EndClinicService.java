package com.powersi.biz.medicare.trigg;

import java.io.Serializable;

/**
 * 
 * @author 刘勇
 *
 */
public interface EndClinicService extends Serializable {

	/**
	 * 门诊类业务结束诊次
	 */
	public void triggerEndClinic();

}
