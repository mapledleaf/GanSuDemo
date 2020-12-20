package com.powersi.biz.medicare.hosp.service.db;

import java.util.List;

import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;
/**
 * 
 * @ClassName:  CloudHospService   
 * @Description:   (医院信息维护)
 * @author: tangmin
 * @date:   2018年1月22日 下午2:41:33
 */
public interface CloudHospService {
	/**
	 * 
	 * @Title: queryHospSection   
	 * @Description: TODO(查询科室信息)   
	 * @param: @param hospInfoDTO
	 * @param: @param akb020
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
	 * @param: @param akb020
	 * @param: @return      
	 * @return: List<HospInfoDTO>
	 */
	public List<HospInfoDTO> queryMedicareDoctor(HospInfoDTO hospInfoDTO,String akb020);
}
