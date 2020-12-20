package com.powersi.biz.medicare.comm.pojo;

/**
 * 
 * 基金状态
 * 
 * @author 刘勇
 *
 */
public class InsuredFundDTO extends POJO {

	private static final long serialVersionUID = 1L;

	private String aab301;// 参保地
	private String aab299;// 就医地
	private String aae140;// 险种编号
	private String aac001;// 个人电脑号
	private String bka571;// 月份
	private String bka572;// 基金编号
	private String bka573;// 基金名称
	private String bka888;// 基金冻结状态(0正常、1冻结、9未参保)
	private String calc_prd;// 月份
	private String pers_type;// 上月缴费人员类别
	private String fund_id;// 基金编号
	private String fund_name;// 基金名称
	private String indi_freeze_status;// 冻结标志
	private String indi_freeze_note;// 说明
	private String corpid;// 单位参保
	private String aaa157;//基金编码
	private String aac031;//冻结状态
	
	
	

	/**
	 * @return the aaa157
	 */
	public String getAaa157() {
		return aaa157;
	}

	/**
	 * @param aaa157 the aaa157 to set
	 */
	public void setAaa157(String aaa157) {
		this.aaa157 = aaa157;
	}

	/**
	 * @return the aac031
	 */
	public String getAac031() {
		return aac031;
	}

	/**
	 * @param aac031 the aac031 to set
	 */
	public void setAac031(String aac031) {
		this.aac031 = aac031;
	}

	public String getCalc_prd() {
		return calc_prd;
	}

	public void setCalc_prd(String calc_prd) {
		this.calc_prd = calc_prd;
	}

	public String getPers_type() {
		return pers_type;
	}

	public void setPers_type(String pers_type) {
		this.pers_type = pers_type;
	}

	public String getFund_id() {
		return fund_id;
	}

	public void setFund_id(String fund_id) {
		this.fund_id = fund_id;
	}

	public String getFund_name() {
		return fund_name;
	}

	public void setFund_name(String fund_name) {
		this.fund_name = fund_name;
	}

	public String getIndi_freeze_status() {
		return indi_freeze_status;
	}

	public void setIndi_freeze_status(String indi_freeze_status) {
		this.indi_freeze_status = indi_freeze_status;
	}

	public String getIndi_freeze_note() {
		return indi_freeze_note;
	}

	public void setIndi_freeze_note(String indi_freeze_note) {
		this.indi_freeze_note = indi_freeze_note;
	}

	public String getCorpid() {
		return corpid;
	}

	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}

	/**
	 * 
	 * @return 参保地
	 */
	public String getAab301() {
		return aab301;
	}

	/**
	 * 
	 * @param aab301
	 *            参保地
	 */
	public void setAab301(String aab301) {
		this.aab301 = aab301;
	}

	/**
	 * 
	 * @return 就医地
	 */
	public String getAab299() {
		return aab299;
	}

	/**
	 * 
	 * @param aab299
	 *            就医地
	 */
	public void setAab299(String aab299) {
		this.aab299 = aab299;
	}

	/**
	 * 
	 * @return 险种编号
	 */
	public String getAae140() {
		return aae140;
	}

	/**
	 * 
	 * @param aae140
	 *            险种编号
	 */
	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}

	/**
	 * 
	 * @return 个人电脑号
	 */
	public String getAac001() {
		return aac001;
	}

	/**
	 * 
	 * @param aac001
	 *            个人电脑号
	 */
	public void setAac001(String aac001) {
		this.aac001 = aac001;
	}

	/**
	 * 
	 * @return 月份
	 */
	public String getBka571() {
		return bka571;
	}

	/**
	 * 
	 * @param bka571
	 *            月份
	 */
	public void setBka571(String bka571) {
		this.bka571 = bka571;
	}

	/**
	 * 
	 * @return 基金编号
	 */
	public String getBka572() {
		return bka572;
	}

	/**
	 * 
	 * @param bka572
	 *            基金编号
	 */
	public void setBka572(String bka572) {
		this.bka572 = bka572;
	}

	/**
	 * 
	 * @return 基金名称
	 */
	public String getBka573() {
		return bka573;
	}

	/**
	 * 
	 * @param bka573
	 *            基金名称
	 */
	public void setBka573(String bka573) {
		this.bka573 = bka573;
	}

	/**
	 * 
	 * @return 基金冻结状态(0正常、1冻结、9未参保)
	 */
	public String getBka888() {
		return bka888;
	}

	/**
	 * 
	 * @param bka888
	 *            基金冻结状态(0正常、1冻结、9未参保)
	 */
	public void setBka888(String bka888) {
		this.bka888 = bka888;
	}

}
