package com.powersi.biz.medicare.inhospital.service.db;

import java.util.List;

import com.powersi.biz.medicare.inhospital.pojo.SceneDTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;

/**
 * 
 * @author 刘勇
 *
 */
public interface SceneService {

	/**
	 * 表名
	 */
	public String table_name = "kce4";

	/**
	 * 
	 * @param sceneDTO
	 * @return
	 */
	public int insert(SceneDTO sceneDTO);

	/**
	 * 
	 * @param sceneDTORows
	 * @return
	 */
	public int insertList(List<SceneDTO> sceneDTORows);

	/**
	 * 
	 * @param sceneDTO
	 * @return
	 */
	public int delete(SceneDTO sceneDTO);

	/**
	 * 
	 * @param sceneDTO
	 * @return
	 */
	public int update(SceneDTO sceneDTO);

	/**
	 * 
	 * @param sceneDTO
	 * @return
	 */
	public SceneDTO select(SceneDTO sceneDTO);

	/**
	 * 
	 * @param sceneDTO
	 * @return
	 */
	public ListResult selectList(SceneDTO sceneDTO);

	/**
	 * 
	 * @param sceneDTO
	 * @return
	 */
	public List<SceneDTO> selectSceneDTOs(SceneDTO sceneDTO);

}
