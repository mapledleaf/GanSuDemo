package com.powersi.biz.medicare.outland.service.api;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.outland.pojo.OutAccountBizDTO;

/**
 * 跨省异地日对账功能接口
 * @author yangmj
 *
 */
public interface OutAccountBizApiService {
	
	public Map<String, Object> queryOutAccount(OutAccountBizDTO outAccountBizDTO);
	
	public String OutAccountData(OutAccountBizDTO outAccountBizDTO,List<Map<String, Object>> outAccountDtoList);
	
}
