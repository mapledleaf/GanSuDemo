package com.powersi.ssm.biz.examination.action;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.examination.service.ExaminationEsbService;
import com.powersi.biz.medicare.comm.pojo.ParamDTO;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.exception.HygeiaException;
import com.powersi.hygeia.framework.util.CodetableHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

/**
 * 题库管理Action
 * 
 * @author lubin
 *
 * 2018年10月11日上午10:42:21
 *
 */
@SuppressWarnings("serial")
@Action(value = "ExaminationAction")
public class ExaminationAction extends BaseAction {
	
	@Autowired
	HygeiaServiceManager hygeiaServiceManager;
	
	/**
	 * 获取答题数据
	 * 
	 * @author lubin
	 * 2018年9月25日上午8:38:44
	 * @return the String
	 */
	public void getAnswerExaminationData() {
		try {
			ParamDTO p = createParamDTO();
			String account_type = getParameter("account_type");
			p.put("userId",BusiContext.getUserInfo().getUserId());
			p.put("account_type",UtilFunc.isNotEmpty(account_type) ? account_type : "2");//账号类型1:中心端,2:结算云...
			Map<String, Object> ret = hygeiaServiceManager.getBeanByClass(ExaminationEsbService.class, p.getAkb020())
					.getAnswerExaminationData(p);
			if(UtilFunc.isNotEmpty(ret)) {
				setJSONReturn(ret);
			}else {
				setJSONReturn("no");
			}
		} catch (IOException e) {
			saveJSONError("获取答题数据出错:"+e.getMessage(), e);
		}
	}
	
	/**
	 * 保存答题结果
	 * 
	 * @author lubin
	 * 2018年9月25日下午1:42:57
	 * @return the String
	 */
	public void saveAnswerResult(){
		try {
			String exam_id = getParameter("exam_id");
			String right_flag = getParameter("right_flag");
			String account_type = getParameter("account_type");
			if (UtilFunc.isEmpty(exam_id)) {
				throw new HygeiaException("缺少考题主键参数。[exam_id]");
			}
			if (UtilFunc.isEmpty(right_flag)) {
				throw new HygeiaException("缺少答案是否正确参数。[right_flag]");
			}
			ParamDTO p = createParamDTO();
			p.put("exam_id", exam_id);
			p.put("right_flag", right_flag);
			p.put("userId", BusiContext.getUserInfo().getUserId());
			p.put("userLogin", BizHelper.getLoginUser());
			p.put("userName", BizHelper.getUserName());
			p.put("account_type",UtilFunc.isNotEmpty(account_type) ? account_type : "2");//账号类型1:中心端,2:结算云...
			Map<String, Object> ret = hygeiaServiceManager.getBeanByClass(ExaminationEsbService.class, p.getAkb020())
					.saveAnswerResult(p);
			if(UtilFunc.isNotEmpty(ret)) {
				setJSONReturn(ret);
			}else {
				setJSONReturn("no");
			}
		} catch (IOException e) {
			saveJSONError("保存答题结果出错:"+e.getMessage(), e);
		}
	}
	
	/**
	 * 创建一个基础的参数对象，对象中包含aaa027、akb020、caa027
	 * 
	 * @author lubin
	 * 2018年10月11日下午2:15:46
	 * @return the ParamDTO
	 */
	protected ParamDTO createParamDTO() {
		ParamDTO param = new ParamDTO();
		initParam(param);
		return param;
	}
	
	/**
	 * 设置对象中的aaa027、akb020、caa027
	 * 
	 * @author lubin
	 * 2018年10月11日下午2:15:17
	 * @param param the void
	 */
	protected void initParam(ParamDTO param) {
		param.setAaa027(UtilFunc.isEmpty(param.getAaa027()) ? BizHelper.getAaa027() : param.getAaa027());
		param.setAkb020(UtilFunc.isEmpty(param.getAkb020()) ? BizHelper.getAkb020() : param.getAkb020());
		if (UtilFunc.isEmpty(param.getCaa027())) {
			@SuppressWarnings("unchecked")
			Map<String, Object> caa027 = CodetableHelper.get("caa027");
			if (caa027 != null && caa027.keySet().size() == 1) {
				param.setCaa027(caa027.keySet().toArray(new String[caa027.keySet().size()])[0]);
			}
		}
	}
}
