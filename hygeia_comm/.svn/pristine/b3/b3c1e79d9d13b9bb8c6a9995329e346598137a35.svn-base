package com.powersi.biz.medicare.query.service.db;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.Kc90DTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.query.pojo.BizQueryDTO;

/**
 * 业务查询,DB服务接口
 * @Date   2018-08-04
 * @author ChenXing 
 */
public interface BizQueryService {

	/**
	 * 信息查询查询索引(医院)
	 * @param bizQueryDto
	 * @return
	 */
	public ListResult queryIndexDB(BizQueryDTO bizQueryDto);
	
	/**
	 * 获取未完成业务
	 * @param bizQueryDto
	 * @return
	 */
	public ListResult queryBusinessInfo(BizQueryDTO bizQueryDto);
	
	/**
	 * 获取已完成业务
	 * @param bizQueryDto
	 * @return
	 */
	public BizQueryDTO queryFinBusinessInfo(BizQueryDTO bizQueryDto);
	
	/**
	 * 查询医院费用汇总信息
	 * 住院分已结算未结算，每次只查一张表 门诊两张表一起查
	 * @param bizQueryDto
	 * @return
	 */
	public BizQueryDTO querySumFee(BizQueryDTO bizQueryDto);
	
	/**
	 * 门诊费用查询(医院)
	 */
	public List<Map<String, Object>> queryBizFeeInfoDB(BizQueryDTO bizQueryDto);
	
	/**
	 * 获取费用清单需要的的数据
	 * @param bizQueryDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryFeeDetailTable(BizQueryDTO bizQueryDto);
	
	/**
	 * 获取业务采集的头像
	 * @param bizQueryDto
	 * @return
	 */
	public Kc90DTO getBusinessImage(BizQueryDTO bizQueryDto);

	/**
	 * 查询所有医院的业务信息
	 * @param bizQueryDto
	 * @param akb020Lst
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryAll(BizQueryDTO bizQueryDto, List<String> akb020Lst);
	
	/**
	 * 查询大病特药业务信息 For API
	 * @param bizQueryDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryForBigDisease(BizQueryDTO bizQueryDto);
}
