package com.powersi.hygeia.comm.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.powersi.comm.exception.ApusException;

/**
 * HygeiaServices RPC
 * 
 * @author 李志钢
 *
 */
@Service
public class HygeiaServiceManager implements ApplicationContextAware {

	private static final ThreadLocal<String> mapLocal_hospitalid = new ThreadLocal<String>();
	private static final ThreadLocal<String> mapLocal_aab301 = new ThreadLocal<String>();
	private ApplicationContext mContext = null;
	private static HygeiaServiceManager instance = null;

	/**
	 * 
	 */
	public HygeiaServiceManager() {
		instance = this;
	}

	/**
	 * 
	 * @return
	 */
	public static HygeiaServiceManager getInstance() {
		return instance;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.setmContext(applicationContext);
	}

	/**
	 * 
	 * @param serviceName
	 * @param hospitalID
	 * @return
	 */
	public Object getBean(String serviceName, String hospitalID) {
		mapLocal_hospitalid.set(hospitalID);
		return this.getmContext().getBean(serviceName);
	}

	/**
	 * 请注意：此方法只针对一个医院定点多个统筹区的情况开放 RPC
	 * 
	 * @param serviceName
	 *            服务名称
	 * @param hospitalID
	 *            定点医院编码
	 * @param aab301
	 *            定点统筹区编码/参保地行政区划代码
	 * @return
	 */
	public Object getBean(String serviceName, String hospitalID, String aab301) {
		if (StringUtils.isBlank(hospitalID)) {
			throw new ApusException("映射远程服务定点医院编码不能为空！");
		}
		if (StringUtils.isBlank(aab301)) {
			throw new ApusException("映射远程服务定点统筹区编码不能为空！");
		}
		mapLocal_hospitalid.set(hospitalID);
		mapLocal_aab301.set(aab301);
		return this.getmContext().getBean(serviceName);
	}

	/**
	 * 
	 * @param clazz
	 * @param hospitalID
	 * @return
	 */
	public <T> T getBeanByClass(Class<T> clazz, String hospitalID) {
		mapLocal_hospitalid.set(hospitalID);
		return this.getmContext().getBean(clazz);
	}

	/**
	 * 请注意：请注意：此方法只针对一个医院定点多个统筹区的情况开放 RPC
	 * 
	 * @param clazz
	 *            服务类型
	 * @param hospitalID
	 *            定点医院编码
	 * @param aab301
	 *            定点统筹区编码/参保地行政区划代码
	 * @return
	 */
	public <T> T getBeanByClass(Class<T> clazz, String hospitalID, String aab301) {
		if (StringUtils.isBlank(hospitalID)) {
			throw new ApusException("映射远程服务定点医院编码不能为空！");
		}
		if (StringUtils.isBlank(aab301)) {
			throw new ApusException("映射远程服务定点统筹区编码不能为空！");
		}
		mapLocal_hospitalid.set(hospitalID);
		mapLocal_aab301.set(aab301);
		return this.getmContext().getBean(clazz);
	}

	/**
	 * 
	 * @return 定点医院编码
	 */
	public static String getCurrentHospitalID() {
		String hospitalID = mapLocal_hospitalid.get();
		if (StringUtils.isBlank(hospitalID)) {
			hospitalID = "";
		}
		return hospitalID;
	}

	/**
	 * 
	 * @return 定点统筹区编码/参保地行政区划代码
	 */
	public static String getCurrentAab301() {
		String aab301 = mapLocal_aab301.get();
		if (StringUtils.isBlank(aab301)) {
			aab301 = "";
		}
		return aab301;
	}

	/**
	 * 
	 * @return
	 */
	public ApplicationContext getmContext() {
		return this.mContext;
	}

	/**
	 * 
	 * @param mContext
	 */
	public void setmContext(ApplicationContext mContext) {
		this.mContext = mContext;
	}

}
