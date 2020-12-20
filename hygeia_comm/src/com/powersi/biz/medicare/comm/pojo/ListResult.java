package com.powersi.biz.medicare.comm.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author 刘勇
 *
 */
public interface ListResult extends Serializable {

	/**
	 * 
	 * @return 总记录数
	 */
	public int getCount();

	/**
	 * 
	 * @param count
	 *            总记录数
	 * @return
	 */
	public ListResult setCount(int count);

	/**
	 * 
	 * @return 数据结果集
	 */
	public List<?> getList();

	/**
	 * 
	 * @param list
	 *            数据结果集
	 * @return
	 */
	public ListResult setList(List<?> list);

}
