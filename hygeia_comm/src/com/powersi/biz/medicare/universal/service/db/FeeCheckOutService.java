package com.powersi.biz.medicare.universal.service.db;

import java.util.List;

import com.powersi.biz.medicare.universal.pojo.KA20_HisDTO;
import com.powersi.biz.medicare.universal.pojo.KCD2_HospFee_HisDTO;

public interface FeeCheckOutService {
			/**
			 * 住院费用上传校验
			 * @param kcdlist
			 * @return
			 */
			public List<KCD2_HospFee_HisDTO> uploadAndCheckFee(List<KCD2_HospFee_HisDTO> kcdlist);
			//上传目录进行校验
			public List<KA20_HisDTO> uploadHisCatalogCheck(List<KA20_HisDTO> ka20list);
}
