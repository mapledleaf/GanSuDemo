package com.powersi.biz.medicare.diagnose.pojo;

import com.powersi.comm.bean.BaseBean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 朱帆宇
 *
 */
public class DiagnoseInfoDTO extends BaseBean {

	private static final long serialVersionUID = 1L;
	private String kcd1id;
	private String kc21id;
	private String akb020;
	private String aaz217;
	private long bka001;
	private long bka009;
	private String aka130;
	private long bka002;
	private String baa027;
	private long aac001;
	private String aac003;
	private String aac004;
	private String bac001;
	private String aac002;
	private String aac066;
	private String bka100;
	private String akb021;
	private String akb022;
	private String aac006;
	private String aae004;
	private String aae005;
	private String aae006;
	private long aab001;
	private String bka008;
	private String aab019;
	private String bka006;
	private long bka010;
	private String bka011;
	private String bka012;
	private long aaz267;
	private String aae036;
	private String aae011;
	private String bka015;
	private String bka016;
	private String bka018;
	private String akf001;
	private String bka020;
	private String bka021;
	private String bka022;
	private String ake020;
	private String bka024;
	private String bka025;
	private String akc193;
	private double bka245;
	private Date bka028;
	private String bka029;
	private long bka030;
	private String akc196;
	private String aae014;
	private String bka034;
	private String bka035;
	private String bka036;
	private String aka030;
	private Date bka038;
	private String bka039;
	private String bka040;
	private String bka041;
	private String bka042;
	private String bka043;
	private String bka044;
	private String bka045;
	private String bka048;
	private String bka050;
	private String aaa027;
	private String caa027;
	private String aae140;
	private String bka414;
	private String aae139;
	private String bae009;
	private String baf313;
	private String baz113;
	private String bka501;
	private String bka502;
	private String bka503;
	private Date bka504;
	private Date bka505;
	private Date bka506;
	private long bka507;
	private long bka508;
	private long bka509;
	private String bka510;
	private String reduceflag;
	private long reducefeebatch;
	private String aae100;
	// 工伤医疗期id、开始时间、结束时间
	private String blz516;
	private String aae030;
	private String aae031;
	private String arg_name;
	private String arg_value;
	private Date bka065;
	private Date bka051;
	// AKA121 N VARCHAR2(300) Y 疾病名称
	private String aka121;
	private String allowed_no_readcard;
	private long aaz213;
	private String comm_subsys_flag;//个人化通用接口子系统通道标识  1是，0否 
	private String bae410; // 发票类型
	private String ake1id;  // 处方UUID
    private String bka898;  //缴费档次
    private String bka888;  //  基金冻结状态(0正常、1冻结、9未参保)
    private String bka700;  //  灵活就业人员标识1、0
    private String bke548;
    private String bka977;
    private String baa027_name;// 参保地地市编码名称（ 例如：440600佛山市）
    private String bka006_name;
    private String aac004_name;
    private String bka035_name;
    private String aae140_name;
    private String bka888_name;
    private String bke301;
    private String aka101;
    private String akc190;
    private String akc191;
    private String akc194;
    private String bka049;
    private String bkz101;
    private String bkz102;
    private String bka061;
    private String bka066;
    private String ake021;
    private String ake022;
    private String ake024;
    private String ake013;
    private String aab999;
    private String bkm003;
    private String bka068;
    private String bka078;
    private String aac148;
    private String aka045;
    private String bka072;
    private String ake025;
    private String aac031;
    private String aac008;
    private String aaz507;//身份校验密码
    private String aaz508;//身份校验修改密码
    private String aaz509;//身份证校验api方式续获取一个标志，无需校验卡鉴权
	private String aaz510;//电子凭证身份证校验令牌
	private String aaz511;//电子凭证身份证校验证件类型
	private String pos_flag;//村卫生室不校验身份密码标志；
    private String bacu18;// 个人账户余额
    private String akb081;// 特药药品申请限额
    private String bkb017;// 特药药品剩余限额
    private String ake001;// 特药药品剩余限额
    private String aae019;// 业务发生总费用
    private String kc90id;// 业务采集图片uuid
    private String kc90id_2;// 指静脉id
    private String bkc290;// 业务采集图片存放地址
    private String bkc292;// 业务采集图片存放地址
    private String bka893;// 保存标识 0:试算 1：收费
    private String aae010;//银行卡号（用于支持康佳银行卡号查询人员信息）
    private String bka889;//基金冻结情况
    private String isneedimg;//是否需要上传照片 1：是
    private String amc021;//准生证号
    private String amc023;//结婚证号
    private String isreset;//是否重置首诊断医院 0 不重置,1 重置
    private String aaf013; //乡镇编码
	private String aaf014; //乡镇街道名称
	private String aaz070; //村社区ID
	private String aaz071; //村社区名称
	private String baf003; //组ID
	private String baf005; //组名称
	private String fzd001;//门特副诊断
	
	
	/* TS19032800229 - 【需求开发】电子社保卡应用相关功能模块改造  扫码结算增加参数  modified 675 2019年3月28日  */
	private String bizorder; //业务交易号（云诊所/云药店）
	private String aaz500; //社会保障卡卡号（电子卡扫码）
	private String esscno; //电子社保卡卡号（电子卡扫码）
	
	//TD19040900142 - 大病特药电子处方售药加入电子社保卡刷卡结算
	private String dbty_flag; //大病特药电子处方系统标识
	private List<Map> bizinfo; //业务信息
	private List<Map> feeinfo; //费用信息
	private String bke054;	//一站式标志
	private String bkz300; //密码签名
	
	//TS19121900162 - 【问题修复】需求测试_湘潭_门特疾病限额没有按年度1800封顶是按审核录入的限额处理的
	private String aka240;	//城乡门特两病打标志使用
	//NTS20050700380 - 生育、门特业务省内异地联网结算需求 -- 湘潭_add by ljl 20200525
	private String hospital_id;	//省异地医疗机构编码	对应akb020
	private String indi_id;	//省异地个人电脑号	对应aac001
	private String idcard;	//省异地社会保障号	对应aac002
	private String iccardno;	//省异地ic卡号	对应bka100
	private String insr_code;	//省异地保险号
	private String corp_name;	//省异地单位名称  对应bka008
	private String name;	//省异地姓名	对应aac003
	private String treatment_type;	//省异地待遇类型  对应bka006
	private String fromdate;	//省异地业务开始时间	对应aae030
	private String todate;		//省异地业务结束时间	对应aae031
	private String biz_type;	//省异地业务类型	对应aka130
	private String center_id;	//省异地统筹中心	对应aaa027
	private String serial_apply;	//省异地特殊业务号 对应aaz267
	private String last_balance;	//省异地个账余额	对应bacu18
	private String icd;		//省异地疾病编码 对应akc193
	private String disease;	//省异地疾病名称	对应bkz101
	private String telephone;	//省异地联系电话 对应aae005
	private String admit_effect;	//省异地申请有效时间	对应bkm030
	private String admit_date;		//省异地申请失效时间	对应bkm031
	private String birthday;		//省异地出生日期  对应aac006
	private String serial_no;		//省异地业务序列号	对应aaz217
	private String input_staff;     //录入人工号  bka063
	private String input_man;       //录入人  bka064
	private String input_date;      //录入日期 aae030
	private String reg_staff;           //登记人员工号 对应aae011
	private String reg_man;             //登记人姓名 对应bka015
	private String diagnose_date;       //就诊时间
	private String pers_type;           //人员类别编码
	private String pers_name;           //人员类别名称
	private String official_code;       //公务员级别编码
	private String official_name;       //公务员级别名称
	private String corp_id;             //单位电脑号
	private String diagnose;            //疾病编码
	private String in_disease_name;     //疾病名称
	private String query_flag;          //收改费标志
	private String fee_batch;           //费用批次

