package com.powersi.biz.medicare.query.service.api;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.Kc90DTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.pojo.ListResultDTO;
import com.powersi.biz.medicare.query.pojo.BizQueryDTO;

/**
 * 业务查询，api服务接口
 * 
 * @author ChenXing
 *
 */
public interface BizQueryApiService extends Serializable {

	static String FUNC_ID = "BIZC200301";

	/**
	 * 获取所有业务信息
	 * 
	 * @param bizQueryDto
	 * @return
	 */
	public default ListResult queryAllBusinessInfo(BizQueryDTO bizQueryDto) {
		return ListResultDTO.newInstance();
	}

	/**
	 * 查询医院费用汇总信息
	 * 
	 * @param bizQueryDto
	 * @return
	 */
	public default BizQueryDTO querySumFee(BizQueryDTO bizQueryDto, String akb020) {
		return null;
	}

	/**
	 * 双击行查费用
	 * 
	 * @param bizQueryDto
	 * @return
	 */
	public default List<Map<String, Object>> queryClinicBizFeeInfo(BizQueryDTO bizQueryDto) {
		return Collections.emptyList();
	}

	/**
	 * 获取费用清单需要显示的数据
	 * 
	 * @param bizQueryDto
	 * @return
	 */
	public default Map<String, Object> queryFeeDetailTable(BizQueryDTO bizQueryDto) {
		return Collections.emptyMap();
	}

	/**
	 * 获取费用统计界面报表所需的数据
	 * 
	 * @param bizQueryDto
	 * @return
	 */
	public default Map<String, Object> getCostSumInfo(BizQueryDTO bizQueryDto) {
		return Collections.emptyMap();
	}

	/**
	 * 获取业务采集的头像
	 * 
	 * @param bizQueryDto
	 * @return
	 */
	public default Kc90DTO getBusinessImage(BizQueryDTO bizQueryDto) {
		return null;
	}

	/**
	 * 获取异地备案详细信息
	 * 
	 * @param bizQueryDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public default List<Map> getOutRecordInfo(BizQueryDTO bizQueryDto) {
		return Collections.emptyList();
	}

	/**
	 * 获取异地备案信息
	 * 
	 * @param bizQueryDto
	 * @return
	 */
	public default Map<String, Object> getOutRecordDetail(BizQueryDTO bizQueryDto) {
		return Collections.emptyMap();
	}
	
	/**
	 * 查询所有医院业务信息(admin账号专用)
	 * @param bizQueryDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public default Map<String, List<Map>> queryAll(BizQueryDTO bizQueryDto) {
		return Collections.emptyMap();
	}

	/**
	 * 查询异地就医住院信息
	 * 
	 * @param bizQueryDto
	 * @return
	 */
	public default ListResult queryAllBizInfoYdjy(BizQueryDTO bizQueryDto) {
		return ListResultDTO.newInstance();
	}

	/**
	 * 查询异地就医费用信息
	 * 
	 * @param bizQueryDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public default List<Map> queryClinicBizFeeInfoYdjy(BizQueryDTO bizQueryDto) {
		return Collections.emptyList();
	}

	/**
	 * 查询异地就医费用明细
	 * 
	 * @param bizQueryDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public default Map queryFeeDetailTableYdjy(BizQueryDTO bizQueryDto) {
		return Collections.emptyMap();
	}

	/**
	 * 异地就医查询结算信息
	 * 
	 * @param bizQueryDTO
	 * @return
	 */
	public default Map<String, Object> settlementReport(BizQueryDTO bizQueryDTO){
		return Collections.emptyMap();
	}
	
	/**
	 * 查询大病特药业务信息 For API
	 * @param bizQueryDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public default List<Map> queryForBigDisease(BizQueryDTO bizQueryDTO){
		return Collections.emptyList();
	}
}
