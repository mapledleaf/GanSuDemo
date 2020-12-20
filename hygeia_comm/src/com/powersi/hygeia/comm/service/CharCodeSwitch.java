package com.powersi.hygeia.comm.service;

import java.util.List;

public interface CharCodeSwitch {

	public String LoadWB(String strInput);

	public String LoadPY(String strInput);

	public String LoadCodeTable(String codeType, String displayValue);

	@SuppressWarnings("rawtypes")
	public List LoadCodeTableDetail(String codeType);

}
