package com.powersi.biz.medicare.health.service.base;

import java.util.List;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.health.pojo.HealthDTO;
//概要描述TS19052400185 - 【问题修复】结算云keh1跟keh2属于中心目录，采用下发方式时两个表应放在hygeia_base库
//修改说明：将原hygeia库的keh22,keh24表相关的接口移到hygeia_base下
//修改人及修改时间：daliang.long 20190530
public interface HealthBaseService {

	/**
	 * 删除从中心下载目录到结算云的中心目录表
	 * @param healthDTO
	 * @return
	 */
	public int deleteKeh22(HealthDTO healthDTO);
	
	/**
	 * 体检目录中心下载到结算云
	 * @param healthDTO
	 * @return
	 */
	public int healthDownloadOnCenter(List<HealthDTO> healthDTO);
	
	/**
	 * 从中心下载到结算云体检目录查询
	 * @param keh1
	 * @return
	 */
	public ListResult queryItemCatalog_center(HealthDTO healthDTO);
	
	
	/**
	 * 获取表列名
	 * 
	 * @param table_name
	 *            keh1
	 * @return
	 */
	public List<String> getRowNameList(String table_name);
	
	/**
	 * 删除从中心端下载的结论表
	 * @param datas
	 */
	public void deleteKeh24(List<HealthDTO> datas);
	
	/**
	 * 保存从中心端下载的结论表
	 * @param datas
	 */
	public int saveKeh24(List<HealthDTO> datas);
	
	
	/**
	 * 获取从中心端下载的结论表keh2
	 * @return
	 */
	public ListResult queryKeh24(HealthDTO healthDTO);
	
	/**
	 * 中心端结论信息查询
	 * @param healthDTO
	 * @return
	 */
	public ListResult queryConclusionInfoOnCenter(HealthDTO healthDTO);
	
	/**
	 * 更改项目目录
	 * @param datas
	 * @return
	 */
	public int updateKeh22Status(List<HealthDTO> datas);
}
