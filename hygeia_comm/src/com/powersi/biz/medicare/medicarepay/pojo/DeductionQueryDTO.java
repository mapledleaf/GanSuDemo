package com.powersi.biz.medicare.medicarepay.pojo;

import com.powersi.biz.medicare.comm.pojo.ParamDTO;

/**
 * 扣付费用查询DTO
 * 
 * 2018年8月3日上午11:46:21
 *
 * @author lwyao
 *
 */
@SuppressWarnings("serial")
public class DeductionQueryDTO extends ParamDTO {

	String aae041; // 申报开始月份
	String aae042; // 申报结束月份
	String aae030; // 结算开始日期(akc194)
	String aae031; // 结算结束日期(akc194)
	String bkp160; // 申诉状态
	String aka130; // 业务类型
	String bka006; // 待遇类型
	String bka035; // 人员类型
	String bkc371; // 扣付类别
	String col;
	String colVal;

	/**
	 * 申报开始月份
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getAae041() {
		return aae041;
	}

	/**
	 * 申报开始月份
	 * 
	 * @author lwyao
	 * @param aae041
	 */
	public void setAae041(String aae041) {
		this.aae041 = aae041;
	}

	/**
	 * 申报结束月份
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getAae042() {
		return aae042;
	}

	/**
	 * 申报结束月份
	 * 
	 * @author lwyao
	 * @param aae042
	 */
	public void setAae042(String aae042) {
		this.aae042 = aae042;
	}

	/**
	 * 结算开始日期(akc194)
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getAae030() {
		return aae030;
	}

	/**
	 * 结算开始日期(akc194)
	 * 
	 * @author lwyao
	 * @param aae030
	 */
	public void setAae030(String aae030) {
		this.aae030 = aae030;
	}

	/**
	 * 结算结束日期(akc194)
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getAae031() {
		return aae031;
	}

	/**
	 * 结算结束日期(akc194)
	 * 
	 * @author lwyao
	 * @param aae031
	 */
	public void setAae031(String aae031) {
		this.aae031 = aae031;
	}

	public String getBkp160() {
		return bkp160;
	}

	public void setBkp160(String bkp160) {
		this.bkp160 = bkp160;
	}

	public String getAka130() {
		return aka130;
	}

	public void setAka130(String aka130) {
		this.aka130 = aka130;
	}

	public String getBka006() {
		return bka006;
	}

	public void setBka006(String bka006) {
		this.bka006 = bka006;
	}

	public String getBka035() {
		return bka035;
	}

	public void setBka035(String bka035) {
		this.bka035 = bka035;
	}

	/**
	 * 扣付类别
	 * 
	 * @author lwyao
	 * @return
	 */
	public String getBkc371() {
		return bkc371;
	}

	/**
	 * 扣付类别
	 * 
	 * @author lwyao
	 * @param bkc371
	 */
	public void setBkc371(String bkc371) {
		this.bkc371 = bkc371;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getColVal() {
		return colVal;
	}

	public void setColVal(String colVal) {
		this.colVal = colVal;
	}

}