package com.powersi.ssm.biz.medicare.diagnose.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.diagnose.pojo.KF03DTO;
import com.powersi.biz.medicare.diagnose.pojo.KF04DTO;
import com.powersi.biz.medicare.diagnose.service.CatalogMatchImportVS;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.entity.AZE1;
import com.powersi.hygeia.comm.service.FileDownLoad;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.DataGridHelper;
import com.powersi.hygeia.web.util.PagerHelper;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.hosp.service.HospService;
import com.powersi.sys.util.StringUtil;

@Action(value = "CatalogMatchBatchImportAction", results = {
		@Result(name = "CatalogMatchBatchImport", location = "/pages/biz/medicare/diagnose/CatalogMatchBatchImport.jsp") })

public class CatalogMatchBatchImportAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private File imgFile;// 上传文件
	private String imgFileName;// 上传文件名称
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	private HospService ser = getBean(HospService.class);

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

	private KF03DTO kf03DTO;
	private KF04DTO kf04DTO;

	public String catalogMatchBatchImport() {
		return "CatalogMatchBatchImport";
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
		List<KF04DTO> list = null;
		try {
			String akb020 = BizHelper.getAkb020();
			String upFileName = DateFunc.dateToString(new Date(), "yyyyMMdd") + "_"
					+ String.valueOf(new Date().getTime()) + "_" + imgFileName;
			// 将文件上传至web服务器[如果是集群服务器，上传的EXCEL将无法寻找回来]
			this.uploadFile(imgFile, upFileName, "/uploadfile/hospfile");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			// 解析上传的文件信息返回List
			list = ser.getExcelContextCatalogMatch(imgFile, upFileName, akb020, sdf.format(date));
			if (kf03DTO == null) {
				kf03DTO = new KF03DTO();
			}
			UserInfo userInfo = BusiContext.getUserInfo();
			kf03DTO.setAkb020(akb020);
			kf03DTO.setKf03id(sdf.format(date));
			kf03DTO.setBkc030(userInfo.getUserName());
			kf03DTO.setBkc031(userInfo.getAdminId());
			kf03DTO.setBkc032(sdf.format(date));
			kf03DTO.setBkc033(upFileName);
			kf03DTO.setBkc034("Excel");
			kf03DTO.setBkc040("0");
			kf03DTO.setAaa027(BizHelper.getAaa027());
			CatalogMatchImportVS catalogMatchImportVS = (CatalogMatchImportVS) hygeiaServiceManager
					.getBean("catalogMatchImportVS", BizHelper.getAkb020());
			int result = catalogMatchImportVS.saveCatalogMatch(kf03DTO, list);
			if (result > 0) {
				this.setJSONReturn(kf03DTO.getKf03id());
			} else {
				this.setJSONReturn("上传失败！");
			}

		} catch (Exception ex) {
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	public void queryCatalogMatch() {
		try {
			if (this.isPostRequest()) {
				PagerHelper.initPagination(this.getRequest());
				if (kf03DTO == null) {
					kf03DTO = new KF03DTO();
				}
				String akb020 = BizHelper.getAkb020();
				kf03DTO.setAkb020(akb020);
				// 把框架的分页信息set到DTO
				kf03DTO.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				kf03DTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());
				CatalogMatchImportVS catalogMatchImportVS = (CatalogMatchImportVS) hygeiaServiceManager
						.getBean("catalogMatchImportVS", BizHelper.getAkb020());
				// ListResult接口作为返回值
				ListResult listResult = catalogMatchImportVS.queryCatalogMatch(kf03DTO);

				// 把结果集返回页面的时候，set分页信息（从ListResult里面取出结果集，返回的list和count）
				PagerHelper.getPaginationObj().setCount(listResult.getCount());
				DataGridHelper.render(this.getRequest(), this.getResponse(),
						PagerHelper.getPaginatedList(listResult.getList()));

			}
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
		}

	}

	public String queryCataMatchDetatil() {
		PagerHelper.initPagination(getRequest());
		try {
			if (this.isPostRequest()) {
				PagerHelper.initPagination(this.getRequest());
				if (kf04DTO == null) {
					kf04DTO = new KF04DTO();
				}
				String akb020 = BizHelper.getAkb020();
				kf04DTO.setAkb020(akb020);
				// 把框架的分页信息set到DTO
				kf04DTO.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				kf04DTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());
				CatalogMatchImportVS catalogMatchImportVS = (CatalogMatchImportVS) hygeiaServiceManager
						.getBean("catalogMatchImportVS", akb020);
				// ListResult接口作为返回值
				ListResult listResult = catalogMatchImportVS.queryCataMatchDetatil(kf04DTO);

				// 把结果集返回页面的时候，set分页信息（从ListResult里面取出结果集，返回的list和count）
				PagerHelper.getPaginationObj().setCount(listResult.getCount());
				DataGridHelper.render(this.getRequest(), this.getResponse(),
						PagerHelper.getPaginatedList(listResult.getList()));
			}

		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	/**
	 * 如果校验到中途时允许重新校验
	 * 2017-12-04
	 */
	public String velidDate(){
		try {
			if (kf03DTO == null) {
				kf03DTO = new KF03DTO();
			}
			kf03DTO.setAkb020(BizHelper.getAkb020());
			kf03DTO.setAaa027(BizHelper.getAaa027());
			CatalogMatchImportVS catalogMatchImportVS = (CatalogMatchImportVS) hygeiaServiceManager
					.getBean("catalogMatchImportVS", BizHelper.getAkb020());
			catalogMatchImportVS.velidDate(kf03DTO);
		} catch (Exception e) {
			logger.error(ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
	    }
		return NONE;
	}
	
	public KF03DTO getKf03DTO() {
		return kf03DTO;
	}

	public void setKf03DTO(KF03DTO kf03dto) {
		kf03DTO = kf03dto;
	}

	public KF04DTO getKf04DTO() {
		return kf04DTO;
	}

	public void setKf04DTO(KF04DTO kf04dto) {
		kf04DTO = kf04dto;
	}

}
