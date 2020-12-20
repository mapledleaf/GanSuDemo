package com.powersi.biz.medicare.iccard.service;

import com.powersi.biz.medicare.iccard.pojo.KE64;

/**
 * TAC
 * 
 * @author 刘勇
 *
 */
public interface KE64Service extends java.io.Serializable {

	int deleteByPrimaryKey(String bke546);

	/**
	 * 
	 * @param record
	 *            TAC
	 * @return
	 */
	int insert(KE64 record);

	/**
	 * 
	 * @param record
	 *            TAC
	 * @return
	 */
	int insertSelective(KE64 record);

	KE64 selectByPrimaryKey(String bke546);

	int updateByPrimaryKeySelective(KE64 record);

	int updateByPrimaryKey(KE64 record);

}