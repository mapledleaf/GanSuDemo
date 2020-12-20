package com.powersi.biz.medicare.inhospital.service.api.mcce;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.KA06DTO;
import com.powersi.biz.medicare.comm.pojo.MediDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 调中心服务： bizh120302 检索中心医院信息、基金状态等等等
 * 
 * @author 刘勇
 *
 */
public interface MCCEbizh120302Service extends MCCEsbService {

	/**
	 * serviceid
	 */
	public String serviceid = mcce_ + "bizh120302";

	/**
	 * 检索中心医院信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO queryHospital(InHospitalDTO inHospitalDTO);

	/**
	 * 检索中心基金状态信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map queryInsuredFund(InHospitalDTO inHospitalDTO);

	/**
	 * 检索中心个人累计信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryCumulative(InHospitalDTO inHospitalDTO);

	/**
	 * 检索中心个人基本信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public List<InHospitalDTO> getPersoninfo(InHospitalDTO inHospitalDTO);

	/**
	 * 检索中心个人基本信息
	 * 
	 * @param mediDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getPersoninfoList(MediDTO mediDTO);

	/**
	 * 通过输入信息或读卡检索该用户信息以及最后一条门诊或住院信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO getPersonLastInhospInfo(InHospitalDTO inHospitalDTO);

	/**
	 * 保存转诊转院申请信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO saveReferralApplyInfo(InHospitalDTO inHospitalDTO);

	/**
	 * 通过输入开始日、结束日期、社会保障号、电脑号查询当前用户归属医院的所有转入或转出的申请信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public List<InHospitalDTO> getChangeHospApplyList(InHospitalDTO inHospitalDTO);

	/**
	 * 更新转诊转院信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO updateReferralApplyInfo(InHospitalDTO inHospitalDTO);

	/**
	 * 删除转诊转院申请信息_通过aaz267更新KC41.aae100为0
	 * 
	 * @return
	 */
	public InHospitalDTO deleteReferralApplyInfo(InHospitalDTO inHospitalDTO);
	
	
	/**
	 * 检索中心基金状态信息
	 * 给API接口使用
	 * @param inHospitalDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryInsuredFundApi(InHospitalDTO inHospitalDTO);
	
	/**
	 * 珠海转诊转院，判断医院是否为可转出
	 * @param hospCataDto
	 * @return
	 */
	public List<InHospitalDTO> queryInhospitalTransfer(InHospitalDTO inHospitalDTO);
	
	/**
	 * 珠海转诊转院，判断医院是否为可转入
	 * @param hospCataDto
	 * @return
	 */
	public List<InHospitalDTO> queryInhospitalTransferIn(InHospitalDTO inHospitalDTO);
	
	/**
	 * 检索中心基金状态信息
	 * @param inHospitalDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryInsuredFundList(InHospitalDTO inHospitalDTO);
	
	
}
