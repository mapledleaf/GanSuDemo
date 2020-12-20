package com.powersi.biz.medicare.inhospital.service.api.mcce;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.pojo.MediDTO;
import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.pojo.Kcg4DTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120102Service;

/**
 * 
 * bizh120102 入院登记后取业务信息
 * 
 * @author 刘勇
 *
 */
public interface MCCEbizh120102Service extends MCCEsbService {

	/**
	 * 
	 */
	public String serviceid = mcce_ + MCCHbizh120102Service.function_id;
	/**
	 * 申请回退获取业务信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO queryBizInfo(InHospitalDTO inHospitalDTO);

	/**
	 * 入院登记后取业务信息
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO queryAaz217(InHospitalDTO inHospitalDTO);

	/**
	 * 住院业务查询全集
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public List<InHospitalDTO> queryInHospitalaaz217s(InHospitalDTO inHospitalDTO);

	/**
	 * 查询结算单数据
	 * 
	 * @param mediDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, List> querySettlementReportData(MediDTO mediDTO);

	/**
	 * 住院一级结算单
	 * 
	 * @param mediDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, List<Map>> loadSettlementReportDataInHospFirst(MediDTO mediDTO);

	/**
	 * 住院二级结算单
	 * 
	 * @param mediDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, List<Map>> loadSettlementReportDataInHospSecond(MediDTO mediDTO);
    
	/**
	 * 住院三级结算单
	 * 
	 * @param mediDTO
	 * @return
	 */
	public Map<String, List<Map>> loadSettlementReportDataInHospThree(MediDTO mediDTO) ;
	/**
	 * 门诊结算单
	 * 
	 * @param mediDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, List<Map>> loadSettlementReportDataClinic(MediDTO mediDTO);

	/**
	 * 住院结算单信息
	 * 
	 * @param mediDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, List<Map>> loadSettlementReportDataInHospKsydMove(MediDTO mediDTO);

	/**
	 * 跨省异地住院算单
	 * 
	 * @param mediDTO
	 * @return
	 * @author zhos
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, List<Map>> loadSettlementReportDataInHospKsyd(MediDTO mediDTO);

	/**
	 * 省内异地住院算单
	 * 
	 * @param mediDTO
	 * @return
	 * @author zhos
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, List<Map>> loadSettlementReportDataInHospSnyd(MediDTO mediDTO);

	/**
	 * 查询住院病人信息病案首页，住院总费用：自费金额
	 * 
	 * @author yangmj 2017年8月30日 下午5:39:37
	 * @param mediDTO
	 * @return int
	 */
	public double selectSumAae019(MediDTO mediDTO);
	
	/**
	 * 判断中心kc68是否存在这笔业务数据
	 * @param inHospitalDTO
	 * @return
	 */
	public void isExistBizInfo(InHospitalDTO inHospitalDTO);
	
	/**
	 * 将kc68数据置为无效
	 * @param inHospitalDTO
	 * @return
	 */
	public int updateFallBackApply(InHospitalDTO inHospitalDTO);
	
	/**
	 * 若参保人还没有进行录入病案首页，如果选了病案首页回退申请，则需要提示“该人员没有录入病案首页，不能进行病案首页回退”
	 * @param inHospitalDTO
	 * @return
	 */
	public void checkBiz(InHospitalDTO inHospitalDTO);

	/**
	 * 根据就医登记号aaz217、akb020 查找有效的多诊断信息
	 * @param aaz217
	 * @param akb020
	 * @return
	 */
	public List<Kcg4DTO> loadDiagnoseInfosByAaz217(String aaz217, String akb020);
	
	//TS19112700299   差别化待遇支付从中心kcd1拿数据 add by zyx 2019/12/04
	public String  selectAka241(InHospitalDTO inHospitalDTO);

	//TS19112700299   系统自动弹出本次刷卡前一个月消费记录 add by ayx 2019/12/04
	public List<Map>  selectChargeList(DiagnoseInfoDTO diagnoseInfoDTO);

}
