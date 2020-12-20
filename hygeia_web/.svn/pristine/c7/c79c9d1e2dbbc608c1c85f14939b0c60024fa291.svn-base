package com.powersi.powerreport.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.powermarker.PowerMarker;
import com.powersi.powermarker.printer.PowerPrinterConfigs;
import com.powersi.powermarker.utils.Excel2Html;
import com.powersi.powermarker.utils.Word2Html;
import com.powersi.powerreport.service.loader.PowerReportTemplateLoaderImpl_file;
import com.powersi.powerreport.service.store.PowerReportStore;

public class PowerReportImpl implements PowerReport {

	// 临时存放文件的路径，可以在xml中配置，未配置将返回系统临时路径
	// 必须以斜杠结尾（斜杠方向根据操作系统来配置）
	private String tempPath;
	private static final PowerReportDao powerReportDao = new PowerReportDao();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String createReport(String reportTemplateID, String bizID, int keepDays, Map mapData, String report_comm,
			String user_name) {
		PowerReportTemplateLoaderImpl_file powerReportTemplateLoaderImpl_file = new PowerReportTemplateLoaderImpl_file();
		// 判断bizID是否已经存在Map
		Map checkMap = powerReportDao.getReportBaseInfoBizID(bizID);
		if (checkMap != null) {
			throw new HygeiaException("传入的报表业务编号已经存在，业务编号为：" + bizID + "!");
		}
		// 产生reportID
		String reportID = UtilFunc.getUUID();
		// 调用PowerMarker渲染生成报表
		// 2016-11-13 通用报表支持word报表格式 lingang
		// 截取模版报表类型
		String report_type = reportTemplateID.substring(reportTemplateID.lastIndexOf(".") + 1,
				reportTemplateID.length());
		String path = this.getTempPath() + reportID + "." + report_type;
		// 如果是doc直接用文件初始化
		PowerMarker pm;
		if (reportTemplateID.endsWith("doc")) {
			// 取得报表模版目录路径
			String wordtempPath = Thread.currentThread().getContextClassLoader().getResource("/").getPath() + "/report/"
					+ reportTemplateID;
			pm = new PowerMarker(wordtempPath);
			pm.render(path, mapData);
		} else {
			// 使用loader加载报表模板
			InputStream load = powerReportTemplateLoaderImpl_file.load(reportTemplateID);
			pm = new PowerMarker(load);
			pm.render(path, mapData);
		}
		// 打印配置信息
		PowerPrinterConfigs printCfg = pm.getPrinterConfigs();
		// 调用store存储报表，得到store_id
		File xlsFile = new File(path);
		if (!xlsFile.exists()) {
			throw new HygeiaException("报表模板临时文件不存在!,临时路径文件为：" + path);
		}
		Map map = new HashMap();
		String store_id;
		FileInputStream isr = null;
		try {
			isr = new FileInputStream(path);
			PowerReportStore powerReportStore = (PowerReportStore) BeanHelper.getBean("PowerReportStore");
			// 如果是sftp或者ftp返回的是上传的文件路径
			store_id = powerReportStore.store(isr, path);
			// 打印配置信息直接写入到report_base表
			String print_config = (printCfg == null ? "" : printCfg.toJson());
			map.put("print_config", print_config);
			// 赋值store_id,调用dao插入数据库
			map.put("report_id", reportID);
			map.put("biz_id", bizID);
			map.put("store_id", store_id);
			map.put("create_time", new Date());
			map.put("keep_days", keepDays);
			map.put("report_comm", report_comm);
			map.put("user_name", user_name);
			// 2016-11-13 通用报表支持word报表格式 lingang
			map.put("report_type", report_type);
			powerReportDao.insertReportBase(map);
			// 返回报表reportID
			return reportID;
		} catch (FileNotFoundException e) {
			throw new HygeiaException("设置报表模板临时路径文件出错!", e);
		} finally {
			try {
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				throw new HygeiaException("关闭读取报表模板临时路径文件流出错!", e);
			} finally {
				// 删除临时文件 excel 与 html 两个临时文件
				this.deleteFile(path);
			}
		}
	}

