package com.powersi.ssm.biz.diagnose;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.account.AcctControlVS;
import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.test.base.TestBase;

public class TestDiagnose extends TestBase {

	@Autowired
	HygeiaServiceManager hygeiaServiceManager;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testAPIService() {
		HashMap biz_data = new HashMap();
		List lstResult = new ArrayList();
		// 构造mt_biz
		HashMap biztemp = new HashMap();
		Vector vbiztemp = new Vector();
		biztemp.put("birthday", "19800401");
		biztemp.put("official_code", "00");
		biztemp.put("sex", "1");
		biztemp.put("corp_id", "2");
		biztemp.put("fee_batch", "1");
		biztemp.put("reg_date", "2016-03-08 14:07:17.0");
		biztemp.put("foregift_remain", "0");
		biztemp.put("serial_no", "556");
		biztemp.put("indi_id", "118");
		biztemp.put("district_code", "439901");
		biztemp.put("end_date", "2016-03-08 00:00:00");
		biztemp.put("biz_stat", "1");
		biztemp.put("name", "张某某");
		biztemp.put("pers_type", "1");
		biztemp.put("treatment_type", "410");
		biztemp.put("in_disease", "no0001");
		biztemp.put("pers_type_detail", "1");
		biztemp.put("fin_disease", "no0001");
		biztemp.put("insur_no", "5");
		biztemp.put("hospital_name", "C医院");
		biztemp.put("biz_type", "41");
		biztemp.put("reimburse_flag", "1");
		biztemp.put("begin_date", "2016-03-08 00:00:00");
		biztemp.put("idcard", "110101198004010235");
		biztemp.put("hospital_id", "9513547");
		biztemp.put("reg_flag", "0");
		biztemp.put("injury_borth_sn", "69");
		biztemp.put("center_id", "439901");
		vbiztemp.add(0, biztemp);
		biz_data.put("mt_biz", vbiztemp);
		// 构造mt_pay_record
		Vector vpaytemp = new Vector();
		biz_data.put("mt_pay_record", vpaytemp);
		// 构造mt_fee
		HashMap feetemp = new HashMap();
		Vector vfeetemp = new Vector();
		feetemp.put("hosp_code", "003031123");
		feetemp.put("stat_type", "001");
		feetemp.put("serial_item", "22");
		feetemp.put("dosage", "0");
		feetemp.put("opp_serial_fee", "0");
		feetemp.put("input_date", "2016-03-08 00:00:00.0");
		feetemp.put("fee_batch", "1");
		feetemp.put("hosp_name", " 阿莫西林");
		feetemp.put("serial_no", "556");
		feetemp.put("serial_match", "0");
		feetemp.put("defray_type", "A000_100");
		feetemp.put("item_code", "003031123");
		feetemp.put("hospital_id", "9513547");
		feetemp.put("item_name", " 阿莫西林");
		feetemp.put("money", "200");
		feetemp.put("fee_date", "2016-03-08");
		feetemp.put("serial_fee", "928");
		feetemp.put("calc_flag", "0");
		feetemp.put("center_id", "439901");
		vfeetemp.add(0, feetemp);
		biz_data.put("mt_fee", vfeetemp);

		HashMap static_data = new HashMap();
		static_data.put("year_sill_170", "0.0");
		static_data.put("sex", "1");
		static_data.put("ZF_P", "17600.0");
		static_data.put("hosp_level", "5");
		static_data.put("office_grade", "00");
		static_data.put("ZF_S", "17600.0");
		static_data.put("year_sill_131", "0.0");
		static_data.put("inhosp_biz_times", "1");
		static_data.put("year_sill_132", "0.0");
		static_data.put("hosp_out_flag", "0");
		static_data.put("year_sill_133", "0.0");
		static_data.put("ZF_A", "1760.0");
		static_data.put("iBizTimes131", "0");
		static_data.put("age", "35");
		static_data.put("pers_type", "1");
		static_data.put("D999998", "19360.0");
		static_data.put("year_cumulate_ih", "0.0");
		static_data.put("already_mz_001", "0");
		static_data.put("inhosp_fee", "0.0");
		static_data.put("aae140", "410");
		static_data.put("hosp_grade", "1");
		static_data.put("benefit_scale", "0");
		static_data.put("cj_clinic_pay_flag", "0");
		static_data.put("partself_pay", "17600.0");
		static_data.put("benefit_limit", "0");
		static_data.put("last_balance_bn", "0");
		static_data.put("year_sill_122", "0.0");
		static_data.put("at_year", "2016");
		static_data.put("already_mz_801", "0");
		static_data.put("year_sill_120", "0.0");
		static_data.put("biz_times", "2");
		static_data.put("year_cumulate_ih_122", "0.0");
		static_data.put("last_balance", "0");
		static_data.put("hosp_kind", "10");
		static_data.put("hosp_district_code", "439901");
		static_data.put("catalog_center", "439901");
		static_data.put("year_sill_ih", "0.0");
		static_data.put("clinic_pay_flag", "0");
		static_data.put("year_sill_140", "0.0");
		static_data.put("nothing_flag", "1");
		static_data.put("first_balance", "0");
		static_data.put("admin_id", "7");
		static_data.put("inhosp_biz_times120", "1");

		HashMap frozen_data = new HashMap();
		frozen_data.put("996", "0");
		frozen_data.put("501", "0");

		Map param = new HashMap();
		param.put("actionCode", "110104");
		param.put("biz_data", biz_data);
		param.put("static_data", static_data);
		param.put("frozen_data", frozen_data);

		// AcctControl accControl = (AcctControl)
		// springUtil.getBean("AcctControl");
		AcctControlVS acctControl = (AcctControlVS) hygeiaServiceManager.getBean("AcctControl", "43104512");
		lstResult = acctControl.runDirect(param);

		// 获取计算结果
		System.out.println(lstResult);

		// DiagnoseSaveBizVS diagnoseSaveBizVS =
		// (DiagnoseSaveBizVS)hygeiaServiceManager.getBean("diagnoseSaveBizVS",
		// "43104512");
		// System.out.println(diagnoseSaveBizVS.test());
		// DiagnoseInfoDTO diagnoseDTO = new DiagnoseInfoDTO();
		// int a = diagnoseSaveBizVS.DiagnoseSave(diagnoseDTO);
		// System.out.println("a:"+ a);
	}

