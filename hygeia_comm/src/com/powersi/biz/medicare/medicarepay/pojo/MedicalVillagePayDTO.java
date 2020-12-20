package com.powersi.biz.medicare.medicarepay.pojo;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.ParamDTO;

/**
 * @author dannie
 *
 *类说明：乡镇对村卫生室拨付统计实体类
 *@date 2018年11月25日下午4:02:20
 */
public class MedicalVillagePayDTO extends ParamDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String akb020; // 医疗机构编号
	private String akb021; // 医疗机构名称
	private String daa027; // 拨付中心
	private String aae140; //险种
	private String aae030; //结算开始日期
	private String aae031; //结算结束日期
	private String bkc008; //拨付状态
	private String bizinfo;//业务数据
	private List<Map<String,Object>> lstBiz;//业务数据列表
	private String bke228;//结算号
	private String aaz262;//经办人工号
	private String aae011;//经办人姓名
	public String getAaz262() {
		return aaz262;
	}
	public void setAaz262(String aaz262) {
		this.aaz262 = aaz262;
	}
	public String getAae011() {
		return aae011;
	}
	public void setAae011(String aae011) {
		this.aae011 = aae011;
	}
	public String getAkb021() {
		return akb021;
	}
	public void setAkb021(String akb021) {
		this.akb021 = akb021;
	}
	public String getDaa027() {
		return daa027;
	}
	public void setDaa027(String daa027) {
		this.daa027 = daa027;
	}
	public String getBke228() {
		return bke228;
	}
	public void setBke228(String bke228) {
		this.bke228 = bke228;
	}
	public String getAkb020() {
		return akb020;
	}
	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}
	public List<Map<String, Object>> getLstBiz() {
		return lstBiz;
	}
	public void setLstBiz(List<Map<String, Object>> lstBiz) {
		this.lstBiz = lstBiz;
	}
	public String getAae140() {
		return aae140;
	}
	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}
	public String getAae030() {
		return aae030;
	}
	public void setAae030(String aae030) {
		this.aae030 = aae030;
	}
	public String getAae031() {
		return aae031;
	}
	public void setAae031(String aae031) {
		this.aae031 = aae031;
	}
	public String getBkc008() {
		return bkc008;
	}
	public void setBkc008(String bkc008) {
		this.bkc008 = bkc008;
	}
	public String getBizinfo() {
		return bizinfo;
	}
	public void setBizinfo(String bizinfo) {
		this.bizinfo = bizinfo;
	}
}
