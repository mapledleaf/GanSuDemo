package com.powersi.biz.medicare.comm.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.powersi.comm.mybatis.Page;

/**
 * 
 * 字段来自5张业务完成表
 * 
 * <pre>
 * 1、MediDTO里所有字段千万都不要写死默认值，默认值在service层处理；
 * 2、已定义的数据项与新增的存在冲突时候，请新增新的数据项，不要出现一个数据项存在多个定义；
 * </pre>
 * 
 * @author 刘勇
 *
 */
public class MediDTO extends Page implements IPOJO {

	private static final long serialVersionUID = 1L;
	private String bka870; // 珠海重大疾病ICD
	private String bka871; // 珠海重大疾病项目编码
	private String bka872; // 珠海中途结算-住院日期
	private String bka873; // 珠海中途结算-起付线累计
	private String bac938;// 免缴人员类别
	/**
	 * <pre>
	 	ac01.bac032	1	学龄前儿童
		ac01.bac032	2	中小学生
		ac01.bac032	3	大中专学生
		ac01.bac032	4	其他未成年人
		ac01.bac032	5	成年人
		ac01.bac032	6	老年人
		ac01.bac032	7	新生儿
	 * </pre>
	 */
	private String bac037;// 城居人员类别
	private String bke613;// 医院业务回退审核信息表_业务审核信息ID
	private String aae101;// 申请类别(1:结算回退申请；2：病案首页信息回退申请)
	private String bac096;// 是否可以享受财政补助待遇
	private String akc252;// 16 医保个人帐户余额
	private String yka116;// 16 起付线累计
	private String bac098;// 公务员标识1是0否
	private String aac020;// 公务员级别_000非公务员
	private String aka036;// 限制使用标志 "0"——"否" "1"——"是"
	private String bka333;// 个人基金冻结信息_是否要返回每个月详细信息（0：否；1：是）
	private String bka999;// 读写卡通用标识（系统内部使用）1、2、3
	private String bka444;// 冲正标识
	private String bkc155;// varchar(50) not null comment '医院科室表id',kac7
	private String bkc157;// varchar(50) comment '医院科室名称',
	private String bke509;// varchar(1) comment '是否医技科室',
	private String bkc152;// varchar(50) not null comment '医院病区表id',kac6
	private String bkc156;// varchar(10) comment '医院科室编码',
	private String bkc153;// varchar(8) not null comment '医院病区编码',
	private String bkc154;// varchar(50) comment '医院病区名称',
	private String bkc027;// varchar(30) comment '维护人',
	private String bkc028;// varchar(20) comment '维护人工号',
	private String bkc029;// datetime comment '维护时间',
	private String bae101;// varchar(30) comment '审核人姓名',
	private String aka068;// 跨省异地_定价上限金额
	private String akc268;// 跨省异地_超限价自付金额
	private String aaa113;// 交易类型(1正交易-1负交易)
	private String aka185;// 监控使用标志
	private String ykc009;// 病历号
	private String akc190;// 住院号(门诊号)
	private String aae004;// 联系人
	private String ykc700;// 就诊登记号
	private String aaz216;// 结算流水号
	private String otransid;// 原交易流水号
	private String seqno;// 顺序号
	private String aae314;// 成功标志
	private String aaa204;// 提示信息
	private String ykc705;// 对账类型
	private String ykc706;// 对账日期
	private String transid;// 交易流水号
	private String sign;// 数字签名
	private String akb026;// 医疗机构编号
	private String aac044; // 证件号码
	private String ake007;// 发生日期
	private String errorcode;// 错误代码
	private String errormsg;// 错误信息
	private String aab003;// 组织机构代码
	private String ake132;// 转院标志
	private String bkc144;// varchar(30) DEFAULT NULL COMMENT '异名库目录编码',
	private String bka700;// 灵活就业人员标识1、0
	private String bka600;// 转诊转院类型 1--转诊 2--转院
	private String aae189;// KC41.aae189复审意见
	private String aaz002;// KC41.aaz002业务日志ID
	private String aka084;// KC41.aka084针对特定就诊
	private String prseno;// KC41.prseno序列号
	private String aae014;// KC41.aae014复核(审批)人
	private String aae015;// KC41.aae015复核(审批)时
	private String bke021;// KC41.bke021初审人姓名
	private String bke022;// KC41.bke022初审时间
	private String bke024;// KC41.bke024初审意见
	private String bac001;// KC41.bac001公务员级别
	private String bke055;// KC41.bke055申请方式
	private String aab069;// KC41.aab069组织机构名称
	private String bkc016;// KC41.bkc016批次
	private String bkb085;// KC41.bkb085关联的申请序
	private String bke016;// KC41.bke016申请人电话
	private String bke042;// KC41.bke042申请理由
	private String bke810;// KC41.bke810是否扩大用药
	private String bpe001;// KC41.bpe001内控稽核状态
	private String bae100;// KC41.bae100经办人姓名
	private String bka143;// KC41.bka143复核人工号
	private String bka137;// KC41.bka137初审人工号
	private String bke047;// KC20.bke047 转出医院意见时间
	private String bke048;// KC20.bke048 转入医院签署意见时间
	private String bke049;// KC20.bke049 是否是自主转院或者是因病情需要转院
	private String bke050;// KC20.bke050 本次住院诊断
	private String bke052;// KC20.bke052 申请类型
	private String bke036;// KC20.bke036 转出医院医师姓名
	private String bke017;// KC20.bke017 申请医师
	private String bke018;// KC20.bke018 主任医师
	private String bke060;// KC20.bke060 原就诊医疗服务机构名称
	private String bke019;// KC20.bke019 主任意见时间
	private String bke020;// KC20.bke020 医师意见时间
	private String bke127;// KC20.bke127 病人意见
	private String bke128;// KC20.bke128 申请病人(或家属)
	private String bke129;// KC20.bke129 病人意见时间
	private String bke053;// KC20.bke053 转入医院签署意见人
	private String bke014;// KC20.bke014 科室电话
	private String bke046;// KC20.bke046 上次住院诊断
	private String bke010;// KC20.bke010 转入医院医务科意见
	private String bke009;// KC20.bke009 本次住院病情摘要
	private String bke008;// KC20.bke008 转出医院医务科意见
	private String bke007;// KC20.bke007 上次住院病情摘要
	private String ake017;// KC20.ake017 转往医疗服务机构编号
	private String ake015;// KC20.ake015 转院原因
	private String ake014;// KC20.ake014 转院日期
	private String akc172;// KC20.akc172 转往医院名称
	private String akb041;// KC20.akb041 原就诊医疗服务机构编号
	private String aka079;// KC20.aka079 独立结算标志
	private String aaz286;// KC20.aaz286 原就诊医疗机构ID
	private String aae190;// KC20.aae190 医嘱(主要诊疗方案)
	private String aae139;// KC20.aae139 异地标志
	private String bka900;// 免起付线标识1或0
	private String bka555;// 查询标识：1查询2保存
	private String bkb100;// 门诊选点申请类型
	private String bmc077;// 未就业配偶标志（1是 ，0否）
	private String bka666;// 程序加载标识_开测专用
	private String aac066;// 人员类别
	private String bka777;// 特殊标识
	private String bka893;// 结算标识0费用试算1结算收费
	private String bka899;// 转出医院级别
	private String aaz501;// 卡片序列号
	private String aaz502;// 卡状态0封存、1正常、2挂失、3应用锁定、9注销
	private String aaz503;// 发卡日期
	private String aaz007;// 卡基本信息id
	private String baz003;// 开户名
	private String bka966;// 生育待遇汇总
	private String aaa036;// 待遇项目
	private String aae129;// 待遇金额
	private String aae041;// 开始时间
	private String aae042;// 结束时间
	private String aae402;// 平均工资
	private String amc032;// 产假天数
	private String amc010;// 配偶姓名
	private String bmc011;// 配偶身份证号码
	private String bmc012;// 配偶地址
	private String aac043;// 证件类型
	private String bke579;// 生育门诊次数
	private String bke578;// 门特门慢申请列表_用逗号分隔(,)
	private String bke576;// 门慢申请列表_用逗号分隔(,)
	private String bke575;// 门特申请列表_用逗号分隔(,)
	private String aac158;// 人才标志
	private String aac157;// 人才类别
	private String bke550;// 卡识别码
	private String bke548;// 读卡返回
	private String bke549;// 写卡返回
	private String bke547;// TAC码(内部使用)
	private String bka848;// 门慢目录标识
	private String ic_reg_permit;// 持卡就诊登记许可号(已解密)(yyyymmddhhmmss-卡号-验证位)
	private String ic_pay_permit;// 持卡就诊结算许可号(已解密)(yyyymmddhhmmss-卡号-交易验证码(TAC)-验证位)
	private String function_id;// 医保交易号
	private String debug_model;// 调试模式1、脱机模式2、联调测试模式3、正式上线模式
	private String pcardinfo;// 依次为：卡识别码、卡号。各数据项之间以“|”分割，且最后一个数据项以“|”结尾
	private String ppayinfo;
	private String ic_permit_model;// 1、持卡就诊登记许可号验证2、持卡就诊结算许可号验证
	private String aaz500;// 银行卡（社保卡卡号）AZ01表
	private String baz021;// 金融账号AZ01表
	private String baz010;// 市民卡卡号AZ01表
	private String ic_bka100;// 社保卡号(读卡)
	private String ic_tac;// TAC码
	private String bac032;// 困难标志
	private String ic_permit;// 后续业务操作许可号
	private String session_id;//
	private String bka846;// 可报销基本药物总费用
	private String aad006;// 基金款项名称
	private String akc264;// 医疗总费用
	private String aka151;// 起付线费用
	private String akb067;// 个人现金支付
	private String akb066;// 个人账户支付
	private String ake039;// 医疗保险统筹基金支付
	private String ake035;// 公务员医疗补助基金支付
	private String ake026;// 企业补充医疗保险基金支付
	private String ake029;// 大额医疗费用补助基金支付
	private String bka821;// 民政救助金支付
	private String bka822;// 公费医疗金支付
	private String bka823;// 工伤基金支付
	private String bka824;// 生育基金支付
	private String bka825;// 全自费费用
	private String bka826;// 部分自费费用
	private String bka827;// 政策范围内费用（基本医疗费用）
	private String bka828;// 共付段费用
	private String bka829;// 基本医疗共付段费用
	private String bka830;// 大病医疗共付段费用
	private String bka831;// 个人自付
	private String bka832;// 医保支付
	private String bka833;// 全自费费用个人自付
	private String bka834;// 部分自费费用个人自付
	private String bka835;// 起付线费用个人自付
	private String bka836;// 基本医疗共付段费用个人自付
	private String bka837;// 大病医疗共付段费用个人自付
	private String bka838;// 超共付段费用个人自付
	private String bka839;// 其他支付
	private String bka840;// 其他基金支付
	private String bka841;// 单位支付
	private String bka842;// 医院垫付
	private String aae011;// 经办人
	private String bka814;// 基本医保统筹已支付
	private String bka815;// 补充保险自付累计
	private String bka816;// 补充保险已支付
	private String bka811;// 医保支付金额
	private String bka812;// 个人支付金额
	private String bka813;// 共付段基金支付比例
	private String bka800;// 费用项目
	private String bka801;// 床位费金额
	private String bka802;// 自费费用
	private String bka803;// 部分项目自付费用
	private String bka804;// 起付标准以下费用
	private String bka805;// 基本医疗统筹共付段费用
	private String bka806;// 重大疾病补助共付段费用
	private String bka807;// 超限额以上费用
	private String bka808;// 合计
	private String bka989;// 先行支付标识
	private String bka980;// reportTemplateID报表模板ID，一般存放在工程的report目录下，按业务分多级子目录，用点拼接，例如：medicare/hosp/xxx.xls
	private String bka981;// bizID业务唯一编号，默认请传输空，这个用于后续按照业务的唯一编号来查询报表，全局不允许重复
	private String bka982;// keepDays保留天数，0标识永久保留，超过配置的天数，生成的报表数据将被删除
	private String bka983;// report_comm报表生成说明，业务传递，例如：财务2015年度数据
	private String bka984;// user_name创建人
	private String bka985;// 生成报表数据的ID，后续通过这个ID来读取报表
	private String bka986;// 报表预留
	private String bka987;// 报表预留
	private String ykc021;// 人员类别 varchar2(2) N 参见代码定义YKC021
	private String ykc300;// 人群类别 varchar2(3) N 参见代码定义YKC300
	private String akc026;// 参加公务员医疗补助标志 varchar2(1) N 0 非公务员；1 公务员
	private String yka119;// 基本医疗本次支付限额 number(16,2) N
	private String yka121;// 大病医疗本次支付限额 number(16,2) N
	private String yka123;// 公务员本次支付限额 number(16,2) N
	private String ake092;// 本年度基本医疗保险统筹基金累计支付金额 number(16,2) N
							// 本年度基本医疗保险统筹基金为该参保患者累计支付的金额合计。
	private String yka437;// 大病医疗统筹累计 number(16,2) N 大病医疗统筹累计支 付（本年度）
	private String akc200;// 本年度住院次数 number(3) N
							// 参保患者在本年度内的第几次住院。就诊方式为住院时，填写本数据项。
	private String ykc667;// 二次返院审批标志 varchar2(3) Y 参见代码定义YKC667
	private String yzz014;// 异地就医备案号varchar2(20)异地就医备案业务标识Y+参保地统筹地市区编 号(6 位)＋
							// 日期（6 位YYMMDD）＋流水号 （7 位）如有备案需返回
	private String aka130_snyd;// 医疗类别 varchar2(2) N 参见代码定义AKA130
	private String bka979;// 总费用
	private String bka988;// 结算标识：模拟结算1、正式结算0
	private String aka120;// 疾病编码
	private String remote_funcid;// 调省内异地前置机功能号(暂时不启用)
	private String bka977;// 标识(通用)开关、同步与否 1、0
	private String bka918;// 住院业务费用明细同步保存标识(kc22)1同步0不同步
	private String bka919;// 住院业务费用明细同步保存标识(kcd2)1同步0不同步
	private String amc021;// 生育证号
	private String amc022;// 出生证号
	private String amc023;// 结婚证号
	private String amc050;// 生育业务类型
	private String amc029;// 生育手术类别
	private String amc031;// 胎次
	private String bka971;// modicount int default 1 not null COMMENT
							// '修改计数器,默认插入时为1，执行一次update需要加一',
	private String bka972;// packsign varchar(1) default '0' not null COMMENT
							// '传输打包标志 0未打包 1已打包 ',
	private String bka973;// moditime datetime default now() not null COMMENT
							// '数据最后修改时间 ',
	private String bka974;// packuuid varchar(64) COMMENT '最后传输批次编号',
	private String blz516;// 医疗期ID
	private String alc011;// 认定书编号
	private String alc022;// 伤害部位
	private String alc020;// 事故发生时间
	private String blc586;// 劳鉴医疗期类型
	private String aab004;// 事故发生单位名称
	private String aaz127;// 工伤认定信息ID
	private String alc035;// 劳动能力鉴定书编号
	private String bae050;// 红黑名单申请序列号
	private String aaz238;// 生育登记ID
	private String aaz065;// 银行ID
	private String bke306;// 银行名称
	private String bka897;// 连续缴费几个月
	private String aae009;// 账号户名
	private String aae010;// 银行账号
	private String aae010_confirm;// 银行账号确认
	private String aaz107;// 定点医疗机构ID
	private String akb063;// 住院天数
	private String ykc195;// 出院原因
	private String ykc680;// 补助类型
	private String ykc679;// 住院原因
	private String bka917;// 婴儿死亡时间
	private String bka916;// 婴儿情况
	private String bka915;// 母亲死亡时间
	private String bka914;// 母亲情况
	private String bka913;// 胎儿数
	private String bka912;// 生育类别
	private String bka911;// 手术日期
	private String bka895;// 入参类型(aac001、aac002、bka100)
	private String bka896;// 入参值
	private String bka898;// 静态要素里要put个珠海要素account_level缴费档次(不知道怎么取给个默认值1)
	private String bka892;// 保存标识1已保存0未保存
	private String bke011;// BKE011 VARCHAR2(1000) Y 病情摘要
	private String bke012;// BKE012 VARCHAR2(1000) Y 治疗方案
	private String bke003;// VARCHAR2(50) Y 推荐病种（关联AKA120）
	private String aae127;// kc41.DATE Y 申请日期
	private String aae016;// kc41.审核标志(1:未审核；2：初审通过；3：初审不通过；4：复审通过；5：复审不通过)
	private String bke035;// kc41.申请类型(0:医院申请 1:中心申请)
	private String aaa127;// kc41.NUMBER(16) Y 工伤认定信息ID
	private String bke811;// kc41.中心编码
	private String querystring;// 查询条件
	private String bka891;// 结算标识1已结算0未结算
	private String bka890;// 当前日期
	private String bka439;// 场景值VARCHAR2(100) not null
	private String bka435;// 场景编号VARCHAR2(30) not null
	private String bka438;// 场景阶段（1：业务开始 2：业务结算 3：业务结束）VARCHAR2(1) not null
	private String bkf001;// 血型
	private String bkf002;// 入院方式
	private String bkf003;// 入院情况
	private String bka066;// 出院转归情况
	private String bkf005;// 抢救次数
	private String bkf006;// 抢救成功次数
	private String bkm029;// 医保识别码
	private String bkc190;// 基本药物标识
	private String bkc194;// 匹配ID
	private String bkc109;// 异名ID
	private String aae013;// 备注
	private String aka057;// 先自付比例
	private String bkc106;// 居民先自付比例
	private String aka065;// 目录等级
	private String aac084;// 退休延缴人员标识
	private String bka784;// 到账日期的次月
	private String aae037;// 到账日期(年月)
	private String bka783;// 台账日期的上月(年月)
	private String bka782;// down_scale 零报下浮比例
	private String bka781;// 取此待遇类型的二级目录使用标志“0”排除法“1”准入法“2”单病种“9”没有二级目录(special_range_use_flag)
	private String bka408;// 零报原因
	private String bka930;// 是否急诊
	private String bka929;// 药费上浮情形
	private String bka780;// 待遇算法用到(treat_pers_type)
	private String bka889;// last_balance 个人账户余额
	private String aac005;// 民族
	private String aae006;// 地址
	private String aac009;// 户口性质
	private String aac012;// 个人身份
	private String aac015;// 国家职业资格等级
	private Date aae036;// 经办日期
	private String bke058;// 医院审核标识
	private String kc21id_last;//
	private String akb020_last;//
	private String aaz217_last;//
	private String akb021_last;//
	private String aka121_last;//
	private String aae030_last;//
	private String aae031_last;//
	private String bka888;// 基金冻结状态(0正常、1冻结、9未参保)
	private String aae002;// 费款所属期yyyyMM
	private String aae003;// 对应费款所属期yyyyMM
	private String bae311;// 入库人群类型(1，已趸交、2，一次性缴清、3，已缴满120个月)
	private String bae312;// 入库时间yyyyMMdd
	private String aaz159;// 参保关系ID
	private String aab999;// 单位管理码
	private String bke956;// 社会救助类别(民政免缴类别)
	private String aac058;// 证件类型
	private String aka083;// 申请待遇类型 "110"——"门诊" 医疗特殊业务申请类型
	private String zyzje;// String 12 总金额
	private String sbzfje;// String 12 社保支付金额
	private String zhzfje;// String 12 帐户支付金额
	private String bfxmzfje;// String 12 部分项目自付金额
	private String qfje;// String 12 个人起付金额
	private String grzfje1;// String 12 个人自费项目金额
	private String grzfje2;// String 12 个人自付金额
	private String grzfje3;// String 12 个人自负金额
	private String cxzfje;// String 12 超统筹支付限额个人自付金额
	private String yyfdje;// String 12 医院垫付金额
	private String cash_pay_com;// String 12 个人自付现金部分
	private String acct_pay_com;// String 12 个人自付个人帐户部分
	private String cash_pay_own;// String 12 个人自费现金部分
	private String acct_pay_own;// String 12 个人自费个人帐户部分
	private String oper_flag;// 操作标志 "1"——"挂号"
	private String save_flag;// 保存标志 "1"——"挂号保存传1" 【门诊保存标志 "0"——"试算""1"——"收费" 】
	private String aka121;// 疾病名称
	private String aac013;// 人员状态
	private String aab301;// 行政区划代码（参保地） varchar2(6) N 地市编码（ 例如： 440600 佛山市）
	private String aab299;// 行政区划代码（就医地） varchar2(6) N 地市编码（ 例如： 440600 佛山市）
	private String bac002;// 退休时间
	private String aac008;// N VARCHAR2(1) Y 人员参保状态
	private String aac031;// N VARCHAR2(1) Y
							// 个人缴费状态"0"——"正常""1"——"冻结""2"——"暂停参保""3"——"中止参保""9"
							// —— "未参保"
	private String aae030;// 开始日期
	private String aae031;// 结束日期
	private String aae035;// 个人参保变更日期
	private String aac050;// 个人参保变更类型
	private String aae001;// 医保年度【业务期间】
	private String bkc110;// 医院级别
	private String bkc116;// 视同社区标识
	private String bkc115;// 城居门诊专科标识
	private String akb023;// 医疗机构类别
	private String bkc205;// 学校标准标记
	private String bkc118;// 城职门诊专科标识
	private String bkc119;// 政府办基层医疗机构标识
	private String aka101;// 医院等级
	private String bkc111;// 外地医院标识
	private String bka155;// N VARCHAR2(50) Y 待遇类型名称
	private String bka156;// N VARCHAR2(1) Y 单病种标志
	private String bac028;// N VARCHAR2(40) Y 医疗证号
	private String bke932;// 泛珠异地标识
	private String kc21id;// varchar(50) not null comment '主键（uuid）',
	private String akb020;// varchar(20) not null comment '医院编号',
	private String aaz217;// varchar(20) not null comment '就医登记号',
	private String bka001;// int not null default 1 comment '费用批次',
	private String bka009;// int comment '病例分型序号',
	private String aka130;// varchar(6) not null comment '业务类别编号',
	private String bka002;// int not null default 0 comment '内部序数',
	private String baa027;// varchar(6) not null comment '当事人所在统筹区',【参保地】
	private String aac001;// int not null comment '电脑号',
	private String aac003;// varchar(20) not null comment '姓名',
	private String aac004;// varchar(1) not null comment '性别',
	private String bka004;// varchar(6) not null comment '人员类别',
	private String bka005;// varchar(6) not null default '000' comment '级别',
	private String aac002;// varchar(25) comment '社会保障号码',
	private String bka100;// varchar(25) comment 'ic卡号',【参保人社保卡号】
	private String akb021;// varchar(200) not null comment '医疗服务机构名称（定点医疗机构名称）',
	private String aac006;// date comment '出生日期',【出生日期_yyyyMMdd】
	private String aae005;// varchar(30) comment '联系电话',
	private String aab001;// int not null comment '单位电脑号',
	private String bka008;// varchar(200) not null comment '单位名称',
	private String aab019;// varchar(6) comment '单位类型编号',【单位类型编号】
	private String bka006;// varchar(6) not null comment '待遇类型',
	private String bka010;// int comment '本年业务次数',
	private String bka011;// varchar(20) comment '关联医疗机构编码',
	private String bka012;// varchar(20) comment '关联业务序列号',
	private String aaz267;// int comment '申请序列号',【门诊选点申请序列号】
	private String bka013;// date not null comment '业务登记日期',
	private String bka015;// varchar(50) not null comment '登记人',
	private String bka016;// varchar(1) not null default '0' comment '登记标志(0：正常
							// 1：转院 2：二次返院（审批通过后rela_serial_no为空） 3：急诊留观转住院
							// 4：90天或180天结算)',
	private String bka017;// date not null comment '业务开始日期',
	private String bka018;// varchar(10) comment '业务开始情况（fr：提取冻结费用的零报业务
							// mw：医疗转工伤的零报业务）',
	private String akf001;// varchar(20) comment '入院科室',
	private String bka020;// varchar(50) comment '入院科室名称',
	private String bka021;// varchar(20) comment '入院病区',
	private String bka022;// varchar(50) comment '入院病区名称',
	private String ake020;// varchar(20) comment '入院床位号',
	private String bka024;// varchar(1) comment '床位类型',
	private String bka025;// varchar(20) comment '医院业务号（住院号）',
	private String akc193;// varchar(20) not null comment '入院疾病诊断编码',
	private String bkz101;// varchar(20) not null comment '入院疾病诊断名称',
	private String akc196;// varchar(20) not null comment '出院疾病诊断编码',
	private String bkz102;// varchar(20) not null comment '出院疾病诊断'名称,
	private String bka245;// decimal(10,2) comment '预付款总额',
	private String bka028;// date comment '确诊日期',
	private String bka029;// varchar(20) comment '确诊疾病诊断',
	private String bka030;// int comment '住院天数',
	private String bka032;// date comment '业务结束日期',
	private String bka033;// varchar(20) comment '结束人工号',
	private String bka034;// varchar(50) comment '结束人',
	private String bka035;// varchar(10) comment '人员类别',
	private String bka036;// varchar(1) not null default '0' comment '用卡标志',
	private String bka037;// varchar(1) not null default '0' comment '中心报帐标志',
	private String bka038;// date comment '诊次结束时间',
	private String bka039;// varchar(1) not null default '0' comment
							// '完成标志',【诊次结束标志】
	private String bka041;// varchar(1) not null default '0' comment '锁定标志',
	private String bka042;// int comment '对应的工伤生育业务号',【工伤生育凭证号】
	private String ake024;// varchar(500) comment '备注',
	private String bka044;// varchar(1) not null default '0' comment '传输标志(0:未传输
							// 1:已成功传输 2:未成功传输）',
	private String bka045;// date not null comment '完成时间',
	private String bka046;// varchar(20) not null comment '完成人工号',
	private String bka047;// varchar(50) comment '完成人',
	private String bka048;// varchar(1) not null comment '完成原因(0 业务正常完成 1 取消业务登记
							// 2 业务信息异动 3 取消业务终结)',
	private String bka050;// varchar(1) default '0' comment '对帐标志',
	private String aaa027;// varchar(6) not null comment 所属统筹中心
	private String aae140;// varchar(6) not null comment '险种',
	private String bka414;// varchar(6) not null default '''0'' ' comment
							// '补充人员类别',
	private String bka415;// varchar(6) not null comment
							// '就诊类型或待遇冻结原因（0正常业务、1省内异地、2泛珠异地、3门诊应急、4用人单位、5第三人、6基金冻结_freezereason、7涉及第三人责任_freezereason、8已办理异地但在本地医院就医_freezereason）',
	private String bae009;// varchar(20) comment '影像档案号',
	private String baf313;// varchar(50) comment '联系人',
	private String aae100;// varchar(1) not null default '1' comment
							// '有效标志,''0'',无效,''1''有效',
	private String bka501;// VARCHAR2(6) Y 统筹区编码
	private String bka502;// VARCHAR2(6) Y 医院级别
	private String ake022;// VARCHAR2(20) Y 医保医师编号
	private String bka504;// date,
	private String bka505;// date,
	private String bka506;// date,
	private String bka507;// int,
	private String bka508;// int,未就业配偶标志（1是 ，0否）
	private String bka509;// int,
	private String bka510;// varchar(50),
	private String kc21;// comment '业务基本信息完成表';
	private String kc22id;// varchar(50) not null comment '主键（uuid）',
	private String aaz213;// int not null comment '费用序列号',
	private String aka063;// varchar(6) not null comment '费用统计类别'
	private String ake003;// varchar(1) not null comment
							// '项目药品类型(0:项目，1：西药，2：中成药，3：中草药)',
	private String ake001;// varchar(20) not null comment '中心药品项目编码',
	private String ake002;// varchar(100) not null comment '中心药品项目名称',
	private String ake005;// varchar(20) not null comment '医院药品项目编码',
	private String ake006;// varchar(100) not null comment '医院药品项目名称',
	private String aka070;// varchar(30) comment '剂型',
	private String bka073;// varchar(50) comment '厂家',
	private String aka074;// varchar(300) comment '规格',
	private String bka055;// varchar(20) comment '计量单位',
	private BigDecimal bka040;// decimal(10,4) not null comment '单价',
	private BigDecimal akc226;// decimal(12,4) not null comment '用量',
	private BigDecimal aae019;// decimal(12,4) not null comment '金额',
	private String bka059;// decimal(12,4) comment '冲减金额（主要为计算方便使用）',
	private String bka060;// varchar(1) comment '使用标志（1：出院带药 2：抢救用药 3：急诊）',
	private String bka061;// int comment '病情严重程度',
	private String bka062;// int comment '对应费用序列号',
	private String bka063;// varchar(20) not null comment '录入人工号',
	private String bka064;// varchar(50) comment '录入人',
	private String bka065;// date not null comment '录入时间',【费用录入日期_yyyyMMdd】
	private String bka067;// varchar(1) default '0' comment
							// '费用冻结标志，用来表识参保人所在单位的基本医疗保险被冻结期间录入的费用。0：未冻结；1：已冻结；2：冻结已处理',
	private String bka068;// int comment '对应冻结的费用序列号',
	private String bka069;// date comment '费用上传时间',
	private String bka070;// varchar(20) comment '处方号',
	private String bka071;// varchar(20) comment '对应医院费用号',【医院费用的唯一标识】
	private String bka072;// varchar(20) comment '处方医院编号',
	private String bka074;// varchar(20) comment '处方医生编号',
	private String bka075;// varchar(50) comment '处方医生姓名',
	private String bka076;// varchar(1) not null default '0' comment '审核标志',
	private String bka511;// varchar(20) comment '城职对应待遇值',
	private String bka512;// varchar(20) comment '城乡对应待遇值',
	private String kc22;// comment '费用明细完成表';
	private String kc27id;// varchar(50) not null comment '主键（uuid）',
	private String aaz215;// int not null comment '支付序列号（每次业务都从1开始的序号）',
	private String bka077;// int not null default 0 comment '支付序数',
	private String bka078;// varchar(20) not null comment '政策明细编码',
	private String aaa157;// varchar(6) not null comment '基金编号',
	private String bka080;// date not null comment '支付时间',
	private String bka432;// varchar(6) not null comment
							// '分类标志(101:现金全自费,102:现金部分自费,103:个人帐户支付,109:冻结状态的全支付,999:历年帐户不够支付,000:除上面的情况)',
	private String kc27;// comment '支付记录完成表';
	private String kc28id;// varchar(50) not null comment '主键（uuid）',
	private String bka081;// decimal(12,4) not null comment '申报金额',
	private String bka082;// decimal(12,4) not null comment '部分自费金额',
	private String bka437;// decimal(12,4) not null comment '全自费金额',
	private String kc28;// comment '费用归类完成表';
	private String kcd1id;// varchar(50) not null comment '主键（uuid）',
	private String kcd1;// comment '业务基本信息表';
	private String kcd2id;// varchar(50) not null comment '主键（uuid）',
	private String kcd2;// comment '费用明细表';
	private String kcd7id;// varchar(50) not null comment '主键（uuid）',
	private String kcd7;// comment '支付记录表';
	private String kcd8id;// varchar(50) not null comment '主键（uuid）',
	private String kcd8;// comment '费用归类表';
	private String kce4id;// varchar(50) not null comment '主键（uuid）',
	private String kce4;// comment '业务场景表';
	private String kce5id;// varchar(50) not null comment '主键（uuid）',
	private String kce5;// comment '业务场景完成表';
	private String aaa100;// 代码类别
	private String aaa102;// 代码码值
	private String aaa104;// 预留(扩展)
	private String bkm017; // 限制用药--药监本位码
	private String akc172_aaa027; // 珠海转诊转院--转入医院的统筹区
	private String bkc500; // 入院类型：住院登记-- 中途结算标识
	private String bka513; // 费用查询自付比例（接口用）
	private String alc023; // 是否外伤 (1.是 2.否)
	private String aaa129;// 人员所属中心值
	private String aaz231;// 社保三大目录ID
	private String aaz277;// 医疗机构三大目录ID
	private String center_flag;// 结算中心标识 0 结算云 1 中心
	private String akc148;// 特殊补助类型
	private String bke054;
	private String akc194;
	private String aab405;
	private String aab400;
	private String bmc030; // 妊娠周期
	private String amc026; // 生育类别
	private String amc020; // 手术日期
	private String amc028; // 胎儿数
	private String bmc029; // 生育出院诊断
	private String amc060; // 生育待遇类型
	private String bka843; // 特惠保 （一站式基金）
	private String bka844; // 医院减免 （一站式基金）
	private String bka845; // 政府兜底 （一站式基金）

