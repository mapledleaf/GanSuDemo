package com.powersi.ssm.biz.medicare.medicarepay.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.powersi.biz.medicare.medicarepay.pojo.AccountDeclareReportResultDTO;
import org.apache.struts2.convention.annotation.Action;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.pojo.ParamDTO;
import com.powersi.biz.medicare.medicarepay.pojo.AccountDeclareQueryDTO;
import com.powersi.biz.medicare.medicarepay.service.MedicarePayEsbService;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.CodetableHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.medicarepay.service.MedicarepayService;
import com.powersi.sys.util.PagerHelper;

/**
 * 结算申报
 *
 * 2018年7月12日上午10:17:54
 *
 * @author lwyao
 *
 */
@SuppressWarnings("serial")
@Action(value = "accountDeclare")
public class AccountDeclareAction extends MedicarepayAction {

	MedicarepayService payService = BeanHelper.getBean(MedicarepayService.class);

	AccountDeclareQueryDTO q;

	/**
	 * 根据拨付中心获取结算申报类型
	 *
	 * @author lwyao
	 * @date 2018年11月14日
	 */
	@SuppressWarnings("unchecked")
	public void getDeclareTypeByDaa027() {
		String daa027 = getParameter("daa027");
		if (UtilFunc.isBlank(daa027)) {
			throw new HygeiaException("daa027(拨付中心)参数不能为空。");
		}
		String codetype = "bkp002_zx_" + daa027;
		Map<String, String> bkp002 = UtilFunc.isNotEmpty(CodetableHelper.get(codetype)) ? CodetableHelper.get(codetype)
				: payService.refreshCenterCodetype("bkp002", daa027).get(codetype);
		try {
			setJSONReturn(bkp002);
		} catch (IOException e) {
			saveError(e);
			e.printStackTrace();
		}
	}

