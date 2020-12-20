package com.powersi.biz.medicare.queryAccount.service.api;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.queryAccount.pojo.AccountDTO;

public interface QueryAccountApiService {

	/**
	 * 获取台账明细信息
	 * 
	 * @param accountDTO
	 * @return
	 */
	public List<AccountDTO> queryAccountDetail(AccountDTO accountDTO);

	/**
	 * 获取台账信息
	 * 
	 * @param accountDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, List<Map>> queryAccountInfo(AccountDTO accountDTO);

}
