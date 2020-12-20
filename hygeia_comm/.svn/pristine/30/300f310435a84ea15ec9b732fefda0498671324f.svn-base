package com.powersi.biz.medicare.catalog.service.base;


import com.powersi.biz.medicare.comm.pojo.DiseaseDirDTO;
import com.powersi.biz.medicare.comm.pojo.HospDTO;
import com.powersi.biz.medicare.comm.pojo.KA16DTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.diagnose.pojo.KF04DTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.pojo.Kad5DTO;
import com.powersi.biz.medicare.yygh.pojo.YYKB01;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.catalog.pojo.DiseaseDTO;
import com.powersi.biz.medicare.catalog.pojo.HospCataDTO;
import com.powersi.biz.medicare.catalog.pojo.KA10;

public interface HospCataBaseService {
	
	/**
	 * 查询中心目录信息(分页)
	 * @param hospCataDto
	 * @return
	 */
	public ListResult queryCenterCataPage(HospCataDTO hospCataDto);
	
	/**
	 * 查询中心目录信息(不分页)
	 * @param hospCataDto
	 * @return
	 */
	public List<HospCataDTO> queryCenterCata(HospCataDTO hospCataDto);
	
	/**
	 * 查kad5条数
	 * @param hospCataDto
	 * @return
	 */
	public int queryKad5Count(HospCataDTO hospCataDto);
	
	/**
	 * 查询中心疾病信息
	 * @param diseaseDirDTO
	 * @return
	 */
	public ListResult queryCenterDisease(DiseaseDTO diseaseDto);
	
	/**
	 * 查询中心疾病信息
	 * @param diseaseDto
	 * @return
	 */
	public List<DiseaseDTO> queryKa06(DiseaseDTO diseaseDto);
	
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
	 * 自动匹配时查询中心所有目录
	 * @param hospCataDto
	 * @return
	 */
	public List<HospCataDTO> queryAutoMatch(HospCataDTO hospCataDto);

    /**
     * 获取医疗机构信息
     * @param hospDTO
     * @return
     */
    public ListResult queryHospInfo(String akb020,HospDTO hospDTO);
    
    /**
     * 获取医疗机构信息
     * @param hospDTO
     * @return
     */
    public ListResult queryHospInfoByAAA027(String akb020,HospDTO hospDTO);

	/**
	 * 查询疾病二级目录
	 * @param akb020
	 * @param hospCataDto
	 * @return
	 */
	public ListResult centerSecondDisease(String akb020, HospCataDTO hospCataDto);
	
	/**
	 * 查询自费补偿疾病诊断
	 * 
	 * @param ka16dto
	 * @return 疾病诊断信息
	 */
	public ListResult querySelfPayDisease(KA16DTO ka16dto);
	
	/**
	 * 通过统筹区，获取开通预约挂号的医院信息
	 * @param yykb01
	 * @return
	 */
	public List<YYKB01> getHospitalByYYGH(YYKB01 yykb01);

	

	/**
	 * 基准库目录
	 * 2017年11月6日 下午8:17:32
	 * @param inHospitalDTO
	 * @return ListResult
	 */
	public ListResult queryHospitalDatumMatched(HospCataDTO hospCataDTO);

	/**
	 * 珠海目录匹配数据批量导入
	 * 查询kad5
	 * 2017年11月27日 
	 * @param KF04DTO
	 * @return List<KF04DTO>
	 */
	public List<KF04DTO> queryCataMatchKad5(KF04DTO kf04dto);
	
	/**
	 * 药品本位码模糊匹配查询
	 * @param dto
	 * @return
	 */
	public ListResult drugGBCodeFuzzyMatch(HospCataDTO hospCataDTO);
	
	/**
	 * 根据中心药品编码获取kad5的数据
	 * @param mpara
	 * @return
	 */
	public List<Kad5DTO> selectKad5(Map<String, Object> mpara);

	/**
	 * 获取码值
	 * @param dataValue
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getCodeDisValue(String codeType);
	
	/**
	 * 获取结算单data12单病种信息
	 * @param inHospitalDTO
	 * @return
	 */
	public List<Map<String, Object>> queryData12Info(InHospitalDTO inHospitalDTO);
	
	/**
	 * 查询连锁药店关系
	 * @return
	 */
	public List<KA10> selectKa10AndKa13();

	/**
	 * 查询中心药品信息
	 * TS19042300092 - 【问题修复】有本位码按照本位码查询
	 * @param hospCataDto
	 * @return
	 */
	public ListResult queryCenterWaitCataPage(HospCataDTO hospCataDto);

	
	/**
	 * 查询中心目录信息(分页)
	 * @param hospCataDto
	 * @return
	 */
	//TS20040700168   【需求开发】湘潭核三内部接口文档功能完善,添加中成药,草药  zyx 20200408
	public ListResult queryCenterAka063(HospCataDTO hospCataDto);
}
