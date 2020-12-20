package com.powersi.biz.medicare.diagnose.service;

import java.util.List;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;

public interface ElectronicPrescriptionService {
	
	
	/**
	 * 查询电子处方
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryErx(DiagnoseInfoDTO diagnoseInfoDTO);
	
	/**
	 * 获取电子处方明细
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */
	public void getErxDetail(DiagnoseInfoDTO diagnoseInfoDTO,String ake1id);
	
	
	/**
	 * 试算收费前获取就医登记号
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */

	public String getAaz217(DiagnoseInfoDTO diagnoseInfoDTO);
}
