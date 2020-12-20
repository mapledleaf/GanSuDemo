package com.powersi.biz.medicare.hosp.service.api;

import java.util.HashMap;
import java.util.List;

import com.powersi.biz.medicare.hosp.pojo.HospitalAccessLicenseDTO;
import com.powersi.biz.medicare.hosp.pojo.HospitalAccessPlanDTO;
import com.powersi.biz.medicare.hosp.pojo.HospitalAccessScheduleDTO;
import com.powersi.biz.medicare.hosp.pojo.HospitalAccessTypeDTO;

/**
 * 
 * @Description: 医院接入管理总控制类,API接口
 * @author zhos
 * @date 2016年12月15日 下午2:03:30
 *
 */
public interface HospAccessMgrBizService {

	/**
	 * 医院接入方式功能号
	 */
	String BIZC300001 = "BIZC300001";
	/**
	 * 医院接入计划功能号
	 */
	String BIZC300002 = "BIZC300002";
	/**
	 * 医院接入进度功能号
	 */
	String BIZC300003 = "BIZC300003";
	/**
	 * 医院接入许可发放情况功能号
	 */
	String BIZC300004 = "BIZC300004";

	/**
	 * 获取医院接入方式信息
	 */
	@SuppressWarnings("rawtypes")
	public HashMap getHosAccessTypeList(HospitalAccessTypeDTO hospitalAccessTypeDTO);

	/**
	 * 保存医院接入方式信息
	 */
	public String saveHosAccessType(HospitalAccessTypeDTO hospitalAccessTypeDTO);

	/**
	 * 更新医院接入方式信息
	 */
	public String updateHosAccessType(HospitalAccessTypeDTO hospitalAccessTypeDTO);

	/**
	 * 删除医院接入方式信息
	 */
	public int deleteHosAccessType(List<HospitalAccessTypeDTO> hospitalAccessTypeDTORows, String akb020);

	/**
	 * 获取医院接入计划信息
	 */
	@SuppressWarnings("rawtypes")
	public HashMap getHosAccessPlanList(HospitalAccessPlanDTO hospitalAccessPlanDTO, String akb020);

	/**
	 * 保存医院接入计划信息
	 */
	public String saveHosAccessPlan(HospitalAccessPlanDTO hospitalAccessPlanDTO, String akb020);

	/**
	 * 更新医院接入计划信息
	 */
	public String updateHosAccessPlan(HospitalAccessPlanDTO hospitalAccessPlanDTO, String akb020);

	/**
	 * 删除医院接入计划信息
	 */
	public int deleteHosAccessPlan(List<HospitalAccessPlanDTO> hospitalAccessPlanDTORows, String akb020);

	/**
	 * 获取医院接入进度信息
	 */
	@SuppressWarnings("rawtypes")
	public HashMap getHosAccessScheduleList(HospitalAccessScheduleDTO hospitalAccessScheduleDTO, String akb020);

	/**
	 * 保存医院接入进度信息
	 */
	public String saveHosAccessSchedule(HospitalAccessScheduleDTO hospitalAccessScheduleDTO, String akb020);

	/**
	 * 更新医院接入进度信息
	 */
	public String updateHosAccessSchedule(HospitalAccessScheduleDTO hospitalAccessScheduleDTO, String akb020);

	/**
	 * 删除医院接入进度信息
	 */
	public int deleteHosAccessSchedule(List<HospitalAccessScheduleDTO> hospitalAccessScheduleDTORows, String akb020);

	/**
	 * 获取医院接入许可信息
	 */
	@SuppressWarnings("rawtypes")
	public HashMap getHosAccessLicenseList(HospitalAccessLicenseDTO hospitalAccessLicenseDTO);

	/**
	 * 保存医院接入许可信息
	 */
	public String saveHosAccessLicense(HospitalAccessLicenseDTO hospitalAccessLicenseDTO);

	/**
	 * 更新医院接入许可信息
	 */
	public String updateHosAccessLicense(HospitalAccessLicenseDTO hospitalAccessLicenseDTO);

	/**
	 * 删除医院接入许可信息
	 */
	public int deleteHosAccessLicense(List<HospitalAccessLicenseDTO> hospitalAccessLicenseDTORows, String akb020);

}
