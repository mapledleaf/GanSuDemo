package com.powersi.biz.medicare.medicalpage.service.api.mccc;

import java.io.Serializable;
import java.util.Map;

public interface MCCCbizxjg000121Service extends Serializable {
	public String serviceid = "BIZXJG000121";
	/**
	 * 删除出院小结信息
	 * @param mParas
	 * @return
	 */
	public int deleteOutHosInfo(Map<String, Object> mParas);
}
