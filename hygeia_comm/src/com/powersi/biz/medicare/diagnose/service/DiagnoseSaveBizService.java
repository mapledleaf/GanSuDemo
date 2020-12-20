package com.powersi.biz.medicare.diagnose.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.diagnose.pojo.FeeInfoDTO;

/**
 * 
 * @ClassName: DiagnoseSaveBizService
 * @Description: 门诊试算收费
 * @author: tangmin
 * @date: 2018年7月23日 下午3:56:25
 */
public interface DiagnoseSaveBizService extends Serializable {

	/** 西药 **/
	public String AKA063_01 = "01";

	/** 中成药 **/
	public String AKA063_02 = "02";

	/** 中草药 **/
	public String AKA063_03 = "03";

	/** 常规检查 **/
	public String AKA063_04 = "04";

	/** CT **/
	public String AKA063_05 = "05";

	/** 核磁 **/
	public String AKA063_06 = "06";

	/** B超 **/
	public String AKA063_07 = "07";

	/** 治疗费 **/
	public String AKA063_08 = "08";

	/** 化验费 **/
	public String AKA063_09 = "09";

	/** 手术费 **/
	public String AKA063_10 = "10";

	/** 输氧费 **/
	public String AKA063_11 = "11";

	/** 放射费 **/
	public String AKA063_12 = "12";

	/** 输血费 **/
	public String AKA063_13 = "13";

	/** 注射费 **/
	public String AKA063_14 = "14";

	/** 透析费 **/
	public String AKA063_15 = "15";

	/** 化疗费 **/
	public String AKA063_16 = "16";

	/** 床位费 **/
	public String AKA063_17 = "17";

	/** 材料费 **/
	public String AKA063_18 = "18";

	/** 护理费 **/
	public String AKA063_19 = "19";

	/** 一般诊疗费 **/
	public String AKA063_20 = "20";

	/** 挂号费 **/
	public String AKA063_21 = "21";

	/** 麻醉费 **/
	public String AKA063_30 = "30";

	/** 其他费用 **/
	public String AKA063_99 = "99";

	/**
	 * 门慢获取人员信息缓存Key前缀
	 */
	public String MZMM_BIZC130101_13_ = "MZMM_BIZC130101_13_";

	/**
	 * 门诊获取人员信息缓存Key前缀
	 */
	public String MZMM_BIZC130101_11_ = "MZMM_BIZC130101_11_";

	/**
	 * 门诊门慢获取人员信息缓存时间
	 */
	public int MZMM_BIZC130101_CACHETIME = 720;

	/**
	 * 先自付政策100%自付
	 */
	public String A000_100 = "A000_100";

	/**
	 * 先自付政策95%自付
	 */
	public String A000_95 = "A000_95";

	/**
	 * 先自付政策90%自付
	 */
	public String A000_90 = "A000_90";

	/**
	 * 先自付政策85%自付
	 */
	public String A000_85 = "A000_85";

	/**
	 * 先自付政策80%自付
	 */
	public String A000_80 = "A000_80";

	/**
	 * 先自付政策75%自付
	 */
	public String A000_75 = "A000_75";

	/**
	 * 先自付政策70%自付
	 */
	public String A000_70 = "A000_70";

	/**
	 * 先自付政策65%自付
	 */
	public String A000_65 = "A000_65";

	/**
	 * 先自付政策60%自付
	 */
	public String A000_60 = "A000_60";

	/**
	 * 先自付政策55%自付
	 */
	public String A000_55 = "A000_55";

	/**
	 * 先自付政策50%自付
	 */
	public String A000_50 = "A000_50";

	/**
	 * 先自付政策45%自付
	 */
	public String A000_45 = "A000_45";

	/**
	 * 先自付政策40%自付
	 */
	public String A000_40 = "A000_40";

	/**
	 * 先自付政策35%自付
	 */
	public String A000_35 = "A000_35";

	/**
	 * 先自付政策30%自付
	 */
	public String A000_30 = "A000_30";

	/**
	 * 先自付政策25%自付
	 */
	public String A000_25 = "A000_25";

	/**
	 * 先自付政策20%自付
	 */
	public String A000_20 = "A000_20";

	/**
	 * 先自付政策15%自付
	 */
	public String A000_15 = "A000_15";

	/**
	 * 先自付政策10%自付
	 */
	public String A000_10 = "A000_10";

	/**
	 * 先自付政策5%自付
	 */
	public String A000_05 = "A000_05";

	/**
	 * 先自付政策0%自付（甲类）
	 */
	public String A000_00 = "A000_00";

	/**
	 * 门诊人次
	 */
	public final String MZ_RC = "MONITER_BIZ_MZ_RC";

	/**
	 * 门慢人次
	 */
	public final String MM_RC = "MONITER_BIZ_MM_RC";

	/**
	 * 购药人次
	 */
	public final String GY_RC = "MONITER_BIZ_GY_RC";

	/**
	 * 门诊总金额
	 */
	public final String MZ_ZJE = "MONITER_BIZ_MZ_ZJE";

	/**
	 * 门慢总金额
	 */
	public final String MM_ZJE = "MONITER_BIZ_MM_ZJE";

	/**
	 * 购药总金额
	 */
	public final String GY_ZJE = "MONITER_BIZ_GY_ZJE";

	/**
	 * 门诊报销总金额
	 */
	public final String MZ_BX_ZJE = "MONITER_BIZ_MZ_BX_ZJE";

	/**
	 * 门慢报销总金额
	 */
	public final String MM_BX_ZJE = "MONITER_BIZ_MM_BX_ZJE";

	/**
	 * 购药报销总金额
	 */
	public final String GY_BX_ZJE = "MONITER_BIZ_GY_BX_ZJE";

	/**
	 * 36种限价药品
	 */
	public final String MAP_HYGEIA_BASE_KAB8 = "MAP_HYGEIA_BASE_KAB8";

	/**
	 * 试算
	 * 
	 * @param diagnoseInfoDTO
	 * @param lstfeeInfoDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List diagnoseCalcSave(DiagnoseInfoDTO diagnoseInfoDTO, List<FeeInfoDTO> lstfeeInfoDTO);

	/**
	 * 收费或退费
	 * 
	 * @param diagnoseInfoDTO
	 * @param lstfeeInfoDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List diagnoseCalcEnPay(DiagnoseInfoDTO diagnoseInfoDTO, List<FeeInfoDTO> lstfeeInfoDTO);

	/**
	 * 门诊取人员信息
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getPersonInfo(DiagnoseInfoDTO diagnoseInfoDTO);

	/**
	 * 门慢取人员信息
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getPersonSpInfo(DiagnoseInfoDTO diagnoseInfoDTO);

	/**
	 * 获取取人员信息列表
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getPersonList(DiagnoseInfoDTO diagnoseInfoDTO);

	/**
	 * 移动支付退费获取费用信息
	 * 
	 * @param diagnoseInfoDTO
	 * @param alistFeeInfoDTORows
	 */
	public void loadRefundFeeInfoForYDZF(DiagnoseInfoDTO diagnoseInfoDTO, List<FeeInfoDTO> alistFeeInfoDTORows);

	/**
	 * 购药收费或退费
	 * 
	 * @param diagnoseInfoDTO
	 * @param lstfeeInfoDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List buyDrugCalcEnPay(DiagnoseInfoDTO diagnoseInfoDTO, List<FeeInfoDTO> lstfeeInfoDTO);

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
