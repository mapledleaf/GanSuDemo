package com.powersi.biz.medicare.inhospital.service.api.mcce;

import java.io.Serializable;
import java.util.List;

import com.powersi.biz.medicare.comm.pojo.MediDTO;
import com.powersi.biz.medicare.inhospital.pojo.BizDTO;
import com.powersi.biz.medicare.inhospital.pojo.FeeDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 
 * 医保结算云服务总线API入口
 * 
 * @author 刘勇
 *
 */
public interface MCCEsbService extends Serializable {

	/**
	 * 材料费
	 */
	public String aka063_18 = "18";

	/**
	 * 是否异地就医
	 */
	public String CheckEcdemicMedicalTreatment = "CheckEcdemicMedicalTreatment";

	/**
	 * 是否需要住院审核
	 */
	public String CheckHospitalRegisterAudit = "CheckHospitalRegisterAudit";

	/**
	 * 是否需要写社保卡
	 */
	public String CheckDoDebit = "CheckDoDebit";

	/**
	 * 持卡就诊
	 */
	public String CheckReadCard = "CheckReadCard";

	/**
	 * 判断鉴权结果
	 */
	public String CheckAuthenticationSSCard = "CheckAuthenticationSSCard";

	/**
	 * 床位费限额前缀
	 */
	public String Bed_ = "Bed_";

	public String oper_distric = "oper_distric";

	/**
	 * 业务支付表
	 */
	public String kcd7 = "kcd7";

	/**
	 * 分类标志(101:现金全自费,102:现金部分自费,103:个人帐户支付,109:冻结状态的全支付,999:历年帐户不够支付,000:
	 * 除上面的情况)
	 */
	public String bka432_000 = "000";

	/**
	 * 分类标志(101:现金全自费,102:现金部分自费,103:个人帐户支付,109:冻结状态的全支付,999:历年帐户不够支付,000:
	 * 除上面的情况)
	 */
	public String bka432_101 = "101";

	/**
	 * 分类标志(101:现金全自费,102:现金部分自费,103:个人帐户支付,109:冻结状态的全支付,999:历年帐户不够支付,000:
	 * 除上面的情况)
	 */
	public String bka432_102 = "102";
	
	/**
	 * 单价超两千材料费（起付线）
	 */
	public String bka432_103 = "103";
	
	/**
	 * 单价超两千材料费（基本医疗段）
	 */
	public String bka432_104 = "104";
	
	/**
	 * 单价超两千材料费（重大疾病段）
	 */
	public String bka432_105 = "105";
	
	/**
	 * 单价超两千材料费（超限额段）
	 */
	public String bka432_106 = "106";
	

	/**
	 * 保存标识1已保存
	 */
	public String bka892_1 = "1";

	/**
	 * 保存标识0未保存
	 */
	public String bka892_0 = "0";

	/**
	 * 结算标识(已结算)
	 */
	public String bka891_1 = "1";

	/**
	 * 结算标识(未结算)
	 */
	public String bka891_0 = "0";

	/**
	 * 完成原因(3 取消业务终结)
	 */
	public String bka048_3 = "3";

	/**
	 * 完成原因(2 业务信息异动 )
	 */
	public String bka048_2 = "2";

	/**
	 * 完成原因(1 取消业务登记 )
	 */
	public String bka048_1 = "1";

	/**
	 * 完成原因(0 业务正常完成 )
	 */
	public String bka048_0 = "0";

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
	 * 床位占用标识(占用)
	 */
	public String bkc171_1 = "1";

	/**
	 * 床位占用标识(空闲)
	 */
	public String bkc171_0 = "0";

	/**
	 * 在院标识(已出院)
	 */
	public String bka039_1 = "1";

	/**
	 * 在院标识(还在院)
	 */
	public String bka039_0 = "0";

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
	 * 其他
	 */
	public String amc029_9 = "9";

	/**
	 * 生育
	 */
	public String amc050_1 = "1";

	/**
	 * 计划生育手术
	 */
	public String amc050_2 = "2";

	/**
	 * 生育合并计生手术
	 */
	public String amc050_3 = "3";

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
	 * 同步标识
	 */
	public String bka977_yes = "1";

	/**
	 * 同步标识
	 */
	public String bka977_no = "0";

	/**
	 * 珠海市
	 */
	public String aaa027_440400 = "440400";

	/**
	 * 清远市
	 */
	public String aaa027_441800 = "441800";

	/**
	 * 费用明细业务表
	 */
	public String kcd2 = "kcd2";

	/**
	 * 费用明细完成表
	 */
	public String kc22 = "kc22";

	/**
	 * 出院转归情况_死亡
	 */
	public String bkf004_05 = "05";

	public String mcce_ = "mcce_";

	public String queue_mcce_ = "queue_mcce_";

	/**
	 * 调试时候加权因子
	 */
	public int debug_cache_model = 2;

	/**
	 * 面向程序自用缓存时间
	 */
	public int forcommon_cache_time = 60 * 1 * debug_cache_model;

	/**
	 * 面向业务方面缓存时间(住院登记)
	 */
	public int foregister_cache_time = 60 * 6 * debug_cache_model;

	/**
	 * all
	 */
	public String all = "all";

	/**
	 * 工伤
	 */
	public String aae140_410 = "410";

	/**
	 * 生育
	 */
	public String aae140_510 = "510";

	/**
	 * 城镇职工
	 */
	public String aae140_310 = "310";

