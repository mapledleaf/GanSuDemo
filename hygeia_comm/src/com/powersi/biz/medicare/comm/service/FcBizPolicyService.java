package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;

/**
 * 
 * @author  chenliang
 *
 */
public interface FcBizPolicyService extends Serializable {

	 
	public String getBizPolicyValue(String policyCode);

 
	public String getBizPolicyValue(String policyCode, char CenterOrHosp);
 
	public String getBizPolicyValue(String policyCode, char CenterOrHosp, String defaultValue);
 
	public String getBizPolicyValue(String CenterId, String policyCode, char CenterOrHosp, String defaultValue);
 
	public String getBizPolicyValue(String policyCode, String defaultPolicyCode);

 
	public String getBizPolicyValue(String policyCode, String defaultPolicyCode, String defaultValue);

}