	/**
	 * 查询结算申报汇总报表
	 *
	 * @author lwyao
	 */
	public void querySum() {
		try {
			Map map = new HashMap();
			map.put("aac004","aac004");
			AccountDeclareReportResultDTO acc = new AccountDeclareReportResultDTO();
			acc.setReportHtml("<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><style type=\"text/css\">.b1{white-space-collapsing:preserve;}\n" +
					".t1{border-collapse:collapse;border-spacing:0;}\n" +
					".r1{height:13.5pt;}\n" +
					".r2{height:16.15pt;}\n" +
					".r3{height:24.95pt;}\n" +
					".r4{height:18.75pt;}\n" +
					".c1{white-space:pre-wrap;text-align:center;font-weight:bold;color: black; font-size:14pt;}\n" +
					".c2{white-space:pre-wrap;text-align:left;color: black; font-size:10pt;}\n" +
					".c3{white-space:pre-wrap;text-align:left;color: black; font-size:11pt;}\n" +
					".c4{white-space:pre-wrap;text-align:left;color: black; font-size:9pt;}\n" +
					".c5{white-space:pre-wrap;text-align:left;border-bottom:1px solid black;color: black; font-size:9pt;}\n" +
					".c6{white-space:pre-wrap;text-align:right;border-bottom:1px solid black;color: black; font-size:9pt;}\n" +
					".c7{white-space:pre-wrap;text-align:center;border-top:1px solid black;border-right:1px solid black;border-left:1px solid black;color: black; font-size:9pt;}\n" +
					".c8{white-space:pre-wrap;text-align:center;border-top:1px solid black;border-right:1px solid black;border-bottom:1px solid black;border-left:1px solid black;color: black; font-size:7pt;}\n" +
					".c9{white-space:pre-wrap;text-align:right;border-top:1px solid black;border-right:1px solid black;border-bottom:1px solid black;border-left:1px solid black;color: black; font-size:7pt;}\n" +
					".c10{white-space:pre-wrap;color: black; font-size:11pt;}\n" +
					".c11{white-space:pre-wrap;text-align:center;border-top:1px solid black;border-right:1px solid black;border-bottom:1px solid black;border-left:1px solid black;color: black; font-size:9pt;}\n" +
					".c12{white-space:pre-wrap;text-align:right;border-top:1px solid black;border-right:1px solid black;border-bottom:1px solid black;border-left:1px solid black;color: black; font-size:9pt;}\n" +
					".c13{white-space:pre-wrap;text-align:left;border-top:1px solid black;color: black; font-size:9pt;}\n" +
					".c14{white-space:pre-wrap;text-align:center;border-top:1px solid black;color: black; font-size:9pt;}\n" +
					".c15{white-space:pre-wrap;font-size:9pt;}\n" +
					"</style></head><body class=\"b1\"><h2> </h2><table class=\"t1\"><colgroup><col width=\"63\"><col width=\"244\"><col width=\"93\"><col width=\"105\"><col width=\"53\"><col width=\"75\"><col width=\"75\"><col width=\"75\"><col width=\"75\"><col width=\"75\"><col width=\"75\"><col width=\"75\"><col width=\"75\"><col width=\"66\"><col width=\"0\"><col width=\"0\"><col width=\"0\"><col width=\"0\"></colgroup><tbody><tr class=\"r1\"><td class=\"c1\" colspan=\"14\" rowspan=\"2\">定点医疗机构医疗费用结算申报表(职工住院月申报)</td><td class=\"c2\">&nbsp;</td><td class=\"c2\">&nbsp;</td><td class=\"c2\">&nbsp;</td><td class=\"c2\">&nbsp;</td></tr><tr class=\"r1\"><td class=\"c3\">&nbsp;</td><td class=\"c2\">&nbsp;</td><td></td><td class=\"c2\">&nbsp;</td></tr><tr class=\"r1\"><td class=\"c4\" colspan=\"5\">拨付中心：湘潭市市本级</td><td class=\"c4\" colspan=\"9\">申报期间：20200101 ~ 20200229</td><td class=\"c3\">&nbsp;</td><td class=\"c2\">&nbsp;</td><td></td><td class=\"c2\">&nbsp;</td></tr><tr class=\"r2\"><td class=\"c5\" colspan=\"12\">定点医疗机构名称(章)：xxx医院(43020113001)</td><td class=\"c6\" colspan=\"2\">单位：元</td><td class=\"c3\">&nbsp;</td><td class=\"c2\">&nbsp;</td><td></td><td class=\"c2\">&nbsp;</td></tr><tr class=\"r3\"><td class=\"c7\">申报批次号</td><td class=\"c7\">医疗机构名称</td><td class=\"c7\">待遇类型</td><td class=\"c7\">人员类别</td><td class=\"c7\">人次</td><td class=\"c7\">医疗费总额</td><td class=\"c7\">账户支付</td><td class=\"c7\">统筹基金</td><td class=\"c7\">大病基金</td><td class=\"c7\">离休基金</td><td class=\"c7\">生育基金</td><td class=\"c7\">公务员基金</td><td class=\"c7\">基金总额</td><td class=\"c7\">现金</td><td></td><td class=\"c2\">&nbsp;</td><td></td><td class=\"c2\">&nbsp;</td></tr><tr class=\"r3\"><td class=\"c8\">2220200112126</td><td class=\"c8\">xxx湘潭市中心医院</td><td class=\"c8\">xxxx</td><td class=\"c8\">在职</td><td class=\"c8\">3</td><td class=\"c9\">472,721.00</td><td class=\"c9\">2,115.50</td><td class=\"c9\">110,817.70</td><td class=\"c9\">276,454.00</td><td class=\"c9\">0.00</td><td class=\"c9\">0.00</td><td class=\"c9\">13,210.80</td><td class=\"c9\">402,598.00</td><td class=\"c9\">70,123.00</td><td class=\"c2\">&nbsp;</td><td class=\"c10\">&nbsp;</td><td class=\"c2\">&nbsp;</td><td class=\"c2\">&nbsp;</td></tr><tr class=\"r3\"><td class=\"c8\">2220200112126</td><td class=\"c8\">xxx湘潭市中心医院</td><td class=\"c8\">xxxx</td><td class=\"c8\">退休</td><td class=\"c8\">2</td><td class=\"c9\">102,500.00</td><td class=\"c9\">500.00</td><td class=\"c9\">322.00</td><td class=\"c9\">88,595.00</td><td class=\"c9\">0.00</td><td class=\"c9\">0.00</td><td class=\"c9\">2,827.50</td><td class=\"c9\">92,244.50</td><td class=\"c9\">10,255.50</td><td class=\"c2\">&nbsp;</td><td class=\"c10\">&nbsp;</td><td class=\"c2\">&nbsp;</td><td class=\"c2\">&nbsp;</td></tr><tr class=\"r3\"><td class=\"c8\">2220200212127</td><td class=\"c8\">xxx湘潭市中心医院</td><td class=\"c8\">xxxx</td><td class=\"c8\">在职</td><td class=\"c8\">2</td><td class=\"c9\">321,721.00</td><td class=\"c9\">3,077.36</td><td class=\"c9\">107,873.84</td><td class=\"c9\">75,200.00</td><td class=\"c9\">0.00</td><td class=\"c9\">0.00</td><td class=\"c9\">6,769.80</td><td class=\"c9\">192,921.00</td><td class=\"c9\">128,800.00</td><td class=\"c2\">&nbsp;</td><td class=\"c10\">&nbsp;</td><td class=\"c2\">&nbsp;</td><td class=\"c2\">&nbsp;</td></tr><tr class=\"r3\"><td class=\"c11\" colspan=\"4\">合计</td><td class=\"c11\">7</td><td class=\"c12\">896,942.00</td><td class=\"c12\">5,692.86</td><td class=\"c12\">219,013.54</td><td class=\"c12\">440,249.00</td><td class=\"c12\">0.00</td><td class=\"c12\">0.00</td><td class=\"c12\">22,808.10</td><td class=\"c12\">687,763.50</td><td class=\"c12\">209,178.50</td><td></td><td class=\"c2\">&nbsp;</td><td></td><td class=\"c2\">&nbsp;</td></tr><tr class=\"r4\"><td class=\"c13\" colspan=\"6\">制表日期:2020年12月21日</td><td class=\"c14\">&nbsp;</td><td class=\"c15\">&nbsp;</td><td class=\"c15\">&nbsp;</td><td class=\"c15\">&nbsp;</td><td class=\"c15\">&nbsp;</td><td class=\"c15\">&nbsp;</td><td class=\"c15\">&nbsp;</td><td class=\"c15\">&nbsp;</td><td class=\"c3\">&nbsp;</td><td class=\"c2\">&nbsp;</td><td></td><td class=\"c2\">&nbsp;</td></tr></tbody></table><div style=\"display:none\" id=\"print_format\"><span id=\"head\">2,3</span><span id=\"foot\">10</span></div></body></html>\n");
			acc.setBke403s("2220200212127,2220200112126");
			acc.setBkp402s("46277,46275,46274");
			setJSONReturn(acc);
//			setJSONReturn(hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryDeclareAccount(q));
		} catch (IOException e) {
			saveError(e);
			e.printStackTrace();
		}
	}

