package com.powersi.biz.medicare.special.pojo;

import java.io.Serializable;

public class MedicareSpecDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 经办人 **/
	private String aae011;

	/** 经办时间 **/
	private String aae036;

	/** 复核人 **/
	private String aae014;

	/** 复核时间 **/
	// private String aae015;
	private String aae015;

	/** 复核标志 **/
	private String aae016;

	/** 有效标志(0:无效 1:有效) **/
	private String aae100;

	/** 备注 **/
	private String aae013;

	/** 审批人 **/
	private String pke021;

	/** 初审时间 **/
	private String pke022;

	/** 初审标志 **/
	private String pke023;

	/** 初审意见 **/
	private String pke024;

	/** 公民身份号码 **/
	private String aac002;

	/** 定点医疗机构ID **/
	private String aaz107;

	/** 变更日期_开始 **/
	// private String aae035_start;
	private String aae035_start;

	/** 变更日期_结束 **/
	// private String aae035_end;
	private String aae035_end;

	/** 申请日期_开始 **/
	private String aae127_start;

	/** 申请日期_结束 **/
	private String aae127_end;

	/** 医院编码 **/
	private String akb020;

	/** 医疗服务机构名称 **/
	private String akb021;

	/** 申请原因 **/
	private String akc030;

	/** 详细地址 **/
	private String pke001;

	/** 诊断依据 **/
	private String pke002;

	/** 推荐病种（关联AKA120 -疾病编码(中心)） **/
	private String pke003;

	/** 并发症（关联AKA120） **/
	private String pke004;

	/** 分型（新增码表） **/
	private String pke005;

	/** 分型（新增码表）名称 **/
	private String pke005_name;

	/** 推荐病种（关联AKA120 -疾病编码(中心)）名称 **/
	private String pke003_name;

	/** 病情严重等级（新增码表） **/
	private String pke006;

	/** 病情严重等级（新增码表）名称 **/
	private String pke006_name;

	/** 并发症（关联AKA120）名称 **/
	private String pke004_name;

	/** 专家鉴定病种（关联AKA120） **/
	private String pke025;

	/** 专家鉴定并发症（关联AKA120） **/
	private String pke026;

	/** 组织机构名称 **/
	private String aab069;

	/** 单位名称 (医院名称 ) **/
	private String aab069_name;

	/** 姓名 **/
	private String aac003;

	/** 专家鉴定病种（关联AKA120）名称 **/
	private String pke025_name;

	/** 专家鉴定并发症（关联AKA120）名称 **/
	private String pke026_name;

	/** 审核推荐病种（关联AKA120）名称 **/
	private String pke029_name;

	/** 审核并发症（关联AKA120）名称 **/
	private String pke030_name;

	/** 专家鉴定分型（新增码表） **/
	private String pke027;

	/** 专家鉴定病情严重等级（新增码表） **/
	private String pke028;

	/** 专家鉴定分型（新增码表）名称 **/
	private String pke027_name;

	/** 专家鉴定病情严重等级（新增码表）名称 **/
	private String pke028_name;

	/** 审核推荐病种（关联AKA120） **/
	private String pke029;

	/** 审核并发症（关联AKA120） **/
	private String pke030;

	/** 审核分型（新增码表） **/
	private String pke031;

	/** 审核病情严重等级（新增码表） **/
	private String pke032;

	/** 金额 (国产对比价) **/
	private String aae019;

	/** 病情摘要 **/
	private String pke011;

	/** 治疗方案 **/
	private String pke012;

	/** 医院意见 **/
	private String pke013;

	/** 科室电话 **/
	private String pke014;

	/** 就诊科室 **/
	private String pke015;

	/** 申请人电话 **/
	private String pke016;

	/** 申请医师 **/
	private String pke017;

	/** 主任医师 **/
	private String pke018;

	/** 主任意见时间 **/
	private String pke019;
	// private String pke019;

	/** 医师意见时间 **/
	private String pke020;

	/** 医院意见时间 **/
	private String pke034;
	// private String pke020;

	/** 实际支付金额 **/
	private String akb081;

	/** 已使用限额 **/
	private String pkb016;

	/** 联系电话 **/
	private String pke033;

	/** ??? **/
	// private String pke034;

	/** 本次住院病情摘要 **/
	private String pke009;

	/** 转入医院医务科意见 **/
	private String pke010;

	/** 上次住院病情摘要 **/
	private String pke007;

	/** 转出医院医务科意见 **/
	private String pke008;

	/** 医院列表 **/
	private String hosplist;

	/** 申请序列号 **/
	private String aaz267;

	/** 个人电脑号 **/
	private String aac001;

	/** 个人电脑号 值 **/
	private String aac001value;

	/** 就诊日期 **/
	private String aae030;

	/** 结束日期 **/
	// private String aae031;
	private String aae031;

	/** 申请日期 **/
	private String aae127;
	// private String aae127;

	/** 复审意见 **/
	private String aae189;

	/** 业务日志ID **/
	private String aaz002;

	/** 医疗特殊业务申请类型 **/
	private String aka083;

	/** 针对特定就诊事件标志 **/
	private String aka084;

	/** 统筹区编码 **/
	private String aaa027;

	/** 申请类型 **/
	private String pke035;

	/** 申请标志(0:医院申请 1:中心申请) **/
	private String pkb100;

	/** 定点医疗机构ID 入 **/
	private String aaz107_in;

	/** 定点医疗机构ID 出 **/
	private String aaz107_out;

	/** 组织机构名称 入 **/
	private String aab069_in;

	/** 组织机构名称 出 **/
	private String aab069_out;

	/** 医院审批人 (转出医院医师姓名) **/
	private String pke036;

	/** 所属疾病 **/
	private String pke038;

	/** 外地医院编码 **/
	private String pke037;

	/** 所属疾病 名称 **/
	private String pke038_name;

	/** 外地医院编码 名称 **/
	private String pke037_name;

	/** 异地标志 **/
	private String aae139;

	/** 医嘱 **/
	private String aae190;

	/** 原就诊医疗机构ID **/
	private String aaz286;

	/** 转往定点医疗机构ID **/
	private String aaz294;

	/** 独立结算标志 **/
	private String aka079;

	/** 医院等级 **/
	private String aka101;

	/** 原就诊医疗服务机构编号 **/
	private String akb041;

	/** 转往医院名称 **/
	private String akc172;

	/** 转院日期 **/
	// private String ake014;
	private String ake014;

	/** 转院原因 **/
	private String ake015;

	/** 转往医疗服务机构编号 **/
	private String ake017;

	/** 审核 **/
	private String audit;

	/** 预计住院天数 **/
	private String pke040;

	/** 预计住院费用 **/
	private String pke041;

	/** 特殊项目类别(1内置材料、2移植介入造影、3大额治疗项目、4人工晶体、5心脏起搏器、6其他检查治疗 **/
	private String pke061;

	/** 申请理由 **/
	private String pke042;

	/** 医院机构相关意见 **/
	private String pke043;

	/** 既往住院情况 **/
	private String pke044;

	/** 诊疗计划 **/
	private String pke045;

	/** 上次住院诊断 **/
	private String pke046;

	/** 上次住院诊断 名称 **/
	private String pke046_name;

	/** 打印次数 **/
	private String printnum;

	/** 打印医院 **/
	private String printhosp;

	/** 性别 **/
	private String aac004;

	/** 公务员级别 **/
	private String pac001;

	/** 人员类别 **/
	private String pka004;

	private String pers_type;

	/** 出生日期 **/
	private String aac006;
	// private String aac006;

	/** 转出医院意见时间 **/
	private String pke047;

	/** 转入医院签署意见时间 **/
	private String pke048;

	/** 是否是自主转院或者是因病情需要转院 **/
	private String pke049;

	/** 本次住院诊断 **/
	private String pke050;

	/** 本次住院诊断 名称 **/
	private String pke050_name;

	/** 目的地 **/
	private String pke051;

	/** 申请类型 **/
	private String pke052;

	/** 单位意见 **/
	private String pke054;

	/** 申请方式 **/
	private String pke055;

	/** 单位电脑号 **/
	private String aab001;

	/** 所属行政区代码 **/
	private String aab301;

	/** 联系地址 **/
	private String aae006;

	/** 邮政编码 **/
	private String aae007;

	/** 安置地社保机构名称 **/
	private String akb040;

	/** 联系电话 **/
	private String pke069;

	/** 开户局邮政编码 **/
	private String pke070;

	/** 开户局邮政储蓄所名称 **/
	private String pke071;

	/** 邮寄的详细地址 **/
	private String pke072;

	/** 邮政帐户 **/
	private String pke073;

	/** 异地类型 **/
	private String pke074;

	/** 邮寄标志 **/
	private String pke075;

	/** 异地类型 名称 **/
	private String pke074_name;

	/** 邮寄标志 名称 **/
	private String pke075_name;

	/** 联系人 **/
	private String pke076;

	/** 与联系人关系 **/
	private String pke077;

	/** 邮寄方式 **/
	private String pke078;

	/** 邮寄方式 名称 **/
	private String pke078_name;

	/** 异地安置省份名称 **/
	private String pke079;

	/** 就诊类型编号 **/
	private String pke080;

	/** 就诊类型名称 **/
	private String pke080_name;

	/** 业务序列号(原SERIAL_NO) **/
	// private String aaz217;
	private String aaz217;

	// XiaoLei add
	/** 参加工作日期 **/
	private String aac007;

	private String aac012;

	private String aka120;
	private String aka121;

	/** 审核状态 1 通过|2 不通过 **/
	private String auditFlag;

	// private String aab069_name;

	private String aac003_name;

	/** 门特门慢标志 0：门特|1：门慢|2：门特门慢|3: 公医门特 **/
	private String mtmmFlag;

	/** 待遇类别 **/
	private String pka006;

	/** 科室 **/
	private String pka020;
	/** 床位号 **/
	private String pka023;
	/** 住院号 **/
	private String pka025;

	/** 险种类别 **/
	private String aae140;
	/** 业务类型编码 **/
	private String aka130;

	/** 关联业务序列号 **/
	private String pka012;

	/** 公医证号 **/
	private String pac028;

	/** 公医级别 **/
	private String pac010;

	/** 诊疗项目列表 **/
	private String itemlist;

	/** 批次 **/
	private String pkc016;

	/** 原就诊医疗服务机构名称 **/
	private String pke060;

	/** 病人意见 **/
	private String pke127;
	/** 申请病人(或家属) **/
	private String pke128;
	/** 病人意见时间 **/
	private String pke129;

	/** 对应的工伤生育业务号 **/
	private String pka042;

	/** 医院审核标志 **/
	private String pke058;

	/** 医院审核时间 **/
	private String pke057;

	/** 医院审核意见 **/
	private String pke056;

	/** 业务在院状态 **/
	private String bizStatus;

	/** 待审核申请列表 **/
	private String applyList;

	/** 业务开始时间 **/
	private String pka017;
	/** 业务结束日期 **/
	private String pka032;

	/** 转入医院签署意见人 **/
	private String pke053;

	/** 业务登记日期 **/
	private String pka013;

	/** 是否扩大用药范围 **/
	private String bke810;

	/** 特殊范围目录使用申请ID **/
	private String bke812;

	/** 关联申请序列号 **/
	private String pkb085;

	/** 医疗特殊业务申请类型名称 **/
	private String pka183;

	/** 医疗特殊业务申请类型名称 **/
	private String pka155;

	/** 审核阶段 **/
	private String auditPhase;

	/** 审核数量 **/
	private String bke809;

	/** 中心项目目录编码 **/
	private String pkc144;

	/** 中心项目目录名称 **/
	private String pkc143;

	/** 医院项目目录编码 **/
	private String ake005;

	/** 医院项目目录名称 **/
	private String ake006;

	/** 药品用法 **/
	private String aaz003;

	/** 药品规格 **/
	private String bke875;

	/** 药品用量 **/
	private String bke876;

	/** 药品单价 **/
	private String pke063;
	/** 申请数量 **/
	private String pke064;

	/** 是否进口(0-否，1-是) **/
	private String pke065;
	/** 是否有同类国产材料(0-否，1-是) **/
	private String pke066;

	/** 内控稽核状态 **/
	private String ppe001;

	/** IC 卡号 **/
	private String pka100;

	/** 转出医院等级 **/
	private String outHospLevel;

	/** 转出医院等级名称 **/
	private String outHospLevelName;

	/** 转入医院等级 **/
	private String inHospLevel;

	/** 转入医院等级名称 **/
	private String inHospLevelName;

	/** 二次返院业务标志 **/
	/** 关于网办系统人员基础库查询增加“是否新生”标识的需求(YBRJ-0395) **/
	private String second_inhosp_flag;

	/** 是否发生业务 **/
	private String type_flag;

	public String getAae011() {
		return aae011;
	}

	public void setAae011(String aae011) {
		this.aae011 = aae011;
	}

	public String getAae036() {
		return aae036;
	}

	public void setAae036(String aae036) {
		this.aae036 = aae036;
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

	public String getAae016() {
		return aae016;
	}

	public void setAae016(String aae016) {
		this.aae016 = aae016;
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	public String getAae013() {
		return aae013;
	}

	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	public String getPke021() {
		return pke021;
	}

	public void setPke021(String pke021) {
		this.pke021 = pke021;
	}

	public String getPke022() {
		return pke022;
	}

	public void setPke022(String pke022) {
		this.pke022 = pke022;
	}

	public String getPke023() {
		return pke023;
	}

	public void setPke023(String pke023) {
		this.pke023 = pke023;
	}

	public String getPke024() {
		return pke024;
	}

	public void setPke024(String pke024) {
		this.pke024 = pke024;
	}

	public String getAac002() {
		return aac002;
	}

	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}

	public String getAaz107() {
		return aaz107;
	}

	public void setAaz107(String aaz107) {
		this.aaz107 = aaz107;
	}

	public String getAae035_start() {
		return aae035_start;
	}

	public void setAae035_start(String aae035_start) {
		this.aae035_start = aae035_start;
	}

	public String getAae035_end() {
		return aae035_end;
	}

	public void setAae035_end(String aae035_end) {
		this.aae035_end = aae035_end;
	}

	public String getAae127_start() {
		return aae127_start;
	}

	public void setAae127_start(String aae127_start) {
		this.aae127_start = aae127_start;
	}

	public String getAae127_end() {
		return aae127_end;
	}

	public void setAae127_end(String aae127_end) {
		this.aae127_end = aae127_end;
	}

	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}

	public String getAkb021() {
		return akb021;
	}

	public void setAkb021(String akb021) {
		this.akb021 = akb021;
	}

	public String getAkc030() {
		return akc030;
	}

	public void setAkc030(String akc030) {
		this.akc030 = akc030;
	}

	public String getPke001() {
		return pke001;
	}

	public void setPke001(String pke001) {
		this.pke001 = pke001;
	}

	public String getPke002() {
		return pke002;
	}

	public void setPke002(String pke002) {
		this.pke002 = pke002;
	}

	public String getPke003() {
		return pke003;
	}

	public void setPke003(String pke003) {
		this.pke003 = pke003;
	}

	public String getPke004() {
		return pke004;
	}

	public void setPke004(String pke004) {
		this.pke004 = pke004;
	}

	public String getPke005() {
		return pke005;
	}

	public void setPke005(String pke005) {
		this.pke005 = pke005;
	}

	public String getPke005_name() {
		return pke005_name;
	}

	public void setPke005_name(String pke005_name) {
		this.pke005_name = pke005_name;
	}

	public String getPke003_name() {
		return pke003_name;
	}

	public void setPke003_name(String pke003_name) {
		this.pke003_name = pke003_name;
	}

	public String getPke006() {
		return pke006;
	}

	public void setPke006(String pke006) {
		this.pke006 = pke006;
	}

	public String getPke006_name() {
		return pke006_name;
	}

	public void setPke006_name(String pke006_name) {
		this.pke006_name = pke006_name;
	}

	public String getPke004_name() {
		return pke004_name;
	}

	public void setPke004_name(String pke004_name) {
		this.pke004_name = pke004_name;
	}

	public String getPke025() {
		return pke025;
	}

	public void setPke025(String pke025) {
		this.pke025 = pke025;
	}

	public String getPke026() {
		return pke026;
	}

	public void setPke026(String pke026) {
		this.pke026 = pke026;
	}

	public String getAab069() {
		return aab069;
	}

	public void setAab069(String aab069) {
		this.aab069 = aab069;
	}

	public String getAab069_name() {
		return aab069_name;
	}

	public void setAab069_name(String aab069_name) {
		this.aab069_name = aab069_name;
	}

	public String getAac003() {
		return aac003;
	}

	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}

	public String getPke025_name() {
		return pke025_name;
	}

	public void setPke025_name(String pke025_name) {
		this.pke025_name = pke025_name;
	}

	public String getPke026_name() {
		return pke026_name;
	}

	public void setPke026_name(String pke026_name) {
		this.pke026_name = pke026_name;
	}

	public String getPke029_name() {
		return pke029_name;
	}

	public void setPke029_name(String pke029_name) {
		this.pke029_name = pke029_name;
	}

	public String getPke030_name() {
		return pke030_name;
	}

	public void setPke030_name(String pke030_name) {
		this.pke030_name = pke030_name;
	}

	public String getPke027() {
		return pke027;
	}

	public void setPke027(String pke027) {
		this.pke027 = pke027;
	}

	public String getPke028() {
		return pke028;
	}

	public void setPke028(String pke028) {
		this.pke028 = pke028;
	}

	public String getPke027_name() {
		return pke027_name;
	}

	public void setPke027_name(String pke027_name) {
		this.pke027_name = pke027_name;
	}

	public String getPke028_name() {
		return pke028_name;
	}

	public void setPke028_name(String pke028_name) {
		this.pke028_name = pke028_name;
	}

	public String getPke029() {
		return pke029;
	}

	public void setPke029(String pke029) {
		this.pke029 = pke029;
	}

	public String getPke030() {
		return pke030;
	}

	public void setPke030(String pke030) {
		this.pke030 = pke030;
	}

	public String getPke031() {
		return pke031;
	}

	public void setPke031(String pke031) {
		this.pke031 = pke031;
	}

	public String getPke032() {
		return pke032;
	}

	public void setPke032(String pke032) {
		this.pke032 = pke032;
	}

	public String getAae019() {
		return aae019;
	}

	public void setAae019(String aae019) {
		this.aae019 = aae019;
	}

	public String getPke011() {
		return pke011;
	}

	public void setPke011(String pke011) {
		this.pke011 = pke011;
	}

	public String getPke012() {
		return pke012;
	}

	public void setPke012(String pke012) {
		this.pke012 = pke012;
	}

	public String getPke013() {
		return pke013;
	}

	public void setPke013(String pke013) {
		this.pke013 = pke013;
	}

	public String getPke014() {
		return pke014;
	}

	public void setPke014(String pke014) {
		this.pke014 = pke014;
	}

	public String getPke015() {
		return pke015;
	}

	public void setPke015(String pke015) {
		this.pke015 = pke015;
	}

	public String getPke016() {
		return pke016;
	}

	public void setPke016(String pke016) {
		this.pke016 = pke016;
	}

	public String getPke017() {
		return pke017;
	}

	public void setPke017(String pke017) {
		this.pke017 = pke017;
	}

	public String getPke018() {
		return pke018;
	}

	public void setPke018(String pke018) {
		this.pke018 = pke018;
	}

	public String getPke019() {
		return pke019;
	}

	public void setPke019(String pke019) {
		this.pke019 = pke019;
	}

	public String getPke020() {
		return pke020;
	}

	public void setPke020(String pke020) {
		this.pke020 = pke020;
	}

	public String getPke034() {
		return pke034;
	}

	public void setPke034(String pke034) {
		this.pke034 = pke034;
	}

	public String getAkb081() {
		return akb081;
	}

	public void setAkb081(String akb081) {
		this.akb081 = akb081;
	}

	public String getPkb016() {
		return pkb016;
	}

	public void setPkb016(String pkb016) {
		this.pkb016 = pkb016;
	}

	public String getPke033() {
		return pke033;
	}

	public void setPke033(String pke033) {
		this.pke033 = pke033;
	}

	public String getPke009() {
		return pke009;
	}

	public void setPke009(String pke009) {
		this.pke009 = pke009;
	}

	public String getPke010() {
		return pke010;
	}

	public void setPke010(String pke010) {
		this.pke010 = pke010;
	}

	public String getPke007() {
		return pke007;
	}

	public void setPke007(String pke007) {
		this.pke007 = pke007;
	}

	public String getPke008() {
		return pke008;
	}

	public void setPke008(String pke008) {
		this.pke008 = pke008;
	}

	public String getHosplist() {
		return hosplist;
	}

	public void setHosplist(String hosplist) {
		this.hosplist = hosplist;
	}

	public String getAaz267() {
		return aaz267;
	}

	public void setAaz267(String aaz267) {
		this.aaz267 = aaz267;
	}

	public String getAac001() {
		return aac001;
	}

	public void setAac001(String aac001) {
		this.aac001 = aac001;
	}

	public String getAac001value() {
		return aac001value;
	}

	public void setAac001value(String aac001value) {
		this.aac001value = aac001value;
	}

	public String getAae030() {
		return aae030;
	}

	public void setAae030(String aae030) {
		this.aae030 = aae030;
	}

	public String getAae031() {
		return aae031;
	}

	public void setAae031(String aae031) {
		this.aae031 = aae031;
	}

	public String getAae127() {
		return aae127;
	}

	public void setAae127(String aae127) {
		this.aae127 = aae127;
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

	public String getAka083() {
		return aka083;
	}

	public void setAka083(String aka083) {
		this.aka083 = aka083;
	}

	public String getAka084() {
		return aka084;
	}

	public void setAka084(String aka084) {
		this.aka084 = aka084;
	}

	public String getAaa027() {
		return aaa027;
	}

	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	public String getPke035() {
		return pke035;
	}

	public void setPke035(String pke035) {
		this.pke035 = pke035;
	}

	public String getPkb100() {
		return pkb100;
	}

	public void setPkb100(String pkb100) {
		this.pkb100 = pkb100;
	}

	public String getAaz107_in() {
		return aaz107_in;
	}

	public void setAaz107_in(String aaz107_in) {
		this.aaz107_in = aaz107_in;
	}

	public String getAaz107_out() {
		return aaz107_out;
	}

	public void setAaz107_out(String aaz107_out) {
		this.aaz107_out = aaz107_out;
	}

	public String getAab069_in() {
		return aab069_in;
	}

	public void setAab069_in(String aab069_in) {
		this.aab069_in = aab069_in;
	}

	public String getAab069_out() {
		return aab069_out;
	}

	public void setAab069_out(String aab069_out) {
		this.aab069_out = aab069_out;
	}

	public String getPke036() {
		return pke036;
	}

	public void setPke036(String pke036) {
		this.pke036 = pke036;
	}

	public String getPke038() {
		return pke038;
	}

	public void setPke038(String pke038) {
		this.pke038 = pke038;
	}

	public String getPke037() {
		return pke037;
	}

	public void setPke037(String pke037) {
		this.pke037 = pke037;
	}

	public String getPke038_name() {
		return pke038_name;
	}

	public void setPke038_name(String pke038_name) {
		this.pke038_name = pke038_name;
	}

	public String getPke037_name() {
		return pke037_name;
	}

	public void setPke037_name(String pke037_name) {
		this.pke037_name = pke037_name;
	}

	public String getAae139() {
		return aae139;
	}

	public void setAae139(String aae139) {
		this.aae139 = aae139;
	}

	public String getAae190() {
		return aae190;
	}

	public void setAae190(String aae190) {
		this.aae190 = aae190;
	}

	public String getAaz286() {
		return aaz286;
	}

	public void setAaz286(String aaz286) {
		this.aaz286 = aaz286;
	}

	public String getAaz294() {
		return aaz294;
	}

	public void setAaz294(String aaz294) {
		this.aaz294 = aaz294;
	}

	public String getAka079() {
		return aka079;
	}

	public void setAka079(String aka079) {
		this.aka079 = aka079;
	}

	public String getAka101() {
		return aka101;
	}

	public void setAka101(String aka101) {
		this.aka101 = aka101;
	}

	public String getAkb041() {
		return akb041;
	}

	public void setAkb041(String akb041) {
		this.akb041 = akb041;
	}

	public String getAkc172() {
		return akc172;
	}

	public void setAkc172(String akc172) {
		this.akc172 = akc172;
	}

	public String getAke014() {
		return ake014;
	}

	public void setAke014(String ake014) {
		this.ake014 = ake014;
	}

	public String getAke015() {
		return ake015;
	}

	public void setAke015(String ake015) {
		this.ake015 = ake015;
	}

	public String getAke017() {
		return ake017;
	}

	public void setAke017(String ake017) {
		this.ake017 = ake017;
	}

	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	public String getPke040() {
		return pke040;
	}

	public void setPke040(String pke040) {
		this.pke040 = pke040;
	}

	public String getPke041() {
		return pke041;
	}

	public void setPke041(String pke041) {
		this.pke041 = pke041;
	}

	public String getPke061() {
		return pke061;
	}

	public void setPke061(String pke061) {
		this.pke061 = pke061;
	}

	public String getPke042() {
		return pke042;
	}

	public void setPke042(String pke042) {
		this.pke042 = pke042;
	}

	public String getPke043() {
		return pke043;
	}

	public void setPke043(String pke043) {
		this.pke043 = pke043;
	}

	public String getPke044() {
		return pke044;
	}

	public void setPke044(String pke044) {
		this.pke044 = pke044;
	}

	public String getPke045() {
		return pke045;
	}

	public void setPke045(String pke045) {
		this.pke045 = pke045;
	}

	public String getPke046() {
		return pke046;
	}

	public void setPke046(String pke046) {
		this.pke046 = pke046;
	}

	public String getPke046_name() {
		return pke046_name;
	}

	public void setPke046_name(String pke046_name) {
		this.pke046_name = pke046_name;
	}

	public String getPrintnum() {
		return printnum;
	}

	public void setPrintnum(String printnum) {
		this.printnum = printnum;
	}

	public String getPrinthosp() {
		return printhosp;
	}

	public void setPrinthosp(String printhosp) {
		this.printhosp = printhosp;
	}

	public String getAac004() {
		return aac004;
	}

	public void setAac004(String aac004) {
		this.aac004 = aac004;
	}

	public String getPac001() {
		return pac001;
	}

	public void setPac001(String pac001) {
		this.pac001 = pac001;
	}

	public String getPka004() {
		return pka004;
	}

	public void setPka004(String pka004) {
		this.pka004 = pka004;
	}

	public String getPers_type() {
		return pers_type;
	}

	public void setPers_type(String pers_type) {
		this.pers_type = pers_type;
	}

	public String getAac006() {
		return aac006;
	}

	public void setAac006(String aac006) {
		this.aac006 = aac006;
	}

	public String getPke047() {
		return pke047;
	}

	public void setPke047(String pke047) {
		this.pke047 = pke047;
	}

	public String getPke048() {
		return pke048;
	}

	public void setPke048(String pke048) {
		this.pke048 = pke048;
	}

	public String getPke049() {
		return pke049;
	}

	public void setPke049(String pke049) {
		this.pke049 = pke049;
	}

	public String getPke050() {
		return pke050;
	}

	public void setPke050(String pke050) {
		this.pke050 = pke050;
	}

	public String getPke050_name() {
		return pke050_name;
	}

	public void setPke050_name(String pke050_name) {
		this.pke050_name = pke050_name;
	}

	public String getPke051() {
		return pke051;
	}

	public void setPke051(String pke051) {
		this.pke051 = pke051;
	}

	public String getPke052() {
		return pke052;
	}

	public void setPke052(String pke052) {
		this.pke052 = pke052;
	}

	public String getPke054() {
		return pke054;
	}

	public void setPke054(String pke054) {
		this.pke054 = pke054;
	}

	public String getPke055() {
		return pke055;
	}

	public void setPke055(String pke055) {
		this.pke055 = pke055;
	}

	public String getAab001() {
		return aab001;
	}

	public void setAab001(String aab001) {
		this.aab001 = aab001;
	}

	public String getAab301() {
		return aab301;
	}

	public void setAab301(String aab301) {
		this.aab301 = aab301;
	}

	public String getAae006() {
		return aae006;
	}

	public void setAae006(String aae006) {
		this.aae006 = aae006;
	}

	public String getAae007() {
		return aae007;
	}

	public void setAae007(String aae007) {
		this.aae007 = aae007;
	}

	public String getAkb040() {
		return akb040;
	}

	public void setAkb040(String akb040) {
		this.akb040 = akb040;
	}

	public String getPke069() {
		return pke069;
	}

	public void setPke069(String pke069) {
		this.pke069 = pke069;
	}

	public String getPke070() {
		return pke070;
	}

	public void setPke070(String pke070) {
		this.pke070 = pke070;
	}

	public String getPke071() {
		return pke071;
	}

	public void setPke071(String pke071) {
		this.pke071 = pke071;
	}

	public String getPke072() {
		return pke072;
	}

	public void setPke072(String pke072) {
		this.pke072 = pke072;
	}

	public String getPke073() {
		return pke073;
	}

	public void setPke073(String pke073) {
		this.pke073 = pke073;
	}

	public String getPke074() {
		return pke074;
	}

	public void setPke074(String pke074) {
		this.pke074 = pke074;
	}

	public String getPke075() {
		return pke075;
	}

	public void setPke075(String pke075) {
		this.pke075 = pke075;
	}

	public String getPke074_name() {
		return pke074_name;
	}

	public void setPke074_name(String pke074_name) {
		this.pke074_name = pke074_name;
	}

	public String getPke075_name() {
		return pke075_name;
	}

	public void setPke075_name(String pke075_name) {
		this.pke075_name = pke075_name;
	}

	public String getPke076() {
		return pke076;
	}

	public void setPke076(String pke076) {
		this.pke076 = pke076;
	}

	public String getPke077() {
		return pke077;
	}

	public void setPke077(String pke077) {
		this.pke077 = pke077;
	}

	public String getPke078() {
		return pke078;
	}

	public void setPke078(String pke078) {
		this.pke078 = pke078;
	}

	public String getPke078_name() {
		return pke078_name;
	}

	public void setPke078_name(String pke078_name) {
		this.pke078_name = pke078_name;
	}

	public String getPke079() {
		return pke079;
	}

	public void setPke079(String pke079) {
		this.pke079 = pke079;
	}

	public String getPke080() {
		return pke080;
	}

	public void setPke080(String pke080) {
		this.pke080 = pke080;
	}

	public String getPke080_name() {
		return pke080_name;
	}

	public void setPke080_name(String pke080_name) {
		this.pke080_name = pke080_name;
	}

	public String getAaz217() {
		return aaz217;
	}

	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217;
	}

	public String getAac007() {
		return aac007;
	}

	public void setAac007(String aac007) {
		this.aac007 = aac007;
	}

	public String getAac012() {
		return aac012;
	}

	public void setAac012(String aac012) {
		this.aac012 = aac012;
	}

	public String getAka120() {
		return aka120;
	}

	public void setAka120(String aka120) {
		this.aka120 = aka120;
	}

	public String getAka121() {
		return aka121;
	}

	public void setAka121(String aka121) {
		this.aka121 = aka121;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getAac003_name() {
		return aac003_name;
	}

	public void setAac003_name(String aac003_name) {
		this.aac003_name = aac003_name;
	}

	public String getMtmmFlag() {
		return mtmmFlag;
	}

	public void setMtmmFlag(String mtmmFlag) {
		this.mtmmFlag = mtmmFlag;
	}

	public String getPka006() {
		return pka006;
	}

	public void setPka006(String pka006) {
		this.pka006 = pka006;
	}

	public String getPka020() {
		return pka020;
	}

	public void setPka020(String pka020) {
		this.pka020 = pka020;
	}

	public String getPka023() {
		return pka023;
	}

	public void setPka023(String pka023) {
		this.pka023 = pka023;
	}

	public String getPka025() {
		return pka025;
	}

	public void setPka025(String pka025) {
		this.pka025 = pka025;
	}

	public String getAae140() {
		return aae140;
	}

	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}

	public String getAka130() {
		return aka130;
	}

	public void setAka130(String aka130) {
		this.aka130 = aka130;
	}

	public String getPka012() {
		return pka012;
	}

	public void setPka012(String pka012) {
		this.pka012 = pka012;
	}

	public String getPac028() {
		return pac028;
	}

	public void setPac028(String pac028) {
		this.pac028 = pac028;
	}

	public String getPac010() {
		return pac010;
	}

	public void setPac010(String pac010) {
		this.pac010 = pac010;
	}

	public String getItemlist() {
		return itemlist;
	}

	public void setItemlist(String itemlist) {
		this.itemlist = itemlist;
	}

	public String getPkc016() {
		return pkc016;
	}

	public void setPkc016(String pkc016) {
		this.pkc016 = pkc016;
	}

	public String getPke060() {
		return pke060;
	}

	public void setPke060(String pke060) {
		this.pke060 = pke060;
	}

	public String getPke127() {
		return pke127;
	}

	public void setPke127(String pke127) {
		this.pke127 = pke127;
	}

	public String getPke128() {
		return pke128;
	}

	public void setPke128(String pke128) {
		this.pke128 = pke128;
	}

	public String getPke129() {
		return pke129;
	}

	public void setPke129(String pke129) {
		this.pke129 = pke129;
	}

	public String getPka042() {
		return pka042;
	}

	public void setPka042(String pka042) {
		this.pka042 = pka042;
	}

	public String getPke058() {
		return pke058;
	}

	public void setPke058(String pke058) {
		this.pke058 = pke058;
	}

	public String getPke057() {
		return pke057;
	}

	public void setPke057(String pke057) {
		this.pke057 = pke057;
	}

	public String getPke056() {
		return pke056;
	}

	public void setPke056(String pke056) {
		this.pke056 = pke056;
	}

	public String getBizStatus() {
		return bizStatus;
	}

	public void setBizStatus(String bizStatus) {
		this.bizStatus = bizStatus;
	}

	public String getApplyList() {
		return applyList;
	}

	public void setApplyList(String applyList) {
		this.applyList = applyList;
	}

	public String getPka017() {
		return pka017;
	}

	public void setPka017(String pka017) {
		this.pka017 = pka017;
	}

	public String getPka032() {
		return pka032;
	}

	public void setPka032(String pka032) {
		this.pka032 = pka032;
	}

	public String getPke053() {
		return pke053;
	}

	public void setPke053(String pke053) {
		this.pke053 = pke053;
	}

	public String getPka013() {
		return pka013;
	}

	public void setPka013(String pka013) {
		this.pka013 = pka013;
	}

	public String getBke810() {
		return bke810;
	}

	public void setBke810(String bke810) {
		this.bke810 = bke810;
	}

	public String getBke812() {
		return bke812;
	}

	public void setBke812(String bke812) {
		this.bke812 = bke812;
	}

	public String getPkb085() {
		return pkb085;
	}

	public void setPkb085(String pkb085) {
		this.pkb085 = pkb085;
	}

	public String getPka183() {
		return pka183;
	}

	public void setPka183(String pka183) {
		this.pka183 = pka183;
	}

	public String getPka155() {
		return pka155;
	}

	public void setPka155(String pka155) {
		this.pka155 = pka155;
	}

	public String getAuditPhase() {
		return auditPhase;
	}

	public void setAuditPhase(String auditPhase) {
		this.auditPhase = auditPhase;
	}

	public String getBke809() {
		return bke809;
	}

	public void setBke809(String bke809) {
		this.bke809 = bke809;
	}

	public String getPkc144() {
		return pkc144;
	}

	public void setPkc144(String pkc144) {
		this.pkc144 = pkc144;
	}

	public String getPkc143() {
		return pkc143;
	}

	public void setPkc143(String pkc143) {
		this.pkc143 = pkc143;
	}

	public String getAke005() {
		return ake005;
	}

	public void setAke005(String ake005) {
		this.ake005 = ake005;
	}

	public String getAke006() {
		return ake006;
	}

	public void setAke006(String ake006) {
		this.ake006 = ake006;
	}

	public String getAaz003() {
		return aaz003;
	}

	public void setAaz003(String aaz003) {
		this.aaz003 = aaz003;
	}

	public String getBke875() {
		return bke875;
	}

	public void setBke875(String bke875) {
		this.bke875 = bke875;
	}

	public String getBke876() {
		return bke876;
	}

	public void setBke876(String bke876) {
		this.bke876 = bke876;
	}

	public String getPke063() {
		return pke063;
	}

	public void setPke063(String pke063) {
		this.pke063 = pke063;
	}

	public String getPke064() {
		return pke064;
	}

	public void setPke064(String pke064) {
		this.pke064 = pke064;
	}

	public String getPke065() {
		return pke065;
	}

	public void setPke065(String pke065) {
		this.pke065 = pke065;
	}

	public String getPke066() {
		return pke066;
	}

	public void setPke066(String pke066) {
		this.pke066 = pke066;
	}

	public String getPpe001() {
		return ppe001;
	}

	public void setPpe001(String ppe001) {
		this.ppe001 = ppe001;
	}

	public String getPka100() {
		return pka100;
	}

	public void setPka100(String pka100) {
		this.pka100 = pka100;
	}

	public String getOutHospLevel() {
		return outHospLevel;
	}

	public void setOutHospLevel(String outHospLevel) {
		this.outHospLevel = outHospLevel;
	}

	public String getOutHospLevelName() {
		return outHospLevelName;
	}

	public void setOutHospLevelName(String outHospLevelName) {
		this.outHospLevelName = outHospLevelName;
	}

	public String getInHospLevel() {
		return inHospLevel;
	}

	public void setInHospLevel(String inHospLevel) {
		this.inHospLevel = inHospLevel;
	}

	public String getInHospLevelName() {
		return inHospLevelName;
	}

	public void setInHospLevelName(String inHospLevelName) {
		this.inHospLevelName = inHospLevelName;
	}

	public String getSecond_inhosp_flag() {
		return second_inhosp_flag;
	}

	public void setSecond_inhosp_flag(String second_inhosp_flag) {
		this.second_inhosp_flag = second_inhosp_flag;
	}

	public String getType_flag() {
		return type_flag;
	}

	public void setType_flag(String type_flag) {
		this.type_flag = type_flag;
	}

}
