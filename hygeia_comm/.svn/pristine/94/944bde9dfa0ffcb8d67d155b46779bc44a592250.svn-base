package com.powersi.biz.medicare.special.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.special.pojo.MediSpecDTO;

public interface MtmmSpecialApplyService {

	/**
	 * 保存特殊业务基本信息
	 * 
	 * @param dto
	 * @return
	 */
	public int saveSpeBsInfo(MediSpecDTO mediSpecDto);//

	/**
	 * 校验门特业务是否可以在该医院申请或者就医
	 * 
	 * @param HospitalId
	 * @param ApplyContent
	 * @return
	 */
	public int verifyHospTreat(MediSpecDTO mediSpecDto);//

	/**
	 * 获取人员信息(包括人员类别)
	 * 
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryMediPersonInfo(MediSpecDTO mediSpecDto);//

	/**
	 * 通过参数获取门特门慢申请信息列表
	 * 
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getSPInfoList(MediSpecDTO mediSpecDto);

	@SuppressWarnings("rawtypes")
	public List queryPerson(MediSpecDTO dto);

	/**
	 * 获取特殊业务可用疾病信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getBizInfo(MediSpecDTO mediSpecDto);

	/**
	 * 获取同类申请信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map verifySPApply(MediSpecDTO mediSpecDto);

	/**
	 * 获取特殊业务申请类型
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getApplyBusiType(MediSpecDTO mediSpecDto);

	/**
	 * 审核待审核特殊业务申请列表
	 * 
	 * @param dto
	 */
	public int auditInfo(List<Map<String, Object>> mAuditInfo);

	/**
	 * 获取待修改人员的列表
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getSpeBsInfoForEdit(MediSpecDTO mediSpecDto);

	/**
	 * 单击行,查询待修改人员详细信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	public MediSpecDTO querySpeBsInfoForEdit(MediSpecDTO mediSpecDto);

	/**
	 * 删除门特申请信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	public int deleteSpeBsInfo(MediSpecDTO mediSpecDto);

	/**
	 * 保存修改的申请信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	public int saveModiSpeBsInfo(MediSpecDTO mediSpecDto);

	/**
	 * 获取门特（门慢）特殊业务申请信息（打印）
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getSPInfoListForPrint(MediSpecDTO mediSpecDto);

	@SuppressWarnings("rawtypes")
	public List<Map> getSpecialTwoDisInfo(MediSpecDTO mediSpecDto);

	/**
	 * 获取人员选点类型
	 * 
	 * @author yangmj 2017年12月7日 下午4:21:19
	 * @param mediSpecDto
	 * @return Map
	 */
	@SuppressWarnings("rawtypes")
	public Map getPersonSignType(MediSpecDTO mediSpecDto);

	/**
	 * 门慢选点申请
	 * 
	 * @author yangmj 2017年12月8日 下午1:58:41
	 * @param mediSpecDto
	 * @return int
	 */
	public int saveSpChooseHosp(MediSpecDTO mediSpecDto);

	/**
	 * 查询医院门慢选点参保信息
	 * 
	 * @author yangmj 2017年12月8日 下午4:12:13
	 * @param mediSpecDto
	 * @return List<Map>
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryPersonChooseHosp(MediSpecDTO mediSpecDto);

}
