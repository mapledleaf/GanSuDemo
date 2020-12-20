package com.powersi.biz.medicare.inhospital.service.api.mcce;

import java.util.List;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.inhospital.pojo.BizDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.biz.medicare.inhospital.service.api.mcch.MCCHbizh120104Service;
import com.powersi.biz.medicare.query.dto.KCD1Log;

/**
 * 
 * bizh120104 入院登记信息修改
 * 
 * @author 刘勇
 *
 */
public interface MCCEbizh120104Service extends MCCEsbService {

	public String serviceid = mcce_ + MCCHbizh120104Service.function_id;

	/**
	 * 中心审核状态同步本地
	 * 
	 * @param bizDTO
	 */
	public void updateInHospitalDTO(BizDTO bizDTO);

	/**
	 * 入院登记信息修改保存
	 * 
	 * @param inHospitalDTO
	 * @return
	 */
	public InHospitalDTO modifyRegisterInfo(InHospitalDTO inHospitalDTO);

	/**
	 * 加载修改历史记录
	 * @param inHospitalDTO
	 * @return
	 */
	public List<KCD1Log> selectKcd1Log(InHospitalDTO inHospitalDTO);

}
