package com.powersi.biz.medicare.diagnose.pojo;

import com.powersi.comm.mybatis.Page;

public class KF04DTO extends Page {

	private static final long serialVersionUID = 1L;
	private String kf04id;// 主键UUID
	private String kf03id;// KF03表的主键UUID
	private String akb020;// 医疗服务机构编号
	private String ake003;// 三大目录类别
	private String ake005;// 医疗机构项目编码
	private String ake006;// 医疗机构三大目录名称
	private String aae030;// 开始时间
	private String aae031; // 结束时间
	private String bkc138; // 医院目录剂型
	private String bkc139;// 医院目录规格
	private Double bkc140;// 医院目录单价
	private String bkc141;// 医院目录厂家
	private String bkm017; // 医院药监本位码
	private String bkm019; // 产地（医院信息）
	private String bkm020; // 医院包装规格
	private String bkm024; // 批准文号/注册号（医院信息）
	private String bkm029; // 医保识别码
	private String bke037; // 成功失败标志:0未校验、1成功、2失败'
	private String bke038; // 失败原因
	private String aaa027; // 统筹区
	private String bke037_suc;//成功失败标志
	private String bkc109; //异名ID
	private String bkc144;
	
	public String getBkc144() {
		return bkc144;
	}

	public void setBkc144(String bkc144) {
		this.bkc144 = bkc144;
	}

	public String getBkc109() {
		return bkc109;
	}

	public void setBkc109(String bkc109) {
		this.bkc109 = bkc109;
	}

	public String getBke037_suc() {
		return bke037_suc;
	}

	public void setBke037_suc(String bke037_suc) {
		this.bke037_suc = bke037_suc;
	}

	public String getAaa027() {
		return aaa027;
	}

	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	public String getKf04id() {
		return kf04id;
	}

	public void setKf04id(String kf04id) {
		this.kf04id = kf04id;
	}

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

	public String getAke003() {
		return ake003;
	}

	public void setAke003(String ake003) {
		this.ake003 = ake003;
	}

	public String getAke005() {
		return ake005;
	}

	public void setAke005(String ake005) {
		this.ake005 = ake005;
	}

	public String getAke006() {
		return ake006;
	}

	public void setAke006(String ake006) {
		this.ake006 = ake006;
	}

	public String getAae030() {
		return aae030;
	}

	public void setAae030(String aae030) {
		this.aae030 = aae030;
	}

	public String getAae031() {
		return aae031;
	}

	public void setAae031(String aae031) {
		this.aae031 = aae031;
	}

	public String getBkc138() {
		return bkc138;
	}

	public void setBkc138(String bkc138) {
		this.bkc138 = bkc138;
	}

	public String getBkc139() {
		return bkc139;
	}

	public void setBkc139(String bkc139) {
		this.bkc139 = bkc139;
	}

	public Double getBkc140() {
		return bkc140;
	}

	public void setBkc140(Double bkc140) {
		this.bkc140 = bkc140;
	}

	public String getBkc141() {
		return bkc141;
	}

	public void setBkc141(String bkc141) {
		this.bkc141 = bkc141;
	}

	public String getBkm017() {
		return bkm017;
	}

	public void setBkm017(String bkm017) {
		this.bkm017 = bkm017;
	}

	public String getBkm019() {
		return bkm019;
	}

	public void setBkm019(String bkm019) {
		this.bkm019 = bkm019;
	}

	public String getBkm020() {
		return bkm020;
	}

	public void setBkm020(String bkm020) {
		this.bkm020 = bkm020;
	}

	public String getBkm024() {
		return bkm024;
	}

	public void setBkm024(String bkm024) {
		this.bkm024 = bkm024;
	}

	public String getBkm029() {
		return bkm029;
	}

	public void setBkm029(String bkm029) {
		this.bkm029 = bkm029;
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

}
