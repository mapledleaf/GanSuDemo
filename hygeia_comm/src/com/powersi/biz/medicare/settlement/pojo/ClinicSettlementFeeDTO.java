package com.powersi.biz.medicare.settlement.pojo;

import com.powersi.comm.bean.BaseBean;

public class ClinicSettlementFeeDTO extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private String ake006;
	private String bka056;
	private String bka057;
	private String bka058;
	private String diagnose_flag;
	private String staple_flag;
	public String getAke006() {
		return ake006;
	}
	public void setAke006(String ake006) {
		this.ake006 = ake006;
	}
	public String getBka056() {
		return bka056;
	}
	public void setBka056(String bka056) {
		this.bka056 = bka056;
	}
	public String getBka057() {
		return bka057;
	}
	public void setBka057(String bka057) {
		this.bka057 = bka057;
	}
	public String getBka058() {
		return bka058;
	}
	public void setBka058(String bka058) {
		this.bka058 = bka058;
	}
	public String getDiagnose_flag() {
		return diagnose_flag;
	}
	public void setDiagnose_flag(String diagnose_flag) {
		this.diagnose_flag = diagnose_flag;
	}
	public String getStaple_flag() {
		return staple_flag;
	}
	public void setStaple_flag(String staple_flag) {
		this.staple_flag = staple_flag;
	}

}
