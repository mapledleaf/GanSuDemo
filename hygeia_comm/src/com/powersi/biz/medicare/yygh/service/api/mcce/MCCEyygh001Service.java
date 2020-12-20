package com.powersi.biz.medicare.yygh.service.api.mcce;

import java.util.List;

import com.powersi.biz.medicare.yygh.pojo.KE01;

/**
 * @Description: 上传预约挂号号源
 * @author zhos
 * @date 2017年3月29日 下午3:03:25
 *
 */
public interface MCCEyygh001Service {

	/**
	 * 上传预约挂号号源 @param @param akb020 @param @param ke01DTORows @param @return
	 * 设定文件 @return KE01 返回类型 @throws
	 */
	public KE01 insertKe01List(String akb020, List<KE01> ke01DTORows);

}
