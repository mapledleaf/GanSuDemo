package com.powersi.biz.medicare.comm.service;

import java.util.Date;

import com.powersi.biz.medicare.comm.pojo.BizCacheTrackDTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;

public interface PolicyCacheTrackService extends java.io.Serializable {

	/**
	 * 获取DB中政策配置信息中最大更新时间
	 */
	public Date queryMaxUpdateTimeFromDB(String strPolicyType, String aaa027);

	/**
	 * 获取缓存中政策配置信息中最大更新时间
	 */
	public Date queryMaxUpdateTimeFromRedis(String strPolicyType, String aaa027);

	/**
	 * 获取政策缓存下发更新失败的数据
	 */
	public ListResult queryInfoCacheFail(String strPolicyType, BizCacheTrackDTO bizCacheTrackDTO);

}
