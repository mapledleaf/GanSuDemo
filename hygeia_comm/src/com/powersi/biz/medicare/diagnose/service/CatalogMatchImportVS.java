package com.powersi.biz.medicare.diagnose.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.diagnose.pojo.KF03DTO;
import com.powersi.biz.medicare.diagnose.pojo.KF04DTO;

public interface CatalogMatchImportVS {

	/**
	 * 生产匹配数据（珠海目录匹配数据批量导入）消息
	 */
	public String MQ_HYGEIA_ESB_CATALOGMATCH = "MQ_HYGEIA_ESB_CATALOGMATCH";
	
	/**
	 *珠海目录匹配数据批量导入 
	 *保存到kf03表
	 * 2017年11月23日 
	 * @param KF03DTO
	 * @author lhy
	 */
	public int saveCatalogMatch(KF03DTO kf03DTO, List<KF04DTO> list);
	
	/**
	 *珠海目录匹配数据批量导入 
	 *查询kf03表
	 * 2017年11月23日 
	 * @param KF03DTO
	 * @author lhy
	 */
	public ListResult queryCatalogMatch(KF03DTO kf03dto);
	
	/**
	 *珠海目录匹配数据批量导入 
	 *查询kf04表
	 * 2017年11月23日 
	 * @param KF04DTO
	 * @author lhy
	 */
	public ListResult queryCataMatchDetatil(KF04DTO kf04dto);
	
	/**
	 *珠海目录匹配数据批量导入 
	 *异步校验时查询kf04表（未校验的数据）
	 * 2017年11月27日 
	 * @param KF04DTO
	 * @author lhy
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryCataDetial(KF04DTO kf04dto);
	
	public int updateCataMatchDetatil(KF04DTO kf04dto);

	public int updateCatalogMatch(KF03DTO kf03dto);
	
	@SuppressWarnings("rawtypes")
	public  List queryKae8(KF04DTO kf04dto);
	
	public  List<KF04DTO> queryKad5(KF04DTO kf04dto);
	
	@SuppressWarnings("rawtypes")
	public List queryKf04(KF04DTO kf04dto);
	
	/**
	 * 重新校验
	 * 
	 * @author lhy
	 * 2017年12月04日 
	 * @param kf03DTO
	 *            
	 */
	public void velidDate(KF03DTO kf03DTO);
}
