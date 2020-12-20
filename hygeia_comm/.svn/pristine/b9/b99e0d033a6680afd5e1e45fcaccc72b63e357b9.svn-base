package com.powersi.biz.medicare.personalizationinter.pojo;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 就诊登记信息
 * 
 * @author 刘勇
 *
 */
public class JA implements Serializable {

	private static final long serialVersionUID = 1L;
	// 字段名 字段类型 字段说明 校验规则
	private String jzjlh;// varchar2 (20) 就诊记录号
	private String dwshbzh;// varchar2 (10) 单位社会保障号
	private String dwmc;// varchar2 (60) 单位名称
	private String grshbzh;// varchar2 (20) 个人社会保障号
	private String zgxm;// varchar2 (50) 职工姓名
	private String zgxb;// varchar2 (6) 职工性别
	private String yljsrq;// varchar2(6) 医疗结算人群 1职工 2居民 3离休
	private String zglb;// varchar2 (6) 职工类别 1—在职，2—退休，3—离休
	private String jbrylb;// varchar2(6) 居保人员类别 1普通居民 2大学生 3新生儿
	// 基本医疗待遇享受标志 0 不可享受1可享受记载本次结算基本医疗待遇享受标志如果是
	private String jbyldyxsbz;// varchar2(6)
	private String deyldyxsbz;// varchar2(6) 大额医疗待遇享受标志 0 不可享受 1可享受
	private String gwybz;// varchar2(6) 公务员标志 0 非公务员 1公务员
	private String lxrybz;// varchar2(6) 离休人员标志 0 非离休人员 1离休人员
	private String zyh;// varchar2 (8) 住院号
	private Date ryrq;// datetime not null 入院日期
	private String ryzd;// varchar2 (20) 入院诊断 住院暂为1，门诊见门诊病种表
	private String mzzybz;// varchar2 (1) 门诊住院标志 1—门诊，2—住院 ，3-普通门诊
	private String yybh;// varchar2 (6) 医院编号
	private String zyks;// varchar2 (15) 住院科室
	private String zycwh;// varchar2 (40) 住院床位号

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
	 * varchar2 (10) 单位社会保障号
	 * 
	 * @return
	 */
	public String getDwshbzh() {
		return dwshbzh;
	}

	/**
	 * varchar2 (10) 单位社会保障号
	 * 
	 * @param dwshbzh
	 */
	public void setDwshbzh(String dwshbzh) {
		this.dwshbzh = dwshbzh;
	}

	/**
	 * varchar2 (60) 单位名称
	 * 
	 * @return
	 */
	public String getDwmc() {
		return dwmc;
	}

	/**
	 * varchar2 (60) 单位名称
	 * 
	 * @param dwmc
	 */
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

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
	 * varchar2 (6) 职工性别
	 * 
	 * @return
	 */
	public String getZgxb() {
		return zgxb;
	}

	/**
	 * varchar2 (6) 职工性别
	 * 
	 * @param zgxb
	 */
	public void setZgxb(String zgxb) {
		this.zgxb = zgxb;
	}

	/**
	 * varchar2(6) 医疗结算人群 1职工 2居民 3离休
	 * 
	 * @return
	 */
	public String getYljsrq() {
		return yljsrq;
	}

	/**
	 * varchar2(6) 医疗结算人群 1职工 2居民 3离休
	 * 
	 * @param yljsrq
	 */
	public void setYljsrq(String yljsrq) {
		this.yljsrq = yljsrq;
	}

	/**
	 * varchar2 (6) 职工类别 1—在职，2—退休，3—离休
	 * 
	 * @return
	 */
	public String getZglb() {
		return zglb;
	}

	/**
	 * varchar2 (6) 职工类别 1—在职，2—退休，3—离休
	 * 
	 * @param zglb
	 */
	public void setZglb(String zglb) {
		this.zglb = zglb;
	}

	/**
	 * varchar2(6) 居保人员类别 1普通居民 2大学生 3新生儿
	 * 
	 * @return
	 */
	public String getJbrylb() {
		return jbrylb;
	}

	/**
	 * varchar2(6) 居保人员类别 1普通居民 2大学生 3新生儿
	 * 
	 * @param jbrylb
	 */
	public void setJbrylb(String jbrylb) {
		this.jbrylb = jbrylb;
	}

