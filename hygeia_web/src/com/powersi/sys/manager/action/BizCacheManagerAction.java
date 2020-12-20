package com.powersi.sys.manager.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.powersi.comm.service.memory.MemoryDBWrapper;
import com.powersi.config.pojo.BizYyInfo;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.sys.manager.service.BizCacheService;

/**
 * 业务缓存管理
 * 
 * @author "lingang"
 * @time 2016年11月1日上午10:11:32
 *
 */
@Action(value = "BizCacheManager", results = { @Result(name = "bizcache", location = "/pages/sys/manager/BizCache.jsp"),
		@Result(name = "showYyInfo", location = "/pages/sys/manager/BinYyInfo.jsp") })
public class BizCacheManagerAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private BizCacheService bizCacheService;
	@Autowired
	@Qualifier("memoryDBWrapper_core")
	private MemoryDBWrapper memoryDBWrapper;

	/**
	 * 获取业务缓存服务列表
	 * 
	 * @return String
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Action("queryRefreshList")
	public String queryRefreshList() {
		try {
			// 获取码表中配置的刷新服务
			Map refreshMap = CodetableMapping.get("refresh_cache");
			if (refreshMap != null && !refreshMap.isEmpty()) {
				List<Map> list = new ArrayList<Map>();
				Map map = null;
				for (Object key : refreshMap.keySet()) {
					map = new HashMap();
					map.put("code", key);
					map.put("value", refreshMap.get(key));
					list.add(map);
				}
				setJSONReturn(list);
			}
		} catch (Exception ex) {
			saveJSONError(ex);
		}
		return NONE;
	}

	/**
	 * 刷新对应的服务缓存
	 * 
	 * @param bizFlag
	 * @return String
	 */
	@Action("refreshCache")
	public String refreshCache() {
		try {
			String bizFlag = getParameter("bizFlag");
			// 判断是否刷新全部医院目录缓存信息
			if ("YYML".equals(bizFlag)) {
				// 需要刷新缓存的医院编码，刷新全部的情况下无效
				String yybm = getParameter("yybm");
				// 是否刷新全部医院
				String ifall = getParameter("ifall");
				bizCacheService.readRefreshFlag(bizFlag, yybm, "true".equals(ifall) ? true : false);
			} else if ("POLICY".equals(bizFlag)) {
				// 需要刷新缓存的统筹编码，刷新全部的情况下无效
				String tcbm = getParameter("tcbm");
				// 是否刷新全部统筹区
				String ifall = getParameter("ifall");

				String refresh_type = getParameter("refresh_type");
				bizCacheService.readRefreshFlag_Policy(refresh_type, tcbm, "true".equals(ifall) ? true : false);
			} else {
				// 需要刷新缓存的统筹编码，刷新全部的情况下无效
				String tcbm = getParameter("tcbm");
				// 是否刷新全部统筹区
				String ifall = getParameter("ifall");
				bizCacheService.readRefreshFlag_jzml(bizFlag, tcbm, "true".equals(ifall) ? true : false);
				// bizCacheService.readRefreshFlag(bizFlag);
			}
			saveJSONMessage("5分钟内完成刷新,请耐心等待。。。");
		} catch (Exception ex) {
			saveJSONError(ex);
		}
		return NONE;
	}

	/**
	 * 查询医院信息
	 * 
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	@Action("findBizYyInfo")
	public String findBizYyInfo() {
		try {
			// 2018-02-07 lhy 代码中写的判断memorydb中是否existKey的，需全部修改为get后判断是否为null来处理。
			// 用existKey会无法从二级缓存中获取，而会到服务器上获取，业务量大了以后会影响性能。
			// if (memoryDB.existKey("MAP_BIZ_YY_INFO")) {
			Map bizYyInfoMap = this.memoryDBWrapper.getMemoryDB().getMap("MAP_BIZ_YY_INFO");
			if (bizYyInfoMap != null && !bizYyInfoMap.isEmpty()) {
				List<BizYyInfo> bizYyInfoList = new ArrayList<BizYyInfo>();
				for (Object key : bizYyInfoMap.keySet()) {
					BizYyInfo bizYyinfo = (BizYyInfo) bizYyInfoMap.get(key);
					bizYyInfoList.add(bizYyinfo);
				}
				setJSONReturn(bizYyInfoList);
			} else {
				return "success";
			}
		} catch (Exception ex) {
			saveJSONError("查询医院信息出错！" + ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 获取广东省21地市统筹区列表
	 * 
	 * @return String
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Action("queryGdsPolicyTcqList")
	public String queryGdsPolicyTcqList() {
		try {
			// 获取码表中配置的刷新服务
			Map refreshMap = CodetableMapping.get("gds_policy_tcq");
			if (refreshMap != null && !refreshMap.isEmpty()) {
				List<Map> list = new ArrayList<Map>();
				Map map = null;
				for (Object key : refreshMap.keySet()) {
					map = new HashMap();
					map.put("code", key);
					map.put("value", refreshMap.get(key));
					list.add(map);
				}
				setJSONReturn(list);
			} else {
				return "success";
			}
		} catch (Exception ex) {
			saveJSONError(ex);
		}
		return NONE;
	}

}
