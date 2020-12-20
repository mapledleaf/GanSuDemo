package com.powersi.ssm.biz.medicare.diagnose.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.diagnose.pojo.FeeInfoDTO;
import com.powersi.biz.medicare.diagnose.service.DiagnoseSaveBizService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.log.service.ErrLogSnService;
import com.powersi.log.service.ErrLogSnService.ProjectType;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

import net.sf.json.JSONArray;

@Action(value = "DiagnoseChargeAction", results = {
		@Result(name = "success", location = "/pages/biz/medicare/diagnose/diagnoseCharge.jsp"),
		@Result(name = "diagnoseChargeComm", location = "/pages/biz/medicare/comminter/diagnoseCharge_comm.jsp"),
		@Result(name = "chargehealthposts", location = "/pages/biz/medicare/diagnose/diagnoseCharge_Healthposts.jsp") })
public class DiagnoseChargeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private DiagnoseInfoDTO diagnoseInfoDTO;
	private FeeInfoDTO feeInfoDTO;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private CommunalService communalService;
	@Autowired
	@Qualifier("errLogSnService")
	private ErrLogSnService errLogSnService;
	public String chargehealthposts() {
		return "chargehealthposts";
	}
	
	/**
	 * 通用接口
	 * @return
	 */
	public String diagnoseChargeComm() {
		return "diagnoseChargeComm";
	}
	
	/**
	 * 试算
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public String diagnoseCalcSave() {
		try {
			diagnoseInfoDTO.setAkb020(BizHelper.getAkb020());
			diagnoseInfoDTO.setAkb021(BizHelper.getAkb021());
			diagnoseInfoDTO.setAaa027(BizHelper.getAaa027());
			String jsonFee = URLDecoder.decode(getParameter("feeinfo"), "UTF-8");
			JSONArray jsonArray = JSONArray.fromObject(jsonFee);
			List<FeeInfoDTO> listFeeInfoDTORows = JSONArray.toList(jsonArray, FeeInfoDTO.class);
			DiagnoseSaveBizService diagnoseSaveBizVS = (DiagnoseSaveBizService) hygeiaServiceManager.getBean("diagnoseSaveBizVS",
					BizHelper.getAkb020());
			List listPayInfo = diagnoseSaveBizVS.diagnoseCalcSave(diagnoseInfoDTO, listFeeInfoDTORows);
			this.setJSONReturn("门诊试算成功！", listPayInfo);
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("门诊试算失败！\r\n"+errLogSn + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
	}

	/**
	 * 收费或退费
	 * 
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public String diagnoseCalcEnPay() {
		try {
			diagnoseInfoDTO.setAkb020(BizHelper.getAkb020());
			diagnoseInfoDTO.setAkb021(BizHelper.getAkb021());
			diagnoseInfoDTO.setAaa027(BizHelper.getAaa027());
			String jsonFee = URLDecoder.decode(getParameter("feeinfo"), "UTF-8");
			JSONArray jsonArray = JSONArray.fromObject(jsonFee);
			List<FeeInfoDTO> listFeeInfoDTORows = JSONArray.toList(jsonArray, FeeInfoDTO.class);
			DiagnoseSaveBizService diagnoseSaveBizVS = (DiagnoseSaveBizService) hygeiaServiceManager.getBean("diagnoseSaveBizVS",
					BizHelper.getAkb020());
			List listPayInfo = diagnoseSaveBizVS.diagnoseCalcEnPay(diagnoseInfoDTO, listFeeInfoDTORows);
			this.setJSONReturn("门诊收费成功！", listPayInfo);
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("门诊收费失败！\r\n" + errLogSn + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
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
    
	public DiagnoseInfoDTO getDiagnoseInfoDTO() {
		return diagnoseInfoDTO;
	}

	public void setDiagnoseInfoDTO(DiagnoseInfoDTO diagnoseInfoDTO) {
		this.diagnoseInfoDTO = diagnoseInfoDTO;
	}

	public FeeInfoDTO getFeeInfoDTO() {
		return feeInfoDTO;
	}

	public void setFeeInfoDTO(FeeInfoDTO feeInfoDTO) {
		this.feeInfoDTO = feeInfoDTO;
	}

}
