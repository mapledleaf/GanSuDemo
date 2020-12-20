package com.powersi.biz.medicare.outland.service.api;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.outland.pojo.OutBizDTO;

/**
 * 异地就医业务，api服务接口
 * 
 * @author Administrator
 *
 */
public interface OutBizApiService {

	/**
	 * 获取人员 信息
	 * 
	 * @param akb020
	 * @param outBizDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getPersonInfo(String akb020, OutBizDTO outBizDTO);

	/**
	 * 获取申请信息
	 * 
	 * @param akb020
	 * @param outBizDTO
	 * @return
	 */
	public Map<String, Object> getOutAreaModifyInfo(OutBizDTO outBizDTO);

	/**
	 * 保存异地申请信息
	 * 
	 * @param akb020
	 * @param outBizDTO
	 * @param list
	 * @return
	 */
	public int saveOutAreaInfo(String akb020, OutBizDTO outBizDTO, List<Map<String, Object>> list);

}
