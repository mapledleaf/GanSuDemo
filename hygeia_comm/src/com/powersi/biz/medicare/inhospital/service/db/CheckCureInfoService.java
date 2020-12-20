package com.powersi.biz.medicare.inhospital.service.db;


import java.util.List;

import com.powersi.biz.medicare.inhospital.pojo.CheckCureInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.DrugWindowInfoDTO;
import com.powersi.biz.medicare.inhospital.pojo.KE14;
import com.powersi.biz.medicare.pay.pojo.PayInfo;

/**
 * 
  * @Description: 检查诊疗信息业务db接口类
  * @author zhos
  * @date 2016年12月7日 上午9:44:17
  *
 */
public interface CheckCureInfoService {

	/**
	 * 
	  * 上传检查诊疗结果
	  * @dto 入参对象
	 */
	public int saveCheckCureInfo(CheckCureInfoDTO dto);
	
	/**
	 * 
	 * 查询检查诊疗结果
	 * @dto 入参对象
	 */
	public List<KE14> queryCheckCureInfos(CheckCureInfoDTO dto);
	
	public KE14 queryKE14(CheckCureInfoDTO dto);
	
	
	/**
	 * 上传取药窗口信息
	 */
	public int saveDrugWindowInfo(DrugWindowInfoDTO drugWindowInfoDTO);
	
	/**
	 * 修改取药信息为无效
	 * @param drugWindowInfoDTO
	 * @return
	 */
	public int updateDrugWindowInfo(DrugWindowInfoDTO drugWindowInfoDTO);
	
	/**
	 * 删除取药信息
	 * @param drugWindowInfoDTO
	 * @return
	 */
	public int deleteDrugWindowInfo(DrugWindowInfoDTO drugWindowInfoDTO);
	
	/**
	 * 移动支付业务，修改kcd1的bka018=app-api
	 * @param pay
	 * @return
	 */
	public int updateBizByBka018(PayInfo pay);
}