	//【NTS20051100306】 【医保】通过电子凭证办理的待遇业务需要有相关记录，确保后续能够统计查询  龚喜洋  2020/06/04
	private String aka242; // 是否是电子凭证办理的业务  0：否  1：是
	// NTS20071500412】 【医保】通过电子凭证办理的结算需要发送消息通知，需要传电子凭证，身份证，姓名给后台  方然青  2020/07/20
	private String ecIndexNo; // 电子凭证
	
	private String idNo; //电子凭证证件号码
	private String idType; //电子凭证证件类别
	private String ecToken; //电子凭证ectoken码
	private String sn_code;//电子凭证sn序列号

	public String getSn_code() {
		return sn_code;
	}

	public void setSn_code(String sn_code) {
		this.sn_code = sn_code;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getEcToken() {
		return ecToken;
	}

	public void setEcToken(String ecToken) {
		this.ecToken = ecToken;
	}

	public String getEcIndexNo() {
		return ecIndexNo;
	}

	public void setEcIndexNo(String ecIndexNo) {
		this.ecIndexNo = ecIndexNo;
	}

	public String getAka242() {
		return aka242;
	}

	public void setAka242(String aka242) {
		this.aka242 = aka242;
	}

	public String getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}

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

	public String getIccardno() {
		return iccardno;
	}

	public void setIccardno(String iccardno) {
		this.iccardno = iccardno;
	}

	public String getInsr_code() {
		return insr_code;
	}

	public void setInsr_code(String insr_code) {
		this.insr_code = insr_code;
	}

	public String getCorp_name() {
		return corp_name;
	}

	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTreatment_type() {
		return treatment_type;
	}

