package com.powersi.hygeia.comm.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.comm.exception.ApusException;
import com.powersi.comm.service.memory.MemoryDB;
import com.powersi.pcloud.dict.service.DictService;

@Service
public class ConfigService {

	@Autowired
	private MemoryDB memoryDB;
	@Autowired
	private DictService dictService;

	/**
	 * 与当前项目所属地市的统筹机构编码校对
	 * 
	 * @param aaa027
	 */
	public void checkTcjgbm(String aaa027) {
		String tcjgbm = this.getTcjgbm();
		if (StringUtils.isNotBlank(tcjgbm) && !tcjgbm.equals(aaa027)) {
			throw new ApusException("统筹区" + aaa027 + "与当前项目所属地市的统筹机构编码" + tcjgbm + "不一致!");
		}
	}

	/**
	 * 获取当前项目所属地市的统筹机构编码
	 * 
	 * @return CURRENT_TCJGBM
	 */
	public String getTcjgbm() {
		Object tcjgbmObj = this.memoryDB.get("CURRENT_TCJGBM");
		String tcjgbm = tcjgbmObj == null ? "" : (String) tcjgbmObj;
		String openFlag = this.dictService.getValue("SYS_PARAM", "OPEN_INDEPENDENT_MICRO_SERVICE", "0");
		if ("1".equals(openFlag)) {
			if (StringUtils.isBlank(tcjgbm)) {
				throw new ApusException("缓存中没有找到指定统筹区编码(CURRENT_TCJGBM)，请核实hygeia_base的config.properties配置文件");
			}
		}
		if ("0".equals(openFlag)) {
			tcjgbm = "";
		}
		return tcjgbm;
	}

	/**
	 * 一个医院定点多个统筹区，需要根据定点统筹区来路由。注意：只有HYGEIA_WEB可以漂移到不同的地市
	 * 
	 * @param serviceName
	 *            服务名称 如：HYGEIA_ESB、HYGEIA_DB等等
	 * @param yybm
	 *            定点医院编码
	 * @param aab301
	 *            定点统筹区编码/参保地行政区划代码
	 * @return 获取当前定点医院对应的定点统筹区编码
	 */
	public String getTargetTcjgbm(String serviceName, String yybm, String aab301) {
		String tcjgbm = this.getTcjgbm();
		if (StringUtils.isBlank(tcjgbm)) {
			return tcjgbm;
		}
		// 只有HYGEIA_WEB调用HYGEIA_ESB需要考虑根据统筹区调用不同的HYGEIA_ESB分组
		if (!"HYGEIA_ESB".equalsIgnoreCase(serviceName)) {
			return tcjgbm;
		}
		if (StringUtils.isBlank(yybm)) {
			return tcjgbm;
		}
		if (StringUtils.isBlank(aab301)) {
			return tcjgbm;
		}
		String targetAab301 = aab301;
		if (aab301 != null && aab301.length() == 6) {
			targetAab301 = aab301.substring(0, 4) + "00";
		}
		String targetTcjgbm = tcjgbm;
		String[] aab301s = this.getAab301s(yybm);
		for (int i = 0; aab301s != null && i < aab301s.length; i++) {
			if (targetAab301.equals(aab301s[i])) {
				targetTcjgbm = targetAab301;
				break;
			}
		}
		return targetTcjgbm;
	}

	/**
	 * 一个医院定点多个统筹区
	 * 
	 * @param yybm
	 * @return
	 */
	private String[] getAab301s(String yybm) {
		String[] aab301s = new String[] {};
		return aab301s;
	}

}
