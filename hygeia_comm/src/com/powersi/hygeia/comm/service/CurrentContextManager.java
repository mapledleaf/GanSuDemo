package com.powersi.hygeia.comm.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

/**
 * 根据观察Mvc_ApiBiz启用了线程池所以此类慎用!
 * 
 * <pre>
 * 在用之前需要初始化!!!
 * </pre>
 * 
 * @author 刘勇
 *
 */
@Service
public class CurrentContextManager {

	private final ThreadLocal<String> threadLocal_trans_sc_id = new ThreadLocal<String>();// 交易ID
	private final ThreadLocal<String> threadLocal_serviceid = new ThreadLocal<String>();// function_id
	private final ThreadLocal<String> threadLocal_access_channel = new ThreadLocal<String>();// 接入渠道标识

	/**
	 * 接入渠道标识
	 * 
	 * @return
	 */
	public final String getAccess_channel() {
		return threadLocal_access_channel.get();
	}

	/**
	 * 接入渠道标识
	 * 
	 * @param access_channel
	 */
	public final void setAccess_channel(String access_channel) {
		threadLocal_access_channel.set(access_channel);
	}

	/**
	 * function_id
	 * 
	 * @return
	 */
	public final String getServiceid() {
		return threadLocal_serviceid.get();
	}

	/**
	 * function_id
	 * 
	 * @param serviceid
	 */
	public final void setServiceid(String serviceid) {
		threadLocal_serviceid.set(serviceid);
	}

	/**
	 * 交易ID
	 * 
	 * @return
	 */
	public final String getTrans_sc_id() {
		return threadLocal_trans_sc_id.get();
	}

	/**
	 * 交易ID
	 * 
	 * @param trans_sc_id
	 */
	public final void setTrans_sc_id(String trans_sc_id) {
		threadLocal_trans_sc_id.set(trans_sc_id);
	}

	public final String getUUID() {
		return "SID-" + UUID.randomUUID().toString().toUpperCase();
	}

}
