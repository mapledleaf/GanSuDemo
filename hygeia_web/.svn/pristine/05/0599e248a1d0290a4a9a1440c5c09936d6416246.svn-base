package com.powersi.ssm.biz.medicare.hosp.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.powersi.biz.medicare.catalog.pojo.HospCataDTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.hosp.pojo.ExpertInfoDTO;
import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;
import com.powersi.biz.medicare.hosp.pojo.Kzh0422Dto;
import com.powersi.biz.medicare.hosp.pojo.Kzh04Dto;
import com.powersi.biz.medicare.hosp.pojo.TreatmentDTO;
import com.powersi.biz.medicare.hosp.service.api.ExpertManagerService;
import com.powersi.biz.medicare.hosp.service.api.HospApiService;
import com.powersi.biz.medicare.hosp.service.api.HospManagerService;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.entity.AZE1;
import com.powersi.hygeia.comm.service.CharCodeSwitch;
import com.powersi.hygeia.comm.service.FileDownLoad;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.comm.service.SaveFileService;
import com.powersi.hygeia.framework.CodetableMapping;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.PaginationHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.ssm.biz.medicare.api.service.mcce.MCCEHospApiServiceImpl;
import com.powersi.ssm.biz.medicare.common.service.CharCodeSwitchImpl;
import com.powersi.ssm.biz.medicare.common.service.SaveFileServiceImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.hosp.service.HospService;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;
import com.powersi.sys.util.StringUtil;

import net.sf.json.JSONArray;

@Action(value = "HospManageAction", results = {
		@Result(name = "success", location = "/pages/biz/medicare/hosp/HospAreaManage.jsp"),
		@Result(name = "areaNew", location = "/pages/biz/medicare/hosp/HospAreaNew.jsp"),
		@Result(name = "areaEdit", location = "/pages/biz/medicare/hosp/HospAreaDetail.jsp"),
		@Result(name = "hospDept", location = "/pages/biz/medicare/hosp/HospDeptManage.jsp"),
		@Result(name = "deptNew", location = "/pages/biz/medicare/hosp/HospDeptNew.jsp"),
		@Result(name = "deptEdit", location = "/pages/biz/medicare/hosp/HospDeptDetail.jsp"),
		@Result(name = "hospDoctor", location = "/pages/biz/medicare/hosp/HospDoctorManage.jsp"),
		@Result(name = "hospBed", location = "/pages/biz/medicare/hosp/HospBedManage.jsp"),
		@Result(name = "bedNew", location = "/pages/biz/medicare/hosp/HospBedNew.jsp"),
		@Result(name = "bedDr", location = "/pages/biz/medicare/hosp/HospBedDr.jsp"),
		@Result(name = "bedEdit", location = "/pages/biz/medicare/hosp/HospBedDetail.jsp"),
		@Result(name = "doctorNew", location = "/pages/biz/medicare/hosp/HospDoctorNew.jsp"),
		@Result(name = "doctorDr", location = "/pages/biz/medicare/hosp/HospDoctorDr.jsp"),
		@Result(name = "doctorEdit", location = "/pages/biz/medicare/hosp/HospDoctorDetail.jsp"),
		@Result(name = "feeSetMealIndex", location = "/pages/biz/medicare/hosp/FeeSetMealIndex.jsp"),
		@Result(name = "feeSetMealDetail", location = "/pages/biz/medicare/hosp/FeeSetMealDetail.jsp"),
		@Result(name = "expert", location = "/pages/biz/medicare/hosp/ExpertManage.jsp"),
		@Result(name = "changsApply", location = "/pages/biz/medicare/hosp/HospDoctorChangsApply.jsp"),
		@Result(name = "expertDetail", location = "/pages/biz/medicare/hosp/ExpertNew.jsp"), })
