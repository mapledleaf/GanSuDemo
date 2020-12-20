package com.powersi.biz.medicare.health.pojo;

import java.math.BigDecimal;

import com.powersi.biz.medicare.comm.pojo.MediDTO;
import com.powersi.comm.mybatis.Page;

/**
 * 体检套餐及体检登记业务
 * 
 * @author hasee
 *
 */
public class SetMealDTO  extends MediDTO {
	private static final long serialVersionUID = 1L;
	/**
	 * 套餐标识
	 */
	private String bkh015;
	/**
	 * 套餐名称
	 */
	private String bkh059;

	/**
	 * 体检类别代码 (1，公务员；2，企事业退休人员；3，离休干部；4，非公务员女性；5，破产改制)
	 */
	private String bkh012;
	/**
	 * 体检对象年龄段
	 */
	private String bkh060;

	/**
	 * 是否省级干部离休套餐(1,省级，2，厅级及以下)
	 */
	private String bkh318;

	/**
	 * 默认套餐 (1:默认套餐,0:不是默认套餐)
	 */
	private String bkh063;
	/**
	 * 生效年度
	 */
	private String bkh061;
	/**
	 * 失效年度
	 */
	private String bkh062;
	/**
	 * 修改时间
	 */
	private String bke204;
	/**
	 * 修改人工号
	 */
	private String bke205;

	/**
	 * 当前有效标志
	 */
	private String aae100;


	/**
	 * 修改人
	 */
	private String bke206;



	/******************** KCH1 *************************/
	/**
	 * 申报流水号
	 */
	private String bkh001;
	/**
	 * 公民身份号码
	 *//*
	private String aac002;
	*//**
	 * 姓名
	 *//*
	private String aac003;*/
	/**
	 * 封面卡号 (社会保障号码)
	 */
	private String aaz500;

	/**
	 * 地址
	 */
	private String aae006;
	/**
	 * 人员类别
	 */
	private String bka035;
	/**
	 * 名称 (单位名称)
	 */
	private String aab069;
	/**
	 * 公务员级别编码
	 */
	private String bac001;
	/**
	 * 公务员身份
	 */
	private String bkh009;
	/**
	 * 体检审核标志 ( 0，待审核；1,审核通过；2，审核不通过)
	 */
	private String bkh002;
	/**
	 * 体检生效日期
	 */
	private int bkh003;
	/**
	 * 体检截止日期
	 */
	private int bkh004;
	/**
	 * 体检年度
	 */
	private String bkh011;
	/**
	 * 申报日期
	 */
	private int bkh005;
	/**
	 * 缴费月数
	 */
	private int bkh006;
	/**
	 * 是否特殊人员
	 */
	private String bkh007;
	/**
	 * 是否条件规则外的人员 (1，是；0，否)
	 */
	private String bkh008;
	/**
	 * 审核意见
	 */
	private String bkh010;

	/**
	 * 申请人工号
	 */
	private String bka053;

	/**
	 * 申请人
	 */
	private String bka054;
	/**
	 * 审核人工号
	 */
	private String bke020;
	/**
	 * 个人预约状态 (0:无预约,1;有预约)
	 */
	private String bkh314;
	/**
	 * 预约时间
	 */
	private String bka055;
	/**
	 * 备注
	 */
	private String aae013;

	/**
	 * 查询标志
	 */
	private String argName;
	/**
	 * 业务序列号
	 */
	private String bkh014;

	/**
	 * 拨付单号
	 */
	private String bke126;
	/**
	 * 统筹区名称
	 */
	private String aaa028;
	/**
	 * 套餐价格
	 */
	private String bkh021;
	/**
	 * 体检服务费清算
	 */
	private String bkh135;
	/**
	 * 登记日期
	 */
	private String bkh016;

	/**
	 * 就诊结算日期
	 */
	private String ake010;
	/**
	 * 申报时间
	 */
	private String bkh019;
	/**
	 * 费用上传时间
	 */
	private String bkh018;
	/**
	 * 体检进度 1已登录2已计算3已申报4已制单
	 */
	private String bkh017;
	/**
	 * 锁定标志
	 */
	private String bka007;

	/**
	 * 费用序列号
	 */
	private String bkh022;
	/**
	 * 统计类别
	 */
	private String bkh046;
	/**
	 * 体检项目类型 (5:体检项目)
	 */
	private String bkh025;
	/**
	 * 是否套餐范围 (0:套餐内，1:套餐外)
	 */
	private String bkh026;
	/**
	 * 中心体检项目编码
	 */
	private String bkh027;
	/**
	 * 中心体检项目名称
	 */
	private String bkh028;
	/**
	 * 医院体检项目编码
	 */
	private String bkh029;
	/**
	 * 医院体检项目名称
	 */
	private String bkh030;

