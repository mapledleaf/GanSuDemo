package com.powersi.biz.medicare.comm.service;

import java.io.Serializable;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.MediDTO;

/**
 * 
 * @author 刘勇
 *
 */
public interface SettlementReportService extends Serializable {

	/**
	 * <pre>
	bka980;reportTemplateID报表模板ID，一般存放在工程的report目录下，按业务分多级子目录，用点拼接，例如：medicare/hosp/xxx.xls
	bka981;bizID业务唯一编号，默认请传输空，这个用于后续按照业务的唯一编号来查询报表，全局不允许重复
	bka982;keepDays保留天数，0标识永久保留，超过配置的天数，生成的报表数据将被删除
	bka983;report_comm报表生成说明，业务传递，例如：财务2015年度数据
	bka984;user_name创建人
	bka985;reportID生成报表数据的ID，后续通过这个ID来读取报表
	 * </pre>
	 * 
	 * @param mediDTO
	 * @param settlementReportData
	 * @return reportID(bka985)
	 */
	@SuppressWarnings("rawtypes")
	public String createSettlementReport(MediDTO mediDTO, Map settlementReportData);

}
