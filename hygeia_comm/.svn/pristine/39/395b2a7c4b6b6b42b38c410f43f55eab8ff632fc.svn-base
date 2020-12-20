package com.powersi.biz.medicare.comm.pojo;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 待遇计算对象
 * 
 * @author 刘勇
 *
 */
@SuppressWarnings("rawtypes")
public class MediCalcDTO extends HashMap implements IMediCalc {

	private static final long serialVersionUID = 1L;

	/**
	 * 待遇计算对象
	 */
	public MediCalcDTO() {
		this.setActionCode("110104");
		this.getBiz();
		this.getBizscene();
		this.getFee();
		this.getFeestat();
		this.getPay();
		this.setPhase("1");
		this.setCacheFlag("0");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setPhase(String phase) {
		this.put("phase", phase);
	}

	@Override
	public String getPhase() {
		return (String) this.get("phase");
	}

	@Override
	public String getAka130() {
		return (String) this.getBizMap().get("aka130");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setAkb020(String akb020) {
		this.getBizMap().put("akb020", akb020);
	}

	@Override
	public String getAkb020() {
		return (String) this.getBizMap().get("akb020");
	}

	@Override
	public boolean isCalcing() {
		return "1".equals(this.get("calcingflag"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setCalcingFlag(String calcingFlag) {
		this.put("calcingflag", calcingFlag);
	}

	@Override
	public boolean isTryCalc() {
		return "0".equals(this.get("calcflag"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setCalcFlag(String calcFlag) {
		this.put("calcflag", calcFlag);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setKcd1id(String kcd1id) {
		this.getBizMap().put("kcd1id", kcd1id);
	}

	@Override
	public String getKcd1id() {
		return (String) this.getBizMap().get("kcd1id");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setAaz217(String aaz217) {
		this.getBizMap().put("aaz217", aaz217);
	}

	@Override
	public String getAaz217() {
		return (String) this.getBizMap().get("aaz217");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getBiz() {
		if (!this.getBizData().containsKey("kcd1")) {
			this.getBizData().put("kcd1", new Vector());
		}
		return (List) this.getBizData().get("kcd1");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setBiz(List biz) {
		this.getBiz().clear();
		this.getBiz().addAll(biz);
	}

	@Override
	public List getBizdtos() {
		return (List) this.get("kcd1dtos");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setBizdtos(List biz) {
		this.put("kcd1dtos", biz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map getBizMap() {
		if (this.getBiz().size() == 0) {
			this.getBiz().add(new HashMap());
		}
		return (Map) this.getBiz().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setBizMap(Map biz) {
		this.getBizMap().clear();
		this.getBizMap().putAll(biz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getFee() {
		if (!this.getBizData().containsKey("kcd2")) {
			this.getBizData().put("kcd2", new Vector());
		}
		return (List) this.getBizData().get("kcd2");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setFee(List fee) {
		this.getFee().clear();
		this.getFee().addAll(fee);
	}

	@Override
	public List getFeedtos() {
		return (List) this.get("kcd2dtos");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setFeedtos(List fee) {
		this.put("kcd2dtos", fee);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getPay() {
		if (!this.getBizData().containsKey("kcd7")) {
			this.getBizData().put("kcd7", new Vector());
		}
		return (List) this.getBizData().get("kcd7");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setPay(List pay) {
		this.getPay().clear();
		this.getPay().addAll(pay);
	}
	
	@Override
	public Map getSpecCumulate() {
		if (!this.containsKey("cumulate_data")) {
			this.setStaticData(new HashMap());
		}
		return (Map) this.get("cumulate_data");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setSpecCumulate(Map specCumulate) {
		this.put("cumulate_data", specCumulate);
	}

	@Override
	public List getPaydtos() {
		return (List) this.get("kcd7dtos");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setPaydtos(List pay) {
		this.put("kcd7dtos", pay);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setActionCode(String actionCode) {
		this.put("actionCode", actionCode);
	}

	@Override
	public String getActionCode() {
		return (String) this.get("actionCode");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setBizData(Map bizData) {
		this.put("biz_data", bizData);
	}

	@Override
	public Map getBizData() {
		if (!this.containsKey("biz_data")) {
			this.setBizData(new HashMap());
		}
		return (Map) this.get("biz_data");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setStaticData(Map staticData) {
		this.put("static_data", staticData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map getStaticData() {
		if (!this.containsKey("static_data")) {
			this.setStaticData(new HashMap());
		}
		if (this.containsKey("static_data")) {
			if (!((Map) this.get("static_data")).containsKey("last_balance")) {
				((Map) this.get("static_data")).put("last_balance", "0");
			}
		}
		return (Map) this.get("static_data");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setFrozenData(Map frozenData) {
		this.put("frozen_data", frozenData);
	}

	@Override
	public Map getFrozenData() {
		if (!this.containsKey("frozen_data")) {
			this.setFrozenData(new HashMap());
		}
		return (Map) this.get("frozen_data");
	}

	@Override
	public List getResult() {
		return this.getPay();
	}

	@Override
	public void setResult(List result) {
		this.setPay(result);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getBizscene() {
		if (!this.getBizData().containsKey("kce4")) {
			this.getBizData().put("kce4", new Vector());
		}
		return (List) this.getBizData().get("kce4");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setBizscene(List bizscene) {
		this.getBizscene().clear();
		this.getBizscene().addAll(bizscene);
	}

	@Override
	public List getBizscenedtos() {
		return (List) this.get("kce4dtos");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setBizscenedtos(List bizscene) {
		this.put("kce4dtos", bizscene);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getFeestat() {
		if (!this.getBizData().containsKey("kcd8")) {
			this.getBizData().put("kcd8", new Vector());
		}
		return (List) this.getBizData().get("kcd8");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setFeestat(List feestat) {
		this.getFeestat().clear();
		this.getFeestat().addAll(feestat);
	}

	@Override
	public List getFeestatdtos() {
		return (List) this.get("kcd8dtos");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setFeestatdtos(List feestat) {
		this.put("kcd8dtos", feestat);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setCacheFlag(String cacheFlag) {
		this.put("cacheFlag", cacheFlag);
	}

	@Override
	public String getCacheFlag() {
		return (String) this.get("cacheFlag");
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
