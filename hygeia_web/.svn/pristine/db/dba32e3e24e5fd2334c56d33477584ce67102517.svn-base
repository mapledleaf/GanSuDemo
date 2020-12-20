package com.powersi.ssm.biz.medicare.outland.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.outland.pojo.OutAccountBizDTO;
import com.powersi.biz.medicare.outland.service.api.OutAccountBizApiService;
import com.powersi.comm.utils.ToolUtil;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.hygeia.web.util.JsonHelper;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;
import com.powersi.sys.util.DataGridHelper;
import com.powersi.sys.util.PagerHelper;

@Action(value = "OutAccountBizAction", results = {})
public class OutAccountBizAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HygeiaServiceManager hygeiaServiceManager;
	private OutAccountBizDTO outAccountBizDTO;

	/**
	 * 查询跨省异地出院数据
	 * 
	 * @author yangmj 2017年9月11日 下午4:16:50
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public String queryOutAccount() {
		try {
			PagerHelper.initPagination(getRequest());
			String akb020 = BizHelper.getAkb020();
			String aaa027 = BizHelper.getAaa027();
			OutAccountBizApiService outService = hygeiaServiceManager.getBeanByClass(OutAccountBizApiService.class,
					akb020);
			if (outAccountBizDTO == null) {
				outAccountBizDTO = new OutAccountBizDTO();
			}
			outAccountBizDTO.setAab301(aaa027);
			outAccountBizDTO.setAab299(aaa027);
			Map<String, Object> mReturn = outService.queryOutAccount(outAccountBizDTO);
			List mList = (List) mReturn.get("lstBiz");
			DataGridHelper.render(getRequest(), getResponse(), PagerHelper.getPaginatedList(mList));
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError("查询申请信息出错！" + ex.getMessage());
		}
		return NONE;
	}

	/**
	 * 出院结算数据日对账
	 * 
	 * @author yangmj 2017年9月11日 下午4:16:44
	 * @return String
	 */
	public String OutAccountData() {

		try {
			List<Map<String, Object>> outAccountDtoList = JsonHelper.toList(getParameter("outAccountDtoList"));
			if (outAccountBizDTO == null) {
				outAccountBizDTO = new OutAccountBizDTO();
			}
			if (!UtilFunc.isEmpty(outAccountDtoList)) {
				for (Map<String, Object> map : outAccountDtoList) {
					outAccountBizDTO.setTotalrow(map.get("totalrow").toString());
					outAccountBizDTO.setYkc706(map.get("ykc706").toString());
					outAccountBizDTO.setYkc705(map.get("ykc705").toString());
					break;
				}
			}
			outAccountBizDTO.setAab299(BizHelper.getAaa027());
			OutAccountBizApiService accountService = hygeiaServiceManager.getBeanByClass(OutAccountBizApiService.class,
					BizHelper.getAkb020());
			String message = accountService.OutAccountData(outAccountBizDTO, outAccountDtoList);
			if (message.equals("1")) {
				setJSONReturn("对账成功！");
			} else {
				setJSONReturn("对账失败！");
			}
		} catch (Exception ex) {
			logger.error(ToolUtil.getExceptionInfo(ex));
			saveJSONError(ex.getMessage());
		}
		return NONE;
	}

	public OutAccountBizDTO getOutAccountBizDTO() {
		return outAccountBizDTO;
	}

	public void setOutAccountBizDTO(OutAccountBizDTO outAccountBizDTO) {
		this.outAccountBizDTO = outAccountBizDTO;
	}

}
