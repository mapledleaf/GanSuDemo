package com.powersi.ssm.biz.medicare.inhospital.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.powersi.biz.medicare.inhospital.pojo.CheckCureInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.KE14;
import com.powersi.biz.medicare.inhospital.service.api.mcce.MCCEbizh410001Service;
import com.powersi.biz.medicare.inhospital.service.api.mcce.QueryCheckCureInfoService;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.util.StringUtil;

@Action(value = "CheckCureInfoManagerAction", results = {
		@Result(name = "success", location = "/pages/biz/medicare/inhospital/InputCheckCureInfo.jsp") })
public class CheckCureInfoManagerAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private File imgFile;// 上传文件
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	private CheckCureInfoDTO checkCureInfoDTO;

	/**
	 * 下载影像图片
	 * 
	 * @param ke14id
	 */
	public String downLoadBka511() {
		try {
			if (StringUtil.isBlank(checkCureInfoDTO.getKe14id())) {
				throw new HygeiaException("查询主键为空，请检查相关信息！");
			}
			QueryCheckCureInfoService queryCheckCureInfoService = hygeiaServiceManager
					.getBeanByClass(QueryCheckCureInfoService.class, BizHelper.getAkb020());
			KE14 ke14 = queryCheckCureInfoService.queryKE14(checkCureInfoDTO);
			byte[] data = ke14.getBka511();

			HttpServletResponse response = getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setContentType("image/jpeg;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + ke14.getBka510() + ".gif");
			OutputStream outputStream = response.getOutputStream();
			InputStream in = new ByteArrayInputStream(data);
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = in.read(buf, 0, 1024)) != -1) {
				outputStream.write(buf, 0, len);
			}
			outputStream.close();
			outputStream.flush();
			in.close();
		} catch (Exception e) {
			logger.error("获取影像图片失败！错误信息：" + ToolUtil.getExceptionInfo(e));
		}
		return null;
	}

	/**
	 * 获取检查诊疗结果
	 */
	public String queryCheckCureInfos() {
		try {
			checkCureInfoDTO.setAkb020(BizHelper.getAkb020());
			MCCEbizh410001Service mCCEbizh410001Service = hygeiaServiceManager
					.getBeanByClass(MCCEbizh410001Service.class, BizHelper.getAkb020());
			List<KE14> checkCureInfoDTORows = mCCEbizh410001Service.queryCheckCureInfos(checkCureInfoDTO);
			setJSONReturn(checkCureInfoDTORows);
		} catch (Exception e) {
			logger.error("上传预约挂号号源失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveJSONError(e.getMessage());
		}
		return NONE;
	}

	/**
	 * 上传检查诊疗结果
	 */
	public String saveCheckCureInfo() {
		FileInputStream is = null;
		FileChannel fc = null;
		try {
			MCCEbizh410001Service mCCEbizh410001Service = hygeiaServiceManager
					.getBeanByClass(MCCEbizh410001Service.class, BizHelper.getAkb020());
			try {
				is = new FileInputStream(imgFile);
				fc = is.getChannel();
				if (fc.size() > 60 * 1024) {
					is.close();
					fc.close();
					throw new HygeiaException("影像图片大小最大值为60KB");
				}

				byte[] bka511 = null;
				bka511 = FileCopyUtils.copyToByteArray(is);
				if (bka511 == null || bka511.length == 0) {
					throw new HygeiaException("影像图片为空【bka511】");
				}
				checkCureInfoDTO.setAkb020(BizHelper.getAkb020());
				mCCEbizh410001Service.saveCheckCureInfo(bka511, checkCureInfoDTO);

				setJSONReturn("上传检查诊疗结果成功", getCheckCureInfoDTO());

			} catch (Exception e) {
				logger.error("上传检查诊疗结果失败！错误信息：" + ToolUtil.getExceptionInfo(e));
				saveJSONError(e.getMessage());
				is.close();
				fc.close();
				return NONE;
			}

			is.close();
			fc.close();
		} catch (Exception ex) {
			try {
				is.close();
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			logger.error("上传检查诊疗结果失败！错误信息：" + ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public CheckCureInfoDTO getCheckCureInfoDTO() {
		if (checkCureInfoDTO == null) {
			checkCureInfoDTO = new CheckCureInfoDTO();
		}
		return checkCureInfoDTO;
	}

	public void setCheckCureInfoDTO(CheckCureInfoDTO checkCureInfoDTO) {
		this.checkCureInfoDTO = checkCureInfoDTO;
	}

}
