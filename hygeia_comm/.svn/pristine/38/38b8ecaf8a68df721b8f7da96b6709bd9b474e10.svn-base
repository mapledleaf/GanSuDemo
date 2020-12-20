package com.powersi.biz.medicare.diagnose.service;

import java.util.List;
import java.util.Map;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.diagnose.pojo.KF03DTO;
import com.powersi.biz.medicare.diagnose.pojo.KF04DTO;


public interface CatalogMatchImportService {

	public int saveBatchCharge(KF03DTO kf03DTO,List<KF04DTO> list);

	public ListResult queryCatalogMatch(KF03DTO kf03dto);
	
	public ListResult queryCataMatchDetatil(KF04DTO kf04dto);

	public int updateCataMatchDetatil(KF04DTO kf04dto);

	public int updateCatalogMatch(KF03DTO kf03dto);
		
	@SuppressWarnings("rawtypes")
	public List queryKae8(KF04DTO kf04dto);
	
	@SuppressWarnings("rawtypes")
	public List<Map> queryCataDetial(KF04DTO kf04dto);
	
	@SuppressWarnings("rawtypes")
	public List queryKf04(KF04DTO kf04dto);
	
}
