package com.powersi.ssm.biz.medicare.special.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.powersi.biz.medicare.catalog.pojo.HospCataDTO;
import com.powersi.biz.medicare.catalog.service.api.HospCataApiService;
import com.powersi.biz.medicare.comm.pojo.KA06;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.service.DiseaseQueryService;
import com.powersi.biz.medicare.hosp.pojo.ExpertInfoDTO;
import com.powersi.biz.medicare.hosp.pojo.TreatmentDTO;
import com.powersi.biz.medicare.hosp.service.api.ExpertManagerService;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.biz.medicare.special.pojo.MediSpecDTO;
import com.powersi.biz.medicare.special.pojo.MediSpec_ZH_DTO;
import com.powersi.biz.medicare.special.pojo.TwoDisThreeMasterTeamDTO;
import com.powersi.biz.medicare.special.service.MtmmSpecialApplyService;
import com.powersi.biz.medicare.special.service.MzchoHospitalBusApplyService;
import com.powersi.biz.medicare.special.service.TeamVsSelfPayApplyService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.DataGridHelper;
import com.powersi.hygeia.web.util.PagerHelper;
import com.powersi.powerreport.service.PowerReportDao;
import com.powersi.powerreport.service.PowerReportImpl;
import com.powersi.ssm.biz.medicare.common.service.CodeTableSwitch;
import com.powersi.ssm.biz.medicare.common.service.CodeTableSwitchImpl;
import com.powersi.ssm.biz.medicare.common.service.QueryStringService;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

@Action(value = "MzchoHospitalBusApplyAction", results = {
		@Result(name = "queryMzchoPersonInfo", location = "/pages/biz/medicare/special/MzchoHospitalBusApply.jsp"),
		@Result(name = "queryMzChoBsPersonInfo", location = "/pages/biz/medicare/special/MzchoHospitalPersInfo.jsp"),
		@Result(name = "modiSpeciaRegister", location = "/pages/biz/medicare/special/Mz_specialRegister.jsp"),
		@Result(name = "chooseDoctor", location = "/pages/biz/medicare/special/Mz_ChooseDoctor.jsp"),
		@Result(name = "chooseIdentify", location = "/pages/biz/medicare/special/Mz_ChooseIdentify.jsp"),
		@Result(name = "printOut", location = "/pages/biz/medicare/special/Mz_printOut.jsp"),
		@Result(name = "printReceipt", location = "/pages/biz/medicare/special/Mz_printReceipt.jsp"),
		@Result(name = "getChangeApplyHosp", location = "/pages/biz/medicare/special/MZhospChangeApply.jsp"),
		@Result(name = "queryDiseaseInfo", location = "/pages/biz/medicare/special/Mz_specialRegisterQuery.jsp"),
		@Result(name = "Mz_diseaseCostComps", location = "/pages/biz/medicare/special/Mz_diseaseCostComps.jsp") })

public class MzchoHospitalBusApplyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private BeanService beanService;
	@Autowired
	private CommunalService communalService;
	private MediSpecDTO mediSpecDto;
	private MediSpec_ZH_DTO mediSpecZHDto;

	public MediSpec_ZH_DTO getMediSpecZHDto() {
		return mediSpecZHDto;
	}

	public void setMediSpecZHDto(MediSpec_ZH_DTO mediSpecZHDto) {
		this.mediSpecZHDto = mediSpecZHDto;
	}

	public MediSpecDTO getMediSpecDto() {
		return mediSpecDto;
	}

	public void setMediSpecDto(MediSpecDTO mediSpecDto) {
		this.mediSpecDto = mediSpecDto;
	}

	/***
	 * 病种信息查询
	 */
	@SuppressWarnings("rawtypes")
	public String queryDiseaseInfo() {
		try {
			if (this.isPostRequest()) {
				try {
					PagerHelper.initPagination(this.getRequest());
					if (mediSpecZHDto == null) {
						mediSpecZHDto = new MediSpec_ZH_DTO();
					}
					mediSpecZHDto.setAkb020(BizHelper.getAkb020());
					mediSpecZHDto.setAaa027(BizHelper.getAaa027());
					MzchoHospitalBusApplyService service = hygeiaServiceManager
							.getBeanByClass(MzchoHospitalBusApplyService.class, BizHelper.getAkb020());
					List<Map> retList = service.queryDiseaseInfo(mediSpecZHDto);
					CodeTableSwitch codeService = new CodeTableSwitchImpl();
					List<Map> result = codeService.loadSpecialZHDtoInfo(retList, BizHelper.getAaa027());

					PagerHelper.getPaginationObj().setCount(result.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(), PagerHelper.getPaginatedList(result));
				} catch (Exception e) {
					this.saveJSONError(e.getMessage());
				}
				return NONE;
			} else {
				return "queryDiseaseInfo";
			}
		} catch (Exception e) {
			this.saveError(e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 获取疾病诊断
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getbka500() {
		try {
			PagerHelper.initPagination(this.getRequest());
			mediSpecZHDto.setAkb020(BizHelper.getAkb020());
			mediSpecZHDto.setAaa027(BizHelper.getAaa027());

			HospCataApiService hospCataService = hygeiaServiceManager.getBeanByClass(HospCataApiService.class,
					BizHelper.getAkb020());
			HospCataDTO hospCataDto = new HospCataDTO();
			Map bka500List = new HashMap();
			if ("".equals(mediSpecZHDto.getBka006())) {
				setJSONReturn(bka500List);
				return NONE;
			}
			hospCataDto.setBka006(mediSpecZHDto.getBka006());
			hospCataDto.setAaa027(BizHelper.getAaa027());
			ListResult listResult = hospCataService.centerSecondDisease(BizHelper.getAkb020(), hospCataDto);
			if (listResult.getList() != null && listResult.getCount() != 0) {
				List<HospCataDTO> list = (List<HospCataDTO>) listResult.getList();
				for (HospCataDTO hospCataDTO : list) {
					bka500List.put(hospCataDTO.getAka120(), hospCataDTO.getAka121());
				}
				setJSONReturn(bka500List);
			} else {
				saveJSONMessage("没有符合条件的疾病！");
			}
			return NONE;
		} catch (Exception e) {
			this.saveError(e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 取消病种登记
	 * 
	 * @return
	 */
	public String cancelInfo() {
		try {
			if (mediSpecZHDto == null) {
				mediSpecZHDto = new MediSpec_ZH_DTO();
			}
			mediSpecZHDto.setAkb020(BizHelper.getAkb020());
			mediSpecZHDto.setAaa027(BizHelper.getAaa027());
			MzchoHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(MzchoHospitalBusApplyService.class, BizHelper.getAkb020());
			int i = service.cancelDiseaseInfo(mediSpecZHDto);
			if (i > 0) {
				saveJSONMessage("取消病种认定信息成功!");
			} else {
				saveJSONMessage("取消病种认定信息失败!");
			}
			return NONE;
		} catch (Exception e) {
			this.saveError(e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 
	 * 打印导出登记信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String printOut() {
		try {
			PagerHelper.initPagination(this.getRequest());
			if (mediSpecZHDto == null) {
				mediSpecZHDto = new MediSpec_ZH_DTO();
			}
			String bka007 = URLDecoder.decode(mediSpecZHDto.getBka007(), "UTF-8");
			if (bka007 != null && bka007 != "") {// 解决bka007乱码问题
				mediSpecZHDto.setBka007(bka007);
			}
			mediSpecZHDto.setAkb020(BizHelper.getAkb020());
			mediSpecZHDto.setAaa027(BizHelper.getAaa027());
			MzchoHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(MzchoHospitalBusApplyService.class, BizHelper.getAkb020());
			List<Map> retList = service.queryDiseaseInfo(mediSpecZHDto);

			CodeTableSwitch codeService = new CodeTableSwitchImpl();
			List<Map> result = codeService.loadSpecialZHDtoInfo(retList, BizHelper.getAaa027());

			if (this.isPostRequest()) {// 之前需求要查询出显示的表，，后面又去掉了，所以isPostRequest 就进不来了，先放这，后续可以删除；
				try {
					PagerHelper.getPaginationObj().setCount(result.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(), PagerHelper.getPaginatedList(result));
				} catch (Exception e) {
					this.saveJSONError(e.getMessage());
				}
				return NONE;
			} else {
				Date date = new Date();
				String bizID = "specail_" + date.getTime();
				Map dateMap = new HashMap();
				dateMap.put("data", result);
				PowerReportImpl pri = new PowerReportImpl();
				String reportId = pri.createReport("specialbus/MzSpecial.xls", bizID, 1, dateMap, "病种认定登记表",
						BizHelper.getUserName());
				setAttribute("reportId", reportId);
				return "printOut";
			}
		} catch (Exception e) {
			this.saveError(e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 打印病种认定回执单
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String printDiseaseInfo() {
		try {
			String aac003 = URLDecoder.decode(getParameter("mediSpecZHDto.aac003"), "UTF-8");
			if (mediSpecZHDto == null) {
				mediSpecZHDto = new MediSpec_ZH_DTO();
			}
			mediSpecZHDto.setAac003(aac003);
			mediSpecZHDto.setAkb020(BizHelper.getAkb020());
			mediSpecZHDto.setAaa027(BizHelper.getAaa027());

			PowerReportImpl pri = new PowerReportImpl();
			String strBizId = "mzDisease_" + mediSpecZHDto.getAac002();

			PowerReportDao dao = new PowerReportDao();
			Map checkMap = dao.getReportBaseInfoBizID(strBizId);
			if (checkMap != null) {
				pri.delReport(null, strBizId);
				DBHelper.commit();
			}
			List<Map> info = new ArrayList<Map>();
			Map mapData = new HashMap();
			Map map = new HashMap();
			mapData.put("aac003", mediSpecZHDto.getAac003());// 姓名
			mapData.put("aae005", mediSpecZHDto.getAae005());// 电话
			mapData.put("aac002", mediSpecZHDto.getAac002());// 身份证
			info.add(mapData);
			map.put("data", info);
			if (mediSpecZHDto.getBka006() != null && !mediSpecZHDto.getBka006().equals("")) {
				String bka006 = mediSpecZHDto.getBka006().toString();
				mapData.put("bka006", CodetableMapping.getDisplayValue("bka006", bka006, bka006));
			}
			String reportIDfirst = pri.createReport("specialbus/MzDiseaseIdentify.xls", strBizId, 1, map, "特定病种申请回执单",
					"sys");
			setAttribute("reportID", reportIDfirst);
		} catch (Exception e) {
			saveJSONError("生成回执单出错:" + e.getMessage(), e);
		}
		return "printReceipt";
	}

	/**
	 * 
	 * 获得鉴定标准内容
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getIdentify() {
		try {
			if (this.isPostRequest()) {
				try {
					PagerHelper.initPagination(this.getRequest());
					mediSpecZHDto.setAkb020(BizHelper.getAkb020());
					mediSpecZHDto.setAaa027(BizHelper.getAaa027());
					MzchoHospitalBusApplyService service = hygeiaServiceManager
							.getBeanByClass(MzchoHospitalBusApplyService.class, BizHelper.getAkb020());
					List<Map> retList = service.queryIdentifyInfo(mediSpecZHDto);
					if (retList != null && retList.size() > 0) {
						setJSONReturn(retList);
					} else {
						saveJSONMessage("没有对应鉴定信息!");
					}
				} catch (Exception e) {
					this.saveJSONError(e.getMessage());
				}
				return NONE;
			} else {
				return "chooseIdentify";
			}
		} catch (Exception e) {
			this.saveError(e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 获取人员信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryMediPersonInfo() {
		try {
			mediSpecDto = new MediSpecDTO();
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			mediSpecDto.setAaa027(aaa027);
			mediSpecDto.setAkb020(akb020);
			MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
					akb020);
			String arg_value = mediSpecZHDto.getQuerystring();
			arg_value = arg_value.trim();
			String arg_name = getQueryName(arg_value);
			mediSpecDto.setArg_name(arg_name);
			mediSpecDto.setArg_value(arg_value);
			List<Map> personInfo = service.queryMediPersonInfo(mediSpecDto);
			if (null == personInfo || personInfo.size() == 0) {
				throw new HygeiaException("没有查询当相关人员信息！");
			}
			CodeTableSwitch codeService = new CodeTableSwitchImpl();
			Map retPerson = codeService.loadPersonInfo(personInfo.get(0), aaa027);
			retPerson.put("aae004", retPerson.get("aac003"));
			setJSONReturn(retPerson);
		} catch (Exception e) {
			saveJSONError(e);
		}
		return NONE;
	}

	/**
	 * 门诊特定病种登记
	 * 
	 * @return
	 */
	public String saveModiSpeciaRegister() {
		if (this.isPostRequest()) {
			try {
				PagerHelper.initPagination(this.getRequest());
				mediSpecZHDto.setAkb020(BizHelper.getAkb020());
				mediSpecZHDto.setAaa027(BizHelper.getAaa027());
				mediSpecZHDto.setAae011(BizHelper.getUserName());
				mediSpecZHDto.setAae036(DateFunc.dateToString(new Date(), CommunalService.yyyyMMdd));
				mediSpecZHDto.setAae016("1"); // 前台改成了显示->未审核，所以这里直接在后台赋值为1
												// <===>
												// 审核标志(1:未审核；2：初审通过；3：初审不通过；4：复审通过；5：复审不通过)
				MzchoHospitalBusApplyService service = hygeiaServiceManager
						.getBeanByClass(MzchoHospitalBusApplyService.class, BizHelper.getAkb020());
				int i = service.saveDiseaseInfoSave(mediSpecZHDto);
				if (i > 0) {
					setJSONReturn("保存信息成功!");
				}
			} catch (Throwable e) {
				this.saveJSONError(e.getMessage());
			}
			return NONE;
		} else {
			return "modiSpeciaRegister";
		}
	}

	/**
	 * 
	 * 选择专家医生
	 * 
	 * @return
	 */
	public String chooseDoctor() {
		try {
			if (this.isPostRequest()) {
				try {
					PagerHelper.initPagination(this.getRequest());
					mediSpecZHDto.setAkb020(BizHelper.getAkb020());
					mediSpecZHDto.setAaa027(BizHelper.getAaa027());
					ExpertInfoDTO expertInfoDto = new ExpertInfoDTO();
					this.beanService.copyProperties(mediSpecZHDto, expertInfoDto, true);
					ExpertManagerService expertManagerService = this.hygeiaServiceManager
							.getBeanByClass(ExpertManagerService.class, BizHelper.getAkb020());
					expertInfoDto.setAae016("2");
					expertInfoDto.setAae100("1");
					List<ExpertInfoDTO> expertList = expertManagerService.selectByPrimaryKey(expertInfoDto);
					PagerHelper.getPaginationObj().setCount(expertList.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(expertList));
				} catch (Exception e) {
					this.saveJSONError(e.getMessage());
				}
				return NONE;
			} else {
				return "chooseDoctor";
			}
		} catch (Exception e) {
			this.saveError(e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 获取当前医生对应的病种信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String chooseDoctorBka006() {
		try {
			String bkc406 = "'" + getParameter("bkc406_1", "") + "','" + getParameter("bkc406_2", "") + "','"
					+ getParameter("bkc406_3", "") + "'";
			ExpertInfoDTO expertInfoDTO = new ExpertInfoDTO();
			expertInfoDTO.setAae015(DateFunc.getDate());
			if ("'','',''".equals(bkc406)) {
				throw new RuntimeException("bkc406为空");
			}
			expertInfoDTO.setBkc406(bkc406);
			expertInfoDTO.setAkb020(BizHelper.getAkb020());
			ExpertManagerService expertManagerService = this.hygeiaServiceManager
					.getBeanByClass(ExpertManagerService.class, expertInfoDTO.getAkb020());
			List<TreatmentDTO> feeList = expertManagerService.seleselectBySpblitBkc406(expertInfoDTO);

			Map bka006List = new HashMap();
			if (feeList != null && feeList.size() != 0) {

				for (TreatmentDTO treatmentDTO : feeList) {
					if (StringUtils.isNotBlank(treatmentDTO.getBka006())) {
						treatmentDTO.setBka006_name(CodetableMapping.getDisplayValue("bka006$" + "440400",
								treatmentDTO.getBka006(), treatmentDTO.getBka006()));
					}
					bka006List.put(treatmentDTO.getBka006(), treatmentDTO.getBka006_name());
				}
				setJSONReturn(bka006List);
			} else {
				saveJSONMessage("该专家没有添加对应疾病待遇类型！");
			}
			return NONE;
		} catch (Exception e) {
			this.saveError(e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 查询门诊选点业务人员信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryMzchoPersonInfo() {
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			mediSpecDto.setAkb020(BizHelper.getAkb020());
			mediSpecDto.setAkb021(BizHelper.getAkb021());
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			MzchoHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(MzchoHospitalBusApplyService.class, BizHelper.getAkb020());

			String arg_value = mediSpecDto.getArg_value();
			arg_value = arg_value.trim();
			String arg_name = getQueryName(arg_value);
			mediSpecDto.setArg_name(arg_name);
			mediSpecDto.setArg_value(arg_value);

			Map personInfo = service.queryMzchoPersonInfo(mediSpecDto);
			setJSONReturn(personInfo);
		} catch (Exception e) {
			saveJSONError("查询出错！" + e.getMessage());
		}
		return NONE;
	}

	public String getQueryName(String arg_value) {
		QueryStringService queryStringService = hygeiaServiceManager.getBeanByClass(QueryStringService.class,
				BizHelper.getAkb020().toString());
		if (StringUtils.isBlank(arg_value)) {
			throw new HygeiaException("您输入的查询条件为空，请确定！");
		}
		if (arg_value.length() > 20) {
			throw new HygeiaException("您输入的查询条件位数过多");
		}
		if (queryStringService.isValidatedBka100(arg_value)) {
			return "bka100";
		}
		if (queryStringService.isValidatedAllIdcard(arg_value)) {
			return "aac002";
		}
		if (queryStringService.isValidatedAac001(arg_value)) {
			return "aac001";
		}
		if (queryStringService.isValidatedaaz217(arg_value)) {
			return "aaz217";
		}
		return "aac001";
	}

	public String verifyApply() {
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			String akb020 = BizHelper.getAkb020();
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			MzchoHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(MzchoHospitalBusApplyService.class, akb020);
			service.verifyApply(mediSpecDto);
			setJSONReturn("校验成功!");
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	// 保存门诊选点业务申请信息
	public String saveSqInfo() {
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			String akb020 = BizHelper.getAkb020();
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			MzchoHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(MzchoHospitalBusApplyService.class, akb020);
			int i = service.saveSqInfo(mediSpecDto);
			if (i > 0) {
				setJSONReturn("保存信息成功!");
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	// 修改门诊选点业务申请信息
	public String updateSqInfo() {
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			String akb020 = BizHelper.getAkb020();
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			MzchoHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(MzchoHospitalBusApplyService.class, akb020);
			int i = service.updateSqInfo(mediSpecDto);
			if (i > 0) {
				setJSONReturn("修改信息成功!");
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 门诊选点审批人员列表
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getAuditPerInfoList() {
		try {
			String akb020 = BizHelper.getAkb020();
			MzchoHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(MzchoHospitalBusApplyService.class, akb020);
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			mediSpecDto.setAkb020(akb020);
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			List<Map> listMap = service.getAuditPerInfoList(mediSpecDto);
			if (listMap.size() > 0 && listMap != null) {
				setJSONReturn(listMap);
			} else {
				saveJSONMessage("没有对应的申请信息!");
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询门诊选点待修改人员列表
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getModifyPerInfoList() {
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			mediSpecDto.setAkb020(BizHelper.getAkb020());
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			MzchoHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(MzchoHospitalBusApplyService.class, BizHelper.getAkb020());
			// TS18011600265 门诊选点查询界面的查询条件不像原来那样获取人员信息了
			// 通过输入的电脑号或者身份证号查询选点信息
			String arg_value = mediSpecDto.getArg_value();
			if (StringUtils.isNotBlank(arg_value)) {
				arg_value = arg_value.trim();
				String arg_name = getQueryName(arg_value);
				mediSpecDto.setArg_name(arg_name);
				mediSpecDto.setArg_value(arg_value);
			}
			List<Map> listMap = service.getModifyPerInfoList(mediSpecDto);
			CodeTableSwitch codeService = new CodeTableSwitchImpl();
			List<Map> retList = codeService.loadSpecialInfo(listMap, BizHelper.getAaa027());
			if (retList != null && retList.size() > 0) {
				setJSONReturn(retList);
			} else {
				saveJSONMessage("没有对应申请信息!");
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 单击行,获取人员详细信息
	 * 
	 * @return
	 */
	public String queryMzChoBsPersonInfo() {
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			mediSpecDto.setAkb020(BizHelper.getAkb020());
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			MzchoHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(MzchoHospitalBusApplyService.class, BizHelper.getAkb020());
			mediSpecDto = service.queryMzChoBsPersonInfo(mediSpecDto);
			setJSONReturn(mediSpecDto);
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return "queryMzChoBsPersonInfo";
	}

	/**
	 * 删除门诊选点申请信息
	 * 
	 * @return
	 */
	public String deleteMzchoHospitalInfo() {
		mediSpecDto.setAaa027(BizHelper.getAaa027());
		MzchoHospitalBusApplyService service = hygeiaServiceManager.getBeanByClass(MzchoHospitalBusApplyService.class,
				BizHelper.getAkb020());
		try {
			int i = service.deleteMzchoHospitalInfo(mediSpecDto);
			if (i > 0) {
				saveJSONMessage("删除成功!");
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询门诊登记表 通过电脑号查询
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public String getChangeApplyHosp() {
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			mediSpecDto.setAkb020(BizHelper.getAkb020());
			mediSpecDto.setAaa027(BizHelper.getAaa027());

			PowerReportImpl pri = new PowerReportImpl();
			String strBizId = "mzApply_" + mediSpecDto.getAac001();
			PowerReportDao dao = new PowerReportDao();
			Map checkMap = dao.getReportBaseInfoBizID(strBizId);
			if (checkMap != null) {
				pri.delReport(null, strBizId);
				DBHelper.commit();
			}

			Map mapData = new HashMap();
			MzchoHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(MzchoHospitalBusApplyService.class, BizHelper.getAkb020());
			mapData = service.getClinicApplyHosp(mediSpecDto);
			String reportID = pri.createReport("specialbus/menZhenXuanDian.xls", strBizId, 1, mapData, "门诊登记表", "sys");
			// setJSONReturn("生成的报表ID为" + reportID, strBizId);
			mediSpecDto.setBizIDfir(strBizId);
		} catch (Exception e) {
			saveJSONError("生成报表菜单出错:" + e.getMessage(), e);
		}
		return "getChangeApplyHosp";

	}

	/**
	 * 两病三师资格校验
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public String verifyTeamQualifying() {
		try {
			String akb020 = BizHelper.getAkb020();
			TeamVsSelfPayApplyService service = hygeiaServiceManager.getBeanByClass(TeamVsSelfPayApplyService.class,
					akb020);
			TwoDisThreeMasterTeamDTO dto = new TwoDisThreeMasterTeamDTO();
			dto.setAkb020(akb020);
			dto.setAae016("1");
			dto.setAae100("1");
			List<Map> list = service.queryTeamApply(dto);
			if (list != null && list.size() > 0) {
				// TS18012900183 勾选两病三师不去校验病种认定信息 以及两病申请信息
				setJSONReturn("1");
			} else {
				setJSONReturn("该医疗机构不具备两病三师团队资格!", "0");
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}

		return NONE;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String saveDiseaseCostComps() {
		try {
			mediSpecZHDto.setAkb020(BizHelper.getAkb020());
			mediSpecZHDto.setAaa027(BizHelper.getAaa027());
			mediSpecZHDto.setAae011(BizHelper.getLoginUser());
			mediSpecZHDto.setBae100(BizHelper.getUserName());
			mediSpecZHDto.setAae036(DateFunc.dateToString(new Date(), CommunalService.yyyyMMdd));
			mediSpecZHDto.setAae016("1"); // 前台改成了显示->未审核，所以这里直接在后台赋值为1 <===>
											// 审核标志(1:未审核；2：初审通过；3：初审不通过；4：复审通过；5：复审不通过)
			mediSpecZHDto.setAae100("1");

			MzchoHospitalBusApplyService ser = hygeiaServiceManager.getBeanByClass(MzchoHospitalBusApplyService.class,
					BizHelper.getAkb020());
			ser.queryZLDiseaseInfo(mediSpecZHDto);// 判断当前人是否有恶性肿瘤门慢病种认定信息（特定重大疾病自费项目申请登记 需要校验一定存在要有存在有效的恶性肿瘤门慢病种认定信息）
			// String gridDatas1 =
			// URLDecoder.decode(this.getParameter("gridDatas"), "UTF-8");
			String gridDatas = new String(this.getParameter("gridDatas").getBytes("iso8859-1"), "UTF-8");
			List<Map> gridDatasMap = (List<Map>) JSONArray.parse(gridDatas);
			String gridDelRow = new String(this.getParameter("gridDelRow").getBytes("iso8859-1"), "UTF-8");
			List<Map> gridDelRowMap = (List<Map>) JSONArray.parse(gridDelRow);
			// List<Map<String,String>> listObjectSec =
			// JSONArray.parseObject(gridDatas,List.class);

			MzchoHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(MzchoHospitalBusApplyService.class, BizHelper.getAkb020());
			service.saveDiseaseCostComps(mediSpecZHDto, gridDatasMap, gridDelRowMap);
			setJSONReturn("保存成功!");
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}

		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public String queryDiseaseComps() {
		if (mediSpecZHDto == null) {
			mediSpecZHDto = new MediSpec_ZH_DTO();
		}
		PagerHelper.initPagination(this.getRequest());
		mediSpecZHDto.setAkb020(BizHelper.getAkb020());
		mediSpecZHDto.setAaa027(BizHelper.getAaa027());
		mediSpecZHDto.setQueryFlag("1");
		MzchoHospitalBusApplyService mzBusApplyService = hygeiaServiceManager
				.getBeanByClass(MzchoHospitalBusApplyService.class, BizHelper.getAkb020());
		try {
			List<Map> diseaseCompsInfo = mzBusApplyService.queryDiseaseCostComps(mediSpecZHDto);
			PagerHelper.getPaginationObj().setCount(diseaseCompsInfo.size());
			DataGridHelper.render(this.getRequest(), this.getResponse(),
					PagerHelper.getPaginatedList(diseaseCompsInfo));
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public String queryDiseaseCompsDetail() {
		if (mediSpecZHDto == null) {
			mediSpecZHDto = new MediSpec_ZH_DTO();
		}
		mediSpecZHDto.setAkb020(BizHelper.getAkb020());
		mediSpecZHDto.setAaa027(BizHelper.getAaa027());
		mediSpecZHDto.setQueryFlag("detail");
		MzchoHospitalBusApplyService mzBusApplyService = hygeiaServiceManager
				.getBeanByClass(MzchoHospitalBusApplyService.class, BizHelper.getAkb020());
		try {
			List<Map> detailInfo = mzBusApplyService.queryDiseaseCostComps(mediSpecZHDto);
			setJSONReturn(detailInfo);
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public String getPersonCostComps() {
		try {
			mediSpecZHDto.setAkb020(BizHelper.getAkb020());
			mediSpecZHDto.setAaa027(BizHelper.getAaa027());
			MzchoHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(MzchoHospitalBusApplyService.class, BizHelper.getAkb020());
			Map getDatas = service.getPersonCostComps(mediSpecZHDto);
			setJSONReturn(getDatas);
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 
	 * @return
	 */
	public String intoDiseaseCostComps() {
		try {
			if (mediSpecZHDto == null) {
				mediSpecZHDto = new MediSpec_ZH_DTO();
			}
			mediSpecZHDto.setAkb020(BizHelper.getAkb020());
			mediSpecZHDto.setAaa027(BizHelper.getAaa027());
			DiseaseQueryService diseaseQueryService = (DiseaseQueryService) hygeiaServiceManager
					.getBean("diseaseQueryServiceesb", BizHelper.getAkb020());
			List<KA06> aka120Datas = diseaseQueryService.getAka120Datas(mediSpecZHDto);
			getRequest().setAttribute("aka120Datas", aka120Datas);
			return "Mz_diseaseCostComps";
		} catch (Throwable e) {
			this.communalService.error(this.logger, e, null);
			this.saveError(e.getMessage());
			return ERROR;
		}
	}

	public String queryMzBusDetailInfo() {
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			mediSpecDto.setAkb020(BizHelper.getAkb020());
			MzchoHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(MzchoHospitalBusApplyService.class, BizHelper.getAkb020());

			String arg_value = mediSpecDto.getArg_value().trim();
			String arg_name = getQueryName(arg_value);
			mediSpecDto.setArg_name(arg_name);
			mediSpecDto.setArg_value(arg_value);

			mediSpecDto = service.queryMzBusDetailInfo(mediSpecDto);
			setJSONReturn(mediSpecDto);
		} catch (Exception e) {
			saveJSONError("获取人员选点信息出错：" + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 修改门诊定点信息
	 * 
	 * @return
	 */
	public String updateClinicBus() {
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			mediSpecDto.setAkb020(BizHelper.getAkb020());
			MzchoHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(MzchoHospitalBusApplyService.class, BizHelper.getAkb020());
			service.updateClinicBus(mediSpecDto);
			setJSONReturn("修改门诊定点信息成功!");
		} catch (Exception e) {
			saveJSONError("修改门诊定点信息失败：" + e.getMessage());
		}
		return NONE;
	}

}