public class HospManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private HospInfoDTO hospInfoDto;
	private HospCataDTO hospCataDto;
	private File imgFile;// 上传文件
	private String imgFileName;// 上传文件名称
	private HospService ser = getBean(HospService.class);
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private BeanService beanService;
	private ExpertInfoDTO expertInfoDto;

	/**
	 * 查询病区信息
	 * 
	 * @return
	 */
	public String queryHospAreaInfo() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				PagerHelper.initPagination(getRequest());
				if (hospInfoDto == null) {
					hospInfoDto = new HospInfoDTO();
				}
				String akb020 = BizHelper.getAkb020();
				HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
				hospInfoDto.setAkb020(akb020);
				hospInfoDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				hospInfoDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());

				ListResult listResult = hospService.queryAreaPage(akb020, hospInfoDto);

				PaginationHelper.getPaginationObj().setCount(listResult.getCount());
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));

			} else {
				return "success";
			}
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 新增科室信息前，查询病区信息
	 * 
	 * @return
	 */
	public String queryDeptForArea() {
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			String bkc156 = URLDecoder.decode(getParameter("bkc156"), "UTF-8");
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);
			hospInfoDto.setBkc156(bkc156);

			List<HospInfoDTO> detail = hospService.queryHospDept(akb020, hospInfoDto);
			if (detail.size() > 0) {
				hospInfoDto = detail.get(0);
			}
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}

		return "areaNew";
	}

	/**
	 * 保存新增病区信息
	 * 
	 * @return
	 */
	public String saveHospArea() {
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);

			int retSize = ser.checkExist(hospInfoDto.getAkb020(), hospInfoDto.getBkc153(), "area", hospService);

			if (retSize > 0) {
				setJSONReturn("病区编码已经存在，请检查！");
			} else {
				hospService.saveHospArea(akb020, hospInfoDto);
				setJSONReturn("保存病区信息成功！");
			}

		} catch (Exception ex) {
			logger.error("保存病区信息失败！错误信息：" + ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 删除未审核的病区信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String delHospArea() {
		try {
			int i = 0;
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);

			String data = getParameter("data");
			if (UtilFunc.hasText(data)) {
				i = hospService.deleteArea(hospInfoDto, JsonHelper.toList(data));
			}

			Map map = new HashMap();
			map.put("data", data);

			if (i > 0) {
				setJSONReturn("删除病区信息成功！", map);
			} else {
				setJSONReturn("删除病区信息失败！", map);
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询科室信息
	 * 
	 * @return
	 */
	public String queryHospDeptInfo() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				if (hospInfoDto == null) {
					hospInfoDto = new HospInfoDTO();
				}
				PagerHelper.initPagination(getRequest());
				hospInfoDto.setSortOrder(PagerHelper.getPaginationObj().getSort());
				String akb020 = BizHelper.getAkb020();
				String aaa027 = BizHelper.getAaa027();
				hospInfoDto.setAaa027(aaa027);
				HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
				hospInfoDto.setAkb020(akb020);
				
				DataGridHelper.render(getRequest(), getResponse(),
						PagerHelper.getPaginatedList(hospService.queryHospDept(akb020, hospInfoDto)));

				
			} else {
				return "hospDept";
			}
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 保存新增科室信息
	 * 
	 * @return
	 */
	public String saveHospDept() {
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);

			int retSize = ser.checkExist(hospInfoDto.getAkb020(), hospInfoDto.getBkc156(), "dept", hospService);
			if (retSize > 0) {
				setJSONReturn("科室编号已经存在！");
			} else {
				hospService.saveHospDept(akb020, hospInfoDto);
				setJSONReturn("保存科室信息成功！");
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	public String updateHospDept() {
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}

			String akb020 = BizHelper.getAkb020();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);
			//TS19111300038   结算云科室信息修改之后，需要同步信息到中心端医疗库的kac7表
			//修改描述 bkc029为维护时间，对应xt--db库的kac7,之前是时间戳1970年的时间，现改为当前修改时间，符合页面逻辑，而且bka044设为0，方便trans传输
			//修改人，时间  医保二部--赵银溪 2019/11/18
			Date  date = new Date();
			hospInfoDto.setBkc029(date);
			hospInfoDto.setBka044("0");

			hospService.updateHospDept(akb020, hospInfoDto);
			setJSONReturn("修改科室信息成功！");

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;

	}

	/**
	 * 删除未审核的科室信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String delHospDept() {
		try {
			
            int i = 0;
			
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String data = getParameter("data");
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			hospInfoDto.setAaa027(aaa027);
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);
			
			//判断该科室下是否有未删除的病区
			Map mm=JsonHelper.toList(data).get(0);
			String BKC156 =mm.get("bkc156").toString();
			hospInfoDto.setBkc156(BKC156);
			hospInfoDto.setAae100("1");
			List<HospInfoDTO> a=hospService.queryHospArea(akb020, hospInfoDto);
			if(a.size()>0){
				setJSONReturn("请先删除该科室下的所有病区！");
				return NONE;
			}
			
			
			if (UtilFunc.hasText(data)) {
				i = hospService.delHospDeptAll(hospInfoDto, JsonHelper.toList(data));
			}

			Map map = new HashMap();
			map.put("data", data);

			if (i > 0) {
				setJSONReturn("删除科室信息成功！", map);
			} else {
				setJSONReturn("删除科室信息失败！", map);
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 新增科室信息前，查询病区信息
	 * 
	 * @return
	 */
	public String queryHospAreaForDept() {
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);
			List<HospInfoDTO> detail = hospService.queryHospArea(akb020, hospInfoDto);
			if (detail.size() > 0) {
				hospInfoDto = detail.get(0);
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}

		return "deptNew";
	}

	/**
	 * 查询需要修改的科室信息
	 * 
	 * @return
	 */
	public String queryHospDeptEdit() {
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);
			List<HospInfoDTO> detail = hospService.queryHospDept(akb020, hospInfoDto);
			if (detail.size() > 0) {
				hospInfoDto = detail.get(0);
			}
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}

		return "deptEdit";
	}

	/**
	 * 查询医师信息
	 * 
	 * @return
	 */
	public String queryHospDoctorInfo() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				PagerHelper.initPagination(getRequest());
				if (hospInfoDto == null) {
					hospInfoDto = new HospInfoDTO();
				}
				String akb020 = BizHelper.getAkb020();
				HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
				hospInfoDto.setAkb020(akb020);

				hospInfoDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				hospInfoDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());

				ListResult listResult = hospService.queryDoctorPage(akb020, hospInfoDto);

				PaginationHelper.getPaginationObj().setCount(listResult.getCount());
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));

			} else {
				return "hospDoctor";
			}
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 查询需要修改的医师信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String queryHospDoctorEdit() {
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);

			List<HospInfoDTO> detail = hospService.queryHospDoctor(akb020, hospInfoDto);
			if (detail.size() > 0) {
				hospInfoDto = detail.get(0);
			} else {
				throw new HygeiaException("查询需要修改的医师信息出错，请检查数据!");
			}

			HospInfoDTO hospDTO = new HospInfoDTO();
			hospDTO.setAkb020(akb020);
			List<HospInfoDTO> deptInfo = hospService.queryHospDept(akb020, hospDTO);
			Map deptList = new LinkedHashMap();
			if (deptInfo.size() > 0) {
				for (HospInfoDTO deptRow : deptInfo) {
					deptList.put(deptRow.getBkc156(), deptRow.getBkc157());
				}
			}
			setAttribute("deptList", deptList);

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}

		return "doctorEdit";
	}

	/**
	 * 保存新增医师信息
	 * 
	 * @return
	 */
	public String saveHospDoctor() {
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}

			String akb020 = BizHelper.getAkb020();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);
			int retSize = ser.checkExist(hospInfoDto.getAkb020(), hospInfoDto.getBkc274() , "doctor",
					hospService);
			if (retSize > 0) {
				saveJSONError("医师编号已经存在！");
			} else {
				CharCodeSwitch charCode = new CharCodeSwitchImpl();
				String aka020 = charCode.LoadPY(hospInfoDto.getBkc275());
				String aka021 = charCode.LoadWB(hospInfoDto.getBkc275());
				hospInfoDto.setAka020(aka020);
				hospInfoDto.setAka021(aka021);
				hospService.saveHospDoctor(akb020, hospInfoDto);
				setJSONReturn("保存医师信息成功！");
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 删除未审核的医师信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String delHospDoctor() {
		try {

			int i = 0;

			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);

			String data = getParameter("data");
			if (UtilFunc.hasText(data)) {
				i = hospService.delHospDoctorAll(hospInfoDto, JsonHelper.toList(data));
			}

			Map map = new HashMap();
			map.put("data", data);

			if (i > 0) {
				setJSONReturn("删除医师信息成功！", map);
			} else {
				setJSONReturn("删除医师信息失败！", map);
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 修改医师信息
	 * 
	 * @return
	 */
	@SuppressWarnings("resource")
	public String updateHospDoctor() {
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);
			hospInfoDto.setAaa027(aaa027);
			if(imgFile!=null){
				FileInputStream is = null;
				FileChannel fc = null;
				is = new FileInputStream(imgFile);
				fc = is.getChannel();
				if(fc.size()>60*1024){
					setJSONReturn("影像图片大小最大值为60KB");
					return NONE;
				}
				byte[] bkc292=null;
				bkc292 = FileCopyUtils.copyToByteArray(is);
				if(bkc292==null || bkc292.length==0){
					setJSONReturn("图片为空【bka292】");
					return NONE;
				}
				hospInfoDto.setBkc292(bkc292);
				//this.getBkc292();
			}
			//非定岗医师 默认审核通过
			if("0".equals(hospInfoDto.getBko003())){
				hospInfoDto.setAae016("1");
			}
			hospService.updateHospDoctor(akb020, hospInfoDto);

			setJSONReturn("修改医师信息成功！");
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage() );
		}
		return NONE;
	}

	/**
	 * 新增医师信息前，查询科室信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String openNewDoctor() {
		String flag = getParameter("flag");
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);

			List<HospInfoDTO> deptInfo = hospService.queryHospDept(akb020, hospInfoDto);

			Map deptList = new LinkedHashMap();
			if (deptInfo.size() > 0) {
				for (HospInfoDTO deptRow : deptInfo) {
					deptList.put(deptRow.getBkc156(), deptRow.getBkc157());
				}
			}
			setAttribute("deptList", deptList);
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}

		if (flag.equals("add")) {
			return "doctorNew";
		} else {
			return "doctorDr";
		}
	}

	/**
	 * 查询病床信息
	 * 
	 * @return
	 */
	public String queryHospBedInfo() {
		try {
			if (WebHelper.isPostRequest(getRequest())) {
				PagerHelper.initPagination(getRequest());
				if (hospInfoDto == null) {
					hospInfoDto = new HospInfoDTO();
				}
				String akb020 = BizHelper.getAkb020();
				HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
				hospInfoDto.setAkb020(akb020);
				hospInfoDto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				hospInfoDto.setPageSize(PagerHelper.getPaginationObj().getPageSize());

				ListResult listResult = hospService.queryBedPage(akb020, hospInfoDto);

				PaginationHelper.getPaginationObj().setCount(listResult.getCount());
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));

			} else {
				return "hospBed";
			}
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}

		return NONE;
	}

	/**
	 * 新增病床信息前，查询科室信息
	 * 
	 * @return
	 */
	public String queryHospAreaForBed() {
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			String bkc153 = URLDecoder.decode(getParameter("bkc153"), "UTF-8");
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);
			hospInfoDto.setBkc153(bkc153);

			List<HospInfoDTO> detail = hospService.queryHospArea(akb020, hospInfoDto);
			if (detail.size() > 0) {
				hospInfoDto = detail.get(0);
			}
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}

		return "bedNew";
	}

	/**
	 * 导入病床信息前，查询病区信息
	 * 
	 * @return
	 */
	public String openHospDr() {
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}

			String akb020 = BizHelper.getAkb020();
			String bkc153 = URLDecoder.decode(getParameter("bkc153"), "UTF-8");
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);
			hospInfoDto.setBkc153(bkc153);

			List<HospInfoDTO> detail = hospService.queryHospArea(akb020, hospInfoDto);
			if (detail.size() > 0) {
				hospInfoDto = detail.get(0);
			}
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}

		return "bedDr";
	}

	/**
	 * 查询需要修改的病床信息
	 * 
	 * @return
	 */
	public String queryHospBedEdit() {
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);
			List<HospInfoDTO> detail = hospService.queryHospBed(akb020, hospInfoDto);
			if (detail.size() > 0) {
				hospInfoDto = detail.get(0);
			}
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}

		return "bedEdit";
	}

	/**
	 * 保存新增病床信息
	 * 
	 * @return
	 */
	public String saveHospBed() {
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}

			String akb020 = BizHelper.getAkb020();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);

			int retSize = ser.checkExistByHosp(hospInfoDto, "bed", hospService);

			if (retSize > 0) {
				setJSONReturn("病床编号已经存在！");
			} else {
				hospService.saveHospBed(akb020, hospInfoDto);
				setJSONReturn("保存病床信息成功！");
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;

	}

	/**
	 * 保存导入病床信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String saveHospBedDr() {
		try {
			int iReturn = 0;
			hospInfoDto = new HospInfoDTO();
			hospInfoDto.setAkb020(BizHelper.getAkb020());
			hospInfoDto.setBkc153(getParameter("bkc153"));
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, 
					hospInfoDto.getAkb020());
			String insert = getParameter("detail");
			// 需要校验是否重复编号
			if (UtilFunc.hasText(insert)) {
				List<Map<String, Object>> insertInfo = JsonHelper.toList(insert);
				// 判断是否重复数据
				List<Map<String, Object>> retDetail = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < insertInfo.size(); i++) {
					Map mHosp = (Map) insertInfo.get(i);
					HospInfoDTO checkHosp = new HospInfoDTO();
					checkHosp.setBkc153(hospInfoDto.getBkc153());
					checkHosp.setAkb020(hospInfoDto.getAkb020());
					checkHosp.setBkc161(UtilFunc.getString(mHosp, "bkc161"));
					int retSize = ser.checkExistByHosp(checkHosp, "bed", hospService);
					if (retSize <= 0) {
						retDetail.add(mHosp);
					}
				}
				if(retDetail.size() > 0) {
					iReturn = hospService.saveHospBedDr(hospInfoDto, retDetail);
				}
			}
			if (iReturn > 0)
				setJSONReturn("保存病床信息成功！");
			else 
				setJSONReturn("保存病床信息失败！");
			
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;

	}

	/**
	 * 保存导入医师信息
	 * 
	 * @return
	 */
	public String saveHospDoctorDr() {
		try {
			int i = 0;
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);
			hospInfoDto.setAaa027(aaa027);
			
			String insert = getParameter("detail");
			String bkc156 = getParameter("bkc156");
			HospInfoDTO hospInfoDtoTemp = new HospInfoDTO();
			hospInfoDtoTemp.setAkb020(akb020);
			hospInfoDtoTemp.setAae100("1");
			List<HospInfoDTO> deptInfo = hospService.queryHospDept(akb020, hospInfoDtoTemp);
			//需要校验是否重复编号
			if (UtilFunc.hasText(insert)) {
				
				List<Map<String, Object>> detail = ser.checkDoctroList(JsonHelper.toList(insert),bkc156,deptInfo);
				i = hospService.saveHospDoctorDr(hospInfoDto, detail);
			}
			if (i > 0) {
				setJSONReturn("保存医师信息成功！");
			} else {
				setJSONReturn("保存医师信息失败！");
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;

	}

	/**
	 * 修改病床信息
	 * 
	 * @return
	 */
	public String updateHospBed() {
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);
			hospService.updateHospBed(akb020, hospInfoDto);
			setJSONReturn("保存病床信息成功！");

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 删除病床信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String delHospBed() {
		try {
			int i = 0;

			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			hospInfoDto.setAkb020(akb020);

			String data = getParameter("data");
			if (UtilFunc.hasText(data)) {
				i = hospService.delHospBedAll(hospInfoDto, JsonHelper.toList(data));
			}

			Map map = new HashMap();
			map.put("data", data);

			if (i > 0) {
				setJSONReturn("删除病床信息成功！", map);
			} else {
				setJSONReturn("删除病床信息失败！", map);
			}

		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 模板下载
	 * 
	 * @param upload
	 * @param uploadFileName
	 * @param filePath
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void downLoadExampleXls() {
		try {
			FileDownLoad f = new FileDownLoad();
			Map map = new HashMap();
			String bzc001 = getParameter("bzc001");
			if (StringUtil.isBlank(bzc001)) {
				throw new HygeiaException("模板编号为空，请检查模板信息！");
			}
			map.put("bzc001", bzc001);
			String[] wheres = { "bzc001" };
			AZE1 aze1 = DAOHelper.queryForObject("AZE1", map, wheres, AZE1.class);
			f.downLoad(getRequest(), getResponse(), aze1.getBzc002(), aze1.getBzc003());
		} catch (Exception e) {
			throw new HygeiaException(e.getMessage());
		}
	}

	/**
	 * 上传文件方法
	 * 
	 * @param upload
	 * @param filePath
	 */
	public void uploadFile(File upload, String uploadFileName, String filePath) {
		String filepath = ServletActionContext.getServletContext().getRealPath(filePath);
		File saveFile = null;
		if (upload != null) {
			File saveDir = new File(filepath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			saveFile = new File(filepath + "/" + uploadFileName);
			try {
				FileUtils.copyFile(upload, saveFile);
			} catch (IOException e) {
				throw new HygeiaException("读取Excel文件出错，请确认文件是否正确！！");
			}
		}
	}

	/**
	 * 上传医院病床文件与插入数据库
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String uploadFileBed() {
		List list = null;
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			hospInfoDto.setAkb020(akb020);
			hospInfoDto.setAaa027(aaa027);

			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			String upFileName = DateFunc.dateToString(new Date(), "yyyyMMdd") + "_"
					+ String.valueOf(new Date().getTime()) + "_" + imgFileName;
			// 将文件上传至web服务器[如果是集群服务器，上传的EXCEL将无法寻找回来]
			this.uploadFile(imgFile, upFileName, "/uploadfile/hospfile");
			// 解析上传的文件信息返回List
			list = ser.getExcelContextBed(imgFile, hospInfoDto);
			List retDetail = new ArrayList();

			for (int i = 0; i < list.size(); i++) {
				Map mHospBed = (Map) list.get(i);
				HospInfoDTO checkHosp = new HospInfoDTO();
				checkHosp.setAkb020(akb020);
				checkHosp.setBkc153(hospInfoDto.getBkc153());
				checkHosp.setBkc161(mHospBed.get("bkc161").toString());
				int retSize = ser.checkExistByHosp(checkHosp, "bed", hospService);
				if (retSize <= 0) {
					retDetail.add(mHospBed);
				}
			}

			if (retDetail.size() > 0) {
				setJSONReturn("上传成功！", retDetail);
			} else {
				setJSONReturn("上传失败，请检查病床编号是否存在！", retDetail);
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public String uploadFileInFee() {
		List list = null;
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			@SuppressWarnings("unused")
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class,
					BizHelper.getAkb020());
			String upFileName = DateFunc.dateToString(new Date(), "yyyyMMdd") + "_"
					+ String.valueOf(new Date().getTime()) + "_" + imgFileName;
			// 将文件上传至web服务器[如果是集群服务器，上传的EXCEL将无法寻找回来]
			this.uploadFile(imgFile, upFileName, "/uploadfile/hospfile");
			// 解析上传的文件信息返回List
			list = ser.getExcelInFee(imgFile, hospInfoDto);
			this.setJSONReturn("保存成功！", list);

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 上传医院医师文件与插入数据库
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String uploadFileDoctor() {
		List list = null;
		try {
			if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			hospInfoDto.setAkb020(akb020);
			hospInfoDto.setAaa027(aaa027);

			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class, akb020);
			String upFileName = DateFunc.dateToString(new Date(), "yyyyMMdd") + "_"
					+ String.valueOf(new Date().getTime()) + "_" + imgFileName;
			// 将文件上传至web服务器[如果是集群服务器，上传的EXCEL将无法寻找回来]
			this.uploadFile(imgFile, upFileName, "/uploadfile/hospfile");
			// 解析上传的文件信息返回List
			list = ser.getExcelContextDoctor(imgFile, hospInfoDto);
			List retDetail = new ArrayList();

			for (int i = 0; i < list.size(); i++) {
				Map mHosp = (Map) list.get(i);
				int retSize = ser.checkExist(akb020, mHosp.get("bkc274").toString(), "doctor", hospService);
				if (retSize <= 0) {
					retDetail.add(mHosp);
				}
			}

			if (retDetail.size() > 0) {
				this.setJSONReturn("保存成功！", retDetail);
			} else {
				this.setJSONReturn("保存失败，请检查文件,医师编号是否已经存在！", retDetail);
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 上传目录文件与插入数据库
	 */
	public String saveFile() {
		try {
			String bzc001 = getParameter("bzc001");
			String bzc002 = getParameter("bzc002");
			SaveFileService save = new SaveFileServiceImpl();
			String sReturn = save.saveFile(imgFile, "AZE1", bzc001, bzc002);
			setJSONReturn(sReturn);
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	/***
	 * 费用套餐设置ediku
	 * 
	 * @return
	 */
	public String feeSetMealIndex() {
		try {
			if (this.isPostRequest()) {
				try {
					Kzh04Dto kzh04Dto = new Kzh04Dto();
					beanService.copyProperties(getAllParameters(), kzh04Dto, true);
					kzh04Dto.setAkb020(BizHelper.getAkb020());
					PagerHelper.initPagination(this.getRequest());
					kzh04Dto.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
					kzh04Dto.setPageSize(PagerHelper.getPaginationObj().getPageSize());
					HospManagerService hospManagerService = this.hygeiaServiceManager
							.getBeanByClass(HospManagerService.class, kzh04Dto.getAkb020());
					List<Kzh04Dto> kzh04Dtos = hospManagerService.selectFeeSetMealIndex(kzh04Dto);

					PagerHelper.getPaginationObj().setCount(kzh04Dtos.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(kzh04Dtos));
				} catch (Exception e) {
					this.saveJSONError(e.getMessage());
				}
				return NONE;
			} else {
				// this.initCtrlInHospitalDTO(aka130_12);
				// Kzh04Dto kzh04Dto=new Kzh04Dto();
				// kzh04Dto.setBke204(DateFunc.getDate());

				return "feeSetMealIndex";
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
			// this.saveError(e.getMessage());
			return ERROR;
		}
	}

	/***
	 * 套餐ediku
	 */
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public String feeSetMealDetail() {
		try {
			if (this.isPostRequest()) {
				Map retMsg = new HashMap();
				retMsg.put("suss", "0");
				retMsg.put("message", "保存失败");

				Kzh04Dto kzh04Dto = new Kzh04Dto();
				beanService.copyProperties(getAllParameters(), kzh04Dto, true);
				kzh04Dto.setAkb020(BizHelper.getAkb020());
				kzh04Dto.setBke204(DateFunc.getDate());
				kzh04Dto.setBke205(BizHelper.getLoginUser());
				kzh04Dto.setBke206(BizHelper.getUserName());
				kzh04Dto.setAaa027(BizHelper.getAaa027());
				kzh04Dto.setAae100("1");
				HospManagerService hospManagerService = this.hygeiaServiceManager
						.getBeanByClass(HospManagerService.class, kzh04Dto.getAkb020());

				String jsonFee = URLDecoder.decode(getParameter("feeinfo"), "UTF-8");
				JSONArray jsonArray = JSONArray.fromObject(jsonFee);

				List<InHospitalDTO> inHospitalDTORows = JSONArray.toList(jsonArray, InHospitalDTO.class);
				for (InHospitalDTO inHospitalDTORow : inHospitalDTORows) {
					inHospitalDTORow.setBka063(BizHelper.getLoginUser());
					inHospitalDTORow.setBka064(BizHelper.getUserName());
					inHospitalDTORow.setBka065(DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				}

				List<Kzh0422Dto> kzh0422DtoRows = new ArrayList();
				for (InHospitalDTO inHospitalDTOTemp : inHospitalDTORows) {
					Kzh0422Dto kzh0422DtoTemp = new Kzh0422Dto();
					beanService.copyProperties(inHospitalDTOTemp, kzh0422DtoTemp, true);
					kzh0422DtoTemp.setAaz231(inHospitalDTOTemp.getBkc109());// 药id
					kzh0422DtoTemp.setBka051(DateFunc.getDate());
					kzh0422DtoTemp.setAae100("1");
					kzh0422DtoRows.add(kzh0422DtoTemp);
				}

				hospManagerService.saveOrUpdateFeeSetMeal(kzh04Dto, kzh0422DtoRows);

				retMsg.put("suss", "1");
				retMsg.put("message", "保存成功");

				setJSONReturn(retMsg);
				return NONE;
			} else {
				// this.initCtrlInHospitalDTO(aka130_12);
				String bkh015 = getParameter("bkh015", "");

				Kzh04Dto kzh04Dto = new Kzh04Dto();
				kzh04Dto.setBke204(DateFunc.getDate());
				if (!"".equals(bkh015)) {
					kzh04Dto.setBkh015(Integer.parseInt(bkh015));
					kzh04Dto.setAkb020(BizHelper.getAkb020());
					HospManagerService hospManagerService = this.hygeiaServiceManager
							.getBeanByClass(HospManagerService.class, kzh04Dto.getAkb020());
					kzh04Dto = hospManagerService.selectFeeSetMealByPrimaryKey(kzh04Dto);

				}
				setAttribute("currDateStr", DateFunc.dateToString(DateFunc.getDate(), "yyyyMMdd"));
				setAttribute("kzh04Dto", kzh04Dto);

				return "feeSetMealDetail";
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
			this.saveError(e.getMessage());
			throw new RuntimeException(e.getMessage());
			// return ERROR;
		}
	}

	/***
	 * 套餐ediku
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String feeSetMealDetailDel() {
		try {
			if (this.isPostRequest()) {
				Map retMsg = new HashMap();
				retMsg.put("suss", "0");
				retMsg.put("message", "失败");

				String typeString = getParameter("type", "");
				if (!typeString.equals("1") && !typeString.equals("0")) {
					retMsg.put("message", "type只能为1,0");
					setJSONReturn(retMsg);
					return NONE;
				}

				Kzh04Dto kzh04Dto = new Kzh04Dto();
				kzh04Dto.setAkb020(BizHelper.getAkb020());

				HospManagerService hospManagerService = this.hygeiaServiceManager
						.getBeanByClass(HospManagerService.class, kzh04Dto.getAkb020());

				hospManagerService.saveDelKzh040(getParameter("bkh015s"), kzh04Dto.getAkb020(), typeString);

				retMsg.put("suss", "1");
				retMsg.put("message", "成功");

				setJSONReturn(retMsg);

			}
			return NONE;
		} catch (Exception e) {
			saveJSONError(e.getMessage());
			this.saveError(e.getMessage());
			throw new RuntimeException(e.getMessage());
			// return ERROR;
		}
	}

	/***
	 * 套餐ediku
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadPsTemplateSel() {
		try {
			Map retMsg = new HashMap();
			retMsg.put("suss", "0");
			retMsg.put("message", "失败");
			String typeString = getParameter("type");
			if (typeString != null && typeString.equals("1"))// 载入管理
			{
				String bkh015 = getParameter("bkh015", "");
				Kzh04Dto kzh04Dto = new Kzh04Dto();
				kzh04Dto.setBke204(DateFunc.getDate());
				if ("".equals(bkh015)) {
					throw new RuntimeException("bkh015为空");
				}
				kzh04Dto.setCaa027(getParameter("caa027", ""));
				kzh04Dto.setBkh015(Integer.parseInt(bkh015));
				kzh04Dto.setAkb020(BizHelper.getAkb020());
				HospManagerService hospManagerService = this.hygeiaServiceManager
						.getBeanByClass(HospManagerService.class, kzh04Dto.getAkb020());
				List feeList = hospManagerService.selectByKzh0422(kzh04Dto);

				if (feeList != null && !feeList.isEmpty()) {
					for (int i = 0; i < feeList.size(); i++) {
						Kzh0422Dto kzh0422Dto = (Kzh0422Dto) feeList.get(i);
						kzh0422Dto.setBka060_name(CodetableMapping.getDisplayValue("bka060", kzh0422Dto.getBka060(),
								kzh0422Dto.getBka060()));
					}

					retMsg.put("feeinfo", feeList);
					retMsg.put("suss", "1");

				} else {
					retMsg.put("message", "没有费用信息");
				}

			} else if (typeString != null && typeString.equals("2"))// 选择
			{
				String bkh015 = getParameter("bkh015", "");
				Kzh04Dto kzh04Dto = new Kzh04Dto();
				kzh04Dto.setBke204(DateFunc.getDate());

				if ("".equals(bkh015)) {
					throw new RuntimeException("bkh015为空");
				}
				kzh04Dto.setCaa027(getParameter("caa027", ""));
				kzh04Dto.setBkh015(Integer.parseInt(bkh015));
				kzh04Dto.setAkb020(BizHelper.getAkb020());
				HospManagerService hospManagerService = this.hygeiaServiceManager
						.getBeanByClass(HospManagerService.class, kzh04Dto.getAkb020());
				List feeList = hospManagerService.loadPsTemplateSel(kzh04Dto);
				if (feeList != null && !feeList.isEmpty()) {
					for (int i = 0; i < feeList.size(); i++) {
						Map map2 = (Map) feeList.get(i);
						if (map2.get("aka065") != null) {
							map2.put("aka065", CodetableMapping.getDisplayValue("aka065", map2.get("aka065").toString(),
									map2.get("aka065").toString()));
						}
					}

					retMsg.put("feeinfo", feeList);
					retMsg.put("suss", "1");
				} else {
					retMsg.put("message", "没有费用信息");
				}

			}

			setJSONReturn(retMsg);
		} catch (Exception e) {
			saveJSONError("载入信息出错！" + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 查询专家库信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryExpertInfo() {
		try {
			if (this.isPostRequest()) {
				try {
					PagerHelper.initPagination(this.getRequest());
					ExpertManagerService expertManagerService = this.hygeiaServiceManager
							.getBeanByClass(ExpertManagerService.class, expertInfoDto.getAkb020());
					List<ExpertInfoDTO> expertList = expertManagerService.selectByPrimaryKey(expertInfoDto);
					PagerHelper.getPaginationObj().setCount(expertList.size());
					DataGridHelper.render(this.getRequest(), this.getResponse(),
							PagerHelper.getPaginatedList(expertList));
					Map deptList = this.loandBka156();
					setAttribute("deptList", deptList);
				} catch (Exception e) {
					this.saveJSONError(e.getMessage());
				}
				return NONE;
			} else {
				return "expert";
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
			// this.saveError(e.getMessage());
			return ERROR;
		}
	}

	/***
	 * 专家库的新增和修改
	 */
	@SuppressWarnings({ "unchecked", "resource", "rawtypes", "deprecation" })
	public String expertDetail() {
		try {
			String flag = this.getRequest().getParameter("flag");
			if (!"1".equals(flag)) {
				Map retMsg = new HashMap();
				retMsg.put("suss", "0");
				retMsg.put("message", "保存失败");
				ExpertManagerService expertManagerService = this.hygeiaServiceManager
						.getBeanByClass(ExpertManagerService.class, expertInfoDto.getAkb020());
				// 查询专家库编码是否重复
				if (null == expertInfoDto.getBkc406() || "".equals(expertInfoDto.getBkc406())) {
					List<ExpertInfoDTO> expertCheck = expertManagerService.selectByPrimaryKey(expertInfoDto);
					if (expertCheck.size() > 0) {
						setJSONReturn("专家编号已经存在！");
					}
				}
				// 保存照片
				FileInputStream is = null;
				FileChannel fc = null;
				if (null != imgFile) {
					is = new FileInputStream(imgFile);
					fc = is.getChannel();
					if (fc.size() > 60 * 1024) {
						setJSONReturn("影像图片大小最大值为60KB");
						return NONE;
					}
					byte[] bkc292 = null;
					bkc292 = FileCopyUtils.copyToByteArray(is);// 将获取的文件流转为byte[]
					if (bkc292 == null || bkc292.length == 0) {
						setJSONReturn("图片为空【bka292】");
						return NONE;
					}
					expertInfoDto.setBkc292(bkc292);
				}
				expertInfoDto.setAae015(DateFunc.getDate());
				expertInfoDto.setAae014(BizHelper.getLoginUser());
				expertInfoDto.setBae101(BizHelper.getUserName());
				String jsonFee = URLDecoder.decode(getParameter("feeinfo"), "UTF-8");
				JSONArray jsonArray = JSONArray.fromObject(jsonFee);
				List<TreatmentDTO> treatmentDtoRows = JSONArray.toList(jsonArray, TreatmentDTO.class);
				List<TreatmentDTO> treatmentDtoList = new ArrayList<TreatmentDTO>();
				for (TreatmentDTO treatmentDto : treatmentDtoRows) {
					TreatmentDTO treatment = new TreatmentDTO();
					treatment.setBkc406(expertInfoDto.getBkc406());// 专家ID
					treatment.setBka006(treatmentDto.getBka006());
					treatmentDtoList.add(treatment);
				}
				expertManagerService.saveOrUpdateExpert(expertInfoDto, treatmentDtoList);
				retMsg.put("suss", "1");
				retMsg.put("message", "保存成功");
				setJSONReturn(retMsg);
				return NONE;
			} else {
				String bkc406 = getParameter("bkc406", "");
				expertInfoDto = new ExpertInfoDTO();
				expertInfoDto.setAkb020(BizHelper.getAkb020());
				ExpertManagerService expertManagerService = this.hygeiaServiceManager
						.getBeanByClass(ExpertManagerService.class, BizHelper.getAkb020());
				if (!"".equals(bkc406)) {
					expertInfoDto.setBkc406(bkc406);
					expertInfoDto = expertManagerService.selectExpertOne(expertInfoDto);
					setAttribute("bkc269", expertInfoDto.getBkc406());
				} else {
					expertInfoDto.setAae015(DateFunc.getDate());
					expertInfoDto.setAae014(BizHelper.getLoginUser());
					expertInfoDto.setBae101(BizHelper.getUserName());
					expertInfoDto.setAae100("1");
					expertInfoDto.setAae016("0");
				}
				Map deptList = this.loandBka156();
				setAttribute("deptList", deptList);
				setAttribute("expertInfoDto", expertInfoDto);
				return "expertDetail";
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
			this.saveError(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 查询需要修改的专家的头像
	 * 
	 * @return
	 * 
	 * 
	 */
	public String getImage() {
		try {
			if (expertInfoDto == null) {
				expertInfoDto = new ExpertInfoDTO();
			}
			String akb020 = BizHelper.getAkb020();
			expertInfoDto.setBkc406(getRequest().getParameter("bkc406"));
			ExpertManagerService expertManagerService = this.hygeiaServiceManager
					.getBeanByClass(ExpertManagerService.class, BizHelper.getAkb020());
			expertInfoDto.setAkb020(akb020);
			expertInfoDto = expertManagerService.selectExpertOne(expertInfoDto);
			if (expertInfoDto.getBkc292() != null) {
				InputStream input = new ByteArrayInputStream(expertInfoDto.getBkc292());
				this.getResponse().setContentType("image/jpeg");
				OutputStream outputStream = this.getResponse().getOutputStream();
				byte[] buffer = new byte[1024];
				int j = -1;
				while ((j = input.read(buffer)) != -1) {
					outputStream.write(buffer, 0, j);
				}
				outputStream.flush();
				outputStream.close();
				input.close();
				outputStream = null;
			}
		} catch (IOException es) {
			es.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 加载科室信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map loandBka156() {
		HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class,
				expertInfoDto.getAkb020());
		if (hospInfoDto == null) {
			hospInfoDto = new HospInfoDTO();
		}
		hospInfoDto.setAkb020(expertInfoDto.getAkb020());
		List<HospInfoDTO> deptInfo = hospService.queryHospDept(expertInfoDto.getAkb020(), hospInfoDto);
		Map deptList = new LinkedHashMap();
		if (deptInfo.size() > 0) {
			for (HospInfoDTO deptRow : deptInfo) {
				deptList.put(deptRow.getBkc156(), deptRow.getBkc157());
			}
		}
		return deptList;
	}

	/**
	 * 加载关联的待遇信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void chooseTreatment() {
		try {
			Map retMsg = new HashMap();
			retMsg.put("suss", "0");
			retMsg.put("message", "查询待遇类型失败");
			String bkc406 = getParameter("bkc406", "");
			ExpertInfoDTO expertInfoDTO = new ExpertInfoDTO();
			expertInfoDTO.setAae015(DateFunc.getDate());
			if ("".equals(bkc406)) {
				throw new RuntimeException("bkc406为空");
			}
			expertInfoDTO.setBkc406(bkc406);
			expertInfoDTO.setAkb020(BizHelper.getAkb020());
			ExpertManagerService expertManagerService = this.hygeiaServiceManager
					.getBeanByClass(ExpertManagerService.class, expertInfoDTO.getAkb020());
			List<TreatmentDTO> feeList = expertManagerService.selectByTreatment(expertInfoDTO);
			// start 2018-01-04 lhy 珠海专家库待遇类型
			for (TreatmentDTO treatmentDTO : feeList) {
				if (StringUtils.isNotBlank(treatmentDTO.getBka006())) {
					treatmentDTO.setBka006_name(CodetableMapping.getDisplayValue("bka006$" + "440400",
							treatmentDTO.getBka006(), treatmentDTO.getBka006()));
				}
			}
			// end
			if (feeList != null && !feeList.isEmpty()) {
				retMsg.put("feeinfo", feeList);
				retMsg.put("suss", "1");
			}
			setJSONReturn(retMsg);
		} catch (Exception e) {
			saveJSONError("载入信息出错！" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 取消专家信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void expertRemove() {
		try {
			if (this.isPostRequest()) {
				Map retMsg = new HashMap();
				retMsg.put("suss", "0");
				retMsg.put("message", "取消失败");
				String bkc406 = this.getRequest().getParameter("bkc406");
				expertInfoDto = new ExpertInfoDTO();
				expertInfoDto.setBkc406(bkc406);
				expertInfoDto.setAkb020(BizHelper.getAkb020());
				ExpertManagerService expertManagerService = this.hygeiaServiceManager
						.getBeanByClass(ExpertManagerService.class, expertInfoDto.getAka020());
				expertInfoDto.setAae100("0");
				expertInfoDto.setAae015(DateFunc.getDate());
				expertInfoDto.setAae014(BizHelper.getLoginUser());
				expertInfoDto.setBae101(BizHelper.getUserName());
				expertManagerService.saveOrUpdateExpert(expertInfoDto, null);
				retMsg.put("suss", "1");
				retMsg.put("message", "取消成功");
				setJSONReturn(retMsg);
			}
		} catch (Exception e) {
			saveJSONError("取消专家信息错误" + e.getMessage());
			e.printStackTrace();
		}
	}
    
	/***
	 * 拼音和五笔 医生
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getPyAndWb() {
		try {
		    if (hospInfoDto == null) {
				hospInfoDto = new HospInfoDTO();
			}
			Map retMsg = new HashMap();
			
			String strInput=hospInfoDto.getBkc275();
			CharCodeSwitch charCode = new CharCodeSwitchImpl();
			String aka020 = "";
			String aka021 = "";
			if(strInput!=null || !"".equals(strInput)){
				aka020 = charCode.LoadPY(strInput);
				aka021 = charCode.LoadWB(strInput);
			}
			retMsg.put("aka020", aka020);
			retMsg.put("aka021", aka021);
			setJSONReturn(retMsg);
			return NONE;
		} catch (Exception e) {
			saveJSONError(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String queryHospDoctorChang() {
		try {
			this.hospInfoDto = this.hospInfoDto == null ? new HospInfoDTO() : this.hospInfoDto;
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class,
					BizHelper.getAkb020());
			List<HospInfoDTO> detail = hospService.queryHospDoctor(BizHelper.getAkb020(), this.hospInfoDto);
			this.beanService.copyProperties(detail.get(0), this.hospInfoDto, true);
			this.hospInfoDto.setAac003(detail.get(0).getBkc275());
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return "changsApply";
	}
	
	public String saveChangApply() {
		try {
			HospApiService hospService = hygeiaServiceManager.getBeanByClass(MCCEHospApiServiceImpl.class,
					BizHelper.getAkb020());
			this.getHospInfoDto().setAkb020(BizHelper.getAkb020());
			hospService.saveDoctorChangApply(this.getHospInfoDto());
			setJSONReturn("医生异动申请信息保存成功！");
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}
	
	/**
	 * TS18111900025 - 【需求开发】普通门诊首诊查询与修改
	 * @Description: 查询人员首诊医疗机构信息
	 * @author: xiexiao
	 * @date: 2018年11月18日
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public String queryPersFirstHosp() {
		Map par = getAllParameters();
		try {
			HospManagerService ser = hygeiaServiceManager.getBeanByClass(HospManagerService.class, BizHelper.getAkb020());
			if(par != null){
				par.put("aaa027", BizHelper.getAaa027());
			}
			
			List list = ser.queryPersFirstHosp(par);
			return renderGrid(list);
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}
	
	/**
	 * TS18111900025 - 【需求开发】普通门诊首诊查询与修改
	 * @Description: 修改人员首诊医疗机构信息
	 * @author: xiexiao
	 * @date: 2018年11月18日
	 * @return String
	 */
	public String editPersFirstHosp() {
		try {
			this.hospInfoDto = this.hospInfoDto == null ? new HospInfoDTO() : this.hospInfoDto;
			HospManagerService ser = hygeiaServiceManager.getBeanByClass(HospManagerService.class, BizHelper.getAkb020());
			
			hospInfoDto.setAaa027(BizHelper.getAaa027());
			hospInfoDto.setAkb020(BizHelper.getAkb020());
			
			ser.editPersFirstHosp(hospInfoDto);
			saveJSONMessage("修改成功！");
		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}
	
	public HospInfoDTO getHospInfoDto() {
		return hospInfoDto;
	}

	public void setHospInfoDto(HospInfoDTO hospInfoDto) {
		this.hospInfoDto = hospInfoDto;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public HospCataDTO getHospCataDto() {
		return hospCataDto;
	}

	public void setHospCataDto(HospCataDTO hospCataDto) {
		this.hospCataDto = hospCataDto;
	}

	public ExpertInfoDTO getExpertInfoDto() {
		return expertInfoDto;
	}

	public void setExpertInfoDto(ExpertInfoDTO expertInfoDto) {
		this.expertInfoDto = expertInfoDto;
	}

}
