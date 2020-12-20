package com.powersi.ssm.biz.medicare.diagnose.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powersi.hygeia.framework.util.UtilFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powersi.biz.medicare.diagnose.pojo.DiagnoseInfoDTO;
import com.powersi.biz.medicare.inhospital.service.bean.BeanService;
import com.powersi.ssm.biz.medicare.api.dto.APIRemoteCallParam;
import com.powersi.ssm.biz.medicare.api.dto.APIRemoteCallResult;
import com.powersi.ssm.biz.medicare.api.service.APIRemoteCallService;
import com.powersi.ssm.biz.medicare.common.util.BizHelper;

@Service
public class DiagnoseRegisterServiceImpl implements DiagnoseRegisterService{
	
	@Autowired
	private BeanService beanService;
	@Autowired
	private APIRemoteCallService aPIRemoteCallService;
	
	@Override
	@SuppressWarnings("rawtypes")
	public List diagnoseCalcSave(DiagnoseInfoDTO diagnoseInfoDTO, List<Map> listFeeDTO) {
		APIRemoteCallParam aParam = new APIRemoteCallParam();
		
		aParam.setFunction_id(bizh110104);
		
		/* TS19032800229 - 【需求开发】电子社保卡应用相关功能模块改造  如果是前台扫码结算 调用扫码结算功能号  modified 675 2019年3月28日 */
		if("2".equals(diagnoseInfoDTO.getEsscno())) {
			aParam.setFunction_id("bizh110114");
			//aParam.setFunction_id("SI.04.57.01");
		}
		
		this.beanService.copyProperties(diagnoseInfoDTO, aParam, true);
		if ("1".equals(diagnoseInfoDTO.getReduceflag()))
			aParam.setValue("bka894", "1");
		else 
			aParam.setValue("bka894", "0");
		aParam.setValue("bka893", "0");
		aParam.setList("feeinfo", listFeeDTO);
		APIRemoteCallResult aRemote = aPIRemoteCallService.execute(aParam);
		return aRemote.getList("payinfo");
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List diagnoseCalcEnPay(DiagnoseInfoDTO diagnoseInfoDTO, List<Map> listFeeDTO) {
		APIRemoteCallParam aParam = new APIRemoteCallParam();
		aParam.setFunction_id(bizh110104);
		this.beanService.copyProperties(diagnoseInfoDTO, aParam, true);
		if ("1".equals(diagnoseInfoDTO.getReduceflag()))
			aParam.setValue("bka894", "1");
		else 
			aParam.setValue("bka894", "0");
		aParam.setValue("bka893", "1");
		aParam.setList("feeinfo", listFeeDTO);
		APIRemoteCallResult aRemote = aPIRemoteCallService.execute(aParam);
		return aRemote.getList("payinfo");
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Map> queryHospDept() {
		APIRemoteCallParam aParam = new APIRemoteCallParam();
		aParam.setFunction_id(bizh200001);
		aParam.setValue("aae016", "1"); //复核标记
		aParam.setValue("aae100", "1"); //有效标记
		aParam.setValue("bke510", "1"); //科室类别  1：门诊
		aParam.setValue("bka896", "queryDept"); //查询类型
		aParam.setValue("aaa027", BizHelper.getAaa027()); //统筹区编码
		aParam.setValue("akb020", BizHelper.getAkb020()); //医院编码
		APIRemoteCallResult aRemote = aPIRemoteCallService.execute(aParam);
		return aRemote.getList("hospInfo");
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Map> queryHospArea(String akf001) {
		APIRemoteCallParam aParam = new APIRemoteCallParam();
		aParam.setFunction_id(bizh200001);
		aParam.setValue("aae016", "1"); //复核标记
		aParam.setValue("aae100", "1"); //有效标记
		aParam.setValue("bkc156", akf001); //所属科室
		aParam.setValue("bka896", "queryArea"); //查询类型
		aParam.setValue("aaa027", BizHelper.getAaa027()); //统筹区编码
		aParam.setValue("akb020", BizHelper.getAkb020()); //医院编码
		APIRemoteCallResult aRemote = aPIRemoteCallService.execute(aParam);
		return aRemote.getList("hospInfo");
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Map> queryHospDoctor(String akf001) {
		APIRemoteCallParam aParam = new APIRemoteCallParam();
		aParam.setFunction_id(bizh200001);
		aParam.setValue("aae016", "1"); //复核标记
		aParam.setValue("aae100", "1"); //有效标记
		aParam.setValue("bkc156", akf001); //所属科室
		aParam.setValue("bka896", "queryDoctor"); //查询类型
		aParam.setValue("aaa027", BizHelper.getAaa027()); //统筹区编码
		aParam.setValue("akb020", BizHelper.getAkb020()); //医院编码
		APIRemoteCallResult aRemote = aPIRemoteCallService.execute(aParam);
		return aRemote.getList("hospInfo");
	}

	/**
	 * TS19092400141 - 【需求开发】电子社保卡医保结算业务相关优化改造
	 */
	@Override
	public List diagnoseCalcSaveAli(DiagnoseInfoDTO diagnoseInfoDTO, List<Map> listFeeDTO) {
		APIRemoteCallParam aParam = new APIRemoteCallParam();
				
		aParam.setFunction_id("bizh110124");
				
		this.beanService.copyProperties(diagnoseInfoDTO, aParam, true);
		if ("1".equals(diagnoseInfoDTO.getReduceflag()))
			aParam.setValue("bka894", "1");
		else 
			aParam.setValue("bka894", "0");
		aParam.setValue("bka893", "0");
		aParam.setList("feeinfo", listFeeDTO);
		APIRemoteCallResult aRemote = aPIRemoteCallService.execute(aParam);
		return aRemote.getList("payinfo");
	}

	/**
	 * 修改概要：NTS20050700380-生育、门特业务省内异地联网结算需求 -- 湘潭
	 * 修改描述：增加异地门特试算
	 * 修改人及修改时间：李嘉伦 2020/5/8
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List diagnoseCalcSave_remote(DiagnoseInfoDTO diagnoseInfoDTO, List<Map> listFeeDTO) {
		APIRemoteCallParam aParam = new APIRemoteCallParam();

		aParam.setFunction_id("Remote_BIZC131104");
		this.beanService.copyProperties(diagnoseInfoDTO, aParam, true);
		aParam.setValue("save_flag", "0");
		aParam.setList("feeinfo", listFeeDTO);
		APIRemoteCallResult aRemote = aPIRemoteCallService.execute(aParam);
		return aRemote.getList("payinfo");
	}
	/**
	 * 修改概要：NTS20050700380-生育、门特业务省内异地联网结算需求 -- 湘潭
	 * 修改描述：增加异地门特结算
	 * 修改人及修改时间：李嘉伦 2020/5/8
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List diagnoseCalcEnPay_remote(DiagnoseInfoDTO diagnoseInfoDTO, List<Map> listFeeDTO) {
		APIRemoteCallParam aParam = new APIRemoteCallParam();
		aParam.setFunction_id("Remote_BIZC131104");
		this.beanService.copyProperties(diagnoseInfoDTO, aParam, true);
		aParam.setValue("save_flag", "1");
		aParam.setList("feeinfo", listFeeDTO);
		APIRemoteCallResult aRemote = aPIRemoteCallService.execute(aParam);
		return aRemote.getList("payinfo");
	}

	/**
	 * 修改概要：NTS20050700380-生育、门特业务省内异地联网结算需求 -- 湘潭
	 * 修改描述：增加异地门特结算
	 * 修改人及修改时间：李嘉伦 2020/5/8
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List chooseDisease_remote() {
		APIRemoteCallParam aParam = new APIRemoteCallParam();
		aParam.setFunction_id("Remote_BIZC200007");
		aParam.setValue("apply_content", "511");
		aParam.setValue("person_center_id", "439900");
		APIRemoteCallResult aRemote = aPIRemoteCallService.execute(aParam);

		//拼接一下两个结果集
		List rtnlist = new ArrayList();
		List diseaseinfo = aRemote.getList("diseaseinfo");
		if (diseaseinfo != null&&diseaseinfo.size() >0){
			for (Object obj:diseaseinfo){
				Map map = (Map)obj;
				map.put("disease", UtilFunc.getString(map,"disease"));
				map.put("icd", UtilFunc.getString(map,"icd"));
				rtnlist.add(map);
			}
		}

		List icdclassinfo = aRemote.getList("icdclassinfo");
		if (icdclassinfo != null&&icdclassinfo.size() >0){
			for (Object obj:icdclassinfo){
				Map map = (Map)obj;
				map.put("disease", UtilFunc.getString(map,"disease"));
				map.put("icd", UtilFunc.getString(map,"icd"));
				rtnlist.add(map);
			}
		}

		return rtnlist;
	}
	
	/**
	 * 电子凭证校验机具设备
	 */
	@Override
	public Map<String, Object> checkAkb020(HashMap<String, Object> map) {
		APIRemoteCallParam aParam = new APIRemoteCallParam();
		aParam.setFunction_id("CheckAkb020");
		this.beanService.copyProperties(map, aParam, true);
		aParam.put("datamap", map);
		
		aParam.setValue("bka893", "1");
		APIRemoteCallResult aRemote = aPIRemoteCallService.execute(aParam);
		List<Map> list = aRemote.getList("resultList");
		
		return list.get(0);
	}
}
