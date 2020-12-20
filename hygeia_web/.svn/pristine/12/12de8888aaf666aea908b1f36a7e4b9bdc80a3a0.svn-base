package com.powersi.ssm.biz.medicare.hosp.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.catalog.service.api.HospElectpresService;
import com.powersi.biz.medicare.comm.pojo.KA06;
import com.powersi.biz.medicare.comm.pojo.KA06DTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.service.DiseaseQueryService;
import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.hosp.pojo.HospBasicsDTO;
import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;
import com.powersi.biz.medicare.hosp.service.api.HospApiService;
import com.powersi.biz.medicare.inhospital.pojo.Kad5DTO;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.powerreport.service.PowerReportImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Action(value = "HospElectpresAction", results = {
		@Result(name = "returnElect", location = "/pages/biz/medicare/special/ElectronicPresQuery.jsp"),
		@Result(name = "hospInfoEdit", location = "/pages/biz/medicare/hosp/HospInfoEdit.jsp"),
		@Result(name = "electronicReport", location = "/pages/biz/medicare/comm/ElectronicReport.jsp") })
public class HospElectpresAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Kad5DTO kad5DTO;
	private DiagnoseInfoDTO diagnoseInfoDTO;
	private HospBasicsDTO hospBasicsDto;
	@Autowired
	private BeanService beanService;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public String setElectronicReport() {
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Map settlementReportData = new HashMap();
			List<Map> data1 = new ArrayList<Map>();
			String personStr = new String(this.getParameter("personData").getBytes("iso8859-1"), "UTF-8");
			String bka019 = null;//new String(diagnoseInfoDTO.getBka019().getBytes("iso8859-1"), "UTF-8");
			String bka026 = null;//new String(diagnoseInfoDTO.getBka026().getBytes("iso8859-1"), "UTF-8");
			String bka006 = new String(diagnoseInfoDTO.getBka006().getBytes("iso8859-1"), "UTF-8");
			JSONObject myJson = JSONObject.fromObject(personStr);
			Map personMap = myJson;
			personMap.put("akb020", BizHelper.getAkb020());
			personMap.put("akb021", BizHelper.getAkb021());
			personMap.put("aaa027", BizHelper.getAaa027());
			personMap.put("bka019", bka019);
			personMap.put("bka043", diagnoseInfoDTO.getBka043());
			personMap.put("bka025", diagnoseInfoDTO.getBka025());
			personMap.put("bka026_name", bka026);
			personMap.put("bka006_name", bka006);
			personMap.put("bka036", BizHelper.getLoginUser());
			int age = Integer.valueOf(sdf.format(date).substring(0, 4))
					- Integer.valueOf(personMap.get("aac006").toString().substring(0, 4));
			personMap.put("aac005", String.valueOf(age));
			personMap.put("ake1id", kad5DTO.getAke1id());
			personMap.put("aac004", personMap.get("aac004") != null && personMap.get("aac004").equals("1") ? "男" : "女");
			String jsonKad5 = URLDecoder.decode(this.getParameter("electDetailData"), "UTF-8");

			JSONArray jsonArray = JSONArray.fromObject(jsonKad5);
			List<Kad5DTO> listKad5InfoDTORows = JSONArray.toList(jsonArray, Kad5DTO.class);
			List<Map> data2 = new ArrayList<Map>();
			if (listKad5InfoDTORows != null && listKad5InfoDTORows.size() > 0) {
				double sum = 0;
				for (Kad5DTO kad5dto : listKad5InfoDTORows) {
					Map kad5dtoMap = new HashMap();
					beanService.copyProperties(kad5dto, kad5dtoMap, true);
					data2.add(kad5dtoMap);
					sum = sum + Double.parseDouble(kad5dto.getBka058());
				}
				personMap.put("aae030", listKad5InfoDTORows.get(0).getBka065() == null ? sdf.format(date)
						: listKad5InfoDTORows.get(0).getBka065());
				personMap.put("bka245", sum);
			}
			data1.add(personMap);
			settlementReportData.put("data1", data1);
			settlementReportData.put("data2", data2);
			PowerReportImpl reportData = new PowerReportImpl();

			String reportID = reportData.createReport("electronic/electronic.xls", "Electronic_" + date.getTime(), 1,
					settlementReportData, "汕头市处方筏", BizHelper.getLoginUser());
			setAttribute("reportID", reportID);
		} catch (Exception e) {
			this.saveError(e.getMessage());
		}
		return "electronicReport";
	}

	/**
	 * 保存电子处方
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String electPresSave() {
		try {
			diagnoseInfoDTO.setAkb020(BizHelper.getAkb020());
			diagnoseInfoDTO.setAkb021(BizHelper.getAkb021());
			diagnoseInfoDTO.setAaa027(BizHelper.getAaa027());
			diagnoseInfoDTO.setBka036(BizHelper.getLoginUser());
			String jsonKad5 = URLDecoder.decode(this.getParameter("kad5List"), "UTF-8");
			String bka020 = URLDecoder.decode(diagnoseInfoDTO.getBka020(), "UTF-8");
			String bka022 = URLDecoder.decode(diagnoseInfoDTO.getBka022(), "UTF-8");
			String bka052 = URLDecoder.decode(diagnoseInfoDTO.getBka502(), "UTF-8");
			diagnoseInfoDTO.setBka020(bka020);
			diagnoseInfoDTO.setBka022(bka022);
			diagnoseInfoDTO.setBka502(bka052);
			JSONArray jsonArray = JSONArray.fromObject(jsonKad5);
			List<Kad5DTO> listKad5InfoDTORows = JSONArray.toList(jsonArray, Kad5DTO.class);

			HospElectpresService hospElectService = hygeiaServiceManager.getBeanByClass(HospElectpresService.class,
					BizHelper.getAkb020());

			String number = hospElectService.electPresSave(diagnoseInfoDTO, listKad5InfoDTORows);
			this.setJSONReturn(number);
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError("保存出错：" + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 加载科室
	 * 
	 * @param bka019List
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String loadBka019List() {
		try {
			HospApiService hospApiService = hygeiaServiceManager.getBeanByClass(HospApiService.class,
					BizHelper.getAkb020());
			HospInfoDTO hospInfoDto = new HospInfoDTO();
			hospInfoDto.setAkb020(BizHelper.getAkb020());
			hospInfoDto.setAae016("1");
			hospInfoDto.setAae100("1");
			List<HospInfoDTO> deptRows = hospApiService.queryHospDept(BizHelper.getAkb020(), hospInfoDto);
			Map bka019List = new LinkedHashMap();
			if (deptRows != null) {
				for (HospInfoDTO deptRow : deptRows) {
					bka019List.put(deptRow.getBkc156(), deptRow.getBkc157());
				}
			}
			this.setJSONReturn(bka019List);
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError("保存出错：" + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询电子处方
	 * 
	 * @author yangmj 2017年11月10日 下午4:19:03
	 * @return String
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String electPresQuery() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				if (kad5DTO == null) {
					kad5DTO = new Kad5DTO();
				}
				kad5DTO.setAkb020(BizHelper.getAkb020());
				kad5DTO.setAkb021(BizHelper.getAkb021());
				kad5DTO.setAaz027(BizHelper.getAaa027());
				HospElectpresService hospElectService = hygeiaServiceManager.getBeanByClass(HospElectpresService.class,
						kad5DTO.getAkb020());
				Map kadMap = hospElectService.electPresQuery(kad5DTO);
				List<Map> kad5List = (List<Map>) kadMap.get("kad5List");
				for (Map map : kad5List) {
					KA06DTO kA06DTO = new KA06DTO();
					kA06DTO.setCurrentPage(1);
					kA06DTO.setPageSize(1000);
					kA06DTO.setAkb020(BizHelper.getAkb020());
					kA06DTO.setAaa027(BizHelper.getAaa027());
					kA06DTO.setAka120(map.get("bka026") != null ? map.get("bka026").toString() : "@@@@@");
					DiseaseQueryService diseaseQueryService = (DiseaseQueryService) this.hygeiaServiceManager
							.getBean("diseaseQueryServiceesb", BizHelper.getAkb020());
					ListResult listResult = diseaseQueryService.queryDisease(kA06DTO);
					map.put("bka026_name", map.get("bka026"));
					if (listResult != null && listResult.getList().size() > 0) {
						List<KA06> mapList = (List<KA06>) listResult.getList();
						map.put("bka026_name", mapList.get(0).getAka121());
					}
					map.put("bka006_name", CodetableMapping.getDisplayValue("bka006$" + BizHelper.getAaa027(),
							map.get("bka006").toString(), map.get("bka006").toString()));
				}

				this.setJSONReturn(kadMap);
			} else {
				/*if (!StringUtil.isBlank(diagnoseInfoDTO.getBka019())) {
					String bka019 = new String(diagnoseInfoDTO.getBka019().getBytes("iso8859-1"), "UTF-8");
					diagnoseInfoDTO.setBka019(bka019);
				}*/
				if (!StringUtil.isBlank(diagnoseInfoDTO.getBka021())) {
					String bka021 = new String(diagnoseInfoDTO.getBka021().getBytes("iso8859-1"), "UTF-8");
					diagnoseInfoDTO.setBka021(bka021);
				}
				if (!StringUtil.isBlank(diagnoseInfoDTO.getBka503())) {
					String bka503 = new String(diagnoseInfoDTO.getBka503().getBytes("iso8859-1"), "UTF-8");
					diagnoseInfoDTO.setBka503(bka503);
				}
				return "returnElect";
			}
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError("查询出错：" + e.getMessage());
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public String electPresDetailQuery() {
		try {
			if (kad5DTO == null) {
				kad5DTO = new Kad5DTO();
			}
			kad5DTO.setAkb020(BizHelper.getAkb020());
			kad5DTO.setAkb021(BizHelper.getAkb021());
			kad5DTO.setAaz027(BizHelper.getAaa027());
			HospElectpresService hospElectService = hygeiaServiceManager.getBeanByClass(HospElectpresService.class,
					kad5DTO.getAkb020());
			Map kadMap = hospElectService.electPresDetailQuery(kad5DTO);
			this.setJSONReturn(kadMap);
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError("查询出错：" + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 电子处方撤销 2017年11月10日 下午4:20:09
	 * 
	 * @return String
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String returnElect() {
		try {
			List<Map<String, Object>> ake1Datas = JsonHelper.toList(getParameter("ake1Datas"));
			List<Map> ake1Maps = new ArrayList<Map>();
			for (Map map : ake1Datas) {
				map.put("aaz027", BizHelper.getAaa027());
				ake1Maps.add(map);
			}
			HospElectpresService hospElectService = hygeiaServiceManager.getBeanByClass(HospElectpresService.class,
					BizHelper.getAkb020());
			String msg = hospElectService.returnElect(ake1Maps);
			if (msg.equals("1")) {
				this.setJSONReturn("撤销成功！");
			}
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError("撤销出错：" + e.getMessage());
		}
		return NONE;
	}
	
	/**
	 * 查询医院基础信息
	 * @return
	 */
	public String queryHospBasicsInfo() {
		try {
			HospElectpresService hospElectService = hygeiaServiceManager.getBeanByClass(HospElectpresService.class,
					BizHelper.getAkb020());
			this.hospBasicsDto = this.getHospBasicsDto()==null?new HospBasicsDTO():this.getHospBasicsDto();
			this.getHospBasicsDto().setAkb020(BizHelper.getAkb020());
			this.getHospBasicsDto().setAaa027(BizHelper.getAaa027());
			Map<String, Object> hospBaseInfo = hospElectService.queryHospBasicsInfo(this.getHospBasicsDto());
			this.beanService.copyProperties(hospBaseInfo, this.getHospBasicsDto(), true);
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError("查询医院信息出错：" + e.getMessage());
		}
		return "hospInfoEdit";
	}
	
	/**
	 * 修改医院基础信息
	 * @return
	 */
	public String editHospBasics() {
		try {
			HospElectpresService hospElectService = hygeiaServiceManager.getBeanByClass(HospElectpresService.class,
					BizHelper.getAkb020());
			this.hospBasicsDto = this.getHospBasicsDto()==null?new HospBasicsDTO():this.getHospBasicsDto();
			hospElectService.editHospBasics(this.getHospBasicsDto());
			this.setJSONReturn("修改成功！");
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError("修改医院信息出错：\n" + e.getMessage());
		}
		return NONE;
	}


	public HospBasicsDTO getHospBasicsDto() {
		return hospBasicsDto;
	}

	public void setHospBasicsDto(HospBasicsDTO hospBasicsDto) {
		this.hospBasicsDto = hospBasicsDto;
	}

	public DiagnoseInfoDTO getDiagnoseInfoDTO() {
		return diagnoseInfoDTO;
	}

	public void setDiagnoseInfoDTO(DiagnoseInfoDTO diagnoseInfoDTO) {
		this.diagnoseInfoDTO = diagnoseInfoDTO;
	}

	public Kad5DTO getKad5DTO() {
		return kad5DTO;
	}

	public void setKad5DTO(Kad5DTO kad5dto) {
		kad5DTO = kad5dto;
	}

}
