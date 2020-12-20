package com.powersi.biz.medicare.inhospital.service.db;

import com.powersi.biz.medicare.inhospital.pojo.SceneFinDTO;

import java.util.List;

import com.powersi.biz.medicare.comm.pojo.ListResult;

/**
 * 
 * @author 刘勇
 *
 */
public interface SceneFinService {
	/**
	 * 表名
	 */
	public String table_name = "kce5";

	/**
	 * 
	 * @param sceneFinDTO
	 * @return
	 */
	public int insert(SceneFinDTO sceneFinDTO);

	/**
	 * 
	 * @param sceneFinDTO
	 * @return
	 */
	public int delete(SceneFinDTO sceneFinDTO);

	/**
	 * 
	 * @param sceneFinDTO
	 * @return
	 */
	public int update(SceneFinDTO sceneFinDTO);

	/**
	 * 
	 * @param sceneFinDTO
	 * @return
	 */
	public SceneFinDTO select(SceneFinDTO sceneFinDTO);

	/**
	 * 
	 * @param sceneFinDTO
	 * @return
	 */
	public List<SceneFinDTO> selectAll(SceneFinDTO sceneFinDTO);

	/**
	 * 
	 * @param sceneFinDTO
	 * @return
	 */
	public ListResult selectList(SceneFinDTO sceneFinDTO);

}
