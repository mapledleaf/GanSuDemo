package com.powersi.biz.medicare.diagnose.service;

import java.util.List;

import com.powersi.biz.medicare.diagnose.pojo.BizsceneDTO;
import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.diagnose.pojo.FeeInfoDTO;
import com.powersi.biz.medicare.diagnose.pojo.PayInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.FeeStatDTO;

public interface BackWritePersonBiz {

	public int backWriteTotalInfo(DiagnoseInfoDTO diagnoseInfoDTO, List<PayInfoDTO> payInfoDTO,
			List<FeeInfoDTO> feeInfoDTO, List<BizsceneDTO> bizsceneInfoDTO, List<FeeStatDTO> feestatInfoDTO);

}
