package com.powersi.ssm.biz.medicare.api.service.mcce;

import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.convert;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.process;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.processApi;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120109Service;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

@Service
public class MCCEbizh120109ServiceImpl implements MCCEbizh120109Service {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String SERVICE_BEAN_ID = "mcce_bizh120109";

	@SuppressWarnings("rawtypes")
	@Override
	public InHospitalDTO cancelRegister(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		if ("1".equals(inHospitalDTO.getAae139())) {
			inHospitalDTO.setAae011(BizHelper.getLoginUser());
			inHospitalDTO.setBka015(BizHelper.getUserName());
			List<Map> listMap = processApi("Remote_BIZC131206", paramObj);
			List<InHospitalDTO> list = convert(listMap, InHospitalDTO.class);
			return list != null && !list.isEmpty() ? list.get(0) : null;
		} else {
			return process(SERVICE_BEAN_ID, "cancelRegister", paramObj);
		}
	}

}
