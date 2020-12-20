package com.powersi.biz.medicare.comm.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 结算单对象
 * @author zhos
 *
 */
public class SettlementRemoteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	private List<Map> kc56Rows;
	
	@SuppressWarnings("rawtypes")
	private List<Map> kc57Rows;
	
	@SuppressWarnings("rawtypes")
	private List<Map> kc26Rows;

	@SuppressWarnings("rawtypes")
	public List<Map> getKc56Rows() {
		return kc56Rows;
	}

	@SuppressWarnings("rawtypes")
	public void setKc56Rows(List<Map> kc56Rows) {
		this.kc56Rows = kc56Rows;
	}

	@SuppressWarnings("rawtypes")
	public List<Map> getKc57Rows() {
		return kc57Rows;
	}

	@SuppressWarnings("rawtypes")
	public void setKc57Rows(List<Map> kc57Rows) {
		this.kc57Rows = kc57Rows;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Map> getKc26Rows() {
		return kc26Rows;
	}
	
	@SuppressWarnings("rawtypes")
	public void setKc26Rows(List<Map> kc26Rows) {
		this.kc26Rows = kc26Rows;
	}
	
	
	

}
