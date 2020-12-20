package com.powersi.ssm.biz.medicare.diagnose.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.web.BaseAction;

@Action(value = "DiagnoseCancelAction", results = {
		@Result(name = "cancelhealthposts", location = "/pages/biz/medicare/diagnose/diagnoseCancel_Healthposts.jsp") })
public class DiagnoseCancelAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	private DiagnoseInfoDTO diagnoseInfoDTO;
	
	public String cancelhealthposts() {
		return "cancelhealthposts";
	}

	public DiagnoseInfoDTO getDiagnoseInfoDTO() {
		return diagnoseInfoDTO;
	}

	public void setDiagnoseInfoDTO(DiagnoseInfoDTO diagnoseInfoDTO) {
		this.diagnoseInfoDTO = diagnoseInfoDTO;
	}

}
