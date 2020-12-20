package com.powersi.biz.medicare.health.service.api;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.health.pojo.KCH2;
import com.powersi.biz.medicare.health.pojo.SetMealDTO;

public interface ExaminationApiService {

	/**
	 * 体检套餐查询
	 * @param setMealDTO
	 * @return
	 */
	public List<Map> querySetMeal(SetMealDTO setMealDTO);
	
	public List<Map> getPersonInfo(SetMealDTO setMealDTO);
	
	/**
	 * 体检登记保存
	 * @param setMealDTO
	 * @return
	 */
	public int identityRegister(SetMealDTO setMealDTO);
	
	/**
	 * 获取体检登记人员信息
	 * @param dto
	 * @return
	 */
	public List<SetMealDTO> getHealthRegisterInfo(SetMealDTO dto);

	/**
	 * 判断体检结算录入项目是否属于套餐内项目
	 * @param dto
	 * @return
	 */
	public int checkItemIsPackage(SetMealDTO dto);
	
	
	//TS19052200158 - 【问题修复】【体检费用结算】-界面功能逻辑与需求不相符合   20190527  daliang.long
	/**
	 * 体检费用保存
	 * @param dto
	 * @param FeeInfo
	 * @return
	 */
	public void saveFee(SetMealDTO dto,List<Map> FeeInfo);
	//TS19052200158 - 【问题修复】【体检费用结算】-界面功能逻辑与需求不相符合   20190527  daliang.long
	/**
	 * 体检费用试算
	 * @param dto
	 * @param FeeInfo
	 */
	public List<Map> tryCalcTreat(SetMealDTO dto);
	//TS19052200158 - 【问题修复】【体检费用结算】-界面功能逻辑与需求不相符合   20190527  daliang.long
	/**
	 * 体检费用结算
	 * @param dto
	 * @param FeeInfo
	 */
	public List<Map> settleMentFee(SetMealDTO dto);
	
	/**
	 * 根据体检中心项目编码获取匹配的医院结论信息
	 * @param dto
	 * @return
	 */
	public List<Map> chooseJielun(SetMealDTO dto);
	
	/**
	 * 获取体检结算信息
	 * @param dto
	 * @return
	 */
	public List<Map> findHealthInfo(SetMealDTO dto);
	
	/**
	 * 查询体检套餐内外信息
	 * @param dto
	 * @return
	 */
	public List<Map> queryCusionInfo(SetMealDTO dto);	
	
	/**
	 * 单击行查询费用信息(按统计类别)
	 * @param dto
	 * @return
	 */
	public List<Map> queryBizFeeInfo(SetMealDTO dto);
	
	/**
	 * 查询并打印结算清单
	 * @param dto
	 * @return
	 */
	public Map querySettlementInfo(SetMealDTO dto);
	
	
	/**
	 * 取消体检登记
	 * @param dto
	 * @return
	 */
	public int cancelRegister(SetMealDTO dto);
	
	/**
	 * 取消体检结算
	 * @param dto
	 * @return
	 */
	public int cancelSettlement(SetMealDTO dto);
	
	/**
	 * 根据aaz217获取费用信息
	 * @param dto
	 * @return
	 */
	public List<Map> getFeeInfo(SetMealDTO dto);
	
}
