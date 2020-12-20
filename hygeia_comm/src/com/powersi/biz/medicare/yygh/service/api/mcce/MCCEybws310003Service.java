package com.powersi.biz.medicare.yygh.service.api.mcce;

import java.util.List;

import com.powersi.biz.medicare.yygh.pojo.KE02;

/**
 * 
 * @Description: 查询预约挂号（或取消的预约挂号）
 * @author zhos
 * @date 2017年3月29日 下午3:03:25
 *
 */
public interface MCCEybws310003Service {

	/**
	 * 查询预约挂号（或取消的预约挂号）信息 @param @param ke01DTO @param @return 设定文件 @return
	 * List<KE01> 返回类型 @throws
	 */
	public List<KE02> selectKe02List(KE02 ke02DTO);
}
