package com.powersi.biz.medicare.health.service.db;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.health.pojo.HealthDTO;

//概要描述TS19052400185 - 【问题修复】结算云keh1跟keh2属于中心目录，采用下发方式时两个表应放在hygeia_base库
//修改说明：将原hygeia库的keh22,keh24表相关的接口移到hygeia_base下
//修改人及修改时间：daliang.long 20190530
public interface HealthService {
	/**
	 * 体检目录查询
	 * @param keh1
	 * @return
	 */
	public ListResult queryItemCatalog(HealthDTO healthDTO);

	/**
	 * 删除体检目录
	 * @param healthDTO
	 * @return
	 */
	public void deleteItemCatalog(HealthDTO healthDTO);
	
	/**
	 * 检查keh1体检编码是否存在
	 * @param akb020
	 * @param healthDTO
	 * @return
	 */
	public int checkKeh1(HealthDTO healthDTO);
	/**
	 * 保存体检目录信息
	 * 
	 * @param healthDTO
	 */
	public void saveHealthCata(HealthDTO healthDTO);
	/**
	 * 验证导入的体检目录编码是否存在
	 * @param healthDTO
	 * @return
	 */
	public List<HealthDTO> checkKeh1WithExport(HealthDTO healthDTO);
	
	/**
	 * 体检目录导入保存导入校验信息表
	 * @param healthDTO
	 */
	public void saveKeh20(HealthDTO healthDTO);
	/**
	 * 体检目录导入保存临时表
	 * @param healthDTO
	 */
	public void saveKeh21(HealthDTO healthDTO);
	
	/**
	 * 查询体检导入验证信息
	 * 
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
	 * 删除体检导入验证信息
	 * 
	 * @param healthDTO
	 * @return
	 */
	public int deleteKeh20(HealthDTO healthDTO);
	
	/**
	 * 删除导入的临时表中导入的体检目录信息
	 * 
	 * @param healthDTO
	 * @return
	 */
	public int deleteKeh21(HealthDTO healthDTO);
	
	/**
	 * 根据体检目录编码删除体检目录
	 * @param healthDTO
	 */
	public void deleteKeh1(List<HealthDTO> datas);
	/**
	 * 查询已匹配的体检信息
	 * 
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
	public int saveMatchCata(List<HealthDTO> datas);
	
	/**
	 * 删除临时表已提交成功的
	 * 
	 * @author 
	 * @param cataQueryDto
	 * @return
	 */
	public void deleteMatchCataSubmited(HealthDTO healthDTO);

	
	/**
	 * 删除匹配信息
	 * 
	 * @author
	 * @param akb020
	 * @param caa027
	 * @param datas
	 * @return
	 */
	public int delMatchCata(List<HealthDTO> datas);
	
	/**
	 * 查询体检临时表目录信息
	 * @param healthDTO
	 * @return
	 */
	public ListResult queryKeh3(HealthDTO healthDTO);
	
	//TS19080100100 - 【问题修复】【医院体检项目目录维护】-中心已审核的目录有效标志为无效
	//中心已审核的目录标志为有效且不能进行修改和删除
	//修改人 daliang.long
	//修改时间 20190803
	/**
	 * 查询体检临时表目录信息
	 * @param healthDTO
	 * @return
	 */
	public ListResult queryKeh3_page(HealthDTO healthDTO);
	
	
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
	public List<HealthDTO> chackImportConclusion(HealthDTO healthDTO);
	/**
	 * 结论导入临时表
	 * @param healthDTORow
	 */
	public void saveKeh23(HealthDTO healthDTO);
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
	 * 保存匹配结论信息到临时表keh5
	 * @param datas
	 * @return
	 */
	public int saveMatchConclusion(List<HealthDTO> datas);

	
	/**
	 * 查询结论临时表信息
	 * @param healthDTO
	 * @return
	 */
	public ListResult queryKeh5(HealthDTO healthDTO);
	/**
	 * 临时表结论提交到中心待审核后删除结算云临时表信息
	 * @param healthDTO
	 */
	public void deleteKeh5(HealthDTO healthDTO);
	
	public void deleteKeh6(List<HealthDTO> datas);
	/**
	 * 体检目录修改
	 * @param healthDTO
	 * @return
	 */
	public int updateHospCata(HealthDTO healthDTO);
	
	/**
	 * 查询匹配信息
	 * @param healthDTO
	 * @return
	 */
	public ListResult queryItemMatch_page(HealthDTO healthDTO);
	/**
	 * 体检结论修改
	 * @param healthDTO
	 */
	public void updatekeh23(HealthDTO healthDTO);
	/**
	 * 体检目录修改
	 * @param healthDTO
	 */
	public void updateKeh21(HealthDTO healthDTO);
	
	/**
	 * 体检结论匹配信息查询
	 * @param healthDTO
	 * @return
	 */
	public ListResult queryKeh6(HealthDTO healthDTO);
	
	/**
	 * 任务概要：TS19080100098 - 【问题修复】【体检项目目录匹配】-“匹配信息”TAB页签中双击提示删除成功，界面依旧存在该值
	 * 修改说明：增加一个操作keh3表的内容
	 * 修改人及时间：李嘉伦 20190801
	 * @param datas
	 * @return int
	 */
	public int delMatchCata_keh3(List<HealthDTO> datas);

	
	
}
