package com.powersi.ssm.biz.medicare.inhospital.service;

import java.io.Serializable;
import java.util.Map;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 住院管理本地服务接口
 * 
 * @author 刘勇
 *
 */
public interface InhospitalManagerService extends Serializable {
	public String  createForegiftReport(String reportTemplateID, String bizID, int keepDays, Map mapData, 
			String report_comm,String user_name);
	
	/***
	 * 用于登记后，通过个人电脑号等查信息,将其它几个设置为空，不查询另外几个字段
	 * @param argName
	 * @param argValue
	 */
	public int resetArgNameInHospitalDTO(String argName,String argValue,InHospitalDTO inHospitalDTO);
	
	public int loadSysParamTips(InHospitalDTO inHospitalDTO,String name,Map mapParam);
}
