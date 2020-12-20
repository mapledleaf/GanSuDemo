package com.powersi.biz.maternity.treatmentregister.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.maternity.treatmentregister.pojo.TreatmentRegisterDTO;


public interface TreatmentRegisterService {
	/**
	 * 检查amc031的胎次
	 */
	public List checkAmc031(TreatmentRegisterDTO trDTO);
	/**
	 * insuranceInfQuery的概要说明： 参保人员信息显示
	 * 
	 * @param trDTO 的中文名称：生育登记信息DTO
	 * @return List
	 */
	public List insuranceInfQuery(TreatmentRegisterDTO trDTO);
	/**
	 * queryBankInfo的概要说明：查询银行信息
	 * 
	 * @param trDTO 的中文名称： 生育登记信息DTO
	 * @return List
	 */
	public Map<String, Object> queryBankInfo(TreatmentRegisterDTO trDTO,int first_row,int last_row );
	/**
	 * registerInfSave的概要说明： 登记信息保存
	 * 
	  * @param trDTO 的中文名称： 生育登记信息DTO
	 * @return String
	 */
	public String registerInfSave(TreatmentRegisterDTO trDTO);
	//判断连续缴费
	public boolean getLxjf(TreatmentRegisterDTO trDTO);
	/**
	 * checkPerson4ident的概要说明： 人员是否已进行登记操作查询
	 * 
	 * @param trDTO 的中文名称： 生育登记信息DTO
	 * @return List
	 */
	public List checkPerson4ident(TreatmentRegisterDTO trDTO);
	
	/**
	 * apprCheck的概要说明： 人员是否进行过认定操作
	 * 
	 * @param trDTO 的中文名称： 生育登记信息DTO
	 * @return List
	 */
	public String apprCheck(TreatmentRegisterDTO trDTO);
	/**
	 * showRegisterInf的概要说明： 登记人员信息查询
	 * 
	 * @param trDTO 的中文名称： 生育登记信息DTO
	 * @return List
	 */
	public List showRegisterInf(TreatmentRegisterDTO trDTO);
	
	/**
	 * changeRegInf的概要说明： 登记人员修改信息显示
	 * 
	 * @param trDTO 的中文名称： 生育登记信息DTO
	 * @return List
	 */
	public List changeRegInf(TreatmentRegisterDTO trDTO);
	
	/**
	 * regInfUpdate的概要说明： 保存更新修改信息
	 * 
	  * @param trDTO 的中文名称： 生育登记信息DTO
	 * @return String
	 */
	
	public String regInfUpdate(TreatmentRegisterDTO trDTO);
	/**
	 * registerInfDelete的概要说明： 删除登记信息
	 * 
	  * @param trDTO 的中文名称： 生育登记信息DTO
	 * @return String
	 */
	
	public String registerInfDelete(TreatmentRegisterDTO trDTO);
	
	/**
	 * apprCheckaaa075的概要说明： 人员是重复登记
	 * 
	 * @param trDTO 的中文名称： 生育登记信息DTO
	 * @return List
	 */
	public List apprCheckaaa075(TreatmentRegisterDTO trDTO);
	/**
	 * queryRegisterInf的概要说明： 登记人员信息查询
	 * 
	 * @param trDTO 的中文名称： 生育登记信息DTO
	 * @return List
	 */
	public Map<String, Object> queryRegisterInf(TreatmentRegisterDTO trDTO,int first_row,int last_row );
	
	/**
	 * queryHistoryTreatment的概要说明： 历史待遇查询
	 * 
	 * @param trDTO 的中文名称：生育登记信息DTO
	 * @return List
	 */
	
	public List queryHistoryTreatment(TreatmentRegisterDTO trDTO);

	
	/**
	 * 人社通查询登记信息
	 * @param trDTO
	 * @return
	 */
	public List queryRegisterInfo(TreatmentRegisterDTO trDTO);
	
	/**
	 * 删除备案
	 * @param trDTO
	 * @return
	 */
	public int updateMc01(TreatmentRegisterDTO trDTO);
	/**
	 * 判断分娩日期往前10个月是否有连续缴费
	 * @param trDTO
	 * @return
	 */
	public boolean checkAmc020(TreatmentRegisterDTO trDTO);
	/**
	 * 判断是否是退休人员
	 * @param trDTO
	 * @return
	 */
	public boolean checkAae127_tx(TreatmentRegisterDTO trDTO);
	
	/**
	 * 末次月经时间上月是否缴费
	 * @param trDTO
	 * @throws HygeiaException
	 */
	public void checkAmc019(TreatmentRegisterDTO trDTO);
	/**
	 * 获取身份证复印件
	 * @param trDTO
	 * @return
	 */
	public List getAmc292Img(TreatmentRegisterDTO trDTO);
	/**
	 * registerInfSave的概要说明： 检查是否已经有银行账号
	 * 
	  * @param trDTO 的中文名称： 生育登记信息DTO
	 * @return String
	 */
	public List checkBankInfo(TreatmentRegisterDTO trDTO);
}
