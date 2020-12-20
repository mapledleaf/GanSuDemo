package com.powersi.biz.medicare.inhospital.service.db;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.powersi.comm.utils.UtilFunc;

/**
 * 封装KC24
 * 
 * @author chenxing
 *
 */
@Service
public class Kc24EncapServiceImpl implements Kc24EncapService {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> loadKc24Info(List kc21List, List kc22List, List kc27List) {
		BigDecimal akc264 = new BigDecimal(0.00d), // 医疗费总额
				ake202 = new BigDecimal(0.00d), // 基本医疗费用
				ake080 = new BigDecimal(0.00d), // 起付线
				ake204 = new BigDecimal(0.00d), // 转外自理费用
				ake034 = new BigDecimal(0.00d), // 个人账户支出
				ake206 = new BigDecimal(0.00d), // 个人现金支付
				ake039 = new BigDecimal(0.00d), // 基本医疗统筹基金支付
				ake041 = new BigDecimal(0.00d), // 城乡居民统筹基金支付
				ake209 = new BigDecimal(0.00d), // 基本医疗大病基金支付
				ake210 = new BigDecimal(0.00d), // 城乡居民大病基金支付
				ake035 = new BigDecimal(0.00d), // 城乡居民大病基金支付
				ame001 = new BigDecimal(0.00d), // 生育基金支出
				ame002 = new BigDecimal(0.00d), // 生育垫付基金支付
				ale001 = new BigDecimal(0.00d), // 工伤基金支出
				aje001 = new BigDecimal(0.00d), // 失业基金支出??
				ake024 = new BigDecimal(0.00d), // 家属基金支出
				ake026 = new BigDecimal(0.00d), // 企业补充医疗基金支出
				ake214 = new BigDecimal(0.00d), // 意外伤害基金支付
				ake215 = new BigDecimal(0.00d), // 民政补助基金支付
				ake216 = new BigDecimal(0.00d), // 军残基金支付??
				ake043 = new BigDecimal(0.00d), // 离休专项医疗基金
				ake042 = new BigDecimal(0.00d), // 老红军专项医疗基金支出??
				ake032 = new BigDecimal(0.00d), // 二乙医疗专项医疗基金支出
				ake029 = new BigDecimal(0.00d), // 大额救助医疗基金支出
				ake072 = new BigDecimal(0.00d), // 其他一次性补助项目??
				ake222 = new BigDecimal(0.00d), // 医院垫付金额
				ake223 = new BigDecimal(0.00d), // 中心支付金额
				akc228 = new BigDecimal(0.00d), // 自付金额
				ake051 = new BigDecimal(0.00d), // 自费金额
				ake233 = new BigDecimal(0.00d), // 门诊统筹申报费用
				ake232 = new BigDecimal(0.00d), // 统筹段个人自付
				ake226 = new BigDecimal(0.00d), // 大病段个人自付
				ake234 = new BigDecimal(0.00d), // 大病段大病基金支付
				ake229 = new BigDecimal(0.00d), // 政策部分自费
				ake230 = new BigDecimal(0.00d), // 段内个人自付
				ake231 = new BigDecimal(0.00d);// 转外自理费用

