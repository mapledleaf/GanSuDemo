package com.powersi.biz.medicare.declare.service.api;

import java.util.Map;
import com.powersi.biz.medicare.declare.pojo.DeclarePayDTO;
import com.powersi.biz.medicare.declare.pojo.HospitalAppealDTO;

/**
 * 结算申报
 * 
 * @author Administrator
 *
 */
public interface DeclarePayService {

	/**
	 * 医保月申报
	 * 
	 * @param akb020
	 * @param declarePayDTO
	 * @return
	 */
	public Map<String, Object> verifyCenterDecl(String akb020, DeclarePayDTO declarePayDTO);

	/**
	 * 查询申报明细表 分页查询
	 * 
	 * @param akb020
	 * @param declarePayDTO
	 * @param first_row
	 * @param last_row
	 * @return
	 */
	public Map<String, Object> queryDeclDetail(String akb020, DeclarePayDTO declarePayDTO, int first_row, int last_row);

	/**
	 * 省内异地月申报
	 * 
	 * @param akb020
	 * @param declarePayDTO
	 * @return
	 */
	public Map<String, Object> verifySNYDDecl(String akb020, DeclarePayDTO declarePayDTO);

	/**
	 * 查询审核信息
	 * 
	 * @param akb020
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getBusinessAppealInfo(HospitalAppealDTO appealDTO);

	/**
	 * 查询明细
	 * 
	 * @param appealDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getBusinessAppealDetail(HospitalAppealDTO appealDTO);

	/**
	 * 取消申报
	 * 
	 * @param akb020
	 * @param declarePayDTO
	 * @return
	 */
	public String cancelDeclarePay(String akb020, DeclarePayDTO declarePayDTO);

	/**
	 * 查询疑点场景信息
	 * 
	 * @param appealDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getAppealDataInfo(HospitalAppealDTO appealDTO);

	/**
	 * 保存申述
	 * 
	 * @param appealDTO
	 * @return
	 */
	public String saveAppeal(HospitalAppealDTO appealDTO);

	/**
	 * 检查文件是否重复
	 * 
	 * @param aaz217
	 * @param fileName
	 * @return
	 */
	public boolean checkFileNameIsExist(String aaz217, String fileName);

	public void deleteAppealData(String aaz530);

	public void uploadAppealData(Map<String, Object> params);

	@SuppressWarnings("rawtypes")
	public Map searchStayAppealDetail(HospitalAppealDTO appealDTO);

	/**
	 * 取消省内异地申报
	 * 
	 * @param akb020
	 * @param declarePayDTO
	 * @return
	 */
	public String cancelSNYDDecl(String akb020, DeclarePayDTO declarePayDTO);

}
