package com.powersi.ssm.biz.medicare.api.service.mcce;

import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.process;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.processApi;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.pojo.ListResultDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120203Service;
import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.comm.utils.UtilFunc;
import com.powersi.hygeia.framework.util.DateFunc;

@Service
public class MCCEbizh120203ServiceImpl implements MCCEbizh120203Service {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8853652387162754492L;

	private static final String SERVICE_BEAN_ID = "mcce_bizh120203";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ListResult querySavedFee(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		if ("1".equals(inHospitalDTO.getAae139())) {
			List<Map> listMap = processApi("Remote_BIZC131253", paramObj);
			for(Map map : listMap) {
				map.put("bka892", "1");
				String ake007Str = UtilFunc.getString(map, "ake007");
				map.put("ake007", DateFunc.dateToString(DateFunc.parseDate(ake007Str), DateService.yyyyMMdd));
			}
			return ListResultDTO.newInstance().setCount(inHospitalDTO.getTotalResult()).setList(listMap);
		} else {
			return process(SERVICE_BEAN_ID, "querySavedFee", paramObj);
		}
	}
}
