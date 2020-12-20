package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.ListResult;

/**
 * 中心下发数据业务缓存跟踪服务
 * @author zhos
 *
 */
public interface CenterBizCacheTrackService extends Serializable{
	
	public String MAP_SILL = "MAP_SILL";
	public String MAP_SEGMENT = "MAP_SEGMENT";
	public String MAP_SEGMENT_PAY = "MAP_SEGMENT_PAY";
	public String MAP_SELF_SCALE = "MAP_SELF_SCALE";
	public String MAP_SPECIAL_TREAT_LIMIT = "MAP_SPECIAL_TREAT_LIMIT";
	public String MAP_BIZ_LIMIT_LINE = "MAP_BIZ_LIMIT_LINE";
	public String[] POLICY_KEYS = {MAP_SILL,MAP_SEGMENT,MAP_SEGMENT_PAY,MAP_SELF_SCALE,MAP_SPECIAL_TREAT_LIMIT,MAP_BIZ_LIMIT_LINE};
	public String POLICY = "POLICY";
	
	/**
	 * 调用中心获取最大更新时间的集合接口
	 */
	public String TYPE_TAG_001 = "001";
	
	/**
	 * 调用中心获取下发失败的数据集合接口
	 */
	public String TYPE_TAG_002 = "002";

	/**
	 * 获取中心、结算云、reids对应的政策下发数据更新的最新时间集合
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryPolicyMaxUpdateTimeList(Map paraMap);
	
	/**
	 * 查询中心政策下发失败的数据信息 
	 */
	@SuppressWarnings("rawtypes")
	public ListResult queryPolicyMissInfoFromCenter(String strPolicyType, Map paraMap);
 
	/**
	 * 查询政策缓存失败（或未刷新）信息 
	 */
	@SuppressWarnings("rawtypes")
	public ListResult queryPolicyMissInfoFromJsy(String strPolicyType, Map paraMap); 
}