	/**
	 * 查询结算申报汇总明细数据
	 *
	 * @author lwyao
	 */
	public void queryDetail() {
		PagerHelper.setPageParam(getRequest(), q);
		ListResult result = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryDeclareAccountDetail(q);
		PagerHelper.renderDataGrid(getRequest(), getResponse(), result);
	}

	/**
	 * 保存申报明细修改
	 *
	 * @author lwyao
	 * @date 2018年9月11日
	 */
	public void saveDeclareAccountDetailChanges() {
		try {
			String datas = getParameter("datas");
			if (UtilFunc.isEmpty(datas)) {
				throw new HygeiaException("缺少修改数据参数。[datas]");
			}
			ParamDTO p = createParamDTO();
			p.put("datas", datas);
			Map<String, Object> ret = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, p.getAkb020())
					.saveDeclareAccountDetailChanges(p);
			setJSONReturn(ret);
		} catch (IOException e) {
			saveError(e);
			e.printStackTrace();
		}
	}

	/**
	 * 确认申报
	 *
	 * @author lwyao
	 */
	public void confirmAccountDeclare() {
		try {
			setJSONReturn(hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).confirmDeclareAccount(q));
		} catch (IOException e) {
			saveError(e);
			e.printStackTrace();
		}
	}

	/**
	 * 异地结算申报查询
	 *
	 * @author lwyao
	 * @date 2018年11月19日
	 */
	public void queryAccountDeclareYD() {
		try {
			q.put("aae030", getParameter("aae030"));
			q.put("aae031", getParameter("aae031"));
			q.put("aaz262", BizHelper.getLoginUser());
			q.put("aae011", BizHelper.getUserName());
			setJSONReturn(hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryDeclareAccountYD(q));
		} catch (IOException e) {
			saveError(e);
			e.printStackTrace();
		}
	}

	/**
	 * TS19052700048 - 【需求开发】湘潭体检系统结算申报拨付流程改造
	 * 体检结算申报查询
	 *
	 * @author lubin
	 * 2019年5月27日下午2:03:43 the void
	 */
	public void queryAccountDeclareHealth() {
		try {
			q.put("aae030", getParameter("aae030"));
			q.put("aae031", getParameter("aae031"));
			setJSONReturn(hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryAccountDeclareHealth(q));
		} catch (IOException e) {
			saveError(e);
			e.printStackTrace();
		}
	}

	/**
	 * TS19052700048 - 【需求开发】湘潭体检系统结算申报拨付流程改造
	 * 体检结算申报确认
	 *
	 * @author lubin
	 * 2019年5月27日下午4:32:22 the void
	 */
	public void confirmAccountDeclareHealth() {
		try {
			q.put("aae030", getParameter("aae030"));
			q.put("aae031", getParameter("aae031"));
			setJSONReturn(hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).confirmAccountDeclareHealth(q));
		} catch (IOException e) {
			saveError(e);
			e.printStackTrace();
		}
	}

	public AccountDeclareQueryDTO getQ() {
		return q;
	}

	public void setQ(AccountDeclareQueryDTO q) {
		this.q = q;
		this.initParam(q);
	}

}
