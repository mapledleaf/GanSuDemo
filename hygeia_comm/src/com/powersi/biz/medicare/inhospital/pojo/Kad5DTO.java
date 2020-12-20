package com.powersi.biz.medicare.inhospital.pojo;

import com.powersi.biz.medicare.comm.pojo.RemotingDTO;

public class Kad5DTO extends RemotingDTO {

	private static final long serialVersionUID = 1L;

	private String aaz027;
	/**
	 * 收费项目等级
	 */
	private String aka065;

	private String aka065_name;
	/**
	 * 三大目录类别编码
	 */
	private String ake003;

	private String ake003_name;
	/**
	 * 药监本位码
	 */
	private String bkm017;

	private String bkc109;/* 异名ID */

	private String bkc144;/* 中心三大目录名称(编码) */
	private String bkc143;/* 中心三大目录名称(名称) */
	private String aka063;/* 医疗发票项目类别 */

	private String bkc141;/* 医院目录厂家(厂家) 生产单位 */

	private String bkm016;/* 医院目录单价(单价) */

	private String bkc106;/* 城居先自付比例 */

	private String aka057;/* 城职先自付比例 */
	private String bka052;
	private String bka052_name;

	/**
	 * 自付比例
	 */
	private String aka053;

	private int totleRow;

	private String ake1id;// '电子处方UUID';
	private String akb020;// '医院编号';
	private String akb021;// '医院名称';
	private String aac001;// '电脑号';
	private String aac003;// '姓名';
	private String aac004;// '性别';
	private String aac005;// '年龄';
	private String aaz216;// '特殊业务申请号';
	private String bka006;// '待遇类别';
	private String bka026;// '入院诊断';
	private String aka121;// '疾病名称';
	private String bka063;// '录入人工号';
	private String bka065;// '录入时间';
	private String aae100;// '有效标志: 0,失效;1:有效';
	private String aae030;// 生效日期
	private String aae031;// 失效日期

	private String ake2id;// '电子处方明细UUID';
	private String ake001;// '基准库编码';
	private String bkm022;// '药品名称';
	private String aka070;// '剂型';
	private String bkc014;// '厂家';
	private String bkc103;// '规格';
	private String bkm012;// '计量单位';
	private String bka056;// '单价';
	private String Bka057;// '用量';
	private String bka058;// '金额';
	private String ake004;// '使用频率';
	private String ake005;// '使用途径';

	public String getAaz027() {
		return aaz027;
	}

	public void setAaz027(String aaz027) {
		this.aaz027 = aaz027;
	}

	public String getAka065() {
		return aka065;
	}

	public void setAka065(String aka065) {
		this.aka065 = aka065;
	}

	public String getAka065_name() {
		return aka065_name;
	}

	public void setAka065_name(String aka065_name) {
		this.aka065_name = aka065_name;
	}

	public String getAke001() {
		return ake001;
	}

	public void setAke001(String ake001) {
		this.ake001 = ake001;
	}

	public String getBkm022() {
		return bkm022;
	}

	public void setBkm022(String bkm022) {
		this.bkm022 = bkm022;
	}

	public String getBka056() {
		return bka056;
	}

	public void setBka056(String bka056) {
		this.bka056 = bka056;
	}

	public String getBka052() {
		return bka052;
	}

	public void setBka052(String bka052) {
		this.bka052 = bka052;
	}

	public String getBka052_name() {
		return bka052_name;
	}

	public void setBka052_name(String bka052_name) {
		this.bka052_name = bka052_name;
	}

	public String getAka053() {
		return aka053;
	}

	public void setAka053(String aka053) {
		this.aka053 = aka053;
	}

	public String getBkc014() {
		return bkc014;
	}

	public void setBkc014(String bkc014) {
		this.bkc014 = bkc014;
	}

	public String getBka057() {
		return Bka057;
	}

	public void setBka057(String bka057) {
		Bka057 = bka057;
	}

	public String getBka058() {
		return bka058;
	}

	public void setBka058(String bka058) {
		this.bka058 = bka058;
	}

