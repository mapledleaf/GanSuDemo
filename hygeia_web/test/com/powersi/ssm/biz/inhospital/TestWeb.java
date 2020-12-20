package com.powersi.ssm.biz.inhospital;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.comm.service.memory.MemoryDB;
import com.powersi.comm.service.timetask.TimeTaskSingleKeeper;
import com.powersi.config.pojo.BizYyInfo;
import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.ServiceLocator;
import com.powersi.hygeia.framework.util.UserHelper;
import com.powersi.pcloud.medicare.comm.utils.MedicareCommUtils;
import com.powersi.ssm.biz.medicare.common.service.QueryStringService;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.outland.service.OutBizService;
import com.powersi.test.base.TestBase;

/**
 * 
 * @author 刘勇
 *
 */
public class TestWeb extends TestBase {

	@Autowired
	private MemoryDB memoryDB;
	@Autowired
	private QueryStringService queryStringService;
	@Autowired
	private OutBizService outBizService;
	@Autowired
	private TimeTaskSingleKeeper timeTaskSingleKeeper;
	private final String methodUUID = "CleanExpriedReportService.clear";

	@Test
	public void testTimeTaskSingleKeeper() {
		try {
			this.timeTaskSingleKeeper.init(methodUUID);
			System.out.println(this.timeTaskSingleKeeper.canIExecute(methodUUID));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testServiceLocator() {
		if (ServiceLocator.getInstance().containsBean("PowerReportStore")) {
			System.out.println(ServiceLocator.getInstance().getBean("PowerReportStore").getClass().getSimpleName());
		}
	}

	// @Test
	public void testMedicareCommUtils() {
		try {
			String message = "提示 java.lang.RuntimeException ： com.powersi.comm.exception.ApusException : 		xxx at weblogic.security.service.SecurityManager.runAs(SecurityManager.java:120 at weblogic.servlet.provider.WlsSubjectHandle.run(WlsSubjectHandle.java:57)java.lang.NullPointerException ：com.powersi.hygeia.framework.exception.HygeiaException com.powersi.biz.core.ws.BizException: message at com.powersi.biz.medicare.medicareos.service.MedicarePersonServiceImpl.filterMedicarePersonInfo(MedicarePersonServiceImpl.java:278)";
			if (StringUtils.isNotBlank(message)) {
				message = MedicareCommUtils.thinErrorMessage(message);
			}
			System.out.println("提示信息:" + message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testOutBizService() {
		try {
			String bkd324 = this.outBizService.getBkd324("441800");
			System.out.println("bkd324：" + bkd324);
			bkd324 = this.outBizService.getBkd324("440400");
			System.out.println("bkd324：" + bkd324);
			bkd324 = this.outBizService.getBkd324("440100");
			System.out.println("bkd324：" + bkd324);
			bkd324 = this.outBizService.getBkd324("440000");
			System.out.println("bkd324：" + bkd324);
			bkd324 = this.outBizService.getBkd324("449900");
			System.out.println("bkd324：" + bkd324);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testCodetableMapping() {
		try {
			String bka006 = CodetableMapping.getDisplayValue("bka006", "820", "");
			System.out.println("bka006_name：" + bka006);
			bka006 = CodetableMapping.getDisplayValue("bka006$441800", "820", "");
			System.out.println("bka006_name：" + bka006);
			bka006 = CodetableMapping.getDisplayValue("bka006$440400", "820", "");
			System.out.println("bka006_name：" + bka006);
			bka006 = CodetableMapping.getDisplayValue("bka006$440100", "820", "");
			System.out.println("bka006_name：" + bka006);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testQueryStringService() {
		try {
			BusiContext.setUserInfo(UserHelper.getUser("9", "100"));
			System.out.println("akb020 is " + BizHelper.getAkb020());
			String aac001 = "";
			// aac001 = "100000000493900";
			// aac001 = "430921831303451";
			// aac001 = "430921010101000";
			// aac001 = "00200220170227000002";
			// aac001 = "1100000005943359";
			aac001 = "430921198306024513";
			System.out.println(aac001 + " is aac001 ? " + this.queryStringService.isValidatedAac001(aac001));
			aac001 = "430921198202264520";
			System.out.println(aac001 + " is aac001 ? " + this.queryStringService.isValidatedAac001(aac001));
			aac001 = "1100000005905503";
			System.out.println(aac001 + " is aac001 ? " + this.queryStringService.isValidatedAac001(aac001));
			aac001 = "941802201501060711";
			System.out.println(aac001 + " is aac002 ? " + this.queryStringService.isValidatedAllIdcard(aac001));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testCompareTo() {
		try {
			if ("20170121".compareTo("20170120") > 0) {
				System.out.println("开始日期不能大于结束日期 ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testAA13Service() {
		try {
			System.out.println(BizHelper.getAaa027ByBka501("441802"));
			System.out.println(BizHelper.getAaa027NameByBka501("441802"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testEstablishDepartmentService() {
		try {
			String yybm = "002002"; // 医院编码
			BizYyInfo yyInfo = (BizYyInfo) this.memoryDB.getMapValue("MAP_BIZ_YY_INFO", yybm);
			if (yyInfo == null) {
				return;
			}
			throw new java.lang.RuntimeException("what is it?");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	// @Test
	public void testEstablishDepartmentServiceProducer() {
		try {
			Map<String, Object> yyRows = this.memoryDB.getMap("MAP_BIZ_YY_INFO");
			for (Entry<String, Object> yyRow : yyRows.entrySet()) {
				BizYyInfo yyInfo = (BizYyInfo) yyRow.getValue();
				if (StringUtils.isBlank(yyInfo.getYymc())) {
					continue;
				}
				if (StringUtils.isBlank(yyInfo.getTcqbm())) {
					continue;
				}
				if (yyInfo.getYybm().length() != 6) {
					continue;
				}
				if (yyInfo.getYybm().startsWith("00601")) {
					System.out.println(yyInfo.getYybm());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
