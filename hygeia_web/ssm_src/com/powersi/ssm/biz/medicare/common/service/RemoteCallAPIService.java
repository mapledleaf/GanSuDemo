package com.powersi.ssm.biz.medicare.common.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.comm.service.memory.MemoryDB;
import com.powersi.comm.service.memory.MemoryDBWrapper;
import com.powersi.comm.utils.NetClient;
import com.powersi.customer.pojo.Customer;
import com.powersi.hygeia.comm.service.RpcConfigReadService;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.common.util.RemoteCallAPIParam;
import com.powersi.ssm.biz.medicare.common.util.RemoteCallAPIResult;

/**
 * 
 * @author tangmin
 *
 */
public class RemoteCallAPIService implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final NetClient netclient = new NetClient();
	@Autowired
	private Properties configs;
	@Autowired
	private DateService dateService;
	@Autowired
	private MemoryDB memoryDB;
	@Autowired
	@Qualifier("memoryDBWrapper_core")
	private MemoryDBWrapper memoryDBWrapper_core;
	@Autowired
	@Qualifier("rpcConfigReadService")
	private RpcConfigReadService rpcConfigReadService;

	// 保存远程调用结算云 session key 失效时间字典维护
	private static final String HYGEIA_WEB_REMOTE_ESB_SESSION_ = "HYGEIA_WEB_REMOTE_ESB_SESSION_";

	// 远程调用对应url服务名
	private static final String HYGEIA_ESB = "HYGEIA_ESB";

	private static final String sys0001 = "sys0001";

	private static final String userid = "userid";

	private static final String password = "password";

	private String url = "http://127.0.0.1:8080/call_web.action";

	private int timeout = 120000;

	private int session_timeout = 60 * 60 * 24;

	/**
	 * 
	 * @param RemoteCallAPIParam
	 * @return
	 */
	public RemoteCallAPIResult execute(RemoteCallAPIParam remoteCallAPIParam) {
		this.loadingBasedOnTheInputParameters(remoteCallAPIParam);
		String targetUrl = this.getUrlBasedOnTheInputParameters(remoteCallAPIParam);
		if (StringUtils.isNotBlank(targetUrl)) {
			targetUrl = this.replaceServiceUrlWithParam(targetUrl, remoteCallAPIParam);
		}
		// 调用登陆 ,方法里面判断当前医院编码是否需要登陆
		this.execute_loginEsb(targetUrl, remoteCallAPIParam);

		RemoteCallAPIResult result = this.execute(targetUrl, remoteCallAPIParam);
		return result;
	}

	/**
	 * 获取调中心url
	 * 
	 * <pre>
	 * 1、默认取数据字典；
	 * 2、字典配置数据格式："http://172.18.100.11:7070/ylsygs"；
	 * 3、XML配置数据格式："http://127.0.0.1:9090/gdyb/ProcessAll?aab301=@{parameters.aab301}"
	 * 4、考虑到发布，采用判断是否调试模式；
	 * </pre>
	 * 
	 * @return "http://127.0.0.1:9090/gdyb/ProcessAll?aab301=@{parameters.aab301}"
	 */
	private String getUrlBasedOnTheInputParameters(RemoteCallAPIParam remoteCallAPIParam) {
		// 如果是本地调试模式
		if ("1".equals(this.configs.getProperty("pcloud_rpc.debug_mode"))) {
			if (StringUtils.isNotBlank(this.getUrl())) {
				return this.getUrl();
			}
		}
		String targetUrl = null;
		targetUrl = this.rpcConfigReadService.getConfigByServiceName(HYGEIA_ESB, remoteCallAPIParam.getAkb020(),
				remoteCallAPIParam.getAab301());
		if (StringUtils.isNotBlank(targetUrl)) {
			StringBuilder sb = new StringBuilder();
			sb.append("http://").append(targetUrl).append("/hygeia_esb/api/call_web.action");
			targetUrl = sb.toString();
		}
		if (StringUtils.isNotBlank(targetUrl)) {
			return targetUrl;
		}
		return this.getUrl();
	}

	/**
	 * <pre>
	 * 1、基于入参装载分库处理；
	 * 2、基于入参装载统筹区编码；
	 * </pre>
	 * 
	 * @param RemoteCallAPIParam
	 */
	private void loadingBasedOnTheInputParameters(RemoteCallAPIParam RemoteCallAPIParam) {
		String aaa027 = "", akb020 = "", aab301 = "";
		if (StringUtils.isBlank(aaa027)) {
			aaa027 = BizHelper.getAaa027();
		}
		if (StringUtils.isBlank(akb020)) {
			akb020 = BizHelper.getAkb020();
		}

		if (StringUtils.isBlank(aab301) && StringUtils.isNotBlank(aaa027)) {
			aab301 = aaa027;
		}
		if (StringUtils.isBlank(aab301)) {
			throw new RuntimeException("调结算云入参需包含统筹区编码(aaa027)!");
		}
		if (StringUtils.isBlank(akb020)) {
			throw new RuntimeException("调结算云入参需包含医院编码(akb020)!");
		}
		if (StringUtils.isNotBlank(aab301)) {
			RemoteCallAPIParam.setAab301(aab301);
		}
		if (StringUtils.isNotBlank(aaa027)) {
			RemoteCallAPIParam.setAaa027(aaa027);
		}
		if (StringUtils.isNotBlank(akb020)) {
			RemoteCallAPIParam.setAkb020(akb020);
		}
	}

	/**
	 * 远程调用
	 * 
	 * @param targetUrl
	 * @param RemoteCallAPIParam
	 * @return
	 */
	private RemoteCallAPIResult execute(String targetUrl, RemoteCallAPIParam RemoteCallAPIParam) {
		String lsCallXML = "";
		StringBuffer lsbReturnXML = new StringBuffer();
		long llTimeBegin = new Date().getTime();
		StringBuilder sbKeywords = new StringBuilder();
		try {
			lsCallXML = RemoteCallAPIParam.toXml();
			Map<String, String> mapHeader = new HashMap<String, String>();
			mapHeader.put("Content-Type", "text/xml; charset=UTF-8");
			RemoteCallAPIResult remoteCallAPIResult = null;
			this.netclient.doPost(targetUrl, mapHeader, lsCallXML, this.getTimeout(), lsbReturnXML);
			remoteCallAPIResult = new RemoteCallAPIResult(lsbReturnXML.toString());
			if (!remoteCallAPIResult.containsKey("return_code")) {
				throw new HygeiaException("应答xml必须包含return_code");
			}
			String return_code = remoteCallAPIResult.get("return_code").toString();
			if (return_code == null || "".equals(return_code.toString().trim())) {
				throw new RuntimeException("应答xml必须包含return_code的值");
			}
			int errorNo = Integer.parseInt(return_code.toString());
			if (errorNo < 0) {
				// 判断登陆是否失效
				if ("-9".equals(return_code)) {
					String session_key = HYGEIA_WEB_REMOTE_ESB_SESSION_ + RemoteCallAPIParam.getAkb020();
					memoryDB.delete(session_key);
					// 重新登陆调用
					this.execute_loginEsb(targetUrl, RemoteCallAPIParam);
					remoteCallAPIResult = this.execute(targetUrl, RemoteCallAPIParam);
				} else {
					Object msg_obj = remoteCallAPIResult.get("return_code_message").toString();
					if (msg_obj == null || "".equals(msg_obj.toString().trim())) {
						throw new RuntimeException("结算云执行发生错误，但未反馈错误信息，\\<return_code_message\\>为空或不存在");
					} else {
						throw new RuntimeException("结算执行发生错误！错误信息：" + msg_obj);
					}
				}
			}
			return remoteCallAPIResult;
		} catch (RuntimeException e) {
			this.logger.error(new StringBuilder("").append("访问结算云服务发生异常\r\nurl:").append(targetUrl)
					.append("\r\nFunctionID:").append(RemoteCallAPIParam.getFunctionID()).append("\r\n关键字:")
					.append(sbKeywords).append("\r\n记录时间:")
					.append(this.dateService.dateToString(new Date(), DateService._yyyyMMddHHmmss))
					.append("\r\n请求xml:\r\n").append(lsCallXML).append("\r\n异常信息:\r\n").append(e.getMessage())
					.toString());
			throw e;
		} catch (Throwable e) {
			this.logger.error(new StringBuilder("").append("访问结算云服务发生网络请求异常\r\nurl:").append(targetUrl)
					.append("\r\nFunctionID:").append(RemoteCallAPIParam.getFunctionID()).append("\r\n关键字:")
					.append(sbKeywords).append("\r\n记录时间:")
					.append(this.dateService.dateToString(new Date(), DateService._yyyyMMddHHmmss))
					.append("\r\n请求xml:\r\n").append(lsCallXML).append("\r\n异常信息:\r\n").append(e.getMessage())
					.toString());
			throw new RuntimeException(e);
		} finally {
			String record_time = this.dateService.dateToString(new Date(), DateService._yyyyMMddHHmmss);
			long exec_time = new Date().getTime() - llTimeBegin;
			this.logger.info(new StringBuilder("").append("访问结算云服务\r\nurl:").append(targetUrl).append("\r\nFunctionID:")
					.append(RemoteCallAPIParam.getFunctionID()).append("\r\n关键字:").append(sbKeywords)
					.append("\r\n应答时间:").append(exec_time).append("\r\n记录时间:").append(record_time).append("\r\n入参的长度:")
					.append(lsCallXML.length()).append("\r\n回参的长度:").append(lsbReturnXML.length())
					.append("\r\n请求xml:\r\n").append(lsCallXML).append("\r\n应答xml:\r\n").append(lsbReturnXML)
					.toString());
		}
	}

	/**
	 * 
	 * @param targetUrl
	 * @param RemoteCallAPIParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String replaceServiceUrlWithParam(String targetUrl, RemoteCallAPIParam RemoteCallAPIParam) {
		String retUrl = targetUrl;
		Pattern pattern = Pattern.compile("(?<=\\@\\{)(\\w|\\.)+", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(retUrl);
		String nodeName = null, dataStr = null, dataKey = null;
		String[] nodes = null;
		Map mapData = null;
		while (matcher.find()) {
			nodeName = matcher.group(0);
			dataStr = "";
			if (StringUtils.isBlank(nodeName)) {
				continue;
			}
			nodes = nodeName.split("\\.");
			mapData = RemoteCallAPIParam;
			for (int i = 0; nodes != null && i < nodes.length - 1; i++) {
				mapData = (Map) mapData.get(nodes[i]);
			}
			dataKey = nodes[nodes.length - 1];
			if (mapData != null && mapData.containsKey(dataKey) && mapData.get(dataKey) != null) {
				dataStr = mapData.get(dataKey).toString();
			} else {
				dataStr = "";
			}
			retUrl = retUrl.replaceAll("@\\{" + nodeName + "\\}", dataStr);
		}
		return retUrl;
	}

	/**
	 * 远程调用结算云（esb）服务 进行登陆
	 * 
	 * @param akb020
	 *            医院编码
	 * @param param
	 *            请求参数
	 * @param type
	 *            云平台字典 BIZ_CALLBACK_TYPE 配置的代码值
	 * @return RemoteCallAPIResult
	 */
	@SuppressWarnings("unchecked")
	private void execute_loginEsb(String targetUrl, RemoteCallAPIParam param) {

		String session_key = HYGEIA_WEB_REMOTE_ESB_SESSION_ + param.getAkb020();
		// 判断是否还需登陆
		Object session_id = memoryDB.get(session_key);
		if (session_id != null) {
			param.setParameter("session_id", session_id.toString());
			return;
		}

		// 根据医院编码获取远程调用服务的账户密码
		RemoteCallAPIParam remoteCallLoginParam = new RemoteCallAPIParam();
		remoteCallLoginParam.setFuncitionID(sys0001);
		remoteCallLoginParam.setParameter(userid, this.getUserid(param));
		remoteCallLoginParam.setParameter(password, this.getPassword(param));
		RemoteCallAPIResult resultMap = this.execute(targetUrl, remoteCallLoginParam);

		if (resultMap == null || resultMap.isEmpty() || !resultMap.containsKey("session_id")) {
			logger.error("调用结算云进行登陆异常，返回结果集或session为空！医院编码为：" + param.getAkb020() + ",登陆信息为："
					+ remoteCallLoginParam.getPrameter("password") + ",返回结果集为：" + resultMap.toString());
		}

		param.put("session_id", resultMap.get("session_id"));

		// 保存到缓存，记录调用前置机session 默认一天
		memoryDB.set(session_key, this.getSession_timeout(), resultMap.get("session_id"));

	}

	/**
	 * 对接研发组取值
	 * 
	 * @param remoteCallParam
	 * @return
	 */
	private String getUserid(RemoteCallAPIParam remoteCallParam) {
		String acc_code = remoteCallParam.getAkb020().toUpperCase();
		return acc_code;
	}

	/**
	 * 对接研发组取值
	 * 
	 * @param remoteCallParam
	 * @return
	 */
	private String getPassword(RemoteCallAPIParam remoteCallParam) {
		String acc_code = this.getUserid(remoteCallParam);
		Customer customer = (Customer) this.memoryDBWrapper_core.getMemoryDB().getMapValue("MAP_CUSTOMER", acc_code);
		if (customer != null) {
			return customer.getPasswd();
		}
		return "";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getSession_timeout() {
		return session_timeout;
	}

	public void setSession_timeout(int session_timeout) {
		this.session_timeout = session_timeout;
	}
}
