package com.powersi.ssm.biz.medicare.treatmentregister.action;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.powersi.biz.maternity.treatmentregister.pojo.TreatmentRegisterDTO;
import com.powersi.biz.maternity.treatmentregister.service.TreatmentRegisterService;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.comm.utils.UtilFunc;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.util.DBHelper;
import com.powersi.hygeia.framework.util.PaginationHelper;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.DataGridHelper;
import com.powersi.hygeia.web.util.PagerHelper;
import com.powersi.ssm.biz.medicare.api.dto.APIRemoteCallParam;
import com.powersi.ssm.biz.medicare.api.service.APIRemoteCallService;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


@ParentPackage("default")
@Namespace("/maternity")
@Action(value="MaternityRegAction",results={
		@Result(name="insuranceInfQuery",location="/pages/biz/medicare/maternity/treatmentRegister/maternityRegister.jsp"),
		@Result(name="showRegInf",location="/pages/biz/injury/maternity/treatmentApprobate/queryRegInf.jsp"),
		@Result(name="editRegInf",location="/pages/biz/injury/maternity/treatmentApprobate/maternityRegister.jsp"),
		@Result(name="amc292Img",location="/pages/biz/injury/maternity/treatmentApprobate/imgInfo.jsp")
})
public class MaternityRegAction extends BaseAction{

	private static final long serialVersionUID = 9095095110282525460L;
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private APIRemoteCallService aPIRemoteCallService;
	@Autowired
	private BeanService beanService;
	private TreatmentRegisterDTO trDTO;    //生育登记信息DTO
	private String  sySel;
	private String result;
	private File imgFile;// 上传文件
	private String imgFileName;// 上传文件名称

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSySel() {
		return sySel;
	}

	public void setSySel(String sySel) {
		this.sySel = sySel;
	}
	
	/**
	 * registerInfAjaxQuery的概要说明： 人员是否已进行登记操作查询
	 * 
	 * @param 的中文名称： 
	 * @return String
	 */	
	
	public String registerInfAjaxQuery() {
		try{
			TreatmentRegisterService trservice = this.hygeiaServiceManager.getBeanByClass(TreatmentRegisterService.class, BizHelper.getAkb020());
			trDTO.setAaa027(BizHelper.getAaa027());
			trservice.apprCheck(trDTO);
			
		}catch(Exception e){
			e.printStackTrace();
			 saveJSONError(e.getMessage());
		}
		
		return NONE;
			
	}
	
	
	/**
	 * insuranceInfQuery的概要说明： 参保人员信息显示
	 * 
	 * @param 的中文名称： 
	 * @return String
	 */	
	 public String insuranceInfQuery(){
		
		 TreatmentRegisterService trservice = this.hygeiaServiceManager.getBeanByClass(TreatmentRegisterService.class, BizHelper.getAkb020());
		 //trDTO.setAaa027(BizHelper.getAaa027());
		 TreatmentRegisterDTO trdto_t = new TreatmentRegisterDTO();
		 
		    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			Timestamp now = DBHelper.getDBTimestamp();
			try {
				List rsList = trservice.insuranceInfQuery(trDTO);
				String right=trDTO.getRight();
				if(null != rsList && rsList.size()>0){	
					Map map = (HashMap)rsList.get(0) ;
					trDTO.setAac001s(trDTO.getAac001()) ;
					if(map.get("aac002")!=null)
						trDTO.setAac002(map.get("aac002").toString()) ;
					if(map.get("aac003")!=null)
						trDTO.setAac003(map.get("aac003").toString()) ;
					if(map.get("aac004")!=null)
						trDTO.setAac004(map.get("aac004").toString()) ;
					if(map.get("aac058")!=null)
						trDTO.setAac058(map.get("aac058").toString()) ;
					if(map.get("aae030")!=null)
						trDTO.setAae030(Long.valueOf(map.get("aae030").toString())) ;
					if(map.get("ljys")!=null)
						trDTO.setLjys(map.get("ljys").toString());				
					if(map.get("aab001")!=null)
					trDTO.setAab001(Long.valueOf(map.get("aab001").toString())) ;
					if(map.get("aab019")!=null)
						trDTO.setAab019(map.get("aab019").toString()) ;
					if(map.get("aab999")!=null)
						trDTO.setAab999(map.get("aab999").toString()) ;
					if(map.get("aab069")!=null)
						trDTO.setAab069(map.get("aab069").toString()) ;	
					if(map.get("aac001")!=null)
						trDTO.setAac001(Long.valueOf(map.get("aac001").toString())) ;	
					if(map.get("aaz157")!=null)
						trDTO.setAaz157(Long.valueOf(map.get("aaz157").toString())) ;	
					if(map.get("aaz159")!=null)
						trDTO.setAaz159(Long.valueOf(map.get("aaz159").toString())) ;
					if(map.get("aac114")!=null)
						trDTO.setAac114(map.get("aac114").toString()) ;
					if(map.get("bmc014")!=null)
						trDTO.setBmc014(map.get("bmc014").toString()) ;
					if(map.get("babd05")!=null)
						trDTO.setBabd05(map.get("babd05").toString()) ;
					if(map.get("amc027")!=null)
						trDTO.setAmc027(map.get("amc027").toString()) ;
					if(map.get("aac066")!=null)
						trDTO.setAac066(map.get("aac066").toString()) ;
					
					this.setAttribute("right", right);
					this.setAttribute("aac001", trDTO.getAac001());
					this.setAttribute("queryString", trDTO.getAac003());
				}else {
					saveError("未查到符合条件的数据！") ;
				}
			} catch (Exception e) {
				e.printStackTrace();
				 saveJSONError("查询出错："+e.getMessage());
			}
		 return "insuranceInfQuery";
	 
	 }
	 
	 
 
