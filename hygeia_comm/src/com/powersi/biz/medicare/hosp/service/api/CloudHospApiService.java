package com.powersi.biz.medicare.hosp.service.api;

import java.util.List;

import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;

/**
 * 
 * @ClassName:  HospApiService   
 * @Description:   (医院维护信息)
 * @author: tangmin
 * @date:   2018年7月22日 下午2:23:12
 */
public interface CloudHospApiService {

	/**
	 * 
	 * @Title: queryHospInfo   
	 * @Description: TODO(查询科室信息)   
	 * @param: @param akb020
	 * @param: @param hospInfoDTO
	 * @param: @return      
	 * @return: List<HospInfoDTO>
	 */
	public List<HospInfoDTO> queryHospSection(HospInfoDTO hospInfoDTO,String akb020);
	
	/**
	 * 
	 * @Title: queryInpatientWard   
	 * @Description: TODO(查询病区信息)   
	 * @param: @param hospInfoDTO
	 * @param: @param akb020
	 * @param: @return      
	 * @return: List<HospInfoDTO>
	 */
	public List<HospInfoDTO> queryInpatientWard(HospInfoDTO hospInfoDTO,String akb020);
	
	/**
	 * 
	 * @Title: queryMedicareDoctor   
	 * @Description: TODO(查询医保医生信息)   
	 * @param: @param hospInfoDTO
	 * @param: @param akb20
	 * @param: @return      
	 * @return: List<HospInfoDTO>
	 */
	public List<HospInfoDTO> queryMedicareDoctor(HospInfoDTO hospInfoDTO,String akb20);
}
