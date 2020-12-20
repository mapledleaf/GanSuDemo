package com.powersi.biz.medicare.comminter.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.powersi.comm.mybatis.Page;

public class KAB3DTO extends Page{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String kab3id;//台账id
	private String akb020;//医院编号
	private String aaz217;//就医登记号
	private String aka130;//业务类别编号
	private String kab1id;//票据ID
	private String bae410;//票据类型，00:门诊住院通用 01：门诊发票 02：住院发票  03：门诊挂号 04：门诊充值 11：住院预交
	private String bae413;//发票号码
	private BigDecimal bae417;//发票金额
	private BigDecimal aae019;//总费用
	private BigDecimal aae020;//基金支付
	private BigDecimal aae021;//个人自付金额
	private BigDecimal aae022;//个人自费金额
	private BigDecimal aae023;//个人账户支付
	private BigDecimal aae024;//现金支付
	private int bae418;//打印次数
	private Date bae419;//最后打印时间
	private String bka014;//开票人ID
	private String bka015;//开票人
	private String aae030;//开票时间
	private String aae100;//有效标志 0 无效 1有效
	private String aae013;//备注
	private Date bka013;//台账登记时间
	private String bka019;//住院科室
	private String bka020;//住院科室名称
	private String bka025;//医院业务号（住院号）
	private String aac003;//姓名
	private String aac004;//性别
	private String aac002;//公民身份号码
	private String bka100;//IC卡号
	private String billReport;//发票ID
	private String bae410_code;
	private String bka445;//门诊1住院2标识
	private Date bka017; //入院时间
	private Date bka032; //出院时间
	private String aaa027;
	
	
	public String getAaa027() {
		return aaa027;
	}
	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}
	public String getBka445() {
		return bka445;
	}
	public void setBka445(String bka445) {
		this.bka445 = bka445;
	}
	public Date getBka017() {
		return bka017;
	}
	public void setBka017(Date bka017) {
		this.bka017 = bka017;
	}
	public Date getBka032() {
		return bka032;
	}
	public void setBka032(Date bka032) {
		this.bka032 = bka032;
	}
	/**西药**/ 
	private BigDecimal bae421;
	/**中成药 **/ 
	private BigDecimal bae422;
	/**中草药**/ 
	private BigDecimal bae423;
	/**常规检查 **/
	private BigDecimal bae424;
	/**CT**/ 
	private BigDecimal bae425;
	/**核磁 **/ 
	private BigDecimal bae426;
	/**B超**/ 
	private BigDecimal bae427;
	/**治疗费 **/ 
	private BigDecimal bae428;
	/**化验费 **/ 
	private BigDecimal bae429;
	/**手术费 **/
	private BigDecimal bae430;
	/**输氧费 **/
	private BigDecimal bae431;
	/**放射费 **/ 
	private BigDecimal bae432;
	/**注射费 **/
	private BigDecimal bae433;
	/**透析费**/
	private BigDecimal bae434;
	/** 化疗费**/
	private BigDecimal bae435;
	/** 床位费**/   
	private BigDecimal bae436;
	/** 材料费**/ 
	private BigDecimal bae437;
	/** 护理费**/  
	private BigDecimal bae438;
	/** 一般诊疗费**/
	private BigDecimal bae439;
	/** 挂号费**/ 
	private BigDecimal bae440;
	/** 麻醉费**/  
	private BigDecimal bae441;
	/** 其他费用**/
	private BigDecimal bae442;

	
	public String getBae410_code() {
		return bae410_code;
	}
	public void setBae410_code(String bae410_code) {
		this.bae410_code = bae410_code;
	}
	private Date fromdate;
	private Date todate;
	
	public Date getFromdate() {
		return fromdate;
	}
	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}
	public Date getTodate() {
		return todate;
	}
	public void setTodate(Date todate) {
		this.todate = todate;
	}
	public String getKab3id() {
		return kab3id;
	}
	public void setKab3id(String kab3id) {
		this.kab3id = kab3id;
	}
	public String getAkb020() {
		return akb020;
	}
	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}
	public String getAaz217() {
		return aaz217;
	}
	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217;
	}
	public String getAka130() {
		return aka130;
	}
	public void setAka130(String aka130) {
		this.aka130 = aka130;
	}
	public String getKab1id() {
		return kab1id;
	}
	public void setKab1id(String kab1id) {
		this.kab1id = kab1id;
	}
	public String getBae410() {
		return bae410;
	}
	public void setBae410(String bae410) {
		this.bae410 = bae410;
	}
	public String getBae413() {
		return bae413;
	}
	public void setBae413(String bae413) {
		this.bae413 = bae413;
	}
	public BigDecimal getBae417() {
		return bae417;
	}
	public void setBae417(BigDecimal bae417) {
		this.bae417 = bae417;
	}
	public BigDecimal getAae019() {
		return aae019;
	}
	public void setAae019(BigDecimal aae019) {
		this.aae019 = aae019;
	}
	public BigDecimal getAae020() {
		return aae020;
	}
	public void setAae020(BigDecimal aae020) {
		this.aae020 = aae020;
	}
	public BigDecimal getAae021() {
		return aae021;
	}
	public void setAae021(BigDecimal aae021) {
		this.aae021 = aae021;
	}
	public BigDecimal getAae022() {
		return aae022;
	}
	public void setAae022(BigDecimal aae022) {
		this.aae022 = aae022;
	}
	public BigDecimal getAae023() {
		return aae023;
	}
	public void setAae023(BigDecimal aae023) {
		this.aae023 = aae023;
	}
	public BigDecimal getAae024() {
		return aae024;
	}
	public void setAae024(BigDecimal aae024) {
		this.aae024 = aae024;
	}
	public int getBae418() {
		return bae418;
	}
	public void setBae418(int bae418) {
		this.bae418 = bae418;
	}
	public Date getBae419() {
		return bae419;
	}
	public void setBae419(Date bae419) {
		this.bae419 = bae419;
	}
	public String getBka014() {
		return bka014;
	}
	public void setBka014(String bka014) {
		this.bka014 = bka014;
	}
	public String getBka015() {
		return bka015;
	}
	public void setBka015(String bka015) {
		this.bka015 = bka015;
	}
	public String getAae030() {
		return aae030;
	}
	public void setAae030(String aae030) {
		this.aae030 = aae030;
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
	public Date getBka013() {
		return bka013;
	}
	public void setBka013(Date bka013) {
		this.bka013 = bka013;
	}
	public String getBka019() {
		return bka019;
	}
	public void setBka019(String bka019) {
		this.bka019 = bka019;
	}
	public String getBka020() {
		return bka020;
	}
	public void setBka020(String bka020) {
		this.bka020 = bka020;
	}
	public String getBka025() {
		return bka025;
	}
	public void setBka025(String bka025) {
		this.bka025 = bka025;
	}
	public String getAac003() {
		return aac003;
	}
	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}
	public String getAac004() {
		return aac004;
	}
	public void setAac004(String aac004) {
		this.aac004 = aac004;
	}
	public String getAac002() {
		return aac002;
	}
	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}
	public String getBka100() {
		return bka100;
	}
	public void setBka100(String bka100) {
		this.bka100 = bka100;
	}
	public String getBillReport() {
		return billReport;
	}
	public void setBillReport(String billReport) {
		this.billReport = billReport;
	}
	public BigDecimal getBae421() {
		return bae421;
	}
	public void setBae421(BigDecimal bae421) {
		this.bae421 = bae421;
	}
	public BigDecimal getBae422() {
		return bae422;
	}
	public void setBae422(BigDecimal bae422) {
		this.bae422 = bae422;
	}
	public BigDecimal getBae423() {
		return bae423;
	}
	public void setBae423(BigDecimal bae423) {
		this.bae423 = bae423;
	}
	public BigDecimal getBae424() {
		return bae424;
	}
	public void setBae424(BigDecimal bae424) {
		this.bae424 = bae424;
	}
	public BigDecimal getBae425() {
		return bae425;
	}
	public void setBae425(BigDecimal bae425) {
		this.bae425 = bae425;
	}
	public BigDecimal getBae426() {
		return bae426;
	}
	public void setBae426(BigDecimal bae426) {
		this.bae426 = bae426;
	}
	public BigDecimal getBae427() {
		return bae427;
	}
	public void setBae427(BigDecimal bae427) {
		this.bae427 = bae427;
	}
	public BigDecimal getBae428() {
		return bae428;
	}
	public void setBae428(BigDecimal bae428) {
		this.bae428 = bae428;
	}
	public BigDecimal getBae429() {
		return bae429;
	}
	public void setBae429(BigDecimal bae429) {
		this.bae429 = bae429;
	}
	public BigDecimal getBae430() {
		return bae430;
	}
	public void setBae430(BigDecimal bae430) {
		this.bae430 = bae430;
	}
	public BigDecimal getBae431() {
		return bae431;
	}
	public void setBae431(BigDecimal bae431) {
		this.bae431 = bae431;
	}
	public BigDecimal getBae432() {
		return bae432;
	}
	public void setBae432(BigDecimal bae432) {
		this.bae432 = bae432;
	}
	public BigDecimal getBae433() {
		return bae433;
	}
	public void setBae433(BigDecimal bae433) {
		this.bae433 = bae433;
	}
	public BigDecimal getBae434() {
		return bae434;
	}
	public void setBae434(BigDecimal bae434) {
		this.bae434 = bae434;
	}
	public BigDecimal getBae435() {
		return bae435;
	}
	public void setBae435(BigDecimal bae435) {
		this.bae435 = bae435;
	}
	public BigDecimal getBae436() {
		return bae436;
	}
	public void setBae436(BigDecimal bae436) {
		this.bae436 = bae436;
	}
	public BigDecimal getBae437() {
		return bae437;
	}
	public void setBae437(BigDecimal bae437) {
		this.bae437 = bae437;
	}
	public BigDecimal getBae438() {
		return bae438;
	}
	public void setBae438(BigDecimal bae438) {
		this.bae438 = bae438;
	}
	public BigDecimal getBae439() {
		return bae439;
	}
	public void setBae439(BigDecimal bae439) {
		this.bae439 = bae439;
	}
	public BigDecimal getBae440() {
		return bae440;
	}
	public void setBae440(BigDecimal bae440) {
		this.bae440 = bae440;
	}
	public BigDecimal getBae441() {
		return bae441;
	}
	public void setBae441(BigDecimal bae441) {
		this.bae441 = bae441;
	}
	public BigDecimal getBae442() {
		return bae442;
	}
	public void setBae442(BigDecimal bae442) {
		this.bae442 = bae442;
	}
	
}
