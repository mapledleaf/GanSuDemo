package com.powersi.ssm.biz.medicare.common.action;

import java.text.DecimalFormat;
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

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.query.pojo.BizQueryDTO;
import com.powersi.biz.medicare.settlement.service.SettlementClinicNewCenter;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.powerreport.service.PowerReport;
import com.powersi.powerreport.service.PowerReportDao;
import com.powersi.powerreport.service.PowerReportImpl;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120102ServiceImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.common.util.ReportData;
import com.powersi.ssm.biz.medicare.inhospital.action.BaseInhospitalManagerAction;

@Action(value = "SettlementReportNewCenterAction", results = {
		@Result(name = "settlementReportClinic", location = "/pages/biz/medicare/comm/SettlementReportClinic.jsp") })
public class SettlementReportNewCenterAction extends BaseInhospitalManagerAction {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final PowerReportDao powerReportDao = new PowerReportDao();
	private String reportIDfirst;
	private String reportIDsecond;
	private BizQueryDTO bizQueryDTO;

	public BizQueryDTO getBizQueryDTO() {
		return bizQueryDTO;
	}

	public void setBizQueryDTO(BizQueryDTO bizQueryDTO) {
		this.bizQueryDTO = bizQueryDTO;
	}

	public String getReportIDfirst() {
		return reportIDfirst;
	}

	public void setReportIDfirst(String reportIDfirst) {
		this.reportIDfirst = reportIDfirst;
	}

	public String getReportIDsecond() {
		return reportIDsecond;
	}

	public void setReportIDsecond(String reportIDsecond) {
		this.reportIDsecond = reportIDsecond;
	}

