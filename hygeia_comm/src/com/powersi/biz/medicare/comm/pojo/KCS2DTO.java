package com.powersi.biz.medicare.comm.pojo;

import java.util.Date;

import com.powersi.comm.mybatis.Page;

/**
 * 手术与操作代码表 DTO
 * @author zhos
 * @date 2017-08-16
 */

public class KCS2DTO  extends Page{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**`BKE530` varchar(50) NOT NULL COMMENT '手术码'**/
	private String bke530;
	  
	/**`BKE531` varchar(300) DEFAULT NULL COMMENT '手术码对应名称',**/
	private String bke531;
	
	/**`BKE532` varchar(100) DEFAULT NULL COMMENT '手术类别',**/
	private String bke532;
	
	/**`BKE533` varchar(100) DEFAULT NULL COMMENT '手术级别',**/
	private String bke533;
	
	/**`BKC001` varchar(20) DEFAULT NULL COMMENT '版本号',**/
	private String bkc001;
	
	/**`AKA020` varchar(100) DEFAULT NULL COMMENT '拼音助记码',**/
	private String aka020;
	
	/**`AKA021` varchar(100) DEFAULT NULL COMMENT '五笔助记码',**/
	private String aka021;
	
	/**`AAE100` varchar(1) DEFAULT '1' COMMENT '有效标志,0,无效,1有效',**/
	private String aae100;
	
	private Date bka032;// 业务终结日期        

	
	public Date getBka032() {
		return bka032;
	}

	public void setBka032(Date bka032) {
		this.bka032 = bka032;
	}

	public String getBke530() {
		return bke530;
	}

	public void setBke530(String bke530) {
		this.bke530 = bke530;
	}

	public String getBke531() {
		return bke531;
	}

	public void setBke531(String bke531) {
		this.bke531 = bke531;
	}

	public String getBke532() {
		return bke532;
	}

	public void setBke532(String bke532) {
		this.bke532 = bke532;
	}

	public String getBke533() {
		return bke533;
	}

	public void setBke533(String bke533) {
		this.bke533 = bke533;
	}

	public String getBkc001() {
		return bkc001;
	}

	public void setBkc001(String bkc001) {
		this.bkc001 = bkc001;
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

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	
	
}
