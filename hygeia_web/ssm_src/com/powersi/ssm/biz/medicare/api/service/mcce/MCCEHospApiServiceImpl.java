package com.powersi.ssm.biz.medicare.api.service.mcce;

import static com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor.process;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.hosp.pojo.Bkn2Dto;
import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;
import com.powersi.biz.medicare.hosp.service.api.HospApiService;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

@Service("mCCEHospApiServiceImpl")
public class MCCEHospApiServiceImpl implements HospApiService {
	
	private static final String SERVICE_BEAN_ID = "hospApiService";
	
	@Override
	public List<HospInfoDTO> queryHospDept(String akb020, HospInfoDTO hospInfoDto) {
		Object[] paramObj = new Object[] {akb020, hospInfoDto};
		return process(SERVICE_BEAN_ID, "queryHospDept", paramObj);
	}
	 
	@Override
	public List<HospInfoDTO> queryHospArea(String akb020, HospInfoDTO hospInfoDto) {
		Object[] paramObj = new Object[] {akb020, hospInfoDto};
		return process(SERVICE_BEAN_ID, "queryHospArea", paramObj);
	}

	@Override
	public ListResult queryAreaPage(String akb020, HospInfoDTO hospInfoDto) {
		Object[] paramObj = new Object[] {akb020, hospInfoDto};
		return process(SERVICE_BEAN_ID, "queryAreaPage", paramObj);
	}

	@Override
	public int saveHospArea(String akb020, HospInfoDTO hospInfoDto) {
		Object[] paramObj = new Object[] {akb020, hospInfoDto};
		return process(SERVICE_BEAN_ID, "saveHospArea", paramObj);
	}

	@Override
	public int deleteArea(HospInfoDTO hospInfoDto, List<Map<String, Object>> detail) {
		Object[] paramObj = new Object[] {hospInfoDto, detail};
		return process(SERVICE_BEAN_ID, "deleteArea", paramObj);
	}

	@Override
	public ListResult queryDeptPage(String akb020, HospInfoDTO hospInfoDto) {
		Object[] paramObj = new Object[] {akb020, hospInfoDto};
		return process(SERVICE_BEAN_ID, "queryDeptPage", paramObj);
	}

	@Override
	public int saveHospDept(String akb020, HospInfoDTO hospInfoDto) {
		Object[] paramObj = new Object[] {akb020, hospInfoDto};
		return process(SERVICE_BEAN_ID, "saveHospDept", paramObj);
	}

	@Override
	public int updateHospDept(String akb020, HospInfoDTO hospInfoDto) {
		Object[] paramObj = new Object[] {akb020, hospInfoDto};
		return process(SERVICE_BEAN_ID, "updateHospDept", paramObj);
	}

	@Override
	public int delHospDeptAll(HospInfoDTO hospInfoDto, List<Map<String, Object>> detail) {
		Object[] paramObj = new Object[] {hospInfoDto, detail};
		return process(SERVICE_BEAN_ID, "delHospDeptAll", paramObj);
	}

	@Override
	public int updateHospDoctor(String akb020, HospInfoDTO hospInfoDto) {
		Object[] paramObj = new Object[] {akb020, hospInfoDto};
		return process(SERVICE_BEAN_ID, "updateHospDoctor", paramObj);
	}

	@Override
	public int saveHospDoctor(String akb020, HospInfoDTO hospInfoDto) {
		Object[] paramObj = new Object[] {akb020, hospInfoDto};
		return process(SERVICE_BEAN_ID, "saveHospDoctor", paramObj);
	}

	@Override
	public List<HospInfoDTO> queryHospDoctor(String akb020, HospInfoDTO hospInfoDto) {
		Object[] paramObj = new Object[] {akb020, hospInfoDto};
		return process(SERVICE_BEAN_ID, "queryHospDoctor", paramObj);
	}