	public void setTreatment_type(String treatment_type) {
		this.treatment_type = treatment_type;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getBiz_type() {
		return biz_type;
	}

	public void setBiz_type(String biz_type) {
		this.biz_type = biz_type;
	}

	public String getCenter_id() {
		return center_id;
	}

	public void setCenter_id(String center_id) {
		this.center_id = center_id;
	}

	public String getSerial_apply() {
		return serial_apply;
	}

	public void setSerial_apply(String serial_apply) {
		this.serial_apply = serial_apply;
	}

	public String getLast_balance() {
		return last_balance;
	}

	public void setLast_balance(String last_balance) {
		this.last_balance = last_balance;
	}

	public String getIcd() {
		return icd;
	}

	public void setIcd(String icd) {
		this.icd = icd;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdmit_effect() {
		return admit_effect;
	}

	public void setAdmit_effect(String admit_effect) {
		this.admit_effect = admit_effect;
	}

	public String getAdmit_date() {
		return admit_date;
	}

	public void setAdmit_date(String admit_date) {
		this.admit_date = admit_date;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSerial_no() {
		return serial_no;
	}

	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}

	public String getInput_staff() {
		return input_staff;
	}

	public void setInput_staff(String input_staff) {
		this.input_staff = input_staff;
	}

	public String getInput_man() {
		return input_man;
	}

	public void setInput_man(String input_man) {
		this.input_man = input_man;
	}

	public String getInput_date() {
		return input_date;
	}

	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}

	public String getReg_staff() {
		return reg_staff;
	}

	public void setReg_staff(String reg_staff) {
		this.reg_staff = reg_staff;
	}

	public String getReg_man() {
		return reg_man;
	}

	public void setReg_man(String reg_man) {
		this.reg_man = reg_man;
	}

	public String getDiagnose_date() {
		return diagnose_date;
	}

	public void setDiagnose_date(String diagnose_date) {
		this.diagnose_date = diagnose_date;
	}

	public String getPers_type() {
		return pers_type;
	}

	public void setPers_type(String pers_type) {
		this.pers_type = pers_type;
	}

	public String getPers_name() {
		return pers_name;
	}

	public void setPers_name(String pers_name) {
		this.pers_name = pers_name;
	}

	public String getOfficial_code() {
		return official_code;
	}

	public void setOfficial_code(String official_code) {
		this.official_code = official_code;
	}

	public String getOfficial_name() {
		return official_name;
	}

	public void setOfficial_name(String official_name) {
		this.official_name = official_name;
	}

	public String getCorp_id() {
		return corp_id;
	}

	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getIn_disease_name() {
		return in_disease_name;
	}

	public void setIn_disease_name(String in_disease_name) {
		this.in_disease_name = in_disease_name;
	}

	public String getQuery_flag() {
		return query_flag;
	}

	public void setQuery_flag(String query_flag) {
		this.query_flag = query_flag;
	}

	public String getFee_batch() {
		return fee_batch;
	}

	public void setFee_batch(String fee_batch) {
		this.fee_batch = fee_batch;
	}

	public String getAaz511() {
		return aaz511;
	}

	public void setAaz511(String aaz511) {
		this.aaz511 = aaz511;
	}

	public String getAaz510() {
		return aaz510;
	}

	public void setAaz510(String aaz510) {
		this.aaz510 = aaz510;
	}

	public String getAka240() {
		return aka240;
	}
	
	public void setAka240(String aka240) {
		this.aka240 = aka240;
	}

	/**
	 * @return the bkz300
	 */
	public String getBkz300() {
		return bkz300;
	}

	/**
	 * @param bkz300 the bkz300 to set
	 */
	public void setBkz300(String bkz300) {
		this.bkz300 = bkz300;
	}

	public String getBke054()
	{
		return bke054;
	}

	public void setBke054(String bke054)
	{
		this.bke054 = bke054;
	}

	public String getAaz500() {
		return aaz500;
	}

	public void setAaz500(String aaz500) {
		this.aaz500 = aaz500;
	}

	public String getEsscno() {
		return esscno;
	}

	public void setEsscno(String esscno) {
		this.esscno = esscno;
	}

	/**
	 * @return the bizorder
	 */
	public String getBizorder() {
		return bizorder;
	}

	/**
	 * @param bizorder the bizorder to set
	 */
	public void setBizorder(String bizorder) {
		this.bizorder = bizorder;
	}

	public String getFzd001() {
		return fzd001;
	}

	public void setFzd001(String fzd001) {
		this.fzd001 = fzd001;
	}

	public String getAaf013() {
		return aaf013;
	}

	public void setAaf013(String aaf013) {
		this.aaf013 = aaf013;
	}

	public String getAaf014() {
		return aaf014;
	}

	public void setAaf014(String aaf014) {
		this.aaf014 = aaf014;
	}

	public String getAaz070() {
		return aaz070;
	}

	public void setAaz070(String aaz070) {
		this.aaz070 = aaz070;
	}

	public String getAaz071() {
		return aaz071;
	}

	public void setAaz071(String aaz071) {
		this.aaz071 = aaz071;
	}

	public String getBaf003() {
		return baf003;
	}

	public void setBaf003(String baf003) {
		this.baf003 = baf003;
	}

	public String getBaf005() {
		return baf005;
	}

	public void setBaf005(String baf005) {
		this.baf005 = baf005;
	}

	public String getIsreset() {
		return isreset;
	}

	public void setIsreset(String isreset) {
		this.isreset = isreset;
	}

	public String getAmc021() {
		return amc021;
	}

	public void setAmc021(String amc021) {
		this.amc021 = amc021;
	}

	public String getAmc023() {
		return amc023;
	}

	public void setAmc023(String amc023) {
		this.amc023 = amc023;
	}

	public String getCaa027() {
		return caa027;
	}

	public void setCaa027(String caa027) {
		this.caa027 = caa027;
	}

	public String getBkc292() {
		return bkc292;
	}

	public void setBkc292(String bkc292) {
		this.bkc292 = bkc292;
	}

	public String getKc90id_2() {
		return kc90id_2;
	}

	public void setKc90id_2(String kc90id_2) {
		this.kc90id_2 = kc90id_2;
	}

	public String getIsneedimg() {
		return isneedimg;
	}

	public void setIsneedimg(String isneedimg) {
		this.isneedimg = isneedimg;
	}

	public String getBka889() {
		return bka889;
	}

	public void setBka889(String bka889) {
		this.bka889 = bka889;
	}

	public String getAaz509() {
		return aaz509;
	}

	public void setAaz509(String aaz509) {
		this.aaz509 = aaz509;
	}

	public String getAae010() {
		return aae010;
	}

	public void setAae010(String aae010) {
		this.aae010 = aae010;
	}

	public String getPos_flag() {
		return pos_flag;
	}

	public void setPos_flag(String pos_flag) {
		this.pos_flag = pos_flag;
	}

	public String getBka893() {
		return bka893;
	}

	public void setBka893(String bka893) {
		this.bka893 = bka893;
	}

	public String getKc90id() {
		return kc90id;
	}

	public void setKc90id(String kc90id) {
		this.kc90id = kc90id;
	}

	public String getBkc290() {
		return bkc290;
	}

	public void setBkc290(String bkc290) {
		this.bkc290 = bkc290;
	}

	public String getAae019() {
		return aae019;
	}

	public void setAae019(String aae019) {
		this.aae019 = aae019;
	}

	public String getAke001() {
		return ake001;
	}

	public void setAke001(String ake001) {
		this.ake001 = ake001;
	}

	public String getAkb081() {
		return akb081;
	}

	public void setAkb081(String akb081) {
		this.akb081 = akb081;
	}

	public String getBkb017() {
		return bkb017;
	}

	public void setBkb017(String bkb017) {
		this.bkb017 = bkb017;
	}

	public String getBacu18() {
		return bacu18;
	}
	
	public void setBacu18(String bacu18) {
		this.bacu18 = bacu18;
	}
    
	public String getAaz508() {
		return aaz508;
	}

	public void setAaz508(String aaz508) {
		this.aaz508 = aaz508;
	}

	public String getAaz507() {
		return aaz507;
	}

	public void setAaz507(String aaz507) {
		this.aaz507 = aaz507;
	}

	public String getKc21id() {
		return kc21id;
	}

	public void setKc21id(String kc21id) {
		this.kc21id = kc21id;
	}

	public String getAke1id() {
		return ake1id;
	}

	public void setAke1id(String ake1id) {
		this.ake1id = ake1id;
	}

	//移动支付调用标识,1是，0否，110退费成功
	private String appPayFlag;

	public String getBke548() {
		return bke548;
	}

	public void setBke548(String bke548) {
		this.bke548 = bke548;
	}

	/**
	 * 标识(通用) 0、1
	 * 
	 * @return
	 */
	public String getBka977() {
		return bka977;
	}

	/**
	 * 标识(通用) 0、1
	 * 
	 * @param bka977
	 */
	public void setBka977(String bka977) {
		this.bka977 = bka977;
	}

	public String getBlz516() {
		return blz516;
	}

	public void setBlz516(String blz516) {
		this.blz516 = blz516;
	}

	/**
	 * 业务开始时间
	 * @return
	 */
	public String getAae030() {
		return aae030;
	}

	/**
	 * 业务开始时间
	 * @param aae030
	 */
	public void setAae030(String aae030) {
		this.aae030 = aae030;
	}

	/**
	 * 业务结束时间
	 * @return
	 */
	public String getAae031() {
		return aae031;
	}

	/**
	 * 业务结束时间
	 * @param aae031
	 */
	public void setAae031(String aae031) {
		this.aae031 = aae031;
	}

	/**
	 * 医院科室编码
	 */
	private String bkc156;

	/**
	 * 医院科室名称
	 */
	private String bkc157;

	public String getBkc156() {
		return bkc156;
	}

	public void setBkc156(String bkc156) {
		this.bkc156 = bkc156;
	}

	public String getBkc157() {
		return bkc157;
	}

	public void setBkc157(String bkc157) {
		this.bkc157 = bkc157;
	}

	public String getAka121() {
		return aka121;
	}

	public void setAka121(String aka121) {
		this.aka121 = aka121;
	}

	/**
	 * 有效标志
	 * 
	 * @return
	 */
	public String getAae100() {
		return aae100;
	}

	/**
	 * 有效标志
	 * 
	 * @return
	 */
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	/**
	 * 唯一索引
	 * 
	 * @return
	 */
	public String getKcd1id() {
		return kcd1id;
	}

	/**
	 * 唯一索引
	 * 
	 * @return
	 */
	public void setKcd1id(String kcd1id) {
		this.kcd1id = kcd1id;
	}

	/**
	 * 医院编码
	 * 
	 * @return
	 */
	public String getAkb020() {
		return akb020;
	}

	/**
	 * 医院编码
	 * 
	 * @return
	 */
	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}

