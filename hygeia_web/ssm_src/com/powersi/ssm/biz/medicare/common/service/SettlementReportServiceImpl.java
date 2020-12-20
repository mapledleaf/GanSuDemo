package com.powersi.ssm.biz.medicare.common.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.comm.pojo.MediDTO;
import com.powersi.biz.medicare.comm.service.SettlementReportService;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.powerreport.service.PowerReport;
import com.powersi.powerreport.service.PowerReportDao;
import com.powersi.powerreport.service.PowerReportImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

/**
 * 
 * @author 刘勇
 *
 */
@Service
public class SettlementReportServiceImpl implements SettlementReportService {

	private static final long serialVersionUID = 1L;
	private static final PowerReportDao powerReportDao = new PowerReportDao();

	@SuppressWarnings("rawtypes")
	@Override
	public String createSettlementReport(MediDTO mediDTO, Map settlementReportData) {
		if (StringUtils.isBlank(mediDTO.getBka981()) && StringUtils.isNotBlank(mediDTO.getAaz217())) {
			mediDTO.setBka981(mediDTO.getAaz217());
		}
		PowerReport powerReport = new PowerReportImpl();
		Map powerReportMap = null;
		if (StringUtils.isNotBlank(mediDTO.getBka981())) {
			powerReportMap = powerReportDao.getReportBaseInfoBizID(mediDTO.getBka981());
		}
		if (powerReportMap != null) {
			powerReport.delReport(null, mediDTO.getBka981());
		}
		if (StringUtils.isBlank(mediDTO.getBka980())) {
			throw new HygeiaException("报表模板ID不能为空!");
		}
		if (StringUtils.isBlank(mediDTO.getBka981())) {
			throw new HygeiaException("业务编号ID不能为空!");
		}
		if (StringUtils.isBlank(mediDTO.getBka982())) {
			mediDTO.setBka982("1");
		}
		if (StringUtils.isBlank(mediDTO.getBka983())) {
			mediDTO.setBka983("结算单");
		}
		if (StringUtils.isBlank(mediDTO.getBka984())) {
			mediDTO.setBka984(BizHelper.getLoginUser());
		}
		String bka985 = powerReport.createReport(mediDTO.getBka980(), mediDTO.getBka981(),
				Integer.parseInt(mediDTO.getBka982()), settlementReportData, mediDTO.getBka983(), mediDTO.getBka984());
		mediDTO.setBka985(bka985);
		return mediDTO.getBka985();
	}

}
