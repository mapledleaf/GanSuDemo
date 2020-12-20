package com.powersi.biz.medicare.comm.service;

import java.util.List;

import com.powersi.biz.medicare.comm.pojo.FeeDirDTO;

public interface FeeInfoVS {

	@SuppressWarnings("rawtypes")
	public List queryCatalog(FeeDirDTO feeInfoDTO);

}
