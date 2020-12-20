package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;
import java.util.Map;

public interface KAB8Service extends Serializable {

	/**
	 * 
	 * @param BKM017
	 * @return 限价药品
	 */
	@SuppressWarnings("rawtypes")
	public Map selectKAB8(String bkm017);

}
