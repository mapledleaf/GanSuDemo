package com.powersi.biz.medicare.inhospital.service.api.mcce;

import java.util.List;
import com.powersi.biz.medicare.inhospital.pojo.DoctorSourceInfoDTO;

/**
 * 
 * bizh310001 上传医院预约挂号号源
 * 
 * @author zhos
 *
 */
public interface MCCEbizh310001Service extends MCCEsbService {

	public String serviceid = mcce_ + "bizh310001";

	public DoctorSourceInfoDTO saveDoctorSourceInfoDTOs(DoctorSourceInfoDTO doctorSourceInfoDTO,
			List<DoctorSourceInfoDTO> doctorSourceInfoDTORows);

	public List<DoctorSourceInfoDTO> selectDoctorSourceInfos(DoctorSourceInfoDTO doctorSourceInfoDTO);
	
}