	/**
	 * 医保结算单
	 * 
	 * @return
	 */
	public String settlementReport() {
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
			String errLogSn = this.addErrSNInfo();
			this.getCommunalService().error(this.logger, e, new StringBuilder(errLogSn).append("入参:")
					.append(this.addNotBlankParameters()).append(":处理结果:").toString());
			this.saveError(errLogSn + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String createSettlementReport() {
		if (StringUtils.isBlank(this.getInHospitalDTO().getAaz217())) {
			throw new HygeiaException("就医登记号不能为空!");
		}
		this.getInHospitalDTO().setAae100("1");
		this.getInHospitalDTO().setBka977("1");
		this.getInHospitalDTO().setBka891("1");
		this.getInHospitalDTO().setAaa027(BizHelper.getAaa027());
		SettlementClinicNewCenter settlementClinic = this.getHygeiaServiceManager().getBeanByClass(SettlementClinicNewCenter.class,this.getInHospitalDTO().getAkb020());
		InHospitalDTO inHospitalDTO = new InHospitalDTO();
		Map mData = new HashMap<>();
		if("1".equals(this.getInHospitalDTO().getCenter_flag())){//核心业务平台
			inHospitalDTO = settlementClinic.queryInHospitalaaz217center(this.getInHospitalDTO());
			mData=settlementClinic.getCenterData(inHospitalDTO);
		}else{//结算云平台
			List<InHospitalDTO> inHospitalDTORows = new ArrayList<>();
			MCCEbizh120102ServiceImpl mCCEbizh120102Service = this.getHygeiaServiceManager()
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, this.getInHospitalDTO().getAkb020());
			inHospitalDTORows = mCCEbizh120102Service.queryInHospitalaaz217s(this.getInHospitalDTO());
			if (inHospitalDTORows == null || inHospitalDTORows.size() == 0) {
				this.getInHospitalDTO().setBka891("0");
				inHospitalDTORows = mCCEbizh120102Service.queryInHospitalaaz217s(this.getInHospitalDTO());
			}
			inHospitalDTO = inHospitalDTORows.get(0);
			mData = settlementClinic.getSettlementInfo(inHospitalDTO);
			if( !UtilFunc.isEmptyString( getParameter("short") )   ){
				 String shortFlag = getParameter("short");
				 mData.put("shortFlag", shortFlag);
			 }
		}
		Map baseInfoMap= ((List<Map>)mData.get("data1")).get(0);
		String aae140= baseInfoMap.get("aae140").toString();
		String bka006= baseInfoMap.get("bka006").toString();
		String aka130= baseInfoMap.get("aka130").toString();
		String bke054= baseInfoMap.get("bke054") == null?null:baseInfoMap.get("bke054").toString();
		String[] arr = new String[]{"",""};
		arr[0] = "/settlement/430300center/";
		
		boolean bz106 = false;
		if(mData.get("data12")!=null && bka006.equals("121")) {
			List data12 = (List)mData.get("data12");
			if(data12.size() > 0 ) {
				bz106 = true;
			}
		}
		//一站式结算单
		if(bke054 != null && "1".equals(bke054)){
			// TS19051500120 - 【需求开发】潭民发76号文门特需求 - by sqp
			if ("131".equals(bka006) && "390".equals(aae140)) {
				arr[0] = arr[0] + "onestep_310_131.xls";
			}else {
				if("1".equals(mData.get("shortFlag"))){
					arr[0] = arr[0] +aae140+"_"+bka006+".xls";
				}else{
					arr[0] = arr[0] +"onestepfinalstatement.xls"; 
				}
			}
				
		}else{
			if("1".equals(mData.get("shortFlag"))){
				arr[0] = arr[0] + aae140+"_"+aka130+"_short.xls";
			}else{
				//TS19123100155 - 【需求开发】城乡居民门诊两病全省需求-湘潭结算云结算单改造_add by ljl 20191231
				String akc196 = baseInfoMap.get("akc196") == null?"":baseInfoMap.get("akc196").toString();
				if (akc196.matches("CXMZLB001|CXMZLB002")&&aae140.equals("390")&&bka006.equals("110")) {
					arr[0] = arr[0] +"390_110td.xls"; 
				}else {
					if(bz106) {
						arr[0] = arr[0] +aae140+"_"+bka006+"_106.xls"; 
					}else {
						arr[0] = arr[0] +aae140+"_"+bka006+".xls"; 
					}
				}
			}
		}	
		this.reportIDfirst=loadJsdModule(mData, arr[0]);
		return "settlementReportClinic";
	}
	
	//中心数据转换
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String loadJsdModule(Map mData,String reportXLS){
		ReportData reportData = new ReportData();
		Map<String, Object> head = reportData.getHead(); // 头部数据列
		Map baseInfoMap= ((List<Map>)mData.get("data1")).get(0);
		String aaa027=baseInfoMap.get("aaa027")==null?"":baseInfoMap.get("aaa027").toString();
		String baa027=baseInfoMap.get("baa027")==null?"":baseInfoMap.get("baa027").toString();
		if(!(aaa027).equals(baa027)){//非异地就医取参保地
			head.put("name",CodetableMapping.getDisplayValue("aaa027",baa027, baa027));
		}else{
			head.put("name",CodetableMapping.getDisplayValue("aaa027",aaa027, aaa027));
		}
		Map<String, Object> foot = reportData.getFoot(); // 底部数据列
		
		reportData.put("data1", mData.get("data1"));
		reportData.put("data2", mData.get("data2"));
		reportData.put("data3", mData.get("data3"));
		reportData.put("data4", mData.get("data4"));
		reportData.put("data5", mData.get("data5"));
		reportData.put("data6", mData.get("data6"));
		reportData.put("data10", mData.get("data10"));
		
		//一站式分类汇总数据
		if(mData.get("data11") !=null){
			reportData.put("data11", mData.get("data11"));
		}
		if(mData.get("data12") !=null){
			reportData.put("data12", mData.get("data12"));
		}
		/*TS19041100099 - 【需求开发】单病种出院结算单需要调整为中心端结算单  modified 675 增加106单病种限额材料信息 2019年4月22日*/
		if(mData.get("data13") !=null){
			reportData.put("data13", mData.get("data13"));
		}
		
		//TS19123100155 - 【需求开发】城乡居民门诊两病全省需求-湘潭结算云结算单改造_add by ljl 20191231_增加data14 15的返回
		if(mData.get("data14") !=null){
			reportData.put("data14", mData.get("data14"));
		}
		if(mData.get("data15") !=null){
			reportData.put("data15", mData.get("data15"));
		}
		
		foot.put("sum_1", 5);
		foot.put("sum_2", 6);
		foot.put("sum_3", 8);
		foot.put("sum_4", 333);

		String bizID =  UtilFunc.getString(baseInfoMap, "aaz217") ; // 业务ID自由定义
	
		PowerReport powerReport = new PowerReportImpl();
		Map powerReportMap = null;
		if (StringUtils.isBlank(bizID)) {
			bizID = "JSQD"+DateFunc.dateToString(new Date(), "yyyyMMddhh");
		}
		powerReportMap = powerReportDao.getReportBaseInfoBizID(bizID);
		
		if (powerReportMap != null) {
			powerReport.delReport(null, bizID);
		}
		String reportID = powerReport.createReport(reportXLS, bizID, 1, reportData, "[" + bizID + "]结算单", BizHelper.getLoginUser()); //返回报表ID
		return reportID;
	}
}
