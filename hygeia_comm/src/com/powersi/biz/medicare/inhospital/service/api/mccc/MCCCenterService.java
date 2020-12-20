package com.powersi.biz.medicare.inhospital.service.api.mccc;

import java.io.Serializable;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 
 * 医保结算云调中心服务接口
 * 
 * @author 刘勇
 *
 */
public interface MCCCenterService extends Serializable {

	public String oper_distric = "oper_distric";

	public String mccc_ = "mccc_";

	/**
	 * 个人基本信息结果集
	 */
	public String personinfo = "personinfo";

	public String GETPERSONINFOMESSAGEFAILURE = "获取人员基本信息失败";

	/**
	 * 省内异地费用明细上传，远程调用一次传输费用数据条数的最大值 add by zhos
	 */
	public int SNYD_UPLOADFEES_SIZE_FOR_ONCE = 200;

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
	 * 先自付政策50%自付
	 */
	public String A000_50 = "A000_50";

	/**
	 * 先自付政策30%自付
	 */
	public String A000_30 = "A000_30";

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
	public int debug_cache_model = 1;

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
	 * 是否异地就医
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public boolean isEcdemicMedical(InHospitalDTO inHospitalDTO);

}
