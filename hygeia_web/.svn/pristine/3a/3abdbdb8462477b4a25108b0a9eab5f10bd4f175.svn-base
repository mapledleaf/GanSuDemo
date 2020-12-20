package com.powersi.ssm.biz.medicare.api.service.mcce;

import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.convert;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.process;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.processApi;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120001Service;

@Service
public class MCCEbizh120001ServiceImpl implements MCCEbizh120001Service{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String SERVICE_BEAN_ID = "mcce_bizh120001";

	@SuppressWarnings("rawtypes")
	@Override
	public List<InHospitalDTO> searchPersonInfo(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		if("1".equals(inHospitalDTO.getAae139())) {//异地就医api接口
			List<Map> listMap = processApi("Remote_BIZC131201", paramObj);
			return convert(listMap, InHospitalDTO.class);
		}else {
			return process(SERVICE_BEAN_ID, "searchPersonInfo", paramObj);
		}
	}

}
