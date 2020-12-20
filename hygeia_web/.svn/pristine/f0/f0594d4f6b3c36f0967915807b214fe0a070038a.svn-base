package com.powersi.ssm.biz.medicare.inhospital.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;
import com.powersi.biz.medicare.hosp.service.api.HospApiService;
import com.powersi.biz.medicare.inhospital.pojo.AppointmentExceptionInfoDTO;
import com.powersi.biz.medicare.yygh.pojo.KE01;
import com.powersi.biz.medicare.yygh.pojo.KE02;
import com.powersi.biz.medicare.yygh.service.api.mcce.MCCEybws310001Service;
import com.powersi.biz.medicare.yygh.service.api.mcce.MCCEybws310002Service;
import com.powersi.biz.medicare.yygh.service.api.mcce.MCCEybws310003Service;
import com.powersi.biz.medicare.yygh.service.api.mcce.MCCEyygh001Service;
import com.powersi.biz.medicare.yygh.service.api.mcce.MCCEyygh003Service;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.PagerHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

/**
 * 
 * 预约挂号管理
 * 
 * @author zhos
 *
 */
@Action(value = "AppointmentManagerAction", results = {
		@Result(name = "initPage", location = "/pages/biz/medicare/inhospital/AppointmentManager.jsp"),
		@Result(name = "submitAppointment", location = "/pages/biz/medicare/inhospital/submitAppointment.jsp"),
		@Result(name = "saveAppointment", location = "/pages/biz/medicare/inhospital/saveAppointment.jsp") })
