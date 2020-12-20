package com.powersi.ssm.biz.medicare.diagnose.action;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.diagnose.pojo.PersonMediBaseInfoDTO;
import com.powersi.biz.medicare.diagnose.service.api.mcce.MCCEbizh110001Service;
import com.powersi.biz.medicare.iccard.service.CheckCardService;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120102Service;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.log.service.ErrLogSnService;
import com.powersi.log.service.ErrLogSnService.ProjectType;
import com.powersi.ssm.biz.medicare.api.dto.APIRemoteCallParam;
import com.powersi.ssm.biz.medicare.api.dto.APIRemoteCallResult;
import com.powersi.ssm.biz.medicare.api.service.APIRemoteCallService;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCERemoteBIZC130121ServiceImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh110001ServiceImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120102ServiceImpl;
import com.powersi.ssm.biz.medicare.common.service.CodetableCacheMapping;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.diagnose.service.GetPersonInfoService;
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

@Action(value = "GetPersonInfoAction", results = {
		@Result(name = "getPersonBusi", location = "/pages/biz/medicare/diagnose/chooseDiagnosebiz.jsp"),
		@Result(name = "getPersonBusi_remote", location = "/pages/biz/medicare/diagnose/chooseDiagnosebiz_remote.jsp"),
		@Result(name = "chooseperson", location = "/pages/biz/medicare/comm/ChoosePerson.jsp"),
        @Result(name = "chargeretire", location = "/pages/biz/medicare/diagnose/Charge_retire.jsp") })
