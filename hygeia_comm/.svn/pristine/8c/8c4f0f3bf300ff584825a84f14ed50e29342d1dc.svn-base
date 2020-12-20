package com.powersi.switches.service;

import java.io.Serializable;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.MediDTO;
import com.powersi.biz.medicare.inhospital.pojo.InHospitalDTO;
import com.powersi.switches.pojo.SwitchesMap;

/**
 * 通用开关
 * 
 * @author 刘勇
 *
 */
public interface APISwitchesService extends Serializable {

	/**
	 * 
	 * @param bke537
	 *            开关化名
	 * @return true开 false关
	 */
	public boolean ison(String bke537);

	/**
	 * @param switchesMap
	 * 
	 * @param bke537
	 *            开关化名
	 * @return true开 false关
	 */
	public boolean isonoff(SwitchesMap switchesMap, String bke537);

	/**
	 * 
	 * @param switchesMap
	 * @param bke537
	 *            开关化名
	 * @return true开 false关
	 */
	@SuppressWarnings("rawtypes")
	public boolean isonoff(Map switchesMap, String bke537);

	/**
	 * 
	 * @param mediDTO
	 * @param bke537
	 *            开关化名
	 * @return true开 false关
	 */
	public boolean isonoff(MediDTO mediDTO, String bke537);

	/**
	 * 
	 * @param inHospitalDTO
	 * @param bke537
	 *            开关化名
	 * @return true开 false关
	 */
	public boolean isonoff(InHospitalDTO inHospitalDTO, String bke537);
}
