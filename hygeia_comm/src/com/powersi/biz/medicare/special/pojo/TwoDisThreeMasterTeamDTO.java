package com.powersi.biz.medicare.special.pojo;

import java.io.Serializable;
import java.util.Date;

public class TwoDisThreeMasterTeamDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 三师团队编码 **/
	private Long aaz308;
	
	/** 医院编码 **/
	private String akb020;
	
	/** 专科医师ID **/
	private String bka801;
	
	/** 专科医师姓名 **/
	private String bka802;
	
	/** 全科医师ID **/
	private String bka803;
	
	/** 全科医师姓名 **/
	private String bka804;
	
	/** 护师士姓名 **/
	private String bka805;
	
	/** 申请时间 **/
	private Date aae036;
	
	/** 申请人工号 **/
	private String aae011;
	
	/** 申请人姓名 **/
	private String bae100;
	
	/** 审核时间 **/
	private Date aae015;
	
	/** 审核人工号 **/
	private String aae014;
	
	/** 审核人姓名 **/
	private String bae101;
	
	/** 审核标志  0未审核 1审核通过 2审核不通过 **/
	private String aae016;
	
	/** 有效标志  0无效 1有效 **/
	private String aae100;
	
	/** 有效期开始时间 **/
	private Date aae030;
	
	/** 有效期截止时间 **/
	private Date aae031;
	
	/**  备注字段1 **/
	private String bkm025;
	
	/**  备注字段2 **/
	private String bkm026;
	
	/**  备注字段3 **/
	private String bkm027;

	public Long getAaz308() {
		return aaz308;
	}

	public void setAaz308(Long aaz308) {
		this.aaz308 = aaz308;
	}

	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}

	public String getBka801() {
		return bka801;
	}

	public void setBka801(String bka801) {
		this.bka801 = bka801;
	}

	public String getBka802() {
		return bka802;
	}

	public void setBka802(String bka802) {
		this.bka802 = bka802;
	}

	public String getBka803() {
		return bka803;
	}

	public void setBka803(String bka803) {
		this.bka803 = bka803;
	}

	public String getBka804() {
		return bka804;
	}

	public void setBka804(String bka804) {
		this.bka804 = bka804;
	}

	public String getBka805() {
		return bka805;
	}

	public void setBka805(String bka805) {
		this.bka805 = bka805;
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
		this.aae011 = aae011;
	}

	public String getBae100() {
		return bae100;
	}

	public void setBae100(String bae100) {
		this.bae100 = bae100;
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
		this.aae014 = aae014;
	}

	public String getBae101() {
		return bae101;
	}

	public void setBae101(String bae101) {
		this.bae101 = bae101;
	}

	public String getAae016() {
		return aae016;
	}

	public void setAae016(String aae016) {
		this.aae016 = aae016;
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
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
	
	
}
