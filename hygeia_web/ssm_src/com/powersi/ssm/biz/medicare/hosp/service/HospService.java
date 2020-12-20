package com.powersi.ssm.biz.medicare.hosp.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.catalog.pojo.HospCataDTO;
import com.powersi.biz.medicare.diagnose.pojo.KF04DTO;
import com.powersi.biz.medicare.diagnose.pojo.Kf02DTO;
import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;
import com.powersi.biz.medicare.hosp.service.api.HospApiService;

/**
 * 医院维护，web接口
 * 
 * @author Administrator
 *
 */
public interface HospService {

	/**
	 * 解析导入的病床文件
	 * 
	 * @param bed_img
	 * @param hospInfoDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getExcelContextBed(File bed_img, HospInfoDTO hospInfoDto);

	/**
	 * @author yangmj 2017年10月10日 下午3:27:57
	 * @param bed_img
	 * @param hospInfoDto
	 * @return List
	 */
	public List<Kf02DTO> getExcelContextBatchCharge(File bed_img, String upFileName, String akb020, String uuid);

	/**
	 * @author lhy 2017年11月23日
	 * @param 珠海目录匹配数据导入需求
	 * @return List
	 */
	public List<KF04DTO> getExcelContextCatalogMatch(File catalog_img, String upFileName, String akb020, String uuid);

	/**
	 * 解析导入的医师文件
	 * 
	 * @param doctor_img
	 * @param hospInfoDto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getExcelContextDoctor(File doctor_img, HospInfoDTO hospInfoDto);

	/**
	 * 解析导入的目录文件
	 * 
	 * @param cata_img
	 * @param HospCataDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getExcelContextCata(File cata_img, HospCataDTO hospCataDto);

	/**
	 * 解析导入的目录文件
	 * 
	 * @param cata_img
	 * @param HospCataDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getExcelByCata(File cata_img, HospCataDTO hospCataDto);

	/**
	 * 解析导入的目录文件
	 * 
	 * @param cata_img
	 * @param HospCataDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getExcelByItem(File cata_img, HospCataDTO hospCataDto);

	/**
	 * 验证病区、科室、病床编码是否已经存在
	 * 
	 * @param akb020
	 * @param id
	 * @param type
	 * @return
	 */
	public int checkExist(String akb020, String id, String type, HospApiService hospService);

	/**
	 * 验证病区、科室、病床编码是否已经存在
	 * 
	 * @param HospInfoDTO
	 * @param type
	 * @return
	 */
	public int checkExistByHosp(HospInfoDTO hospInfoDto, String type, HospApiService hospService);

	@SuppressWarnings("rawtypes")
	public List getExcelInFee(File imgFile, HospInfoDTO hospInfoDto);

	/**
	 * 校验导入的医师信息
	 * 
	 * @param detail
	 * @param bkc156
	 * @return
	 */
	public List<Map<String, Object>> checkDoctroList(List<Map<String, Object>> detail, String bkc156,List<HospInfoDTO> deptInfo);

}
