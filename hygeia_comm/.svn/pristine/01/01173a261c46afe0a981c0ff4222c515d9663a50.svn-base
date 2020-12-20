package com.powersi.biz.medicare.hosp.service.api;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.hosp.pojo.HospInfoDTO;
import com.powersi.biz.medicare.hosp.pojo.Kzh0422Dto;
import com.powersi.biz.medicare.hosp.pojo.Kzh04Dto;

/**
 * 医院管理
 * 
 * @author cl
 *
 */
public interface HospManagerService {

	/***
	 * 套餐
	 * 
	 * @param kzh04Dto
	 * @param kzh0422Dto
	 * @return
	 */
	public int saveOrUpdateFeeSetMeal(Kzh04Dto kzh04Dto, List<Kzh0422Dto> kzh0422Dto);

	/***
	 * 套餐
	 */
	public Kzh04Dto selectFeeSetMealByPrimaryKey(Kzh04Dto kzh04DtoP);

	/***
	 * 套餐
	 */
	public List<Kzh04Dto> selectFeeSetMealIndex(Kzh04Dto param);

	/***
	 * 套餐
	 */
	public List<?> loadPsTemplateSel(Kzh04Dto kzh04DtoP);

	/***
	 * 删除
	 * 
	 * @param bkh015s
	 * @param akb020
	 * @return
	 */
	public int saveDelKzh040(String bkh015s, String akb020, String type);

	/***
	 * 套餐,费用记账
	 */
	@SuppressWarnings("rawtypes")
	public List<Kzh04Dto> selectFeeSetMealFee(Kzh04Dto param, Map params);

	/***
	 * 套餐,费用记账,包装选择费用
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> createKzh04InputFee(Kzh04Dto param, Map params, List<Kzh04Dto> kzh04Dtos);

	/***
	 * 套餐
	 */
	public List<Kzh0422Dto> selectByKzh0422(Kzh04Dto kzh04Dto);
	
	/**
	 * TS18111900025 - 【需求开发】普通门诊首诊查询与修改
	 * @Description: 查询个人首诊医疗机构信息
	 * @author: xiexiao
	 * @date: 2018年11月19日
	 * @param hospInfoDto
	 * @return List
	 */
	public List queryPersFirstHosp(Map par);
	
	/**
	 * TS18111900025 - 【需求开发】普通门诊首诊查询与修改
	 * @Description: 修改个人首诊医疗机构信息
	 * @author: xiexiao
	 * @date: 2018年11月19日
	 * @param hospInfoDto
	 * @return int
	 */
	public int editPersFirstHosp(HospInfoDTO hospInfoDto);

}
