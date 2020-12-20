package com.powersi.biz.medicare.special.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.special.pojo.TwoDisThreeMasterTeamDTO;
import com.powersi.biz.medicare.special.pojo.MediSpecDTO;

/**
 * 两病三师团队备案vs自费补偿申请
 * 
 * @author Administrator
 *
 */
public interface TeamVsSelfPayApplyService {

	/**
	 * 两病三师团队备案申请信息处理（增、删、改）
	 * 
	 * @param verify_flag
	 * @param data
	 * @param dto
	 * @return int 返回类型
	 */
	public int dealwithTeamApply(String verify_flag, List<Map<String, Object>> data, TwoDisThreeMasterTeamDTO dto);

	/**
	 * 获取两病三师团队备案申请信息
	 * 
	 * @param dto
	 * @return List<Map> 返回类型
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryTeamApply(TwoDisThreeMasterTeamDTO dto);

	/**
	 * 自费补偿申请获取人员信息 校验能否申请
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getPersonByPaysCompensation(MediSpecDTO mediSpecDto);

	/**
	 * 保存自费补偿申请
	 * 
	 * @return
	 */
	public int savePaysCompensation(MediSpecDTO mediSpecDto);

	/**
	 * 查询自费补偿申请信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryPaysCompensationList(MediSpecDTO mediSpecDto);

	/**
	 * 删除自费补偿申请
	 * 
	 * @return
	 */
	public int delPaysCompensation(String akb020, List<Map<String, Object>> detail);

	/**
	 * 修改自费补偿申请
	 * 
	 * @return
	 */
	public int modifyPaysCompensation(MediSpecDTO mediSpecDto);

	/**
	 * 自费补偿申请获取单条明细
	 * 
	 * @return
	 */
	public MediSpecDTO getDetailByAaz267(MediSpecDTO mediSpecDto);

}
