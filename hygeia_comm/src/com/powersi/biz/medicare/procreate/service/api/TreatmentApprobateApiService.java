package com.powersi.biz.medicare.procreate.service.api;

import java.util.List;
import java.util.Map;
import com.powersi.biz.medicare.procreate.pojo.TreatmentApprobateDTO;

public interface TreatmentApprobateApiService {

	/**
	 * 获取人员 信息
	 * 
	 * @param akb020
	 * @param taDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getPersonInfo(String akb020, TreatmentApprobateDTO taDTO);

	/**
	 * 生育登记保存
	 * 
	 * @param akb020
	 * @param taDTO
	 * @return
	 */
	public String registerInfSave(String akb020, TreatmentApprobateDTO taDTO);

	/**
	 * 通过中心查询生育资格登记信息
	 * 
	 * @param taDTO
	 * @param first_row
	 * @param last_row
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map registerInfQuery(TreatmentApprobateDTO taDTO, int first_row, int last_row);

}
