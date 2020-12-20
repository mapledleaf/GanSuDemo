package com.powersi.biz.medicare.catalog.service.api;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.catalog.pojo.DiseaseDTO;
import com.powersi.biz.medicare.catalog.pojo.HospCataDTO;
import com.powersi.biz.medicare.comm.pojo.DiseaseDirDTO;
import com.powersi.biz.medicare.comm.pojo.HospDTO;
import com.powersi.biz.medicare.comm.pojo.KA16DTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;

public interface HospCataApiService {
	
	/**
	 * 查询疾病限额信息
	 * @param diseaseDirDTO
	 * @return
	 */
	public ListResult queryDiseaseDirectory(DiseaseDirDTO diseaseDirDTO);
	
	/**
	 * 查询疾病限额详情
	 * @param diseaseDirDTO
	 * @return
	 */
	public List<DiseaseDirDTO> loadDiseaseDirDetail(DiseaseDirDTO diseaseDirDTO);
	
	/**
	 * 查询导入信息
	 */
	public ListResult queryKad6(HospCataDTO hospCataDTO);

	/**
	 * 分页查可导入明细信息
	 */
	public ListResult queryKad7Page(HospCataDTO hospCataDTO);

	/**
	 * 查询医院目录信息(分页)
	 */
	public ListResult queryHospCataPage(HospCataDTO hospCataDto);
	
	/**
	 * 查询医院目录信息(不分页)
	 */
	public List<HospCataDTO> queryHospCata(HospCataDTO hospCataDto);

	/**
	 * 查询匹配信息
	 * @param akb020
	 * @param hospCataDto
	 * @return
	 */
	public ListResult getCataMatch(HospCataDTO hospCataDto);
	
	/**
	 * 查询疾病匹配目录
	 * @param diseaseDto
	 * @return
	 */
	public ListResult getDiseaseMatch(DiseaseDTO diseaseDto);

	/**
	 * 删除目录信息
	 * 
	 * @param list
	 * @param akb020
	 * @return
	 */
	public int delHospCata(String akb020, List<HospCataDTO> hospCataList);

	/**
	 * 新增药品目录信息
	 * 
	 * @param hospCataDto
	 * @return
	 */
	public int saveHospCata(HospCataDTO hospCataDto);

	/**
	 * 查询中心药品信息
	 * @param hospCataDto
	 * @return
	 */
	public ListResult queryCenterCata(HospCataDTO hospCataDto);
	
	
	/**
	 * 查询中心药品信息
	 * TS19042300092 - 【问题修复】有本位码按照本位码查询
	 * @param hospCataDto
	 * @return
	 */
	public ListResult queryCenterWaitCata(HospCataDTO hospCataDto);
	
	/**
	 * 查询中心疾病信息
	 * @return
	 */
	public ListResult queryCenterDisease(DiseaseDTO diseaseDto);

	/**
	 * 查询疾病二级目录
	 * 
	 * @param hospCataDto
	 * @return
	 */
	public ListResult centerSecondDisease(String akb020, HospCataDTO hospCataDto);


	/**
	 * 自动匹配  根据目录类别、名称、本位码匹配一个中心目录
	 */
	public int doAutoMatchCata(HospCataDTO hospCataDto);
	
	/**
	 * 根据疾病编码匹配
	 * @param diseaseDto
	 * @return
	 */
	public int doAutoMatchDisease(DiseaseDTO diseaseDto);
	
	/**
	 * 手动匹配
	 */
	public int doMatchCata(HospCataDTO hospCataDto);
	
	/**
	 * 手动匹配疾病目录
	 * @param diseaseDto
	 * @return
	 */
	public int doMatchDisease(DiseaseDTO diseaseDto);
	
	/**
	 * 修改目录信息
	 * 
	 * @param hospCataDto
	 * @return
	 */
	public int updateHospCata(HospCataDTO hospCataDto);

	/**
	 * 获取医疗机构信息
	 * 
	 * @param hospDTO
	 * @return
	 */
	public ListResult queryHospInfo(String akb020, HospDTO hospDTO);

