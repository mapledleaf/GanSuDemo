package com.powersi.biz.medicare.hosp.pojo;

import java.io.Serializable;

/**
 * 医院基础信息DTO
 * 
 * @author ChenXing
 */
public class HospBasicsDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String aaz269; // 医疗机构及药店id
	private String aab003; // 社会信用代码
	private String akb020; // 医疗机构编号
	private String akb021; // 医疗机构名称
	private String aae007; // 邮政编码
	private String aaa027; // 所属统筹区
	private String aae004; // 联系人姓名
	private String aae005; // 联系电话
	private String aab013; // 法人姓名
	private String aab014; // 法人身份证号
	private String aae159; // 联系电子邮箱
	private String bae001; // 手机号码
	private String aae051; // 批准成立文号
	private String aae047; // 成立日期
	private String aae049; // 批准成立日期
	private String aae048; // 批准成立部门
	private String aae006; // 单位地址
	private String bke301; // 医院级别
	private String aka101; // 医院等级
	private String bke302; // 行政级别
	private String akb022; // 机构服务类型
	private String akb023; // 医疗机构类型
	private String aka086; // 业务开展类别
	private String aab020; // 经济类型
	private String bke304; // 专科类型
	private String bke305; // 主管部门
	private String bkf030; // 营利类型
	private String akf007; // 执业许可证号
	private String akf006; // 药品经营许可证
	private String akf009; // 医院科室数
	private String akf010; // 重点科室数
	private String akf015; // 床位数
	private String akf008; // 在编职工数
	private String akf011; // 正高职称人数
	private String akf014; // 副高职称人数
	private String akf012; // 中级职工人数
	private String akf013; // 专业技术人数
	private String akf017; // 营业人数
	private String akf018; // 其他人数
	private String akf016; // 经营面积
	private String aaz065; // 银行id
	private String bke306; //
	private String aae009; // 户名
	private String aae010; // 银行帐号
	private String aae013; // 备注
	private String aab069; // 医疗机构名称
	private String staff_id; // 操作人工号
	private String staff_name; // 操作人姓名
	private String bkf021; // 系统厂商
	private String bkf022; // 网络公司
	private String bkf023; // 异地业务开通
	private String bkf024; // 系统维护情况
	private String bkf025; // 接口模式
	private String bkf026; // 软硬件情况
	private String bkf027; // 网络安防情况
	
	public String getBkf021()
	{
		return bkf021;
	}
	public void setBkf021(String bkf021)
	{
		this.bkf021 = bkf021;
	}
	public String getBkf022()
	{
		return bkf022;
	}
	public void setBkf022(String bkf022)
	{
		this.bkf022 = bkf022;
	}
	public String getBkf023()
	{
		return bkf023;
	}
	public void setBkf023(String bkf023)
	{
		this.bkf023 = bkf023;
	}
	public String getBkf024()
	{
		return bkf024;
	}
	public void setBkf024(String bkf024)
	{
		this.bkf024 = bkf024;
	}
	public String getBkf025()
	{
		return bkf025;
	}
	public void setBkf025(String bkf025)
	{
		this.bkf025 = bkf025;
	}
	public String getBkf026()
	{
		return bkf026;
	}
	public void setBkf026(String bkf026)
	{
		this.bkf026 = bkf026;
	}
	public String getBkf027()
	{
		return bkf027;
	}
	public void setBkf027(String bkf027)
	{
		this.bkf027 = bkf027;
	}
	public String getAaz269() {
		return aaz269;
	}
	public void setAaz269(String aaz269) {
		this.aaz269 = aaz269;
	}
	public String getAab003() {
		return aab003;
	}
	public void setAab003(String aab003) {
		this.aab003 = aab003;
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
	public String getAae007() {
		return aae007;
	}
	public void setAae007(String aae007) {
		this.aae007 = aae007;
	}
	public String getAaa027() {
		return aaa027;
	}
	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}
	public String getAae004() {
		return aae004;
	}
	public void setAae004(String aae004) {
		this.aae004 = aae004;
	}
	public String getAae005() {
		return aae005;
	}
	public void setAae005(String aae005) {
		this.aae005 = aae005;
	}
	public String getAab013() {
		return aab013;
	}
	public void setAab013(String aab013) {
		this.aab013 = aab013;
	}
	public String getAab014() {
		return aab014;
	}
	public void setAab014(String aab014) {
		this.aab014 = aab014;
	}
	public String getAae159() {
		return aae159;
	}
	public void setAae159(String aae159) {
		this.aae159 = aae159;
	}
	public String getBae001() {
		return bae001;
	}
	public void setBae001(String bae001) {
		this.bae001 = bae001;
	}
	public String getAae051() {
		return aae051;
	}
	public void setAae051(String aae051) {
		this.aae051 = aae051;
	}
	public String getAae047() {
		return aae047;
	}
	public void setAae047(String aae047) {
		this.aae047 = aae047;
	}
	public String getAae049() {
		return aae049;
	}
	public void setAae049(String aae049) {
		this.aae049 = aae049;
	}
	public String getAae048() {
		return aae048;
	}
	public void setAae048(String aae048) {
		this.aae048 = aae048;
	}
	public String getAae006() {
		return aae006;
	}
	public void setAae006(String aae006) {
		this.aae006 = aae006;
	}
	public String getBke301() {
		return bke301;
	}
	public void setBke301(String bke301) {
		this.bke301 = bke301;
	}
	public String getAka101() {
		return aka101;
	}
	public void setAka101(String aka101) {
		this.aka101 = aka101;
	}
	public String getBke302() {
		return bke302;
	}
	public void setBke302(String bke302) {
		this.bke302 = bke302;
	}
	public String getAkb022() {
		return akb022;
	}
	public void setAkb022(String akb022) {
		this.akb022 = akb022;
	}
	public String getAkb023() {
		return akb023;
	}
	public void setAkb023(String akb023) {
		this.akb023 = akb023;
	}
	public String getAka086() {
		return aka086;
	}
	public void setAka086(String aka086) {
		this.aka086 = aka086;
	}
	public String getAab020() {
		return aab020;
	}
	public void setAab020(String aab020) {
		this.aab020 = aab020;
	}
	public String getBke304() {
		return bke304;
	}
	public void setBke304(String bke304) {
		this.bke304 = bke304;
	}
	public String getBke305() {
		return bke305;
	}
	public void setBke305(String bke305) {
		this.bke305 = bke305;
	}
	public String getBkf030() {
		return bkf030;
	}
	public void setBkf030(String bkf030) {
		this.bkf030 = bkf030;
	}
	public String getAkf007() {
		return akf007;
	}
	public void setAkf007(String akf007) {
		this.akf007 = akf007;
	}
	public String getAkf006() {
		return akf006;
	}
	public void setAkf006(String akf006) {
		this.akf006 = akf006;
	}
	public String getAkf009() {
		return akf009;
	}
	public void setAkf009(String akf009) {
		this.akf009 = akf009;
	}
	public String getAkf010() {
		return akf010;
	}
	public void setAkf010(String akf010) {
		this.akf010 = akf010;
	}
	public String getAkf015() {
		return akf015;
	}
	public void setAkf015(String akf015) {
		this.akf015 = akf015;
	}
	public String getAkf008() {
		return akf008;
	}
	public void setAkf008(String akf008) {
		this.akf008 = akf008;
	}
	public String getAkf011() {
		return akf011;
	}
	public void setAkf011(String akf011) {
		this.akf011 = akf011;
	}
	public String getAkf014() {
		return akf014;
	}
	public void setAkf014(String akf014) {
		this.akf014 = akf014;
	}
	public String getAkf012() {
		return akf012;
	}
	public void setAkf012(String akf012) {
		this.akf012 = akf012;
	}
	public String getAkf013() {
		return akf013;
	}
	public void setAkf013(String akf013) {
		this.akf013 = akf013;
	}
	public String getAkf017() {
		return akf017;
	}
	public void setAkf017(String akf017) {
		this.akf017 = akf017;
	}
	public String getAkf018() {
		return akf018;
	}
	public void setAkf018(String akf018) {
		this.akf018 = akf018;
	}
	public String getAkf016() {
		return akf016;
	}
	public void setAkf016(String akf016) {
		this.akf016 = akf016;
	}
	public String getAaz065() {
		return aaz065;
	}
	public void setAaz065(String aaz065) {
		this.aaz065 = aaz065;
	}
	public String getBke306() {
		return bke306;
	}
	public void setBke306(String bke306) {
		this.bke306 = bke306;
	}
	public String getAae009() {
		return aae009;
	}
	public void setAae009(String aae009) {
		this.aae009 = aae009;
	}
	public String getAae010() {
		return aae010;
	}
	public void setAae010(String aae010) {
		this.aae010 = aae010;
	}
	public String getAae013() {
		return aae013;
	}
	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getAab069() {
		return aab069;
	}
	public void setAab069(String aab069) {
		this.aab069 = aab069;
	}
	
	
}
