package com.powersi.ssm.biz.medicare.api.service.mcce;

import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.convert;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.process;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.processApi;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120103Service;
import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DateFunc;

@Service
public class MCCEbizh120103ServiceImpl implements MCCEbizh120103Service {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8184101030034316580L;

	private static final String SERVICE_BEAN_ID = "mcce_bizh120103";
	
	@SuppressWarnings("rawtypes")
	@Override
	public InHospitalDTO saveRegisterInfo(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		if ("1".equals(inHospitalDTO.getAae139())) {
			checkconverReadCardBase(inHospitalDTO);
			String aae030 = inHospitalDTO.getAae030();
			inHospitalDTO.setAae030(DateFunc.dateToString(DateFunc.parseDate(aae030), DateService._yyyyMMddHHmmss));
			List<Map> listMap = processApi("Remote_BIZC131204", paramObj);
			List<InHospitalDTO> list = convert(listMap, InHospitalDTO.class);
			return list != null && !list.isEmpty() ? list.get(0) : null;
		} else {
			return process(SERVICE_BEAN_ID, "saveRegisterInfo", paramObj);
		}
	}
	
	
	private void checkconverReadCardBase(InHospitalDTO inHospitalDTO) {
		// ic_no 卡号  card_iden 卡识别码  idcard 身份证号码 sex 性别
		String aaz500 = "";
		String aaz501 = "";
		String bke548 = "";
		String aac002 = "";
		bke548 = inHospitalDTO.getBke548();
		if (StringUtils.isNotBlank(bke548)) {
			String[] params = bke548.split("\\|");
			if (params == null || params.length < 2) {
				throw new HygeiaException("读卡返回值格式错误(" + bke548 + ")!");
			}else {
				aac002 = params[1];
				aaz500 = params[2];
				aaz501 = params[3];
			}
			//封装朱立反馈的跨省异地少了的几个字段 by cj
			inHospitalDTO.setIdcard(aac002);
			inHospitalDTO.setBka100(aaz500);
			inHospitalDTO.setBke550(aaz501);
		}
	}

}
