package com.powersi.biz.medicare.catalog.pojo;

import com.powersi.comm.bean.BaseBean;

/**
 * 连锁医疗机构关系表
 * @author ChenXing
 *
 */
public class KA10 extends BaseBean{

	private static final long serialVersionUID = 1L;
	public static final String MAP_HYGEIA_BASE_LSGX = "MAP_HYGEIA_BASE_LSGX";
	private String kaa111;//医疗关系序列号 
	private String kaa001;//医疗关系编码
	private String akb020;//医疗服务机构编号
	private String bke460;//总店标志 0：非总店，1：总店
	private String bkn024;//总店名称
	
	public String getKaa111() {
		return kaa111;
	}
	public void setKaa111(String kaa111) {
		this.kaa111 = kaa111;
	}
	public String getKaa001() {
		return kaa001;
	}
	public void setKaa001(String kaa001) {
		this.kaa001 = kaa001;
	}
	public String getAkb020() {
		return akb020;
	}
	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}
	public String getBke460() {
		return bke460;
	}
	public void setBke460(String bke460) {
		this.bke460 = bke460;
	}
	public String getBkn024() {
		return bkn024;
	}
	public void setBkn024(String bkn024) {
		this.bkn024 = bkn024;
	}
	
}
