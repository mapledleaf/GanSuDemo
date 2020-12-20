package com.powersi.biz.medicare.hosp.service.db;

import java.util.List;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;


/**
 *  医院维护，db服务接口
 * @author Administrator
 *
 */
public interface HospService {

	/**
	 * 查询病区信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public List<HospInfoDTO> queryHospArea(String akb020,HospInfoDTO hospInfoDto);
	
	/**
	 * 分页查询病区信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public ListResult queryAreaPage(String akb020,HospInfoDTO hospInfoDto);
	
	/**
	 * 保存病区信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public int saveHospArea(String akb020,HospInfoDTO hospInfoDto);
	
	/**
	 * 保存病区信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public int updateHospArea(String akb020,HospInfoDTO hospInfoDto);
	
	/**
	 * 删除病区信息
	 * @param akb020
	 * @param kac6id
	 * @return
	 */
	public int delHospArea(String akb020,String bkc152);
	
	/**
	 * 查询科室信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public List<HospInfoDTO> queryHospDept(String akb020,HospInfoDTO hospInfoDto);
	
	/**
	 * 分页查询科室信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public ListResult queryDeptPage(String akb020,HospInfoDTO hospInfoDto);
	
	/**
	 * 保存科室信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public int saveHospDept(String akb020,HospInfoDTO hospInfoDto);
	
	/**
	 * 删除科室信息
	 * @param akb020
	 * @param kac7id
	 * @return
	 */
	public int delHospDept(String akb020,String bkc155);
	
	/**
	 * 修改医院科室信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public int updateHospDept(String akb020,HospInfoDTO hospInfoDto);

	/**
	 * 修改医师信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public int updateHospDoctor(String akb020,HospInfoDTO hospInfoDto);

	/**
	 * 删除医师信息
	 * @param akb020
	 * @param kad1id
	 * @return
	 */
	public int delHospDoctor(String akb020,String bkc269);

	/**
	 * 保存医院医师信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public int saveHospDoctor(String akb020,HospInfoDTO hospInfoDto);

	/**
	 * 查询医院医师信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public List<HospInfoDTO> queryHospDoctor(String akb020,HospInfoDTO hospInfoDto);
	
	
	/**
	 * 分页查询医院医师信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public ListResult queryDoctorPage(String akb020,HospInfoDTO hospInfoDto);
	
	
	/**
	 * 查询医院病床信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public List<HospInfoDTO> queryHospBed(String akb020,HospInfoDTO hospInfoDto);
	
	/**
	 * 分页查询医院病床信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public ListResult queryBedPage(String akb020,HospInfoDTO hospInfoDto);
	
	/**
	 * 保存医院病床信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public int saveHospBed(String akb020,HospInfoDTO hospInfoDto);
	
	/**
	 * 删除医院病床信息
	 * @param akb020
	 * @param kac9id
	 * @return
	 */
	public int delHospBed(String akb020,String kac9id);
	
	/**
	 * 修改医院病床信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public int updateHospBed(String akb020,HospInfoDTO hospInfoDto);
	
	
	
	/**
	 * 修改病区信息
	 * 通过中心返回的信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public int updateAreaListByCenter(String akb020,HospInfoDTO hospInfoDto);
	
	/**
	 * 修改科室信息
	 * 通过中心返回的信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public int updateDeptListByCenter(String akb020,HospInfoDTO hospInfoDto);
	
	/**
	 * 修改医师信息
	 * 通过中心返回的信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public int updateDoctorListByCenter(String akb020,HospInfoDTO hospInfoDto);
	

	
	/**
	 * 修改空闲标志
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public int updateBedBkc171(String akb020,HospInfoDTO hospInfoDto);

	/**
	 * 保存医生异动申请信息
	 * @param hospInfoDTO
	 * @return
	 */
	public int saveDoctorChangApply(HospInfoDTO hospInfoDto);
	
	/**
	 * 查询异动申请信息
	 * @param hospInfoDTO
	 * @return
	 */
	public List<HospInfoDTO> selectDoctorChangApply(HospInfoDTO hospInfoDto);
}
