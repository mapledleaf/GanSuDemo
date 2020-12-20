package com.powersi.ssm.biz.medicare.hosp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.hosp.pojo.HospitalAccessLicenseDTO;
import com.powersi.biz.medicare.hosp.pojo.HospitalAccessPlanDTO;
import com.powersi.biz.medicare.hosp.pojo.HospitalAccessScheduleDTO;
import com.powersi.biz.medicare.hosp.pojo.HospitalAccessTypeDTO;
import com.powersi.biz.medicare.hosp.pojo.JdReportDTO;
import com.powersi.biz.medicare.hosp.service.api.HospAccessMgrBizService;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.jdreport.service.ApiJdReportService;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

@Action(value = "HospAccessMgrAction", results = {
		@Result(name = "queryAccessTypeList", location = "/pages/biz/medicare/hosp/QueryHospAccessTypeList.jsp"),
		@Result(name = "queryHospAccessTypeEdit", location = "/pages/biz/medicare/hosp/HospAccessTypeEdit.jsp"),
		@Result(name = "queryAccessPlanList", location = "/pages/biz/medicare/hosp/QueryHospAccessPlanList.jsp"),
		@Result(name = "queryAccessPlanListByBae300", location = "/pages/biz/medicare/hosp/HospAccessPlanList.jsp"),
		@Result(name = "queryHospAccessPlanEdit", location = "/pages/biz/medicare/hosp/HospAccessPlanEdit.jsp"),
		@Result(name = "queryAccessScheduleList", location = "/pages/biz/medicare/hosp/QueryHospAccessScheduleList.jsp"),
		@Result(name = "queryAccessScheduleListByBae310", location = "/pages/biz/medicare/hosp/HospAccessScheduleList.jsp"),
		@Result(name = "queryHospAccessScheduleEdit", location = "/pages/biz/medicare/hosp/HospAccessScheduleEdit.jsp"),
		@Result(name = "queryAccessLicenseList", location = "/pages/biz/medicare/hosp/QueryHospAccessLicenseList.jsp"),
		@Result(name = "queryHospAccessLicenseEdit", location = "/pages/biz/medicare/hosp/HospAccessLicenseEdit.jsp") })
