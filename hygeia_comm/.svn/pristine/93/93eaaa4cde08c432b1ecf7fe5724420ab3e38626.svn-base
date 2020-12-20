package com.powersi.biz.medicare.inhospital.pojo;

import com.powersi.biz.medicare.comm.pojo.RemotingDTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author 刘勇
 *
 */
public class FeeDTO extends RemotingDTO {

	private static final long serialVersionUID = 1L;

	private String kcd2id;

	private String kcd1id;

	/**
	 * 医院编号 AKB020 VARCHAR2(20) 20 FALSE FALSE TRUE
	 */
	private String akb020;

	/**
	 * 业务序列号 aaz217 VARCHAR2(20) 20 FALSE FALSE TRUE
	 */
	private String aaz217;

	/**
	 * 费用批次 BKA001 NUMBER(12) 12 FALSE FALSE TRUE
	 */
	private Long bka001;

	/**
	 * 费用序列号 AAZ213 NUMBER(12) 12 FALSE FALSE TRUE
	 */
	private Long aaz213;

	/**
	 * 统计类别 AKA063 VARCHAR2(6) 6 FALSE FALSE TRUE
	 */
	private String aka063;

	/**
	 * 项目药品类型(0:项目，1：西药，2：中成药，3：中草药) AKE003 VARCHAR2(1) 1 FALSE FALSE TRUE
	 */
	private String ake003;

	/**
	 * 中心药品项目编码 AKE001 VARCHAR2(20) 20 FALSE FALSE TRUE
	 */
	private String ake001;

	/**
	 * 中心药品项目名称 AKE002 VARCHAR2(100) 100 FALSE FALSE TRUE
	 */
	private String ake002;

	/**
	 * 医院药品项目编码 AKE005 VARCHAR2(20) 20 FALSE FALSE TRUE
	 */
	private String ake005;

	/**
	 * 医院药品项目名称 AKE006 VARCHAR2(100) 100 FALSE FALSE TRUE
	 */
	private String ake006;

	/**
	 * 申请序列号 AAZ267 NUMBER(12) 12 FALSE FALSE FALSE
	 */
	private Long aaz267;

	/**
	 * 费用发生时间 ake007 DATE FALSE FALSE TRUE
	 */
	private Date ake007;

	/**
	 * 剂型 aka070 VARCHAR2(30) 30 FALSE FALSE FALSE
	 */
	private String aka070;

	/**
	 * 厂家 bka073 VARCHAR2(50) 50 FALSE FALSE FALSE
	 */
	private String bka073;

	/**
	 * 规格 aka074 VARCHAR2(300) 300 FALSE FALSE FALSE
	 */
	private String aka074;

	/**
	 * 计量单位 aka067 VARCHAR2(20) 20 FALSE FALSE FALSE
	 */
	private String aka067;

	/**
	 * 单价 bka040 NUMBER(10,4) 10 4 FALSE FALSE TRUE
	 */
	private BigDecimal bka040;

	/**
	 * 用量 akc226 NUMBER(12,4) 12 4 FALSE FALSE TRUE
	 */
	private BigDecimal akc226;

	/**
	 * 金额 aae019 NUMBER(12,4) 12 4 FALSE FALSE TRUE
	 */
	private BigDecimal aae019;

	/**
	 * 冲减金额（主要为计算方便使用） BKA059 NUMBER(12,4) 12 4 FALSE FALSE FALSE
	 */
	private BigDecimal bka059;

	/**
	 * 使用标志（1：出院带药 2：抢救用药 3：急诊） BKA060 VARCHAR2(1) 1 FALSE FALSE FALSE
	 */
	private String bka060;

	/**
	 * 出院带药天数 BKA061 NUMBER(12) 12 FALSE FALSE FALSE
	 */
	private Long bka061;

	/**
	 * 对应费用序列号 BKA062 NUMBER(12) 12 FALSE FALSE FALSE
	 */
	private Long bka062;

	/**
	 * 录入人工号 BKA063 VARCHAR2(20) 20 FALSE FALSE TRUE
	 */
	private String bka063;

	/**
	 * 录入人 BKA064 VARCHAR2(50) 50 FALSE FALSE FALSE
	 */
	private String bka064;

	/**
	 * 录入时间 BKA065 DATE FALSE FALSE TRUE
	 */
	private Date bka065;

	/**
	 * 计算标志 BKA066 VARCHAR2(1) 1 FALSE FALSE FALSE
	 */
	private String bka066;

	/**
	 * 费用冻结标志，用来表识参保人所在单位的基本医疗保险被冻结期间录入的费用。0：未冻结；1：已冻结；2：冻结已处理 BKA067
	 * VARCHAR2(1) 1 FALSE FALSE FALSE
	 */
	private String bka067;

	/**
	 * 对应冻结的费用序列号 BKA068 NUMBER(12) 12 FALSE FALSE FALSE
	 */
	private Long bka068;

