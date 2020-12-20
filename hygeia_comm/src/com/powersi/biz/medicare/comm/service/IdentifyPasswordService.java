package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;


public interface IdentifyPasswordService extends Serializable {
	
	/**
	 *身份证密码校验
	 */
	public Map<String,Object> checkPassword(InHospitalDTO inHospitalDTO); 
	
	/**
	 * 校验银行卡信息（康佳）
	 * @param inHospitalDTO
	 * @return
	 */
	public Map<String ,Object> checkBankCard(InHospitalDTO inHospitalDTO);
	
	/**
	 * 掉公共社保卡激活接口
	 */
	@SuppressWarnings("rawtypes")
	public Map queryPersonCardInfo(InHospitalDTO inHospitalDTO);
	
	/**
	 * 查询社保卡信息
	 */
	public ListResult queryICcardInfo(InHospitalDTO inHospitalDTO);
	
	/**
	 * 社保卡激活查询医保信息（取基本信息）
	 * @param inHospitalDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryAC01Info(InHospitalDTO inHospitalDTO);
	
	/**
	 * 读卡鉴权
	 */
	public int icCardAuthCards(InHospitalDTO inHospitalDTO);
	
	/**
	 * 单获取人员信息，不做任何校验
	 * @param inHospitalDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryPersonInfo(InHospitalDTO inHospitalDTO);
	
	/**
	 * 社保卡领卡点查询
	 * @param inHospitalDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> cardReceiveQueryManage(InHospitalDTO inHospitalDTO);

	/**
	 * 解析二维码信息
	 * @param inHospitalDTO
	 * @return
	 */
	public Map<String, Object> queryPersonQrcode(InHospitalDTO inHospitalDTO);
	
	public Map<String,Object> checkIcCard(InHospitalDTO inHospitalDTO); 
	
}
