package com.powersi.ssm.biz.medicare.comminter.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.comminter.service.FeeManageService;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

import net.sf.json.JSONArray;

/**
 * 个性化通用接口--查询费用明细
 * 
 * @author laihaiyan
 */

@Action(value = "DetermineFeeAction", results = {
		@Result(name = "success", location = "/pages/biz/medicare/comminter/determineFee.jsp"),
		@Result(name = "success", location = "/pages/biz/medicare/comminter/determineFee.jsp") })
public class DetermineFeeAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	private InHospitalDTO inHospitalDTO;

	public InHospitalDTO getInHospitalDTO() {
		return inHospitalDTO;
	}

	public void setInHospitalDTO(InHospitalDTO inHospitalDTO) {
		this.inHospitalDTO = inHospitalDTO;
	}

	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;

	@SuppressWarnings("unchecked")
	public String queryAac002() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				PagerHelper.initPagination(getRequest());
				if (inHospitalDTO == null) {
					inHospitalDTO = new InHospitalDTO();
				}
				inHospitalDTO.setAkb020(BizHelper.getAkb020());
				FeeManageService feeManagerService = this.hygeiaServiceManager.getBeanByClass(FeeManageService.class,
						inHospitalDTO.getAkb020());
				List<InHospitalDTO> inHospitalDTOList = feeManagerService.selectByAac002(inHospitalDTO);
				for (int i = 0; i < inHospitalDTOList.size(); i++) {
					inHospitalDTOList.get(i).setBka017(inHospitalDTO.getBka017());
					inHospitalDTOList.get(i).setKcd1id(inHospitalDTO.getKcd1id());
					inHospitalDTOList.get(i).setAaz217(inHospitalDTO.getAaz217());
				}
				if (inHospitalDTOList.size() == 0) {
					throw new HygeiaException("在ke22表中未查到相关数据");
				}
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(inHospitalDTOList));
			} else {
				return "success";
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public String queryFeeInfo() {
		try {
			if (inHospitalDTO == null) {
				inHospitalDTO = new InHospitalDTO();
			}
			inHospitalDTO.setAkb020(BizHelper.getAkb020());
			FeeManageService feeManagerService = this.hygeiaServiceManager.getBeanByClass(FeeManageService.class,
					inHospitalDTO.getAkb020());
			PagerHelper.initPagination(getRequest());
			this.inHospitalDTO.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			this.inHospitalDTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());
			String data = getParameter("data");
			String jsonFee = URLDecoder.decode(data, "UTF-8");
			JSONArray jsonArray = JSONArray.fromObject(jsonFee);
			List<InHospitalDTO> inHospitalDTOList = JSONArray.toList(jsonArray, InHospitalDTO.class);
			List<Map> inHospitalDTOListAll = feeManagerService.selectByBka446(inHospitalDTOList, inHospitalDTO);
			setJSONReturn(inHospitalDTOListAll);
		} catch (Exception e) {
			this.saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/*
	 * 保存费用明细
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public String saveFee() {
		try {
			if (this.isPostRequest()) {
				PagerHelper.initPagination(this.getRequest());
				if (inHospitalDTO == null) {
					inHospitalDTO = new InHospitalDTO();
				}
				String data = getParameter("data");
				String jsonFee = URLDecoder.decode(data, "UTF-8");
				JSONArray jsonArray = JSONArray.fromObject(jsonFee);
				List<InHospitalDTO> listFeeInfoDTORows = JSONArray.toList(jsonArray, InHospitalDTO.class);
				inHospitalDTO.setAkb020(BizHelper.getAkb020());
				FeeManageService feeManageService = this.hygeiaServiceManager.getBeanByClass(FeeManageService.class,
						inHospitalDTO.getAkb020());
				int msg = feeManageService.saveFeeInfo(listFeeInfoDTORows, inHospitalDTO);
				Map retMsg = new HashMap();
				if (msg == 1) {
					retMsg.put("suss", "1");
					retMsg.put("message", "保存成功");
				} else {
					retMsg.put("suss", "0");
					retMsg.put("message", "保存失败");
				}
				setJSONReturn(retMsg);
			}
		} catch (Exception e) {
			this.saveJSONError(e.getMessage());
		}
		return NONE;
	}

}
