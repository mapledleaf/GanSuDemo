package com.powersi.ssm.biz.medicare.medicarepay.action;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.pojo.ParamDTO;
import com.powersi.biz.medicare.medicarepay.pojo.DeductionQueryDTO;
import com.powersi.biz.medicare.medicarepay.service.MedicarePayEsbService;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.util.PagerHelper;

/**
 * 医疗费用申诉ACTION
 * 
 * 2018年8月6日上午9:19:26
 *
 * @author lwyao
 *
 */
@SuppressWarnings("serial")
@Action(value = "feeAppeal")
public class FeeAppealAction extends MedicarepayAction {

	DeductionQueryDTO q;

	/**
	 * 查询扣付业务
	 * 
	 * @author lwyao
	 */
	public void queryDeductionBiz() {
		PagerHelper.setPageParam(getRequest(), q);
		ListResult result = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryDeductionBiz(q);
		PagerHelper.renderDataGrid(getRequest(), getResponse(), result);
	}

	/**
	 * 查询扣付费用
	 * 
	 * @author lwyao
	 */
	@SuppressWarnings("unchecked")
	public void queryDeductionFee() {
		PagerHelper.setPageParam(getRequest(), q);
		ListResult result = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryDeductionFee(q);
		for (Object o : result.getList()) {
			Map<String, Object> data = (Map<String, Object>) o;
			data.put("bkp059_show", data.get("bkp059"));
			data.put("bkp060_show", data.get("bkp060"));
		}
		PagerHelper.renderDataGrid(getRequest(), getResponse(), result);
	}

	public void saveFeeAppeal() {
		try {
			String akb020 = BizHelper.getAkb020();
			String datas = getParameter("datas");
			if (UtilFunc.isEmpty(datas)) {
				throw new HygeiaException("缺少申诉内容参数。[datas]");
			}
			ParamDTO param = this.createParamDTO();
			param.put("datas", datas);
			setJSONReturn(hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, akb020).saveFeeAppeal(param));
		} catch (IOException e) {
			saveError(e);
			e.printStackTrace();
		}
	}

	/**
	 * 异地就医-疑点费用人员信息查询
	 * 
	 * @author lwyao
	 * @date 2018年11月29日
	 */
	public void queryDeductionBizYD() {
		try {
			setJSONReturn(hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryDeductionBizYD(q).getList());
		} catch (IOException e) {
			saveError(e);
			e.printStackTrace();
		}
	}

	/**
	 * 异地就医-疑点人员费用明细查询
	 * 
	 * @author lwyao
	 * @date 2018年11月21日
	 */
	public void queryDeductionFeeYD() {
		try {
			setJSONReturn(hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, q.getAkb020()).queryDeductionFeeYD(q).getList());
		} catch (IOException e) {
			saveError(e);
			e.printStackTrace();
		}
	}

	/**
	 * 异地就医-疑点人员费用申诉反馈意见保存
	 * 
	 * @author lwyao
	 * @date 2018年11月21日
	 */
	public void saveFeeAppealYD() {
		try {
			String inputs = getParameter("inputs");
			if (UtilFunc.isEmpty(inputs)) {
				throw new HygeiaException("缺少申诉内容参数。[inputs]");
			}
			ParamDTO param = this.createParamDTO();
			param.put("bkp057", BizHelper.getLoginUser()); // 申述人工号
			param.put("bkp058", BizHelper.getUserName()); // 申述人名称
			param.put("inputs", inputs);
			setJSONReturn(hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, param.getAkb020()).saveFeeAppealYD(param));
		} catch (IOException e) {
			saveError(e);
			e.printStackTrace();
		}
	}

	public DeductionQueryDTO getQ() {
		return q;
	}

	public void setQ(DeductionQueryDTO q) {
		this.q = q;
		this.initParam(q);
	}

}
