package com.powersi.ssm.biz.medicare.api.service.mcce;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.pojo.ListResultDTO;
import com.powersi.biz.medicare.comm.service.MoneyUtil;
import com.powersi.biz.medicare.query.pojo.BizQueryDTO;
import com.powersi.biz.medicare.query.service.api.BizQueryApiService;
import com.powersi.comm.utils.UtilFunc;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.ssm.biz.medicare.common.util.ApiRemoteCallWebProcessor;

@Service
public class BizQueryApiServiceImpl implements BizQueryApiService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3441894281484059221L;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ListResult queryAllBizInfoYdjy(BizQueryDTO bizQueryDTO) {
		bizQueryDTO.setBiz_or_fee_or_detail("biz");
		Object[] paramObj = new Object[] { bizQueryDTO };
		List<Map> list = ApiRemoteCallWebProcessor.processApi("Remote_BIZC200301", paramObj);
		for (Map map : list) {
			String bka035 = UtilFunc.getString(map, "bka035");
			map.put("bka035", CodetableMapping.getDisplayValue("bka035", bka035, bka035));
			String aac004 = UtilFunc.getString(map, "aac004");
			map.put("aac004", CodetableMapping.getDisplayValue("aac004", aac004, aac004));
			String bka006 = UtilFunc.getString(map, "bka006");
			map.put("bka006", CodetableMapping.getDisplayValue("bka006", bka006, bka006));
			String bac001 = UtilFunc.getString(map, "bac001");
			map.put("bac001", CodetableMapping.getDisplayValue("bac001", bac001, bac001));
		}
		return ListResultDTO.newInstance().setList(list).setCount(list.size());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> queryClinicBizFeeInfoYdjy(BizQueryDTO bizQueryDTO) {
		bizQueryDTO.setBiz_or_fee_or_detail("fee");
		Object[] paramObj = new Object[] { bizQueryDTO };
		return ApiRemoteCallWebProcessor.processApi("Remote_BIZC200301", paramObj);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map queryFeeDetailTableYdjy(BizQueryDTO bizQueryDTO) {
		bizQueryDTO.setBiz_or_fee_or_detail("detail");
		Object[] paramObj = new Object[] { bizQueryDTO };
		List<Map> resultList = ApiRemoteCallWebProcessor.processApi("Remote_BIZC200301", paramObj);
		return resultList != null && !resultList.isEmpty() ? resultList.get(0) : null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> settlementReport(BizQueryDTO bizQueryDTO) {
		bizQueryDTO.setSettle_or_fund("settle");
		Object[] paramObj = new Object[] { bizQueryDTO };
		List<Map> resultList = ApiRemoteCallWebProcessor.processApi("Remote_BIZC200101", paramObj);
		return this.handleData(bizQueryDTO.getAaz217(), resultList);
	}

	/**
	 * 处理结算数据
	 * 
	 * @param aaz217
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<String, Object> handleData(String aaz217, List<Map> list) {
		Map<String, Object> resultMap = new HashMap<>();
		Map map = list.get(0);
		List infolist = (List) map.get("info");
		if (aaz217.startsWith("po")) { // 跨省
			resultMap.put("data1", infolist);
			if (infolist != null && !infolist.isEmpty()) {
				Map tmp = (Map) infolist.get(0);
				
				String beginDate = UtilFunc.getString(tmp, "aae030");
				String endDate = UtilFunc.getString(tmp, "aae031");
				String birthday = UtilFunc.getString(tmp, "aac006");
				tmp.put("aae030", beginDate.length() >= 10 ? beginDate.substring(0, 10) : beginDate);
				tmp.put("aae031", endDate.length() >= 10 ? endDate.substring(0, 10) : endDate);
				tmp.put("aac006", birthday.length() >= 10 ? birthday.substring(0, 10) : birthday);

				// 总费用
				String akc264 = (String) tmp.getOrDefault("akc264", "0.0");
				
				String akc228 = (String) tmp.getOrDefault("akc228", "0.0");
				String akc268 = (String) tmp.getOrDefault("akc268", "0.0");
				// 自费费用
				String akc253 = (String) tmp.getOrDefault("akc253", "0.0");
				
				// 统筹内费用akc264-akc253
				BigDecimal ake171NumShow = new BigDecimal(akc264).subtract(new BigDecimal(akc253)).setScale(2, BigDecimal.ROUND_HALF_UP);
				// 其中部分政策自付akc228+akc268
				BigDecimal akc228NumShow = new BigDecimal(akc228).add(new BigDecimal(akc268)).setScale(2, BigDecimal.ROUND_HALF_UP);
				
				tmp.put("ake171", ake171NumShow.toPlainString());
				tmp.put("akc228", akc228NumShow.toPlainString());
			}
		} else if (aaz217.startsWith("yd")) { // 省内
			if (infolist != null && !infolist.isEmpty()) {
				Map tmp = (Map) infolist.get(0);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				tmp.put("ake118", sdf.format(new Date()));// 打印时间
				String beginDate = UtilFunc.getString(tmp, "aae030");
				String endDate = UtilFunc.getString(tmp, "aae031");
				String birthday = UtilFunc.getString(tmp, "aac006");
				tmp.put("begin_date", beginDate.length() >= 10 ? beginDate.substring(0, 10) : beginDate);
				tmp.put("end_date", endDate.length() >= 10 ? endDate.substring(0, 10) : endDate);
				tmp.put("birthday", birthday.length() >= 10 ? birthday.substring(0, 10) : birthday);
			}
			List seglist = (List) map.get("seg");
			List scene = (List) map.get("scene");
			List fee = (List) map.get("fee");
			List data2 = getData2Scene(scene);
			List data3 = getData3ISeg(seglist);
			List data4 = getData4ISeg(data3);
			List data5 = getData5ISeg(seglist);
			List data7 = getData7Scene(fee);// 费用信息
			List data8 = getData8ISeg(seglist);// 超标费用
			List data9 = getData9ISeg(seglist);// 费用统计
			resultMap.put("data1", infolist);
			resultMap.put("data2", data2);
			resultMap.put("data3", data3);
			resultMap.put("data4", data4);
			resultMap.put("data5", data5);
			resultMap.put("data7", data7);
			resultMap.put("data8", data8);
			resultMap.put("data9", data9);
		}
		return resultMap;
	}

	/**
	 * 加载费用信息
	 * 
	 * @param fee
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<?> getData7Scene(List<?> fee) {
		List data7 = fee;
		String stat_name = "";// 政策自付费用类别
		double zfy = 0.00;// 政策自付总费用
		double blzf = 0.00;// 政策自付费用
		double blzf_all = 0.00;// 政策自付总费用
		double zfy_all = 0.00;// 总费用合计

		String stat_name1 = "";// 完全政策自付费用类别
		double wqzfy = 0.00;// 完全政策自付总费用
		double wqzf = 0.00;// 完全政策自付费用
		double wqzf_all = 0.00;// 总费用合计
		double wqzfy_all = 0.00;// 完全政策自付合计

		Map m3n = new HashMap();
		List data7_n = new ArrayList();
		if (null != data7 && data7.size() > 0) {
			for (int i = 0; i < data7.size(); i++) {
				Map m2n = new HashMap();
				Map<String, Object> m = (Map<String, Object>) data7.get(i);
				if (Double.parseDouble(m.get("blzf").toString()) == 0
						&& Double.parseDouble(m.get("qzf").toString()) == 0) {
					continue;
				}

				// 部分政策自付
				if (m.containsKey("blzf") && !m.get("blzf").toString().isEmpty()
						&& Double.parseDouble(m.get("blzf").toString()) != 0) {
					stat_name = (String) m.get("stat_name");
					zfy = Double.parseDouble(m.get("zfy").toString());
					blzf = Double.parseDouble(m.get("blzf").toString());
					zfy_all = zfy;
					blzf_all += blzf;
				}
				if (m.containsKey("qzf") && !m.get("qzf").toString().isEmpty()
						&& Double.parseDouble(m.get("qzf").toString()) != 0) {
					stat_name1 = (String) m.get("stat_name");
					wqzfy = Double.parseDouble(m.get("zfy").toString());
					wqzf = Double.parseDouble(m.get("qzf").toString());
					wqzfy_all = wqzfy;
					wqzf_all += wqzf;
				}
				m2n.put("stat_name", stat_name);
				m2n.put("zfy", zfy);
				m2n.put("blzf", blzf);
				m2n.put("zfy_all", zfy_all);

				m2n.put("stat_name1", stat_name1);
				m2n.put("wqzfy", wqzfy);
				m2n.put("wqzf", wqzf);
				m3n.put("wqzfy_all", wqzfy_all);
				data7_n.add(m2n);
			}
			// ***********政策自付总费用**********
			m3n.put("blzf_all", blzf_all);
			// **********完全政策自付总费用**********
			m3n.put("wqzf_all", wqzf_all);
			data7_n.add(m3n);

		}
		return data7_n;

	}

	/**
	 * 加载费用信息
	 * 
	 * @param sceneList
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List getData2Scene(List sceneList) {
		List data2 = sceneList;
		String inhosp_count = "0";
		String declare_pay = "0.00";
		String total_pay = "0.00";
		String start_pay = "0.00";
		String fund_pay = "0.00";
		String all_self_pay = "0.00";
		String part_self_pay = "0.00";
		String seg_self_pay = "0.00";
		String additional_pay = "0.00";
		String official_pay = "0.00";
		double D0010 = 0.00, D2020 = 0.00, D5110 = 0.00, D8010 = 0.00, D2010 = 0.00, D8030 = 0.00;
		Map m2n = new HashMap();
		List data2_n = new ArrayList();
		if (null != data2 && data2.size() > 0) {
			for (int i = 0; i < data2.size(); i++) {
				Map<String, Object> m = (Map<String, Object>) data2.get(i);

				if ("inhosp_count".equals(m.get("scene_code"))) {
					inhosp_count = m.get("scene_value").toString();
				}
				if ("year_cumulate_ih".equals(m.get("scene_code"))
						|| m.get("scene_code").toString().indexOf("year_cumulate_ih") > -1) {
					declare_pay = m.get("scene_value").toString();
				}
				if ("inhosp_fee".equals(m.get("scene_code"))) {
					total_pay = m.get("scene_value").toString();
				}
				if ("year_sill_ih".equals(m.get("scene_code"))) {
					start_pay = m.get("scene_value").toString();
				}
				if ("D0010".equals(m.get("scene_code"))) {
					D0010 = Double.parseDouble(m.get("scene_value").toString());
				}
				if ("D2020".equals(m.get("scene_code"))) {
					D2020 = Double.parseDouble(m.get("scene_value").toString());
				}
				if ("D5110".equals(m.get("scene_code"))) {
					D5110 = Double.parseDouble(m.get("scene_value").toString());
				}
				if ("D8010".equals(m.get("scene_code"))) {
					D8010 = Double.parseDouble(m.get("scene_value").toString());
				}
				if ("ZF_A".equals(m.get("scene_code"))) {
					all_self_pay = m.get("scene_value").toString();
				}
				if ("ZF_P".equals(m.get("scene_code"))) {
					part_self_pay = m.get("scene_value").toString();
				}
				if ("ZF_S".equals(m.get("scene_code"))) {
					seg_self_pay = m.get("scene_value").toString();
				}
				if ("D2010".equals(m.get("scene_code"))) {
					D2010 = Double.parseDouble(m.get("scene_value").toString());
				}
				if ("D8030".equals(m.get("scene_code"))) {
					D8030 = Double.parseDouble(m.get("scene_value").toString());
				}
				if ("D3010".equals(m.get("scene_code"))) {
					official_pay = m.get("scene_value").toString();
				}
			}
			fund_pay = String.valueOf(D0010 + D2020 + D5110 + D8010);
			official_pay = String.valueOf(D2010 + D8030);

			m2n.put("inhosp_count", inhosp_count);
			m2n.put("declare_pay", declare_pay);
			m2n.put("total_pay", total_pay);
			m2n.put("start_pay", start_pay);
			m2n.put("fund_pay", fund_pay);
			m2n.put("all_self_pay", all_self_pay);
			m2n.put("part_self_pay", part_self_pay);
			m2n.put("seg_self_pay", seg_self_pay);
			m2n.put("additional_pay", additional_pay);
			m2n.put("official_pay", official_pay);
			data2_n.add(m2n);
		}
		return data2_n;

	}

	/**
	 * 加载支付信息
	 * 
	 * @param seglist
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List getData3ISeg(List seglist) {
		List data3 = seglist;
		double cash_pay = 0.0d;
		double total_pay = 0.0d;
		double base_pay = 0.0d;
		double additional_pay = 0.0d;
		double offi_tsbt = 0.0d;
		double tc_pay = 0.0d;
		//double official_pay;
		if (null != data3 && !data3.isEmpty()) {
			for (int i = 0; i < data3.size(); i++) {
				Map<String, Object> m = (Map<String, Object>) data3.get(i);

				cash_pay = Double.parseDouble(m.get("cash_pay").toString());
				total_pay = Double.parseDouble(m.get("total_pay").toString());

				base_pay = Double.parseDouble(m.get("base_pay").toString());
				additional_pay = Double.parseDouble(m.get("additional_pay").toString());
				offi_tsbt = Double.parseDouble(m.get("offi_tsbt").toString());
				//official_pay = Double.parseDouble(m.get("official_pay").toString());
				tc_pay = base_pay + additional_pay + offi_tsbt;

				String compute_1 = "0.00%";
				String compute_3 = "0.00%";
				String compute_4 = "0.00%";
				if (total_pay > 0) {
					BigDecimal b1 = BigDecimal.valueOf((cash_pay / total_pay) * 100);
					double f1 = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					compute_1 = "" + f1 + "%";

					BigDecimal b2 = BigDecimal.valueOf((tc_pay / total_pay) * 100);
					double f2 = b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					compute_3 = "" + f2 + "%";

					BigDecimal b3 = BigDecimal.valueOf((tc_pay / total_pay) * 100);
					double f3 = b3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					compute_4 = "" + f3 + "%";
				}

				m.put("compute_1", compute_1);
				m.put("compute_fund", String.valueOf(tc_pay));
				m.put("compute_3", compute_3);
				m.put("compute_4", compute_4);

			}
		}
		return data3;

	}

	/**
	 * 加载费用信息
	 * 
	 * @param seglist
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List getData8ISeg(List seglist) {
		List data3 = seglist;
		double bka363 = 0.00;// 超标费用合计
		double bka364 = 0.00;// 超标费用政策自付
		double bka365 = 0.00;// 项目内超标费

		if (null != data3 && !data3.isEmpty()) {
			for (int i = 0; i < data3.size(); ) {
				Map<String, Object> m = (Map<String, Object>) data3.get(i);
				if (m.containsKey("policy_self_pay")) {
					bka364 += Double.parseDouble(m.get("policy_self_pay").toString());
				}
				if (m.containsKey("seg_self_pay")) {
					bka365 += Double.parseDouble(m.get("seg_self_pay").toString());
				}
				if (m.containsKey("self_pay")) {
					bka363 += Double.parseDouble(m.get("self_pay").toString());
				}
				m.put("bka364", bka364);
				m.put("bka365", bka365);
				m.put("bka363", bka363);
				data3.add(m);
				break;
			}

		}
		return data3;

	}

	/**
	 * 费用分类后的汇总信息
	 * 
	 * @param data3
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List getData4ISeg(List data3) {
		List lst = new ArrayList();
		Map map = new HashMap();
		double compute_cash = 0.00;
		double compute_fund_total = 0.00;
		double compute_official = 0.00;
		double compute_all = 0.00;
		for (int i = 0; i < data3.size(); i++) {
			Map tmpMap = new HashMap();
			tmpMap = (Map) data3.get(i);
			if (tmpMap.get("cash_pay") != null && !"".equals(tmpMap.get("cash_pay"))) {
				compute_cash += Double.parseDouble(tmpMap.get("cash_pay").toString());
			}
			if (tmpMap.get("compute_fund") != null && !"".equals(tmpMap.get("compute_fund"))) {
				compute_fund_total += Double.parseDouble(tmpMap.get("compute_fund").toString());
			}
			if (tmpMap.get("official_pay") != null && !"".equals(tmpMap.get("official_pay"))) {
				compute_official += Double.parseDouble(tmpMap.get("official_pay").toString());
			}
			if (tmpMap.get("total_pay") != null && !"".equals(tmpMap.get("total_pay"))) {
				compute_all += Double.parseDouble(tmpMap.get("total_pay").toString());
			}

		}
		DecimalFormat doubleFormat = new DecimalFormat("#0.00");
		String compute_cash_Str = doubleFormat.format(compute_cash);
		String compute_fund_total_Str = doubleFormat.format(compute_fund_total);
		String compute_official_Str = doubleFormat.format(compute_official);
		String compute_all_Str = doubleFormat.format(compute_all);
		map.put("compute_cash", compute_cash_Str);
		map.put("compute_fund_total", compute_fund_total_Str);
		map.put("compute_official", compute_official_Str);
		map.put("compute_all", compute_all_Str);
		lst.add(map);
		return lst;
	}

	/**
	 * 加载费用信息
	 * 
	 * @param seglist
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getData9ISeg(List seglist) {
		List lst = new ArrayList();
		Map map = new HashMap();
		double bkp351 = 0.0;// 本次医疗费用
		double bkp352 = 0.0;// 个人自付金额
		double bkp353 = 0.0;// 个人账户支付金额
		double bkp354 = 0.0;// 个人现金支付金额
		double bkp355 = 0.0;// 基本统筹基金支付
		double bkp356 = 0.0;// 大病互助基金支付
		double bkp357 = 0.0;// 公务员补助支付
		double bkp358 = 0.0;// 公务员特殊补贴
		//double bkp359 = 0.0;// 医院支付
		double cash_pay = 0.0, acct_pay = 0.0, base_pay = 0.0, additional_pay = 0.0, official_pay = 0.0,
				offi_tsbt = 0.0;
		if (null != seglist && seglist.size() > 0) {
			for (int i = 0; i < seglist.size(); i++) {
				Map tmpMap = new HashMap();

				tmpMap = (Map<String, Object>) seglist.get(i);
				if (tmpMap.get("total_pay") != null && !"".equals(tmpMap.get("total_pay"))) {
					bkp351 = Double.parseDouble(tmpMap.get("total_pay").toString());
				}

				if (tmpMap.get("seg_self_pay") != null && !"".equals(tmpMap.get("seg_self_pay"))) {
					cash_pay = Double.parseDouble(tmpMap.get("seg_self_pay").toString());
				}

				if (tmpMap.get("acct_pay") != null && !"".equals(tmpMap.get("acct_pay"))) {
					acct_pay += Double.parseDouble(tmpMap.get("acct_pay").toString());
				}
				if (tmpMap.get("fund_pay") != null && !"".equals(tmpMap.get("fund_pay"))) {
					base_pay = Double.parseDouble(tmpMap.get("fund_pay").toString());
				}
				if (tmpMap.get("additional_pay") != null && !"".equals(tmpMap.get("additional_pay"))) {
					additional_pay += Double.parseDouble(tmpMap.get("additional_pay").toString());
				}
				if (tmpMap.get("official_pay") != null && !"".equals(tmpMap.get("official_pay"))) {
					official_pay += Double.parseDouble(tmpMap.get("official_pay").toString());
				}
				if (tmpMap.get("offi_tsbt") != null && !"".equals(tmpMap.get("offi_tsbt"))) {
					offi_tsbt += Double.parseDouble(tmpMap.get("offi_tsbt").toString());
				}

			}
		}
		DecimalFormat doubleFormat = new DecimalFormat("#0.00");

		String bkp351Str = doubleFormat.format(bkp351);
		String bkp351_dx = MoneyUtil.toChinese(bkp351Str);

		bkp352 = cash_pay;
		String bkp352Str = doubleFormat.format(bkp352);
		String bkp352_dx = MoneyUtil.toChinese(bkp352Str);

		bkp353 = acct_pay;
		String bkp353Str = doubleFormat.format(bkp353);
		String bkp353_dx = MoneyUtil.toChinese(bkp353Str);

		bkp354 = cash_pay - acct_pay;
		String bkp354Str = doubleFormat.format(bkp354);
		String bkp354_dx = MoneyUtil.toChinese(bkp354Str);

		bkp355 = base_pay;
		String bkp355Str = doubleFormat.format(bkp355);
		String bkp355_dx = MoneyUtil.toChinese(bkp355Str);

		bkp356 = additional_pay;
		String bkp356Str = doubleFormat.format(bkp356);
		String bkp356_dx = MoneyUtil.toChinese(bkp356Str);

		bkp357 = official_pay;
		String bkp357Str = doubleFormat.format(bkp357);
		String bkp357_dx = MoneyUtil.toChinese(bkp357Str);

		bkp358 = offi_tsbt;
		String bkp358Str = doubleFormat.format(bkp358);
		String bkp358_dx = MoneyUtil.toChinese(bkp358Str);

		map.put("bkp351", bkp351Str);
		map.put("bkp351_dx", bkp351_dx);

		map.put("bkp352", bkp352Str);
		map.put("bkp352_dx", bkp352_dx);

		map.put("bkp353", bkp353Str);
		map.put("bkp353_dx", bkp353_dx);

		map.put("bkp354", bkp354Str);
		map.put("bkp354_dx", bkp354_dx);

		map.put("bkp355", bkp355Str);
		map.put("bkp355_dx", bkp355_dx);

		map.put("bkp356", bkp356Str);
		map.put("bkp356_dx", bkp356_dx);

		map.put("bkp357", bkp357Str);
		map.put("bkp357_dx", bkp357_dx);

		map.put("bkp358", bkp358Str);
		map.put("bkp358_dx", bkp358_dx);

		map.put("bkp359", "");
		map.put("bkp359_dx", "");

		map.put("bkp360", "0");
		String bkp360_dx = MoneyUtil.toChinese("0");

		map.put("bkp360_dx", bkp360_dx);

		map.put("bkp361", bkp354Str);
		map.put("bkp361_dx", bkp354_dx);

		map.put("bkp362", "0");
		String bkp362_dx = MoneyUtil.toChinese("0");

		map.put("bkp362_dx", bkp362_dx);

		lst.add(map);
		return lst;

	}

	/**
	 * 加载费用信息
	 * 
	 * @param seglist
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List getData5ISeg(List seglist) {
		List lst = new ArrayList();
		Map map = new HashMap();
		double bkp351 = 0.0;// 本次医疗费用
		double bkp352 = 0.0;// 个人自付金额
		double bkp353 = 0.0;// 个人账户支付金额
		double bkp354 = 0.0;// 个人现金支付金额
		double bkp355 = 0.0;// 基本统筹基金支付
		double bkp356 = 0.0;// 大病互助基金支付
		double bkp357 = 0.0;// 公务员补助支付
		double bkp358 = 0.0;// 公务员特殊补贴
		//double bkp359 = 0.0;// 医院支付
		double cash_pay = 0.0, acct_pay = 0.0, base_pay = 0.0, additional_pay = 0.0, official_pay = 0.0,
				offi_tsbt = 0.0;
		if (null != seglist && seglist.size() > 0) {
			for (int i = 0; i < seglist.size(); i++) {
				Map tmpMap = new HashMap();

				tmpMap = (Map<String, Object>) seglist.get(i);
				if (tmpMap.get("total_pay") != null && !"".equals(tmpMap.get("total_pay"))) {
					bkp351 += Double.parseDouble(tmpMap.get("total_pay").toString());
				}
				if (tmpMap.get("cash_pay") != null && !"".equals(tmpMap.get("cash_pay"))) {
					cash_pay += Double.parseDouble(tmpMap.get("cash_pay").toString());
				}
				if (tmpMap.get("acct_pay") != null && !"".equals(tmpMap.get("acct_pay"))) {
					acct_pay += Double.parseDouble(tmpMap.get("acct_pay").toString());
				}
				if (tmpMap.get("base_pay") != null && !"".equals(tmpMap.get("base_pay"))) {
					base_pay += Double.parseDouble(tmpMap.get("base_pay").toString());
				}
				if (tmpMap.get("additional_pay") != null && !"".equals(tmpMap.get("additional_pay"))) {
					additional_pay += Double.parseDouble(tmpMap.get("additional_pay").toString());
				}
				if (tmpMap.get("official_pay") != null && !"".equals(tmpMap.get("official_pay"))) {
					official_pay += Double.parseDouble(tmpMap.get("official_pay").toString());
				}
				if (tmpMap.get("offi_tsbt") != null && !"".equals(tmpMap.get("offi_tsbt"))) {
					offi_tsbt += Double.parseDouble(tmpMap.get("offi_tsbt").toString());
				}

			}
		}
		DecimalFormat doubleFormat = new DecimalFormat("#0.00");

		String bkp351Str = doubleFormat.format(bkp351);
		String bkp351_dx = MoneyUtil.toChinese(bkp351Str);

		bkp352 = cash_pay;
		String bkp352Str = doubleFormat.format(bkp352);
		String bkp352_dx = MoneyUtil.toChinese(bkp352Str);

		bkp353 = acct_pay;
		String bkp353Str = doubleFormat.format(bkp353);
		String bkp353_dx = MoneyUtil.toChinese(bkp353Str);

		bkp354 = cash_pay - acct_pay;
		String bkp354Str = doubleFormat.format(bkp354);
		String bkp354_dx = MoneyUtil.toChinese(bkp354Str);

		bkp355 = base_pay;
		String bkp355Str = doubleFormat.format(bkp355);
		String bkp355_dx = MoneyUtil.toChinese(bkp355Str);

		bkp356 = additional_pay;
		String bkp356Str = doubleFormat.format(bkp356);
		String bkp356_dx = MoneyUtil.toChinese(bkp356Str);

		bkp357 = official_pay;
		String bkp357Str = doubleFormat.format(bkp357);
		String bkp357_dx = MoneyUtil.toChinese(bkp357Str);

		bkp358 = offi_tsbt;
		String bkp358Str = doubleFormat.format(bkp358);
		String bkp358_dx = MoneyUtil.toChinese(bkp358Str);

		map.put("bkp351", bkp351Str);
		map.put("bkp351_dx", bkp351_dx);

		map.put("bkp352", bkp352Str);
		map.put("bkp352_dx", bkp352_dx);

		map.put("bkp353", bkp353Str);
		map.put("bkp353_dx", bkp353_dx);

		map.put("bkp354", bkp354Str);
		map.put("bkp354_dx", bkp354_dx);

		map.put("bkp355", bkp355Str);
		map.put("bkp355_dx", bkp355_dx);

		map.put("bkp356", bkp356Str);
		map.put("bkp356_dx", bkp356_dx);

		map.put("bkp357", bkp357Str);
		map.put("bkp357_dx", bkp357_dx);

		map.put("bkp358", bkp358Str);
		map.put("bkp358_dx", bkp358_dx);

		map.put("bkp359", "");
		map.put("bkp359_dx", "");

		map.put("bkp360", "0");
		String bkp360_dx = MoneyUtil.toChinese("0");

		map.put("bkp360_dx", bkp360_dx);

		map.put("bkp361", bkp354Str);
		map.put("bkp361_dx", bkp354_dx);

		map.put("bkp362", "0");
		String bkp362_dx = MoneyUtil.toChinese("0");

		map.put("bkp362_dx", bkp362_dx);

		lst.add(map);
		return lst;

	}
}
