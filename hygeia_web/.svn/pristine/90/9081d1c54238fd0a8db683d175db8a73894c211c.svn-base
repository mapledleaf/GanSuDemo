package com.powersi.ssm.biz.medicare.api.service.mcce;

import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.process;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.comm.pojo.KA06;
import com.powersi.biz.medicare.comm.pojo.KA06DTO;
import com.powersi.biz.medicare.comm.pojo.KA14DTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.service.DiseaseQueryService;
import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.special.pojo.MediSpec_ZH_DTO;

@Service
public class DiseaseQueryServiceesbImpl implements DiseaseQueryService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String SERVICE_BEAN_ID="diseaseQueryServiceesb";
	@Override
	public ListResult queryDisease(KA06DTO ka06dto) {
		Object[] paramObj = new Object[] {ka06dto};
		return process(SERVICE_BEAN_ID, "queryDisease", paramObj);
	}

	@Override
	public KA06 queryDisease(String aaa027, String aka120) {
		Object[] paramObj = new Object[] {aaa027, aka120};
		return process(SERVICE_BEAN_ID, "queryDisease", paramObj);
	}

	@Override
	public List<KA06> getAka120Datas(MediSpec_ZH_DTO mediSpecZHDto) {
		Object[] paramObj = new Object[] {mediSpecZHDto};
		return process(SERVICE_BEAN_ID, "getAka120Datas", paramObj);
	}

	@Override
	public List<KA06DTO> querybke216(KA06DTO ka06dto) {
		Object[] paramObj = new Object[] {ka06dto};
		return process(SERVICE_BEAN_ID, "querybke216", paramObj);
	}

	@Override
	public String is106Disease(KA06DTO ka06dto) {
		Object[] paramObj = new Object[] {ka06dto};
		return process(SERVICE_BEAN_ID, "is106Disease", paramObj);
	}
	
	@Override
	public ListResult choose106SubDisease(KA06DTO ka06dto) {
		Object[] paramObj = new Object[] {ka06dto};
		return process(SERVICE_BEAN_ID, "choose106SubDisease", paramObj);
	}

	@Override
	public List<Map<String, Object>> checkSingleDiseaseLimit(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		return process(SERVICE_BEAN_ID, "checkSingleDiseaseLimit", paramObj);
	}
	
	@Override
	public ListResult queryOperation(KA14DTO ka14dto) {
		Object[] paramObj = new Object[] {ka14dto};
		return process(SERVICE_BEAN_ID, "queryOperation", paramObj);
	}

	@Override
	public List<KA06DTO> getMaternityGradeOneDisease(KA06DTO ka06Dto) {
		Object[] paramObj = new Object[] {ka06Dto};
		return process(SERVICE_BEAN_ID, "getMaternityGradeOneDisease", paramObj);
	}
	
	//TS19121900162 - 【问题修复】需求测试_湘潭_门特疾病限额没有按年度1800封顶是按审核录入的限额处理的_add by ljl 20191220_查询是否两病内病种
	@Override
	public List<Map<String, Object>>  queryTwoDiseaseInfo(DiagnoseInfoDTO diagnoseInfoDTO) {
		Object[] paramObj = new Object[] {diagnoseInfoDTO};
		return process(SERVICE_BEAN_ID, "queryTwoDiseaseInfo", paramObj);
	}
	
	/*TS20010700083  【需求开发】湘潭启用新版疾病目录相关处理  陈洁 20191226
     * 将传入疾病在配置表中心进行查询转换contrast
     * 国临2.0诊断-》新版诊断1.0
     * 老版疾病-》新版诊断1.0
     * */
	@Override
	public List<Map<String, Object>> getNewdiseaseEdition(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		return process(SERVICE_BEAN_ID, "getNewdiseaseEdition", paramObj);
	}
	
	@Override
	public List<Map<String, Object>> getNewdiseaseEdition10(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		return process(SERVICE_BEAN_ID, "getNewdiseaseEdition10", paramObj);
	}
	
	@Override
	public List<Map<String, Object>> getNewdiseaseEditionold(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		return process(SERVICE_BEAN_ID, "getNewdiseaseEditionold", paramObj);
	}
	
	@Override
	public List<Map<String, Object>> getNewdiseaseEditionforedit(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		return process(SERVICE_BEAN_ID, "getNewdiseaseEditionforedit", paramObj);
	}
	
	//TS20031600120   【需求开发】结算云多诊断上传优化  本地只做type2数据转换  赵银溪 20200316
	@Override
	public List<Map<String, Object>> getNewdiseaseForLocal(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		return process(SERVICE_BEAN_ID, "getNewdiseaseForLocal", paramObj);
	}
}