		BigDecimal ake047 = new BigDecimal(0.00d), // 西药费
				ake050 = new BigDecimal(0.00d), // 中成药费
				ake049 = new BigDecimal(0.00d), // 中草药费用
				ake030 = new BigDecimal(0.00d), // 材料费
				alc136 = new BigDecimal(0.00d), // 治疗费
				ake046 = new BigDecimal(0.00d), // 输血费
				ake016 = new BigDecimal(0.00d), // 输氧费
				ake083 = new BigDecimal(0.00d), // 诊察费
				ake084 = new BigDecimal(0.00d), // 特治费
				alc113 = new BigDecimal(0.00d), // 护理费
				ake040 = new BigDecimal(0.00d), // 检查费
				ake037 = new BigDecimal(0.00d), // 化验费
				ake085 = new BigDecimal(0.00d), // 特检费
				ake045 = new BigDecimal(0.00d), // 手术费
				ake079 = new BigDecimal(0.00d), // 麻醉费
				ake086 = new BigDecimal(0.00d), // 医疗服务费
				ake087 = new BigDecimal(0.00d), // 床位费
				ake088 = new BigDecimal(0.00d), // 内置材料
				ake073 = new BigDecimal(0.00d), // 新生儿费用
				ake089 = new BigDecimal(0.00d), // 急诊费
				ake090 = new BigDecimal(0.00d), // 药店药费
				ake054 = new BigDecimal(0.00d), // 康复治疗费
				ake055 = new BigDecimal(0.00d), // 医疗康复
				ake056 = new BigDecimal(0.00d), // 职业社会康复
				ake033 = new BigDecimal(0.00d), // 放射费
				ake057 = new BigDecimal(0.00d), // 抢救费
				ake058 = new BigDecimal(0.00d), // 辅助器具鉴定费
				ake059 = new BigDecimal(0.00d), // 超费(限门诊)
				ake060 = new BigDecimal(0.00d), // 辅助器具
				ake025 = new BigDecimal(0.00d), // 化疗费
				ake009 = new BigDecimal(0.00d), // CT费
				ake011 = new BigDecimal(0.00d), // 核磁费
				ake012 = new BigDecimal(0.00d), // B超费
				ake018 = new BigDecimal(0.00d), // 注射费
				ake019 = new BigDecimal(0.00d), // 透析费
				ake071 = new BigDecimal(0.00d), // 接生费
				ake078 = new BigDecimal(0.00d), // 家庭病床(出诊费)
				ake061 = new BigDecimal(0.00d), // 计划生育手术费
				ake048 = new BigDecimal(0.00d), // 伙食费
				ake062 = new BigDecimal(0.00d), // 工伤保存劳鉴费
				ake044 = new BigDecimal(0.00d);// 其它费 +保健品
		String aaa157 = null;// 基金编号
		String bka432 = null;// 分类标志
		String aka002 = null;// 政策明细编码
		String bka006 = ((Map) kc21List.get(0)).get("bka006").toString();// 待遇类型
		String aae140 = ((Map) kc21List.get(0)).get("aae140").toString();// 险种
		String aaz217 = ((Map) kc21List.get(0)).get("aaz217").toString();// 就医登记号
		String bka001 = ((Map) kc22List.get(0)).get("bka001").toString();// 费用批次号
		BigDecimal aae019;
		for (int i = 0; i < kc27List.size(); i++) {
			Map pay = (Map) kc27List.get(i);
			aka002 = UtilFunc.getString(pay, "aka002");
			aaa157 = UtilFunc.getString(pay, "aaa157");
			bka432 = UtilFunc.getString(pay, "bka432");
			String aae019Str = UtilFunc.getString(pay, "aae019");
			aae019 = new BigDecimal(!"".equals(aae019Str)?aae019Str:"0.0");

			akc264 = akc264.add(aae019);
			if ((listDeclare.indexOf(aka002) >= 0) && lisTreatmentType.indexOf(bka006) >= 0) {
				ake202 = ake202.add(aae019);
				if ("C001".equals(aka002) || "C002".equals(aka002) || "C003".equals(aka002)) {
					if ("999".equals(aaa157) || "003".equals(aaa157)) {
						ake232 = ake232.add(aae019);
					}
				}
				if ("999".equals(aaa157) || "003".equals(aaa157)) {
					ake230 = ake230.add(aae019);
				}
				if ("D001".equals(aka002) || "D002".equals(aka002) || "D003".equals(aka002)) {
					if ("201".equals(aaa157)) {
						ake234 = ake234.add(aae019);
					}
					if ("999".equals(aaa157) || "003".equals(aaa157)) {
						ake226 = ake226.add(aae019);
					}
				}
			}
			if ("102".equals(bka432) && ("999".equals(aaa157) || "003".equals(aaa157))
					&& lisTreatmentType.indexOf(bka006) >= 0) {
				ake229 = ake229.add(aae019);
			}
			if ("S01".equals(aka002) || "S02".equals(aka002)) {
				ake080 = ake080.add(aae019);
			}
			if ("S00".equals(aka002)) {
				ake204 = ake204.add(aae019);
				ake231 = ake231.add(aae019);
			}
			if ("E001".equals(aka002)) {
				akc228 = akc228.add(aae019);
			}
			if ("101".equals(bka432) && ("999".equals(aaa157) || "003".equals(aaa157))
					&& lisTreatmentType.indexOf(bka006) >= 0) {
				ake051 = ake051.add(aae019);
			}

			if ("001".equals(aaa157)) {
				ake039 = ake039.add(aae019);
				ake233 = ake233.add(aae019);
			} else if ("003".equals(aaa157)) {
				ake034 = ake034.add(aae019);
			} else if ("201".equals(aaa157)) {
				ake209 = ake209.add(aae019);
				ake029 = ake029.add(aae019);
			} else if ("202".equals(aaa157)) {
				ake043 = ake043.add(aae019);
			} else if ("204".equals(aaa157)) {
				ake024 = ake024.add(aae019);
			} else if ("205".equals(aaa157)) {
				ake032 = ake032.add(aae019);
			} else if ("301".equals(aaa157) || "302".equals(aaa157) || "303".equals(aaa157)) {
				ake035 = ake035.add(aae019);
			} else if ("306".equals(aaa157)) {
				ake026 = ake026.add(aae019);
			} else if ("401".equals(aaa157)) {
				ake215 = ake215.add(aae019);
			} else if ("501".equals(aaa157)) {
				ale001 = ale001.add(aae019);
			} else if ("511".equals(aaa157)) {
				ame001 = ame001.add(aae019);
			} else if ("519".equals(aaa157)) {
				ame002 = ame002.add(aae019);
			} else if ("801".equals(aaa157)) {
				ake041 = ake041.add(aae019);
			} else if ("802".equals(aaa157)) {
				ake214 = ake214.add(aae019);
			} else if ("803".equals(aaa157)) {
				ake210 = ake210.add(aae019);
			} else if ("996".equals(aaa157)) {
				ake222 = ake222.add(aae019);
			} else if ("998".equals(aaa157)) {
				ake223 = ake223.add(aae019);
			} else if ("999".equals(aaa157)) {
				ake206 = ake206.add(aae019);
			}
		}

