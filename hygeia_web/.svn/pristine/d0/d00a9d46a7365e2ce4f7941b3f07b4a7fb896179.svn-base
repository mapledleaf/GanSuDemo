package com.powersi.ssm.biz.medicare.medicarepay.action;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.pojo.ParamDTO;
import com.powersi.biz.medicare.medicarepay.pojo.AccountCancelApplyQueryDTO;
import com.powersi.biz.medicare.medicarepay.service.MedicarePayEsbService;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.util.PagerHelper;

/**
 * 取消登账申请ACTION
 * 
 * @author lubin
 *
 * 2018年11月7日下午3:02:39
 *
 */
@SuppressWarnings("serial")
@Action(value = "accountCancelApply")
public class AccountCancelApplyAction extends MedicarepayAction {

	AccountCancelApplyQueryDTO q;

	/**
	 * 查询登账数据
	 * 
	 * @author lubin
	 * 2018年11月7日下午3:09:09 the void
	 */
	public void queryAccountBiz() {
		PagerHelper.setPageParam(getRequest(), q);
		ListResult result = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryAccountBiz(q);
		PagerHelper.renderDataGrid(getRequest(), getResponse(), result);
	}
	
	/**
	 * 查询取消登账申请数据
	 * 
	 * @author lubin
	 * 2018年11月8日上午8:55:09 the void
	 */
	public void queryAccountApply() {
		PagerHelper.setPageParam(getRequest(), q);
		ListResult result = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryAccountApply(q);
		PagerHelper.renderDataGrid(getRequest(), getResponse(), result);
	}

	/**
	 * 保存取消登账申请数据
	 * 
	 * @author lubin
	 * 2018年11月7日下午3:13:53 the void
	 */
	public void saveAccountCancelApply() {
		try {
			String akb020 = BizHelper.getAkb020();
			String datas = getParameter("datas");
			if (UtilFunc.isEmpty(datas)) {
				throw new HygeiaException("缺少申诉内容参数。[datas]");
			}
			ParamDTO param = this.createParamDTO();
			param.put("datas", datas);
			setJSONReturn(hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, akb020).saveAccountCancelApply(param));
		} catch (IOException e) {
			saveError(e);
			e.printStackTrace();
		}
	}

	public AccountCancelApplyQueryDTO getQ() {
		return q;
	}

	public void setQ(AccountCancelApplyQueryDTO q) {
		this.q = q;
		this.initParam(q);
	}

}
