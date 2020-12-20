package com.powersi.biz.medicare.inhospital.service.db;

import java.io.Serializable;
import java.util.List;

import com.powersi.biz.medicare.inhospital.pojo.FeeDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;

/**
 * 
 * @author 刘勇
 *
 */
public interface FeeService extends Serializable {

	/**
	 * 校验并保存费用
	 */
	public String actionNameSaveFee = "校验并保存费用-";

	/**
	 * 表名
	 */
	public String table_name = "kcd2";

	/**
	 * 获取表列名
	 * 
	 * @param table_name
	 *            kcd2/kc22
	 * @return
	 */
	public List<String> getRowNameList(String table_name);

	/**
	 * 
	 * @param feeDTO
	 * @return
	 */
	public int insert(FeeDTO feeDTO);

	/**
	 * 
	 * @param feeDTORows
	 * @return
	 */
	public int insertList(List<FeeDTO> feeDTORows);

	/**
	 * 通用接口保存费用明细到临时表
	 * 
	 * @param inHospitalDTORows
	 * @return
	 */
	public int insertListKE22(List<InHospitalDTO> inHospitalDTORows);

	/**
	 * 
	 * @param feeDTO
	 * @return
	 */
	public int delete(FeeDTO feeDTO);

	/**
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public int deleteCommnInterface(InHospitalDTO inHospitalDTO);

	/**
	 * 
	 * @param feeDTO
	 * @return
	 */
	public int update(FeeDTO feeDTO);

	/**
	 * 
	 * @param feeDTO
	 * @return
	 */
	public FeeDTO select(FeeDTO feeDTO);

	/**
	 * 查询已保存的费用信息_费用记账收费
	 * 
	 * @param feeDTO
	 * @return
	 */
	public ListResult selectList(FeeDTO feeDTO);

	/**
	 * 查询已保存的费用信息_费用待遇计算
	 * 
	 * @param feeDTO
	 * @return
	 */
	public List<FeeDTO> selectCalcFeeList(FeeDTO feeDTO);

}
