package com.powersi.biz.medicare.comm.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.comm.service.memory.MemoryDB;

/**
 * 
 * @author cl
 *
 */
@Service
public class FcBizPolicyServiceImpl implements FcBizPolicyService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MemoryDB memoryDB;

	 

	@Override
	public String getBizPolicyValue(String policyCode) {
		return this.getBizPolicyValue(policyCode, '1');
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getBizPolicyValue(String policyCode, char CenterOrHosp) {
		Map mMap = new HashMap();
		mMap = this.memoryDB.getMap("MAP_BIZ_POLICY");
		if (!this.memoryDB.existKey("MAP_BIZ_POLICY")) {
			throw new RuntimeException("业务参数码表数据中没有维护政策数据，请维护并刷新码表数据(RefreshBizPolicy)");
		}
		String policyCode_new = "0" + CenterOrHosp + policyCode;
		if (!mMap.containsKey(policyCode_new)) {
			throw new RuntimeException("业务参数码表数据中没有维护'" + (CenterOrHosp == '1' ? "中心" : "医院") + policyCode
					+ "'的数据，请维护并刷新码表数据(RefreshBizPolicy)");
		}
		return mMap.get(policyCode_new).toString();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getBizPolicyValue(String policyCode, char CenterOrHosp, String defaultValue) {
		Map mMap = new HashMap();
		mMap = this.memoryDB.getMap("MAP_BIZ_POLICY");
		String policyCode_new = "0" + CenterOrHosp + policyCode;
		if (!mMap.containsKey(policyCode_new)) {
			return defaultValue;
		}
		return mMap.get(policyCode_new).toString();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getBizPolicyValue(String CenterId, String policyCode, char CenterOrHosp, String defaultValue) {
		Map mMap = new HashMap();
		mMap = this.memoryDB.getMap("MAP_BIZ_POLICY");
		if (CenterId.trim().equals(""))
			CenterId = "0";
		String policyCode_new = CenterId + CenterOrHosp + policyCode;
		if (!mMap.containsKey(policyCode_new)) {
			if (!CenterId.equals("0")) {
				policyCode_new = "0" + CenterOrHosp + policyCode;
				if (!mMap.containsKey(policyCode_new)) {
					return defaultValue;
				}
			} else {
				return defaultValue;
			}
		}
		return mMap.get(policyCode_new).toString();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getBizPolicyValue(String policyCode, String defaultPolicyCode) {
		Map mMap = new HashMap();
		mMap = this.memoryDB.getMap("MAP_BIZ_POLICY");
		String policyCode_new = "0" + "1" + policyCode;
		if (mMap.containsKey(policyCode_new)) {
			return mMap.get(policyCode_new).toString();
		} else {
			policyCode_new = "1" + defaultPolicyCode;
			if (mMap.containsKey(policyCode_new)) {
				return mMap.get(policyCode_new).toString();
			} else {
				throw new RuntimeException("业务参数码表数据中没有维护中心'" + policyCode + "或" + defaultPolicyCode
						+ "'的数据，请维护并刷新码表数据(RefreshBizPolicy)");
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getBizPolicyValue(String policyCode, String defaultPolicyCode, String defaultValue) {
		Map mMap = new HashMap();
		mMap = this.memoryDB.getMap("MAP_BIZ_POLICY");
		String policyCode_new = "0" + "1" + policyCode;
		if (mMap.containsKey(policyCode_new)) {
			return getBizPolicyValue(policyCode_new);
		} else {
			policyCode_new = "0" + "1" + defaultPolicyCode;
			if (mMap.containsKey(policyCode_new)) {
				return getBizPolicyValue(policyCode_new);
			} else {
				return defaultValue;
			}
		}
	}

}