	@Override
	public ListResult queryDoctorPage(String akb020, HospInfoDTO hospInfoDto) {
		Object[] paramObj = new Object[] {akb020, hospInfoDto};
		return process(SERVICE_BEAN_ID, "queryDoctorPage", paramObj);
	}

	@Override
	public List<HospInfoDTO> queryHospBed(String akb020, HospInfoDTO hospInfoDto) {
		Object[] paramObj = new Object[] {akb020, hospInfoDto};
		return process(SERVICE_BEAN_ID, "queryHospBed", paramObj);
	}

	@Override
	public ListResult queryBedPage(String akb020, HospInfoDTO hospInfoDto) {
		Object[] paramObj = new Object[] {akb020, hospInfoDto};
		return process(SERVICE_BEAN_ID, "queryBedPage", paramObj);
	}

	@Override
	public int saveHospBed(String akb020, HospInfoDTO hospInfoDto) {
		Object[] paramObj = new Object[] {akb020, hospInfoDto};
		return process(SERVICE_BEAN_ID, "saveHospBed", paramObj);
	}

	@Override
	public int saveHospBedDr(HospInfoDTO hospInfoDto, List<Map<String, Object>> detail) {
		Object[] paramObj = new Object[] {hospInfoDto, detail};
		return process(SERVICE_BEAN_ID, "saveHospBedDr", paramObj);
	}

	@Override
	public int saveHospDoctorDr(HospInfoDTO hospInfoDto, List<Map<String, Object>> detail) {
		Object[] paramObj = new Object[] {hospInfoDto, detail};
		return process(SERVICE_BEAN_ID, "saveHospDoctorDr", paramObj);
	}

	@Override
	public int updateHospBed(String akb020, HospInfoDTO hospInfoDto) {
		Object[] paramObj = new Object[] {akb020, hospInfoDto};
		return process(SERVICE_BEAN_ID, "updateHospBed", paramObj);
	}

	@Override
	public int delHospDoctorAll(HospInfoDTO hospInfoDto, List<Map<String, Object>> detail) {
		Object[] paramObj = new Object[] {hospInfoDto, detail};
		return process(SERVICE_BEAN_ID, "delHospDoctorAll", paramObj);
	}

	@Override
	public int delHospBedAll(HospInfoDTO hospInfoDto, List<Map<String, Object>> list) {
		Object[] paramObj = new Object[] {hospInfoDto, list};
		return process(SERVICE_BEAN_ID, "delHospBedAll", paramObj);
	}

	@Override
	public int updateBedBkc171(String akb020, String bkc153, String bkc161, String bkc171) {
		Object[] paramObj = new Object[] {akb020, bkc153, bkc161, bkc171};
		return process(SERVICE_BEAN_ID, "updateBedBkc171", paramObj);
	}

	@Override
	public List<Bkn2Dto> selectByBkn2Aaz217(InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {inHospitalDTO};
		return process(SERVICE_BEAN_ID, "selectByBkn2Aaz217", paramObj);
	}

	@Override
	public int saveBkn2Update(Bkn2Dto bkn2Dto) {
		Object[] paramObj = new Object[] {bkn2Dto};
		return process(SERVICE_BEAN_ID, "saveBkn2Update", paramObj);
	}

	@Override
	public int saveBkn2(Bkn2Dto bkn2Dto, InHospitalDTO inHospitalDTO) {
		Object[] paramObj = new Object[] {bkn2Dto, inHospitalDTO};
		return process(SERVICE_BEAN_ID, "saveBkn2", paramObj);
	}

	@Override
	public Bkn2Dto selectByPrimaryKey(String akb020, Integer id) {
		Object[] paramObj = new Object[] {akb020, id};
		return process(SERVICE_BEAN_ID, "selectByPrimaryKey", paramObj);
	}

	@Override
	public int saveDoctorChangApply(HospInfoDTO hospInfoDTO) {
		Object[] paramObj = new Object[] {hospInfoDTO};
		return process(SERVICE_BEAN_ID, "saveDoctorChangApply", paramObj);
	}
}
