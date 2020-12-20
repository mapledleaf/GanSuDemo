package com.powersi.biz.medicare.universal.service.api;

import java.util.List;

import com.powersi.biz.medicare.universal.pojo.KA20_HisDTO;
import com.powersi.biz.medicare.universal.pojo.KCD2_Fee_HisDTO;
import com.powersi.biz.medicare.universal.pojo.KCD2_HospFee_HisDTO;

public interface FeeCheckOutApiService {
	//药店购药费用校验
		public List<KCD2_Fee_HisDTO> drugFeeCheckOut(List<KCD2_Fee_HisDTO> drugList,String aae140);
	
		/**
		 * 住院费用上传校验
		 * @param kcdlist
		 * @return
		 */
		public List<KCD2_HospFee_HisDTO> uploadAndCheckFee(List<KCD2_HospFee_HisDTO> drugList);
		/**
		 * 上传目录进行校验
		 * @param ka20list
		 * @return
		 */
		public List<KA20_HisDTO> uploadHisCatalogCheck(List<KA20_HisDTO> ka20list);
		
}
