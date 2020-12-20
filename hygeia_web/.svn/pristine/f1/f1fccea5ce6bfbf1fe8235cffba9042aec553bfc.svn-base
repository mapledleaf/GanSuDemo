package com.powersi.sys.session.service;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;

import com.powersi.comm.exception.ApusException;
import com.powersi.comm.utils.ToolUtil;



public class SpringSessionFactory implements FactoryBean<Object>{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Properties configs;	
	//是否采用HA集群方式
	private boolean useRedisHA = false;
	
	/**
	 * 自定义的加载配置文件memorydb.properties的配置项方法
	 * 在初始化之前加载配置文件，用于替代xml的配置方式
	 */
	public RedisSentinelConfiguration init(){
		RedisSentinelConfiguration sentinelConfig = null;
		try {
			Properties prop = null;
			prop = configs;
			useRedisHA = Boolean.parseBoolean( prop.getProperty("memorydb.redis_use_ha", "false") );
			System.out.println("spring-session采用redisHA：" + useRedisHA);
			logger.info("spring-session采用redisHA：" + useRedisHA);
			//不采用ha方式
			if( useRedisHA ){
				//采用ha方式 ----采用哨兵模式
				Set<String> redis_ha_server = new HashSet<String>();
				int redis_numbers = Integer.parseInt( prop.getProperty("memorydb.redis.ha.numbers", "1") );
				final String redis_master = prop.getProperty( "memorydb.redis.ha.master", "master" );
				
				//读取集群ip
				for (int i = 1; i <= redis_numbers; ++i) {
					String key = String.format("memorydb.redis.ha.server.%d", i);
					String value = prop.getProperty(key, "").trim();
					if (value.length() > 0) {
						redis_ha_server.add(value);
					}
				}
				
				//没有读取到ip,直接抛出异常
				if( redis_ha_server.size() == 0 ){
					throw new ApusException("没有读取到哨兵的IP，程序将采用直连模式，请检查配置文件memorydb.redis.ha.server.XX配置");
				}
				//设置主redis的信息配置
				sentinelConfig = new RedisSentinelConfiguration();
				sentinelConfig = sentinelConfig.master(redis_master);
				//将N个哨兵配置
				for (String server : redis_ha_server) {
					String[] arr = server.split(":");
					RedisNode sentinel = new RedisNode(arr[0],Integer.parseInt(arr[1]));
					sentinelConfig =  sentinelConfig.sentinel(sentinel);
				}
				
			}
		} catch (Exception ex) {
			logger.error("redis创建失败： \r\n" + ToolUtil.getExceptionInfo(ex));
		}
		return sentinelConfig;
	}
	
	@Override
	public Object getObject() throws Exception {
		
		return init();
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getObjectType() {
		return RedisSentinelConfiguration.class;
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
	
	
	
}