	/**
	 * 【TS19041000039】【需求开发】结算云生育出院结算优化
	 */
	public String getAmc023() {
		return amc023;
	}

	/**
	 * 【TS19041000039】【需求开发】结算云生育出院结算优化
	 */
	public void setAmc023(String amc023) {
		this.amc023 = amc023;
	}

	public String getBka843() {
		return bka843;
	}

	public void setBka843(String bka843) {
		this.bka843 = bka843;
	}

	public String getBka844() {
		return bka844;
	}

	public void setBka844(String bka844) {
		this.bka844 = bka844;
	}

	public String getBka845() {
		return bka845;
	}

	public void setBka845(String bka845) {
		this.bka845 = bka845;
	}

	public String getAmc060() {
		return amc060;
	}

	public void setAmc060(String amc060) {
		this.amc060 = amc060;
	}

	public String getBmc029() {
		return bmc029;
	}

	public void setBmc029(String bmc029) {
		this.bmc029 = bmc029;
	}

	public String getBmc030() {
		return bmc030;
	}

	public void setBmc030(String bmc030) {
		this.bmc030 = bmc030;
	}

	public String getAmc026() {
		return amc026;
	}

	public void setAmc026(String amc026) {
		this.amc026 = amc026;
	}

	public String getAmc020() {
		return amc020;
	}

	public void setAmc020(String amc020) {
		this.amc020 = amc020;
	}

	public String getAmc028() {
		return amc028;
	}

	public void setAmc028(String amc028) {
		this.amc028 = amc028;
	}

	public String getBke306() {
		return bke306;
	}

	public void setBke306(String bke306) {
		this.bke306 = bke306;
	}

	/**
	 * @return the aab405
	 */
	public String getAab405() {
		return aab405;
	}

	/**
	 * @param aab405 the aab405 to set
	 */
	public void setAab405(String aab405) {
		this.aab405 = aab405;
	}

	/**
	 * @return the aab400
	 */
	public String getAab400() {
		return aab400;
	}

	/**
	 * @param aab400 the aab400 to set
	 */
	public void setAab400(String aab400) {
		this.aab400 = aab400;
	}

	/**
	 * @return the akc194
	 */
	public String getAkc194() {
		return akc194;
	}

	/**
	 * @param akc194 the akc194 to set
	 */
	public void setAkc194(String akc194) {
		this.akc194 = akc194;
	}

	/**
	 * @return the bke054
	 */
	public String getBke054() {
		return bke054;
	}

	/**
	 * @param bke054 the bke054 to set
	 */
	public void setBke054(String bke054) {
		this.bke054 = bke054;
	}

	public String getAkc148() {
		return akc148;
	}

	public void setAkc148(String akc148) {
		this.akc148 = akc148;
	}

	public String getCenter_flag() {
		return center_flag;
	}

