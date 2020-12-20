package com.powersi.ssm.biz.medicare.actualize.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.powersi.biz.medicare.actualize.pojo.KFD6DTO;
import com.powersi.biz.medicare.actualize.pojo.KFD3DTO;
import com.powersi.biz.medicare.actualize.pojo.KFD4DTO;
import com.powersi.biz.medicare.actualize.service.ActualizeManagerService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.biz.medicare.actualize.pojo.KFD1DTO;
import com.powersi.biz.medicare.actualize.pojo.KFD5DTO;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.PaginationHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.log.service.ErrLogSnService;
import com.powersi.log.service.ErrLogSnService.ProjectType;
import com.powersi.powerreport.service.PowerReportImpl;

@Action(value = "ActualizeManageAction", results = {
		@Result(name = "newPlan", location = "/pages/biz/medicare/actualize/NewPlan.jsp"),
		@Result(name = "success", location = "/pages/biz/medicare/actualize/ActualizeModels.jsp"),
		@Result(name = "newBiz", location = "/pages/biz/medicare/actualize/ActualizeModelsNew.jsp"),
		@Result(name = "queryActualize", location = "/pages/biz/medicare/actualize/ActualizeApply.jsp"),
		@Result(name = "applyReport", location = "/pages/biz/medicare/actualize/ApplyReport.jsp")})
