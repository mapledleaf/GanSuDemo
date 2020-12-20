package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.KA06;
import com.powersi.biz.medicare.comm.pojo.KA06DTO;
import com.powersi.biz.medicare.comm.pojo.KA14DTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.pojo.ListResultDTO;
import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.special.pojo.MediSpec_ZH_DTO;

/**
 * 
 * @author 刘勇
 *
 */
public interface DiseaseQueryService extends Serializable {

	/**
	 * 查询疾病诊断
	 * 
	 * @param ka06dto
	 * @return 疾病诊断信息
	 */
	public ListResult queryDisease(KA06DTO ka06dto);

	/**
	 * 
	 * @param aaa027 医保分中心（统筹区）编码
	 * @param aka120 疾病编码
	 * @return
	 */
	public KA06 queryDisease(String aaa027, String aka120);

	/**
	 * 获取重大疾病类型
	 * 
	 * @author yangmj 2018年1月13日 上午11:38:37
	 * @param mediSpecZHDto
	 * @return List<Map>
	 */
	public List<KA06> getAka120Datas(MediSpec_ZH_DTO mediSpecZHDto);

	/**
	 * 取对应统筹区kcc2表
	 * 
	 * @param ka06dto
	 * @return
	 */
	public List<KA06DTO> querybke216(KA06DTO ka06dto);

	/**
	 * 查询单病种疾病是否是106个单病种
	 * 
	 * @param ka06dto
	 * @return
	 */
	public default String is106Disease(KA06DTO ka06dto) {
		return "";
	}

	/**
	 * 查询106个单病种副诊断
	 * 
	 * @param ka06dto
	 * @return
	 */
	public default ListResult choose106SubDisease(KA06DTO ka06dto) {
		return ListResultDTO.newInstance();
	}

	/**
	 * 查詢单病种住院疾病限额
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public List<Map<String, Object>> checkSingleDiseaseLimit(InHospitalDTO inHospitalDTO);

	/**
	 * 查询手术治疗方式
	 * 
	 * @param ka14dto
	 * @return
	 */
	public default ListResult queryOperation(KA14DTO ka14dto) {
		return ListResultDTO.newInstance();
	}

	/**
	 * 
	 * @param ka06Dto
	 * @return
	 */
	public List<KA06DTO> getMaternityGradeOneDisease(KA06DTO ka06Dto);
	
	//TS19121900162 - 【问题修复】需求测试_湘潭_门特疾病限额没有按年度1800封顶是按审核录入的限额处理的_add by ljl 20191220_查询是否两病内病种
	public List<Map<String, Object>> queryTwoDiseaseInfo(DiagnoseInfoDTO diagnoseInfoDTO);
	
	/*TS20010700083  【需求开发】湘潭启用新版疾病目录相关处理  陈洁 20191226
     * 将传入疾病在配置表中心进行查询转换contrast
     * 国临2.0诊断-》新版诊断1.0
     * 老版疾病-》新版诊断1.0
     * */
	public List<Map<String, Object>> getNewdiseaseEdition(InHospitalDTO inHospitalDTO);
	
	public List<Map<String, Object>> getNewdiseaseEdition10(InHospitalDTO inHospitalDTO);
	
	public List<Map<String, Object>> getNewdiseaseEditionold(InHospitalDTO inHospitalDTO);
	
	public List<Map<String, Object>> getNewdiseaseEditionforedit(InHospitalDTO inHospitalDTO);
	
	//TS20031600120   【需求开发】结算云多诊断上传优化  本地只做type2数据转换  赵银溪 20200316
	public List<Map<String, Object>> getNewdiseaseForLocal(InHospitalDTO inHospitalDTO);
}
