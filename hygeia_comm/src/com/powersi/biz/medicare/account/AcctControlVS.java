package com.powersi.biz.medicare.account;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.IMediCalc;

public interface AcctControlVS extends Serializable {

	@SuppressWarnings("rawtypes")
	public List runDirect(Map inparm);

	@SuppressWarnings("rawtypes")
	public IMediCalc runDirectForReimburse(Map inparm);

	@SuppressWarnings("rawtypes")
	public IMediCalc mediCalcMZ(Map inparm);
	
	public IMediCalc mediCalc(IMediCalc imediCalc);

}
