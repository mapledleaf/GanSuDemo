package com.powersi.hygeia.comm.service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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

import com.powersi.asyn.service.AsynService;
import com.powersi.biz.medicare.comm.service.MCCEBusniessService;
import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.comm.utils.NetClient;
import com.powersi.hygeia.comm.dto.RemoteCallParam;
import com.powersi.hygeia.comm.dto.RemoteCallResult;
import com.powersi.hygeia.etl.transname.TransNameService;
import com.powersi.hygeia.etl.transvalue.TransValueService;
import com.powersi.pcloud.dict.service.DictService;

/**
 * 
 * @author 李志钢
 *
 */
public class RemoteCallService implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final NetClient netclient = new NetClient();
	// 定义服务入口的url，例如:http://ip:port/xxx/xxx/processAll
	private String url = null;
	// 请求超时毫秒，可以通过xml配置
	private int timeout = 120 * 1000;
	// 1、nkjh内控稽核 2、gdyb广东医保
	private String direct = null;
	@Autowired
	private MCCEBusniessService mCCEBusniessService;
	@Autowired
	private DictService dictService;
	@Autowired
	private Properties configs;
	@Autowired
	private DateService dateService;
	@Autowired
	@Qualifier("asynService")
	private AsynService asynService;
	@Autowired
	private TransNameService transNameService;
	@Autowired
	private TransValueService transValueService;
	// hy 20170824 增加设置当前线程的马上要访问的中心编号，由中心编号-险种编号组成
	private final ThreadLocal<String> mapCurrentCenterID = new ThreadLocal<String>();
	@Autowired
	private CurrentContextManager currentContextManager;

	/**
	 * 
	 * @param zxbh
	 * @param xzbh
	 */
	public void setCenterID(String zxbh, String xzbh) {
		mapCurrentCenterID.set(zxbh + "-" + xzbh);
	}

	/**
	 * 
	 * @param remoteCallParam
	 * @return
	 */
	public RemoteCallResult execute(RemoteCallParam remoteCallParam) {
		this.loadingBasedOnTheInputParameters(remoteCallParam);
		String targetUrl = this.getUrlBasedOnTheInputParameters();
		String caa027 = remoteCallParam.getPrameter("caa027");
		this.setCenterID(caa027.substring(0, 4), caa027.substring(5, 7));
		if (StringUtils.isNotBlank(targetUrl)) {
			targetUrl = this.replaceServiceUrlWithParam(targetUrl, remoteCallParam);
		}
		return this.execute(targetUrl, remoteCallParam);
	}

	/**
	 * 需要业务冲正的远程调用
	 * 
	 * <pre>
	 * 1、不抛出业务逻辑异常；
	 * </pre>
	 * 
	 * @param remoteCallParam
	 * @return
	 */
	public RemoteCallResult executeSilence(RemoteCallParam remoteCallParam) {
		String targetUrl = null;
		try {
			this.loadingBasedOnTheInputParameters(remoteCallParam);
			String caa027 = remoteCallParam.getPrameter("caa027");
			this.setCenterID(caa027.substring(0, 4), caa027.substring(5, 7));
			targetUrl = this.getUrlBasedOnTheInputParameters();
			if (StringUtils.isNotBlank(targetUrl)) {
				targetUrl = this.replaceServiceUrlWithParam(targetUrl, remoteCallParam);
			}
		} catch (RuntimeException e) {
			this.logger.error(new StringBuilder("executeSilence ").append(e.getMessage())
					.append(this.getExceptionInfo(e)).toString());
		}
		String lsCallXML = "";
		StringBuffer lsbReturnXML = new StringBuffer();
		long llTimeBegin = new Date().getTime();
		StringBuilder sbKeywords = new StringBuilder();
		RemoteCallResult remoteCallResult = null;
		try {
			String aac001 = remoteCallParam.getPrameter("aac001");
			String aac002 = remoteCallParam.getPrameter("aac002");
			String bka100 = remoteCallParam.getPrameter("bka100");
			String aaz217 = remoteCallParam.getPrameter("aaz217");
			String keyword = remoteCallParam.getPrameter("keyword");
			sbKeywords.append("-").append(aac001 == null ? "" : aac001);
			sbKeywords.append("-").append(aac002 == null ? "" : aac002);
			sbKeywords.append("-").append(bka100 == null ? "" : bka100);
			sbKeywords.append("-").append(aaz217 == null ? "" : aaz217);
			sbKeywords.append("-").append(keyword == null ? "" : keyword);
			// hy 20180824
			transNameService.transName(remoteCallParam, mapCurrentCenterID.get(), 1);
			transValueService.transValue(remoteCallParam, mapCurrentCenterID.get(), 1);

			lsCallXML = remoteCallParam.toXml();
			Map<String, String> mapHeader = new HashMap<String, String>();
			mapHeader.put("Content-Type", "text/xml; charset=UTF-8");
			this.netclient.doPost(targetUrl, mapHeader, lsCallXML, this.getTimeout(), lsbReturnXML);
			remoteCallResult = new RemoteCallResult(lsbReturnXML.toString());
			// hy 20180824
			transNameService.transName(remoteCallResult, mapCurrentCenterID.get(), 2);
			transValueService.transValue(remoteCallResult, mapCurrentCenterID.get(), 2);
		} finally {
			String record_time = this.dateService.dateToString(new Date(), DateService._yyyyMMddHHmmss);
			long exec_time = new Date().getTime() - llTimeBegin;
			this.logger.info(new StringBuilder("executeSilence ").append("访问中心服务\r\nurl:").append(targetUrl)
					.append("\r\nFunctionID:").append(remoteCallParam.getFunctionID()).append("\r\n关键字:")
					.append(sbKeywords).append("\r\n应答时间:").append(exec_time).append("\r\n记录时间:").append(record_time)
					.append("\r\n入参的长度：").append(lsCallXML.length()).append("\r\n回参的长度：").append(lsbReturnXML.length())
					.append("\r\n请求xml:\r\n").append(lsCallXML).append("\r\n应答xml:\r\n").append(lsbReturnXML)
					.toString());
			String errorType = remoteCallResult.getErrorType();
			try {
				// 20180320 hy 添加对中api调用的打分机制，进行监控数据的采集
				asynService.addWork("centerApiMoniter", "sendApiCallInfo",
						new Object[] { remoteCallParam, "0".equals(errorType), record_time, exec_time });
			} catch (Exception e) {
				this.logger.error(e.getMessage());
			}
		}
		return remoteCallResult == null ? new RemoteCallResult("") : remoteCallResult;
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	private String getExceptionInfo(Throwable e) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(byteArrayOutputStream);
		e.printStackTrace(printStream);
		String lsReturn = new String(byteArrayOutputStream.toByteArray());
		printStream.close();
		try {
			byteArrayOutputStream.close();
		} catch (Throwable ex) {
			return StringUtils.isNotBlank(lsReturn) ? lsReturn : ex.getMessage();
		}
		return lsReturn;
	}

	/**
	 *
	 * <pre>
	 * "http://10.137.67.206:7001/hnyb_interface_service/"
	 * "http://10.137.67.206:7001/hnyb_interface_service/ProcessAll?oper_distric=@{parameters.oper_distric}"
	 * </pre>
	 * 
	 * @return
	 */
	private String getUrlBasedOnTheInputParameters() {
		if ("1".equals(this.configs.getProperty("pcloud_rpc.debug_mode"))) {
			if (StringUtils.isNotBlank(this.getUrl())) {
				return this.getUrl();
			}
		}
		String targetUrl = null;
		if ("nkjh".equals(this.getDirect())) {
			targetUrl = this.dictService.getValue("HYGEIA_COMM_REMOTECALL", "DIRECT_NKJH");
		} else if ("inter".equals(this.getDirect())) {
			targetUrl = this.dictService.getValue("HYGEIA_COMM_REMOTECALL", "DIRECT_INTER");
		} else if ("gdyb".equals(this.getDirect())) {
			targetUrl = this.dictService.getValue("HYGEIA_COMM_REMOTECALL", "DIRECT_GDYB");
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
	 * @param remoteCallParam
	 */
	private void loadingBasedOnTheInputParameters(RemoteCallParam remoteCallParam) {
		String oper_distric = "", aaa027 = "", akb020 = "";
		if (StringUtils.isBlank(oper_distric)) {
			oper_distric = remoteCallParam.getPrameter("oper_distric");
			if (!StringUtils.isNumeric(oper_distric)) {
				oper_distric = "";
				remoteCallParam.setParameter("oper_distric", "");
			}
		}
		if (StringUtils.isBlank(aaa027)) {
			aaa027 = remoteCallParam.getPrameter("aaa027");
			if (!StringUtils.isNumeric(aaa027)) {
				aaa027 = "";
				remoteCallParam.setParameter("aaa027", "");
			}
		}
		if (StringUtils.isBlank(akb020)) {
			akb020 = remoteCallParam.getPrameter("akb020");
			if (StringUtils.isBlank(akb020)) {
				akb020 = remoteCallParam.getPrameter("hospital_id");
			}
		}
		if (StringUtils.isBlank(aaa027) && StringUtils.isNotBlank(akb020)) {
			aaa027 = this.mCCEBusniessService.getAaa027(akb020);
		}
		if (StringUtils.isBlank(oper_distric) && StringUtils.isNotBlank(aaa027)) {
			oper_distric = aaa027;
		}
		if (StringUtils.isBlank(oper_distric)) {
			throw new RuntimeException("调中心入参需包含统筹区编码(aaa027)!");
		}
		if (StringUtils.isBlank(aaa027) && StringUtils.isNotBlank(oper_distric)) {
			aaa027 = oper_distric;
		}
		if (StringUtils.isNotBlank(oper_distric)) {
			remoteCallParam.setParameter("oper_distric", oper_distric);
		}
		if (StringUtils.isNotBlank(aaa027)) {
			remoteCallParam.setParameter("aaa027", aaa027);
		}
		if (StringUtils.isNotBlank(akb020)) {
			remoteCallParam.setParameter("akb020", akb020);
		}
		if (!remoteCallParam.containsKey("trans_sc_id") || remoteCallParam.get("trans_sc_id") == null
				|| "".equals(remoteCallParam.get("trans_sc_id").toString().trim())) {
			if (StringUtils.isNotBlank(currentContextManager.getTrans_sc_id())) {
				remoteCallParam.setParameter("trans_sc_id", currentContextManager.getTrans_sc_id());
			}
		}
	}

	/**
	 * 
	 * @param targetUrl
	 * @param remoteCallParam
	 * @return
	 */
	private RemoteCallResult execute(String targetUrl, RemoteCallParam remoteCallParam) {
		String lsCallXML = "";
		boolean exec_succ = true;
		StringBuffer lsbReturnXML = new StringBuffer();
		long llTimeBegin = new Date().getTime();
		StringBuilder sbKeywords = new StringBuilder();
		try {
			String aac001 = remoteCallParam.getPrameter("aac001");
			String aac002 = remoteCallParam.getPrameter("aac002");
			String bka100 = remoteCallParam.getPrameter("bka100");
			String aaz217 = remoteCallParam.getPrameter("aaz217");
			String keyword = remoteCallParam.getPrameter("keyword");
			sbKeywords.append("-").append(aac001 == null ? "" : aac001);
			sbKeywords.append("-").append(aac002 == null ? "" : aac002);
			sbKeywords.append("-").append(bka100 == null ? "" : bka100);
			sbKeywords.append("-").append(aaz217 == null ? "" : aaz217);
			sbKeywords.append("-").append(keyword == null ? "" : keyword);
			// hy 20170824 增加翻译处理
			transNameService.transName(remoteCallParam, mapCurrentCenterID.get(), 1);
			transValueService.transValue(remoteCallParam, mapCurrentCenterID.get(), 1);

			lsCallXML = remoteCallParam.toXml();
			Map<String, String> mapHeader = new HashMap<String, String>();
			mapHeader.put("Content-Type", "text/xml; charset=UTF-8");
			RemoteCallResult remoteCallResult = null;
			this.netclient.doPost(targetUrl, mapHeader, lsCallXML, this.getTimeout(), lsbReturnXML);
			remoteCallResult = new RemoteCallResult(lsbReturnXML.toString());
			if (!"0".equals(remoteCallResult.getErrorType())) {
				if (StringUtils.isNotBlank(remoteCallResult.getErrorMessage())) {
					throw new RuntimeException(remoteCallResult.getErrorMessage());
				} else {
					throw new RuntimeException(remoteCallResult.getException());
				}
			}
			if ("-1".equals(remoteCallResult.getErrorNo())) {
				if (StringUtils.isNotBlank(remoteCallResult.getErrorMessage())) {
					throw new RuntimeException(remoteCallResult.getErrorMessage());
				} else {
					throw new RuntimeException(remoteCallResult.getException());
				}
			}
			if ("1".equals(this.dictService.getValue("HYGEIA_COMM_REMOTECALL", "IS_CHECK_ERRORNO", "1"))) {
				if (!"0".equals(remoteCallResult.getErrorNo()) && !"1".equals(remoteCallResult.getErrorNo())) {
					if (StringUtils.isNotBlank(remoteCallResult.getErrorMessage())) {
						throw new RuntimeException(remoteCallResult.getErrorMessage());
					} else {
						throw new RuntimeException(remoteCallResult.getException());
					}
				}
			}
			// hy 20170824 增加翻译处理
			transNameService.transName(remoteCallResult, mapCurrentCenterID.get(), 2);
			transValueService.transValue(remoteCallResult, mapCurrentCenterID.get(), 2);
			return remoteCallResult;
		} catch (RuntimeException e) {
			this.logger.error(new StringBuilder("").append("访问中心服务发生异常\r\nurl:").append(targetUrl)
					.append("\r\nFunctionID:").append(remoteCallParam.getFunctionID()).append("\r\n关键字:")
					.append(sbKeywords).append("\r\n记录时间:")
					.append(this.dateService.dateToString(new Date(), DateService._yyyyMMddHHmmss))
					.append("\r\n请求xml:\r\n").append(lsCallXML).append("\r\n异常信息:\r\n").append(e.getMessage())
					.toString());
			exec_succ = false;
			throw e;
		} catch (Throwable e) {
			this.logger.error(new StringBuilder("").append("访问中心服务发生网络请求异常\r\nurl:").append(targetUrl)
					.append("\r\nFunctionID:").append(remoteCallParam.getFunctionID()).append("\r\n关键字:")
					.append(sbKeywords).append("\r\n记录时间:")
					.append(this.dateService.dateToString(new Date(), DateService._yyyyMMddHHmmss))
					.append("\r\n请求xml:\r\n").append(lsCallXML).append("\r\n异常信息:\r\n").append(e.getMessage())
					.toString());
			exec_succ = false;
			throw new RuntimeException(e);
		} finally {
			String record_time = this.dateService.dateToString(new Date(), DateService._yyyyMMddHHmmss);
			long exec_time = new Date().getTime() - llTimeBegin;
			this.logger.info(new StringBuilder("").append("访问中心服务\r\nurl:").append(targetUrl).append("\r\nFunctionID:")
					.append(remoteCallParam.getFunctionID()).append("\r\n关键字:").append(sbKeywords).append("\r\n应答时间:")
					.append(exec_time).append("\r\n记录时间:").append(record_time).append("\r\n入参的长度:")
					.append(lsCallXML.length()).append("\r\n回参的长度:").append(lsbReturnXML.length())
					.append("\r\n请求xml:\r\n").append(lsCallXML).append("\r\n应答xml:\r\n").append(lsbReturnXML)
					.toString());
			try {
				// 20180320 hy 添加对中api调用的打分机制，进行监控数据的采集
				asynService.addWork("centerApiMoniter", "sendApiCallInfo",
						new Object[] { remoteCallParam, exec_succ, record_time, exec_time });
			} catch (Exception e) {
				this.logger.error(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param targetUrl
	 * @param remoteCallParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String replaceServiceUrlWithParam(String targetUrl, RemoteCallParam remoteCallParam) {
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
			mapData = remoteCallParam;
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

	public String getDirect() {
		return direct;
	}

	public void setDirect(String direct) {
		this.direct = direct;
	}

}
