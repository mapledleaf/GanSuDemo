package com.powersi.sys.manager.service;

import java.util.Arrays;
import java.util.List;

import com.powersi.hygeia.framework.BaseService;

/**
 * 业务缓存管理 service 接口
 * 
 * @author "lingang"
 * @time 2016年11月1日上午10:27:43
 *
 */
public interface BizCacheService extends BaseService, java.io.Serializable {

	/**
	 * 存在全表或增量更新缓存_全表更新缓存标识(后缀)
	 */
	public String _ALL = "_ALL";

	/**
	 * 目录匹配关系缓存key前缀
	 */
	public String ML_ = "ML_";

	/**
	 * 政策缓存key前缀
	 */
	public String POLICY_ = "POLICY_";

	/**
	 * 缓存中置放刷新标志的Map
	 */
	public String MAP_CACHEREFRESH_SIGIN = "MAP_CACHEREFRESH_SIGIN";
	/**
	 * 缓存中存储医院信息的Map
	 */
	public String MAP_BIZ_YY_INFO = "MAP_BIZ_YY_INFO";
	/**
	 * 记录存在全表或增量更新缓存的动作_JZML
	 */
	public List<String> updateCacheAlltables = Arrays.asList(new String[] { "JZML" });
	/**
	 * 基准目录标识
	 */
	public String JZML = "JZML";
	/**
	 * 基准目录数据前缀
	 */
	public String JZML_ = "JZML_";
	/**
	 * 二级目录数据前缀
	 */
	public String EJML_ = "EJML_";

	/**
	 * 写入刷新标志
	 * 
	 * @param bizFlag
	 *            业务服务标志 void
	 */
	public void readRefreshFlag(String bizFlag);

	/**
	 * 写入刷新标志
	 * 
	 * @param bizFlag
	 *            业务服务标志
	 * @param yybm
	 *            医院编码 支持多个逗号分隔
	 * @param ifall
	 *            是否刷新全部医院对应的业务缓存数据 void
	 */
	public void readRefreshFlag(String bizFlag, String yybm, boolean ifall);

	/**
	 * 写入刷新标志
	 * 
	 * @param bizFlag
	 *            业务服务标志
	 * @param tcbm
	 *            统筹编码 支持多个逗号分隔
	 * @param ifall
	 *            是否刷新全部统筹区对应的政策缓存数据 void
	 */
	public void readRefreshFlag_Policy(String bizFlag, String tcbm, boolean ifall);

	/**
	 * 写入刷新标志
	 * 
	 * @param bizFlag
	 *            业务服务标志
	 * @param tcbm
	 *            统筹编码 支持多个逗号分隔
	 * @param ifall
	 *            是否刷新全部统筹区对应的业务缓存数据 void
	 */
	public void readRefreshFlag_jzml(String bizFlag, String tcbm, boolean ifall);

}
