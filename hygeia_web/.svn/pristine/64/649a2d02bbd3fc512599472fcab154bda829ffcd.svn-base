package com.powersi.ssm.biz.medicare.outland.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.outland.pojo.OutBizDTO;

/**
 * 
 * @author 董孝辉
 *
 */
public interface OutBizDao extends Serializable {

	@SuppressWarnings("rawtypes")
	public List getCityAndDept(String akb020, OutBizDTO outBizDTO);

	/**
	 * <pre>
	 * bkd324 varchar(30)  '名称'
	 * bkd325 varchar(10)  '编号'
	 * bkd326 varchar(10)  '上一级编号'
	 * bkd327 int(11)  '级别'
	 * </pre>
	 * 
	 * @param bkd325
	 *            编号
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getCityRegion(String bkd325);

}
