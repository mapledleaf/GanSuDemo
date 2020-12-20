package com.powersi.biz.medicare.hosp.service.api;
import java.util.List;

import com.powersi.biz.medicare.hosp.pojo.ExpertInfoDTO;
import com.powersi.biz.medicare.hosp.pojo.TreatmentDTO;

/**
 * 专家库管理
 * @author felix_liu
 *
 */
public interface ExpertManagerService {
	
	/**
	 * 查询专家信息
	 * @param akb020
	 * @param ExpertInfoDTO
	 * @return
	 */
	public List<ExpertInfoDTO> selectByPrimaryKey(ExpertInfoDTO expertInfoDto);
	
	/***
	 * 保存专家信息
	 * @param ExpertInfoDTO
	 * @param TreatmentDTO
	 * @return
	 */
	public int saveOrUpdateExpert(ExpertInfoDTO expertInfoDto,List<TreatmentDTO> treatmentList);
	
	public List<TreatmentDTO> selectByTreatment(ExpertInfoDTO expertInfoDto);
	
	public ExpertInfoDTO selectExpertOne(ExpertInfoDTO expertInfoDto);
	
	public List<TreatmentDTO> seleselectBySpblitBkc406(ExpertInfoDTO expertInfoDto);
}
