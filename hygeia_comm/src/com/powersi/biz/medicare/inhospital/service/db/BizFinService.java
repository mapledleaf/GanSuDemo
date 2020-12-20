package com.powersi.biz.medicare.inhospital.service.db;

import com.powersi.biz.medicare.inhospital.pojo.BizFinDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author 刘勇
 *
 */
public interface BizFinService extends Serializable {

	/**
	 * 表名
	 */
	public String table_name = "kc21";

	/**
	 * 
	 * @param bizFinDTO
	 * @return
	 */
	public int insert(BizFinDTO bizFinDTO);

	/**
	 * 
	 * @param bizFinDTO
	 * @return
	 */
	public int delete(BizFinDTO bizFinDTO);

	/**
	 * 
	 * @param bizFinDTO
	 * @return
	 */
	public int update(BizFinDTO bizFinDTO);

	/**
	 * 
	 * @param bizFinDTO
	 * @return
	 */
	public BizFinDTO select(BizFinDTO bizFinDTO);

	/**
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public List<InHospitalDTO> selectAll(InHospitalDTO inHospitalDTO);
	
	/**
	 * 
	 * @param bizFinDTO
	 * @return
	 */
	public BizFinDTO selectByBka012(BizFinDTO bizFinDTO);

}
