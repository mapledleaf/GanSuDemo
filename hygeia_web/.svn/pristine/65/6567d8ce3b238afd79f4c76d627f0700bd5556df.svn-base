package com.powersi.sys.manager.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.comm.pojo.BizCacheTrackDTO;
import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.comm.service.CenterBizCacheTrackService;
import com.powersi.biz.medicare.inhospital.service.date.DateService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.util.PaginationHelper;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

/**
 * 业务缓存跟踪管理
 * 
 */
@Action(value = "CacheTrackManager", results = { 
		@Result(name = "bizCacheTrackInfo", location = "/pages/sys/manager/BizCacheTrack.jsp")})
public class CacheTrackManagerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	@Autowired
	private DateService dateService;
	
	private BizCacheTrackDTO bizCacheTrackDTO;

	/**
	 * 获取中心下发对应缓存跟踪信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Action("queryMainTrackInfo")
	public String queryMainTrackInfo() {
		try {
			PagerHelper.initPagination(getRequest());
			List<Map> list = new ArrayList<Map>();
			Map paraMap = new HashMap();
			CenterBizCacheTrackService trackService = this.hygeiaServiceManager.getBeanByClass(CenterBizCacheTrackService.class, BizHelper.getAkb020());
			String aaa027 = this.getParameter("aaa027");
			String bizFlag = this.getParameter("bizFlag");
			if("POLICY".equals(bizFlag)) {//政策配置信息
				paraMap.put("akb020", BizHelper.getAkb020());
				paraMap.put("aaa027", aaa027);
				list = trackService.queryPolicyMaxUpdateTimeList(paraMap);
			} else if ("JZML".equals(bizFlag)) {//基准目录
				
			} else if ("EJML".equals(bizFlag)) {//二级目录
				
			} else if ("KAE8".equals(bizFlag)) {//目录匹配信息
				
			} else if ("KA06".equals(bizFlag)) {//疾病目录
				
			}
			if(list!=null && !list.isEmpty()){
				PaginationHelper.getPaginationObj().setCount(list.size());
				DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(list));
			}
		} catch (Exception ex) {
			saveJSONError(ex);
		}
		return NONE;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Action("queryMissInfoDetail")
	public String queryMissInfoDetail(){
		try {
			PagerHelper.initPagination(getRequest());
			int currentPage = PagerHelper.getPaginationObj().getPageIndex();
			int  pageSize = PagerHelper.getPaginationObj().getPageSize();
			int first_row = pageSize * (currentPage - 1) + 1;
			int last_row = pageSize * currentPage;
			
			String zxTimeStr = this.getParameter("zxTime");
			String jsyTimeStr = this.getParameter("jsyTime");
			String redisTimeStr = this.getParameter("redisTime");
			String aaa027 = this.getParameter("aaa027");
			
			ListResult listResult  = null;
			CenterBizCacheTrackService trackService = this.hygeiaServiceManager.getBeanByClass(CenterBizCacheTrackService.class, BizHelper.getAkb020());
			Map paraMap = new HashMap();
			paraMap.put("first_row", first_row);
			paraMap.put("last_row", last_row);
			String bizFlag = getParameter("bizFlag");
			
			paraMap.put("akb020", BizHelper.getAkb020());
			paraMap.put("aaa027", aaa027);
			
			if ("POLICY".equals(bizFlag)) {//政策配置信息
				String strPolicyType = this.getParameter("policyType");
				
				Date zxTime = dateService.toDate(zxTimeStr, DateService._yyyyMMddHHmmss);
				Date jsyTime = dateService.toDate(jsyTimeStr, DateService._yyyyMMddHHmmss);
				Date redisTime = dateService.toDate(redisTimeStr, DateService._yyyyMMddHHmmss);
				if (zxTime.compareTo(jsyTime) > 0) {
					paraMap.put("jsyTime", jsyTimeStr);
					listResult = trackService.queryPolicyMissInfoFromCenter(strPolicyType, paraMap);
				} else if (zxTime.compareTo(jsyTime) < 0) {//不用考虑中心最新时间小于结算云最新时间的情况
					return NONE;
				} else {
					if (jsyTime.compareTo(redisTime) > 0) {
						paraMap.put("redisTime", redisTimeStr);
						paraMap.put("currentPage", PagerHelper.getPaginationObj().getPageIndex());
						paraMap.put("pageSize", PagerHelper.getPaginationObj().getPageSize());
						listResult = trackService.queryPolicyMissInfoFromJsy(strPolicyType, paraMap);
						this.getRequest().setAttribute("policyType", strPolicyType);
					} else if (jsyTime.compareTo(redisTime) < 0) {//不用考虑结算云最新时间小于缓存中最新时间的情况
						return NONE;
					} else {
						return NONE;
					}
				}
				PaginationHelper.getPaginationObj().setCount(listResult.getCount());
				DataGridHelper.render(this.getRequest(), this.getResponse(), PagerHelper.getPaginatedList(listResult.getList()));
			} else if ("JZML".equals(bizFlag)) {//基准目录
				
			} else if ("EJML".equals(bizFlag)) {//二级目录
				
			} else if ("KAE8".equals(bizFlag)) {//目录匹配信息
				
			} else if ("KA06".equals(bizFlag)) {//疾病目录
				
			}
			
		} catch (Throwable e) {
			saveJSONError(e);
		}
		return NONE;
	}

	public BizCacheTrackDTO getBizCacheTrackDTO() {
		return bizCacheTrackDTO;
	}

	public void setBizCacheTrackDTO(BizCacheTrackDTO bizCacheTrackDTO) {
		this.bizCacheTrackDTO = bizCacheTrackDTO;
	}
	
}
