package com.powersi.biz.medicare.recallcenter.pojo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 
 * 用于存储冲正的数据实体列表
 * 
 * @author 朱帆宇
 *
 */
@Service
public class NeedRecallCenterDataList {

	private Object lockList = new Object();
	private List<NeedRecallCenterDataDTO> lstMediBizData = new ArrayList<NeedRecallCenterDataDTO>();

	public void add(NeedRecallCenterDataDTO medicalBizDataDTO) {
		synchronized (lockList) {
			lstMediBizData.add(medicalBizDataDTO);
		}
	}

	public void remove(NeedRecallCenterDataDTO medicalBizDataDTO) {
		synchronized (lockList) {
			lstMediBizData.remove(medicalBizDataDTO);
		}
	}
}
