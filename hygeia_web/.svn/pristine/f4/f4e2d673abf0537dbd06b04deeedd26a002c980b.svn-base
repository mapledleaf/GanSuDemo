package com.powersi.ssm.biz.medicare.hosp.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.powersi.asyn.service.AsynService;
import com.powersi.biz.medicare.comm.pojo.Kc90DTO;
import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.diagnose.service.AsnyHandPhotoService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.DAOHelper;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

@Action(value = "EquipmentAction", results = {
		@Result(name = "electronicReport", location = "/pages/biz/medicare/comm/ElectronicReport.jsp") })
public class EquipmentAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	protected List<File> filename;
	@Autowired
	@Qualifier("asynService")
	private AsynService asynService;
	@Autowired
	HygeiaServiceManager hygeiaServiceManager;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private Kc90DTO kc90Dto;
	/**
	 * 保存用户选择的摄像头
	 * @return
	 */
	public String saveCameraInfo() {
		try {
			Object cameraInfo = getParameter("cameraInfo");
			Map<String, Object> sysCameraConfig = new HashMap<>();
			sysCameraConfig.put("AAE100", "1");
			sysCameraConfig.put("AKB020", BizHelper.getAkb020());
			sysCameraConfig.put("USER_ID", BizHelper.getLoginUser());
			sysCameraConfig.put("USER_NAME", BizHelper.getUserName());
			sysCameraConfig.put("CAMERA_DATA", cameraInfo.toString());
			sysCameraConfig.put("CAMERA_ID", UUID.randomUUID().toString());
			String[] deWhere = {"USER_ID"};
			DAOHelper.delete("sys_camera_config", sysCameraConfig, deWhere);
			DAOHelper.insert("sys_camera_config", sysCameraConfig);
			setJSONReturn("设置成功！");
		} catch (Exception e) {
			saveJSONError("摄像头配置出错：" + e.getMessage());
		}
		return NONE;
	}
	
	@SuppressWarnings("rawtypes")
	public String getConfig() {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("	SELECT * FROM sys_camera_config");
			sql.append("	WHERE USER_ID = '"+BizHelper.getLoginUser()+"'");
			List camera = DBHelper.executeList(sql.toString());
			setJSONReturn(camera.get(0));
		} catch (Exception e) {
			saveJSONError("获取摄像头配置出错：" + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 文件上传
	 * @return
	 */
	public String savePhoto() {
		String type=getParameter("type");
		if(null!=type && !"".equals(type) && "1".equals(type)){
			savetake();
		}else{
			try {
				String kc90id = UUID.randomUUID().toString();
				if(filename!=null && filename.size()>0) {
					Kc90DTO kc90 = new Kc90DTO();
					kc90.setKc90id(kc90id);
					kc90.setAkb020(getParameter("akb020"));
					kc90.setAac002(getParameter("aac002"));
					File saveFile = null;
					File file = filename.get(0);
					String uploadFileName = String.valueOf(new Date().getTime()) + "_chenJieDsb.jpg";
					try {
						String filepath = ServletActionContext.getServletContext().getRealPath("/biz/img");
						File saveDir = new File(filepath);
						if (!saveDir.exists()) 
							saveDir.mkdirs();
						saveFile = new File(filepath + File.separator + uploadFileName);
						FileUtils.copyFile(file, saveFile);
						asynService.addWork(this, "saveTemPhoto", new Object[]{kc90,saveFile});
					} catch (Exception e) {
						logger.error("采集业务图像出错 -文件读取出错 - 错误信息："+e.getMessage());
					}
				}
				setJSONReturn(kc90id);
			} catch (Exception e) {
				throw new HygeiaException("保存图片失败！");
			}
		}
		return NONE;
		
	}
	
	/**
	 * 保存图片（临时）
	 * @param kc90
	 * @param saveFile
	 */
	public void saveTemPhoto(Kc90DTO kc90,File saveFile) {
		AsnyHandPhotoService asnyHandPhotoService = this.hygeiaServiceManager.
				getBeanByClass(AsnyHandPhotoService.class, BizHelper.getAkb020());
			InputStream inputStream = null;
			byte[] buffer = new byte[4096];
			try {
				inputStream = new FileInputStream(saveFile);
				ByteArrayOutputStream baops = new ByteArrayOutputStream();
				int n = 0;
				while (-1 != (n = inputStream.read(buffer))) {
					baops.write(buffer, 0, n);
				}
				inputStream.close();
				kc90.setAae100("0");
				kc90.setBkc291(baops.toByteArray());
				kc90.setBkc292("1");
				asnyHandPhotoService.saveTemPhoto(kc90);
			} catch (Exception e) {
				logger.error("采集业务图像出错 -错误信息："+e.getMessage());
			}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String fingerVeins() {
		try {
			Map paras = getAllParameters();
			AsnyHandPhotoService asnyHandPhotoService = this.hygeiaServiceManager.
					getBeanByClass(AsnyHandPhotoService.class, BizHelper.getAkb020());
			paras.put("akb020", BizHelper.getAkb020());
			paras.put("aaa027", BizHelper.getAaa027());
			paras.put("pkm005", BizHelper.getLoginUser());
			paras.put("pkm006", BizHelper.getUserName());
			int bpd591 = asnyHandPhotoService.saveTemFingerVeins(paras);
			setJSONReturn(bpd591);
		} catch (Exception e) {
			logger.error("采集指静脉数据失败！错误信息：" + e.getMessage());
			throw new HygeiaException(e.getMessage());
		}
		return NONE;
	}
	
	public String modeling() {
		try {
			AsnyHandPhotoService asnyHandPhotoService = this.hygeiaServiceManager.
					getBeanByClass(AsnyHandPhotoService.class, BizHelper.getAkb020());
			int bpd591 = asnyHandPhotoService.modeling(kc90Dto);
			setJSONReturn(bpd591);
		} catch (Exception e) {
			throw new HygeiaException("指静脉建模失败,错误信息："+e.getMessage());
		}
		return NONE;
	}
	

	/**
	 * 文件上传
	 * @return
	 */
	public String savetake() {
		try {
			if(filename!=null && filename.size()>0) {
				DiagnoseInfoDTO diagnoseInfoDTO = new DiagnoseInfoDTO();
				diagnoseInfoDTO.setAkb020(getParameter("akb020"));
				diagnoseInfoDTO.setAac002(getParameter("aac002"));
				diagnoseInfoDTO.setAac003("密码重置");
				File saveFile = null;
				File file = filename.get(0);
				String uploadFileName = String.valueOf(new Date().getTime()) + "_chenJieDsb01.jpg";
				try {
					String filepath = ServletActionContext.getServletContext().getRealPath("/biz/img");
					File saveDir = new File(filepath);
					if (!saveDir.exists()) 
						saveDir.mkdirs();
					saveFile = new File(filepath + File.separator + uploadFileName);
					FileUtils.copyFile(file, saveFile);
				} catch (Exception e) {
					logger.error("采集业务图像出错 -文件读取出错 - 错误信息："+e.getMessage());
				}
				
				AsnyHandPhotoService asnyHandPhotoService = this.hygeiaServiceManager.
						getBeanByClass(AsnyHandPhotoService.class, BizHelper.getAkb020());
					InputStream inputStream = null;
					byte[] buffer = new byte[4096];
					try {
						inputStream = new FileInputStream(saveFile);
						ByteArrayOutputStream baops = new ByteArrayOutputStream();
						int n = 0;
						while (-1 != (n = inputStream.read(buffer))) {
							baops.write(buffer, 0, n);
						}
						inputStream.close();
						diagnoseInfoDTO.setBkc290( new String(Base64.encodeBase64(baops.toByteArray())));
						asnyHandPhotoService.savePhotoUpdateKz11SaveKz21(diagnoseInfoDTO);			
					} catch (Exception e) {
						
						throw new HygeiaException("保存图片失败！"+e);
					}		
			}
		} catch (Exception e) {
			throw new HygeiaException("保存图片失败！"+e);
		}
		return NONE;
	
	}
		
		
	public Kc90DTO getKc90Dto() {
		return kc90Dto;
	}
	public void setKc90Dto(Kc90DTO kc90Dto) {
		this.kc90Dto = kc90Dto;
	}
	public List<File> getFilename() {
		return filename;
	}
	public void setFilename(List<File> filename) {
		this.filename = filename;
	}
	public AsynService getAsynService() {
		return asynService;
	}
	public void setAsynService(AsynService asynService) {
		this.asynService = asynService;
	}
	
}
