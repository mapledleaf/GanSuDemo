package com.powersi.biz.medicare.diagnose.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;

public interface DiagnoseEndClinic {

	/**
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */
	public List<DiagnoseInfoDTO> getDiagnoseData(DiagnoseInfoDTO diagnoseInfoDTO);

	@SuppressWarnings("rawtypes")
	public Map getHospital();

	public void endClinic(DiagnoseInfoDTO diagnoseInfoDTO);

	public void savelog(DiagnoseInfoDTO diagnoseInfoDTO);

	/**
	 * 查询诊次时间小于当前时间的汕头购药业务
	 * 2018-03-27  lhy
	 * @param diagnoseInfoDTO
	 * @return
	 */
	public List<DiagnoseInfoDTO> getDrugData(DiagnoseInfoDTO diagnoseInfoDTO);
	
	/**
	 * 查询kc22的bka059+kc27的
	 * 2018-03-27  lhy
	 * @param diagnoseInfoDTO
	 * @return
	 */
	public int queryReduceData(DiagnoseInfoDTO diagnoseInfoDTO);
	
	/**
	 * 把kc21、kc22、kc27等表数据无效
	 * 2018-03-27  lhy
	 * @param diagnoseInfoDTO
	 * @return
	 */
	public void endClinicDrug(DiagnoseInfoDTO diagnoseInfoDTO);
	
}
