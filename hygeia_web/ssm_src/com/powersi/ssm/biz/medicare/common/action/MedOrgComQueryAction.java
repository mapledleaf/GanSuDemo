package com.powersi.ssm.biz.medicare.common.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.util.PaginationHelper;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;
import com.powersi.hygeia.web.util.WebHelper;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.biz.medicare.catalog.service.api.HospCataApiService;
import com.powersi.biz.medicare.comm.pojo.DiseaseDirDTO;
import com.powersi.biz.medicare.comm.pojo.HospDTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.service.MedOrgComQueryVS;
import com.powersi.comm.utils.ToolUtil;

@Action(value = "MedOrgComQueryAction", results = {
		@Result(name = "queMedOrgInfo", location = "/pages/biz/medicare/comm/diseasequery.jsp"),
		@Result(name = "hospInfo", location = "/pages/biz/medicare/comm/ChooseHospInfo.jsp")})
public class MedOrgComQueryAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	HygeiaServiceManager hygeiaServiceManager;

	private DiseaseDirDTO diseaseDirDTO;
	
	private HospDTO hospDTO;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public DiseaseDirDTO getDiseaseDirDTO() {
		return diseaseDirDTO;
	}

	public void setDiseaseDirDTO(DiseaseDirDTO diseaseDirDTO) {
		this.diseaseDirDTO = diseaseDirDTO;
	}
	

	/**
	 * 查询疾病信息
	 * 
	 * @return
	 */
	public String queMedOrgInfo() {

		try {
			if (WebHelper.isPostRequest(getRequest())) {
				// 调用分页辅助类的初始化分页
				PagerHelper.initPagination(getRequest());

				if (diseaseDirDTO == null) {
					diseaseDirDTO = new DiseaseDirDTO();
				}
				MedOrgComQueryVS medOrgComQueryVS = (MedOrgComQueryVS) hygeiaServiceManager
						.getBean("MedOrgComQueryService", BizHelper.getAkb020());

				// 把框架的分页信息set到DTO
				diseaseDirDTO.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				diseaseDirDTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());

				// ListResult接口作为返回值
				ListResult listResult = medOrgComQueryVS.queMedOrgInfo(diseaseDirDTO);

				if (null != listResult) {
					// 把结果集返回页面的时候，set分页信息（从ListResult里面取出结果集，返回的list和count）
					PaginationHelper.getPaginationObj().setCount(listResult.getCount());
					DataGridHelper.render(getRequest(), getResponse(),
							PagerHelper.getPaginatedList(listResult.getList()));
				} else {
					saveMessage("没有符合条件的疾病信息！");
				}
				return NONE;
			}else{
				return "queMedOrgInfo";
			}
		} catch (Exception e) {
			logger.error("查询疾病失败！错误信息：" + ToolUtil.getExceptionInfo(e));
			saveError("查询出错！", e);
			return ERROR;
		}
		
	}
	
	public String queryHospInfo(){
		try {

			if (WebHelper.isPostRequest(getRequest())) {
				PagerHelper.initPagination(getRequest());
				if (hospDTO == null) {
					hospDTO = new HospDTO();
				}
				String akb020 = BizHelper.getAkb020();
				HospCataApiService hospCataService = hygeiaServiceManager.getBeanByClass(HospCataApiService.class,
						akb020);

				hospDTO.setCurrentPage(PagerHelper.getPaginationObj().getPageIndex());
				hospDTO.setPageSize(PagerHelper.getPaginationObj().getPageSize());

				ListResult listResult = hospCataService.queryHospInfo(akb020, hospDTO);

				PaginationHelper.getPaginationObj().setCount(listResult.getCount());
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(listResult.getList()));

			} else {
				return "hospInfo";
			}
			
		} catch (Exception ex) {
			saveJSONError("查询医疗机构信息出错！"+ex.getMessage() );
		}
		return NONE;
	}

	public HospDTO getHospDTO() {
		return hospDTO;
	}

	public void setHospDTO(HospDTO hospDTO) {
		this.hospDTO = hospDTO;
	}

	
}
