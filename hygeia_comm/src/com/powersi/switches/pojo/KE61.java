package com.powersi.switches.pojo;

import com.powersi.comm.bean.BaseBean;

/**
 * 要素定义
 * 
 * @author 刘勇
 *
 */
public class KE61 extends BaseBean {

	private static final long serialVersionUID = 1L;

	private String bke528;// 要素ID(UUID)(VARCHAR2(50) not null)
	private String bke529;// 名称(VARCHAR2(100) not null)
	private String bke530;// 化名(VARCHAR2(100) not null)
	private String bke531;// 数据类型(VARCHAR2(10) not null)
	private String bke532;// 数据名称(VARCHAR2(100))
	private String aae100;// 有效标志(VARCHAR2(1) default '1' not null)

	/**
	 * 
	 * @return 要素ID(UUID)
	 */
	public String getBke528() {
		return bke528;
	}

	/**
	 * 
	 * @param bke528
	 *            要素ID(UUID)
	 */
	public void setBke528(String bke528) {
		this.bke528 = bke528;
	}

	/**
	 * 
	 * @return 名称
	 */
	public String getBke529() {
		return bke529;
	}

	/**
	 * 
	 * @param bke529
	 *            名称
	 */
	public void setBke529(String bke529) {
		this.bke529 = bke529;
	}

	/**
	 * 
	 * @return 化名
	 */
	public String getBke530() {
		return bke530;
	}

	/**
	 * 
	 * @param bke530
	 *            化名
	 */
	public void setBke530(String bke530) {
		this.bke530 = bke530;
	}

	/**
	 * 
	 * @return 数据类型
	 */
	public String getBke531() {
		return bke531;
	}

	/**
	 * 
	 * @param bke531
	 *            数据类型
	 */
	public void setBke531(String bke531) {
		this.bke531 = bke531;
	}

	/**
	 * 
	 * @return 数据名称
	 */
	public String getBke532() {
		return bke532;
	}

	/**
	 * 
	 * @param bke532
	 *            数据名称
	 */
	public void setBke532(String bke532) {
		this.bke532 = bke532;
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
