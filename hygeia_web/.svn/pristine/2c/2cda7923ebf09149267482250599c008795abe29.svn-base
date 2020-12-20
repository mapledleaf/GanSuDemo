package com.powersi.ssm.biz.medicare.api.service.mcce;

import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.process;
import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.processApi;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.comm.pojo.MediDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120302Service;

@Service
public class MCCEbizh120302ServiceImpl implements MCCEbizh120302Service {

	private static final String SERVICE_BEAN_ID = "mcce_bizh120302";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public InHospitalDTO queryHospital(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "queryHospital", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map queryInsuredFund(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "queryInsuredFund", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List queryCumulative(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "queryCumulative", paramObj);
	}

	@Override
	public List<InHospitalDTO> getPersoninfo(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "getPersoninfo", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getPersoninfoList(MediDTO mediDTO) {
		Object[] paramObj = new Object[] { mediDTO };
		return process(SERVICE_BEAN_ID, "getPersoninfoList", paramObj);
	}

	@Override
	public InHospitalDTO getPersonLastInhospInfo(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "getPersonLastInhospInfo", paramObj);
	}

	@Override
	public InHospitalDTO saveReferralApplyInfo(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "saveReferralApplyInfo", paramObj);
	}

	@Override
	public List<InHospitalDTO> getChangeHospApplyList(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "getChangeHospApplyList", paramObj);
	}

	@Override
	public InHospitalDTO updateReferralApplyInfo(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "updateReferralApplyInfo", paramObj);
	}

	@Override
	public InHospitalDTO deleteReferralApplyInfo(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "deleteReferralApplyInfo", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List queryInsuredFundApi(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "queryInsuredFundApi", paramObj);
	}

	@Override
	public List<InHospitalDTO> queryInhospitalTransfer(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "queryInhospitalTransfer", paramObj);
	}

	@Override
	public List<InHospitalDTO> queryInhospitalTransferIn(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		return process(SERVICE_BEAN_ID, "queryInhospitalTransferIn", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> queryInsuredFundList(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] { inHospitalDTO };
		if("1".equals(inHospitalDTO.getAae139())) {
			return processApi("Remote_BIZC130103", paramObj);
		}else {
			return process(SERVICE_BEAN_ID, "queryInsuredFundList", paramObj);
		}
	}

}
