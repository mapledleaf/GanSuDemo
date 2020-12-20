package com.powersi.ssm.biz.medicare.special.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.special.pojo.MediSpecDTO;
import com.powersi.biz.medicare.special.service.ChangeHospitalBusApplyService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.ssm.biz.medicare.common.service.CodeTableSwitch;
import com.powersi.ssm.biz.medicare.common.service.CodeTableSwitchImpl;
import com.powersi.ssm.biz.medicare.common.service.QueryStringService;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

@Action(value = "ChangeHospitalBusApplyAction", results = {
		@Result(name = "queryChanHospPersonInfo", location = "/pages/biz/medicare/special/ChangeHospitalBusApply.jsp"),
		@Result(name = "getChaPersInfoList", location = "/pages/biz/medicare/special/ChangeHospitalBusAudi.jsp"),
		@Result(name = "hospitalChoose", location = "/pages/biz/medicare/special/ChangeHospitalChoose.jsp"),
		@Result(name = "modifyPersInfo", location = "/pages/biz/medicare/special/ChangeHospitalModifyInfo.jsp") })

public class ChangeHospitalBusApplyAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;

	private MediSpecDTO mediSpecDto;

	public MediSpecDTO getMediSpecDto() {
		return mediSpecDto;
	}

	public void setMediSpecDto(MediSpecDTO mediSpecDto) {
		this.mediSpecDto = mediSpecDto;
	}

	/**
	 * 保存转诊转院基本信息
	 * 
	 * @return
	 */
	public String saveChangeHospitalizedInfo() {
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			ChangeHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(ChangeHospitalBusApplyService.class, BizHelper.getAkb020());
			int i = service.saveChangeHospitalizedInfo(mediSpecDto);
			if (i > 0) {
				setJSONReturn("保存信息成功!");
			}
		} catch (Exception e) {
			saveJSONMessage(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询转诊转院人员基本信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryChanHospPersonInfo() {
		try {
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			String arg_value = mediSpecDto.getArg_value();
			arg_value = arg_value.trim();
			String arg_name = getQueryName(arg_value);
			mediSpecDto.setArg_name(arg_name);
			mediSpecDto.setArg_value(arg_value);
			ChangeHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(ChangeHospitalBusApplyService.class, BizHelper.getAkb020());
			Map personInfo = service.queryChanHospPersonInfo(mediSpecDto);
			setJSONReturn(personInfo);
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
		if (queryStringService.isValidatedaaz217(arg_value)) {
			return "aaz217";
		}
		return "aac001";
	}

	/**
	 * 查询转院医院信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryChangeHospitalChoose() {
		mediSpecDto.setAaa027(BizHelper.getAaa027());
		try {
			ChangeHospitalBusApplyService service = hygeiaServiceManager
					.getBeanByClass(ChangeHospitalBusApplyService.class, BizHelper.getAkb020());
			List<Map> mHosp = service.queryChangeHospitalChoose(mediSpecDto);
			setJSONReturn(mHosp);
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		// return "hospitalChoose";
		return NONE;

	}

	/**
	 * 查询转院待审核人员信息列表
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getChaPersInfoList() {
		mediSpecDto.setAaa027(BizHelper.getAaa027());
		ChangeHospitalBusApplyService service = hygeiaServiceManager.getBeanByClass(ChangeHospitalBusApplyService.class,
				BizHelper.getAkb020());
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			List<Map> listMap = service.getChaPersInfoList(mediSpecDto);
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

	// 转院确认通过
	public String auditPassInfo() {
		String audit_flag = getParameter("audit_flag");
		String inhosp_opinion = getParameter("inhosp_opinion");
		String user = BizHelper.getLoginUser();
		String aaa027 = BizHelper.getAaa027();
		ChangeHospitalBusApplyService service = hygeiaServiceManager.getBeanByClass(ChangeHospitalBusApplyService.class,
				BizHelper.getAkb020());
		try {
			// 将String转为List<Map>
			List<Map<String, Object>> mAuditInfo = JsonHelper.toList(getParameter("applyList"));
			service.auditPassInfo(mAuditInfo, audit_flag, user, inhosp_opinion, aaa027);
			saveJSONMessage("审核成功!");
		} catch (Exception e) {
			saveJSONMessage(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 转诊转诊信息查询
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getSpeBsInfoForEdit() {
		
		try {
			if (mediSpecDto == null) {
				mediSpecDto = new MediSpecDTO();
			}
			mediSpecDto.setAaa027(BizHelper.getAaa027());
			mediSpecDto.setAkb020(BizHelper.getAkb020());
			ChangeHospitalBusApplyService service = hygeiaServiceManager.getBeanByClass(ChangeHospitalBusApplyService.class,
					BizHelper.getAkb020());
			List<Map> listMap = service.getSpeBsInfoForEdit(mediSpecDto);
			CodeTableSwitch codeService = new CodeTableSwitchImpl();
			List<Map> retList = codeService.loadSpecialInfo(listMap,BizHelper.getAaa027());
			if (retList != null && retList.size() > 0) {
				setJSONReturn(retList);
			} else {
				saveJSONMessage("没有对应的申请信息!");
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 单击行,获取转院待修改人员详细信息
	 * 
	 * @return
	 */
	public String querySpeBsInfoForEdit() {
		mediSpecDto.setAaa027(BizHelper.getAaa027());
		ChangeHospitalBusApplyService service = hygeiaServiceManager.getBeanByClass(ChangeHospitalBusApplyService.class,
				BizHelper.getAkb020());
		try {
			mediSpecDto = service.querySpeBsInfoForEdit(mediSpecDto);
			setJSONReturn(mediSpecDto);
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return "modifyPersInfo";
	}

	// 保存修改人员信息
	public String saveModiSpeBsInfo() {
		mediSpecDto.setAaa027(BizHelper.getAaa027());
		ChangeHospitalBusApplyService service = hygeiaServiceManager.getBeanByClass(ChangeHospitalBusApplyService.class,
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

	// 删除修改人员信息
	public String deleteSpeBsInfo() {
		mediSpecDto.setAaa027(BizHelper.getAaa027());
		ChangeHospitalBusApplyService service = hygeiaServiceManager.getBeanByClass(ChangeHospitalBusApplyService.class,
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
}
