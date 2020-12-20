package com.powersi.biz.medicare.personalizationinter.pojo;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 结算结果信息
 * 
 * @author 刘勇
 *
 */
public class JC implements Serializable {

	private static final long serialVersionUID = 1L;
	// 字段名 字段类型 字段说明 校验规则
	private String grshbzh;// varchar2 (20) 个人社会保障号
	private String zgxm;// varchar2 (50) 职工姓名
	private String zyh;// varchar2 (20) 住院号
	private String jsjlh;// varchar2 (20) 结算记录号
	private String jzjlh;// varchar2 (20) 就诊记录号
	private double bxbl;// number(4) 报销比例
	private double qfbz;// number(12,2) 起付标准
	private double zfy;// number(16,2) 总费用
	private double gffy;// number(16,2) 公费费用 医保费用 =总费用-自费费用
	private double zffy;// number(16,2) 自费费用记载纯自费费用
	private double jbyltc;// number(16,2) 基本医疗统筹
	private double jbylgr;// number(16,2) 基本医疗个人比例自付
	private double gwytc;// number(16,2) 公务员统筹
	private double gwygr;// number(16,2) 公务员个人比例自付
	private double deyltc;// number(16，2) 大额医疗段统筹支付
	private double deylgr;// number(16，2) 大额医疗段个人支付
	private double dbtc1;// number(16，2) 年度最高支付限额以内大病统筹支付 记载5万-16/18万大病统筹支付金额
	private double dbgr1;// number(16，2) 年度最高支付限额以内大病统筹个人支付 记载5万-16/18万大病个人支付金额
	private double dbtc2;// number(16，2) 超过年度最高支付限额大病统筹支付 记载16-25万或18-30万大病统筹支付
	private double dbgr2;// number(16，2) 超过年度最高支付限额大病个人支付 记载16-25万或18-30万大病个人支付
	private double hjcgf;// number(16,2) 超过最高限额部分记载最终的超封顶线金额
	private double grzzf;// number(16,2) 个人总应付=总金额-基本统筹-大病统筹-公务员补助-大额医疗统筹

	/**
	 * varchar2 (20) 个人社会保障号
	 * 
	 * @return
	 */
	public String getGrshbzh() {
		return grshbzh;
	}

	/**
	 * varchar2 (20) 个人社会保障号
	 * 
	 * @param grshbzh
	 */
	public void setGrshbzh(String grshbzh) {
		this.grshbzh = grshbzh;
	}

	/**
	 * varchar2 (50) 职工姓名
	 * 
	 * @return
	 */
	public String getZgxm() {
		return zgxm;
	}

	/**
	 * varchar2 (50) 职工姓名
	 * 
	 * @param zgxm
	 */
	public void setZgxm(String zgxm) {
		this.zgxm = zgxm;
	}

	/**
	 * varchar2 (20) 住院号
	 * 
	 * @return
	 */
	public String getZyh() {
		return zyh;
	}

	/**
	 * varchar2 (20) 住院号
	 * 
	 * @param zyh
	 */
	public void setZyh(String zyh) {
		this.zyh = zyh;
	}

	/**
	 * varchar2 (20) 结算记录号
	 * 
	 * @return
	 */
	public String getJsjlh() {
		return jsjlh;
	}

	/**
	 * varchar2 (20) 结算记录号
	 * 
	 * @param jsjlh
	 */
	public void setJsjlh(String jsjlh) {
		this.jsjlh = jsjlh;
	}

	/**
	 * varchar2 (20) 就诊记录号
	 * 
	 * @return
	 */
	public String getJzjlh() {
		return jzjlh;
	}

	/**
	 * varchar2 (20) 就诊记录号
	 * 
	 * @param jzjlh
	 */
	public void setJzjlh(String jzjlh) {
		this.jzjlh = jzjlh;
	}

	/**
	 * number(4) 报销比例
	 * 
	 * @return
	 */
	public double getBxbl() {
		return bxbl;
	}

	/**
	 * number(4) 报销比例
	 * 
	 * @param bxbl
	 */
	public void setBxbl(double bxbl) {
		this.bxbl = bxbl;
	}

	/**
	 * number(12,2) 起付标准
	 * 
	 * @return
	 */
	public double getQfbz() {
		return qfbz;
	}

	/**
	 * number(12,2) 起付标准
	 * 
	 * @param qfbz
	 */
	public void setQfbz(double qfbz) {
		this.qfbz = qfbz;
	}

	/**
	 * number(16,2) 总费用
	 * 
	 * @return
	 */
	public double getZfy() {
		return zfy;
	}

	/**
	 * number(16,2) 总费用
	 * 
	 * @param zfy
	 */
	public void setZfy(double zfy) {
		this.zfy = zfy;
	}

	/**
	 * number(16,2) 公费费用 医保费用 =总费用-自费费用
	 * 
	 * @return
	 */
	public double getGffy() {
		return gffy;
	}

	/**
	 * number(16,2) 公费费用 医保费用 =总费用-自费费用
	 * 
	 * @param gffy
	 */
	public void setGffy(double gffy) {
		this.gffy = gffy;
	}

	/**
	 * number(16,2) 自费费用记载纯自费费用
	 * 
	 * @return
	 */
	public double getZffy() {
		return zffy;
	}

