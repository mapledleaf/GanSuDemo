package com.powersi.pcloud.keepalive.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.powersi.comm.exception.ApusException;
import com.powersi.comm.utils.ToolUtil;

/**
 * 在业务程序上有几个地方需要判断当前程序的类型是keepalive的主或者从 需要在需要使用该类的程序xml中注入
 * <bean id="keepAliveStatusService" class=
 * "com.powersi.pcloud.keepalive.service.KeepAliveStatusService"> </bean>
 * 
 * @author 黄尧
 *
 */
public class KeepAliveStatusService {

	private final Logger logger = LoggerFactory.getLogger(KeepAliveStatusService.class);
	private final String kepalivedFilePath = "/app/log/keepalived/status";

	/**
	 * 判断当前程序所在服务器是否为keepalive中的主
	 * 
	 * @return
	 */
	public boolean isMaster() {
		return "MASTER".equals(getCurrentHostStatus());
	}

	/**
	 * 读取keepalived的状态配置文件,获取keepalived.status， 如果keepalived读取文件异常或者结果为空，返回值都为MASTER
	 * 
	 * @return
	 */
	private String getCurrentHostStatus() {
		String keepalivedStatus = "MASTER";
		try {
			String platform = getPlatform();
			// 操作系统不为linux的话则默认作为主
			if (!"LINUX".equals(platform)) {
				return keepalivedStatus;
			}
			// 操作系统为linux则从文件中判断是主还是备,为文件状态为空是默认为主
			Map<String, String> map = readFile(kepalivedFilePath);
			keepalivedStatus = map.get("keepalived.status");
			if (StringUtils.isBlank(keepalivedStatus)) {
				keepalivedStatus = "MASTER";
			}
		} catch (Exception e) {
			// 读取文件错误时，如文件不存在时，默认为主
			keepalivedStatus = "MASTER";
		}
		return keepalivedStatus;
	}

	/**
	 * 读取文件，将读取的参数放入一个map并返回
	 * 
	 * @param filePath
	 * @return
	 */
	private Map<String, String> readFile(String filePath) {
		Map<String, String> map = new HashMap<String, String>();
		BufferedReader bufferedReader = null;
		try {
			// 获取配置文件
			File file = new File(filePath);
			// 文件不存在，则创建文件
			if (!file.exists()) {
				file.createNewFile();
			}
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			// 从本地文件中读取数据并放入map中
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				String[] arr = line.split("=");
				if (arr.length == 1) {
					arr = Arrays.copyOf(arr, arr.length + 1);
					arr[1] = "";
				}
				String key = arr[0];
				String value = arr[1];
				map.put(key, value);
			}
			logger.debug("读取配置文件并写入map:" + map);
			return map;
		} catch (Exception e) {
			throw new ApusException("文件处理异常,异常信息为:" + ToolUtil.getExceptionInfo(e));
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					throw new ApusException("流关闭异常,异常信息为:" + ToolUtil.getExceptionInfo(e));
				}
			}
		}
	}

	/**
	 * 得到当前操作系统类型
	 * 
	 * @return "WINDOWS" "LINUX" "MAC"
	 */
	private String getPlatform() {
		String systemType = "";
		Properties props = System.getProperties(); // 获得系统属性集
		String os = props.getProperty("os.name"); // 操作系统名称
		// 判断系统
		if (os.startsWith("windows") || os.startsWith("Windows")) {
			systemType = "WINDOWS";
		} else if (os.startsWith("linux") || os.startsWith("Linux")) {
			systemType = "LINUX";
		} else if (os.startsWith("Mac") || os.startsWith("mac")) {
			systemType = "MAC";
		}
		return systemType;
	}

}
