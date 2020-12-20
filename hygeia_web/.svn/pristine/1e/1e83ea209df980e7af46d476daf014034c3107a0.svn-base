package com.powersi.powerreport.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 报表管理服务 提供报表生成，报表查询等接口
 * 
 * @author 李志钢
 *
 */
public interface PowerReport {

	/**
	 * 根据给定的报表模板，产生带数据的报表
	 * 
	 * @param reportTemplateID
	 *            报表模板ID，一般存放在工程的report目录下，按业务分多级子目录，用点拼接，例如：medicare/hosp/xxx.xls
	 * @param bizID
	 *            业务唯一编号，默认请传输空，这个用于后续按照业务的唯一编号来查询报表，全局不允许重复
	 * @param keepDays
	 *            保留天数，0标识永久保留，超过配置的天数，生成的报表数据将被删除
	 * @param mapData
	 *            符合PowerMarker要求的报表数据
	 * @param report_comm
	 *            报表生成说明，业务传递，例如：财务2015年度数据
	 * @param user_name
	 *            创建人
	 * @return 生成报表数据的ID，后续通过这个ID来读取报表
	 */
	@SuppressWarnings("rawtypes")
	public String createReport(String reportTemplateID, String bizID, int keepDays, Map mapData, String report_comm,
			String user_name);

	/**
	 * 不带keepDays的版本，默认为1天，由于异步查询，无法提供完全不保存的支持
	 */
	@SuppressWarnings("rawtypes")
	public String createReport(String reportTemplateID, String bizID, Map mapData, String report_comm,
			String user_name);

	/**
	 * 删除指定的报表数据
	 * 
	 * @param reportID
	 *            报表的编号，由createReport返回
	 * @param bizID
	 *            如果createReport时传入了业务编号，这里可以根据业务编号查询
	 */
	public void delReport(String reportID, String bizID);

	/**
	 * 得到报表数据文件 reportID 与 bizID 任选其一进行赋值即可
	 * 
	 * @param reportID
	 *            报表的编号，由createReport返回
	 * @param bizID
	 *            如果createReport时传入了业务编号，这里可以根据业务编号查询
	 * @param ops
	 *            系统将向这个流输出报表文件数据
	 */
	public void getReport(String reportID, String bizID, OutputStream ops);

	/**
	 * 得到报表转换成为的html文件 reportID 与 bizID 任选其一进行赋值即可
	 * 
	 * @param reportID
	 *            报表的编号，由createReport返回
	 * @param bizID
	 *            如果createReport时传入了业务编号，这里可以根据业务编号查询
	 * @param ops
	 *            系统将向这个流输出报表文件数据
	 */
	public void getReportHtml(String reportID, String bizID, OutputStream ops);

	/**
	 * List<dto>实体类对象的数据转为List<Map>map对象 通用报表的数据只能通过map对象去渲染，不支持实体类对象
	 * 
	 * @param object
	 *            需要转换的参数 list<dto> 实体类对象数据;
	 * @param listDtoClass
	 *            list<dto>实体类对象的class
	 * @return List<Map>
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> listDtoTolistMap(Object object, Class<?> listDtoClass);

	/**
	 * 获取报表名称，取的数据为报表生成说明
	 * 
	 * @param reportID
	 *            报表的编号，由createReport返回
	 * @param bizID
	 *            如果createReport时传入了业务编号，这里可以根据业务编号查询
	 */
	public String getReportName(String reportID, String bizID);

	/**
	 * 获取默认路径
	 * 
	 * @return String
	 */
	public String getTempPath();

}
