package com.powersi.ssm.biz.medicare.diagnose.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.diagnose.service.ElectronicPrescriptionService;
import com.powersi.comm.service.memory.MemoryDB;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

@Action(value = "ElectronicPrescriptionAction", results = {
		@Result(name = "chooseErx", location = "/pages/biz/medicare/diagnose/ChooseElectronicPrescription.jsp") })

public class ElectronicPrescriptionAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	private DiagnoseInfoDTO diagnoseInfoDTO;
	@Autowired
	private MemoryDB memoryDB;

	/**
	 * 通过aac001查处方
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryErx() {
		try {
			diagnoseInfoDTO.setAkb020(BizHelper.getAkb020());
			ElectronicPrescriptionService erxService = (ElectronicPrescriptionService) this.hygeiaServiceManager
					.getBean("ElectronicPrescriptionService", diagnoseInfoDTO.getAkb020());
			List erxlist = erxService.queryErx(diagnoseInfoDTO);

			if (erxlist != null && erxlist.size() > 0) {
				getRequest().setAttribute("selectErxList", erxlist);
			} else {
				saveJSONError("没有符合条件的处方信息！");
			}
		} catch (Exception e) {
			logger.error("查询人员业务信息出错！错误信息：" + ToolUtil.getExceptionInfo(e));
			this.saveJSONError(e.getMessage());
		}
		return "chooseErx";
	}

	/**
	 * 通过ake1id查处方明细
	 * 
	 * @return
	 */
	public String getErxDetail() {
		try {
			if (this.isPostRequest()) {
				String ake1id = getParameter("ake1id");
				String akb020 = BizHelper.getAkb020();
				DiagnoseInfoDTO diagnoseInfoDTO = new DiagnoseInfoDTO();
				diagnoseInfoDTO.setAkb020(akb020);

				ElectronicPrescriptionService erxService = (ElectronicPrescriptionService) this.hygeiaServiceManager
						.getBean("ElectronicPrescriptionService", diagnoseInfoDTO.getAkb020());
				erxService.getErxDetail(diagnoseInfoDTO, ake1id);
			}
			return NONE;
		} catch (Exception e) {
			this.saveError(e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 检索药品是否在缓存中
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryErxMemoryDB() {
		try {
			Map<String, String> retMsg = new HashMap<String, String>();
			retMsg.put("suss", "0");
			String ake001 = getParameter("ake001");
			String ake1id = getParameter("ake1id");
			// 获得ake1id的所有电子处方明细
			Map<String, Map> ErxList = (Map<String, Map>) this.memoryDB.getMap("MAP_DZCF_" + ake1id);
			if (ErxList == null) {
				throw new HygeiaException("电子处方明细缓存为空(" + "MAP_DZCF_" + ake1id + ")!");
			}
			if ((ErxList.get(ake001)) == null) {
				retMsg.put("suss", "1");
				retMsg.put("bka511", "A000_100");
				retMsg.put("bka512", "A000_100");
			}
			setJSONReturn(retMsg);
		} catch (Exception e) {
			this.saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 在试算和收费前获取就医登记号
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getAaz217() {
		try {
			diagnoseInfoDTO.setAkb020(BizHelper.getAkb020());
			ElectronicPrescriptionService erxService = (ElectronicPrescriptionService) this.hygeiaServiceManager
					.getBean("ElectronicPrescriptionService", diagnoseInfoDTO.getAkb020());

			String straaz217 = erxService.getAaz217(diagnoseInfoDTO);
			Map mapReturn = new HashMap();
			mapReturn.put("aaz217", straaz217);
			setJSONReturn(mapReturn);
		} catch (Exception e) {
			this.saveError(e.getMessage());
			return ERROR;
		}
		return NONE;
	}

	public DiagnoseInfoDTO getDiagnoseInfoDTO() {
		return diagnoseInfoDTO;
	}

	public void setDiagnoseInfoDTO(DiagnoseInfoDTO diagnoseInfoDTO) {
		this.diagnoseInfoDTO = diagnoseInfoDTO;
	}
}