public class GetPersonInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BeanService beanService;
	private DiagnoseInfoDTO diagnoseInfoDTO;
	@Autowired
	private CommunalService communalService;
	@Autowired
	@Qualifier("errLogSnService")
	private ErrLogSnService errLogSnService;
	private PersonMediBaseInfoDTO pMediBaseInfoDTO;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private GetPersonInfoService getPersonInfoService;

	@Autowired
	private APIRemoteCallService aPIRemoteCallService;
	
	@SuppressWarnings("rawtypes")
	public String chooseperson() {
		try {
			if (this.isPostRequest()) {
				try {
					PagerHelper.initPagination(this.getRequest());

					diagnoseInfoDTO.setAkb020(BizHelper.getAkb020());
					MCCEbizh110001Service mCCEbizh110001Service =this.hygeiaServiceManager.getBeanByClass(MCCEbizh110001ServiceImpl.class, diagnoseInfoDTO.getAkb020());
					String arg_value = diagnoseInfoDTO.getArg_value();
					String arg_name = diagnoseInfoDTO.getArg_name();
					if (!"bka100".equals(arg_name)&&!"qrcode".equals(arg_name)) {
						 this.getQueryName(arg_value,arg_name);
					}
					List lstPersonInfo = mCCEbizh110001Service.getPersonList(diagnoseInfoDTO);
					if (lstPersonInfo == null) {
						lstPersonInfo = new ArrayList();
					}
					PagerHelper.getPaginationObj().setCount(lstPersonInfo.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(lstPersonInfo));
				} catch (Exception e) {
					this.saveJSONError(e.getMessage());
				}
				return NONE;
			} else {
				return "chooseperson";
			}
		} catch (Exception e) {
			this.saveError(e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 普通门诊取人员信息
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getPersonInfo() {
		try {
			diagnoseInfoDTO.setAkb020(BizHelper.getAkb020());
			diagnoseInfoDTO.setAkb021(BizHelper.getAkb021());
			diagnoseInfoDTO.setAaa027(BizHelper.getAaa027());
			String arg_value = diagnoseInfoDTO.getArg_value();
			String arg_name = diagnoseInfoDTO.getArg_name();
			if (!"bka100".equals(arg_name)&&!"qrcode".equals(arg_name)) {
				 this.getQueryName(arg_value,arg_name);
			}
					
			MCCEbizh110001Service mCCEbizh110001Service = this.hygeiaServiceManager.getBeanByClass(MCCEbizh110001ServiceImpl.class, diagnoseInfoDTO.getAkb020());
			Map retMap = mCCEbizh110001Service.searchPersonInfo(diagnoseInfoDTO);
			
			List<Map> reMaps = (List<Map>) retMap.get("personinfo");
			Map lmapReturn = new HashMap();
			if (reMaps.size() == 1) {
				Map mapRow = new HashMap();
				this.beanService.copyProperties(reMaps.get(0), mapRow, true);
				this.loadCodeValue(mapRow);
				lmapReturn.put("personinfo", mapRow);
				lmapReturn.put("spinfo", retMap.get("spinfo"));
				lmapReturn.put("hospinfo", retMap.get("hospinfo"));
				if(retMap.get("injuryorbirthinfo")!=null)
					lmapReturn.put("injuryorbirthinfo", ((List<Map>)retMap.get("injuryorbirthinfo")).get(0));
				this.setJSONReturn(lmapReturn);
			} else if (reMaps.size() > 1) {
				List<Map> mapRows = new ArrayList<Map>();
				Map mapRow = null;
				for (Map map : reMaps) {
					mapRow = new HashMap();
					this.beanService.copyProperties(map, mapRow, true);
					mapRows.add(mapRow);
				}
				this.setJSONReturn("multi-row", mapRows);
			}
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}
	
	/**
	 * 门慢取人员信息
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getPersonSpInfo() {
		try {
			diagnoseInfoDTO.setAkb020(BizHelper.getAkb020());
			diagnoseInfoDTO.setAkb021(BizHelper.getAkb021());
			diagnoseInfoDTO.setAaa027(BizHelper.getAaa027());
			
			String arg_value = diagnoseInfoDTO.getArg_value();
			arg_value = arg_value.trim();
			String arg_name = diagnoseInfoDTO.getArg_name();
			if (!"bka100".equals(arg_name)&&!"qrcode".equals(arg_name)) {
				 this.getQueryName(arg_value,arg_name);
			}
			
			MCCEbizh110001Service mCCEbizh110001Service = this.hygeiaServiceManager.getBeanByClass(MCCEbizh110001ServiceImpl.class, diagnoseInfoDTO.getAkb020());
			Map retMap = mCCEbizh110001Service.searchPersonInfo(diagnoseInfoDTO);
			List<Map> reMaps = (List<Map>) retMap.get("personinfo");
			Map lmapReturn = new HashMap();
			if (reMaps.size() == 1) {
				Map mapRow = new HashMap();
				this.beanService.copyProperties(reMaps.get(0), mapRow, true);
				this.loadCodeValue(mapRow);
				lmapReturn.put("personinfo", mapRow);
				lmapReturn.put("spinfo", retMap.get("spinfo"));
				this.setJSONReturn(lmapReturn);
			} else if (reMaps.size() > 1) {
				List<Map> mapRows = new ArrayList<Map>();
				Map mapRow = null;
				for (Map map : reMaps) {
					mapRow = new HashMap();
					this.beanService.copyProperties(map, mapRow, true);
					mapRows.add(mapRow);
				}
				this.setJSONReturn("multi-row", mapRows);
			}
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			saveJSONError("查询人员特殊信息出错！错误信息：" + errLogSn + e.getMessage());
		}
		return NONE;
	}


	@SuppressWarnings("rawtypes")
	public String getBusiFeeInfo() {
		try {
			diagnoseInfoDTO = new DiagnoseInfoDTO();
			diagnoseInfoDTO.setAkb020(BizHelper.getAkb020());
			diagnoseInfoDTO.setAkb021(BizHelper.getAkb021());
			String kc21id = getParameter("kc21id");
			String aaz217 = getParameter("aaz217");
			if (kc21id != null) {
				this.diagnoseInfoDTO.setAaz217(aaz217);
				List<Map>  bizInfoList = this.getPersonInfoService.getBusiFeeInfo(diagnoseInfoDTO);
				Map<String, Object> hi = new HashMap<String, Object>();
				hi.put("data1", bizInfoList);
				if (null != hi && bizInfoList.size() > 0) {
					setJSONReturn(hi);
				} else {
					setJSONReturn("no");
				}
			}
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			saveJSONError("查询人员业务信息出错！错误信息：" + errLogSn + e.getMessage());
		}
		return NONE;
	}

	
	@SuppressWarnings("rawtypes")
	public String getPersonBusi() {
		try {
			diagnoseInfoDTO.setAkb020(BizHelper.getAkb020());
			diagnoseInfoDTO.setAkb021(BizHelper.getAkb021());
			diagnoseInfoDTO.setAaa027(BizHelper.getAaa027());
			this.getQueryName(diagnoseInfoDTO.getArg_value(),
					diagnoseInfoDTO.getArg_name());
			List<Map>  bizInfoList = getPersonInfoService.getPersonBusi(this.getDiagnoseInfoDTO());
			if (bizInfoList != null && bizInfoList.size()==0)
				throw new HygeiaException("未查到该参保人的业务信息！");
			if (bizInfoList != null && bizInfoList.size() > 0)
				this.getRequest().setAttribute("selectBizList", bizInfoList);
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			saveJSONError("查询人员业务信息出错！错误信息：" + errLogSn + e.getMessage());
		}
		return "getPersonBusi";
	}

	@SuppressWarnings({ "rawtypes" })
	public String getPersonBusiDetail() {
		try {
			DiagnoseInfoDTO diagnoseInfoDTO = new DiagnoseInfoDTO();
			diagnoseInfoDTO.setAkb020(getParameter("akb020"));
			diagnoseInfoDTO.setAka130(getParameter("aka130"));
			diagnoseInfoDTO.setBka006(getParameter("bka006"));
			diagnoseInfoDTO.setAaz217(getParameter("aaz217"));
			Map bf = this.getPersonInfoService.getPersonBusiDetail(diagnoseInfoDTO);
			setJSONReturn(bf);
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			saveJSONError("查询人员业务明细信息出错！错误信息：" + errLogSn + e.getMessage());
		}
		return NONE;
	}

	private void getQueryName(String arg_value,String arg_name) {
		if (StringUtils.isBlank(arg_value)) {
			throw new HygeiaException("您输入的查询条件为空，请确定！");
		}
		if (arg_value.length() > 20) {
			throw new HygeiaException("您输入的查询条件位数过多");
		}
		if ("aac001".equals(arg_name)) {
			if (StringUtils.isNotBlank(arg_value) && StringUtils.isNumeric(arg_value)) {
				diagnoseInfoDTO.setAac001(Long.parseLong(arg_value));
			}else {
				throw new HygeiaException("电脑号格式有误");
			}		
		} else if ("aac002".equals(arg_name)) {
			diagnoseInfoDTO.setAac002(arg_value);
		}else if ("aaz217".equals(arg_name)) {
			diagnoseInfoDTO.setAaz217(arg_value);
		}else {
			diagnoseInfoDTO.setAac002(arg_value);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked"})
	private void loadCodeValue(Map mapRow) {
		String bka888 = UtilFunc.getString(mapRow, "bka888");
		mapRow.put("bka888", CodetableCacheMapping.getDisplayValue("bka888", bka888, bka888));
	}
	
	/**
	 * 持卡就诊
	 * @param inHospitalDTO
	 */
	@SuppressWarnings("unused")
	private void checkReadCard(InHospitalDTO inHospitalDTO) {
		CheckCardService checkCardService = this.hygeiaServiceManager.getBeanByClass(CheckCardService.class,
				BizHelper.getAkb020());
		checkCardService.checkReadCard(inHospitalDTO);
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
    
	public PersonMediBaseInfoDTO getpMediBaseInfoDTO() {
		return pMediBaseInfoDTO;
	}

	public void setpMediBaseInfoDTO(PersonMediBaseInfoDTO pMediBaseInfoDTO) {
		this.pMediBaseInfoDTO = pMediBaseInfoDTO;
	}

	public DiagnoseInfoDTO getDiagnoseInfoDTO() {
		return diagnoseInfoDTO;
	}

	public void setDiagnoseInfoDTO(DiagnoseInfoDTO diagnoseInfoDTO) {
		this.diagnoseInfoDTO = diagnoseInfoDTO;
	}
	
	/**
	 * TS19112600047      【需求开发】结算云（医院端）离休干部、荣残军人在门诊配送点消费时，系统自动弹出本次刷卡前一个月（刷卡日期往前一个月（30天））的消费记录，包含刷卡时间、金额、医疗机构，基金支付金额，个人自付金额
	 * 新增方法获取30天消费记录
	 * 赵银溪 20191201
	 * 查询消费记录
	 * 
	 * @return
	 */
	public String queryChargeretire() {
		if (this.isPostRequest()) {
				try {
					PagerHelper.initPagination(this.getRequest());
					diagnoseInfoDTO.setAkb020(BizHelper.getAkb020());
					diagnoseInfoDTO.setAkb021(BizHelper.getAkb021());
					diagnoseInfoDTO.setAaa027(BizHelper.getAaa027());
					MCCEbizh120102Service mCCEbizh120102Service = getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, diagnoseInfoDTO.getAkb020());
		            String aac001 =		String.valueOf(diagnoseInfoDTO.getAac001())	;
				    if(StringUtils.isEmpty(aac001)|| aac001.equals("0")){
					throw new HygeiaException("请先查询人员信息");
				    }
			        List ChargeList = mCCEbizh120102Service.selectChargeList(diagnoseInfoDTO);
		            int a =	 ChargeList.size();
		            for(int i = 0;i < ChargeList.size(); i ++){
		                	ChargeList.get(i); 
		            }
					PagerHelper.getPaginationObj().setCount(a);
					DataGridHelper.render(this.getRequest(), this.getResponse(),
					PagerHelper.getPaginatedList(ChargeList));		
				}catch (Exception e) {
					this.saveJSONError(e.getMessage());
				}
				return NONE;
				
				}else {
					return "chargeretire";
				}		
	}
	
	public HygeiaServiceManager getHygeiaServiceManager() {
		return hygeiaServiceManager;
	}

	/**
	* 修改概要：NTS20050700380-生育、门特业务省内异地联网结算需求 -- 湘潭
	* 修改描述：增加异地门特查人
	* 修改人及修改时间：李嘉伦 2020/5/8
	*/
	public String getPersonInfo_special_remote(){
		try{
			diagnoseInfoDTO.setHospital_id(BizHelper.getAkb020());
			diagnoseInfoDTO.setAkb020(BizHelper.getAkb020());
			diagnoseInfoDTO.setCenter_id("430201");	//先固定写死430201 后面调整

			String biz_type = diagnoseInfoDTO.getBiz_type();

			MCCERemoteBIZC130121ServiceImpl mcceRemoteBIZC130121Service = this.hygeiaServiceManager.getBeanByClass(MCCERemoteBIZC130121ServiceImpl.class, diagnoseInfoDTO.getAkb020());
			Map retMap = mcceRemoteBIZC130121Service.getPersonInfo_BIZC130121(diagnoseInfoDTO);

			List<Map> reMaps = (List<Map>) retMap.get("personinfo");
			Map lmapReturn = new HashMap();
			if (reMaps.size() == 1) {
				Map mapRow = new HashMap();
				this.beanService.copyProperties(reMaps.get(0), mapRow, true);
				//判断职工基本统筹001基金是否冻结
				List<Map> funds = (List<Map>) retMap.get("freezeinfo");
				for(Map<String,String> fund : funds) {
					String fund_id = UtilFunc.getString(fund,"fund_id");
					if ("001".equals(fund_id)&&"13".equals(biz_type)){
						String indi_freeze_status = UtilFunc.getString(fund,"indi_freeze_status");
						if ("0".equals(indi_freeze_status)){
							mapRow.put("bka888", "正常");
						}else{
							mapRow.put("bka888", "冻结");
						}
						break;
					}
					//门诊生育判断生育基金
					if ("511".equals(fund_id)&&"51".equals(biz_type)){
						String indi_freeze_status = UtilFunc.getString(fund,"indi_freeze_status");
						if ("0".equals(indi_freeze_status)){
							mapRow.put("bka888", "正常");
						}else{
							mapRow.put("bka888", "冻结");
						}
						break;
					}
				}
				lmapReturn.put("personinfo", mapRow);
				lmapReturn.put("spinfo", retMap.get("spinfo"));
				if(retMap.get("injuryorbirthinfo")!=null)
					lmapReturn.put("injuryorbirthinfo", retMap.get("injuryorbirthinfo"));
				this.setJSONReturn(lmapReturn);
			} else if (reMaps.size() > 1) {
				List<Map> mapRows = new ArrayList<Map>();
				Map mapRow = null;
				for (Map map : reMaps) {
					mapRow = new HashMap();
					this.beanService.copyProperties(map, mapRow, true);
					mapRows.add(mapRow);
				}
				this.setJSONReturn("multi-row", mapRows);
			}
		}catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveJSONError(errLogSn + e.getMessage());
		}
		return NONE;
	}

	public String getPersonBusi_remote() {
		try {
			diagnoseInfoDTO.setHospital_id(BizHelper.getAkb020());
			diagnoseInfoDTO.setReg_staff(BizHelper.getLoginUser());
			diagnoseInfoDTO.setReg_man(BizHelper.getUserName());
			diagnoseInfoDTO.setFromdate(DateFunc.dateToString(DateFunc.addMonths(new Date(),-1),"yyyy-MM-dd"));
			diagnoseInfoDTO.setTodate(DateFunc.dateToString(new Date(),"yyyy-MM-dd"));
			if(UtilFunc.isBlank(diagnoseInfoDTO.getCenter_id())){
				diagnoseInfoDTO.setCenter_id(BizHelper.getAaa027());
			}
			List<Map>  bizInfoList = getPersonInfoService.getPersonBusi_remote(this.getDiagnoseInfoDTO());
			if (bizInfoList != null && bizInfoList.size()==0)
				throw new HygeiaException("未查到该参保人的业务信息！");
			if (bizInfoList != null && bizInfoList.size() > 0)
				this.getRequest().setAttribute("selectBizList", bizInfoList);
		} catch (Exception e) {
			String errLogSn = this.addErrSNInfo();
			this.communalService.error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			saveJSONError("查询人员业务信息出错！错误信息：" + errLogSn + e.getMessage());
		}
		return "getPersonBusi_remote";
	}
	//异地门特生育业务查询费用信息
    @SuppressWarnings({ "rawtypes" })
    public String getPersonBusiDetail_remote() {
        try {
            DiagnoseInfoDTO diagnoseInfoDTO = new DiagnoseInfoDTO();
            diagnoseInfoDTO.setHospital_id(BizHelper.getAkb020());
            diagnoseInfoDTO.setSerial_no(getParameter("serial_no"));
            diagnoseInfoDTO.setFee_batch(getParameter("fee_batch"));
            Map bf = this.getPersonInfoService.getPersonBusiDetail_remote(diagnoseInfoDTO);
            setJSONReturn(bf);
        } catch (Exception e) {
            String errLogSn = this.addErrSNInfo();
            this.communalService.error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
                    .append(this.addNotBlankParameters()).append(":处理结果:").toString());
            saveJSONError("查询人员业务明细信息出错！错误信息：" + errLogSn + e.getMessage());
        }
        return NONE;
    }
}
