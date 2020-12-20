package com.powersi.ssm.biz.medicare.special.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.comm.service.FcBizPolicyService;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.special.pojo.SpecialManagerDTO;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.powerreport.service.PowerReport;
import com.powersi.powerreport.service.PowerReportDao;
import com.powersi.powerreport.service.PowerReportImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

/**
 * 
 * 住院管理本地服务实现类
 * 
 * @author 刘勇
 *
 */
@Service
public class SpecialManagerLocalServiceImpl implements SpecialManagerLocalService {

	private static final long serialVersionUID = 1L;

	@Override
	public String loadJsdModule(Map data,SpecialManagerDTO dto) {
		return loadJsdModule(dto,getTemplatePath(dto),data);
	}

	private String loadJsdModule(SpecialManagerDTO dto,String reportXLS,Map data){
		PowerReport pri = new PowerReportImpl();
		PowerReportDao prd = new PowerReportDao();
		String bizID = "spapp" + dto.getAaz267().toString();
		Map<String,List<Map>> reportData = new HashMap<>();
		List<Map> data1=(List<Map>) data.get("data1");
		reportData.put("data1", (List<Map>) data.get("data1"));
		Map<String, Object> checkMap = prd.getReportBaseInfoBizID(bizID);
		if (checkMap != null && checkMap.size()>0) {
			pri.delReport(null, bizID);
			DBHelper.commit();
		}
		String reportID = pri.createReport(reportXLS, bizID, 1, reportData, "特殊业务申请单", BizHelper.getUserName()); 
		return reportID;
	}
	
	private String getTemplatePath(SpecialManagerDTO dto){
		String rootPath;
		try {
			rootPath = URLDecoder.decode(this.getClass().getClassLoader().getResource("/").getFile() ,"utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new HygeiaException("未指明需要的模板！");
		}
		String commonPath = "special/applytype" + dto.getPageFlag() + "/common/";
		String customPath = "special/applytype" + dto.getPageFlag() + "/" + dto.getAaa027() +"/";
		String fileName = null;
		
		//获取跟目录
	//	rootPath = new File(new File(rootPath).getParent()).getParent()+"/report/";
		
		//拼接自定义模板路径
		switch(dto.getComFlag()){
			case "11":
				fileName = "batch_apply.xls";
				break;
			case "12":
				fileName = "apply_print.xls";
				break;
			case "101"://定点医院修改打印
				fileName = "fixhosp_print.xls";
				break;
			default:
				throw new HygeiaException("未指明需要的模板！");
		}
		
		//本地化模板不存在时，返回通用模板路径，都没有时报错
		if(new File(rootPath + customPath + fileName).exists()) {
			return customPath + fileName;
		} else if(new File(rootPath + commonPath + fileName).exists()) { 
			return commonPath + fileName;
		} else {
			throw new HygeiaException("找不到模板！");
		}
	}
	
}
