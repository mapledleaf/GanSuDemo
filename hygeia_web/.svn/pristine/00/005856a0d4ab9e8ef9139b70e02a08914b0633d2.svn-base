package com.powersi.ssm.biz.diagnose;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Test;

import com.powersi.powermarker.PowerMarker;
import com.powersi.powermarker.utils.Excel2Html;
import com.powersi.test.base.TestBase;

public class TestCalcResultPrint extends TestBase {

	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	@Test
	public void test() {
		String localTemplateFile = "";
		String newExcelFile = "";
		// 准备list数据
		List<Map> SQL2 = new ArrayList<Map>();
		Map map2 = new HashMap();
		map2.put("ake006", "阿司匹林肠溶片");
		map2.put("bka056", "10.00");
		map2.put("bka057", "10");
		map2.put("bka058", "100.00");
		map2.put("diagnose_flag", "是");
		map2.put("staple_flag", "甲类");
		SQL2.add(map2);

		map2 = new HashMap();
		map2.put("ake006", "青霉素");
		map2.put("bka056", "20.00");
		map2.put("bka057", "5");
		map2.put("bka058", "100.00");
		map2.put("diagnose_flag", "是");
		map2.put("staple_flag", "甲类");
		SQL2.add(map2);

		map2 = new HashMap();
		map2.put("ake006", "感冒通胶囊");
		map2.put("bka056", "25.00");
		map2.put("bka057", "4");
		map2.put("bka058", "100.00");
		map2.put("diagnose_flag", "是");
		map2.put("staple_flag", "甲类");
		SQL2.add(map2);

		// 固定数据
		List<Map> SQL1 = new ArrayList<Map>();
		Map map = new HashMap();
		map.put("akb021", "广东省人民医院");
		map.put("bkc110", "三级");
		map.put("bkb023", "是");
		map.put("diangose_flag", "是");
		map.put("bsmedi_flag", "是");
		map.put("aac001", "1000000389");
		map.put("aac003", "张某某");
		map.put("bka100", "440145213546");
		map.put("bka004", "在职");
		map.put("bka026", "其他疾病");
		map.put("bka006", "普通门诊");
		map.put("aaz217", "0020011606060123");
		map.put("bka017", "2016-06-06");
		map.put("fund_pay_006", "200.00");
		map.put("fund_pay_999", "100.00");
		map.put("all_part_pay", "60.00");
		map.put("self_part_pay", "40.00");
		map.put("self_all_pay", "20.00");
		map.put("test1", "☑  测试1");
		map.put("test2", "□ 测试2");
		SQL1.add(map);

		Map mapData = new HashMap();
		mapData.put("SQL1", SQL1);
		mapData.put("SQL2", SQL2);

		PowerMarker pm = new PowerMarker("/Volumes/BOOTCAMP/del/clinicwork.xls");
		pm.render("/Volumes/BOOTCAMP/del/clinicwork_new.xls", mapData);

		try {
			Excel2Html.convertExcel2Html("/Volumes/BOOTCAMP/del/clinicwork_new.xls",
					"/Volumes/BOOTCAMP/del/clinicwork_new.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
