package com.powersi.ssm.biz.medicare.special.action;

import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.biz.medicare.special.pojo.MediSpecDTO;
import com.powersi.biz.medicare.special.pojo.MedicareSpecDTO;
import com.powersi.biz.medicare.special.service.MtmmSpecialApplyService;
import com.powersi.comm.service.memory.MemoryDBWrapper;
import com.powersi.config.pojo.BizYyInfo;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.powerreport.service.PowerReportDao;
import com.powersi.powerreport.service.PowerReportImpl;
import com.powersi.ssm.biz.medicare.common.service.CodeTableSwitch;
import com.powersi.ssm.biz.medicare.common.service.CodeTableSwitchImpl;
import com.powersi.ssm.biz.medicare.common.service.QueryStringService;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Action(value = "MtmmSpecialApplyAction", results = {
		@Result(name = "queryMediPersonInfo", location = "/pages/biz/medicare/specia/Mtmm_specialBusApply.jsp"),
		@Result(name = "getSPInfoList", location = "/pages/biz/medicare/special/Mtmm_specialBusAudit.jsp"),
		@Result(name = "getSpeBsInfoForEdit", location = "/pages/biz/medicare/special/Mtmm_specialBusQueryAndModify.jsp"),
		@Result(name = "auditInfo", location = "/pages/biz/medicare/special/Mtmm_AudtiInfo.jsp"),
		@Result(name = "Mm_choose_specialBusSign", location = "/pages/biz/medicare/special/Mm_choose_specialBusSign.jsp"), })

