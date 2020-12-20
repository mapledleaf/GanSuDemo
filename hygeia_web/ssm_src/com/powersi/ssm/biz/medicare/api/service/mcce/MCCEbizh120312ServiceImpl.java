package com.powersi.ssm.biz.medicare.api.service.mcce;

import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.convert;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.process;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.processApi;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.inhospital.pojo.FundPayInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120312Service;

@Service
public class MCCEbizh120312ServiceImpl implements MCCEbizh120312Service {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3068962416615731990L;

	private static final String SERVICE_BEAN_ID = "mcce_bizh120312";

	@SuppressWarnings("rawtypes")
	@Override
	public List<FundPayInfoDTO> queryFundPay(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		if ("1".equals(inHospitalDTO.getAae139())) {
			inHospitalDTO.setBkm100("1");
			inHospitalDTO.setSearchTerm("fund");// 借用searchTerm作为异地就医查询费用还是基金的标识
			List<Map> listMap = processApi("Remote_BIZC131255", paramObj);
			inHospitalDTO.setSearchTerm(null);// 完璧归赵
			return convert(listMap, FundPayInfoDTO.class);
		} else {
			return process(SERVICE_BEAN_ID, "queryFundPay", paramObj);
		}
	}

}
