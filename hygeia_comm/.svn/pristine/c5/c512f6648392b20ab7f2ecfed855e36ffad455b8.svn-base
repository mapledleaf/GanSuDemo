package com.powersi.biz.medicare.comm.service;

import java.util.Map;

/**
 * 门诊挂号减免_珠海
 * 
 * <pre>
 * 
 * 门诊挂号减免10元定额的问题，我们打算走门诊业务流程，如果费用明细里录入了特殊项目（门诊挂号费(珠海)）系统自动推送一条减免记录到中心
 * 
 * 1、通过费用明细录入特殊项目（门诊挂号费(珠海)/xxx）来触发门诊挂号减免10元定额的记录；
 * 2、门慢特殊处理：如果是门慢，门诊挂号费17元减免10元剩下7元要进入统筹支付；
 * 3、请在中心建表保存门诊挂号减免信息；
 * </pre>
 * 
 * @author 刘勇
 *
 */
public interface ClinicRegisterReductionService {

	/**
	 * 新增门诊挂号减免记录(台账)
	 * 
	 * @param row
	 */
	@SuppressWarnings("rawtypes")
	public void addClinicRegisterReductionInfo(Map row);

	/**
	 * 作废门诊挂号减免记录(台账)
	 * 
	 * @param row
	 */
	@SuppressWarnings("rawtypes")
	public void cancelClinicRegisterReductionInfo(Map row);

	/**
	 * 是否不需要选点就可以办理门诊
	 * 
	 * @param akb020
	 *            医院编号
	 * @return
	 */
	public boolean isCanHandlerClinicRegisterNoPointSelection(String akb020);

}
