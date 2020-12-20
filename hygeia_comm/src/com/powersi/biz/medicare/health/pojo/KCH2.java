package com.powersi.biz.medicare.health.pojo;

import com.powersi.comm.mybatis.Page;

public class KCH2 extends Page{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 查询标志
	 */
	private String argName;
	/**
	 * 查询内容
	 */
	private String argValue;
	/**
	 * 业务序列号
	 */
	private String bkh014;
	/**
	 * 申报流水号
	 */
	private String bkh001;
	/**
	 * 拨付单号
	 */
	private String bke126;
	/**
	 * 统筹区编码
	 */
	private String aaa027;
	/**
	 * 统筹区名称
	 */
	private String aaa028;
	/**
	 * 医疗服务机构编码
	 */
	private String akb020;
	/**
	 * 人员ID
	 */
	private String aac001;
	/**
	 * 公民身份证号码
	 */
	private String aac002;
	/**
	 * 姓名
	 */
	private String aac003;
	/**
	 * 性别
	 */
	private String aac004;
	/**
	 * 出生日期
	 */
	private String aac006;
	/**
	 * 人员类别
	 */
	private String bka035;
	/**
	 * 公务员级别（默认000）
	 */
	private String bac001;
	/**
	 * 体检类别代码 (1，公务员；2，企事业退休人员；3，离休干部；4，非公务员女性；5，破产改制)
	 */
	private String bkh012;
	/**
	 * 联系电话
	 */
	private String bke069;
	/**
	 * 套餐标识
	 */
	private String bkh015;
	/**
	 * 套餐价格
	 */
	private String bkh021; 
	/**
	 * 体检服务费清算
	 */
	private String bkh135;
	/**
	 * 单位ID
	 */
	private String aab001;
	/**
	 * 名称 (单位名称)
	 */
	private String aab069;
	/**
	 * 登记日期
	 */
	private String bkh016;
	/**
	 * 登记人工号
	 */
	private String bka053;
	/**
	 * 登记人
	 */
	private String bka054;
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
	 * 备注
	 */
	private String aae013;
	/**
	 * 当前有效标志
	 */
	private String aae100;
	
	
//////////////////////////////////////////
	
	
	/**
	 * 业务序列号
	 */
	public String getBkh014() {
		return bkh014;
	}
	
	/**
	 * 业务序列号
	 */
	public void setBkh014(String bkh014) {
		this.bkh014 = bkh014;
	}
	
	/**
	 * 申报流水号
	 */
	public String getBkh001() {
		return bkh001;
	}
	
	/**
	 * 申报流水号
	 */
	public void setBkh001(String bkh001) {
		this.bkh001 = bkh001;
	}
	
	/**
	 * 拨付单号
	 */
	public String getBke126() {
		return bke126;
	}
	
	/**
	 * 拨付单号
	 */
	public void setBke126(String bke126) {
		this.bke126 = bke126;
	}
	
	/**
	 * 统筹区编码
	 */
	public String getAaa027() {
		return aaa027;
	}
	
