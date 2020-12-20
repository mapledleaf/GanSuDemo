package com.powersi.ssm.biz.medicare.api.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.powersi.comm.service.memory.MemoryDBWrapper;
import com.powersi.comm.utils.NetClient;
import com.powersi.customer.pojo.Customer;
import com.powersi.hygeia.comm.service.RpcConfigReadService;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.ssm.biz.medicare.api.dto.APIRemoteCallParam;
import com.powersi.ssm.biz.medicare.api.dto.APIRemoteCallResult;
import com.powersi.ssm.biz.medicare.api.exception.APIHygeiaException;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

/**
 * 
 * @author 刘勇
 *
 */
public class APIRemoteCallService {

	private final NetClient netclient = new NetClient();
	private final Map<String, String> mapHeader = new HashMap<String, String>();
	private final String session_id_key = "session_id";
	private final String notify_msg_key = "notify_msg";
	private final String HYGEIA_ESB = "HYGEIA_ESB";
	private String url = null;
	private int timeout = 120000;
	@Autowired
	@Qualifier("memoryDBWrapper_core")
	private MemoryDBWrapper memoryDBWrapper_core;
	@Autowired
	@Qualifier("rpcConfigReadService")
	private RpcConfigReadService rpcConfigReadService;

	/**
	 * 
	 * @param remoteCallParam
	 * @return
	 */
	public APIRemoteCallResult execute(APIRemoteCallParam remoteCallParam) {
		this.loadBaseInfo(remoteCallParam);
		String targetUrl = this.getTargetUrl(remoteCallParam);
		String session_id = remoteCallParam.getSession_id();
		if (StringUtils.isBlank(session_id)) {
			session_id = this.getSessionIdRemote(targetUrl, remoteCallParam);
			remoteCallParam.setSession_id(session_id);
		}
		return this.execute(targetUrl, remoteCallParam);
	}

	/**
	 * 
	 * @param remoteCallParam
	 * @return
	 */
	private String getTargetUrl(APIRemoteCallParam remoteCallParam) {
		String endpoint = this.rpcConfigReadService.getConfigByServiceName(HYGEIA_ESB, remoteCallParam.getAkb020(),
				remoteCallParam.getAab301());
		if (StringUtils.isNotBlank(endpoint)) {
			return new StringBuilder("http://").append(endpoint).append("/hygeia_esb/api/call_web.action").toString();
		}
		return this.getUrl();
	}

	/**
	 * 
	 * @param remoteCallParam
	 */
	private void loadBaseInfo(APIRemoteCallParam remoteCallParam) {
		mapHeader.put("Content-Type", "text/xml; charset=UTF-8");
		if (StringUtils.isBlank(remoteCallParam.getAkb020())) {
			remoteCallParam.setAkb020(BizHelper.getAkb020());
		}
		if (StringUtils.isBlank(remoteCallParam.getAae011())) {
			remoteCallParam.setAae011(BizHelper.getLoginUser());
		}
		if (StringUtils.isBlank(remoteCallParam.getAae036())) {
			remoteCallParam.setAae036(DateFunc.dateToString(new Date(), "yyyyMMdd"));
		}
		if (StringUtils.isBlank(remoteCallParam.getAaa027())) {
			remoteCallParam.setAaa027(BizHelper.getAaa027());
		}
		if (StringUtils.isBlank(remoteCallParam.getAab301())) {
			remoteCallParam.setAab301(remoteCallParam.getAaa027());
		}
		if (StringUtils.isBlank(remoteCallParam.getTrans_id())) {
			String trans_id = UUID.randomUUID().toString().toLowerCase();
			remoteCallParam.setTrans_id(trans_id);
		}
		// 强制注入 inte_mode
		remoteCallParam.setValue("inte_mode", "*^_^*");
	}

	/**
	 * 
	 * @param targetUrl
	 * @param remoteCallParam
	 * @return
	 */
	private String getSessionIdRemote(String targetUrl, APIRemoteCallParam remoteCallParam) {
		APIRemoteCallParam aPIRemoteCallParam = new APIRemoteCallParam();
		this.loadBaseInfo(aPIRemoteCallParam);
		aPIRemoteCallParam.setFunction_id(APIRemoteCallParam.getSys0001Key());
		aPIRemoteCallParam.setValue(APIRemoteCallParam.getUseridKey(), this.getUserid(remoteCallParam));
		aPIRemoteCallParam.setValue(APIRemoteCallParam.getPasswordKey(), this.getPassword(remoteCallParam));
		String lsCallXML = aPIRemoteCallParam.toXml();
		StringBuffer lsbReturnXML = new StringBuffer();
		netclient.doPost(targetUrl, mapHeader, lsCallXML, this.getTimeout(), lsbReturnXML);
		APIRemoteCallResult aPIRemoteCallResult = new APIRemoteCallResult(lsbReturnXML.toString());
		if (!"1".equals(aPIRemoteCallResult.getReturn_code())) {
			throw new APIHygeiaException("API登录：" + aPIRemoteCallResult.getReturn_code_message());
		}
		String session_id = aPIRemoteCallResult.getValue(session_id_key);
		String notify_msg = aPIRemoteCallResult.getValue(notify_msg_key);
		if (StringUtils.isNotBlank(notify_msg)) {
			// 对接web端提示信息展示_预留_20180902_刘勇
			// ......
		}
		return session_id;
	}

	/**
	 * 
	 * @param targetUrl
	 * @param remoteCallParam
	 * @return
	 */
	private APIRemoteCallResult execute(String targetUrl, APIRemoteCallParam remoteCallParam) {
		String lsCallXML = remoteCallParam.toXml();
		StringBuffer lsbReturnXML = new StringBuffer();
		netclient.doPost(targetUrl, mapHeader, lsCallXML, this.getTimeout(), lsbReturnXML);
		APIRemoteCallResult remoteCallResult = new APIRemoteCallResult(lsbReturnXML.toString());
		if ("-9".equals(remoteCallResult.getReturn_code())) {
			String session_id = this.getSessionIdRemote(targetUrl, remoteCallParam);
			remoteCallParam.setSession_id(session_id);
			remoteCallParam.setValue("systemcurrenttimemillis", String.valueOf(System.currentTimeMillis()));
			lsCallXML = remoteCallParam.toXml();
			lsbReturnXML = new StringBuffer();
			netclient.doPost(targetUrl, mapHeader, lsCallXML, this.getTimeout(), lsbReturnXML);
			remoteCallResult = new APIRemoteCallResult(lsbReturnXML.toString());
		}
		if (!"1".equals(remoteCallResult.getReturn_code())) {
			throw new APIHygeiaException("API执行：" + remoteCallResult.getReturn_code_message());
		}
		return remoteCallResult;
	}

	/**
	 * 对接研发组取值
	 * 
	 * @param remoteCallParam
	 * @return
	 */
	private String getUserid(APIRemoteCallParam remoteCallParam) {
		String acc_code = remoteCallParam.getAkb020().toUpperCase();
		return acc_code;
	}

	/**
	 * 对接研发组取值
	 * 
	 * @param remoteCallParam
	 * @return
	 */
	private String getPassword(APIRemoteCallParam remoteCallParam) {
		String acc_code = this.getUserid(remoteCallParam);
		Customer customer = (Customer) this.memoryDBWrapper_core.getMemoryDB().getMapValue("MAP_CUSTOMER", acc_code);
		if (customer != null) {
			return customer.getPasswd();
		}
		return "";
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTimeout() {
		return this.timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
