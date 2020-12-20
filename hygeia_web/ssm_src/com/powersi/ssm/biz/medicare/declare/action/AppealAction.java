/*package com.powersi.biz.hospital.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;

import com.powersi.biz.bizutil.service.DoctorService;
import com.powersi.biz.bizutil.service.HospitalService;
import com.powersi.biz.bizutil.service.PersonInfoQueryService;
import com.powersi.biz.hospital.service.AppealService;
import com.powersi.biz.util.WordUtil;
import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.UserInfo;
import com.powersi.hygeia.framework.util.BeanHelper;
import com.powersi.hygeia.framework.util.CollectionHelper;
import com.powersi.hygeia.framework.util.DateFunc;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

*//**
 * 医院用户申诉Action层
* @Description: TODO() 
* @author tanrui
* @date 2016年7月18日 下午4:58:44 
*
 *//*
@SuppressWarnings("all")
public class AppealAction extends BaseAction {

	private static final long serialVersionUID = 8006960981961828744L;

	private static AppealService service = BeanHelper.getBean(AppealService.class);
	
	private static PersonInfoQueryService personInfoService = BeanHelper.getBean(PersonInfoQueryService.class);
	
	private static DoctorService doctorService = BeanHelper.getBean(DoctorService.class);
	
	private static HospitalService hospitalService = BeanHelper.getBean(HospitalService.class);
	
	*//**
	 * 获取业务风险自查申诉的信息
	* @Description: TODO() 
	* @return
	* @date 2016年7月18日 下午5:02:26 
	* @author tanrui
	 *//*
	public String getBusinessAppealInfo(){
		try {
			PagerHelper.initPagination(getRequest());
			List<Map<String, Object>> list = service.searchBusinessAppealInfo(getAllParameters());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(list));
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	
	*//**
	 * 业务自查申诉详细信息
	* @Description: TODO(查询对象的就诊和结算信息) 
	* @return
	* @date 2016年7月19日 下午3:04:29 
	* @author tanrui
	 *//*
	public String getBusinessAppealDetail() {
		try {
			String aaz217 = getParameter("aaz217");
			String aaz328 = getParameter("aaz328");
			String wkd001 = getParameter("wkd001");
			String issuedFlag = getParameter("issuedFlag");// 1表示自动下发疑点，0表示中心审核后下发疑点
			
			Map<String, Object> map = personInfoService.queryDiagnoseAccountInfo(new BigDecimal(aaz217));
			map.put("aaz217", aaz217);
			map.put("aaz328", aaz328);
			this.setAttribute("diagnoseMap", map);
		} catch (Exception e) {
			saveError(e.getMessage());
		}
		if (StringUtils.equals(getRequest().getParameter("pagetype"), "1")){
			return "businessHospAppealDetail";
		}
		return "businessAppealDetail";
	}
	
	*//**
	 * 获取业务待申诉信息
	* @Description: TODO() 
	* @return
	* @date 2016年7月22日 上午11:25:24 
	* @author tanrui
	 *//*
	public String getBusinessStayAppealInfo(){
		try {
			PagerHelper.initPagination(getRequest());
			List<Map<String, Object>> list = service.searchStayAppealInfo(getAllParameters());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(list));
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	
	*//**
	 * 查询申诉信息
	* @Description: TODO() 
	* @return
	* @date 2016年7月27日 上午10:08:59 
	* @author tanrui
	 *//*
	public String getAppealInfo(){
		try {
			
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	
	*//**
	 * 保存申诉信息
	* @Description: TODO() 
	* @return
	* @date 2016年7月25日 上午11:04:51 
	* @author tanrui
	 *//*
	public String saveAppealExplain(){
		try {
			service.saveAppealExplain(getAllParameters());
			renderJson("code", 0);
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	
	*//**
	 * 上传申诉材料
	* @Description: TODO() 
	* @return
	* @date 2016年7月25日 下午8:22:49 
	* @author tanrui
	 *//*
	public String uploadAppealData(){
		try {
			if (CollectionHelper.isNotEmpty(uploads)) {
				String bpd413 = getParameter("bpd413");
				String file_content = uploads.get(0).getAbsolutePath();
				String file_name = uploadsFileName.get(0);
				String flag = getParameter("flag");// 附件是否已上传
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("bpd413", bpd413);
				params.put("file_content", file_content);
				params.put("file_name", file_name);
				params.put("flag", flag);
				service.uploadAppealData(params);
				saveJSONMessage("上传成功");
			}
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	
	*//**
	 * 检查上传的文件名是否重复
	* @Description: TODO() 
	* @return
	* @date 2016年7月25日 下午8:33:39 
	* @author tanrui
	 *//*
	public String checkFileName(){
		try {
			String bpd413 = getParameter("bpd413");
			String fileName = getParameter("fileName");
			boolean flag = service.checkFileNameIsExist(bpd413, fileName);
			renderJson(flag);
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	
	*//**
	 * 获取申诉材料的文件信息
	* @Description: TODO() 
	* @return
	* @date 2016年7月26日 上午10:40:57 
	* @author tanrui
	 *//*
	public String getAppealDataInfo(){
		try {
			String bpd413 = getParameter("bpd413");
			List<Map<String, Object>> list = service.searchAppealDataInfo(bpd413);
			renderJson(list);
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	
	*//**
	 * 删除申诉材料
	* @Description: TODO() 
	* @return
	* @date 2016年7月26日 上午11:12:30 
	* @author tanrui
	 *//*
	public String deleteAppealData(){
		try {
			String aaz530 = getParameter("aaz530");
			
			service.deleteAppealData(aaz530);
			saveJSONMessage("删除成功");
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	
	*//**
	 * 获取申诉说明
	* @Description: TODO() 
	* @return
	* @date 2016年8月2日 下午3:32:05 
	* @author tanrui
	 *//*
	public String getAppealExplain(){
		try {
			Map<String, Object> map = service.searchAppealExplain(getAllParameters());
			renderJson(map);
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	
	*//**
	 * 获取医院申诉和医师申诉页面
	* @Description: TODO() 
	* @return
	* @date 2016年8月4日 下午4:22:16 
	* @author tanrui
	 *//*
	public String getAppealPage(){
		String page = getParameter("page");
		try {
			UserInfo userInfo = BusiContext.getUserInfo();
			String user_class = userInfo.getString("user_class");//用户类别
			String akb020 = userInfo.getString("hospital_id");
			this.setAttribute("user_class", user_class);
			this.setAttribute("akb020", akb020);
			this.setAttribute("ape139", getParameter("ape139"));
		} catch (Exception e) {
			saveError(e.getMessage());
		}
		return page;
	}
	
	*//**
	 * 获取医师风险自查申诉信息
	* @Description: TODO() 
	* @return
	* @date 2016年8月3日 下午5:05:37 
	* @author tanrui
	 *//*
	public String getDoctorAppealInfo(){
		try {
			Map params = getAllParameters();
			params.put("apa709", "2");
			PagerHelper.initPagination(getRequest());
			List<Map<String, Object>> list = service.searchAppealInfo(params);
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(list));
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	
	*//**
	 * 返回医师申诉处理页面
	* @Description: TODO(查询医师基本信息) 
	* @return
	* @date 2016年8月3日 下午7:49:59 
	* @author tanrui
	 *//*
	public String getDoctorAppealHandle(){
		try {
			String aaz010 = getParameter("aaz010");
			String aaz328 = getParameter("aaz328");
			Map<String, Object> doctor = doctorService.searchDoctorInfo(new BigDecimal(aaz010));
			doctor.put("aaz328", aaz328);
			doctor.put("aaz010", aaz010);
			this.setAttribute("doctorMap", doctor);
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return "getDoctorAppealHandle";
	}
	
	*//**
	 * 获取待申诉的详细
	* @Description: TODO(分为医师和医院) 
	* @return
	* @date 2016年8月3日 下午9:14:27 
	* @author tanrui
	 *//*
	public String getStayAppealDetail(){
		try {
			String aaz328 = getParameter("aaz328");
			PagerHelper.initPagination(getRequest());
			List<Map<String, Object>> list = service.searchStayAppealDetail(new BigDecimal(aaz328));
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(list));
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	
	*//**
	 * 获取申诉说明列表
	* @Description: TODO() 
	* @return
	* @date 2016年8月4日 下午2:27:22 
	* @author tanrui
	 *//*
	public String getAppealExplainList(){
		try {
			PagerHelper.initPagination(getRequest());
			List<Map<String, Object>> list = service.searchAppealExplainList(getAllParameters());
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(list));
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	
	*//**
	 * 获取医院风险自查申诉信息
	* @Description: TODO() 
	* @return
	* @date 2016年8月4日 下午4:23:19 
	* @author tanrui
	 *//*
	public String getHospitalAppealInfo(){
		try {
			Map params = getAllParameters();
			params.put("apa709", "3");
			PagerHelper.initPagination(getRequest());
			List<Map<String, Object>> list = service.searchAppealInfo(params);
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(list));
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	
	*//**
	 * 返回医院申诉处理页面
	* @Description: TODO() 
	* @return
	* @date 2016年8月4日 下午4:24:01 
	* @author tanrui
	 *//*
	public String getHospitalAppealHandle(){
		try {
			String aaz010 = getParameter("aaz010");
			String aaz328 = getParameter("aaz328");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("aaz107", aaz010);
			Map<String, Object> hospital = hospitalService.searchHospitalInfo(params);
			if (null == hospital) {
				hospital = new HashMap();
			} 
			hospital.put("aaz328", aaz328);
			hospital.put("aaz010", aaz010);
			this.setAttribute("hospitalMap", hospital);
		} catch (Exception e) {
			e.printStackTrace();
			saveJSONError(e.getMessage());
		}
		return "getHospitalAppealHandle";
	}
	
	*//**
	 * 下载申诉材料
	* @Description: TODO() 
	* @return
	* @date 2016年8月4日 下午9:00:26 
	* @author tanrui
	 *//*
	public String downLoadAppealData(){
		InputStream is = null;
		OutputStream os = null;
		try {
			String aaz530 = getParameter("aaz530");
			Map<String, Object> af74 = service.searchAppealDataByAaz530(new BigDecimal(aaz530));
			String ape022 = UtilFunc.getString(af74, "ape022");
			String ape023 = UtilFunc.getString(af74, "ape023");
			// path是指欲下载的文件的路径。
            File file = new File(ape023 + "/" + ape022);
            if (!file.exists()){
            	saveError("文件不存在！");
            	return "error";
            }
            // 以流的形式下载文件。
            is = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            
            // 清空response
            getResponse().reset();
            // 设置response的Header
            String userAgent = getRequest().getHeader("USER-AGENT");
            ape022 = WordUtil.fileNameEncoder(userAgent, ape022);
            getResponse().addHeader("Content-Disposition", "attachment;filename=" + ape022);
            getResponse().addHeader("Content-Length", "" + file.length());
            os = new BufferedOutputStream(getResponse().getOutputStream());
            getResponse().setContentType("application/octet-stream");
            
            os.write(buffer);
		} catch (Exception e) {
			saveError(e.getMessage());
		} finally{
			if (is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null){
				try {
					os.flush();
		            os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return NONE;
	}
	
	*//**
	 * 医院端审核
	* @Description: TODO() 
	* @return
	* @date 2016年10月27日 下午5:33:51 
	* @author tanrui
	 *//*
	public String audit(){
		try {
			service.audit(getAllParameters());
			setJSONReturn("success");
		} catch (Exception e) {
			saveJSONError(e.getMessage());
		}
		return NONE;
	}
	
	*//** 定义变量 **//*
	private List<File> uploads;

	private List<String> uploadsFileName;
	
	public List<File> getUploads() {
		return uploads;
	}

	public void setUploads(List<File> uploads) {
		this.uploads = uploads;
	}

	public List<String> getUploadsFileName() {
		return uploadsFileName;
	}

	public void setUploadsFileName(List<String> uploadsFileName) {
		this.uploadsFileName = uploadsFileName;
	}
	
}
*/