package com.powersi.ssm.biz.medicare.diagnose.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.diagnose.pojo.Kf01DTO;
import com.powersi.biz.medicare.diagnose.pojo.Kf02DTO;
import com.powersi.biz.medicare.diagnose.service.DiagnoseBatchChargeVS;
import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.entity.AZE1;
import com.powersi.hygeia.comm.service.FileDownLoad;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.hosp.service.HospService;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;
import com.powersi.sys.util.StringUtil;

@Action(value = "DiagnoseBatchChargeAction", results = {
		@Result(name = "diagnoseBatchCharge", location = "/pages/biz/medicare/diagnose/diagnoseBatchCharge.jsp"),
		@Result(name = "diagnoseBatchCharge_recheck", location = "/pages/biz/medicare/diagnose/diagnoseBatchCharge_recheck.jsp") })
public class DiagnoseBatchChargeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private File imgFile;// 上传文件
	private String imgFileName;// 上传文件名称
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;

	@Autowired
	private DateService dateService;

	private HospService ser = getBean(HospService.class);

	private Kf01DTO kf01DTO;
	private Kf02DTO kf02DTO;

	public String diagnoseBatchCharge() {
		if (kf01DTO == null) {
			kf01DTO = new Kf01DTO();
		}
		kf01DTO.setBkc032(this.dateService.dateToString(new Date(), "yyyyMM"));
		return "diagnoseBatchCharge";
	}

	public String diagnoseBatchCharge_recheck() {
		if (kf01DTO == null) {
			kf01DTO = new Kf01DTO();
		}
		kf01DTO.setBkc032(this.dateService.dateToString(new Date(), "yyyyMM"));
		return "diagnoseBatchCharge_recheck";
	}

	/**
	 * 模板下载
	 * 
	 * @param upload
	 * @param uploadFileName
	 * @param filePath
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		String xls = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1, uploadFileName.length());
		if (!xls.equals("xls") && !xls.equals("xlsx")) {
			throw new HygeiaException("请上传excel文件！");
		}
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

	public String uploadFileBatchLead() {
		List<Kf02DTO> list = null;
		try {

			String akb020 = BizHelper.getAkb020();
			String upFileName = DateFunc.dateToString(new Date(), "yyyyMMdd") + "_"
					+ String.valueOf(new Date().getTime()) + "_" + imgFileName;
			// 将文件上传至web服务器[如果是集群服务器，上传的EXCEL将无法寻找回来]
			this.uploadFile(imgFile, upFileName, "/uploadfile/hospfile");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			// 解析上传的文件信息返回List
			list = ser.getExcelContextBatchCharge(imgFile, upFileName, akb020, sdf.format(date));
			if (kf01DTO == null) {
				kf01DTO = new Kf01DTO();
			}
			UserInfo userInfo = BusiContext.getUserInfo();
			kf01DTO.setAkb020(akb020);
			kf01DTO.setAaz217(akb020 + sdf.format(date));
			kf01DTO.setKf01id(sdf.format(date));
			kf01DTO.setBkc030(userInfo.getUserName());
			kf01DTO.setBkc031(userInfo.getAdminId());
			kf01DTO.setBkc032(sdf.format(date));
			kf01DTO.setBkc033(upFileName);
			kf01DTO.setBkc034("Excel");
			kf01DTO.setBkc040("0");
			kf01DTO.setAaa027(BizHelper.getAaa027());
			DiagnoseBatchChargeVS diagnoseSaveBizVS = (DiagnoseBatchChargeVS) hygeiaServiceManager
					.getBean("diagnoseBatchChargeVS", BizHelper.getAkb020());
			int result = diagnoseSaveBizVS.saveBatchCharge(kf01DTO, list);
			if (result > 0) {
				this.setJSONReturn(kf01DTO.getKf01id());
			} else {
				this.setJSONReturn("上传失败！");
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public String queryBatchCharge() {
		PagerHelper.initPagination(this.getRequest());
		try {
			if (kf01DTO == null) {
				kf01DTO = new Kf01DTO();
			}
			kf01DTO.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			kf01DTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());
			kf01DTO.setStartRow((kf01DTO.getCurrentPage() - 1) * kf01DTO.getPageSize());
			kf01DTO.setAkb020(BizHelper.getAkb020());
			String bke032 = StringUtils.isBlank(kf01DTO.getBkc032())
					? this.dateService.dateToString(new Date(), "yyyyMM")
					: kf01DTO.getBkc032();
			kf01DTO.setAaz217(BizHelper.getAkb020() + bke032 + "01000001");

			DiagnoseBatchChargeVS diagnoseSaveBizVS = (DiagnoseBatchChargeVS) hygeiaServiceManager
					.getBean("diagnoseBatchChargeVS", BizHelper.getAkb020());
			List<Map> batchKf01List = diagnoseSaveBizVS.queryBatchCharge(kf01DTO);
			kf01DTO.setTotalPage(UtilFunc.isEmpty(batchKf01List) ? 0
					: (Integer.parseInt(batchKf01List.get(0).get("totleRow").toString()) + kf01DTO.getPageSize() - 1)
							/ kf01DTO.getPageSize());
			PagerHelper.getPaginationObj().setCount(UtilFunc.isEmpty(batchKf01List) ? 0
					: Integer.parseInt(batchKf01List.get(0).get("totleRow").toString()));
			DataGridHelper.render(this.getRequest(), this.getResponse(), PagerHelper.getPaginatedList(batchKf01List));
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
		}
		return NONE;

	}

	public String velidDate() {
		try {
			if (kf01DTO == null) {
				kf01DTO = new Kf01DTO();
			}
			kf01DTO.setAkb020(BizHelper.getAkb020());
			kf01DTO.setAaa027(BizHelper.getAaa027());
			UserInfo userInfo = BusiContext.getUserInfo();
			kf01DTO.setBkc030(userInfo.getUserName());
			kf01DTO.setBkc031(userInfo.getAdminId());
			DiagnoseBatchChargeVS diagnoseSaveBizVS = (DiagnoseBatchChargeVS) hygeiaServiceManager
					.getBean("diagnoseBatchChargeVS", BizHelper.getAkb020());
			diagnoseSaveBizVS.velidDate(kf01DTO);
			/*
			 * String jsonKf01info = URLDecoder.decode(this.getParameter("kf01info"),
			 * "UTF-8"); JSONArray jsonArray = JSONArray.fromObject(jsonKf01info);
			 * List<Kf01DTO> listKf01DTO = JSONArray.toList(jsonArray, Kf01DTO.class);
			 * saveJSONError(listKf01DTO.toString()); for (Kf01DTO kf01temp : listKf01DTO) {
			 * DiagnoseBatchChargeVS diagnoseSaveBizVS = (DiagnoseBatchChargeVS)
			 * hygeiaServiceManager .getBean("diagnoseBatchChargeVS", kf01temp.getAkb020());
			 * diagnoseSaveBizVS.velidDate(kf01temp); }
			 */
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	@SuppressWarnings("rawtypes")
	public String queryDetatilCharge() {
		PagerHelper.initPagination(getRequest());
		try {
			if (kf02DTO == null) {
				kf02DTO = new Kf02DTO();
			}
			kf02DTO.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
			kf02DTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());
			kf02DTO.setStartRow((kf02DTO.getCurrentPage() - 1) * kf02DTO.getPageSize());
			kf02DTO.setAkb020(BizHelper.getAkb020());
			if (StringUtils.isNotBlank(kf02DTO.getBke037()) && kf02DTO.getBke037().equals("0")) {
				kf02DTO.setAaz217(kf02DTO.getAkb020() + kf01DTO.getBkc032() + "01000001");
			} else {
				kf02DTO.setAaz217(kf02DTO.getAkb020() + kf02DTO.getKf01id());
			}

			DiagnoseBatchChargeVS diagnoseSaveBizVS = (DiagnoseBatchChargeVS) hygeiaServiceManager
					.getBean("diagnoseBatchChargeVS", BizHelper.getAkb020());
			List<Map> batchKf01List = diagnoseSaveBizVS.queryDetatilCharge(kf02DTO);
			/*
			 * setJSONReturn(declList);
			 * PagerHelper.getPaginationObj().setCount(declList.size());
			 */
			kf02DTO.setTotalPage(UtilFunc.isEmpty(batchKf01List) ? 0
					: (Integer.parseInt(batchKf01List.get(0).get("totleRow").toString()) + kf02DTO.getPageSize() - 1)
							/ kf02DTO.getPageSize());
			PagerHelper.getPaginationObj().setCount(UtilFunc.isEmpty(batchKf01List) ? 0
					: Integer.parseInt(batchKf01List.get(0).get("totleRow").toString()));
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(batchKf01List));

		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
		}
		return NONE;
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

	public Kf01DTO getKf01DTO() {
		return kf01DTO;
	}

	public void setKf01DTO(Kf01DTO kf01dto) {
		kf01DTO = kf01dto;
	}

	public Kf02DTO getKf02DTO() {
		return kf02DTO;
	}

	public void setKf02DTO(Kf02DTO kf02dto) {
		kf02DTO = kf02dto;
	}

}
