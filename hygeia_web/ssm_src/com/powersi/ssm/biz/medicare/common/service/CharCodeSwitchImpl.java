package com.powersi.ssm.biz.medicare.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.powersi.hygeia.comm.service.CharCodeSwitch;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.UtilFunc;

@Service
public class CharCodeSwitchImpl implements CharCodeSwitch {

	@Override
	public String LoadWB(String strInput) {
		return DBHelper.executeScale("SELECT uf_code_wb('" + strInput + "')").toString();
	}

	@Override
	public String LoadPY(String strInput) {
		return DBHelper.executeScale("SELECT uf_code_py('" + strInput + "')").toString();
	}

	@Override
	public String LoadCodeTable(String codeType, String displayValue) {
		Object obj = DBHelper.executeScale("SELECT data_value from sys_code_table_detail where CODE_TYPE = '" + codeType
				+ "' and DISPLAY_VALUE = '" + displayValue + "'  ");
		if (UtilFunc.isBlank(obj)) {
			return "";
		} else {
			return obj.toString();
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List LoadCodeTableDetail(String codeType) {
		return DBHelper.executeList("select * from sys_code_table_detail where code_type = '" + codeType + "'");
	}

}
