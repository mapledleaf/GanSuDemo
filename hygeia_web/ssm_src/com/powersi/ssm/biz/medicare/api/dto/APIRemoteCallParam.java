package com.powersi.ssm.biz.medicare.api.dto;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powersi.comm.utils.XMLUtils;

/**
 * 
 * @author 刘勇
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class APIRemoteCallParam extends LinkedHashMap {

	private static final long serialVersionUID = 1L;
	private final String function_id_key = "function_id";
	private final String trans_id_key = "trans_id";
	private final String session_id_key = "session_id";
	private final String ic_reg_permit_key = "ic_reg_permit";
	private final String ic_pay_permit_key = "ic_pay_permit";
	private final String sign_key = "sign";
	private final String akb020_key = "akb020";
	private final String aaa027_key = "aaa027";
	private final String aab301_key = "aab301";
	private final String aac001_key = "aac001";
	private final String aac002_key = "aac002";
	private final String acc_subcode_key = "acc_subcode";
	private final String bka895_key = "bka895";
	private final String bka896_key = "bka896";
	private final String aae011_key = "aae011";
	private final String aae036_key = "aae036";
	private static final String sys0001_key = "sys0001";
	private static final String userid_key = "userid";
	private static final String password_key = "password";
	private String formate = "yb2";
	private String rootName = "program";
	private String paramXML = "";

	/**
	 * 
	 */
	public APIRemoteCallParam() {
		this.setFunction_id("");
		this.setTrans_id("");
	}

	/**
	 * 子账户编号
	 * 
	 * @param acc_subcode
	 */
	public void setAcc_subcode(String acc_subcode) {
		this.put(acc_subcode_key, acc_subcode);
	}

	/**
	 * 子账户编号
	 * 
	 * @return
	 */
	public String getAcc_subcode() {
		return (String) this.get(acc_subcode_key);
	}

	/**
	 * 功能号
	 * 
	 * @param function_id
	 */
	public void setFunction_id(String function_id) {
		this.put(function_id_key, function_id);
	}

	/**
	 * 功能号
	 * 
	 * @return
	 */
	public String getFunction_id() {
		return (String) this.get(function_id_key);
	}

	/**
	 * 交易流水号UUID
	 * 
	 * @param trans_id
	 */
	public void setTrans_id(String trans_id) {
		this.put(trans_id_key, trans_id);
	}

	/**
	 * 交易流水号UUID
	 * 
	 * @return
	 */
	public String getTrans_id() {
		return (String) this.get(trans_id_key);
	}

	/**
	 * 如果session_id失效，所有API请求将返回错误代码 -9 , 判断请求返回 -9 后，请立即执行登录，获取最新的session_id。
	 * 
	 * @param session_id
	 */
	public void setSession_id(String session_id) {
		this.put(session_id_key, session_id);
	}

	/**
	 * 如果session_id失效，所有API请求将返回错误代码 -9 , 判断请求返回 -9 后，请立即执行登录，获取最新的session_id。
	 * 
	 * @return
	 */
	public String getSession_id() {
		return (String) this.get(session_id_key);
	}

	/**
	 * 持卡就诊登记许可号
	 * 
	 * @param ic_reg_permit
	 */
	public void setIc_reg_permit(String ic_reg_permit) {
		this.put(ic_reg_permit_key, ic_reg_permit);
	}

	/**
	 * 持卡就诊登记许可号
	 * 
	 * @return
	 */
	public String getIc_reg_permit() {
		return (String) this.get(ic_reg_permit_key);
	}

	/**
	 * 持卡就诊结算许可号
	 * 
	 * @param ic_pay_permit
	 */
	public void setIc_pay_permit(String ic_pay_permit) {
		this.put(ic_pay_permit_key, ic_pay_permit);
	}

	/**
	 * 持卡就诊结算许可号
	 * 
	 * @return
	 */
	public String getIc_pay_permit() {
		return (String) this.get(ic_pay_permit_key);
	}

	/**
	 * 医疗机构编码
	 * 
	 * @return
	 */
	public String getAkb020() {
		return (String) this.get(akb020_key);
	}

	/**
	 * 医疗机构编码
	 * 
	 * @param akb020
	 */
	public void setAkb020(String akb020) {
		this.put(akb020_key, akb020);
	}

	/**
	 * 统筹区编码
	 * 
	 * @return
	 */
	public String getAaa027() {
		return (String) this.get(aaa027_key);
	}

	/**
	 * 统筹区编码
	 * 
	 * @param aaa027
	 */
	public void setAaa027(String aaa027) {
		this.put(aaa027_key, aaa027);
	}

	/**
	 * 参保人所属行政区划代码
	 * 
	 * @return
	 */
	public String getAab301() {
		return (String) this.get(aab301_key);
	}

	/**
	 * 参保人所属行政区划代码
	 * 
	 * @param aab301
	 */
	public void setAab301(String aab301) {
		this.put(aab301_key, aab301);
	}

	/**
	 * 个人电脑号
	 * 
	 * @return
	 */
	public String getAac001() {
		return (String) this.get(aac001_key);
	}

	/**
	 * 个人电脑号
	 * 
	 * @param aac001
	 */
	public void setAac001(String aac001) {
		this.put(aac001_key, aac001);
	}

	/**
	 * 社会保障号码
	 * 
	 * @return
	 */
	public String getAac002() {
		return (String) this.get(aac002_key);
	}

	/**
	 * 社会保障号码
	 * 
	 * @param aac002
	 */
	public void setAac002(String aac002) {
		this.put(aac002_key, aac002);
	}

	/**
	 * aac001电脑号；aac002社会保障号码；bka100社保卡号；aaz218就医登记号
	 * 
	 * @return 入参类型
	 */
	public String getBka895() {
		return (String) this.get(bka895_key);
	}

	/**
	 * aac001电脑号；aac002社会保障号码；bka100社保卡号；aaz218就医登记号
	 * 
	 * @param bka895
	 *            入参类型
	 */
	public void setBka895(String bka895) {
		this.put(bka895_key, bka895);
	}

	/**
	 * 入参值
	 * 
	 * @return
	 */
	public String getBka896() {
		return (String) this.get(bka896_key);
	}

	/**
	 * 入参值
	 * 
	 * @param bka896
	 */
	public void setBka896(String bka896) {
		this.put(bka896_key, bka896);
	}

	/**
	 * MD5签名
	 * 
	 * @return
	 */
	public String getSign() {
		return (String) this.get(sign_key);
	}

	/**
	 * MD5签名
	 * 
	 * @param sign
	 */
	public void setSign(String sign) {
		this.put(sign_key, sign);
	}

	/**
	 * 经办人
	 * 
	 * @return
	 */
	public String getAae011() {
		return (String) this.get(aae011_key);
	}

	/**
	 * 经办人
	 * 
	 * @param aae011
	 */
	public void setAae011(String aae011) {
		this.put(aae011_key, aae011);
	}

	/**
	 * 经办日期
	 * 
	 * @return
	 */
	public String getAae036() {
		return (String) this.get(aae036_key);
	}

	/**
	 * 经办日期
	 * 
	 * @param aae036
	 */
	public void setAae036(String aae036) {
		this.put(aae036_key, aae036);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void setValue(String key, String value) {
		this.put(key, value);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		Object valueobj = this.get(key);
		if (valueobj == null) {
			return "";
		}
		return (String) valueobj;
	}

	/**
	 * 
	 * @param listName
	 * @param listValue
	 */
	public void setList(String listName, List<Map> listValue) {
		this.put(listName, listValue);
	}

	/**
	 * 
	 * @param listName
	 * @return
	 */
	public List<Map> getList(String listName) {
		if (this.get(listName) instanceof List) {
			return (List<Map>) this.get(listName);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * Send XML
	 * 
	 * @return
	 */
	public String toXml() {
		String xml = XMLUtils.map2xml(this, this.rootName, this.formate);
		this.paramXML = xml;
		return xml;
	}

	/**
	 * Input XML
	 * 
	 * @return
	 */
	public String getParamXML() {
		if (StringUtils.isBlank(this.paramXML)) {
			this.toXml();
		}
		return this.paramXML;
	}

	/**
	 * "yb2"标准XML，"yb"中心端XML
	 * 
	 * @param formate
	 */
	public void setFormate(String formate) {
		this.formate = formate;
	}

	/**
	 * "Program" or "program"
	 * 
	 * @param rootName
	 */
	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	/**
	 * 请求登录功能号key
	 * 
	 * @return
	 */
	public static final String getSys0001Key() {
		return sys0001_key;
	}

	/**
	 * 帐号key
	 * 
	 * @return
	 */
	public static final String getUseridKey() {
		return userid_key;
	}

	/**
	 * 验证码key
	 * 
	 * @return
	 */
	public static final String getPasswordKey() {
		return password_key;
	}

}
