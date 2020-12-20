package com.powersi.biz.medicare.inhospital.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.powersi.biz.medicare.comm.pojo.RemotingDTO;

/**
 * 
 * @author 刘勇
 *
 */
public class FeeStatFinDTO extends RemotingDTO {

	private static final long serialVersionUID = 1L;

	private String aaz217;	//就医登记号
	
	private String akb020;	//医院编码
	
	private String kc28id;	//KCD8主键ID

	private String kc21id;	//KCD1主键
	
	private String aaa027;	//统筹区编码（分区用）
	
	private Date akc194;	//医患最终结算日期

	private String aka063;	//医疗发票项目类别
	
	private BigDecimal aae019;	//金额
	
	private BigDecimal bka081;	//申报金额
	
	private BigDecimal ake081;	//个人完全自付

	private BigDecimal ake082;	//个人部份自付

	private String bka044;	//传输标志(0:未传输 1:已成功传输 2:未成功传输）
	
	private Long bka001;//批次号

	private String aae100;	//当前有效标志
    
	/**
	 * @return the bka001
	 */
	public Long getBka001() {
		return bka001;
	}

	/**
	 * @param bka001 the bka001 to set
	 */
	public void setBka001(Long bka001) {
		this.bka001 = bka001;
	}
	
	public String getAaz217() {
		return aaz217;
	}

	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217;
	}

	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020;
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

	public BigDecimal getAke081() {
		return ake081;
	}

	public void setAke081(BigDecimal ake081) {
		this.ake081 = ake081;
	}

	public BigDecimal getAke082() {
		return ake082;
	}

	public void setAke082(BigDecimal ake082) {
		this.ake082 = ake082;
	}

	public String getKc28id() {
		return kc28id;
	}

	public void setKc28id(String kc28id) {
		this.kc28id = kc28id == null ? null : kc28id.trim();
	}

	public String getKc21id() {
		return kc21id;
	}

	public void setKc21id(String kc21id) {
		this.kc21id = kc21id == null ? null : kc21id.trim();
	}

	/**
	 * 
	 * @return 费用分类统计
	 */
	public String getAka063() {
		return aka063;
	}

	/**
	 * 
	 * @param aka063
	 *            费用分类统计
	 */
	public void setAka063(String aka063) {
		this.aka063 = aka063 == null ? null : aka063.trim();
	}

	/**
	 * 
	 * @return 总费用
	 */
	public BigDecimal getAae019() {
		return aae019;
	}

	/**
	 * 
	 * @param aae019
	 *            总费用
	 */
	public void setAae019(BigDecimal aae019) {
		this.aae019 = aae019;
	}

	public BigDecimal getBka081() {
		return bka081;
	}

	public void setBka081(BigDecimal bka081) {
		this.bka081 = bka081;
	}

	public String getBka044() {
		return bka044;
	}

	public void setBka044(String bka044) {
		this.bka044 = bka044 == null ? null : bka044.trim();
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100 == null ? null : aae100.trim();
	}

}
