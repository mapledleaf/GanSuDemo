package com.powersi.biz.medicare.query.dto;

import java.util.Date;

public class KCE5 implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String kce5id;

	private String kc21id;

	private String akb020;

	private String aaz217;

	private Long bka001;
	
	private String aaa027;
	
	private Date   akc194;

	private String bka438;

	private String bka435;

	private String bka439;

	private String bka044;

	private String aae100;

	public String getKce5id() {
		return kce5id;
	}

	public void setKce5id(String kce5id) {
		this.kce5id = kce5id;
	}

	public String getKc21id() {
		return kc21id;
	}

	public void setKc21id(String kc21id) {
		this.kc21id = kc21id;
	}

	/**
	 * 医疗结构编码
	 * @return
	 */
	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}

	/**
	 * 就医登记号
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
	 * 场景阶段（1：业务开始 2：业务结算 3：业务结束）
	 * @return
	 */
	public String getBka438() {
		return bka438;
	}

	public void setBka438(String bka438) {
		this.bka438 = bka438;
	}

	/**
	 * 场景编号
	 * @return
	 */
	public String getBka435() {
		return bka435;
	}

	public void setBka435(String bka435) {
		this.bka435 = bka435;
	}

	/**
	 * 场景值
	 * @return
	 */
	public String getBka439() {
		return bka439;
	}

	public void setBka439(String bka439) {
		this.bka439 = bka439;
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