	@Test
	public void testCallFeeInputService() {
		UUID uuid = UUID.randomUUID();

		//DiagnoseSaveVS diagnoseSaveVS = (DiagnoseSaveVS) hygeiaServiceManager.getBean("diagnoseSaveVS", "43104512");
		DiagnoseInfoDTO diagnoseDTO = new DiagnoseInfoDTO();
		diagnoseDTO.setAaa027("1");
		diagnoseDTO.setAab001(100L);
		diagnoseDTO.setAab019("1");
		diagnoseDTO.setAac001(118L);
		diagnoseDTO.setAac002("1");
		diagnoseDTO.setAac003("中文");
		diagnoseDTO.setAac004("1");
		//diagnoseDTO.setAac006(new java.util.Date());
		diagnoseDTO.setAae005("1");
		diagnoseDTO.setAae140("1");
		diagnoseDTO.setAaz217("xxxxxxxxxx");
		diagnoseDTO.setAaz267(123L);
		diagnoseDTO.setAka130("1");
		diagnoseDTO.setAkb020("0021");
		diagnoseDTO.setAkb021("1");
		diagnoseDTO.setKcd1id(uuid.toString());
		diagnoseDTO.setBaa027("1");
		diagnoseDTO.setBae009("1");
		diagnoseDTO.setBaf313("1");
		diagnoseDTO.setBaz113("1");
		diagnoseDTO.setBka001(1L);
		diagnoseDTO.setBka002(2L);
		//diagnoseDTO.setBka004("1");
		//diagnoseDTO.setBka005("1");
		diagnoseDTO.setBka006("1");
		diagnoseDTO.setBka008("1");
		diagnoseDTO.setBka009(1L);
		diagnoseDTO.setBka010(1L);
		diagnoseDTO.setBka011("1");
		diagnoseDTO.setBka012("1");
		//diagnoseDTO.setBka013(DateFunc.dateToString(new Date()));
		//diagnoseDTO.setBka014("1");
		diagnoseDTO.setBka015("1");
		diagnoseDTO.setBka016("1");
		//diagnoseDTO.setBka017((new java.util.Date()).toString());
		diagnoseDTO.setBka018("1");
		//diagnoseDTO.setBka019("1");
		diagnoseDTO.setBka020("1");
		diagnoseDTO.setBka020("1");
		diagnoseDTO.setBka021("1");
		diagnoseDTO.setBka022("1");
		//diagnoseDTO.setBka023("1");
		diagnoseDTO.setBka024("1");
		diagnoseDTO.setBka025("1");
		//diagnoseDTO.setBka026("1");
		diagnoseDTO.setBka028(new java.util.Date());
		diagnoseDTO.setBka029("1");
		diagnoseDTO.setBka030(1L);
		//diagnoseDTO.setBka031("1");
		//diagnoseDTO.setBka032(DateFunc.dateToString(new Date()));
		//diagnoseDTO.setBka033("1");
		diagnoseDTO.setBka034("1");
		diagnoseDTO.setBka035("1");
		diagnoseDTO.setBka036("1");
		//diagnoseDTO.setBka037("1");
		diagnoseDTO.setBka038(new java.util.Date());
		diagnoseDTO.setBka039("1");
		diagnoseDTO.setBka040("1");
		diagnoseDTO.setBka041("1");
		diagnoseDTO.setBka042("1");
		diagnoseDTO.setBka043("1");
		diagnoseDTO.setBka044("1");
		diagnoseDTO.setBka100("1");
		diagnoseDTO.setBka245(1.00);
		diagnoseDTO.setBka414("1");
		//diagnoseDTO.setBka415("1");
		diagnoseDTO.setBka501("1");
		diagnoseDTO.setBka502("1");
		diagnoseDTO.setBka503("1");
		diagnoseDTO.setBka504(new java.util.Date());
		diagnoseDTO.setBka505(new java.util.Date());
		diagnoseDTO.setBka506(new java.util.Date());
		diagnoseDTO.setBka507(0L);
		diagnoseDTO.setBka508(0L);
		diagnoseDTO.setBka509(0L);
		diagnoseDTO.setBka510("0");
		//int a = diagnoseSaveVS.diagnoseSave(diagnoseDTO, null);
		//System.out.println("a:" + a);
	}

}
