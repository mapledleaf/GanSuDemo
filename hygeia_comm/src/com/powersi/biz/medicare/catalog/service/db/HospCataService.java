package com.powersi.biz.medicare.catalog.service.db;


import com.powersi.biz.medicare.comm.pojo.ListResult;

import java.util.List;

import com.powersi.biz.medicare.catalog.pojo.DiseaseDTO;
import com.powersi.biz.medicare.catalog.pojo.HospCataDTO;

/**
 *目录管理，dbService接口
 * @author ChenXing
 */
public interface HospCataService {
	
	/**
	 * 查询目录信息(分页)
	 * @param hospInfoDto
	 * @return
	 */
	public ListResult queryHospCataPage(HospCataDTO hospCataDto);
	
	/**
	 * 查询医院目录信息(不分页)
	 */
	public List<HospCataDTO> queryHospCata(HospCataDTO hospCataDto);
	
	/**
	 * 查询医院目录匹配信息(分页)
	 */
	public ListResult getCataMatchPage(HospCataDTO hospCataDto);
	
	/**
	 * 查询医院目录匹配信息(分页) 
	 * lastrow fristrow
	 */
	public List<HospCataDTO> getCataMatchForApi(HospCataDTO hospCataDto);
	
	/**
	 * 查询kae8条数
	 * lastrow fristrow
	 */
	public int getKae8Count(HospCataDTO hospCataDto);
	
	/**
	 * 查询医院目录匹配信息(不分页)
	 * @param hospCataDto
	 * @return
	 */
	public List<HospCataDTO> getCataMatch(HospCataDTO hospCataDto);
	
	/**
	 * 查询疾病匹配目录(分页)
	 * @param diseaseDto
	 * @return
	 */
	public ListResult getDiseaseMatchPage(DiseaseDTO diseaseDto);
	
	/**
	 * 查询疾病匹配目录(不分页)
	 * @param diseaseDto
	 * @return
	 */
	public List<DiseaseDTO> getDiseaseMatch(DiseaseDTO diseaseDto);
	
	/**
	 * 查询医院疾病目录ka60(分页)
	 * @param diseaseDto
	 * @return
	 */
	public ListResult queryKa60Page(DiseaseDTO diseaseDto);
	
	/**
	 * 查询医院疾病目录ka60(不分页)
	 * @param diseaseDto
	 * @return
	 */
	public List<DiseaseDTO> queryKa60(DiseaseDTO diseaseDto);
	
	/**
	 * 查询疾病匹配目录ke06(分页)
	 * @param diseaseDto
	 * @return
	 */
	public ListResult queryKe06Page(DiseaseDTO diseaseDto);
	
	/**
	 * 录入疾病时分页查询
	 * @param diseaseDto
	 * @return
	 */
	public ListResult queryDisease(DiseaseDTO diseaseDto);
	
	/**
	 * 查询疾病匹配目录ke06(不分页)
	 * @param diseaseDto
	 * @return
	 */
	public List<DiseaseDTO> queryKe06(DiseaseDTO diseaseDto);
	
	/**
	 * 保存目录信息
	 */
	public int insertKa40(HospCataDTO hospCataDto);
	
	/**
	 * 保存目录信息
	 */
	public int insertBatchKa40(List<HospCataDTO> hospCataList);
	
	/**
	 * 反向生成医院目录(添加Ka40)
	 * @return
	 */
	public int createHospCata(HospCataDTO hospCataDto);

	/**
	 * 保存匹配信息
	 * @param hospCataDto
	 * @return
	 */
	public int insertKae8(HospCataDTO hospCataDto);
	
	/**
	 * 保存匹配信息(批量)
	 * @param hospCataDto
	 * @return
	 */
	public int insertKae8List(List<HospCataDTO> hospCataDtoList);
	
