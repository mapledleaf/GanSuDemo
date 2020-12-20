package com.powersi.moniter;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.comm.exception.ApusException;
import com.powersi.comm.service.memory.MemoryDBWrapper;
import com.powersi.comm.utils.TimeUtils;
import com.powersi.hygeia.comm.service.HygeiaBeanService;

/**
 * 
 * @author 李志钢、黄尧
 *
 */
@Service
public class BizMoniter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String firstZoneKey = "";
	@Autowired
	private CommunalService communalService;

	/**
	 * 增加监控对象的数据,添加地区编码 例如：门诊结算一次，调用为：type=MONITER_BIZ_MZ_RC fValue=1 表示增加一个人次记录
	 * 
	 * @param type
	 *            类型，引用来自MoniterTypes中的定义
	 * @param fValue
	 *            数值，可以是1表示一次，可以是一个金额，根据类型来
	 * @param zone_code
	 *            经办地行政区域编码,对应业务aaa027，如果是监控数据传输则传固定值"TRANS"
	 */
	@SuppressWarnings("rawtypes")
	public void plusBizValue(String type, float fValue, String zone_code) {
		try {
			MemoryDBWrapper memoryDBWrapper = null;
			String MAP_MONITER_BIZ_TYPES = "MAP_MONITER_BIZ_TYPES";
			memoryDBWrapper = (MemoryDBWrapper) HygeiaBeanService.getInstance().getBean("memoryDBWrapper");
			if (!memoryDBWrapper.getMemoryDB().existKey(MAP_MONITER_BIZ_TYPES)) {
				memoryDBWrapper.getMemoryDB().initMap(MAP_MONITER_BIZ_TYPES, 0, MoniterTypes.getAllTypes());
			} else {
				Map mapMoniterBizTypes = memoryDBWrapper.getMemoryDB().getMap(MAP_MONITER_BIZ_TYPES);
				if ((mapMoniterBizTypes == null || mapMoniterBizTypes.size() == 0)
						&& MoniterTypes.getAllTypes().size() > 0) {
					memoryDBWrapper.getMemoryDB().initMap(MAP_MONITER_BIZ_TYPES, 0, MoniterTypes.getAllTypes());
				} else if ((mapMoniterBizTypes.size() != MoniterTypes.getAllTypes().size())
						&& MoniterTypes.getAllTypes().size() > 0) {
					memoryDBWrapper.getMemoryDB().initMap(MAP_MONITER_BIZ_TYPES, 0, MoniterTypes.getAllTypes());
				}
			}
			// 存储产生业务监控数据的地区编号的缓存key前缀，需要拼接yyyyMMddHH
			String MAP_MONITER_BIZ_ZONE = "MAP_MONITER_BIZ_ZONE_";
			if (StringUtils.isBlank(zone_code)) {
				throw new ApusException("获取地区编码或者数据传输标识值不能为空!");
			}
			String hourStr = TimeUtils.formatDate(new Date(), "yyyyMMddHH");
			String dayStr = TimeUtils.formatDate(new Date(), "yyyyMMdd");
			// 为了保证每小时统计了的地区不冲突，zone_code按小时登记
			String zoneHourKey = MAP_MONITER_BIZ_ZONE + hourStr;
			// hy 20170803 为了防止数据传输重复记录，特加此判断
			if (!"TRANS".equals(zone_code)) {
				// 拼接所有地区小时的和天的业务数据
				memoryDBWrapper.getMemoryDB().setMapValue(zoneHourKey, "ALL", "ALL");
				String allZoneDay = type + "_ALL_" + dayStr;
				String allZoneHour = type + "_ALL_" + hourStr;
				memoryDBWrapper.getMemoryDB().plusDouble(allZoneDay, 24 * 60 * 60 * 2, fValue);
				memoryDBWrapper.getMemoryDB().plusDouble(allZoneHour, 60 * 60 * 2, fValue);
			}
			// 需要按地区拼接小时的和天的业务数据
			memoryDBWrapper.getMemoryDB().setMapValue(zoneHourKey, zone_code, zone_code);
			// 设置redis的key过期
			if (StringUtils.isBlank(firstZoneKey) || !firstZoneKey.equals(zoneHourKey)) {
				firstZoneKey = zoneHourKey;
				memoryDBWrapper.getMemoryDB().expire(zoneHourKey, 60 * 60 * 2);
			}
			String zoneDay = type + "_" + zone_code + "_" + dayStr;
			String zoneHour = type + "_" + zone_code + "_" + hourStr;
			memoryDBWrapper.getMemoryDB().plusDouble(zoneDay, 24 * 60 * 60 * 2, fValue);
			memoryDBWrapper.getMemoryDB().plusDouble(zoneHour, 60 * 60 * 2, fValue);
		} catch (Throwable ex) {
			this.communalService.error(this.logger, ex, "BizMoniter.plusBizValue");
		}
	}

}
