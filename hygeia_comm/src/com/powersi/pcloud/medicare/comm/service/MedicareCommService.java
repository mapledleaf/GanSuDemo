package com.powersi.pcloud.medicare.comm.service;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.webservice.WebServiceRequestParam;

/**
 * 
 * @author 刘勇
 *
 */
public interface MedicareCommService {

	/**
	 * 
	 * @param request
	 * @return
	 */
	public String getRequestParamKey(WebServiceRequestParam request);

	/**
	 * 
	 * @param inHospitalDTO
	 */
	public void loadEcdemicMedicalParam(InHospitalDTO inHospitalDTO);

	/**
	 * 
	 * @param request
	 */
	public void checkRequestParam(WebServiceRequestParam request);

	/**
	 * 是否有效的医院编号
	 * 
	 * @param akb020
	 *            医院编号
	 * @return
	 */
	public boolean isValidatedAkb020(String akb020);

	/**
	 * "440000201709081634550002680000000000000002"
	 * 
	 * @return 交易流水号(长度42)
	 */
	public String getTransedID();

	/**
	 * <pre>
	 * 接口事务管理（机制）：弱化事务，只对需要关注事务的外部请求，内部在对其响应时记录事务情况，并提供取消机制（后台重发请求中带冲减标识）；
	 * transid： uuid 统一由结算云生成或者结算云提供规则各系统自己生成
	 * 记录：执行标识 1成功 0失败
	 *     冲减标识 1成功 0失败
	 * 取消场景描述：
	 * 1、外部重发请求，内部响应：先核对 transid 是否存在且对应的执行标识是否成功如果成功则调度取消机制实现冲减；
	 * </pre>
	 * 
	 * @param akb020
	 *            医院编号
	 * @return "sr0001201709081634550002680000000000000002"
	 */
	public String getTransedID(String akb020);

	/**
	 * <pre>
	 * "sr0001201709081634550002680000000000000002"
	 * "440000201709081634550002680000000000000002"
	 * </pre>
	 * 
	 * @param transedID
	 * @return
	 */
	public boolean isValidatedTransedID(String transedID);

}
