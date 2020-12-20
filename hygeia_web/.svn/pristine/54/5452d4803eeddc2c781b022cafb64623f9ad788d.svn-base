package com.powersi.ssm.biz.medicare.medicarepay.action;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.pojo.ParamDTO;
import com.powersi.biz.medicare.medicarepay.pojo.BillQueryDTO;
import com.powersi.biz.medicare.medicarepay.service.MedicarePayEsbService;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.util.PagerHelper;

/**
 * 拨付单查询ACTION
 * 
 * 2018年8月6日上午9:19:26
 *
 * @author lwyao
 *
 */
@SuppressWarnings("serial")
@Action(value = "billQuery")
public class BillQueryAction extends MedicarepayAction {

	BillQueryDTO q;

	/**
	 * 查询拨付单
	 * 
	 * @author lwyao
	 */
	public void queryBill() {
		PagerHelper.setPageParam(getRequest(), q);
		ListResult result = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryBill(q);
		PagerHelper.renderDataGrid(getRequest(), getResponse(), result);
	}
	
	/**
	 * TS19052700048 - 【需求开发】湘潭体检系统结算申报拨付流程改造
	 * 查询体检拨付单
	 * 
	 * @author lubin
	 * 2019年5月29日下午1:34:03 the void
	 */
	public void queryBillHealth() {
		PagerHelper.setPageParam(getRequest(), q);
		ListResult result = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryBillHealth(q);
		PagerHelper.renderDataGrid(getRequest(), getResponse(), result);
	}

	/**
	 * 查询拨付单明细
	 * 
	 * @author lwyao
	 */
	public void queryBillDetail() {
		PagerHelper.setPageParam(getRequest(), q);
		ListResult result = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryBillDetail(q);
		PagerHelper.renderDataGrid(getRequest(), getResponse(), result);
	}
	
	/**
	 * TS19052700048 - 【需求开发】湘潭体检系统结算申报拨付流程改造
	 * 查询体检拨付单明细
	 * 
	 * @author lubin
	 * 2019年5月29日下午2:14:15 the void
	 */
	public void queryBillHealthDetail() {
		PagerHelper.setPageParam(getRequest(), q);
		ListResult result = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryBillHealthDetail(q);
		PagerHelper.renderDataGrid(getRequest(), getResponse(), result);
	}

	public void confirmBill() {
		try {
			String akb020 = BizHelper.getAkb020();
			String datas = getParameter("datas");
			if (UtilFunc.isEmpty(datas)) {
				throw new HygeiaException("缺少内容参数。[datas]");
			}
			ParamDTO param = this.createParamDTO();
			param.put("datas", datas);
			setJSONReturn(hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, akb020).confirmBill(param));
		} catch (IOException e) {
			saveError(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询结算周期
	 * TS19032600105 - 【需求开发】医疗机构客户端拨付单查询条件设置
	 * 
	 * @author lubin
	 * 2019年3月27日上午11:41:33 the void
	 */
	public void querySettlementCycle() {
		try {
			String akb020 = BizHelper.getAkb020();
			String daa027 = getParameter("daa027");//拨付中心
			String field = getParameter("field");//查询日期yyyyMM
			String aae140 = getParameter("aae140");//险种
			if (UtilFunc.isEmpty(daa027)) {
				throw new HygeiaException("缺少拨付中心参数。[daa027]");
			}
			if (UtilFunc.isEmpty(field)) {
				throw new HygeiaException("缺少月份参数。[field]");
			}
			ParamDTO param = this.createParamDTO();
			param.put("daa027", daa027);
			param.put("field", field);
			param.put("aae140", aae140);
			setJSONReturn(hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, akb020).querySettlementCycle(param));
		} catch (IOException e) {
			saveError(e);
			e.printStackTrace();
		}
	}

	/**
	 * 查询拨付单业务明细
	 * 
	 * @author lwyao
	 */
	public void queryBillBiz() {
		PagerHelper.setPageParam(getRequest(), q);
		ListResult result = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryBillBiz(q);
		PagerHelper.renderDataGrid(getRequest(), getResponse(), result);
	}
	
	/**
	 * TS19052700048 - 【需求开发】湘潭体检系统结算申报拨付流程改造
	 * 查询体检拨付单业务明细
	 * 
	 * @author lubin
	 * 2019年5月29日下午2:44:23 the void
	 */
	public void queryBillHealthBiz() {
		PagerHelper.setPageParam(getRequest(), q);
		ListResult result = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryBillHealthBiz(q);
		PagerHelper.renderDataGrid(getRequest(), getResponse(), result);
	}

	public BillQueryDTO getQ() {
		return q;
	}

	public void setQ(BillQueryDTO q) {
		this.q = q;
		this.initParam(q);
	}

}