	/**
	 * 复核标志
	 */
	private String aae016;
	/**
	 * 计算标志
	 */
	private String bka009;
	/**
	 * 传输标志 (1:接口录入 2:手工录入）
	 */
	private String bkh317;
	
	/**
	 * 支付序列号
	 */
	private String bkh031;
	/**
	 * 医疗费列支项目 (政策明细编码)
	 */
	private String aka002;
	/**
	 * 支付序数
	 */
	private int    bkh032;
	
	private String tracestring;
	
	
	private String bke548;
	
	private String bke069;
	
	private String caa027;
	
	
	 /********************显示体检计算后的费用信息***************/
	
	/**
	 * 总费用
	 */
	private String bkh201;
	/**
	 * 协议机构优惠
	 */
	private String bkh202;
	
	/**
	 * 现金支付
	 */
	private String bkh203;
	
	/**
	 * 个人账户支付
	 */
	private String bkh204;
	
	/**
	 * 统筹支付
	 */
	private String bkh205;
	
	/**
	 * 公务员补助
	 */
	private String bkh206;
	
	/**
	 * 离休支付
	 */
	private String bkh207;
	
	/**
	 * 其他基金支付
	 */
	private String bkh208;
	
	/**
	 * 医院结论编码
	 */
	private String bkh149;
	/**
	 * 医院结论名称
	 */
	private String bkh150;
	//TS19052200158 - 【问题修复】【体检费用结算】-界面功能逻辑与需求不相符合   20190527  daliang.long
	/**
	 * 体检登记号
	 */
	private String aaz217s;
	
	

	public String getAaz217s() {
		return aaz217s;
	}

	public void setAaz217s(String aaz217s) {
		this.aaz217s = aaz217s;
	}



	private String arg_name;
	public String getCenter_flag() {
		return center_flag;
	}

	public void setCenter_flag(String center_flag) {
		this.center_flag = center_flag;
	}



	/**
	 * 中心标志
	 */
	private String center_flag;
	
	public String getArg_name() {
		return arg_name;
	}

	public void setArg_name(String arg_name) {
		this.arg_name = arg_name;
	}

	public String getBkh149() {
		return bkh149;
	}

	public void setBkh149(String bkh149) {
		this.bkh149 = bkh149;
	}

	public String getBkh150() {
		return bkh150;
	}

	public void setBkh150(String bkh150) {
		this.bkh150 = bkh150;
	}

	public String getBkh201() {
		return bkh201;
	}

	public void setBkh201(String bkh201) {
		this.bkh201 = bkh201;
	}

	public String getBkh202() {
		return bkh202;
	}

	public void setBkh202(String bkh202) {
		this.bkh202 = bkh202;
	}

	public String getBkh203() {
		return bkh203;
	}

	public void setBkh203(String bkh203) {
		this.bkh203 = bkh203;
	}

	public String getBkh204() {
		return bkh204;
	}

	public void setBkh204(String bkh204) {
		this.bkh204 = bkh204;
	}

	public String getBkh205() {
		return bkh205;
	}

	public void setBkh205(String bkh205) {
		this.bkh205 = bkh205;
	}

	public String getBkh206() {
		return bkh206;
	}

	public void setBkh206(String bkh206) {
		this.bkh206 = bkh206;
	}

	public String getBkh207() {
		return bkh207;
	}

	public void setBkh207(String bkh207) {
		this.bkh207 = bkh207;
	}

	public String getBkh208() {
		return bkh208;
	}

	public void setBkh208(String bkh208) {
		this.bkh208 = bkh208;
	}

	public String getCaa027() {
		return caa027;
	}

	public void setCaa027(String caa027) {
		this.caa027 = caa027;
	}

	public String getBke069() {
		return bke069;
	}

	public void setBke069(String bke069) {
		this.bke069 = bke069;
	}



	/**
	 * 人员类别
	 */
	private String bacb16;
	public String getBacb16() {
		return bacb16;
	}

	public void setBacb16(String bacb16) {
		this.bacb16 = bacb16;
	}



	/**
	 * 查询方式
	 */
	private String querystring;
	public String getBkh015() {
		return bkh015;
	}

	public void setBkh015(String bkh015) {
		this.bkh015 = bkh015;
	}

	public String getBkh059() {
		return bkh059;
	}

	public void setBkh059(String bkh059) {
		this.bkh059 = bkh059;
	}


	public String getBkh012() {
		return bkh012;
	}

	public void setBkh012(String bkh012) {
		this.bkh012 = bkh012;
	}

	public String getBkh060() {
		return bkh060;
	}

	public void setBkh060(String bkh060) {
		this.bkh060 = bkh060;
	}


	public String getBkh318() {
		return bkh318;
	}

	public void setBkh318(String bkh318) {
		this.bkh318 = bkh318;
	}

	public String getBkh063() {
		return bkh063;
	}

