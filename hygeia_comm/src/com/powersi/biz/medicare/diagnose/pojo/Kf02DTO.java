package com.powersi.biz.medicare.diagnose.pojo;

import com.powersi.comm.mybatis.Page;


public class Kf02DTO extends Page {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rownum;
	private String kf02id;//主键UUID
	private String kf01id;//kf01表UUID
	private String aaz217;//模拟的就医登记号
	private String akb020;//医院编号
	private String aac002;//身份证号
	private String aac003;//姓名
	private String bke030;//总费用
	private String ake039;//统筹支付费用
	private String bke034;//甲类费用
	private String bke035;//丙类费用
	private String bke036;//就医时间
	private String bke037;//成功失败标志:0未校验、1成功、2失败
	private String bke038;//失败原因
	private String bke039;//个人支付
	private int startRow;
	private int totleRow;
	

	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	private String bke037_suc;//成功失败标志
	public String getKf02id() {
		return kf02id;
	}
	public void setKf02id(String kf02id) {
		this.kf02id = kf02id;
	}
	public String getKf01id() {
		return kf01id;
	}
	public void setKf01id(String kf01id) {
		this.kf01id = kf01id;
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
	public String getAac002() {
		return aac002;
	}
	
	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}
	public String getAac003() {
		return aac003;
	}
	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}
	public String getBke030() {
		return bke030;
	}
	public void setBke030(String bke030) {
		this.bke030 = bke030;
	}
	public String getAke039() {
		return ake039;
	}
	public void setAke039(String ake039) {
		this.ake039 = ake039;
	}
	public String getBke034() {
		return bke034;
	}
	public void setBke034(String bke034) {
		this.bke034 = bke034;
	}
	public String getBke035() {
		return bke035;
	}
	public void setBke035(String bke035) {
		this.bke035 = bke035;
	}
	public String getBke036() {
		return bke036;
	}
	public void setBke036(String bke036) {
		this.bke036 = bke036;
	}
	public String getBke037() {
		return bke037;
	}
	public void setBke037(String bke037) {
		this.bke037 = bke037;
	}
	public String getBke038() {
		return bke038;
	}
	public void setBke038(String bke038) {
		this.bke038 = bke038;
	}
	public String getBke039() {
		return bke039;
	}
	public void setBke039(String bke039) {
		this.bke039 = bke039;
	}
	public String getBke037_suc() {
		return bke037_suc;
	}
	public void setBke037_suc(String bke037_suc) {
		this.bke037_suc = bke037_suc;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getTotleRow() {
		return totleRow;
	}
	public void setTotleRow(int totleRow) {
		this.totleRow = totleRow;
	}
	
	
}
