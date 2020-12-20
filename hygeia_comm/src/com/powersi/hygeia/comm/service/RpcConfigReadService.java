package com.powersi.hygeia.comm.service;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.powersi.comm.service.memory.MemoryDBWrapper;

/**
 * 
 * @author 李志钢
 *
 */
@Service("rpcConfigReadService")
public class RpcConfigReadService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String MAP_RPC_SERVICE_CONFIG = "MAP_RPC_SERVICE_CONFIG";
	private final String MAP_RPC_BIZ_DBSERVICE = "MAP_RPC_BIZ_DBSERVICE";
	@Autowired
	@Qualifier("memoryDBWrapper_core")
	private MemoryDBWrapper memoryDBWrapper_core;
	@Autowired
	private Properties configs;
	@Autowired
	private ConfigService configService;

	@PostConstruct
	public void postInit() {
		this.logger.info("rpc是否为debug模式：" + this.configs.getProperty("pcloud_rpc.debug_mode"));
	}

	/**
	 * 
	 * @param serviceName
	 * @return IP:PORT
	 */
	public String getConfigByServiceName(String serviceName) {
		return this.getConfigByServiceName(serviceName, "", "");
	}

	/**
	 * 
	 * @param serviceName
	 *            服务名称
	 * @param yybm
	 *            定点医院编码
	 * @param aab301
	 *            定点统筹区编码/参保地行政区划代码
	 * @return IP:PORT
	 */
	public String getConfigByServiceName(String serviceName, String yybm, String aab301) {
		if ("1".equals(this.configs.getProperty("pcloud_rpc.debug_mode"))) {
			return this.configs.getProperty("pcloud_rpc.debug_endpoint", "127.0.0.1:8080");
		}
		String tcjgbm = this.configService.getTargetTcjgbm(serviceName, yybm, aab301);
		Object endpointobj = this.memoryDBWrapper_core.getMemoryDB().getMapValue(MAP_RPC_SERVICE_CONFIG,
				serviceName + tcjgbm);
		if (endpointobj == null || StringUtils.isBlank(endpointobj.toString().trim())) {
			this.logger.error("无法找到给定服务对应的访问入口信息：" + serviceName + tcjgbm + "!");
			return "";
		}
		this.logger.info(new StringBuilder("服务： ").append(serviceName).append(" 医院： ").append(yybm).append(" 定点统筹区： ")
				.append(aab301).append(" 映射定点统筹区： ").append(tcjgbm).append(" 对应的入口地址为： ")
				.append(endpointobj.toString().trim()).toString());
		return endpointobj.toString().trim();
	}

	/**
	 * 
	 * @param yybm
	 *            定点医院编码
	 * @return IP:PORT
	 */
	public String getDBServiceConfigByYYBM(String yybm) {
		return this.getDBServiceConfigByYYBM("HYGEIA_DB", yybm, "");
	}

	/**
	 * 
	 * @param serviceName
	 *            服务名称
	 * @param yybm
	 *            定点医院编码
	 * @param aab301
	 *            定点统筹区编码/参保地行政区划代码
	 * @return IP:PORT
	 */
	public String getDBServiceConfigByYYBM(String serviceName, String yybm, String aab301) {
		if ("1".equals(this.configs.getProperty("pcloud_rpc.debug_mode"))) {
			return this.configs.getProperty("pcloud_rpc.debug_endpoint", "127.0.0.1:8080");
		}
		String tcjgbm = this.configService.getTargetTcjgbm(serviceName, yybm, aab301);
		Object endpointobj = this.memoryDBWrapper_core.getMemoryDB().getMapValue(MAP_RPC_BIZ_DBSERVICE, yybm + tcjgbm);
		if (endpointobj == null || StringUtils.isBlank(endpointobj.toString().trim())) {
			this.logger.debug("无法找到给定服务对应的数据服务访问入口信息,医院编码：" + yybm + tcjgbm + "!");
			endpointobj = this.memoryDBWrapper_core.getMemoryDB().getMapValue(MAP_RPC_BIZ_DBSERVICE, yybm);
			if (endpointobj == null || StringUtils.isBlank(endpointobj.toString().trim())) {
				this.logger.error("无法找到给定服务对应的数据服务访问入口信息,医院编码：" + yybm + "!");
				return "";
			}
		}
		this.logger.info(new StringBuilder("服务： ").append(serviceName).append(" 医院： ").append(yybm).append(" 定点统筹区： ")
				.append(aab301).append(" 映射定点统筹区： ").append(tcjgbm).append(" 对应的入口地址为： ")
				.append(endpointobj.toString().trim()).toString());
		return endpointobj.toString().trim();
	}

}
