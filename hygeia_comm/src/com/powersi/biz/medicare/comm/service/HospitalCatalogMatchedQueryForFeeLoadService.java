package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;
import java.util.List;

import com.powersi.biz.medicare.inhospital.pojo.FeeDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 医院已匹配目录查询_后台费用加载
 * 
 * @author 刘勇
 *
 */
public interface HospitalCatalogMatchedQueryForFeeLoadService extends Serializable {

	/**
	 * 医院已匹配目录查询_后台费用加载
	 * 
	 * <pre>
	 * aka065 收费项目等级编码(等级)
	 * ake003 三大目录类别编码(类别)
	 * ake005 医疗机构三大目录编码(编码)
	 * ake006 医疗机构三大目录名称(名称)
	 * bka052 医院目录剂型(剂型)
	 * bka054 医院目录规格(规格)
	 * bka053 医院目录厂家(厂家)
	 * aka063 医疗发票项目类别
	 * ake001 异名库目录编码
	 * ake002 异名目录名称
	 * bkc190 基本药物标识
	 * bkm029 医保识别码
	 * aka057 先自付比例
	 * </pre>
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public List<FeeDTO> queryHospitalCatalogMatchedForFeeLoad(InHospitalDTO inHospitalDTO);

}
