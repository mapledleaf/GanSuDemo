package com.powersi.biz.medicare.inhospital.service.api.mcce;

import java.util.List;
import com.powersi.biz.medicare.inhospital.pojo.AppointmentInfoDTO;

/**
 * 
 * bizh310003 上传预约挂号（或取消预挂号）结果
 * 
 * @author zhos
 *
 */
public interface MCCEbizh310003Service extends MCCEsbService {

	public String serviceid = mcce_ + "bizh310003";

	/**
	 *
	 * 保存预约挂号（取消预挂号）结果
	 * 
	 */
	public AppointmentInfoDTO saveAppointmentInfos(AppointmentInfoDTO appointmentInfoDTO,
			List<AppointmentInfoDTO> appointmentInfoDTORows);

	/**
	 * 提交预约挂号（或取消预约挂号）
	 */
	public AppointmentInfoDTO saveOrCancelYygh(AppointmentInfoDTO dto);
	
}