 /**
	 * registerInfSave的概要说明： 登记人员信息保存
	 * 
	 * @param 的中文名称： 
	 * @return String
	 */	
	 public String registerInfSave(){
		 try {
			 TreatmentRegisterService service = this.hygeiaServiceManager.getBeanByClass(TreatmentRegisterService.class, BizHelper.getAkb020());
			 //trDTO.setAaa027(BizHelper.getAaa027());
			 trDTO.setAkb020(BizHelper.getAkb020());
			 trDTO.setAae011(BizHelper.getLoginUser());
			 //保存生育证复印件
//			 if(imgFile!=null){
//					FileInputStream is = new FileInputStream(imgFile);
//					byte[] bkc292= FileCopyUtils.copyToByteArray(is);
//					if(bkc292!=null &&bkc292.length!=0){
//						trDTO.setAmc292(bkc292);
//					}
//					is.close();
//				}
			 service.registerInfSave(trDTO);
			 setJSONReturn("保存成功");
			} catch (Exception e) {
				e.printStackTrace();
				saveJSONError("保存失败：" + e.getMessage());
			}
			return NONE;
	 }
	 
	 
		 
 /**
	 * showRegisterInf的概要说明： 登记人员信息显示
	 * 
	 * @param 的中文名称： 
	 * @return String
	 */	
	 public String showRegisterInf(){
		 
//		 try {
//			 PagerHelper.initPagination(getRequest());
//				TreatmentRegisterService trservice = getBean(TreatmentRegisterService.class) ;
//				List rsList = trservice.showRegisterInf(trDTO);						
//				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(rsList));
//				if(null != rsList && rsList.size()>0){
//					this.setAttribute("rsList", rsList);
//				}else{
//					saveMessage("没有匹配的登记信息！") ;
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				saveError("查询出错！") ;
//			}
			return NONE;
	 }
		 
