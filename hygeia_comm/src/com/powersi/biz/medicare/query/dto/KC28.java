package com.powersi.biz.medicare.query.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 人员医疗结算费用汇总
 * @author Developer Name
 *
 */
public class KC28 implements Serializable {

	private static final long serialVersionUID = 1L;

	private String kc28id;

	private String kc21id;
	
	private String aaa027;
	
	private Date   akc194;
	
	private String aka063;
	
	private BigDecimal aae019;
	
	private BigDecimal bka081;

	private BigDecimal ake081;

	private BigDecimal ake082;
	
	private String bka044;

	private String aae100;

	public String getKc28id() {
		return kc28id;
	}

	public void setKc28id(String kc28id) {
		this.kc28id = kc28id;
	}

	public String getKc21id() {
		return kc21id;
	}

	public void setKc21id(String kc21id) {
		this.kc21id = kc21id;
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
	 * 医疗发票项目类别
	 * @return
	 */
	public String getAka063() {
		return aka063;
	}

	public void setAka063(String aka063) {
		this.aka063 = aka063;
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
	 * 申报金额
	 * @return
	 */
	public BigDecimal getBka081() {
		return bka081;
	}

	public void setBka081(BigDecimal bka081) {
		this.bka081 = bka081;
	}

	/**
	 * 个人完全自付
	 * @return
	 */
	public BigDecimal getAke081() {
		return ake081;
	}

	public void setAke081(BigDecimal ake081) {
		this.ake081 = ake081;
	}

	/**
	 * 个人部份自付
	 * @return
	 */
	public BigDecimal getAke082() {
		return ake082;
	}

	public void setAke082(BigDecimal ake082) {
		this.ake082 = ake082;
	}

	/**
	 * 传输标志(0:未传输 1:已成功传输 2:未成功传输）
	 * @return
	 */
	public String getBka044() {
		return bka044;
	}

	public void setBka044(String bka044) {
		this.bka044 = bka044;
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