	/**
	 * 验证导入明细，并产生kad6,kad7的数据
	 * @return
	 */
	public int chackImportDetail(HospCataDTO hospCataDto, List<HospCataDTO> hospCataList, String pageFlag);

	/**
	 * 删除导入的目录信息
	 * @param hospCataDTO
	 * @return
	 */
	public int deleItemInfo(HospCataDTO hospCataDTO);

	/**
	 * 保存导入的目录信息
	 * @return
	 */
	public int saveImportCata(HospCataDTO hospCataDTO);

	/**
	 * 保存导入的疾病目录信息
	 * @return
	 */
	public int saveImportDisease(HospCataDTO hospCataDTO);

	/**
	 * 查询自费补偿疾病诊断
	 * 
	 * @param ka16dto
	 * @return 疾病诊断信息
	 */
	public ListResult querySelfPayDisease(String akb020, KA16DTO ka16dto);

	/**
	 * 通过kae8生成ka40数据
	 * 
	 * @param akb020
	 * @return
	 */
	public int copyHospCata(HospCataDTO hospCataDTO);
	
	/**
	 * 通过kae8生成ka40数据(生成所有医院目录)
	 * @param akb020
	 * @return
	 */
	public int copyAllCata(HospCataDTO hospCataDto);

	/**
	 * 获取医疗机构信息 通过AAA027等信息获取
	 * 
	 * @param hospDTO
	 * @return
	 */
	public ListResult queryHospInfoByAAA027(String akb020, HospDTO hospDTO);

	/**
	 * 给api提供查询目录匹配信息的接口
	 * @param hospCataDTO
	 * @return List<HospCataDTO>
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryKae8ByAke005(HospCataDTO hospCataDto,List<Map> feeList);

	/**
	 * 药品本位码模糊匹配查询
	 * @param hospCataDTO
	 * @return
	 */
	public ListResult drugGBCodeFuzzyMatch(HospCataDTO hospCataDTO);
	
	/**
	 * 目录匹配时加载医院信息
	 */
	public List<Map<String, Object>> queryDesignatedCenter(HospCataDTO hospCataDto);
	
	/**
	 * 把未审核的目录匹配信息提交至中心审核
	 * @param hospCataDTO
	 * @return
	 */
	public int submitMatchCata(HospCataDTO hospCataDTO);
	
	/**
	 * 把未审核的目录匹配信息提交至中心审核
	 * @param diseaseDto
	 * @return
	 */
	public int submitMatchDisease(DiseaseDTO diseaseDto);

	/**
	 * 删除目录匹配信息
	 * @param hospCataDto
	 * @return
	 */
	public int delMatchCata(HospCataDTO hospCataDto);
	
	/**
	 * 删除疾病匹配信息
	 * @param diseaseDto
	 * @return
	 */
	public int delMatchDisease(DiseaseDTO diseaseDto);

	/**
	 * 查询医院疾病目录信息(分页)
	 * @param diseaseDto
	 * @return
	 */
	public ListResult queryHospDiseasePage(DiseaseDTO diseaseDto);

	/**
	 * 保存医院疾病目录
	 * @param diseaseDto
	 * @return
	 */
	public int saveHospDisease(DiseaseDTO diseaseDto);

	/**
	 * 修改医院疾病目录
	 * @param diseaseDto
	 * @return
	 */
	public int updateHospDisease(DiseaseDTO diseaseDto);

	/**
	 * 删除医院疾病目录
	 * @param akb020
	 * @param diseaseList
	 * @return
	 */
	public int delHospDisease(String akb020, List<DiseaseDTO> diseaseList);

	public int copyHospDisease(DiseaseDTO diseaseDto);
	
	/**
	 * 删除匹配目录信息(批量删除)
	 * @param hospCataDto
	 * @return
	 */
	public int updateKae8Rows(String akb020, List<HospCataDTO> hospCataList);

}
