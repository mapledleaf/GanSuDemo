package com.powersi.biz.medicare.inhospital.service.api.mcce;

import java.util.List;
import com.powersi.biz.medicare.inhospital.pojo.CheckCureInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.KE14;

/**
 * 
 * bizh410001 上传检查诊疗结果
 * 
 * @author zhos
 *
 */
public interface MCCEbizh410001Service extends MCCEsbService {

	public String serviceid = mcce_ + "bizh410001";

	/**
	 *
	 * 上传检查诊疗结果
	 * 
	 * @param is
	 *            影像图片流 checkCureInfoDTO 其他检查诊疗结果信息
	 */
	public CheckCureInfoDTO saveCheckCureInfo(byte[] file, CheckCureInfoDTO checkCureInfoDTO);

	/**
	 *
	 * 获取检查诊疗结果
	 * 
	 * @param checkCureInfoDTO
	 *            检查诊疗结果入参信息
	 */
	public List<KE14> queryCheckCureInfos(CheckCureInfoDTO checkCureInfoDTO);

	/**
	 *
	 * 获取单个检查诊疗结果
	 * 
	 * @param checkCureInfoDTO
	 *            检查诊疗结果入参信息
	 */
	public KE14 queryKE14(CheckCureInfoDTO dto);
	
}
