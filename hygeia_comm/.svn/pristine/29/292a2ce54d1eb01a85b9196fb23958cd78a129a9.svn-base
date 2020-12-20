package com.powersi.biz.medicare.outland.service.api;

import java.util.Map;

import com.powersi.biz.medicare.outland.pojo.OutDeclDTO;

public interface OutDeclService {
	
	
	/**跨省外来就医月度结算清分明细查询
	 * @author yangmj
	 * 2017年6月29日 上午9:59:11
	 * @param outDeclDTO
	 * @return Map<String,Object>
	 */
	@SuppressWarnings("rawtypes")
	public Map queryDeclDetail(OutDeclDTO outDeclDTO);
	
	
	/**提取信息
	 * @author yangmj
	 * 2017年6月29日 上午10:00:01
	 * @param outDeclDTO
	 * @return Map<String,Object>
	 */
	public String getDeclDetail(OutDeclDTO outDeclDTO);
	
	
	/**
	 * 确认、不确认
	 * @return String
	 */
	public String enterDeclDetail(OutDeclDTO outDeclDTO);
	
	/**申报
	 * @return String
	 */
	public String doDeclare(OutDeclDTO outDeclDTO);
	
	/**取消申报
	 * @return String
	 */
	public String backDeclare(OutDeclDTO outDeclDTO);
	
	/**
	 * 获取报表数据
	 * @param outDeclDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map printReport(OutDeclDTO outDeclDTO);
}
