package com.powersi.ssm.biz.medicare.medicarepay.action;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.comm.pojo.ParamDTO;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.CodetableHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.medicarepay.service.MedicarepayService;

/**
 * 
 * 医疗拨付基础action
 * 
 * 2018年9月14日下午3:32:03
 *
 * @author lwyao
 *
 */
@SuppressWarnings("serial")
public class MedicarepayAction extends BaseAction {

	protected MedicarepayService payService = BeanHelper.getBean(MedicarepayService.class);

	protected @Autowired HygeiaServiceManager hygeiaServiceManager;

	public MedicarepayAction() {
		if (payService != null && payService.getHygeiaServiceManager() == null) {
			new Thread(new Runnable() {
				public @Override void run() {
					while (payService.getHygeiaServiceManager() == null) {
						try {
							if (hygeiaServiceManager != null) {
								payService.setHygeiaServiceManager(hygeiaServiceManager);
							} else {
								Thread.sleep(100);
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	/**
	 * 创建一个基础的参数对象，对象中包含aaa027、akb020、caa027
	 * 
	 * @author lwyao
	 * @date 2018年9月11日
	 * @return
	 */
	protected ParamDTO createParamDTO() {
		ParamDTO param = new ParamDTO();
		initParam(param);
		return param;
	}

	/**
	 * 设置对象中的aaa027、akb020、caa027
	 * 
	 * @author lwyao
	 * @date 2018年9月14日
	 * @param param
	 */
	protected void initParam(ParamDTO param) {
		param.setAaa027(UtilFunc.isEmpty(param.getAaa027()) ? BizHelper.getAaa027() : param.getAaa027());
		param.setAkb020(UtilFunc.isEmpty(param.getAkb020()) ? BizHelper.getAkb020() : param.getAkb020());
		if (UtilFunc.isEmpty(param.getCaa027())) {
			@SuppressWarnings("unchecked")
			Map<String, Object> caa027 = CodetableHelper.get("caa027");
			if (caa027 != null && caa027.keySet().size() == 1) {
				param.setCaa027(caa027.keySet().toArray(new String[caa027.keySet().size()])[0]);
			}
		}
	}

	/**
	 * 刷新拨付码表到结算云
	 * 
	 * @author lwyao
	 * @date 2018年9月29日
	 */
	public void refreshCenterCodetype() {
		try {
			setJSONReturn(payService.refreshCenterCodetype(null, null));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
