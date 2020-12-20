package com.powersi.biz.medicare.diagnose.pojo;

import com.powersi.comm.mybatis.Page;

public class KF03DTO extends Page {

	private static final long serialVersionUID = 1L;
	private String kf03id;// 主键UUID
	private String akb020;// 医院编号
	private String bkc030;// 导入人姓名
	private String bkc031;// 导入人工号
	private String bkc032;// 导入时间
	private String bkc033;// 文件名称
	private String bkc034;// 文件类型
	private String bkc040;// 导入状态标志: 0导入校验未完成、1导入校验全部完成
	private String progress; // 成功标志
	private String aaa027; // 统筹区

	public String getKf03id() {
		return kf03id;
	}

	public void setKf03id(String kf03id) {
		this.kf03id = kf03id;
	}

	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020;
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

}