	/**
	 * 费用上传时间 BKA069 DATE FALSE FALSE FALSE
	 */
	private Date bka069;

	/**
	 * 处方号 BKA070 VARCHAR2(20) 20 FALSE FALSE FALSE
	 */
	private String bka070;

	/**
	 * 对应医院费用号 BKA071 VARCHAR2(20) 20 FALSE FALSE FALSE
	 */
	private String bka071;

	/**
	 * 处方医生编号 BKA074 VARCHAR2(20) 20 FALSE FALSE FALSE
	 */
	private String bka074;

	/**
	 * 处方医生姓名 BKA075 VARCHAR2(50) 50 FALSE FALSE FALSE
	 */
	private String bka075;

	/**
	 * 审核标志 BKA076 VARCHAR2(1) 1 FALSE FALSE TRUE
	 */
	private String bka076;

	/**
	 * 传输标志(0:未传输 1:已成功传输 2:未成功传输） BKA044 VARCHAR2(1) 1 FALSE FALSE TRUE
	 */
	private String bka044;

	/**
	 * 城职对应待遇值 BKA511 VARCHAR2(20) 20 FALSE FALSE FALSE
	 */
	private String bka511;

	/**
	 * 城乡对应待遇值 BKA512 VARCHAR2(20) 20 FALSE FALSE FALSE
	 */
	private String bka512;
	
	/**
	 * 社保三大目录ID bigint NOT NULL 
	 */
	private String aaz231;
	
	/**
	 * 医疗机构三大目录ID bigint 
	 */
	private String aaz277;
	
	/**
	 * 收费项目等级(甲、乙、丙)
	 */
	private String aka065;
	
	private String ake105;
	
	/**
	 * 费用明细信息标识
	 */
	private String feeinfoflag;

	/**
	 * 	 //NTS20051900637 业务系统增加超范围用药标识传输 杨世明 20200520
	 */
	private String bkz103;

	public String getBkz103() {
		return bkz103;
	}

	public void setBkz103(String bkz103) {
		this.bkz103 = bkz103;
	}

	public String getFeeinfoflag()
	{
		return feeinfoflag;
	}

	public void setFeeinfoflag(String feeinfoflag)
	{
		this.feeinfoflag = feeinfoflag;
	}

	/**
	 * 药监本位码
	 * @return
	 */
	public String getAke105() {
		return ake105;
	}

	/**
	 * 药监本位码
	 * @param ake105
	 */
	public void setAke105(String ake105) {
		this.ake105 = ake105;
	}

	public String getAka065() {
		return aka065;
	}

	public void setAka065(String aka065) {
		this.aka065 = aka065;
	}

	public String getAaz277() {
		return aaz277;
	}

	public void setAaz277(String aaz277) {
		this.aaz277 = aaz277;
	}

	public String getAaz231() {
		return aaz231;
	}

	public void setAaz231(String aaz231) {
		this.aaz231 = aaz231;
	}

	public String getKcd2id() {
		return kcd2id;
	}

	public void setKcd2id(String kcd2id) {
		this.kcd2id = kcd2id == null ? null : kcd2id.trim();
	}

	public String getKcd1id() {
		return kcd1id;
	}

