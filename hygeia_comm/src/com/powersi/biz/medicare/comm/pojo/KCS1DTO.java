package com.powersi.biz.medicare.comm.pojo;

import java.util.Date;

import com.powersi.comm.mybatis.Page;

/**
 * 疾病分类与代码表 DTO
 * @author zhos
 * @date 2017-08-16
 */

public class KCS1DTO extends Page{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**`AKA120` varchar(50) NOT NULL COMMENT '疾病ICD编码',**/
	private String aka120;
	  
	/**`AKA121` varchar(300) DEFAULT NULL COMMENT '疾病名称',**/
	private String aka121;
	
	/**`AKA020` varchar(100) DEFAULT NULL COMMENT '拼音助记码',**/
	private String aka020;
	
	/**`AKA021` varchar(100) DEFAULT NULL COMMENT '五笔助记码',**/
	private String aka021;
	
	/**`BKC001` varchar(20) DEFAULT NULL COMMENT 'ICD版本',**/
	private String bkc001;
	
	/**`AAE100` varchar(1) DEFAULT '1' COMMENT '有效标志,0,无效,1有效',**/
	private String aae100;
	
	private Date bka032;// 业务终结日期        

	public String getAka120() {
		return aka120;
	}

	public void setAka120(String aka120) {
		this.aka120 = aka120;
	}

	public String getAka121() {
		return aka121;
	}

	public void setAka121(String aka121) {
		this.aka121 = aka121;
	}

	public String getAka020() {
		return aka020;
	}

	public void setAka020(String aka020) {
		this.aka020 = aka020;
	}

	public String getAka021() {
		return aka021;
	}

	public void setAka021(String aka021) {
		this.aka021 = aka021;
	}

	public String getBkc001() {
		return bkc001;
	}

	public void setBkc001(String bkc001) {
		this.bkc001 = bkc001;
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	public Date getBka032() {
		return bka032;
	}

	public void setBka032(Date bka032) {
		this.bka032 = bka032;
	}

	
	
	
}
