package com.powersi.ssm.biz.medicare.universal.action;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.diagnose.pojo.FeeInfoDTO;
import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.biz.medicare.universal.pojo.KCD1_Mz_HisDTO;
import com.powersi.biz.medicare.universal.service.api.DiagnoseQueryApiService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.log.service.ErrLogSnService;
import com.powersi.log.service.ErrLogSnService.ProjectType;
import com.powersi.ssm.biz.medicare.common.service.RemoteCallAPIService;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.common.util.RemoteCallAPIParam;
import com.powersi.ssm.biz.medicare.common.util.RemoteCallAPIResult;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Action(value = "DiagnoseRegisterAction_HIS", results = {
		@Result(name = "registerhealthposts", location = "/pages/biz/medicare/diagnose_his/diagnoseRegister_Healthposts.jsp"),
		@Result(name = "register", location = "/pages/biz/medicare/diagnose_his/diagnoseCharge.jsp"),
		@Result(name = "registerbirth", location = "/pages/biz/medicare/diagnose_his/diagnoseRegister_birth.jsp"),
		@Result(name = "registerDrug", location = "/pages/biz/medicare/diagnose_his/diagnoseCharge_drug.jsp"),
		@Result(name = "registerMaternity", location = "/pages/biz/medicare/diagnose_his/diagnoseCharge_maternity.jsp"),
		@Result(name = "registerAccident", location = "/pages/biz/medicare/diagnose_his/diagnoseCharge_accident.jsp"),
		@Result(name = "registerRetire", location = "/pages/biz/medicare/diagnose_his/diagnoseCharge_retire.jsp"),
		@Result(name = "registerFm", location = "/pages/biz/medicare/diagnose_his/diagnoseCharge_fm.jsp") })
