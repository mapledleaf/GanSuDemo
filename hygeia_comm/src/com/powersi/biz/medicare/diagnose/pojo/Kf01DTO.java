package com.powersi.biz.medicare.diagnose.pojo;

import com.powersi.comm.mybatis.Page;

public class Kf01DTO extends Page {

	private static final long serialVersionUID = 1L;
	private String kf01id;// 主键UUID
	private String akb020;// 医院编号
	private String aaz217;// 模拟的就医登记号
	private String bkc030;// 导入人姓名
	private String bkc031;// 导入人工号
	private String bkc032;// 导入时间
	private String bkc033;// 文件名称
	private String bkc034;// 文件类型
	private String bkc040;// 导入状态标志: 0导入校验未完成、1导入校验全部完成
	private String progress;
	private String aaa027;
	private int startRow;
	private int totleRow;

	public String getKf01id() {
		return kf01id;
	}

	public void setKf01id(String kf01id) {
		this.kf01id = kf01id;
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

	public String getBkc030() {
		return bkc030;
	}

	public void setBkc030(String bkc030) {
		this.bkc030 = bkc030;
	}

	public String getBkc031() {
		return bkc031;
	}

	public void setBkc031(String bkc031) {
		this.bkc031 = bkc031;
	}

	public String getBkc032() {
		return bkc032;
	}

	public void setBkc032(String bkc032) {
		this.bkc032 = bkc032;
	}

	public String getBkc033() {
		return bkc033;
	}

	public void setBkc033(String bkc033) {
		this.bkc033 = bkc033;
	}

	public String getBkc034() {
		return bkc034;
	}

	public void setBkc034(String bkc034) {
		this.bkc034 = bkc034;
	}

	public String getBkc040() {
		return bkc040;
	}

	public void setBkc040(String bkc040) {
		this.bkc040 = bkc040;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getAaa027() {
		return aaa027;
	}

	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
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
	private String aaz218;

	public String getAaz218() {
		return aaz218;
	}

	public void setAaz218(String aaz218) {
		this.aaz218 = aaz218;
	}

}
