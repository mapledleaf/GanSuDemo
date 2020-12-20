package com.powersi.ssm.biz.question.action;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.powersi.biz.medicare.comm.pojo.ParamDTO;
import com.powersi.biz.question.service.QuestionEsbService;
import com.powersi.hygeia.comm.service.HygeiaServiceManager;
import com.powersi.hygeia.framework.BusiContext;
import com.powersi.hygeia.framework.util.CodetableHelper;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.hygeia.web.BaseAction;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

/**
 * 问卷调查管理Action
 * 
 * @author lubin
 *
 * 2019年3月13日上午10:27:12
 *
 * RQ19030315017 - 增加问卷调查表功能
 */
@Action(value = "QuestionAction")
public class QuestionAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	HygeiaServiceManager hygeiaServiceManager;
	
	/**
	 * 获取问卷调查数据
	 * 
	 * @author lubin
	 * 2019年3月13日上午10:37:55 the void
	 */
	public void getQuestionExamData() {
		try {
			ParamDTO p = createParamDTO();
			String account_type = getParameter("account_type");
			p.put("aaa027",getParameter("aaa027"));
			p.put("account_type",UtilFunc.isNotEmpty(account_type) ? account_type : "2");//账号类型1:中心端,2:结算云...
			Map<String, Object> ret = hygeiaServiceManager.getBeanByClass(QuestionEsbService.class, p.getAkb020())
					.getQuestionExamData(p);
			if(UtilFunc.isNotEmpty(ret)) {
				setJSONReturn(ret);
			}else {
				setJSONReturn("no");
			}
		} catch (IOException e) {
			saveJSONError("获取问卷调查数据出错:"+e.getMessage(), e);
		}
	}
	
	/**
	 * 保存问卷调查结果
	 * 
	 * @author lubin
	 * 2019年3月13日上午10:39:41 the void
	 */
	public void saveQuestionExamData(){
		try {
			String account_type = getParameter("account_type");
			String datas = getParameter("datas");
			ParamDTO p = createParamDTO();
			p.put("answer_no", BusiContext.getUserInfo().getUserId());//答题人工号
			p.put("answer_login", BizHelper.getLoginUser());//答题人登录用户名
			p.put("answer_man", BizHelper.getUserName());//答题人姓名
			p.put("akb021", BizHelper.getAkb021());//医疗机构名称
			p.put("account_type",UtilFunc.isNotEmpty(account_type) ? account_type : "2");//账号类型1:中心端,2:结算云...
			p.put("datas", datas);
			Map<String, Object> ret = hygeiaServiceManager.getBeanByClass(QuestionEsbService.class, p.getAkb020())
					.saveQuestionExamData(p);
			setJSONReturn(ret);
		} catch (IOException e) {
			saveJSONError("保存问卷调查结果出错:"+e.getMessage(), e);
		}
	}
	
	
	/**
	 * 创建一个基础的参数对象，对象中包含aaa027、akb020、caa027
	 * 
	 * @author lubin
	 * 2019年3月13日上午10:31:05
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
	 * 2019年3月13日上午10:31:17
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
