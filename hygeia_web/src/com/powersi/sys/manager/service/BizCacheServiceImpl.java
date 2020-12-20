package com.powersi.sys.manager.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.powersi.comm.service.memory.MemoryDB;
import com.powersi.comm.service.memory.MemoryDBWrapper;
import com.powersi.hygeia.framework.CodetableMapping;

/**
 * 业务缓存管理 service 实现类
 * 
 * @author "lingang"
 * @time 2016年11月1日上午10:27:43
 *
 */
@Service
public class BizCacheServiceImpl implements BizCacheService {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemoryDB memoryDB;
	@Autowired
	@Qualifier("memoryDBWrapper_core")
	private MemoryDBWrapper memoryDBWrapper;

	@Override
	public void readRefreshFlag(String bizFlag) {
		this.memoryDB.setMapValue(MAP_CACHEREFRESH_SIGIN, bizFlag, bizFlag);
		if (updateCacheAlltables.indexOf(bizFlag) != -1) {
			this.memoryDB.setMapValue(MAP_CACHEREFRESH_SIGIN, bizFlag + _ALL, bizFlag + _ALL);
		}
		this.logger.info("WEB服务业务缓存管理操作刷新缓存,通知存储" + bizFlag + "信息的数据表进行缓存刷新");
	}

	@Override
	public void readRefreshFlag(String bizFlag, String yybm, boolean ifall) {
		// 判断是否刷新全部
		if (ifall) {
			yybm = this.getAllYybm();
		}
		if (StringUtils.isBlank(yybm)) {
			return;
		}
		// 下载的医院目录,则根据相应医院编码设置刷新缓存
		// 防止下面分隔的时候出现空值
		if (yybm != null && yybm.indexOf(",") == -1) {
			yybm = yybm + ",";
		}
		String[] yybm_arr = yybm.split(",");
		if (yybm_arr == null || yybm_arr.length == 0) {
			return;
		}
		for (String key : yybm_arr) {
			if (StringUtils.isBlank(key)) {
				continue;
			}
			this.memoryDB.setMapValue(MAP_CACHEREFRESH_SIGIN, ML_ + key, ML_ + key);
			this.memoryDB.setMapValue(MAP_CACHEREFRESH_SIGIN, ML_ + key + _ALL, ML_ + key + _ALL);
		}
		this.logger.info("WEB服务业务缓存管理操作刷新缓存,通知医院编码为:" + yybm + "的目录存储表刷新缓存");
	}

	/**
	 * 获取全部的医院编码
	 * 
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	private String getAllYybm() {
		String yybmStr = "";
		// 从缓存获取所有的医院信息
		Map bizYyInfomap = this.memoryDBWrapper.getMemoryDB().getMap(MAP_BIZ_YY_INFO);
		if (bizYyInfomap == null || bizYyInfomap.size() == 0) {
			return yybmStr;
		}
		for (Object key : bizYyInfomap.keySet()) {
			if (key == null || StringUtils.isBlank(key.toString())) {
				continue;
			}
			yybmStr = yybmStr + key.toString() + ",";
		}
		return yybmStr;
	}

	public void readRefreshFlag_Policy(String bizFlag, String tcbm, boolean ifall) {
		// 判断是否刷新全部
		if (ifall) {
			tcbm = this.getAllTcbm();
		}
		if (StringUtils.isBlank(tcbm)) {
			return;
		}
		// 防止下面分隔的时候出现空值
		if (tcbm != null && tcbm.indexOf(",") == -1) {
			tcbm = tcbm + ",";
		}
		String[] tcbm_arr = tcbm.split(",");
		if (tcbm_arr == null || tcbm_arr.length == 0) {
			return;
		}
		for (String key : tcbm_arr) {
			if (StringUtils.isBlank(key)) {
				continue;
			}
			if ("INCREMENT".equals(bizFlag)) {
				this.memoryDB.setMapValue(MAP_CACHEREFRESH_SIGIN, POLICY_ + "INCREMENT", POLICY_ + "INCREMENT");
			} else if ("ALL".equals(bizFlag)) {
				this.memoryDB.setMapValue(MAP_CACHEREFRESH_SIGIN, POLICY_ + "ALL", POLICY_ + "ALL");
			}
			this.memoryDB.setMapValue(MAP_CACHEREFRESH_SIGIN, POLICY_ + key, POLICY_ + key);
		}
		this.logger.info("WEB服务业务缓存管理操作刷新缓存,通知统筹编码为:" + tcbm + "的政策参数表刷新缓存");
	}

	@Override
	public void readRefreshFlag_jzml(String bizFlag, String tcbm, boolean ifall) {
		// 判断是否刷新全部
		if (ifall) {
			tcbm = this.getAllTcbm();
		}
		if (StringUtils.isBlank(tcbm)) {
			return;
		}
		// 防止下面分隔的时候出现空值
		if (tcbm != null && tcbm.indexOf(",") == -1) {
			tcbm = tcbm + ",";
		}
		String[] tcbm_arr = tcbm.split(",");
		if (tcbm_arr == null || tcbm_arr.length == 0) {
			return;
		}
		for (String key : tcbm_arr) {
			if (StringUtils.isBlank(key)) {
				continue;
			}
			if (JZML.equals(bizFlag)) {
				this.memoryDB.setMapValue(MAP_CACHEREFRESH_SIGIN, bizFlag + _ALL, bizFlag + _ALL);
				this.memoryDB.setMapValue(MAP_CACHEREFRESH_SIGIN, JZML_ + key, JZML_ + key);
			} else {
				this.memoryDB.setMapValue(MAP_CACHEREFRESH_SIGIN, EJML_ + key, EJML_ + key);
			}
		}
		this.logger.info("WEB服务业务缓存管理操作刷新缓存,通知存储" + bizFlag + "信息的数据表进行缓存刷新");

	}

	/**
	 * 获取全部的统筹区编码
	 * 
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	private String getAllTcbm() {
		String tcbmStr = "";
		// 获取码表中配置的刷新服务
		Map tcbmInfomap = CodetableMapping.get("gds_policy_tcq");
		if (tcbmInfomap == null || tcbmInfomap.size() == 0) {
			return tcbmStr;
		}
		for (Object key : tcbmInfomap.keySet()) {
			if (key == null || StringUtils.isBlank(key.toString())) {
				continue;
			}
			tcbmStr = tcbmStr + key.toString() + ",";
		}
		return tcbmStr;
	}

}
