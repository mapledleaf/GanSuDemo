package com.powersi.biz.question.service;

import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.ParamDTO;

/**
 * 问卷调查esb服务接口
 * 
 * @author lubin
 *
 * 2019年3月13日上午10:50:03
 *
 * RQ19030315017 - 增加问卷调查表功能
 */
public interface QuestionEsbService {

	/**
	 * 获取问卷调查数据
	 * 
	 * @author lubin
	 * 2019年3月13日上午10:50:23
	 * @param p
	 * @return the Map<String,Object>
	 */
	public Map<String, Object> getQuestionExamData(ParamDTO p);
	
	/**
	 * 保存问卷调查结果
	 * 
	 * @author lubin
	 * 2019年3月13日上午10:52:19
	 * @param p
	 * @return the Map<String,Object>
	 */
	public Map<String, Object> saveQuestionExamData(ParamDTO p);
}
