package com.powersi.hygeia.comm.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.comm.exception.ApusException;
import com.powersi.comm.service.memory.MemoryDB;

@Service("hygeiaBizYymlTypeService")
public class HygeiaBizYymlTypeService {

	private final String MAP_ML_TYPES_INFO = "MAP_ML_TYPES_INFO";
	private final String MAP_BIZ_YY_MATCH_PCLOUD_CENTER = "MAP_BIZ_YY_MATCH_PCLOUD_CENTER";
	@Autowired
	private MemoryDB memoryDB;

	/**
	 * 根据云端医院编码获取医院目录标识 1单独目录 2公共目录，
	 * 
	 * @param caa027
	 *            中心编码
	 * @param pcloud_akb020
	 *            云端医院编码
	 * @return
	 */
	public String getYymlType(String caa027, String pcloud_akb020) {
		// 通过结算云医院编码和统筹编码获取中心端医院编码
		String centerYybm = (String) memoryDB.getMapValue(MAP_BIZ_YY_MATCH_PCLOUD_CENTER, caa027 + "_" + pcloud_akb020);
		if (StringUtils.isBlank(centerYybm)) {
			throw new ApusException("memoryDB找不到云端匹配的中心医院编码，中心编码为：" + caa027 + "，云端医院编码为：" + pcloud_akb020);
		}
		String yymllx = (String) memoryDB.getMapValue(MAP_ML_TYPES_INFO, caa027 + "_" + centerYybm);
		//TS19040300208 湘潭目录增量刷新 如果目录类型取出为空则默认赋值为1单独目录  cj  20190428
		if (StringUtils.isBlank(yymllx)) {
			yymllx = "1";
		}
		if (StringUtils.isBlank(yymllx)) {
			throw new ApusException(
					"memoryDB找不到对应中心医院的目录类型，中心编码：" + caa027 + ",中心医院编码," + centerYybm + "，云端编码：" + pcloud_akb020);
		}
		return yymllx;
	}

}
