package com.powersi.ssm.biz.medicare.outland.action;


import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.biz.medicare.outland.pojo.OutBizDTO;
import com.powersi.biz.medicare.outland.service.api.OutBizApiService;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.ssm.biz.medicare.common.service.CodeTableSwitch;
import com.powersi.ssm.biz.medicare.common.service.CodeTableSwitchImpl;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.ssm.biz.medicare.outland.service.OutBizService;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

@Action(value = "OutBizAction", results = {
		@Result(name = "clinicSelected", location = "/pages/biz/medicare/query/ClinicSelectedQuery.jsp") })
public class OutBizAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	OutBizService ser;
	@Autowired
	private CommunalService communalService;
	
	private OutBizDTO outBizDTO;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("rawtypes")
	public String getPersonInfo(){
		try {
			String akb020 = BizHelper.getAkb020();
			OutBizApiService outService = hygeiaServiceManager.getBeanByClass(OutBizApiService.class, akb020);
			if (outBizDTO == null) {
				outBizDTO = new OutBizDTO();
			}
			Map personinfo = outService.getPersonInfo(akb020, outBizDTO);
			CodeTableSwitch codeService = new CodeTableSwitchImpl();
			Map mm = codeService.loadPersonInfo(personinfo,BizHelper.getAaa027());
			if(personinfo.size() > 0){
				setJSONReturn(mm);
			}else{
				setJSONReturn("no");
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError("查询人员信息出错！"+ex.getMessage() );
		}
		return NONE;
	}
	
	@SuppressWarnings("rawtypes")
	public String getOutAreaModifyInfo(){
		try {
			if (outBizDTO == null) {
				outBizDTO = new OutBizDTO();
			}
			PagerHelper.initPagination(getRequest());
			OutBizApiService outService = hygeiaServiceManager.
					getBeanByClass(OutBizApiService.class, BizHelper.getAkb020());
			outBizDTO.setAkb020(BizHelper.getAkb020());
			outBizDTO.setAab299(BizHelper.getAaa027());
			outBizDTO.setAaa027(BizHelper.getAaa027());
			Map<String, Object>  mReturn= outService.getOutAreaModifyInfo(outBizDTO);
			List mList = (List) mReturn.get("OtherPlaces");
			DataGridHelper.render(getRequest(), getResponse(),
					PagerHelper.getPaginatedList(mList));
		} catch (Throwable e) {
			this.getCommunalService().error(this.logger, e, null);
			this.saveJSONError("获取备案信息失败:"+e.getMessage());
			return ERROR;
		}
		return NONE;
	}
	
	@SuppressWarnings("unused")
	public String saveOutAreaInfo(){
		try {
			PagerHelper.initPagination(getRequest());
			int sReturn = 0;
			String akb020 = BizHelper.getAkb020();
			String objList = getParameter("objList");
			OutBizApiService outService = hygeiaServiceManager.getBeanByClass(OutBizApiService.class, akb020);
			if (outBizDTO == null) {
				outBizDTO = new OutBizDTO();
			}
			
			if (UtilFunc.hasText(objList)) {
				sReturn = outService.saveOutAreaInfo(akb020, outBizDTO, JsonHelper.toList(objList));
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError("保存申请信息出错！"+ex.getMessage() );
		}
		return NONE;
	}
	@SuppressWarnings("rawtypes")
	public String getCityAndDept(){
		try {
			String akb020 = BizHelper.getAkb020();
			if (outBizDTO == null) {
				outBizDTO = new OutBizDTO();
			}
			List cityAndDeptList = ser.getCityAndDept(akb020, outBizDTO);
			setJSONReturn(cityAndDeptList);
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError("查询统筹区信息出错！"+ex.getMessage() );
		}
		return NONE;
	}
	

	public OutBizDTO getOutBizDTO() {
		return outBizDTO;
	}

	public void setOutBizDTO(OutBizDTO outBizDTO) {
		this.outBizDTO = outBizDTO;
	}

	public CommunalService getCommunalService() {
		return communalService;
	}

	public void setCommunalService(CommunalService communalService) {
		this.communalService = communalService;
	}
 
	
}
