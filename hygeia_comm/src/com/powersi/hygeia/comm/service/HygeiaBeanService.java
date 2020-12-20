package com.powersi.hygeia.comm.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 
 * @author 刘勇
 *
 */
@Service
public class HygeiaBeanService implements ApplicationContextAware {

	private ApplicationContext mContext = null;

	private static HygeiaBeanService instance = null;

	/**
	 * 
	 */
	public HygeiaBeanService() {
		instance = this;
	}

	/**
	 * 
	 * @return
	 */
	public static HygeiaBeanService getInstance() {
		return instance;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.setmContext(applicationContext);
	}

	/**
	 * 
	 * @param serviceName
	 * @return
	 */
	public Object getBean(String serviceName) {
		return this.getmContext().getBean(serviceName);
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public <T> T getBeanByClass(Class<T> clazz) {
		return this.getmContext().getBean(clazz);
	}

	public ApplicationContext getmContext() {
		return this.mContext;
	}

	public void setmContext(ApplicationContext mContext) {
		this.mContext = mContext;
	}

}
