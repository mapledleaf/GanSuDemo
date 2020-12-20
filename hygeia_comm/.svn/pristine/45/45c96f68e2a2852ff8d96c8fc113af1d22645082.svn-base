package com.powersi.biz.medicare.inhospital.service.biz;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.inhospital.pojo.FeeDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 费用校验服务类
 * 
 * @author MonkGirl
 *
 */
public interface FeeCheckerService {

	/**
	 * 校验并保存费用信息
	 * 
	 * @param inHospitalDTO
	 *            业务信息
	 * @param inHospitalDTORows
	 *            费用信息
	 * @param feeinfoRowsScale
	 *            返回到外部的部分明细信息比如自付比例
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public InHospitalDTO checkAndSaveFees(InHospitalDTO inHospitalDTO, List<InHospitalDTO> inHospitalDTORows,
			List<Map> feeinfoRowsScale);

	/**
	 * inHospitalDTORow to feeDTORow
	 * 
	 * @param inHospitalDTO
	 * @param inHospitalDTORow
	 * @param feeDTORow
	 */
	public void copyProperties(InHospitalDTO inHospitalDTO, InHospitalDTO inHospitalDTORow, FeeDTO feeDTORow);

}
