package com.powersi.events;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 事件
 * 
 * @author 刘勇
 *
 */
public class KE67 implements Serializable {

	private static final long serialVersionUID = 1L;

	private String bke581;// 事件ID UUID(VARCHAR2(50) not null)
	private String bke582;// 事件外键ID UUID(VARCHAR2(50) null)
	private String bke583;// 事件主体 VARCHAR2(100) null
	/**
	 * 事件类型
	 * 
	 * <pre>
	 * 1(新增/add)
	 * 2(删除/delete/作废)
	 * 3(修改/update)
	 * 4(查询/select)
	 * 5(审核/audit)
	 * </pre>
	 */
	private String bke584;// 事件类型 VARCHAR2(10) not null
	private String bke585;// 事件触发人工号 VARCHAR2(50) null
	private String bke586;// 事件触发人 VARCHAR2(50) null
	private Date aae036;// 事件触发时间 DATE not null
	private String aae013;// 事件备注 VARCHAR2(100) null
	private String aae100;// 有效标志(VARCHAR2(1) default '1' not null)

	@Override
	public String toString() {
		return this.toJson();
	}

	/**
	 * 
	 * @return
	 */
	public String toJson() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public boolean compareTo(Object obj) {
		return this.toString().equals(obj.toString());
	}

	/**
	 * 事件ID
	 * 
	 * @return
	 */
	public String getBke581() {
		return bke581;
	}

	/**
	 * 事件ID
	 * 
	 * @param bke581
	 */
	public void setBke581(String bke581) {
		this.bke581 = bke581;
	}

	/**
	 * 事件外键ID
	 * 
	 * @return
	 */
	public String getBke582() {
		return bke582;
	}

	/**
	 * 事件外键ID
	 * 
	 * @param bke582
	 */
	public void setBke582(String bke582) {
		this.bke582 = bke582;
	}

	/**
	 * 事件主体
	 * 
	 * @return
	 */
	public String getBke583() {
		return bke583;
	}

	/**
	 * 事件主体
	 * 
	 * @param bke583
	 */
	public void setBke583(String bke583) {
		this.bke583 = bke583;
	}

	/**
	 * 事件类型
	 * 
	 * <pre>
	 * 1(新增/add)
	 * 2(删除/delete/作废)
	 * 3(修改/update)
	 * 4(查询/select)
	 * 5(审核/audit)
	 * </pre>
	 * 
	 * @return
	 */
	public String getBke584() {
		return bke584;
	}

	/**
	 * 事件类型
	 * 
	 * <pre>
	 * 1(新增/add)
	 * 2(删除/delete/作废)
	 * 3(修改/update)
	 * 4(查询/select)
	 * 5(审核/audit)
	 * </pre>
	 * 
	 * @param bke584
	 */
	public void setBke584(String bke584) {
		this.bke584 = bke584;
	}

	/**
	 * 事件触发人工号
	 * 
	 * @return
	 */
	public String getBke585() {
		return bke585;
	}

	/**
	 * 事件触发人工号
	 * 
	 * @param bke585
	 */
	public void setBke585(String bke585) {
		this.bke585 = bke585;
	}

	/**
	 * 事件触发人
	 * 
	 * @return
	 */
	public String getBke586() {
		return bke586;
	}

	/**
	 * 事件触发人
	 * 
	 * @param bke586
	 */
	public void setBke586(String bke586) {
		this.bke586 = bke586;
	}

	/**
	 * 事件触发时间
	 * 
	 * @return
	 */
	public Date getAae036() {
		return aae036;
	}

	/**
	 * 事件触发时间
	 * 
	 * @param aae036
	 */
	public void setAae036(Date aae036) {
		this.aae036 = aae036;
	}

	/**
	 * 事件备注
	 * 
	 * @return
	 */
	public String getAae013() {
		return aae013;
	}

	/**
	 * 事件备注
	 * 
	 * @param aae013
	 */
	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	/**
	 * 有效标志
	 * 
	 * @return
	 */
	public String getAae100() {
		return aae100;
	}

	/**
	 * 有效标志
	 * 
	 * @param aae100
	 */
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