public class AppointmentManagerAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HygeiaServiceManager hygeiaServiceManager;

	private KE01 doctorSourceInfoDTO;

	private KE02 appointmentInfoDTO;

	private AppointmentExceptionInfoDTO appointmentExcepInfoDTO;

	private String akb020;

	/**
	 * 上传（保存）预约挂号号源(修改)
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String saveDoctorSourceInfos() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				getDoctorSourceInfoDTO().setAkb020(BizHelper.getAkb020());
				String jsonDoctorSource = URLDecoder.decode(getParameter("doctorsourceinfo"), "UTF-8");
				JSONArray jsonArray = JSONArray.fromObject(jsonDoctorSource);

				List<KE01> doctorSourceInfoDTORows = JSONArray.toList(jsonArray, KE01.class);
				for (KE01 doctorSourceInfo : doctorSourceInfoDTORows) {
					doctorSourceInfo.setAkb020(BizHelper.getAkb020());
				}
				MCCEyygh001Service mccEyygh001Service = hygeiaServiceManager.getBeanByClass(MCCEyygh001Service.class,
						BizHelper.getAkb020());
				mccEyygh001Service.insertKe01List(BizHelper.getAkb020(), doctorSourceInfoDTORows);
				setJSONReturn("上传预约挂号号源成功！", getDoctorSourceInfoDTO());
			}
		} catch (Exception e) {
			logger.error("上传预约挂号号源失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("上传预约挂号号源失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}
		return NONE;
	}

	/**
	 * 获取预约挂号（或取消预挂号）信息 (修改)
	 * 
	 * @return
	 */
	public String queryAppointmentInfos() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				PagerHelper.initPagination(getRequest());
				String bac002 = getParameter("bac002");
				String aac003 = getParameter("aac003");
				String bka504 = getParameter("bka504");
				String bae029 = getParameter("bae029");

				getAppointmentInfoDTO().setAkb020(BizHelper.getAkb020());
				getAppointmentInfoDTO().setBac002(bac002);
				getAppointmentInfoDTO().setAac003(aac003);
				getAppointmentInfoDTO().setBka504(bka504);
				getAppointmentInfoDTO().setBae029(bae029);
				MCCEybws310003Service service = hygeiaServiceManager.getBeanByClass(MCCEybws310003Service.class,
						BizHelper.getAkb020());
				List<KE02> appointmentInfoDTORows = service.selectKe02List(getAppointmentInfoDTO());
				setJSONReturn(appointmentInfoDTORows);
			}
		} catch (Exception e) {
			logger.error("获取医保预挂号（或取消预挂号）信息失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("获取医保预挂号（或取消预挂号）信息失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}

		return NONE;
	}

	/**
	 * 跳转至处理提交预约挂号(或取消预约挂号)界面(修改)
	 * 
	 * @return
	 */
	public String initSaveAppointmentInfo() {
		try {
			String bac003 = URLDecoder.decode(getAppointmentInfoDTO().getBac003(), "UTF-8");// 解决中文乱码
			String aae006 = URLDecoder.decode(getAppointmentInfoDTO().getAae006(), "UTF-8");// 解决中文乱码
			String aac003 = URLDecoder.decode(getAppointmentInfoDTO().getAac003(), "UTF-8");// 解决中文乱码
			String bae031 = URLDecoder.decode(getAppointmentInfoDTO().getBae031(), "UTF-8");// 解决中文乱码

			getAppointmentInfoDTO().setBac003(bac003);
			getAppointmentInfoDTO().setAae006(aae006);
			getAppointmentInfoDTO().setAac003(aac003);
			getAppointmentInfoDTO().setBae031(bae031);

			setAttribute("appointmentInfoDTO", getAppointmentInfoDTO());
			return "saveAppointment";
		} catch (Exception e) {
			logger.error("初始化加载预约挂号管理页面信息失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveError(e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 取消预挂号 (修改)
	 * 
	 * @return
	 */
	public String saveAppointmentInfo() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				getAppointmentInfoDTO().setAkb020(BizHelper.getAkb020());
				getAppointmentInfoDTO().setBka504("2");
				MCCEybws310002Service service = hygeiaServiceManager.getBeanByClass(MCCEybws310002Service.class,
						BizHelper.getAkb020());
				service.saveOrCancelYygh(getAppointmentInfoDTO());
				setJSONReturn("取消预挂号成功！", getAppointmentInfoDTO());
			}
		} catch (Exception e) {
			logger.error("或取消预挂号失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("取消预挂号失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}

		return NONE;
	}

	/**
	 * 上传预约挂号停诊异动信息(修改)
	 * 
	 * @return
	 */
	public String saveAppointmentExcepInfos() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				getAppointmentInfoDTO().setAkb020(BizHelper.getAkb020());
				MCCEyygh003Service service = hygeiaServiceManager.getBeanByClass(MCCEyygh003Service.class,
						BizHelper.getAkb020());
				List<KE02> appointmentExceptionDTORows = new ArrayList<>();
				appointmentExceptionDTORows.add(getAppointmentInfoDTO());
				service.saveAppointmentExceptionInfos(getAppointmentInfoDTO(), appointmentExceptionDTORows);
				setJSONReturn("上传预约挂号停诊异动信息成功！", getAppointmentExcepInfoDTO());
			}

		} catch (Exception e) {
			logger.error("上传预约挂号停诊异动信息失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("上传预约挂号停诊异动信息失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}

		return NONE;
	}

	/**
	 * 获取预约挂号号源信息(修改)
	 * 
	 * @return
	 */
	public String queryDoctorSourceInfos() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				PagerHelper.initPagination(getRequest());
				getDoctorSourceInfoDTO().setAkb020(BizHelper.getAkb020());
				MCCEybws310001Service service = hygeiaServiceManager.getBeanByClass(MCCEybws310001Service.class,
						BizHelper.getAkb020());
				List<KE01> doctorSourceInfoDTORows = service.selectKe01List(getDoctorSourceInfoDTO());
				setJSONReturn(doctorSourceInfoDTORows);
			}
		} catch (Exception e) {
			logger.error("获取医保预挂号号源信息失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("获取医保预挂号号源信息失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}

		return NONE;
	}

	public String initPage() {
		try {
			loadCodeSelectData();
			return "initPage";
		} catch (Exception e) {
			logger.error("初始化加载预约挂号管理页面信息失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveError(e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 加载控件值
	 */
	@SuppressWarnings("rawtypes")
	void loadCodeSelectData() {
		Map bka019List = new LinkedHashMap();
		Map bka503List = new LinkedHashMap();
		loadBka019List(bka019List);
		setAttribute("bka019List", bka019List);
		setAttribute("bka503List", bka503List);
	}

	/**
	 * 加载科室
	 * 
	 * @param bka019List
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	void loadBka019List(Map bka019List) {
		HospApiService hospApiService = hygeiaServiceManager.getBeanByClass(HospApiService.class,
				BizHelper.getAkb020());
		HospInfoDTO hospInfoDto = new HospInfoDTO();
		hospInfoDto.setAkb020(BizHelper.getAkb020());
		hospInfoDto.setAae016("1");
		hospInfoDto.setAae100("1");
		List<HospInfoDTO> deptRows = hospApiService.queryHospDept(BizHelper.getAkb020(), hospInfoDto);
		if (deptRows != null) {
			for (HospInfoDTO deptRow : deptRows) {
				bka019List.put(deptRow.getBkc156(), deptRow.getBkc157());
			}
		}
	}

	/**
	 * 加载医保医师
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String loadBka503List() {
		try {
			Map bka503List = new HashMap();
			if (StringUtils.isNotBlank(doctorSourceInfoDTO.getAaz307())) {
				HospApiService hospApiService = hygeiaServiceManager.getBeanByClass(HospApiService.class,
						BizHelper.getAkb020());
				HospInfoDTO hospInfoDto = new HospInfoDTO();
				hospInfoDto.setAkb020(BizHelper.getAkb020());
				hospInfoDto.setAae016("1");
				hospInfoDto.setAae100("1");
				hospInfoDto.setBkc156(doctorSourceInfoDTO.getAaz307());
				List<HospInfoDTO> doctorRows = hospApiService.queryHospDoctor(BizHelper.getAkb020(), hospInfoDto);
				if (doctorRows != null) {
					for (HospInfoDTO doctorRow : doctorRows) {
						bka503List.put(doctorRow.getBkc274() + "|" + doctorRow.getBkc269(), doctorRow.getBkc275());
					}
				}
			}
			setJSONReturn(bka503List);
		} catch (Exception e) {
			logger.error("加载医保医生信息错误：" + ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 加载医生职称专业
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String loadDoctorZcAndZyList() {
		try {
			Map zcAndZyList = new HashMap();
			String bkc269 = getParameter("bkc269");
			if (StringUtils.isNotBlank(bkc269)) {
				HospApiService hospApiService = hygeiaServiceManager.getBeanByClass(HospApiService.class,
						BizHelper.getAkb020());
				HospInfoDTO hospInfoDto = new HospInfoDTO();
				hospInfoDto.setAkb020(BizHelper.getAkb020());
				hospInfoDto.setAae016("1");
				hospInfoDto.setAae100("1");
				hospInfoDto.setBkc269(bkc269);
				List<HospInfoDTO> doctorRows = hospApiService.queryHospDoctor(BizHelper.getAkb020(), hospInfoDto);
				if (doctorRows != null && doctorRows.size() > 0) {
					if (doctorRows.size() == 1) {
						String bkc272 = doctorRows.get(0).getBkc272();
						String bkc281 = doctorRows.get(0).getBkc281();
						String bkc291 = doctorRows.get(0).getBkc291();
						zcAndZyList.put(CodetableMapping.getDisplayValue("bkc272", bkc272, bkc272),
								bkc281 + "|" + bkc291);
					} else {
						throw new Exception("通过档案号【" + bkc269 + "】查询到多条医生数据！");
					}
				}
			}
			setJSONReturn(zcAndZyList);
		} catch (Exception e) {
			logger.error("加载医保医生信息错误：" + ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 跳转至提交预约挂号界面 （修改）
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String toSubmitAppointment() {
		try {
			String bae032 = getDoctorSourceInfoDTO().getBae032();
			String arry[] = (bae032 != null ? bae032.split(",") : new String[] {});
			Map bka030List = new LinkedHashMap();
			int i = 0;
			if (arry.length > 0) {
				for (String string : arry) {
					bka030List.put("bka030_" + i, string);
					i++;
				}
			}
			String aac003 = URLDecoder.decode(getDoctorSourceInfoDTO().getAac003(), "UTF-8");// 解决中文乱码
			String aaz386 = URLDecoder.decode(getDoctorSourceInfoDTO().getAaz386(), "UTF-8");// 解决中文乱码
			String bae031 = URLDecoder.decode(getDoctorSourceInfoDTO().getBae031(), "UTF-8");// 解决中文乱码
			getDoctorSourceInfoDTO().setAac003(aac003);
			getDoctorSourceInfoDTO().setAaz386(aaz386);
			getDoctorSourceInfoDTO().setBae031(bae031);
			setAttribute("bka030List", bka030List);
			setAttribute("doctorSourceInfoDTO", getDoctorSourceInfoDTO());

			return "submitAppointment";
		} catch (Exception e) {
			logger.error("初始化加载预约挂号管理页面信息失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveError(e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 提交预约挂号请求(修改)
	 * 
	 * @return
	 */
	public String submitAppointment() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				getAppointmentInfoDTO().setAkb020(BizHelper.getAkb020());
				getAppointmentInfoDTO().setChannel_tag("sys");
				MCCEybws310002Service service = hygeiaServiceManager.getBeanByClass(MCCEybws310002Service.class,
						BizHelper.getAkb020());
				service.saveOrCancelYygh(getAppointmentInfoDTO());
				setJSONReturn("提交预约挂号请求成功！", getAppointmentInfoDTO());
			}
		} catch (Exception e) {
			logger.error("提交预约挂号请求失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError("提交预约挂号请求失败！\r\n" + (e.getMessage() == null ? "" : e.getMessage()));
		}

		return NONE;
	}

	public KE01 getDoctorSourceInfoDTO() {
		if (doctorSourceInfoDTO == null) {
			doctorSourceInfoDTO = new KE01();
		}
		return doctorSourceInfoDTO;
	}

	public void setDoctorSourceInfoDTO(KE01 doctorSourceInfoDTO) {
		this.doctorSourceInfoDTO = doctorSourceInfoDTO;
	}

	public KE02 getAppointmentInfoDTO() {
		if (appointmentInfoDTO == null) {
			appointmentInfoDTO = new KE02();
		}
		return appointmentInfoDTO;
	}

	public void setAppointmentInfoDTO(KE02 appointmentInfoDTO) {
		this.appointmentInfoDTO = appointmentInfoDTO;
	}

	public AppointmentExceptionInfoDTO getAppointmentExcepInfoDTO() {
		if (appointmentExcepInfoDTO == null) {
			appointmentExcepInfoDTO = new AppointmentExceptionInfoDTO();
		}
		return appointmentExcepInfoDTO;
	}

	public void setAppointmentExcepInfoDTO(AppointmentExceptionInfoDTO appointmentExcepInfoDTO) {
		this.appointmentExcepInfoDTO = appointmentExcepInfoDTO;
	}

	public String getAkb020() {
		return akb020;
	}

	public void setAkb020(String akb020) {
		this.akb020 = akb020;
	}

}
