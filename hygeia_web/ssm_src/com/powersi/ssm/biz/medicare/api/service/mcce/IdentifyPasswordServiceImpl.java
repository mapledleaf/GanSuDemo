package com.powersi.ssm.biz.medicare.api.service.mcce;


import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.process;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.service.IdentifyPasswordService;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

@Service
public class IdentifyPasswordServiceImpl implements IdentifyPasswordService{

	private static final String SERVICE_BEAN_ID = "mcccBizhCommonService";
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 校验身份证密码
	 */
	@Override
	public Map<String,Object> checkPassword(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		return process(SERVICE_BEAN_ID, "checkPassword", paramObj);
	}
	
	@Override
	public Map<String,Object> checkIcCard(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		return process(SERVICE_BEAN_ID, "checkIcCard", paramObj);
	}
	
	@Override
	public Map<String, Object> checkBankCard(InHospitalDTO inHospitalDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map queryPersonCardInfo(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		return process(SERVICE_BEAN_ID, "queryPersonCardInfo", paramObj);
	}

	@Override
	public ListResult queryICcardInfo(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		return process(SERVICE_BEAN_ID, "queryICcardInfo", paramObj);
	}

	@Override
	public int icCardAuthCards(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
	    return process(SERVICE_BEAN_ID, "icCardAuthCards", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List queryAC01Info(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		return process(SERVICE_BEAN_ID, "queryAC01Info", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> queryPersonInfo(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		return process(SERVICE_BEAN_ID, "queryPersonInfo", paramObj);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Map> cardReceiveQueryManage(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		return process(SERVICE_BEAN_ID, "cardReceiveQueryManage", paramObj);
	}

	@Override
	public Map<String, Object> queryPersonQrcode(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		return process(SERVICE_BEAN_ID, "queryPersonQrcode", paramObj);
	}

}
