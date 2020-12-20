package com.powersi.hygeia.comm.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.comm.service.memory.MemoryDB;

/**
 * 医院编码翻译 服务
 * 
 * @author "lingang"
 *
 */
@Service("hygeiaBizYyMatchService")
public class HygeiaBizYyMatchService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemoryDB memoryDB;

	/**
	 * 获取中心医院编码 云端医院编码翻译至中心医院编码
	 * 
	 * @param caa027
	 *            中心服务标识
	 * @param yybm
	 *            云端医院编码
	 * @return
	 */
	public String getCenterYybm(String caa027, String yybm) {
		String centerYybm = (String) memoryDB.getMapValue("MAP_BIZ_YY_MATCH_PCLOUD_CENTER", caa027 + "_" + yybm);
		if (StringUtils.isBlank(centerYybm)) {
			logger.error("云端医院编码翻译至中心医院编码,中心服务为:" + caa027 + "云端医院编码为:" + yybm + "的翻译值为空.");
			return yybm;
		}
		return centerYybm;
	}

	/**
	 * 获取云端医院编码 中心医院编码翻译至云端医院编码
	 * 
	 * @param caa027
	 *            中心服务标识
	 * @param akb020
	 *            中心医院编码
	 * @return
	 */
	public String getPcloudYybm(String caa027, String akb020) {
		String pcloudYybm = (String) memoryDB.getMapValue("MAP_BIZ_YY_MATCH_CENTER_PCLOUD", caa027 + "_" + akb020);
		if (StringUtils.isBlank(pcloudYybm)) {
			logger.error("中心医院编码翻译至云端医院编码,中心服务为:" + caa027 + "中心医院编码为:" + akb020 + "的翻译值为空.");
			return akb020;
		}
		return pcloudYybm;
	}

	/**
	 * 获取匹配的中心医院的中心统筹区编码
	 * 
	 * @param caa027
	 *            中心服务标识
	 * @param yybm
	 *            云端医院编码
	 * @return
	 */
	public String getCenterTcqbm(String caa027, String yybm) {
		String tcqbm = (String) memoryDB.getMapValue("MAP_BIZ_YY_CENTER_TCQBM", caa027 + "_" + yybm);
		if (StringUtils.isBlank(tcqbm)) {
			logger.error("获取云端医院匹配的中心医院对应统筹区编码为空,中心服务为:" + caa027 + "云端医院编码为:" + yybm + ".");
		}
		return tcqbm;
	}

}
