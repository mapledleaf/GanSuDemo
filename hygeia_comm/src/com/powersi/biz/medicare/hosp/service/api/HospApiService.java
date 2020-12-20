package com.powersi.biz.medicare.hosp.service.api;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.hosp.pojo.Bkn2Dto;
import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 医院维护，api服务接口
 * @author Administrator
 *
 */
public interface HospApiService {

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
	 * 批量删除病区信息
	 * @param hospInfoDto
	 * @param detail
	 * @return
	 */
	public int deleteArea(HospInfoDTO hospInfoDto, List<Map<String, Object>> detail);
	
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
	 * 修改医院科室信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public int updateHospDept(String akb020,HospInfoDTO hospInfoDto);
	
	
	/**
	 * 批量删除科室信息
	 * @param hospInfoDto
	 * @param detail
	 * @return
	 */
	public int delHospDeptAll(HospInfoDTO hospInfoDto,List<Map<String, Object>> detail);

	/**
	 * 修改医师信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public int updateHospDoctor(String akb020,HospInfoDTO hospInfoDto);


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
	 * 保存导入的医院病床信息
	 * @param hospInfoDto
	 * @param detail
	 * @return
	 */
	public int saveHospBedDr(HospInfoDTO hospInfoDto,List<Map<String, Object>> detail);
	
	
	/**
	 * 保存导入的医院医师信息
	 * @param detail
	 * @param hospInfoDto
	 * @return
	 */
	public int saveHospDoctorDr(HospInfoDTO hospInfoDto,List<Map<String, Object>> detail);
	
	/**
	 * 修改医院病床信息
	 * @param akb020
	 * @param hospInfoDto
	 * @return
	 */
	public int updateHospBed(String akb020,HospInfoDTO hospInfoDto);
	
	/**
	 * 批量删除医师信息
	 * @param detail	
	 * @param hospInfoDto
	 * @return
	 */
	public int delHospDoctorAll(HospInfoDTO hospInfoDto,List<Map<String, Object>> detail);

	/**
	 * 批量删除病床信息
	 * @param hospInfoDto
	 * @param list
	 * @return
	 */
	public int delHospBedAll(HospInfoDTO hospInfoDto,List<Map<String, Object>> list);
	
	/**
	 * 
	 * @param akb020 医院编码
	 * @param bkc153 病区编码
	 * @param bkc161 病床编号
	 * @param bkc171 占用标志0、1
	 * @return
	 */
	public int updateBedBkc171(String akb020,String bkc153 ,String bkc161 ,String bkc171);
	
	/**
	 * 
	 * 预付款
	 * @return
	 */
	public List<Bkn2Dto> selectByBkn2Aaz217(InHospitalDTO inHospitalDTO);
	@Transactional
	public int saveBkn2Update(Bkn2Dto bkn2Dto);
	@Transactional
	public int saveBkn2(Bkn2Dto bkn2Dto,InHospitalDTO inHospitalDTO);
	
	public Bkn2Dto selectByPrimaryKey(String akb020,Integer id);
	
	/**
	 * 保存医生异动申请信息
	 * @param hospInfoDTO
	 * @return
	 */
	public int saveDoctorChangApply(HospInfoDTO hospInfoDTO);
}
