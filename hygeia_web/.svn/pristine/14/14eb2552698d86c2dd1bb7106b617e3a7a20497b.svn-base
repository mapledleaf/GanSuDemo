package com.powersi.ssm.biz.medicare.api.service.mcce;

import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.convert;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.process;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.processApi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120106Service;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.hygeia.framework.util.UtilFunc;

@Service
public class MCCEbizh120106ServiceImpl implements MCCEbizh120106Service {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String SERVICE_BEAN_ID = "mcce_bizh120106";

	@Autowired
	private BeanService beanService;

	@SuppressWarnings("rawtypes")
	@Override
	public InHospitalDTO outCharge(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		if ("1".equals(inHospitalDTO.getAae139())) {
			checkconverReadCardBase(inHospitalDTO);
			inHospitalDTO.setSearchTerm("fee");// 借用searchTerm作为异地就医查询费用还是基金的标识
			List<Map> listMap = processApi("Remote_BIZC131256", paramObj);
			List<InHospitalDTO> list = convert(listMap, InHospitalDTO.class);
			inHospitalDTO.setHospInfoflag(null);
			if (list != null && !list.isEmpty()) {
				InHospitalDTO tepDTO = list.get(0);
				beanService.copyProperties(tepDTO, inHospitalDTO, true);
			}
			inHospitalDTO.setSearchTerm(null);// 完璧归赵
			return inHospitalDTO;

		} else {
			return process(SERVICE_BEAN_ID, "outCharge", paramObj);
		}
	}
	
	private void checkconverReadCardBase(InHospitalDTO inHospitalDTO) {
		// ic_no 卡号  card_iden 卡识别码  idcard 身份证号码 sex 性别
		//封装朱立反馈的跨省异地少了的几个字段 by cj
		if (UtilFunc.isNotBlank(inHospitalDTO.getAaz500())) {
			inHospitalDTO.setBka100(inHospitalDTO.getAaz500().toString());
		}
		if (UtilFunc.isNotBlank(inHospitalDTO.getBke550())) {
			inHospitalDTO.setBke550(inHospitalDTO.getBke550().toString());
		}
		if (UtilFunc.isNotBlank(inHospitalDTO.getAac002())) {
			inHospitalDTO.setIdcard(inHospitalDTO.getAac002().toString());
		}
	}
}
