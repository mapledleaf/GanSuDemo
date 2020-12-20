package com.powersi.ssm.biz.medicare.api.service.mcce;

import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.convert;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.process;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.processApi;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.inhospital.pojo.BizDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120104Service;
import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.biz.medicare.query.dto.KCD1Log;
import com.powersi.hygeia.framework.util.DateFunc;

@Service
public class MCCEbizh120104ServiceImpl implements MCCEbizh120104Service {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String SERVICE_BEAN_ID = "mcce_bizh120104";

	@Override
	public void updateInHospitalDTO(BizDTO bizDTO) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("rawtypes")
	@Override
	public InHospitalDTO modifyRegisterInfo(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		if ("1".equals(inHospitalDTO.getAae139())) {
			String aae030 = inHospitalDTO.getAae030();
			inHospitalDTO.setAae030(DateFunc.dateToString(DateFunc.parseDate(aae030), DateService._yyyyMMddHHmmss));
			List<Map> listMap = processApi("Remote_BIZC131205", paramObj);
			List<InHospitalDTO> list = convert(listMap, InHospitalDTO.class);
			return list != null && !list.isEmpty() ? list.get(0) : null;
		} else {
			return process(SERVICE_BEAN_ID, "modifyRegisterInfo", paramObj);
		}
	}

	@Override
	public List<KCD1Log> selectKcd1Log(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "selectKcd1Log", paramObj);
	}

}
