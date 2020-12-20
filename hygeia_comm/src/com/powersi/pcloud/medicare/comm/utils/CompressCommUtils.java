package com.powersi.pcloud.medicare.comm.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author 刘勇
 *
 */
public class CompressCommUtils {

	/**
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map compressMapStr(Map map) {
		Map compressMap = new HashMap();
		if (map == null || map.size() == 0) {
			return compressMap;
		}
		String key = "", value = "";
		Object objValue = null;
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			key = it.next().toString();
			objValue = map.get(key);
			if (objValue != null) {
				value = objValue.toString();
				if (StringUtils.isNotBlank(value)) {
					compressMap.put(key, value);
				}
			}
		}
		return compressMap;
	}

}
