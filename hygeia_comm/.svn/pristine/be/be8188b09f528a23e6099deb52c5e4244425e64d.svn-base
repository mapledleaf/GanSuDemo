package com.powersi.biz.medicare.special.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.special.pojo.MediSpecDTO;

public interface ChangeHospitalBusApplyService {

	/**
	 * 保存转诊转院基本信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	public int saveChangeHospitalizedInfo(MediSpecDTO mediSpecDto);

	/**
	 * 查询转诊转院人员基本信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map queryChanHospPersonInfo(MediSpecDTO mediSpecDto);

	/**
	 * 查询医院信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryChangeHospitalChoose(MediSpecDTO mediSpecDto);

	/**
	 * 获取转院申请信息列表
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getChaPersInfoList(MediSpecDTO mediSpecDto);

	/**
	 * 确认通过
	 * 
	 * @param mAuditInfo
	 * @param audit_flag
	 * @param inhosp_opinion
	 * @param aaa027
	 * @return
	 */
	public int auditPassInfo(List<Map<String, Object>> mAuditInfo, String audit_flag, String user,
			String inhosp_opinion, String aaa027);

	/**
	 * 查询转院人员待修改信息列表
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getSpeBsInfoForEdit(MediSpecDTO mediSpecDto);

	/**
	 * 单击行,获取转院待修改人员详细信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	public MediSpecDTO querySpeBsInfoForEdit(MediSpecDTO mediSpecDto);

	/**
	 * 保存修改人员信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	public int saveModiSpeBsInfo(MediSpecDTO mediSpecDto);

	/**
	 * 删除修改人员信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	public int deleteSpeBsInfo(MediSpecDTO mediSpecDto);

}
