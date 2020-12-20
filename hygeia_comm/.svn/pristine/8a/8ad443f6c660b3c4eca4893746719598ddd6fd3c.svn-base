package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.AA10DTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 
 * @author 刘勇
 *
 */
public interface AA10Service extends Serializable {

	/**
	 * 
	 * @param aA13DTO
	 * @return 码值明细
	 */
	public List<AA10DTO> selectAll(InHospitalDTO inHospitalDTO);
	
	/**
	 * 根据key和LocalId取码表value值
	 * @param inHospitalDTO
	 * @return
	 */
	public AA10DTO selectByKeyAndLocalId(InHospitalDTO inHospitalDTO);
	
	public List<AA10DTO> selectCodeDisValue(String codeType);

	/**
	 * 获取全部政策刷新统筹区信息
	 * 
	 * @return Map
	 */
	@SuppressWarnings("rawtypes")
	public Map getPolicyTcqInfo();
	
	/**
	 * 获取基金风险监控脚本
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> selectMonitorScripts(Map params);
	
	/**
	 * 获取全部基金风险监控脚本
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> selectALLMonitorScripts();
}
