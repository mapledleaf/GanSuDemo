package com.powersi.hygeia.comm.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import com.powersi.comm.exception.ApusException;

/**
 * RPC Service
 * 
 * @author 李志钢
 *
 */
public class HygeiaServiceFactoryBean implements FactoryBean<Object> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String serviceUrl;
	private String serviceInterface;
	private final ConcurrentHashMap<String, Object> serviceMap = new ConcurrentHashMap<String, Object>();
	@Autowired
	@Qualifier("rpcConfigReadService")
	private RpcConfigReadService rpcConfigReadService;

	@Override
	public Object getObject() throws Exception {
		String hospitalID = HygeiaServiceManager.getCurrentHospitalID();
		String aab301 = HygeiaServiceManager.getCurrentAab301();
		String lsServiceUrl = this.getServiceUrl();
		lsServiceUrl = this.replaceServiceUrlWithConfig(lsServiceUrl, hospitalID, aab301);
		if (this.serviceMap.containsKey(lsServiceUrl)) {
			return this.serviceMap.get(lsServiceUrl);
		}
		HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
		httpInvokerProxyFactoryBean.setServiceUrl(lsServiceUrl);
		httpInvokerProxyFactoryBean.setServiceInterface(Class.forName(this.getServiceInterface()));
		httpInvokerProxyFactoryBean.afterPropertiesSet();
		Object rpcobj = httpInvokerProxyFactoryBean.getObject();
		if (rpcobj == null) {
			throw new ApusException(
					"无法根据url,class创建service: url:" + lsServiceUrl + ",class:" + this.getServiceInterface());
		}
		this.serviceMap.put(lsServiceUrl, rpcobj);
		return rpcobj;
	}

	/**
	 * 
	 * @param strurl
	 * 
	 * @param yybm
	 *            定点医院编码
	 * @param aab301
	 *            定点统筹区编码/参保地行政区划代码
	 * @return
	 */
	private String replaceServiceUrlWithConfig(String strurl, String yybm, String aab301) {
		String retUrl = strurl;
		Pattern pattern = Pattern.compile("(?<=\\@\\{)\\w+", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(retUrl);
		String serviceName = "", endpoint = "";
		while (matcher.find()) {
			serviceName = matcher.group(0);
			break;
		}
		if (StringUtils.isBlank(serviceName)) {
			throw new ApusException("解析远程服务名称为空,url=" + retUrl + "!");
		}
		if ("HYGEIA_DB".equalsIgnoreCase(serviceName)) {
			if (StringUtils.isBlank(yybm)) {
				throw new ApusException("远程服务工厂类无法得到当前线程存放的医院编码!");
			}
			endpoint = this.rpcConfigReadService.getDBServiceConfigByYYBM(serviceName, yybm, aab301);
		} else {
			endpoint = this.rpcConfigReadService.getConfigByServiceName(serviceName, yybm, aab301);
		}
		if (StringUtils.isBlank(endpoint)) {
			this.logger.error("无法根据参数配置找到服务：" + serviceName + "对应的地址,serviceName=" + serviceName + ",yybm=" + yybm
					+ ",aab301=" + aab301);
			throw new ApusException("无法根据参数配置找到服务：" + serviceName + "对应的地址,serviceName=" + serviceName + ",yybm=" + yybm
					+ ",aab301=" + aab301);
		}
		retUrl = retUrl.replaceAll("@\\{" + serviceName + "\\}", endpoint);
		return retUrl;
	}

	@Override
	public Class<?> getObjectType() {
		if (StringUtils.isBlank(this.getServiceInterface())) {
			return null;
		}
		try {
			return Class.forName(this.getServiceInterface());
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getServiceInterface() {
		return serviceInterface;
	}

	public void setServiceInterface(String serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

}
