package com.powersi.log.service;

import java.util.Date;

import com.powersi.comm.service.memory.MemoryDB;
import com.powersi.comm.utils.TimeUtils;

/**
 * 
 * <pre>
 * 错误日志编号生成的服务 在需要使用到该service的工程xml中注入，方可使用
 * //<bean id="errLogSnService" class="com.powersi.log.service.ErrLogSnService">
 * //	<property name="memoryDB" value="memoryDB"></property> 
 * //</bean>
 * 使用时采用如下方式进行注入
 * //@Autowired 
 * //@Qualifier("errLogSnService") 
 * //private ErrLogSnService errLogSnService;
 * </pre>
 * 
 * @author 黄尧
 *
 */
public class ErrLogSnService {

	private MemoryDB memoryDB = null;
	// 日志编号过期时间
	private int time_out = 2 * 24 * 60 * 60;// (s)

	/**
	 * 应用的类别，API表示hygeia_esb，WEB表示hygeia_web
	 */
	public enum ProjectType {
		API, WEB
	}

	/**
	 * 根据应用的类别获取日志编号
	 * 
	 * @param type
	 *            应用的类别 API或者WEB
	 * @return
	 */
	public String getErrSN(ProjectType type) {
		String time = TimeUtils.formatDate(new Date(), "yyyyMMdd");
		String day = time.substring(6);
		// 日志错误编号为设计为 应用类别+日期+流水号，如：API12110，WEB12110
		switch (type) {
		case API:
			return "API" + day + memoryDB.plusLong("ERR_LOG_SN_API_" + time, time_out, 1);
		case WEB:
			return "WEB" + day + memoryDB.plusLong("ERR_LOG_SN_WEB_" + time, time_out, 1);
		default:
			return "";
		}
	}

	public void setMemoryDB(MemoryDB memoryDB) {
		this.memoryDB = memoryDB;
	}

	public MemoryDB getMemoryDB() {
		return memoryDB;
	}

	public int getTime_out() {
		return time_out;
	}

	public void setTime_out(int time_out) {
		this.time_out = time_out;
	}

}
