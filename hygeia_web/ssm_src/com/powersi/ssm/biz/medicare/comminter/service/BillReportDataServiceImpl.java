package com.powersi.ssm.biz.medicare.comminter.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.comm.service.MoneyUtil;
import com.powersi.biz.medicare.comminter.pojo.KAB3DTO;
import com.powersi.biz.medicare.comminter.service.BillReportDataService;
import com.powersi.biz.medicare.comminter.service.InvoiceManagerService;
import com.powersi.biz.medicare.inhospital.pojo.FeeFinDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.pojo.PayFinDTO;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.SysFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEbizh120102ServiceImpl;
import com.powersi.ssm.biz.medicare.comminter.util.ConvertNumberUtil;

/**
 * 
 * @author 刘飞扬
 *
 */
@Service
public class BillReportDataServiceImpl implements BillReportDataService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private BeanService beanService;

	/**
	 * 湘潭获取门诊住院发票套打数据
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map loadPrintingReportData(KAB3DTO kab3DTO) {
		Map billReportData = new HashMap();
		List<Map> list = new ArrayList<Map>();
		//获取基础数据
		Map dataMap = new HashMap<>();
		InHospitalDTO inHospitalDTO =new InHospitalDTO();
		this.beanService.copyProperties(kab3DTO, inHospitalDTO, true);
		inHospitalDTO.setAae100("1");
		inHospitalDTO.setBka977("1");
		inHospitalDTO.setBka891("1");
		if("1".equals(inHospitalDTO.getCenter_flag())){//核心业务平台
			/*
			   SettlementClinicNewCenter settlementClinic = hygeiaServiceManager.getBeanByClass(SettlementClinicNewCenter.class,inHospitalDTO.getAkb020());
			   inHospitalDTO = settlementClinic.queryInHospitalaaz217center(inHospitalDTO);
			   mData=settlementClinic.getCenterData(inHospitalDTO);
			*/
		}else{//结算云平台
			List<InHospitalDTO> inHospitalDTORows = new ArrayList<>();
			MCCEbizh120102ServiceImpl mCCEbizh120102Service = hygeiaServiceManager.getBeanByClass(MCCEbizh120102ServiceImpl.class, inHospitalDTO.getAkb020());		
			inHospitalDTORows = mCCEbizh120102Service.queryInHospitalaaz217s(inHospitalDTO);
			if (inHospitalDTORows == null || inHospitalDTORows.size() == 0) {
				inHospitalDTO.setBka891("0");
				inHospitalDTORows = mCCEbizh120102Service.queryInHospitalaaz217s(inHospitalDTO);
			}
			inHospitalDTO = inHospitalDTORows.get(0);
			inHospitalDTO.setBka445(kab3DTO.getBka445());
		}
		dataMap=loadDataClinc(inHospitalDTO);
		list.add(dataMap);
		billReportData.put("data1", list);	
		return billReportData;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map loadDataClinc(InHospitalDTO inHospitalDTO){
		Map data = new HashMap<>();
		//获取基础数据
		data.put("aac003", inHospitalDTO.getAac003());
		if(UtilFunc.isNotBlank(inHospitalDTO.getAac004())){
			data.put("aac004",CodetableMapping.getDisplayValue("aac004", inHospitalDTO.getAac004(), inHospitalDTO.getAac004()));//性别
		}
		if(UtilFunc.isNotBlank(inHospitalDTO.getBke301())){
			data.put("bke301",CodetableMapping.getDisplayValue("bke301", inHospitalDTO.getBke301(), inHospitalDTO.getBke301()));//医院级别（三级）
		}
		if(UtilFunc.isNotBlank(inHospitalDTO.getAka101())){
		 data.put("aka101", CodetableMapping.getDisplayValue("aka101", inHospitalDTO.getAka101(), inHospitalDTO.getAka101()));//医院等级（特等）
		}
		data.put("aac002", inHospitalDTO.getAac002());
		data.put("aaz217", inHospitalDTO.getAaz217());
		data.put("aae031", inHospitalDTO.getAae031());
	
		//获取医保类型
		String bka035=inHospitalDTO.getBka035(); 
		if("51".equals(inHospitalDTO.getAka130()) || "52".equals(inHospitalDTO.getAka130())){
			data.put("type", "生育保险");
		}else if("21".equals(bka035) || "22".equals(bka035) ||"23".equals(bka035) ||"24".equals(bka035) || "25".equals(bka035) || "70".equals(bka035)){
			data.put("type", "城居医疗");
		}else if("3".equals(bka035)){
			data.put("type", "离休医疗");
		}else{
			data.put("type", "城职医疗");
		}	
					
		//打印收费项目内容 门诊
		if ("0".equals(inHospitalDTO.getBka891())) {
			throw new HygeiaException("不能打印未结算业务的发票");
		} else {
			InvoiceManagerService invoiceManagerService = this.hygeiaServiceManager.getBeanByClass(InvoiceManagerService.class, inHospitalDTO.getAkb020());
			if("1".equals(inHospitalDTO.getBka445())){//门诊套打-项目数据
				List<FeeFinDTO> feeFinDTORows =invoiceManagerService.queryFeeFinInfo(inHospitalDTO);			
				if (feeFinDTORows == null || feeFinDTORows.size() == 0) {
					throw new HygeiaException("医院端查询本笔业务费用明细信息为空(" + inHospitalDTO.getAaz217() + ")!");
				} 
				int i=1;
				Double bka999 = 0.00d;
				BigDecimal money = BigDecimal.valueOf(0.00);
				for (FeeFinDTO fee : feeFinDTORows) {//循环加载费用明细；
					data.put("a"+i, fee.getAke006());//医疗机构三大目录名称
					i++;
					data.put("a"+i, fee.getAkc226());//数量
					i++;
					data.put("a"+i, fee.getBka040());//金额
					i++;
					data.put("a"+i, fee.getAae019());//个人支付金额
					i++;
		
					money=money.add(fee.getAae019());
				}
				bka999=Double.parseDouble(money.toString());
				data.put("bka999", bka999);
				data.put("bka888",
						bka999 >= 0 ? "" + MoneyUtil.toChinese(String.valueOf(SysFunc.getRound(Math.abs(bka999), 2)))
								: "负" + MoneyUtil.toChinese(String.valueOf(SysFunc.getRound(Math.abs(bka999), 2))));
				if(bka999==0) {data.put("bka888","零");}
				//data.put("bka888", MoneyUtil.toChinese(String.valueOf(bka999)));
				
			}else{//打印收费项目内容 住院
				data.put("aae030", inHospitalDTO.getAae030()+"  到   ");
				data.put("bka030", inHospitalDTO.getBka030());//住院天数
				data.put("akc190", inHospitalDTO.getAkc190());//住院号
				data.put("bka245", inHospitalDTO.getBka245());//预付款
				List<Map<String, Object>> feeMap = new ArrayList<>();
				feeMap = invoiceManagerService.queryBizFeeInfo(inHospitalDTO);
				Double bka999 = 0.00d;
				int i=1;
				for (Map<String, Object> map : feeMap) {
					String aka063=UtilFunc.getString(map, "aka063");
					bka999 += Double.parseDouble(UtilFunc.isBlank(UtilFunc.getString(map, "aae019"))?"0.00":UtilFunc.getString(map, "aae019"));
					data.put("a"+i, CodetableMapping.getDisplayValue("aka063", aka063, aka063));
					i++;
					data.put("a"+i, UtilFunc.getString(map, "aae019"));
					i++;
					
				}
				data.put("bka999", bka999);
				data.put("bka888",
						bka999 >= 0 ? "" + MoneyUtil.toChinese(String.valueOf(SysFunc.getRound(Math.abs(bka999), 2)))
								: "负" + MoneyUtil.toChinese(String.valueOf(SysFunc.getRound(Math.abs(bka999), 2))));
				if(bka999==0) {data.put("bka888","零");}
				//data.put("bka888", MoneyUtil.toChinese(String.valueOf(bka999)));			
			}
			
			//查询kc27获取基金		
			List<PayFinDTO> payFinDTORows =invoiceManagerService.queryPayFinInfo(inHospitalDTO);
			if (payFinDTORows == null || payFinDTORows.size() == 0) {
				throw new HygeiaException("医院端查询本笔业务费用支付信息为空(" + inHospitalDTO.getAaz217() + ")!");
			} 
			//基金区分加载；
			double bka801_1 = 0.00d;// 个人支付999
			double bka801_2 = 0.00d;// 个人账户支付003
			double bka801_3 = 0.00d;// 医保统筹511+001+801
			double bka801_4 = 0.00d;// 其他基金支付201+202+301+803+996+701+702+703+806
			DecimalFormat df = new DecimalFormat("0.00");
			for (PayFinDTO payFinDTORow : payFinDTORows) {
				if ("999".equals(payFinDTORow.getAaa157())) {  //现金
					bka801_1 += payFinDTORow.getAae019().doubleValue();
				}
				else if ("003".equals(payFinDTORow.getAaa157())) {//个账
					bka801_2 += payFinDTORow.getAae019().doubleValue();
				}else if("511".equals(payFinDTORow.getAaa157()) || "001".equals(payFinDTORow.getAaa157())
						||"801".equals(payFinDTORow.getAaa157())){//医保统筹
					bka801_3 += payFinDTORow.getAae019().doubleValue();
				}else if ("201".equals(payFinDTORow.getAaa157()) || "202".equals(payFinDTORow.getAaa157())
						|| "301".equals(payFinDTORow.getAaa157()) || "803".equals(payFinDTORow.getAaa157())
						|| "996".equals(payFinDTORow.getAaa157()) || "701".equals(payFinDTORow.getAaa157())
						|| "702".equals(payFinDTORow.getAaa157()) || "703".equals(payFinDTORow.getAaa157())
						|| "806".equals(payFinDTORow.getAaa157())) {//其他基金支付
					bka801_4 += payFinDTORow.getAae019().doubleValue();
				}
			}
			data.put("bka801_1", df.format(bka801_1));
			data.put("bka801_2", df.format(bka801_2));
			data.put("bka801_3", df.format(bka801_3));
			data.put("bka801_4", df.format(bka801_4));
		}
		return data;
	}

	
	@SuppressWarnings("rawtypes")
	@Override
	public Map loadBillReportData(KAB3DTO kab3DTO) {
		Map billReportData = new HashMap();
		this.loadBillReportDataComm(kab3DTO, billReportData);
		return billReportData;
	}

	/**
	 * 加载数据
	 * 
	 * @param kab1DTO
	 * @param billReportData
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadBillReportDataComm(KAB3DTO kab3DTO, Map billReportData) {
		InvoiceManagerService invoiceManagerService = this.hygeiaServiceManager
				.getBeanByClass(InvoiceManagerService.class, kab3DTO.getAkb020());
		String aae030 = null;// 开票时间
		String bae413 = kab3DTO.getBae413();// 发票号码（此时kab3表里还没存发票号，只能前台传入，在此先记录）
		String draw_tag_11 = ""; // 门诊
		String draw_tag_111 = "";// 急诊
		String draw_tag_12 = "";// 住院
		String bka017 = null;// 入院日期（yyyy-MM-dd）
		String bka032 = null;// 出院日期（yyyy-MM-dd）

		BigDecimal bae421 = new BigDecimal(0.00); // 西药费
		BigDecimal bae422 = new BigDecimal(0.00); // 中成药费
		BigDecimal bae423 = new BigDecimal(0.00); // 中草药费
		BigDecimal bae424 = new BigDecimal(0.00); // 检查费
		BigDecimal bae425 = new BigDecimal(0.00); // CT费用
		BigDecimal bae426 = new BigDecimal(0.00); // 核磁费费
		BigDecimal bae427 = new BigDecimal(0.00); // B超费
		BigDecimal bae428 = new BigDecimal(0.00); // 治疗费
		BigDecimal bae429 = new BigDecimal(0.00); // 化验费
		BigDecimal bae430 = new BigDecimal(0.00); // 手术费
		BigDecimal bae431 = new BigDecimal(0.00); // 输氧费
		BigDecimal bae432 = new BigDecimal(0.00); // 放射费
		BigDecimal bae433 = new BigDecimal(0.00);// 注射费
		BigDecimal bae434 = new BigDecimal(0.00);// 透析费
		BigDecimal bae435 = new BigDecimal(0.00);// 化疗费
		BigDecimal bae436 = new BigDecimal(0.00); // 床位费
		BigDecimal bae437 = new BigDecimal(0.00); // 材料费
		BigDecimal bae438 = new BigDecimal(0.00); // 护理费
		BigDecimal bae439 = new BigDecimal(0.00); // 一般诊疗费
		BigDecimal bae440 = new BigDecimal(0.00); // 挂号费
		BigDecimal bae441 = new BigDecimal(0.00); // 麻醉费
		BigDecimal bae442 = new BigDecimal(0.00); // 其他费

		String settlement_tag = "现金";// 结算方式
		String upperNumbers = null;// 合计大写金额
		BigDecimal aae019 = new BigDecimal(0.00);// 合计金额
		BigDecimal aae020 = new BigDecimal(0.00);// 基金支付金额
		BigDecimal aae024 = new BigDecimal(0.00);// 个人缴费金额

		KAB3DTO kab3dtoRow = new KAB3DTO();
		String[] propertyName = { "akb020", "aaz217", "kab3id" };// modify by zhos 打印前台账表里并没有bae413
		this.beanService.copyProperties(kab3DTO, kab3dtoRow, propertyName);
		kab3DTO = invoiceManagerService.selectKab3(kab3dtoRow);
		if (UtilFunc.isBlank(kab3DTO)) {
			throw new HygeiaException("获取结算信息台账失败请检查：aaz217[" + kab3dtoRow.getAaz217() + "]");
		}

		List<Map> list = new ArrayList<Map>();
		Map dataMap = new HashMap<>();
		String[] propertyName2 = { "aac003", "bka020", "bka025", "bka015" };// 姓名 科室 住院号 收费员姓名
		this.beanService.copyProperties(kab3DTO, dataMap, propertyName2);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		aae030 = dateFormat.format(DateFunc.getDatetime());
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		bka017 = kab3DTO.getBka017() != null ? dateFormat1.format(kab3DTO.getBka017()) : "";
		bka032 = kab3DTO.getBka032() != null ? dateFormat1.format(kab3DTO.getBka032()) : "";

		String aka130 = kab3DTO.getAka130();
		switch (aka130) {
		case "11":
			draw_tag_11 = "√";
			break;
		case "111":
			draw_tag_111 = "√";
			break;
		case "12":
			draw_tag_12 = "√";
			break;
		default:
			break;
		}

		bae421 = kab3DTO.getBae421() != null ? kab3DTO.getBae421().setScale(2, BigDecimal.ROUND_HALF_UP) : bae421;
		bae422 = kab3DTO.getBae422() != null ? kab3DTO.getBae422().setScale(2, BigDecimal.ROUND_HALF_UP) : bae422;
		bae423 = kab3DTO.getBae423() != null ? kab3DTO.getBae423().setScale(2, BigDecimal.ROUND_HALF_UP) : bae423;
		bae424 = kab3DTO.getBae424() != null ? kab3DTO.getBae424().setScale(2, BigDecimal.ROUND_HALF_UP) : bae424;
		bae425 = kab3DTO.getBae424() != null ? kab3DTO.getBae424().setScale(2, BigDecimal.ROUND_HALF_UP) : bae425;
		bae426 = kab3DTO.getBae424() != null ? kab3DTO.getBae424().setScale(2, BigDecimal.ROUND_HALF_UP) : bae426;
		bae427 = kab3DTO.getBae424() != null ? kab3DTO.getBae424().setScale(2, BigDecimal.ROUND_HALF_UP) : bae427;
		bae428 = kab3DTO.getBae424() != null ? kab3DTO.getBae424().setScale(2, BigDecimal.ROUND_HALF_UP) : bae428;
		bae429 = kab3DTO.getBae429() != null ? kab3DTO.getBae429().setScale(2, BigDecimal.ROUND_HALF_UP) : bae429;
		bae430 = kab3DTO.getBae430() != null ? kab3DTO.getBae430().setScale(2, BigDecimal.ROUND_HALF_UP) : bae430;
		bae431 = kab3DTO.getBae431() != null ? kab3DTO.getBae431().setScale(2, BigDecimal.ROUND_HALF_UP) : bae431;
		bae432 = kab3DTO.getBae432() != null ? kab3DTO.getBae432().setScale(2, BigDecimal.ROUND_HALF_UP) : bae432;
		bae433 = kab3DTO.getBae433() != null ? kab3DTO.getBae433().setScale(2, BigDecimal.ROUND_HALF_UP) : bae433;
		bae434 = kab3DTO.getBae434() != null ? kab3DTO.getBae434().setScale(2, BigDecimal.ROUND_HALF_UP) : bae434;
		bae435 = kab3DTO.getBae435() != null ? kab3DTO.getBae435().setScale(2, BigDecimal.ROUND_HALF_UP) : bae435;
		bae436 = kab3DTO.getBae436() != null ? kab3DTO.getBae436().setScale(2, BigDecimal.ROUND_HALF_UP) : bae436;
		bae437 = kab3DTO.getBae437() != null ? kab3DTO.getBae437().setScale(2, BigDecimal.ROUND_HALF_UP) : bae437;
		bae438 = kab3DTO.getBae438() != null ? kab3DTO.getBae438().setScale(2, BigDecimal.ROUND_HALF_UP) : bae438;
		bae439 = kab3DTO.getBae439() != null ? kab3DTO.getBae439().setScale(2, BigDecimal.ROUND_HALF_UP) : bae439;
		bae440 = kab3DTO.getBae440() != null ? kab3DTO.getBae440().setScale(2, BigDecimal.ROUND_HALF_UP) : bae440;
		bae441 = kab3DTO.getBae441() != null ? kab3DTO.getBae441().setScale(2, BigDecimal.ROUND_HALF_UP) : bae441;
		bae442 = kab3DTO.getBae442() != null ? kab3DTO.getBae442().setScale(2, BigDecimal.ROUND_HALF_UP) : bae442;

		aae019 = kab3DTO.getAae019() != null ? kab3DTO.getAae019().setScale(2, BigDecimal.ROUND_HALF_UP) : aae019;
		upperNumbers = ConvertNumberUtil.toUpperNumber(String.valueOf(aae019));

		aae020 = kab3DTO.getAae020() != null ? kab3DTO.getAae020().setScale(2, BigDecimal.ROUND_HALF_UP) : aae020;
		aae024 = kab3DTO.getAae024() != null ? kab3DTO.getAae024().setScale(2, BigDecimal.ROUND_HALF_UP) : aae024;

		dataMap.put("aae030", aae030);// 开票时间
		dataMap.put("bae413", bae413);// 发票号码
		dataMap.put("draw_tag_11", draw_tag_11);
		dataMap.put("draw_tag_111", draw_tag_111);
		dataMap.put("draw_tag_12", draw_tag_12);
		dataMap.put("bka017", bka017);
		dataMap.put("bka032", bka032);
		dataMap.put("settlement_tag", settlement_tag);

		// 01西药，02中成药，03中草药 归类到：医药费
		// 04常规检查,(05 CT,06 核磁,07 B超) 发票显示类别: CT/B超/核磁 , 09 化验费 归类到：诊查费
		// (08治疗费,10手术费,11输氧费,12放射费,13输血费,14注射费,15透析费,16化疗费,30麻醉费) 发票显示类别：治疗费, 20一般诊疗费
		// 归类到：治疗费
		// (17床位费,18材料费,19护理费,99其他费用) 发票显示类别：其他费用, 21挂号费 归类到：其他
		bae425 = new BigDecimal(bae425.doubleValue() + bae426.doubleValue() + bae427.doubleValue()).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		bae428 = new BigDecimal(
				bae428.doubleValue() + bae430.doubleValue() + bae431.doubleValue() + bae432.doubleValue()
						+ bae433.doubleValue() + bae434.doubleValue() + bae435.doubleValue() + bae441.doubleValue())
								.setScale(2, BigDecimal.ROUND_HALF_UP);
		bae442 = new BigDecimal(
				bae436.doubleValue() + bae437.doubleValue() + bae438.doubleValue() + bae442.doubleValue()).setScale(2,
						BigDecimal.ROUND_HALF_UP);

		// 医药费
		dataMap.put("bae421", bae421.compareTo(new BigDecimal(0.00)) == 0 ? "" : " 西药费    " + bae421 + "  ");// 西药
		dataMap.put("bae422", bae422.compareTo(new BigDecimal(0.00)) == 0 ? "" : " 中成药    " + bae422 + "  ");// 中成药
		dataMap.put("bae423", bae423.compareTo(new BigDecimal(0.00)) == 0 ? "" : " 中草药    " + bae423 + "  ");// 中草药

		// 诊查费
		dataMap.put("bae424", bae424.compareTo(new BigDecimal(0.00)) == 0 ? "" : " 常规检查费    " + bae424 + "  ");// 常规检查费
		dataMap.put("bae425", bae425.compareTo(new BigDecimal(0.00)) == 0 ? "" : " CT/B超/核磁    " + bae425 + "  ");// CT/B超/核磁
		dataMap.put("bae429", bae429.compareTo(new BigDecimal(0.00)) == 0 ? "" : " 化验费    " + bae429 + "  ");// 化验费

		// 治疗费
		dataMap.put("bae428", bae428.compareTo(new BigDecimal(0.00)) == 0 ? "" : " 治疗费    " + bae428 + "  ");// 治疗费
		dataMap.put("bae439", bae439.compareTo(new BigDecimal(0.00)) == 0 ? "" : " 一般诊疗费    " + bae439 + "  ");// 一般诊疗费

		// 其他
		dataMap.put("bae442", bae442.compareTo(new BigDecimal(0.00)) == 0 ? "" : " 其他费用    " + bae442 + "  ");// 其他费用
		dataMap.put("bae440", bae440.compareTo(new BigDecimal(0.00)) == 0 ? "" : " 挂号费    " + bae440 + "  ");// 挂号费
		// 发票费用类别项目归类：1 医药费 2 诊查费 3 治疗费 4 其他 在发票上显示 类别名称+对应金额

		dataMap.put("aae019", aae019);// 合计
		dataMap.put("upperNumbers", upperNumbers);// 合计大写
		dataMap.put("aae020", aae020);// 医保记账金额
		dataMap.put("aae024", aae024);// 个人缴费金额

		list.add(dataMap);
		billReportData.put("kab3DTO", list);
	}

}
