package com.powersi.biz.medicare.inhospital.pojo;

import java.util.Date;

import com.powersi.biz.medicare.comm.pojo.RemotingDTO;

/**
 * 
 * @author 刘勇
 *
 */
public class SceneFinDTO extends RemotingDTO {

	private static final long serialVersionUID = 1L;

	private String kce5id;

	private String kc21id;

	private String akb020;

	private String aaz217;

	private Long bka001;

	private String bka438;// 场景阶段（1：业务开始 2：业务结算 3：业务结束）VARCHAR2(1) not null

	private String bka435;

	private String bka439;

	private String bka044;

	private String aae100;
	
	private String aaa027;
	
	private Date akc194;
	
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

	public String getKce5id() {
		return kce5id;
	}

	public void setKce5id(String kce5id) {
		this.kce5id = kce5id == null ? null : kce5id.trim();
	}

	public String getKc21id() {
		return kc21id;
	}

	public void setKc21id(String kc21id) {
		this.kc21id = kc21id == null ? null : kc21id.trim();
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
	 * @return 场景阶段（1：业务开始 2：业务结算 3：业务结束）VARCHAR2(1) not null
	 */
	public String getBka438() {
		return bka438;
	}

	/**
	 * 
	 * @param bka438
	 *            场景阶段（1：业务开始 2：业务结算 3：业务结束）VARCHAR2(1) not null
	 */
	public void setBka438(String bka438) {
		this.bka438 = bka438 == null ? null : bka438.trim();
	}

	public String getBka435() {
		return bka435;
	}

	public void setBka435(String bka435) {
		this.bka435 = bka435 == null ? null : bka435.trim();
	}

	public String getBka439() {
		return bka439;
	}

	public void setBka439(String bka439) {
		this.bka439 = bka439 == null ? null : bka439.trim();
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
