package com.powersi.biz.medicare.catalog.service.api;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.catalog.pojo.HospCataDTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.hosp.pojo.HospBasicsDTO;
import com.powersi.biz.medicare.inhospital.pojo.Kad5DTO;

public interface HospElectpresService {

	/**
	 * 基准库目录 2017年11月6日 下午3:45:13
	 * 
	 * @param inHospitalDTO
	 * @return ListResult
	 */
	public ListResult queryHospitalDatumMatched(HospCataDTO hospCataDTO);

	/**
	 * 保存电子处方 2017年11月8日 上午9:15:23
	 * 
	 * @param kad5dto
	 * @param listKad5InfoDTORows
	 * @return List
	 */
	public String electPresSave(DiagnoseInfoDTO diagnoseInfoDTO, List<Kad5DTO> listKad5InfoDTORows);

	/**
	 * 电子处方查询 2017年11月9日 上午10:37:59
	 * 
	 * @param kad5dto
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public Map electPresQuery(Kad5DTO kad5dto);

	/**
	 * 电子处方明细查询
	 * 
	 * @author yangmj 2017年12月7日 下午5:08:41
	 * @param kad5dto
	 * @return Map
	 */
	@SuppressWarnings("rawtypes")
	public Map electPresDetailQuery(Kad5DTO kad5dto);

	/**
	 * 电子处方回退 2017年11月9日 下午6:51:38
	 * 
	 * @param kad5dto
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public String returnElect(List<Map> kad5Map);
	
	/**
	 * 获取医院基础信息
	 * @param basicsInfo
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryHospBasicsInfo(HospBasicsDTO basicsInfo);
	
	/**
	 * 修改医院基础信息
	 * @param basicsInfo
	 * @return int
	 */
	public int editHospBasics(HospBasicsDTO basicsInfo);

}
