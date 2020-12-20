package com.powersi.ssm.biz.medicare.medicarepay.service;

import java.util.Collections;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.ParamDTO;
import com.powersi.biz.medicare.medicarepay.service.MedicarePayEsbService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.CodetableHelper;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.medicarepay.dao.MedicarepayDAO;

public class MedicarepayServiceImpl implements MedicarepayService {

	MedicarepayDAO dao = BeanHelper.getBean(MedicarepayDAO.class);

	HygeiaServiceManager hygeiaServiceManager;

	static int INIT_CENTER_CODETYPES = 0; // 初始化拨付码表

	public MedicarepayServiceImpl() { // 初始化同步中心码表
		String aaa027 = BizHelper.getAaa027();
		new Thread(new Runnable() {
			public @Override void run() {
				while (INIT_CENTER_CODETYPES == 0) {
					if (hygeiaServiceManager == null) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						INIT_CENTER_CODETYPES++;
						refreshCenterCodetype(null, aaa027);
					}
				}
			}
		}).start();
	}

	@Override
	public void setHygeiaServiceManager(HygeiaServiceManager hygeiaServiceManager) {
		this.hygeiaServiceManager = hygeiaServiceManager;
	}

	@Override
	public HygeiaServiceManager getHygeiaServiceManager() {
		return this.hygeiaServiceManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Map<String, String>> refreshCenterCodetype(String codetype, String daa027) {
		if (getHygeiaServiceManager() == null) { // 如果HygeiaService RPC为空等待1秒
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (getHygeiaServiceManager() != null) {
			ParamDTO param = new ParamDTO();
			param.setAkb020(BizHelper.getAkb020());
			param.setAaa027(UtilFunc.isBlank(daa027) ? BizHelper.getAaa027() : daa027);
			param.put("codetype", codetype);
			Map<String, Map<String, String>> codetypes = hygeiaServiceManager.getBeanByClass(MedicarePayEsbService.class, param.getAkb020())
					.getCenterCodetypes(param);
			if (codetypes != null) {
				try {
					codetypes.forEach((codeType, v) -> {
						Map<String, String> codeMap = CodetableHelper.get(codeType);
						if (codeMap == null) {
							if (dao.insertCodetype(
									CollectionHelper.newHashMap("code_type", codeType, "code_name", "医保中心：" + codeType, "valid_flag", "1")) > 0) {
								CodetableHelper.refresh(codeType);
								codeMap = CodetableHelper.get(codeType);
							}
						}
						if (codeMap != null) {
							codeMap.clear();
							codeMap.putAll(v);
						}
					});
					DBHelper.commit();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBHelper.close();
				}
				return codetypes;
			}
		}
		return Collections.EMPTY_MAP;
	}

}