	public void setBkh063(String bkh063) {
		this.bkh063 = bkh063;
	}

	public String getBkh061() {
		return bkh061;
	}

	public void setBkh061(String bkh061) {
		this.bkh061 = bkh061;
	}

	public String getBkh062() {
		return bkh062;
	}

	public void setBkh062(String bkh062) {
		this.bkh062 = bkh062;
	}

	public String getBke204() {
		return bke204;
	}

	public void setBke204(String bke204) {
		this.bke204 = bke204;
	}

	public String getBke205() {
		return bke205;
	}

	public void setBke205(String bke205) {
		this.bke205 = bke205;
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	public String getBke206() {
		return bke206;
	}

	public void setBke206(String bke206) {
		this.bke206 = bke206;
	}

	public String getBkh001() {
		return bkh001;
	}

	public void setBkh001(String bkh001) {
		this.bkh001 = bkh001;
	}




	public String getAaz500() {
		return aaz500;
	}

	public void setAaz500(String aaz500) {
		this.aaz500 = aaz500;
	}



	public String getAae006() {
		return aae006;
	}

	public void setAae006(String aae006) {
		this.aae006 = aae006;
	}

	public String getBka035() {
		return bka035;
	}

	public void setBka035(String bka035) {
		this.bka035 = bka035;
	}


	public String getAab069() {
		return aab069;
	}

	public void setAab069(String aab069) {
		this.aab069 = aab069;
	}

	public String getBac001() {
		return bac001;
	}

	public void setBac001(String bac001) {
		this.bac001 = bac001;
	}

	public String getBkh009() {
		return bkh009;
	}

	public void setBkh009(String bkh009) {
		this.bkh009 = bkh009;
	}

	public String getBkh002() {
		return bkh002;
	}

	public void setBkh002(String bkh002) {
		this.bkh002 = bkh002;
	}

	public int getBkh003() {
		return bkh003;
	}

	public void setBkh003(int bkh003) {
		this.bkh003 = bkh003;
	}

	public int getBkh004() {
		return bkh004;
	}

	public void setBkh004(int bkh004) {
		this.bkh004 = bkh004;
	}

	public String getBkh011() {
		return bkh011;
	}

	public void setBkh011(String bkh011) {
		this.bkh011 = bkh011;
	}

	public int getBkh005() {
		return bkh005;
	}

	public void setBkh005(int bkh005) {
		this.bkh005 = bkh005;
	}

	public int getBkh006() {
		return bkh006;
	}

	public void setBkh006(int bkh006) {
		this.bkh006 = bkh006;
	}

	public String getBkh007() {
		return bkh007;
	}

	public void setBkh007(String bkh007) {
		this.bkh007 = bkh007;
	}

	public String getBkh008() {
		return bkh008;
	}

	public void setBkh008(String bkh008) {
		this.bkh008 = bkh008;
	}

	public String getBkh010() {
		return bkh010;
	}

	public void setBkh010(String bkh010) {
		this.bkh010 = bkh010;
	}


	public String getBka053() {
		return bka053;
	}

	public void setBka053(String bka053) {
		this.bka053 = bka053;
	}

	public String getBka054() {
		return bka054;
	}

	public void setBka054(String bka054) {
		this.bka054 = bka054;
	}

	public String getBke020() {
		return bke020;
	}

	public void setBke020(String bke020) {
		this.bke020 = bke020;
	}

	public String getBkh314() {
		return bkh314;
	}

	public void setBkh314(String bkh314) {
		this.bkh314 = bkh314;
	}

	public String getBka055() {
		return bka055;
	}

	public void setBka055(String bka055) {
		this.bka055 = bka055;
	}

	public String getAae013() {
		return aae013;
	}

	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	public String getArgName() {
		return argName;
	}

	public void setArgName(String argName) {
		this.argName = argName;
	}

	public String getBkh014() {
		return bkh014;
	}

	public void setBkh014(String bkh014) {
		this.bkh014 = bkh014;
	}

	public String getBke126() {
		return bke126;
	}

	public void setBke126(String bke126) {
		this.bke126 = bke126;
	}

	public String getAaa028() {
		return aaa028;
	}

	public void setAaa028(String aaa028) {
		this.aaa028 = aaa028;
	}

	public String getBkh021() {
		return bkh021;
	}

	public void setBkh021(String bkh021) {
		this.bkh021 = bkh021;
	}

	public String getBkh135() {
		return bkh135;
	}

	public void setBkh135(String bkh135) {
		this.bkh135 = bkh135;
	}

	public String getBkh016() {
		return bkh016;
	}

	public void setBkh016(String bkh016) {
		this.bkh016 = bkh016;
	}

	public String getAke010() {
		return ake010;
	}

	public void setAke010(String ake010) {
		this.ake010 = ake010;
	}

	public String getBkh019() {
		return bkh019;
	}

	public void setBkh019(String bkh019) {
		this.bkh019 = bkh019;
	}

	public String getBkh018() {
		return bkh018;
	}

	public void setBkh018(String bkh018) {
		this.bkh018 = bkh018;
	}

	public String getBkh017() {
		return bkh017;
	}

	public void setBkh017(String bkh017) {
		this.bkh017 = bkh017;
	}

	public String getBka007() {
		return bka007;
	}

	public void setBka007(String bka007) {
		this.bka007 = bka007;
	}

	public String getBkh022() {
		return bkh022;
	}

	public void setBkh022(String bkh022) {
		this.bkh022 = bkh022;
	}

	public String getBkh046() {
		return bkh046;
	}

	public void setBkh046(String bkh046) {
		this.bkh046 = bkh046;
	}

	public String getBkh025() {
		return bkh025;
	}

	public void setBkh025(String bkh025) {
		this.bkh025 = bkh025;
	}

	public String getBkh026() {
		return bkh026;
	}

	public void setBkh026(String bkh026) {
		this.bkh026 = bkh026;
	}

	public String getBkh027() {
		return bkh027;
	}

	public void setBkh027(String bkh027) {
		this.bkh027 = bkh027;
	}

	public String getBkh028() {
		return bkh028;
	}

	public void setBkh028(String bkh028) {
		this.bkh028 = bkh028;
	}

	public String getBkh029() {
		return bkh029;
	}

	public void setBkh029(String bkh029) {
		this.bkh029 = bkh029;
	}

	public String getBkh030() {
		return bkh030;
	}

	public void setBkh030(String bkh030) {
		this.bkh030 = bkh030;
	}



	public String getAae016() {
		return aae016;
	}

	public void setAae016(String aae016) {
		this.aae016 = aae016;
	}

	public String getBka009() {
		return bka009;
	}

	public void setBka009(String bka009) {
		this.bka009 = bka009;
	}

	public String getBkh317() {
		return bkh317;
	}

	public void setBkh317(String bkh317) {
		this.bkh317 = bkh317;
	}

	public String getBkh031() {
		return bkh031;
	}

	public void setBkh031(String bkh031) {
		this.bkh031 = bkh031;
	}

	public String getAka002() {
		return aka002;
	}

	public void setAka002(String aka002) {
		this.aka002 = aka002;
	}

	public int getBkh032() {
		return bkh032;
	}

	public void setBkh032(int bkh032) {
		this.bkh032 = bkh032;
	}

	public String getTracestring() {
		return tracestring;
	}

	public void setTracestring(String tracestring) {
		this.tracestring = tracestring;
	}

	public String getBke548() {
		return bke548;
	}

	public void setBke548(String bke548) {
		this.bke548 = bke548;
	}

	public String getQuerystring() {
		return querystring;
	}

	public void setQuerystring(String querystring) {
		this.querystring = querystring;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	/**
	 * 读卡标志 0 未读卡  1  已读卡
	 */
	private String readcardflag;

	
	
	private String indi_id;
	private String idcard;
	private String aac004_name;
	private String bka035_name;
	private String bkh007_name;
	private String bac001_name;
	private String bkh002_name;
	private String bkh015_name;
	private String aab069_name;
	private String flag;
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getAab069_name() {
		return aab069_name;
	}

	public void setAab069_name(String aab069_name) {
		this.aab069_name = aab069_name;
	}

	public String getBkh015_name() {
		return bkh015_name;
	}

	public void setBkh015_name(String bkh015_name) {
		this.bkh015_name = bkh015_name;
	}

	public String getBkh002_name() {
		return bkh002_name;
	}

	public void setBkh002_name(String bkh002_name) {
		this.bkh002_name = bkh002_name;
	}

	public String getBac001_name() {
		return bac001_name;
	}

	public void setBac001_name(String bac001_name) {
		this.bac001_name = bac001_name;
	}

	public String getBkh007_name() {
		return bkh007_name;
	}

	public void setBkh007_name(String bkh007_name) {
		this.bkh007_name = bkh007_name;
	}

	public String getBka035_name() {
		return bka035_name;
	}

	public void setBka035_name(String bka035_name) {
		this.bka035_name = bka035_name;
	}

	public String getAac004_name() {
		return aac004_name;
	}

	public void setAac004_name(String aac004_name) {
		this.aac004_name = aac004_name;
	}

	public String getReadcardflag() {
		return readcardflag;
	}

	public void setReadcardflag(String readcardflag) {
		this.readcardflag = readcardflag;
	}

	private String insr_code;

	public String getIndi_id() {
		return indi_id;
	}

	public void setIndi_id(String indi_id) {
		this.indi_id = indi_id;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getInsr_code() {
		return insr_code;
	}

	public void setInsr_code(String insr_code) {
		this.insr_code = insr_code;
	}

}