public class ActualizeManageAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	HygeiaServiceManager hygeiaServiceManager;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommunalService communalService;
	@Autowired
	@Qualifier("errLogSnService")
	private ErrLogSnService errLogSnService;
	private KFD1DTO kfd1Dto; // 模板表
	private KFD4DTO kfd4Dto; // 实施计划明细表
	private KFD6DTO kfd6Dto; // 实施计划总表
    private	KFD3DTO kfd3Dto;
    private	KFD5DTO kfd5Dto;
    
	public KFD5DTO getKfd5Dto() {
		return kfd5Dto;
	}
	public void setKfd5Dto(KFD5DTO kfd5Dto) {
		this.kfd5Dto = kfd5Dto;
	}
	public KFD1DTO getKfd1Dto() {
		return kfd1Dto;
	}
	public void setKfd1Dto(KFD1DTO kfd1Dto) {
		this.kfd1Dto = kfd1Dto;
	}
	public KFD3DTO getKfd3Dto() {
		return kfd3Dto;
	}
	public void setKfd3Dto(KFD3DTO kfd3Dto) {
		this.kfd3Dto = kfd3Dto;
	}

	public KFD4DTO getKfd4Dto() {
		return kfd4Dto;
	}

	public void setKfd4Dto(KFD4DTO kfd4Dto) {
		this.kfd4Dto = kfd4Dto;
	}
	public KFD6DTO getKfd6Dto() {
		return kfd6Dto;
	}
	public void setKfd6Dto(KFD6DTO kfd6Dto) {
		this.kfd6Dto = kfd6Dto;
	}
	public CommunalService getCommunalService() {
		return communalService;
	}
	public void setCommunalService(CommunalService communalService) {
		this.communalService = communalService;
	}
	public ErrLogSnService getErrLogSnService() {
		return errLogSnService;
	}
	public void setErrLogSnService(ErrLogSnService errLogSnService) {
		this.errLogSnService = errLogSnService;
	}
	/**
	 * 查询实施模板信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryActualizeModels() {
		try {
			if (kfd1Dto == null) {
				kfd1Dto = new KFD1DTO();
			}
			PagerHelper.initPagination(getRequest());
			String akb020 = BizHelper.getAkb020();
			ActualizeManagerService actualizeService = hygeiaServiceManager.getBeanByClass(ActualizeManagerService.class, akb020);
			kfd1Dto.setAkb020(akb020);
			List list = actualizeService.queryActualizeModelsInfo(kfd1Dto);
			DataGridHelper.render(getRequest(), getResponse(),
					 PagerHelper.getPaginatedList(list));
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}
	/**
	 * 获取所有计划
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryActualizePlan() {
		try {
			ActualizeManagerService actualizeService = hygeiaServiceManager
					.getBeanByClass(ActualizeManagerService.class, BizHelper.getAkb020());
			if(kfd6Dto==null) {
				kfd6Dto = new KFD6DTO();
			}
			List<Map> listMap = new ArrayList<>();
			int count = 0;
			PagerHelper.initPagination(getRequest());
			this.kfd6Dto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.kfd6Dto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
			kfd6Dto.setAaa027(BizHelper.getAaa027());
			listMap = actualizeService.queryActualizePlan(kfd6Dto);
			if(!UtilFunc.isEmpty(listMap)){
				count = listMap.size();
			}
			if(count>0){
			    PaginationHelper.getPaginationObj().setCount(count);
			    DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listMap));
			}
		}catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取实施计划失败："+errLogSn+e.getMessage());
		}
		return NONE;
	}
	/**
	 * 获取医院信息
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public String queryHospInfo() {
		String aaa027 =  getParameter("aaa027");
		try {
			
			ActualizeManagerService actualizeService = hygeiaServiceManager
					.getBeanByClass(ActualizeManagerService.class, BizHelper.getAkb020());
			List<Map> hosplist = new ArrayList<>();
			if(kfd6Dto==null) {
				kfd6Dto = new KFD6DTO();
			}
			kfd6Dto.setAaa027(aaa027);
			hosplist = actualizeService.queryHospInfo(kfd6Dto);
			setJSONReturn(hosplist);
		}catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取医院信息失败："+errLogSn+e.getMessage());
		}
		return NONE;
	}
	/**
	 * 实施计划录入
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public String applyActPlan() {
		try {
			ActualizeManagerService actualizeService = hygeiaServiceManager
					.getBeanByClass(ActualizeManagerService.class, BizHelper.getAkb020());
			kfd6Dto = new KFD6DTO();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			kfd6Dto.setBkf318(getParameter("bkf318"));
			kfd6Dto.setBkf320(sdf.parse(getParameter("bkf320")));
			kfd6Dto.setBkf321(sdf.parse(getParameter("bkf321")));
			kfd6Dto.setAaa027(getParameter("aaa027"));
			kfd6Dto.setAae014(BizHelper.getLoginUser());
			kfd6Dto.setBae101(BizHelper.getUserName());
			kfd6Dto.setBkf319(new Date());
			String modelist = getParameter("modelist");
			String hosplist = getParameter("hosplist");
			List<Map<String, Object>> kfd1map = JsonHelper.toList(modelist);
			List<Map<String, Object>> kfd2map = JsonHelper.toList(hosplist);
			List<Map> kfd1List = new ArrayList<>();
			List<Map> kfd2List = new ArrayList<>();
			for (Map<String, Object> kfd1map1 : kfd1map) {
				Map map = kfd1map1;
				kfd1List.add(map);
			}
			for (Map<String, Object> kfd2map1 : kfd2map) {
				Map map = kfd2map1;
				kfd2List.add(map);
			}
			if(actualizeService.applyActPlan(kfd6Dto, kfd1List, kfd2List)>=1) {
				setJSONReturn("计划已成功录入！");
			}
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("计划申请失败："+errLogSn+e.getMessage());
		}
		return NONE;
	}
	
	/**
	 * 新增业务子类前获取业务大类描述
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryBizInfo() {
		try {
			Map bizInfo = new LinkedHashMap();
			if (kfd1Dto == null) {
				kfd1Dto = new KFD1DTO();
			}
			String akb020 = BizHelper.getAkb020();
			ActualizeManagerService actualizeService = hygeiaServiceManager.getBeanByClass(ActualizeManagerService.class, akb020);
			kfd1Dto.setAkb020(akb020);
			List<Map> bizlist = actualizeService.queryBkf301(kfd1Dto);
			if (bizlist.size() > 0) {
				for (Map map : bizlist) {
					bizInfo.put(map.get("bkf300"), map.get("bkf301"));
				}
			}
			setAttribute("bizInfo", bizInfo);
		} catch (Exception e) {
			this.saveJSONError("获取业务大类描述出错" + e.getMessage());
		}
		return "newBiz";
	}
	/**
	 * 保存新增实施模板信息
	 * 
	 * @return
	 */
	public String saveActualizeModel() {
		try {
			if (kfd1Dto == null) {
				kfd1Dto = new KFD1DTO();
			}
			String akb020 = BizHelper.getAkb020();
			ActualizeManagerService actualizeService = hygeiaServiceManager.getBeanByClass(ActualizeManagerService.class, akb020);
			kfd1Dto.setAkb020(akb020);
			actualizeService.saveActualizeModel(kfd1Dto);
			setJSONReturn("保存模板信息成功！");
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}
	/**
	 * 获取医院计划
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public String queryHospPlan() {
		try {
			if (kfd3Dto == null) {
				kfd3Dto = new KFD3DTO();
			}
			PagerHelper.initPagination(getRequest());
			String akb020 = BizHelper.getAkb020();
			int count = 0;
			ActualizeManagerService actualizeService = hygeiaServiceManager
					.getBeanByClass(ActualizeManagerService.class, akb020);
			kfd3Dto.setAkb020(akb020);
			List<Map>  planlist = actualizeService.queryHospPlan(kfd3Dto);
			if(!UtilFunc.isEmpty(planlist)){
				count = planlist.size();
			}
			if(count>0){
			    PaginationHelper.getPaginationObj().setCount(count);
			    DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(planlist));
			}
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取医院计划失败："+errLogSn+ex.getMessage());
		}
		return NONE;
	}
	
	@SuppressWarnings("rawtypes")
	public String queryPlanDetails() {
		try {
			if (kfd4Dto == null) {
				kfd4Dto = new KFD4DTO();
			}
			String bkf307 = getParameter("bkf307");
			ActualizeManagerService actualizeService = hygeiaServiceManager
					.getBeanByClass(ActualizeManagerService.class, BizHelper.getAkb020());
			kfd4Dto.setBkf307(bkf307);
			List<Map> kfd4Info = actualizeService.queryPlanDetails(kfd4Dto);
			setJSONReturn(kfd4Info);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取医院计划详情失败："+errLogSn+ex.getMessage());
		}
		return NONE;
	}
	
	/**
	 * 保存进度上报日志
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String savePlanLog() {
		try {
			ActualizeManagerService actualizeService = hygeiaServiceManager
					.getBeanByClass(ActualizeManagerService.class, BizHelper.getAkb020());
			String logInfo = getParameter("logInfo");
			List<Map> kfd5Info = new ArrayList<>();
			Map logmap = JsonHelper.toMap(logInfo);
			logmap.put("aae014", BizHelper.getUserName());
			logmap.put("bae101", BizHelper.getLoginUser());
			kfd5Info.add(logmap);
			actualizeService.savePlanLog(kfd5Info);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("保存日志信息失败："+errLogSn+ex.getMessage());
		}
		return NONE;
	}
	
	/**
	 * 获取日志信息
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public String queryLogList() {
		try {
			if(kfd5Dto == null) {
				kfd5Dto = new KFD5DTO();
			}
			ActualizeManagerService actualizeService = hygeiaServiceManager
					.getBeanByClass(ActualizeManagerService.class, BizHelper.getAkb020());
			kfd5Dto.setBkf309(getParameter("bkf309"));
			List<Map> kfd5Info = actualizeService.queryLogList(kfd5Dto);
			setJSONReturn(kfd5Info);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取日志信息失败："+errLogSn+ex.getMessage());
		}
		return NONE;
	}
	
	/**
	 * 获取参与计划的医院
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public String queryHospBybkf306() {
		try {
			if(kfd3Dto == null) {
				kfd3Dto = new KFD3DTO();
			}
			ActualizeManagerService actualizeService = hygeiaServiceManager
					.getBeanByClass(ActualizeManagerService.class, BizHelper.getAkb020());
			kfd3Dto.setBkf306(getParameter("bkf306")); 
			List<Map> hospInfo = actualizeService.queryHospBybkf306(kfd3Dto);
			setJSONReturn(hospInfo);
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取参与计划医院失败："+errLogSn+ex.getMessage());
		}
		return NONE;
	}
	
	/**
	 * 获取参与计划的医院
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public String queryPlanModel() {
		try {
			if(kfd3Dto == null) {
				kfd3Dto = new KFD3DTO();
			}
			PagerHelper.initPagination(getRequest());
			ActualizeManagerService actualizeService = hygeiaServiceManager
					.getBeanByClass(ActualizeManagerService.class, BizHelper.getAkb020());
			kfd3Dto.setBkf306(getParameter("bkf306")); 
			List<Map> PlanModel = actualizeService.queryPlanModel(kfd3Dto);
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper
					.getPaginatedList(PlanModel));
		} catch (Exception ex) {
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, ex, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("获取日志信息失败："+errLogSn+ex.getMessage());
		}
		return NONE;
	}
	/**
	 * 查询实施计划信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryActualize() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				if (kfd3Dto == null) {
					kfd3Dto = new KFD3DTO();
				}
				if (kfd6Dto == null) {
					kfd6Dto = new KFD6DTO();
				}
				PagerHelper.initPagination(getRequest());
				this.kfd3Dto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				this.kfd3Dto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
				String akb020 = BizHelper.getAkb020();
				ActualizeManagerService actualizeService = hygeiaServiceManager.getBeanByClass(ActualizeManagerService.class, akb020);
				kfd3Dto.setAkb020(akb020);
				List<Map> listMap = actualizeService.queryActualize(kfd3Dto, kfd6Dto);
				int count = 0;
				if(!UtilFunc.isEmpty(listMap)){
					count = listMap.size();
				}
				if(count>0){
				    PaginationHelper.getPaginationObj().setCount(count);
				    DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listMap));
				}
			} else {
				return "queryActualize";
			}
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 保存实施计划验收申请
	 * 
	 * @return
	 */
	public String saveActualizeApply() {
		try {
			Map<String, String> retMsg = new HashMap<>();
			retMsg.put("suss", "0");
			retMsg.put("message", "提交验收申请失败");
			String akb020 = BizHelper.getAkb020();
			ActualizeManagerService actualizeService = hygeiaServiceManager.getBeanByClass(ActualizeManagerService.class, akb020);
			String bkf307 = getParameter("bkf307"); // 实施计划医院表ID
			if (kfd4Dto == null) {
				kfd4Dto = new KFD4DTO();
			}
			kfd4Dto.setBkf307(bkf307);
			kfd4Dto.setAkb020(akb020);
			kfd4Dto.setAaa027(BizHelper.getAaa027());
			int ret = actualizeService.queryActualizeInfo(kfd4Dto);
			if (ret == 0) {
				if (kfd3Dto == null) {
					kfd3Dto = new KFD3DTO();
				}
				kfd3Dto.setBkf307(bkf307);
				kfd3Dto.setAkb020(akb020);
				kfd3Dto.setAaa027(BizHelper.getAaa027());
				kfd3Dto.setBkf325(BizHelper.getLoginUser());
				kfd3Dto.setBkf326(BizHelper.getUserName());
				int iret = actualizeService.saveActualizeApply(kfd3Dto);
				if (iret > 0) {
					retMsg.put("suss", "1");
					retMsg.put("message", "保存申请验收成功");
					setJSONReturn(retMsg);
				}
			}else{
				throw new HygeiaException("该计划还存在"+ret+"条未完成的子类业务！");
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/**
     *获取验收申请表
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String applyReport() {
    	try {
    		if(kfd4Dto == null) {
    			kfd4Dto = new KFD4DTO();
    		}
    		List<Map> title= new ArrayList<Map>();
    		Map hospinfo = new HashMap<>();
    		hospinfo.put("date",new SimpleDateFormat("yyyy-MM-dd").format(new Date())); 
    		hospinfo.put("aaa027_name",BizHelper.getAaa027Name());
    		title.add(hospinfo);
    		//明细信息
			ActualizeManagerService actualizeService = hygeiaServiceManager.getBeanByClass(ActualizeManagerService.class,
					BizHelper.getAkb020());
			kfd4Dto.setBkf307(getParameter("bkf307"));
    		List<Map> applyInfo = actualizeService.queryApplyReport(kfd4Dto); 
    		Map mapData = new HashMap();
    		mapData.put("applyInfo", applyInfo);    
    		mapData.put("TITLE", title); 
    		Date date =  new Date();
    		String bizID = "LTBB_"+ date.getTime();
    		
    		PowerReportImpl pri = new PowerReportImpl();
    		String reportID = pri.createReport("actualize/AcceptanceOfApplicationForm.xls", bizID, 1, mapData, "验收申请表","a");
    		setAttribute("reportID", reportID);
    	} catch (Exception e) {
    		saveError("获取验收申请表失败：",e);
    	}
    	return "applyReport";
    }
    
    public String addErrSNInfo() {
		String errLogSn = this.getErrLogSnService().getErrSN(ProjectType.WEB);
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
}
