package com.powersi.biz.medicare.comm.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 医保前台待遇计算对象
 * 
 * @author 刘勇
 *
 */
@SuppressWarnings("rawtypes")
public interface IMediCalc extends Map, Serializable {

	/**
	 * 
	 * @param cacheFlag
	 *            使用缓存标识
	 * @return 1使用缓存 0不使用缓存
	 */
	public void setCacheFlag(String cacheFlag);

	/**
	 * 
	 * @return 使用缓存标识 1使用缓存 0不使用缓存
	 */
	public String getCacheFlag();

	/**
	 * bka438 场景阶段
	 * 
	 * @return 场景阶段(1：业务开始 2：业务结算 3：业务结束)
	 */
	public String getPhase();

	/**
	 * bka438 场景阶段
	 * 
	 * @param phase
	 *            场景阶段(1：业务开始 2：业务结算 3：业务结束)
	 */
	public void setPhase(String phase);

	/**
	 * 
	 * @return 业务类别编码
	 */
	public String getAka130();

	/**
	 * 
	 * @return 医院编号
	 */
	public String getAkb020();

	/**
	 * 
	 * @param akb020
	 *            医院编号
	 */
	public void setAkb020(String akb020);

	/**
	 * 
	 * @return 计算状态(1正在计算，0未正在计算)
	 */
	public boolean isCalcing();

	/**
	 * 
	 * @param calcingFlag
	 *            计算状态(1正在计算，0未正在计算)
	 */
	public void setCalcingFlag(String calcingFlag);

	/**
	 * 
	 * @return 是否试算
	 */
	public boolean isTryCalc();

	/**
	 * 
	 * @param calcFlag
	 *            加载计算标识(0试算、1结算)
	 */
	public void setCalcFlag(String calcFlag);

	/**
	 * 
	 * @param kcd1id
	 *            业务ID
	 */
	public void setKcd1id(String kcd1id);

	/**
	 * 
	 * @return 业务ID
	 */
	public String getKcd1id();

	/**
	 * 
	 * @param aaz217
	 *            就医登记号
	 */
	public void setAaz217(String aaz217);

	/**
	 * 
	 * @return 就医登记号
	 */
	public String getAaz217();

	/**
	 * 
	 * @return 业务数据就医登记信息(mt_biz/kcd1)多行
	 */
	public List getBiz();

	/**
	 * 
	 * @param biz
	 *            业务数据就医登记信息(mt_biz/kcd1)
	 */
	public void setBiz(List biz);

	/**
	 * 
	 * @return 业务数据就医登记信息(mt_biz/kcd1)多行
	 */
	public List getBizdtos();

	/**
	 * 
	 * @param biz
	 *            业务数据就医登记信息(mt_biz/kcd1)
	 */
	public void setBizdtos(List biz);

	/**
	 * 
	 * @return 业务数据就医登记信息(mt_biz/kcd1)单行
	 */
	public Map getBizMap();

	/**
	 * 
	 * @param biz
	 *            业务数据就医登记信息(mt_biz/kcd1)单行
	 */
	public void setBizMap(Map biz);

	/**
	 * 
	 * @return 业务数据费用信息(mt_fee/kcd2)
	 */
	public List getFee();

	/**
	 * 
	 * @param fee
	 *            业务数据费用信息(mt_fee/kcd2)
	 */
	public void setFee(List fee);

	/**
	 * 
	 * @return 业务数据费用信息(mt_fee/kcd2)
	 */
	public List getFeedtos();

	/**
	 * 
	 * @param fee
	 *            业务数据费用信息(mt_fee/kcd2)
	 */
	public void setFeedtos(List fee);

	/**
	 * 
	 * @return 业务数据支付信息(mt_pay_record/kcd7)
	 */
	public List getPay();

	/**
	 * 
	 * @param pay
	 *            业务数据支付信息(mt_pay_record/kcd7)
	 */
	public void setPay(List pay);
	
	/**
	 * 
	 * @return 业务数据特殊累计
	 */
	public Map getSpecCumulate();

	/**
	 * 
	 * @param specCumulate
	 *            业务数据特殊累计
	 */
	public void setSpecCumulate(Map specCumulate);

	/**
	 * 
	 * @return 业务数据支付信息(mt_pay_record/kcd7)
	 */
	public List getPaydtos();

	/**
	 * 
	 * @param pay
	 *            业务数据支付信息(mt_pay_record/kcd7)
	 */
	public void setPaydtos(List pay);

	/**
	 * 
	 * @param actionCode
	 *            算法调度服务ID
	 */
	public void setActionCode(String actionCode);

	/**
	 * 
	 * @return 算法调度服务ID
	 */
	public String getActionCode();

	/**
	 * 
	 * @param bizData
	 *            业务数据(mt_biz/kcd1/mt_fee/kcd2/mt_pay_record/kcd7)
	 */
	public void setBizData(Map bizData);

	/**
	 * 
	 * @return 业务数据(mt_biz/kcd1/mt_fee/kcd2/mt_pay_record/kcd7)
	 */
	public Map getBizData();

	/**
	 * 
	 * @param staticData
	 *            静态要素
	 */
	public void setStaticData(Map staticData);

	/**
	 * 
	 * @return 静态要素
	 */
	public Map getStaticData();

	/**
	 * 
	 * @param frozenData
	 *            冻结信息
	 */
	public void setFrozenData(Map frozenData);

	/**
	 * 
	 * @return 冻结信息
	 */
	public Map getFrozenData();

	/**
	 * 
	 * @return 计算结果
	 */
	public List getResult();

	/**
	 * 
	 * @param result
	 *            计算结果
	 */
	public void setResult(List result);

	/**
	 * 
	 * @return 业务场景
	 */
	public List getBizscene();

	/**
	 * 
	 * @param bizscene
	 *            业务场景
	 */
	public void setBizscene(List bizscene);

	/**
	 * 
	 * @return 业务场景
	 */
	public List getBizscenedtos();

	/**
	 * 
	 * @param bizscene
	 *            业务场景
	 */
	public void setBizscenedtos(List bizscene);

	/**
	 * 
	 * @return 费用分类
	 */
	public List getFeestat();

	/**
	 * 
	 * @param feestat
	 *            费用分类
	 */
	public void setFeestat(List feestat);

	/**
	 * 
	 * @return 费用分类
	 */
	public List getFeestatdtos();

	/**
	 * 
	 * @param feestat
	 *            费用分类
	 */
	public void setFeestatdtos(List feestat);

	/**
	 * 
	 * @return
	 */
	public String toJson();

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public boolean compareTo(Object obj);

}
