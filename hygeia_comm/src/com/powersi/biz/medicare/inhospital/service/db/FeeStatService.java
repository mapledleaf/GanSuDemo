package com.powersi.biz.medicare.inhospital.service.db;

import com.powersi.biz.medicare.inhospital.pojo.FeeStatDTO;

import java.util.List;

import com.powersi.biz.medicare.comm.pojo.ListResult;

/**
 * 
 * @author 刘勇
 *
 */
public interface FeeStatService {

	/**
	 * 表名
	 */
	public String table_name = "kcd8";

	/**
	 * 
	 * @param feeStatDTO
	 * @return
	 */
	public int insert(FeeStatDTO feeStatDTO);

	/**
	 * 
	 * @param feeStatDTORows
	 * @return
	 */
	public int insertList(List<FeeStatDTO> feeStatDTORows);

	/**
	 * 
	 * @param feeStatDTO
	 * @return
	 */
	public int delete(FeeStatDTO feeStatDTO);

	/**
	 * 
	 * @param feeStatDTO
	 * @return
	 */
	public int update(FeeStatDTO feeStatDTO);

	/**
	 * 
	 * @param feeStatDTO
	 * @return
	 */
	public FeeStatDTO select(FeeStatDTO feeStatDTO);

	/**
	 * 
	 * @param feeStatDTO
	 * @return
	 */
	public List<FeeStatDTO> selectAll(FeeStatDTO feeStatDTO);

	/**
	 * 
	 * @param feeStatDTO
	 * @return
	 */
	public ListResult selectList(FeeStatDTO feeStatDTO);

}
