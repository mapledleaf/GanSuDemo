package com.powersi.biz.medicare.comm.pojo;

import java.util.Date;

import com.powersi.comm.bean.BaseBean;

/**
 * 目录匹配表kae8 pojo对象 hygeia_db
 * 
 * @Description TODO
 * @author "lingang"
 * @time 2016年10月27日下午3:34:14
 *
 */
public class KAE8 extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 异名医院匹配UUID
	 */
	private String bkc194;

	private String aaa027;

	private Long aaz107;

	private String akb020;

	/**
	 * 项目药品类型(0:项目，1：西药，2：中成药，3：中草药)
	 */
	private String ake003;

	/**
	 * 异名ID
	 */
	private Long bkc109;

	/**
	 * 异名库目录编码
	 */
	private String bkc144;// varchar(30) DEFAULT NULL COMMENT '异名库目录编码',

	/**
	 * 异名目录名称
	 */
	private String bkc143;

	/**
	 * 医院药品项目编码
	 */
	private String ake005;

	private String ake006;

	private String aka020;

	private String aka021;

	private Double aka053;

	/**
	 * 限价金额
	 */
	private Double aka068;

	/**
	 * 城职先自付比例
	 */
	private Double aka057;

	/**
	 * 医疗发票项目类别
	 */
	private String aka063;

	private String aka064;

	/**
	 * 收费项目等级
	 */
	private String aka065;

	private String aja006;

	private String aka022;

	private String ala011;

	private String ama011;

	private String bkc100;

	/**
	 * 城居先自付比例
	 */
	private Double bkc106;

	private String bkc104;

	private String bkc115;

	private String bkc118;

	private String bkc189;

	private String bkc190;

	private String bkc191;

	private String aae100;

	private Date aae030;

	private Date aae031;

	private String bkc138;

	private String bkc139;

	private Double bkc140;

	private String bkc141;

	private String bkc142;

	private String bkc148;

	private Long bkc001;

	private String aka062;

	private Date aae036;

	private String aae011;

	private String bae100;

	private Date aae015;

	private String aae014;

	private String bae101;

	private String aae016;

	private String bkc248;

	private String bkc249;

	private String aae013;

	private Date bkc250;

	private String bkc253;

	private String bkc254;

	private Date bkc255;

	private String bkc256;

	private String bkc257;

	private Date bkc258;

	private String bkc259;

	private String bkc260;

	private String bkc263;

	private String bkc264;

	private Date bkc265;

	private Date bkc266;

	private String aae406;

	private String aae407;

	private Long bpe001;

	/**
	 * 国家药品编码本位码
	 */
	private String bkm017;

	private String bkm018;

	private String bkm019;

	private String bkm020;

	private String bkm021;

	private String bkm022;

	private Date bkm023;

	private String bkm024;

	private String bkm025;

	private String bkm026;

	private String bkm027;

	private String bkm028;

	private String bkm029;

	private String aaz231;
	
	private String bke215;//职工
	private String bke216;//居民
	private String bke217;//离休人员先支付比例
	
	
	
	public String getBke215() {
		return bke215;
	}

	public void setBke215(String bke215) {
		this.bke215 = bke215;
	}

	public String getBke216() {
		return bke216;
	}

	public void setBke216(String bke216) {
		this.bke216 = bke216;
	}

	public String getBke217() {
		return bke217;
	}

	public void setBke217(String bke217) {
		this.bke217 = bke217;
	}

	public String getAaz231() {
		return aaz231;
	}

	public void setAaz231(String aaz231) {
		this.aaz231 = aaz231;
	}

	/**
	 * 
	 * @return 异名医院匹配UUID
	 */
	public String getBkc194() {
		return bkc194;
	}

	/**
	 * 
	 * @param bkc194
	 *            异名医院匹配UUID
	 */
	public void setBkc194(String bkc194) {
		this.bkc194 = bkc194 == null ? null : bkc194.trim();
	}

	public String getAaa027() {
		return aaa027;
	}

	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027 == null ? null : aaa027.trim();
	}

	public Long getAaz107() {
		return aaz107;
	}

	public void setAaz107(Long aaz107) {
		this.aaz107 = aaz107;
	}

	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020 == null ? null : akb020.trim();
	}

	/**
	 * 
	 * @return 项目药品类型(0:项目，1：西药，2：中成药，3：中草药)
	 */
	public String getAke003() {
		return ake003;
	}

	/**
	 * 
	 * @param ake003
	 *            项目药品类型(0:项目，1：西药，2：中成药，3：中草药)
	 */
	public void setAke003(String ake003) {
		this.ake003 = ake003 == null ? null : ake003.trim();
	}

	/**
	 * 
	 * @return 异名ID
	 */
	public Long getBkc109() {
		return bkc109;
	}

	/**
	 * 
	 * @param bkc109
	 *            异名ID
	 */
	public void setBkc109(Long bkc109) {
		this.bkc109 = bkc109;
	}

	/**
	 * 
	 * @return 异名库目录编码
	 */
	public String getBkc144() {
		return bkc144;
	}

	/**
	 * 
	 * @param bkc144
	 *            异名库目录编码
	 */
	public void setBkc144(String bkc144) {
		this.bkc144 = bkc144 == null ? null : bkc144.trim();
	}

	/**
	 * 
	 * @return 异名目录名称
	 */
	public String getBkc143() {
		return bkc143;
	}

	/**
	 * 
	 * @param bkc143
	 *            异名目录名称
	 */
	public void setBkc143(String bkc143) {
		this.bkc143 = bkc143 == null ? null : bkc143.trim();
	}

	/**
	 * 
	 * @return 医院药品项目编码
	 */
	public String getAke005() {
		return ake005;
	}

	/**
	 * 
	 * @param ake005
	 *            医院药品项目编码
	 */
	public void setAke005(String ake005) {
		this.ake005 = ake005 == null ? null : ake005.trim();
	}

	public String getAke006() {
		return ake006;
	}

	public void setAke006(String ake006) {
		this.ake006 = ake006 == null ? null : ake006.trim();
	}

	public String getAka020() {
		return aka020;
	}

	public void setAka020(String aka020) {
		this.aka020 = aka020 == null ? null : aka020.trim();
	}

	public String getAka021() {
		return aka021;
	}

	public void setAka021(String aka021) {
		this.aka021 = aka021 == null ? null : aka021.trim();
	}

	public Double getAka053() {
		return aka053;
	}

	public void setAka053(Double aka053) {
		this.aka053 = aka053;
	}

	/**
	 * 
	 * @return 限价金额
	 */
	public Double getAka068() {
		return aka068;
	}

	/**
	 * 
	 * @param aka068
	 *            限价金额
	 */
	public void setAka068(Double aka068) {
		this.aka068 = aka068;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 * @return 城职先自付比例
	 */
	public Double getAka057() {
		return aka057;
	}

	/**
	 * 
	 * @param aka057
	 *            城职先自付比例
	 */
	public void setAka057(Double aka057) {
		this.aka057 = aka057;
	}

	/**
	 * 
	 * @return 医疗发票项目类别
	 */
	public String getAka063() {
		return aka063;
	}

	/**
	 * 
	 * @param aka063
	 *            医疗发票项目类别
	 */
	public void setAka063(String aka063) {
		this.aka063 = aka063 == null ? null : aka063.trim();
	}

	public String getAka064() {
		return aka064;
	}

	public void setAka064(String aka064) {
		this.aka064 = aka064 == null ? null : aka064.trim();
	}

	/**
	 * 
	 * @return 收费项目等级
	 */
	public String getAka065() {
		return aka065;
	}

	/**
	 * 
	 * @param aka065
	 *            收费项目等级
	 */
	public void setAka065(String aka065) {
		this.aka065 = aka065 == null ? null : aka065.trim();
	}

	public String getAja006() {
		return aja006;
	}

	public void setAja006(String aja006) {
		this.aja006 = aja006 == null ? null : aja006.trim();
	}

	public String getAka022() {
		return aka022;
	}

	public void setAka022(String aka022) {
		this.aka022 = aka022 == null ? null : aka022.trim();
	}

	public String getAla011() {
		return ala011;
	}

	public void setAla011(String ala011) {
		this.ala011 = ala011 == null ? null : ala011.trim();
	}

	public String getAma011() {
		return ama011;
	}

	public void setAma011(String ama011) {
		this.ama011 = ama011 == null ? null : ama011.trim();
	}

	public String getBkc100() {
		return bkc100;
	}

	public void setBkc100(String bkc100) {
		this.bkc100 = bkc100 == null ? null : bkc100.trim();
	}

	/**
	 * 
	 * @return 城居先自付比例
	 */
	public Double getBkc106() {
		return bkc106;
	}

	/**
	 * 
	 * @param bkc106
	 *            城居先自付比例
	 */
	public void setBkc106(Double bkc106) {
		this.bkc106 = bkc106;
	}

	public String getBkc104() {
		return bkc104;
	}

	public void setBkc104(String bkc104) {
		this.bkc104 = bkc104 == null ? null : bkc104.trim();
	}

	public String getBkc115() {
		return bkc115;
	}

	public void setBkc115(String bkc115) {
		this.bkc115 = bkc115 == null ? null : bkc115.trim();
	}

	public String getBkc118() {
		return bkc118;
	}

	public void setBkc118(String bkc118) {
		this.bkc118 = bkc118 == null ? null : bkc118.trim();
	}

	public String getBkc189() {
		return bkc189;
	}

	public void setBkc189(String bkc189) {
		this.bkc189 = bkc189 == null ? null : bkc189.trim();
	}

	public String getBkc190() {
		return bkc190;
	}

	public void setBkc190(String bkc190) {
		this.bkc190 = bkc190 == null ? null : bkc190.trim();
	}

	public String getBkc191() {
		return bkc191;
	}

	public void setBkc191(String bkc191) {
		this.bkc191 = bkc191 == null ? null : bkc191.trim();
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100 == null ? null : aae100.trim();
	}

	public Date getAae030() {
		return aae030;
	}

	public void setAae030(Date aae030) {
		this.aae030 = aae030;
	}

	public Date getAae031() {
		return aae031;
	}

	public void setAae031(Date aae031) {
		this.aae031 = aae031;
	}

	public String getBkc138() {
		return bkc138;
	}

	public void setBkc138(String bkc138) {
		this.bkc138 = bkc138 == null ? null : bkc138.trim();
	}

	public String getBkc139() {
		return bkc139;
	}

	public void setBkc139(String bkc139) {
		this.bkc139 = bkc139 == null ? null : bkc139.trim();
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
		this.bkc141 = bkc141 == null ? null : bkc141.trim();
	}

	public String getBkc142() {
		return bkc142;
	}

	public void setBkc142(String bkc142) {
		this.bkc142 = bkc142 == null ? null : bkc142.trim();
	}

	public String getBkc148() {
		return bkc148;
	}

	public void setBkc148(String bkc148) {
		this.bkc148 = bkc148 == null ? null : bkc148.trim();
	}

	public Long getBkc001() {
		return bkc001;
	}

	public void setBkc001(Long bkc001) {
		this.bkc001 = bkc001;
	}

	public String getAka062() {
		return aka062;
	}

	public void setAka062(String aka062) {
		this.aka062 = aka062 == null ? null : aka062.trim();
	}

	public Date getAae036() {
		return aae036;
	}

	public void setAae036(Date aae036) {
		this.aae036 = aae036;
	}

	public String getAae011() {
		return aae011;
	}

	public void setAae011(String aae011) {
		this.aae011 = aae011 == null ? null : aae011.trim();
	}

	public String getBae100() {
		return bae100;
	}

	public void setBae100(String bae100) {
		this.bae100 = bae100 == null ? null : bae100.trim();
	}

	public Date getAae015() {
		return aae015;
	}

	public void setAae015(Date aae015) {
		this.aae015 = aae015;
	}

	public String getAae014() {
		return aae014;
	}

	public void setAae014(String aae014) {
		this.aae014 = aae014 == null ? null : aae014.trim();
	}

	public String getBae101() {
		return bae101;
	}

	public void setBae101(String bae101) {
		this.bae101 = bae101 == null ? null : bae101.trim();
	}

	public String getAae016() {
		return aae016;
	}

	public void setAae016(String aae016) {
		this.aae016 = aae016 == null ? null : aae016.trim();
	}

	public String getBkc248() {
		return bkc248;
	}

	public void setBkc248(String bkc248) {
		this.bkc248 = bkc248 == null ? null : bkc248.trim();
	}

	public String getBkc249() {
		return bkc249;
	}

	public void setBkc249(String bkc249) {
		this.bkc249 = bkc249 == null ? null : bkc249.trim();
	}

	public String getAae013() {
		return aae013;
	}

	public void setAae013(String aae013) {
		this.aae013 = aae013 == null ? null : aae013.trim();
	}

	public Date getBkc250() {
		return bkc250;
	}

	public void setBkc250(Date bkc250) {
		this.bkc250 = bkc250;
	}

	public String getBkc253() {
		return bkc253;
	}

	public void setBkc253(String bkc253) {
		this.bkc253 = bkc253 == null ? null : bkc253.trim();
	}

	public String getBkc254() {
		return bkc254;
	}

	public void setBkc254(String bkc254) {
		this.bkc254 = bkc254 == null ? null : bkc254.trim();
	}

	public Date getBkc255() {
		return bkc255;
	}

	public void setBkc255(Date bkc255) {
		this.bkc255 = bkc255;
	}

	public String getBkc256() {
		return bkc256;
	}

	public void setBkc256(String bkc256) {
		this.bkc256 = bkc256 == null ? null : bkc256.trim();
	}

	public String getBkc257() {
		return bkc257;
	}

	public void setBkc257(String bkc257) {
		this.bkc257 = bkc257 == null ? null : bkc257.trim();
	}

	public Date getBkc258() {
		return bkc258;
	}

	public void setBkc258(Date bkc258) {
		this.bkc258 = bkc258;
	}

	public String getBkc259() {
		return bkc259;
	}

	public void setBkc259(String bkc259) {
		this.bkc259 = bkc259 == null ? null : bkc259.trim();
	}

	public String getBkc260() {
		return bkc260;
	}

	public void setBkc260(String bkc260) {
		this.bkc260 = bkc260 == null ? null : bkc260.trim();
	}

	public String getBkc263() {
		return bkc263;
	}

	public void setBkc263(String bkc263) {
		this.bkc263 = bkc263 == null ? null : bkc263.trim();
	}

	public String getBkc264() {
		return bkc264;
	}

	public void setBkc264(String bkc264) {
		this.bkc264 = bkc264 == null ? null : bkc264.trim();
	}

	public Date getBkc265() {
		return bkc265;
	}

	public void setBkc265(Date bkc265) {
		this.bkc265 = bkc265;
	}

	public Date getBkc266() {
		return bkc266;
	}

	public void setBkc266(Date bkc266) {
		this.bkc266 = bkc266;
	}

	public String getAae406() {
		return aae406;
	}

	public void setAae406(String aae406) {
		this.aae406 = aae406 == null ? null : aae406.trim();
	}

	public String getAae407() {
		return aae407;
	}

	public void setAae407(String aae407) {
		this.aae407 = aae407 == null ? null : aae407.trim();
	}

	public Long getBpe001() {
		return bpe001;
	}

	public void setBpe001(Long bpe001) {
		this.bpe001 = bpe001;
	}

	/**
	 * 
	 * @return 国家药品编码本位码
	 */
	public String getBkm017() {
		return bkm017;
	}

	/**
	 * 
	 * @param bkm017
	 *            国家药品编码本位码
	 */
	public void setBkm017(String bkm017) {
		this.bkm017 = bkm017 == null ? null : bkm017.trim();
	}

	public String getBkm018() {
		return bkm018;
	}

	public void setBkm018(String bkm018) {
		this.bkm018 = bkm018 == null ? null : bkm018.trim();
	}

	public String getBkm019() {
		return bkm019;
	}

	public void setBkm019(String bkm019) {
		this.bkm019 = bkm019 == null ? null : bkm019.trim();
	}

	public String getBkm020() {
		return bkm020;
	}

	public void setBkm020(String bkm020) {
		this.bkm020 = bkm020 == null ? null : bkm020.trim();
	}

	public String getBkm021() {
		return bkm021;
	}

	public void setBkm021(String bkm021) {
		this.bkm021 = bkm021 == null ? null : bkm021.trim();
	}

	public String getBkm022() {
		return bkm022;
	}

	public void setBkm022(String bkm022) {
		this.bkm022 = bkm022 == null ? null : bkm022.trim();
	}

	public Date getBkm023() {
		return bkm023;
	}

	public void setBkm023(Date bkm023) {
		this.bkm023 = bkm023;
	}

	public String getBkm024() {
		return bkm024;
	}

	public void setBkm024(String bkm024) {
		this.bkm024 = bkm024 == null ? null : bkm024.trim();
	}

	public String getBkm025() {
		return bkm025;
	}

	public void setBkm025(String bkm025) {
		this.bkm025 = bkm025 == null ? null : bkm025.trim();
	}

	public String getBkm026() {
		return bkm026;
	}

	public void setBkm026(String bkm026) {
		this.bkm026 = bkm026 == null ? null : bkm026.trim();
	}

	public String getBkm027() {
		return bkm027;
	}

	public void setBkm027(String bkm027) {
		this.bkm027 = bkm027 == null ? null : bkm027.trim();
	}

	public String getBkm028() {
		return bkm028;
	}

	public void setBkm028(String bkm028) {
		this.bkm028 = bkm028 == null ? null : bkm028.trim();
	}

	public String getBkm029() {
		return bkm029;
	}

	public void setBkm029(String bkm029) {
		this.bkm029 = bkm029 == null ? null : bkm029.trim();
	}

}