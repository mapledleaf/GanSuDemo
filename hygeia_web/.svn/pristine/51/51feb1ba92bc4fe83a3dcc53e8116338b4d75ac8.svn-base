package com.powersi.ssm.biz.medicare.outland.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.outland.pojo.OutDeclDTO;
import com.powersi.biz.medicare.outland.service.api.OutDeclService;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.powerreport.service.PowerReportImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

@Action(value = "OutDeclAction", results = {
		@Result(name = "outlandReport", location = "/pages/biz/medicare/outland/OutlandReport.jsp") })
public class OutDeclAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HygeiaServiceManager hygeiaServiceManager;

	/**
	 * 查询
	 * 
	 * @author yangmj 2017年6月27日 上午8:31:33
	 * @return String
	 */
	@SuppressWarnings({ "rawtypes" })
	public String queryDeclDetail() {
		try {
			OutDeclService declService = hygeiaServiceManager.getBeanByClass(OutDeclService.class,
					BizHelper.getAkb020());

			OutDeclDTO dto = new OutDeclDTO();
			dto.setYearMonth(getParameter("yearMonth"));
			dto.setConfirmflag(getParameter("confirmflag"));
			dto.setAkb020(BizHelper.getAkb020());
			dto.setAaa027(BizHelper.getAaa027());
			Map declList = declService.queryDeclDetail(dto);
			setJSONReturn("data", declList);

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 提取
	 * 
	 * @author yangmj 2017年6月26日 下午1:32:34
	 * @return String
	 */
	public String getDeclDetail() {
		try {
			OutDeclDTO dto = new OutDeclDTO();

			OutDeclService declService = hygeiaServiceManager.getBeanByClass(OutDeclService.class,
					BizHelper.getAkb020());
			dto.setYearMonth(getParameter("yearMonth"));
			dto.setAkb020(BizHelper.getAkb020());
			dto.setAaa027(BizHelper.getAaa027());
			declService.getDeclDetail(dto);
			setJSONReturn("提取信息成功！");
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 确认、不确认
	 * 
	 * @author yangmj 2017年6月26日 下午1:31:56
	 * @return String
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String enterDeclDetail() {
		try {
			OutDeclService declService = hygeiaServiceManager.getBeanByClass(OutDeclService.class,
					BizHelper.getAkb020());
			OutDeclDTO dto = new OutDeclDTO();

			List<Map<String, Object>> detail = JsonHelper.toList(getParameter("data"));
			List<Map> detailList = new ArrayList<>();
			for (Map<String, Object> map : detail) {
				Map dmap = new HashMap<>();
				dmap.put("checkflag", "1");
				dmap.put("showflag", map.get("showflag"));
				dmap.put("yzz061", map.get("yzz061"));
				dmap.put("ykc700", map.get("ykc700"));
				dmap.put("akc194", map.get("akc194"));
				dmap.put("ake105", map.get("ake105"));
				dmap.put("aaz216", map.get("aaz216"));
				dmap.put("akc264", map.get("akc264"));
				dmap.put("ake149", map.get("ake149"));
				dmap.put("aac002", map.get("aac002"));
				detailList.add(dmap);
			}
			dto.setAkb020(BizHelper.getAkb020());
			dto.setAaa027(BizHelper.getAaa027());
			dto.setYearMonth(getParameter("yearMonth"));
			dto.setConfirmflag(getParameter("confirmflag"));
			dto.setDetailList(detailList);
			declService.enterDeclDetail(dto);

			if ("1".equals(getParameter("confirmflag"))) {
				setJSONReturn("确认成功！");
			} else {
				setJSONReturn("取消确认成功！");
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 申报确认
	 * 
	 * @author yangmj 2017年6月26日 下午1:31:56
	 * @return String
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String doDeclare() {
		try {
			OutDeclService declService = hygeiaServiceManager.getBeanByClass(OutDeclService.class,
					BizHelper.getAkb020());
			OutDeclDTO dto = new OutDeclDTO();
			
			List<Map<String, Object>> dataMap = JsonHelper.toList(getParameter("data"));
			List<Map> lsttotalInfo = new ArrayList<>();
			for (Map<String, Object> map : dataMap) {
				Map dmap = new HashMap<>();
				dmap.put("checkflag", "1");
				dmap.put("showflag", map.get("showflag"));
				dmap.put("yzz061", map.get("yzz061"));
				dmap.put("ykc700", map.get("ykc700"));
				dmap.put("akc194", map.get("akc194"));
				dmap.put("ake105", map.get("ake105"));
				dmap.put("aaz216", map.get("aaz216"));
				dmap.put("akc264", map.get("akc264"));
				dmap.put("ake149", map.get("ake149"));
				dmap.put("aac002", map.get("aac002"));
				lsttotalInfo.add(dmap);
			}
			dto.setAkb020(BizHelper.getAkb020());
			dto.setAaa027(BizHelper.getAaa027());
			dto.setYearMonth(getParameter("yearMonth"));
			dto.setDetailList(lsttotalInfo);

			String count = declService.doDeclare(dto);
			if ("0".equals(count)) {
				setJSONReturn("申报确认成功！");
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 取消申报
	 * 
	 * @author yangmj 2017年6月26日 下午1:31:56
	 * @return String
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String backDeclare() {
		try {
			OutDeclService declService = hygeiaServiceManager.getBeanByClass(OutDeclService.class,
					BizHelper.getAkb020());
			OutDeclDTO dto = new OutDeclDTO();
			String otransid = getParameter("otransid");
			List<Map<String, Object>> dataMap = JsonHelper.toList(getParameter("data"));
			List<Map> lsttotalInfo = new ArrayList<>();
			for (Map<String, Object> map : dataMap) {
				Map dmap = new HashMap<>();
				dmap.put("checkflag", "1");
				dmap.put("otransid", otransid);
				dmap.put("showflag", map.get("showflag"));
				dmap.put("yzz061", map.get("yzz061"));
				dmap.put("ykc700", map.get("ykc700"));
				dmap.put("akc194", map.get("akc194"));
				dmap.put("ake105", map.get("ake105"));
				dmap.put("aaz216", map.get("aaz216"));
				dmap.put("akc264", map.get("akc264"));
				dmap.put("ake149", map.get("ake149"));
				dmap.put("aac002", map.get("aac002"));
				lsttotalInfo.add(dmap);
			}
			dto.setOtransid(otransid);
			dto.setYearMonth(getParameter("yearMonth"));
			dto.setAkb020(BizHelper.getAkb020());
			dto.setAaa027(BizHelper.getAaa027());
			dto.setDetailList(lsttotalInfo);
			String count = declService.backDeclare(dto);
			if ("0".equals(count)) {
				setJSONReturn("取消申报成功！");
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 获取报表
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String printReport() {
		try {
			String yearMonth = getParameter("yearMonth");
			OutDeclService declService = hygeiaServiceManager.getBeanByClass(OutDeclService.class,
					BizHelper.getAkb020());
			OutDeclDTO dto = new OutDeclDTO();
			dto.setAkb020(BizHelper.getAkb020());
			dto.setAaa027(BizHelper.getAaa027());
			dto.setYearMonth(yearMonth);
			Map mapList = declService.printReport(dto);
			Map data = new HashMap<>();

			PowerReportImpl pri = new PowerReportImpl();
			List<Map> lstbiz = (List<Map>) mapList.get("lstbiz");
			List<Map> lstzgbiz = (List<Map>) mapList.get("lstzgbiz");
			List<Map> lstjmbiz = (List<Map>) mapList.get("lstjmbiz");
			List<Map> lstzgdetail = (List<Map>) mapList.get("lstzgdetail");
			List<Map> lstjmdetail = (List<Map>) mapList.get("lstjmdetail");

			String SummyReport = null;
			String ZhiGSummyReport = null;
			String JuMSummyReport = null;
			String ZhiGDetailReport = null;
			String JuMDetailReport = null;

			if (lstbiz != null && lstbiz.size() > 0)
				SummyReport = this.summyReport(lstbiz, yearMonth, pri);
			if (lstzgbiz != null && lstzgbiz.size() > 0)
				ZhiGSummyReport = this.ZhiGSummyReport(lstzgbiz, yearMonth, pri);
			if (lstjmbiz != null && lstjmbiz.size() > 0)
				JuMSummyReport = this.JuMSummyReport(lstjmbiz, yearMonth, pri);
			if (lstzgdetail != null && lstzgdetail.size() > 0)
				ZhiGDetailReport = this.ZhiGDetailReport(lstzgdetail, yearMonth, pri);
			if (lstjmdetail != null && lstjmdetail.size() > 0)
				JuMDetailReport = this.JuMDetailReport(lstjmdetail, yearMonth, pri);

			data.put("SummyReport", SummyReport);
			data.put("ZhiGSummyReport", ZhiGSummyReport);
			data.put("JuMSummyReport", JuMSummyReport);
			data.put("ZhiGDetailReport", ZhiGDetailReport);
			data.put("JuMDetailReport", JuMDetailReport);
			setJSONReturn("data", data);
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String summyReport(List<Map> lstbiz, String yearMonth, PowerReportImpl pri) {
		Map dateMap1 = new HashMap<>();
		List<Map> title1 = new ArrayList<Map>();

		int index = 0, totalman = 0, jzzrc = 0, checkman = 0, uncheckman = 0;
		Double akc264all = 0.00, ake149all = 0.00, geczf = 0.00;
		for (Map map : lstbiz) {
			index += 1;
			map.put("index", index);
			String baa027 = String.valueOf(map.get("baa027"));
			map.put("baa027", getBaa027(baa027));
			totalman += Double.valueOf((String) map.get("totalman"));
			jzzrc += Double.valueOf((String) map.get("jzzrc"));
			checkman += Double.valueOf((String) map.get("checkman"));
			uncheckman += Double.valueOf((String) map.get("uncheckman"));
			akc264all += Double.valueOf((String) map.get("akc264all"));
			ake149all += Double.valueOf((String) map.get("ake149all"));
			geczf += Double.valueOf((String) map.get("geczf"));
		}

		DecimalFormat df = new DecimalFormat("#.00");
		Map info1 = new HashMap<>();
		info1.put("index", index);
		info1.put("totalman", totalman);
		info1.put("jzzrc", jzzrc);
		info1.put("checkman", checkman);
		info1.put("uncheckman", uncheckman);
		info1.put("akc264all", df.format(akc264all));
		info1.put("ake149all", df.format(ake149all));
		info1.put("geczf", df.format(geczf));
		String year = yearMonth.substring(0, 4), month = yearMonth.substring(4, 6);
		String todate = year + " 年" + month + " 月" + "1 日";
		info1.put("todate", todate);
		info1.put("enddate", getEndDate(yearMonth));
		info1.put("akb020", BizHelper.getAkb020());
		info1.put("akb021", BizHelper.getAkb021());
		title1.add(info1);

		dateMap1.put("lstbiz", lstbiz);
		dateMap1.put("title", title1);
		Date date = new Date();
		String bizID = "SR_" + date.getTime();

		String reportID = pri.createReport("outland/MonthlyDeclaration_HuiZ.xls", bizID, 1, dateMap1, "申报汇总表",
				BizHelper.getUserName());
		return reportID;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String ZhiGSummyReport(List<Map> lstzgbiz, String yearMonth, PowerReportImpl pri) {
		Map dateMap1 = new HashMap<>();
		List<Map> title1 = new ArrayList<Map>();

		int index = 0, totalman = 0, jzzrc = 0, checkman = 0, uncheckman = 0;
		Double akc264all = 0.00, ake149all = 0.00, geczf = 0.00;
		for (Map map : lstzgbiz) {
			index += 1;
			map.put("index", index);
			String baa027 = String.valueOf(map.get("baa027"));
			map.put("baa027", getBaa027(baa027));

			totalman += Double.valueOf((String) map.get("totalman"));
			jzzrc += Double.valueOf((String) map.get("jzzrc"));
			checkman += Double.valueOf((String) map.get("checkman"));
			uncheckman += Double.valueOf((String) map.get("uncheckman"));
			akc264all += Double.valueOf((String) map.get("akc264all"));
			ake149all += Double.valueOf((String) map.get("ake149all"));
			geczf += Double.valueOf((String) map.get("geczf"));
		}

		DecimalFormat df = new DecimalFormat("#.00");
		Map info1 = new HashMap<>();
		info1.put("index", index);
		info1.put("totalman", totalman);
		info1.put("jzzrc", jzzrc);
		info1.put("checkman", checkman);
		info1.put("uncheckman", uncheckman);
		info1.put("akc264all", df.format(akc264all));
		info1.put("ake149all", df.format(ake149all));
		info1.put("geczf", df.format(geczf));
		String year = yearMonth.substring(0, 4), month = yearMonth.substring(4, 6);
		String todate = year + " 年" + month + " 月" + "1 日";
		info1.put("todate", todate);
		info1.put("enddate", getEndDate(yearMonth));
		info1.put("akb020", BizHelper.getAkb020());
		info1.put("akb021", BizHelper.getAkb021());
		title1.add(info1);

		dateMap1.put("lstbiz", lstzgbiz);
		dateMap1.put("title", title1);
		Date date = new Date();
		String bizID = "ZGSR_" + date.getTime();

		String reportID = pri.createReport("outland/MonthlyDeclaration_Zhig.xls", bizID, 1, dateMap1, "申报汇总表(职工)",
				BizHelper.getUserName());
		return reportID;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String JuMSummyReport(List<Map> lstjmbiz, String yearMonth, PowerReportImpl pri) {
		Map dateMap1 = new HashMap<>();
		List<Map> title1 = new ArrayList<Map>();

		int index = 0, totalman = 0, jzzrc = 0, checkman = 0, uncheckman = 0;
		Double akc264all = 0.00, ake149all = 0.00, geczf = 0.00;
		for (Map map : lstjmbiz) {
			index += 1;
			map.put("index", index);
			String baa027 = String.valueOf(map.get("baa027"));
			map.put("baa027", getBaa027(baa027));

			totalman += Double.valueOf((String) map.get("totalman"));
			jzzrc += Double.valueOf((String) map.get("jzzrc"));
			checkman += Double.valueOf((String) map.get("checkman"));
			uncheckman += Double.valueOf((String) map.get("uncheckman"));
			akc264all += Double.valueOf((String) map.get("akc264all"));
			ake149all += Double.valueOf((String) map.get("ake149all"));
			geczf += Double.valueOf((String) map.get("geczf"));
		}

		DecimalFormat df = new DecimalFormat("#.00");
		Map info1 = new HashMap<>();
		info1.put("index", index);
		info1.put("totalman", totalman);
		info1.put("jzzrc", jzzrc);
		info1.put("checkman", checkman);
		info1.put("uncheckman", uncheckman);
		info1.put("akc264all", df.format(akc264all));
		info1.put("ake149all", df.format(ake149all));
		info1.put("geczf", df.format(geczf));
		String year = yearMonth.substring(0, 4), month = yearMonth.substring(4, 6);
		String todate = year + " 年" + month + " 月" + "1 日";
		info1.put("todate", todate);
		info1.put("enddate", getEndDate(yearMonth));
		info1.put("akb020", BizHelper.getAkb020());
		info1.put("akb021", BizHelper.getAkb021());
		title1.add(info1);

		dateMap1.put("lstbiz", lstjmbiz);
		dateMap1.put("title", title1);
		Date date = new Date();
		String bizID = "JMSR_" + date.getTime();

		String reportID = pri.createReport("outland/MonthlyDeclaration_cxjm.xls", bizID, 1, dateMap1, "申报汇总表(居民)",
				BizHelper.getUserName());
		return reportID;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String JuMDetailReport(List<Map> lstjmbiz, String yearMonth, PowerReportImpl pri) {
		Map dateMap1 = new HashMap<>();
		List<Map> title1 = new ArrayList<Map>();

		int index = 0, count = 0;
		Map baa027map = new HashMap<>();
		Double akc264 = 0.00, ake149 = 0.00, geczf = 0.00;
		for (Map map : lstjmbiz) {
			index += 1;
			map.put("index", index);
			String baa027 = String.valueOf(map.get("baa027"));
			if (!baa027map.containsKey(baa027)) {
				count += 1;
				baa027map.put(baa027, baa027);
			}
			map.put("baa027", getBaa027(baa027));
			map.put("aka130", getAka130((String) map.get("aka130")));
			map.put("bka004", getbka004((String) map.get("bka004")));
			akc264 += Double.valueOf((String) map.get("akc264"));
			ake149 += Double.valueOf((String) map.get("ake149"));
			geczf += Double.valueOf((String) map.get("geczf"));
		}

		DecimalFormat df = new DecimalFormat("#.00");
		Map info1 = new HashMap<>();
		info1.put("count", count);
		info1.put("index", index);
		info1.put("akc264", df.format(akc264));
		info1.put("ake149", df.format(ake149));
		info1.put("geczf", df.format(geczf));
		String year = yearMonth.substring(0, 4), month = yearMonth.substring(4, 6);
		String todate = year + " 年" + month + " 月" + "1 日";
		info1.put("todate", todate);
		info1.put("enddate", getEndDate(yearMonth));
		info1.put("akb020", BizHelper.getAkb020());
		info1.put("akb021", BizHelper.getAkb021());
		info1.put("aaa027", lstjmbiz.get(0).get("aaa027"));
		title1.add(info1);

		dateMap1.put("detail", lstjmbiz);
		dateMap1.put("title", title1);
		Date date = new Date();
		String bizID = "JMDR_" + date.getTime();

		String reportID = pri.createReport("outland/MonthlyDeclarationDetails_cxjm.xls", bizID, 1, dateMap1,
				"申报明细表(居民)", BizHelper.getUserName());
		return reportID;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String ZhiGDetailReport(List<Map> lstzgbiz, String yearMonth, PowerReportImpl pri) {
		Map dateMap1 = new HashMap<>();
		List<Map> title1 = new ArrayList<Map>();

		int index = 0, count = 0;
		Map baa027map = new HashMap<>();
		Double akc264 = 0.00, ake149 = 0.00, geczf = 0.00;

		for (Map map : lstzgbiz) {
			index += 1;

			String baa027 = String.valueOf(map.get("baa027"));
			if (!baa027map.containsKey(baa027)) {
				count += 1;
				baa027map.put(baa027, baa027);
			}
			map.put("index", index);
			map.put("baa027", getBaa027(baa027));
			map.put("aka130", getAka130((String) map.get("aka130")));
			map.put("bka004", getbka004((String) map.get("bka004")));
			akc264 += Double.valueOf((String) map.get("akc264"));
			ake149 += Double.valueOf((String) map.get("ake149"));
			geczf += Double.valueOf((String) map.get("geczf"));
		}

		DecimalFormat df = new DecimalFormat("#.00");
		Map info1 = new HashMap<>();
		info1.put("count", count);
		info1.put("index", index);
		info1.put("akc264", df.format(akc264));
		info1.put("ake149", df.format(ake149));
		info1.put("geczf", df.format(geczf));
		String year = yearMonth.substring(0, 4), month = yearMonth.substring(4, 6);
		String todate = year + " 年" + month + " 月" + "1 日";
		info1.put("todate", todate);
		info1.put("enddate", getEndDate(yearMonth));
		info1.put("akb020", BizHelper.getAkb020());
		info1.put("akb021", BizHelper.getAkb021());
		info1.put("aaa027", lstzgbiz.get(0).get("aaa027"));
		title1.add(info1);

		dateMap1.put("detail", lstzgbiz);
		dateMap1.put("title", title1);
		Date date = new Date();
		String bizID = "ZGDR_" + date.getTime();

		String reportID = pri.createReport("outland/MonthlyDeclarationDetails_Zhig.xls", bizID, 1, dateMap1,
				"申报明细表(职工)", BizHelper.getUserName());
		return reportID;
	}

	/**
	 * 统筹区转为码值
	 * 
	 * @param baa027
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String getBaa027(String baa027) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * from sys_code_table_detail ");
		sql.append("where CODE_TYPE = 'baa027' ");
		sql.append("AND DATA_VALUE = '" + baa027 + "'");
		List<Map> list = DBHelper.executeList(sql.toString());
		if (list.size() > 0) {
			return String.valueOf(list.get(0).get("display_value"));
		}
		return baa027;
	}

	/**
	 * 业务类别转为码值
	 * 
	 * @param baa027
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String getbka004(String bka004) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * from sys_code_table_detail ");
		sql.append("where CODE_TYPE = 'bka004' ");
		sql.append("AND DATA_VALUE = '" + bka004 + "'");
		List<Map> list = DBHelper.executeList(sql.toString());
		if (list.size() > 0) {
			return String.valueOf(list.get(0).get("display_value"));
		}
		return bka004;
	}

	/**
	 * 业务类别转为码值
	 * 
	 * @param baa027
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String getAka130(String aka130) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * from sys_code_table_detail ");
		sql.append("where CODE_TYPE = 'aka130' ");
		sql.append("AND DATA_VALUE = '" + aka130 + "'");
		List<Map> list = DBHelper.executeList(sql.toString());
		if (list.size() > 0) {
			return String.valueOf(list.get(0).get("display_value"));
		}
		return aka130;
	}

	/**
	 * 根据 年、月 获取对应的月份 的 天数
	 */
	private String getEndDate(String yearmonth) {
		int year = Integer.parseInt(yearmonth.substring(0, 4));
		int month = Integer.parseInt(yearmonth.substring(4, 6));
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int day = a.get(Calendar.DATE);
		return year + " 年" + month + " 月" + day + " 日";
	}

}
