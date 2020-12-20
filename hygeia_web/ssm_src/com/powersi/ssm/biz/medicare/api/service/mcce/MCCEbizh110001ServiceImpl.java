package com.powersi.ssm.biz.medicare.api.service.mcce;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.diagnose.service.api.mcce.MCCEbizh110001Service;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.*;

@Service
public class MCCEbizh110001ServiceImpl implements MCCEbizh110001Service{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String SERVICE_BEAN_ID = "mcce_bizh110001";

	@SuppressWarnings("rawtypes")
	@Override
	public Map searchPersonInfo(DiagnoseInfoDTO diagnoseInfoDTO) {
		Object[] paramObj = new Object[] {diagnoseInfoDTO};
		return process(SERVICE_BEAN_ID, "searchPersonInfo", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getPersonList(DiagnoseInfoDTO diagnoseInfoDTO) {
		Object[] paramObj = new Object[] {diagnoseInfoDTO};
		return process(SERVICE_BEAN_ID, "getPersonList", paramObj);
	}

}
