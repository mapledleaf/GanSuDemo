package com.powersi.demo;

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

public class TestPowerMarker extends TestBase {

	/**
	 * 测试渲染数据到excel
	 */
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	@Test
	public void testRenderExcel() {
		String localTemplateFile = "";
		String newExcelFile = "";
		// 准备list数据
		List<Map> SQL1 = new ArrayList<Map>();
		Map map1 = new HashMap();
		map1.put("xm", "李志钢01");
		map1.put("xb", "男");
		SQL1.add(map1);

		Map map2 = new HashMap();
		map2.put("xm", "李志钢02");
		map2.put("xb", "男");
		SQL1.add(map2);

		// 固定数据
		List<Map> SQL2 = new ArrayList<Map>();
		Map map = new HashMap();
		map.put("dw", "长沙创智");
		SQL2.add(map);

		Map mapData = new HashMap();
		mapData.put("SQL1", SQL1);
		mapData.put("TITLE", SQL2);

		PowerMarker pm = new PowerMarker("/Volumes/BOOTCAMP/del/temp.xls");
		pm.render("/Volumes/BOOTCAMP/del/new.xls", mapData);

		try {
			Excel2Html.convertExcel2Html("/Volumes/BOOTCAMP/del/new.xls", "/Volumes/BOOTCAMP/del/new.html");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

}
