package com.powersi.ssm.biz.medicare.jdreport.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.hosp.pojo.JdReportDTO;

/**
 * 
  * @ClassName: ApiJdReportService
  * @Description: API接口开发进度上报服务接口类
  * @author zhos
  * @date 2017年2月22日 下午6:52:49
  *
 */
public interface ApiJdReportService {

	/**
	 * 保存进度上报信息
	 */
	public int saveJdReportInfo(JdReportDTO dto,List<Map<String,Object>> list);
	
	/**
	 * 获取进度上报信息
	 */
	@SuppressWarnings("rawtypes")
	public Map getJdReportInfo(JdReportDTO dto);

}
