package com.powersi.biz.medicare.comm.pojo;

import java.util.List;

/**
 * 
 * @author 刘勇
 *
 */
public class ListResultDTO implements ListResult {

	private static final long serialVersionUID = 1L;

	private int count = 0;
	private List<?> list = null;

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public ListResult setCount(int count) {
		this.count = count;
		return this;
	}

	@Override
	public List<?> getList() {
		return list;
	}

	@Override
	public ListResult setList(List<?> list) {
		this.list = list;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public static final ListResult newInstance() {
		return new ListResultDTO();
	}

}
