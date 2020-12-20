package com.powersi.configvalue.pojo;

import java.io.Serializable;

/**
 * 配置取值_需要配置的对象定义_比如：医保年度
 * 
 * @author 刘勇
 *
 */
public class KE65 implements Serializable {

	private static final long serialVersionUID = 1L;

	private String bke551;// ID_UUID(VARCHAR2(50) not null)
	private String bke552;// 需要配置的对象名称(VARCHAR2(100) not null)
	private String bke553;// 需要配置的对象化名(VARCHAR2(100) not null)
	private String bke554;// 需要配置的对象描述(VARCHAR2(100))
	private String bke555;// 需要配置的对象条件ID(UUID)(VARCHAR2(50) )
	private String bke528;// 需要配置的对象条件成立时取值要素ID(UUID)(VARCHAR2(50) )
	private String aae100;// 有效标志(VARCHAR2(1) default '1' not null)

	/**
	 * 
	 * @return ID_UUID
	 */
	public String getBke551() {
		return bke551;
	}

	/**
	 * 
	 * @param bke551
	 *            ID_UUID
	 */
	public void setBke551(String bke551) {
		this.bke551 = bke551;
	}

	/**
	 * 
	 * @return 需要配置的对象名称
	 */
	public String getBke552() {
		return bke552;
	}

	/**
	 * 
	 * @param bke552
	 *            需要配置的对象名称
	 */
	public void setBke552(String bke552) {
		this.bke552 = bke552;
	}

	/**
	 * 
	 * @return 需要配置的对象化名
	 */
	public String getBke553() {
		return bke553;
	}

	/**
	 * 
	 * @param bke553
	 *            需要配置的对象化名
	 */
	public void setBke553(String bke553) {
		this.bke553 = bke553;
	}

	/**
	 * 
	 * @return 需要配置的对象描述
	 */
	public String getBke554() {
		return bke554;
	}

	/**
	 * 
	 * @param bke554
	 *            需要配置的对象描述
	 */
	public void setBke554(String bke554) {
		this.bke554 = bke554;
	}

	/**
	 * 
	 * @return 需要配置的对象条件ID(UUID)
	 */
	public String getBke555() {
		return bke555;
	}

	/**
	 * 
	 * @param bke555
	 *            需要配置的对象条件ID(UUID)
	 */
	public void setBke555(String bke555) {
		this.bke555 = bke555;
	}

	/**
	 * 
	 * @return 需要配置的对象条件成立时取值要素ID(UUID)
	 */
	public String getBke528() {
		return bke528;
	}

	/**
	 * 
	 * @param bke528
	 *            需要配置的对象条件成立时取值要素ID(UUID)
	 */
	public void setBke528(String bke528) {
		this.bke528 = bke528;
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
