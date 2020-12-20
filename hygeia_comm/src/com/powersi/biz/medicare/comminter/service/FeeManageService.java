package com.powersi.biz.medicare.comminter.service;

import java.util.List;

import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 确定费用
 * 
 * @author laihaiyan
 *
 */
public interface FeeManageService {
	/**
	 * 通过身份证号查就医登记号
	 * 
	 * @param aac002
	 *            身份证号
	 * @param inHospitalDTO
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List selectByAac002(InHospitalDTO inHospitalDTO);

	/**
	 * 通过就医登记号查询费用明细
	 * 
	 * @param List<InHospitalDTO>
	 *            inHospitalDTOList
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List selectByBka446(List<InHospitalDTO> inHospitalDTOList, InHospitalDTO inHospitalDTO);

	/**
	 * 弹窗前看是否有费用明细
	 * 
	 * @param List<InHospitalDTO>
	 *            inHospitalDTOList
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List selectFees(InHospitalDTO inHospitalDTO);

	/**
	 * 保存费用明细
	 * 
	 * @param List<InHospitalDTO>
	 *            inHospitalDTOList
	 * @return int
	 */
	public int saveFeeInfo(List<InHospitalDTO> inHospitalDTOList, InHospitalDTO inHospitalDTO);

	/**
	 * 查询费用明细清单（医院端） 因避免相应接口做分页查询故而区分查询方法 添加于2017/12/14
	 * 对应接口MCCHbizh410010Service
	 */
	@SuppressWarnings("rawtypes")
	public List queryHospFeeDetailTable(InHospitalDTO inHospitalDTO);

}
