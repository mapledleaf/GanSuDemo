package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;

import com.powersi.biz.medicare.comm.pojo.KCS1DTO;
import com.powersi.biz.medicare.comm.pojo.KCS2DTO;
import com.powersi.biz.medicare.comm.pojo.KCS3DTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;

/**
 * 病案首页：疾病和手术码相关配置表查询 
 * @author zhos
 * @date 2017-08-16
 */
public interface MedicalPageParamsService extends Serializable {

	/**
	 * 查询疾病分类与代码表信息
	 * 
	 * @param kcs1DTO
	 * @return 
	 */
	public ListResult queryDiseaseTypeAndCodes(KCS1DTO kcs1DTO);
	
	/**
	 * 查询手术与操作代码表信息
	 * 
	 * @param kcs2DTO
	 * @return 
	 */
	public ListResult queryOperationAndCodes(KCS2DTO kcs2DTO);

	/**诊治代码库查询
	 * @author yangmj
	 * 2017年11月22日 下午5:04:58
	 * @param kcs2dto
	 * @return ListResult
	 */
	public ListResult queryKcs3AndCodes(KCS3DTO kcs3dto);


}
