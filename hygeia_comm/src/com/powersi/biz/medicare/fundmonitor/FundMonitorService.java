package com.powersi.biz.medicare.fundmonitor;

import java.io.Serializable;
import java.util.Map;

/**
 * 基金风险监控服务
 * @author 钟松
 *
 */
public interface FundMonitorService extends Serializable{
	
	/**
	 * 是否启用缓存开关
	 */
	public boolean MEMORY_ON_OFF = false;

	/**
	 * 基金风险监控脚本对应缓存KEY
	 */
	public String FUND_MONITOR_SCRIPT = "FUND_MONITOR_SCRIPT"; 
	
	/**
	 * 基金风险监控类型
	 */
	public String JKTYPE_FUND = "0";
	
	/**
	  * 基金风险监控服务开关
	  */
	public String CalcFundMonitorSwitche = "CalcFundMonitorSwitche";
	
	/**
	 * 执行监控脚本检查
	 * @param params
	 */
	@SuppressWarnings("rawtypes")
	public void execMonitorScript(Map params);
}
