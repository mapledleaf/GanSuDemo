package com.powersi.biz.medicare.diagnose.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.diagnose.pojo.Kf01DTO;
import com.powersi.biz.medicare.diagnose.pojo.Kf02DTO;

public interface DiagnoseBatchChargeVS {

	/**
	 * 生产结算数据（卫生站批量费用导入）消息
	 */
	public String MQ_HYGEIA_ESB_DIAGNOSEBATCH = "MQ_HYGEIA_ESB_DIAGNOSEBATCH";

	/**
	 * 卫生站费用批量导入查询 2017年10月12日 下午2:50:37
	 * 
	 * @param kf01DTO
	 *            void
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryBatchCharge(Kf01DTO kf01DTO);

	/**
	 * 卫生站费用导入批次明细查询 2017年10月12日 下午3:00:56
	 * 
	 * @param kf02DTO
	 * @return List<Map>
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryDetatilCharge(Kf02DTO kf02DTO);

	/**
	 * 卫生站费用导入批量导入 2017年10月12日 下午3:01:53
	 * 
	 * @param kf01DTO
	 * @param list
	 * @return int
	 */
	public int saveBatchCharge(Kf01DTO kf01DTO, List<Kf02DTO> list);

	/**
	 * 卫生站费用导入批次明细修改 2017年10月18日 下午4:49:54
	 * 
	 * @param kf02DTO
	 * @return List<Map>
	 */
	public int updateDetatilCharge(Kf02DTO kf02DTO);

	/**
	 * 卫生站批次导入状态更新 2017年10月20日 上午10:50:51
	 * 
	 * @param kf01DTO
	 * @return int
	 */
	public int updateCharge(Kf01DTO kf01DTO);

	/**
	 * 出发校验
	 * 
	 * @author yangmj 2017年10月24日 下午3:47:49
	 * @param kf01DTO
	 *            void
	 */
	public void velidDate(Kf01DTO kf01DTO);
	
	

}
