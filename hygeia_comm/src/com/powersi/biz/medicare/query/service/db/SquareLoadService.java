package com.powersi.biz.medicare.query.service.db;

import java.io.Serializable;
import java.util.Map;

import com.powersi.biz.medicare.query.pojo.BizQueryDTO;

/**
 * 业务查询，DB服务接口
 * 
 * @author Administrator
 *
 */
public interface SquareLoadService extends Serializable {

	/**
	 * 动态加载结算单
	 * 
	 * @param medi
	 * @return
	 */
	public Map<String, Object> squareLoad(String akb020, BizQueryDTO bizQueryDto);

}