	public void setCenter_flag(String center_flag) {
		this.center_flag = center_flag;
	}

	public String getBkz101() {
		return bkz101;
	}

	public void setBkz101(String bkz101) {
		this.bkz101 = bkz101;
	}

	public String getBkz102() {
		return bkz102;
	}

	public void setBkz102(String bkz102) {
		this.bkz102 = bkz102;
	}

	public String getAaz231() {
		return aaz231;
	}

	public void setAaz231(String aaz231) {
		this.aaz231 = aaz231;
	}

	public String getAaz277() {
		return aaz277;
	}

	public void setAaz277(String aaz277) {
		this.aaz277 = aaz277;
	}

	public String getAaa129() {
		return aaa129;
	}

	public void setAaa129(String aaa129) {
		this.aaa129 = aaa129;
	}

	public String getBka870() {
		return bka870;
	}

	public void setBka870(String bka870) {
		this.bka870 = bka870;
	}

	public String getBka871() {
		return bka871;
	}

	public void setBka871(String bka871) {
		this.bka871 = bka871;
	}

	public String getBka872() {
		return bka872;
	}

	public void setBka872(String bka872) {
		this.bka872 = bka872;
	}

	public String getBka873() {
		return bka873;
	}

	public void setBka873(String bka873) {
		this.bka873 = bka873;
	}

	public String getBac938() {
		return bac938;
	}

	public void setBac938(String bac938) {
		this.bac938 = bac938;
	}

	public String getBac037() {
		return bac037;
	}

	public void setBac037(String bac037) {
		this.bac037 = bac037;
	}

	/**
	 * 
	 * @return 入院类型 : 住院登记 -- 中途结算
	 */
	public String getBkc500() {
		return bkc500;
	}

	/**
	 * 
	 * @param bkc500 入院类型 : 住院登记--中途结算
	 */
	public void setBkc500(String bkc500) {
		this.bkc500 = bkc500;
	}

	public String getAkc172_aaa027() {
		return akc172_aaa027;
	}

	public void setAkc172_aaa027(String akc172_aaa027) {
		this.akc172_aaa027 = akc172_aaa027;
	}

	/**
	 * 
	 * @return 医院业务回退审核信息表_业务审核信息ID
	 */
	public String getBke613() {
		return bke613;
	}

	/**
	 * 
	 * @param bke613 医院业务回退审核信息表_业务审核信息ID
	 */
	public void setBke613(String bke613) {
		this.bke613 = bke613;
	}

	/**
	 * 
	 * @return 申请类别(1:结算回退申请；2：病案首页信息回退申请)
	 */
	public String getAae101() {
		return aae101;
	}

	/**
	 * 
	 * @param aae101 申请类别(1:结算回退申请；2：病案首页信息回退申请)
	 */
	public void setAae101(String aae101) {
		this.aae101 = aae101;
	}

	/**
	 * 是否可以享受财政补助待遇
	 * 
	 * @return 0-否，1-是，可享受待遇。
	 */
	public String getBac096() {
		return bac096;
	}

	/**
	 * 是否可以享受财政补助待遇
	 * 
	 * @param bac096 0-否，1-是，可享受待遇。
	 */
	public void setBac096(String bac096) {
		this.bac096 = bac096;
	}

	public String getAkc252() {
		return akc252;
	}

	public void setAkc252(String akc252) {
		this.akc252 = akc252;
	}

	public String getYka116() {
		return yka116;
	}

	public void setYka116(String yka116) {
		this.yka116 = yka116;
	}

	/**
	 * 
	 * @return 公务员标识1是0否
	 */
	public String getBac098() {
		return bac098;
	}

	/**
	 * 
	 * @param bac098 公务员标识1是0否
	 */
	public void setBac098(String bac098) {
		this.bac098 = bac098;
	}

	/**
	 * 
	 * @return 公务员级别_000非公务员
	 */
	public String getAac020() {
		return aac020;
	}

	/**
	 * 
	 * @param aac020 公务员级别_000非公务员
	 */
	public void setAac020(String aac020) {
		this.aac020 = aac020;
	}

	/**
	 * 
	 * @return 药监本位码
	 */
	public String getBkm017() {
		return bkm017;
	}

	/**
	 * 
	 * @param bkm017 药监本位码
	 */
	public void setBkm017(String bkm017) {
		this.bkm017 = bkm017;
	}

	/**
	 * 
	 * @return 限制使用标志 "0"——"否" "1"——"是"
	 */
	public String getAka036() {
		return aka036;
	}

	/**
	 * 
	 * @param aka036 限制使用标志 "0"——"否" "1"——"是"
	 */
	public void setAka036(String aka036) {
		this.aka036 = aka036;
	}

	/**
	 * 
	 * @return 个人基金冻结信息_是否要返回每个月详细信息（0：否；1：是）
	 */
	public String getBka333() {
		return bka333;
	}

	/**
	 * 
	 * @param bka333 个人基金冻结信息_是否要返回每个月详细信息（0：否；1：是）
	 */
	public void setBka333(String bka333) {
		this.bka333 = bka333;
	}

	/**
	 * 
	 * @return 读写卡通用标识（系统内部使用）1、2、3
	 */
	public String getBka999() {
		return bka999;
	}

	/**
	 * 
	 * @param bka999 读写卡通用标识（系统内部使用）1、2、3
	 */
	public void setBka999(String bka999) {
		this.bka999 = bka999;
	}

	/**
	 * 
	 * @return 冲正标识
	 */
	public String getBka444() {
		return bka444;
	}

	/**
	 * 
	 * @param bka444 冲正标识
	 */
	public void setBka444(String bka444) {
		this.bka444 = bka444;
	}

	public String getBkc155() {
		return bkc155;
	}

	public void setBkc155(String bkc155) {
		this.bkc155 = bkc155;
	}

	public String getBkc157() {
		return bkc157;
	}

	public void setBkc157(String bkc157) {
		this.bkc157 = bkc157;
	}

	public String getBke509() {
		return bke509;
	}

	public void setBke509(String bke509) {
		this.bke509 = bke509;
	}

	public String getBkc152() {
		return bkc152;
	}

	public void setBkc152(String bkc152) {
		this.bkc152 = bkc152;
	}

	public String getBkc156() {
		return bkc156;
	}

	public void setBkc156(String bkc156) {
		this.bkc156 = bkc156;
	}

	public String getBkc153() {
		return bkc153;
	}

	public void setBkc153(String bkc153) {
		this.bkc153 = bkc153;
	}

	public String getBkc154() {
		return bkc154;
	}

	public void setBkc154(String bkc154) {
		this.bkc154 = bkc154;
	}

	public String getBkc027() {
		return bkc027;
	}

	public void setBkc027(String bkc027) {
		this.bkc027 = bkc027;
	}

	public String getBkc028() {
		return bkc028;
	}

	public void setBkc028(String bkc028) {
		this.bkc028 = bkc028;
	}

	public String getBkc029() {
		return bkc029;
	}

	public void setBkc029(String bkc029) {
		this.bkc029 = bkc029;
	}

	public String getBae101() {
		return bae101;
	}

	public void setBae101(String bae101) {
		this.bae101 = bae101;
	}

	/**
	 * 
	 * @return 跨省异地_定价上限金额
	 */
	public String getAka068() {
		return aka068;
	}

	/**
	 * 
	 * @param aka068 跨省异地_定价上限金额
	 */
	public void setAka068(String aka068) {
		this.aka068 = aka068;
	}

	/**
	 * 
	 * @return 跨省异地_超限价自付金额
	 */
	public String getAkc268() {
		return akc268;
	}

	/**
	 * 
	 * @param akc268 跨省异地_超限价自付金额
	 */
	public void setAkc268(String akc268) {
		this.akc268 = akc268;
	}

	/**
	 * 
	 * @return 交易类型(1正交易-1负交易)
	 */
	public String getAaa113() {
		return aaa113;
	}

	/**
	 * 
	 * @param aaa113 交易类型(1正交易-1负交易)
	 */
	public void setAaa113(String aaa113) {
		this.aaa113 = aaa113;
	}

	public String getAka185() {
		return aka185;
	}

	public void setAka185(String aka185) {
		this.aka185 = aka185;
	}

	public String getYkc009() {
		return ykc009;
	}

	public void setYkc009(String ykc009) {
		this.ykc009 = ykc009;
	}

	public String getAkc190() {
		return akc190;
	}

	public void setAkc190(String akc190) {
		this.akc190 = akc190;
	}

	public String getAae004() {
		return aae004;
	}

	public void setAae004(String aae004) {
		this.aae004 = aae004;
	}

	public String getYkc700() {
		return ykc700;
	}

	public void setYkc700(String ykc700) {
		this.ykc700 = ykc700;
	}

	public String getAaz216() {
		return aaz216;
	}

	public void setAaz216(String aaz216) {
		this.aaz216 = aaz216;
	}

	public String getOtransid() {
		return otransid;
	}

	public void setOtransid(String otransid) {
		this.otransid = otransid;
	}

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getAae314() {
		return aae314;
	}

	public void setAae314(String aae314) {
		this.aae314 = aae314;
	}

	public String getAaa204() {
		return aaa204;
	}

	public void setAaa204(String aaa204) {
		this.aaa204 = aaa204;
	}

	public String getYkc705() {
		return ykc705;
	}

	public void setYkc705(String ykc705) {
		this.ykc705 = ykc705;
	}

	public String getYkc706() {
		return ykc706;
	}

	public void setYkc706(String ykc706) {
		this.ykc706 = ykc706;
	}

	public String getTransid() {
		return transid;
	}

