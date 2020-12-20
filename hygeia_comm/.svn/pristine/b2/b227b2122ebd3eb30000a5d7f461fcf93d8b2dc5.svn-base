package com.powersi.biz.medicare.diagnose.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.diagnose.pojo.Kf01DTO;
import com.powersi.biz.medicare.diagnose.pojo.Kf02DTO;

public interface DiagnoseBatchChargeService {

	public int saveBatchCharge(Kf01DTO kf01DTO, List<Kf02DTO> list);

	@SuppressWarnings("rawtypes")
	public List<Map> queryBatchCharge(Kf01DTO kf01DTO);

	@SuppressWarnings("rawtypes")
	public List<Map> queryDetatilCharge(Kf02DTO kf02DTO);

	public int updateDetatilCharge(Kf02DTO kf02DTO);

	public int updateCharge(Kf01DTO kf01DTO);

}
