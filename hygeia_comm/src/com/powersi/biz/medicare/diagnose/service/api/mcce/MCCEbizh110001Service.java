package com.powersi.biz.medicare.diagnose.service.api.mcce;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;

/**
 * 
 * bizh110001 门诊取人员信息
 *
 */
public interface MCCEbizh110001Service extends Serializable{

	public String serviceid = "bizh110001";

	public String NOPERSONINFOMESSAGE = "无法获取人员基本信息";

	/**
	 * 门诊取人员信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map searchPersonInfo(DiagnoseInfoDTO diagnoseInfoDTO);

	/**
	 * 多个人员信息获取
	 * @param diagnoseInfoDTO
	 * @return
	 */
	public List getPersonList(DiagnoseInfoDTO diagnoseInfoDTO);
}
