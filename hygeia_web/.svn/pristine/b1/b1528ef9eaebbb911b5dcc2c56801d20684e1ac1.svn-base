package com.powersi.ssm.biz.medicare.comminter.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.comminter.pojo.KAB3DTO;
import com.powersi.biz.medicare.comminter.service.BillReportService;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.powerreport.service.PowerReport;
import com.powersi.powerreport.service.PowerReportDao;
import com.powersi.powerreport.service.PowerReportImpl;

/**
 * 
 * @author 刘飞扬
 *
 */
@Service
public class BillReportServiceImpl implements BillReportService {

	private static final long serialVersionUID = 1L;
	private static final PowerReportDao powerReportDao = new PowerReportDao();

	@SuppressWarnings("rawtypes")
	@Override
	public String createBillReport(KAB3DTO kab3DTO, Map billReportData) {
		PowerReport powerReport = new PowerReportImpl();
		Map powerReportMap = null;
		if (StringUtils.isNotBlank(kab3DTO.getAaz217())) {
			powerReportMap = powerReportDao.getReportBaseInfoBizID(kab3DTO.getAaz217());
		}
		if (powerReportMap != null) {
			powerReport.delReport(null, kab3DTO.getAaz217());
		}
		if (StringUtils.isBlank(kab3DTO.getBillReport())) {
			throw new HygeiaException("发票模板ID不能为空");
		}
		String billReport = powerReport.createReport(kab3DTO.getBillReport(), kab3DTO.getAaz217(), 0, billReportData,
				"发票", kab3DTO.getBka015());
		return billReport;
	}

}
