package com.powersi.biz.medicare.hosp.service.db;

import java.util.List;

import com.powersi.biz.medicare.comm.pojo.ListResult;
import com.powersi.biz.medicare.hosp.pojo.Kzh0422Dto;
import com.powersi.biz.medicare.hosp.pojo.Kzh04Dto;

public interface Kzh04DBService {

	public Kzh04Dto selectByPrimaryKey(Integer id);

	public int deleteByPrimaryKey(Integer id);

	public List<Kzh04Dto> selectByKzh040(Kzh04Dto kzh04Dto);

	public int saveOrUpdateKzh04(Kzh04Dto kzh04Dto, List<Kzh0422Dto> kzh0422Dto);

	public List<Kzh0422Dto> selectByKzh0422(Kzh04Dto kzh04Dto);

	public ListResult selectByKzh0422PageResult(Kzh04Dto kzh04Dto);

	/***
	 * 删除
	 * 
	 * @param bkh015s
	 * @param aaa027
	 * @return
	 */
	public int saveDelKzh040(String bkh015s, String akb020, String type);

}