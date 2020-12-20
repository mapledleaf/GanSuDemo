package com.powersi.biz.medicare.universal.service.api;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.universal.pojo.KA20_HisDTO;
import com.powersi.biz.medicare.universal.pojo.KA40_HISDTO;
import com.powersi.biz.medicare.universal.pojo.KCD1_Hosp_HisDTO;
import com.powersi.biz.medicare.universal.pojo.KCD1_Mz_HisDTO;
import com.powersi.biz.medicare.universal.pojo.KCD2_Fee_HisDTO;
import com.powersi.biz.medicare.universal.pojo.KCD2_His;
import com.powersi.biz.medicare.universal.pojo.KCD2_HospFee_HisDTO;

public interface DiagnoseQueryApiService {
	
	/**
	 * 药店购药，查询流水号详情
	 * @param number 流水号
	 * @param time 创建时间
	 * @return
	 */
	public List queryNumberDetails(String akc190,String aae030,String abk020);
	
	/**
	 * 药店购药，根据流水号查询详细药品数据
	 * @param number
	 * @return
	 */
	public List queryDiagnoseDetails(String akc190,String abk020);
	
	/**
	 * 门诊购药，通过住院号查询是否有记录
	 * @param akc190
	 * @return
	 */
	public List queryHospHisInfo(Map map);
	
	/**
	 * 门诊购药，查询住院产生费用明细
	 * @param map
	 * @return
	 */
	public List queryHospFeeInfo(Map map);
	
	/**
	 * 查询结算云中住院人员信息
	 * @param map
	 * @return
	 */
	public List queryYunInHospPersonInfo(Map map);
	
	
	/**
	 * 分页查询住院人员信息
	 * @param hosp
	 * @return
	 */
	public ListResult queryInhospPersonInfo_page(KCD1_Hosp_HisDTO hosp);
	
	/**
	 * 查询通用接口中住院人员的信息
	 * @param map
	 * @return
	 */
	public List queryInhospPersonInfo(Map map);
	
	/**
	 * 查询通用接口中住院人员产生的费用
	 * @param map
	 * @return
	 */
	public List queryInhospPersonFee(Map map);
	
	/**
	 * 通用接口查询kcd1_akb020已经上传的费用数目
	 * @param map
	 * @return
	 */
	public int queryInhospPersonKcd2Info(Map map);
	
	/**
	 * 分页查询住院费用明细
	 * @param map
	 * @return
	 */
	public ListResult queryInhospPersonFee_page(KCD2_HospFee_HisDTO dto);
	
	/**
	 * 分页查询通用接口门诊费用查询
	 * @param map
	 * @return
	 */
	public ListResult queryOutpatientPersonFee_page(KCD2_Fee_HisDTO dto);
	
	/**
	 * 查询通用接口中门诊人员的信息
	 * @param map
	 * @return
	 */
	public List queryOutpatientPersonInfo(Map map);
	
	/**
	 * 分页查询住院人员信息
	 * @return
	 */
	public ListResult queryOutpatientPersonInfo_page(KCD1_Mz_HisDTO mz);
	
	/**
	 * 查询门诊人员产生的费用
	 * @param map
	 * @return
	 */
	public List queryOutpatientPersonFee(Map map);
	
	/**
	 * 修改住院人员信息上传标记
	 * @param hospdto
	 */
	public void updateInhospitalFeeUploadFlag(KCD1_Hosp_HisDTO hospdto);
	
	/**
	 * 修改门诊人员信息上传标记
	 * @param mzdto
	 */
	public void updateOutpatientFeeUploadFlag(KCD1_Mz_HisDTO mzdto);
	
	/**
	 * 修改ka20目录表传输标记
	 * @param ka20
	 */
	public void updateKa20HisUploadFlag(KA20_HisDTO ka20);
	
	/**
	 * 查询费用目录数据
	 * @param map
	 * @return
	 */
	public List queryHisCatalog(Map map);
	
	/**
	 * 插入ka20表中
	 * @param hisdto
	 */
	public void insertKa20His(List mapParam);
	
	/**
	 * 插入kcd1_mz_his表
	 * @param hisdto
	 */
	public void insertKcd1MzHis(KCD1_Mz_HisDTO hisdto);
	
	/**
	 * 插入住院登记表kcd1_hosp_his
	 * @param hisdto
	 */
	public void insertKcd1hospHis(KCD1_Hosp_HisDTO hisdto);
	
	/**
	 * 插入门诊费用记录表 kcd2_fee_his
	 * @param hisdto
	 */
	public void insertKcd2FeeHis(List hisdtoList);
	
	/**
	 * 插入住院费用记录表kcd2_hospfee_his 
	 * @param hisdto
	 */
	public void insertKcd2HospFeeHis(List hisdtoList);
	
	/**
	 * his目录上传 到ka40表
	 * @param ka40
	 */
	public void insertKa40(List ka40List);
	
	/**
	 * 插入kcd2表
	 * @param kcd2
	 */
	public void insertKcd2(InHospitalDTO inHospitalDTO);
	
	/**
	 * 给抓取过来的数据添加就医登记号
	 * @param hospfee
	 */
	public void updateKcd2hospFeeAaz217(Map hospfeeMap);
	
	/**
	 * 修改临时表kcd1_hosp_his中的住院登记信息
	 * @param hosphisMap
	 */
	public void updateKcd1hospHis(Map hosphisMap);
	
	/**
	 * 修改费用的中心药品编码和中心药品名称
	 * @param hosphisMap
	 */
	public void updateKcd2HospFeeAke001(Map hosphisMap);
	
	/**
	 * 修改门诊费用的中心药品编码
	 * @param hosphisMap
	 */
	public void updateKcd2FeeAke001(Map hosphisMap);
	
	/**
	 * 删除住院费用
	 * @param akcMap
	 */
	public void deleteKcd2HospFeeByAkc190(Map akcMap);
	
	/**
	 * 删除门诊费用
	 * @param bkmMap
	 */
	public void deleteKcd2FeeByBkm902(Map bkmMap);
	
	/**
	 * 删除医院目录
	 * @param mediMap
	 */
	public void deleteAllMedi(Map mediMap);
	
	/**
	 * 修改kcd1_hosp_his表的登记信息
	 */
	public void updataKcd1Hosp(Map param);
	/**
	 * 修改kcd1_mz_his表的登记信息
	 */
	public void updataKcd1Mz(Map param);
	
	/**
	 * 查询ka40表
	 * @param param
	 * @return
	 */
	public ListResult queryKA40(KA40_HISDTO dto);
	
	/**
	 * ka20分页查询
	 * @param dto
	 * @return
	 */
	public ListResult queryKa20_page(KA20_HisDTO dto);
	
	/**
	 * 删除ka40表的数据通过医院编码
	 * @param map
	 */
	public void deleteKa40ByAkb020(Map map);
	
	/**
	 * 删除已经上传的住院费用
	 * @param bzMap
	 */
	public void deleteKcd2HospFeeByBZ1(Map bzMap);
	
	/**
	 * 删除已经上传的门诊费用
	 * @param bzMap
	 */
	//public void deleteKcd2FeeHisByBZ1(Map bzMap);
	
	public void updataKcd2hospFeeBZ1(Map bzMap);
	
	/**
	 * 删除结算云正式表kcd2的费用数据
	 * @param bzMap
	 */
	public void deleteKcd2ByAaz218(Map bzMap);
	
	
}
