package com.powersi.ssm.biz.medicare.common.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.comm.pojo.AA13DTO;
import com.powersi.biz.medicare.comm.pojo.MediDTO;
import com.powersi.biz.medicare.comm.service.AA13Service;
import com.powersi.biz.medicare.comm.service.SettlementReportDataService;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh120102Service;
import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.biz.medicare.settlement.service.SettlementClinic;
import com.powersi.comm.service.memory.MemoryDB;
import com.powersi.comm.service.memory.MemoryDBWrapper;
import com.powersi.config.pojo.BizYyInfo;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120102ServiceImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.outland.service.OutBizService;

/**
 * 
 * @author 刘勇
 *
 */
@Service
public class SettlementReportDataServiceImpl implements SettlementReportDataService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private MemoryDB memoryDB;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private CommunalService communalService;
	@Autowired
	private DateService dateService;
	@Autowired
	private OutBizService outBizService;
	@Autowired
	@Qualifier("memoryDBWrapper_core")
	private MemoryDBWrapper memoryDBWrapper;
	
	/**
	 * 待遇类型本地化业务列表
	 */
	private final List<String> aka130LocalList = Arrays.asList(new String[] {"10", "11", "12", "13", "16", "51", "52" });

	@SuppressWarnings("rawtypes")
	@Override
	public Map loadSettlementReportData(MediDTO mediDTO) {
		Map settlementReportData = new HashMap();
		if (aka130_12.equals(mediDTO.getAka130()) || aka130_14.equals(mediDTO.getAka130())
				|| aka130_16.equals(mediDTO.getAka130()) || aka130_17.equals(mediDTO.getAka130())
				|| aka130_42.equals(mediDTO.getAka130()) || aka130_52.equals(mediDTO.getAka130())
				|| aka130_72.equals(mediDTO.getAka130()) || aka130_82.equals(mediDTO.getAka130())) {
			if (mediDTO.getAaz217().startsWith("po")) {
				this.loadSettlementReportDataInHospKsyd(mediDTO, settlementReportData);
			} else if (mediDTO.getAaz217().startsWith("yd")) {
				this.loadSettlementReportDataInHospSnyd(mediDTO, settlementReportData);
			} else {
				if ((mediDTO.getAaz217() + "_1").equals(mediDTO.getBka981())) {
					this.loadSettlementReportDataInHospFirst(mediDTO, settlementReportData);
				} else if ((mediDTO.getAaz217() + "_2").equals(mediDTO.getBka981())) {
					this.loadSettlementReportDataInHospSecond(mediDTO, settlementReportData);
				}else if ((mediDTO.getAaz217() + "_3").equals(mediDTO.getBka981())) {
					// 三级结算单
					this.loadSettlementReportDataInHospThree(mediDTO, settlementReportData);
				} 
				else {
					throw new HygeiaException("住院报表业务ID错误!");
				}
			}
		} else {
			if (StringUtils.isBlank(mediDTO.getBka981())) {
				mediDTO.setBka981(mediDTO.getAaz217());
			}
			if (!mediDTO.getBka981().equals(mediDTO.getAaz217())) {
				throw new HygeiaException("门诊报表业务ID错误!");
			}
			this.loadSettlementReportDataClinic(mediDTO, settlementReportData);
		}
		return settlementReportData;
	}
    
	/**
	 * 
	 * @param mediDTO
	 * @param settlementReportData
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadSettlementReportDataInHospThree(MediDTO mediDTO, Map settlementReportData) {
		MCCEbizh120102Service mCCEbizh120102Service = this.hygeiaServiceManager
				.getBeanByClass(MCCEbizh120102ServiceImpl.class, mediDTO.getAkb020());
		
		Map<String, List<Map>> data = mCCEbizh120102Service.loadSettlementReportDataInHospThree(mediDTO);
		if (data == null || data.size() == 0) {
			return;
		}
		List<Map> data1 = data.get("data1");
		if (data1 != null) {
			this.loadDataItemsNameData1(data1.get(0));
			this.loadDataItemsNameData1Other(data1.get(0));
		}
		List<Map> data5 = data.get("data5");
		if (data5 != null) {
			this.loadDataItemsNameData5(data5);
		}
		List<Map> data4 = data.get("data4");
		if (data4 != null) {
			this.loadDataItemsNameData4(data4);
		}
		
		settlementReportData.putAll(data);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadDataItemsNameData1Other(Map mapRow) {
		mapRow.put("aaa027Name", BizHelper.getAaa027Name());//就医地所属中心名称
	}
	
	/**
	 * 场景处理
	 * 
	 * @param data5
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadDataItemsNameData4(List<Map> data4) {
		String bkf004 = "";
		for (Map row : data4) {
			bkf004 = this.communalService.str(row, "bkf004", "");
			 
			if (StringUtils.isNotBlank(bkf004)) {
				row.put("bkf004", CodetableMapping.getDisplayValue("bkf004", bkf004, bkf004));
			}
			 
		}
	}
	
	/**
	 * 
	 * @param mediDTO
	 * @param settlementReportData
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadSettlementReportDataInHospSecond(MediDTO mediDTO, Map settlementReportData) {
		Map<String, List<Map>> data = new HashMap<>();
		if("2".equals(mediDTO.getBke035())){
			SettlementClinic settlementClinic = this.hygeiaServiceManager
					.getBeanByClass(SettlementClinic.class, mediDTO.getAkb020());
			data = settlementClinic.loadCenterSettlementReportData(mediDTO);
		}else{
			MCCEbizh120102Service mCCEbizh120102Service = this.hygeiaServiceManager
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, mediDTO.getAkb020());
			data = mCCEbizh120102Service.loadSettlementReportDataInHospSecond(mediDTO);
		}
		if (data == null || data.size() == 0) {
			return;
		}
		List<Map> data1 = data.get("data1");
		if (data1 != null && data1.size() > 0) {
			this.loadDataItemsNameData1(data1.get(0));
		}
		List<Map> data5 = data.get("data5");
		if (data5 != null && data5.size() > 0) {
			this.loadDataItemsNameData5(data5);
		}
		settlementReportData.putAll(data);
	}

	/**
	 * 费用分类处理
	 * 
	 * @param data5
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadDataItemsNameData5(List<Map> data5) {
		if (data5 == null || data5.size() == 0) {
			return;
		}
		String bka802_1 = "", bka802_5 = "";
		for (Map row : data5) {
			bka802_1 = this.communalService.str(row, "bka802_1", "");
			bka802_5 = this.communalService.str(row, "bka802_5", "");
			if (StringUtils.isNotBlank(bka802_1)) {
				row.put("bka802_1", CodetableMapping.getDisplayValue("aka063", bka802_1, bka802_1));
			}
			if (StringUtils.isNotBlank(bka802_5)) {
				row.put("bka802_5", CodetableMapping.getDisplayValue("aka063", bka802_5, bka802_5));
			}
		}
	}

	/**
	 * 
	 * @param mediDTO
	 * @param settlementReportData
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadSettlementReportDataInHospFirst(MediDTO mediDTO, Map settlementReportData) {
		Map<String, List<Map>> data = new HashMap<>();
		if("2".equals(mediDTO.getBke035())){
			SettlementClinic settlementClinic = this.hygeiaServiceManager
					.getBeanByClass(SettlementClinic.class, mediDTO.getAkb020());
			data = settlementClinic.loadCenterSettlementReportData(mediDTO);
		}
		else{
			MCCEbizh120102Service mCCEbizh120102Service = this.hygeiaServiceManager
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, mediDTO.getAkb020());
				data = mCCEbizh120102Service.loadSettlementReportDataInHospFirst(mediDTO);
			}
		
		if (data == null || data.size() == 0) {
			return;
		}
		List<Map> data1 = data.get("data1");
		if (data1 != null && data1.size() > 0) {
			this.loadDataItemsNameData1(data1.get(0));
		}
		settlementReportData.putAll(data);
	}

	/**
	 * 
	 * @param mapRow
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadDataItemsNameData1(Map mapRow) {
		String bka035 = this.communalService.str(mapRow, "bka035", "");//人员类别
		if (StringUtils.isNotBlank(bka035)) {
			mapRow.put("bka035", CodetableMapping.getDisplayValue("bka035", bka035, bka035));
		}
		
		String aka101 = this.communalService.str(mapRow, "aka101", "");
		if (StringUtils.isNotBlank(aka101)) {
			mapRow.put("aka101", CodetableMapping.getDisplayValue("aka101", aka101, aka101));
		}
		String bkc119 = this.communalService.str(mapRow, "bkc119", "");
		if (StringUtils.isNotBlank(bkc119)) {
			mapRow.put("bkc119", CodetableMapping.getDisplayValue("bkc119", bkc119, bkc119));
		}
		String bka989 = this.communalService.str(mapRow, "bka989", "");
		if (StringUtils.isNotBlank(bka989)) {
			mapRow.put("bka989", CodetableMapping.getDisplayValue("bka989", bka989, bka989));
		}
		String aac004 = this.communalService.str(mapRow, "aac004", "");
		if (StringUtils.isNotBlank(aac004)) {
			mapRow.put("aac004", CodetableMapping.getDisplayValue("aac004", aac004, aac004));
		}
		String bka004 = this.communalService.str(mapRow, "bka004", "");
		if (StringUtils.isNotBlank(bka004)) {
			mapRow.put("bka004", CodetableMapping.getDisplayValue("bka004", bka004, bka004));
		}
		String aka130 = this.communalService.str(mapRow, "aka130", "");
		if (StringUtils.isNotBlank(aka130)) {
			mapRow.put("aka130", CodetableMapping.getDisplayValue("aka130", aka130, aka130));
		}
		String bka066 = this.communalService.str(mapRow, "bka066", "");
		if (StringUtils.isNotBlank(bka066)) {
			mapRow.put("bka066", CodetableMapping.getDisplayValue("bka066", bka066, bka066));
		}
		String bke301 = this.communalService.str(mapRow, "bke301", "");
		if (StringUtils.isNotBlank(bke301)) {
			mapRow.put("bke301", CodetableMapping.getDisplayValue("bke301", bke301, bke301));
		}
		String bke302 = this.communalService.str(mapRow, "bke302", "");
		if (StringUtils.isNotBlank(bke302)) {
			mapRow.put("bke302", CodetableMapping.getDisplayValue("bke302", bke302, bke302));
		}
		String bka006 = this.communalService.str(mapRow, "bka006", "");
		String aaa027 = this.communalService.str(mapRow, "aaa027", "");
		String baa027 = this.communalService.str(mapRow, "baa027", "");
		if (StringUtils.isNotBlank(bka006) && StringUtils.isNotBlank(aaa027)
				&& this.aka130LocalList.indexOf(aka130) != -1) {
			mapRow.put("bka006", CodetableMapping.getDisplayValue("bka006", bka006, bka006));
		} else {
			mapRow.put("bka006", CodetableMapping.getDisplayValue("bka006", bka006, bka006));
		}
		//取biz_yy_info表
		String selectTag = this.communalService.str(mapRow, "selectTag", "");
		String akb020 = this.communalService.str(mapRow, "akb020", "");
		String bkc110 = "";
		if("yes".equals(selectTag)){
			String bka502 =  this.communalService.str(mapRow, "bka502", "");
			if(StringUtils.isNotBlank(bka502)){
				bkc110 = bka502;
			}else{
				BizYyInfo yyInfo = (BizYyInfo) this.memoryDBWrapper.getMemoryDB().getMapValue("MAP_BIZ_YY_INFO", akb020);
				bkc110 = yyInfo.getYyjb();
			}
		}else{
			bkc110 = this.communalService.str(mapRow, "bkc110", "");
		}
		if (StringUtils.isNotBlank(bkc110)) {
			mapRow.put("bkc110", CodetableMapping.getDisplayValue("bkc110", bkc110, bkc110));
		}
		
		List<AA13DTO> aA13DTORows = (List<AA13DTO>) memoryDB.getList(AA13Service.MAP_HYGEIA_BASE_AA13, 0, -1);
		if (aA13DTORows == null || aA13DTORows.size() == 0) {
			return;
		}
		String aaa027_name="",baa027_name="";
		for (AA13DTO aA13DTORow : aA13DTORows) {
			if (aaa027.equals(aA13DTORow.getBka501())) {
				aaa027_name = aA13DTORow.getAaa129();
			}
			if (baa027.equals(aA13DTORow.getBka501())) {
				baa027_name = aA13DTORow.getAaa129();
			}
		}
		if (aaa027_name != null && !"".equals(aaa027_name)) {
			mapRow.put("aaa027_name", aaa027_name);//
		} else {
			mapRow.put("aaa027_name", "未知统筹区");
		}

		if (baa027_name != null && !"".equals(baa027_name)) {
			mapRow.put("baa027_name", baa027_name);//
		} else {
			mapRow.put("baa027_name", "未知统筹区");
		}
		
		if (StringUtils.isNotBlank(aka130) && (aka130_82.equals(aka130) || aka130_72.equals(aka130))) {
			/*if (StringUtils.isNotBlank(aaa027)) {
				mapRow.put("aaa027_name", this.outBizService.getBkd324(aaa027));
			}*/
			/*String baa027 = this.communalService.str(mapRow, "baa027", "");
			if (StringUtils.isNotBlank(baa027)) {
				mapRow.put("baa027_name", this.outBizService.getBkd324(baa027));
			}*/
			String aae140 = this.communalService.str(mapRow, "aae140", "");
			if (StringUtils.isNotBlank(baa027)) {
				mapRow.put("aae140_name", CodetableMapping.getDisplayValue("aae140", aae140, aae140));
			}
			String bkf002 = this.communalService.str(mapRow, "bkf002", "0");
			if (StringUtils.isNotBlank(bkf002)) {
				mapRow.put("bkf002", CodetableMapping.getDisplayValue("bkf002", bkf002, bkf002));
			}
			String aac006 = this.communalService.str(mapRow, "aac006", "");
			if (StringUtils.isNotBlank(aac006)) {
				Date birthday = this.dateService.toDate(aac006, DateService.yyyyMMdd);
				int age = this.communalService.calcAgeByBirth(birthday);
				mapRow.put("age", age);
			}
			String bka018 = this.communalService.str(mapRow, "bka018", "");
			String bka018_1 = "";
			String bka018_2 = "";
			if (StringUtils.isNotBlank(bka018)) {
				String[] arr = bka018.split("\\|");
				if (arr != null && arr.length > 0) {
					if (StringUtils.isNotBlank(arr[0])) {
						String[] arr_1 = arr[0].split(",");
						bka018_1 = (arr_1 != null && arr_1.length > 1) ? arr_1[1] : "";
					}
					if (arr.length > 1) {
						if (StringUtils.isNotBlank(arr[1])) {
							String[] arr_2 = arr[1].split(",");
							bka018_2 = (arr_2 != null && arr_2.length > 1) ? arr_2[1] : "";
						}
					}
				}
			}
			mapRow.put("bka018_1", bka018_1);
			mapRow.put("bka018_2", bka018_2);
			String bka890 = this.dateService.dateToString(new Date(), " yyyy 年MM 月dd 日");
			mapRow.put("bka890", bka890);
		}
	}

	/**
	 * 
	 * @param mediDTO
	 * @param settlementReportData
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadSettlementReportDataClinic(MediDTO mediDTO, Map settlementReportData) {
		Map<String, List<Map>> data = new HashMap<>();
		if("2".equals(mediDTO.getBke035())){
			SettlementClinic settlementClinic = this.hygeiaServiceManager
					.getBeanByClass(SettlementClinic.class, mediDTO.getAkb020());
			data = settlementClinic.loadCenterSettlementReportData(mediDTO);
		}else{
			MCCEbizh120102ServiceImpl mCCEbizh120102Service = this.hygeiaServiceManager
					.getBeanByClass(MCCEbizh120102ServiceImpl.class, mediDTO.getAkb020());
			 data = mCCEbizh120102Service.loadSettlementReportDataClinic(mediDTO);
		}
		if (data == null || data.size() == 0) {
			return;
		}
		List<Map> data1 = data.get("data1");
		if (data1 != null && data1.size() > 0) {
			this.loadDataItemsNameData1(data1.get(0));
		}
		List<Map> data7 = data.get("data7");
		if (data7 != null && data7.size() > 0) {
			this.loadDataItemsNameData7(data7);
		}
		List<Map> data3 = data.get("data3");
		if (data3 != null && data3.size() > 0) {
			this.loadDataItemsNameData3(data3);
		}
		settlementReportData.putAll(data);
	}

	/**
	 * 
	 * @param data7
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadDataItemsNameData7(List<Map> data7) {
		if (data7 == null || data7.size() == 0) {
			return;
		}
		String bkc118 = null, bkc115 = null, aka022 = null, ala011 = null, ama011 = null, bka848 = null;
		for (Map mapRow : data7) {
			bkc118 = this.communalService.str(mapRow, "bkc118", "");
			if (StringUtils.isNotBlank(bkc118)) {
				mapRow.put("bkc118", CodetableMapping.getDisplayValue("bkc118", bkc118, bkc118));
			}
			bkc115 = this.communalService.str(mapRow, "bkc115", "");
			if (StringUtils.isNotBlank(bkc115)) {
				mapRow.put("bkc115", CodetableMapping.getDisplayValue("bkc115", bkc115, bkc115));
			}
			aka022 = this.communalService.str(mapRow, "aka022", "");
			if (StringUtils.isNotBlank(aka022)) {
				mapRow.put("aka022", CodetableMapping.getDisplayValue("aka022", aka022, aka022));
			}
			ala011 = this.communalService.str(mapRow, "ala011", "");
			if (StringUtils.isNotBlank(ala011)) {
				mapRow.put("ala011", CodetableMapping.getDisplayValue("ala011", ala011, ala011));
			}
			ama011 = this.communalService.str(mapRow, "ama011", "");
			if (StringUtils.isNotBlank(ama011)) {
				mapRow.put("ama011", CodetableMapping.getDisplayValue("ama011", ama011, ama011));
			}
			bka848 = this.communalService.str(mapRow, "bka848", "");
			if (StringUtils.isNotBlank(bka848)) {
				mapRow.put("bka848", CodetableMapping.getDisplayValue("bka848", bka848, bka848));
			}
		}
	}

	/**
	 * 
	 * @param mediDTO
	 * @param settlementReportData
	 * @author zhos
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadSettlementReportDataInHospKsyd(MediDTO mediDTO, Map settlementReportData) {
		MCCEbizh120102Service mCCEbizh120102Service = this.hygeiaServiceManager
				.getBeanByClass(MCCEbizh120102ServiceImpl.class, mediDTO.getAkb020());
		Map<String, List<Map>> data = mCCEbizh120102Service.loadSettlementReportDataInHospKsyd(mediDTO);
		if (data == null || data.size() == 0) {
			return;
		}
		List<Map> data1 = data.get("data1");
		if (data1 != null && data1.size() > 0) {
			this.loadDataItemsNameData1(data1.get(0));
		}
		List<Map> data2 = data.get("data2");
		if (data2 != null && data2.size() > 0) {
			this.loadDataItemsNameData2Ksyd(data2.get(0));
		}
		settlementReportData.putAll(data);
	}

	/**
	 * 
	 * @param mediDTO
	 * @param settlementReportData
	 * @author zhos
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadSettlementReportDataInHospSnyd(MediDTO mediDTO, Map settlementReportData) {
		MCCEbizh120102Service mCCEbizh120102Service = this.hygeiaServiceManager
				.getBeanByClass(MCCEbizh120102ServiceImpl.class, mediDTO.getAkb020());
		Map<String, List<Map>> data = mCCEbizh120102Service.loadSettlementReportDataInHospSnyd(mediDTO);
		if (data == null || data.size() == 0) {
			return;
		}
		List<Map> data1 = data.get("data1");
		if (data1 != null && data1.size() > 0) {
			this.loadDataItemsNameData1(data1.get(0));
		}
		settlementReportData.putAll(data);
	}

	/**
	 * 
	 * @param mapRow
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadDataItemsNameData2Ksyd(Map mapRow) {
		String aae140 = null;
		aae140 = this.communalService.str(mapRow, "aae140", "");
		if (StringUtils.isNotBlank(aae140)) {
			mapRow.put("aae140_name", CodetableMapping.getDisplayValue("aae140", aae140, aae140));
		}
	}
	
	/**
	 * 
	 * @param data7
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadDataItemsNameData3(List<Map> data3) {
		if (data3 == null || data3.size() == 0) {
			return;
		}
		String aka101 = null;
		String aka103 = null ;
		for (Map mapRow : data3) {
			aka101 = this.communalService.str(mapRow, "aka101", "");
			if (StringUtils.isNotBlank(aka101)) {
				mapRow.put("aka101", CodetableMapping.getDisplayValue("aka063", aka101, aka101));
			}
			aka103 = this.communalService.str(mapRow, "aka103", "");
			if (StringUtils.isNotBlank(aka103)) {
				mapRow.put("aka103", CodetableMapping.getDisplayValue("aka063", aka103, aka103));
			}
		}
	}
}