public class MtmmSpecialApplyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	private MediSpecDTO mediSpecDto;
	private MedicareSpecDTO medicareSpecDto;
	@Autowired
	private BeanService beanService;
	@Autowired
	@Qualifier("memoryDBWrapper_core")
	private MemoryDBWrapper memoryDBWrapper;

	public MediSpecDTO getMediSpecDto() {
		return mediSpecDto;
	}

	public void setMediSpecDto(MediSpecDTO mediSpecDto) {
		this.mediSpecDto = mediSpecDto;
	}

	public MedicareSpecDTO getMedicareSpecDto() {
		return medicareSpecDto;
	}

	public void setMedicareSpecDto(MedicareSpecDTO medicareSpecDto) {
		this.medicareSpecDto = medicareSpecDto;
	}

	/**
	 * 医疗管理特殊业务管理,查询人员信息都调用这个方法.
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryMediPersonInfo() {
		try {
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			mediSpecDto.setAaa027(aaa027);
			mediSpecDto.setAkb020(akb020);
			MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
					akb020);
			String arg_value = mediSpecDto.getArg_value();
			arg_value = arg_value.trim();
			String arg_name = getQueryName(arg_value);
			mediSpecDto.setArg_name(arg_name);
			mediSpecDto.setArg_value(arg_value);
			List<Map> personInfo = service.queryMediPersonInfo(mediSpecDto);

			CodeTableSwitch codeService = new CodeTableSwitchImpl();
			Map retPerson = codeService.loadPerson(personInfo, aaa027);

			setJSONReturn(retPerson);
		} catch (Exception e) {
			saveJSONError(e);
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
		return "aac001";
	}

	// 获取门特项目申请类型
	@SuppressWarnings("rawtypes")
	public String getApplyBusiType() {
		try {
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			mediSpecDto.setAaa027(aaa027);
			MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
					akb020);
			List<Map> applyContent = service.getApplyBusiType(mediSpecDto);
			setJSONReturn(applyContent);
		} catch (Exception e) {
			saveJSONError(e);
		}
		return NONE;
	}

	// 获取同类申请信息
	@SuppressWarnings("rawtypes")
	public String verifySPApply() {
		try {
			// String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			mediSpecDto.setAaa027(aaa027);
			MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
					BizHelper.getAkb020());
			Map latestApplyInfo = service.verifySPApply(mediSpecDto);
			setJSONReturn(latestApplyInfo);
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	// 获取门特待审核信息列表
	@SuppressWarnings("rawtypes")
	public String getSPInfoList() {
		// PagerHelper.initPagination(getRequest());
		mediSpecDto.setAaa027(BizHelper.getAaa027());
		mediSpecDto.setAka083(mediSpecDto.getBka006());
		MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
				BizHelper.getAkb020());
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			List<Map> spinfo = service.getSPInfoList(mediSpecDto);
			if (spinfo.size() > 0 && spinfo != null) {
				setJSONReturn(spinfo);
			} else {
				saveJSONMessage("没有对应的申请信息!");
			}
		} catch (Exception e) {
			saveJSONError(e);
		}
		return NONE;
	}

	public String saveSpeBsInfo() {// 保存特殊业务申请信息
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			String akb020 = BizHelper.getAkb020();
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
					akb020);
			int i = service.saveSpeBsInfo(mediSpecDto);
			if (i > 0) {
				setJSONReturn("保存信息成功!");
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	public String verifyHospTreat() {// 检验本院是否可以办理该项业务
		MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
				BizHelper.getAkb020());
		try {
			// String hospitalId = mediSpecDto.getAkb020();
			// String applyContent = mediSpecDto.getAka083();
			mediSpecDto.setAka083(mediSpecDto.getBka006());
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			int count = service.verifyHospTreat(mediSpecDto);// 校验门特业务是否可以在本院申请
			if (count < 1) {
				throw new HygeiaException("本医院不能申请本业务,请选择其他医院申请!");
			}
			saveJSONMessage("");
		} catch (Exception e) {
			saveJSONMessage(e.getMessage());
		}
		return NONE;
	}

	// 获取特殊业务可用疾病信息
	@SuppressWarnings("rawtypes")
	public String getBizInfo() {

		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			mediSpecDto.setAka083(mediSpecDto.getBka006());
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
					BizHelper.getAkb020());
			List diseaseinfo = null;
			if (!(mediSpecDto.getAka083().equals("140") || mediSpecDto.getAka083().equals("640"))) {
				diseaseinfo = service.getBizInfo(mediSpecDto);
			}
			setJSONReturn(diseaseinfo);
		} catch (IOException e) {
			saveJSONMessage(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 保存特殊业务审核信息
	 * 
	 * @return
	 */
	public String auditInfo() {
		String detail = getParameter("applyList");
		MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
				BizHelper.getAkb020());
		// 将String转为List<Map>
		List<Map<String, Object>> mAuditInfo = JsonHelper.toList(detail);
		try {
			service.auditInfo(mAuditInfo);
			saveJSONMessage("审核成功!");
		} catch (Exception e) {
			saveJSONMessage(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 获取门特待修改人员信息列表
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getSpeBsInfoForEdit() {
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			mediSpecDto.setAkb020(BizHelper.getAkb020());

			MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
					BizHelper.getAkb020());
			List<Map> listMap = service.getSpeBsInfoForEdit(mediSpecDto);
			CodeTableSwitch codeService = new CodeTableSwitchImpl();
			List<Map> retList = codeService.loadSpecialInfo(listMap, BizHelper.getAaa027());
			if (retList != null && retList.size() > 0) {
				setJSONReturn(retList);
			} else {
				saveJSONMessage("没有对应申请信息!");
			}
		} catch (Exception e) {
			saveJSONError(e);
		}
		return NONE;
	}

	/**
	 * 双击行,查询人员详细信息
	 * 
	 * @return
	 */
	public String querySpeBsInfoForEdit() {
		String aaa027 = BizHelper.getAaa027();
		mediSpecDto.setAaa027(aaa027);
		MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
				BizHelper.getAkb020());
		try {
			mediSpecDto = service.querySpeBsInfoForEdit(mediSpecDto);
			setJSONReturn(mediSpecDto);
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return "auditInfo";
	}

	/**
	 * 删除修改人员信息
	 * 
	 * @return
	 */
	public String deleteSpeBsInfo() {
		mediSpecDto.setAaa027(BizHelper.getAaa027());
		MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
				BizHelper.getAkb020());
		try {
			int i = service.deleteSpeBsInfo(mediSpecDto);
			if (i > 0) {
				saveJSONMessage("删除成功!");
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	public String saveModiSpeBsInfo() {
		mediSpecDto.setAaa027(BizHelper.getAaa027());
		MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
				BizHelper.getAkb020());
		try {
			int i = service.saveModiSpeBsInfo(mediSpecDto);
			if (i > 0) {
				saveJSONMessage("保存修改信息成功!");
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 打印门特门慢申请表
	 * 
	 * @throws IOException
	 *             add by zhongsong
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String printApplyInfo() {
		MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
				BizHelper.getAkb020());
		try {

			PowerReportImpl pri = new PowerReportImpl();
			Date now = new Date();
			String strBizId = "MTMM" + now.getTime(); // 生成bizId
			// 判断bizId是否已经存在Map，存在先删除老数据，再新增
			PowerReportDao dao = new PowerReportDao();
			Map checkMap = dao.getReportBaseInfoBizID(strBizId);
			if (checkMap != null) {
				pri.delReport(null, strBizId);// 删除老数据
				DBHelper.commit();// 提交事务
			}
			mediSpecDto.setAkb020(BizHelper.getAkb020());
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			List<Map> applyInfoList = service.getSPInfoListForPrint(mediSpecDto);
			String treatment_type = null;
			String pers_type = null;
			if (applyInfoList != null && applyInfoList.size() > 0) {
				Map tempMap = applyInfoList.get(0);
				treatment_type = (String) tempMap.get("treatment_type");
				pers_type = (String) tempMap.get("pers_type");
			}
			Map hosMap = new HashMap();
			hosMap.put("SQL1", applyInfoList);
			String reportId = "";
			if (treatment_type != null && !"".equals(treatment_type)) {
				if ("13".equals(treatment_type.substring(0, 2)) || treatment_type.startsWith("11R")) {
					// 如果perstype为70，71，72，73，74，75则说明此人为城居，否则为城职
					if ("70".equals(pers_type) || "71".equals(pers_type) || "72".equals(pers_type)
							|| "73".equals(pers_type) || "74".equals(pers_type) || "75".equals(pers_type)) {
						reportId = pri.createReport("specialbus/menManChengJu.xls", strBizId, 1, hosMap,
								"广州市城镇居民医疗保险指定慢性病诊断证明书", "sys");
					} else {
						reportId = pri.createReport("specialbus/menManChengZhi.xls", strBizId, 1, hosMap,
								"广州市医疗保险指定慢性病诊断证明书", "sys");
					}
				} else if (!"66".equals(treatment_type.substring(0, 2))) {
					if ("70".equals(pers_type) || "71".equals(pers_type) || "72".equals(pers_type)
							|| "73".equals(pers_type) || "74".equals(pers_type) || "75".equals(pers_type)) {
						reportId = pri.createReport("specialbus/MenTeChengJu.xls", strBizId, 1, hosMap,
								"广州市城镇居民基本医疗保险参保人员", "sys");
					} else {
						reportId = pri.createReport("specialbus/MenTeChengZhi.xls", strBizId, 1, hosMap,
								"广州市城镇职工基本医疗保险参保人员", "sys");
					}
				}
			}
			setJSONReturn("生成的报表ID为" + reportId, strBizId);

		} catch (Exception e) {
			saveJSONError("生成报表菜单出错:" + e.getMessage(), e);
		}
		return NONE;
	}

	/**
	 * 珠海门慢业务选点申请
	 * 
	 * @return
	 */
	public String chooseHospSign() {
		try {
			if (this.isPostRequest()) {
				try {

				} catch (Throwable e) {
					this.saveJSONError(e.getMessage());
				}
				return NONE;
			} else {
				return "Mm_choose_specialBusSign";
			}
		} catch (Throwable e) {
			this.saveError(e.getMessage());
			return ERROR;
		}
	}

	@SuppressWarnings("rawtypes")
	public String chooseSignType() {
		mediSpecDto.setAaa027(BizHelper.getAaa027());
		mediSpecDto.setAkb020(BizHelper.getAkb020());
		MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
				BizHelper.getAkb020());
		try {
			Map map = service.getPersonSignType(mediSpecDto);
			setJSONReturn(map);
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	public String saveSpChooseHosp() {
		mediSpecDto.setAaa027(BizHelper.getAaa027());
		mediSpecDto.setAkb020(BizHelper.getAkb020());
		MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
				BizHelper.getAkb020());
		try {
			int i = service.saveSpChooseHosp(mediSpecDto);
			if (i > 0) {
				saveJSONMessage("选点申请成功!");
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public String queryPersonChooseHosp() {
		mediSpecDto.setAaa027(BizHelper.getAaa027());
		mediSpecDto.setAkb020(BizHelper.getAkb020());
		MtmmSpecialApplyService service = hygeiaServiceManager.getBeanByClass(MtmmSpecialApplyService.class,
				BizHelper.getAkb020());
		if (this.isPostRequest()) {
			try {
				List<Map> retList = service.queryPersonChooseHosp(mediSpecDto);
				CodeTableSwitch codeService = new CodeTableSwitchImpl();
				retList = codeService.loadSpecialInfo(retList, BizHelper.getAaa027());
				if (retList != null && retList.size() > 0) {
					setJSONReturn(retList);
				} else {
					saveJSONMessage("没有查到本医院对应的参保人信息!");
				}
			} catch (Exception e) {
				saveJSONError(e.getMessage());
			}

		} else {
			try {
				List<Map> retList = service.queryPersonChooseHosp(mediSpecDto);
				beanService.copyProperties(retList.get(0), mediSpecDto, true);
				mediSpecDto.setOldAkb020(
						retList.get(0).get("oldakb020") == null ? "" : retList.get(0).get("oldakb020").toString());
				if (!mediSpecDto.getOldAkb020().equals("")) {
					BizYyInfo yyInfo = (BizYyInfo) this.memoryDBWrapper.getMemoryDB().getMapValue("MAP_BIZ_YY_INFO",
							mediSpecDto.getOldAkb020());
					mediSpecDto.setOldAkb020(mediSpecDto.getOldAkb020() + "@" + yyInfo.getYymc());
				}

			} catch (Exception e) {
				saveJSONError(e.getMessage());
			}
			return "Mm_choose_specialBusSign";
		}
		return NONE;
	}

}