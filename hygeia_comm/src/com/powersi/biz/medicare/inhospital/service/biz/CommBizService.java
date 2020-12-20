package com.powersi.biz.medicare.inhospital.service.biz;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.IMediCalc;
import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.diagnose.pojo.FeeInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.BizDTO;
import com.powersi.biz.medicare.inhospital.pojo.BizFinDTO;
import com.powersi.biz.medicare.inhospital.pojo.FeeDTO;
import com.powersi.biz.medicare.inhospital.pojo.FeeStatDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.pojo.PayDTO;
import com.powersi.biz.medicare.inhospital.pojo.SceneDTO;
import com.powersi.webservice.WebServiceRequestParam;

/**
 * 
 * 通用业务处理(住院业务调用)
 * 
 * @author 刘勇
 *
 */
public interface CommBizService extends Serializable {

	/**
	 * 场景阶段 业务结束
	 */
	public String bka438_3 = "3";

	/**
	 * 场景阶段业务结算
	 */
	public String bka438_2 = "2";

	/**
	 * 场景阶段业务开始
	 */
	public String bka438_1 = "1";

	/**
	 * 数据有效标识(无效)
	 */
	public String aae100_0 = "0";

	/**
	 * 数据有效标识(有效)
	 */
	public String aae100_1 = "1";

	/**
	 * 数据传输标识(已传输)
	 */
	public String bka044_1 = "1";

	/**
	 * 数据传输标识(未传输)
	 */
	public String bka044_0 = "0";

	/**
	 * 同步标识
	 */
	public String bka977_yes = "1";

	/**
	 * 同步标识
	 */
	public String bka977_no = "0";

	/**
	 * 省内异地就医住院
	 */
	public String aka130_72 = "72";

	/**
	 * 费用明细业务表
	 */
	public String kcd2 = "kcd2";

	/**
	 * 费用明细完成表
	 */
	public String kc22 = "kc22";

	/**
	 * 业务支付表
	 */
	public String kcd7 = "kcd7";

	/**
	 * 场景表
	 */
	public String kce4 = "kce4";

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
	 * 加载业务数据
	 * 
	 * @param imediCalc
	 * @return
	 */
	public BizDTO loadBizInfo(IMediCalc imediCalc);

	/**
	 * 加载费用数据
	 * 
	 * @param imediCalc
	 * @return
	 */
	public List<FeeDTO> loadFeeInfo(IMediCalc imediCalc);

	/**
	 * 加载场景数据
	 * 
	 * @param imediCalc
	 * @return
	 */
	public List<SceneDTO> loadSceneInfo(IMediCalc imediCalc);

	/**
	 * 
	 * @param imediCalc
	 * @return
	 */
	public List<PayDTO> loadPayDTOInfo(IMediCalc imediCalc);

	/**
	 * 
	 * @param imediCalc
	 * @return
	 */
	public List<FeeStatDTO> loadFeeStatDTOInfo(IMediCalc imediCalc);

	/**
	 * 加载静态要素
	 * 
	 * @param imediCalc
	 * @param bizDTO
	 */
	public void loadStaticFactor(IMediCalc imediCalc, BizDTO bizDTO);

	/**
	 * 二次数据装载
	 * 
	 * @param imediCalc
	 * @param bizDTO
	 * @param feeDTORows
	 */
	public void assembleMediCalcResult(IMediCalc imediCalc, BizDTO bizDTO, List<FeeDTO> feeDTORows);

	/**
	 * 二次数据装载_出院结算
	 * 
	 * @param imediCalc
	 * @param bizFinDTO
	 * @param feeDTORows
	 * @param sceneDTORows
	 */
	public void assembleMediCalcResult(IMediCalc imediCalc, BizFinDTO bizFinDTO, List<FeeDTO> feeDTORows,
			List<SceneDTO> sceneDTORows);

	/**
	 * 保存计算结果到数据库
	 * 
	 * @param imediCalc
	 * @param bizDTO
	 * @param feeDTORows
	 */
	public void saveMediCalcResult(IMediCalc imediCalc, BizDTO bizDTO, List<FeeDTO> feeDTORows);

