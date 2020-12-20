package com.powersi.sample.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;

import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.powerreport.service.PowerReportDao;
import com.powersi.powerreport.service.PowerReportImpl;
import com.powersi.sys.manager.dao.MenuDAO;

/**
 * 通用报表
 * @author "lingang"
 * @time 2016年7月11日下午8:53:28
 *
 */
@Action(value = "report")
public class ReportAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 生成菜单报表，作为通用报表界面展示
	 * @return
	 * String
	 */
	public String createReport(){
		try {
			PowerReportImpl pri = new PowerReportImpl();
			
			//判断bizID是否已经存在Map,存在先删除老数据，在新增
			PowerReportDao dao = new PowerReportDao();
			Map checkMap = dao.getReportBaseInfoBizID("1");
			if(checkMap != null) {
				pri.delReport(null, "1");
				DBHelper.commit();
			}
			
			MenuDAO menuDAO = BeanHelper.getBean(MenuDAO.class);
			List<Map<String,String>> allMenuList = menuDAO.getMenuTree("0");//获取所有菜单
			//准备list数据
			List<Map> SQL1 = new ArrayList<Map>();
			
			//遍历list在转map转存到sql1中
			for (int i = 0; i <= 100; i++) {
				String menuid =  String.valueOf(allMenuList.get(i).get("menu_id"));
				Map menuInfo = menuDAO.getMenuInfo(menuid);
				SQL1.add(menuInfo);
			}
			
			//固定数据
			List<Map> SQL2 = new ArrayList<Map>();
			Map map = new HashMap();
			map.put("dw", "长沙创智和宇");
			//制表日期
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = format.format(date);
			map.put("date", dateStr);
			//制表人
			UserInfo userInfo = BusiContext.getUserInfo();
			map.put("name", userInfo.getUserName());
			SQL2.add(map);
			
			Map mapData = new HashMap();
			mapData.put("SQL1", SQL1);	
			mapData.put("TITLE", SQL2);	
			
			//测试产生带数据的报表
			String reportID = pri.createReport("demo/sysMenuTemp.xls", "1", 5, mapData, "菜单报表","lingand");
			setJSONReturn("生成的报表ID为"+reportID,reportID);
		} catch (IOException e) {
			saveJSONError("生成菜单报表出错:" + e.getMessage(), e);
		}
		
		return NONE;
	}
}
