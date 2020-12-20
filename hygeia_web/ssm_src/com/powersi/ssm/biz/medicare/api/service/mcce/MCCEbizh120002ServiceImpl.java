package com.powersi.ssm.biz.medicare.api.service.mcce;

import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.convert;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.process;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.processApi;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120002Service;

@Service
public class MCCEbizh120002ServiceImpl implements MCCEbizh120002Service {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String SERVICE_BEAN_ID = "mcce_bizh120002";

	@SuppressWarnings("rawtypes")
	@Override
	public InHospitalDTO checkAndSaveFeeInfo(InHospitalDTO inHospitalDTO, List<InHospitalDTO> inHospitalDTORows) {
		Object[] paramObj = new Object[] { inHospitalDTO, inHospitalDTORows };
		if ("1".equals(inHospitalDTO.getAae139())) {
			for(InHospitalDTO row : inHospitalDTORows) {
				if(StringUtils.isBlank(row.getAke002())) {
					row.setAke002(row.getAke006());
				}
			}
			List<Map> listMap = processApi("Remote_BIZC131252", paramObj);
			List<InHospitalDTO> list = convert(listMap, InHospitalDTO.class);
			return list != null && !list.isEmpty() ? list.get(0) : null;
		} else {
			return process(SERVICE_BEAN_ID, "checkAndSaveFeeInfo", paramObj);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public InHospitalDTO checkAndSaveFeeInfo(InHospitalDTO inHospitalDTO, List<InHospitalDTO> inHospitalDTORows,
			List<Map> feeinfoRowsScale) {
		Object[] paramObj = new Object[] { inHospitalDTO, inHospitalDTORows, feeinfoRowsScale };
		return process(SERVICE_BEAN_ID, "checkAndSaveFeeInfo", paramObj);
	}

	@Override
	public InHospitalDTO proccessFeeInfoSnyd(InHospitalDTO inHospitalDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InHospitalDTO checkAndSaveFeeInfoCommnInterface(InHospitalDTO inHospitalDTO,
			List<InHospitalDTO> inHospitalDTORows) {
		// TODO Auto-generated method stub
		return null;
	}

}
