package com.powersi.biz.medicare.comm.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * 报表数据存储服务
 * 
 * @author 李志钢
 *
 */
public interface ReportStore extends Serializable{

	/**
	 * 存储报表数据
	 * 
	 * @param ips
	 *            报表数据读取流
	 * @param path
	 *            临时文件路径带文件名称后缀信息
	 * @return 返回存储的唯一ID
	 */
	public String store(InputStream ips, String path);

	/**
	 * 根据存储的ID，读取报表数据
	 * 
	 * @param store_id
	 *            存储报表时返回的ID
	 * @param ops
	 *            数据输出通道
	 */
	public void get(String store_id, OutputStream ops);

	/**
	 * 根据存储的ID，删除报表数据
	 * 
	 * @param store_id
	 *            void
	 */
	public void delte(String store_id);

}
