package com.powersi.biz.medicare.special.pojo;

import java.io.Serializable;

import com.powersi.comm.mybatis.Page;

public class MediSpec_ZH_DTO extends Page implements Serializable {

	/**
	 * 珠海医疗特殊业务DTO
	 */
	private static final long serialVersionUID = 1L;
	/**** 病种认定编号或者特大病种费用补偿录入编号 ****/
	private String ace001;
	/** 特大病种费用补偿录入详细编号 */
	private String ace002;

	/**** 医院编码 ****/
	private String akb020;

	/**** 统筹区编码 ****/
	private String aaa027;

	/**** 电脑号 ****/
	private String aac001;

	/**** 姓名 ****/
	private String aac003;

	/**** 身份证号 ****/
	private String aac002;

	/**** 出生日期 ****/
	private String aac006;

	/**** 参保险种 ****/
	private String aae140;

	/**** 人员类别 ****/
	private String bka004;

	/**** 所属单位 ****/
	private String bka008;

	/**** 联系人 ****/
	private String aae004;

	/**** 手机号码 ****/
	private String aae005;

	/**** 病种码值 ****/
	private String bka006;

	/**** 病种手术日期 ****/
	private String bka911;

	/**** 病种开始日期 ****/
	private String aae030;

	/**** 病种截止日期 ****/
	private String aae031;

	/**** 确诊时间 ****/
	private String bka028;

	/**** 是否境外 ****/
	private String bka010;

	/**** 是否提前认定 ****/
	private String bka011;

	/**** 是否需要年审 ****/
	private String bka012;

	/**** 鉴定标准编号（保存） ****/
	private String bka013;

	/**** 专家姓名(按名称查询) ****/
	private String bkc275;

	/**** 医院专家1 ****/
	private String aaa001;

	/**** 医院专家2 ****/
	private String aaa002;

	/**** 医院专家3 ****/
	private String aaa003;

	/**** 有效标志,0,无效,1有效 ****/
	private String aae100;

	/**** 审核标志(1:未审核；2：初审通过；3：初审不通过；4：复审通过；5：复审不通过) ****/
	private String aae016;

	/**** 查询条件（社保卡） ****/
	private String querystring;

	/**** 鉴定标准（查询） ****/
	private String bkz002;
	/**** 鉴定标准编号（查询） ****/
	private String bkz001;
	/**** 回执单id ****/
	private String bizid;
	/** 病种名称 **/
	private String bka007;
	/** 经办时间 **/
	private String aae036;
	/**经办开始时间**/
	private String aae037;
	/**经办结束时间**/
	private String aae038;
	/** 经办人 **/
	private String aae011;
	/** 经办人姓名 */
	private String bae100;
	/** 备注 */
	private String aae013;

	private String aka120;// 重大疾病病种编码
	private String aka121;// 重大疾病名称
	private String queryFlag;
	
	
	public String getQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(String queryFlag) {
		this.queryFlag = queryFlag;
	}

	public String getAae037() {
		return aae037;
	}

	public void setAae037(String aae037) {
		this.aae037 = aae037;
	}

	public String getAae038() {
		return aae038;
	}

	public void setAae038(String aae038) {
		this.aae038 = aae038;
	}

	public String getAce002() {
		return ace002;
	}

	public void setAce002(String ace002) {
		this.ace002 = ace002;
	}

	public String getBae100() {
		return bae100;
	}

	public void setBae100(String bae100) {
		this.bae100 = bae100;
	}

	public String getAae013() {
		return aae013;
	}

	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	public String getAke001() {
		return ake001;
	}

	public void setAke001(String ake001) {
		this.ake001 = ake001;
	}

	public String getAke002() {
		return ake002;
	}

	public void setAke002(String ake002) {
		this.ake002 = ake002;
	}

	/** 药品编码 */
	private String ake001;
	/** 药品名称 */
	private String ake002;

	public String getAae036() {
		return aae036;
	}

	public void setAae036(String aae036) {
		this.aae036 = aae036;
	}

	public String getAae011() {
		return aae011;
	}

	public void setAae011(String aae011) {
		this.aae011 = aae011;
	}

	public String getBka007() {
		return bka007;
	}

	public void setBka007(String bka007) {
		this.bka007 = bka007;
	}

	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}

	public String getBkz002() {
		return bkz002;
	}

	public void setBkz002(String bkz002) {
		this.bkz002 = bkz002;
	}

	public String getBkz001() {
		return bkz001;
	}

	public void setBkz001(String bkz001) {
		this.bkz001 = bkz001;
	}

	public String getBka004() {
		return bka004;
	}

	public void setBka004(String bka004) {
		this.bka004 = bka004;
	}

	public String getAce001() {
		return ace001;
	}

	public String getBka008() {
		return bka008;
	}

	public void setBka008(String bka008) {
		this.bka008 = bka008;
	}

	public void setAce001(String ace001) {
		this.ace001 = ace001;
	}

	public String getBka006() {
		return bka006;
	}

	public void setBka006(String bka006) {
		this.bka006 = bka006;
	}

	public String getAac002() {
		return aac002;
	}

	public void setAac002(String aac002) {
		this.aac002 = aac002;
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

	public String getAac006() {
		return aac006;
	}

	public void setAac006(String aac006) {
		this.aac006 = aac006;
	}

	public String getAae140() {
		return aae140;
	}

	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}

	public String getAae004() {
		return aae004;
	}

	public void setAae004(String aae004) {
		this.aae004 = aae004;
	}

	public String getAae005() {
		return aae005;
	}

	public void setAae005(String aae005) {
		this.aae005 = aae005;
	}

	public String getBka911() {
		return bka911;
	}

	public void setBka911(String bka911) {
		this.bka911 = bka911;
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

	public String getBka028() {
		return bka028;
	}

	public void setBka028(String bka028) {
		this.bka028 = bka028;
	}

	public String getBka010() {
		return bka010;
	}

	public void setBka010(String bka010) {
		this.bka010 = bka010;
	}

	public String getBka011() {
		return bka011;
	}

	public void setBka011(String bka011) {
		this.bka011 = bka011;
	}

	public String getBka012() {
		return bka012;
	}

	public void setBka012(String bka012) {
		this.bka012 = bka012;
	}

	public String getBka013() {
		return bka013;
	}

	public void setBka013(String bka013) {
		this.bka013 = bka013;
	}

	public String getQuerystring() {
		return querystring;
	}

	public void setQuerystring(String querystring) {
		this.querystring = querystring;
	}

	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}

	public String getAaa027() {
		return aaa027;
	}

	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	public String getBkc275() {
		return bkc275;
	}

	public void setBkc275(String bkc275) {
		this.bkc275 = bkc275;
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	public String getAae016() {
		return aae016;
	}

	public void setAae016(String aae016) {
		this.aae016 = aae016;
	}

	public String getAaa001() {
		return aaa001;
	}

	public void setAaa001(String aaa001) {
		this.aaa001 = aaa001;
	}

	public String getAaa002() {
		return aaa002;
	}

	public void setAaa002(String aaa002) {
		this.aaa002 = aaa002;
	}

	public String getAaa003() {
		return aaa003;
	}

	public void setAaa003(String aaa003) {
		this.aaa003 = aaa003;
	}

	public String getAka120() {
		return aka120;
	}

	public void setAka120(String aka120) {
		this.aka120 = aka120;
	}

	public String getAka121() {
		return aka121;
	}

	public void setAka121(String aka121) {
		this.aka121 = aka121;
	}

}
