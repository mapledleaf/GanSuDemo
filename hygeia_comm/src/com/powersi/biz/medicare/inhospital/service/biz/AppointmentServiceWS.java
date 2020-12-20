package com.powersi.biz.medicare.inhospital.service.biz;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.AppointmentInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.DrugWindowInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;

/**
 * 预约挂号业务服务-提供给人社服务平台医保移动支付接口调用
 * 
 * @ClassName: AppointmentServiceWS
 * @Description: TODO
 * @author Comsys-asus
 * @date 2017年1月12日 下午3:13:01
 *
 */
public interface AppointmentServiceWS {

	/**
	 * 提交预约挂号（或取消预约挂号）
	 */
	public AppointmentInfoDTO saveOrCancelYygh(AppointmentInfoDTO dto);

	/**
	 * 查询医保预挂号结果信息
	 */
	public List<AppointmentInfoDTO> getYyghResults(AppointmentInfoDTO dto);

	/**
	 * 查询取药信
	 */
	public List<DrugWindowInfoDTO> getDrugWindowInfo(InHospitalDTO inHospitalDTO);

	/**
	 * 获取地市医院列表信息
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getYyInfoList(String aaa027);

	/**
	 * 获取医院科室列表信息
	 */
	public List<HospInfoDTO> getHospDeptList(HospInfoDTO hospInfoDTO);
}
