package com.powersi.ssm.biz.medicare.jdreport.dao;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.hosp.pojo.JdReportDTO;

/**
 * 
  * @ClassName: ApiJdReportDao
  * @Description: api接口开发与联调进度上报DAO接口类
  * @author zhos
  * @date 2017年2月22日 下午6:59:07
  *
 */
public interface ApiJdReportDao {

	/**
	 * 查询接口信息模板配置
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryJdReportMb();

	/**
	 * 查询进度上报信息
	 * @param akb020 医院编码
	 * @return
	 */
	public List<JdReportDTO> queryJdReport(JdReportDTO dto);
	
	/**
	 * 保存进度上报信息
	 * @param dto 进度上报DTO
	 * @param list 进度上报集合信息
	 * @return
	 */
	public int saveJdReport(JdReportDTO dto,List<JdReportDTO> list);
}
