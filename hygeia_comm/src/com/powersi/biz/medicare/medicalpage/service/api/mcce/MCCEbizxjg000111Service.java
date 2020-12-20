package com.powersi.biz.medicare.medicalpage.service.api.mcce;

import java.io.Serializable;
import java.util.Map;

public interface MCCEbizxjg000111Service extends Serializable {
	/**
	 * 查询出院小结
	 * @param mParas
	 * @return
	 */
	public Map<String, Object> queryOutHosInfo(Map<String, Object> mParas);
}