	@Override
	@SuppressWarnings({ "rawtypes" })
	public String createReport(String reportTemplateID, String bizID, Map mapData, String report_comm,
			String user_name) {
		return this.createReport(reportTemplateID, bizID, 1, mapData, report_comm, user_name); // 默认1天
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void delReport(String reportID, String bizID) {
		// 判断bizID，活的reportID
		if (StringUtils.isEmpty(reportID) && StringUtils.isEmpty(bizID)) {
			throw new HygeiaException("报表ID与对应的业务编号不能同时为空!");
		}
		String store_id = "";
		// 判断reportID是否为空，两者优先取reportID
		if (StringUtils.isEmpty(reportID)) {
			Map bizIDMap = powerReportDao.getReportBaseInfoBizID(bizID);
			if (bizIDMap == null) {
				throw new HygeiaException("报表基础信息表不存在此业务编号的数据，业务编号为：" + bizID + " ");
			}
			store_id = (String) bizIDMap.get("store_id");
			reportID = (String) bizIDMap.get("report_id");
		} else {
			Map reportIDMap = powerReportDao.getReportBaseInfoReportID(reportID);
			if (reportIDMap == null) {
				throw new HygeiaException("报表基础信息表不存在此报表ID的数据，报表ID为：" + reportID + " ");
			}
			store_id = (String) reportIDMap.get("store_id");
		}
		// 调用dao得到store_id,然后删除报表记录
		powerReportDao.deleteReportBaseInfo(reportID);
		// 调用store，传入store_id,清理报表文件
		PowerReportStore powerReportStore = (PowerReportStore) BeanHelper.getBean("PowerReportStore");
		powerReportStore.delte(store_id);
	}

	@Override
	@SuppressWarnings({ "rawtypes" })
	public void getReport(String reportID, String bizID, OutputStream ops) {
		// 获取store_id //2016-11-13 通用报表支持word报表格式 lingang
		Map reportIDMap = this.getReportBaseInfoById(reportID, bizID);
		String store_id = reportIDMap.get("store_id").toString();
		// 根据加载的报表store_id，调用store得到报表数据，写入ops
		PowerReportStore powerReportStore = (PowerReportStore) BeanHelper.getBean("PowerReportStore");
		powerReportStore.get(store_id, ops);
	}

	@Override
	@SuppressWarnings({ "rawtypes" })
	public void getReportHtml(String reportID, String bizID, OutputStream ops) {
		// 本地创建临时文件，存放带数据的文件
		// 2016-11-13 通用报表支持word报表格式 lingang
		String pathFile = "";
		String pathHtml = "";
		OutputStream ops_tmpfile = null;
		FileInputStream fis = null;
		try {
			// 2016-11-13 通用报表支持word报表格式 lingang
			// 先获取打印配置信息
			Map reportIDMap = this.getReportBaseInfoById(reportID, bizID);// 获取store_id
			String store_id = reportIDMap.get("store_id").toString();
			String report_type = reportIDMap.get("report_type").toString();
			// 本地创建临时文件，存放带数据的excel
			pathFile = getTempPath() + UtilFunc.getUUID() + "." + report_type;
			pathHtml = pathFile.replace(report_type, "html");
			PowerReportStore powerReportStore = (PowerReportStore) BeanHelper.getBean("PowerReportStore");
			// 打印配置信息
			PowerPrinterConfigs printCfg = null;
			if (reportIDMap.get("print_config") != null) {
				Gson gson = new Gson();
				printCfg = gson.fromJson(reportIDMap.get("print_config").toString(), PowerPrinterConfigs.class);
			}
			ops_tmpfile = new FileOutputStream(pathFile);
			// 根据加载的报表store_id，调用store得到报表数据，写入ops
			powerReportStore.get(store_id, ops_tmpfile);
			// 调用Excel2Html进行转换
			// 2016-11-13 通用报表支持word报表格式 lingang 判断是excel还是word，用不同的方法去转换
			if ("xls".equals(report_type)) {
				Excel2Html.convertExcel2Html(pathFile, pathHtml);
			} else if ("doc".equals(report_type)) {
				Word2Html.setCharset("UTF-8");
				Word2Html.convert2Html(pathFile, pathHtml);
			}
			// 将打印配置信息添加到html里面
			if (printCfg != null) {
				PowerMarker.addPrintInfo2Html(pathHtml, printCfg);
			}
			// 输出到ops
			File xlsFile = new File(pathHtml);
			fis = new FileInputStream(xlsFile);
			int temp = -1;
			byte[] bt = new byte[1024]; // 可以根据实际情况调整，建议使用1024，即每次读1KB
			while ((temp = fis.read(bt)) != -1) {
				ops.write(bt, 0, temp); // 建议不要直接用os.write(bt)
			}
		} catch (FileNotFoundException e) {
			throw new HygeiaException("设置本地临时文件出错,错误信息:", e);
		} catch (IOException e) {
			throw new HygeiaException("读取报表模板html临时文件出错,错误信息:", e);
		} catch (ParserConfigurationException e) {
			throw new HygeiaException("转换报表文件出错,报表配置信息异常,错误信息:", e);
		} catch (TransformerException e) {
			throw new HygeiaException("转换报表文件出错,数据传输信息异常,错误信息:", e);
		} catch (Exception e) {
			throw new HygeiaException("转换报表文件出错,错误信息:", e);
		} finally {
			// 关闭流
			try {
				if (ops_tmpfile != null) {
					ops_tmpfile.flush();
					ops_tmpfile.close();
				}
				if (ops != null) {
					ops.flush();
					ops.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				throw new HygeiaException("关闭文件流出错!", e);
			} finally {
				// 2016-11-13 通用报表支持word报表格式 lingang
				// 删除临时文件 报表 与 html 两个临时文件
				if (StringUtils.isNotBlank(pathFile)) {
					this.deleteFile(pathFile);
				}
				if (StringUtils.isNotBlank(pathHtml)) {
					this.deleteFile(pathHtml);
				}
			}
		}
	}

	/**
	 * 获取storeID 通过get("store_id")获取
	 * 
	 * @param reportID
	 * @param bizID
	 * @return Map
	 */
	@SuppressWarnings({ "rawtypes" })
	private Map getReportBaseInfoById(String reportID, String bizID) {
		// 如果为传入reportID, 根据bizID得到reportID
		if (StringUtils.isEmpty(reportID) && StringUtils.isEmpty(bizID)) {
			throw new HygeiaException("报表ID与对应的业务编号不能同时为空!");
		}
		Map reportIDMap = new HashMap();
		// 判断reportID是否为空，两者优先取reportID //dao加载报表记录
		if (StringUtils.isEmpty(reportID)) {
			reportIDMap = powerReportDao.getReportBaseInfoBizID(bizID);
			if (reportIDMap == null) {
				throw new HygeiaException("报表基础信息表不存在此业务编号的数据，业务编号为：" + bizID + " ");
			}
		} else {
			reportIDMap = powerReportDao.getReportBaseInfoReportID(reportID);
			if (reportIDMap == null) {
				throw new HygeiaException("报表基础信息表不存在此报表ID的数据，报表ID为：" + reportID + " ");
			}
		}
		return reportIDMap;
	}

	/**
	 * 删除临时文件
	 * 
	 * @param path
	 *            void
	 */
	private void deleteFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	public String getTempPath() {
		if (StringUtils.isBlank(this.tempPath)) {
			this.tempPath = System.getProperty("java.io.tmpdir");
		}
		// 判断是否是“/“结尾,On Linux: java.io.tmpdir: [/tmp] but On Windows:
		// java.io.tmpdir:[C:\DOCUME~1\joshua\LOCALS~1\Temp\]
		if (!this.tempPath.endsWith(System.getProperty("file.separator"))) {
			this.tempPath = this.tempPath + System.getProperty("file.separator");
		}
		return this.tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map> listDtoTolistMap(Object object, Class<?> dtoClass) {
		Gson gson = new Gson();
		// 先将数据转为json对象
		String dtojson = gson.toJson(object, dtoClass);
		// 将json对象转为List<Map>
		List<Map> dtolist = new ArrayList<Map>();
		dtolist = gson.fromJson(dtojson, dtolist.getClass());
		return dtolist;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getReportName(String reportID, String bizID) {
		// 判断bizID，活的reportID
		if (StringUtils.isEmpty(reportID) && StringUtils.isEmpty(bizID)) {
			throw new HygeiaException("报表ID与对应的业务编号不能同时为空!");
		}
		Map baseInfoMap = new HashMap();
		// 判断reportID是否为空，两者优先取reportID
		if (StringUtils.isEmpty(reportID)) {
			baseInfoMap = powerReportDao.getReportBaseInfoBizID(bizID);
			if (baseInfoMap == null) {
				throw new HygeiaException("报表基础信息表不存在此业务编号的数据，业务编号为：" + bizID + " ");
			}
		} else {
			baseInfoMap = powerReportDao.getReportBaseInfoReportID(reportID);
			if (baseInfoMap == null) {
				throw new HygeiaException("报表基础信息表不存在此报表ID的数据，报表ID为：" + reportID + " ");
			}
		}
		return baseInfoMap.get("report_comm").toString();
	}

}
