package com.powersi.biz.medicare.health.pojo;

import com.powersi.comm.bean.BaseBean;
/**
 * 项目组－项目关系表
 * @author hasee
 *
 */
public class KEH9 extends BaseBean{
	private static final long serialVersionUID = 1L;
	/**
	 * 项目组编号
	 */
	private String bkh070;
	/**
	 * 体检项目名称
	 */
	private String bkh027;
	/**
	 * 体检项目编码
	 */
	private String bkh028;
	/**
	 * 是否默认项目
	 */
	private String bkh078;
	/**
	 * 统筹区编码
	 */
	private String aaa027;
	/**
	 * 修改时间
	 */
	private int bke204;
	/**
	 * 修改人工号
	 */
	private String bke205;
	/**
	 * 修改人
	 */
	private String bke206;
	/**
	 * 排序
	 */
	private int bkh073;
	public String getBkh070() {
		return bkh070;
	}
	public void setBkh070(String bkh070) {
		this.bkh070 = bkh070;
	}
	public String getBkh027() {
		return bkh027;
	}
	public void setBkh027(String bkh027) {
		this.bkh027 = bkh027;
	}
	public String getBkh028() {
		return bkh028;
	}
	public void setBkh028(String bkh028) {
		this.bkh028 = bkh028;
	}
	public String getBkh078() {
		return bkh078;
	}
	public void setBkh078(String bkh078) {
		this.bkh078 = bkh078;
	}
	public String getAaa027() {
		return aaa027;
	}
	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}
	public int getBke204() {
		return bke204;
	}
	public void setBke204(int bke204) {
		this.bke204 = bke204;
	}
	public String getBke205() {
		return bke205;
	}
	public void setBke205(String bke205) {
		this.bke205 = bke205;
	}
	public String getBke206() {
		return bke206;
	}
	public void setBke206(String bke206) {
		this.bke206 = bke206;
	}
	public int getBkh073() {
		return bkh073;
	}
	public void setBkh073(int bkh073) {
		this.bkh073 = bkh073;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
