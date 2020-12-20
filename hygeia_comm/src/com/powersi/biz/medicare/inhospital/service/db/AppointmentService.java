package com.powersi.biz.medicare.inhospital.service.db;

import java.util.List;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.AppointmentExceptionInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.AppointmentInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.BizDTO;
import com.powersi.biz.medicare.inhospital.pojo.DoctorSourceInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.DrugWindowInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 
  * @Description: 预约挂号业务db接口类
  * @author zhos
  * @date 2016年12月2日 上午9:44:17
  *
 */
public interface AppointmentService {
	
	/**
	 * 处理上传医院预约挂号号源
	 * HIS接口调用
	 */
	public void processSaveDoctorsource(List<DoctorSourceInfoDTO> insertDTORows,List<DoctorSourceInfoDTO> updateDTORows);
	
	/**
	 * 
	 * 插入医院预约挂号号源
	 * @return
	 */
	public int insertDoctorSourceList(List<DoctorSourceInfoDTO> doctorSourceInfoDTOs);
	
	/**
	 * 
	 * 获取医院预约挂号（或者取消挂号）信息
	 * @return
	 */
	public List<AppointmentInfoDTO> selectAppointmentInfo(AppointmentInfoDTO dto);
	
	/**
	 * 
	 * 上传预约挂号（或取消预挂号）结果
	 * HIS接口调用(更新预约挂号信息和预约挂号号源信息)
	 * @return
	 */
	public void processSaveOrCancelYYghResultByHIS(List<AppointmentInfoDTO> appointmentInfoDTOs,List<DoctorSourceInfoDTO> doctorSourceInfoDTOs);
	
	/**
	 * 
	 * 上传预约挂号停诊异动信息
	 * @return
	 */
	public int insertExcepList(List<AppointmentExceptionInfoDTO> appointmentExceptionInfoDTOs);
	
	/**
	 * 
	 * 获取预约挂号号源信息-WS
	 * @return List<DoctorSourceInfoDTO>    返回类型
	 */
	public List<DoctorSourceInfoDTO> selectDoctorSourceInfo(DoctorSourceInfoDTO dto);
	
	/**
	 * 
	 * 更新预约挂号号源信息
	 * @return int    返回类型
	 */
	public int updateDoctorSourceBatch(List<DoctorSourceInfoDTO> doctorSourceInfoDTORows);
	
	/**
	 * 
	 * 批量更新预约挂号信息表
	 * @return int 返回类型
	 */
	public int updateAppointmentBatch(List<AppointmentInfoDTO> appointmentInfoDTORows);
	
	/**
	 * 提交预约挂号
	 * @Desc 提供给YBWS接口使用-需新增一条挂号信息记录
	 */
	public int insertAppointmentByOne(AppointmentInfoDTO dto);
	
	/**
	 * 取消预约挂号
	 * @Desc 提供给YBWS接口使用-需更新一条挂号信息记录
	 */
	public int updateAppointmentByOne(AppointmentInfoDTO dto);
	
	/**
	 * 更新挂号号源表
	 * @Desc 提供给YBWS接口使用-更新一条号源记录
	 */
	public int updateDoctorSourceByOne(DoctorSourceInfoDTO dto);
	
	/**
	 * 处理提交预约挂号
	 * @Desc 提供给YBWS接口使用-提交预约挂号
	 */
	public void processSaveYygh(DoctorSourceInfoDTO doctorSourceInfoDTO,AppointmentInfoDTO appointmentInfoDTO);
	
	
	/**
	 * 查询kcd1信息
	 */
	public BizDTO selectKcd1(InHospitalDTO inHospitalDTO);
	
	/**
	 * 查询取药信息
	 * @Desc 提供给YBWS接口使用
	 */
	public List<DrugWindowInfoDTO> selectDrugWindowInfo(DrugWindowInfoDTO drugWindowInfoDTO);
	
	
	/**
	 * 查询业务信息
	 * @param inHospitalDTO
	 * @return
	 */
	public List<DiagnoseInfoDTO> getBizInfo(InHospitalDTO inHospitalDTO);
}