public class HospAccessMgrAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private BeanService beanService;
	private static ApiJdReportService apiJdReportService = BeanHelper.getBean(ApiJdReportService.class);
	private HospitalAccessTypeDTO hospitalAccessTypeDTO;
	private HospitalAccessPlanDTO hospitalAccessPlanDTO;
	private HospitalAccessScheduleDTO hospitalAccessScheduleDTO;
	private HospitalAccessLicenseDTO hospitalAccessLicenseDTO;
	private JdReportDTO jdReportDTO;

	public JdReportDTO getJdReportDTO() {
		return jdReportDTO;
	}

	public void setJdReportDTO(JdReportDTO jdReportDTO) {
		this.jdReportDTO = jdReportDTO;
	}

	public HospAccessMgrBizService getHospAccessMgrBizService() {
		return hygeiaServiceManager.getBeanByClass(HospAccessMgrBizService.class, BizHelper.getAkb020());
	}

	public String saveJdReportInfo() {
		try {
			String param = getParameter("reportList");
			List<Map<String, Object>> reportList = JsonHelper.toList(param);
			jdReportDTO.setYybm(BizHelper.getAkb020());
			apiJdReportService.saveJdReportInfo(jdReportDTO, reportList);
			setJSONReturn("API接口开发与联调进度上报成功！");
		} catch (Exception e) {
			logger.error("保存API接口开发与联调进度上报失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("保存API接口开发与联调进度上报失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}

		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public String queryJdReportInfo() {
		try {
			JdReportDTO dto = new JdReportDTO();
			dto.setYybm(BizHelper.getAkb020());

			Map result = apiJdReportService.getJdReportInfo(dto);
			setJSONReturn(result);
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}

		return NONE;
	}

	/**
	 * 获取医院接入方式
	 */
	@SuppressWarnings("rawtypes")
	public String queryAccessTypeList() {
		try {

			if (WebHelper.isPostRequest(getRequest())) {

				PagerHelper.initPagination(getRequest());
				hospitalAccessTypeDTO.setAkb020(BizHelper.getAkb020());

				HashMap resultMap = getHospAccessMgrBizService().getHosAccessTypeList(hospitalAccessTypeDTO);

				List<HospitalAccessTypeDTO> hospitalAccessTypeDTORows = new ArrayList<HospitalAccessTypeDTO>();
				List list = (List) resultMap.get("accesstypeinfo");
				HospitalAccessTypeDTO tempDTO = null;
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						tempDTO = new HospitalAccessTypeDTO();
						beanService.copyProperties(list.get(i), tempDTO, true);
						hospitalAccessTypeDTORows.add(tempDTO);
					}
				}

				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(hospitalAccessTypeDTORows));

			} else {
				return "queryAccessTypeList";
			}

		} catch (Exception ex) {
			saveJSONError("查询医院接入方式信息出错！" + ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 保存医院接入方式
	 */
	public String saveAccessType() {
		try {
			hospitalAccessTypeDTO.setAkb020(BizHelper.getAkb020());
			hospitalAccessTypeDTO.setAkb021(BizHelper.getAkb021());
			hospitalAccessTypeDTO.setBae302(BizHelper.getUserName());

			getHospAccessMgrBizService().saveHosAccessType(hospitalAccessTypeDTO);
			setJSONReturn("保存医院接入方式成功！");
		} catch (Exception e) {
			logger.error("保存医院接入方式失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("保存医院接入方式失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public String queryHospAccessTypeEdit() {
		try {
			hospitalAccessTypeDTO.setAkb020(BizHelper.getAkb020());
			HashMap resultMap = getHospAccessMgrBizService().getHosAccessTypeList(hospitalAccessTypeDTO);
			List list = (List) resultMap.get("accesstypeinfo");
			if (list != null && list.size() > 0) {
				beanService.copyProperties(list.get(0), hospitalAccessTypeDTO, true);
			}
		} catch (Exception e) {
			saveJSONError("查询医院接入方式信息出错！" + e.getMessage());
		}

		return "queryHospAccessTypeEdit";
	}

	public String updateAccessType() {
		try {
			hospitalAccessTypeDTO.setAkb020(BizHelper.getAkb020());
			hospitalAccessTypeDTO.setAkb021(BizHelper.getAkb021());
			hospitalAccessTypeDTO.setBae302(BizHelper.getUserName());

			getHospAccessMgrBizService().updateHosAccessType(hospitalAccessTypeDTO);
			setJSONReturn("修改医院接入方式成功！");
		} catch (Exception e) {
			logger.error("修改医院接入方式失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("修改医院接入方式失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
	}

	/**
	 * 删除医院接入方式信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String delAccessType() {
		try {
			int iReturn = 0;
			String data = getParameter("data");
			if (UtilFunc.hasText(data)) {
				List<HospitalAccessTypeDTO> hospitalAccessTypeDTORows = new ArrayList<HospitalAccessTypeDTO>();

				List<Map<String, Object>> list = JsonHelper.toList(data);
				HospitalAccessTypeDTO tempDTO = null;
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						tempDTO = new HospitalAccessTypeDTO();
						beanService.copyProperties(list.get(i), tempDTO, true);
						hospitalAccessTypeDTORows.add(tempDTO);
					}
					iReturn = getHospAccessMgrBizService().deleteHosAccessType(hospitalAccessTypeDTORows,
							BizHelper.getAkb020());
				}

			}

			Map map = new HashMap();
			map.put("data", data);
			if (iReturn > 0) {
				setJSONReturn("删除医院接入方式信息成功！", map);
			} else {
				setJSONReturn("删除医院接入方式信息失败！", map);
			}

		} catch (Exception ex) {
			saveJSONError("删除医院接入方式信息出错！" + ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 获取医院接入计划
	 */
	@SuppressWarnings("rawtypes")
	public String queryAccessPlanList() {

		try {

			if (WebHelper.isPostRequest(getRequest())) {

				PagerHelper.initPagination(getRequest());

				HashMap resultMap = getHospAccessMgrBizService().getHosAccessPlanList(hospitalAccessPlanDTO,
						BizHelper.getAkb020());

				List<HospitalAccessPlanDTO> hospitalAccessPlanDTORows = new ArrayList<HospitalAccessPlanDTO>();
				List list = (List) resultMap.get("accessplaninfo");
				HospitalAccessPlanDTO tempDTO = null;
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						tempDTO = new HospitalAccessPlanDTO();
						beanService.copyProperties(list.get(i), tempDTO, true);
						hospitalAccessPlanDTORows.add(tempDTO);
					}
				}

				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(hospitalAccessPlanDTORows));

			} else {
				return "queryAccessPlanList";
			}

		} catch (Exception ex) {
			saveJSONError("查询医院接入计划信息出错！" + ex.getMessage());
			ex.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 根据医院接入流水号获取医院接入计划
	 */
	@SuppressWarnings("rawtypes")
	public String queryAccessPlanListByBae300() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {

				PagerHelper.initPagination(getRequest());

				HashMap resultMap = getHospAccessMgrBizService().getHosAccessPlanList(hospitalAccessPlanDTO,
						BizHelper.getAkb020());

				List<HospitalAccessPlanDTO> hospitalAccessPlanDTORows = new ArrayList<HospitalAccessPlanDTO>();
				List list = (List) resultMap.get("accessplaninfo");
				HospitalAccessPlanDTO tempDTO = null;
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						tempDTO = new HospitalAccessPlanDTO();
						beanService.copyProperties(list.get(i), tempDTO, true);
						hospitalAccessPlanDTORows.add(tempDTO);
					}
				}
				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(hospitalAccessPlanDTORows));
				setAttribute("hospitalAccessTypeDTO", hospitalAccessTypeDTO);
			} else {
				return "queryAccessPlanListByBae300";
			}
		} catch (Exception ex) {
			saveJSONError("根据接入方式流水号查询医院接入计划信息出错！" + ex.getMessage());
			ex.printStackTrace();
		}

		return NONE;
	}

	/**
	 * 保存医院接入计划
	 */
	public String saveAccessPlan() {
		try {
			hospitalAccessPlanDTO.setBae302(BizHelper.getUserName());
			getHospAccessMgrBizService().saveHosAccessPlan(hospitalAccessPlanDTO, BizHelper.getAkb020());
			setJSONReturn("保存医院接入计划成功！");
		} catch (Exception e) {
			logger.error("保存医院接入计划失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("保存医院接入计划失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public String queryHospAccessPlanEdit() {
		try {
			HashMap resultMap = getHospAccessMgrBizService().getHosAccessPlanList(hospitalAccessPlanDTO,
					BizHelper.getAkb020());
			List list = (List) resultMap.get("accessplaninfo");
			if (list != null && list.size() > 0) {
				beanService.copyProperties(list.get(0), hospitalAccessPlanDTO, true);
			}
		} catch (Exception e) {
			saveJSONError("查询医院接入信息出错！" + e.getMessage());
		}

		return "queryHospAccessPlanEdit";
	}

	public String updateAccessPlan() {
		try {

			getHospAccessMgrBizService().updateHosAccessPlan(hospitalAccessPlanDTO, BizHelper.getAkb020());
			setJSONReturn("修改医院接入计划成功！");
		} catch (Exception e) {
			logger.error("修改医院接入计划失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("修改医院接入计划失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
	}

	/**
	 * 删除医院接入计划信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String delAccessPlan() {
		try {
			int iReturn = 0;
			String data = getParameter("data");
			if (UtilFunc.hasText(data)) {
				List<HospitalAccessPlanDTO> hospitalAccessPlanDTORows = new ArrayList<HospitalAccessPlanDTO>();

				List<Map<String, Object>> list = JsonHelper.toList(data);
				HospitalAccessPlanDTO tempDTO = null;
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						tempDTO = new HospitalAccessPlanDTO();
						beanService.copyProperties(list.get(i), tempDTO, true);
						hospitalAccessPlanDTORows.add(tempDTO);
					}
					iReturn = getHospAccessMgrBizService().deleteHosAccessPlan(hospitalAccessPlanDTORows,
							BizHelper.getAkb020());
				}

			}

			Map map = new HashMap();
			map.put("data", data);
			if (iReturn > 0) {
				setJSONReturn("删除医院接入计划信息成功！", map);
			} else {
				setJSONReturn("删除医院接入计划信息失败！", map);
			}

		} catch (Exception ex) {
			saveJSONError("删除医院接入计划信息出错！" + ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 获取医院接入进度
	 */
	@SuppressWarnings("rawtypes")
	public String queryAccessScheduleList() {

		try {

			if (WebHelper.isPostRequest(getRequest())) {

				PagerHelper.initPagination(getRequest());

				HashMap resultMap = getHospAccessMgrBizService().getHosAccessScheduleList(hospitalAccessScheduleDTO,
						BizHelper.getAkb020());

				List<HospitalAccessScheduleDTO> hospitalAccessScheduleDTORows = new ArrayList<HospitalAccessScheduleDTO>();
				List list = (List) resultMap.get("accessscheduleinfo");
				HospitalAccessScheduleDTO tempDTO = null;
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						tempDTO = new HospitalAccessScheduleDTO();
						beanService.copyProperties(list.get(i), tempDTO, true);
						hospitalAccessScheduleDTORows.add(tempDTO);
					}
				}

				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(hospitalAccessScheduleDTORows));

			} else {
				return "queryAccessScheduleList";
			}

		} catch (Exception ex) {
			saveJSONError("查询医院接入进度信息出错！" + ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 根据接入计划流水号获取医院接入进度
	 */
	@SuppressWarnings("rawtypes")
	public String queryAccessScheduleListByBae310() {

		try {

			if (WebHelper.isPostRequest(getRequest())) {

				PagerHelper.initPagination(getRequest());

				HashMap resultMap = getHospAccessMgrBizService().getHosAccessScheduleList(hospitalAccessScheduleDTO,
						BizHelper.getAkb020());

				List<HospitalAccessScheduleDTO> hospitalAccessScheduleDTORows = new ArrayList<HospitalAccessScheduleDTO>();
				List list = (List) resultMap.get("accessscheduleinfo");
				HospitalAccessScheduleDTO tempDTO = null;
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						tempDTO = new HospitalAccessScheduleDTO();
						beanService.copyProperties(list.get(i), tempDTO, true);
						hospitalAccessScheduleDTORows.add(tempDTO);
					}
				}

				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(hospitalAccessScheduleDTORows));
				setAttribute("hospitalAccessPlanDTO", hospitalAccessPlanDTO);
			} else {
				return "queryAccessScheduleListByBae310";
			}

		} catch (Exception ex) {
			saveJSONError("查询医院接入进度信息出错！" + ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 保存医院接入进度
	 */
	public String saveAccessSchedule() {
		try {
			getHospAccessMgrBizService().saveHosAccessSchedule(hospitalAccessScheduleDTO, BizHelper.getAkb020());
			setJSONReturn("保存医院接入进度成功！");
		} catch (Exception e) {
			logger.error("保存医院接入进度失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("保存医院接入进度失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
	}

	/**
	 * 查询要修改的医院接入进度
	 */
	@SuppressWarnings("rawtypes")
	public String queryHospAccessScheduleEdit() {
		try {
			HashMap resultMap = getHospAccessMgrBizService().getHosAccessScheduleList(hospitalAccessScheduleDTO,
					BizHelper.getAkb020());
			List list = (List) resultMap.get("accessscheduleinfo");
			if (list != null && list.size() > 0) {
				beanService.copyProperties(list.get(0), hospitalAccessScheduleDTO, true);
			}
		} catch (Exception e) {
			saveJSONError("查询医院接入进度信息出错！" + e.getMessage());
		}

		return "queryHospAccessScheduleEdit";
	}

	/**
	 * 修改医院接入进度
	 */
	public String updateAccessSchedule() {
		try {
			getHospAccessMgrBizService().updateHosAccessSchedule(hospitalAccessScheduleDTO, BizHelper.getAkb020());
			setJSONReturn("修改医院接入进度成功！");
		} catch (Exception e) {
			logger.error("修改医院接入进度失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("修改医院接入进度失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
	}

	/**
	 * 删除医院接入进度信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String delAccessSchedule() {
		try {
			int iReturn = 0;
			String data = getParameter("data");
			if (UtilFunc.hasText(data)) {
				List<HospitalAccessScheduleDTO> hospitalAccessScheduleDTORows = new ArrayList<HospitalAccessScheduleDTO>();

				List<Map<String, Object>> list = JsonHelper.toList(data);
				HospitalAccessScheduleDTO tempDTO = null;
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						tempDTO = new HospitalAccessScheduleDTO();
						beanService.copyProperties(list.get(i), tempDTO, true);
						hospitalAccessScheduleDTORows.add(tempDTO);
					}
					iReturn = getHospAccessMgrBizService().deleteHosAccessSchedule(hospitalAccessScheduleDTORows,
							BizHelper.getAkb020());
				}

			}

			Map map = new HashMap();
			map.put("data", data);
			if (iReturn > 0) {
				setJSONReturn("删除医院接入进度信息成功！", map);
			} else {
				setJSONReturn("删除医院接入进度信息失败！", map);
			}

		} catch (Exception ex) {
			saveJSONError("删除医院接入进度信息出错！" + ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 获取医院接入许可
	 */
	@SuppressWarnings("rawtypes")
	public String queryAccessLicenseList() {

		try {

			if (WebHelper.isPostRequest(getRequest())) {

				PagerHelper.initPagination(getRequest());

				hospitalAccessLicenseDTO.setAkb020(BizHelper.getAkb020());
				hospitalAccessLicenseDTO.setAkb021(BizHelper.getAkb021());
				HashMap resultMap = getHospAccessMgrBizService().getHosAccessLicenseList(hospitalAccessLicenseDTO);

				List<HospitalAccessLicenseDTO> hospitalAccessLicenseDTORows = new ArrayList<HospitalAccessLicenseDTO>();
				List list = (List) resultMap.get("accesslicenseinfo");
				HospitalAccessLicenseDTO tempDTO = null;
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						tempDTO = new HospitalAccessLicenseDTO();
						beanService.copyProperties(list.get(i), tempDTO, true);
						hospitalAccessLicenseDTORows.add(tempDTO);
					}
				}

				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(hospitalAccessLicenseDTORows));

			} else {
				return "queryAccessLicenseList";
			}

		} catch (Exception ex) {
			saveJSONError("查询医院接入许可信息出错！" + ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 保存医院接入许可
	 */
	public String saveAccessLicense() {
		try {
			getHospAccessMgrBizService().saveHosAccessLicense(hospitalAccessLicenseDTO);
			setJSONReturn("保存医院接入许可成功！");
		} catch (Exception e) {
			logger.error("保存医院接入许可失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("保存医院接入许可失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public String queryHospAccessLicenseEdit() {
		try {
			hospitalAccessLicenseDTO.setAkb020(BizHelper.getAkb020());
			HashMap resultMap = getHospAccessMgrBizService().getHosAccessLicenseList(hospitalAccessLicenseDTO);
			List list = (List) resultMap.get("accesslicenseinfo");
			if (list != null && list.size() > 0) {
				beanService.copyProperties(list.get(0), hospitalAccessLicenseDTO, true);
			}
		} catch (Exception e) {
			saveJSONError("查询医院接入信息出错！" + e.getMessage());
		}

		return "queryHospAccessLicenseEdit";
	}

	public String updateAccessLicense() {
		try {

			getHospAccessMgrBizService().updateHosAccessLicense(hospitalAccessLicenseDTO);
			setJSONReturn("修改医院接入许可成功！");
		} catch (Exception e) {
			logger.error("修改医院接入许可失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("修改医院接入许可失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
	}

	/**
	 * 删除医院接入许可信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String delAccessLicense() {
		try {
			int iReturn = 0;
			String data = getParameter("data");
			if (UtilFunc.hasText(data)) {
				List<HospitalAccessLicenseDTO> hospitalAccessLicenseDTORows = new ArrayList<HospitalAccessLicenseDTO>();

				List<Map<String, Object>> list = JsonHelper.toList(data);
				HospitalAccessLicenseDTO tempDTO = null;
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						tempDTO = new HospitalAccessLicenseDTO();
						beanService.copyProperties(list.get(i), tempDTO, true);
						hospitalAccessLicenseDTORows.add(tempDTO);
					}
					iReturn = getHospAccessMgrBizService().deleteHosAccessLicense(hospitalAccessLicenseDTORows,
							BizHelper.getAkb020());
				}

			}

			Map map = new HashMap();
			map.put("data", data);
			if (iReturn > 0) {
				setJSONReturn("删除医院接入许可信息成功！", map);
			} else {
				setJSONReturn("删除医院接入许可信息失败！", map);
			}

		} catch (Exception ex) {
			saveJSONError("删除医院接入计划信息出错！" + ex.getMessage());
		}
		return NONE;
	}

	public HospitalAccessTypeDTO getHospitalAccessTypeDTO() {
		return hospitalAccessTypeDTO;
	}

	public void setHospitalAccessTypeDTO(HospitalAccessTypeDTO hospitalAccessTypeDTO) {
		this.hospitalAccessTypeDTO = hospitalAccessTypeDTO;
	}

	public HospitalAccessPlanDTO getHospitalAccessPlanDTO() {
		return hospitalAccessPlanDTO;
	}

	public void setHospitalAccessPlanDTO(HospitalAccessPlanDTO hospitalAccessPlanDTO) {
		this.hospitalAccessPlanDTO = hospitalAccessPlanDTO;
	}

	public HospitalAccessScheduleDTO getHospitalAccessScheduleDTO() {
		return hospitalAccessScheduleDTO;
	}

	public void setHospitalAccessScheduleDTO(HospitalAccessScheduleDTO hospitalAccessScheduleDTO) {
		this.hospitalAccessScheduleDTO = hospitalAccessScheduleDTO;
	}

	public HospitalAccessLicenseDTO getHospitalAccessLicenseDTO() {
		return hospitalAccessLicenseDTO;
	}

	public void setHospitalAccessLicenseDTO(HospitalAccessLicenseDTO hospitalAccessLicenseDTO) {
		this.hospitalAccessLicenseDTO = hospitalAccessLicenseDTO;
	}

}
