package com.powersi.biz.medicare.hosp.service.db;

import java.util.List;

import com.powersi.biz.medicare.hosp.pojo.TestDTO;


/**
 *  test维护，db服务接口
 * @author Administrator
 *
 */
public interface TestService {

	/**
	 * 查询
	 * @param akb020
	 * @param TestDTO
	 * @return
	 */
	public List<TestDTO> queryTest01(String akb020,TestDTO testDTO);
	
	
	/**
	 * 保存
	 * @param akb020
	 * @param TestDTO
	 * @return
	 */
	public int saveTest01(String akb020,TestDTO testDTO);
	
	/**
	 * 修改
	 * @param akb020
	 * @param TestDTO
	 * @return
	 */
	public int updateTest01(String akb020,TestDTO testDTO);
	
	/**
	 * 删除
	 * @param akb020
	 * @param aka120
	 * @return
	 */
	public int delTest01(String akb020,String aka120);
	
}
