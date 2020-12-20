package com.powersi.biz.medicare.inhospital.service.db;

import com.powersi.biz.medicare.inhospital.pojo.PayDTO;

import java.util.List;

import com.powersi.biz.medicare.comm.pojo.ListResult;

/**
 * 
 * @author 刘勇
 *
 */
public interface PayService {
	/**
	 * 表名
	 */
	public String table_name = "kcd7";

	/**
	 * 
	 * @param payDTO
	 * @return
	 */
	public int insert(PayDTO payDTO);

	/**
	 * 
	 * @param payDTORows
	 * @return
	 */
	public int insertList(List<PayDTO> payDTORows);

	/**
	 * 
	 * @param payDTO
	 * @return
	 */
	public int delete(PayDTO payDTO);

	/**
	 * 
	 * @param payDTO
	 * @return
	 */
	public int update(PayDTO payDTO);

	/**
	 * 
	 * @param payDTO
	 * @return
	 */
	public PayDTO select(PayDTO payDTO);

	/**
	 * 
	 * @param payDTO
	 * @return
	 */
	public List<PayDTO> selectPayDTOs(PayDTO payDTO);

	/**
	 * 
	 * @param payDTO
	 * @return
	 */
	public ListResult selectList(PayDTO payDTO);

}
