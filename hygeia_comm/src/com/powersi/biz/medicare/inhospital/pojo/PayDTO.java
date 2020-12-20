package com.powersi.biz.medicare.inhospital.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.powersi.biz.medicare.comm.pojo.RemotingDTO;

/**
 * 
 * @author 刘勇
 *
 */
public class PayDTO extends RemotingDTO {

	private static final long serialVersionUID = 1L;

	private String kcd7id;	//KCD7ID主键ID

	private String kcd1id;	//KCD1主键ID
	
	private String kcd2id;	//KCD2主键ID

	private String akb020;	//医疗服务机构编号

	private String aaz217;	//人员医疗就诊事件ID
	
	private Long bka001;	//费用批次

	private Long aaz213;	//费用序列号

	private String aaa027;	//统筹区编码（分区用）

	private Date akc194;	//医患最终结算日期

	private BigDecimal aae019;	//金额

	private String aaa157;	//基金款项代码

	private String bka432;	//分类标志(101:现金全自费,102:现金部分自费,103:超限价金额,000：除上情况

	private String aka002;	//医疗费列支项目
	
	private String bka044;	//医疗费列支项目
	
	private String aae001;  //跨年断帐年度
	
	private String aaz215; 	//计算次序
	
	public String getAaz215() {
		return aaz215;
	}

	public void setAaz215(String aaz215) {
		this.aaz215 = aaz215;
	}

	public String getAae001() {
		return aae001;
	}

	public void setAae001(String aae001) {
		this.aae001 = aae001;
	}

	public String getBka044() {
		return bka044;
	}

	public void setBka044(String bka044) {
		this.bka044 = bka044;
	}

	public String getKcd2id() {
		return kcd2id;
	}

	public void setKcd2id(String kcd2id) {
		this.kcd2id = kcd2id;
	}

	public String getAaa027() {
		return aaa027;
	}

	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	public Date getAkc194() {
		return akc194;
	}

	public void setAkc194(Date akc194) {
		this.akc194 = akc194;
	}

	public String getAka002() {
		return aka002;
	}

	public void setAka002(String aka002) {
		this.aka002 = aka002;
	}

	public String getKcd7id() {
		return kcd7id;
	}

	public void setKcd7id(String kcd7id) {
		this.kcd7id = kcd7id == null ? null : kcd7id.trim();
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

	public Long getBka001() {
		return bka001;
	}

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

	public String getAaa157() {
		return aaa157;
	}

	public void setAaa157(String aaa157) {
		this.aaa157 = aaa157 == null ? null : aaa157.trim();
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

	/**
	 * 
	 * @return 分类标志(101:现金全自费,102:现金部分自费,103:个人帐户支付,109:冻结状态的全支付,999:历年帐户不够支付,
	 *         000:除上面的情况)
	 */
	public String getBka432() {
		return bka432;
	}

	/**
	 * 
	 * @param bka432
	 *            分类标志(101:现金全自费,102:现金部分自费,103:个人帐户支付,109:冻结状态的全支付,999:历年帐户不够支付
	 *            ,000:除上面的情况)
	 */
	public void setBka432(String bka432) {
		this.bka432 = bka432 == null ? null : bka432.trim();
	}

}
