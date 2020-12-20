package com.powersi.sys.session.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.FactoryBean;

import com.powersi.comm.exception.ApusException;
import com.powersi.comm.utils.ToolUtil;

/**
 * 由redis配置文件获取参数值的工厂类
 * 
 * @author dell-pc
 *
 */
public class MemoryDbParamFactoryBean implements FactoryBean<Object> {

	private Properties configs;
	private String paramPart;// 配置文件中的部分配置参数
	boolean useRedisHA = false;
	private static Map<String, String> translate_class_map;// 用于配置参数的基本类型翻译

	// 静态加载翻译的map
	static {
		translate_class_map = new HashMap<String, String>();
		translate_class_map.put("password", "java.lang.String");
		translate_class_map.put("server", "java.lang.String");
		translate_class_map.put("port", "java.lang.Integer");
		translate_class_map.put("timeout", "java.lang.Integer");
		translate_class_map.put("database", "java.lang.Integer");
	}

	@Override
	public Object getObject() throws Exception {
		Properties prop = null;
		Object returnParam = null;
		try {
			prop = configs;
			useRedisHA = Boolean.parseBoolean(prop.getProperty("memorydb.redis_use_ha", "false"));
			// 采用ha方式
			if (useRedisHA) {
				// 端口和redis服务器ip只有直连模式才配置
				if ("port".equals(paramPart)) {
					return 0;
				}
				if ("server".equals(paramPart)) {
					return null;
				}
				returnParam = prop.getProperty("memorydb.redis.ha." + paramPart);
			} else {
				returnParam = prop.getProperty("memorydb.redis.pool." + paramPart);
			}
			// 参数没有配置
			if (returnParam == null) {
				throw new ApusException(
						"请核查memorydb.properties中memorydb.redis_use_ha为:" + useRedisHA + "配置的" + paramPart + "参数是否正确");
			}
		} catch (Exception e) {
			throw new ApusException("由redis配置文件获取参数值时，读取配置文件异常", e);
		}
		return returnParam;
	}

	@Override
	public Class<?> getObjectType() {
		Class<?> forName = null;
		if (paramPart == null) {
			return forName;
		}
		try {
			String class_str = translate_class_map.get(paramPart);
			if (StringUtils.isBlank(class_str)) {
				throw new ApusException(paramPart + "为无效redis配置参数");
			}
			forName = Class.forName(class_str);
		} catch (Exception e) {
			throw new ApusException("未找到参数" + paramPart + "所属类异常信息：" + ToolUtil.getExceptionInfo(e));
		}
		return forName;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public Properties getConfigs() {
		return configs;
	}

	public void setConfigs(Properties configs) {
		this.configs = configs;
	}

	public String getParamPart() {
		return paramPart;
	}

	public void setParamPart(String paramPart) {
		this.paramPart = paramPart;
	}

}
