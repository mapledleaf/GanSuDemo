package com.powersi.biz.medicare.diagnose.service;

import java.util.List;

import com.powersi.biz.medicare.comm.pojo.Kc90DTO;
import com.powersi.biz.medicare.diagnose.pojo.BizsceneDTO;
import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.diagnose.pojo.FeeInfoDTO;
import com.powersi.biz.medicare.diagnose.pojo.PayInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.FeeStatDTO;
import com.powersi.biz.medicare.query.dto.BizOrder;

/**
 * 
 * @ClassName: DiagnoseSaveDAO
 * @Description: 门诊业务
 * @author: tangmin
 * @date: 2018年7月23日 下午4:13:28
 */
public interface DiagnoseSaveDAO {

	/**
	 * 查询费用支付信息
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getDiagnosePayMoney(DiagnoseInfoDTO diagnoseInfoDTO);

	/**
	 * 退费时送入对应就诊序列号，检查是否存在对应的退费明细
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */
	public int getDiagnoseFeeAaz213(DiagnoseInfoDTO diagnoseInfoDTO);

	/**
	 * 加载非退费的费用明细
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getDiagnoseFee(DiagnoseInfoDTO diagnoseInfoDTO);

	/**
	 * 加载基金信息
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getDiagnosePay(DiagnoseInfoDTO diagnoseInfoDTO);

	/**
	 * 门诊收费
	 * 
	 * @param diagnoseInfoDTO
	 * @param bizOrder 业务交易信息
	 * @return
	 */
	public int diagnoseChargeFee(DiagnoseInfoDTO diagnoseInfoDTO, List<PayInfoDTO> payInfoDTO,
			List<FeeInfoDTO> feeInfoDTO, List<BizsceneDTO> bizsceneInfoDTO, List<FeeStatDTO> feestatInfoDTO, BizOrder bizOrder);

	/**
	 * 购药收费
	 * 
	 * @param diagnoseInfoDTO
	 * @author
	 */
	public int buyDrugChargeFee(DiagnoseInfoDTO diagnoseInfoDTO, List<PayInfoDTO> payInfoDTO,
			List<FeeInfoDTO> feeInfoDTO, List<BizsceneDTO> bizsceneInfoDTO, List<FeeStatDTO> feestatInfoDTO);

	@SuppressWarnings("rawtypes")
	public List getDiagnosePayByBka001(DiagnoseInfoDTO diagnoseInfoDTO);

	@SuppressWarnings("rawtypes")
	public List getDiagnoseFeeByUpload(DiagnoseInfoDTO diagnoseInfoDTO);

	// 退费时判断是否整批次退费
	public int getDiagnoseFeeCount(DiagnoseInfoDTO diagnoseInfoDTO);

	/**
	 * 自动化测试接口
	 * 
	 * @param diagnoseInfoDTO
	 */
	public void deleteKcd1_akb020(DiagnoseInfoDTO diagnoseInfoDTO);

	/**
	 * 图片采集(临时)
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */
	public int insertKc90(Kc90DTO kc90Dto);

	/**
	 * 图片采集
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */
	public int updateKc90(DiagnoseInfoDTO diagnoseInfoDTO);

	/**
	 * 插入副诊断
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */
	public int insertKcg4(DiagnoseInfoDTO diagnoseInfoDTO);

	/**
	 * 云药店/云诊所，查询已结算的支付信息
	 * 
	 * @param bizOrder
	 * @param akb020
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getPayinfoList(String bizOrder, String akb020);

}
