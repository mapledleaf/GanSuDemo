package com.powersi.biz.medicare.health.service.db;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.health.pojo.SetMealDTO;

public interface ExaminationService {
	public String table_name = "kch2";
	
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
	 * 保存登记kch4费用信息表
	 * @param map
	 * @return
	 */
	public int saveFeeInfo(List<Map> map);

	/**
	 * 保存费用支付信息表kch5
	 * @param map
	 * @return
	 */
	public int healthFundSave(List<Map> map);
	
	/**
	 * 修改业务状态
	 * @return
	 */
	public int updateHealthRegisterStatus(SetMealDTO dto);
	
    /**
     * 保存体检结论项目信息
     * @return
     */
    public int saveCusionInfo(List<Map> listCusion,SetMealDTO dto);
    
    /**
	 * 获取体检结算信息
	 * @param dto
	 * @return
	 */
	public List<SetMealDTO> findHealthInfo(SetMealDTO dto);
	
	/**
	 * 获取体检支付信息
	 * @param dto
	 * @return
	 */
	public List<SetMealDTO> findHealthMoney(SetMealDTO dto);
    /**
     * 获取套餐内外体检项目信息
     * @param dto
     * @return
     */
	public List<Map> queryCusionInfo(SetMealDTO dto);
	
	/**
	 * 双击行查询费用信息(按统计类别)
	 * @param dto
	 * @return
	 */
	public List<Map> queryBizFeeInfo(SetMealDTO dto);
	
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
	 * 根据体检登记号获取aaz217
	 * @param dto
	 * @return
	 */
	public List<Map> getFeeInfo(SetMealDTO dto);
}