	/**
	 * 基本医疗待遇享受标志 0 不可享受1可享受记载本次结算基本医疗待遇享受标志如果是
	 * 
	 * @return varchar2(6)
	 */
	public String getJbyldyxsbz() {
		return jbyldyxsbz;
	}

	/**
	 * 基本医疗待遇享受标志 0 不可享受1可享受记载本次结算基本医疗待遇享受标志如果是
	 * 
	 * @param jbyldyxsbz
	 *            varchar2(6)
	 */
	public void setJbyldyxsbz(String jbyldyxsbz) {
		this.jbyldyxsbz = jbyldyxsbz;
	}

	/**
	 * varchar2(6) 大额医疗待遇享受标志 0 不可享受 1可享受
	 * 
	 * @return
	 */
	public String getDeyldyxsbz() {
		return deyldyxsbz;
	}

	/**
	 * varchar2(6) 大额医疗待遇享受标志 0 不可享受 1可享受
	 * 
	 * @param deyldyxsbz
	 */
	public void setDeyldyxsbz(String deyldyxsbz) {
		this.deyldyxsbz = deyldyxsbz;
	}

	/**
	 * varchar2(6) 公务员标志 0 非公务员 1公务员
	 * 
	 * @return
	 */
	public String getGwybz() {
		return gwybz;
	}

	/**
	 * varchar2(6) 公务员标志 0 非公务员 1公务员
	 * 
	 * @param gwybz
	 */
	public void setGwybz(String gwybz) {
		this.gwybz = gwybz;
	}

	public String getLxrybz() {
		return lxrybz;
	}

	public void setLxrybz(String lxrybz) {
		this.lxrybz = lxrybz;
	}

	/**
	 * varchar2 (8) 住院号
	 * 
	 * @return
	 */
	public String getZyh() {
		return zyh;
	}

	/**
	 * varchar2 (8) 住院号
	 * 
	 * @param zyh
	 */
	public void setZyh(String zyh) {
		this.zyh = zyh;
	}

	/**
	 * datetime not null 入院日期
	 * 
	 * @return "yyyy-MM-dd HH:mm:ss"
	 */
	public Date getRyrq() {
		return ryrq;
	}

	/**
	 * datetime not null 入院日期
	 * 
	 * @param ryrq
	 *            "yyyy-MM-dd HH:mm:ss"
	 */
	public void setRyrq(Date ryrq) {
		this.ryrq = ryrq;
	}

	/**
	 * varchar2 (20) 入院诊断 住院暂为1，门诊见门诊病种表
	 * 
	 * @return
	 */
	public String getRyzd() {
		return ryzd;
	}

	/**
	 * varchar2 (20) 入院诊断 住院暂为1，门诊见门诊病种表
	 * 
	 * @param ryzd
	 */
	public void setRyzd(String ryzd) {
		this.ryzd = ryzd;
	}

	/**
	 * varchar2 (1) 门诊住院标志 1—门诊，2—住院 ，3-普通门诊
	 * 
	 * @return
	 */
	public String getMzzybz() {
		return mzzybz;
	}

	/**
	 * varchar2 (1) 门诊住院标志 1—门诊，2—住院 ，3-普通门诊
	 * 
	 * @param mzzybz
	 */
	public void setMzzybz(String mzzybz) {
		this.mzzybz = mzzybz;
	}

	/**
	 * varchar2 (6) 医院编号
	 * 
	 * @return
	 */
	public String getYybh() {
		return yybh;
	}

	/**
	 * varchar2 (6) 医院编号
	 * 
	 * @param yybh
	 */
	public void setYybh(String yybh) {
		this.yybh = yybh;
	}

	/**
	 * varchar2 (15) 住院科室
	 * 
	 * @return
	 */
	public String getZyks() {
		return zyks;
	}

	/**
	 * varchar2 (15) 住院科室
	 * 
	 * @param zyks
	 */
	public void setZyks(String zyks) {
		this.zyks = zyks;
	}

	/**
	 * varchar2 (40) 住院床位号
	 * 
	 * @return
	 */
	public String getZycwh() {
		return zycwh;
	}

	/**
	 * varchar2 (40) 住院床位号
	 * 
	 * @param zycwh
	 */
	public void setZycwh(String zycwh) {
		this.zycwh = zycwh;
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