	public void setKcd1id(String kcd1id) {
		this.kcd1id = kcd1id == null ? null : kcd1id.trim();
	}

	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020 == null ? null : akb020.trim();
	}

	public String getAaz217() {
		return aaz217;
	}

	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217 == null ? null : aaz217.trim();
	}

	/**
	 * 
	 * @return 费用批次
	 */
	public Long getBka001() {
		return bka001;
	}

	/**
	 * 
	 * @param bka001
	 *            费用批次
	 */
	public void setBka001(Long bka001) {
		this.bka001 = bka001;
	}

	/**
	 * 
	 * @return 费用序列号
	 */
	public Long getAaz213() {
		return aaz213;
	}

	/**
	 * 
	 * @param aaz213
	 *            费用序列号
	 */
	public void setAaz213(Long aaz213) {
		this.aaz213 = aaz213;
	}

	/**
	 * 
	 * @return 统计类别
	 */
	public String getAka063() {
		return aka063;
	}

	/**
	 * 
	 * @param aka063
	 *            统计类别
	 */
	public void setAka063(String aka063) {
		this.aka063 = aka063 == null ? null : aka063.trim();
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
	 * @param ake003
	 *            项目药品类型
	 */
	public void setAke003(String ake003) {
		this.ake003 = ake003 == null ? null : ake003.trim();
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
	 * @param ake001
	 *            中心药品项目编码
	 */
	public void setAke001(String ake001) {
		this.ake001 = ake001 == null ? null : ake001.trim();
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
	 * @param ake002
	 *            中心药品项目名称
	 */
	public void setAke002(String ake002) {
		this.ake002 = ake002 == null ? null : ake002.trim();
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
	 * @param ake005
	 *            医院药品项目编码
	 */
	public void setAke005(String ake005) {
		this.ake005 = ake005 == null ? null : ake005.trim();
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
	 * @param ake006
	 *            医院药品项目名称
	 */
	public void setAke006(String ake006) {
		this.ake006 = ake006 == null ? null : ake006.trim();
	}

	public Long getAaz267() {
		return aaz267;
	}

	public void setAaz267(Long aaz267) {
		this.aaz267 = aaz267;
	}

	public Date getAke007() {
		return ake007;
	}

	public void setAke007(Date ake007) {
		this.ake007 = ake007;
	}

	/**
	 * 
	 * @return 医院目录剂型(剂型)
	 */
	public String getAka070() {
		return aka070;
	}

	/**
	 * 
	 * @param aka070
	 *            医院目录剂型(剂型)
	 */
	public void setAka070(String aka070) {
		this.aka070 = aka070 == null ? null : aka070.trim();
	}

	/**
	 * 
	 * @return 医院目录厂家(厂家)
	 */
	public String getBka073() {
		return bka073;
	}

	/**
	 * 
	 * @param bka073
	 *            医院目录厂家(厂家)
	 */
	public void setBka073(String bka073) {
		this.bka073 = bka073 == null ? null : bka073.trim();
	}

	/**
	 * 
	 * @return 规格
	 */
	public String getAka074() {
		return aka074;
	}

	/**
	 * 
	 * @param aka074
	 *            规格
	 */
	public void setAka074(String aka074) {
		this.aka074 = aka074 == null ? null : aka074.trim();
	}

	public String getAka067() {
		return aka067;
	}

	public void setAka067(String aka067) {
		this.aka067 = aka067 == null ? null : aka067.trim();
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
	 * @param bka040
	 *            单价
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
	 * @param akc226
	 *            用量
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
	 * @param aae019
	 *            金额
	 */
	public void setAae019(BigDecimal aae019) {
		this.aae019 = aae019;
	}

	public BigDecimal getBka059() {
		return bka059;
	}

	public void setBka059(BigDecimal bka059) {
		this.bka059 = bka059;
	}

	public String getBka060() {
		return bka060;
	}

	public void setBka060(String bka060) {
		this.bka060 = bka060 == null ? null : bka060.trim();
	}

	public Long getBka061() {
		return bka061;
	}

	public void setBka061(Long bka061) {
		this.bka061 = bka061;
	}

	public Long getBka062() {
		return bka062;
	}

	public void setBka062(Long bka062) {
		this.bka062 = bka062;
	}

	public String getBka063() {
		return bka063;
	}

	public void setBka063(String bka063) {
		this.bka063 = bka063 == null ? null : bka063.trim();
	}

	public String getBka064() {
		return bka064;
	}

	public void setBka064(String bka064) {
		this.bka064 = bka064 == null ? null : bka064.trim();
	}

	public Date getBka065() {
		return bka065;
	}

	public void setBka065(Date bka065) {
		this.bka065 = bka065;
	}

	public String getBka066() {
		return bka066;
	}

	public void setBka066(String bka066) {
		this.bka066 = bka066 == null ? null : bka066.trim();
	}

	public String getBka067() {
		return bka067;
	}

	public void setBka067(String bka067) {
		this.bka067 = bka067 == null ? null : bka067.trim();
	}

	public Long getBka068() {
		return bka068;
	}

	public void setBka068(Long bka068) {
		this.bka068 = bka068;
	}

	public Date getBka069() {
		return bka069;
	}

	public void setBka069(Date bka069) {
		this.bka069 = bka069;
	}

	public String getBka070() {
		return bka070;
	}

	public void setBka070(String bka070) {
		this.bka070 = bka070 == null ? null : bka070.trim();
	}

	public String getBka071() {
		return bka071;
	}

	public void setBka071(String bka071) {
		this.bka071 = bka071 == null ? null : bka071.trim();
	}

	public String getBka074() {
		return bka074;
	}

	public void setBka074(String bka074) {
		this.bka074 = bka074 == null ? null : bka074.trim();
	}

	public String getBka075() {
		return bka075;
	}

	public void setBka075(String bka075) {
		this.bka075 = bka075 == null ? null : bka075.trim();
	}

	public String getBka076() {
		return bka076;
	}

	public void setBka076(String bka076) {
		this.bka076 = bka076 == null ? null : bka076.trim();
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
	 * @param bka044
	 *            传输标志
	 */
	public void setBka044(String bka044) {
		this.bka044 = bka044 == null ? null : bka044.trim();
	}

	/**
	 * @return 城职对应待遇值
	 */
	public String getBka511() {
		return bka511;
	}

	/**
	 * @param 城职对应待遇值
	 */
	public void setBka511(String bka511) {
		this.bka511 = bka511;
	}

	/**
	 * @return 城乡对应待遇值
	 */
	public String getBka512() {
		return bka512;
	}

	/**
	 * @param 城乡对应待遇值
	 */
	public void setBka512(String bka512) {
		this.bka512 = bka512;
	}

}
