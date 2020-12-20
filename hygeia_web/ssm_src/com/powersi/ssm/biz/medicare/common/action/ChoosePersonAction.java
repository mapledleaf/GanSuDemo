package com.powersi.ssm.biz.medicare. common.action;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.comm.utils.ToolUtil;
import com.powersi.biz.medicare.comm.service.ChoosePersonVS;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

@Action(value = "ChoosePersonAction", results={
	}
)
public class ChoosePersonAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	HygeiaServiceManager hygeiaServiceManager;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("rawtypes")
	public String getPersonInfo() {
		
		String querystring = getParameter("querystring");
		if(!querystring.startsWith("ac01")){
			querystring = getQueryCause(querystring);
		}
		
		try{
			ChoosePersonVS choosePersonVS = (ChoosePersonVS) hygeiaServiceManager.getBean("choosePersonVS", BizHelper.getAkb020());
			List lstPerson = choosePersonVS.getPerson(querystring);
			setJSONReturn(lstPerson);
			return NONE;
		}catch(Exception ex){
			logger.error( "取人员信息失败！错误信息：" + ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex);
			return ERROR;
		}
	}
	
	public String getQueryCause(String querystring) {
		String ret = "";
		if(isNumeric(querystring)){
			if(querystring.length() <= 10){
				ret = "ac01.aac001 = '"+querystring+"'";
			}else{
				ret = "ac01.aac002 = '"+querystring+"'";
			}
		}else{
			ret = "ac01.aac002 = '"+querystring+"'";
		}
		return ret;
	}
	
	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
}
