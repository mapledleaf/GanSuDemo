package com.powersi.ssm.biz.medicare.diagnose.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;

public interface GetPersonInfoService {

	/**
	 * 门诊 退费取人员信息接口
	 */
	public static final String bizh110103 = "bizh110103";
	
	/**
	 * 门诊退费时获取人员业务信息
	 * @param diagnoseInfoDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getPersonBusi(DiagnoseInfoDTO diagnoseInfoDTO);
	
	/**
	 * 门诊退费时获取人员业务信息
	 * @param diagnoseInfoDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getBusiFeeInfo(DiagnoseInfoDTO diagnoseInfoDTO);
	
	@SuppressWarnings("rawtypes")
	public Map getPersonBusiDetail(DiagnoseInfoDTO diagnoseInfoDTO);

	/**
	 * 修改概要：NTS20050700380-生育、门特业务省内异地联网结算需求 -- 湘潭
	 * 修改描述：增加异地改费查人
	 * 修改人及修改时间：李嘉伦 2020/5/18
	 */
	public List<Map> getPersonBusi_remote(DiagnoseInfoDTO diagnoseInfoDTO);
	/**
	 * 修改概要：NTS20050700380-生育、门特业务省内异地联网结算需求 -- 湘潭
	 * 修改描述：增加异地改费查费用信息
	 * 修改人及修改时间：李嘉伦 2020/5/18
	 */
	public Map getPersonBusiDetail_remote(DiagnoseInfoDTO diagnoseInfoDTO);
}
