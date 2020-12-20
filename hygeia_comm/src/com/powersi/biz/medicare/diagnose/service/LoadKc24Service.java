package com.powersi.biz.medicare.diagnose.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface LoadKc24Service extends Serializable {

	public static final String KBX = "000"; //进入报销金额
	public static final String ALL_SELF_PAY = "101"; //完全政策自付
	public static final String PART_SELF_PAY = "102"; //部分政策自付
	public static final String OVER_LIMIT_PRICE_PAY = "103"; //超限价自付
	public static final String QFX = "S01"; //起付线
	public static final String ZWZL = "S00"; //转外自理
	public static final String CGFD = "E001"; //超共付段
	public static final String CBFY = "E00"; //超标费用
	public static final String TC_SEG_1 = "C001"; //统筹一段
	public static final String TC_SEG_2 = "C002"; //统筹二段
	public static final String TC_SEG_3 = "C003"; //统筹三段
	public static final String DB_SEG_1 = "D001"; //大病一段
	public static final String DB_SEG_2 = "D002"; //大病二段
	public static final String DB_SEG_3 = "D002"; //大病三段
	public static final String DB_SEG_CX_1 = "G1D1"; //居民大病一段
	public static final String DB_SEG_CX_2 = "G1D2"; //居民大病二段
	public static final String DB_SEG_CX_3 = "G1D3"; //居民大病三段
	public static final String DB_SEG_CX_4 = "G1D4"; //居民大病四段
	public static final String CGFD_CX = "G1E0"; //居民大病补偿超共付段
	public static final String CJD_CX = "G1E1"; //居民大病补偿冲减段
	public static final String QFX_CX = "G1S1"; //居民大病补偿段起付线
	
	public static final String TC_FUND_CODE_CZ = "001"; //职工统筹基金
	public static final String TC_FUND_CODE_CX = "801"; //居民统筹基金
	public static final String DB_FUND_CODE_CZ = "201"; //职工大病基金
	public static final String DB_FUND_CODE_CX = "803"; //居民大病基金
	public static final String GWY_FUND_CODE = "301"; //公务员基金
	public static final String SY_FUND_CODE = "511"; //生育基金
	public static final String SY_DF_FUND_CODE = "996";//"510519"; //生育垫付基金
	public static final String QYBC_FUND_CODE = "306"; //企业补充基金
	public static final String MZ_FUND_CODE = "401"; //民政补助基金（医疗补偿金）
	public static final String YW_FUND_CODE = "390901"; //意外伤害基金 TODO 洁哥说医院段无此基金项
	public static final String JC_FUND_CODE = "204"; //军残基金
	public static final String LX_FUND_CODE = "202"; //离休基金
	public static final String YY_FUND_CODE = "996"; //医院垫付
	public static final String ZX_FUND_CODE = "999997"; //中心垫付 TODO 洁哥说医院段无此基金项
	public static final String THB_FUND_CODE = "701"; //特惠保
	public static final String YYJM_FUND_CODE = "702"; //医院减免
	public static final String JKFP_FUND_CODE = "703"; //健康扶贫（财政兜底）
	public static final String YLQX_FUND_CODE = "390707"; //医疗倾斜基金 TODO 洁哥说医院段无此基金项
	public static final String MZDBBC_FUND_CODE = "806"; //民政大病补充基金
	public static final String XJ_FUND_CODE = "999"; //现金支付
	public static final String GRZH_FUND_CODE = "003"; //个人账户支付
	public static final String JTZH_FUND_CODE = "005"; //家庭账户支付 
	public static final String OTHER_FUND_CODE = "202,306,390901,204,701,702,703,390707,806,401"; //其他基金
	public static final String HGFY_CODE_CX = "G1D1,G1D2,G1D3,G1D4,G1E0,G1S1"; //城乡居民合规费用段

	@SuppressWarnings("rawtypes")
	public List<Map> loadKc24Info(List kc21List, List kc22List, List kc27List, List kc28List);

}