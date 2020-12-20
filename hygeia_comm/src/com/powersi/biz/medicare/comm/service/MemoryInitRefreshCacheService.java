package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;

/**
 * 刷新缓存
 * 
 * @author 刘勇
 *
 */
public interface MemoryInitRefreshCacheService extends Serializable {

	/**
	 * 业务互斥锁标识
	 */
	public String HYGEIA_BASE_HASINITREFRESH_FLAG = "HYGEIA_BASE_HASINITREFRESH_FLAG";

	/**
	 * 业务互斥锁标识缓存时间_默认15分钟
	 */
	public int HYGEIA_BASE_HASINITREFRESH_FLAG_CACHETIME = 1 * 60 * 15;

	/**
	 * 启应用刷新缓存
	 */
	public void memoryInitRefreshCache();

	/**
	 * 刷新缓存
	 */
	public void memoryInitRefreshCacheBase();

}
