package com.powersi.biz.medicare.inhospital.service.bean;

import java.io.Serializable;

/**
 * 
 * @author 刘勇
 *
 */
public interface BeanService extends Serializable {

	/**
	 * 
	 * @param source
	 * @param target
	 */
	public void copyProperties(Object source, Object target);

	/**
	 * 
	 * @param source
	 * @param target
	 * @param ignoreNull
	 */
	public void copyProperties(Object source, Object target, boolean ignoreNull);

	/**
	 * 
	 * @param source
	 * @param target
	 * @param propertyName
	 */
	public void copyProperties(Object source, Object target, String[] propertyName);

}
