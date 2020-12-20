package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;
import java.util.List;

import com.powersi.biz.medicare.comm.pojo.AA13DTO;

/**
 * 
 * @author 刘勇
 *
 */
public interface AA13Service extends Serializable {

	public String MAP_HYGEIA_BASE_AA13 = "MAP_HYGEIA_BASE_AA13";

	/**
	 * 
	 * @param aA13DTO
	 * @return 统筹区
	 */
	public List<AA13DTO> selectAll(AA13DTO aA13DTO);

	/**
	 * 刷新统筹区缓存
	 */
	public void refreshCache();

}