	/**
	 * 保存计算结果到数据库_出院结算
	 * 
	 * @param imediCalc
	 * @param bizFinDTO
	 * @param feeDTORows
	 */
	public void saveMediCalcResult(IMediCalc imediCalc, BizFinDTO bizFinDTO, InHospitalDTO inHospitalDTO,
			List<FeeDTO> feeDTORows);

	/**
	 * 汇总待遇支付信息(面向前台界面)
	 * 
	 * <pre>
		医疗总费用（akc264） akc264 = bka831 + bka832 
		个人自付（bka831）   bka831 = akb067 + akb066 + bka821 + bka839
		医保支付（bka832）   bka832 = ake039 + ake035 + ake026 + ake029 + bka841 + bka842 + bka840 
		全自费费用（bka825）
		部分自费费用（bka826）
		起付线费用（aka151）
		超共付段费用个人自付（bka838）
		个人现金支付（akb067）
		个人账户支付（akb066）
		民政救助金支付（bka821）
		其他支付（bka839）
		医疗保险统筹基金支付（ake039）
		公务员医疗补助基金支付（ake035）
		企业补充医疗保险基金支付（ake026）
		大额医疗费用补助基金支付（ake029）
		单位支付（bka841）
		医院垫付（bka842）
		其他基金支付（bka840）
	 * </pre>
	 * 
	 * @param imediCalc
	 * @return
	 */
	public InHospitalDTO dealPay(IMediCalc imediCalc);

	/**
	 * 异地就医处理结算信息，页面展示
	 * 
	 * @param payInfoList
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public InHospitalDTO dealPay(List<Map> payInfoList);

	/**
	 * 门诊费用明细处理
	 * 
	 * @param bka893
	 * @param diagnoseInfoDTO
	 * @param alistFeeInfoDTO
	 * @param request
	 */
	public void preparedParamsForClinicApi(DiagnoseInfoDTO diagnoseInfoDTO, List<FeeInfoDTO> alistFeeInfoDTO,
			WebServiceRequestParam request);

	/**
	 * 校验金额是否为两位精度
	 * 
	 * @param ake006 医院药品项目名称
	 * @param ake005 医院药品项目编码
	 * @param bka058 金额
	 */
	public void checkAccuracy(String ake006, String ake005, String bka058);

	/**
	 * 对等校验
	 * 
	 * @param ake006 医院药品项目名称
	 * @param ake005 医院药品项目编码
	 * @param bka056 单价
	 * @param bka057 用量
	 * @param bka058 金额
	 */
	public void checkReciprocityParallel(String ake006, String ake005, String bka056, String bka057, String bka058);

	/**
	 * 对等校验
	 * 
	 * @param ake006 医院药品项目名称
	 * @param ake005 医院药品项目编码
	 * @param bka056 单价
	 * @param bka057 用量
	 * @param bka058 金额
	 */
	public void checkReciprocityParallelDouble(String ake006, String ake005, double bka056, double bka057,
			double bka058);

	/**
	 * 保存计算结果到跨年断帐记录表中
	 * 
	 * @param imediCalc
	 * @param year
	 */
	public void saveCalcResultBeforeMerge(IMediCalc imediCalc, String year);

	/**
	 * 不断帐情况下，保存支付信息到kc27中
	 * 
	 * @param imediCalc
	 * @param bizFinDTO
	 */
	public void assembleMediCalcResultPay(IMediCalc imediCalc, BizFinDTO bizFinDTO);

	/**
	 * 断帐情况下，分批次保存支付信息到kc27中
	 * 
	 * @param firstYearPayList
	 * @param secondYearPayList
	 * @param imediCalc
	 * @param bizFinDTO
	 */
	@SuppressWarnings("rawtypes")
	public void assembleMediCalcResultSplit(List<Map> firstYearPayList, List<Map> secondYearPayList,
			IMediCalc imediCalc, BizFinDTO bizFinDTO);

}
