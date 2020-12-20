package com.powersi.biz.medicare.catalog.pojo;

import java.math.BigDecimal;

import com.powersi.comm.mybatis.Page;
/**
 * 医院目录表
 * @author ChenXing
 */
public class Ka40DTO extends Page{

	private static final long serialVersionUID = 1L;
	private String ka40id;//主键（uuid）
	private String aaa027;//统筹区编码
	private String akb020;//医院编码
	private String ake003;//目录类别
	private String ake005;//医疗机构三大目录编码
	private String ake006;//医疗机构三大目录名称
	private String aka020;//拼音助记码
	private String aka021;//五笔助记码
	private String aae100;//有效标志,0,无效,1有效
	private String bkc003;//经办人工号
	private String bkc002;//经办人
	private String aae036;//经办时间
	private String aka062;//英文名称
	private String aka064;//处方药标示
	private String aka065;//收费项目等级
	private String aka066;//毒麻精贵标志
	private String aka067;//重病用标志
	private String bkm005;//医院剂型
	private String bkm006;//药品注册规格
	private BigDecimal bkc140;//单价
	private String bkc141;//生产单位
	private String bkc139;//医院目录规格
	private BigDecimal bkm014;//包装价格
	private String bkm017;//药监本位码
	private String bkm018;//医院目录分类
	private String bkm019;//产地
	private String bkm020;//包装规格
	private String bkm021;//别名
	private String bkm022;//商品名
	private String bkm009;//批准日期
	private String bkm008;//批准文号备注
	private String bkm007;//批准文号
	private String aae016;//审核标志
	private String aae408;//审核时间
	private String aae407;//审核人工号
	private String aae406;//审核人姓名
	private String bkm025;//备注1
	private String bkm026;//备注2
	private String bkm027;//备注3
	private String bkm028;//注册变更号
	private String bkm029;//备用字段
	private String bkm030;//备用字段
	private String bkm031;//备用字段
	public String getKa40id() {
		return ka40id;
	}
	public void setKa40id(String ka40id) {
		this.ka40id = ka40id;
	}
	public String getAaa027() {
		return aaa027;
	}
	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
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
	public String getAka020() {
		return aka020;
	}
	public void setAka020(String aka020) {
		this.aka020 = aka020;
	}
	public String getAka021() {
		return aka021;
	}
	public void setAka021(String aka021) {
		this.aka021 = aka021;
	}
	public String getAae100() {
		return aae100;
	}
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}
	public String getBkc003() {
		return bkc003;
	}
	public void setBkc003(String bkc003) {
		this.bkc003 = bkc003;
	}
	public String getBkc002() {
		return bkc002;
	}
	public void setBkc002(String bkc002) {
		this.bkc002 = bkc002;
	}
	public String getAae036() {
		return aae036;
	}
	public void setAae036(String aae036) {
		this.aae036 = aae036;
	}
	public String getAka062() {
		return aka062;
	}
	public void setAka062(String aka062) {
		this.aka062 = aka062;
	}
	public String getAka064() {
		return aka064;
	}
	public void setAka064(String aka064) {
		this.aka064 = aka064;
	}
	public String getAka065() {
		return aka065;
	}
	public void setAka065(String aka065) {
		this.aka065 = aka065;
	}
	public String getAka066() {
		return aka066;
	}
	public void setAka066(String aka066) {
		this.aka066 = aka066;
	}
	public String getAka067() {
		return aka067;
	}
	public void setAka067(String aka067) {
		this.aka067 = aka067;
	}
	public String getBkm005() {
		return bkm005;
	}
	public void setBkm005(String bkm005) {
		this.bkm005 = bkm005;
	}
	public String getBkm006() {
		return bkm006;
	}
	public void setBkm006(String bkm006) {
		this.bkm006 = bkm006;
	}
	public BigDecimal getBkc140() {
		return bkc140;
	}
	public void setBkc140(BigDecimal bkc140) {
		this.bkc140 = bkc140;
	}
	public String getBkc141() {
		return bkc141;
	}
	public void setBkc141(String bkc141) {
		this.bkc141 = bkc141;
	}
	public String getBkc139() {
		return bkc139;
	}
	public void setBkc139(String bkc139) {
		this.bkc139 = bkc139;
	}
	public BigDecimal getBkm014() {
		return bkm014;
	}
	public void setBkm014(BigDecimal bkm014) {
		this.bkm014 = bkm014;
	}
	public String getBkm017() {
		return bkm017;
	}
	public void setBkm017(String bkm017) {
		this.bkm017 = bkm017;
	}
	public String getBkm018() {
		return bkm018;
	}
	public void setBkm018(String bkm018) {
		this.bkm018 = bkm018;
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
	public String getBkm021() {
		return bkm021;
	}
	public void setBkm021(String bkm021) {
		this.bkm021 = bkm021;
	}
	public String getBkm022() {
		return bkm022;
	}
	public void setBkm022(String bkm022) {
		this.bkm022 = bkm022;
	}
	public String getBkm009() {
		return bkm009;
	}
	public void setBkm009(String bkm009) {
		this.bkm009 = bkm009;
	}
	public String getBkm008() {
		return bkm008;
	}
	public void setBkm008(String bkm008) {
		this.bkm008 = bkm008;
	}
	public String getBkm007() {
		return bkm007;
	}
	public void setBkm007(String bkm007) {
		this.bkm007 = bkm007;
	}
	public String getAae016() {
		return aae016;
	}
	public void setAae016(String aae016) {
		this.aae016 = aae016;
	}
	public String getAae408() {
		return aae408;
	}
	public void setAae408(String aae408) {
		this.aae408 = aae408;
	}
	public String getAae407() {
		return aae407;
	}
	public void setAae407(String aae407) {
		this.aae407 = aae407;
	}
	public String getAae406() {
		return aae406;
	}
	public void setAae406(String aae406) {
		this.aae406 = aae406;
	}
	public String getBkm025() {
		return bkm025;
	}
	public void setBkm025(String bkm025) {
		this.bkm025 = bkm025;
	}
	public String getBkm026() {
		return bkm026;
	}
	public void setBkm026(String bkm026) {
		this.bkm026 = bkm026;
	}
	public String getBkm027() {
		return bkm027;
	}
	public void setBkm027(String bkm027) {
		this.bkm027 = bkm027;
	}
	public String getBkm028() {
		return bkm028;
	}
	public void setBkm028(String bkm028) {
		this.bkm028 = bkm028;
	}
	public String getBkm029() {
		return bkm029;
	}
	public void setBkm029(String bkm029) {
		this.bkm029 = bkm029;
	}
	public String getBkm030() {
		return bkm030;
	}
	public void setBkm030(String bkm030) {
		this.bkm030 = bkm030;
	}
	public String getBkm031() {
		return bkm031;
	}
	public void setBkm031(String bkm031) {
		this.bkm031 = bkm031;
	}
	
}
