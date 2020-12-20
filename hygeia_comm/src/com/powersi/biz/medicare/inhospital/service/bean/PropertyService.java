package com.powersi.biz.medicare.inhospital.service.bean;

import java.io.Serializable;

/**
 * 
 * @author 刘勇
 *
 */
public interface PropertyService extends Serializable {

	/**
	 * 
	 * @param bean
	 * @param field
	 * @param value
	 * @param ignoreNull
	 */
	public void setProperty(Object bean, String field, Object value, boolean ignoreNull);

	/**
	 * 
	 * @param bean
	 * @param field
	 * @param value
	 */
	public void setProperty(Object bean, String field, Object value);

}
