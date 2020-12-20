
package com.powersi.ssm.biz.medicare.health.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.pojo.ListResultDTO;
import com.powersi.biz.medicare.health.pojo.SetMealDTO;
import com.powersi.biz.medicare.query.service.api.BizQueryApiService;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.comm.utils.UtilFunc;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.powerreport.service.PowerReportDao;
import com.powersi.powerreport.service.PowerReportImpl;
import com.powersi.ssm.biz.medicare.api.dto.APIRemoteCallParam;
import com.powersi.ssm.biz.medicare.api.dto.APIRemoteCallResult;
import com.powersi.ssm.biz.medicare.api.service.APIRemoteCallService;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.common.util.ReportData;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Action(value = "ExaminationAction", results = {
		@Result(name = "choosePersonAac001", location = "/pages/biz/medicare/health/ChoosePersonAac001.jsp"),
		@Result(name = "identityRegister", location = "/pages/biz/medicare/health/identityRegister.jsp"),
		@Result(name = "jielun", location = "/pages/biz/medicare/health/jielun.jsp"),
		@Result(name = "cusionInfo", location = "/pages/biz/medicare/health/cusionInfo.jsp"),
		@Result(name = "settlementReportClinic", location = "/pages/biz/medicare/health/SettlementReportClinic.jsp") })
public class ExaminationAction extends BaseExaminationAction {

	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private APIRemoteCallService aPIRemoteCallService;
	private String reportIDfirst;

	public String getReportIDfirst() {
		return reportIDfirst;
	}

	public void setReportIDfirst(String reportIDfirst) {
		this.reportIDfirst = reportIDfirst;
	}