	public void setTransid(String transid) {
		this.transid = transid;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAkb026() {
		return akb026;
	}

	public void setAkb026(String akb026) {
		this.akb026 = akb026;
	}

	public String getAac044() {
		return aac044;
	}

	public void setAac044(String aac044) {
		this.aac044 = aac044;
	}

	/**
	 * 
	 * @return 费用发生日期
	 */
	public String getAke007() {
		return ake007;
	}

	/**
	 * @param 费用发生日期
	 */
	public void setAke007(String ake007) {
		this.ake007 = ake007;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getAab003() {
		return aab003;
	}

	public void setAab003(String aab003) {
		this.aab003 = aab003;
	}

	public String getAke132() {
		return ake132;
	}

	public void setAke132(String ake132) {
		this.ake132 = ake132;
	}

	public String getBkc144() {
		return bkc144;
	}

	public void setBkc144(String bkc144) {
		this.bkc144 = bkc144;
	}

	/**
	 * 
	 * @return 灵活就业人员标识1、0
	 */
	public String getBka700() {
		return bka700;
	}

	/**
	 * 
	 * @param bka700 灵活就业人员标识1、0
	 */
	public void setBka700(String bka700) {
		this.bka700 = bka700;
	}

	/**
	 * 
	 * @return 转诊转院类型 1--转诊 2--转院
	 */
	public String getBka600() {
		return bka600;
	}

	/**
	 * 
	 * @param bka600 转诊转院类型 1--转诊 2--转院
	 */
	public void setBka600(String bka600) {
		this.bka600 = bka600;
	}

	public String getAae189() {
		return aae189;
	}

	public void setAae189(String aae189) {
		this.aae189 = aae189;
	}

	public String getAaz002() {
		return aaz002;
	}

	public void setAaz002(String aaz002) {
		this.aaz002 = aaz002;
	}

	public String getAka084() {
		return aka084;
	}

	public void setAka084(String aka084) {
		this.aka084 = aka084;
	}

	public String getPrseno() {
		return prseno;
	}

	public void setPrseno(String prseno) {
		this.prseno = prseno;
	}

	public String getAae014() {
		return aae014;
	}

	public void setAae014(String aae014) {
		this.aae014 = aae014;
	}

	public String getAae015() {
		return aae015;
	}

	public void setAae015(String aae015) {
		this.aae015 = aae015;
	}

	public String getBke021() {
		return bke021;
	}

	public void setBke021(String bke021) {
		this.bke021 = bke021;
	}

	public String getBke022() {
		return bke022;
	}

	public void setBke022(String bke022) {
		this.bke022 = bke022;
	}

	public String getBke024() {
		return bke024;
	}

	public void setBke024(String bke024) {
		this.bke024 = bke024;
	}

	public String getBac001() {
		return bac001;
	}

	public void setBac001(String bac001) {
		this.bac001 = bac001;
	}

	public String getBke055() {
		return bke055;
	}

	public void setBke055(String bke055) {
		this.bke055 = bke055;
	}

	public String getAab069() {
		return aab069;
	}

	public void setAab069(String aab069) {
		this.aab069 = aab069;
	}

	public String getBkc016() {
		return bkc016;
	}

	public void setBkc016(String bkc016) {
		this.bkc016 = bkc016;
	}

	public String getBkb085() {
		return bkb085;
	}

	public void setBkb085(String bkb085) {
		this.bkb085 = bkb085;
	}

	public String getBke016() {
		return bke016;
	}

	public void setBke016(String bke016) {
		this.bke016 = bke016;
	}

	public String getBke042() {
		return bke042;
	}

	public void setBke042(String bke042) {
		this.bke042 = bke042;
	}

	public String getBke810() {
		return bke810;
	}

	public void setBke810(String bke810) {
		this.bke810 = bke810;
	}

	public String getBpe001() {
		return bpe001;
	}

	public void setBpe001(String bpe001) {
		this.bpe001 = bpe001;
	}

	public String getBae100() {
		return bae100;
	}

	public void setBae100(String bae100) {
		this.bae100 = bae100;
	}

	public String getBka143() {
		return bka143;
	}

	public void setBka143(String bka143) {
		this.bka143 = bka143;
	}

	public String getBka137() {
		return bka137;
	}

	public void setBka137(String bka137) {
		this.bka137 = bka137;
	}

	public String getBke047() {
		return bke047;
	}

	public void setBke047(String bke047) {
		this.bke047 = bke047;
	}

	public String getBke048() {
		return bke048;
	}

	public void setBke048(String bke048) {
		this.bke048 = bke048;
	}

	public String getBke049() {
		return bke049;
	}

	public void setBke049(String bke049) {
		this.bke049 = bke049;
	}

	public String getBke050() {
		return bke050;
	}

	public void setBke050(String bke050) {
		this.bke050 = bke050;
	}

	/**
	 * 
	 * @return KC20.bke052 申请类型
	 */
	public String getBke052() {
		return bke052;
	}

	/**
	 * 
	 * @param bke052 KC20.bke052 申请类型
	 */
	public void setBke052(String bke052) {
		this.bke052 = bke052;
	}

	public String getBke036() {
		return bke036;
	}

	public void setBke036(String bke036) {
		this.bke036 = bke036;
	}

	public String getBke017() {
		return bke017;
	}

	public void setBke017(String bke017) {
		this.bke017 = bke017;
	}

	public String getBke018() {
		return bke018;
	}

	public void setBke018(String bke018) {
		this.bke018 = bke018;
	}

	public String getBke060() {
		return bke060;
	}

	public void setBke060(String bke060) {
		this.bke060 = bke060;
	}

	public String getBke019() {
		return bke019;
	}

	public void setBke019(String bke019) {
		this.bke019 = bke019;
	}

	public String getBke020() {
		return bke020;
	}

	public void setBke020(String bke020) {
		this.bke020 = bke020;
	}

	public String getBke127() {
		return bke127;
	}

	public void setBke127(String bke127) {
		this.bke127 = bke127;
	}

	public String getBke128() {
		return bke128;
	}

	public void setBke128(String bke128) {
		this.bke128 = bke128;
	}

	public String getBke129() {
		return bke129;
	}

	public void setBke129(String bke129) {
		this.bke129 = bke129;
	}

	public String getBke053() {
		return bke053;
	}

	public void setBke053(String bke053) {
		this.bke053 = bke053;
	}

	public String getBke014() {
		return bke014;
	}

	public void setBke014(String bke014) {
		this.bke014 = bke014;
	}

	public String getBke046() {
		return bke046;
	}

	public void setBke046(String bke046) {
		this.bke046 = bke046;
	}

	public String getBke010() {
		return bke010;
	}

	public void setBke010(String bke010) {
		this.bke010 = bke010;
	}

	public String getBke009() {
		return bke009;
	}

	public void setBke009(String bke009) {
		this.bke009 = bke009;
	}

	public String getBke008() {
		return bke008;
	}

	public void setBke008(String bke008) {
		this.bke008 = bke008;
	}

	public String getBke007() {
		return bke007;
	}

	public void setBke007(String bke007) {
		this.bke007 = bke007;
	}

	public String getAke017() {
		return ake017;
	}

	public void setAke017(String ake017) {
		this.ake017 = ake017;
	}

	public String getAke015() {
		return ake015;
	}

	public void setAke015(String ake015) {
		this.ake015 = ake015;
	}

	public String getAke014() {
		return ake014;
	}

	public void setAke014(String ake014) {
		this.ake014 = ake014;
	}

	public String getAkc172() {
		return akc172;
	}

	public void setAkc172(String akc172) {
		this.akc172 = akc172;
	}

	public String getAkb041() {
		return akb041;
	}

	public void setAkb041(String akb041) {
		this.akb041 = akb041;
	}

	public String getAka079() {
		return aka079;
	}

	public void setAka079(String aka079) {
		this.aka079 = aka079;
	}

	public String getAaz286() {
		return aaz286;
	}

	public void setAaz286(String aaz286) {
		this.aaz286 = aaz286;
	}

	public String getAae190() {
		return aae190;
	}

	public void setAae190(String aae190) {
		this.aae190 = aae190;
	}

	public String getAae139() {
		return aae139;
	}

	public void setAae139(String aae139) {
		this.aae139 = aae139;
	}

	/**
	 * 
	 * @return 免起付线标识1或0
	 */
	public String getBka900() {
		return bka900;
	}

	/**
	 * 
	 * @param bka900 免起付线标识1或0
	 */
	public void setBka900(String bka900) {
		this.bka900 = bka900;
	}

	/**
	 * 
	 * @return 查询标识：1查询2保存
	 */
	public String getBka555() {
		return bka555;
	}

	/**
	 * 
	 * @param bka555 查询标识：1查询2保存
	 */
	public void setBka555(String bka555) {
		this.bka555 = bka555;
	}

	public String getBkb100() {
		return bkb100;
	}

	public void setBkb100(String bkb100) {
		this.bkb100 = bkb100;
	}

	/**
	 * 
	 * @return 未就业配偶标志
	 */
	public String getBmc077() {
		return bmc077;
	}

	/**
	 * 
	 * @param bmc077 未就业配偶标志
	 */
	public void setBmc077(String bmc077) {
		this.bmc077 = bmc077;
	}

	/**
	 * 
	 * @return 程序加载标识_开测专用
	 */
	public String getBka666() {
		return bka666;
	}

	/**
	 * 
	 * @param bka666 程序加载标识_开测专用
	 */
	public void setBka666(String bka666) {
		this.bka666 = bka666;
	}

	/**
	 * 
	 * @return 人员类别
	 */
	public String getAac066() {
		return aac066;
	}

	/**
	 * 
	 * @param aac066 人员类别
	 */
	public void setAac066(String aac066) {
		this.aac066 = aac066;
	}

	/**
	 * 特殊标识：1省内异地业务(本地参保人到外地就医)
	 * 
	 * @return
	 */
	public String getBka777() {
		return bka777;
	}

	/**
	 * 特殊标识：1省内异地业务(本地参保人到外地就医)
	 * 
	 * @param bka777
	 */
	public void setBka777(String bka777) {
		this.bka777 = bka777;
	}

	/**
	 * 
	 * @return 结算标识0费用试算1结算收费
	 */
	public String getBka893() {
		return bka893;
	}

	/**
	 * 
	 * @param bka893 结算标识0费用试算1结算收费
	 */
	public void setBka893(String bka893) {
		this.bka893 = bka893;
	}

	/**
	 * 
	 * @return 转出医院级别
	 */
	public String getBka899() {
		return bka899;
	}

	/**
	 * 
	 * @param bka899 转出医院级别
	 */
	public void setBka899(String bka899) {
		this.bka899 = bka899;
	}

	public String getAaz501() {
		return aaz501;
	}

	public void setAaz501(String aaz501) {
		this.aaz501 = aaz501;
	}

	/**
	 * 
	 * @return 卡状态0封存、1正常、2挂失、3应用锁定、9注销
	 */
	public String getAaz502() {
		return aaz502;
	}

	/**
	 * 
	 * @param aaz502 卡状态0封存、1正常、2挂失、3应用锁定、9注销
	 */
	public void setAaz502(String aaz502) {
		this.aaz502 = aaz502;
	}

	public String getAaz503() {
		return aaz503;
	}

	public void setAaz503(String aaz503) {
		this.aaz503 = aaz503;
	}

	public String getAaz007() {
		return aaz007;
	}

	public void setAaz007(String aaz007) {
		this.aaz007 = aaz007;
	}

	public String getBaz003() {
		return baz003;
	}

	public void setBaz003(String baz003) {
		this.baz003 = baz003;
	}

	/**
	 * 
	 * @return 生育待遇汇总
	 */
	public String getBka966() {
		return bka966;
	}

	/**
	 * 
	 * @param bka966 生育待遇汇总
	 */
	public void setBka966(String bka966) {
		this.bka966 = bka966;
	}

	/**
	 * 
	 * @return 待遇项目
	 */
	public String getAaa036() {
		return aaa036;
	}

	/**
	 * 
	 * @param aaa036 待遇项目
	 */
	public void setAaa036(String aaa036) {
		this.aaa036 = aaa036;
	}

	/**
	 * 
	 * @return 待遇金额
	 */
	public String getAae129() {
		return aae129;
	}

	/**
	 * 
	 * @param aae129 待遇金额
	 */
	public void setAae129(String aae129) {
		this.aae129 = aae129;
	}

	/**
	 * 
	 * @return 开始时间
	 */
	public String getAae041() {
		return aae041;
	}

	/**
	 * 
	 * @param aae041 开始时间
	 */
	public void setAae041(String aae041) {
		this.aae041 = aae041;
	}

	/**
	 * 
	 * @return 结束时间
	 */
	public String getAae042() {
		return aae042;
	}

	/**
	 * 
	 * @param aae042 结束时间
	 */
	public void setAae042(String aae042) {
		this.aae042 = aae042;
	}

	/**
	 * 
	 * @return 平均工资
	 */
	public String getAae402() {
		return aae402;
	}

	/**
	 * 
	 * @param aae402 平均工资
	 */
	public void setAae402(String aae402) {
		this.aae402 = aae402;
	}

	/**
	 * 
	 * @return 产假天数
	 */
	public String getAmc032() {
		return amc032;
	}

	/**
	 * 
	 * @param amc032 产假天数
	 */
	public void setAmc032(String amc032) {
		this.amc032 = amc032;
	}

	/**
	 * 
	 * @return 配偶姓名
	 */
	public String getAmc010() {
		return amc010;
	}

	/**
	 * 
	 * @param amc010 配偶姓名
	 */
	public void setAmc010(String amc010) {
		this.amc010 = amc010;
	}

	/**
	 * 
	 * @return 配偶身份证号码
	 */
	public String getBmc011() {
		return bmc011;
	}

	/**
	 * 
	 * @param bmc011 配偶身份证号码
	 */
	public void setBmc011(String bmc011) {
		this.bmc011 = bmc011;
	}

	/**
	 * 
	 * @return 配偶地址
	 */
	public String getBmc012() {
		return bmc012;
	}

	/**
	 * 
	 * @param bmc012 配偶地址
	 */
	public void setBmc012(String bmc012) {
		this.bmc012 = bmc012;
	}

	/**
	 * 
	 * @return 证件类型
	 */
	public String getAac043() {
		return aac043;
	}

	/**
	 * 
	 * @param aac043 证件类型
	 */
	public void setAac043(String aac043) {
		this.aac043 = aac043;
	}

	public String getBke579() {
		return bke579;
	}

	public void setBke579(String bke579) {
		this.bke579 = bke579;
	}

	/**
	 * 
	 * @return 门特门慢申请列表_用逗号分隔(,)
	 */
	public String getBke578() {
		return bke578;
	}

	/**
	 * 
	 * @param bke578 门特门慢申请列表_用逗号分隔(,)
	 */
	public void setBke578(String bke578) {
		this.bke578 = bke578;
	}

	public String getBke576() {
		return bke576;
	}

	public void setBke576(String bke576) {
		this.bke576 = bke576;
	}

	public String getBke575() {
		return bke575;
	}

	public void setBke575(String bke575) {
		this.bke575 = bke575;
	}

	/**
	 * 
	 * @return 人才标志
	 */
	public String getAac158() {
		return aac158;
	}

	/**
	 * 
	 * @param aac158 人才标志
	 */
	public void setAac158(String aac158) {
		this.aac158 = aac158;
	}

	/**
	 * 
	 * @return 人才类别
	 */
	public String getAac157() {
		return aac157;
	}

	/**
	 * 
	 * @param aac157 人才类别
	 */
	public void setAac157(String aac157) {
		this.aac157 = aac157;
	}

	/**
	 * 
	 * @return 卡识别码
	 */
	public String getBke550() {
		return bke550;
	}

	/**
	 * 
	 * @param bke550 卡识别码
	 */
	public void setBke550(String bke550) {
		this.bke550 = bke550;
	}

	/**
	 * 
	 * @return 读卡返回
	 */
	public String getBke548() {
		return bke548;
	}

	/**
	 * 
	 * @param bke548 读卡返回
	 */
	public void setBke548(String bke548) {
		this.bke548 = bke548;
	}

	/**
	 * 
	 * @return 写卡返回
	 */
	public String getBke549() {
		return bke549;
	}

	/**
	 * 
	 * @param bke549 写卡返回
	 */
	public void setBke549(String bke549) {
		this.bke549 = bke549;
	}

	/**
	 * 
	 * @return TAC码(内部使用)
	 */
	public String getBke547() {
		return bke547;
	}

	/**
	 * 
	 * @param bke547 TAC码(内部使用)
	 */
	public void setBke547(String bke547) {
		this.bke547 = bke547;
	}

	/**
	 * 
	 * @return 门慢目录标识
	 */
	public String getBka848() {
		return bka848;
	}

	/**
	 * 
	 * @param bka848 门慢目录标识
	 */
	public void setBka848(String bka848) {
		this.bka848 = bka848;
	}

	/**
	 * 
	 * @return 持卡就诊登记许可号
	 */
	public String getIc_reg_permit() {
		return ic_reg_permit;
	}

	/**
	 * 
	 * @param ic_reg_permit 持卡就诊登记许可号
	 */
	public void setIc_reg_permit(String ic_reg_permit) {
		this.ic_reg_permit = ic_reg_permit;
	}

	/**
	 * 
	 * @return 持卡就诊结算许可号_含tac码
	 */
	public String getIc_pay_permit() {
		return ic_pay_permit;
	}

	/**
	 * 
	 * @param ic_pay_permit 持卡就诊结算许可号_含tac码
	 */
	public void setIc_pay_permit(String ic_pay_permit) {
		this.ic_pay_permit = ic_pay_permit;
	}

	public String getFunction_id() {
		return function_id;
	}

	public void setFunction_id(String function_id) {
		this.function_id = function_id;
	}

	public String getDebug_model() {
		return debug_model;
	}

	public void setDebug_model(String debug_model) {
		this.debug_model = debug_model;
	}

	/**
	 * 
	 * @return 依次为：卡识别码、卡号。各数据项之间以“|”分割，且最后一个数据项以“|”结尾
	 */
	public String getPcardinfo() {
		return pcardinfo;
	}

	/**
	 * 
	 * @param pcardinfo 依次为：卡识别码、卡号。各数据项之间以“|”分割，且最后一个数据项以“|”结尾
	 */
	public void setPcardinfo(String pcardinfo) {
		this.pcardinfo = pcardinfo;
	}

	/**
	 * 
	 * @return 依次为：本次消费总金额(小于42949672.95的小数，小数点后保留两位)、个人账户交易金额和统筹基金支付金额相加的总金额（小于42949672.95的小数，小数点后保留两位）、交易时间（格式为YYYYMMDDHHMMSS）。各数据项之间以“|”分割，且最后一个数据项以“|”结尾。
	 */
	public String getPpayinfo() {
		return ppayinfo;
	}

	/**
	 * 
	 * @param ppayinfo 依次为：本次消费总金额(小于42949672.95的小数，小数点后保留两位)、个人账户交易金额和统筹基金支付金额相加的总金额（小于42949672.95的小数，小数点后保留两位）、交易时间（格式为YYYYMMDDHHMMSS）。各数据项之间以“|”分割，且最后一个数据项以“|”结尾。
	 */
	public void setPpayinfo(String ppayinfo) {
		this.ppayinfo = ppayinfo;
	}

	public String getIc_permit_model() {
		return ic_permit_model;
	}

	public void setIc_permit_model(String ic_permit_model) {
		this.ic_permit_model = ic_permit_model;
	}

	public String getAaz500() {
		return aaz500;
	}

	public void setAaz500(String aaz500) {
		this.aaz500 = aaz500;
	}

	public String getBaz021() {
		return baz021;
	}

	public void setBaz021(String baz021) {
		this.baz021 = baz021;
	}

	public String getBaz010() {
		return baz010;
	}

	public void setBaz010(String baz010) {
		this.baz010 = baz010;
	}

	public String getIc_bka100() {
		return ic_bka100;
	}

	public void setIc_bka100(String ic_bka100) {
		this.ic_bka100 = ic_bka100;
	}

	public String getIc_tac() {
		return ic_tac;
	}

	public void setIc_tac(String ic_tac) {
		this.ic_tac = ic_tac;
	}

	public String getBac032() {
		return bac032;
	}

	public void setBac032(String bac032) {
		this.bac032 = bac032;
	}

	public String getIc_permit() {
		return ic_permit;
	}

	public void setIc_permit(String ic_permit) {
		this.ic_permit = ic_permit;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	/**
	 * 
	 * @return 可报销基本药物总费用
	 */
	public String getBka846() {
		return bka846;
	}

	/**
	 * 
	 * @param bka846 可报销基本药物总费用
	 */
	public void setBka846(String bka846) {
		this.bka846 = bka846;
	}

	public String getAad006() {
		return aad006;
	}

	public void setAad006(String aad006) {
		this.aad006 = aad006;
	}

	/**
	 * 
	 * @return 医疗总费用
	 */
	public String getAkc264() {
		return akc264;
	}

	/**
	 * 
	 * @param akc264 医疗总费用
	 */
	public void setAkc264(String akc264) {
		this.akc264 = akc264;
	}

	public String getAka151() {
		return aka151;
	}

	public void setAka151(String aka151) {
		this.aka151 = aka151;
	}

	public String getAkb067() {
		return akb067;
	}

	public void setAkb067(String akb067) {
		this.akb067 = akb067;
	}

	/**
	 * 
	 * @return 个人账户支付
	 */
	public String getAkb066() {
		return akb066;
	}

	/**
	 * 
	 * @param akb066 个人账户支付
	 */
	public void setAkb066(String akb066) {
		this.akb066 = akb066;
	}

	public String getAke039() {
		return ake039;
	}

	public void setAke039(String ake039) {
		this.ake039 = ake039;
	}

	public String getAke035() {
		return ake035;
	}

	public void setAke035(String ake035) {
		this.ake035 = ake035;
	}

	public String getAke026() {
		return ake026;
	}

	public void setAke026(String ake026) {
		this.ake026 = ake026;
	}

	public String getAke029() {
		return ake029;
	}

	public void setAke029(String ake029) {
		this.ake029 = ake029;
	}

	public String getBka821() {
		return bka821;
	}

	public void setBka821(String bka821) {
		this.bka821 = bka821;
	}

	public String getBka822() {
		return bka822;
	}

	public void setBka822(String bka822) {
		this.bka822 = bka822;
	}

	public String getBka823() {
		return bka823;
	}

	public void setBka823(String bka823) {
		this.bka823 = bka823;
	}

	public String getBka824() {
		return bka824;
	}

	public void setBka824(String bka824) {
		this.bka824 = bka824;
	}

	public String getBka825() {
		return bka825;
	}

	public void setBka825(String bka825) {
		this.bka825 = bka825;
	}

	public String getBka826() {
		return bka826;
	}

	public void setBka826(String bka826) {
		this.bka826 = bka826;
	}

	public String getBka827() {
		return bka827;
	}

	public void setBka827(String bka827) {
		this.bka827 = bka827;
	}

	public String getBka828() {
		return bka828;
	}

	public void setBka828(String bka828) {
		this.bka828 = bka828;
	}

	public String getBka829() {
		return bka829;
	}

	public void setBka829(String bka829) {
		this.bka829 = bka829;
	}

	public String getBka830() {
		return bka830;
	}

	public void setBka830(String bka830) {
		this.bka830 = bka830;
	}

	public String getBka831() {
		return bka831;
	}

	public void setBka831(String bka831) {
		this.bka831 = bka831;
	}

	/**
	 * 
	 * @return 医保支付
	 */
	public String getBka832() {
		return bka832;
	}

	/**
	 * 
	 * @param bka832 医保支付
	 */
	public void setBka832(String bka832) {
		this.bka832 = bka832;
	}

	public String getBka833() {
		return bka833;
	}

	public void setBka833(String bka833) {
		this.bka833 = bka833;
	}

	public String getBka834() {
		return bka834;
	}

	public void setBka834(String bka834) {
		this.bka834 = bka834;
	}

	public String getBka835() {
		return bka835;
	}

	public void setBka835(String bka835) {
		this.bka835 = bka835;
	}

	public String getBka836() {
		return bka836;
	}

	public void setBka836(String bka836) {
		this.bka836 = bka836;
	}

	public String getBka837() {
		return bka837;
	}

	public void setBka837(String bka837) {
		this.bka837 = bka837;
	}

	public String getBka838() {
		return bka838;
	}

	public void setBka838(String bka838) {
		this.bka838 = bka838;
	}

	public String getBka839() {
		return bka839;
	}

	public void setBka839(String bka839) {
		this.bka839 = bka839;
	}

	public String getBka840() {
		return bka840;
	}

	public void setBka840(String bka840) {
		this.bka840 = bka840;
	}

	public String getBka841() {
		return bka841;
	}

	public void setBka841(String bka841) {
		this.bka841 = bka841;
	}

	public String getBka842() {
		return bka842;
	}

	public void setBka842(String bka842) {
		this.bka842 = bka842;
	}

	/**
	 * 
	 * @return 经办人
	 */
	public String getAae011() {
		return aae011;
	}

	/**
	 * 
	 * @param aae011 经办人
	 */
	public void setAae011(String aae011) {
		this.aae011 = aae011;
	}

	public String getBka814() {
		return bka814;
	}

	public void setBka814(String bka814) {
		this.bka814 = bka814;
	}

	public String getBka815() {
		return bka815;
	}

	public void setBka815(String bka815) {
		this.bka815 = bka815;
	}

	public String getBka816() {
		return bka816;
	}

	public void setBka816(String bka816) {
		this.bka816 = bka816;
	}

	public String getBka811() {
		return bka811;
	}

	public void setBka811(String bka811) {
		this.bka811 = bka811;
	}

	public String getBka812() {
		return bka812;
	}

	public void setBka812(String bka812) {
		this.bka812 = bka812;
	}

	public String getBka813() {
		return bka813;
	}

	public void setBka813(String bka813) {
		this.bka813 = bka813;
	}

	public String getBka800() {
		return bka800;
	}

	public void setBka800(String bka800) {
		this.bka800 = bka800;
	}

	public String getBka801() {
		return bka801;
	}

	public void setBka801(String bka801) {
		this.bka801 = bka801;
	}

	public String getBka802() {
		return bka802;
	}

	public void setBka802(String bka802) {
		this.bka802 = bka802;
	}

	public String getBka803() {
		return bka803;
	}

	public void setBka803(String bka803) {
		this.bka803 = bka803;
	}

	public String getBka804() {
		return bka804;
	}

	public void setBka804(String bka804) {
		this.bka804 = bka804;
	}

	public String getBka805() {
		return bka805;
	}

	public void setBka805(String bka805) {
		this.bka805 = bka805;
	}

	public String getBka806() {
		return bka806;
	}

	public void setBka806(String bka806) {
		this.bka806 = bka806;
	}

	public String getBka807() {
		return bka807;
	}

	public void setBka807(String bka807) {
		this.bka807 = bka807;
	}

	public String getBka808() {
		return bka808;
	}

	public void setBka808(String bka808) {
		this.bka808 = bka808;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 * @return 先行支付标识
	 */
	public String getBka989() {
		return bka989;
	}

	/**
	 * 
	 * @param bka989 先行支付标识
	 */
	public void setBka989(String bka989) {
		this.bka989 = bka989;
	}

	/**
	 * 
	 * @return reportTemplateID报表模板ID，一般存放在工程的report目录下，按业务分多级子目录，用点拼接，例如：
	 *         medicare/hosp/xxx.xls
	 */
	public String getBka980() {
		return bka980;
	}

	/**
	 * 
	 * @param bka980 reportTemplateID报表模板ID，一般存放在工程的report目录下，按业务分多级子目录，用点拼接，例如：
	 *               medicare/hosp/xxx.xls
	 */
	public void setBka980(String bka980) {
		this.bka980 = bka980;
	}

	/**
	 * 
	 * @return bizID业务唯一编号，默认请传输空，这个用于后续按照业务的唯一编号来查询报表，全局不允许重复
	 */
	public String getBka981() {
		return bka981;
	}

	/**
	 * 
	 * @param bka981 bizID业务唯一编号，默认请传输空，这个用于后续按照业务的唯一编号来查询报表，全局不允许重复
	 */
	public void setBka981(String bka981) {
		this.bka981 = bka981;
	}

	/**
	 * 
	 * @return keepDays保留天数，0标识永久保留，超过配置的天数，生成的报表数据将被删除
	 */
	public String getBka982() {
		return bka982;
	}

	/**
	 * 
	 * @param bka982 keepDays保留天数，0标识永久保留，超过配置的天数，生成的报表数据将被删除
	 */
	public void setBka982(String bka982) {
		this.bka982 = bka982;
	}

	/**
	 * 
	 * @return report_comm报表生成说明，业务传递，例如：财务2015年度数据
	 */
	public String getBka983() {
		return bka983;
	}

	/**
	 * 
	 * @param bka983 report_comm报表生成说明，业务传递，例如：财务2015年度数据
	 */
	public void setBka983(String bka983) {
		this.bka983 = bka983;
	}

	/**
	 * 
	 * @return user_name创建人
	 */
	public String getBka984() {
		return bka984;
	}

	/**
	 * 
	 * @param bka984 user_name创建人
	 */
	public void setBka984(String bka984) {
		this.bka984 = bka984;
	}

	/**
	 * 
	 * @return 生成报表数据的ID，后续通过这个ID来读取报表
	 */
	public String getBka985() {
		return bka985;
	}

	/**
	 * 
	 * @param bka985 生成报表数据的ID，后续通过这个ID来读取报表
	 */
	public void setBka985(String bka985) {
		this.bka985 = bka985;
	}

	public String getBka986() {
		return bka986;
	}

	public void setBka986(String bka986) {
		this.bka986 = bka986;
	}

	public String getBka987() {
		return bka987;
	}

	public void setBka987(String bka987) {
		this.bka987 = bka987;
	}

	public String getYkc021() {
		return ykc021;
	}

	public void setYkc021(String ykc021) {
		this.ykc021 = ykc021;
	}

	public String getYkc300() {
		return ykc300;
	}

	public void setYkc300(String ykc300) {
		this.ykc300 = ykc300;
	}

	public String getAkc026() {
		return akc026;
	}

	public void setAkc026(String akc026) {
		this.akc026 = akc026;
	}

	/**
	 * 
	 * @return 基本医疗本次支付限额
	 */
	public String getYka119() {
		return yka119;
	}

	/**
	 * 
	 * @param yka119 基本医疗本次支付限额
	 */
	public void setYka119(String yka119) {
		this.yka119 = yka119;
	}

	/**
	 * 
	 * @return 大病医疗本次支付限额
	 */
	public String getYka121() {
		return yka121;
	}

	/**
	 * 
	 * @param yka121 大病医疗本次支付限额
	 */
	public void setYka121(String yka121) {
		this.yka121 = yka121;
	}

	/**
	 * 
	 * @return 公务员本次支付限额
	 */
	public String getYka123() {
		return yka123;
	}

	/**
	 * 
	 * @param yka123 公务员本次支付限额
	 */
	public void setYka123(String yka123) {
		this.yka123 = yka123;
	}

	public String getAke092() {
		return ake092;
	}

	public void setAke092(String ake092) {
		this.ake092 = ake092;
	}

	public String getYka437() {
		return yka437;
	}

	public void setYka437(String yka437) {
		this.yka437 = yka437;
	}

	public String getAkc200() {
		return akc200;
	}

	public void setAkc200(String akc200) {
		this.akc200 = akc200;
	}

	public String getYkc667() {
		return ykc667;
	}

	public void setYkc667(String ykc667) {
		this.ykc667 = ykc667;
	}

	/**
	 * 
	 * @return 医疗类别
	 */
	public String getAka130_snyd() {
		return aka130_snyd;
	}

	/**
	 * 
	 * @param aka130_snyd 医疗类别
	 */
	public void setAka130_snyd(String aka130_snyd) {
		this.aka130_snyd = aka130_snyd;
	}

	/**
	 * 
	 * @return 总费用
	 */
	public String getBka979() {
		return bka979;
	}

	/**
	 * 
	 * @param bka979 总费用
	 */
	public void setBka979(String bka979) {
		this.bka979 = bka979;
	}

	/**
	 * 
	 * @return 结算标识：模拟结算1、正式结算0
	 */
	public String getBka988() {
		return bka988;
	}

	/**
	 * 
	 * @param bka988 结算标识：模拟结算1、正式结算0
	 */
	public void setBka988(String bka988) {
		this.bka988 = bka988;
	}

	/**
	 * 
	 * @return 疾病编码
	 */
	public String getAka120() {
		return aka120;
	}

	/**
	 * 
	 * @param aka120 疾病编码
	 */
	public void setAka120(String aka120) {
		this.aka120 = aka120;
	}

	/**
	 * 
	 * @return 调省内异地前置机功能号(暂时不启用)
	 */
	public String getRemote_funcid() {
		return remote_funcid;
	}

	/**
	 * 
	 * @param remote_funcid 调省内异地前置机功能号(暂时不启用)
	 */
	public void setRemote_funcid(String remote_funcid) {
		this.remote_funcid = remote_funcid;
	}

	/**
	 * 
	 * @return 标识(通用)开关、同步与否 1、0
	 */
	public String getBka977() {
		return bka977;
	}

	/**
	 * 
	 * @param bka977 标识(通用)开关、同步与否 1、0
	 */
	public void setBka977(String bka977) {
		this.bka977 = bka977;
	}

	/**
	 * 
	 * @return 住院业务费用明细同步保存标识(kc22)1同步0不同步
	 */
	public String getBka918() {
		return bka918;
	}

	/**
	 * 
	 * @param bka918 住院业务费用明细同步保存标识(kc22)1同步0不同步
	 */
	public void setBka918(String bka918) {
		this.bka918 = bka918;
	}

	/**
	 * 
	 * @return 住院业务费用明细同步保存标识(kcd2)1同步0不同步
	 */
	public String getBka919() {
		return bka919;
	}

	/**
	 * 
	 * @param bka919 住院业务费用明细同步保存标识(kcd2)1同步0不同步
	 */
	public void setBka919(String bka919) {
		this.bka919 = bka919;
	}

	public String getAmc021() {
		return amc021;
	}

	public void setAmc021(String amc021) {
		this.amc021 = amc021;
	}

	public String getAmc022() {
		return amc022;
	}

	public void setAmc022(String amc022) {
		this.amc022 = amc022;
	}

	/**
	 * 
	 * @return 生育业务类型
	 */
	public String getAmc050() {
		return amc050;
	}

	/**
	 * 
	 * @param amc050 生育业务类型
	 */
	public void setAmc050(String amc050) {
		this.amc050 = amc050;
	}

	/**
	 * 
	 * @return 生育手术类别
	 */
	public String getAmc029() {
		return amc029;
	}

	/**
	 * 
	 * @param amc029 生育手术类别
	 */
	public void setAmc029(String amc029) {
		this.amc029 = amc029;
	}

	/**
	 * 
	 * @return 胎次
	 */
	public String getAmc031() {
		return amc031;
	}

	/**
	 * 
	 * @param amc031 胎次
	 */
	public void setAmc031(String amc031) {
		this.amc031 = amc031;
	}

	/**
	 * 
	 * @return 修改计数器,默认插入时为1，执行一次update需要加一
	 */
	public String getBka971() {
		return bka971;
	}

	/**
	 * 
	 * @param bka971 修改计数器,默认插入时为1，执行一次update需要加一
	 */
	public void setBka971(String bka971) {
		this.bka971 = bka971;
	}

	/**
	 * 
	 * @return 传输打包标志 0未打包 1已打包
	 */
	public String getBka972() {
		return bka972;
	}

	/**
	 * 
	 * @param bka972 传输打包标志 0未打包 1已打包
	 */
	public void setBka972(String bka972) {
		this.bka972 = bka972;
	}

	/**
	 * 
	 * @return 数据最后修改时间
	 */
	public String getBka973() {
		return bka973;
	}

	/**
	 * 
	 * @param bka973 数据最后修改时间
	 */
	public void setBka973(String bka973) {
		this.bka973 = bka973;
	}

	/**
	 * 
	 * @return 最后传输批次编号
	 */
	public String getBka974() {
		return bka974;
	}

	/**
	 * 
	 * @param bka974 最后传输批次编号
	 */
	public void setBka974(String bka974) {
		this.bka974 = bka974;
	}

	/**
	 * 
	 * @return 医疗期ID
	 */
	public String getBlz516() {
		return blz516;
	}

	/**
	 * 
	 * @param blz516 医疗期ID
	 */
	public void setBlz516(String blz516) {
		this.blz516 = blz516;
	}

	/**
	 * 
	 * @return 认定书编号
	 */
	public String getAlc011() {
		return alc011;
	}

	/**
	 * 
	 * @param alc011 认定书编号
	 */
	public void setAlc011(String alc011) {
		this.alc011 = alc011;
	}

	/**
	 * 
	 * @return 伤害部位
	 */
	public String getAlc022() {
		return alc022;
	}

	/**
	 * 
	 * @param alc022 伤害部位
	 */
	public void setAlc022(String alc022) {
		this.alc022 = alc022;
	}

	/**
	 * 
	 * @return 事故发生时间
	 */
	public String getAlc020() {
		return alc020;
	}

	/**
	 * 
	 * @param alc020 事故发生时间
	 */
	public void setAlc020(String alc020) {
		this.alc020 = alc020;
	}

	/**
	 * 
	 * @return 劳鉴医疗期类型
	 */
	public String getBlc586() {
		return blc586;
	}

	/**
	 * 
	 * @param blc586 劳鉴医疗期类型
	 */
	public void setBlc586(String blc586) {
		this.blc586 = blc586;
	}

	/**
	 * 
	 * @return 事故发生单位名称
	 */
	public String getAab004() {
		return aab004;
	}

	/**
	 * 
	 * @param aab004 事故发生单位名称
	 */
	public void setAab004(String aab004) {
		this.aab004 = aab004;
	}

	/**
	 * 
	 * @return 工伤认定信息ID
	 */
	public String getAaz127() {
		return aaz127;
	}

	/**
	 * 
	 * @param aaz127 工伤认定信息ID
	 */
	public void setAaz127(String aaz127) {
		this.aaz127 = aaz127;
	}

	/**
	 * 
	 * @return 劳动能力鉴定书编号
	 */
	public String getAlc035() {
		return alc035;
	}

	/**
	 * 
	 * @param alc035 劳动能力鉴定书编号
	 */
	public void setAlc035(String alc035) {
		this.alc035 = alc035;
	}

	/**
	 * 
	 * @return 红黑名单申请序列号
	 */
	public String getBae050() {
		return bae050;
	}

	/**
	 * 
	 * @param bae050 红黑名单申请序列号
	 */
	public void setBae050(String bae050) {
		this.bae050 = bae050;
	}

	/**
	 * 
	 * @return 生育登记ID
	 */
	public String getAaz238() {
		return aaz238;
	}

	/**
	 * 
	 * @param aaz238 生育登记ID
	 */
	public void setAaz238(String aaz238) {
		this.aaz238 = aaz238;
	}

	/**
	 * 
	 * @return 银行ID
	 */
	public String getAaz065() {
		return aaz065;
	}

	/**
	 * 
	 * @param aaz065 银行ID
	 */
	public void setAaz065(String aaz065) {
		this.aaz065 = aaz065;
	}

	/**
	 * 
	 * @return 连续缴费几个月
	 */
	public String getBka897() {
		return bka897;
	}

	/**
	 * 
	 * @param bka897 连续缴费几个月
	 */
	public void setBka897(String bka897) {
		this.bka897 = bka897;
	}

	/**
	 * 
	 * @return 账号户名
	 */
	public String getAae009() {
		return aae009;
	}

	/**
	 * 
	 * @param aae009 账号户名
	 */
	public void setAae009(String aae009) {
		this.aae009 = aae009;
	}

	/**
	 * 
	 * @return 银行账号
	 */
	public String getAae010() {
		return aae010;
	}

	/**
	 * 
	 * @param aae010 银行账号
	 */
	public void setAae010(String aae010) {
		this.aae010 = aae010;
	}

	/**
	 * 
	 * @return 银行账号确认
	 */
	public String getAae010_confirm() {
		return aae010_confirm;
	}

	/**
	 * 
	 * @param aae010_confirm 银行账号确认
	 */
	public void setAae010_confirm(String aae010_confirm) {
		this.aae010_confirm = aae010_confirm;
	}

	/**
	 * 
	 * @return 定点医疗机构ID
	 */
	public String getAaz107() {
		return aaz107;
	}

	/**
	 * 
	 * @param aaz107 定点医疗机构ID
	 */
	public void setAaz107(String aaz107) {
		this.aaz107 = aaz107;
	}

	public String getAkb063() {
		return akb063;
	}

	public void setAkb063(String akb063) {
		this.akb063 = akb063;
	}

	public String getYkc195() {
		return ykc195;
	}

	public void setYkc195(String ykc195) {
		this.ykc195 = ykc195;
	}

	public String getYkc680() {
		return ykc680;
	}

	public void setYkc680(String ykc680) {
		this.ykc680 = ykc680;
	}

	public String getYkc679() {
		return ykc679;
	}

	public void setYkc679(String ykc679) {
		this.ykc679 = ykc679;
	}

	public String getYzz014() {
		return yzz014;
	}

	public void setYzz014(String yzz014) {
		this.yzz014 = yzz014;
	}

	public String getBka917() {
		return bka917;
	}

	public void setBka917(String bka917) {
		this.bka917 = bka917;
	}

	public String getBka916() {
		return bka916;
	}

	public void setBka916(String bka916) {
		this.bka916 = bka916;
	}

	public String getBka915() {
		return bka915;
	}

	public void setBka915(String bka915) {
		this.bka915 = bka915;
	}

	public String getBka914() {
		return bka914;
	}

	public void setBka914(String bka914) {
		this.bka914 = bka914;
	}

	/**
	 * 
	 * @return 胎儿数
	 */
	public String getBka913() {
		return bka913;
	}

	/**
	 * 
	 * @param bka913 胎儿数
	 */
	public void setBka913(String bka913) {
		this.bka913 = bka913;
	}

	/**
	 * 
	 * @return 生育类别
	 */
	public String getBka912() {
		return bka912;
	}

	/**
	 * 
	 * @param bka912 生育类别
	 */
	public void setBka912(String bka912) {
		this.bka912 = bka912;
	}

	/**
	 * 手术日期
	 * 
	 * @return
	 */
	public String getBka911() {
		return bka911;
	}

	/**
	 * 手术日期
	 * 
	 * @param bka911
	 */
	public void setBka911(String bka911) {
		this.bka911 = bka911;
	}

	/**
	 * 
	 * @return 入参类型(aac001、aac002、bka100)
	 */
	public String getBka895() {
		return bka895;
	}

	/**
	 * 
	 * @param bka895 入参类型(aac001、aac002、bka100)
	 */
	public void setBka895(String bka895) {
		this.bka895 = bka895;
	}

	/**
	 * 
	 * @return 入参值
	 */
	public String getBka896() {
		return bka896;
	}

	/**
	 * 
	 * @param bka896 入参值
	 */
	public void setBka896(String bka896) {
		this.bka896 = bka896;
	}

	/**
	 * 
	 * @return 缴费档次
	 */
	public String getBka898() {
		return bka898;
	}

	/**
	 * 
	 * @param bka898 缴费档次
	 */
	public void setBka898(String bka898) {
		this.bka898 = bka898;
	}

	/**
	 * 
	 * @return 保存标识1已保存0未保存
	 */
	public String getBka892() {
		return bka892;
	}

	/**
	 * 
	 * @param bka892 保存标识1已保存0未保存
	 */
	public void setBka892(String bka892) {
		this.bka892 = bka892;
	}

	/**
	 * 
	 * @return 病情摘要
	 */
	public String getBke011() {
		return bke011;
	}

	/**
	 * 
	 * @param bke011 病情摘要
	 */
	public void setBke011(String bke011) {
		this.bke011 = bke011;
	}

	/**
	 * 
	 * @return 治疗方案
	 */
	public String getBke012() {
		return bke012;
	}

	/**
	 * 
	 * @param bke012 治疗方案
	 */
	public void setBke012(String bke012) {
		this.bke012 = bke012;
	}

	/**
	 * 
	 * @return VARCHAR2(50) Y 推荐病种（关联AKA120）
	 */
	public String getBke003() {
		return bke003;
	}

	/**
	 * 
	 * @param bke003 VARCHAR2(50) Y 推荐病种（关联AKA120）
	 */
	public void setBke003(String bke003) {
		this.bke003 = bke003;
	}

	public String getAae127() {
		return aae127;
	}

	public void setAae127(String aae127) {
		this.aae127 = aae127;
	}

	public String getAae016() {
		return aae016;
	}

	public void setAae016(String aae016) {
		this.aae016 = aae016;
	}

	public String getBke035() {
		return bke035;
	}

	public void setBke035(String bke035) {
		this.bke035 = bke035;
	}

	public String getAaa127() {
		return aaa127;
	}

	public void setAaa127(String aaa127) {
		this.aaa127 = aaa127;
	}

	public String getBke811() {
		return bke811;
	}

	public void setBke811(String bke811) {
		this.bke811 = bke811;
	}

	/**
	 * 
	 * @return 查询条件
	 */
	public String getQuerystring() {
		return querystring;
	}

	/**
	 * 
	 * @param querystring 查询条件
	 */
	public void setQuerystring(String querystring) {
		this.querystring = querystring;
	}

	/**
	 * 
	 * @return 结算标识1已结算0未结算
	 */
	public String getBka891() {
		return bka891;
	}

	/**
	 * 
	 * @param bka891 结算标识1已结算0未结算
	 */
	public void setBka891(String bka891) {
		this.bka891 = bka891;
	}

	/**
	 * 
	 * @return 当前日期
	 */
	public String getBka890() {
		return bka890;
	}

	/**
	 * 
	 * @param bka890 当前日期
	 */
	public void setBka890(String bka890) {
		this.bka890 = bka890;
	}

	/**
	 * 
	 * @return 场景值
	 */
	public String getBka439() {
		return bka439;
	}

	/**
	 * 
	 * @param bka439 场景值
	 */
	public void setBka439(String bka439) {
		this.bka439 = bka439;
	}

	/**
	 * 
	 * @return 场景编号
	 */
	public String getBka435() {
		return bka435;
	}

	/**
	 * 
	 * @param bka435 场景编号
	 */
	public void setBka435(String bka435) {
		this.bka435 = bka435;
	}

	/**
	 * 
	 * @return 场景阶段（1：业务开始 2：业务结算 3：业务结束）
	 */
	public String getBka438() {
		return bka438;
	}

	/**
	 * 
	 * @param bka438 场景阶段（1：业务开始 2：业务结算 3：业务结束）
	 */
	public void setBka438(String bka438) {
		this.bka438 = bka438;
	}

	/**
	 * 
	 * @return 血型
	 */
	public String getBkf001() {
		return bkf001;
	}

	/**
	 * 
	 * @param bkf001 血型
	 */
	public void setBkf001(String bkf001) {
		this.bkf001 = bkf001;
	}

	/**
	 * 
	 * @return 入院方式
	 */
	public String getBkf002() {
		return bkf002;
	}

	/**
	 * 
	 * @param bkf002 入院方式
	 */
	public void setBkf002(String bkf002) {
		this.bkf002 = bkf002;
	}

	/**
	 * 
	 * @return 入院情况
	 */
	public String getBkf003() {
		return bkf003;
	}

	/**
	 * 
	 * @param bkf003 入院情况
	 */
	public void setBkf003(String bkf003) {
		this.bkf003 = bkf003;
	}

	/**
	 * 
	 * @return 出院转归情况
	 */
	public String getBka066() {
		return bka066;
	}

	/**
	 * 
	 * @param bkf004 出院转归情况
	 */
	public void setBka066(String bka066) {
		this.bka066 = bka066;
	}

	/**
	 * 
	 * @return 抢救次数
	 */
	public String getBkf005() {
		return bkf005;
	}

	/**
	 * 
	 * @param bkf005 抢救次数
	 */
	public void setBkf005(String bkf005) {
		this.bkf005 = bkf005;
	}

	/**
	 * 
	 * @return 抢救成功次数
	 */
	public String getBkf006() {
		return bkf006;
	}

	/**
	 * 
	 * @param bkf006 抢救成功次数
	 */
	public void setBkf006(String bkf006) {
		this.bkf006 = bkf006;
	}

	/**
	 * 
	 * @return 医保识别码
	 */
	public String getBkm029() {
		return bkm029;
	}

	/**
	 * 
	 * @param bkm029 医保识别码
	 */
	public void setBkm029(String bkm029) {
		this.bkm029 = bkm029;
	}

	/**
	 * 
	 * @return 基本药物标识
	 */
	public String getBkc190() {
		return bkc190;
	}

	/**
	 * 
	 * @param bkc190 基本药物标识
	 */
	public void setBkc190(String bkc190) {
		this.bkc190 = bkc190;
	}

	/**
	 * 
	 * @return 匹配ID
	 */
	public String getBkc194() {
		return bkc194;
	}

	/**
	 * 
	 * @param bkc194 匹配ID
	 */
	public void setBkc194(String bkc194) {
		this.bkc194 = bkc194;
	}

	/**
	 * 
	 * @return 异名ID
	 */
	public String getBkc109() {
		return bkc109;
	}

	/**
	 * 
	 * @param bkc109 异名ID
	 */
	public void setBkc109(String bkc109) {
		this.bkc109 = bkc109;
	}

	/**
	 * 
	 * @return 备注
	 */
	public String getAae013() {
		return aae013;
	}

	/**
	 * 
	 * @param aae013 备注
	 */
	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	/**
	 *
	 * @return 先自付比例
	 */
	public String getAka057() {
		return aka057;
	}

	/**
	 * 
	 * @param aka057 先自付比例
	 */
	public void setAka057(String aka057) {
		this.aka057 = aka057;
	}

	/**
	 * 
	 * @return 居民先自付比例
	 */
	public String getBkc106() {
		return bkc106;
	}

	/**
	 * 
	 * @param bkc106 居民先自付比例
	 */
	public void setBkc106(String bkc106) {
		this.bkc106 = bkc106;
	}

	/**
	 * 
	 * @return 目录等级
	 */
	public String getAka065() {
		return aka065;
	}

	/**
	 * 
	 * @param aka065 目录等级
	 */
	public void setAka065(String aka065) {
		this.aka065 = aka065;
	}

	/**
	 * 
	 * @return 退休延缴人员标识
	 */
	public String getAac084() {
		return aac084;
	}

	/**
	 * 
	 * @param aac084 退休延缴人员标识
	 */
	public void setAac084(String aac084) {
		this.aac084 = aac084;
	}

	/**
	 * 
	 * @return 到账日期的次月
	 */
	public String getBka784() {
		return bka784;
	}

	/**
	 * 
	 * @param bka784 到账日期的次月
	 */
	public void setBka784(String bka784) {
		this.bka784 = bka784;
	}

	/**
	 * 
	 * @return 到账日期(年月)
	 */
	public String getAae037() {
		return aae037;
	}

	/**
	 * 
	 * @param aae037 到账日期(年月)
	 */
	public void setAae037(String aae037) {
		this.aae037 = aae037;
	}

	/**
	 * 
	 * @return 台账日期的上月(年月)
	 */
	public String getBka783() {
		return bka783;
	}

	/**
	 * 
	 * @param bka783 台账日期的上月(年月)
	 */
	public void setBka783(String bka783) {
		this.bka783 = bka783;
	}

	/**
	 * 
	 * @return 零报下浮比例
	 */
	public String getBka782() {
		return bka782;
	}

	/**
	 * 
	 * @param bka782 零报下浮比例
	 */
	public void setBka782(String bka782) {
		this.bka782 = bka782;
	}

	/**
	 * 
	 * @return 取此待遇类型的二级目录使用标志“0”排除法“1”准入法“2”单病种“9”没有二级目录( special_range_use_flag)
	 */
	public String getBka781() {
		return bka781;
	}

	/**
	 * 
	 * @param bka781 取此待遇类型的二级目录使用标志“0”排除法“1”准入法“2”单病种“9”没有二级目录(
	 *               special_range_use_flag)
	 */
	public void setBka781(String bka781) {
		this.bka781 = bka781;
	}

	/**
	 * 
	 * @return 零报原因
	 */
	public String getBka408() {
		return bka408;
	}

	/**
	 * 
	 * @param bka408 零报原因
	 */
	public void setBka408(String bka408) {
		this.bka408 = bka408;
	}

	/**
	 * 
	 * @return 是否急诊
	 */
	public String getBka930() {
		return bka930;
	}

	/**
	 * 
	 * @param bka930 是否急诊
	 */
	public void setBka930(String bka930) {
		this.bka930 = bka930;
	}

	/**
	 * 
	 * @return 药费上浮情形
	 */
	public String getBka929() {
		return bka929;
	}

	/**
	 * 
	 * @param bka929 药费上浮情形
	 */
	public void setBka929(String bka929) {
		this.bka929 = bka929;
	}

	/**
	 * 
	 * @return 待遇算法用到(treat_pers_type)
	 */
	public String getBka780() {
		return bka780;
	}

	/**
	 * 
	 * @param bka780 待遇算法用到(treat_pers_type)
	 */
	public void setBka780(String bka780) {
		this.bka780 = bka780;
	}

	/**
	 * 
	 * @return 个人账户余额
	 */
	public String getBka889() {
		return bka889;
	}

	/**
	 * 
	 * @param bka889 个人账户余额
	 */
	public void setBka889(String bka889) {
		this.bka889 = bka889;
	}

	/**
	 * 
	 * @return 民族
	 */
	public String getAac005() {
		return aac005;
	}

	/**
	 * 
	 * @param aac005 民族
	 */
	public void setAac005(String aac005) {
		this.aac005 = aac005;
	}

	/**
	 * 
	 * @return 地址
	 */
	public String getAae006() {
		return aae006;
	}

	/**
	 * 
	 * @param aae006 地址
	 */
	public void setAae006(String aae006) {
		this.aae006 = aae006;
	}

	public String getAac009() {
		return aac009;
	}

	public void setAac009(String aac009) {
		this.aac009 = aac009;
	}

	public String getAac012() {
		return aac012;
	}

	public void setAac012(String aac012) {
		this.aac012 = aac012;
	}

	public String getAac015() {
		return aac015;
	}

	public void setAac015(String aac015) {
		this.aac015 = aac015;
	}

	/**
	 * 
	 * @return 经办日期
	 */
	public Date getAae036() {
		return aae036;
	}

	/**
	 * 
	 * @param aae036 经办日期
	 */
	public void setAae036(Date aae036) {
		this.aae036 = aae036;
	}

	/**
	 * 
	 * @return 医院审核标识
	 */
	public String getBke058() {
		return bke058;
	}

	/**
	 * 
	 * @param bke058 医院审核标识
	 */
	public void setBke058(String bke058) {
		this.bke058 = bke058;
	}

	public String getKc21id_last() {
		return kc21id_last;
	}

	public void setKc21id_last(String kc21id_last) {
		this.kc21id_last = kc21id_last;
	}

	public String getAkb020_last() {
		return akb020_last;
	}

	public void setAkb020_last(String akb020_last) {
		this.akb020_last = akb020_last;
	}

	/**
	 * 
	 * @return 就医登记号
	 */
	public String getAaz217_last() {
		return aaz217_last;
	}

	/**
	 * 
	 * @param aaz217_last 就医登记号
	 */
	public void setAaz217_last(String aaz217_last) {
		this.aaz217_last = aaz217_last;
	}

	/**
	 * 
	 * @return 定点医疗机构名称
	 */
	public String getAkb021_last() {
		return akb021_last;
	}

	/**
	 * 
	 * @param akb021_last 定点医疗机构名称
	 */
	public void setAkb021_last(String akb021_last) {
		this.akb021_last = akb021_last;
	}

	/**
	 * 
	 * @return 疾病名称
	 */
	public String getAka121_last() {
		return aka121_last;
	}

	/**
	 * 
	 * @param aka121_last 疾病名称
	 */
	public void setAka121_last(String aka121_last) {
		this.aka121_last = aka121_last;
	}

	/**
	 * 
	 * @return 业务开始日期
	 */
	public String getAae030_last() {
		return aae030_last;
	}

	/**
	 * 
	 * @param aae030_last 业务开始日期
	 */
	public void setAae030_last(String aae030_last) {
		this.aae030_last = aae030_last;
	}

	/**
	 * 
	 * @return 业务结束日期
	 */
	public String getAae031_last() {
		return aae031_last;
	}

	/**
	 * 
	 * @param aae031_last 业务结束日期
	 */
	public void setAae031_last(String aae031_last) {
		this.aae031_last = aae031_last;
	}

	/**
	 * 
	 * @return 基金冻结状态(0正常、1冻结、9未参保)
	 */
	public String getBka888() {
		return bka888;
	}

	/**
	 * 
	 * @param bka888 基金冻结状态(0正常、1冻结、9未参保)
	 */
	public void setBka888(String bka888) {
		this.bka888 = bka888;
	}

	public String getAae002() {
		return aae002;
	}

	public void setAae002(String aae002) {
		this.aae002 = aae002;
	}

	public String getAae003() {
		return aae003;
	}

	public void setAae003(String aae003) {
		this.aae003 = aae003;
	}

	public String getBae311() {
		return bae311;
	}

	public void setBae311(String bae311) {
		this.bae311 = bae311;
	}

	public String getBae312() {
		return bae312;
	}

	public void setBae312(String bae312) {
		this.bae312 = bae312;
	}

	public String getAaz159() {
		return aaz159;
	}

	public void setAaz159(String aaz159) {
		this.aaz159 = aaz159;
	}

	public String getAab999() {
		return aab999;
	}

	public void setAab999(String aab999) {
		this.aab999 = aab999;
	}

	/**
	 * 
	 * @return 社会救助类别(民政免缴类别)
	 */
	public String getBke956() {
		return bke956;
	}

	/**
	 * 
	 * @param bke956 社会救助类别(民政免缴类别)
	 */
	public void setBke956(String bke956) {
		this.bke956 = bke956;
	}

	public String getAac058() {
		return aac058;
	}

	public void setAac058(String aac058) {
		this.aac058 = aac058;
	}

	public String getAka083() {
		return aka083;
	}

	public void setAka083(String aka083) {
		this.aka083 = aka083;
	}

	public String getZyzje() {
		return zyzje;
	}

	public void setZyzje(String zyzje) {
		this.zyzje = zyzje;
	}

	public String getSbzfje() {
		return sbzfje;
	}

	public void setSbzfje(String sbzfje) {
		this.sbzfje = sbzfje;
	}

	public String getZhzfje() {
		return zhzfje;
	}

	public void setZhzfje(String zhzfje) {
		this.zhzfje = zhzfje;
	}

	public String getBfxmzfje() {
		return bfxmzfje;
	}

	public void setBfxmzfje(String bfxmzfje) {
		this.bfxmzfje = bfxmzfje;
	}

	public String getQfje() {
		return qfje;
	}

	public void setQfje(String qfje) {
		this.qfje = qfje;
	}

	public String getGrzfje1() {
		return grzfje1;
	}

	public void setGrzfje1(String grzfje1) {
		this.grzfje1 = grzfje1;
	}

	public String getGrzfje2() {
		return grzfje2;
	}

	public void setGrzfje2(String grzfje2) {
		this.grzfje2 = grzfje2;
	}

	public String getGrzfje3() {
		return grzfje3;
	}

	public void setGrzfje3(String grzfje3) {
		this.grzfje3 = grzfje3;
	}

	public String getCxzfje() {
		return cxzfje;
	}

	public void setCxzfje(String cxzfje) {
		this.cxzfje = cxzfje;
	}

	public String getYyfdje() {
		return yyfdje;
	}

	public void setYyfdje(String yyfdje) {
		this.yyfdje = yyfdje;
	}

	public String getCash_pay_com() {
		return cash_pay_com;
	}

	public void setCash_pay_com(String cash_pay_com) {
		this.cash_pay_com = cash_pay_com;
	}

	public String getAcct_pay_com() {
		return acct_pay_com;
	}

	public void setAcct_pay_com(String acct_pay_com) {
		this.acct_pay_com = acct_pay_com;
	}

	public String getCash_pay_own() {
		return cash_pay_own;
	}

	public void setCash_pay_own(String cash_pay_own) {
		this.cash_pay_own = cash_pay_own;
	}

	public String getAcct_pay_own() {
		return acct_pay_own;
	}

	public void setAcct_pay_own(String acct_pay_own) {
		this.acct_pay_own = acct_pay_own;
	}

	public String getOper_flag() {
		return oper_flag;
	}

	public void setOper_flag(String oper_flag) {
		this.oper_flag = oper_flag;
	}

	public String getSave_flag() {
		return save_flag;
	}

	public void setSave_flag(String save_flag) {
		this.save_flag = save_flag;
	}

	/**
	 * 
	 * @return 疾病名称
	 */
	public String getAka121() {
		return aka121;
	}

	/**
	 * 
	 * @param aka121 疾病名称
	 */
	public void setAka121(String aka121) {
		this.aka121 = aka121;
	}

	/**
	 * 
	 * @return 人员状态
	 */
	public String getAac013() {
		return aac013;
	}

	/**
	 * 
	 * @param aac013 人员状态
	 */
	public void setAac013(String aac013) {
		this.aac013 = aac013;
	}

	/**
	 * 
	 * @return 【行政区划代码参保地】
	 */
	public String getAab301() {
		return aab301;
	}

	/**
	 * 
	 * @param aab301 【行政区划代码参保地】
	 */
	public void setAab301(String aab301) {
		this.aab301 = aab301;
	}

	/**
	 * 
	 * @return 【行政区划代码就医地】
	 */
	public String getAab299() {
		return aab299;
	}

	/**
	 * 
	 * @param aab299 【行政区划代码就医地】
	 */
	public void setAab299(String aab299) {
		this.aab299 = aab299;
	}

	/**
	 * 
	 * @return 退休时间
	 */
	public String getBac002() {
		return bac002;
	}

	/**
	 * 
	 * @param bac002 退休时间
	 */
	public void setBac002(String bac002) {
		this.bac002 = bac002;
	}

	/**
	 * 
	 * @return 人员参保状态
	 */
	public String getAac008() {
		return aac008;
	}

	/**
	 * 
	 * @param aac008 人员参保状态
	 */
	public void setAac008(String aac008) {
		this.aac008 = aac008;
	}

	/**
	 * 
	 * @return 个人缴费状态0正常1冻结2暂停参保3中止参保9未参保
	 */
	public String getAac031() {
		return aac031;
	}

	/**
	 * 
	 * @param aac031 个人缴费状态0正常1冻结2暂停参保3中止参保9未参保
	 */
	public void setAac031(String aac031) {
		this.aac031 = aac031;
	}

	/**
	 * 
	 * @return 开始日期
	 */
	public String getAae030() {
		return aae030;
	}

	/**
	 * 
	 * @param aae030 开始日期
	 */
	public void setAae030(String aae030) {
		this.aae030 = aae030;
	}

	/**
	 * 
	 * @return 结束日期
	 */
	public String getAae031() {
		return aae031;
	}

	/**
	 * 
	 * @param aae031 结束日期
	 */
	public void setAae031(String aae031) {
		this.aae031 = aae031;
	}

	/**
	 * 
	 * @return 个人参保变更日期
	 */
	public String getAae035() {
		return aae035;
	}

	/**
	 * 
	 * @param aae035 个人参保变更日期
	 */
	public void setAae035(String aae035) {
		this.aae035 = aae035;
	}

	/**
	 * 
	 * @return 个人参保变更类型
	 */
	public String getAac050() {
		return aac050;
	}

	/**
	 * 
	 * @param aac050 个人参保变更类型
	 */
	public void setAac050(String aac050) {
		this.aac050 = aac050;
	}

	/**
	 * 
	 * @return 医保年度【业务期间】
	 */
	public String getAae001() {
		return aae001;
	}

	/**
	 * 
	 * @param aae001 医保年度【业务期间】
	 */
	public void setAae001(String aae001) {
		this.aae001 = aae001;
	}

	/**
	 * 
	 * @return 医院级别
	 */
	public String getBkc110() {
		return bkc110;
	}

	/**
	 * 
	 * @param bkc110 医院级别
	 */
	public void setBkc110(String bkc110) {
		this.bkc110 = bkc110;
	}

	/**
	 * 
	 * @return 视同社区标识
	 */
	public String getBkc116() {
		return bkc116;
	}

	/**
	 * 
	 * @param bkc116 视同社区标识
	 */
	public void setBkc116(String bkc116) {
		this.bkc116 = bkc116;
	}

	/**
	 * 
	 * @return 城居门诊专科标识
	 */
	public String getBkc115() {
		return bkc115;
	}

	/**
	 * 
	 * @param bkc115 城居门诊专科标识
	 */
	public void setBkc115(String bkc115) {
		this.bkc115 = bkc115;
	}

	/**
	 * 
	 * @return 医疗机构类别
	 */
	public String getAkb023() {
		return akb023;
	}

	/**
	 * 
	 * @param akb023 医疗机构类别
	 */
	public void setAkb023(String akb023) {
		this.akb023 = akb023;
	}

	/**
	 * 
	 * @return 学校标准标记
	 */
	public String getBkc205() {
		return bkc205;
	}

	/**
	 * 
	 * @param bkc205 学校标准标记
	 */
	public void setBkc205(String bkc205) {
		this.bkc205 = bkc205;
	}

	/**
	 * 
	 * @return 城职门诊专科标识
	 */
	public String getBkc118() {
		return bkc118;
	}

	/**
	 * 
	 * @param bkc118 城职门诊专科标识
	 */
	public void setBkc118(String bkc118) {
		this.bkc118 = bkc118;
	}

	/**
	 * 
	 * @return 政府办基层医疗机构标识
	 */
	public String getBkc119() {
		return bkc119;
	}

	/**
	 * 
	 * @param bkc119 政府办基层医疗机构标识
	 */
	public void setBkc119(String bkc119) {
		this.bkc119 = bkc119;
	}

	/**
	 * 
	 * @return 医院等级
	 */
	public String getAka101() {
		return aka101;
	}

	/**
	 * 
	 * @param aka101 医院等级
	 */
	public void setAka101(String aka101) {
		this.aka101 = aka101;
	}

	/**
	 * 
	 * @return 外地医院标识
	 */
	public String getBkc111() {
		return bkc111;
	}

	/**
	 * 
	 * @param bkc111 外地医院标识
	 */
	public void setBkc111(String bkc111) {
		this.bkc111 = bkc111;
	}

	/**
	 * 
	 * @return 待遇类型名称
	 */
	public String getBka155() {
		return bka155;
	}

	/**
	 * 
	 * @param bka155 待遇类型名称
	 */
	public void setBka155(String bka155) {
		this.bka155 = bka155;
	}

	/**
	 * 
	 * @return 单病种标志
	 */
	public String getBka156() {
		return bka156;
	}

	/**
	 * 
	 * @param bka156 单病种标志
	 */
	public void setBka156(String bka156) {
		this.bka156 = bka156;
	}

	/**
	 * 
	 * @return 医疗证号
	 */
	public String getBac028() {
		return bac028;
	}

	/**
	 * 
	 * @param bac028 医疗证号
	 */
	public void setBac028(String bac028) {
		this.bac028 = bac028;
	}

	/**
	 * 
	 * @return 泛珠异地标识
	 */
	public String getBke932() {
		return bke932;
	}

	/**
	 * 
	 * @param bke932 泛珠异地标识
	 */
	public void setBke932(String bke932) {
		this.bke932 = bke932;
	}

	public String getKc21id() {
		return kc21id;
	}

	public void setKc21id(String kc21id) {
		this.kc21id = kc21id;
	}

	/**
	 * 
	 * @return 医院编号
	 */
	public String getAkb020() {
		return akb020;
	}

	/**
	 * 
	 * @param akb020 医院编号
	 */
	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}

	/**
	 * 
	 * @return 就医登记号
	 */
	public String getAaz217() {
		return aaz217;
	}

	/**
	 * 
	 * @param aaz217 就医登记号
	 */
	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217;
	}

	/**
	 * 
	 * @return 费用批次
	 */
	public String getBka001() {
		return bka001;
	}

	/**
	 * 
	 * @param bka001 费用批次
	 */
	public void setBka001(String bka001) {
		this.bka001 = bka001;
	}

	/**
	 * 
	 * @return 病例分型序号
	 */
	public String getBka009() {
		return bka009;
	}

	/**
	 * 
	 * @param bka009 病例分型序号
	 */
	public void setBka009(String bka009) {
		this.bka009 = bka009;
	}

	/**
	 * 
	 * @return 【业务类别编号】
	 */
	public String getAka130() {
		return aka130;
	}

	/**
	 * 
	 * @param aka130 【业务类别编号】
	 */
	public void setAka130(String aka130) {
		this.aka130 = aka130;
	}

	/**
	 * 
	 * @return 内部序数
	 */
	public String getBka002() {
		return bka002;
	}

	/**
	 * 
	 * @param bka002 内部序数
	 */
	public void setBka002(String bka002) {
		this.bka002 = bka002;
	}

	/**
	 * 
	 * @return 当事人所在统筹区
	 */
	public String getBaa027() {
		return baa027;
	}

	/**
	 * 
	 * @param baa027 当事人所在统筹区
	 */
	public void setBaa027(String baa027) {
		this.baa027 = baa027;
	}

	/**
	 * 
	 * @return 【电脑号】
	 */
	public String getAac001() {
		if (aac001 != null) {
			aac001 = aac001.trim();
		}
		return aac001;
	}

	/**
	 * 
	 * @param aac001 【电脑号】
	 */
	public void setAac001(String aac001) {
		if (aac001 != null) {
			aac001 = aac001.trim();
		}
		this.aac001 = aac001;
	}

	/**
	 * 
	 * @return 姓名
	 */
	public String getAac003() {
		if (aac003 != null) {
			aac003 = aac003.trim();
		}
		return aac003;
	}

	/**
	 * 
	 * @param aac003 姓名
	 */
	public void setAac003(String aac003) {
		if (aac003 != null) {
			aac003 = aac003.trim();
		}
		this.aac003 = aac003;
	}

	/**
	 * 
	 * @return 性别
	 */
	public String getAac004() {
		return aac004;
	}

	/**
	 * 
	 * @param aac004 性别
	 */
	public void setAac004(String aac004) {
		this.aac004 = aac004;
	}

	/**
	 * 
	 * @return 【人员类别编码】
	 */
	public String getBka004() {
		return bka004;
	}

	/**
	 * 
	 * @param bka004 【人员类别编码】
	 */
	public void setBka004(String bka004) {
		this.bka004 = bka004;
	}

	public String getBka005() {
		return bka005;
	}

	public void setBka005(String bka005) {
		this.bka005 = bka005;
	}
    
	
	//修改概要：TS19081500017 - 【需求开发】结算云用身份证刷卡的时候，如果身份证最后一位是字母，希望自动转换为大写并去除空格
	//修改描述：将aac002的值转大写
	//修改人及修改时间：李嘉伦 20190815
	/**
	 * 
	 * @return 社会保障号码
	 */
	public String getAac002() {

		if (aac002 != null) {

			aac002 = aac002.trim().toUpperCase();

		}

		return aac002;
	}

	/**
	 * 
	 * @param aac002 社会保障号码
	 */
	public void setAac002(String aac002) {

		if (aac002 != null) {

			aac002 = aac002.trim().toUpperCase();
		}

		this.aac002 = aac002;
	}

	/**
	 * 
	 * @return 社保卡号
	 */
	public String getBka100() {
		return bka100;
	}

	/**
	 * 
	 * @param bka100 社保卡号
	 */
	public void setBka100(String bka100) {
		this.bka100 = bka100;
	}

	/**
	 * 
	 * @return 定点医疗机构名称
	 */
	public String getAkb021() {
		return akb021;
	}

	/**
	 * 
	 * @param akb021 定点医疗机构名称
	 */
	public void setAkb021(String akb021) {
		this.akb021 = akb021;
	}

	/**
	 * 
	 * @return 出生日期
	 */
	public String getAac006() {
		return aac006;
	}

	/**
	 * 
	 * @param aac006 出生日期
	 */
	public void setAac006(String aac006) {
		this.aac006 = aac006;
	}

	public String getAae005() {
		return aae005;
	}

	public void setAae005(String aae005) {
		this.aae005 = aae005;
	}

	public String getAab001() {
		return aab001;
	}

	public void setAab001(String aab001) {
		this.aab001 = aab001;
	}

	public String getBka008() {
		return bka008;
	}

	public void setBka008(String bka008) {
		this.bka008 = bka008;
	}

	public String getAab019() {
		return aab019;
	}

	public void setAab019(String aab019) {
		this.aab019 = aab019;
	}

	/**
	 * 
	 * @return 【待遇类型编码】
	 */
	public String getBka006() {
		return bka006;
	}

	/**
	 * 
	 * @param bka006 【待遇类型编码】
	 */
	public void setBka006(String bka006) {
		this.bka006 = bka006;
	}

	public String getBka010() {
		return bka010;
	}

	public void setBka010(String bka010) {
		this.bka010 = bka010;
	}

	/**
	 * 
	 * @return 关联医疗机构编码
	 */
	public String getBka011() {
		return bka011;
	}

	/**
	 * 
	 * @param bka011 关联医疗机构编码
	 */
	public void setBka011(String bka011) {
		this.bka011 = bka011;
	}

	/**
	 * 
	 * @return 关联业务序列号
	 */
	public String getBka012() {
		return bka012;
	}

	/**
	 * 
	 * @param bka012 关联业务序列号
	 */
	public void setBka012(String bka012) {
		this.bka012 = bka012;
	}

	/**
	 * 
	 * @return 申请序列号
	 */
	public String getAaz267() {
		return aaz267;
	}

	/**
	 * 
	 * @param aaz267 申请序列号
	 */
	public void setAaz267(String aaz267) {
		this.aaz267 = aaz267;
	}

	/**
	 * 
	 * @return 业务登记日期
	 */
	public String getBka013() {
		return bka013;
	}

	/**
	 * 
	 * @param bka013 业务登记日期
	 */
	public void setBka013(String bka013) {
		this.bka013 = bka013;
	}

	/**
	 * 
	 * @return 登记人
	 */
	public String getBka015() {
		return bka015;
	}

	/**
	 * 
	 * @param bka015 登记人
	 */
	public void setBka015(String bka015) {
		this.bka015 = bka015;
	}

	/**
	 * 
	 * @return 登记标志(0：正常 1：转院 2：二次返院（审批通过后rela_serial_no为空） 3：急诊留观转住院 4：90天或180天结算
	 *         5:中途结算)
	 */
	public String getBka016() {
		return bka016;
	}

	/**
	 * 
	 * @param bka016 登记标志(0：正常 1：转院 2：二次返院（审批通过后rela_serial_no为空） 3：急诊留观转住院
	 *               4：90天或180天结算 5:中途结算)
	 */
	public void setBka016(String bka016) {
		this.bka016 = bka016;
	}

	/**
	 * 
	 * @return 业务开始日期
	 */
	public String getBka017() {
		return bka017;
	}

	/**
	 * 
	 * @param bka017 业务开始日期
	 */
	public void setBka017(String bka017) {
		this.bka017 = bka017;
	}

	/**
	 * 业务开始情况
	 * 
	 * @return
	 */
	public String getBka018() {
		return bka018;
	}

	/**
	 * 业务开始情况
	 * 
	 * @param bka018
	 */
	public void setBka018(String bka018) {
		this.bka018 = bka018;
	}

	/**
	 * 
	 * @return 入院科室
	 */
	public String getAkf001() {
		return akf001;
	}

	/**
	 * 
	 * @param akf001 入院科室
	 */
	public void setAkf001(String akf001) {
		this.akf001 = akf001;
	}

	/**
	 * 
	 * @return 入院科室名称
	 */
	public String getBka020() {
		return bka020;
	}

	/**
	 * 
	 * @param bka020 入院科室名称
	 */
	public void setBka020(String bka020) {
		this.bka020 = bka020;
	}

	/**
	 * 
	 * @return 入院病区
	 */
	public String getBka021() {
		return bka021;
	}

	/**
	 * 
	 * @param bka021 入院病区
	 */
	public void setBka021(String bka021) {
		this.bka021 = bka021;
	}

	/**
	 * 
	 * @return 入院病区名称
	 */
	public String getBka022() {
		return bka022;
	}

	/**
	 * 
	 * @param bka022 入院病区名称
	 */
	public void setBka022(String bka022) {
		this.bka022 = bka022;
	}

	/**
	 * 
	 * @return 入院床位号
	 */
	public String getAke020() {
		return ake020;
	}

	/**
	 * 
	 * @param ake020 入院床位号
	 */
	public void setAke020(String ake020) {
		this.ake020 = ake020;
	}

	public String getBka024() {
		return bka024;
	}

	public void setBka024(String bka024) {
		this.bka024 = bka024;
	}

	/**
	 * 
	 * @return 住院号
	 */
	public String getBka025() {
		return bka025;
	}

	/**
	 * 
	 * @param bka025 住院号
	 */
	public void setBka025(String bka025) {
		this.bka025 = bka025;
	}

	/**
	 * 
	 * @return 入院疾病诊断
	 */
	public String getAkc193() {
		return akc193;
	}

	/**
	 * 
	 * @param bka026 入院疾病诊断
	 */
	public void setAkc193(String akc193) {
		this.akc193 = akc193;
	}

	/**
	 * 
	 * @return 预付款总额
	 */
	public String getBka245() {
		return bka245;
	}

	/**
	 * 
	 * @param bka245 预付款总额
	 */
	public void setBka245(String bka245) {
		this.bka245 = bka245;
	}

	/**
	 * 
	 * @return 确诊日期
	 */
	public String getBka028() {
		return bka028;
	}

	/**
	 * 
	 * @param bka028 确诊日期
	 */
	public void setBka028(String bka028) {
		this.bka028 = bka028;
	}

	/**
	 * 
	 * @return 确诊疾病诊断
	 */
	public String getBka029() {
		return bka029;
	}

	/**
	 * 
	 * @param bka029 确诊疾病诊断
	 */
	public void setBka029(String bka029) {
		this.bka029 = bka029;
	}

	/**
	 * 
	 * @return 住院天数
	 */
	public String getBka030() {
		return bka030;
	}

	/**
	 * 
	 * @param bka030 住院天数
	 */
	public void setBka030(String bka030) {
		this.bka030 = bka030;
	}

	/**
	 * 
	 * @return 出院疾病诊断
	 */
	public String getAkc196() {
		return akc196;
	}

	/**
	 * 
	 * @param akc196 出院疾病诊断
	 */
	public void setAkc196(String akc196) {
		this.akc196 = akc196;
	}

	/**
	 * 
	 * @return 业务结束日期
	 */
	public String getBka032() {
		return bka032;
	}

	/**
	 * 
	 * @param bka032 业务结束日期
	 */
	public void setBka032(String bka032) {
		this.bka032 = bka032;
	}

	/**
	 * 
	 * @return 结束人工号
	 */
	public String getBka033() {
		return bka033;
	}

	/**
	 * 
	 * @param bka033 结束人工号
	 */
	public void setBka033(String bka033) {
		this.bka033 = bka033;
	}

	/**
	 * 
	 * @return 结束人
	 */
	public String getBka034() {
		return bka034;
	}

	/**
	 * 
	 * @param bka034 结束人
	 */
	public void setBka034(String bka034) {
		this.bka034 = bka034;
	}

	/**
	 * 
	 * @return 业务终结情况
	 */
	public String getBka035() {
		return bka035;
	}

	/**
	 * 
	 * @param bka035 业务终结情况
	 */
	public void setBka035(String bka035) {
		this.bka035 = bka035;
	}

	/**
	 * 
	 * @return 用卡标志
	 */
	public String getBka036() {
		return bka036;
	}

	/**
	 * 
	 * @param bka036 用卡标志
	 */
	public void setBka036(String bka036) {
		this.bka036 = bka036;
	}

	/**
	 * 
	 * @return 中心报帐标志
	 */
	public String getBka037() {
		return bka037;
	}

	/**
	 * 
	 * @param bka037 中心报帐标志
	 */
	public void setBka037(String bka037) {
		this.bka037 = bka037;
	}

	public String getBka038() {
		return bka038;
	}

	public void setBka038(String bka038) {
		this.bka038 = bka038;
	}

	public String getBka039() {
		return bka039;
	}

	public void setBka039(String bka039) {
		this.bka039 = bka039;
	}

	public String getBka041() {
		return bka041;
	}

	public void setBka041(String bka041) {
		this.bka041 = bka041;
	}

	/**
	 * 
	 * @return 工伤生育凭证号
	 */
	public String getBka042() {
		return bka042;
	}

	/**
	 * 
	 * @param bka042 工伤生育凭证号
	 */
	public void setBka042(String bka042) {
		this.bka042 = bka042;
	}

	/**
	 * 
	 * @return 病情备注
	 */
	public String getAke024() {
		return ake024;
	}

	/**
	 * 
	 * @param bka043 病情备注
	 */
	public void setAke024(String bka043) {
		this.ake024 = bka043;
	}

	/**
	 * 
	 * @return 传输标志
	 */
	public String getBka044() {
		return bka044;
	}

	/**
	 * 
	 * @param bka044 传输标志
	 */
	public void setBka044(String bka044) {
		this.bka044 = bka044;
	}

	/**
	 * 
	 * @return 业务完成日期
	 */
	public String getBka045() {
		return bka045;
	}

	/**
	 * 
	 * @param bka045 业务完成日期
	 */
	public void setBka045(String bka045) {
		this.bka045 = bka045;
	}

	/**
	 * 
	 * @return 完成人工号
	 */
	public String getBka046() {
		return bka046;
	}

	/**
	 * 
	 * @param bka046 完成人工号
	 */
	public void setBka046(String bka046) {
		this.bka046 = bka046;
	}

	/**
	 * 
	 * @return 完成人
	 */
	public String getBka047() {
		return bka047;
	}

	/**
	 * 
	 * @param bka047 完成人
	 */
	public void setBka047(String bka047) {
		this.bka047 = bka047;
	}

	public String getBka048() {
		return bka048;
	}

	public void setBka048(String bka048) {
		this.bka048 = bka048;
	}

	public String getBka050() {
		return bka050;
	}

	public void setBka050(String bka050) {
		this.bka050 = bka050;
	}

	/**
	 * 
	 * @return 所属统筹中心
	 */
	public String getAaa027() {
		return aaa027;
	}

	/**
	 * 
	 * @param aaa027 所属统筹中心
	 */
	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	/**
	 * 
	 * @return 【险种编码】
	 */
	public String getAae140() {
		return aae140;
	}

	/**
	 * 
	 * @param aae140 【险种编码】
	 */
	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}

	public String getBka414() {
		return bka414;
	}

	public void setBka414(String bka414) {
		this.bka414 = bka414;
	}

	/**
	 * 
	 * @return
	 */
	public String getBka415() {
		return bka415;
	}

	/**
	 * 
	 * @param bka415
	 */
	public void setBka415(String bka415) {
		this.bka415 = bka415;
	}

	public String getBae009() {
		return bae009;
	}

	public void setBae009(String bae009) {
		this.bae009 = bae009;
	}

	public String getBaf313() {
		return baf313;
	}

	public void setBaf313(String baf313) {
		this.baf313 = baf313;
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	/**
	 * 
	 * @return 统筹区编码
	 */
	public String getBka501() {
		return bka501;
	}

	/**
	 * 
	 * @param bka501 统筹区编码
	 */
	public void setBka501(String bka501) {
		this.bka501 = bka501;
	}

	/**
	 * 
	 * @return 医院级别
	 */
	public String getBka502() {
		return bka502;
	}

	/**
	 * 
	 * @param bka502 医院级别
	 */
	public void setBka502(String bka502) {
		this.bka502 = bka502;
	}

	/**
	 * 
	 * @return 医保医师编号
	 */
	public String getAke022() {
		return ake022;
	}

	/**
	 * 
	 * @param ake022 医保医师编号
	 */
	public void setAke022(String ake022) {
		this.ake022 = ake022;
	}

	public String getBka504() {
		return bka504;
	}

	public void setBka504(String bka504) {
		this.bka504 = bka504;
	}

	public String getBka505() {
		return bka505;
	}

	public void setBka505(String bka505) {
		this.bka505 = bka505;
	}

	public String getBka506() {
		return bka506;
	}

	public void setBka506(String bka506) {
		this.bka506 = bka506;
	}

	/**
	 * 
	 * @return 医疗期ID
	 */
	public String getBka507() {
		return bka507;
	}

	/**
	 * 
	 * @param bka507 医疗期ID
	 */
	public void setBka507(String bka507) {
		this.bka507 = bka507;
	}

	/**
	 * 
	 * @return 未就业配偶标志（1是 ，0否）
	 */
	public String getBka508() {
		return bka508;
	}

	/**
	 * 
	 * @param bka508 未就业配偶标志（1是 ，0否）
	 */
	public void setBka508(String bka508) {
		this.bka508 = bka508;
	}

	/**
	 * 
	 * @return 银行ID
	 */
	public String getBka509() {
		return bka509;
	}

	/**
	 * 
	 * @param bka509 银行ID
	 */
	public void setBka509(String bka509) {
		this.bka509 = bka509;
	}

	/**
	 * 
	 * @return 账号
	 */
	public String getBka510() {
		return bka510;
	}

	/**
	 * 
	 * @param bka510 账号
	 * 
	 */
	public void setBka510(String bka510) {
		this.bka510 = bka510;
	}

	public String getKc21() {
		return kc21;
	}

	public void setKc21(String kc21) {
		this.kc21 = kc21;
	}

	public String getKc22id() {
		return kc22id;
	}

	public void setKc22id(String kc22id) {
		this.kc22id = kc22id;
	}

	/**
	 * 
	 * @return 费用序列号
	 */
	public String getAaz213() {
		return aaz213;
	}

	/**
	 * 
	 * @param aaz213 费用序列号
	 */
	public void setAaz213(String aaz213) {
		this.aaz213 = aaz213;
	}

	public String getAka063() {
		return aka063;
	}

	public void setAka063(String aka063) {
		this.aka063 = aka063;
	}

	/**
	 * 
	 * @return 项目药品类型
	 */
	public String getAke003() {
		return ake003;
	}

	/**
	 * 
	 * @param ake003 项目药品类型
	 */
	public void setAke003(String ake003) {
		this.ake003 = ake003;
	}

	/**
	 * 
	 * @return 中心药品项目编码
	 */
	public String getAke001() {
		return ake001;
	}

	/**
	 * 
	 * @param ake001 中心药品项目编码
	 */
	public void setAke001(String ake001) {
		this.ake001 = ake001;
	}

	/**
	 * 
	 * @return 中心药品项目名称
	 */
	public String getAke002() {
		return ake002;
	}

	/**
	 * 
	 * @param ake002 中心药品项目名称
	 */
	public void setAke002(String ake002) {
		this.ake002 = ake002;
	}

	/**
	 * 
	 * @return 医院药品项目编码
	 */
	public String getAke005() {
		return ake005;
	}

	/**
	 * 
	 * @param ake005 医院药品项目编码
	 */
	public void setAke005(String ake005) {
		this.ake005 = ake005;
	}

	/**
	 * 
	 * @return 医院药品项目名称
	 */
	public String getAke006() {
		return ake006;
	}

	/**
	 * 
	 * @param ake006 医院药品项目名称
	 */
	public void setAke006(String ake006) {
		this.ake006 = ake006;
	}

	public String getBka055() {
		return bka055;
	}

	public void setBka055(String bka055) {
		this.bka055 = bka055;
	}

	/**
	 * 
	 * @return 单价
	 */
	public BigDecimal getBka040() {
		return bka040;
	}

	/**
	 * 
	 * @param bka040 单价
	 */
	public void setBka040(BigDecimal bka040) {
		this.bka040 = bka040;
	}

	/**
	 * 
	 * @return 用量
	 */
	public BigDecimal getAkc226() {
		return akc226;
	}

	/**
	 * 
	 * @param akc226 用量
	 */
	public void setAkc226(BigDecimal akc226) {
		this.akc226 = akc226;
	}

	/**
	 * 
	 * @return 金额
	 */
	public BigDecimal getAae019() {
		return aae019;
	}

	/**
	 * 
	 * @param aae019 金额
	 */
	public void setAae019(BigDecimal aae019) {
		this.aae019 = aae019;
	}

	public String getBka059() {
		return bka059;
	}

	public void setBka059(String bka059) {
		this.bka059 = bka059;
	}

	public String getBka060() {
		return bka060;
	}

	public void setBka060(String bka060) {
		this.bka060 = bka060;
	}

	public String getBka061() {
		return bka061;
	}

	public void setBka061(String bka061) {
		this.bka061 = bka061;
	}

	public String getBka062() {
		return bka062;
	}

	public void setBka062(String bka062) {
		this.bka062 = bka062;
	}

	/**
	 * 
	 * @return 录入人工号
	 */
	public String getBka063() {
		return bka063;
	}

	/**
	 * 
	 * @param bka063 录入人工号
	 */
	public void setBka063(String bka063) {
		this.bka063 = bka063;
	}

	/**
	 * 
	 * @return 录入人
	 */
	public String getBka064() {
		return bka064;
	}

	/**
	 * 
	 * @param bka064 录入人
	 */
	public void setBka064(String bka064) {
		this.bka064 = bka064;
	}

	/**
	 * 
	 * @return 费用录入日期
	 */
	public String getBka065() {
		return bka065;
	}

	/**
	 * 
	 * @param bka065 费用录入日期
	 */
	public void setBka065(String bka065) {
		this.bka065 = bka065;
	}

	/**
	 * 
	 * @return 费用冻结标
	 */
	public String getBka067() {
		return bka067;
	}

	/**
	 * 
	 * @param bka067 费用冻结标
	 */
	public void setBka067(String bka067) {
		this.bka067 = bka067;
	}

	public String getBka068() {
		return bka068;
	}

	public void setBka068(String bka068) {
		this.bka068 = bka068;
	}

	public String getBka069() {
		return bka069;
	}

	public void setBka069(String bka069) {
		this.bka069 = bka069;
	}

	public String getBka070() {
		return bka070;
	}

	public void setBka070(String bka070) {
		this.bka070 = bka070;
	}

	public String getBka071() {
		return bka071;
	}

	public void setBka071(String bka071) {
		this.bka071 = bka071;
	}

	public String getBka072() {
		return bka072;
	}

	public void setBka072(String bka072) {
		this.bka072 = bka072;
	}

	public String getBka073() {
		return bka073;
	}

	public void setBka073(String bka073) {
		this.bka073 = bka073;
	}

	public String getBka074() {
		return bka074;
	}

	public void setBka074(String bka074) {
		this.bka074 = bka074;
	}

	public String getBka075() {
		return bka075;
	}

	public void setBka075(String bka075) {
		this.bka075 = bka075;
	}

	/**
	 * 
	 * @return 审核标志
	 */
	public String getBka076() {
		return bka076;
	}

	/**
	 * 
	 * @param bka076 审核标志
	 */
	public void setBka076(String bka076) {
		this.bka076 = bka076;
	}

	public String getBka511() {
		return bka511;
	}

	public void setBka511(String bka511) {
		this.bka511 = bka511;
	}

	public String getBka512() {
		return bka512;
	}

	public void setBka512(String bka512) {
		this.bka512 = bka512;
	}

	public String getKc22() {
		return kc22;
	}

	public void setKc22(String kc22) {
		this.kc22 = kc22;
	}

	public String getKc27id() {
		return kc27id;
	}

	public void setKc27id(String kc27id) {
		this.kc27id = kc27id;
	}

	public String getAaz215() {
		return aaz215;
	}

	public void setAaz215(String aaz215) {
		this.aaz215 = aaz215;
	}

	public String getBka077() {
		return bka077;
	}

	public void setBka077(String bka077) {
		this.bka077 = bka077;
	}

	public String getBka078() {
		return bka078;
	}

	public void setBka078(String bka078) {
		this.bka078 = bka078;
	}

	public String getAaa157() {
		return aaa157;
	}

	public void setAaa157(String aaa157) {
		this.aaa157 = aaa157;
	}

	public String getBka080() {
		return bka080;
	}

	public void setBka080(String bka080) {
		this.bka080 = bka080;
	}

	public String getBka432() {
		return bka432;
	}

	public void setBka432(String bka432) {
		this.bka432 = bka432;
	}

	public String getKc27() {
		return kc27;
	}

	public void setKc27(String kc27) {
		this.kc27 = kc27;
	}

	public String getKc28id() {
		return kc28id;
	}

	public void setKc28id(String kc28id) {
		this.kc28id = kc28id;
	}

	public String getBka081() {
		return bka081;
	}

	public void setBka081(String bka081) {
		this.bka081 = bka081;
	}

	public String getBka082() {
		return bka082;
	}

	public void setBka082(String bka082) {
		this.bka082 = bka082;
	}

	public String getBka437() {
		return bka437;
	}

	public void setBka437(String bka437) {
		this.bka437 = bka437;
	}

	public String getKc28() {
		return kc28;
	}

	public void setKc28(String kc28) {
		this.kc28 = kc28;
	}

	public String getKcd1id() {
		return kcd1id;
	}

	public void setKcd1id(String kcd1id) {
		this.kcd1id = kcd1id;
	}

	public String getKcd1() {
		return kcd1;
	}

	public void setKcd1(String kcd1) {
		this.kcd1 = kcd1;
	}

	public String getKcd2id() {
		return kcd2id;
	}

	public void setKcd2id(String kcd2id) {
		this.kcd2id = kcd2id;
	}

	public String getKcd2() {
		return kcd2;
	}

	public void setKcd2(String kcd2) {
		this.kcd2 = kcd2;
	}

	public String getKcd7id() {
		return kcd7id;
	}

	public void setKcd7id(String kcd7id) {
		this.kcd7id = kcd7id;
	}

	public String getKcd7() {
		return kcd7;
	}

	public void setKcd7(String kcd7) {
		this.kcd7 = kcd7;
	}

	public String getKcd8id() {
		return kcd8id;
	}

	public void setKcd8id(String kcd8id) {
		this.kcd8id = kcd8id;
	}

	public String getKcd8() {
		return kcd8;
	}

	public void setKcd8(String kcd8) {
		this.kcd8 = kcd8;
	}

	public String getKce4id() {
		return kce4id;
	}

	public void setKce4id(String kce4id) {
		this.kce4id = kce4id;
	}

	public String getKce4() {
		return kce4;
	}

	public void setKce4(String kce4) {
		this.kce4 = kce4;
	}

	public String getKce5id() {
		return kce5id;
	}

	public void setKce5id(String kce5id) {
		this.kce5id = kce5id;
	}

	public String getKce5() {
		return kce5;
	}

	public void setKce5(String kce5) {
		this.kce5 = kce5;
	}

	public String getAaa100() {
		return aaa100;
	}

	public void setAaa100(String aaa100) {
		this.aaa100 = aaa100;
	}

	public String getAaa102() {
		return aaa102;
	}

	public void setAaa102(String aaa102) {
		this.aaa102 = aaa102;
	}

	public String getAaa104() {
		return aaa104;
	}

	public void setAaa104(String aaa104) {
		this.aaa104 = aaa104;
	}

	public String getBka513() {
		return bka513;
	}

	public void setBka513(String bka513) {
		this.bka513 = bka513;
	}

	public String getAka070() {
		return aka070;
	}

	public void setAka070(String aka070) {
		this.aka070 = aka070;
	}

	public String getAka074() {
		return aka074;
	}

	public void setAka074(String aka074) {
		this.aka074 = aka074;
	}

	/**
	 * 
	 * @return 是否外伤(1.是 2.否)
	 */
	public String getAlc023() {
		return alc023;
	}

	/**
	 * 
	 * @param 是否外伤(1.是 2.否)
	 */
	public void setAlc023(String alc023) {
		this.alc023 = alc023;
	}

	@Override
	public String toString() {

		return this.toJson();
	}

	@Override
	public String toJson() {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		return gson.toJson(this);
	}

	@Override
	public boolean compareTo(Object obj) {

		return this.toString().equals(obj.toString());
	}

}