	public String getBka065() {
		return bka065;
	}

	public void setBka065(String bka065) {
		this.bka065 = bka065;
	}

	public String getAke004() {
		return ake004;
	}

	public void setAke004(String ake004) {
		this.ake004 = ake004;
	}

	public String getAke005() {
		return ake005;
	}

	public void setAke005(String ake005) {
		this.ake005 = ake005;
	}

	public String getAke003() {
		return ake003;
	}

	public void setAke003(String ake003) {
		this.ake003 = ake003;
	}

	public String getAke003_name() {
		return ake003_name;
	}

	public void setAke003_name(String ake003_name) {
		this.ake003_name = ake003_name;
	}

	public String getBkm017() {
		return bkm017;
	}

	public void setBkm017(String bkm017) {
		this.bkm017 = bkm017;
	}

	public String getBkc109() {
		return bkc109;
	}

	public void setBkc109(String bkc109) {
		this.bkc109 = bkc109;
	}

	public String getBkc144() {
		return bkc144;
	}

	public void setBkc144(String bkc144) {
		this.bkc144 = bkc144;
	}

	public String getBkc143() {
		return bkc143;
	}

	public void setBkc143(String bkc143) {
		this.bkc143 = bkc143;
	}

	public String getAka063() {
		return aka063;
	}

	public void setAka063(String aka063) {
		this.aka063 = aka063;
	}

	public String getBkc141() {
		return bkc141;
	}

	public void setBkc141(String bkc141) {
		this.bkc141 = bkc141;
	}

	public String getBkm016() {
		return bkm016;
	}

	public void setBkm016(String bkm016) {
		this.bkm016 = bkm016;
	}

	public String getBkc106() {
		return bkc106;
	}

	public void setBkc106(String bkc106) {
		this.bkc106 = bkc106;
	}

	public String getAka057() {
		return aka057;
	}

	public void setAka057(String aka057) {
		this.aka057 = aka057;
	}

	public int getTotleRow() {
		return totleRow;
	}

	public void setTotleRow(int totleRow) {
		this.totleRow = totleRow;
	}

	public String getAke1id() {
		return ake1id;
	}

	public void setAke1id(String ake1id) {
		this.ake1id = ake1id;
	}

	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}

	public String getAkb021() {
		return akb021;
	}

	public void setAkb021(String akb021) {
		this.akb021 = akb021;
	}

	public String getAac001() {
		return aac001;
	}

	public void setAac001(String aac001) {
		this.aac001 = aac001;
	}

	public String getAac003() {
		return aac003;
	}

	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}

	public String getAac004() {
		return aac004;
	}

	public void setAac004(String aac004) {
		this.aac004 = aac004;
	}

	public String getAac005() {
		return aac005;
	}

	public void setAac005(String aac005) {
		this.aac005 = aac005;
	}

	public String getAaz216() {
		return aaz216;
	}

	public void setAaz216(String aaz216) {
		this.aaz216 = aaz216;
	}

	public String getBka006() {
		return bka006;
	}

	public void setBka006(String bka006) {
		this.bka006 = bka006;
	}

	public String getBka026() {
		return bka026;
	}

	public void setBka026(String bka026) {
		this.bka026 = bka026;
	}

	public String getAka121() {
		return aka121;
	}

	public void setAka121(String aka121) {
		this.aka121 = aka121;
	}

	public String getBka063() {
		return bka063;
	}

	public void setBka063(String bka063) {
		this.bka063 = bka063;
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
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

	public String getAke2id() {
		return ake2id;
	}

	public void setAke2id(String ake2id) {
		this.ake2id = ake2id;
	}

	public String getAka070() {
		return aka070;
	}

	public void setAka070(String aka070) {
		this.aka070 = aka070;
	}

	public String getBkc103() {
		return bkc103;
	}

	public void setBkc103(String bkc103) {
		this.bkc103 = bkc103;
	}

	public String getBkm012() {
		return bkm012;
	}

	public void setBkm012(String bkm012) {
		this.bkm012 = bkm012;
	}

}
