package com.powersi.ssm.biz.medicare.api.service.mcce;

import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.convert;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.process;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.processApi;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.comm.pojo.MediDTO;
import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.pojo.Kcg4DTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120102Service;
import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.hygeia.framework.util.DateFunc;

@Service
public class MCCEbizh120102ServiceImpl implements MCCEbizh120102Service {

	private static final String SERVICE_BEAN_ID = "mcce_bizh120102";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public InHospitalDTO queryBizInfo(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "queryBizInfo", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public InHospitalDTO queryAaz217(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		if ("1".equals(inHospitalDTO.getAae139())) {// 异地就医api接口
			List<Map> listMap = processApi("Remote_BIZC131251", paramObj);
			List<InHospitalDTO> list = convert(listMap, InHospitalDTO.class);
			return list != null && !list.isEmpty() ? list.get(0) : null;
		} else {
			return process(SERVICE_BEAN_ID, "queryAaz217", paramObj);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<InHospitalDTO> queryInHospitalaaz217s(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		if ("1".equals(inHospitalDTO.getAae139())) {
			List<Map> listMap;
			if ("0".equals(inHospitalDTO.getBka891())) {// 未结算业务
				listMap = processApi("Remote_BIZC131251", paramObj);
				// 已结算业务
			} else {
				// 转换日期格式
				String aae030 = inHospitalDTO.getAae030();
				String aae031 = inHospitalDTO.getAae031();
				inHospitalDTO.setAae030(DateFunc.dateToString(DateFunc.parseDate(aae030), DateService._yyyyMMdd));
				inHospitalDTO.setAae031(DateFunc.dateToString(DateFunc.parseDate(aae031), DateService._yyyyMMdd));
				listMap = processApi("Remote_BIZC131258", paramObj);
			}
			return convert(listMap, InHospitalDTO.class);
		} else {
			return process(SERVICE_BEAN_ID, "queryInHospitalaaz217s", paramObj);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List> querySettlementReportData(MediDTO mediDTO) {
		Object[] paramObj = new Object[] { mediDTO };
		return process(SERVICE_BEAN_ID, "querySettlementReportData", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List<Map>> loadSettlementReportDataInHospFirst(MediDTO mediDTO) {
		Object[] paramObj = new Object[] { mediDTO };
		return process(SERVICE_BEAN_ID, "loadSettlementReportDataInHospFirst", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List<Map>> loadSettlementReportDataInHospSecond(MediDTO mediDTO) {
		Object[] paramObj = new Object[] { mediDTO };
		return process(SERVICE_BEAN_ID, "loadSettlementReportDataInHospSecond", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List<Map>> loadSettlementReportDataInHospThree(MediDTO mediDTO) {
		Object[] paramObj = new Object[] { mediDTO };
		return process(SERVICE_BEAN_ID, "loadSettlementReportDataInHospThree", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List<Map>> loadSettlementReportDataClinic(MediDTO mediDTO) {
		Object[] paramObj = new Object[] { mediDTO };
		return process(SERVICE_BEAN_ID, "loadSettlementReportDataClinic", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List<Map>> loadSettlementReportDataInHospKsydMove(MediDTO mediDTO) {
		Object[] paramObj = new Object[] { mediDTO };
		return process(SERVICE_BEAN_ID, "loadSettlementReportDataInHospKsydMove", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List<Map>> loadSettlementReportDataInHospKsyd(MediDTO mediDTO) {
		Object[] paramObj = new Object[] { mediDTO };
		return process(SERVICE_BEAN_ID, "loadSettlementReportDataInHospKsyd", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List<Map>> loadSettlementReportDataInHospSnyd(MediDTO mediDTO) {
		Object[] paramObj = new Object[] { mediDTO };
		return process(SERVICE_BEAN_ID, "loadSettlementReportDataInHospSnyd", paramObj);
	}

	@Override
	public double selectSumAae019(MediDTO mediDTO) {
		Object[] paramObj = new Object[] { mediDTO };
		return process(SERVICE_BEAN_ID, "selectSumAae019", paramObj);
	}

	@Override
	public void isExistBizInfo(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		process(SERVICE_BEAN_ID, "isExistBizInfo", paramObj);
	}

	@Override
	public int updateFallBackApply(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "updateFallBackApply", paramObj);
	}

	@Override
	public void checkBiz(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		process(SERVICE_BEAN_ID, "checkBiz", paramObj);
	}

	@Override
	public List<Kcg4DTO> loadDiagnoseInfosByAaz217(String aaz217, String akb020) {
		Object[] paramObj = new Object[] { aaz217, akb020 };
		return process(SERVICE_BEAN_ID, "loadDiagnoseInfosByAaz217", paramObj);
	}
	
	//TS19112700299    新增取差别化待遇支付标志  add by zyx 2019/12/04
	@Override
	public String selectAka241(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "selectAka241", paramObj);
	}
	
	//TS19112600047       刷卡前一个月消费记录  add by zyx 2019/12/04	
	@Override
	public List selectChargeList(DiagnoseInfoDTO diagnoseInfoDTO) {
	    Object[] paramObj = new Object[] {diagnoseInfoDTO};
		return process(SERVICE_BEAN_ID, "selectChargeList", paramObj);
	}


}
