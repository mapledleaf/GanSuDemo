package com.powersi.biz.medicare.yygh.service.db;

import java.util.List;

import com.powersi.biz.medicare.yygh.pojo.KE01;
import com.powersi.biz.medicare.yygh.pojo.KE02;
import com.powersi.biz.medicare.yygh.pojo.KE07;
import com.powersi.biz.medicare.yygh.pojo.MobilePayDTO;

/**
 * 预约挂号，DB服务接口
 * 
 * @author Administrator
 *
 */
public interface BizYyghService {

	/**
	 * 处理上传预约挂号号源
	 * 
	 * @return int
	 */
	public void processSaveDoctorsource(List<KE01> insertDTORows, List<KE01> updateDTORows);

	/**
	 * 查询预约挂号号源
	 * 
	 * @return list
	 */
	public List<KE01> selectKe01List(KE01 ke01DTORow);

	/**
	 * 每次查询前先删除号源，然后再让医院上传
	 * 
	 * @param ke01DTORow
	 * @return
	 */
	public int deleteKe01(KE01 ke01DTORow);

	/**
	 * 提交预约挂号（或取消）
	 * 
	 * @return
	 */
	public void processSaveOrCancelYygh(List<KE02> ke02DTORows, List<KE01> ke01DTORows);

	/**
	 * 
	 * 获取医院预约挂号（或者取消挂号）信息
	 * 
	 * @return list
	 */
	public List<KE02> selectKe02List(KE02 ke01DTORow);

	/**
	 * 
	 * 通知停诊异动
	 * 
	 * @return list
	 */
	public int notifyTzyd(List<KE02> ke02DTORows);

	/**
	 * 查询预约挂号上传号源里面的科室信息
	 * 
	 * @param ke07
	 * @return
	 */
	public List<KE07> selectKe07List(KE07 ke07);

	/**
	 * 新增ke07
	 * 
	 * @param ke07
	 * @return
	 */
	public int insertKE07(KE07 ke07);

	/**
	 * 获取预约挂号明细
	 * 
	 * @param mobilePayDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryRegistrationDetail(MobilePayDTO mobilePayDTO);

	/**
	 * 获取预约挂号汇总
	 * 
	 * @param mobilePayDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryRegistrationSum(MobilePayDTO mobilePayDTO);

}
