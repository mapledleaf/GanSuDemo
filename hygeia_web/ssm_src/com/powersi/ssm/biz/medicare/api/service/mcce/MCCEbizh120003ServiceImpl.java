package com.powersi.ssm.biz.medicare.api.service.mcce;

import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.convert;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.process;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.processApi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120003Service;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;

@Service
public class MCCEbizh120003ServiceImpl implements MCCEbizh120003Service {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String SERVICE_BEAN_ID = "mcce_bizh120003";

	@Autowired
	private BeanService beanService;

	@SuppressWarnings("rawtypes")
	@Override
	public InHospitalDTO checkAndCalcFeeInfo(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		if ("1".equals(inHospitalDTO.getAae139())) {
			inHospitalDTO.setSearchTerm("fee");// 借用searchTerm作为异地就医查询费用还是基金的标识
			List<Map> listMap = processApi("Remote_BIZC131255", paramObj);
			List<InHospitalDTO> list = convert(listMap, InHospitalDTO.class);
			inHospitalDTO.setHospInfoflag(null);
			if (list != null && !list.isEmpty()) {
				InHospitalDTO tepDTO = list.get(0);
				beanService.copyProperties(tepDTO, inHospitalDTO, true);
			}
			inHospitalDTO.setSearchTerm(null);// 完璧归赵
			return inHospitalDTO;
		} else {
			return process(SERVICE_BEAN_ID, "checkAndCalcFeeInfo", paramObj);
		}
	}

}
