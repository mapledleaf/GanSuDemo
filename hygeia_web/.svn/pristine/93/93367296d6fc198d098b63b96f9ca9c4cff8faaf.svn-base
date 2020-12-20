package com.powersi.ssm.biz.medicare.outland.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.inhospital.service.util.CommunalService;
import com.powersi.biz.medicare.outland.pojo.OutBizDTO;
import com.powersi.ssm.biz.medicare.outland.dao.OutBizDao;

/**
 * 
 * @author 董孝辉
 *
 */
@Service
public class OutBizServiceImpl implements OutBizService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private OutBizDao outBizDao;
	@Autowired
	private CommunalService communalService;

	@SuppressWarnings("rawtypes")
	@Override
	public List getCityAndDept(String akb020, OutBizDTO outBizDTO) {
		return this.outBizDao.getCityAndDept(akb020, outBizDTO);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getBkd324(String bkd325) {
		if (StringUtils.isBlank(bkd325)) {
			return null;
		}
		Map mapRow = this.outBizDao.getCityRegion(bkd325);
		return this.communalService.str(mapRow, "bkd324", bkd325);
	}

}