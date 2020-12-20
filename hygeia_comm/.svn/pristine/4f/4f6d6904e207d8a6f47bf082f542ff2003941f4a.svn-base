package com.powersi.biz.medicare.hosp.service.db;

import java.util.List;

import com.powersi.biz.medicare.hosp.pojo.ExpertInfoDTO;
import com.powersi.biz.medicare.hosp.pojo.TreatmentDTO;

/**
 *  专家维护，db服务接口
 * @author Administrator
 *
 */
public interface ExpertDBService {
	
	/**
	 * 查询专家信息
	 * @param id
	 * @return
	 */
	public List<ExpertInfoDTO> selectByPrimaryKey(ExpertInfoDTO expertInfoDto);
	
	public int saveOrUpdateExpertInfo(ExpertInfoDTO expertInfodto,List<TreatmentDTO> treatmentDtoList);
	
	public List<TreatmentDTO> seleselectByTreatment(ExpertInfoDTO expertInfoDto);
	public ExpertInfoDTO selectExpertOne(ExpertInfoDTO expertInfoDto);
	public List<TreatmentDTO> seleselectBySpblitBkc406(ExpertInfoDTO expertInfoDto);
}
