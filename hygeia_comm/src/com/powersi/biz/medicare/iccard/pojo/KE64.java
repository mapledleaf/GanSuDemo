package com.powersi.biz.medicare.iccard.pojo;

import java.util.Date;

import com.powersi.comm.bean.BaseBean;

/**
 * TAC保存
 * 
 * @author 刘勇
 *
 */
public class KE64 extends BaseBean {

	private static final long serialVersionUID = 1L;

	private String bke546;// UUID

	private String bke547;// TAC码

	private Date bke549;// 系统时间

	private String akb020;// 医院编号

	private String aaz217;// 就医登记号

	private String aac002;// 社会保障号码

	private Long aac001;// 电脑号

	private String bka100;// 社保卡号

	private String aac003;// 姓名

	private String bke548;// 卡读写时间

	private String aae100;// 有效标志

	public String getBke546() {
		return bke546;
	}

	public void setBke546(String bke546) {
		this.bke546 = bke546 == null ? null : bke546.trim();
	}

	public String getBke547() {
		return bke547;
	}

	public void setBke547(String bke547) {
		this.bke547 = bke547 == null ? null : bke547.trim();
	}

	public Date getBke549() {
		return bke549;
	}

	public void setBke549(Date bke549) {
		this.bke549 = bke549;
	}

	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020 == null ? null : akb020.trim();
	}

	public String getAaz217() {
		return aaz217;
	}

	public void setAaz217(String aaz217) {
		this.aaz217 = aaz217 == null ? null : aaz217.trim();
	}

	public String getAac002() {
		return aac002;
	}

	public void setAac002(String aac002) {
		this.aac002 = aac002 == null ? null : aac002.trim();
	}

	public Long getAac001() {
		return aac001;
	}

	public void setAac001(Long aac001) {
		this.aac001 = aac001;
	}

	public String getBka100() {
		return bka100;
	}

	public void setBka100(String bka100) {
		this.bka100 = bka100 == null ? null : bka100.trim();
	}

	public String getAac003() {
		return aac003;
	}

	public void setAac003(String aac003) {
		this.aac003 = aac003 == null ? null : aac003.trim();
	}

	public String getBke548() {
		return bke548;
	}

	public void setBke548(String bke548) {
		this.bke548 = bke548 == null ? null : bke548.trim();
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100 == null ? null : aae100.trim();
	}

}