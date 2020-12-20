package com.powersi.biz.medicare.inhospital.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.powersi.biz.medicare.comm.pojo.RemotingDTO;


/**
 * The persistent class for the KC21 database table.
 * 
 */
public class BizFinDTO extends RemotingDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String kc21id;	//就诊业务主键

	private String aaa027;	//统筹区编码

	private Long aab001;	//单位ID

	private String aab019;	//单位类型

	private String aab999;	//单位管理码

	private Long aac001;	//人员ID

	private String aac002;	//公民身份号码

	private String aac003;	//姓名

	private String aac004;	//性别

	private Long aac006;	//出生日期

	private String aac148;	//特殊补助类别（低保、五保、重残、精准扶贫、标准）

	private String aae004;	//联系人姓名

	private String aae005;	//联系电话

	private String aae006;	//地址

	private String aae011;	//经办人

	private String aae014;	//复核人

	private String aae030;	//开始日期

	private String aae031;	//终止日期

	private Date aae036;	//经办时间

	private String aae100;	//当前有效标志

	private String aae139;	//报账类型 1-普通报账 2-大类报账 3-汇总报账

	private String aae140;	//险种类型（码表AAE140）

	private String aaz217;	//人员医疗就诊事件ID

	private String aaz267;	//医疗待遇申请事件ID

	private String aka030;	//结算类别（1、中心报账；2、医院上传）

	private String aka045;	//取消结算标志（1、取消结算，2：取消出院登记；3：取消就诊登记；4:取消出院结算

	private String aka101;	//医院等级

	private String aka130;	//医疗类别

	private String akb020;	//医疗服务机构编号

	private String akb021;	//医疗服务机构名称

	private String akc190;	//住院号（门诊号）

	private String akc191;	//发票号

	private String akc193;	//入院诊断疾病编码

	private Date akc194;	//医患最终结算日期

	private String akc196;	//出院诊断疾病编码(ICD_10 见KA06)

	private String ake013;	//报销原因

	private String ake020;	//病区床位

	private String ake021;	//出院诊断医生

	private String ake022;	//入院诊断医生

	private String ake024;	//主要病情描述

	private String akf001;	//科室

	private String baa027;	//参保地中心编码

	private String bac001;	//公务员级别（码表 BAC001）

	private Long bka001;	//结算批次号

	private String bka006;	//待遇类别（码表BKA006）

	private String bka008;	//单位名称

	private String bka015;	//就诊登记名称

	private String bka020;	//科室名称

	private String bka021;	//病区

	private String bka022;	//病区名称

	private Long bka030;	//住院天数（床日）

	private String bka034;	//结算人名称

	private String bka035;	//人员类别（码表 BKA035）

	private String bka039;	//完成标志（码表 BKA039）

	private String bka041;	//锁定标志（1-锁定 其他未锁定）

	private String bka042;	//生育序列号(生育待遇资格认定IDAAZ238)

	private String bka044;	//传输标志(0:未传输 1:已成功传输 2:未成功传输）

	private Date bka049;	//最后操作时间

	private String bka061;	//病种严重程度(A：病种单纯 B：严重 C：严重并发 D：危重)

	private String bka066;	//治愈情况（好转、死亡）

	private String bka068;	//住院类型（政策计算用特殊标志）（reg_flag）

	private String bka072;	//业务终结情况

	private String bka078;	//报账类型 1-普通报账 2-大类报账 3-汇总报账

	private BigDecimal bka245;	//预付款金额

	private String bke301;	//医院级别（码表BKE301）

	private String bkm003;	//生物特征信息id（与智能医审对接）

	private String bkz101;	//入院疾病诊断名称

	private String bkz102;	//出院疾病诊断名称

	private String caa027;	//统筹中心
	
	private String bke054;	//一站式结算标识
	
	private String ake025;  //手术治疗方式
	
	private String bmc029; 	//生育出院诊断
	
	private String amc020; 	//手术日期
	
	private String amc026; 	//生育类别
	
	private String amc028; 	//胎儿数
	
	private String amc022; 	//出生证号
	
	private String amc031; 	//胎次
	
	private String bmc030; 	//妊娠周期
	
    private String aaf013; //乡镇编码
	
	private String aaz070; //村社区ID
	
	private String baf003; //组ID
	
    private String aaf014; //乡镇编码
	
	private String aaz071; //村社区ID
	
	private String baf005; //组ID
	
	private String amc021; //准生证号 【TS19041600105】已出院结算写入结婚证号的业务，在mc04中查询不到结婚证号【杨世明20190416】
	
	private String amc023; //结婚证号 【TS19041600105】已出院结算写入结婚证号的业务，在mc04中查询不到结婚证号【杨世明20190416】
	//【NTS20051100306】 【医保】通过电子凭证办理的待遇业务需要有相关记录，确保后续能够统计查询  龚喜洋  2020/06/04
	private String aka242; // 是否是电子凭证办理的业务  0：否  1：是

	public String getAka242() {
		return aka242;
	}

	public void setAka242(String aka242) {
		this.aka242 = aka242;
	}

	/**
	 * @return the amc021
	 */
	public String getAmc021() {
		return amc021;
	}

	/**
	 * @param amc021 the amc021 to set
	 */
	public void setAmc021(String amc021) {
		this.amc021 = amc021;
	}

	/**
	 * @return the amc023
	 */
	public String getAmc023() {
		return amc023;
	}

	/**
	 * @param amc023 the amc023 to set
	 */
	public void setAmc023(String amc023) {
		this.amc023 = amc023;
	}

	/**
	 * @return the aaf013
	 */
	public String getAaf013() {
		return aaf013;
	}

	/**
	 * @param aaf013 the aaf013 to set
	 */
	public void setAaf013(String aaf013) {
		this.aaf013 = aaf013;
	}

	/**
	 * @return the aaz070
	 */
	public String getAaz070() {
		return aaz070;
	}

	/**
	 * @param aaz070 the aaz070 to set
	 */
	public void setAaz070(String aaz070) {
		this.aaz070 = aaz070;
	}

	/**
	 * @return the baf003
	 */
	public String getBaf003() {
		return baf003;
	}

	/**
	 * @param baf003 the baf003 to set
	 */
	public void setBaf003(String baf003) {
		this.baf003 = baf003;
	}

	/**
	 * @return the aaf014
	 */
	public String getAaf014() {
		return aaf014;
	}

	/**
	 * @param aaf014 the aaf014 to set
	 */
	public void setAaf014(String aaf014) {
		this.aaf014 = aaf014;
	}

	/**
	 * @return the aaz071
	 */
	public String getAaz071() {
		return aaz071;
	}

	/**
	 * @param aaz071 the aaz071 to set
	 */
	public void setAaz071(String aaz071) {
		this.aaz071 = aaz071;
	}

	/**
	 * @return the baf005
	 */
	public String getBaf005() {
		return baf005;
	}

	/**
	 * @param baf005 the baf005 to set
	 */
	public void setBaf005(String baf005) {
		this.baf005 = baf005;
	}

	public String getBmc029() {
		return bmc029;
	}

	public void setBmc029(String bmc029) {
		this.bmc029 = bmc029;
	}

	public String getAmc020() {
		return amc020;
	}

	public void setAmc020(String amc020) {
		this.amc020 = amc020;
	}

	public String getAmc026() {
		return amc026;
	}

	public void setAmc026(String amc026) {
		this.amc026 = amc026;
	}

	public String getAmc028() {
		return amc028;
	}

	public void setAmc028(String amc028) {
		this.amc028 = amc028;
	}

	public String getAmc022() {
		return amc022;
	}

	public void setAmc022(String amc022) {
		this.amc022 = amc022;
	}

	public String getAmc031() {
		return amc031;
	}

	public void setAmc031(String amc031) {
		this.amc031 = amc031;
	}

	public String getBmc030() {
		return bmc030;
	}

	public void setBmc030(String bmc030) {
		this.bmc030 = bmc030;
	}

	public String getAke025() {
		return ake025;
	}

	public void setAke025(String ake025) {
		this.ake025 = ake025;
	}

	public String getBke054() {
		return bke054;
	}

	public void setBke054(String bke054) {
		this.bke054 = bke054;
	}

	public String getCaa027() {
		return caa027;
	}

	public void setCaa027(String caa027) {
		this.caa027 = caa027;
	}

	public BizFinDTO() {
	}

	public String getKc21id() {
		return this.kc21id;
	}

	public void setKc21id(String kc21id) {
		this.kc21id = kc21id;
	}

	public String getAaa027() {
		return this.aaa027;
	}

	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	public Long getAab001() {
		return this.aab001;
	}

	public void setAab001(Long aab001) {
		this.aab001 = aab001;
	}

	public String getAab019() {
		return this.aab019;
	}

	public void setAab019(String aab019) {
		this.aab019 = aab019;
	}

	public String getAab999() {
		return this.aab999;
	}

	public void setAab999(String aab999) {
		this.aab999 = aab999;
	}

	public Long getAac001() {
		return this.aac001;
	}

	public void setAac001(Long aac001) {
		this.aac001 = aac001;
	}

	public String getAac002() {
		return this.aac002;
	}

	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}

	public String getAac003() {
		return this.aac003;
	}

	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}

	public String getAac004() {
		return this.aac004;
	}

	public void setAac004(String aac004) {
		this.aac004 = aac004;
	}

	public Long getAac006() {
		return this.aac006;
	}

	public void setAac006(Long aac006) {
		this.aac006 = aac006;
	}

	public String getAac148() {
		return this.aac148;
	}

	public void setAac148(String aac148) {
		this.aac148 = aac148;
	}

	public String getAae004() {
		return this.aae004;
	}

	public void setAae004(String aae004) {
		this.aae004 = aae004;
	}

	public String getAae005() {
		return this.aae005;
	}

	public void setAae005(String aae005) {
		this.aae005 = aae005;
	}

	public String getAae006() {
		return this.aae006;
	}

	public void setAae006(String aae006) {
		this.aae006 = aae006;
	}

	public String getAae011() {
		return this.aae011;
	}

	public void setAae011(String aae011) {
		this.aae011 = aae011;
	}

	public String getAae014() {
		return this.aae014;
	}

	public void setAae014(String aae014) {
		this.aae014 = aae014;
	}

	public String getAae030() {
		return this.aae030;
	}

	public void setAae030(String aae030) {
		this.aae030 = aae030;
	}

	public String getAae031() {
		return this.aae031;
	}

	public void setAae031(String aae031) {
		this.aae031 = aae031;
	}

	public Date getAae036() {
		return this.aae036;
	}

	public void setAae036(Date aae036) {
		this.aae036 = aae036;
	}

	public String getAae100() {
		return this.aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	public String getAae139() {
		return this.aae139;
	}

	public void setAae139(String aae139) {
		this.aae139 = aae139;
	}

	public String getAae140() {
		return this.aae140;
	}

	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}

	public String getAaz217() {
		return this.aaz217;
	}

	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217;
	}

	public String getAaz267() {
		return this.aaz267;
	}

	public void setAaz267(String aaz267) {
		this.aaz267 = aaz267;
	}

	public String getAka030() {
		return this.aka030;
	}

	public void setAka030(String aka030) {
		this.aka030 = aka030;
	}

	public String getAka045() {
		return this.aka045;
	}

	public void setAka045(String aka045) {
		this.aka045 = aka045;
	}

	public String getAka101() {
		return this.aka101;
	}

	public void setAka101(String aka101) {
		this.aka101 = aka101;
	}

	public String getAka130() {
		return this.aka130;
	}

	public void setAka130(String aka130) {
		this.aka130 = aka130;
	}

	public String getAkb020() {
		return this.akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}

	public String getAkb021() {
		return this.akb021;
	}

	public void setAkb021(String akb021) {
		this.akb021 = akb021;
	}

	public String getAkc190() {
		return this.akc190;
	}

	public void setAkc190(String akc190) {
		this.akc190 = akc190;
	}

	public String getAkc191() {
		return this.akc191;
	}

	public void setAkc191(String akc191) {
		this.akc191 = akc191;
	}

	public String getAkc193() {
		return this.akc193;
	}

	public void setAkc193(String akc193) {
		this.akc193 = akc193;
	}

	public Date getAkc194() {
		return this.akc194;
	}

	public void setAkc194(Date akc194) {
		this.akc194 = akc194;
	}

	public String getAkc196() {
		return this.akc196;
	}

	public void setAkc196(String akc196) {
		this.akc196 = akc196;
	}

	public String getAke013() {
		return this.ake013;
	}

	public void setAke013(String ake013) {
		this.ake013 = ake013;
	}

	public String getAke020() {
		return this.ake020;
	}

	public void setAke020(String ake020) {
		this.ake020 = ake020;
	}

	public String getAke021() {
		return this.ake021;
	}

	public void setAke021(String ake021) {
		this.ake021 = ake021;
	}

	public String getAke022() {
		return this.ake022;
	}

	public void setAke022(String ake022) {
		this.ake022 = ake022;
	}

	public String getAke024() {
		return this.ake024;
	}

	public void setAke024(String ake024) {
		this.ake024 = ake024;
	}

	public String getAkf001() {
		return this.akf001;
	}

	public void setAkf001(String akf001) {
		this.akf001 = akf001;
	}

	public String getBaa027() {
		return this.baa027;
	}

	public void setBaa027(String baa027) {
		this.baa027 = baa027;
	}

	public String getBac001() {
		return this.bac001;
	}

	public void setBac001(String bac001) {
		this.bac001 = bac001;
	}

	public Long getBka001() {
		return this.bka001;
	}

	public void setBka001(Long bka001) {
		this.bka001 = bka001;
	}

	public String getBka006() {
		return this.bka006;
	}

	public void setBka006(String bka006) {
		this.bka006 = bka006;
	}

	public String getBka008() {
		return this.bka008;
	}

	public void setBka008(String bka008) {
		this.bka008 = bka008;
	}

	public String getBka015() {
		return this.bka015;
	}

	public void setBka015(String bka015) {
		this.bka015 = bka015;
	}

	public String getBka020() {
		return this.bka020;
	}

	public void setBka020(String bka020) {
		this.bka020 = bka020;
	}

	public String getBka021() {
		return this.bka021;
	}

	public void setBka021(String bka021) {
		this.bka021 = bka021;
	}

	public String getBka022() {
		return this.bka022;
	}

	public void setBka022(String bka022) {
		this.bka022 = bka022;
	}

	public Long getBka030() {
		return this.bka030;
	}

	public void setBka030(Long bka030) {
		this.bka030 = bka030;
	}

	public String getBka034() {
		return this.bka034;
	}

	public void setBka034(String bka034) {
		this.bka034 = bka034;
	}

	public String getBka035() {
		return this.bka035;
	}

	public void setBka035(String bka035) {
		this.bka035 = bka035;
	}

	public String getBka039() {
		return this.bka039;
	}

	public void setBka039(String bka039) {
		this.bka039 = bka039;
	}

	public String getBka041() {
		return this.bka041;
	}

	public void setBka041(String bka041) {
		this.bka041 = bka041;
	}

	public String getBka042() {
		return this.bka042;
	}

	public void setBka042(String bka042) {
		this.bka042 = bka042;
	}

	public String getBka044() {
		return this.bka044;
	}

	public void setBka044(String bka044) {
		this.bka044 = bka044;
	}

	public Date getBka049() {
		return this.bka049;
	}

	public void setBka049(Date bka049) {
		this.bka049 = bka049;
	}

	public String getBka061() {
		return this.bka061;
	}

	public void setBka061(String bka061) {
		this.bka061 = bka061;
	}

	public String getBka066() {
		return this.bka066;
	}

	public void setBka066(String bka066) {
		this.bka066 = bka066;
	}

	public String getBka068() {
		return this.bka068;
	}

	public void setBka068(String bka068) {
		this.bka068 = bka068;
	}

	public String getBka072() {
		return this.bka072;
	}

	public void setBka072(String bka072) {
		this.bka072 = bka072;
	}

	public String getBka078() {
		return this.bka078;
	}

	public void setBka078(String bka078) {
		this.bka078 = bka078;
	}

	public BigDecimal getBka245() {
		return this.bka245;
	}

	public void setBka245(BigDecimal bka245) {
		this.bka245 = bka245;
	}

	public String getBke301() {
		return this.bke301;
	}

	public void setBke301(String bke301) {
		this.bke301 = bke301;
	}

	public String getBkm003() {
		return this.bkm003;
	}

	public void setBkm003(String bkm003) {
		this.bkm003 = bkm003;
	}

	public String getBkz101() {
		return this.bkz101;
	}

	public void setBkz101(String bkz101) {
		this.bkz101 = bkz101;
	}

	public String getBkz102() {
		return this.bkz102;
	}

	public void setBkz102(String bkz102) {
		this.bkz102 = bkz102;
	}
}