package com.powersi.biz.medicare.diagnose.service.api.mccc;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.diagnose.pojo.BizsceneDTO;
import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.diagnose.pojo.FeeInfoDTO;
import com.powersi.biz.medicare.diagnose.pojo.PayInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.FeeStatDTO;
import com.powersi.biz.medicare.inhospital.service.api.mccc.MCCCenterService;

/**
 *
 * bizh110002 中心保存门诊业务信息
 * 
 * @author tangmin
 *
 */
public interface MCCCbizh110002Service extends MCCCenterService {
	String serviceid = mccc_+"bizh110002";

	/**
	 * 中心保存门诊业务信息
	 * 
	 * @param diagnoseInfoDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public void saveDiagnoseInfo(DiagnoseInfoDTO diagnoseInfoDTO, List<PayInfoDTO> payInfoDTO,
			List<FeeInfoDTO> feeInfoDTO, List<BizsceneDTO> bizsceneInfoDTO, List<FeeStatDTO> feestatInfoDTO, Map cumulateData);

}