		BigDecimal aae019New;
		for (int i = 0; i < kc22List.size(); i++) {
			Map feeFin = (Map) kc22List.get(i);
			String aka063 = String.valueOf(feeFin.get("aka063"));
			String aae019NewStr = UtilFunc.getString(feeFin,"aae019");
			aae019New = new BigDecimal(!"".equals(aae019NewStr)?aae019NewStr:"0.0");
			if (aka063.equals("001")) {
				ake047 = ake047.add(aae019New);
			} else if (aka063.equals("002")) {
				ake050 = ake050.add(aae019New);
			} else if (aka063.equals("003")) {
				ake049 = ake049.add(aae019New);
			} else if (aka063.equals("004")) {
				ake030 = ake030.add(aae019New);
			} else if (aka063.equals("005")) {
				alc136 = alc136.add(aae019New);
			} else if (aka063.equals("006")) {
				ake046 = ake046.add(aae019New);
			} else if (aka063.equals("007")) {
				ake016 = ake016.add(aae019New);
			} else if (aka063.equals("008")) {
				ake083 = ake083.add(aae019New);
			} else if (aka063.equals("009")) {
				ake084 = ake084.add(aae019New);
			} else if (aka063.equals("010")) {
				alc113 = alc113.add(aae019New);
			} else if (aka063.equals("011")) {
				ake040 = ake040.add(aae019New);
			} else if (aka063.equals("012")) {
				ake037 = ake037.add(aae019New);
			} else if (aka063.equals("013")) {
				ake085 = ake085.add(aae019New);
			} else if (aka063.equals("014")) {
				ake045 = ake045.add(aae019New);
			} else if (aka063.equals("015")) {
				ake079 = ake079.add(aae019New);
			} else if (aka063.equals("016")) {
				ake086 = ake086.add(aae019New);
			} else if (aka063.equals("017")) {
				ake087 = ake087.add(aae019New);
			} else if (aka063.equals("018")) {
				ake088 = ake088.add(aae019New);
			} else if (aka063.equals("019")) {
				ake073 = ake073.add(aae019New);
			} else if (aka063.equals("020")) {
				ake089 = ake089.add(aae019New);
			} else if (aka063.equals("021")) {
				ake090 = ake090.add(aae019New);
			} else if (aka063.equals("022")) {
				ake054 = ake054.add(aae019New);
			} else if (aka063.equals("023")) {
				ake055 = ake055.add(aae019New);
			} else if (aka063.equals("024")) {
				ake056 = ake056.add(aae019New);
			} else if (aka063.equals("025")) {
				ake033 = ake033.add(aae019New);
			} else if (aka063.equals("026")) {
				ake057 = ake057.add(aae019New);
			} else if (aka063.equals("027")) {
				ake058 = ake058.add(aae019New);
			} else if (aka063.equals("028")) {
				ake059 = ake059.add(aae019New);
			} else if (aka063.equals("029")) {
				ake060 = ake060.add(aae019New);
			} else if (aka063.equals("030")) {
				ake025 = ake025.add(aae019New);
			} else if (aka063.equals("031")) {
				ake009 = ake009.add(aae019New);
			} else if (aka063.equals("032")) {
				ake011 = ake011.add(aae019New);
			} else if (aka063.equals("033")) {
				ake012 = ake012.add(aae019New);
			} else if (aka063.equals("034")) {
				ake018 = ake018.add(aae019New);
			} else if (aka063.equals("035")) {
				ake019 = ake019.add(aae019New);
			} else if (aka063.equals("036")) {
				ake071 = ake071.add(aae019New);
			} else if (aka063.equals("037")) {
				ake078 = ake078.add(aae019New);
			} else if (aka063.equals("038")) {
				ake061 = ake061.add(aae019New);
			} else if (aka063.equals("040")) {
				ake048 = ake048.add(aae019New);
			} else if (aka063.equals("041")) {
				if (aae140.equals("410")) {
					ake062 = ake062.add(aae019New);
				}
			} else if (aka063.equals("099") || aka063.equals("F19")) {
				ake044 = ake044.add(aae019New);
			}
		}
		List<Map> kc21Info = new ArrayList<>();
		Map mKc24 = new HashMap();
		mKc24.put("aaz217", aaz217);
		mKc24.put("bka001", bka001);
		mKc24.put("akc264", akc264);
		mKc24.put("ake202", ake202);
		mKc24.put("ake080", ake080);
		mKc24.put("ake204", ake204);
		mKc24.put("ake034", ake034);
		mKc24.put("ake206", ake206);
		mKc24.put("ake039", ake039);
		mKc24.put("ake041", ake041);
		mKc24.put("ake209", ake209);
		mKc24.put("ake210", ake210);
		mKc24.put("ake035", ake035);
		mKc24.put("ame001", ame001);
		mKc24.put("ame002", ame002);
		mKc24.put("ale001", ale001);
		mKc24.put("aje001", aje001);
		mKc24.put("ake024", ake024);
		mKc24.put("ake026", ake026);
		mKc24.put("ake214", ake214);
		mKc24.put("ake215", ake215);
		mKc24.put("ake216", ake216);
		mKc24.put("ake043", ake043);
		mKc24.put("ake042", ake042);
		mKc24.put("ake032", ake032);
		mKc24.put("ake029", ake029);
		mKc24.put("ake072", ake072);
		mKc24.put("ake222", ake222);
		mKc24.put("ake223", ake223);
		mKc24.put("akc228", akc228);
		mKc24.put("ake051", ake051);
		mKc24.put("ake233", ake233);
		mKc24.put("ake232", ake232);
		mKc24.put("ake226", ake226);
		mKc24.put("ake234", ake234);
		mKc24.put("ake229", ake229);
		mKc24.put("ake230", ake230);
		mKc24.put("ake231", ake231);
		mKc24.put("ake047", ake047);
		mKc24.put("ake050", ake050);
		mKc24.put("ake049", ake049);
		mKc24.put("ake030", ake030);
		mKc24.put("alc136", alc136);
		mKc24.put("ake046", ake046);
		mKc24.put("ake016", ake016);
		mKc24.put("ake083", ake083);
		mKc24.put("ake084", ake084);
		mKc24.put("alc113", alc113);
		mKc24.put("ake040", ake040);
		mKc24.put("ake037", ake037);
		mKc24.put("ake085", ake085);
		mKc24.put("ake045", ake045);
		mKc24.put("ake079", ake079);
		mKc24.put("ake086", ake086);
		mKc24.put("ake087", ake087);
		mKc24.put("ake088", ake088);
		mKc24.put("ake073", ake073);
		mKc24.put("ake089", ake089);
		mKc24.put("ake090", ake090);
		mKc24.put("ake054", ake054);
		mKc24.put("ake055", ake055);
		mKc24.put("ake056", ake056);
		mKc24.put("ake033", ake033);
		mKc24.put("ake057", ake057);
		mKc24.put("ake058", ake058);
		mKc24.put("ake059", ake059);
		mKc24.put("ake060", ake060);
		mKc24.put("ake025", ake025);
		mKc24.put("ake009", ake009);
		mKc24.put("ake011", ake011);
		mKc24.put("ake012", ake012);
		mKc24.put("ake018", ake018);
		mKc24.put("ake019", ake019);
		mKc24.put("ake071", ake071);
		mKc24.put("ake078", ake078);
		mKc24.put("ake061", ake061);
		mKc24.put("ake048", ake048);
		mKc24.put("ake062", ake062);
		mKc24.put("ake044", ake044);
		kc21Info.add(mKc24);
		return kc21Info;
	}
}