	/**
	 * 统筹区编码
	 */
	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}
	
	/**
	 * 统筹区名称
	 */
	public String getAaa028() {
		return aaa028;
	}
	
	/**
	 * 统筹区名称
	 */
	public void setAaa028(String aaa028) {
		this.aaa028 = aaa028;
	}

	/**
	 * 医疗服务机构编码
	 */
	public String getAkb020() {
		return akb020;
	}
	
	/**
	 * 医疗服务机构编码
	 */
	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}
	
	/**
	 * 人员ID
	 */
	public String getAac001() {
		return aac001;
	}
	
	/**
	 * 人员ID
	 */
	public void setAac001(String aac001) {
		this.aac001 = aac001;
	}
	
	/**
	 * 公民身份证号码
	 */
	public String getAac002() {
		return aac002;
	}
	
	/**
	 * 公民身份证号码
	 */
	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}
	
	/**
	 * 姓名
	 */
	public String getAac003() {
		return aac003;
	}
	
	/**
	 * 姓名
	 */
	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}
	
	/**
	 * 性别
	 */
	public String getAac004() {
		return aac004;
	}
	
	/**
	 * 性别
	 */
	public void setAac004(String aac004) {
		this.aac004 = aac004;
	}
	
	/**
	 * 出生日期
	 */
	public String getAac006() {
		return aac006;
	}
	
	/**
	 * 出生日期
	 */
	public void setAac006(String aac006) {
		this.aac006 = aac006;
	}
	
	/**
	 * 人员类别
	 */
	public String getBka035() {
		return bka035;
	}
	
	/**
	 * 人员类别
	 */
	public void setBka035(String bka035) {
		this.bka035 = bka035;
	}
	
	/**
	 * 公务员级别
	 */
	public String getBac001() {
		return bac001;
	}
	
	/**
	 * 公务员级别
	 */
	public void setBac001(String bac001) {
		this.bac001 = bac001;
	}
	
	/**
	 * 体检类别代码 (1，公务员；2，企事业退休人员；3，离休干部；4，非公务员女性；5，破产改制)
	 */
	public String getBkh012() {
		return bkh012;
	}
	
	/**
	 * 体检类别代码 (1，公务员；2，企事业退休人员；3，离休干部；4，非公务员女性；5，破产改制)
	 */
	public void setBkh012(String bkh012) {
		this.bkh012 = bkh012;
	}
	
	/**
	 * 联系电话
	 */
	public String getBke069() {
		return bke069;
	}
	
	/**
	 * 联系电话
	 */
	public void setBke069(String bke069) {
		this.bke069 = bke069;
	}
	
	/**
	 * 套餐标识
	 */
	public String getBkh015() {
		return bkh015;
	}
	
	/**
	 * 套餐标识
	 */
	public void setBkh015(String bkh015) {
		this.bkh015 = bkh015;
	}
	
	/**
	 * 套餐价格
	 */
	public String getBkh021() {
		return bkh021;
	}
	
	/**
	 * 套餐价格
	 */
	public void setBkh021(String bkh021) {
		this.bkh021 = bkh021;
	}
	
	/**
	 * 体检服务费清算
	 * @return
	 */
	public String getBkh135() {
		return bkh135;
	}
	
	/**
	 * 体检服务费清算
	 * @return
	 */
	public void setBkh135(String bkh135) {
		this.bkh135 = bkh135;
	}
	
	/**
	 * 单位ID
	 * @return
	 */
	public String getAab001() {
		return aab001;
	}
	
	/**
	 * 单位ID
	 * @return
	 */
	public void setAab001(String aab001) {
		this.aab001 = aab001;
	}
	
	/**
	 * 名称 (单位名称)
	 * @return
	 */
	public String getAab069() {
		return aab069;
	}
	
	/**
	 * 名称 (单位名称)
	 * @return
	 */
	public void setAab069(String aab069) {
		this.aab069 = aab069;
	}
	
	/**
	 * 登记日期
	 * @return
	 */
	public String getBkh016() {
		return bkh016;
	}
	
	/**
	 * 登记日期
	 * @return
	 */
	public void setBkh016(String bkh016) {
		this.bkh016 = bkh016;
	}
	
	/**
	 * 登记人工号
	 * @return
	 */
	public String getBka053() {
		return bka053;
	}
	
	/**
	 * 登记人工号
	 * @return
	 */
	public void setBka053(String bka053) {
		this.bka053 = bka053;
	}
	
	/**
	 * 登记人
	 * @return
	 */
	public String getBka054() {
		return bka054;
	}
	
	/**
	 * 登记人
	 * @return
	 */
	public void setBka054(String bka054) {
		this.bka054 = bka054;
	}
	
	/**
	 * 就诊结算日期
	 * @return
	 */
	public String getAke010() {
		return ake010;
	}
	
	/**
	 * 就诊结算日期
	 * @return
	 */
	public void setAke010(String ake010) {
		this.ake010 = ake010;
	}
	
	/**
	 * 申报时间
	 * @return
	 */
	public String getBkh019() {
		return bkh019;
	}
	
	/**
	 * 申报时间
	 * @return
	 */
	public void setBkh019(String bkh019) {
		this.bkh019 = bkh019;
	}
	
	/**
	 * 费用上传时间
	 * @return
	 */
	public String getBkh018() {
		return bkh018;
	}
	
	/**
	 * 费用上传时间
	 * @return
	 */
	public void setBkh018(String bkh018) {
		this.bkh018 = bkh018;
	}
	
	/**
	 * 1已登录2已计算3已申报4已制单
	 * @return
	 */
	public String getBkh017() {
		return bkh017;
	}
	
	/**
	 * 1已登录2已计算3已申报4已制单
	 * @return
	 */
	public void setBkh017(String bkh017) {
		this.bkh017 = bkh017;
	}
	
	/**
	 * 锁定标志
	 * @return
	 */
	public String getBka007() {
		return bka007;
	}
	
	/**
	 * 锁定标志
	 * @return
	 */
	public void setBka007(String bka007) {
		this.bka007 = bka007;
	}
	
	/**
	 * 备注
	 * @return
	 */
	public String getAae013() {
		return aae013;
	}
	
	/**
	 * 备注
	 * @return
	 */
	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}
	
	/**
	 * 有效标志
	 * @return
	 */
	public String getAae100() {
		return aae100;
	}
	
	/**
	 * 有效标志
	 * @return
	 */
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}
	
	
}
