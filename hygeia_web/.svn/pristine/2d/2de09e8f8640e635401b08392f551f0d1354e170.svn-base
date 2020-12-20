package com.powersi.powerreport.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.comm.service.timetask.TimeTaskSingleKeeper;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.powerreport.service.store.PowerReportStore;

/**
 * 清理过期报表数据
 * 
 * @author 林刚
 *
 */
@Service
public class CleanExpriedReportService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TimeTaskSingleKeeper timeTaskSingleKeeper;
	@Autowired
	private CommunalService communalService;
	private final String methodUUID = "CleanExpriedReportService.clear";

	@PostConstruct
	public void init() {
		try {
			this.timeTaskSingleKeeper.init(methodUUID);
		} catch (Throwable e) {
			this.communalService.error(this.logger, e, "自动清理通用报表数据过期数据初始化异常");
		}
	}

	/**
	 * 清理过期报表,定时任务调用
	 */
	@Scheduled(cron = "2 2 2 * * ?")
	public void run() {
		try {
			if (!this.timeTaskSingleKeeper.canIExecute(methodUUID)) {
				return;
			}
			this.execute();
		} catch (Throwable e) {
			this.communalService.error(this.logger, e, "自动清理通用报表数据过期数据执行异常");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void execute() {
		try {
			PowerReportDao dao = new PowerReportDao();
			PowerReportStore powerReportStore = (PowerReportStore) BeanHelper.getBean("PowerReportStore");
			Map<String, String> reportBaseRow = null;
			String storeId = null;
			String report_id = null;
			List reportBaseRows = null;
			while (true) {
				reportBaseRows = dao.queryExpriedReportInfo();
				if (reportBaseRows == null || reportBaseRows.size() == 0) {
					break;
				}
				for (Object object : reportBaseRows) {
					try {
						reportBaseRow = (Map<String, String>) object;
						report_id = reportBaseRow.get("report_id");
						dao.deleteReportBaseInfo(report_id);
						storeId = reportBaseRow.get("store_id");
						powerReportStore.delte(storeId);
					} catch (Throwable e) {
						this.communalService.error(this.logger, e, "逐一循环自动清理通用报表数据过期数据异常");
					} finally {
						DBHelper.commit();
					}
				}
			}
		} finally {
			DBHelper.close();
		}
	}

}
