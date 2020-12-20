package com.powersi.biz.medicare.outland.pojo;

import java.util.List;
import java.util.Map;

import com.powersi.comm.bean.BaseBean;

public class OutDeclDTO extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String sign;
	private String transid;
	private String aab299;
	private String yab600;
	private String yzz060;
	private String yzz061;
	private String akb026;
	private String akb020;
	private String akb021;
	private String startrow;
	private String aac002;
	private String ykc700;

	private String aaa027;
	private String bae007;
	private String akc194;
	private String otransid;
	private String bae008;
	private String akc264;
	private String ake149;
	
	private String yearMonth;
	private String confirmflag;
	
	@SuppressWarnings("rawtypes")
	private List<Map> detailList;
	
	
	@SuppressWarnings("rawtypes")
	public List<Map> getDetailList() {
		return detailList;
	}
	
	@SuppressWarnings("rawtypes")
	public void setDetailList(List<Map> detailList) {
		this.detailList = detailList;
	}
	public String getConfirmflag() {
		return confirmflag;
	}
	public void setConfirmflag(String confirmflag) {
		this.confirmflag = confirmflag;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTransid() {
		return transid;
	}
	public void setTransid(String transid) {
		this.transid = transid;
	}
	public String getAab299() {
		return aab299;
	}
	public void setAab299(String aab299) {
		this.aab299 = aab299;
	}
	public String getYab600() {
		return yab600;
	}
	public void setYab600(String yab600) {
		this.yab600 = yab600;
	}
	public String getYzz060() {
		return yzz060;
	}
	public void setYzz060(String yzz060) {
		this.yzz060 = yzz060;
	}
	public String getYzz061() {
		return yzz061;
	}
	public void setYzz061(String yzz061) {
		this.yzz061 = yzz061;
	}
	public String getAkb026() {
		return akb026;
	}
	public void setAkb026(String akb026) {
		this.akb026 = akb026;
	}
	
	public String getAkb020() {
		return akb020;
	}
	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}
	public String getAkb021() {
		return akb021;
	}
	public void setAkb021(String akb021) {
		this.akb021 = akb021;
	}
	public String getStartrow() {
		return startrow;
	}
	public void setStartrow(String startrow) {
		this.startrow = startrow;
	}
	public String getAac002() {
		return aac002;
	}
	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}
	public String getYkc700() {
		return ykc700;
	}
	public void setYkc700(String ykc700) {
		this.ykc700 = ykc700;
	}
	public String getAaa027() {
		return aaa027;
	}
	public void setAaa027(String aaa027) {
		this.aaa027 = aaa027;
	}
	public String getBae007() {
		return bae007;
	}
	public void setBae007(String bae007) {
		this.bae007 = bae007;
	}
	public String getAkc194() {
		return akc194;
	}
	public void setAkc194(String akc194) {
		this.akc194 = akc194;
	}
	public String getOtransid() {
		return otransid;
	}
	public void setOtransid(String otransid) {
		this.otransid = otransid;
	}
	public String getBae008() {
		return bae008;
	}
	public void setBae008(String bae008) {
		this.bae008 = bae008;
	}
	public String getAkc264() {
		return akc264;
	}
	public void setAkc264(String akc264) {
		this.akc264 = akc264;
	}
	public String getAke149() {
		return ake149;
	}
	public void setAke149(String ake149) {
		this.ake149 = ake149;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	
	
	
}
