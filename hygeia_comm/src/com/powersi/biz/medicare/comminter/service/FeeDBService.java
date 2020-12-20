package com.powersi.biz.medicare.comminter.service;

import java.util.List;

import com.powersi.biz.medicare.diagnose.pojo.FeeInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 确定费用，db服务接口
 * 
 * @author laihaiyan
 *
 */

public interface FeeDBService {
	/**
	 * 通过身份证号查就医登记号
	 * 
	 * @param aac002
	 *            身份证号
	 * @param inHospitalDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List selectByAac002(InHospitalDTO inHospitalDTO);

	/**
	 * 通过住院号或门诊号查询费用明细
	 * 
	 * @param Bka446
	 * @param inHospitalDTO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List selectByBka446(InHospitalDTO inHospitalDTO);

	/**
	 * 保存费用明细
	 * 
	 * @param List<FeeInfoDTO>
	 *            feeInfoDTO
	 * @return
	 */
	public int saveFeeInfo(List<FeeInfoDTO> feeInfoDTO);

	/**
	 * 更新明细表
	 * 
	 * @param InHospitalDTO
	 *            inHospitalDTO
	 * @return
	 */
	public int updateFeeInfo(InHospitalDTO inHospitalDTOList);

	/**
	 * 查询费用明细清单（医院端） 因避免相应接口做分页查询故而区分查询方法 添加于2017/12/14
	 * 对应接口MCCHbizh410010Service
	 */
	@SuppressWarnings("rawtypes")
	public List queryHospFeeDetailTable(InHospitalDTO inHospitalDTO);

}