	/**
	 * 就医登记号
	 * 
	 * @return
	 */
	public String getAaz217() {
		return aaz217;
	}

	/**
	 * 就医登记号
	 * 
	 * @return
	 */
	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217;
	}

	/**
	 * 费用批次
	 * 
	 * @return
	 */
	public long getBka001() {
		return bka001;
	}

	/**
	 * 费用批次
	 * 
	 * @return
	 */
	public void setBka001(long bka001) {
		this.bka001 = bka001;
	}

	/**
	 * 病例分型序号
	 * 
	 * @return
	 */
	public long getBka009() {
		return bka009;
	}

	/**
	 * 病例分型序号
	 * 
	 * @return
	 */
	public void setBka009(long bka009) {
		this.bka009 = bka009;
	}

	/**
	 * 业务类别编号
	 * 
	 * @return
	 */
	public String getAka130() {
		return aka130;
	}

	/**
	 * 业务类别编号
	 * 
	 * @return
	 */
	public void setAka130(String aka130) {
		this.aka130 = aka130;
	}

	/**
	 * 内部序数
	 * 
	 * @return
	 */
	public long getBka002() {
		return bka002;
	}

	/**
	 * 内部序数
	 * 
	 * @return
	 */
	public void setBka002(long bka002) {
		this.bka002 = bka002;
	}

	/**
	 * 当事人所在统筹区
	 * 
	 * @return
	 */
	public String getBaa027() {
		return baa027;
	}

	/**
	 * 当事人所在统筹区
	 * 
	 * @return
	 */
	public void setBaa027(String baa027) {
		this.baa027 = baa027;
	}

	/**
	 * 个人电脑号
	 * 
	 * @return
	 */
	public long getAac001() {
		return aac001;
	}

	/**
	 * 个人电脑号
	 * 
	 * @return
	 */
	public void setAac001(long aac001) {
		this.aac001 = aac001;
	}

	/**
	 * 姓名
	 * 
	 * @return
	 */
	public String getAac003() {
		return aac003;
	}

	/**
	 * 姓名
	 * 
	 * @return
	 */
	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}

	/**
	 * 性别
	 * 
	 * @return
	 */
	public String getAac004() {
		return aac004;
	}

	/**
	 * 性别
	 * 
	 * @return
	 */
	public void setAac004(String aac004) {
		this.aac004 = aac004;
	}


	/**
	 * 公务员级别
	 * 
	 * @return
	 */
	public String getBac001() {
		return bac001;
	}

	/**
	 * 公务员级别
	 * 
	 * @return
	 */
	public void setBac001(String bac001) {
		this.bac001 = bac001;
	}

	/**
	 * 公民身份号码
	 * 
	 * @return
	 */
	public String getAac002() {
		return aac002;
	}

	/**
	 * 公民身份号码
	 * 
	 * @return
	 */
	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}

	/**
	 * IC卡号
	 * 
	 * @return
	 */
	public String getBka100() {
		return bka100;
	}

	/**
	 * IC卡号
	 * 
	 * @return
	 */
	public void setBka100(String bka100) {
		this.bka100 = bka100;
	}

	/**
	 * 医院名称
	 * 
	 * @return
	 */
	public String getAkb021() {
		return akb021;
	}

	/**
	 * 医院名称
	 * 
	 * @return
	 */
	public void setAkb021(String akb021) {
		this.akb021 = akb021;
	}

	/**
	 * 出生日期
	 * 
	 * @return
	 */
	public String getAac006() {
		return aac006;
	}

	/**
	 * 出生日期
	 * 
	 * @return
	 */
	public void setAac006(String aac006) {
		this.aac006 = aac006;
	}

	/**
	 * 联系电话
	 * 
	 * @return
	 */
	public String getAae005() {
		return aae005;
	}

	/**
	 * 联系电话
	 * 
	 * @return
	 */
	public void setAae005(String aae005) {
		this.aae005 = aae005;
	}

	/**
	 * 单位电脑号
	 * 
	 * @return
	 */
	public long getAab001() {
		return aab001;
	}

	/**
	 * 单位电脑号
	 * 
	 * @return
	 */
	public void setAab001(long aab001) {
		this.aab001 = aab001;
	}

	/**
	 * 单位名称
	 * 
	 * @return
	 */
	public String getBka008() {
		return bka008;
	}

	/**
	 * 单位名称
	 * 
	 * @return
	 */
	public void setBka008(String bka008) {
		this.bka008 = bka008;
	}

	/**
	 * ？？？
	 * 
	 * @return
	 */
	public String getAab019() {
		return aab019;
	}

	/**
	 * ？？？
	 * 
	 * @return
	 */
	public void setAab019(String aab019) {
		this.aab019 = aab019;
	}

	/**
	 * 待遇类型
	 * 
	 * @return
	 */
	public String getBka006() {
		return bka006;
	}

	/**
	 * 待遇类型
	 * 
	 * @return
	 */
	public void setBka006(String bka006) {
		this.bka006 = bka006;
	}

	/**
	 * 本年业务次数
	 * 
	 * @return
	 */
	public long getBka010() {
		return bka010;
	}

	/**
	 * 本年业务次数
	 * 
	 * @return
	 */
	public void setBka010(long bka010) {
		this.bka010 = bka010;
	}

	/**
	 * 关联医疗机构编码
	 * 
	 * @return
	 */
	public String getBka011() {
		return bka011;
	}

	/**
	 * 关联医疗机构编码
	 * 
	 * @return
	 */
	public void setBka011(String bka011) {
		this.bka011 = bka011;
	}

	/**
	 * 关联业务序列号
	 * 
	 * @return
	 */
	public String getBka012() {
		return bka012;
	}

	/**
	 * 关联业务序列号
	 * 
	 * @return
	 */
	public void setBka012(String bka012) {
		this.bka012 = bka012;
	}

	/**
	 * 申请序列号
	 * 
	 * @return
	 */
	public long getAaz267() {
		return aaz267;
	}

	/**
	 * 申请序列号
	 * 
	 * @return
	 */
	public void setAaz267(long aaz267) {
		this.aaz267 = aaz267;
	}

	/**
	 * 业务登记日期
	 * 
	 * @return
	 */
	public String getAae036() {
		return aae036;
	}

	/**
	 * 业务登记日期
	 * 
	 * @return
	 */
	public void setAae036(String aae036) {
		this.aae036 = aae036;
	}

	/**
	 * 登记人工号
	 * 
	 * @return
	 */
	public String getAae011() {
		return aae011;
	}

	/**
	 * 登记人工号
	 * 
	 * @return
	 */
	public void setAae011(String aae011) {
		this.aae011 = aae011;
	}

	/**
	 * 登记人
	 * 
	 * @return
	 */
	public String getBka015() {
		return bka015;
	}

	/**
	 * 登记人
	 * 
	 * @return
	 */
	public void setBka015(String bka015) {
		this.bka015 = bka015;
	}

	/**
	 * 登记标志(0：正常 1：转院 2：二次返院（审批通过后RELA_SERIAL_NO为空） 3：急诊留观转住院 4：90天或180天结算)
	 * 
	 * @return
	 */
	public String getBka016() {
		return bka016;
	}

	/**
	 * 登记标志(0：正常 1：转院 2：二次返院（审批通过后RELA_SERIAL_NO为空） 3：急诊留观转住院 4：90天或180天结算)
	 * 
	 * @return
	 */
	public void setBka016(String bka016) {
		this.bka016 = bka016;
	}

	/**
	 * 业务开始情况（FR：提取冻结费用的零报业务 MW：医疗转工伤的零报业务）
	 * 
	 * @return
	 */
	public String getBka018() {
		return bka018;
	}

	/**
	 * 业务开始情况（FR：提取冻结费用的零报业务 MW：医疗转工伤的零报业务）
	 * 
	 * @return
	 */
	public void setBka018(String bka018) {
		this.bka018 = bka018;
	}

	/**
	 * 入院科室
	 * 
	 * @return
	 */
	public String getAkf001() {
		return akf001;
	}

	/**
	 * 入院科室
	 * 
	 * @return
	 */
	public void setAkf001(String akf001) {
		this.akf001 = akf001;
	}

	/**
	 * 入院科室名称
	 * 
	 * @return
	 */
	public String getBka020() {
		return bka020;
	}

	/**
	 * 入院科室名称
	 * 
	 * @return
	 */
	public void setBka020(String bka020) {
		this.bka020 = bka020;
	}

	/**
	 * 入院病区
	 * 
	 * @return
	 */
	public String getBka021() {
		return bka021;
	}

	/**
	 * 入院病区
	 * 
	 * @return
	 */
	public void setBka021(String bka021) {
		this.bka021 = bka021;
	}

	/**
	 * 入院病区名称
	 * 
	 * @return
	 */
	public String getBka022() {
		return bka022;
	}

	/**
	 * 入院病区名称
	 * 
	 * @return
	 */
	public void setBka022(String bka022) {
		this.bka022 = bka022;
	}

	/**
	 * 入院床位号
	 * 
	 * @return
	 */
	public String getAke020() {
		return ake020;
	}

	/**
	 * 入院床位号
	 * 
	 * @return
	 */
	public void setAke020(String ake020) {
		this.ake020 = ake020;
	}

	/**
	 * 床位类型
	 * 
	 * @return
	 */
	public String getBka024() {
		return bka024;
	}

	/**
	 * 床位类型
	 * 
	 * @return
	 */
	public void setBka024(String bka024) {
		this.bka024 = bka024;
	}

	/**
	 * 医院业务号
	 * 
	 * @return
	 */
	public String getBka025() {
		return bka025;
	}

	/**
	 * 医院业务号
	 * 
	 * @return
	 */
	public void setBka025(String bka025) {
		this.bka025 = bka025;
	}

	/**
	 * 入院疾病诊断
	 * 
	 * @return
	 */
	public String getAkc193() {
		return akc193;
	}

	/**
	 * 入院疾病诊断
	 * 
	 * @return
	 */
	public void setAkc193(String akc193) {
		this.akc193 = akc193;
	}

	/**
	 * 预付款总额
	 * 
	 * @return
	 */
	public double getBka245() {
		return bka245;
	}

	/**
	 * 预付款总额
	 * 
	 * @return
	 */
	public void setBka245(double bka245) {
		this.bka245 = bka245;
	}

	/**
	 * 确诊日期
	 * 
	 * @return
	 */
	public Date getBka028() {
		return bka028;
	}

	/**
	 * 确诊日期
	 * 
	 * @return
	 */
	public void setBka028(Date bka028) {
		this.bka028 = bka028;
	}

	/**
	 * 确诊疾病诊断
	 * 
	 * @return
	 */
	public String getBka029() {
		return bka029;
	}

	/**
	 * 确诊疾病诊断
	 * 
	 * @return
	 */
	public void setBka029(String bka029) {
		this.bka029 = bka029;
	}

	/**
	 * 住院天数
	 * 
	 * @return
	 */
	public long getBka030() {
		return bka030;
	}

	/**
	 * 住院天数
	 * 
	 * @return
	 */
	public void setBka030(long bka030) {
		this.bka030 = bka030;
	}

	/**
	 * 出院疾病诊断
	 * 
	 * @return
	 */
	public String getAkc196() {
		return akc196;
	}

	/**
	 * 出院疾病诊断
	 * 
	 * @return
	 */
	public void setAkc196(String akc196) {
		this.akc196 = akc196;
	}

	/**
	 * 结束人工号
	 * 
	 * @return
	 */
	public String getAae014() {
		return aae014;
	}

	/**
	 * 结束人工号
	 * 
	 * @return
	 */
	public void setAae014(String aae014) {
		this.aae014 = aae014;
	}

	/**
	 * 结束人
	 * 
	 * @return
	 */
	public String getBka034() {
		return bka034;
	}

	/**
	 * 结束人
	 * 
	 * @return
	 */
	public void setBka034(String bka034) {
		this.bka034 = bka034;
	}

	/**
	 * 人员类别
	 * 
	 * @return
	 */
	public String getBka035() {
		return bka035;
	}

	/**
	 * 人员类别
	 * 
	 * @return
	 */
	public void setBka035(String bka035) {
		this.bka035 = bka035;
	}

	/**
	 * 用卡标志
	 * 
	 * @return
	 */
	public String getBka036() {
		return bka036;
	}

	/**
	 * 用卡标志
	 * 
	 * @return
	 */
	public void setBka036(String bka036) {
		this.bka036 = bka036;
	}

	/**
	 * 中心报帐标志
	 * 
	 * @return
	 */
	public String getAka030() {
		return aka030;
	}

	/**
	 * 中心报帐标志
	 * 
	 * @return
	 */
	public void setAka030(String aka030) {
		this.aka030 = aka030;
	}

	/**
	 * 诊次结束时间
	 * 
	 * @return
	 */
	public Date getBka038() {
		return bka038;
	}

	/**
	 * 诊次结束时间
	 * 
	 * @return
	 */
	public void setBka038(Date bka038) {
		this.bka038 = bka038;
	}

	/**
	 * 完成标志
	 * 
	 * @return
	 */
	public String getBka039() {
		return bka039;
	}

	/**
	 * 完成标志
	 * 
	 * @return
	 */
	public void setBka039(String bka039) {
		this.bka039 = bka039;
	}

	/**
	 * POS机编号
	 * 
	 * @return
	 */
	public String getBka040() {
		return bka040;
	}

	/**
	 * POS机编号
	 * 
	 * @return
	 */
	public void setBka040(String bka040) {
		this.bka040 = bka040;
	}

	/**
	 * 锁定标志
	 * 
	 * @return
	 */
	public String getBka041() {
		return bka041;
	}

	/**
	 * 锁定标志
	 * 
	 * @return
	 */
	public void setBka041(String bka041) {
		this.bka041 = bka041;
	}

	/**
	 * 对应的工伤生育业务号
	 * 
	 * @return
	 */
	public String getBka042() {
		return bka042;
	}

	/**
	 * 对应的工伤生育业务号
	 * 
	 * @return
	 */
	public void setBka042(String bka042) {
		this.bka042 = bka042;
	}

	/**
	 * 备注
	 * 
	 * @return
	 */
	public String getBka043() {
		return bka043;
	}

	/**
	 * 备注
	 * 
	 * @return
	 */
	public void setBka043(String bka043) {
		this.bka043 = bka043;
	}
	
	/**
	 * 传输标志(0:未传输 1:已成功传输 2:未成功传输）
	 * 
	 * @return
	 */
	public String getBka044() {
		return bka044;
	}

	/**
	 * 传输标志(0:未传输 1:已成功传输 2:未成功传输）
	 * 
	 * @return
	 */
	public void setBka044(String bka044) {
		this.bka044 = bka044;
	}

	/**
	 * 完成时间
	 * 
	 * @return
	 */
	public String getBka045() {
		return bka045;
	}

	/**
	 * 完成时间
	 * 
	 * @return
	 */
	public void setBka045(String bka045) {
		this.bka045 = bka045;
	}
	
	/**
	 * 完成原因(0 业务正常完成 1 取消业务登记 2 业务信息异动 3 取消业务终结
	 * 
	 * @return
	 */
	public String getBka048() {
		return bka048;
	}

	/**
	 * 完成原因(0 业务正常完成 1 取消业务登记 2 业务信息异动 3 取消业务终结
	 * 
	 * @return
	 */
	public void setBka048(String bka048) {
		this.bka048 = bka048;
	}

	/**
	 * 对账标志 
	 * 
	 * @return
	 */
	public String getBka050() {
		return bka050;
	}

	/**
	 * 对账标志 
	 * 
	 * @return
	 */
	public void setBka050(String bka050) {
		this.bka050 = bka050;
	}

	/**
	 * 统筹区编码
	 * 
	 * @return
	 */
	public String getAaa027() {
		return aaa027;
	}

	/**
	 * 统筹区编码
	 * 
	 * @return
	 */
	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	/**
	 * 险种
	 * 
	 * @return
	 */
	public String getAae140() {
		return aae140;
	}

	/**
	 * 险种
	 * 
	 * @return
	 */
	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}

	/**
	 * 补充人员类别(0 缺省值;1-8 城居免缴（困难救助）人员类别;13 灵活就业人员) 备注：1- 最低生活保障对象 2- 低收入困难家庭 3-
	 * 重度残疾未成年人 4- 丧失劳动能力的重度残疾人 5- 三无人员 7- 优抚对象 8- 非本市低保、重残学生
	 * 
	 * @return
	 */
	public String getBka414() {
		return bka414;
	}

	/**
	 * 补充人员类别(0 缺省值;1-8 城居免缴（困难救助）人员类别;13 灵活就业人员) 备注：1- 最低生活保障对象 2- 低收入困难家庭 3-
	 * 重度残疾未成年人 4- 丧失劳动能力的重度残疾人 5- 三无人员 7- 优抚对象 8- 非本市低保、重残学生
	 * 
	 * @return
	 */
	public void setBka414(String bka414) {
		this.bka414 = bka414;
	}

	/**
	 * 异地就医类型（0、本地；1、市内异地；2、省内异地；3、跨省转入；4、跨省转出；）
	 * 
	 * @return
	 */
	public String getAae139() {
		return aae139;
	}

	/**
	 * 异地就医类型（0、本地；1、市内异地；2、省内异地；3、跨省转入；4、跨省转出；）
	 * 
	 * @return
	 */
	public void setAae139(String aae139) {
		this.aae139 = aae139;
	}

	/**
	 * 影像档案号
	 * 
	 * @return
	 */
	public String getBae009() {
		return bae009;
	}

	/**
	 * 影像档案号
	 * 
	 * @return
	 */
	public void setBae009(String bae009) {
		this.bae009 = bae009;
	}

	/**
	 * 联系人
	 * 
	 * @return
	 */
	public String getBaf313() {
		return baf313;
	}

	/**
	 * 联系人
	 * 
	 * @return
	 */
	public void setBaf313(String baf313) {
		this.baf313 = baf313;
	}

	/**
	 * 联系地址
	 * 
	 * @return
	 */
	public String getBaz113() {
		return baz113;
	}

	/**
	 * 联系地址
	 * 
	 * @return
	 */
	public void setBaz113(String baz113) {
		this.baz113 = baz113;
	}

	public String getBka501() {
		return bka501;
	}

	public void setBka501(String bka501) {
		this.bka501 = bka501;
	}

	public String getBka502() {
		return bka502;
	}

	public void setBka502(String bka502) {
		this.bka502 = bka502;
	}

	public String getBka503() {
		return bka503;
	}

	public void setBka503(String bka503) {
		this.bka503 = bka503;
	}

	public Date getBka504() {
		return bka504;
	}

	public void setBka504(Date bka504) {
		this.bka504 = bka504;
	}

	public Date getBka505() {
		return bka505;
	}

	public void setBka505(Date bka505) {
		this.bka505 = bka505;
	}

	public Date getBka506() {
		return bka506;
	}

	public void setBka506(Date bka506) {
		this.bka506 = bka506;
	}

	public long getBka507() {
		return bka507;
	}

	public void setBka507(long bka507) {
		this.bka507 = bka507;
	}

	public long getBka508() {
		return bka508;
	}

	public void setBka508(long bka508) {
		this.bka508 = bka508;
	}

	public long getBka509() {
		return bka509;
	}

	public void setBka509(long bka509) {
		this.bka509 = bka509;
	}

	public String getBka510() {
		return bka510;
	}

	public void setBka510(String bka510) {
		this.bka510 = bka510;
	}

	/**
	 * 0收费 1退费
	 * 
	 * @return
	 */
	public String getReduceflag() {
		return reduceflag;
	}

	/**
	 * 0收费 1退费
	 * 
	 * @return
	 */
	public void setReduceflag(String reduceflag) {
		this.reduceflag = reduceflag;
	}

	/**
	 * 退费的批次
	 * 
	 * @return
	 */
	public long getReducefeebatch() {
		return reducefeebatch;
	}

	/**
	 * 退费的批次
	 * 
	 * @return
	 */
	public void setReducefeebatch(long reducefeebatch) {
		this.reducefeebatch = reducefeebatch;
	}

	/**
	 * 查询条件
	 * 
	 * @return
	 */
	public String getArg_name() {
		return arg_name;
	}

	/**
	 * 查询条件
	 * 
	 * @return
	 */
	public void setArg_name(String arg_name) {
		this.arg_name = arg_name;
	}

	/**
	 * 查询值
	 * 
	 * @return
	 */
	public String getArg_value() {
		return arg_value;
	}

	/**
	 * 查询值
	 * 
	 * @return
	 */
	public void setArg_value(String arg_value) {
		this.arg_value = arg_value;
	}

	/**
	 * @return the bka065
	 */
	public Date getBka065() {
		return bka065;
	}

	/**
	 * @param bka065
	 *            the bka065 to set
	 */
	public void setBka065(Date bka065) {
		this.bka065 = bka065;
	}

	/**
	 * @return the bka051
	 */
	public Date getBka051() {
		return bka051;
	}

	/**
	 * @param bka051
	 *            the bka051 to set
	 */
	public void setBka051(Date bka051) {
		this.bka051 = bka051;
	}

	public String getAllowed_no_readcard() {
		return allowed_no_readcard;
	}

	public void setAllowed_no_readcard(String allowed_no_readcard) {
		this.allowed_no_readcard = allowed_no_readcard;
	}

	public long getAaz213() {
		return aaz213;
	}

	public void setAaz213(long aaz213) {
		this.aaz213 = aaz213;
	}

	/**
	 * 个人化通用接口子系统通道标识  1是，0否 
	 * @return
	 */
	public String getComm_subsys_flag() {
		return comm_subsys_flag;
	}

	/**
	 * 个人化通用接口子系统通道标识  1是，0否 
	 * @return
	 */
	public void setComm_subsys_flag(String comm_subsys_flag) {
		this.comm_subsys_flag = comm_subsys_flag;
	}

	public String getBae410() {
		return bae410;
	}

	public void setBae410(String bae410) {
		this.bae410 = bae410;
	}

	public String getAppPayFlag() {
		return appPayFlag;
	}

	public void setAppPayFlag(String appPayFlag) {
		this.appPayFlag = appPayFlag;
	}

	public String getBka898() {
		return bka898;
	}

	public void setBka898(String bka898) {
		this.bka898 = bka898;
	}


	public String getAac066() {
		return aac066;
	}

	public void setAac066(String aac066) {
		this.aac066 = aac066;
	}
	

	
	 /**
     * 基金冻结状态(0正常、1冻结、9未参保)
     * @return
     */
	public String getBka888() {
		return bka888;
	}
	
	/**
	 * 基金冻结状态(0正常、1冻结、9未参保)
	 * @param bka888
	 */
	public void setBka888(String bka888) {
		this.bka888 = bka888;
	}

	/**
	 * 灵活就业人员标识1、0
	 * @return
	 */
	public String getBka700() {
		return bka700;
	}

	/**
	 * 灵活就业人员标识1、0
	 * @return
	 */
	public void setBka700(String bka700) {
		this.bka700 = bka700;
	}
	private String aaz218;

	public String getAaz218() {
		return aaz218;
	}

	public void setAaz218(String aaz218) {
		this.aaz218 = aaz218;
	}

	/**
	 * 联系人
	 * @return
	 */
	public String getAae004() {
		return aae004;
	}

	/**
	 * 联系人
	 * @param aae004
	 */
	public void setAae004(String aae004) {
		this.aae004 = aae004;
	}

	/**
	 * 联系地址
	 * @return
	 */
	public String getAae006() {
		return aae006;
	}

	/**
	 * 联系地址
	 * @param aae006
	 */
	public void setAae006(String aae006) {
		this.aae006 = aae006;
	}

	/**
	 * 参保地
	 * @param baa027_name
	 */
	public String getBaa027_name() {
		return baa027_name;
	}

	/**
	 * 参保地
	 * @param baa027_name
	 */
	public void setBaa027_name(String baa027_name) {
		this.baa027_name = baa027_name;
	}

	/**
	 * 待遇类型
	 * @param bka006_name
	 */
	public String getBka006_name() {
		return bka006_name;
	}

	/**
	 * 待遇类型
	 * @param bka006_name
	 */
	public void setBka006_name(String bka006_name) {
		this.bka006_name = bka006_name;
	}

	/**
	 * 性别
	 * @param aac004_name
	 */
	public String getAac004_name() {
		return aac004_name;
	}

	/**
	 * 性别
	 * @param aac004_name
	 */
	public void setAac004_name(String aac004_name) {
		this.aac004_name = aac004_name;
	}

	/**
	 * 人员类别
	 * @param bka035_name
	 */
	public String getBka035_name() {
		return bka035_name;
	}

	/**
	 * 人员类别
	 * @param bka035_name
	 */
	public void setBka035_name(String bka035_name) {
		this.bka035_name = bka035_name;
	}

	/**
	 * 险种
	 * @param aae140_name
	 */
	public String getAae140_name() {
		return aae140_name;
	}

	/**
	 * 险种
	 * @param aae140_name
	 */
	public void setAae140_name(String aae140_name) {
		this.aae140_name = aae140_name;
	}

	/**
	 * 基金冻结情况
	 * @param bka888_name
	 */
	public String getBka888_name() {
		return bka888_name;
	}

	/**
	 * 基金冻结情况
	 * @param bka888_name
	 */
	public void setBka888_name(String bka888_name) {
		this.bka888_name = bka888_name;
	}

	/**
	 * 医院级别
	 * @param bke301
	 */
	public String getBke301() {
		return bke301;
	}

	/**
	 * 基医院级别
	 * @param bke301
	 */
	public void setBke301(String bke301) {
		this.bke301 = bke301;
	}

	/**
	 * 医院等级
	 * @param aka101
	 */
	public String getAka101() {
		return aka101;
	}

	/**
	 * 医院等级
	 * @param aka101
	 */
	public void setAka101(String aka101) {
		this.aka101 = aka101;
	}

	
	/**
	 * 发票号
	 * @param akc191
	 */
	public String getAkc191() {
		return akc191;
	}

	/**
	 * 发票号
	 * @param akc191
	 */
	public void setAkc191(String akc191) {
		this.akc191 = akc191;
	}

	/**
	 * 住院号
	 * @param  akc190
	 */
	public String getAkc190() {
		return akc190;
	}

	/**
	 * 住院号
	 * @param akc190
	 */
	public void setAkc190(String akc190) {
		this.akc190 = akc190;
	}

	/**
	 * 医患最终结束日期
	 * @param 
	 */
	public String getAkc194() {
		return akc194;
	}

	/**
	 * 医患最终结束日期
	 * @param akc194
	 */
	public void setAkc194(String akc194) {
		this.akc194 = akc194;
	}

	/**
	 * 最后操作时间
	 * @return
	 */
	public String getBka049() {
		return bka049;
	}

	/**
	 * 最后操作时间
	 * @param bka049
	 */
	public void setBka049(String bka049) {
		this.bka049 = bka049;
	}

	/**
	 * 入院疾病诊断名称
	 * @return
	 */
	public String getBkz101() {
		return bkz101;
	}

	/**
	 * 入院疾病诊断名称
	 * @param bkz101
	 */
	public void setBkz101(String bkz101) {
		this.bkz101 = bkz101;
	}

	/**
	 * 出院疾病诊断名称
	 * @return
	 */
	public String getBkz102() {
		return bkz102;
	}

	/**
	 * 出院疾病诊断名称
	 * @param bkz102
	 */
	public void setBkz102(String bkz102) {
		this.bkz102 = bkz102;
	}

	/**
	 * 病种严重程度(A：病种单纯 B：严重 C：严重并发 D：危重)
	 * @return
	 */
	public String getBka061() {
		return bka061;
	}

	/**
	 * 病种严重程度(A：病种单纯 B：严重 C：严重并发 D：危重)
	 * @param bka061
	 */
	public void setBka061(String bka061) {
		this.bka061 = bka061;
	}

	/**
	 * 治愈情况（好转、死亡）
	 * @return
	 */
	public String getBka066() {
		return bka066;
	}

	/**
	 * 治愈情况（好转、死亡）
	 * @param bka066
	 */
	public void setBka066(String bka066) {
		this.bka066 = bka066;
	}

	/**
	 * 出院诊断医生
	 * @return
	 */
	public String getAke021() {
		return ake021;
	}

	/**
	 * 出院诊断医生
	 * @param ake021
	 */
	public void setAke021(String ake021) {
		this.ake021 = ake021;
	}

	/**
	 * 入院诊断医生
	 * @return
	 */
	public String getAke022() {
		return ake022;
	}

	/**
	 * 入院诊断医生
	 * @param ake022
	 */
	public void setAke022(String ake022) {
		this.ake022 = ake022;
	}

	/**
	 * 主要病情描述
	 * @return
	 */
	public String getAke024() {
		return ake024;
	}

	/**
	 * 主要病情描述
	 * @param ake024
	 */
	public void setAke024(String ake024) {
		this.ake024 = ake024;
	}

	/**
	 * 报销原因
	 * @return
	 */
	public String getAke013() {
		return ake013;
	}

	/**
	 * 报销原因
	 * @param ake013
	 */
	public void setAke013(String ake013) {
		this.ake013 = ake013;
	}

	/**
	 * 单位管理码
	 * @return
	 */
	public String getAab999() {
		return aab999;
	}

	/**
	 * 单位管理码
	 * @param aab999
	 */
	public void setAab999(String aab999) {
		this.aab999 = aab999;
	}

	/**
	 * 生物特征信息id（与智能医审对接
	 * @return
	 */
	public String getBkm003() {
		return bkm003;
	}

	/**
	 * 生物特征信息id（与智能医审对接
	 * @param bkm003
	 */
	public void setBkm003(String bkm003) {
		this.bkm003 = bkm003;
	}

	/**
	 * 住院类型（政策计算用特殊标志）（reg_flag）
	 * @return
	 */
	public String getBka068() {
		return bka068;
	}

	/**
	 * 住院类型（政策计算用特殊标志）（reg_flag）
	 * @param bka068
	 */
	public void setBka068(String bka068) {
		this.bka068 = bka068;
	}

	/**
	 * 报账类型 1-普通报账 2-大类报账 3-汇总报账
	 * @return
	 */
	public String getBka078() {
		return bka078;
	}

	/**
	 * 报账类型 1-普通报账 2-大类报账 3-汇总报账
	 * @param bka078
	 */
	public void setBka078(String bka078) {
		this.bka078 = bka078;
	}

	/**
	 * 特殊补助类别（低保、五保、重残、精准扶贫、标准）
	 * @return
	 */
	public String getAac148() {
		return aac148;
	}

	/**
	 * 特殊补助类别（低保、五保、重残、精准扶贫、标准）
	 * @param aac148
	 */
	public void setAac148(String aac148) {
		this.aac148 = aac148;
	}

	/**
	 * 取消结算标志（1、取消结算，2：取消出院登记；3：取消就诊登记；
	 * @return
	 */
	public String getAka045() {
		return aka045;
	}

	/**
	 * 取消结算标志（1、取消结算，2：取消出院登记；3：取消就诊登记；
	 * @param aka045
	 */
	public void setAka045(String aka045) {
		this.aka045 = aka045;
	}

	/**
	 * 业务终结情况
	 * @return
	 */
	public String getBka072() {
		return bka072;
	}

	/**
	 * 业务终结情况
	 * @param bka072
	 */
	public void setBka072(String bka072) {
		this.bka072 = bka072;
	}

	/**
	 * 化疗费
	 * @return
	 */
	public String getAke025() {
		return ake025;
	}

	/**
	 * 化疗费
	 * @param ake025
	 */
	public void setAke025(String ake025) {
		this.ake025 = ake025;
	}

	/**
	 * 人员参保状态
	 * @param aac031
	 */
	public String getAac031() {
		return aac031;
	}

	/**
	 * 人员参保状态
	 * @param ake025
	 */
	public void setAac031(String aac031) {
		this.aac031 = aac031;
	}

	/**
	 * 人员参保状态
	 * @return
	 */
	public String getAac008() {
		return aac008;
	}

	public void setAac008(String aac031_name) {
		this.aac008 = aac031_name;
	}

	public String getDbty_flag() {
		return dbty_flag;
	}

	public void setDbty_flag(String dbty_flag) {
		this.dbty_flag = dbty_flag;
	}

	public List<Map> getBizinfo() {
		return bizinfo;
	}

	public void setBizinfo(List<Map> bizinfo) {
		this.bizinfo = bizinfo;
	}

	public List<Map> getFeeinfo() {
		return feeinfo;
	}

	public void setFeeinfo(List<Map> feeinfo) {
		this.feeinfo = feeinfo;
	}

	public String getAkb022() {
		return akb022;
	}

	public void setAkb022(String akb022) {
		this.akb022 = akb022;
	}
	
}
