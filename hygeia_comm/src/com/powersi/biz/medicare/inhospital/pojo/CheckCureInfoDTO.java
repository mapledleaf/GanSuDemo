package com.powersi.biz.medicare.inhospital.pojo;


import com.powersi.biz.medicare.comm.pojo.RemotingDTO;

public class CheckCureInfoDTO extends RemotingDTO{

	private static final long serialVersionUID = 1L;
	private String ke14id;
	private String akb020;//医院编号
	private String aaz217;//就医登记号
	private String bka509;//影像编号
	private String bka510;//影像名称
	private byte[] bka511; //影像图片
	private String bae013;//说明
	private String bka044;//传输标志 0待传输 1传输完毕
	private String bka971;//修改计数器,默认插入时为1，执行一次update需要加一
	private String bka972;//传输打包标志 0未打包 1已打包
	private String bka973;//数据最后修改时间
	private String bka974;//最后传输批次编号
	private String aac002;//社会保障号
	public String getKe14id() {
		return ke14id;
	}
	public void setKe14id(String ke14id) {
		this.ke14id = ke14id;
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
	public String getBka509() {
		return bka509;
	}
	public void setBka509(String bka509) {
		this.bka509 = bka509;
	}
	public String getBka510() {
		return bka510;
	}
	public void setBka510(String bka510) {
		this.bka510 = bka510;
	}
	public byte[] getBka511() {
		return bka511;
	}
	public void setBka511(byte[] bka511) {
		this.bka511 = bka511;
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
	public String getAac002() {
		return aac002;
	}
	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}
	
	
	
}
