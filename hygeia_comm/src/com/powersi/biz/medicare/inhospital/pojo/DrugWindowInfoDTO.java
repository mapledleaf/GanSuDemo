package com.powersi.biz.medicare.inhospital.pojo;


import com.powersi.biz.medicare.comm.pojo.RemotingDTO;

public class DrugWindowInfoDTO extends RemotingDTO{

	private static final long serialVersionUID = 1L;
	private String ke15id;
	private String akb020;//医院编号
	private String aaz217;//就医登记号
	private String bka520;//取药批次
	private String bka521;//药品信息列表
	private String bka522;//取药窗口编号
	private String bka523;//取药窗口名称
	private String bka524;//取药状态 0 未取药 ; 1 已取药
	private String bae013;//说明
	private String bka044;//传输标志 0待传输 1传输完毕
	private String bka971;//修改计数器,默认插入时为1，执行一次update需要加一
	private String bka972;//传输打包标志 0未打包 1已打包
	private String bka973;//数据最后修改时间
	private String bka974;//最后传输批次编号
	private String akb021;//医院名称
	private String bka525;//发票流水号
	private String aae100;//有效标志
	
	public String getKe15id() {
		return ke15id;
	}
	public void setKe15id(String ke15id) {
		this.ke15id = ke15id;
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
	public String getBka520() {
		return bka520;
	}
	public void setBka520(String bka520) {
		this.bka520 = bka520;
	}
	public String getBka521() {
		return bka521;
	}
	public void setBka521(String bka521) {
		this.bka521 = bka521;
	}
	public String getBka522() {
		return bka522;
	}
	public void setBka522(String bka522) {
		this.bka522 = bka522;
	}
	public String getBka523() {
		return bka523;
	}
	public void setBka523(String bka523) {
		this.bka523 = bka523;
	}
	public String getBka524() {
		return bka524;
	}
	public void setBka524(String bka524) {
		this.bka524 = bka524;
	}
	public String getBae013() {
		return bae013;
	}
	public void setBae013(String bae013) {
		this.bae013 = bae013;
	}
	public String getBka044() {
		return bka044;
	}
	public void setBka044(String bka044) {
		this.bka044 = bka044;
	}
	public String getBka971() {
		return bka971;
	}
	public void setBka971(String bka971) {
		this.bka971 = bka971;
	}
	public String getBka972() {
		return bka972;
	}
	public void setBka972(String bka972) {
		this.bka972 = bka972;
	}
	public String getBka973() {
		return bka973;
	}
	public void setBka973(String bka973) {
		this.bka973 = bka973;
	}
	public String getBka974() {
		return bka974;
	}
	public void setBka974(String bka974) {
		this.bka974 = bka974;
	}
	public String getAkb021() {
		return akb021;
	}
	public void setAkb021(String akb021) {
		this.akb021 = akb021;
	}
	public String getBka525() {
		return bka525;
	}
	public void setBka525(String bka525) {
		this.bka525 = bka525;
	}
	public String getAae100() {
		return aae100;
	}
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}
	
	
	
	
}
