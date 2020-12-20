package com.powersi.ssm.biz.medicare.diagnose.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.hygeia.framework.util.UtilFunc;
import com.powersi.ssm.biz.medicare.api.dto.APIRemoteCallParam;
import com.powersi.ssm.biz.medicare.api.dto.APIRemoteCallResult;
import com.powersi.ssm.biz.medicare.api.service.APIRemoteCallService;
import com.powersi.ssm.biz.medicare.common.service.CodetableCacheMapping;

@Service
public class GetPersonInfoServiceImpl implements GetPersonInfoService{

	@Autowired
	private BeanService beanService;
	@Autowired
	private APIRemoteCallService aPIRemoteCallService;
	
	@Override
	@SuppressWarnings("rawtypes")
	public List<Map> getPersonBusi(DiagnoseInfoDTO diagnoseInfoDTO) {
		APIRemoteCallParam aParam = new APIRemoteCallParam();
		aParam.setFunction_id(bizh110103);
		this.beanService.copyProperties(diagnoseInfoDTO, aParam, true);
		aParam.setValue("bka895", diagnoseInfoDTO.getArg_name());
		aParam.setValue("bka896", diagnoseInfoDTO.getArg_value());
		APIRemoteCallResult result = aPIRemoteCallService.execute(aParam);
		return result.getList("bizinfo");
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Map> getBusiFeeInfo(DiagnoseInfoDTO diagnoseInfoDTO) {
		APIRemoteCallParam aParam = new APIRemoteCallParam();
		aParam.setFunction_id(bizh110103);
		this.beanService.copyProperties(diagnoseInfoDTO, aParam, true);
		aParam.setValue("bka895", "aaz217");
		aParam.setValue("bka896", diagnoseInfoDTO.getAaz217());
		APIRemoteCallResult result = aPIRemoteCallService.execute(aParam);
		return result.getList("bizinfo");
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getPersonBusiDetail(DiagnoseInfoDTO diagnoseInfoDTO) {
		APIRemoteCallParam aParam = new APIRemoteCallParam();
		aParam.setFunction_id(bizh110103);
		aParam.setValue("bka895", "aaz217");
		aParam.setValue("akb020", diagnoseInfoDTO.getAkb020());
		aParam.setValue("bka896", diagnoseInfoDTO.getAaz217());
		aParam.setValue("aka130", diagnoseInfoDTO.getAka130());
		APIRemoteCallResult result = aPIRemoteCallService.execute(aParam);
		for (Map bizinfo : result.getList("bizinfo")) {
			String baa027 = UtilFunc.getString(bizinfo, "baa027");
			bizinfo.put("baa027", CodetableCacheMapping.getDisplayValue("baa027", baa027, baa027));
		}
		Map bf = new HashMap();
		bf.put("bizinfo", result.getList("bizinfo"));
		bf.put("feeinfo", result.getList("feeinfo"));
		bf.put("speinfo", result.getList("speinfo"));
		return bf;
	}
	/**
	 * 修改概要：NTS20050700380-生育、门特业务省内异地联网结算需求 -- 湘潭
	 * 修改描述：增加异地改费查人
	 * 修改人及修改时间：李嘉伦 2020/5/18
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> getPersonBusi_remote(DiagnoseInfoDTO diagnoseInfoDTO) {
		APIRemoteCallParam aParam = new APIRemoteCallParam();
		aParam.setFunction_id("Remote_BIZC131110");
		this.beanService.copyProperties(diagnoseInfoDTO, aParam, true);
		aParam.setValue(diagnoseInfoDTO.getArg_name(), diagnoseInfoDTO.getArg_value());
		APIRemoteCallResult result = aPIRemoteCallService.execute(aParam);
		return result.getList("bizinfo");
	}
	/**
	 * 修改概要：NTS20050700380-生育、门特业务省内异地联网结算需求 -- 湘潭
	 * 修改描述：增加异地改费查费用信息
	 * 修改人及修改时间：李嘉伦 2020/5/18
	 */
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getPersonBusiDetail_remote(DiagnoseInfoDTO diagnoseInfoDTO){
        APIRemoteCallParam aParam = new APIRemoteCallParam();
        aParam.setFunction_id("Remote_BIZC131111");
        aParam.setValue("serial_no", diagnoseInfoDTO.getSerial_no());
        aParam.setValue("hospital_id", diagnoseInfoDTO.getHospital_id());
        aParam.setValue("fee_batch", diagnoseInfoDTO.getFee_batch());
        APIRemoteCallResult result = aPIRemoteCallService.execute(aParam);
        Map bf = new HashMap();
        bf.put("feeinfo", result.getList("feeinfo"));
        return bf;
    }
}
