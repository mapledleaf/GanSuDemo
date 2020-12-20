package com.powersi.ssm.biz.medicare.medicarepay.action;

import java.io.IOException;
import java.util.Map;

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
			setJSONReturn(hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryDeclareAccount(q));
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
