package com.powersi.ssm.biz.medicare.medicaretag.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.medicarecommon.medicaretag.dto.MedicareChoosePersonDTO;
import com.powersi.biz.medicare.medicarecommon.medicaretag.service.MedicareChoosePersonService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;


/**
 * TS18112300024 - 【需求开发】结算云人员选择控件

 * <p>Title: MedicareChoosePersonAction</p>

 * <p>Description: 人员选择控件</p>

 * <p>Company: POWERSI</p> 

 * @author xiexiao

 * @date 2018年11月23日
 */
@Action(value = "MedicareChoosePersonAction", results = {
		@Result(name = "success", location = "/pages/biz/medicare/medicarecommon/medicaretag/medicareChoosePerson.jsp") })
@SuppressWarnings("all")
public class MedicareChoosePersonAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	private  MedicareChoosePersonDTO dto;
	
	@Autowired
	private MedicareChoosePersonService ser;
	
	/**
	 * TS18112300024 - 【需求开发】结算云人员选择控件
	 * @Description: 查询人员信息
	 * @author: xiexiao
	 * @date: 2018年11月22日
	 * @return
	 */
	public String getPersonInfo() {
		Map retMsg = null;
		int rowCount;
		Map param = getAllParameters();
		param.put("aaa027", BizHelper.getAaa027());
		List lst = ser.getPersonInfo(param);
		rowCount = lst.size();
		if (rowCount > 0) {
			if (rowCount == 1) {
				retMsg = (Map) lst.get(0);
				retMsg.put("rowsize", "1");
			} else {
				retMsg = new HashMap();
				retMsg.put("rowsize", rowCount);
			}
		} else {
			retMsg = new HashMap();
			retMsg.put("rowsize", "0");
		}
		try {
			setJSONReturn(retMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * TS18112300024 - 【需求开发】结算云人员选择控件
	 * @Description: 查询人员信息集合
	 * @author: xiexiao
	 * @date: 2018年11月22日
	 * @return
	 */
	public String getPersonList() {
		Map param = getAllParameters();
		param.put("aaa027", BizHelper.getAaa027());
		List rsPersonList = ser.getPersonInfo(param);
		setAttribute("rsPersonList", rsPersonList);
		return SUCCESS;
	}
	
	/**
	 * TS18112300024 - 【需求开发】结算云人员选择控件
	 * @Description: 读社保卡或者身份证+密码模式
	 * @author: xiexiao
	 * @date: 2018年11月23日
	 * @throws Exception
	 */
	public void checkCard() throws Exception {
		Map retMsg = null;
		int rowCount;

		Map param = getAllParameters();
		param.put("aaa027", BizHelper.getAaa027());
		param.put("akb020", BizHelper.getAkb020());

		// 获取人员信息
		List list = ser.getPersInfoByICCard(param);

		rowCount = list.size();

		if (rowCount > 0) {
			if (rowCount == 1) {
				retMsg = (Map) list.get(0);
				retMsg.put("rowsize", "1");
				if(param.get("card_no") != null && !"".equals(param.get("card_no"))) {
					retMsg.put("aaz500", param.get("card_no"));
				}
			} else {
				retMsg = new HashMap();
				retMsg.put("rowsize", rowCount);
			}
		} else {
			retMsg = new HashMap();
			retMsg.put("rowsize", "0");
		}
		try {
			setJSONReturn(retMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