	 /**
	 * regInfUpdate的概要说明： 登记人员信息更新
	 * 
	 * @param 的中文名称： 
	 * @return String
	 */	
	public String regInfUpdate(){
//		try {
//			TreatmentRegisterService trservice = getBean(TreatmentRegisterService.class) ;
//			String rs = trservice.regInfUpdate(trDTO);
//			if(rs.equals("ok")){
//				setJSONReturn("修改成功!");
//			}else{
//				saveJSONError("修改失败！") ;	
//			}
//		} catch (Exception e) {
//				e.printStackTrace();
//				saveJSONError(e.getMessage());
//		}
		return NONE;
	 }
	 
				 
	 /**
	 * registerInfDelete的概要说明： 登记人员信息删除
	 * 
	 * @param 的中文名称： 
	 * @return String
	 */	
	public String registerInfDelete(){
//		try{
//			TreatmentRegisterService trservice = getBean(TreatmentRegisterService.class) ;
//			String rs = trservice.registerInfDelete(trDTO);						
//			if(rs.equals("ok")){
//				setJSONReturn("删除成功！", rs);
//			}else{
//				saveJSONError("删除失败！该人员已经产生待遇信息。");
//			}
//		} catch(Exception ex){
//			saveJSONError(ex);
//	    }			 
		return NONE;
	}
	public String getAmc292Img(){
		try{
			TreatmentRegisterService trservice = this.hygeiaServiceManager.getBeanByClass(TreatmentRegisterService.class, BizHelper.getAkb020());
			if(trDTO==null){
				trDTO = new TreatmentRegisterDTO();
			}
			trDTO.setAaa027(BizHelper.getAaa027());
			trDTO.setAkb020(BizHelper.getAkb020());
			List rsList = trservice.getAmc292Img(trDTO);
			if(rsList!=null&&rsList.size()>0&&!"".equals(UtilFunc.getString((Map) rsList.get(0), "amc292",""))){
				BASE64Decoder decoder = new BASE64Decoder();  
				byte[] amc292 =decoder.decodeBuffer(UtilFunc.getString((Map) rsList.get(0), "amc292"));
				InputStream input = new ByteArrayInputStream(amc292);
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
			
		}catch(Exception e){
			e.printStackTrace();
			saveJSONError(e);
			return ERROR;
		}
		return NONE;
	}
	
	 /**
	 * queryRegisterInf的概要说明： 登记人员信息显示
	 * 
	 * @param 的中文名称： 
	 * @return String
	 */	
	 public String queryRegisterInf(){
		 
		 try {
			 	PagerHelper.initPagination(getRequest());
				TreatmentRegisterService trservice = this.hygeiaServiceManager.getBeanByClass(TreatmentRegisterService.class, BizHelper.getAkb020());
				if(trDTO==null){
					trDTO = new TreatmentRegisterDTO();
				}
				trDTO.setAaa027(BizHelper.getAaa027());
				trDTO.setAkb020(BizHelper.getAkb020());
				//用于分页
				int pageIndex = PagerHelper.getPaginationObj().getPageIndex();
				int pageSize = PagerHelper.getPaginationObj().getPageSize();

				int first_row = pageSize * (pageIndex - 1) + 1;
				int last_row = pageSize * pageIndex;
				Map<String,Object> mRet = trservice.queryRegisterInf(trDTO,first_row,last_row);						
				List declDetail = (List) mRet.get("detail");
				int row_sum = Integer.parseInt(mRet.get("row_sum").toString());
				if (row_sum > 0) {
					PaginationHelper.getPaginationObj().setCount(row_sum);
					DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(declDetail));
				}
//				if(null != rsList && rsList.size()>0){
//					this.setAttribute("rsList", rsList);
//				}else{
//					saveMessage("没有匹配的登记信息！") ;
//				}
			} catch (Exception e) {
				e.printStackTrace();
				saveJSONError(e);
			}
	 return NONE;
	}
	 /**
		 * 查询银行信息
		 * @return
		 */
	public String queryBankInfo() {
		try {
			//分页处理
			PagerHelper.initPagination(getRequest());
			TreatmentRegisterService trservice = this.hygeiaServiceManager.getBeanByClass(TreatmentRegisterService.class, BizHelper.getAkb020());
			if(trDTO==null){
				trDTO = new TreatmentRegisterDTO();
			}
			trDTO.setAaa027(BizHelper.getAaa027());
			trDTO.setAkb020(BizHelper.getAkb020());
			//用于分页
			int pageIndex = PagerHelper.getPaginationObj().getPageIndex();
			int pageSize = PagerHelper.getPaginationObj().getPageSize();

			int first_row = pageSize * (pageIndex - 1) + 1;
			int last_row = pageSize * pageIndex;
			Map<String,Object> mRet = trservice.queryBankInfo(trDTO,first_row,last_row);						
			List declDetail = (List) mRet.get("detail");
			int row_sum = Integer.parseInt(mRet.get("row_sum").toString());
			if (row_sum > 0) {
				PaginationHelper.getPaginationObj().setCount(row_sum);
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(declDetail));
			}
//			if(null != rsList && rsList.size()>0){
//				this.setAttribute("rsList", rsList);
//			}else{
//				saveMessage("没有匹配的登记信息！") ;
//			}
		} catch (Exception e) {
			e.printStackTrace();
			saveJSONError(e);
		}
		 return NONE;
	}
	public String checkBankInfo(){
		 try {
			 TreatmentRegisterService service = this.hygeiaServiceManager.getBeanByClass(TreatmentRegisterService.class, BizHelper.getAkb020());
			 trDTO.setAaa027(BizHelper.getAaa027());
			 trDTO.setAkb020(BizHelper.getAkb020());
			 List list=service.checkBankInfo(trDTO);
			 if(list!=null&&list.size()>0){
				 setJSONReturn("已经存在银行账号【"+UtilFunc.getString((Map) list.get(0), "aae010")+"】，无需重新录入");
			 }
			} catch (Exception e) {
				e.printStackTrace();
				saveJSONError("检查失败：" + e.getMessage());
			}
			return NONE;
	}
	 /**
	 * showRegInf的概要说明： 登记人员详细信息显示
	 * 
	 * @param 的中文名称： 
	 * @return String
	 */	
	 public String showRegInf(){
//		 try {
//			TreatmentRegisterService trservice = getBean(TreatmentRegisterService.class) ;
//			List rsList = trservice.changeRegInf(trDTO);	
//			if(null != rsList && rsList.size()>0){
//				Map map = (HashMap)rsList.get(0) ;
//				BeanHelper.copyProperties(map, trDTO);
//			}
//			//生育参保信息调用接口
//			List basePersonInfo=trservice.insuranceInfQuery(trDTO);
//			if(null != basePersonInfo && basePersonInfo.size()>0){	
//				Map map = (HashMap)basePersonInfo.get(0) ;
//				trDTO.setAac001s(trDTO.getAac001()) ;
//				if(map.get("aac002")!=null)
//					trDTO.setAac002(map.get("aac002").toString()) ;
//				if(map.get("aac003")!=null)
//					trDTO.setAac003(map.get("aac003").toString()) ;
//				if(map.get("aac004")!=null)
//					trDTO.setAac004(map.get("aac004").toString()) ;
//				if(map.get("aae030")!=null)
//					trDTO.setAae030(Long.valueOf(map.get("aae030").toString())) ;
//				if(map.get("ljys")!=null)
//					trDTO.setLjys(map.get("ljys").toString());				
//				if(map.get("aab001")!=null)
//					trDTO.setAab001(Long.valueOf(map.get("aab001").toString())) ;
//				if(map.get("aab019")!=null)
//					trDTO.setAab019(map.get("aab019").toString()) ;
//				if(map.get("aab999")!=null)
//					trDTO.setAab999(map.get("aab999").toString()) ;
//				if(map.get("aab069")!=null)
//					trDTO.setAab069(map.get("aab069").toString()) ;	
//				if(map.get("aac001")!=null)
//					trDTO.setAac001(Long.valueOf(map.get("aac001").toString())) ;	
//				}
//			//查询历史待遇信息先屏蔽
//		    PagerHelper.initPagination(getRequest());
//			List htrList = trservice.queryHistoryTreatment(trDTO);	
//			if(null != htrList && htrList.size()>0){
//				this.setAttribute("htrList", htrList);
//			}
//			//查询待遇审核信息先屏蔽
//		    PagerHelper.initPagination(getRequest());
//			List auditList = trservice.queryHistoryAudit(trDTO);	
//			if(null != auditList && auditList.size()>0){
//				this.setAttribute("auditList", auditList);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			saveError("查询出错！") ;
//		}
//		if("1".equals(trDTO.getFlag())){
//			return "showRegInf";
//		}else{
//			return "editRegInf";
//		}
		 return "" ;
		
	 }
    /**
	 * queryHistoryTreatment的概要说明：历史待遇查询
	 * 
	 * @param 的中文名称： 
	 * @return String
	 */	
	public String queryHistoryTreatment()
	{
//		try{
//			PagerHelper.initPagination(getRequest());
//			TreatmentRegisterService trservice = getBean(TreatmentRegisterService.class) ;
//			List rsList = trservice.queryHistoryTreatment(trDTO);	
//			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(rsList));
//		}catch(Exception e){
//			saveError("查询出错"+e.getMessage());
//		}
		return NONE;
	}
	
	

							 
	public TreatmentRegisterDTO getTrDTO() {
		return trDTO;
	}

	public void setTrDTO(TreatmentRegisterDTO trDTO) {
		this.trDTO = trDTO;
	}
	
	/**
	 * approbateInfDelete的概要说明： 信息删除
	 * @param 的中文名称： 
	 * @return String
	 */
	public String deleteInf(){
//		TreatmentRegisterService trservice = getBean(TreatmentRegisterService.class) ;	
//		int i = trservice.updateMc01(trDTO);
//		if(i == 1){
//			saveJSONMessage("删除成功");
//		}
//		else{
//			saveJSONMessage("删除失败");	
//		}		
		return NONE;
	}
	/**
	 * 判断分娩日期往前10个月是否有连续缴费
	 * @return String
	 */
	public String getLxjf(){
		try{
			TreatmentRegisterService service = this.hygeiaServiceManager.getBeanByClass(TreatmentRegisterService.class, BizHelper.getAkb020());
			trDTO.setAaa027(BizHelper.getAaa027());
			boolean flag = service.getLxjf(trDTO);
			if(flag){//是否满足连续缴费10个月，1不满足 0 ，满足
				saveJSONMessage("0");
			}
			else{
				saveJSONMessage("1");	
			}		
		}catch(Exception ex){
			saveJSONError("1");
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
	
}
