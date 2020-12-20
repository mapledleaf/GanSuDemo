package com.powersi.biz.medicare.health.service.api;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.health.pojo.HealthDTO;

public interface HealthApiService {
	/**
	 * 体检目录查询
	 * @param keh1
	 * @return
	 */
	public ListResult queryItemCatalog(HealthDTO healthDTO);
	
	/**
	 * 体检目录中心下载到结算云
	 * @param healthDTO
	 * @return
	 */
	public int healthDownloadOnCenter(HealthDTO healthDTO);
	/**
	 * 检查keh1体检编码是否存在
	 * @param akb020
	 * @param healthDTO
	 * @return
	 */
	public int checkKeh1(HealthDTO healthDTO);
	/**
	 * 保存体检目录信息
	 * @param akb020
	 * @param healthDTO
	 */
	public void saveHealthCata(HealthDTO healthDTO);
	
	/**
	 * 验证导入体检目录
	 * @param akb020
	 * @param detail
	 * @return
	 */
	public Map chackImportDetail(List detail,HealthDTO healthDTO);
	
	/**
	 * 验证导入的体检目录编码是否存在
	 * @param healthDTO
	 * @return
	 */
	public List<HealthDTO> checkKeh1WithExport(HealthDTO healthDTO);
	/**
	 * 体检目录导入上传
	 * @param akb020
	 * @param mDetail
	 * @param healthDTO
	 * @return
	 */
	public List saveExcelAsCata(Map mDetail, HealthDTO healthDTO);

	/**
	 * 查询体检导入验证信息
	 * @param akb020
	 * @param healthDTO
	 * @return
	 */
	public ListResult queryKeh20(HealthDTO healthDTO);
	/**
	 * 查询体检导入临时表
	 * @param akb020
	 * @param healthDTO
	 * @return
	 */
	public ListResult queryKeh21(HealthDTO healthDTO);

	/**
	 * 保存导入的体检目录信息至keh1
	 * @param akb020
	 * @param healthDTO
	 * @return
	 */
	public int saveImportCata(HealthDTO healthDTO);
	/**
	 * 删除体检导入验证信息
	 * @param akb020
	 * @param healthDTO
	 * @return
	 */
	public int deleteKeh20(HealthDTO healthDTO);
	
	/**
	 * 删除导入的临时表中导入的体检目录信息
	 * @param akb020
	 * @param healthDTO
	 * @return
	 */
	public int deleteKeh21(HealthDTO healthDTO);
	
	/**
	 * 根据体检目录编码删除体检目录
	 * @param healthDTO
	 */
	public void deleteKeh1(HealthDTO healthDTO,List<HealthDTO> datas);
	
	/**
	 * 查询已匹配的体检信息
	 * @param akb020
	 * @param healthDTO
	 * @return
	 */
	public ListResult queryItemMatch(HealthDTO healthDTO);
	
	/**
	 * 保存匹配信息到keh3临时表
	 * @param healthDTO
	 * @param datas
	 * @return
	 */
	public int saveMatchCata(HealthDTO healthDTO,List<HealthDTO> datas);
	
	/**
	 * 提交匹配目录给中心审批
	 * 
	 * @author lwyao
	 * @param cataQueryDto
	 * @return
	 */
	public int submitMatchCata(HealthDTO healthDTO);
	
	/**
	 * 自动目录匹配
	 * 
	 * @author lwyao
	 * @param cataQueryDto
	 * @return
	 */
	public int autoMatchCatch(HealthDTO healthDTO);
	
	/**
	 * 删除匹配信息
	 * 
	 * @author lwyao
	 * @param akb020
	 * @param caa027
	 * @param datas
	 * @return
	 */
	public int delMatchCata(HealthDTO healthDTO, List<HealthDTO> datas);
	
	
	public ListResult queryMatchCenter(HealthDTO healthDTO);
	
	
	/*****************体检结论接口******************************/
	/**
	 * 结论查询
	 * @return
	 */
	public ListResult queryConclusions(HealthDTO healthDTO);
	/**
	 * 结论增加
	 * @param datas
	 * @return
	 */
	public int saveConclusions(List<HealthDTO> datas);
	/**
	 * 体检结论删除
	 * @param datas
	 * @return
	 */
	public int deleteConclusions(List<HealthDTO> datas);
	
	/**
	 * 验证导入的结论
	 * @param healthDTO
	 * @return
	 */
	public Map chackImportConclusion(HealthDTO healthDTO,List<Map> detail);
	
	/**
	 * 结论导入上传
	 * @param akb020
	 * @param mDetail
	 * @param healthDTO
	 * @return
	 */
	public List saveExcelAsConclusion(Map mDetail, HealthDTO healthDTO);
	
	
	/**
	 * 保存结论目录信息
	 * @param healthDTO
	 * @return
	 */
	public int saveConCluSionInfo(HealthDTO healthDTO);
	/**
	 * 查询导入的结论临时表信息
	 * @return
	 */
	public ListResult queryKeh23(HealthDTO healthDTO);
	/**
	 * 删除结论临时表信息
	 * @param healthDTO
	 * @return
	 */
	public int deleteKeh23(HealthDTO healthDTO);

	/**
	 * 中心端结论信息查询
	 * @param healthDTO
	 * @return
	 */
	public ListResult queryConclusionInfoOnCenter(HealthDTO healthDTO);
	
	/**
	 * 保存匹配信息到keh5临时表
	 * @param healthDTO
	 * @param datas
	 * @return
	 */
	public int saveMatchConclusion(HealthDTO healthDTO,List<HealthDTO> datas);
	
	/**
	 * 结论自动匹配
	 * @param healthDTO
	 * @param datas
	 * @return
	 */
	public int autoSaveMatchConclusion(HealthDTO healthDTO);
	
	/**
	 * 体检keh5临时表结论至中心审核
	 * @param healthDTO
	 * @param datas
	 * @return
	 */
	public int submitMatchConclusion(HealthDTO healthDTO);
	
	/**
	 * 删除目录匹配信息
	 * @param healthDTO
	 * @param datas
	 */
	public void delMatchCataOnKeh6(HealthDTO healthDTO,List<HealthDTO> datas);
	/**
	 * 中心结论目录下载
	 * @param healthDTO
	 * @return
	 */
	public int doDownload(HealthDTO healthDTO);
	
	/**
	 * 目录修改
	 * @param healthDTO
	 * @return
	 */
	public int updateHospCata(HealthDTO healthDTO);
	
	/**
	 * 体检结论匹配查询
	 * @return
	 */
	public ListResult queryKeh6(HealthDTO healthDTO);
	
    
	/**
	 * 任务概要：TS19080100098 - 【问题修复】【体检项目目录匹配】-“匹配信息”TAB页签中双击提示删除成功，界面依旧存在该值
	 * 修改说明：增加一个操作keh3表的内容
	 * 修改人及时间：李嘉伦 20190801
	 * @param healthDTO
	 * @param datas
	 * @return int 
	 */
	public int delMatchCata_keh3(HealthDTO healthDTO, List<HealthDTO> datas);

	
}
