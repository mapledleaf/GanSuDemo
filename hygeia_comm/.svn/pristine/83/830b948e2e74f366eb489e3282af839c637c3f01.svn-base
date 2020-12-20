package com.powersi.biz.medicare.iccard.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.iccard.pojo.ICInfoDTO;
import com.powersi.biz.medicare.iccard.pojo.SSCardDTO;
import com.powersi.webservice.WebServiceRequestParam;

/**
 * 
 * @author 刘勇
 *
 */
public interface SSCardService extends java.io.Serializable {

	/**
	 * <pre>
	 * 1、需要检查读卡许可证的功能号；
	 * 2、对于不同地区同一个功能号是否需要拦截的处理；
	 * 3、检查请求的参数，是否是这个卡号对应的人员，如果不是，抛出异常，非法的持卡就诊登记许可号，请确保使用本人社保卡就诊；
	 * 4、结算类交易需保存就诊信息的交易TAC码；
	 * </pre>
	 * 
	 * @param dto
	 * @param request
	 */
	public void doICFilter(SSCardDTO dto, WebServiceRequestParam request);
	
	/**
	 * 调用中心BIZC200903功能号修改卡密码
	 * @param params
	 * @return
	 */
	public boolean ylcardPswModify(ICInfoDTO icInfo);
	
	/**
	 * 调用中心BIZC200904功能号挂失、启用卡
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> cardReportLoss(Map<String, Object> params);

	@SuppressWarnings("rawtypes")
	public Map findActiveCardData(Map param);
	
	@SuppressWarnings("rawtypes")
	public int SaveSicCardInfo(Map param);
}
