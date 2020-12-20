package com.powersi.ssm.biz.medicare.query.action;

import com.powersi.biz.medicare.comm.pojo.Kc90DTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.biz.medicare.query.pojo.BizQueryDTO;
import com.powersi.biz.medicare.query.service.api.BizQueryApiService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.util.*;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.log.service.ErrLogSnService;
import com.powersi.log.service.ErrLogSnService.ProjectType;
import com.powersi.pcloud.dict.service.DictService;
import com.powersi.powerreport.service.PowerReport;
import com.powersi.powerreport.service.PowerReportDao;
import com.powersi.powerreport.service.PowerReportImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.user.service.UserService;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

@Action(value = "BizQueryAction", results = {
		@Result(name = "feeDetail", location = "/pages/biz/medicare/query/BizQueryFeeDetail.jsp"),
		@Result(name = "bizImage", location = "/pages/biz/medicare/query/ClinicBizImage.jsp"),
		@Result(name = "outBizDetail", location = "/pages/biz/medicare/query/OutBizDetail.jsp"),
		@Result(name = "initPage", location = "/pages/biz/medicare/query/QueryFeeItemReport.jsp") })
public class BizQueryAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final PowerReportDao powerReportDao = new PowerReportDao();
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;

	@Autowired
	private CommunalService communalService;

	private BizQueryDTO bizQueryDto;

	@Autowired
	private BeanService beanService;

	@Autowired
	@Qualifier("errLogSnService")
	private ErrLogSnService errLogSnService;

	@Autowired
	private DictService dictService;

	private static final String SERVICE_ID = "bizQueryApiService";

	/**
	 * 获取所有业务信息
	 * 
	 * @return
	 */
	public String queryAllBusinessInfo() {
		try {
			BizQueryApiService bizQueryApiService = (BizQueryApiService) hygeiaServiceManager.getBean(SERVICE_ID,
					BizHelper.getAkb020());
			PagerHelper.initPagination(getRequest());
			bizQueryDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			bizQueryDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
			ListResult listResult = bizQueryApiService.queryAllBusinessInfo(bizQueryDto);
			this.loadAka030(listResult.getList());
			PaginationHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("查询业务信息时出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	/**
	 * 获取信息查询费用汇总信息
	 * 
	 * @param bizDto
	 */
	@SuppressWarnings("rawtypes")
	public String querySum() {
		try {
			BizQueryApiService bizQueryApiService = (BizQueryApiService) hygeiaServiceManager.getBean(SERVICE_ID,
					BizHelper.getAkb020());
			BizQueryDTO queryInfo = bizQueryApiService.querySumFee(this.bizQueryDto, BizHelper.getAkb020());
			Map map = new HashMap<>();
			String[] name = { "zrsTotal", "zfyTotal", "grzhPayTotal", "yydfPayTotal", "xjPayTotal", "syPayTotal",
					"tcPayTotal" };
			this.beanService.copyProperties(queryInfo, map, name);
			setJSONReturn("data", map);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("查询业务汇总信息时出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	/**
	 * 双击行查询费用
	 * 
	 * @return
	 */
	public String queryBizFeeInfo() {
		try {
			BizQueryApiService bizQueryApiService = (BizQueryApiService) hygeiaServiceManager.getBean(SERVICE_ID,
					BizHelper.getAkb020());
			this.bizQueryDto.setAkb020(BizHelper.getAkb020());
			this.bizQueryDto.setAaa027(BizHelper.getAaa027());
			List<Map<String, Object>> cliListM = bizQueryApiService.queryClinicBizFeeInfo(this.bizQueryDto);
			this.loadAka063(cliListM);
			setJSONReturn(cliListM);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("查询业务费用信息时出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	public String initPage() {
		try {
			Map<String, Object> userInfo = new LinkedHashMap<>();
			UserService userService = BeanHelper.getBean(UserService.class);
			List<Map<String, Object>> userlist = userService.queryUserInfosByCurrentDeptId();
			if (userlist.size() > 0) {
				for (Map<String, Object> map : userlist) {
					userInfo.put(map.get("login_user").toString(), map.get("staff_name"));
				}
			}
			this.getRequest().setAttribute("userInfo", userInfo);
			this.getRequest().setAttribute("handle", getParameter("handle"));
			return "initPage";
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("初始化界面参数出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
			return ERROR;
		}
	}

	/**
	 * 查询费用明细清单
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public String queryFeeDetailTable() {
		String reportId = null;
		try {
			if (!WebHelper.isPostRequest(getRequest())) {
				setAttribute("reportId", reportId);
				return "feeDetail";
			} else {
				BizQueryApiService bizQueryApiService = (BizQueryApiService) hygeiaServiceManager.getBean(SERVICE_ID,
						BizHelper.getAkb020());
				PowerReport powerReport = new PowerReportImpl();
				String strBizId = "FYQD_" + bizQueryDto.getAaz217();
				Map powerReportMap = powerReportDao.getReportBaseInfoBizID(strBizId);
				if (powerReportMap != null)
					powerReport.delReport(null, strBizId);

				this.bizQueryDto.setAkb020(BizHelper.getAkb020());
				this.bizQueryDto.setAaa027(BizHelper.getAaa027());
				if ("2".equals(bizQueryDto.getOperate())) {
					bizQueryDto.setOperate(null);
				}
				Map reportData = bizQueryApiService.queryFeeDetailTable(bizQueryDto);// 获取报表数据来源
				this.loadAka063((List) reportData.get("feeData"));
				this.loadAac004((List) reportData.get("head"));
				reportId = powerReport.createReport("query/BusFeeDetail.xls", strBizId, 1, reportData, "医疗保险医嘱明细清单报表",
						BizHelper.getLoginUser());
				setJSONReturn(reportId);
			}
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取费用明细清单失败！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	@SuppressWarnings({ "rawtypes" })
	public String createFeeItemReport() {
		try {
			BizQueryApiService bizQueryApiService = (BizQueryApiService) hygeiaServiceManager.getBean(SERVICE_ID,
					BizHelper.getAkb020());
			String strBizId = "FYTJ_" + bizQueryDto.getAka130() + "_"
					+ DateFunc.dateToString(DateFunc.parseDate(bizQueryDto.getFromdate()), "yyyyMMdd")
					+ DateFunc.dateToString(DateFunc.parseDate(bizQueryDto.getTodate()), "yyyyMMdd");

			PowerReport powerReport = new PowerReportImpl();
			Map powerReportMap = powerReportDao.getReportBaseInfoBizID(strBizId.toString());
			if (powerReportMap != null)
				powerReport.delReport(null, strBizId.toString());

			this.bizQueryDto.setAkb020(BizHelper.getAkb020());
			this.bizQueryDto.setAkb021(BizHelper.getAkb021());
			this.bizQueryDto.setUserName(BizHelper.getUserName());
			Map reportData = bizQueryApiService.getCostSumInfo(this.bizQueryDto);// 中心取报表数据
			this.loadCodeValue(reportData);
			String reportId = powerReport.createReport("query/CostSumReport.xls", strBizId, 1, reportData, "医疗业务费用统计报表",
					BizHelper.getLoginUser());
			setJSONReturn("生成的报表ID为" + reportId, reportId);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取费用汇总清单失败！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	public String showImage() {
		try {
			BizQueryApiService bizQueryApiService = (BizQueryApiService) hygeiaServiceManager.getBean(SERVICE_ID,
					BizHelper.getAkb020());
			this.getBizQueryDto().setAkb020(BizHelper.getAkb020());
			Kc90DTO kc90Dto = bizQueryApiService.getBusinessImage(this.getBizQueryDto());
			this.getBizQueryDto().setBkc290(kc90Dto.getBkc290());
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取业务关联所采集头像出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return "bizImage";
	}

	/**
	 * 查询异地备案信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryOutRecordInfo() {
		try {
			BizQueryApiService bizQueryApiService = (BizQueryApiService) hygeiaServiceManager.getBean(SERVICE_ID,
					BizHelper.getAkb020());
			PagerHelper.initPagination(getRequest());
			bizQueryDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			bizQueryDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());

			this.getBizQueryDto().setAkb020(BizHelper.getAkb020());
			List<Map> recordList = bizQueryApiService.getOutRecordInfo(this.getBizQueryDto());
			PaginationHelper.getPaginationObj().setCount(recordList.size());
			DataGridHelper.render(getRequest(), getResponse(), recordList);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("查询异地备案信息出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	/**
	 * 查询异地备案详细信息
	 * 
	 * @return
	 */
	public String queryOutRecordDetail() {
		try {
			BizQueryApiService bizQueryApiService = (BizQueryApiService) hygeiaServiceManager.getBean(SERVICE_ID,
					BizHelper.getAkb020());
			this.getBizQueryDto().setAkb020(BizHelper.getAkb020());
			this.getBizQueryDto().setAaa027(BizHelper.getAaa027());
			Map<String, Object> recordList = bizQueryApiService.getOutRecordDetail(this.getBizQueryDto());
			setJSONReturn(recordList);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("查询异地备案详细信息出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}
	
	@SuppressWarnings("rawtypes")
	public String queryAll() {
		try {
			if(this.bizQueryDto==null)
				this.bizQueryDto = new BizQueryDTO();
			BizQueryApiService bizQueryApiService = (BizQueryApiService) hygeiaServiceManager.getBean(SERVICE_ID,
					BizHelper.getAkb020());
			Map<String, List<Map>> reMap= bizQueryApiService.queryAll(this.bizQueryDto);
			setJSONReturn(reMap);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("查询业务信息出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	private void loadCodeValue(Map reportData) {
		this.loadAka130((List) reportData.get("head"));
		this.loadAka063((List) reportData.get("feeList"));
		this.loadAaa027((List) reportData.get("head"));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadAka030(List<?> list) {
		for (Object obj : list) {
			if (obj instanceof BizQueryDTO) {
				String aka030 = ((BizQueryDTO) obj).getAka030();
				String disPlayValue = CodetableMapping.getDisplayValue("aka030", aka030, aka030);
				((BizQueryDTO) obj).setAka030(disPlayValue);
			} else if (obj instanceof Map) {
				String aka030 = (String) (((Map) obj).get("aka030"));
				String disPlayValue = CodetableMapping.getDisplayValue("aka030", aka030, aka030);
				((Map) obj).put("aka030", disPlayValue);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadAaa027(List<?> list) {
		for (Object obj : list) {
			if (obj instanceof BizQueryDTO) {
				String aaa027 = ((BizQueryDTO) obj).getAaa027();
				String disPlayValue = CodetableMapping.getDisplayValue("aaa027", aaa027, aaa027);
				((BizQueryDTO) obj).setAaa027(disPlayValue);
			} else if (obj instanceof Map) {
				String center_name = (String) (((Map) obj).get("center_name"));
				String disPlayValue = CodetableMapping.getDisplayValue("aaa027", center_name, center_name);
				((Map) obj).put("center_name", disPlayValue);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadAka130(List<?> list) {
		for (Object obj : list) {
			if (obj instanceof BizQueryDTO) {
				String aka130 = ((BizQueryDTO) obj).getAka130();
				String disPlayValue = CodetableMapping.getDisplayValue("aka130", aka130, aka130);
				((BizQueryDTO) obj).setAka130(disPlayValue);
				String bka006 = ((BizQueryDTO) obj).getBka006();
				String bka006dispValue = "".equals(bka006) ? "全部" : CodetableMapping.getDisplayValue("bka006", bka006, bka006);
				((Map) obj).put("bka006", bka006dispValue);
			} else if (obj instanceof Map) {
				String aka130 = (String) (((Map) obj).get("aka130"));
				String disPlayValue = CodetableMapping.getDisplayValue("aka130", aka130, aka130);
				((Map) obj).put("aka130", disPlayValue);
				String bka006 = (String) (((Map) obj).get("bka006"));
				String bka006dispValue = "".equals(bka006) ? "全部" : CodetableMapping.getDisplayValue("bka006", bka006, bka006);
				((Map) obj).put("bka006", bka006dispValue);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadAka063(List<?> list) {
		for (Object obj : list) {
			if (obj instanceof BizQueryDTO) {

			} else if (obj instanceof Map) {
				Map data =  (Map) obj;
				String aka063 = UtilFunc.getString(data, "aka063");
				String disPlayValue = CodetableMapping.getDisplayValue("aka063", aka063, aka063);
				data.put("aka063", disPlayValue);
				aka063 = UtilFunc.getString(data, "aka063_1");
				disPlayValue = CodetableMapping.getDisplayValue("aka063", aka063, aka063);
				data.put("aka063_1", disPlayValue);
				aka063 = UtilFunc.getString(data, "aka063_2");
				disPlayValue = CodetableMapping.getDisplayValue("aka063", aka063, aka063);
				data.put("aka063_2", disPlayValue);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadAac004(List<?> list) {
		for (Object obj : list) {
			if (obj instanceof BizQueryDTO) {
				System.out.println("YES");
			} else if (obj instanceof Map) {
				String aac004 = (String) (((Map) obj).get("aac004"));
				String disPlayValue = CodetableMapping.getDisplayValue("aac004", aac004, aac004);
				((Map) obj).put("aac004", disPlayValue);
				String aka130 = (String) (((Map) obj).get("aka130"));
				String disaka130 = CodetableMapping.getDisplayValue("aka130", aka130, aka130);
				((Map) obj).put("aka130", disaka130);
				String bka006 = (String) (((Map) obj).get("bka006"));
				String disabka006 = CodetableMapping.getDisplayValue("bka006", bka006, bka006);
				((Map) obj).put("bka006", disabka006);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private void loadBka004(List<?> list) {
		for (Object obj : list) {
			if (obj instanceof BizQueryDTO) {

			} else if (obj instanceof Map) {
				String bka004 = (String) (((Map) obj).get("bka004"));
				String disPlayValue = CodetableMapping.getDisplayValue("bka004", bka004, bka004);
				((Map) obj).put("bka004", disPlayValue);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private void loadBaa027(List<?> list) {
		StringBuffer sql = new StringBuffer();
		for (Object obj : list) {
			sql.delete(0, sql.length());
			sql.append("SELECT * from sys_code_table_detail ");
			sql.append("where CODE_TYPE = 'baa027_all' ");
			if (obj instanceof BizQueryDTO) {
				sql.append("AND DATA_VALUE = '" + ((BizQueryDTO) obj).getBaa027() + "'");
				List<Map> codeTable = DBFunc.executeList(DBHelper.getDefaultConnection(), sql.toString()); // DBHelper.executeList(sql.toString());
				if (codeTable.size() > 0) {
					((BizQueryDTO) obj).setBaa027(String.valueOf(codeTable.get(0).get("display_value")));
				}
			} else if (obj instanceof Map) {
				if (((Map) obj).containsKey("baa027")) {
					sql.append("AND DATA_VALUE = '" + ((Map) obj).get("baa027") + "'");
					List<Map> codeTable = DBFunc.executeList(DBHelper.getDefaultConnection(), sql.toString()); // DBHelper.executeList(sql.toString());
					if (codeTable.size() > 0) {
						((Map) obj).put("baa027", String.valueOf(codeTable.get(0).get("display_value")));
					}
				}
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private void loadBka006(List<?> list) {
		for (Object obj : list) {
			if (obj instanceof BizQueryDTO) {
				String bka006 = ((BizQueryDTO) obj).getBka006();
				String disPlayValue = CodetableMapping.getDisplayValue("bka006$" + BizHelper.getAaa027(), bka006,
						bka006);
				((BizQueryDTO) obj).setBka006(disPlayValue);
			} else if (obj instanceof Map) {
				String bka006 = (String) (((Map) obj).get("bka006"));
				String disPlayValue = CodetableMapping.getDisplayValue("bka006$" + BizHelper.getAaa027(), bka006,
						bka006);
				((Map) obj).put("bka006", disPlayValue);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private void loadBka507(List<?> list) {
		for (Object obj : list) {
			if (obj instanceof BizQueryDTO) {
				/*
				 * String bka507 = ((BizQueryDTO) obj).getBka507(); String disPlayValue =
				 * CodetableMapping.getDisplayValue("bka507", bka507, bka507); ((BizQueryDTO)
				 * obj).setBka507(disPlayValue);
				 */} else if (obj instanceof Map) {
				String bka507 = (String) (((Map) obj).get("bka507"));
				String disPlayValue = CodetableMapping.getDisplayValue("bka507_zx", bka507, bka507);
				((Map) obj).put("bka507", disPlayValue);
			}
		}
	}

	public String addErrSNInfo() {
		String errLogSn = this.errLogSnService.getErrSN(ProjectType.WEB);
		errLogSn = "错误号 " + errLogSn + " 信息： ";
		return errLogSn;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String addNotBlankParameters() {
		Map allParameters = this.getAllParameters();
		if (allParameters == null || allParameters.size() == 0) {
			return "{}";
		}
		Map parameters = new HashMap();
		String key = "", value = "";
		Object objValue = null;
		Iterator it = allParameters.keySet().iterator();
		while (it.hasNext()) {
			key = it.next().toString();
			objValue = allParameters.get(key);
			if (objValue != null) {
				value = objValue.toString();
				if (StringUtils.isNotBlank(value)) {
					parameters.put(key, value);
				}
			}
		}
		return parameters.toString();
	}

	public BizQueryDTO getBizQueryDto() {
		return this.bizQueryDto;
	}

	public void setBizQueryDto(BizQueryDTO bizQueryDto) {
		this.bizQueryDto = bizQueryDto;
	}

	/**
	 * 异地就医信息查询
	 * 
	 * @return
	 */
	public String queryAllBusinessInfoYdjy() {
		try {
			BizQueryApiService bizQueryApiService = (BizQueryApiService) hygeiaServiceManager
					.getBean("bizQueryApiServiceImpl", BizHelper.getAkb020());
			PagerHelper.initPagination(getRequest());
			bizQueryDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			bizQueryDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
			ListResult listResult = bizQueryApiService.queryAllBizInfoYdjy(bizQueryDto);
			this.loadAka030(listResult.getList());
			PaginationHelper.getPaginationObj().setCount(listResult.getCount());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("查询业务信息时出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}

	/**
	 * 双击行查询费用信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryBizFeeInfoYdjy() {
		try {
			BizQueryApiService bizQueryApiService = (BizQueryApiService) hygeiaServiceManager.getBean("bizQueryApiServiceImpl",
					BizHelper.getAkb020());
			this.bizQueryDto.setAkb020(BizHelper.getAkb020());
			this.bizQueryDto.setAaa027(BizHelper.getAaa027());
			List<Map> cliListM = bizQueryApiService.queryClinicBizFeeInfoYdjy(this.bizQueryDto);
			this.loadAka063(cliListM);
			setJSONReturn(cliListM);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("查询业务费用信息时出错！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}
	
	/**
	 * 异地就医查询费用明细清单
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public String queryFeeDetailTableYdjy() {
		String reportId = null;
		try {
			if (!WebHelper.isPostRequest(getRequest())) {
				setAttribute("reportId", reportId);
				return "feeDetail";
			} else {
				BizQueryApiService bizQueryApiService = (BizQueryApiService) hygeiaServiceManager.getBean("bizQueryApiServiceImpl",
						BizHelper.getAkb020());
				PowerReport powerReport = new PowerReportImpl();
				String strBizId = "FYQD_" + bizQueryDto.getAaz217();
				Map powerReportMap = powerReportDao.getReportBaseInfoBizID(strBizId);
				if (powerReportMap != null)
					powerReport.delReport(null, strBizId);

				this.bizQueryDto.setAkb020(BizHelper.getAkb020());
				this.bizQueryDto.setAaa027(BizHelper.getAaa027());
				if ("2".equals(bizQueryDto.getOperate())) {
					bizQueryDto.setOperate(null);
				}
				Map reportData = bizQueryApiService.queryFeeDetailTableYdjy(bizQueryDto);// 获取报表数据来源
				this.loadAka063((List) reportData.get("feeData"));
				this.loadAac004((List) reportData.get("head"));
 				reportId = powerReport.createReport("query/BusFeeDetail.xls", strBizId, 1, reportData, "医疗保险医嘱明细清单报表",
						BizHelper.getLoginUser());
				setJSONReturn(reportId);
			}
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取费用明细清单失败！\r\n" + errLogSn + (ex.getMessage() == null ? "" : ex.getMessage()));
		}
		return NONE;
	}
}