	/**
	 * 获取个人基本信息和业务信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findAac001() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO();
					//TS19052200174 - 【问题修复】健康体检业务子系统涉及到查询数据的模块，均有10S的控制，控制不合理 daliang.long 20190523
					APIRemoteCallParam param = new APIRemoteCallParam();
					param.setFunction_id("bizh131001");
					param.setValue("bkh999", "001");
					this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
					APIRemoteCallResult apiResult =  aPIRemoteCallService.execute(param);
					List<Map> result = apiResult.getList("personInfo");
					if (result.size() == 1) {
						this.getBeanService().copyProperties(result.get(0), this.getSetMealDTO(), true);
						this.loadHidDataAndItemName();
						Map mapRow = new HashMap();
						this.getBeanService().copyProperties(this.getSetMealDTO(), mapRow, true);
						this.setJSONReturn(mapRow);
					} else if (result.size() > 1) {
						this.setJSONReturn(this.getSetMealDTO().getAac002(), result);
					}

				} catch (Throwable e) {
					this.errorInfo(e);
				}
				return NONE;

			} else {
				loadCodeSelectData();
				return "identityRegister";
			}
		} catch (Throwable e) {
			this.errorInfo(e);
			return ERROR;
		}
	}

	/**
	 * 选择参保人信息
	 * 
	 * @return
	 */
	public String choosePersonAac001() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO();
					//TS19052200174 - 【问题修复】健康体检业务子系统涉及到查询数据的模块，均有10S的控制，控制不合理 daliang.long 20190523
					APIRemoteCallParam  param = new APIRemoteCallParam ();
					param.setFunction_id("bizh131001");
					param.setValue("bkh999", "findAac001");
					this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
					APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);
					List<Map> result = apiResult.getList("personinfo");
					this.getBeanService().copyProperties(result.get(0), this.getSetMealDTO(), true);
					Map mapRow = new HashMap();
					this.getBeanService().copyProperties(this.getSetMealDTO(), mapRow, true);
					this.setJSONReturn(mapRow);

				} catch (Throwable e) {
					this.errorInfo(e);
				}
				return NONE;
			} else {
				this.getSetMealDTO();
				return "choosePersonAac001";
			}
		} catch (Throwable e) {
			this.errorInfo(e);
			return ERROR;
		}
	}

	/**
	 * 保存体检登记
	 * 
	 * @return
	 */
	public String identityRegister() {
		try {
			if (this.isPostRequest()) {
				try {

					this.initCtrlInHospitalDTO();
					//TS19052200174 - 【问题修复】健康体检业务子系统涉及到查询数据的模块，均有10S的控制，控制不合理 daliang.long 20190523
					APIRemoteCallParam  param = new APIRemoteCallParam ();
					param.setFunction_id("bizh131001");
					param.setValue("bkh999", "003");
					this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
					//任务概要：  TS19052200158 - 【问题修复】【体检费用结算】-界面功能逻辑与需求不相符合
					//修改说明：bkh014从结算云统一传
					//修改人及时间：daliang.long  20190529
					//TS19072200173  体检登记的时候报 获取最大的序列号BKH014出错。 序列号改用UUID  陈洁  20190722
					//UUID bkh014 = UUID.randomUUID();  序列号
					//param.setValue("bkh014", bkh014.toString());
					//TS19081200213    体检项目附加   扩大bkh014序列号的值 陈洁 20190812
					param.setValue("bkh014", SysFunc.getMaxNo("bkh014",50000000)+"");
					APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);
					String result = (String) apiResult.get("saveInfo");
					this.setJSONReturn(result);
				} catch (Throwable e) {
					this.errorInfo(e);
				}
				return NONE;
			}
		} catch (Throwable e) {
			this.errorInfo(e);
			return ERROR;
		}
		return NONE;
	}

	/**
	 * 体检套餐查询
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String querySetMeal() {
		try {
			PagerHelper.setPageParam(getRequest(), this.getSetMealDTO());
			//TS19052200174 - 【问题修复】健康体检业务子系统涉及到查询数据的模块，均有10S的控制，控制不合理 daliang.long 20190523
			APIRemoteCallParam  param = new APIRemoteCallParam ();
			param.setFunction_id("bizh131001");
			param.setValue("bkh999", "004");
			
			if("getSetMeal".equals(this.getSetMealDTO().getFlag())) {
				param.setValue("aac004",  this.getSetMealDTO().getAac004());
				param.setValue("bkh060",  this.getSetMealDTO().getAac006());
			}
			this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
			APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);
			List<SetMealDTO> list = new ArrayList<SetMealDTO>();
			List<Map> result = apiResult.getList("mealRows");
			for (int i = 0; i < result.size(); i++) {
				Map map = result.get(i);
				SetMealDTO set = new SetMealDTO();
				this.getBeanService().copyProperties(map, set, true);
				//TS19052200147 - 【问题修复】【体检套餐查询】-界面优化内容汇总  20190527 daliang.long
				set.setBkh012("一般人员");
				if("1".equals(set.getBkh060())) {
					set.setBkh060("60岁以上");
				}else if("2".equals(set.getBkh060())) {
					set.setBkh060("60岁以下");
				}else {
					set.setBkh060("无");
				}
				list.add(set);
			}
			//TS19052400058 - 【问题修复】【体检身份登记】-“默认套餐”的值未根据获取人员的男女或其他熟悉自动带出默认值  daliang.long 20190528
			if ("getSetMeal".equals(this.getSetMealDTO().getFlag())) {
				Map bkh15List = new LinkedHashMap();
				if (result.size() > 0) {
					for (Map map : result) {
						bkh15List.put(this.getCommunalService().str(map, "bkh015", ""),
								this.getCommunalService().str(map, "bkh059", ""));
					}
				}
				setJSONReturn(bkh15List);
			} else {
				PagerHelper.setPageParam(getRequest(), this.getSetMealDTO());
				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(list, new int[] { list.size() }));
			}

		} catch (Throwable e) {
			this.errorInfo(e);
			//修改概要：TS19052200153 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
			//修改描述：ERROR是系统级错误，此处不定义
			//修改人及修改时间：daliang.long  20190531
		}
		return NONE;
	}

	/**
	 * 获取体检登记人员信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryHealthRegisterInfo() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO();
					//TS19052200174 - 【问题修复】健康体检业务子系统涉及到查询数据的模块，均有10S的控制，控制不合理 daliang.long 20190523
					APIRemoteCallParam  param = new APIRemoteCallParam ();
					param.setFunction_id("bizh131001");
					param.setValue("bkh999", "005");
					this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
					APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);
					List<Map> result = apiResult.getList("personInfo");
					Map map = result.get(0);
					map.put("akb021", CodetableMapping.getDisplayValue("akb020", map.get("akb020").toString(),
							map.get("akb020").toString()));
					map.put("aac004_name", CodetableMapping.getDisplayValue("aac004", map.get("aac004").toString(),
							map.get("aac004").toString()));
					this.setJSONReturn(map);
				} catch (Throwable e) {
					this.errorInfo(e);
				}
			}
		} catch (Throwable e) {
			this.errorInfo(e);
			return ERROR;
		}
		return NONE;

	}

	/**
	 * 判断体检结算录入项目是否属于套餐内项目
	 * 
	 * @return
	 */
	public String checkItemIsPackage() {
		try {
			try {
				if (this.isPostRequest()) {
					//TS19052200174 - 【问题修复】健康体检业务子系统涉及到查询数据的模块，均有10S的控制，控制不合理 daliang.long 20190523
					APIRemoteCallParam  param = new APIRemoteCallParam ();
					this.initCtrlInHospitalDTO();
					param.setFunction_id("bizh131001");
					param.setValue("bkh999", "006");
					this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
					APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);
					String num = apiResult.get("num").toString();
					this.setJSONReturn(num);
				}

			} catch (Throwable e) {
				this.errorInfo(e);
			}
		} catch (Throwable e) {
			this.errorInfo(e);
		}

		return NONE;
	}
	
	/**
	 * 保存费用
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String saveFee() {
		//TS19052200158 - 【问题修复】【体检费用结算】-界面功能逻辑与需求不相符合   20190527  daliang.long
		if (this.isPostRequest()) {
			try {
				String jsonFee = URLDecoder.decode(this.getParameter("feeinfo"), "UTF-8");
				JSONArray Arrays = null;
				List<Map> listFeeInfoDTORows = null;
				Arrays = JSONArray.fromObject(jsonFee);
				listFeeInfoDTORows = JSONArray.toList(Arrays, new HashMap(), new JsonConfig());

				for (int i = 0; listFeeInfoDTORows != null && i < listFeeInfoDTORows.size(); i++) {
					listFeeInfoDTORows.get(i).put("aac001", this.getSetMealDTO().getAac001());
					listFeeInfoDTORows.get(i).put("bkh014", this.getSetMealDTO().getBkh014());
					listFeeInfoDTORows.get(i).put("aaz217", this.getSetMealDTO().getAaz217());
					// bkh022 费用序列号 从hygeia_web中sys_serial表中获取 没有需配置FEE_ID
					listFeeInfoDTORows.get(i).put("bkh022", SysFunc.getMaxNo("FEE_ID", 1));
					listFeeInfoDTORows.get(i).put("bka055", DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
					listFeeInfoDTORows.get(i).put("bkh018", DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
					listFeeInfoDTORows.get(i).put("bka001", "1");
					listFeeInfoDTORows.get(i).put("aae016", "1");
					listFeeInfoDTORows.get(i).put("bka009", "1");
					listFeeInfoDTORows.get(i).put("bkh317", "2");
					listFeeInfoDTORows.get(i).put("akb020", this.getSetMealDTO().getAkb020());
					listFeeInfoDTORows.get(i).put("bkh025", "5");
					listFeeInfoDTORows.get(i).put("bka053", BizHelper.getLoginUser());
					listFeeInfoDTORows.get(i).put("bka054", BizHelper.getUserName());

				}
				APIRemoteCallParam  param = new APIRemoteCallParam ();
				param.setFunction_id("bizh131001");
				param.setValue("bkh999", "007");
				param.setList("feeInfo", listFeeInfoDTORows);
				
				this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
				APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);
				String message = (String) apiResult.get("retMessage");
				setJSONReturn(message, "1");
			}catch (Throwable e) {
				this.errorInfo(e);
			}
		}
		return NONE;
	}
	
	/**
	 * 试算费用
	 * @return
	 */
	public String tryCalcTreat() {
		//TS19052200158 - 【问题修复】【体检费用结算】-界面功能逻辑与需求不相符合   20190527  daliang.long
		if (this.isPostRequest()) {
			try {
				APIRemoteCallParam  param = new APIRemoteCallParam ();
				param.setFunction_id("bizh131001");
				param.setValue("bkh999", "008");
				
				this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
				APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);
				List<Map> result = apiResult.getList("calcInfo");
				SetMealDTO dto = new SetMealDTO();
				this.getBeanService().copyProperties(result.get(0), dto, true);
				this.setJSONReturn(dto);
				
			}catch(Throwable e) {
				this.errorInfo(e);
			}
		}
		return NONE;
	}
	
	/**
	 * 结算费用
	 * @return
	 */
	public String settleMentFee() {
		//TS19052200158 - 【问题修复】【体检费用结算】-界面功能逻辑与需求不相符合   20190527  daliang.long
		if (this.isPostRequest()) {
			try {
				APIRemoteCallParam  param = new APIRemoteCallParam ();
				param.setFunction_id("bizh131001");
				param.setValue("bkh999", "009");
				this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
				APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);
				List<Map> result = apiResult.getList("calcInfo");
				SetMealDTO dto = new SetMealDTO();
				this.getBeanService().copyProperties(result.get(0), dto, true);
				this.setJSONReturn(dto);
				
			}catch(Throwable e) {
				this.errorInfo(e);
			}
		}
		return NONE;
	}

	/**
	 * 根据aaz217查询kch4费用信息
	 * @return
	 */
	public String getFeeInfo() {
		try {
			if (this.isPostRequest()) {
				PagerHelper.initPagination(this.getRequest());
				APIRemoteCallParam  param = new APIRemoteCallParam ();
				param.setFunction_id("bizh131001");
				param.setValue("bkh999", "010");
				initCtrlInHospitalDTO();
				this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
				APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);
				List<Map> listResult = apiResult.getList("bizFeeInfo");
				for(Map row : listResult) {
					String bkh046 = UtilFunc.getString(row,"bkh046");
					String bkh026 = UtilFunc.getString(row,"bkh026");
					if(StringUtils.isNotBlank(bkh026) && "1".equals(bkh026)) {
						row.put("bkh026_name", "套餐外");
					}else {
						row.put("bkh026_name", "套餐内");
					}
					row.put("bkh046_name", CodetableMapping.getDisplayValue("bkh046",bkh046, bkh046));
				}
				PagerHelper.getPaginationObj().setCount(listResult.size());
				DataGridHelper.render(this.getRequest(), this.getResponse(), PagerHelper.getPaginatedList(listResult));
			}
		}catch (Throwable e) {
			this.errorInfo(e);
		}
		return NONE;
	}
	


	/**
	 * 选择结论项目
	 * 
	 * @return
	 */
	public String chooseJielun() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO();
					PagerHelper.initPagination(this.getRequest());
					//TS19052200174 - 【问题修复】健康体检业务子系统涉及到查询数据的模块，均有10S的控制，控制不合理 daliang.long 20190523
					APIRemoteCallParam  param = new APIRemoteCallParam ();
					param.setFunction_id("bizh131001");
					param.setValue("bkh999", "011");
					this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
					APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);
					List<Map> result = apiResult.getList("matchInfo");
					List<SetMealDTO> list = new ArrayList<SetMealDTO>();
					for (int i = 0; i < result.size(); i++) {
						Map map = result.get(i);
						SetMealDTO dto = new SetMealDTO();
						this.getBeanService().copyProperties(map, dto, true);
						list.add(dto);
					}
					PagerHelper.getPaginationObj().setCount(list.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(), PagerHelper.getPaginatedList(list));
				} catch (Throwable e) {
					//任务概要：TS19052900187 - 【问题修复】【体检费用结算】-在体检明细信息界面双击明细信息增加结论信息，弹出窗口需要优化
					//修改描述：this.errorInfo(e)方法后面不需要增加ERROR
					//修改人及修改时间：daliang.long 20190530
					this.errorInfo(e);
				}
			} else {
				this.getSetMealDTO();
				return "jielun";
			}
			return NONE;

		} catch (Throwable e) {
			this.errorInfo(e);
			return ERROR;
		}
	}

	/**
	 * 体检结算信息查询
	 * 
	 * @return
	 */
	public String findHealthInfo() {
		try {
			this.initCtrlInHospitalDTO();
			PagerHelper.initPagination(getRequest());
			//TS19052200174 - 【问题修复】健康体检业务子系统涉及到查询数据的模块，均有10S的控制，控制不合理 daliang.long 20190523
			APIRemoteCallParam param = new APIRemoteCallParam();
			param.setFunction_id("bizh131001");
			param.setValue("bkh999", "012");
			String akb021 = BizHelper.getAkb021();
			this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
			APIRemoteCallResult apiResult =  aPIRemoteCallService.execute(param);
			List<Map> result = apiResult.getList("settlementInfo");
			List<SetMealDTO> list = new ArrayList<SetMealDTO>();
			for (int i = 0; i < result.size(); i++) {
				Map map = result.get(i);
				SetMealDTO dto = new SetMealDTO();
				dto.setAkb021(akb021);
				this.getBeanService().copyProperties(map, dto, true);
				dto.setBkh012(CodetableMapping.getDisplayValue("bkh012", dto.getBkh012(), dto.getBkh012()));
				dto.setBac001(CodetableMapping.getDisplayValue("bac001", dto.getBac001(), dto.getBac001()));
				list.add(dto);
			}
			PagerHelper.getPaginationObj().setCount(list.size());
			DataGridHelper.render(this.getRequest(), this.getResponse(), PagerHelper.getPaginatedList(list));

		} catch (Throwable e) {
			this.errorInfo(e);
		}
		return NONE;
	}

	/**
	 * 获取套餐内外信息
	 * 
	 * @return
	 */
	public String queryCusionInfo() {
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO();
					PagerHelper.initPagination(getRequest());
					//TS19052200174 - 【问题修复】健康体检业务子系统涉及到查询数据的模块，均有10S的控制，控制不合理 daliang.long 20190523
					APIRemoteCallParam  param = new APIRemoteCallParam ();
					param.setFunction_id("bizh131001");
					param.setValue("bkh999", "013");
					this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
					APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);
					List<Map> result = apiResult.getList("cusionInfo");
					List<SetMealDTO> list = new ArrayList<SetMealDTO>();
					for (int i = 0; i < result.size(); i++) {
						Map map = result.get(i);
						SetMealDTO dto = new SetMealDTO();
						this.getBeanService().copyProperties(map, dto, true);
						dto.setBkh046(CodetableMapping.getDisplayValue("bkh046", dto.getBkh046(), dto.getBkh046()));
						list.add(dto);
					}
					PagerHelper.getPaginationObj().setCount(list.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(), PagerHelper.getPaginatedList(list));

				} catch (Throwable e) {
					this.errorInfo(e);
				}
			} else {
				this.getSetMealDTO();
				return "cusionInfo";
			}

		} catch (Exception e) {
			this.errorInfo(e);
			return ERROR;
		}
		return NONE;

	}

	/**
	 * 双击行查询费用
	 * 
	 * @return
	 */
	public String queryBizFeeInfo() {
		try {
			this.initCtrlInHospitalDTO();
			PagerHelper.initPagination(getRequest());
			//TS19052200174 - 【问题修复】健康体检业务子系统涉及到查询数据的模块，均有10S的控制，控制不合理 daliang.long 20190523
			APIRemoteCallParam  param = new APIRemoteCallParam ();
			param.setFunction_id("bizh131001");
			param.setValue("bkh999", "014");
			this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
			APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);

			List<Map> cliListM = apiResult.getList("feeInfo");
			for (int i = 0; i < cliListM.size(); i++) {
				if (cliListM.get(i).containsKey("bkh046")) {
					cliListM.get(i).put("bkh046", CodetableMapping.getDisplayValue("bkh046",
							cliListM.get(i).get("bkh046").toString(), cliListM.get(i).get("bkh046").toString()));
				}

			}
			setJSONReturn(cliListM);
		} catch (Exception ex) {
			this.errorInfo(ex);
		}
		return NONE;
	}

	/**
	 * 打印结算单
	 * 
	 * @return
	 */
	public String settlementReport() {
		// TS19051000161 - 【需求开发】医院端健康体检模块优化和梳理
		try {
			if (this.isPostRequest()) {
				try {
					this.initCtrlInHospitalDTO();
				} catch (Exception e) {
					this.getCommunalService().error(this.logger, e, null);
					this.saveJSONError(e.getMessage());
				}
				return NONE;
			} else {
				this.initCtrlInHospitalDTO();
				return this.createSettlementReport();
			}
		} catch (Exception e) {
			this.errorInfo(e);
			return ERROR;
		}

	}

	/**
	 * 体检结算单
	 * 
	 * @return
	 */
	private String createSettlementReport() {
		// TS19051000161 - 【需求开发】医院端健康体检模块优化和梳理
		if (StringUtils.isBlank(this.getSetMealDTO().getAaz217())) {
			throw new HygeiaException("就医登记号不能为空!");
		}
		PagerHelper.initPagination(getRequest());
		//TS19052200174 - 【问题修复】健康体检业务子系统涉及到查询数据的模块，均有10S的控制，控制不合理 daliang.long 20190523
		APIRemoteCallParam  param = new APIRemoteCallParam ();
		param.setFunction_id("bizh131001");
		param.setValue("bkh999", "015");

		this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
		// 1、从后台获取结果信息
		APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);
		List<Map> data1 = apiResult.getList("data1");
		List<Map> data2 = apiResult.getList("data2");
		List<Map> data3 = apiResult.getList("data3");
		// 2、获取结算单的模板位置以及返回的jsp界面
		String arr = "health/health.xls";
		// 3、加载模板数据
		this.reportIDfirst = loadJsdModule(data1, data2, data3, arr);

		// 返回报表ID到jsp前台界面

		return "settlementReportClinic";
	}

	/*
	 * 体检取消登记
	 * 
	 * @return
	 */
	public String cancelRegister() {
		// TS19051000161 - 【需求开发】医院端健康体检模块优化和梳理
		try {
			if (this.isPostRequest()) {
				try {
					PagerHelper.initPagination(getRequest());
					this.initCtrlInHospitalDTO();
					APIRemoteCallParam  param = new APIRemoteCallParam ();
					param.setFunction_id("bizh131001");
					param.setValue("bkh999", "016");
					param.setValue("akb020", BizHelper.getAkb020());
					this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
					APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);
					String message = (String) apiResult.get("retMessage");
					setJSONReturn(message, "1");

				} catch (Throwable e) {
					this.errorInfo(e);
				}
			}

		} catch (Exception e) {
			this.errorInfo(e);
			return ERROR;
		}
		return NONE;

	}

	/*
	 * 体检取消结算
	 * 
	 * @return
	 */
	public String cancelSettlement() {
		// TS19051000161 - 【需求开发】医院端健康体检模块优化和梳理
		try {
			if (this.isPostRequest()) {
				try {
					PagerHelper.initPagination(getRequest());
					this.initCtrlInHospitalDTO();
					APIRemoteCallParam  param = new APIRemoteCallParam ();
					param.setFunction_id("bizh131001");
					param.setValue("bkh999", "017");
					param.setValue("akb020", BizHelper.getAkb020());
					this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
					APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);
					String message = (String) apiResult.get("retMessage");
					setJSONReturn(message, "1");

				} catch (Throwable e) {
					this.errorInfo(e);
				}
			}

		} catch (Exception e) {
			this.errorInfo(e);
			return ERROR;
		}
		return NONE;

	}

	// TS19051000161 - 【需求开发】医院端健康体检模块优化和梳理
	private String loadJsdModule(List<Map> data1, List<Map> data2, List<Map> data3, String reportXLS) {
		ReportData reportData = new ReportData();

		reportData.put("data1", data1);
		reportData.put("data2", data2);
		reportData.put("data3", data3);
		String bizID = "TJ" + DateFunc.dateToString(new Date(), "yyyyMMddhh");// 业务ID自由定义
		PowerReportDao prd = new PowerReportDao();
		PowerReportImpl pri = new PowerReportImpl();
		Map<String, Object> checkMap = prd.getReportBaseInfoBizID(bizID);
		if (checkMap != null) {
			pri.delReport(null, bizID);
			DBHelper.commit();
		}
		String reportID = pri.createReport(reportXLS, bizID, 1, reportData, "[" + bizID + "]",
				BizHelper.getLoginUser()); // 返回报表ID
		return reportID;
	}

	/**
	 * 异常抛出
	 * 
	 * @param e
	 */
	private void errorInfo(Throwable e) {
		String errLogSn = this.addErrSNInfo();
		this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
				.append(this.addNotBlankParameters()).append(":处理结果:").toString());
		this.saveJSONError(errLogSn + e.getMessage());
	}

	/**
	 * 加载控件值
	 */
	private void loadCodeSelectData() {
		this.initCtrlInHospitalDTO();
		APIRemoteCallParam  param = new APIRemoteCallParam ();
		param.setFunction_id("bizh131001");
		param.setValue("bkh999", "002");
		this.getBeanService().copyProperties(this.getSetMealDTO(), param, true);
		APIRemoteCallResult apiResult = aPIRemoteCallService.execute(param);
		List<Map> result = apiResult.getList("meals");
		Map bkh15List = new LinkedHashMap();
		if (result.size() > 0) {
			for (Map map : result) {
				bkh15List.put(this.getCommunalService().str(map, "bkh015", ""),
						this.getCommunalService().str(map, "bkh059", ""));
			}
		}
		this.setAttribute("bkh015List", bkh15List);

	}

}
