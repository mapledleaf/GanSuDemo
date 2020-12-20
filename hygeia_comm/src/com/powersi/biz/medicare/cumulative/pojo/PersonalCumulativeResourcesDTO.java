package com.powersi.biz.medicare.cumulative.pojo;

import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.powersi.biz.medicare.comm.pojo.IPOJO;
import com.powersi.biz.medicare.query.dto.KC21;
import com.powersi.biz.medicare.query.dto.KC22;
import com.powersi.biz.medicare.query.dto.KC27;
import com.powersi.biz.medicare.query.dto.KC28;
import com.powersi.biz.medicare.query.dto.KCD1;
import com.powersi.biz.medicare.query.dto.KCD2;
import com.powersi.biz.medicare.query.dto.KCD7;
import com.powersi.biz.medicare.query.dto.KCD8;
import com.powersi.biz.medicare.query.dto.KCE4;
import com.powersi.biz.medicare.query.dto.KCE5;

/**
 * 个人医疗累计计算资源
 * 
 * @author zhos
 *
 */
@SuppressWarnings("rawtypes")
public class PersonalCumulativeResourcesDTO extends HashMap implements IPOJO {

	private static final long serialVersionUID = 1L;

	/**
	 * 默认收费、非审核扣减
	 */
	public PersonalCumulativeResourcesDTO() {
		this.setRefundFlag("1");
		this.setDeduction("0");
	}

	/**
	 * 
	 * @return 是否审核扣减
	 */
	public boolean isDeduction() {
		return "1".equals(this.getDeduction());
	}

	/**
	 * 
	 * @return 审核扣减标识 1是 0否
	 */
	public String getDeduction() {
		return (String) this.get("Deduction");
	}

	/**
	 * 
	 * @param deductionFlag
	 *            审核扣减标识 1是 0否
	 */
	@SuppressWarnings("unchecked")
	public void setDeduction(String deductionFlag) {
		this.put("Deduction", deductionFlag);
	}

	/**
	 * 
	 * @return 是否退费
	 */
	public boolean isRefund() {
		return "-1".equals(this.getRefundFlag());
	}

	/**
	 * 
	 * @return 退费标识 1收费 -1退费
	 */
	public String getRefundFlag() {
		return (String) this.get("RefundFlag");
	}

	/**
	 * 
	 * @param flag
	 *            退费标识 1收费 -1退费
	 */
	@SuppressWarnings("unchecked")
	public void setRefundFlag(String flag) {
		this.put("RefundFlag", flag);
	}
	
	/**
	 * 
	 * @return 个人医疗累计要素集合
	 */
	public PersonalCumulativeElementDTO getPersonalCumulativeElementDTO() {
		return (PersonalCumulativeElementDTO) this.get("personalCumulativeElementDTO");
	}

	/**
	 * 
	 * @param personalCumulativeElementDTO
	 *            个人医疗累计要素集合
	 */
	@SuppressWarnings("unchecked")
	public void setPersonalCumulativeElementDTO(PersonalCumulativeElementDTO personalCumulativeElementDTO) {
		this.put("personalCumulativeElementDTO", personalCumulativeElementDTO);
	}


	/**
	 * 
	 * @return
	 */
	public KCD1 getKcd1() {
		return (KCD1) this.get("kcd1");
	}

	/**
	 * 
	 * @param kcd1
	 */
	@SuppressWarnings("unchecked")
	public void setKcd1(KCD1 kcd1) {
		this.put("kcd1", kcd1);
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<KCD2> getKcd2() {
		return (List<KCD2>) this.get("kcd2");
	}

	/**
	 * 
	 * @param kcd2
	 */
	@SuppressWarnings("unchecked")
	public void setKcd2(List<KCD2> kcd2) {
		this.put("kcd2", kcd2);
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<KCD7> getKcd7() {
		return (List<KCD7>) this.get("kcd7");
	}

	/**
	 * 
	 * @param kcd7
	 */
	@SuppressWarnings("unchecked")
	public void setKcd7(List<KCD7> kcd7) {
		this.put("kcd7", kcd7);
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<KCD8> getKcd8() {
		return (List<KCD8>) this.get("kcd8");
	}

	/**
	 * 
	 * @param kcd8
	 */
	@SuppressWarnings("unchecked")
	public void setKcd8(List<KCD8> kcd8) {
		this.put("kcd8", kcd8);
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<KCE4> getKce4() {
		return (List<KCE4>) this.get("kce4");
	}

	/**
	 * 
	 * @param kce4
	 */
	@SuppressWarnings("unchecked")
	public void setKce4(List<KCE4> kce4) {
		this.put("kce4", kce4);
	}

	/**
	 * 
	 * @return
	 */
	public KC21 getKc21() {
		return (KC21) this.get("kc21");
	}

	/**
	 * 
	 * @param kc21
	 */
	@SuppressWarnings("unchecked")
	public void setKc21(KC21 kc21) {
		this.put("kc21", kc21);
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<KC22> getKc22() {
		return (List<KC22>) this.get("kc22");
	}

	/**
	 * 
	 * @param kc22
	 */
	@SuppressWarnings("unchecked")
	public void setKc22(List<KC22> kc22) {
		this.put("kc22", kc22);
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<KC27> getKc27() {
		return (List<KC27>) this.get("kc27");
	}

	/**
	 * 
	 * @param kc27
	 */
	@SuppressWarnings("unchecked")
	public void setKc27(List<KC27> kc27) {
		this.put("kc27", kc27);
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<KC28> getKc28() {
		return (List<KC28>) this.get("kc28");
	}

	/**
	 * 
	 * @param kc28
	 */
	@SuppressWarnings("unchecked")
	public void setKc28(List<KC28> kc28) {
		this.put("kc28", kc28);
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<KCE5> getKce5() {
		return (List<KCE5>) this.get("kce5");
	}

	/**
	 * 
	 * @param kce5
	 */
	@SuppressWarnings("unchecked")
	public void setKce5(List<KCE5> kce5) {
		this.put("kce5", kce5);
	}

	@Override
	public String toString() {
		return this.toJson();
	}

	@Override
	public String toJson() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}

	@Override
	public boolean compareTo(Object obj) {
		return this.toString().equals(obj.toString());
	}

}