public class DiagnoseRegisterAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private DiagnoseInfoDTO diagnoseInfoDTO;
	private FeeInfoDTO feeInfoDTO;
	private String ake007;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private CommunalService communalService;
	@Autowired
	@Qualifier("errLogSnService")
	private ErrLogSnService errLogSnService;
	@Autowired
	private BeanService beanService;
	@Autowired
	private RemoteCallAPIService remoteCallAPIService;

	public String registerhealthposts() {
		if (!isPostRequest()) {
			loadCodeSelectData();
		}
		return "registerhealthposts";
	}

	public String register() {
		if (!isPostRequest()) {
			loadCodeSelectData();
		}
		if (diagnoseInfoDTO == null) {
			diagnoseInfoDTO = new DiagnoseInfoDTO();
		}
		diagnoseInfoDTO.setAae030(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		this.setAke007(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return "register";
	}
	
	public String registerMaternity() {
		if (!isPostRequest()) {
			loadCodeSelectData();
		}
		if (diagnoseInfoDTO == null) {
			diagnoseInfoDTO = new DiagnoseInfoDTO();
		}
		diagnoseInfoDTO.setAae030(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		this.setAke007(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return "registerMaternity";
	}
	
	public String registerAccident() {
		if (!isPostRequest()) {
			loadCodeSelectData();
		}
		if (diagnoseInfoDTO == null) {
			diagnoseInfoDTO = new DiagnoseInfoDTO();
		}
		diagnoseInfoDTO.setAae030(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		this.setAke007(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return "registerAccident";
	}

	public String registerBirth() {
		if (!isPostRequest()) {
			loadCodeSelectData();
		}
		if (diagnoseInfoDTO == null) {
			diagnoseInfoDTO = new DiagnoseInfoDTO();
		}
		diagnoseInfoDTO.setAae030(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		this.setAke007(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return "registerbirth";
	}

	public String registerDrug() {
		if (!isPostRequest()) {
			loadCodeSelectData();
		}
		if (diagnoseInfoDTO == null) {
			diagnoseInfoDTO = new DiagnoseInfoDTO();
		}
		diagnoseInfoDTO.setAae030(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		this.setAke007(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return "registerDrug";
	}

	public String registerRetire() {
		if (!isPostRequest()) {
			loadCodeSelectData();
		}
		if (diagnoseInfoDTO == null) {
			diagnoseInfoDTO = new DiagnoseInfoDTO();
		}
		diagnoseInfoDTO.setAae030(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		this.setAke007(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return "registerRetire";
	}
	
	public String registerFm() {
		if (!isPostRequest()) {
			loadCodeSelectData();
		}
		if (diagnoseInfoDTO == null) {
			diagnoseInfoDTO = new DiagnoseInfoDTO();
		}
		diagnoseInfoDTO.setAae030(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		this.setAke007(DateFunc.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return "registerFm";
	}

	@SuppressWarnings("rawtypes")
	private void loadCodeSelectData() {
		Map bka019List = new LinkedHashMap();
		Map bka021List = new LinkedHashMap();
		Map bka503List = new LinkedHashMap();
		loadBka019List(bka019List);
		setAttribute("bka019List", bka019List);
		setAttribute("bka021List", bka021List);
		setAttribute("bka503List", bka503List);
	}

	/**
	 * 试算
	 * 
	 * 
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes" })
	public String diagnoseCalcSave() {
		try {
			diagnoseInfoDTO.setAkb020(BizHelper.getAkb020());
			diagnoseInfoDTO.setAkb021(BizHelper.getAkb021());
			diagnoseInfoDTO.setAaa027(BizHelper.getAaa027());
			diagnoseInfoDTO.setAae011(BizHelper.getLoginUser());
			diagnoseInfoDTO.setBka015(BizHelper.getUserName());
			diagnoseInfoDTO.setAae030(this.getDiagnoseInfoDTO().getAae030());
			if (StringUtils.isBlank(this.getDiagnoseInfoDTO().getBka020())
					&& StringUtils.isNotBlank(this.getDiagnoseInfoDTO().getAkf001())) {
				this.getDiagnoseInfoDTO().setBka020(this.getDiagnoseInfoDTO().getAkf001());
			}
			if (StringUtils.isBlank(this.getDiagnoseInfoDTO().getBka022())
					&& StringUtils.isNotBlank(this.getDiagnoseInfoDTO().getBka021())) {
				this.getDiagnoseInfoDTO().setBka022(this.getDiagnoseInfoDTO().getBka021());
			}
			String jsonFee = URLDecoder.decode(this.getParameter("feeinfo"), "UTF-8");
			JSONArray jsonArray = JSONArray.fromObject(jsonFee);
			List<Map> listFeeInfoDTORows = JSONArray.toList(jsonArray, new HashMap(), new JsonConfig());
			for (int i = 0; listFeeInfoDTORows != null && i < listFeeInfoDTORows.size(); i++) {
				listFeeInfoDTORows.get(i).put("bka063", BizHelper.getLoginUser());
				listFeeInfoDTORows.get(i).put("bka064", BizHelper.getUserName());
			}
			RemoteCallAPIParam param = new RemoteCallAPIParam();
			param.setFuncitionID("bizh110104");
			param.setParameter("bka893", "0");
			if ("1".equals(diagnoseInfoDTO.getReduceflag())){
				param.setParameter("bka894", "1");
			} else {
				param.setParameter("bka894", "0");
			}
			this.beanService.copyProperties(diagnoseInfoDTO, param.getParameters(), true);
			param.setList("feeinfo", listFeeInfoDTORows);
			RemoteCallAPIResult result = remoteCallAPIService.execute(param);
			List payinfoList = result.getList("payinfo");
			this.setJSONReturn("门诊试算成功！", payinfoList);
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("门诊试算失败！\r\n" + errLogSn + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
	}

	/**
	 * 收费或退费
	 * 
	 * 
	 * @return
	 */
	@SuppressWarnings({"rawtypes", "unchecked" })
	public String diagnoseCalcEnPay() {
		try {
			diagnoseInfoDTO.setAkb020(BizHelper.getAkb020());
			diagnoseInfoDTO.setAkb021(BizHelper.getAkb021());
			diagnoseInfoDTO.setAaa027(BizHelper.getAaa027());
			diagnoseInfoDTO.setAae011(BizHelper.getLoginUser());
			diagnoseInfoDTO.setBka015(BizHelper.getUserName());
			if (StringUtils.isBlank(this.getDiagnoseInfoDTO().getBka020())
					&& StringUtils.isNotBlank(this.getDiagnoseInfoDTO().getAkf001())) {
				this.getDiagnoseInfoDTO().setBka020(this.getDiagnoseInfoDTO().getAkf001());
			}
			if (StringUtils.isBlank(this.getDiagnoseInfoDTO().getBka022())
					&& StringUtils.isNotBlank(this.getDiagnoseInfoDTO().getBka021())) {
				this.getDiagnoseInfoDTO().setBka022(this.getDiagnoseInfoDTO().getBka021());
			}
			String jsonFee = URLDecoder.decode(getParameter("feeinfo"), "UTF-8");
			JSONArray jsonArray = JSONArray.fromObject(jsonFee);
			List<Map> listFeeInfoDTORows = JSONArray.toList(jsonArray,new HashMap(), new JsonConfig());
			for (int i = 0; listFeeInfoDTORows != null && i < listFeeInfoDTORows.size(); i++) {
				listFeeInfoDTORows.get(i).put("bka063", BizHelper.getLoginUser());
				listFeeInfoDTORows.get(i).put("bka064", BizHelper.getUserName());
			}
			
			RemoteCallAPIParam param = new RemoteCallAPIParam();
			param.setFuncitionID("bizh110104");
			param.setParameter("bka893", "1");
			if ("1".equals(diagnoseInfoDTO.getReduceflag())){
				param.setParameter("bka894", "1");
			} else {
				param.setParameter("bka894", "0");
			}
			this.beanService.copyProperties(diagnoseInfoDTO, param.getParameters(), true);
			param.setList("feeinfo", listFeeInfoDTORows);
			RemoteCallAPIResult result = remoteCallAPIService.execute(param);
			List payinfoList = result.getList("payinfo");
			
			DiagnoseQueryApiService diagnoseQueryService_HIS = (DiagnoseQueryApiService) hygeiaServiceManager.getBean("diagnoseQueryApiService", BizHelper.getAkb020());
			KCD1_Mz_HisDTO hisDto = new KCD1_Mz_HisDTO();
			hisDto.setAkb020(BizHelper.getAkb020());
			hisDto.setAkc190(this.getParameter("akc190"));
			//如果是退费
			if("1".equals(diagnoseInfoDTO.getReduceflag())){
				hisDto.setBka044("0");//退费
			}else{
				hisDto.setBka044("2");//已结算
			}
			diagnoseQueryService_HIS.updateOutpatientFeeUploadFlag(hisDto);
			
			
			this.setJSONReturn("门诊收费成功！", payinfoList);
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError("门诊收费失败！\r\n" + errLogSn + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
	}
	
	
	
	/**
	 * 加载科室
	 * @Title: loadBka019List   
	 * @param: @param bka019List      
	 * @return: void
	 */
	@SuppressWarnings(value = { "unchecked","rawtypes"})
	private void loadBka019List(Map bka019List){
		try {
			HospInfoDTO hospInfoDTO = new HospInfoDTO();
			hospInfoDTO.setAaa027(BizHelper.getAaa027()); //统筹区编码
			hospInfoDTO.setAkb020(BizHelper.getAkb020()); //医院编码
			hospInfoDTO.setAae016("1");					 //复核标记
			hospInfoDTO.setAae100("1");                  //有效标记
			RemoteCallAPIParam param = new RemoteCallAPIParam();
			param.setFuncitionID("bizh200001");
			param.setParameter("bka896", "queryDept"); //查询类型
			this.beanService.copyProperties(hospInfoDTO, param.getParameters(), true);
			RemoteCallAPIResult result = remoteCallAPIService.execute(param);
			List<Map> hospList = result.getList("hospInfo");
			//获取科室信息
			if(hospList != null && hospList.size() > 0){
				for (Map map : hospList) {
					bka019List.put(this.communalService.str(map, "bkc156", ""), this.communalService.str(map, "bkc157", ""));
				}
			}
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn+e.getMessage());
		}
		
	}
	
	/**
	 * 加载病区
	 * @Title: loadBka021List   
	 * @param: @return      
	 * @return: String
	 */
	@SuppressWarnings(value = { "unchecked","rawtypes"})
	public String loadBka021List(){
		try {
			Map bka021List= new HashMap();
			if(StringUtils.isNotBlank(this.getDiagnoseInfoDTO().getAkf001())){
				HospInfoDTO hospInfoDTO = new HospInfoDTO();
				hospInfoDTO.setAaa027(BizHelper.getAaa027());  //统筹区编码
				hospInfoDTO.setAka020(BizHelper.getAkb020());  //医院编码
				hospInfoDTO.setAae016("1");					 //复核标记
				hospInfoDTO.setAae100("1");                  //有效标记
				hospInfoDTO.setBkc156(this.getDiagnoseInfoDTO().getAkf001());
				
				RemoteCallAPIParam param = new RemoteCallAPIParam();
				param.setFuncitionID("bizh200001");
				param.setParameter("bka896", "queryArea"); //查询类型
				this.beanService.copyProperties(hospInfoDTO, param.getParameters(), true);
				RemoteCallAPIResult result = remoteCallAPIService.execute(param);
				List<Map> hospList = result.getList("hospInfo");
				//获取科室信息
				if(hospList != null && hospList.size() > 0){
					for (Map map : hospList) {
						bka021List.put(this.communalService.str(map, "bkc153", ""), this.communalService.str(map, "bkc154", ""));
					}
				}
			}
			this.setJSONReturn(bka021List);
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn+e.getMessage());
		}
		return NONE;
	}
	
	/**
	 * 加载医保医生
	 * @Title: loadBka503List   
	 * @param: @return      
	 * @return: String
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public String loadBka503List(){
		try {
			Map bka503List = new HashMap();
			if(StringUtils.isNotBlank(this.getDiagnoseInfoDTO().getAkf001())){
				HospInfoDTO hospInfoDTO = new HospInfoDTO();
				hospInfoDTO.setAaa027(BizHelper.getAaa027()); //统筹区编码
				hospInfoDTO.setAkb020(BizHelper.getAkb020()); //医院编码
				hospInfoDTO.setAae016("1");					 //复核标记
				hospInfoDTO.setAae100("1");                  //有效标记
				hospInfoDTO.setBkc156(this.getDiagnoseInfoDTO().getAkf001());
				
				RemoteCallAPIParam param = new RemoteCallAPIParam();
				param.setFuncitionID("bizh200001");
				param.setParameter("bka896", "queryDoctor"); //查询类型
				this.beanService.copyProperties(hospInfoDTO, param.getParameters(), true);
				RemoteCallAPIResult result = remoteCallAPIService.execute(param);
				List<Map> hospList = result.getList("hospInfo");
				//获取科室信息
				if(hospList != null && hospList.size() > 0){
					for (Map map : hospList) {
						bka503List.put(this.communalService.str(map, "bkc274", ""), this.communalService.str(map, "bkc275", ""));
					}
				}
			}
			this.setJSONReturn(bka503List);
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn+e.getMessage());
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

	public String getAke007() {
		return ake007;
	}

	public void setAke007(String ake007) {
		this.ake007 = ake007;
	}

}