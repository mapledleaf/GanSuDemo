package com.powersi.biz.medicare.comm.pojo;

import java.io.Serializable;

/**
 * 
 * @author 刘勇
 *
 */
public interface IPOJO extends Serializable {

	/**
	 * 
	 * @return
	 */
	public String toJson();

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public boolean compareTo(Object obj);

}
