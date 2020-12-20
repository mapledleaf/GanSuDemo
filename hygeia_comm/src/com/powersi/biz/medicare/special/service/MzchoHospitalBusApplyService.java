package com.powersi.biz.medicare.special.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.special.pojo.MediSpecDTO;
import com.powersi.biz.medicare.special.pojo.MediSpec_ZH_DTO;

public interface MzchoHospitalBusApplyService {

	/**
	 * 查询是否做了产前检查选点
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */
	public int queryCJ(DiagnoseInfoDTO diagnoseInfoDTO);

	/**
	 * 取消病种信息
	 * 
	 * @param mediSpecZHDto
	 * @return
	 */
	public int cancelDiseaseInfo(MediSpec_ZH_DTO mediSpecZHDto);

	/**
	 * 查询病种认定信息
	 * 
	 * @param mediSpecZHDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryDiseaseInfo(MediSpec_ZH_DTO mediSpecZHDto);

	/**
	 * 查询鉴定信息
	 * 
	 * @param mediSpecZHDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryIdentifyInfo(MediSpec_ZH_DTO mediSpecZHDto);

	/**
	 * 保存病种信息
	 * 
	 * @param mediSpecZHDto
	 * @return
	 */
	public int saveDiseaseInfoSave(MediSpec_ZH_DTO mediSpecZHDto);

	/**
	 * 门诊选点人员信息查询
	 * 
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map queryMzchoPersonInfo(MediSpecDTO mediSpecDto);

	/**
	 * 校验门诊选点信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	public int verifyApply(MediSpecDTO mediSpecDto);

	/**
	 * 保存门诊选点信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	public int saveSqInfo(MediSpecDTO mediSpecDto);

	/**
	 * 修改门诊选点信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	public int updateSqInfo(MediSpecDTO mediSpecDto);

	/**
	 * 获取门诊选点信息列表
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAuditPerInfoList(MediSpecDTO mediSpecDto);

	/**
	 * 查询门诊选点待修改人员列表
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getModifyPerInfoList(MediSpecDTO mediSpecDto);

	/**
	 * 单击行,获取选点人员详细信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	public MediSpecDTO queryMzChoBsPersonInfo(MediSpecDTO mediSpecDto);

	/**
	 * 删除门诊选点申请信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	public int deleteMzchoHospitalInfo(MediSpecDTO mediSpecDto);

	/**
	 * 查门诊登记表
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getClinicApplyHosp(MediSpecDTO mediSpecDto);

	/**
	 * 特大病种疾病费用补录保存
	 * 
	 * @author yangmj 2018年1月11日 上午11:24:24
	 * @return Map
	 */
	@SuppressWarnings("rawtypes")
	public int saveDiseaseCostComps(MediSpec_ZH_DTO mediSpecDto, List<Map> details, List<Map> detailsDel);

	/**
	 * 特大病种疾病费用补录信息查询 2018年5月17日
	 * 
	 * @return Map
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryDiseaseCostComps(MediSpec_ZH_DTO mediSpecDto);

	/**
	 * 查询当前参保人所有申请的记录
	 * 
	 * @author yangmj 2018年1月11日 下午4:02:55
	 * @param mediSpecDto
	 * @return Map
	 */
	@SuppressWarnings("rawtypes")
	public Map getPersonCostComps(MediSpec_ZH_DTO mediSpecDto);

	/**
	 * 收费获取重大疾病以及药品信息
	 * 
	 * @author yangmj 2018年1月15日 上午10:29:09
	 * @param inHospitalDTO
	 * @return List<Map>
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getKc79(InHospitalDTO inHospitalDTO);

	/**
	 * 门诊改点时获取原申请信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	public MediSpecDTO queryMzBusDetailInfo(MediSpecDTO mediSpecDto);

	/**
	 * 修改门诊定点信息
	 * 
	 * @param mediSpecDto
	 * @return
	 */
	public int updateClinicBus(MediSpecDTO mediSpecDto);

	/**
	 * 查询当前人是否存在肿瘤病种信息
	 * 
	 * @param mediSpecZHDto
	 * @return
	 */
	public int queryZLDiseaseInfo(MediSpec_ZH_DTO mediSpecZHDto);

}
