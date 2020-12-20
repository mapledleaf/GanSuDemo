package com.powersi.biz.medicare.comm.pojo;

/**
 * 医院表
 * 
 * @author 刘勇
 *
 */
public class KB01 extends RemotingDTO {

	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private String kb01id;
	/**
	 * 部门ID
	 */
	private String dept_id;
	/**
	 * 定点医疗机构编号
	 */
	private String akb020;
	/**
	 * 定点医疗机构名称
	 */
	private String akb021;
	/**
	 * 经办地(医院所在的统筹区)
	 */
	private String aaa027;
	/**
	 * 有效标识1有效0无效
	 */
	private String aae100;
	
	/**
	 * 医院级别
	 */
	private String bkc110;

	public String getKb01id() {
		return kb01id;
	}

	public void setKb01id(String kb01id) {
		this.kb01id = kb01id;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
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

	public String getAaa027() {
		return aaa027;
	}

	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	public String getBkc110() {
		return bkc110;
	}

	public void setBkc110(String bkc110) {
		this.bkc110 = bkc110;
	}

}
