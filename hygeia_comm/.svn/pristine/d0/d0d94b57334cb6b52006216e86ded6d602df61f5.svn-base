package com.powersi.hygeia.comm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.powersi.asyn.service.AsynService;
import com.powersi.comm.utils.NetClient;
import com.powersi.comm.utils.TimeUtils;
import com.powersi.hygeia.comm.dto.RemoteCallParam;
import com.powersi.hygeia.comm.dto.RemoteCallResult;

/**
 * 
 * @author 林刚
 *
 */
public class RemoteCallService_YY implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final NetClient netclient = new NetClient();
	private int timeout = 120 * 1000;
	@Autowired
	@Qualifier("asynService")
	private AsynService asynService;

	/**
	 * 远程调用医院服务
	 * 
	 * @param targetUrl
	 *            目标地址
	 * @param param
	 *            请求参数
	 * @return
	 */
	public RemoteCallResult execute(String targetUrl, RemoteCallParam param) {
		String lsCallXML = "";
		StringBuffer lsbReturnXML = new StringBuffer();
		long llTimeBegin = new Date().getTime();
		StringBuilder sbKeywords = new StringBuilder();
		boolean exec_succ = true;
		try {
			String dealingjournalno = "", aaz217 = "";
			dealingjournalno = param.getPrameter("dealingjournalno");
			dealingjournalno = dealingjournalno == null ? "" : dealingjournalno;
			aaz217 = param.getPrameter("aaz217");
			aaz217 = aaz217 == null ? "" : aaz217;
			sbKeywords.append("-");
			sbKeywords.append(dealingjournalno);
			sbKeywords.append("-");
			sbKeywords.append(aaz217);
			param.setRootName("program");
			lsCallXML = param.toXml();
			Map<String, String> mapHeader = new HashMap<String, String>();
			mapHeader.put("Content-Type", "text/xml; charset=UTF-8");
			this.netclient.doPost(targetUrl, mapHeader, lsCallXML, this.getTimeout(), lsbReturnXML);
			RemoteCallResult remoteCallResult = null;
			remoteCallResult = new RemoteCallResult(lsbReturnXML.toString());
			if (!remoteCallResult.containsKey("return_code")) {
				throw new RuntimeException("医院应答xml必须包含return_code");
			}
			Object retCode = remoteCallResult.get("return_code");
			if (retCode == null || "".equals(retCode.toString().trim())) {
				throw new RuntimeException("医院应答xml必须包含return_code的值");
			}
			int errorNo = Integer.parseInt(retCode.toString());
			if (errorNo < 0) {
				Object msg_obj = remoteCallResult.get("return_code_message");
				if (msg_obj == null || "".equals(msg_obj.toString().trim())) {
					throw new RuntimeException("医院执行错误，但未反馈错误信息，\\<return_code_message\\>为空或不存在");
				}
				String msg = msg_obj.toString();
				if (StringUtils.isNotBlank(msg)) {
					throw new RuntimeException("医院返回：" + msg);
				} else {
					throw new RuntimeException("医院执行错误，但未反馈错误信息，<return_code_message>为空");
				}
			}
			return remoteCallResult;
		} catch (RuntimeException e) {
			exec_succ = false;
			this.logger.error(new StringBuilder("").append("访问医院服务发生异常\r\nurl:").append(targetUrl).append(sbKeywords)
					.append("\r\nxml:\r\n").append(lsCallXML).append("\r\n异常信息:").append(e.getMessage()).toString());
			throw e;
		} catch (Throwable e) {
			exec_succ = false;
			this.logger.error(new StringBuilder("").append("访问医院服务发生网络请求异常\r\nurl:").append(targetUrl)
					.append(sbKeywords).append("\r\nxml:\r\n").append(lsCallXML).append("\r\n异常信息:")
					.append(e.getMessage()).toString());
			throw new RuntimeException(e);
		} finally {
			String record_time = TimeUtils.formatDate(new Date(), "yyyyMMddHHmmss");
			long exec_time = new Date().getTime() - llTimeBegin;
			this.logger.info(new StringBuilder("").append("访问医院服务\r\nurl:").append(targetUrl).append("\r\nFunctionID:")
					.append(param.getFunctionID()).append(sbKeywords).append("\r\n应答时间:").append(exec_time)
					.append("\r\n请求xml:\r\n").append(lsCallXML).append("\r\n应答xml:\r\n").append(lsbReturnXML)
					.toString());
			try {
				// 20180320 hy 添加对中api调用的打分机制，进行监控数据的采集
				asynService.addWork("centerApiMoniter", "sendApiCallInfo",
						new Object[] { param, exec_succ, record_time, exec_time });
			} catch (Exception e) {
				this.logger.error(e.getMessage());
			}
		}
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