	/**
	 * number(16,2) 自费费用记载纯自费费用
	 * 
	 * @param zffy
	 */
	public void setZffy(double zffy) {
		this.zffy = zffy;
	}

	/**
	 * number(16,2) 基本医疗统筹
	 * 
	 * @return
	 */
	public double getJbyltc() {
		return jbyltc;
	}

	/**
	 * number(16,2) 基本医疗统筹
	 * 
	 * @param jbyltc
	 */
	public void setJbyltc(double jbyltc) {
		this.jbyltc = jbyltc;
	}

	/**
	 * number(16,2) 基本医疗个人比例自付
	 * 
	 * @return
	 */
	public double getJbylgr() {
		return jbylgr;
	}

	/**
	 * number(16,2) 基本医疗个人比例自付
	 * 
	 * @param jbylgr
	 */
	public void setJbylgr(double jbylgr) {
		this.jbylgr = jbylgr;
	}

	/**
	 * number(16,2) 公务员统筹
	 * 
	 * @return
	 */
	public double getGwytc() {
		return gwytc;
	}

	/**
	 * number(16,2) 公务员统筹
	 * 
	 * @param gwytc
	 */
	public void setGwytc(double gwytc) {
		this.gwytc = gwytc;
	}

	/**
	 * number(16,2) 公务员个人比例自付
	 * 
	 * @return
	 */
	public double getGwygr() {
		return gwygr;
	}

	/**
	 * number(16,2) 公务员个人比例自付
	 * 
	 * @param gwygr
	 */
	public void setGwygr(double gwygr) {
		this.gwygr = gwygr;
	}

	/**
	 * number(16，2) 大额医疗段统筹支付
	 * 
	 * @return
	 */
	public double getDeyltc() {
		return deyltc;
	}

	/**
	 * number(16，2) 大额医疗段统筹支付
	 * 
	 * @param deyltc
	 */
	public void setDeyltc(double deyltc) {
		this.deyltc = deyltc;
	}

	/**
	 * number(16，2) 大额医疗段个人支付
	 * 
	 * @return
	 */
	public double getDeylgr() {
		return deylgr;
	}

	/**
	 * number(16，2) 大额医疗段个人支付
	 * 
	 * @param deylgr
	 */
	public void setDeylgr(double deylgr) {
		this.deylgr = deylgr;
	}

	/**
	 * number(16，2) 年度最高支付限额以内大病统筹支付 记载5万-16/18万大病统筹支付金额
	 * 
	 * @return
	 */
	public double getDbtc1() {
		return dbtc1;
	}

	/**
	 * number(16，2) 年度最高支付限额以内大病统筹支付 记载5万-16/18万大病统筹支付金额
	 * 
	 * @param dbtc1
	 */
	public void setDbtc1(double dbtc1) {
		this.dbtc1 = dbtc1;
	}

	/**
	 * number(16，2) 年度最高支付限额以内大病统筹个人支付 记载5万-16/18万大病个人支付金额
	 * 
	 * @return
	 */
	public double getDbgr1() {
		return dbgr1;
	}

	/**
	 * number(16，2) 年度最高支付限额以内大病统筹个人支付 记载5万-16/18万大病个人支付金额
	 * 
	 * @param dbgr1
	 */
	public void setDbgr1(double dbgr1) {
		this.dbgr1 = dbgr1;
	}

	/**
	 * number(16，2) 超过年度最高支付限额大病统筹支付 记载16-25万或18-30万大病统筹支付
	 * 
	 * @return
	 */
	public double getDbtc2() {
		return dbtc2;
	}

	/**
	 * number(16，2) 超过年度最高支付限额大病统筹支付 记载16-25万或18-30万大病统筹支付
	 * 
	 * @param dbtc2
	 */
	public void setDbtc2(double dbtc2) {
		this.dbtc2 = dbtc2;
	}

	/**
	 * number(16，2) 超过年度最高支付限额大病个人支付 记载16-25万或18-30万大病个人支付
	 * 
	 * @return
	 */
	public double getDbgr2() {
		return dbgr2;
	}

	/**
	 * number(16，2) 超过年度最高支付限额大病个人支付 记载16-25万或18-30万大病个人支付
	 * 
	 * @param dbgr2
	 */
	public void setDbgr2(double dbgr2) {
		this.dbgr2 = dbgr2;
	}

	/**
	 * number(16,2) 超过最高限额部分记载最终的超封顶线金额
	 * 
	 * @return
	 */
	public double getHjcgf() {
		return hjcgf;
	}

	/**
	 * number(16,2) 超过最高限额部分记载最终的超封顶线金额
	 * 
	 * @param hjcgf
	 */
	public void setHjcgf(double hjcgf) {
		this.hjcgf = hjcgf;
	}

	/**
	 * number(16,2) 个人总应付=总金额-基本统筹-大病统筹-公务员补助-大额医疗统筹
	 * 
	 * @return
	 */
	public double getGrzzf() {
		return grzzf;
	}

	/**
	 * number(16,2) 个人总应付=总金额-基本统筹-大病统筹-公务员补助-大额医疗统筹
	 * 
	 * @param grzzf
	 */
	public void setGrzzf(double grzzf) {
		this.grzzf = grzzf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

}
