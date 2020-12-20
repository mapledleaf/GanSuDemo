package com.powersi.biz.medicare.inhospital.service.db;

import com.powersi.biz.medicare.inhospital.pojo.FeeStatFinDTO;

import java.util.List;

import com.powersi.biz.medicare.comm.pojo.ListResult;

/**
 * 
 * @author 刘勇
 *
 */
public interface FeeStatFinService {

	/**
	 * 表名
	 */
	public String table_name = "kc28";

	/**
	 * 
	 * @param feeStatFinDTO
	 * @return
	 */
	public int insert(FeeStatFinDTO feeStatFinDTO);

	/**
	 * 
	 * @param feeStatFinDTO
	 * @return
	 */
	public int delete(FeeStatFinDTO feeStatFinDTO);

	/**
	 * 
	 * @param feeStatFinDTO
	 * @return
	 */
	public int update(FeeStatFinDTO feeStatFinDTO);

	/**
	 * 
	 * @param feeStatFinDTO
	 * @return
	 */
	public FeeStatFinDTO select(FeeStatFinDTO feeStatFinDTO);

	/**
	 * 
	 * @param feeStatFinDTO
	 * @return
	 */
	public List<FeeStatFinDTO> selectAll(FeeStatFinDTO feeStatFinDTO);

	/**
	 * 
	 * @param feeStatFinDTO
	 * @return
	 */
	public ListResult selectList(FeeStatFinDTO feeStatFinDTO);

}
