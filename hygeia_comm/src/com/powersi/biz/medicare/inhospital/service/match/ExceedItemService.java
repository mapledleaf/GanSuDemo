package com.powersi.biz.medicare.inhospital.service.match;

import java.io.Serializable;

/***
 * 限价
 * 
 * @author 朱帆宇
 *
 */
public interface ExceedItemService extends Serializable {

	/**
	 * 通过限价待遇类型（如Bed_7)和统筹区，查询限价金额
	 * 
	 * @param defry_type
	 * @param aaa027
	 * @return
	 */
	public double getExceedItemMoney(String defry_type, String aaa027);

}
