package com.powersi.ssm.biz.medicare.procreate.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.procreate.pojo.TreatmentApprobateDTO;
import com.powersi.biz.medicare.procreate.service.api.TreatmentApprobateApiService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.PaginationHelper;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.ssm.biz.medicare.common.service.CodeTableSwitch;
import com.powersi.ssm.biz.medicare.common.service.CodeTableSwitchImpl;
import com.powersi.ssm.biz.medicare.common.service.QueryStringService;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

@Action(value = "MaternityRegHospAction", results = {
		@Result(name = "clinicSelected", location = "/pages/biz/medicare/query/ClinicSelectedQuery.jsp") })
public class MaternityRegHospAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	private TreatmentApprobateDTO taDTO;

	@SuppressWarnings("rawtypes")
	public String getPersonInfo() {
		try {
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			TreatmentApprobateApiService taService = hygeiaServiceManager
					.getBeanByClass(TreatmentApprobateApiService.class, akb020);
			if (taDTO == null) {
				taDTO = new TreatmentApprobateDTO();
			}
			taDTO.setAkb020(akb020);
			taDTO.setAaa027(aaa027);
			String arg_value = taDTO.getArg_value();
			arg_value = arg_value.trim();
			String arg_name = getQueryName(arg_value);
			taDTO.setArg_name(arg_name);
			taDTO.setArg_value(arg_value);
			List<Map> personinfo = taService.getPersonInfo(akb020, taDTO);
			CodeTableSwitch codeService = new CodeTableSwitchImpl();
			Map mPerson = codeService.loadPerson(personinfo, BizHelper.getAaa027());
			setJSONReturn(mPerson);
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	public String approbateInfSave() {
		try {
			String akb020 = BizHelper.getAkb020();
			TreatmentApprobateApiService taService = hygeiaServiceManager
					.getBeanByClass(TreatmentApprobateApiService.class, akb020);
			if (taDTO == null) {
				taDTO = new TreatmentApprobateDTO();
			}
			taDTO.setAkb020(akb020);
			taDTO.setBmc004(akb020);
			taDTO.setBmc006(akb020);
			String strReturn = taService.registerInfSave(akb020, taDTO);
			setJSONReturn(strReturn);
		} catch (Exception ex) {
			saveJSONError(ex);
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public String approbateInfQuery() {
		try {
			PagerHelper.initPagination(getRequest());
			String akb020 = BizHelper.getAkb020();
			TreatmentApprobateApiService taService = hygeiaServiceManager
					.getBeanByClass(TreatmentApprobateApiService.class, akb020);
			if (taDTO == null) {
				taDTO = new TreatmentApprobateDTO();
			}
			taDTO.setAkb020(akb020);

			int pageIndex = PagerHelper.getPaginationObj().getPageIndex();
			int pageSize = PagerHelper.getPaginationObj().getPageSize();

			int first_row = pageSize * (pageIndex - 1) + 1;
			int last_row = pageSize * pageIndex;

			Map mInfo = taService.registerInfQuery(taDTO, first_row, last_row);
			List lInfo = (List) mInfo.get("pageinfo");
			int count = Integer.parseInt(mInfo.get("count").toString());
			if (count > 0) {
				PaginationHelper.getPaginationObj().setCount(count);
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(lInfo));
			}

		} catch (Exception ex) {
			saveJSONError(ex);
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

	public TreatmentApprobateDTO getTaDTO() {
		return taDTO;
	}

	public void setTaDTO(TreatmentApprobateDTO taDTO) {
		this.taDTO = taDTO;
	}

}
