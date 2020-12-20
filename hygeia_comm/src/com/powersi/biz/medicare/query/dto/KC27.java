package com.powersi.biz.medicare.query.dto;

import java.math.BigDecimal;
import java.util.Date;

public class KC27 implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String kc27id;

	private String kc21id;
	
	private String kc22id;

	private String akb020;

	private String aaz217;

	private Long bka001;
	
	private Long aaz213;

	private String aaa027;

	private Date akc194;

	private String aaa157;
	
	private String bka432;

	private String aka002;

	private BigDecimal aae019;

	private String aae100;
	
	private String bka044;
	
	private String aaz215;

	public String getAaz215() {
		return aaz215;
	}

	public void setAaz215(String aaz215) {
		this.aaz215 = aaz215;
	}

	public String getBka044() {
		return bka044;
	}

	public void setBka044(String bka044) {
		this.bka044 = bka044;
	}

	public String getKc27id() {
		return kc27id;
	}

	public void setKc27id(String kc27id) {
		this.kc27id = kc27id;
	}

	public String getKc21id() {
		return kc21id;
	}

	public void setKc21id(String kc21id) {
		this.kc21id = kc21id;
	}

	public String getKc22id() {
		return kc22id;
	}

	public void setKc22id(String kc22id) {
		this.kc22id = kc22id;
	}
	/**
	 * 医疗机构编码
	 * @return
	 */
	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}
	
	/**
	 * 业务序列号
	 * @return
	 */
	public String getAaz217() {
		return aaz217;
	}

	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217;
	}

	/**
	 * 费用批次
	 * @return
	 */
	public Long getBka001() {
		return bka001;
	}

	public void setBka001(Long bka001) {
		this.bka001 = bka001;
	}

	/**
	 * 费用序列号(每次业务都从1开始的费用序号)
	 * @return
	 */
	public Long getAaz213() {
		return aaz213;
	}

	public void setAaz213(Long aaz213) {
		this.aaz213 = aaz213;
	}

	/**
	 * 统筹区编码
	 * @return
	 */
	public String getAaa027() {
		return aaa027;
	}

	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	/**
	 * 结算时间
	 * @return
	 */
	public Date getAkc194() {
		return akc194;
	}

	public void setAkc194(Date akc194) {
		this.akc194 = akc194;
	}

	/**
	 * 基金款项代码
	 * @return
	 */
	public String getAaa157() {
		return aaa157;
	}

	public void setAaa157(String aaa157) {
		this.aaa157 = aaa157;
	}

	/**
	 * 分类标志(101:现金全自费,102:现金部分自费,103:超限价金额,000：除上情况
	 * @return
	 */
	public String getBka432() {
		return bka432;
	}

	public void setBka432(String bka432) {
		this.bka432 = bka432;
	}

	/**
	 * 医疗费列支项目
	 * @return
	 */
	public String getAka002() {
		return aka002;
	}

	public void setAka002(String aka002) {
		this.aka002 = aka002;
	}

	/**
	 * 金额
	 * @return
	 */
	public BigDecimal getAae019() {
		return aae019;
	}

	public void setAae019(BigDecimal aae019) {
		this.aae019 = aae019;
	}

	/**
	 * 有效标志
	 * @return
	 */
	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

}
