package com.powersi.biz.medicare.inhospital.pojo;

import java.util.Date;

import com.powersi.biz.medicare.comm.pojo.RemotingDTO;

/**
 * 
 * @author 刘勇
 *
 */
public class SceneDTO extends RemotingDTO {

	private static final long serialVersionUID = 1L;

	
	private String kce4id;

	private String kcd1id;

	private String akb020;

	private String aaz217;

	/**
	 * 费用批次
	 */
	private Long bka001;// bka001 number(12) 费用批次

	/**
	 * 场景阶段
	 */
	private String bka438;// bka438 varchar2(1) 场景阶段（1：业务开始 2：业务结算 3：业务结束）

	/**
	 * 场景编号
	 */
	private String bka435;// bka435 varchar2(30) 场景编号

	/**
	 * 场景值
	 */
	private String bka439;// bka439 varchar2(100) 场景值

	private String bka044;// bka044 varchar2(1) 传输标志(0:未传输 1:已成功传输 2:未成功传输）
	
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

	public String getKce4id() {
		return kce4id;
	}

	public void setKce4id(String kce4id) {
		this.kce4id = kce4id == null ? null : kce4id.trim();
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
	 * @return 场景阶段（1：业务开始 2：业务结算 3：业务结束）
	 */
	public String getBka438() {
		return bka438;
	}

	/**
	 * 
	 * @param bka438
	 *            场景阶段（1：业务开始 2：业务结算 3：业务结束）
	 */
	public void setBka438(String bka438) {
		this.bka438 = bka438 == null ? null : bka438.trim();
	}

	/**
	 * 
	 * @return 场景编号
	 */
	public String getBka435() {
		return bka435;
	}

	/**
	 * 
	 * @param bka435
	 *            场景编号
	 */
	public void setBka435(String bka435) {
		this.bka435 = bka435 == null ? null : bka435.trim();
	}

	/**
	 * 
	 * @return 场景值
	 */
	public String getBka439() {
		return bka439;
	}

	/**
	 * 
	 * @param bka439
	 *            场景值
	 */
	public void setBka439(String bka439) {
		this.bka439 = bka439 == null ? null : bka439.trim();
	}

	/**
	 * 
	 * @return 传输标志(0:未传输 1:已成功传输 2:未成功传输）
	 */
	public String getBka044() {
		return bka044;
	}

	/**
	 * 
	 * @param bka044
	 *            传输标志(0:未传输 1:已成功传输 2:未成功传输）
	 */
	public void setBka044(String bka044) {
		this.bka044 = bka044 == null ? null : bka044.trim();
	}
	private String aaz218;

	public String getAaz218() {
		return aaz218;
	}

	public void setAaz218(String aaz218) {
		this.aaz218 = aaz218;
	}

}
