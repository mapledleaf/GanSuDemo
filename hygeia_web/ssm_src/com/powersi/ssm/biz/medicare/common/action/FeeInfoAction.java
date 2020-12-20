package com.powersi.ssm.biz.medicare.common.action;

import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.biz.medicare.comm.pojo.FeeDirDTO;
import com.powersi.biz.medicare.comm.service.FeeInfoVS;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper; 

@Action(value = "FeeInfoAction", results = {

})
public class FeeInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	HygeiaServiceManager hygeiaServiceManager;
	
	private FeeDirDTO feeInfoDTO;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("deprecation")
	public String queryCatalog() {
		try {
			String centerId = getParameter("centerid");
			String queryType = getParameter("searchType");
			String queryValue = getParameter("searchTerm");
			String bka035 = getParameter("bka035");
			if ("".equals(bka035)) {
				bka035 = "0";
			}

			FeeInfoVS feeInfoVS = (FeeInfoVS) hygeiaServiceManager.getBean("feeInfoVS", BizHelper.getAkb020());

			feeInfoDTO = new FeeDirDTO();
			feeInfoDTO.setCenterId(centerId);
			feeInfoDTO.setQueryType(queryType);
			feeInfoDTO.setQueryValue(queryValue);
//			feeInfoDTO.setBka035(bka035);
			PagerHelper.initPagination(getRequest());
			DataGridHelper.render(getResponse(), PagerHelper.getPaginatedList(feeInfoVS.queryCatalog(feeInfoDTO)));
		} catch (Exception ex) {
			logger.error( "查询目录信息失败！错误信息：" + ToolUtil.getExceptionInfo(ex));
			saveError(ex);
		}
		return NONE;
	}

	public FeeDirDTO getFeeInfoDTO() {
		return feeInfoDTO;
	}

	public void setFeeInfoDTO(FeeDirDTO feeInfoDTO) {
		this.feeInfoDTO = feeInfoDTO;
	}
}
