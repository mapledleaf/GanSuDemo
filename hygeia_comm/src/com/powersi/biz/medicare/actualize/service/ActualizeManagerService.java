package com.powersi.biz.medicare.actualize.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.actualize.pojo.KFD1DTO;
import com.powersi.biz.medicare.actualize.pojo.KFD3DTO;
import com.powersi.biz.medicare.actualize.pojo.KFD4DTO;
import com.powersi.biz.medicare.actualize.pojo.KFD5DTO;
import com.powersi.biz.medicare.actualize.pojo.KFD6DTO;

public interface ActualizeManagerService {

	/**
	 * 查询实施计划总表
	 * @param kfd6
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryActualizePlan(KFD6DTO kfd6);
	
	/**
	 * 查询实施模板信息
	 * @param kfd1Dto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List  queryActualizeModelsInfo(KFD1DTO kfd1Dto);
	
	/**
	 * 保存实施模板信息
	 * @param kfd1Dto
	 * @return
	 */
	public int saveActualizeModel(KFD1DTO kfd1Dto);
	
	/**
	 * 获取全部医院信息
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryHospInfo(KFD6DTO kfd6Dto);
	
	/**
	 * 获取全部医院信息
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int applyActPlan(KFD6DTO kfd6Info,List<Map> kfd1List,List<Map> kfd2List);
	
	/**
	 * 获取各医院计划
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryHospPlan(KFD3DTO kfd3Dto);
	
	/**
	 * 获取医院计划详情
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryPlanDetails(KFD4DTO kfd4Dto);
	
	/**
	 * 保存医院计划明细的日志信息
	 * @param mpara
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int savePlanLog(List<Map> kfd5Info);
	
	/**
	 * 获取医院明细日志信息
	 * @param mpara
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryLogList(KFD5DTO kfd5);
	
	/**
	 * 获取参与计划的医院
	 * @param mpara
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryHospBybkf306(KFD3DTO kfd3);
	
	/**
	 * 获取计划中的模板
	 * @param mpara
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryPlanModel(KFD3DTO kfd3);
	/**
	 * 获取业务大类描述
	 * @param kfd1Dto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryBkf301(KFD1DTO kfd1Dto);
	
	/**
	 * 查询实施计划信息
	 * @param kfd1Dto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map>  queryActualize(KFD3DTO kfd3Dto,KFD6DTO kfd6Dto);
	
	/**
	 * 验收申请时查询计划明细
	 * @param kfd4Dto
	 * @return
	 */
	public int  queryActualizeInfo(KFD4DTO kfd4Dto);
	/**
	 * 保存申请信息
	 * @param kfd1Dto
	 * @return
	 */
	public int saveActualizeApply(KFD3DTO kfd3Dto);
	
	/**
	 * 后去申请表所需的信息
	 * @param kfd4Dto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryApplyReport(KFD4DTO kfd4Dto);
}