	/**
	 * 城乡居民
	 */
	public String aae140_391 = "391";

	/**
	 * 急诊留观
	 */
	public String aka130_17 = "17";

	/**
	 * 门特
	 */
	public String aka130_16 = "16";

	/**
	 * 家庭病床
	 */
	public String aka130_14 = "14";

	/**
	 * 门慢
	 */
	public String aka130_13 = "13";

	/**
	 * 住院
	 */
	public String aka130_12 = "12";

	/**
	 * 工伤住院
	 */
	public String aka130_42 = "42";

	/**
	 * 生育住院
	 */
	public String aka130_52 = "52";

	/**
	 * 省内异地住院
	 */
	public String aka130_72 = "72";

	/**
	 * 跨省异地住院
	 */
	public String aka130_82 = "82";

	/**
	 * 血型
	 */
	public String bkf001 = "bkf001";

	/**
	 * 入院方式
	 */
	public String bkf002 = "bkf002";

	/**
	 * 入院情况
	 */
	public String bkf003 = "bkf003";

	/**
	 * 出院转归情况
	 */
	public String bkf004 = "bkf004";

	/**
	 * 抢救次数
	 */
	public String bkf005 = "bkf005";

	/**
	 * 抢救成功次数
	 */
	public String bkf006 = "bkf006";
	/**
	 * 结算类别（中心报账）
	 */
	public static final String AKA030_1 = "1";

	/**
	 * 结算类别（医院上传）
	 */
	public static final String AKA030_2 = "2";

	/**
	 * 检索中心医院信息、基金状态等等等
	 */
	public String mccc_bizh120302 = "mccc_bizh120302";
	
	/**
	 * 校验写社保卡
	 * 
	 * @param inHospitalDTO
	 */
	public default void checkDoDebit(InHospitalDTO inHospitalDTO) {
		
	}

	/**
	 * 出院结算先试算拿到pcardinfo和ppayinfo
	 * 
	 * <pre>
	 * 1、输入参数pCardInfo，该参数用于传入卡的基本信息，依次为：卡识别码、卡号。各数据项之间以“|”分割，且最后一个数据项以“|”结尾；
	 * 2、输入参数pPayInfo，该参数用于传入消费相关信息，依次为：本次消费总金额(小于42949672.95的小数，小数点后保留两位)、个人账户交易金额和统筹基金支付金额相加的
	 *    总金额（小于42949672.95的小数，小数点后保留两位）、交易时间（格式为YYYYMMDDHHMMSS）。各数据项之间以“|”分割，且最后一个数据项以“|”结尾；
	 * </pre>
	 * 
	 * @param inHospitalDTO
	 * @param InHospitalDTOReturn
	 */
	public default void loadCardAndPayInfo(InHospitalDTO inHospitalDTO, InHospitalDTO InHospitalDTOReturn) {
		
	}

	/**
	 * 持卡就诊
	 * 
	 * @param inHospitalDTOTemp
	 * @return true需要读卡 false不需要读卡
	 */
	public default boolean checkReadCard(InHospitalDTO inHospitalDTOTemp) {
		return false;
	}

	/**
	 * 判断鉴权结果_有开关_一般在登记保存阶段调用
	 * 
	 * @param inHospitalDTOTemp
	 */
	public default void authenticationSSCard(InHospitalDTO inHospitalDTOTemp) {
		
	}

	/**
	 * 加载疾病名称
	 * 
	 * <pre>
	 * 1、bka026 入院疾病诊断；
	 * 2、bka031 出院疾病诊断；
	 * </pre>
	 * 
	 * @param inHospitalDTOReturn
	 */
	public default void loadDiseaseName(InHospitalDTO inHospitalDTOReturn) {
		
	}

	/**
	 * 加载医保医师姓名
	 * 
	 * @param inHospitalDTOReturn
	 */
	public default void loadBka503Name(InHospitalDTO inHospitalDTOReturn) {
		
	}

	/**
	 * 校验是否可以删除或者新增费用明细
	 * 
	 * @param inHospitalDTO
	 */
	public default void checkInputOrDeleteFees(InHospitalDTO inHospitalDTO) {
		
	}

	/**
	 * 
	 * @param inHospitalDTOReturn
	 */
	public default void convertDateFormate(InHospitalDTO inHospitalDTOReturn) {
		
	}

	/**
	 * 
	 * @param mediDTO
	 */
	public default void loadDiseaseNameThis(MediDTO mediDTO) {
		
	}

	/**
	 * 是否需要住院审核
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public default boolean isInHospitalRegisterAudit(InHospitalDTO inHospitalDTO) {
		return false;
	}

	/**
	 * 
	 * @param inHospitalDTO
	 */
	public default void loadHiddenData(InHospitalDTO inHospitalDTO) {
		
	}

	/**
	 * 在院状态判断
	 * 
	 * @param inHospitalDTO
	 * @param bizDTO
	 */
	public default void checkBizStatus(InHospitalDTO inHospitalDTO, BizDTO bizDTO) {
		
	}

	/**
	 * 是否异地就医
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public default boolean isEcdemicMedical(InHospitalDTO inHospitalDTO) {
		return false;
	}

	/**
	 * <pre>
	 * bka979_akc264_总费用
	 * </pre>
	 * 
	 * @param inHospitalDTO
	 * @param feeDTORows
	 */
	public default void loadEcdemicMedicalDataItmes(InHospitalDTO inHospitalDTO, List<FeeDTO> feeDTORows) {
		
	}

}
