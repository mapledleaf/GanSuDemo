package com.powersi.biz.medicare.diagnose.service;

import java.util.List;

public interface CloudChooseDiagnoseBizDBVS {

	/**
	 * 
	 * @Title: selectBiz
	 * @Description: TODO(查询业务信息)
	 * @param: @param
	 *             arg_name
	 * @param: @param
	 *             arg_value
	 * @param: @param
	 *             hospid
	 * @param: @param
	 *             biztype
	 * @param: @return
	 * @return: List
	 */
	@SuppressWarnings("rawtypes")
	public List selectBiz(String arg_name, String arg_value, String hospid, String biztype);

	/**
	 * 
	 * @Title: selectBizFee
	 * @Description: TODO(查询费用批次信息)
	 * @param: @param
	 *             aaz217
	 * @param: @param
	 *             akb020
	 * @param: @param
	 *             kcd1id
	 * @param: @param
	 *             bka039
	 * @param: @return
	 * @return: List
	 */
	@SuppressWarnings("rawtypes")
	public List selectBizFee(String aaz217, String akb020, String kcd1id, String bka039);

	/**
	 * 
	 * @Title: getDiagnoseDetail
	 * @Description: TODO(查询人员业务明细)
	 * @param: @param
	 *             akb020
	 * @param: @param
	 *             aaz217
	 * @param: @return
	 * @return: List
	 */
	@SuppressWarnings("rawtypes")
	public List getDiagnoseDetail(String akb020, String aaz217);

	/**
	 * 
	 * @Title: selectBizFeeInfo
	 * @Description: TODO(查询业务费用明细)
	 * @param: @param
	 *             aaz217
	 * @param: @param
	 *             akb020
	 * @param: @param
	 *             kcd1id
	 * @param: @param
	 *             pka001
	 * @param: @param
	 *             bka039
	 * @param: @return
	 * @return: List
	 */
	@SuppressWarnings("rawtypes")
	public List selectBizFeeInfo(String aaz217, String akb020, String kcd1id, String pka001, String bka039);

	/**
	 * 
	 * @Title: selectBatchFee
	 * @Description: TODO(根据费用批次查询费用明细)
	 * @param: @param
	 *             aaz217
	 * @param: @param
	 *             akb020
	 * @param: @param
	 *             kcd1id
	 * @param: @param
	 *             pka039
	 * @param: @param
	 *             pka001
	 * @param: @return
	 * @return: List
	 */
	@SuppressWarnings("rawtypes")
	public List selectBatchFee(String aaz217, String akb020, String kcd1id, String pka039, String pka001);

}