	/**
	 * 保存匹配数据 提供给api
	 * 与 insertKae8List 方法的区别
	 * 防止api入参数据集中有重复数据
	 * 先做保存操作 在做去重操作 保证数据唯一
	 * @param hospCataDtoList
	 * @return
	 */
	public int insertKae8ListForApi(List<HospCataDTO> hospCataDtoList);
	
	/**
	 * 保存医院疾病目录
	 * @param hospCataDto
	 * @return
	 */
	public int insertKa60(DiseaseDTO diseaseDto);
	
	/**
	 * 保存医院疾病目录(批量)
	 * @param hospCataDto
	 * @return
	 */
	public int insertKa60List(List<DiseaseDTO> diseaseDtoList);
	
	/**
	 * 保存疾病匹配信息
	 * @param hospCataDto
	 * @return
	 */
	public int insertKe06(DiseaseDTO diseaseDto);
	
	/**
	 * 保存疾病匹配信息(批量)
	 * @param hospCataDto
	 * @return
	 */
	public int insertKe06List(List<DiseaseDTO> diseaseDtoList);
	
	/**
	 * 删除目录信息
	 * @param ka40id
	 * @return
	 */
	public int delKa40(List<HospCataDTO> hospCataDtoList);
	
	/**
	 * 删除医院疾病目录
	 * @param diseaseList
	 * @return
	 */
	public int delKa60(List<DiseaseDTO> diseaseList);
	
	/**
	 * 修改目录信息
	 * @param hospCataDto
	 * @return
	 */
	public int updateKa40(HospCataDTO hospCataDto);
	
	/**
	 * 修改疾病目录
	 * @param diseaseDto
	 * @return
	 */
	public int updateKa60(DiseaseDTO diseaseDto);
	
	/**
	 * 修改疾病匹配信息
	 * @param hospCataDto
	 * @return
	 */
	public int updateKae8(HospCataDTO hospCataDto);
	
	/**
	 * 修改目录匹配信息
	 * @param diseaseDto
	 * @return
	 */
	public int updateKe06(DiseaseDTO diseaseDto);
	

	/**
	 * 修改匹配目录信息(批量修改)
	 * @param hospCataDto
	 * @return
	 */
	public int updateBatchKae8(List<HospCataDTO> hospCataList);
	
	/**
	 * 修改匹配目录信息(批量修改)
	 * @param hospCataDto
	 * @return
	 */
	public int updateBatchKe06(List<DiseaseDTO> uHospDiseaseList);
	
	/**
	 * 保存kad6
	 * @return
	 */
	public int saveKad6(HospCataDTO hospCataDTO);
	
	/**
	 * 保存kad7
	 * @param akb020
	 * @param hospCataDTO
	 * @return
	 */
	public int saveKad7(HospCataDTO hospCataDto);
	
	public int saveKad7List(List<HospCataDTO> hospCataDtoList);
	
	/**
	 * 删除导入信息
	 * @param hospCataDTO
	 * @return
	 */
	public int deleteKad6(HospCataDTO hospCataDTO);
	
	/**
	 * 删除导入的明细
	 * @param hospCataDTO
	 * @return
	 */
	public int deleteKad7(HospCataDTO hospCataDTO);
	
	/**
	 * 查询导入信息
	 * @param akb020
	 * @param hospCataDTO
	 * @return
	 */
	public ListResult queryKad6(HospCataDTO hospCataDTO);
	
	/**
	 * 分页查询可导入的明细信息(分页)
	 * @return
	 */
	public ListResult queryKad7Page(HospCataDTO hospCataDTO);
	
	/**
	 * 查询可导入的明细信息(不分页)
	 */
	public List<HospCataDTO> queryKad7(HospCataDTO hospCataDTO);

	/**
	 * 反向生成医院疾病目录
	 * @param diseaseDto
	 * @return
	 */
	public int createHospDisease(DiseaseDTO diseaseDto);

	/**
	 * 删除匹配目录信息(批量删除)
	 * @param hospCataDto
	 * @return
	 */
	public int updateKae8Rows(List<HospCataDTO> hospCataList);
	
	
}
