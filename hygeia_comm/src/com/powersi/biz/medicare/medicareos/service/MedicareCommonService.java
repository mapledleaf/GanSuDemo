package com.powersi.biz.medicare.medicareos.service;

import java.io.Serializable;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.KAE8;
import com.powersi.biz.medicare.medicareos.dto.MedicareosDTO;

/**
 * 
 * @author 刘勇
 *
 */
public interface MedicareCommonService extends Serializable {

	/**
	 * 
	 * @param medicareosDTO
	 * @param kae8
	 * @param kae8Map
	 * @param kad5Map
	 */
	@SuppressWarnings("rawtypes")
	public void loadFeeRowBka511Bka512(MedicareosDTO medicareosDTO, KAE8 kae8, Map kae8Map, Map kad5Map);

}
